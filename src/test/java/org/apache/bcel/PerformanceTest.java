begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
begin_comment
comment|/*  * Copyright  2000-2009 The Apache Software Foundation  *  *  Licensed under the Apache License, Version 2.0 (the "License");   *  you may not use this file except in compliance with the License.  *  You may obtain a copy of the License at  *  *      http://www.apache.org/licenses/LICENSE-2.0  *  *  Unless required by applicable law or agreed to in writing, software  *  distributed under the License is distributed on an "AS IS" BASIS,  *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  *  See the License for the specific language governing permissions and  *  limitations under the License.   *  */
end_comment

begin_package
package|package
name|org
operator|.
name|apache
operator|.
name|bcel
package|;
end_package

begin_import
import|import
name|java
operator|.
name|io
operator|.
name|ByteArrayInputStream
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
name|Locale
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
name|junit
operator|.
name|framework
operator|.
name|TestCase
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
name|apache
operator|.
name|bcel
operator|.
name|generic
operator|.
name|ClassGen
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
name|InstructionList
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
name|MethodGen
import|;
end_import

begin_class
specifier|public
specifier|final
class|class
name|PerformanceTest
extends|extends
name|TestCase
block|{
specifier|private
specifier|static
name|byte
index|[]
name|read
parameter_list|(
specifier|final
name|InputStream
name|is
parameter_list|)
throws|throws
name|IOException
block|{
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
literal|"Class not found"
argument_list|)
throw|;
block|}
name|byte
index|[]
name|b
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
name|int
name|len
init|=
literal|0
decl_stmt|;
while|while
condition|(
literal|true
condition|)
block|{
name|int
name|n
init|=
name|is
operator|.
name|read
argument_list|(
name|b
argument_list|,
name|len
argument_list|,
name|b
operator|.
name|length
operator|-
name|len
argument_list|)
decl_stmt|;
if|if
condition|(
name|n
operator|==
operator|-
literal|1
condition|)
block|{
if|if
condition|(
name|len
operator|<
name|b
operator|.
name|length
condition|)
block|{
name|byte
index|[]
name|c
init|=
operator|new
name|byte
index|[
name|len
index|]
decl_stmt|;
name|System
operator|.
name|arraycopy
argument_list|(
name|b
argument_list|,
literal|0
argument_list|,
name|c
argument_list|,
literal|0
argument_list|,
name|len
argument_list|)
expr_stmt|;
name|b
operator|=
name|c
expr_stmt|;
block|}
return|return
name|b
return|;
block|}
name|len
operator|+=
name|n
expr_stmt|;
if|if
condition|(
name|len
operator|==
name|b
operator|.
name|length
condition|)
block|{
name|byte
index|[]
name|c
init|=
operator|new
name|byte
index|[
name|b
operator|.
name|length
operator|+
literal|1000
index|]
decl_stmt|;
name|System
operator|.
name|arraycopy
argument_list|(
name|b
argument_list|,
literal|0
argument_list|,
name|c
argument_list|,
literal|0
argument_list|,
name|len
argument_list|)
expr_stmt|;
name|b
operator|=
name|c
expr_stmt|;
block|}
block|}
block|}
specifier|private
specifier|static
name|void
name|test
parameter_list|(
name|int
name|fraction
parameter_list|)
throws|throws
name|IOException
block|{
name|NanoTimer
name|total
init|=
operator|new
name|NanoTimer
argument_list|()
decl_stmt|;
name|NanoTimer
name|parseTime
init|=
operator|new
name|NanoTimer
argument_list|()
decl_stmt|;
name|NanoTimer
name|cgenTime
init|=
operator|new
name|NanoTimer
argument_list|()
decl_stmt|;
name|NanoTimer
name|mgenTime
init|=
operator|new
name|NanoTimer
argument_list|()
decl_stmt|;
name|NanoTimer
name|mserTime
init|=
operator|new
name|NanoTimer
argument_list|()
decl_stmt|;
name|NanoTimer
name|serTime
init|=
operator|new
name|NanoTimer
argument_list|()
decl_stmt|;
name|total
operator|.
name|start
argument_list|()
expr_stmt|;
name|String
name|javaHome
init|=
name|System
operator|.
name|getProperty
argument_list|(
literal|"java.home"
argument_list|)
decl_stmt|;
name|JarFile
name|jar
init|=
operator|new
name|JarFile
argument_list|(
name|javaHome
operator|+
literal|"/lib/dt.jar"
argument_list|)
decl_stmt|;
name|Enumeration
name|en
init|=
name|jar
operator|.
name|entries
argument_list|()
decl_stmt|;
name|int
name|i
init|=
literal|0
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
operator|(
name|JarEntry
operator|)
name|en
operator|.
name|nextElement
argument_list|()
decl_stmt|;
if|if
condition|(
name|e
operator|.
name|getName
argument_list|()
operator|.
name|endsWith
argument_list|(
literal|".class"
argument_list|)
operator|&&
name|i
operator|++
operator|%
name|fraction
operator|==
literal|0
condition|)
block|{
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
name|byte
index|[]
name|bytes
init|=
name|read
argument_list|(
name|in
argument_list|)
decl_stmt|;
name|parseTime
operator|.
name|start
argument_list|()
expr_stmt|;
name|JavaClass
name|clazz
init|=
operator|new
name|ClassParser
argument_list|(
operator|new
name|ByteArrayInputStream
argument_list|(
name|bytes
argument_list|)
argument_list|,
name|e
operator|.
name|getName
argument_list|()
argument_list|)
operator|.
name|parse
argument_list|()
decl_stmt|;
name|parseTime
operator|.
name|stop
argument_list|()
expr_stmt|;
name|cgenTime
operator|.
name|start
argument_list|()
expr_stmt|;
name|ClassGen
name|cg
init|=
operator|new
name|ClassGen
argument_list|(
name|clazz
argument_list|)
decl_stmt|;
name|cgenTime
operator|.
name|stop
argument_list|()
expr_stmt|;
name|Method
index|[]
name|methods
init|=
name|cg
operator|.
name|getMethods
argument_list|()
decl_stmt|;
for|for
control|(
name|int
name|j
init|=
literal|0
init|;
name|j
operator|<
name|methods
operator|.
name|length
condition|;
name|j
operator|++
control|)
block|{
name|Method
name|m
init|=
name|methods
index|[
name|j
index|]
decl_stmt|;
name|mgenTime
operator|.
name|start
argument_list|()
expr_stmt|;
name|MethodGen
name|mg
init|=
operator|new
name|MethodGen
argument_list|(
name|m
argument_list|,
name|cg
operator|.
name|getClassName
argument_list|()
argument_list|,
name|cg
operator|.
name|getConstantPool
argument_list|()
argument_list|)
decl_stmt|;
name|InstructionList
name|il
init|=
name|mg
operator|.
name|getInstructionList
argument_list|()
decl_stmt|;
name|mgenTime
operator|.
name|stop
argument_list|()
expr_stmt|;
name|mserTime
operator|.
name|start
argument_list|()
expr_stmt|;
if|if
condition|(
name|il
operator|!=
literal|null
condition|)
block|{
name|mg
operator|.
name|getInstructionList
argument_list|()
operator|.
name|setPositions
argument_list|()
expr_stmt|;
name|mg
operator|.
name|setMaxLocals
argument_list|()
expr_stmt|;
name|mg
operator|.
name|setMaxStack
argument_list|()
expr_stmt|;
block|}
name|cg
operator|.
name|replaceMethod
argument_list|(
name|m
argument_list|,
name|mg
operator|.
name|getMethod
argument_list|()
argument_list|)
expr_stmt|;
name|mserTime
operator|.
name|stop
argument_list|()
expr_stmt|;
block|}
name|serTime
operator|.
name|start
argument_list|()
expr_stmt|;
name|cg
operator|.
name|getJavaClass
argument_list|()
operator|.
name|getBytes
argument_list|()
expr_stmt|;
name|serTime
operator|.
name|stop
argument_list|()
expr_stmt|;
block|}
block|}
name|total
operator|.
name|stop
argument_list|()
expr_stmt|;
name|System
operator|.
name|out
operator|.
name|println
argument_list|(
literal|"ClassParser.parse: "
operator|+
name|parseTime
argument_list|)
expr_stmt|;
name|System
operator|.
name|out
operator|.
name|println
argument_list|(
literal|"ClassGen.init: "
operator|+
name|cgenTime
argument_list|)
expr_stmt|;
name|System
operator|.
name|out
operator|.
name|println
argument_list|(
literal|"MethodGen.init: "
operator|+
name|mgenTime
argument_list|)
expr_stmt|;
name|System
operator|.
name|out
operator|.
name|println
argument_list|(
literal|"MethodGen.getMethod: "
operator|+
name|mserTime
argument_list|)
expr_stmt|;
name|System
operator|.
name|out
operator|.
name|println
argument_list|(
literal|"ClassGen.getJavaClass.getBytes: "
operator|+
name|serTime
argument_list|)
expr_stmt|;
name|System
operator|.
name|out
operator|.
name|println
argument_list|(
literal|"Total: "
operator|+
name|total
argument_list|)
expr_stmt|;
name|System
operator|.
name|out
operator|.
name|println
argument_list|()
expr_stmt|;
block|}
specifier|public
name|void
name|testPerformance
parameter_list|()
throws|throws
name|IOException
block|{
name|test
argument_list|(
literal|1
argument_list|)
expr_stmt|;
name|test
argument_list|(
literal|1
argument_list|)
expr_stmt|;
name|test
argument_list|(
literal|1
argument_list|)
expr_stmt|;
block|}
block|}
end_class

end_unit
