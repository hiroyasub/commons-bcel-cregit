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
name|assertNotNull
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
name|assertThrows
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
name|AbstractTestCase
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
name|Const
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
name|ConstantPoolGen
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
name|InstructionHandle
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
name|junit
operator|.
name|jupiter
operator|.
name|api
operator|.
name|Test
import|;
end_import

begin_class
specifier|public
class|class
name|ConstantPoolTestCase
extends|extends
name|AbstractTestCase
block|{
specifier|private
name|InstructionHandle
index|[]
name|getInstructionHandles
parameter_list|(
specifier|final
name|JavaClass
name|clazz
parameter_list|,
specifier|final
name|ConstantPoolGen
name|cp
parameter_list|,
specifier|final
name|Method
name|method
parameter_list|)
block|{
specifier|final
name|MethodGen
name|methodGen
init|=
operator|new
name|MethodGen
argument_list|(
name|method
argument_list|,
name|clazz
operator|.
name|getClassName
argument_list|()
argument_list|,
name|cp
argument_list|)
decl_stmt|;
specifier|final
name|InstructionList
name|instructionList
init|=
name|methodGen
operator|.
name|getInstructionList
argument_list|()
decl_stmt|;
return|return
name|instructionList
operator|.
name|getInstructionHandles
argument_list|()
return|;
block|}
annotation|@
name|Test
specifier|public
name|void
name|testConstantToString
parameter_list|()
throws|throws
name|ClassNotFoundException
block|{
specifier|final
name|JavaClass
name|clazz
init|=
name|getTestClass
argument_list|(
name|PACKAGE_BASE_NAME
operator|+
literal|".data.SimpleClassWithDefaultConstructor"
argument_list|)
decl_stmt|;
specifier|final
name|ConstantPoolGen
name|cp
init|=
operator|new
name|ConstantPoolGen
argument_list|(
name|clazz
operator|.
name|getConstantPool
argument_list|()
argument_list|)
decl_stmt|;
specifier|final
name|Method
index|[]
name|methods
init|=
name|clazz
operator|.
name|getMethods
argument_list|()
decl_stmt|;
for|for
control|(
specifier|final
name|Method
name|method
range|:
name|methods
control|)
block|{
if|if
condition|(
name|method
operator|.
name|getName
argument_list|()
operator|.
name|equals
argument_list|(
literal|"<init>"
argument_list|)
condition|)
block|{
for|for
control|(
specifier|final
name|InstructionHandle
name|instructionHandle
range|:
name|getInstructionHandles
argument_list|(
name|clazz
argument_list|,
name|cp
argument_list|,
name|method
argument_list|)
control|)
block|{
specifier|final
name|String
name|string
init|=
name|instructionHandle
operator|.
name|getInstruction
argument_list|()
operator|.
name|toString
argument_list|(
name|cp
operator|.
name|getConstantPool
argument_list|()
argument_list|)
decl_stmt|;
name|assertNotNull
argument_list|(
name|string
argument_list|)
expr_stmt|;
comment|// TODO Need real assertions.
comment|// System.out.println(string);
block|}
block|}
block|}
block|}
annotation|@
name|Test
specifier|public
name|void
name|testTooManyConstants
parameter_list|()
throws|throws
name|ClassNotFoundException
block|{
specifier|final
name|JavaClass
name|clazz
init|=
name|getTestClass
argument_list|(
name|PACKAGE_BASE_NAME
operator|+
literal|".data.SimpleClassWithDefaultConstructor"
argument_list|)
decl_stmt|;
specifier|final
name|ConstantPoolGen
name|cp
init|=
operator|new
name|ConstantPoolGen
argument_list|(
name|clazz
operator|.
name|getConstantPool
argument_list|()
argument_list|)
decl_stmt|;
name|int
name|i
init|=
name|cp
operator|.
name|getSize
argument_list|()
decl_stmt|;
while|while
condition|(
name|i
operator|<
name|Const
operator|.
name|MAX_CP_ENTRIES
operator|-
literal|1
condition|)
block|{
name|cp
operator|.
name|addLong
argument_list|(
name|i
argument_list|)
expr_stmt|;
name|i
operator|=
name|cp
operator|.
name|getSize
argument_list|()
expr_stmt|;
comment|// i += 2
block|}
name|assertThrows
argument_list|(
name|IllegalStateException
operator|.
name|class
argument_list|,
parameter_list|()
lambda|->
name|cp
operator|.
name|addLong
argument_list|(
literal|0
argument_list|)
argument_list|)
expr_stmt|;
block|}
block|}
end_class

end_unit

