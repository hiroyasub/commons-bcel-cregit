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
name|classfile
operator|.
name|LocalVariable
import|;
end_import

begin_comment
comment|/**  * Represents a local variable within a method. It contains its  * scope, name and type. The generated LocalVariable object can be obtained  * with getLocalVariable which needs the instruction list and the constant  * pool as parameters.  *  * @see     LocalVariable  * @see     MethodGen  */
end_comment

begin_class
specifier|public
class|class
name|LocalVariableGen
implements|implements
name|InstructionTargeter
implements|,
name|NamedAndTyped
implements|,
name|Cloneable
block|{
specifier|private
name|int
name|index
decl_stmt|;
specifier|private
name|String
name|name
decl_stmt|;
specifier|private
name|Type
name|type
decl_stmt|;
specifier|private
name|InstructionHandle
name|start
decl_stmt|;
specifier|private
name|InstructionHandle
name|end
decl_stmt|;
specifier|private
name|int
name|origIndex
decl_stmt|;
comment|// never changes; used to match up with LocalVariableTypeTable entries
specifier|private
name|boolean
name|liveToEnd
decl_stmt|;
comment|/**      * Generate a local variable that with index `index'. Note that double and long      * variables need two indexs. Index indices have to be provided by the user.      *      * @param index index of local variable      * @param name its name      * @param type its type      * @param start from where the instruction is valid (null means from the start)      * @param end until where the instruction is valid (null means to the end)      */
specifier|public
name|LocalVariableGen
parameter_list|(
specifier|final
name|int
name|index
parameter_list|,
specifier|final
name|String
name|name
parameter_list|,
specifier|final
name|Type
name|type
parameter_list|,
specifier|final
name|InstructionHandle
name|start
parameter_list|,
specifier|final
name|InstructionHandle
name|end
parameter_list|)
block|{
if|if
condition|(
name|index
operator|<
literal|0
operator|||
name|index
operator|>
name|Const
operator|.
name|MAX_SHORT
condition|)
block|{
throw|throw
operator|new
name|ClassGenException
argument_list|(
literal|"Invalid index index: "
operator|+
name|index
argument_list|)
throw|;
block|}
name|this
operator|.
name|name
operator|=
name|name
expr_stmt|;
name|this
operator|.
name|type
operator|=
name|type
expr_stmt|;
name|this
operator|.
name|index
operator|=
name|index
expr_stmt|;
name|setStart
argument_list|(
name|start
argument_list|)
expr_stmt|;
name|setEnd
argument_list|(
name|end
argument_list|)
expr_stmt|;
name|this
operator|.
name|origIndex
operator|=
name|index
expr_stmt|;
name|this
operator|.
name|liveToEnd
operator|=
name|end
operator|==
literal|null
expr_stmt|;
block|}
comment|/**      * Generates a local variable that with index `index'. Note that double and long      * variables need two indexs. Index indices have to be provided by the user.      *      * @param index index of local variable      * @param name its name      * @param type its type      * @param start from where the instruction is valid (null means from the start)      * @param end until where the instruction is valid (null means to the end)      * @param origIndex index of local variable prior to any changes to index      */
specifier|public
name|LocalVariableGen
parameter_list|(
specifier|final
name|int
name|index
parameter_list|,
specifier|final
name|String
name|name
parameter_list|,
specifier|final
name|Type
name|type
parameter_list|,
specifier|final
name|InstructionHandle
name|start
parameter_list|,
specifier|final
name|InstructionHandle
name|end
parameter_list|,
specifier|final
name|int
name|origIndex
parameter_list|)
block|{
name|this
argument_list|(
name|index
argument_list|,
name|name
argument_list|,
name|type
argument_list|,
name|start
argument_list|,
name|end
argument_list|)
expr_stmt|;
name|this
operator|.
name|origIndex
operator|=
name|origIndex
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|Object
name|clone
parameter_list|()
block|{
try|try
block|{
return|return
name|super
operator|.
name|clone
argument_list|()
return|;
block|}
catch|catch
parameter_list|(
specifier|final
name|CloneNotSupportedException
name|e
parameter_list|)
block|{
throw|throw
operator|new
name|Error
argument_list|(
literal|"Clone Not Supported"
argument_list|)
throw|;
comment|// never happens
block|}
block|}
comment|/**      * @return true, if ih is target of this variable      */
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
name|start
operator|==
name|ih
operator|||
name|end
operator|==
name|ih
return|;
block|}
comment|/**      * Clear the references from and to this variable when it's removed.      */
name|void
name|dispose
parameter_list|()
block|{
name|setStart
argument_list|(
literal|null
argument_list|)
expr_stmt|;
name|setEnd
argument_list|(
literal|null
argument_list|)
expr_stmt|;
block|}
comment|/**      * We consider to local variables to be equal, if the use the same index and      * are valid in the same range.      */
annotation|@
name|Override
specifier|public
name|boolean
name|equals
parameter_list|(
specifier|final
name|Object
name|o
parameter_list|)
block|{
if|if
condition|(
operator|!
operator|(
name|o
operator|instanceof
name|LocalVariableGen
operator|)
condition|)
block|{
return|return
literal|false
return|;
block|}
specifier|final
name|LocalVariableGen
name|l
init|=
operator|(
name|LocalVariableGen
operator|)
name|o
decl_stmt|;
return|return
name|l
operator|.
name|index
operator|==
name|index
operator|&&
name|l
operator|.
name|start
operator|==
name|start
operator|&&
name|l
operator|.
name|end
operator|==
name|end
return|;
block|}
specifier|public
name|InstructionHandle
name|getEnd
parameter_list|()
block|{
return|return
name|end
return|;
block|}
specifier|public
name|int
name|getIndex
parameter_list|()
block|{
return|return
name|index
return|;
block|}
specifier|public
name|boolean
name|getLiveToEnd
parameter_list|()
block|{
return|return
name|liveToEnd
return|;
block|}
comment|/**      * Gets LocalVariable object.      *      * This relies on that the instruction list has already been dumped to byte code or      * or that the `setPositions' methods has been called for the instruction list.      *      * Note that due to the conversion from byte code offset to InstructionHandle,      * it is impossible to tell the difference between a live range that ends BEFORE      * the last insturction of the method or a live range that ends AFTER the last      * instruction of the method.  Hence the liveToEnd flag to differentiate      * between these two cases.      *      * @param cp constant pool      */
specifier|public
name|LocalVariable
name|getLocalVariable
parameter_list|(
specifier|final
name|ConstantPoolGen
name|cp
parameter_list|)
block|{
name|int
name|start_pc
init|=
literal|0
decl_stmt|;
name|int
name|length
init|=
literal|0
decl_stmt|;
if|if
condition|(
name|start
operator|!=
literal|null
operator|&&
name|end
operator|!=
literal|null
condition|)
block|{
name|start_pc
operator|=
name|start
operator|.
name|getPosition
argument_list|()
expr_stmt|;
name|length
operator|=
name|end
operator|.
name|getPosition
argument_list|()
operator|-
name|start_pc
expr_stmt|;
if|if
condition|(
name|end
operator|.
name|getNext
argument_list|()
operator|==
literal|null
operator|&&
name|liveToEnd
condition|)
block|{
name|length
operator|+=
name|end
operator|.
name|getInstruction
argument_list|()
operator|.
name|getLength
argument_list|()
expr_stmt|;
block|}
block|}
specifier|final
name|int
name|name_index
init|=
name|cp
operator|.
name|addUtf8
argument_list|(
name|name
argument_list|)
decl_stmt|;
specifier|final
name|int
name|signature_index
init|=
name|cp
operator|.
name|addUtf8
argument_list|(
name|type
operator|.
name|getSignature
argument_list|()
argument_list|)
decl_stmt|;
return|return
operator|new
name|LocalVariable
argument_list|(
name|start_pc
argument_list|,
name|length
argument_list|,
name|name_index
argument_list|,
name|signature_index
argument_list|,
name|index
argument_list|,
name|cp
operator|.
name|getConstantPool
argument_list|()
argument_list|,
name|origIndex
argument_list|)
return|;
block|}
annotation|@
name|Override
specifier|public
name|String
name|getName
parameter_list|()
block|{
return|return
name|name
return|;
block|}
specifier|public
name|int
name|getOrigIndex
parameter_list|()
block|{
return|return
name|origIndex
return|;
block|}
specifier|public
name|InstructionHandle
name|getStart
parameter_list|()
block|{
return|return
name|start
return|;
block|}
annotation|@
name|Override
specifier|public
name|Type
name|getType
parameter_list|()
block|{
return|return
name|type
return|;
block|}
annotation|@
name|Override
specifier|public
name|int
name|hashCode
parameter_list|()
block|{
comment|// If the user changes the name or type, problems with the targeter hashmap will occur.
comment|// Note: index cannot be part of hash as it may be changed by the user.
return|return
name|name
operator|.
name|hashCode
argument_list|()
operator|^
name|type
operator|.
name|hashCode
argument_list|()
return|;
block|}
specifier|public
name|void
name|setEnd
parameter_list|(
specifier|final
name|InstructionHandle
name|end
parameter_list|)
block|{
comment|// TODO could be package-protected?
name|BranchInstruction
operator|.
name|notifyTarget
argument_list|(
name|this
operator|.
name|end
argument_list|,
name|end
argument_list|,
name|this
argument_list|)
expr_stmt|;
name|this
operator|.
name|end
operator|=
name|end
expr_stmt|;
block|}
specifier|public
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
specifier|public
name|void
name|setLiveToEnd
parameter_list|(
specifier|final
name|boolean
name|live_to_end
parameter_list|)
block|{
name|this
operator|.
name|liveToEnd
operator|=
name|live_to_end
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|setName
parameter_list|(
specifier|final
name|String
name|name
parameter_list|)
block|{
name|this
operator|.
name|name
operator|=
name|name
expr_stmt|;
block|}
specifier|public
name|void
name|setStart
parameter_list|(
specifier|final
name|InstructionHandle
name|start
parameter_list|)
block|{
comment|// TODO could be package-protected?
name|BranchInstruction
operator|.
name|notifyTarget
argument_list|(
name|this
operator|.
name|start
argument_list|,
name|start
argument_list|,
name|this
argument_list|)
expr_stmt|;
name|this
operator|.
name|start
operator|=
name|start
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|setType
parameter_list|(
specifier|final
name|Type
name|type
parameter_list|)
block|{
name|this
operator|.
name|type
operator|=
name|type
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|String
name|toString
parameter_list|()
block|{
return|return
literal|"LocalVariableGen("
operator|+
name|name
operator|+
literal|", "
operator|+
name|type
operator|+
literal|", "
operator|+
name|start
operator|+
literal|", "
operator|+
name|end
operator|+
literal|")"
return|;
block|}
comment|/**      * @param old_ih old target, either start or end      * @param new_ih new target      */
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
name|boolean
name|targeted
init|=
literal|false
decl_stmt|;
if|if
condition|(
name|start
operator|==
name|old_ih
condition|)
block|{
name|targeted
operator|=
literal|true
expr_stmt|;
name|setStart
argument_list|(
name|new_ih
argument_list|)
expr_stmt|;
block|}
if|if
condition|(
name|end
operator|==
name|old_ih
condition|)
block|{
name|targeted
operator|=
literal|true
expr_stmt|;
name|setEnd
argument_list|(
name|new_ih
argument_list|)
expr_stmt|;
block|}
if|if
condition|(
operator|!
name|targeted
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
literal|", but {"
operator|+
name|start
operator|+
literal|", "
operator|+
name|end
operator|+
literal|"}"
argument_list|)
throw|;
block|}
block|}
block|}
end_class

end_unit

