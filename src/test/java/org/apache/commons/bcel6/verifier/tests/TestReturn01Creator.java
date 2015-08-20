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
name|commons
operator|.
name|bcel6
operator|.
name|verifier
operator|.
name|tests
package|;
end_package

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
name|OutputStream
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
name|bcel6
operator|.
name|Constants
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
name|bcel6
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
name|commons
operator|.
name|bcel6
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
name|commons
operator|.
name|bcel6
operator|.
name|generic
operator|.
name|InstructionConstants
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
name|bcel6
operator|.
name|generic
operator|.
name|InstructionFactory
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
name|bcel6
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
name|commons
operator|.
name|bcel6
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
name|commons
operator|.
name|bcel6
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
name|bcel6
operator|.
name|generic
operator|.
name|Type
import|;
end_import

begin_import
import|import
name|org
operator|.
name|junit
operator|.
name|Assert
import|;
end_import

begin_class
specifier|public
class|class
name|TestReturn01Creator
extends|extends
name|TestCreator
block|{
specifier|private
specifier|final
name|InstructionFactory
name|_factory
decl_stmt|;
specifier|private
specifier|final
name|ConstantPoolGen
name|_cp
decl_stmt|;
specifier|private
specifier|final
name|ClassGen
name|_cg
decl_stmt|;
specifier|public
name|TestReturn01Creator
parameter_list|()
block|{
name|_cg
operator|=
operator|new
name|ClassGen
argument_list|(
name|TEST_PACKAGE
operator|+
literal|".TestReturn01"
argument_list|,
literal|"java.lang.Object"
argument_list|,
literal|"TestReturn01.java"
argument_list|,
name|Constants
operator|.
name|ACC_PUBLIC
operator||
name|Constants
operator|.
name|ACC_SUPER
argument_list|,
operator|new
name|String
index|[]
block|{  }
argument_list|)
expr_stmt|;
name|_cp
operator|=
name|_cg
operator|.
name|getConstantPool
argument_list|()
expr_stmt|;
name|_factory
operator|=
operator|new
name|InstructionFactory
argument_list|(
name|_cg
argument_list|,
name|_cp
argument_list|)
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|create
parameter_list|(
name|OutputStream
name|out
parameter_list|)
throws|throws
name|IOException
block|{
name|createMethod_0
argument_list|()
expr_stmt|;
name|createMethod_1
argument_list|()
expr_stmt|;
name|_cg
operator|.
name|getJavaClass
argument_list|()
operator|.
name|dump
argument_list|(
name|out
argument_list|)
expr_stmt|;
block|}
specifier|private
name|void
name|createMethod_0
parameter_list|()
block|{
name|InstructionList
name|il
init|=
operator|new
name|InstructionList
argument_list|()
decl_stmt|;
name|MethodGen
name|method
init|=
operator|new
name|MethodGen
argument_list|(
name|Constants
operator|.
name|ACC_PUBLIC
argument_list|,
name|Type
operator|.
name|VOID
argument_list|,
name|Type
operator|.
name|NO_ARGS
argument_list|,
operator|new
name|String
index|[]
block|{  }
argument_list|,
literal|"<init>"
argument_list|,
name|TEST_PACKAGE
operator|+
literal|".TestReturn01"
argument_list|,
name|il
argument_list|,
name|_cp
argument_list|)
decl_stmt|;
name|InstructionHandle
name|ih_0
init|=
name|il
operator|.
name|append
argument_list|(
name|InstructionFactory
operator|.
name|createLoad
argument_list|(
name|Type
operator|.
name|OBJECT
argument_list|,
literal|0
argument_list|)
argument_list|)
decl_stmt|;
name|Assert
operator|.
name|assertNotNull
argument_list|(
name|ih_0
argument_list|)
expr_stmt|;
comment|// TODO why is this not used
name|il
operator|.
name|append
argument_list|(
name|_factory
operator|.
name|createInvoke
argument_list|(
literal|"java.lang.Object"
argument_list|,
literal|"<init>"
argument_list|,
name|Type
operator|.
name|VOID
argument_list|,
name|Type
operator|.
name|NO_ARGS
argument_list|,
name|Constants
operator|.
name|INVOKESPECIAL
argument_list|)
argument_list|)
expr_stmt|;
name|InstructionHandle
name|ih_4
init|=
name|il
operator|.
name|append
argument_list|(
name|InstructionFactory
operator|.
name|createReturn
argument_list|(
name|Type
operator|.
name|VOID
argument_list|)
argument_list|)
decl_stmt|;
name|Assert
operator|.
name|assertNotNull
argument_list|(
name|ih_4
argument_list|)
expr_stmt|;
comment|// TODO why is this not used
name|method
operator|.
name|setMaxStack
argument_list|()
expr_stmt|;
name|method
operator|.
name|setMaxLocals
argument_list|()
expr_stmt|;
name|_cg
operator|.
name|addMethod
argument_list|(
name|method
operator|.
name|getMethod
argument_list|()
argument_list|)
expr_stmt|;
name|il
operator|.
name|dispose
argument_list|()
expr_stmt|;
block|}
specifier|private
name|void
name|createMethod_1
parameter_list|()
block|{
name|InstructionList
name|il
init|=
operator|new
name|InstructionList
argument_list|()
decl_stmt|;
name|MethodGen
name|method
init|=
operator|new
name|MethodGen
argument_list|(
name|Constants
operator|.
name|ACC_PUBLIC
operator||
name|Constants
operator|.
name|ACC_STATIC
argument_list|,
name|Type
operator|.
name|VOID
argument_list|,
name|Type
operator|.
name|NO_ARGS
argument_list|,
operator|new
name|String
index|[]
block|{  }
argument_list|,
literal|"foo"
argument_list|,
name|TEST_PACKAGE
operator|+
literal|".TestReturn01"
argument_list|,
name|il
argument_list|,
name|_cp
argument_list|)
decl_stmt|;
name|InstructionHandle
name|ih_0
init|=
name|il
operator|.
name|append
argument_list|(
name|_factory
operator|.
name|createNew
argument_list|(
literal|"java.lang.Object"
argument_list|)
argument_list|)
decl_stmt|;
name|Assert
operator|.
name|assertNotNull
argument_list|(
name|ih_0
argument_list|)
expr_stmt|;
comment|// TODO why is this not used
name|il
operator|.
name|append
argument_list|(
name|InstructionConstants
operator|.
name|DUP
argument_list|)
expr_stmt|;
name|il
operator|.
name|append
argument_list|(
name|_factory
operator|.
name|createInvoke
argument_list|(
literal|"java.lang.Object"
argument_list|,
literal|"<init>"
argument_list|,
name|Type
operator|.
name|VOID
argument_list|,
name|Type
operator|.
name|NO_ARGS
argument_list|,
name|Constants
operator|.
name|INVOKESPECIAL
argument_list|)
argument_list|)
expr_stmt|;
name|il
operator|.
name|append
argument_list|(
name|InstructionConstants
operator|.
name|NOP
argument_list|)
expr_stmt|;
name|InstructionHandle
name|ih_8
init|=
name|il
operator|.
name|append
argument_list|(
name|InstructionFactory
operator|.
name|createReturn
argument_list|(
name|Type
operator|.
name|OBJECT
argument_list|)
argument_list|)
decl_stmt|;
name|Assert
operator|.
name|assertNotNull
argument_list|(
name|ih_8
argument_list|)
expr_stmt|;
comment|// TODO why is this not used
name|method
operator|.
name|setMaxStack
argument_list|()
expr_stmt|;
name|method
operator|.
name|setMaxLocals
argument_list|()
expr_stmt|;
name|_cg
operator|.
name|addMethod
argument_list|(
name|method
operator|.
name|getMethod
argument_list|()
argument_list|)
expr_stmt|;
name|il
operator|.
name|dispose
argument_list|()
expr_stmt|;
block|}
block|}
end_class

end_unit

