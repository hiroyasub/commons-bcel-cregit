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
name|commons
operator|.
name|bcel6
operator|.
name|generic
package|;
end_package

begin_import
import|import
name|java
operator|.
name|util
operator|.
name|Collection
import|;
end_import

begin_import
import|import
name|java
operator|.
name|util
operator|.
name|HashMap
import|;
end_import

begin_import
import|import
name|java
operator|.
name|util
operator|.
name|HashSet
import|;
end_import

begin_import
import|import
name|java
operator|.
name|util
operator|.
name|Map
import|;
end_import

begin_import
import|import
name|java
operator|.
name|util
operator|.
name|Set
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
name|classfile
operator|.
name|Utility
import|;
end_import

begin_comment
comment|/**  * Instances of this class give users a handle to the instructions contained in  * an InstructionList. Instruction objects may be used more than once within a  * list, this is useful because it saves memory and may be much faster.  *  * Within an InstructionList an InstructionHandle object is wrapped  * around all instructions, i.e., it implements a cell in a  * doubly-linked list. From the outside only the next and the  * previous instruction (handle) are accessible. One  * can traverse the list via an Enumeration returned by  * InstructionList.elements().  *  * @version $Id$  * @see Instruction  * @see BranchHandle  * @see InstructionList   */
end_comment

begin_class
specifier|public
class|class
name|InstructionHandle
block|{
specifier|private
name|InstructionHandle
name|next
decl_stmt|;
specifier|private
name|InstructionHandle
name|prev
decl_stmt|;
specifier|private
name|Instruction
name|instruction
decl_stmt|;
comment|/**      * @deprecated will be made private; do not access directly, use getter/setter      */
annotation|@
name|Deprecated
specifier|protected
name|int
name|i_position
init|=
operator|-
literal|1
decl_stmt|;
comment|// byte code offset of instruction
specifier|private
name|Set
argument_list|<
name|InstructionTargeter
argument_list|>
name|targeters
decl_stmt|;
specifier|private
name|Map
argument_list|<
name|Object
argument_list|,
name|Object
argument_list|>
name|attributes
decl_stmt|;
specifier|public
specifier|final
name|InstructionHandle
name|getNext
parameter_list|()
block|{
return|return
name|next
return|;
block|}
specifier|public
specifier|final
name|InstructionHandle
name|getPrev
parameter_list|()
block|{
return|return
name|prev
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
comment|/**      * Replace current instruction contained in this handle.      * Old instruction is disposed using Instruction.dispose().      */
specifier|public
name|void
name|setInstruction
parameter_list|(
name|Instruction
name|i
parameter_list|)
block|{
comment|// Overridden in BranchHandle TODO could be package-protected?
if|if
condition|(
name|i
operator|==
literal|null
condition|)
block|{
throw|throw
operator|new
name|ClassGenException
argument_list|(
literal|"Assigning null to handle"
argument_list|)
throw|;
block|}
if|if
condition|(
operator|(
name|this
operator|.
name|getClass
argument_list|()
operator|!=
name|BranchHandle
operator|.
name|class
operator|)
operator|&&
operator|(
name|i
operator|instanceof
name|BranchInstruction
operator|)
condition|)
block|{
throw|throw
operator|new
name|ClassGenException
argument_list|(
literal|"Assigning branch instruction "
operator|+
name|i
operator|+
literal|" to plain handle"
argument_list|)
throw|;
block|}
if|if
condition|(
name|instruction
operator|!=
literal|null
condition|)
block|{
name|instruction
operator|.
name|dispose
argument_list|()
expr_stmt|;
block|}
name|instruction
operator|=
name|i
expr_stmt|;
block|}
comment|/**      * Temporarily swap the current instruction, without disturbing      * anything. Meant to be used by a debugger, implementing      * breakpoints. Current instruction is returned.      */
specifier|public
name|Instruction
name|swapInstruction
parameter_list|(
name|Instruction
name|i
parameter_list|)
block|{
name|Instruction
name|oldInstruction
init|=
name|instruction
decl_stmt|;
name|instruction
operator|=
name|i
expr_stmt|;
return|return
name|oldInstruction
return|;
block|}
comment|/*private*/
specifier|protected
name|InstructionHandle
parameter_list|(
name|Instruction
name|i
parameter_list|)
block|{
name|setInstruction
argument_list|(
name|i
argument_list|)
expr_stmt|;
block|}
specifier|private
specifier|static
name|InstructionHandle
name|ih_list
init|=
literal|null
decl_stmt|;
comment|// List of reusable handles
comment|/** Factory method.      */
specifier|static
name|InstructionHandle
name|getInstructionHandle
parameter_list|(
name|Instruction
name|i
parameter_list|)
block|{
if|if
condition|(
name|ih_list
operator|==
literal|null
condition|)
block|{
return|return
operator|new
name|InstructionHandle
argument_list|(
name|i
argument_list|)
return|;
block|}
name|InstructionHandle
name|ih
init|=
name|ih_list
decl_stmt|;
name|ih_list
operator|=
name|ih
operator|.
name|next
expr_stmt|;
name|ih
operator|.
name|setInstruction
argument_list|(
name|i
argument_list|)
expr_stmt|;
return|return
name|ih
return|;
block|}
comment|/**      * Called by InstructionList.setPositions when setting the position for every      * instruction. In the presence of variable length instructions `setPositions()'      * performs multiple passes over the instruction list to calculate the      * correct (byte) positions and offsets by calling this function.      *      * @param offset additional offset caused by preceding (variable length) instructions      * @param max_offset the maximum offset that may be caused by these instructions      * @return additional offset caused by possible change of this instruction's length      */
specifier|protected
name|int
name|updatePosition
parameter_list|(
name|int
name|offset
parameter_list|,
name|int
name|max_offset
parameter_list|)
block|{
name|i_position
operator|+=
name|offset
expr_stmt|;
return|return
literal|0
return|;
block|}
comment|/** @return the position, i.e., the byte code offset of the contained      * instruction. This is accurate only after      * InstructionList.setPositions() has been called.      */
specifier|public
name|int
name|getPosition
parameter_list|()
block|{
return|return
name|i_position
return|;
block|}
comment|/** Set the position, i.e., the byte code offset of the contained      * instruction.      */
name|void
name|setPosition
parameter_list|(
name|int
name|pos
parameter_list|)
block|{
name|i_position
operator|=
name|pos
expr_stmt|;
block|}
comment|/** Overridden in BranchHandle      */
specifier|protected
name|void
name|addHandle
parameter_list|()
block|{
name|next
operator|=
name|ih_list
expr_stmt|;
name|ih_list
operator|=
name|this
expr_stmt|;
block|}
comment|/**      * Delete contents, i.e., remove user access and make handle reusable.      */
name|void
name|dispose
parameter_list|()
block|{
name|next
operator|=
name|prev
operator|=
literal|null
expr_stmt|;
name|instruction
operator|.
name|dispose
argument_list|()
expr_stmt|;
name|instruction
operator|=
literal|null
expr_stmt|;
name|i_position
operator|=
operator|-
literal|1
expr_stmt|;
name|attributes
operator|=
literal|null
expr_stmt|;
name|removeAllTargeters
argument_list|()
expr_stmt|;
name|addHandle
argument_list|()
expr_stmt|;
block|}
comment|/** Remove all targeters, if any.      */
specifier|public
name|void
name|removeAllTargeters
parameter_list|()
block|{
if|if
condition|(
name|targeters
operator|!=
literal|null
condition|)
block|{
name|targeters
operator|.
name|clear
argument_list|()
expr_stmt|;
block|}
block|}
comment|/**      * Denote this handle isn't referenced anymore by t.      */
specifier|public
name|void
name|removeTargeter
parameter_list|(
name|InstructionTargeter
name|t
parameter_list|)
block|{
if|if
condition|(
name|targeters
operator|!=
literal|null
condition|)
block|{
name|targeters
operator|.
name|remove
argument_list|(
name|t
argument_list|)
expr_stmt|;
block|}
block|}
comment|/**      * Denote this handle is being referenced by t.      */
specifier|public
name|void
name|addTargeter
parameter_list|(
name|InstructionTargeter
name|t
parameter_list|)
block|{
if|if
condition|(
name|targeters
operator|==
literal|null
condition|)
block|{
name|targeters
operator|=
operator|new
name|HashSet
argument_list|<>
argument_list|()
expr_stmt|;
block|}
comment|//if(!targeters.contains(t))
name|targeters
operator|.
name|add
argument_list|(
name|t
argument_list|)
expr_stmt|;
block|}
specifier|public
name|boolean
name|hasTargeters
parameter_list|()
block|{
return|return
operator|(
name|targeters
operator|!=
literal|null
operator|)
operator|&&
operator|(
name|targeters
operator|.
name|size
argument_list|()
operator|>
literal|0
operator|)
return|;
block|}
comment|/**      * @return null, if there are no targeters      */
specifier|public
name|InstructionTargeter
index|[]
name|getTargeters
parameter_list|()
block|{
if|if
condition|(
operator|!
name|hasTargeters
argument_list|()
condition|)
block|{
return|return
operator|new
name|InstructionTargeter
index|[
literal|0
index|]
return|;
block|}
name|InstructionTargeter
index|[]
name|t
init|=
operator|new
name|InstructionTargeter
index|[
name|targeters
operator|.
name|size
argument_list|()
index|]
decl_stmt|;
name|targeters
operator|.
name|toArray
argument_list|(
name|t
argument_list|)
expr_stmt|;
return|return
name|t
return|;
block|}
comment|/** @return a (verbose) string representation of the contained instruction.       */
specifier|public
name|String
name|toString
parameter_list|(
name|boolean
name|verbose
parameter_list|)
block|{
return|return
name|Utility
operator|.
name|format
argument_list|(
name|i_position
argument_list|,
literal|4
argument_list|,
literal|false
argument_list|,
literal|' '
argument_list|)
operator|+
literal|": "
operator|+
name|instruction
operator|.
name|toString
argument_list|(
name|verbose
argument_list|)
return|;
block|}
comment|/** @return a string representation of the contained instruction.       */
annotation|@
name|Override
specifier|public
name|String
name|toString
parameter_list|()
block|{
return|return
name|toString
argument_list|(
literal|true
argument_list|)
return|;
block|}
comment|/** Add an attribute to an instruction handle.      *      * @param key the key object to store/retrieve the attribute      * @param attr the attribute to associate with this handle      */
specifier|public
name|void
name|addAttribute
parameter_list|(
name|Object
name|key
parameter_list|,
name|Object
name|attr
parameter_list|)
block|{
if|if
condition|(
name|attributes
operator|==
literal|null
condition|)
block|{
name|attributes
operator|=
operator|new
name|HashMap
argument_list|<>
argument_list|(
literal|3
argument_list|)
expr_stmt|;
block|}
name|attributes
operator|.
name|put
argument_list|(
name|key
argument_list|,
name|attr
argument_list|)
expr_stmt|;
block|}
comment|/** Delete an attribute of an instruction handle.      *      * @param key the key object to retrieve the attribute      */
specifier|public
name|void
name|removeAttribute
parameter_list|(
name|Object
name|key
parameter_list|)
block|{
if|if
condition|(
name|attributes
operator|!=
literal|null
condition|)
block|{
name|attributes
operator|.
name|remove
argument_list|(
name|key
argument_list|)
expr_stmt|;
block|}
block|}
comment|/** Get attribute of an instruction handle.      *      * @param key the key object to store/retrieve the attribute      */
specifier|public
name|Object
name|getAttribute
parameter_list|(
name|Object
name|key
parameter_list|)
block|{
if|if
condition|(
name|attributes
operator|!=
literal|null
condition|)
block|{
return|return
name|attributes
operator|.
name|get
argument_list|(
name|key
argument_list|)
return|;
block|}
return|return
literal|null
return|;
block|}
comment|/** @return all attributes associated with this handle      */
specifier|public
name|Collection
argument_list|<
name|Object
argument_list|>
name|getAttributes
parameter_list|()
block|{
if|if
condition|(
name|attributes
operator|==
literal|null
condition|)
block|{
name|attributes
operator|=
operator|new
name|HashMap
argument_list|<>
argument_list|(
literal|3
argument_list|)
expr_stmt|;
block|}
return|return
name|attributes
operator|.
name|values
argument_list|()
return|;
block|}
comment|/** Convenience method, simply calls accept() on the contained instruction.      *      * @param v Visitor object      */
specifier|public
name|void
name|accept
parameter_list|(
name|Visitor
name|v
parameter_list|)
block|{
name|instruction
operator|.
name|accept
argument_list|(
name|v
argument_list|)
expr_stmt|;
block|}
comment|/**      * @param next the next to set      * @ since 6.0      */
specifier|final
name|InstructionHandle
name|setNext
parameter_list|(
name|InstructionHandle
name|next
parameter_list|)
block|{
name|this
operator|.
name|next
operator|=
name|next
expr_stmt|;
return|return
name|next
return|;
block|}
comment|/**      * @param prev the prev to set      * @ since 6.0      */
specifier|final
name|InstructionHandle
name|setPrev
parameter_list|(
name|InstructionHandle
name|prev
parameter_list|)
block|{
name|this
operator|.
name|prev
operator|=
name|prev
expr_stmt|;
return|return
name|prev
return|;
block|}
block|}
end_class

end_unit

