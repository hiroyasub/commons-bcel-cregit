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

begin_comment
comment|/**  * SWITCH - Branch depending on int value, generates either LOOKUPSWITCH or  * TABLESWITCH instruction, depending on whether the match values (int[]) can be  * sorted with no gaps between the numbers.  *  * @version $Id$  */
end_comment

begin_class
specifier|public
specifier|final
class|class
name|SWITCH
implements|implements
name|CompoundInstruction
block|{
specifier|private
name|int
index|[]
name|match
decl_stmt|;
specifier|private
name|InstructionHandle
index|[]
name|targets
decl_stmt|;
specifier|private
name|Select
name|instruction
decl_stmt|;
specifier|private
name|int
name|match_length
decl_stmt|;
comment|/**      * Template for switch() constructs. If the match array can be      * sorted in ascending order with gaps no larger than max_gap      * between the numbers, a TABLESWITCH instruction is generated, and      * a LOOKUPSWITCH otherwise. The former may be more efficient, but      * needs more space.      *      * Note, that the key array always will be sorted, though we leave      * the original arrays unaltered.      *      * @param match array of match values (case 2: ... case 7: ..., etc.)      * @param targets the instructions to be branched to for each case      * @param target the default target      * @param max_gap maximum gap that may between case branches      */
specifier|public
name|SWITCH
parameter_list|(
specifier|final
name|int
index|[]
name|match
parameter_list|,
specifier|final
name|InstructionHandle
index|[]
name|targets
parameter_list|,
specifier|final
name|InstructionHandle
name|target
parameter_list|,
specifier|final
name|int
name|max_gap
parameter_list|)
block|{
name|this
operator|.
name|match
operator|=
name|match
operator|.
name|clone
argument_list|()
expr_stmt|;
name|this
operator|.
name|targets
operator|=
name|targets
operator|.
name|clone
argument_list|()
expr_stmt|;
if|if
condition|(
operator|(
name|match_length
operator|=
name|match
operator|.
name|length
operator|)
operator|<
literal|2
condition|)
block|{
name|instruction
operator|=
operator|new
name|TABLESWITCH
argument_list|(
name|match
argument_list|,
name|targets
argument_list|,
name|target
argument_list|)
expr_stmt|;
block|}
else|else
block|{
name|sort
argument_list|(
literal|0
argument_list|,
name|match_length
operator|-
literal|1
argument_list|)
expr_stmt|;
if|if
condition|(
name|matchIsOrdered
argument_list|(
name|max_gap
argument_list|)
condition|)
block|{
name|fillup
argument_list|(
name|max_gap
argument_list|,
name|target
argument_list|)
expr_stmt|;
name|instruction
operator|=
operator|new
name|TABLESWITCH
argument_list|(
name|this
operator|.
name|match
argument_list|,
name|this
operator|.
name|targets
argument_list|,
name|target
argument_list|)
expr_stmt|;
block|}
else|else
block|{
name|instruction
operator|=
operator|new
name|LOOKUPSWITCH
argument_list|(
name|this
operator|.
name|match
argument_list|,
name|this
operator|.
name|targets
argument_list|,
name|target
argument_list|)
expr_stmt|;
block|}
block|}
block|}
specifier|public
name|SWITCH
parameter_list|(
specifier|final
name|int
index|[]
name|match
parameter_list|,
specifier|final
name|InstructionHandle
index|[]
name|targets
parameter_list|,
specifier|final
name|InstructionHandle
name|target
parameter_list|)
block|{
name|this
argument_list|(
name|match
argument_list|,
name|targets
argument_list|,
name|target
argument_list|,
literal|1
argument_list|)
expr_stmt|;
block|}
specifier|private
name|void
name|fillup
parameter_list|(
specifier|final
name|int
name|max_gap
parameter_list|,
specifier|final
name|InstructionHandle
name|target
parameter_list|)
block|{
specifier|final
name|int
name|max_size
init|=
name|match_length
operator|+
name|match_length
operator|*
name|max_gap
decl_stmt|;
specifier|final
name|int
index|[]
name|m_vec
init|=
operator|new
name|int
index|[
name|max_size
index|]
decl_stmt|;
specifier|final
name|InstructionHandle
index|[]
name|t_vec
init|=
operator|new
name|InstructionHandle
index|[
name|max_size
index|]
decl_stmt|;
name|int
name|count
init|=
literal|1
decl_stmt|;
name|m_vec
index|[
literal|0
index|]
operator|=
name|match
index|[
literal|0
index|]
expr_stmt|;
name|t_vec
index|[
literal|0
index|]
operator|=
name|targets
index|[
literal|0
index|]
expr_stmt|;
for|for
control|(
name|int
name|i
init|=
literal|1
init|;
name|i
operator|<
name|match_length
condition|;
name|i
operator|++
control|)
block|{
specifier|final
name|int
name|prev
init|=
name|match
index|[
name|i
operator|-
literal|1
index|]
decl_stmt|;
specifier|final
name|int
name|gap
init|=
name|match
index|[
name|i
index|]
operator|-
name|prev
decl_stmt|;
for|for
control|(
name|int
name|j
init|=
literal|1
init|;
name|j
operator|<
name|gap
condition|;
name|j
operator|++
control|)
block|{
name|m_vec
index|[
name|count
index|]
operator|=
name|prev
operator|+
name|j
expr_stmt|;
name|t_vec
index|[
name|count
index|]
operator|=
name|target
expr_stmt|;
name|count
operator|++
expr_stmt|;
block|}
name|m_vec
index|[
name|count
index|]
operator|=
name|match
index|[
name|i
index|]
expr_stmt|;
name|t_vec
index|[
name|count
index|]
operator|=
name|targets
index|[
name|i
index|]
expr_stmt|;
name|count
operator|++
expr_stmt|;
block|}
name|match
operator|=
operator|new
name|int
index|[
name|count
index|]
expr_stmt|;
name|targets
operator|=
operator|new
name|InstructionHandle
index|[
name|count
index|]
expr_stmt|;
name|System
operator|.
name|arraycopy
argument_list|(
name|m_vec
argument_list|,
literal|0
argument_list|,
name|match
argument_list|,
literal|0
argument_list|,
name|count
argument_list|)
expr_stmt|;
name|System
operator|.
name|arraycopy
argument_list|(
name|t_vec
argument_list|,
literal|0
argument_list|,
name|targets
argument_list|,
literal|0
argument_list|,
name|count
argument_list|)
expr_stmt|;
block|}
comment|/**      * Sort match and targets array with QuickSort.      */
specifier|private
name|void
name|sort
parameter_list|(
specifier|final
name|int
name|l
parameter_list|,
specifier|final
name|int
name|r
parameter_list|)
block|{
name|int
name|i
init|=
name|l
decl_stmt|;
name|int
name|j
init|=
name|r
decl_stmt|;
name|int
name|h
decl_stmt|;
specifier|final
name|int
name|m
init|=
name|match
index|[
operator|(
name|l
operator|+
name|r
operator|)
operator|>>>
literal|1
index|]
decl_stmt|;
name|InstructionHandle
name|h2
decl_stmt|;
do|do
block|{
while|while
condition|(
name|match
index|[
name|i
index|]
operator|<
name|m
condition|)
block|{
name|i
operator|++
expr_stmt|;
block|}
while|while
condition|(
name|m
operator|<
name|match
index|[
name|j
index|]
condition|)
block|{
name|j
operator|--
expr_stmt|;
block|}
if|if
condition|(
name|i
operator|<=
name|j
condition|)
block|{
name|h
operator|=
name|match
index|[
name|i
index|]
expr_stmt|;
name|match
index|[
name|i
index|]
operator|=
name|match
index|[
name|j
index|]
expr_stmt|;
name|match
index|[
name|j
index|]
operator|=
name|h
expr_stmt|;
comment|// Swap elements
name|h2
operator|=
name|targets
index|[
name|i
index|]
expr_stmt|;
name|targets
index|[
name|i
index|]
operator|=
name|targets
index|[
name|j
index|]
expr_stmt|;
name|targets
index|[
name|j
index|]
operator|=
name|h2
expr_stmt|;
comment|// Swap instructions, too
name|i
operator|++
expr_stmt|;
name|j
operator|--
expr_stmt|;
block|}
block|}
do|while
condition|(
name|i
operator|<=
name|j
condition|)
do|;
if|if
condition|(
name|l
operator|<
name|j
condition|)
block|{
name|sort
argument_list|(
name|l
argument_list|,
name|j
argument_list|)
expr_stmt|;
block|}
if|if
condition|(
name|i
operator|<
name|r
condition|)
block|{
name|sort
argument_list|(
name|i
argument_list|,
name|r
argument_list|)
expr_stmt|;
block|}
block|}
comment|/**      * @return match is sorted in ascending order with no gap bigger than max_gap?      */
specifier|private
name|boolean
name|matchIsOrdered
parameter_list|(
specifier|final
name|int
name|max_gap
parameter_list|)
block|{
for|for
control|(
name|int
name|i
init|=
literal|1
init|;
name|i
operator|<
name|match_length
condition|;
name|i
operator|++
control|)
block|{
if|if
condition|(
name|match
index|[
name|i
index|]
operator|-
name|match
index|[
name|i
operator|-
literal|1
index|]
operator|>
name|max_gap
condition|)
block|{
return|return
literal|false
return|;
block|}
block|}
return|return
literal|true
return|;
block|}
annotation|@
name|Override
specifier|public
specifier|final
name|InstructionList
name|getInstructionList
parameter_list|()
block|{
return|return
operator|new
name|InstructionList
argument_list|(
name|instruction
argument_list|)
return|;
block|}
specifier|public
specifier|final
name|Instruction
name|getInstruction
parameter_list|()
block|{
return|return
name|instruction
return|;
block|}
block|}
end_class

end_unit

