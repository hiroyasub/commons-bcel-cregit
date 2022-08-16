begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
begin_comment
comment|/*  * Licensed to the Apache Software Foundation (ASF) under one or more  * contributor license agreements.  See the NOTICE file distributed with  * this work for additional information regarding copyright ownership.  * The ASF licenses this file to You under the Apache License, Version 2.0  * (the "License"); you may not use this file except in compliance with  * the License.  You may obtain a copy of the License at  *  *   http://www.apache.org/licenses/LICENSE-2.0  *  * Unless required by applicable law or agreed to in writing, software  * distributed under the License is distributed on an "AS IS" BASIS,  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  * See the License for the specific language governing permissions and  * limitations under the License.  *  */
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
name|generic
operator|.
name|GOTO
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
name|ILOAD
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
name|NOP
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

begin_comment
comment|/**  * Test for https://issues.apache.org/jira/browse/BCEL-267 "Race conditions on static fields in BranchHandle and  * InstructionHandle".  */
end_comment

begin_class
specifier|public
class|class
name|HandleTestCase
block|{
specifier|static
name|Throwable
name|exception
decl_stmt|;
specifier|static
specifier|final
name|int
name|MAXI
init|=
literal|100
decl_stmt|;
specifier|static
specifier|final
name|int
name|MAXJ
init|=
literal|1000
decl_stmt|;
comment|/**      * Asserts that branch handles can be added an instruction list, without corrupting the list.      */
specifier|static
name|void
name|branchHandles
parameter_list|()
block|{
for|for
control|(
name|int
name|i
init|=
literal|0
init|;
name|i
operator|<
name|MAXI
condition|;
name|i
operator|++
control|)
block|{
specifier|final
name|InstructionList
name|list
init|=
operator|new
name|InstructionList
argument_list|()
decl_stmt|;
specifier|final
name|InstructionHandle
name|start
init|=
name|list
operator|.
name|append
argument_list|(
operator|new
name|NOP
argument_list|()
argument_list|)
decl_stmt|;
try|try
block|{
for|for
control|(
name|int
name|j
init|=
literal|0
init|;
name|j
operator|<
name|MAXJ
condition|;
name|j
operator|++
control|)
block|{
name|list
operator|.
name|append
argument_list|(
operator|new
name|GOTO
argument_list|(
name|start
argument_list|)
argument_list|)
expr_stmt|;
block|}
specifier|final
name|InstructionHandle
index|[]
name|instructionHandles
init|=
name|list
operator|.
name|getInstructionHandles
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
name|instructionHandles
operator|.
name|length
condition|;
name|j
operator|++
control|)
block|{
specifier|final
name|InstructionHandle
name|handle
init|=
name|instructionHandles
index|[
name|j
index|]
decl_stmt|;
if|if
condition|(
name|j
operator|>
literal|0
condition|)
block|{
name|checkLinkage
argument_list|(
name|handle
argument_list|,
name|j
argument_list|)
expr_stmt|;
if|if
condition|(
name|start
operator|!=
operator|(
operator|(
name|GOTO
operator|)
name|handle
operator|.
name|getInstruction
argument_list|()
operator|)
operator|.
name|getTarget
argument_list|()
condition|)
block|{
specifier|final
name|AssertionError
name|error
init|=
operator|new
name|AssertionError
argument_list|(
literal|"unexpected instruction at index "
operator|+
name|j
argument_list|)
decl_stmt|;
name|exception
operator|=
name|error
expr_stmt|;
throw|throw
name|error
throw|;
block|}
block|}
block|}
if|if
condition|(
name|exception
operator|!=
literal|null
condition|)
block|{
return|return;
block|}
block|}
catch|catch
parameter_list|(
specifier|final
name|NullPointerException
name|e
parameter_list|)
block|{
name|System
operator|.
name|out
operator|.
name|println
argument_list|(
literal|"NPE at i="
operator|+
name|i
argument_list|)
expr_stmt|;
name|exception
operator|=
name|e
expr_stmt|;
throw|throw
name|e
throw|;
block|}
name|list
operator|.
name|dispose
argument_list|()
expr_stmt|;
comment|// this initializes caching of unused instruction handles
block|}
block|}
comment|/**      * Assert that opposite next/prev pairs always match.      */
specifier|static
name|void
name|checkLinkage
parameter_list|(
specifier|final
name|InstructionHandle
name|ih
parameter_list|,
specifier|final
name|int
name|index
parameter_list|)
block|{
specifier|final
name|InstructionHandle
name|prev
init|=
name|ih
operator|.
name|getPrev
argument_list|()
decl_stmt|;
specifier|final
name|InstructionHandle
name|next
init|=
name|ih
operator|.
name|getNext
argument_list|()
decl_stmt|;
if|if
condition|(
operator|(
name|prev
operator|!=
literal|null
operator|&&
name|prev
operator|.
name|getNext
argument_list|()
operator|!=
name|ih
operator|)
operator|||
operator|(
name|next
operator|!=
literal|null
operator|&&
name|next
operator|.
name|getPrev
argument_list|()
operator|!=
name|ih
operator|)
condition|)
block|{
specifier|final
name|AssertionError
name|error
init|=
operator|new
name|AssertionError
argument_list|(
literal|"corrupt instruction list at index "
operator|+
name|index
argument_list|)
decl_stmt|;
name|exception
operator|=
name|error
expr_stmt|;
throw|throw
name|error
throw|;
block|}
block|}
comment|/**      * Asserts that instruction handles can be added an instruction list, without corrupting the list.      */
specifier|static
name|void
name|handles
parameter_list|()
block|{
for|for
control|(
name|int
name|i
init|=
literal|0
init|;
name|i
operator|<
name|MAXI
condition|;
name|i
operator|++
control|)
block|{
specifier|final
name|InstructionList
name|list
init|=
operator|new
name|InstructionList
argument_list|()
decl_stmt|;
try|try
block|{
for|for
control|(
name|int
name|j
init|=
literal|0
init|;
name|j
operator|<
name|MAXJ
condition|;
name|j
operator|++
control|)
block|{
name|list
operator|.
name|append
argument_list|(
operator|new
name|ILOAD
argument_list|(
name|j
argument_list|)
argument_list|)
expr_stmt|;
block|}
specifier|final
name|InstructionHandle
index|[]
name|instructionHandles
init|=
name|list
operator|.
name|getInstructionHandles
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
name|instructionHandles
operator|.
name|length
condition|;
name|j
operator|++
control|)
block|{
specifier|final
name|InstructionHandle
name|handle
init|=
name|instructionHandles
index|[
name|j
index|]
decl_stmt|;
name|checkLinkage
argument_list|(
name|handle
argument_list|,
name|j
argument_list|)
expr_stmt|;
if|if
condition|(
name|j
operator|!=
operator|(
operator|(
name|ILOAD
operator|)
name|handle
operator|.
name|getInstruction
argument_list|()
operator|)
operator|.
name|getIndex
argument_list|()
condition|)
block|{
specifier|final
name|AssertionError
name|error
init|=
operator|new
name|AssertionError
argument_list|(
literal|"unexpected instruction at index "
operator|+
name|j
argument_list|)
decl_stmt|;
name|exception
operator|=
name|error
expr_stmt|;
throw|throw
name|error
throw|;
block|}
block|}
if|if
condition|(
name|exception
operator|!=
literal|null
condition|)
block|{
return|return;
block|}
block|}
catch|catch
parameter_list|(
specifier|final
name|NullPointerException
name|e
parameter_list|)
block|{
name|System
operator|.
name|out
operator|.
name|println
argument_list|(
literal|"NPE at i="
operator|+
name|i
argument_list|)
expr_stmt|;
name|exception
operator|=
name|e
expr_stmt|;
throw|throw
name|e
throw|;
block|}
name|list
operator|.
name|dispose
argument_list|()
expr_stmt|;
comment|// this initializes caching of unused instruction handles
block|}
block|}
comment|/**      * Concurrently run the given runnable in two threads.      */
specifier|private
name|void
name|perform
parameter_list|(
specifier|final
name|Runnable
name|r
parameter_list|)
throws|throws
name|Throwable
block|{
name|exception
operator|=
literal|null
expr_stmt|;
specifier|final
name|Thread
name|t1
init|=
operator|new
name|Thread
argument_list|(
name|r
argument_list|)
decl_stmt|;
specifier|final
name|Thread
name|t2
init|=
operator|new
name|Thread
argument_list|(
name|r
argument_list|)
decl_stmt|;
name|t1
operator|.
name|start
argument_list|()
expr_stmt|;
name|t2
operator|.
name|start
argument_list|()
expr_stmt|;
name|t1
operator|.
name|join
argument_list|()
expr_stmt|;
name|t2
operator|.
name|join
argument_list|()
expr_stmt|;
if|if
condition|(
name|exception
operator|!=
literal|null
condition|)
block|{
throw|throw
name|exception
throw|;
block|}
block|}
comment|/**      * Assert that two independent instruction lists can be modified concurrently. Here: inserting branch instructions.      */
annotation|@
name|Test
specifier|public
name|void
name|testBranchHandle
parameter_list|()
throws|throws
name|Throwable
block|{
name|perform
argument_list|(
name|HandleTestCase
operator|::
name|branchHandles
argument_list|)
expr_stmt|;
block|}
comment|/**      * Assert that two independent instruction lists can be modified concurrently. Here: inserting regular instructions.      */
annotation|@
name|Test
specifier|public
name|void
name|testInstructionHandle
parameter_list|()
throws|throws
name|Throwable
block|{
name|perform
argument_list|(
name|HandleTestCase
operator|::
name|handles
argument_list|)
expr_stmt|;
block|}
block|}
end_class

end_unit

