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
name|IOException
import|;
end_import

begin_import
import|import
name|java
operator|.
name|util
operator|.
name|concurrent
operator|.
name|TimeUnit
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

begin_import
import|import
name|org
operator|.
name|apache
operator|.
name|commons
operator|.
name|collections4
operator|.
name|Predicate
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
name|collections4
operator|.
name|iterators
operator|.
name|EnumerationIterator
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
name|collections4
operator|.
name|iterators
operator|.
name|FilterIterator
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
name|collections4
operator|.
name|iterators
operator|.
name|IteratorIterable
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
name|io
operator|.
name|IOUtils
import|;
end_import

begin_import
import|import
name|org
operator|.
name|openjdk
operator|.
name|jmh
operator|.
name|annotations
operator|.
name|Benchmark
import|;
end_import

begin_import
import|import
name|org
operator|.
name|openjdk
operator|.
name|jmh
operator|.
name|annotations
operator|.
name|BenchmarkMode
import|;
end_import

begin_import
import|import
name|org
operator|.
name|openjdk
operator|.
name|jmh
operator|.
name|annotations
operator|.
name|Fork
import|;
end_import

begin_import
import|import
name|org
operator|.
name|openjdk
operator|.
name|jmh
operator|.
name|annotations
operator|.
name|Measurement
import|;
end_import

begin_import
import|import
name|org
operator|.
name|openjdk
operator|.
name|jmh
operator|.
name|annotations
operator|.
name|Mode
import|;
end_import

begin_import
import|import
name|org
operator|.
name|openjdk
operator|.
name|jmh
operator|.
name|annotations
operator|.
name|OutputTimeUnit
import|;
end_import

begin_import
import|import
name|org
operator|.
name|openjdk
operator|.
name|jmh
operator|.
name|annotations
operator|.
name|Threads
import|;
end_import

begin_import
import|import
name|org
operator|.
name|openjdk
operator|.
name|jmh
operator|.
name|annotations
operator|.
name|Warmup
import|;
end_import

begin_import
import|import
name|org
operator|.
name|openjdk
operator|.
name|jmh
operator|.
name|infra
operator|.
name|Blackhole
import|;
end_import

begin_class
annotation|@
name|BenchmarkMode
argument_list|(
name|Mode
operator|.
name|AverageTime
argument_list|)
annotation|@
name|Fork
argument_list|(
name|value
operator|=
literal|1
argument_list|,
name|jvmArgs
operator|=
literal|"-server"
argument_list|)
annotation|@
name|Threads
argument_list|(
literal|1
argument_list|)
annotation|@
name|Warmup
argument_list|(
name|iterations
operator|=
literal|10
argument_list|)
annotation|@
name|Measurement
argument_list|(
name|iterations
operator|=
literal|20
argument_list|)
annotation|@
name|OutputTimeUnit
argument_list|(
name|TimeUnit
operator|.
name|MILLISECONDS
argument_list|)
specifier|public
class|class
name|BCELBenchmark
block|{
specifier|private
name|JarFile
name|getJarFile
parameter_list|()
throws|throws
name|IOException
block|{
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
return|return
operator|new
name|JarFile
argument_list|(
name|javaHome
operator|+
literal|"/lib/rt.jar"
argument_list|)
return|;
block|}
specifier|private
name|Iterable
argument_list|<
name|JarEntry
argument_list|>
name|getClasses
parameter_list|(
name|JarFile
name|jar
parameter_list|)
block|{
return|return
operator|new
name|IteratorIterable
argument_list|<>
argument_list|(
operator|new
name|FilterIterator
argument_list|<>
argument_list|(
operator|new
name|EnumerationIterator
argument_list|<>
argument_list|(
name|jar
operator|.
name|entries
argument_list|()
argument_list|)
argument_list|,
operator|new
name|Predicate
argument_list|<
name|JarEntry
argument_list|>
argument_list|()
block|{
annotation|@
name|Override
specifier|public
name|boolean
name|evaluate
parameter_list|(
name|JarEntry
name|entry
parameter_list|)
block|{
return|return
name|entry
operator|.
name|getName
argument_list|()
operator|.
name|endsWith
argument_list|(
literal|".class"
argument_list|)
return|;
block|}
block|}
argument_list|)
argument_list|)
return|;
block|}
comment|/**      * Baseline benchmark. Read the classes but don't parse them.      */
annotation|@
name|Benchmark
specifier|public
name|void
name|baseline
parameter_list|(
name|Blackhole
name|bh
parameter_list|)
throws|throws
name|IOException
block|{
name|JarFile
name|jar
init|=
name|getJarFile
argument_list|()
decl_stmt|;
for|for
control|(
name|JarEntry
name|entry
range|:
name|getClasses
argument_list|(
name|jar
argument_list|)
control|)
block|{
name|byte
index|[]
name|bytes
init|=
name|IOUtils
operator|.
name|toByteArray
argument_list|(
name|jar
operator|.
name|getInputStream
argument_list|(
name|entry
argument_list|)
argument_list|)
decl_stmt|;
name|bh
operator|.
name|consume
argument_list|(
name|bytes
argument_list|)
expr_stmt|;
block|}
name|jar
operator|.
name|close
argument_list|()
expr_stmt|;
block|}
annotation|@
name|Benchmark
specifier|public
name|void
name|parser
parameter_list|(
name|Blackhole
name|bh
parameter_list|)
throws|throws
name|IOException
block|{
name|JarFile
name|jar
init|=
name|getJarFile
argument_list|()
decl_stmt|;
for|for
control|(
name|JarEntry
name|entry
range|:
name|getClasses
argument_list|(
name|jar
argument_list|)
control|)
block|{
name|byte
index|[]
name|bytes
init|=
name|IOUtils
operator|.
name|toByteArray
argument_list|(
name|jar
operator|.
name|getInputStream
argument_list|(
name|entry
argument_list|)
argument_list|)
decl_stmt|;
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
name|entry
operator|.
name|getName
argument_list|()
argument_list|)
operator|.
name|parse
argument_list|()
decl_stmt|;
name|bh
operator|.
name|consume
argument_list|(
name|clazz
argument_list|)
expr_stmt|;
block|}
name|jar
operator|.
name|close
argument_list|()
expr_stmt|;
block|}
annotation|@
name|Benchmark
specifier|public
name|void
name|generator
parameter_list|(
name|Blackhole
name|bh
parameter_list|)
throws|throws
name|IOException
block|{
name|JarFile
name|jar
init|=
name|getJarFile
argument_list|()
decl_stmt|;
for|for
control|(
name|JarEntry
name|entry
range|:
name|getClasses
argument_list|(
name|jar
argument_list|)
control|)
block|{
name|byte
index|[]
name|bytes
init|=
name|IOUtils
operator|.
name|toByteArray
argument_list|(
name|jar
operator|.
name|getInputStream
argument_list|(
name|entry
argument_list|)
argument_list|)
decl_stmt|;
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
name|entry
operator|.
name|getName
argument_list|()
argument_list|)
operator|.
name|parse
argument_list|()
decl_stmt|;
name|ClassGen
name|cg
init|=
operator|new
name|ClassGen
argument_list|(
name|clazz
argument_list|)
decl_stmt|;
for|for
control|(
name|Method
name|m
range|:
name|cg
operator|.
name|getMethods
argument_list|()
control|)
block|{
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
block|}
name|bh
operator|.
name|consume
argument_list|(
name|cg
operator|.
name|getJavaClass
argument_list|()
operator|.
name|getBytes
argument_list|()
argument_list|)
expr_stmt|;
block|}
name|jar
operator|.
name|close
argument_list|()
expr_stmt|;
block|}
block|}
end_class

end_unit
