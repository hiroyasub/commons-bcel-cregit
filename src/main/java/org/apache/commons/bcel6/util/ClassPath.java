begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
begin_comment
comment|/*  * Licensed to the Apache Software Foundation (ASF) under one or more  * contributor license agreements.  See the NOTICE file distributed with  * this work for additional information regarding copyright ownership.  * The ASF licenses this file to You under the Apache License, Version 2.0  * (the "License"); you may not use this file except in compliance with  * the License.  You may obtain a copy of the License at  *  *      http://www.apache.org/licenses/LICENSE-2.0  *  *  Unless required by applicable law or agreed to in writing, software  *  distributed under the License is distributed on an "AS IS" BASIS,  *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  *  See the License for the specific language governing permissions and  *  limitations under the License.  *  */
end_comment

begin_package
package|package
name|org
operator|.
name|apache
operator|.
name|commons
operator|.
name|bcel6
operator|.
name|util
package|;
end_package

begin_import
import|import
name|java
operator|.
name|io
operator|.
name|DataInputStream
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
name|FileInputStream
import|;
end_import

begin_import
import|import
name|java
operator|.
name|io
operator|.
name|FilenameFilter
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
name|InputStream
import|;
end_import

begin_import
import|import
name|java
operator|.
name|net
operator|.
name|MalformedURLException
import|;
end_import

begin_import
import|import
name|java
operator|.
name|net
operator|.
name|URL
import|;
end_import

begin_import
import|import
name|java
operator|.
name|util
operator|.
name|ArrayList
import|;
end_import

begin_import
import|import
name|java
operator|.
name|util
operator|.
name|Enumeration
import|;
end_import

begin_import
import|import
name|java
operator|.
name|util
operator|.
name|List
import|;
end_import

begin_import
import|import
name|java
operator|.
name|util
operator|.
name|Locale
import|;
end_import

begin_import
import|import
name|java
operator|.
name|util
operator|.
name|StringTokenizer
import|;
end_import

begin_import
import|import
name|java
operator|.
name|util
operator|.
name|Vector
import|;
end_import

begin_import
import|import
name|java
operator|.
name|util
operator|.
name|zip
operator|.
name|ZipEntry
import|;
end_import

begin_import
import|import
name|java
operator|.
name|util
operator|.
name|zip
operator|.
name|ZipFile
import|;
end_import

begin_comment
comment|/**  * Responsible for loading (class) files from the CLASSPATH. Inspired by  * sun.tools.ClassPath.  *  * @version $Id$  */
end_comment

begin_class
specifier|public
class|class
name|ClassPath
block|{
specifier|public
specifier|static
specifier|final
name|ClassPath
name|SYSTEM_CLASS_PATH
init|=
operator|new
name|ClassPath
argument_list|(
name|getClassPath
argument_list|()
argument_list|)
decl_stmt|;
specifier|private
specifier|static
specifier|final
name|FilenameFilter
name|ARCHIVE_FILTER
init|=
operator|new
name|FilenameFilter
argument_list|()
block|{
annotation|@
name|Override
specifier|public
name|boolean
name|accept
parameter_list|(
name|File
name|dir
parameter_list|,
name|String
name|name
parameter_list|)
block|{
name|name
operator|=
name|name
operator|.
name|toLowerCase
argument_list|(
name|Locale
operator|.
name|ENGLISH
argument_list|)
expr_stmt|;
return|return
name|name
operator|.
name|endsWith
argument_list|(
literal|".zip"
argument_list|)
operator|||
name|name
operator|.
name|endsWith
argument_list|(
literal|".jar"
argument_list|)
return|;
block|}
block|}
decl_stmt|;
specifier|private
specifier|final
name|PathEntry
index|[]
name|paths
decl_stmt|;
specifier|private
specifier|final
name|String
name|class_path
decl_stmt|;
specifier|private
name|ClassPath
name|parent
decl_stmt|;
specifier|public
name|ClassPath
parameter_list|(
name|ClassPath
name|parent
parameter_list|,
name|String
name|class_path
parameter_list|)
block|{
name|this
argument_list|(
name|class_path
argument_list|)
expr_stmt|;
name|this
operator|.
name|parent
operator|=
name|parent
expr_stmt|;
block|}
comment|/**      * Search for classes in given path.      *       * @param class_path      */
specifier|public
name|ClassPath
parameter_list|(
name|String
name|class_path
parameter_list|)
block|{
name|this
operator|.
name|class_path
operator|=
name|class_path
expr_stmt|;
name|List
argument_list|<
name|PathEntry
argument_list|>
name|vec
init|=
operator|new
name|ArrayList
argument_list|<>
argument_list|()
decl_stmt|;
for|for
control|(
name|StringTokenizer
name|tok
init|=
operator|new
name|StringTokenizer
argument_list|(
name|class_path
argument_list|,
name|File
operator|.
name|pathSeparator
argument_list|)
init|;
name|tok
operator|.
name|hasMoreTokens
argument_list|()
condition|;
control|)
block|{
name|String
name|path
init|=
name|tok
operator|.
name|nextToken
argument_list|()
decl_stmt|;
if|if
condition|(
operator|!
name|path
operator|.
name|equals
argument_list|(
literal|""
argument_list|)
condition|)
block|{
name|File
name|file
init|=
operator|new
name|File
argument_list|(
name|path
argument_list|)
decl_stmt|;
try|try
block|{
if|if
condition|(
name|file
operator|.
name|exists
argument_list|()
condition|)
block|{
if|if
condition|(
name|file
operator|.
name|isDirectory
argument_list|()
condition|)
block|{
name|vec
operator|.
name|add
argument_list|(
operator|new
name|Dir
argument_list|(
name|path
argument_list|)
argument_list|)
expr_stmt|;
block|}
else|else
block|{
name|vec
operator|.
name|add
argument_list|(
operator|new
name|Zip
argument_list|(
operator|new
name|ZipFile
argument_list|(
name|file
argument_list|)
argument_list|)
argument_list|)
expr_stmt|;
block|}
block|}
block|}
catch|catch
parameter_list|(
name|IOException
name|e
parameter_list|)
block|{
if|if
condition|(
name|path
operator|.
name|endsWith
argument_list|(
literal|".zip"
argument_list|)
operator|||
name|path
operator|.
name|endsWith
argument_list|(
literal|".jar"
argument_list|)
condition|)
block|{
name|System
operator|.
name|err
operator|.
name|println
argument_list|(
literal|"CLASSPATH component "
operator|+
name|file
operator|+
literal|": "
operator|+
name|e
argument_list|)
expr_stmt|;
block|}
block|}
block|}
block|}
name|paths
operator|=
operator|new
name|PathEntry
index|[
name|vec
operator|.
name|size
argument_list|()
index|]
expr_stmt|;
name|vec
operator|.
name|toArray
argument_list|(
name|paths
argument_list|)
expr_stmt|;
block|}
comment|/** @return used class path string      */
annotation|@
name|Override
specifier|public
name|String
name|toString
parameter_list|()
block|{
if|if
condition|(
name|parent
operator|!=
literal|null
condition|)
block|{
return|return
name|parent
operator|.
name|toString
argument_list|()
operator|+
name|File
operator|.
name|pathSeparator
operator|+
name|class_path
return|;
block|}
return|return
name|class_path
return|;
block|}
annotation|@
name|Override
specifier|public
name|int
name|hashCode
parameter_list|()
block|{
if|if
condition|(
name|parent
operator|!=
literal|null
condition|)
block|{
return|return
name|class_path
operator|.
name|hashCode
argument_list|()
operator|+
name|parent
operator|.
name|hashCode
argument_list|()
return|;
block|}
return|return
name|class_path
operator|.
name|hashCode
argument_list|()
return|;
block|}
annotation|@
name|Override
specifier|public
name|boolean
name|equals
parameter_list|(
name|Object
name|o
parameter_list|)
block|{
if|if
condition|(
name|o
operator|instanceof
name|ClassPath
condition|)
block|{
name|ClassPath
name|cp
init|=
operator|(
name|ClassPath
operator|)
name|o
decl_stmt|;
return|return
name|class_path
operator|.
name|equals
argument_list|(
name|cp
operator|.
name|toString
argument_list|()
argument_list|)
return|;
block|}
return|return
literal|false
return|;
block|}
specifier|private
specifier|static
name|void
name|getPathComponents
parameter_list|(
name|String
name|path
parameter_list|,
name|List
argument_list|<
name|String
argument_list|>
name|list
parameter_list|)
block|{
if|if
condition|(
name|path
operator|!=
literal|null
condition|)
block|{
name|StringTokenizer
name|tok
init|=
operator|new
name|StringTokenizer
argument_list|(
name|path
argument_list|,
name|File
operator|.
name|pathSeparator
argument_list|)
decl_stmt|;
while|while
condition|(
name|tok
operator|.
name|hasMoreTokens
argument_list|()
condition|)
block|{
name|String
name|name
init|=
name|tok
operator|.
name|nextToken
argument_list|()
decl_stmt|;
name|File
name|file
init|=
operator|new
name|File
argument_list|(
name|name
argument_list|)
decl_stmt|;
if|if
condition|(
name|file
operator|.
name|exists
argument_list|()
condition|)
block|{
name|list
operator|.
name|add
argument_list|(
name|name
argument_list|)
expr_stmt|;
block|}
block|}
block|}
block|}
comment|/** Checks for class path components in the following properties:      * "java.class.path", "sun.boot.class.path", "java.ext.dirs"      *      * @return class path as used by default by BCEL      */
comment|// @since 6.0 no longer final
specifier|public
specifier|static
name|String
name|getClassPath
parameter_list|()
block|{
name|String
name|class_path
init|=
name|System
operator|.
name|getProperty
argument_list|(
literal|"java.class.path"
argument_list|)
decl_stmt|;
name|String
name|boot_path
init|=
name|System
operator|.
name|getProperty
argument_list|(
literal|"sun.boot.class.path"
argument_list|)
decl_stmt|;
name|String
name|ext_path
init|=
name|System
operator|.
name|getProperty
argument_list|(
literal|"java.ext.dirs"
argument_list|)
decl_stmt|;
name|List
argument_list|<
name|String
argument_list|>
name|list
init|=
operator|new
name|ArrayList
argument_list|<>
argument_list|()
decl_stmt|;
name|getPathComponents
argument_list|(
name|class_path
argument_list|,
name|list
argument_list|)
expr_stmt|;
name|getPathComponents
argument_list|(
name|boot_path
argument_list|,
name|list
argument_list|)
expr_stmt|;
name|List
argument_list|<
name|String
argument_list|>
name|dirs
init|=
operator|new
name|ArrayList
argument_list|<>
argument_list|()
decl_stmt|;
name|getPathComponents
argument_list|(
name|ext_path
argument_list|,
name|dirs
argument_list|)
expr_stmt|;
for|for
control|(
name|String
name|d
range|:
name|dirs
control|)
block|{
name|File
name|ext_dir
init|=
operator|new
name|File
argument_list|(
name|d
argument_list|)
decl_stmt|;
name|String
index|[]
name|extensions
init|=
name|ext_dir
operator|.
name|list
argument_list|(
name|ARCHIVE_FILTER
argument_list|)
decl_stmt|;
if|if
condition|(
name|extensions
operator|!=
literal|null
condition|)
block|{
for|for
control|(
name|String
name|extension
range|:
name|extensions
control|)
block|{
name|list
operator|.
name|add
argument_list|(
name|ext_dir
operator|.
name|getPath
argument_list|()
operator|+
name|File
operator|.
name|separatorChar
operator|+
name|extension
argument_list|)
expr_stmt|;
block|}
block|}
block|}
name|StringBuilder
name|buf
init|=
operator|new
name|StringBuilder
argument_list|()
decl_stmt|;
name|String
name|separator
init|=
literal|""
decl_stmt|;
for|for
control|(
name|String
name|path
range|:
name|list
control|)
block|{
name|buf
operator|.
name|append
argument_list|(
name|separator
argument_list|)
expr_stmt|;
name|separator
operator|=
name|File
operator|.
name|pathSeparator
expr_stmt|;
name|buf
operator|.
name|append
argument_list|(
name|path
argument_list|)
expr_stmt|;
block|}
return|return
name|buf
operator|.
name|toString
argument_list|()
operator|.
name|intern
argument_list|()
return|;
block|}
comment|/**      * @param name fully qualified class name, e.g. java.lang.String      * @return input stream for class      */
specifier|public
name|InputStream
name|getInputStream
parameter_list|(
name|String
name|name
parameter_list|)
throws|throws
name|IOException
block|{
return|return
name|getInputStream
argument_list|(
name|name
operator|.
name|replace
argument_list|(
literal|'.'
argument_list|,
literal|'/'
argument_list|)
argument_list|,
literal|".class"
argument_list|)
return|;
block|}
comment|/**      * Return stream for class or resource on CLASSPATH.      *      * @param name fully qualified file name, e.g. java/lang/String      * @param suffix file name ends with suff, e.g. .java      * @return input stream for file on class path      */
specifier|public
name|InputStream
name|getInputStream
parameter_list|(
name|String
name|name
parameter_list|,
name|String
name|suffix
parameter_list|)
throws|throws
name|IOException
block|{
name|InputStream
name|is
init|=
literal|null
decl_stmt|;
try|try
block|{
name|is
operator|=
name|getClass
argument_list|()
operator|.
name|getClassLoader
argument_list|()
operator|.
name|getResourceAsStream
argument_list|(
name|name
operator|+
name|suffix
argument_list|)
expr_stmt|;
block|}
catch|catch
parameter_list|(
name|Exception
name|e
parameter_list|)
block|{
block|}
if|if
condition|(
name|is
operator|!=
literal|null
condition|)
block|{
return|return
name|is
return|;
block|}
return|return
name|getClassFile
argument_list|(
name|name
argument_list|,
name|suffix
argument_list|)
operator|.
name|getInputStream
argument_list|()
return|;
block|}
comment|/**      * @param name fully qualified resource name, e.g. java/lang/String.class      * @return InputStream supplying the resource, or null if no resource with that name.      * @since 6.0      */
specifier|public
name|InputStream
name|getResourceAsStream
parameter_list|(
name|String
name|name
parameter_list|)
block|{
for|for
control|(
name|PathEntry
name|path
range|:
name|paths
control|)
block|{
name|InputStream
name|is
decl_stmt|;
if|if
condition|(
operator|(
name|is
operator|=
name|path
operator|.
name|getResourceAsStream
argument_list|(
name|name
argument_list|)
operator|)
operator|!=
literal|null
condition|)
block|{
return|return
name|is
return|;
block|}
block|}
return|return
literal|null
return|;
block|}
comment|/**      * @param name fully qualified resource name, e.g. java/lang/String.class      * @return URL supplying the resource, or null if no resource with that name.      * @since 6.0      */
specifier|public
name|URL
name|getResource
parameter_list|(
name|String
name|name
parameter_list|)
block|{
for|for
control|(
name|PathEntry
name|path
range|:
name|paths
control|)
block|{
name|URL
name|url
decl_stmt|;
if|if
condition|(
operator|(
name|url
operator|=
name|path
operator|.
name|getResource
argument_list|(
name|name
argument_list|)
operator|)
operator|!=
literal|null
condition|)
block|{
return|return
name|url
return|;
block|}
block|}
return|return
literal|null
return|;
block|}
comment|/**      * @param name fully qualified resource name, e.g. java/lang/String.class      * @return An Enumeration of URLs supplying the resource, or an      * empty Enumeration if no resource with that name.      * @since 6.0      */
specifier|public
name|Enumeration
argument_list|<
name|URL
argument_list|>
name|getResources
parameter_list|(
name|String
name|name
parameter_list|)
block|{
name|Vector
argument_list|<
name|URL
argument_list|>
name|results
init|=
operator|new
name|Vector
argument_list|<>
argument_list|()
decl_stmt|;
for|for
control|(
name|PathEntry
name|path
range|:
name|paths
control|)
block|{
name|URL
name|url
decl_stmt|;
if|if
condition|(
operator|(
name|url
operator|=
name|path
operator|.
name|getResource
argument_list|(
name|name
argument_list|)
operator|)
operator|!=
literal|null
condition|)
block|{
name|results
operator|.
name|add
argument_list|(
name|url
argument_list|)
expr_stmt|;
block|}
block|}
return|return
name|results
operator|.
name|elements
argument_list|()
return|;
block|}
comment|/**      * @param name fully qualified file name, e.g. java/lang/String      * @param suffix file name ends with suff, e.g. .java      * @return class file for the java class      */
specifier|public
name|ClassFile
name|getClassFile
parameter_list|(
name|String
name|name
parameter_list|,
name|String
name|suffix
parameter_list|)
throws|throws
name|IOException
block|{
name|ClassFile
name|cf
init|=
literal|null
decl_stmt|;
if|if
condition|(
name|parent
operator|!=
literal|null
condition|)
block|{
name|cf
operator|=
name|parent
operator|.
name|getClassFileInternal
argument_list|(
name|name
argument_list|,
name|suffix
argument_list|)
expr_stmt|;
block|}
if|if
condition|(
name|cf
operator|==
literal|null
condition|)
block|{
name|cf
operator|=
name|getClassFileInternal
argument_list|(
name|name
argument_list|,
name|suffix
argument_list|)
expr_stmt|;
block|}
if|if
condition|(
name|cf
operator|!=
literal|null
condition|)
block|{
return|return
name|cf
return|;
block|}
throw|throw
operator|new
name|IOException
argument_list|(
literal|"Couldn't find: "
operator|+
name|name
operator|+
name|suffix
argument_list|)
throw|;
block|}
specifier|private
name|ClassFile
name|getClassFileInternal
parameter_list|(
name|String
name|name
parameter_list|,
name|String
name|suffix
parameter_list|)
throws|throws
name|IOException
block|{
for|for
control|(
name|PathEntry
name|path
range|:
name|paths
control|)
block|{
name|ClassFile
name|cf
init|=
name|path
operator|.
name|getClassFile
argument_list|(
name|name
argument_list|,
name|suffix
argument_list|)
decl_stmt|;
if|if
condition|(
name|cf
operator|!=
literal|null
condition|)
block|{
return|return
name|cf
return|;
block|}
block|}
return|return
literal|null
return|;
block|}
comment|/**      * @param name fully qualified class name, e.g. java.lang.String      * @return input stream for class      */
specifier|public
name|ClassFile
name|getClassFile
parameter_list|(
name|String
name|name
parameter_list|)
throws|throws
name|IOException
block|{
return|return
name|getClassFile
argument_list|(
name|name
argument_list|,
literal|".class"
argument_list|)
return|;
block|}
comment|/**      * @param name fully qualified file name, e.g. java/lang/String      * @param suffix file name ends with suffix, e.g. .java      * @return byte array for file on class path      */
specifier|public
name|byte
index|[]
name|getBytes
parameter_list|(
name|String
name|name
parameter_list|,
name|String
name|suffix
parameter_list|)
throws|throws
name|IOException
block|{
name|DataInputStream
name|dis
init|=
literal|null
decl_stmt|;
try|try
block|{
name|InputStream
name|is
init|=
name|getInputStream
argument_list|(
name|name
argument_list|,
name|suffix
argument_list|)
decl_stmt|;
if|if
condition|(
name|is
operator|==
literal|null
condition|)
block|{
throw|throw
operator|new
name|IOException
argument_list|(
literal|"Couldn't find: "
operator|+
name|name
operator|+
name|suffix
argument_list|)
throw|;
block|}
name|dis
operator|=
operator|new
name|DataInputStream
argument_list|(
name|is
argument_list|)
expr_stmt|;
name|byte
index|[]
name|bytes
init|=
operator|new
name|byte
index|[
name|is
operator|.
name|available
argument_list|()
index|]
decl_stmt|;
name|dis
operator|.
name|readFully
argument_list|(
name|bytes
argument_list|)
expr_stmt|;
return|return
name|bytes
return|;
block|}
finally|finally
block|{
if|if
condition|(
name|dis
operator|!=
literal|null
condition|)
block|{
name|dis
operator|.
name|close
argument_list|()
expr_stmt|;
block|}
block|}
block|}
comment|/**      * @return byte array for class      */
specifier|public
name|byte
index|[]
name|getBytes
parameter_list|(
name|String
name|name
parameter_list|)
throws|throws
name|IOException
block|{
return|return
name|getBytes
argument_list|(
name|name
argument_list|,
literal|".class"
argument_list|)
return|;
block|}
comment|/**      * @param name name of file to search for, e.g. java/lang/String.java      * @return full (canonical) path for file      */
specifier|public
name|String
name|getPath
parameter_list|(
name|String
name|name
parameter_list|)
throws|throws
name|IOException
block|{
name|int
name|index
init|=
name|name
operator|.
name|lastIndexOf
argument_list|(
literal|'.'
argument_list|)
decl_stmt|;
name|String
name|suffix
init|=
literal|""
decl_stmt|;
if|if
condition|(
name|index
operator|>
literal|0
condition|)
block|{
name|suffix
operator|=
name|name
operator|.
name|substring
argument_list|(
name|index
argument_list|)
expr_stmt|;
name|name
operator|=
name|name
operator|.
name|substring
argument_list|(
literal|0
argument_list|,
name|index
argument_list|)
expr_stmt|;
block|}
return|return
name|getPath
argument_list|(
name|name
argument_list|,
name|suffix
argument_list|)
return|;
block|}
comment|/**      * @param name name of file to search for, e.g. java/lang/String      * @param suffix file name suffix, e.g. .java      * @return full (canonical) path for file, if it exists      */
specifier|public
name|String
name|getPath
parameter_list|(
name|String
name|name
parameter_list|,
name|String
name|suffix
parameter_list|)
throws|throws
name|IOException
block|{
return|return
name|getClassFile
argument_list|(
name|name
argument_list|,
name|suffix
argument_list|)
operator|.
name|getPath
argument_list|()
return|;
block|}
specifier|private
specifier|static
specifier|abstract
class|class
name|PathEntry
block|{
specifier|abstract
name|ClassFile
name|getClassFile
parameter_list|(
name|String
name|name
parameter_list|,
name|String
name|suffix
parameter_list|)
throws|throws
name|IOException
function_decl|;
specifier|abstract
name|URL
name|getResource
parameter_list|(
name|String
name|name
parameter_list|)
function_decl|;
specifier|abstract
name|InputStream
name|getResourceAsStream
parameter_list|(
name|String
name|name
parameter_list|)
function_decl|;
block|}
comment|/** Contains information about file/ZIP entry of the Java class.      */
specifier|public
interface|interface
name|ClassFile
block|{
comment|/** @return input stream for class file.          */
specifier|public
specifier|abstract
name|InputStream
name|getInputStream
parameter_list|()
throws|throws
name|IOException
function_decl|;
comment|/** @return canonical path to class file.          */
specifier|public
specifier|abstract
name|String
name|getPath
parameter_list|()
function_decl|;
comment|/** @return base path of found class, i.e. class is contained relative          * to that path, which may either denote a directory, or zip file          */
specifier|public
specifier|abstract
name|String
name|getBase
parameter_list|()
function_decl|;
comment|/** @return modification time of class file.          */
specifier|public
specifier|abstract
name|long
name|getTime
parameter_list|()
function_decl|;
comment|/** @return size of class file.          */
specifier|public
specifier|abstract
name|long
name|getSize
parameter_list|()
function_decl|;
block|}
specifier|private
specifier|static
class|class
name|Dir
extends|extends
name|PathEntry
block|{
specifier|private
specifier|final
name|String
name|dir
decl_stmt|;
name|Dir
parameter_list|(
name|String
name|d
parameter_list|)
block|{
name|dir
operator|=
name|d
expr_stmt|;
block|}
annotation|@
name|Override
name|URL
name|getResource
parameter_list|(
name|String
name|name
parameter_list|)
block|{
comment|// Resource specification uses '/' whatever the platform
specifier|final
name|File
name|file
init|=
operator|new
name|File
argument_list|(
name|dir
operator|+
name|File
operator|.
name|separatorChar
operator|+
name|name
operator|.
name|replace
argument_list|(
literal|'/'
argument_list|,
name|File
operator|.
name|separatorChar
argument_list|)
argument_list|)
decl_stmt|;
try|try
block|{
return|return
name|file
operator|.
name|exists
argument_list|()
condition|?
name|file
operator|.
name|toURI
argument_list|()
operator|.
name|toURL
argument_list|()
else|:
literal|null
return|;
block|}
catch|catch
parameter_list|(
name|MalformedURLException
name|e
parameter_list|)
block|{
return|return
literal|null
return|;
block|}
block|}
annotation|@
name|Override
name|InputStream
name|getResourceAsStream
parameter_list|(
name|String
name|name
parameter_list|)
block|{
comment|// Resource specification uses '/' whatever the platform
specifier|final
name|File
name|file
init|=
operator|new
name|File
argument_list|(
name|dir
operator|+
name|File
operator|.
name|separatorChar
operator|+
name|name
operator|.
name|replace
argument_list|(
literal|'/'
argument_list|,
name|File
operator|.
name|separatorChar
argument_list|)
argument_list|)
decl_stmt|;
try|try
block|{
return|return
name|file
operator|.
name|exists
argument_list|()
condition|?
operator|new
name|FileInputStream
argument_list|(
name|file
argument_list|)
else|:
literal|null
return|;
block|}
catch|catch
parameter_list|(
name|IOException
name|e
parameter_list|)
block|{
return|return
literal|null
return|;
block|}
block|}
annotation|@
name|Override
name|ClassFile
name|getClassFile
parameter_list|(
name|String
name|name
parameter_list|,
name|String
name|suffix
parameter_list|)
throws|throws
name|IOException
block|{
specifier|final
name|File
name|file
init|=
operator|new
name|File
argument_list|(
name|dir
operator|+
name|File
operator|.
name|separatorChar
operator|+
name|name
operator|.
name|replace
argument_list|(
literal|'.'
argument_list|,
name|File
operator|.
name|separatorChar
argument_list|)
operator|+
name|suffix
argument_list|)
decl_stmt|;
return|return
name|file
operator|.
name|exists
argument_list|()
condition|?
operator|new
name|ClassFile
argument_list|()
block|{
annotation|@
name|Override
specifier|public
name|InputStream
name|getInputStream
parameter_list|()
throws|throws
name|IOException
block|{
return|return
operator|new
name|FileInputStream
argument_list|(
name|file
argument_list|)
return|;
block|}
annotation|@
name|Override
specifier|public
name|String
name|getPath
parameter_list|()
block|{
try|try
block|{
return|return
name|file
operator|.
name|getCanonicalPath
argument_list|()
return|;
block|}
catch|catch
parameter_list|(
name|IOException
name|e
parameter_list|)
block|{
return|return
literal|null
return|;
block|}
block|}
annotation|@
name|Override
specifier|public
name|long
name|getTime
parameter_list|()
block|{
return|return
name|file
operator|.
name|lastModified
argument_list|()
return|;
block|}
annotation|@
name|Override
specifier|public
name|long
name|getSize
parameter_list|()
block|{
return|return
name|file
operator|.
name|length
argument_list|()
return|;
block|}
annotation|@
name|Override
specifier|public
name|String
name|getBase
parameter_list|()
block|{
return|return
name|dir
return|;
block|}
block|}
else|:
literal|null
return|;
block|}
annotation|@
name|Override
specifier|public
name|String
name|toString
parameter_list|()
block|{
return|return
name|dir
return|;
block|}
block|}
specifier|private
specifier|static
class|class
name|Zip
extends|extends
name|PathEntry
block|{
specifier|private
specifier|final
name|ZipFile
name|zip
decl_stmt|;
name|Zip
parameter_list|(
name|ZipFile
name|z
parameter_list|)
block|{
name|zip
operator|=
name|z
expr_stmt|;
block|}
annotation|@
name|Override
name|URL
name|getResource
parameter_list|(
name|String
name|name
parameter_list|)
block|{
specifier|final
name|ZipEntry
name|entry
init|=
name|zip
operator|.
name|getEntry
argument_list|(
name|name
argument_list|)
decl_stmt|;
try|try
block|{
return|return
operator|(
name|entry
operator|!=
literal|null
operator|)
condition|?
operator|new
name|URL
argument_list|(
literal|"jar:file:"
operator|+
name|zip
operator|.
name|getName
argument_list|()
operator|+
literal|"!/"
operator|+
name|name
argument_list|)
else|:
literal|null
return|;
block|}
catch|catch
parameter_list|(
name|MalformedURLException
name|e
parameter_list|)
block|{
return|return
literal|null
return|;
block|}
block|}
annotation|@
name|Override
name|InputStream
name|getResourceAsStream
parameter_list|(
name|String
name|name
parameter_list|)
block|{
specifier|final
name|ZipEntry
name|entry
init|=
name|zip
operator|.
name|getEntry
argument_list|(
name|name
argument_list|)
decl_stmt|;
try|try
block|{
return|return
operator|(
name|entry
operator|!=
literal|null
operator|)
condition|?
name|zip
operator|.
name|getInputStream
argument_list|(
name|entry
argument_list|)
else|:
literal|null
return|;
block|}
catch|catch
parameter_list|(
name|IOException
name|e
parameter_list|)
block|{
return|return
literal|null
return|;
block|}
block|}
annotation|@
name|Override
name|ClassFile
name|getClassFile
parameter_list|(
name|String
name|name
parameter_list|,
name|String
name|suffix
parameter_list|)
throws|throws
name|IOException
block|{
specifier|final
name|ZipEntry
name|entry
init|=
name|zip
operator|.
name|getEntry
argument_list|(
name|name
operator|.
name|replace
argument_list|(
literal|'.'
argument_list|,
literal|'/'
argument_list|)
operator|+
name|suffix
argument_list|)
decl_stmt|;
if|if
condition|(
name|entry
operator|==
literal|null
condition|)
block|{
return|return
literal|null
return|;
block|}
return|return
operator|new
name|ClassFile
argument_list|()
block|{
annotation|@
name|Override
specifier|public
name|InputStream
name|getInputStream
parameter_list|()
throws|throws
name|IOException
block|{
return|return
name|zip
operator|.
name|getInputStream
argument_list|(
name|entry
argument_list|)
return|;
block|}
annotation|@
name|Override
specifier|public
name|String
name|getPath
parameter_list|()
block|{
return|return
name|entry
operator|.
name|toString
argument_list|()
return|;
block|}
annotation|@
name|Override
specifier|public
name|long
name|getTime
parameter_list|()
block|{
return|return
name|entry
operator|.
name|getTime
argument_list|()
return|;
block|}
annotation|@
name|Override
specifier|public
name|long
name|getSize
parameter_list|()
block|{
return|return
name|entry
operator|.
name|getSize
argument_list|()
return|;
block|}
annotation|@
name|Override
specifier|public
name|String
name|getBase
parameter_list|()
block|{
return|return
name|zip
operator|.
name|getName
argument_list|()
return|;
block|}
block|}
return|;
block|}
block|}
block|}
end_class

end_unit

