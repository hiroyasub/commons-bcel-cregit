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
name|BranchHandleTestCase
block|{
annotation|@
name|Test
specifier|public
name|void
name|testGetBHnull
parameter_list|()
block|{
name|assertThrows
argument_list|(
name|ClassGenException
operator|.
name|class
argument_list|,
parameter_list|()
lambda|->
name|BranchHandle
operator|.
name|getBranchHandle
argument_list|(
literal|null
argument_list|)
argument_list|)
expr_stmt|;
block|}
annotation|@
name|Test
specifier|public
name|void
name|testsetInstructionBI
parameter_list|()
block|{
specifier|final
name|BranchHandle
name|bh
init|=
name|BranchHandle
operator|.
name|getBranchHandle
argument_list|(
operator|new
name|GOTO
argument_list|(
literal|null
argument_list|)
argument_list|)
decl_stmt|;
comment|// have to start with a valid BI
name|assertNotNull
argument_list|(
name|bh
argument_list|)
expr_stmt|;
name|bh
operator|.
name|setInstruction
argument_list|(
operator|new
name|GOTO
argument_list|(
literal|null
argument_list|)
argument_list|)
expr_stmt|;
name|assertNotNull
argument_list|(
name|bh
argument_list|)
expr_stmt|;
block|}
annotation|@
name|Test
specifier|public
name|void
name|testsetInstructionnotBI
parameter_list|()
block|{
specifier|final
name|BranchHandle
name|bh
init|=
name|BranchHandle
operator|.
name|getBranchHandle
argument_list|(
operator|new
name|GOTO
argument_list|(
literal|null
argument_list|)
argument_list|)
decl_stmt|;
comment|// have to start with a valid BI
name|assertNotNull
argument_list|(
name|bh
argument_list|)
expr_stmt|;
name|assertThrows
argument_list|(
name|ClassGenException
operator|.
name|class
argument_list|,
parameter_list|()
lambda|->
name|bh
operator|.
name|setInstruction
argument_list|(
operator|new
name|NOP
argument_list|()
argument_list|)
argument_list|)
expr_stmt|;
block|}
comment|// Test that setInstruction only allows BranchInstructions
annotation|@
name|Test
specifier|public
name|void
name|testsetInstructionNull
parameter_list|()
block|{
specifier|final
name|BranchHandle
name|bh
init|=
name|BranchHandle
operator|.
name|getBranchHandle
argument_list|(
operator|new
name|GOTO
argument_list|(
literal|null
argument_list|)
argument_list|)
decl_stmt|;
comment|// have to start with a valid BI
name|assertNotNull
argument_list|(
name|bh
argument_list|)
expr_stmt|;
name|assertThrows
argument_list|(
name|ClassGenException
operator|.
name|class
argument_list|,
parameter_list|()
lambda|->
name|bh
operator|.
name|setInstruction
argument_list|(
literal|null
argument_list|)
argument_list|)
expr_stmt|;
block|}
block|}
end_class

end_unit

