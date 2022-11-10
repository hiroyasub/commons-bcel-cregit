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
name|classfile
package|;
end_package

begin_import
import|import static
name|org
operator|.
name|junit
operator|.
name|jupiter
operator|.
name|api
operator|.
name|Assertions
operator|.
name|assertDoesNotThrow
import|;
end_import

begin_import
import|import static
name|org
operator|.
name|junit
operator|.
name|jupiter
operator|.
name|api
operator|.
name|Assertions
operator|.
name|assertEquals
import|;
end_import

begin_import
import|import
name|java
operator|.
name|io
operator|.
name|ByteArrayOutputStream
import|;
end_import

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
name|DataOutputStream
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
name|org
operator|.
name|apache
operator|.
name|bcel
operator|.
name|generic
operator|.
name|JavaHome
import|;
end_import

begin_import
import|import
name|org
operator|.
name|junit
operator|.
name|jupiter
operator|.
name|api
operator|.
name|Test
import|;
end_import

begin_import
import|import
name|org
operator|.
name|junit
operator|.
name|jupiter
operator|.
name|params
operator|.
name|ParameterizedTest
import|;
end_import

begin_import
import|import
name|org
operator|.
name|junit
operator|.
name|jupiter
operator|.
name|params
operator|.
name|provider
operator|.
name|MethodSource
import|;
end_import

begin_comment
comment|/**  * Test that dump() methods work on the JDK classes  */
end_comment

begin_class
specifier|public
class|class
name|JDKClassDumpTestCase
block|{
specifier|private
name|void
name|compare
parameter_list|(
specifier|final
name|JavaClass
name|jc
parameter_list|,
specifier|final
name|InputStream
name|inputStream
parameter_list|,
specifier|final
name|String
name|name
parameter_list|)
throws|throws
name|Exception
block|{
specifier|final
name|ByteArrayOutputStream
name|baos
init|=
operator|new
name|ByteArrayOutputStream
argument_list|()
decl_stmt|;
try|try
init|(
name|DataOutputStream
name|dos
init|=
operator|new
name|DataOutputStream
argument_list|(
name|baos
argument_list|)
init|)
block|{
name|jc
operator|.
name|dump
argument_list|(
name|dos
argument_list|)
expr_stmt|;
block|}
try|try
init|(
name|DataInputStream
name|src
init|=
operator|new
name|DataInputStream
argument_list|(
name|inputStream
argument_list|)
init|)
block|{
name|int
name|i
init|=
literal|0
decl_stmt|;
for|for
control|(
specifier|final
name|int
name|out
range|:
name|baos
operator|.
name|toByteArray
argument_list|()
control|)
block|{
specifier|final
name|int
name|in
init|=
name|src
operator|.
name|read
argument_list|()
decl_stmt|;
specifier|final
name|int
name|j
init|=
name|i
decl_stmt|;
name|assertEquals
argument_list|(
name|in
argument_list|,
name|out
operator|&
literal|0xFF
argument_list|,
parameter_list|()
lambda|->
name|name
operator|+
literal|": Mismatch at "
operator|+
name|j
argument_list|)
expr_stmt|;
name|i
operator|++
expr_stmt|;
block|}
block|}
block|}
specifier|private
name|void
name|testJar
parameter_list|(
specifier|final
name|Path
name|path
parameter_list|)
throws|throws
name|Exception
block|{
try|try
init|(
name|JarFile
name|jar
init|=
operator|new
name|JarFile
argument_list|(
name|path
operator|.
name|toFile
argument_list|()
argument_list|)
init|)
block|{
name|System
operator|.
name|out
operator|.
name|println
argument_list|(
literal|"Parsing "
operator|+
name|jar
operator|.
name|getName
argument_list|()
argument_list|)
expr_stmt|;
specifier|final
name|Enumeration
argument_list|<
name|JarEntry
argument_list|>
name|en
init|=
name|jar
operator|.
name|entries
argument_list|()
decl_stmt|;
while|while
condition|(
name|en
operator|.
name|hasMoreElements
argument_list|()
condition|)
block|{
specifier|final
name|JarEntry
name|e
init|=
name|en
operator|.
name|nextElement
argument_list|()
decl_stmt|;
specifier|final
name|String
name|name
init|=
name|e
operator|.
name|getName
argument_list|()
decl_stmt|;
if|if
condition|(
name|name
operator|.
name|endsWith
argument_list|(
literal|".class"
argument_list|)
condition|)
block|{
comment|// System.out.println("Parsing " + name);
try|try
init|(
name|InputStream
name|inputStream1
init|=
name|jar
operator|.
name|getInputStream
argument_list|(
name|e
argument_list|)
init|;
name|InputStream
name|inputStream2
init|=
name|jar
operator|.
name|getInputStream
argument_list|(
name|e
argument_list|)
init|;
init|)
block|{
name|compare
argument_list|(
operator|new
name|ClassParser
argument_list|(
name|inputStream1
argument_list|,
name|name
argument_list|)
operator|.
name|parse
argument_list|()
argument_list|,
name|inputStream2
argument_list|,
name|name
argument_list|)
expr_stmt|;
block|}
block|}
block|}
block|}
block|}
annotation|@
name|ParameterizedTest
annotation|@
name|MethodSource
argument_list|(
literal|"org.apache.bcel.generic.JavaHome#streamJarPath"
argument_list|)
specifier|public
name|void
name|testPerformance
parameter_list|(
specifier|final
name|Path
name|path
parameter_list|)
throws|throws
name|Exception
block|{
name|assertDoesNotThrow
argument_list|(
parameter_list|()
lambda|->
name|testJar
argument_list|(
name|path
argument_list|)
argument_list|)
expr_stmt|;
block|}
annotation|@
name|Test
specifier|public
name|void
name|testPerformanceJmod
parameter_list|()
throws|throws
name|Exception
block|{
name|JavaHome
operator|.
name|streamModulePath
argument_list|()
operator|.
name|forEach
argument_list|(
name|path
lambda|->
name|assertDoesNotThrow
argument_list|(
parameter_list|()
lambda|->
name|testJar
argument_list|(
name|path
argument_list|)
argument_list|)
argument_list|)
expr_stmt|;
block|}
block|}
end_class

end_unit

