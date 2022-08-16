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

begin_import
import|import
name|org
operator|.
name|apache
operator|.
name|bcel
operator|.
name|util
operator|.
name|ByteSequence
import|;
end_import

begin_comment
comment|/**  * Abstract super class for branching instructions like GOTO, IFEQ, etc.. Branch instructions may have a variable  * length, namely GOTO, JSR, LOOKUPSWITCH and TABLESWITCH.  *  * @see InstructionList  */
end_comment

begin_class
specifier|public
specifier|abstract
class|class
name|BranchInstruction
extends|extends
name|Instruction
implements|implements
name|InstructionTargeter
block|{
comment|/**      * Used by BranchInstruction, LocalVariableGen, CodeExceptionGen, LineNumberGen      */
specifier|static
name|void
name|notifyTarget
parameter_list|(
specifier|final
name|InstructionHandle
name|old_ih
parameter_list|,
specifier|final
name|InstructionHandle
name|new_ih
parameter_list|,
specifier|final
name|InstructionTargeter
name|t
parameter_list|)
block|{
if|if
condition|(
name|old_ih
operator|!=
literal|null
condition|)
block|{
name|old_ih
operator|.
name|removeTargeter
argument_list|(
name|t
argument_list|)
expr_stmt|;
block|}
if|if
condition|(
name|new_ih
operator|!=
literal|null
condition|)
block|{
name|new_ih
operator|.
name|addTargeter
argument_list|(
name|t
argument_list|)
expr_stmt|;
block|}
block|}
comment|/**      * @deprecated (since 6.0) will be made private; do not access directly, use getter/setter      */
annotation|@
name|Deprecated
specifier|protected
name|int
name|index
decl_stmt|;
comment|// Branch target relative to this instruction
comment|/**      * @deprecated (since 6.0) will be made private; do not access directly, use getter/setter      */
annotation|@
name|Deprecated
specifier|protected
name|InstructionHandle
name|target
decl_stmt|;
comment|// Target object in instruction list
comment|/**      * @deprecated (since 6.0) will be made private; do not access directly, use getter/setter      */
annotation|@
name|Deprecated
specifier|protected
name|int
name|position
decl_stmt|;
comment|// Byte code offset
comment|/**      * Empty constructor needed for Instruction.readInstruction. Not to be used otherwise.      */
name|BranchInstruction
parameter_list|()
block|{
block|}
comment|/**      * Common super constructor      *       * @param opcode Instruction opcode      * @param target instruction to branch to      */
specifier|protected
name|BranchInstruction
parameter_list|(
specifier|final
name|short
name|opcode
parameter_list|,
specifier|final
name|InstructionHandle
name|target
parameter_list|)
block|{
name|super
argument_list|(
name|opcode
argument_list|,
operator|(
name|short
operator|)
literal|3
argument_list|)
expr_stmt|;
name|setTarget
argument_list|(
name|target
argument_list|)
expr_stmt|;
block|}
comment|/**      * @return true, if ih is target of this instruction      */
annotation|@
name|Override
specifier|public
name|boolean
name|containsTarget
parameter_list|(
specifier|final
name|InstructionHandle
name|ih
parameter_list|)
block|{
return|return
name|target
operator|==
name|ih
return|;
block|}
comment|/**      * Inform target that it's not targeted anymore.      */
annotation|@
name|Override
name|void
name|dispose
parameter_list|()
block|{
name|setTarget
argument_list|(
literal|null
argument_list|)
expr_stmt|;
name|index
operator|=
operator|-
literal|1
expr_stmt|;
name|position
operator|=
operator|-
literal|1
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
name|out
operator|.
name|writeByte
argument_list|(
name|super
operator|.
name|getOpcode
argument_list|()
argument_list|)
expr_stmt|;
name|index
operator|=
name|getTargetOffset
argument_list|()
expr_stmt|;
if|if
condition|(
operator|!
name|isValidShort
argument_list|(
name|index
argument_list|)
condition|)
block|{
throw|throw
operator|new
name|ClassGenException
argument_list|(
literal|"Branch target offset too large for short: "
operator|+
name|index
argument_list|)
throw|;
block|}
name|out
operator|.
name|writeShort
argument_list|(
name|index
argument_list|)
expr_stmt|;
comment|// May be negative, i.e., point backwards
block|}
comment|/**      * @return target offset in byte code      */
specifier|public
specifier|final
name|int
name|getIndex
parameter_list|()
block|{
return|return
name|index
return|;
block|}
comment|/**      * @return the position      * @since 6.0      */
specifier|protected
name|int
name|getPosition
parameter_list|()
block|{
return|return
name|position
return|;
block|}
comment|/**      * @return target of branch instruction      */
specifier|public
name|InstructionHandle
name|getTarget
parameter_list|()
block|{
return|return
name|target
return|;
block|}
comment|/**      * @return the offset to this instruction's target      */
specifier|protected
name|int
name|getTargetOffset
parameter_list|()
block|{
return|return
name|getTargetOffset
argument_list|(
name|target
argument_list|)
return|;
block|}
comment|/**      * @param _target branch target      * @return the offset to `target' relative to this instruction      */
specifier|protected
name|int
name|getTargetOffset
parameter_list|(
specifier|final
name|InstructionHandle
name|_target
parameter_list|)
block|{
if|if
condition|(
name|_target
operator|==
literal|null
condition|)
block|{
throw|throw
operator|new
name|ClassGenException
argument_list|(
literal|"Target of "
operator|+
name|super
operator|.
name|toString
argument_list|(
literal|true
argument_list|)
operator|+
literal|" is invalid null handle"
argument_list|)
throw|;
block|}
specifier|final
name|int
name|t
init|=
name|_target
operator|.
name|getPosition
argument_list|()
decl_stmt|;
if|if
condition|(
name|t
operator|<
literal|0
condition|)
block|{
throw|throw
operator|new
name|ClassGenException
argument_list|(
literal|"Invalid branch target position offset for "
operator|+
name|super
operator|.
name|toString
argument_list|(
literal|true
argument_list|)
operator|+
literal|":"
operator|+
name|t
operator|+
literal|":"
operator|+
name|_target
argument_list|)
throw|;
block|}
return|return
name|t
operator|-
name|position
return|;
block|}
comment|/**      * Read needed data (e.g. index) from file. Conversion to a InstructionHandle is done in InstructionList(byte[]).      *      * @param bytes input stream      * @param wide wide prefix?      * @see InstructionList      */
annotation|@
name|Override
specifier|protected
name|void
name|initFromFile
parameter_list|(
specifier|final
name|ByteSequence
name|bytes
parameter_list|,
specifier|final
name|boolean
name|wide
parameter_list|)
throws|throws
name|IOException
block|{
name|super
operator|.
name|setLength
argument_list|(
literal|3
argument_list|)
expr_stmt|;
name|index
operator|=
name|bytes
operator|.
name|readShort
argument_list|()
expr_stmt|;
block|}
comment|/**      * @param index the index to set      * @since 6.0      */
specifier|protected
name|void
name|setIndex
parameter_list|(
specifier|final
name|int
name|index
parameter_list|)
block|{
name|this
operator|.
name|index
operator|=
name|index
expr_stmt|;
block|}
comment|/**      * @param position the position to set      * @since 6.0      */
specifier|protected
name|void
name|setPosition
parameter_list|(
specifier|final
name|int
name|position
parameter_list|)
block|{
name|this
operator|.
name|position
operator|=
name|position
expr_stmt|;
block|}
comment|/**      * Set branch target      *       * @param target branch target      */
specifier|public
name|void
name|setTarget
parameter_list|(
specifier|final
name|InstructionHandle
name|target
parameter_list|)
block|{
name|notifyTarget
argument_list|(
name|this
operator|.
name|target
argument_list|,
name|target
argument_list|,
name|this
argument_list|)
expr_stmt|;
name|this
operator|.
name|target
operator|=
name|target
expr_stmt|;
block|}
comment|/**      * Long output format:      *      *&lt;position in byte code&gt;&lt;name of opcode&gt; "["&lt;opcode number&gt;"]" "("&lt;length of instruction&gt;")"      * "&lt;"&lt;target instruction&gt;"&gt;" "@"&lt;branch target offset&gt;      *      * @param verbose long/short format switch      * @return mnemonic for instruction      */
annotation|@
name|Override
specifier|public
name|String
name|toString
parameter_list|(
specifier|final
name|boolean
name|verbose
parameter_list|)
block|{
specifier|final
name|String
name|s
init|=
name|super
operator|.
name|toString
argument_list|(
name|verbose
argument_list|)
decl_stmt|;
name|String
name|t
init|=
literal|"null"
decl_stmt|;
if|if
condition|(
name|target
operator|!=
literal|null
condition|)
block|{
if|if
condition|(
name|verbose
condition|)
block|{
if|if
condition|(
name|target
operator|.
name|getInstruction
argument_list|()
operator|==
name|this
condition|)
block|{
name|t
operator|=
literal|"<points to itself>"
expr_stmt|;
block|}
if|else if
condition|(
name|target
operator|.
name|getInstruction
argument_list|()
operator|==
literal|null
condition|)
block|{
name|t
operator|=
literal|"<null instruction!!!?>"
expr_stmt|;
block|}
else|else
block|{
comment|// I'm more interested in the address of the target then
comment|// the instruction located there.
comment|// t = target.getInstruction().toString(false); // Avoid circles
name|t
operator|=
literal|""
operator|+
name|target
operator|.
name|getPosition
argument_list|()
expr_stmt|;
block|}
block|}
else|else
block|{
name|index
operator|=
name|target
operator|.
name|getPosition
argument_list|()
expr_stmt|;
comment|// index = getTargetOffset(); crashes if positions haven't been set
comment|// t = "" + (index + position);
name|t
operator|=
literal|""
operator|+
name|index
expr_stmt|;
block|}
block|}
return|return
name|s
operator|+
literal|" -> "
operator|+
name|t
return|;
block|}
comment|/**      * Called by InstructionList.setPositions when setting the position for every instruction. In the presence of variable      * length instructions `setPositions' performs multiple passes over the instruction list to calculate the correct (byte)      * positions and offsets by calling this function.      *      * @param offset additional offset caused by preceding (variable length) instructions      * @param max_offset the maximum offset that may be caused by these instructions      * @return additional offset caused by possible change of this instruction's length      */
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
name|position
operator|+=
name|offset
expr_stmt|;
return|return
literal|0
return|;
block|}
comment|/**      * @param old_ih old target      * @param new_ih new target      */
annotation|@
name|Override
specifier|public
name|void
name|updateTarget
parameter_list|(
specifier|final
name|InstructionHandle
name|old_ih
parameter_list|,
specifier|final
name|InstructionHandle
name|new_ih
parameter_list|)
block|{
if|if
condition|(
name|target
operator|!=
name|old_ih
condition|)
block|{
throw|throw
operator|new
name|ClassGenException
argument_list|(
literal|"Not targeting "
operator|+
name|old_ih
operator|+
literal|", but "
operator|+
name|target
argument_list|)
throw|;
block|}
name|setTarget
argument_list|(
name|new_ih
argument_list|)
expr_stmt|;
block|}
block|}
end_class

end_unit

