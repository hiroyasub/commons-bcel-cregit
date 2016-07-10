begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
begin_comment
comment|/*  * Licensed to the Apache Software Foundation (ASF) under one or more  * contributor license agreements.  See the NOTICE file distributed with  * this work for additional information regarding copyright ownership.  * The ASF licenses this file to You under the Apache License, Version 2.0  * (the "License"); you may not use this file except in compliance with  * the License.  You may obtain a copy of the License at  *  *   http://www.apache.org/licenses/LICENSE-2.0  *  * Unless required by applicable law or agreed to in writing, software  * distributed under the License is distributed on an "AS IS" BASIS,  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  * See the License for the specific language governing permissions and  * limitations under the License.  *   */
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
name|LocalVariableTable
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
name|InvokeInstruction
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
name|bcel
operator|.
name|generic
operator|.
name|Type
import|;
end_import

begin_class
specifier|public
class|class
name|PLSETestCase
extends|extends
name|AbstractTestCase
block|{
comment|/**      * BCEL-208: A couple of methods in MethodGen.java need to test for      * an empty instruction list.      */
specifier|public
name|void
name|testB208
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
literal|".data.PLSETestClass"
argument_list|)
decl_stmt|;
specifier|final
name|ClassGen
name|gen
init|=
operator|new
name|ClassGen
argument_list|(
name|clazz
argument_list|)
decl_stmt|;
specifier|final
name|ConstantPoolGen
name|pool
init|=
name|gen
operator|.
name|getConstantPool
argument_list|()
decl_stmt|;
specifier|final
name|Method
name|m
init|=
name|gen
operator|.
name|getMethodAt
argument_list|(
literal|1
argument_list|)
decl_stmt|;
specifier|final
name|MethodGen
name|mg
init|=
operator|new
name|MethodGen
argument_list|(
name|m
argument_list|,
name|gen
operator|.
name|getClassName
argument_list|()
argument_list|,
name|pool
argument_list|)
decl_stmt|;
name|mg
operator|.
name|setInstructionList
argument_list|(
literal|null
argument_list|)
expr_stmt|;
name|mg
operator|.
name|addLocalVariable
argument_list|(
literal|"local2"
argument_list|,
name|Type
operator|.
name|INT
argument_list|,
literal|null
argument_list|,
literal|null
argument_list|)
expr_stmt|;
comment|// currently, this will cause null pointer exception
name|mg
operator|.
name|getLocalVariableTable
argument_list|(
name|pool
argument_list|)
expr_stmt|;
block|}
comment|/**      * BCEL-79:       */
specifier|public
name|void
name|testB79
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
literal|".data.PLSETestClass"
argument_list|)
decl_stmt|;
specifier|final
name|ClassGen
name|gen
init|=
operator|new
name|ClassGen
argument_list|(
name|clazz
argument_list|)
decl_stmt|;
specifier|final
name|ConstantPoolGen
name|pool
init|=
name|gen
operator|.
name|getConstantPool
argument_list|()
decl_stmt|;
specifier|final
name|Method
name|m
init|=
name|gen
operator|.
name|getMethodAt
argument_list|(
literal|2
argument_list|)
decl_stmt|;
specifier|final
name|LocalVariableTable
name|lvt
init|=
name|m
operator|.
name|getLocalVariableTable
argument_list|()
decl_stmt|;
comment|//System.out.println(lvt);
comment|//System.out.println(lvt.getTableLength());
specifier|final
name|MethodGen
name|mg
init|=
operator|new
name|MethodGen
argument_list|(
name|m
argument_list|,
name|gen
operator|.
name|getClassName
argument_list|()
argument_list|,
name|pool
argument_list|)
decl_stmt|;
specifier|final
name|LocalVariableTable
name|new_lvt
init|=
name|mg
operator|.
name|getLocalVariableTable
argument_list|(
name|mg
operator|.
name|getConstantPool
argument_list|()
argument_list|)
decl_stmt|;
comment|//System.out.println(new_lvt);
name|assertEquals
argument_list|(
literal|"number of locals"
argument_list|,
name|lvt
operator|.
name|getTableLength
argument_list|()
argument_list|,
name|new_lvt
operator|.
name|getTableLength
argument_list|()
argument_list|)
expr_stmt|;
block|}
comment|/**      * BCEL-262:       */
specifier|public
name|void
name|testB262
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
literal|".data.PLSETestEnum"
argument_list|)
decl_stmt|;
specifier|final
name|ClassGen
name|gen
init|=
operator|new
name|ClassGen
argument_list|(
name|clazz
argument_list|)
decl_stmt|;
specifier|final
name|ConstantPoolGen
name|pool
init|=
name|gen
operator|.
name|getConstantPool
argument_list|()
decl_stmt|;
comment|// get the values() method
specifier|final
name|Method
name|m
init|=
name|gen
operator|.
name|getMethodAt
argument_list|(
literal|0
argument_list|)
decl_stmt|;
specifier|final
name|MethodGen
name|mg
init|=
operator|new
name|MethodGen
argument_list|(
name|m
argument_list|,
name|gen
operator|.
name|getClassName
argument_list|()
argument_list|,
name|pool
argument_list|)
decl_stmt|;
specifier|final
name|InstructionList
name|il
init|=
name|mg
operator|.
name|getInstructionList
argument_list|()
decl_stmt|;
comment|// get the invokevirtual instruction
specifier|final
name|InstructionHandle
name|ih
init|=
name|il
operator|.
name|findHandle
argument_list|(
literal|3
argument_list|)
decl_stmt|;
specifier|final
name|InvokeInstruction
name|ii
init|=
operator|(
name|InvokeInstruction
operator|)
operator|(
name|ih
operator|.
name|getInstruction
argument_list|()
operator|)
decl_stmt|;
comment|// without fix, the getClassName() will throw:
comment|//   java.lang.IllegalArgumentException: Cannot be used on an array type
specifier|final
name|String
name|cn
init|=
name|ii
operator|.
name|getClassName
argument_list|(
name|pool
argument_list|)
decl_stmt|;
name|assertEquals
argument_list|(
literal|"[Lorg.apache.bcel.data.PLSETestEnum;"
argument_list|,
name|cn
argument_list|)
expr_stmt|;
block|}
block|}
end_class

end_unit

