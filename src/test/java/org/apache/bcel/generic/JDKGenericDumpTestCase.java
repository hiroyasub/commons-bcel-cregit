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
name|org
operator|.
name|junit
operator|.
name|Assert
operator|.
name|assertArrayEquals
import|;
end_import

begin_import
import|import static
name|org
operator|.
name|junit
operator|.
name|Assert
operator|.
name|fail
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
name|FileFilter
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
name|classfile
operator|.
name|ClassParser
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
name|Code
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
name|Method
import|;
end_import

begin_import
import|import
name|org
operator|.
name|junit
operator|.
name|Test
import|;
end_import

begin_comment
comment|/**  * Test that the generic dump() methods work on the JDK classes  * Reads each class into an instruction list and then dumps  * the instructions. The output bytes should be the same as the input.  */
end_comment

begin_class
specifier|public
class|class
name|JDKGenericDumpTestCase
block|{
annotation|@
name|Test
specifier|public
name|void
name|testJDKjars
parameter_list|()
throws|throws
name|Exception
block|{
name|File
index|[]
name|jars
init|=
name|listJDKjars
argument_list|()
decl_stmt|;
for|for
control|(
name|File
name|file
range|:
name|jars
control|)
block|{
name|testJar
argument_list|(
name|file
argument_list|)
expr_stmt|;
block|}
block|}
specifier|private
name|void
name|testJar
parameter_list|(
specifier|final
name|File
name|file
parameter_list|)
throws|throws
name|Exception
block|{
name|System
operator|.
name|out
operator|.
name|println
argument_list|(
name|file
argument_list|)
expr_stmt|;
name|JarFile
name|jar
init|=
operator|new
name|JarFile
argument_list|(
name|file
argument_list|)
decl_stmt|;
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
comment|//                System.out.println("- " + name);
name|InputStream
name|in
init|=
name|jar
operator|.
name|getInputStream
argument_list|(
name|e
argument_list|)
decl_stmt|;
name|ClassParser
name|parser
init|=
operator|new
name|ClassParser
argument_list|(
name|in
argument_list|,
name|name
argument_list|)
decl_stmt|;
name|JavaClass
name|jc
init|=
name|parser
operator|.
name|parse
argument_list|()
decl_stmt|;
for|for
control|(
name|Method
name|m
range|:
name|jc
operator|.
name|getMethods
argument_list|()
control|)
block|{
name|compare
argument_list|(
name|name
argument_list|,
name|m
argument_list|)
expr_stmt|;
block|}
block|}
block|}
name|jar
operator|.
name|close
argument_list|()
expr_stmt|;
block|}
specifier|private
name|void
name|compare
parameter_list|(
specifier|final
name|String
name|name
parameter_list|,
specifier|final
name|Method
name|m
parameter_list|)
block|{
comment|//        System.out.println("Method: " + m);
name|Code
name|c
init|=
name|m
operator|.
name|getCode
argument_list|()
decl_stmt|;
if|if
condition|(
name|c
operator|==
literal|null
condition|)
block|{
return|return;
comment|// e.g. abstract method
block|}
name|byte
index|[]
name|src
init|=
name|c
operator|.
name|getCode
argument_list|()
decl_stmt|;
name|InstructionList
name|il
init|=
operator|new
name|InstructionList
argument_list|(
name|src
argument_list|)
decl_stmt|;
name|byte
index|[]
name|out
init|=
name|il
operator|.
name|getByteCode
argument_list|()
decl_stmt|;
if|if
condition|(
name|src
operator|.
name|length
operator|==
name|out
operator|.
name|length
condition|)
block|{
name|assertArrayEquals
argument_list|(
name|name
operator|+
literal|": "
operator|+
name|m
operator|.
name|toString
argument_list|()
argument_list|,
name|src
argument_list|,
name|out
argument_list|)
expr_stmt|;
block|}
else|else
block|{
name|System
operator|.
name|out
operator|.
name|println
argument_list|(
name|name
operator|+
literal|": "
operator|+
name|m
operator|.
name|toString
argument_list|()
operator|+
literal|" "
operator|+
name|src
operator|.
name|length
operator|+
literal|" "
operator|+
name|out
operator|.
name|length
argument_list|)
expr_stmt|;
name|System
operator|.
name|out
operator|.
name|println
argument_list|(
name|bytesToHex
argument_list|(
name|src
argument_list|)
argument_list|)
expr_stmt|;
name|System
operator|.
name|out
operator|.
name|println
argument_list|(
name|bytesToHex
argument_list|(
name|out
argument_list|)
argument_list|)
expr_stmt|;
for|for
control|(
name|InstructionHandle
name|ih
range|:
name|il
control|)
block|{
name|System
operator|.
name|out
operator|.
name|println
argument_list|(
name|ih
operator|.
name|toString
argument_list|(
literal|false
argument_list|)
argument_list|)
expr_stmt|;
block|}
name|fail
argument_list|(
literal|"Array comparison failure"
argument_list|)
expr_stmt|;
block|}
block|}
specifier|private
name|File
index|[]
name|listJDKjars
parameter_list|()
throws|throws
name|Exception
block|{
name|File
name|javaLib
init|=
operator|new
name|File
argument_list|(
name|System
operator|.
name|getProperty
argument_list|(
literal|"java.home"
argument_list|)
operator|+
literal|"/lib"
argument_list|)
decl_stmt|;
return|return
name|javaLib
operator|.
name|listFiles
argument_list|(
operator|new
name|FileFilter
argument_list|()
block|{
annotation|@
name|Override
specifier|public
name|boolean
name|accept
parameter_list|(
specifier|final
name|File
name|file
parameter_list|)
block|{
return|return
name|file
operator|.
name|getName
argument_list|()
operator|.
name|endsWith
argument_list|(
literal|".jar"
argument_list|)
return|;
block|}
block|}
argument_list|)
return|;
block|}
specifier|private
specifier|static
specifier|final
name|char
index|[]
name|hexArray
init|=
literal|"0123456789ABCDEF"
operator|.
name|toCharArray
argument_list|()
decl_stmt|;
specifier|private
specifier|static
name|String
name|bytesToHex
parameter_list|(
specifier|final
name|byte
index|[]
name|bytes
parameter_list|)
block|{
name|char
index|[]
name|hexChars
init|=
operator|new
name|char
index|[
name|bytes
operator|.
name|length
operator|*
literal|3
index|]
decl_stmt|;
name|int
name|i
init|=
literal|0
decl_stmt|;
for|for
control|(
name|byte
name|b
range|:
name|bytes
control|)
block|{
name|int
name|v
init|=
name|b
operator|&
literal|0xFF
decl_stmt|;
name|hexChars
index|[
name|i
operator|++
index|]
operator|=
name|hexArray
index|[
name|v
operator|>>>
literal|4
index|]
expr_stmt|;
name|hexChars
index|[
name|i
operator|++
index|]
operator|=
name|hexArray
index|[
name|v
operator|&
literal|0x0F
index|]
expr_stmt|;
name|hexChars
index|[
name|i
operator|++
index|]
operator|=
literal|' '
expr_stmt|;
block|}
return|return
operator|new
name|String
argument_list|(
name|hexChars
argument_list|)
return|;
block|}
block|}
end_class

end_unit

