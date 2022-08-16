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
name|IOException
import|;
end_import

begin_comment
comment|/**  * GOTO - Branch always (to relative offset, not absolute address)  *  */
end_comment

begin_class
specifier|public
class|class
name|GOTO
extends|extends
name|GotoInstruction
implements|implements
name|VariableLengthInstruction
block|{
comment|/**      * Empty constructor needed for Instruction.readInstruction. Not to be used otherwise.      */
name|GOTO
parameter_list|()
block|{
block|}
specifier|public
name|GOTO
parameter_list|(
specifier|final
name|InstructionHandle
name|target
parameter_list|)
block|{
name|super
argument_list|(
name|org
operator|.
name|apache
operator|.
name|bcel
operator|.
name|Const
operator|.
name|GOTO
argument_list|,
name|target
argument_list|)
expr_stmt|;
block|}
comment|/**      * Call corresponding visitor method(s). The order is: Call visitor methods of implemented interfaces first, then call      * methods according to the class hierarchy in descending order, i.e., the most specific visitXXX() call comes last.      *      * @param v Visitor object      */
annotation|@
name|Override
specifier|public
name|void
name|accept
parameter_list|(
specifier|final
name|Visitor
name|v
parameter_list|)
block|{
name|v
operator|.
name|visitVariableLengthInstruction
argument_list|(
name|this
argument_list|)
expr_stmt|;
name|v
operator|.
name|visitUnconditionalBranch
argument_list|(
name|this
argument_list|)
expr_stmt|;
name|v
operator|.
name|visitBranchInstruction
argument_list|(
name|this
argument_list|)
expr_stmt|;
name|v
operator|.
name|visitGotoInstruction
argument_list|(
name|this
argument_list|)
expr_stmt|;
name|v
operator|.
name|visitGOTO
argument_list|(
name|this
argument_list|)
expr_stmt|;
block|}
comment|/**      * Dump instruction as byte code to stream out.      *       * @param out Output stream      */
annotation|@
name|Override
specifier|public
name|void
name|dump
parameter_list|(
specifier|final
name|DataOutputStream
name|out
parameter_list|)
throws|throws
name|IOException
block|{
name|super
operator|.
name|setIndex
argument_list|(
name|getTargetOffset
argument_list|()
argument_list|)
expr_stmt|;
specifier|final
name|short
name|_opcode
init|=
name|getOpcode
argument_list|()
decl_stmt|;
if|if
condition|(
name|_opcode
operator|==
name|org
operator|.
name|apache
operator|.
name|bcel
operator|.
name|Const
operator|.
name|GOTO
condition|)
block|{
name|super
operator|.
name|dump
argument_list|(
name|out
argument_list|)
expr_stmt|;
block|}
else|else
block|{
comment|// GOTO_W
name|super
operator|.
name|setIndex
argument_list|(
name|getTargetOffset
argument_list|()
argument_list|)
expr_stmt|;
name|out
operator|.
name|writeByte
argument_list|(
name|_opcode
argument_list|)
expr_stmt|;
name|out
operator|.
name|writeInt
argument_list|(
name|super
operator|.
name|getIndex
argument_list|()
argument_list|)
expr_stmt|;
block|}
block|}
comment|/**      * Called in pass 2 of InstructionList.setPositions() in order to update the branch target, that may shift due to      * variable length instructions.      *      * @param offset additional offset caused by preceding (variable length) instructions      * @param max_offset the maximum offset that may be caused by these instructions      * @return additional offset caused by possible change of this instruction's length      */
annotation|@
name|Override
specifier|protected
name|int
name|updatePosition
parameter_list|(
specifier|final
name|int
name|offset
parameter_list|,
specifier|final
name|int
name|max_offset
parameter_list|)
block|{
specifier|final
name|int
name|i
init|=
name|getTargetOffset
argument_list|()
decl_stmt|;
comment|// Depending on old position value
name|setPosition
argument_list|(
name|getPosition
argument_list|()
operator|+
name|offset
argument_list|)
expr_stmt|;
comment|// Position may be shifted by preceding expansions
if|if
condition|(
name|Math
operator|.
name|abs
argument_list|(
name|i
argument_list|)
operator|>=
name|Short
operator|.
name|MAX_VALUE
operator|-
name|max_offset
condition|)
block|{
comment|// to large for short (estimate)
name|super
operator|.
name|setOpcode
argument_list|(
name|org
operator|.
name|apache
operator|.
name|bcel
operator|.
name|Const
operator|.
name|GOTO_W
argument_list|)
expr_stmt|;
specifier|final
name|short
name|old_length
init|=
operator|(
name|short
operator|)
name|super
operator|.
name|getLength
argument_list|()
decl_stmt|;
name|super
operator|.
name|setLength
argument_list|(
literal|5
argument_list|)
expr_stmt|;
return|return
name|super
operator|.
name|getLength
argument_list|()
operator|-
name|old_length
return|;
block|}
return|return
literal|0
return|;
block|}
block|}
end_class

end_unit

