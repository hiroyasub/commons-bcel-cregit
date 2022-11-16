begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
begin_comment
comment|/*  * Licensed to the Apache Software Foundation (ASF) under one or more  * contributor license agreements.  See the NOTICE file distributed with  * this work for additional information regarding copyright ownership.  * The ASF licenses this file to You under the Apache License, Version 2.0  * (the "License"); you may not use this file except in compliance with  * the License.  You may obtain a copy of the License at  *  *   http://www.apache.org/licenses/LICENSE-2.0  *  * Unless required by applicable law or agreed to in writing, software  * distributed under the License is distributed on an "AS IS" BASIS,  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  * See the License for the specific language governing permissions and  * limitations under the License.  */
end_comment

begin_package
package|package
name|org
operator|.
name|apache
operator|.
name|bcel
operator|.
name|generic
package|;
end_package

begin_import
import|import static
name|com
operator|.
name|sun
operator|.
name|jna
operator|.
name|platform
operator|.
name|win32
operator|.
name|WinReg
operator|.
name|HKEY_LOCAL_MACHINE
import|;
end_import

begin_import
import|import
name|java
operator|.
name|io
operator|.
name|File
import|;
end_import

begin_import
import|import
name|java
operator|.
name|io
operator|.
name|IOException
import|;
end_import

begin_import
import|import
name|java
operator|.
name|io
operator|.
name|UncheckedIOException
import|;
end_import

begin_import
import|import
name|java
operator|.
name|nio
operator|.
name|file
operator|.
name|FileVisitOption
import|;
end_import

begin_import
import|import
name|java
operator|.
name|nio
operator|.
name|file
operator|.
name|Files
import|;
end_import

begin_import
import|import
name|java
operator|.
name|nio
operator|.
name|file
operator|.
name|Path
import|;
end_import

begin_import
import|import
name|java
operator|.
name|nio
operator|.
name|file
operator|.
name|Paths
import|;
end_import

begin_import
import|import
name|java
operator|.
name|nio
operator|.
name|file
operator|.
name|attribute
operator|.
name|BasicFileAttributes
import|;
end_import

begin_import
import|import
name|java
operator|.
name|util
operator|.
name|HashSet
import|;
end_import

begin_import
import|import
name|java
operator|.
name|util
operator|.
name|Objects
import|;
end_import

begin_import
import|import
name|java
operator|.
name|util
operator|.
name|Set
import|;
end_import

begin_import
import|import
name|java
operator|.
name|util
operator|.
name|function
operator|.
name|BiPredicate
import|;
end_import

begin_import
import|import
name|java
operator|.
name|util
operator|.
name|jar
operator|.
name|JarEntry
import|;
end_import

begin_import
import|import
name|java
operator|.
name|util
operator|.
name|jar
operator|.
name|JarFile
import|;
end_import

begin_import
import|import
name|java
operator|.
name|util
operator|.
name|stream
operator|.
name|Stream
import|;
end_import

begin_import
import|import
name|org
operator|.
name|apache
operator|.
name|bcel
operator|.
name|classfile
operator|.
name|JavaClass
import|;
end_import

begin_import
import|import
name|org
operator|.
name|apache
operator|.
name|bcel
operator|.
name|classfile
operator|.
name|Utility
import|;
end_import

begin_import
import|import
name|org
operator|.
name|apache
operator|.
name|bcel
operator|.
name|util
operator|.
name|ModularRuntimeImage
import|;
end_import

begin_import
import|import
name|org
operator|.
name|apache
operator|.
name|commons
operator|.
name|lang3
operator|.
name|StringUtils
import|;
end_import

begin_import
import|import
name|org
operator|.
name|apache
operator|.
name|commons
operator|.
name|lang3
operator|.
name|SystemUtils
import|;
end_import

begin_import
import|import
name|com
operator|.
name|sun
operator|.
name|jna
operator|.
name|platform
operator|.
name|win32
operator|.
name|Advapi32Util
import|;
end_import

begin_comment
comment|/**  * Used from {@code @MethodSource} for tests.  */
end_comment

begin_class
specifier|public
class|class
name|JavaHome
block|{
specifier|private
specifier|static
specifier|final
name|String
name|EXTRA_JAVA_HOMES
init|=
literal|"ExtraJavaHomes"
decl_stmt|;
comment|/** A folder containing Java homes, for example, on Windows "C:/Program Files/Eclipse Adoptium/" */
specifier|private
specifier|static
specifier|final
name|String
name|EXTRA_JAVA_ROOT
init|=
literal|"ExtraJavaRoot"
decl_stmt|;
comment|/** The default home for Java installs on Windows for Eclipse Adoptium. */
specifier|private
specifier|static
specifier|final
name|String
name|ADOPTIUM_WINDOWS
init|=
literal|"C:/Program Files/Eclipse Adoptium/"
decl_stmt|;
comment|/** The default home for Java installs on Windows for Eclipse Oracle. */
specifier|private
specifier|static
specifier|final
name|String
name|ORACLE_WINDOWS
init|=
literal|"C:/Program Files/Java/"
decl_stmt|;
specifier|private
specifier|static
specifier|final
name|String
name|EXTRA_JAVA_ROOT_DEFAULT
init|=
name|ADOPTIUM_WINDOWS
operator|+
name|File
operator|.
name|pathSeparator
operator|+
name|ORACLE_WINDOWS
decl_stmt|;
specifier|private
specifier|static
specifier|final
name|String
name|KEY_JDK
init|=
literal|"SOFTWARE\\JavaSoft\\Java Development Kit"
decl_stmt|;
specifier|private
specifier|static
specifier|final
name|String
name|KEY_JDK_9
init|=
literal|"SOFTWARE\\JavaSoft\\JDK"
decl_stmt|;
specifier|private
specifier|static
specifier|final
name|String
name|KEY_JRE
init|=
literal|"SOFTWARE\\JavaSoft\\Java Runtime Environment"
decl_stmt|;
specifier|private
specifier|static
specifier|final
name|String
name|KEY_JRE_9
init|=
literal|"SOFTWARE\\JavaSoft\\JRE"
decl_stmt|;
specifier|private
specifier|static
name|Stream
argument_list|<
name|Path
argument_list|>
name|find
parameter_list|(
specifier|final
name|Path
name|start
parameter_list|,
specifier|final
name|int
name|maxDepth
parameter_list|,
specifier|final
name|BiPredicate
argument_list|<
name|Path
argument_list|,
name|BasicFileAttributes
argument_list|>
name|matcher
parameter_list|,
specifier|final
name|FileVisitOption
modifier|...
name|options
parameter_list|)
block|{
try|try
block|{
comment|// TODO Replace with Apache Commons IO UncheckedFiles later.
return|return
name|Files
operator|.
name|exists
argument_list|(
name|start
argument_list|)
condition|?
name|Files
operator|.
name|find
argument_list|(
name|start
argument_list|,
name|maxDepth
argument_list|,
name|matcher
argument_list|,
name|options
argument_list|)
else|:
name|Stream
operator|.
name|empty
argument_list|()
return|;
block|}
catch|catch
parameter_list|(
specifier|final
name|IOException
name|e
parameter_list|)
block|{
throw|throw
operator|new
name|UncheckedIOException
argument_list|(
name|e
argument_list|)
throw|;
block|}
block|}
specifier|private
specifier|static
name|JavaHome
name|from
parameter_list|(
specifier|final
name|String
name|javaHome
parameter_list|)
block|{
return|return
operator|new
name|JavaHome
argument_list|(
name|Paths
operator|.
name|get
argument_list|(
name|javaHome
argument_list|)
argument_list|)
return|;
block|}
specifier|private
specifier|static
name|Stream
argument_list|<
name|String
argument_list|>
name|streamAllWindowsJavaHomes
parameter_list|(
specifier|final
name|String
name|keyJre
parameter_list|)
block|{
if|if
condition|(
name|Advapi32Util
operator|.
name|registryKeyExists
argument_list|(
name|HKEY_LOCAL_MACHINE
argument_list|,
name|keyJre
argument_list|)
condition|)
block|{
return|return
name|streamWindowsJavaHomes
argument_list|(
name|keyJre
argument_list|,
name|Advapi32Util
operator|.
name|registryGetKeys
argument_list|(
name|HKEY_LOCAL_MACHINE
argument_list|,
name|keyJre
argument_list|)
argument_list|)
return|;
block|}
return|return
name|Stream
operator|.
name|empty
argument_list|()
return|;
block|}
specifier|private
specifier|static
name|Stream
argument_list|<
name|String
argument_list|>
name|streamFromCustomKey
parameter_list|(
specifier|final
name|String
name|key
parameter_list|,
specifier|final
name|String
name|defaultValue
parameter_list|)
block|{
return|return
name|streamPropertyAndEnvVarValues
argument_list|(
name|key
argument_list|,
name|defaultValue
argument_list|)
operator|.
name|flatMap
argument_list|(
name|s
lambda|->
name|find
argument_list|(
name|Paths
operator|.
name|get
argument_list|(
name|s
argument_list|)
argument_list|,
literal|1
argument_list|,
parameter_list|(
name|p
parameter_list|,
name|a
parameter_list|)
lambda|->
name|Files
operator|.
name|isDirectory
argument_list|(
name|p
argument_list|)
argument_list|)
operator|.
name|map
argument_list|(
name|Path
operator|::
name|toString
argument_list|)
argument_list|)
return|;
block|}
specifier|private
specifier|static
name|Stream
argument_list|<
name|String
argument_list|>
name|streamFromCustomKeys
parameter_list|()
block|{
specifier|final
name|String
name|defaultRoot
init|=
name|SystemUtils
operator|.
name|IS_OS_WINDOWS
condition|?
name|EXTRA_JAVA_ROOT_DEFAULT
else|:
literal|null
decl_stmt|;
return|return
name|Stream
operator|.
name|concat
argument_list|(
name|streamPropertyAndEnvVarValues
argument_list|(
name|EXTRA_JAVA_HOMES
argument_list|,
literal|null
argument_list|)
argument_list|,
name|streamFromCustomKey
argument_list|(
name|EXTRA_JAVA_ROOT
argument_list|,
name|defaultRoot
argument_list|)
argument_list|)
return|;
block|}
comment|/**      * Used from {@code @MethodSource} for tests.      *      * @return a stream of JarFile.      */
specifier|public
specifier|static
name|Stream
argument_list|<
name|JarEntry
argument_list|>
name|streamJarEntry
parameter_list|()
block|{
return|return
name|streamJavaHome
argument_list|()
operator|.
name|flatMap
argument_list|(
name|JavaHome
operator|::
name|streamJarEntryByExt
argument_list|)
return|;
block|}
comment|/**      * Used from {@code @MethodSource} for tests.      *      * @return a stream of JarFile.      */
specifier|public
specifier|static
name|Stream
argument_list|<
name|JarEntry
argument_list|>
name|streamJarEntryClass
parameter_list|()
block|{
return|return
name|streamJavaHome
argument_list|()
operator|.
name|flatMap
argument_list|(
name|JavaHome
operator|::
name|streamJarEntryByExtClass
argument_list|)
return|;
block|}
comment|/**      * Used from {@code @MethodSource} for tests.      *      * @return a stream of JarFile.      */
specifier|public
specifier|static
name|Stream
argument_list|<
name|String
argument_list|>
name|streamJarEntryClassName
parameter_list|()
block|{
return|return
name|streamJavaHome
argument_list|()
operator|.
name|flatMap
argument_list|(
name|JavaHome
operator|::
name|streamJarEntryByExtClassName
argument_list|)
return|;
block|}
comment|/**      * Used from {@code @MethodSource} for tests.      *      * @return a stream of JarFile.      */
specifier|public
specifier|static
name|Stream
argument_list|<
name|JarFile
argument_list|>
name|streamJarFile
parameter_list|()
block|{
return|return
name|streamJavaHome
argument_list|()
operator|.
name|flatMap
argument_list|(
name|JavaHome
operator|::
name|streamJarFileByExt
argument_list|)
return|;
block|}
comment|/**      * Used from {@code @MethodSource} for tests.      *      * @return a stream of Java jar paths.      */
specifier|public
specifier|static
name|Stream
argument_list|<
name|Path
argument_list|>
name|streamJarPath
parameter_list|()
block|{
return|return
name|streamJavaHome
argument_list|()
operator|.
name|flatMap
argument_list|(
name|JavaHome
operator|::
name|streamJarPathByExt
argument_list|)
return|;
block|}
comment|/**      * Used from {@code @MethodSource} for tests.      *      * @return a stream of Java homes.      */
specifier|public
specifier|static
name|Stream
argument_list|<
name|JavaHome
argument_list|>
name|streamJavaHome
parameter_list|()
block|{
return|return
name|streamJavaHomeString
argument_list|()
operator|.
name|map
argument_list|(
name|JavaHome
operator|::
name|from
argument_list|)
return|;
block|}
comment|/**      * Used from {@code @MethodSource} for tests.      *      * @return a stream of Java homes.      */
specifier|public
specifier|static
name|Stream
argument_list|<
name|String
argument_list|>
name|streamJavaHomeString
parameter_list|()
block|{
specifier|final
name|Stream
argument_list|<
name|String
argument_list|>
name|streamW
init|=
name|SystemUtils
operator|.
name|IS_OS_WINDOWS
condition|?
name|streamWindowsStrings
argument_list|()
else|:
name|Stream
operator|.
name|empty
argument_list|()
decl_stmt|;
specifier|final
name|Stream
argument_list|<
name|String
argument_list|>
name|streamK
init|=
name|Stream
operator|.
name|concat
argument_list|(
name|streamW
argument_list|,
name|streamFromCustomKeys
argument_list|()
argument_list|)
decl_stmt|;
specifier|final
name|Stream
argument_list|<
name|String
argument_list|>
name|streamJ
init|=
name|StringUtils
operator|.
name|isEmpty
argument_list|(
name|SystemUtils
operator|.
name|JAVA_HOME
argument_list|)
condition|?
name|Stream
operator|.
name|empty
argument_list|()
else|:
name|Stream
operator|.
name|of
argument_list|(
name|SystemUtils
operator|.
name|JAVA_HOME
argument_list|)
decl_stmt|;
return|return
name|Stream
operator|.
name|concat
argument_list|(
name|streamK
argument_list|,
name|streamJ
argument_list|)
return|;
block|}
comment|/**      * Used from {@code @MethodSource} for tests.      *      * @return a stream of Java jar paths.      */
specifier|public
specifier|static
name|Stream
argument_list|<
name|ModularRuntimeImage
argument_list|>
name|streamModularRuntimeImage
parameter_list|()
block|{
return|return
name|streamJavaHome
argument_list|()
operator|.
name|map
argument_list|(
name|JavaHome
operator|::
name|getModularRuntimeImage
argument_list|)
return|;
block|}
comment|/**      * Used from {@code @MethodSource} for tests.      *      * @return a stream of Java jar paths.      */
specifier|public
specifier|static
name|Stream
argument_list|<
name|Path
argument_list|>
name|streamModulePath
parameter_list|()
block|{
return|return
name|streamJavaHome
argument_list|()
operator|.
name|flatMap
argument_list|(
name|JavaHome
operator|::
name|streamModuleByExt
argument_list|)
return|;
block|}
specifier|private
specifier|static
name|Stream
argument_list|<
name|String
argument_list|>
name|streamPropertyAndEnvVarValues
parameter_list|(
specifier|final
name|String
name|key
parameter_list|,
specifier|final
name|String
name|defaultValue
parameter_list|)
block|{
return|return
name|Stream
operator|.
name|concat
argument_list|(
name|toPathStringStream
argument_list|(
name|System
operator|.
name|getProperty
argument_list|(
name|key
argument_list|,
name|defaultValue
argument_list|)
argument_list|)
argument_list|,
name|toPathStringStream
argument_list|(
name|System
operator|.
name|getenv
argument_list|(
name|key
argument_list|)
argument_list|)
argument_list|)
return|;
block|}
specifier|private
specifier|static
name|Stream
argument_list|<
name|String
argument_list|>
name|streamWindowsJavaHomes
parameter_list|(
specifier|final
name|String
name|keyJavaHome
parameter_list|,
specifier|final
name|String
index|[]
name|keys
parameter_list|)
block|{
specifier|final
name|Set
argument_list|<
name|String
argument_list|>
name|javaHomes
init|=
operator|new
name|HashSet
argument_list|<>
argument_list|(
name|keys
operator|.
name|length
argument_list|)
decl_stmt|;
for|for
control|(
specifier|final
name|String
name|key
range|:
name|keys
control|)
block|{
if|if
condition|(
name|Advapi32Util
operator|.
name|registryKeyExists
argument_list|(
name|HKEY_LOCAL_MACHINE
argument_list|,
name|keyJavaHome
operator|+
literal|"\\"
operator|+
name|key
argument_list|)
condition|)
block|{
specifier|final
name|String
name|javaHome
init|=
name|Advapi32Util
operator|.
name|registryGetStringValue
argument_list|(
name|HKEY_LOCAL_MACHINE
argument_list|,
name|keyJavaHome
operator|+
literal|"\\"
operator|+
name|key
argument_list|,
literal|"JavaHome"
argument_list|)
decl_stmt|;
if|if
condition|(
name|StringUtils
operator|.
name|isNoneBlank
argument_list|(
name|javaHome
argument_list|)
operator|&&
operator|new
name|File
argument_list|(
name|javaHome
argument_list|)
operator|.
name|exists
argument_list|()
condition|)
block|{
name|javaHomes
operator|.
name|add
argument_list|(
name|javaHome
argument_list|)
expr_stmt|;
block|}
block|}
block|}
return|return
name|javaHomes
operator|.
name|stream
argument_list|()
return|;
block|}
specifier|private
specifier|static
name|Stream
argument_list|<
name|String
argument_list|>
name|streamWindowsStrings
parameter_list|()
block|{
return|return
name|Stream
operator|.
name|concat
argument_list|(
name|Stream
operator|.
name|of
argument_list|(
name|KEY_JRE
argument_list|,
name|KEY_JRE_9
argument_list|,
name|KEY_JDK
argument_list|,
name|KEY_JDK_9
argument_list|)
operator|.
name|flatMap
argument_list|(
name|JavaHome
operator|::
name|streamAllWindowsJavaHomes
argument_list|)
argument_list|,
name|streamPropertyAndEnvVarValues
argument_list|(
name|EXTRA_JAVA_HOMES
argument_list|,
literal|null
argument_list|)
argument_list|)
operator|.
name|distinct
argument_list|()
return|;
block|}
specifier|private
specifier|static
name|Stream
argument_list|<
name|String
argument_list|>
name|toPathStringStream
parameter_list|(
specifier|final
name|String
name|path
parameter_list|)
block|{
return|return
name|StringUtils
operator|.
name|isEmpty
argument_list|(
name|path
argument_list|)
condition|?
name|Stream
operator|.
name|empty
argument_list|()
else|:
name|Stream
operator|.
name|of
argument_list|(
name|path
operator|.
name|split
argument_list|(
name|File
operator|.
name|pathSeparator
argument_list|)
argument_list|)
return|;
block|}
specifier|private
specifier|final
name|Path
name|path
decl_stmt|;
specifier|private
name|JavaHome
parameter_list|(
specifier|final
name|Path
name|path
parameter_list|)
block|{
name|this
operator|.
name|path
operator|=
name|Objects
operator|.
name|requireNonNull
argument_list|(
name|path
argument_list|,
literal|"path"
argument_list|)
expr_stmt|;
block|}
name|Stream
argument_list|<
name|Path
argument_list|>
name|find
parameter_list|(
specifier|final
name|int
name|maxDepth
parameter_list|,
specifier|final
name|BiPredicate
argument_list|<
name|Path
argument_list|,
name|BasicFileAttributes
argument_list|>
name|matcher
parameter_list|,
specifier|final
name|FileVisitOption
modifier|...
name|options
parameter_list|)
block|{
return|return
name|find
argument_list|(
name|path
argument_list|,
name|maxDepth
argument_list|,
name|matcher
argument_list|,
name|options
argument_list|)
return|;
block|}
name|ModularRuntimeImage
name|getModularRuntimeImage
parameter_list|()
block|{
try|try
block|{
return|return
operator|new
name|ModularRuntimeImage
argument_list|(
name|path
operator|.
name|toString
argument_list|()
argument_list|)
return|;
block|}
catch|catch
parameter_list|(
specifier|final
name|IOException
name|e
parameter_list|)
block|{
throw|throw
operator|new
name|UncheckedIOException
argument_list|(
name|e
argument_list|)
throw|;
block|}
block|}
name|Path
name|getPath
parameter_list|()
block|{
return|return
name|path
return|;
block|}
specifier|private
name|Stream
argument_list|<
name|Path
argument_list|>
name|streamEndsWith
parameter_list|(
specifier|final
name|String
name|suffix
parameter_list|)
block|{
return|return
name|find
argument_list|(
literal|10
argument_list|,
parameter_list|(
name|p
parameter_list|,
name|a
parameter_list|)
lambda|->
name|p
operator|.
name|toString
argument_list|()
operator|.
name|endsWith
argument_list|(
name|suffix
argument_list|)
argument_list|)
return|;
block|}
specifier|private
name|Stream
argument_list|<
name|JarEntry
argument_list|>
name|streamJarEntryByExt
parameter_list|()
block|{
return|return
name|streamJarFileByExt
argument_list|()
operator|.
name|flatMap
argument_list|(
name|JarFile
operator|::
name|stream
argument_list|)
return|;
block|}
specifier|private
name|Stream
argument_list|<
name|JarEntry
argument_list|>
name|streamJarEntryByExtClass
parameter_list|()
block|{
return|return
name|streamJarEntryByExt
argument_list|()
operator|.
name|filter
argument_list|(
name|je
lambda|->
name|je
operator|.
name|getName
argument_list|()
operator|.
name|endsWith
argument_list|(
name|JavaClass
operator|.
name|EXTENSION
argument_list|)
argument_list|)
return|;
block|}
specifier|private
name|Stream
argument_list|<
name|String
argument_list|>
name|streamJarEntryByExtClassName
parameter_list|()
block|{
return|return
name|streamJarEntryByExtClass
argument_list|()
operator|.
name|map
argument_list|(
name|je
lambda|->
name|Utility
operator|.
name|pathToPackage
argument_list|(
name|je
operator|.
name|getName
argument_list|()
operator|.
name|substring
argument_list|(
literal|0
argument_list|,
name|je
operator|.
name|getName
argument_list|()
operator|.
name|indexOf
argument_list|(
name|JavaClass
operator|.
name|EXTENSION
argument_list|)
argument_list|)
argument_list|)
argument_list|)
return|;
block|}
specifier|private
name|Stream
argument_list|<
name|JarFile
argument_list|>
name|streamJarFileByExt
parameter_list|()
block|{
return|return
name|streamJarPathByExt
argument_list|()
operator|.
name|map
argument_list|(
name|this
operator|::
name|toJarFile
argument_list|)
return|;
block|}
specifier|private
name|Stream
argument_list|<
name|Path
argument_list|>
name|streamJarPathByExt
parameter_list|()
block|{
return|return
name|streamEndsWith
argument_list|(
literal|".jar"
argument_list|)
return|;
block|}
specifier|private
name|Stream
argument_list|<
name|Path
argument_list|>
name|streamModuleByExt
parameter_list|()
block|{
return|return
name|streamEndsWith
argument_list|(
literal|".jmod"
argument_list|)
return|;
block|}
specifier|private
name|JarFile
name|toJarFile
parameter_list|(
specifier|final
name|Path
name|path
parameter_list|)
block|{
try|try
block|{
return|return
operator|new
name|JarFile
argument_list|(
name|path
operator|.
name|toFile
argument_list|()
argument_list|)
return|;
block|}
catch|catch
parameter_list|(
specifier|final
name|IOException
name|e
parameter_list|)
block|{
throw|throw
operator|new
name|UncheckedIOException
argument_list|(
name|e
argument_list|)
throw|;
block|}
block|}
annotation|@
name|Override
specifier|public
name|String
name|toString
parameter_list|()
block|{
return|return
name|path
operator|.
name|toString
argument_list|()
return|;
block|}
block|}
end_class

end_unit

