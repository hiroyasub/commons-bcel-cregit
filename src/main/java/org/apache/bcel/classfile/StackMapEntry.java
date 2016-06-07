begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
begin_comment
comment|/*  * Licensed to the Apache Software Foundation (ASF) under one or more  * contributor license agreements.  See the NOTICE file distributed with  * this work for additional information regarding copyright ownership.  * The ASF licenses this file to You under the Apache License, Version 2.0  * (the "License"); you may not use this file except in compliance with  * the License.  You may obtain a copy of the License at  *  *      http://www.apache.org/licenses/LICENSE-2.0  *  *  Unless required by applicable law or agreed to in writing, software  *  distributed under the License is distributed on an "AS IS" BASIS,  *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  *  See the License for the specific language governing permissions and  *  limitations under the License.  */
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
import|import
name|java
operator|.
name|io
operator|.
name|DataInput
import|;
end_import

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
name|Const
import|;
end_import

begin_comment
comment|/**  * This class represents a stack map entry recording the types of  * local variables and the the of stack items at a given byte code offset.  * See CLDC specification ï¿½5.3.1.2  *  * @version $Id$  * @see     StackMap  * @see     StackMapType  */
end_comment

begin_class
specifier|public
specifier|final
class|class
name|StackMapEntry
implements|implements
name|Node
implements|,
name|Cloneable
block|{
specifier|private
name|int
name|frame_type
decl_stmt|;
specifier|private
name|int
name|byte_code_offset
decl_stmt|;
specifier|private
name|StackMapType
index|[]
name|types_of_locals
decl_stmt|;
specifier|private
name|StackMapType
index|[]
name|types_of_stack_items
decl_stmt|;
specifier|private
name|ConstantPool
name|constant_pool
decl_stmt|;
comment|/**      * Construct object from input stream.      *       * @param input Input stream      * @throws IOException      */
name|StackMapEntry
parameter_list|(
specifier|final
name|DataInput
name|input
parameter_list|,
specifier|final
name|ConstantPool
name|constant_pool
parameter_list|)
throws|throws
name|IOException
block|{
name|this
argument_list|(
name|input
operator|.
name|readByte
argument_list|()
operator|&
literal|0xFF
argument_list|,
operator|-
literal|1
argument_list|,
literal|null
argument_list|,
literal|null
argument_list|,
name|constant_pool
argument_list|)
expr_stmt|;
if|if
condition|(
name|frame_type
operator|>=
name|Const
operator|.
name|SAME_FRAME
operator|&&
name|frame_type
operator|<=
name|Const
operator|.
name|SAME_FRAME_MAX
condition|)
block|{
name|byte_code_offset
operator|=
name|frame_type
operator|-
name|Const
operator|.
name|SAME_FRAME
expr_stmt|;
block|}
if|else if
condition|(
name|frame_type
operator|>=
name|Const
operator|.
name|SAME_LOCALS_1_STACK_ITEM_FRAME
operator|&&
name|frame_type
operator|<=
name|Const
operator|.
name|SAME_LOCALS_1_STACK_ITEM_FRAME_MAX
condition|)
block|{
name|byte_code_offset
operator|=
name|frame_type
operator|-
name|Const
operator|.
name|SAME_LOCALS_1_STACK_ITEM_FRAME
expr_stmt|;
name|types_of_stack_items
operator|=
operator|new
name|StackMapType
index|[
literal|1
index|]
expr_stmt|;
name|types_of_stack_items
index|[
literal|0
index|]
operator|=
operator|new
name|StackMapType
argument_list|(
name|input
argument_list|,
name|constant_pool
argument_list|)
expr_stmt|;
block|}
if|else if
condition|(
name|frame_type
operator|==
name|Const
operator|.
name|SAME_LOCALS_1_STACK_ITEM_FRAME_EXTENDED
condition|)
block|{
name|byte_code_offset
operator|=
name|input
operator|.
name|readShort
argument_list|()
expr_stmt|;
name|types_of_stack_items
operator|=
operator|new
name|StackMapType
index|[
literal|1
index|]
expr_stmt|;
name|types_of_stack_items
index|[
literal|0
index|]
operator|=
operator|new
name|StackMapType
argument_list|(
name|input
argument_list|,
name|constant_pool
argument_list|)
expr_stmt|;
block|}
if|else if
condition|(
name|frame_type
operator|>=
name|Const
operator|.
name|CHOP_FRAME
operator|&&
name|frame_type
operator|<=
name|Const
operator|.
name|CHOP_FRAME_MAX
condition|)
block|{
name|byte_code_offset
operator|=
name|input
operator|.
name|readShort
argument_list|()
expr_stmt|;
block|}
if|else if
condition|(
name|frame_type
operator|==
name|Const
operator|.
name|SAME_FRAME_EXTENDED
condition|)
block|{
name|byte_code_offset
operator|=
name|input
operator|.
name|readShort
argument_list|()
expr_stmt|;
block|}
if|else if
condition|(
name|frame_type
operator|>=
name|Const
operator|.
name|APPEND_FRAME
operator|&&
name|frame_type
operator|<=
name|Const
operator|.
name|APPEND_FRAME_MAX
condition|)
block|{
name|byte_code_offset
operator|=
name|input
operator|.
name|readShort
argument_list|()
expr_stmt|;
name|int
name|number_of_locals
init|=
name|frame_type
operator|-
literal|251
decl_stmt|;
name|types_of_locals
operator|=
operator|new
name|StackMapType
index|[
name|number_of_locals
index|]
expr_stmt|;
for|for
control|(
name|int
name|i
init|=
literal|0
init|;
name|i
operator|<
name|number_of_locals
condition|;
name|i
operator|++
control|)
block|{
name|types_of_locals
index|[
name|i
index|]
operator|=
operator|new
name|StackMapType
argument_list|(
name|input
argument_list|,
name|constant_pool
argument_list|)
expr_stmt|;
block|}
block|}
if|else if
condition|(
name|frame_type
operator|==
name|Const
operator|.
name|FULL_FRAME
condition|)
block|{
name|byte_code_offset
operator|=
name|input
operator|.
name|readShort
argument_list|()
expr_stmt|;
name|int
name|number_of_locals
init|=
name|input
operator|.
name|readShort
argument_list|()
decl_stmt|;
name|types_of_locals
operator|=
operator|new
name|StackMapType
index|[
name|number_of_locals
index|]
expr_stmt|;
for|for
control|(
name|int
name|i
init|=
literal|0
init|;
name|i
operator|<
name|number_of_locals
condition|;
name|i
operator|++
control|)
block|{
name|types_of_locals
index|[
name|i
index|]
operator|=
operator|new
name|StackMapType
argument_list|(
name|input
argument_list|,
name|constant_pool
argument_list|)
expr_stmt|;
block|}
name|int
name|number_of_stack_items
init|=
name|input
operator|.
name|readShort
argument_list|()
decl_stmt|;
name|types_of_stack_items
operator|=
operator|new
name|StackMapType
index|[
name|number_of_stack_items
index|]
expr_stmt|;
for|for
control|(
name|int
name|i
init|=
literal|0
init|;
name|i
operator|<
name|number_of_stack_items
condition|;
name|i
operator|++
control|)
block|{
name|types_of_stack_items
index|[
name|i
index|]
operator|=
operator|new
name|StackMapType
argument_list|(
name|input
argument_list|,
name|constant_pool
argument_list|)
expr_stmt|;
block|}
block|}
else|else
block|{
comment|/* Can't happen */
throw|throw
operator|new
name|ClassFormatException
argument_list|(
literal|"Invalid frame type found while parsing stack map table: "
operator|+
name|frame_type
argument_list|)
throw|;
block|}
block|}
comment|/**      * DO NOT USE      *      * @param byte_code_offset      * @param number_of_locals NOT USED      * @param types_of_locals array of {@link StackMapType}s of locals      * @param number_of_stack_items NOT USED      * @param types_of_stack_items array ot {@link StackMapType}s of stack items      * @param constant_pool the constant pool      * @deprecated Since 6.0, use {@link #StackMapEntry(int, int, StackMapType[], StackMapType[], ConstantPool)}      * instead      */
specifier|public
name|StackMapEntry
parameter_list|(
specifier|final
name|int
name|byte_code_offset
parameter_list|,
specifier|final
name|int
name|number_of_locals
parameter_list|,
specifier|final
name|StackMapType
index|[]
name|types_of_locals
parameter_list|,
specifier|final
name|int
name|number_of_stack_items
parameter_list|,
specifier|final
name|StackMapType
index|[]
name|types_of_stack_items
parameter_list|,
specifier|final
name|ConstantPool
name|constant_pool
parameter_list|)
block|{
name|this
operator|.
name|byte_code_offset
operator|=
name|byte_code_offset
expr_stmt|;
name|this
operator|.
name|types_of_locals
operator|=
name|types_of_locals
operator|!=
literal|null
condition|?
name|types_of_locals
else|:
operator|new
name|StackMapType
index|[
literal|0
index|]
expr_stmt|;
name|this
operator|.
name|types_of_stack_items
operator|=
name|types_of_stack_items
operator|!=
literal|null
condition|?
name|types_of_stack_items
else|:
operator|new
name|StackMapType
index|[
literal|0
index|]
expr_stmt|;
name|this
operator|.
name|constant_pool
operator|=
name|constant_pool
expr_stmt|;
block|}
comment|/**      * Create an instance      *      * @param tag the frame_type to use      * @param byte_code_offset      * @param types_of_locals array of {@link StackMapType}s of locals      * @param types_of_stack_items array ot {@link StackMapType}s of stack items      * @param constant_pool the constant pool      */
specifier|public
name|StackMapEntry
parameter_list|(
specifier|final
name|int
name|tag
parameter_list|,
specifier|final
name|int
name|byte_code_offset
parameter_list|,
specifier|final
name|StackMapType
index|[]
name|types_of_locals
parameter_list|,
specifier|final
name|StackMapType
index|[]
name|types_of_stack_items
parameter_list|,
specifier|final
name|ConstantPool
name|constant_pool
parameter_list|)
block|{
name|this
operator|.
name|frame_type
operator|=
name|tag
expr_stmt|;
name|this
operator|.
name|byte_code_offset
operator|=
name|byte_code_offset
expr_stmt|;
name|this
operator|.
name|types_of_locals
operator|=
name|types_of_locals
operator|!=
literal|null
condition|?
name|types_of_locals
else|:
operator|new
name|StackMapType
index|[
literal|0
index|]
expr_stmt|;
name|this
operator|.
name|types_of_stack_items
operator|=
name|types_of_stack_items
operator|!=
literal|null
condition|?
name|types_of_stack_items
else|:
operator|new
name|StackMapType
index|[
literal|0
index|]
expr_stmt|;
name|this
operator|.
name|constant_pool
operator|=
name|constant_pool
expr_stmt|;
block|}
comment|/**      * Dump stack map entry      *      * @param file Output file stream      * @throws IOException      */
specifier|public
specifier|final
name|void
name|dump
parameter_list|(
specifier|final
name|DataOutputStream
name|file
parameter_list|)
throws|throws
name|IOException
block|{
name|file
operator|.
name|write
argument_list|(
name|frame_type
argument_list|)
expr_stmt|;
if|if
condition|(
name|frame_type
operator|>=
name|Const
operator|.
name|SAME_FRAME
operator|&&
name|frame_type
operator|<=
name|Const
operator|.
name|SAME_FRAME_MAX
condition|)
block|{
comment|// nothing to be done
block|}
if|else if
condition|(
name|frame_type
operator|>=
name|Const
operator|.
name|SAME_LOCALS_1_STACK_ITEM_FRAME
operator|&&
name|frame_type
operator|<=
name|Const
operator|.
name|SAME_LOCALS_1_STACK_ITEM_FRAME_MAX
condition|)
block|{
name|types_of_stack_items
index|[
literal|0
index|]
operator|.
name|dump
argument_list|(
name|file
argument_list|)
expr_stmt|;
block|}
if|else if
condition|(
name|frame_type
operator|==
name|Const
operator|.
name|SAME_LOCALS_1_STACK_ITEM_FRAME_EXTENDED
condition|)
block|{
name|file
operator|.
name|writeShort
argument_list|(
name|byte_code_offset
argument_list|)
expr_stmt|;
name|types_of_stack_items
index|[
literal|0
index|]
operator|.
name|dump
argument_list|(
name|file
argument_list|)
expr_stmt|;
block|}
if|else if
condition|(
name|frame_type
operator|>=
name|Const
operator|.
name|CHOP_FRAME
operator|&&
name|frame_type
operator|<=
name|Const
operator|.
name|CHOP_FRAME_MAX
condition|)
block|{
name|file
operator|.
name|writeShort
argument_list|(
name|byte_code_offset
argument_list|)
expr_stmt|;
block|}
if|else if
condition|(
name|frame_type
operator|==
name|Const
operator|.
name|SAME_FRAME_EXTENDED
condition|)
block|{
name|file
operator|.
name|writeShort
argument_list|(
name|byte_code_offset
argument_list|)
expr_stmt|;
block|}
if|else if
condition|(
name|frame_type
operator|>=
name|Const
operator|.
name|APPEND_FRAME
operator|&&
name|frame_type
operator|<=
name|Const
operator|.
name|APPEND_FRAME_MAX
condition|)
block|{
name|file
operator|.
name|writeShort
argument_list|(
name|byte_code_offset
argument_list|)
expr_stmt|;
for|for
control|(
name|StackMapType
name|type
range|:
name|types_of_locals
control|)
block|{
name|type
operator|.
name|dump
argument_list|(
name|file
argument_list|)
expr_stmt|;
block|}
block|}
if|else if
condition|(
name|frame_type
operator|==
name|Const
operator|.
name|FULL_FRAME
condition|)
block|{
name|file
operator|.
name|writeShort
argument_list|(
name|byte_code_offset
argument_list|)
expr_stmt|;
name|file
operator|.
name|writeShort
argument_list|(
name|types_of_locals
operator|.
name|length
argument_list|)
expr_stmt|;
for|for
control|(
name|StackMapType
name|type
range|:
name|types_of_locals
control|)
block|{
name|type
operator|.
name|dump
argument_list|(
name|file
argument_list|)
expr_stmt|;
block|}
name|file
operator|.
name|writeShort
argument_list|(
name|types_of_stack_items
operator|.
name|length
argument_list|)
expr_stmt|;
for|for
control|(
name|StackMapType
name|type
range|:
name|types_of_stack_items
control|)
block|{
name|type
operator|.
name|dump
argument_list|(
name|file
argument_list|)
expr_stmt|;
block|}
block|}
else|else
block|{
comment|/* Can't happen */
throw|throw
operator|new
name|ClassFormatException
argument_list|(
literal|"Invalid Stack map table tag: "
operator|+
name|frame_type
argument_list|)
throw|;
block|}
block|}
comment|/**      * @return String representation.      */
annotation|@
name|Override
specifier|public
specifier|final
name|String
name|toString
parameter_list|()
block|{
name|StringBuilder
name|buf
init|=
operator|new
name|StringBuilder
argument_list|(
literal|64
argument_list|)
decl_stmt|;
name|buf
operator|.
name|append
argument_list|(
literal|"("
argument_list|)
expr_stmt|;
if|if
condition|(
name|frame_type
operator|>=
name|Const
operator|.
name|SAME_FRAME
operator|&&
name|frame_type
operator|<=
name|Const
operator|.
name|SAME_FRAME_MAX
condition|)
block|{
name|buf
operator|.
name|append
argument_list|(
literal|"SAME"
argument_list|)
expr_stmt|;
block|}
if|else if
condition|(
name|frame_type
operator|>=
name|Const
operator|.
name|SAME_LOCALS_1_STACK_ITEM_FRAME
operator|&&
name|frame_type
operator|<=
name|Const
operator|.
name|SAME_LOCALS_1_STACK_ITEM_FRAME_MAX
condition|)
block|{
name|buf
operator|.
name|append
argument_list|(
literal|"SAME_LOCALS_1_STACK"
argument_list|)
expr_stmt|;
block|}
if|else if
condition|(
name|frame_type
operator|==
name|Const
operator|.
name|SAME_LOCALS_1_STACK_ITEM_FRAME_EXTENDED
condition|)
block|{
name|buf
operator|.
name|append
argument_list|(
literal|"SAME_LOCALS_1_STACK_EXTENDED"
argument_list|)
expr_stmt|;
block|}
if|else if
condition|(
name|frame_type
operator|>=
name|Const
operator|.
name|CHOP_FRAME
operator|&&
name|frame_type
operator|<=
name|Const
operator|.
name|CHOP_FRAME_MAX
condition|)
block|{
name|buf
operator|.
name|append
argument_list|(
literal|"CHOP "
argument_list|)
operator|.
name|append
argument_list|(
name|String
operator|.
name|valueOf
argument_list|(
literal|251
operator|-
name|frame_type
argument_list|)
argument_list|)
expr_stmt|;
block|}
if|else if
condition|(
name|frame_type
operator|==
name|Const
operator|.
name|SAME_FRAME_EXTENDED
condition|)
block|{
name|buf
operator|.
name|append
argument_list|(
literal|"SAME_EXTENDED"
argument_list|)
expr_stmt|;
block|}
if|else if
condition|(
name|frame_type
operator|>=
name|Const
operator|.
name|APPEND_FRAME
operator|&&
name|frame_type
operator|<=
name|Const
operator|.
name|APPEND_FRAME_MAX
condition|)
block|{
name|buf
operator|.
name|append
argument_list|(
literal|"APPEND "
argument_list|)
operator|.
name|append
argument_list|(
name|String
operator|.
name|valueOf
argument_list|(
name|frame_type
operator|-
literal|251
argument_list|)
argument_list|)
expr_stmt|;
block|}
if|else if
condition|(
name|frame_type
operator|==
name|Const
operator|.
name|FULL_FRAME
condition|)
block|{
name|buf
operator|.
name|append
argument_list|(
literal|"FULL"
argument_list|)
expr_stmt|;
block|}
else|else
block|{
name|buf
operator|.
name|append
argument_list|(
literal|"UNKNOWN ("
argument_list|)
operator|.
name|append
argument_list|(
name|frame_type
argument_list|)
operator|.
name|append
argument_list|(
literal|")"
argument_list|)
expr_stmt|;
block|}
name|buf
operator|.
name|append
argument_list|(
literal|", offset delta="
argument_list|)
operator|.
name|append
argument_list|(
name|byte_code_offset
argument_list|)
expr_stmt|;
if|if
condition|(
name|types_of_locals
operator|.
name|length
operator|>
literal|0
condition|)
block|{
name|buf
operator|.
name|append
argument_list|(
literal|", locals={"
argument_list|)
expr_stmt|;
for|for
control|(
name|int
name|i
init|=
literal|0
init|;
name|i
operator|<
name|types_of_locals
operator|.
name|length
condition|;
name|i
operator|++
control|)
block|{
name|buf
operator|.
name|append
argument_list|(
name|types_of_locals
index|[
name|i
index|]
argument_list|)
expr_stmt|;
if|if
condition|(
name|i
operator|<
name|types_of_locals
operator|.
name|length
operator|-
literal|1
condition|)
block|{
name|buf
operator|.
name|append
argument_list|(
literal|", "
argument_list|)
expr_stmt|;
block|}
block|}
name|buf
operator|.
name|append
argument_list|(
literal|"}"
argument_list|)
expr_stmt|;
block|}
if|if
condition|(
name|types_of_stack_items
operator|.
name|length
operator|>
literal|0
condition|)
block|{
name|buf
operator|.
name|append
argument_list|(
literal|", stack items={"
argument_list|)
expr_stmt|;
for|for
control|(
name|int
name|i
init|=
literal|0
init|;
name|i
operator|<
name|types_of_stack_items
operator|.
name|length
condition|;
name|i
operator|++
control|)
block|{
name|buf
operator|.
name|append
argument_list|(
name|types_of_stack_items
index|[
name|i
index|]
argument_list|)
expr_stmt|;
if|if
condition|(
name|i
operator|<
name|types_of_stack_items
operator|.
name|length
operator|-
literal|1
condition|)
block|{
name|buf
operator|.
name|append
argument_list|(
literal|", "
argument_list|)
expr_stmt|;
block|}
block|}
name|buf
operator|.
name|append
argument_list|(
literal|"}"
argument_list|)
expr_stmt|;
block|}
name|buf
operator|.
name|append
argument_list|(
literal|")"
argument_list|)
expr_stmt|;
return|return
name|buf
operator|.
name|toString
argument_list|()
return|;
block|}
comment|/**      * Calculate stack map entry size      *      */
name|int
name|getMapEntrySize
parameter_list|()
block|{
if|if
condition|(
name|frame_type
operator|>=
name|Const
operator|.
name|SAME_FRAME
operator|&&
name|frame_type
operator|<=
name|Const
operator|.
name|SAME_FRAME_MAX
condition|)
block|{
return|return
literal|1
return|;
block|}
if|else if
condition|(
name|frame_type
operator|>=
name|Const
operator|.
name|SAME_LOCALS_1_STACK_ITEM_FRAME
operator|&&
name|frame_type
operator|<=
name|Const
operator|.
name|SAME_LOCALS_1_STACK_ITEM_FRAME_MAX
condition|)
block|{
return|return
literal|1
operator|+
operator|(
name|types_of_stack_items
index|[
literal|0
index|]
operator|.
name|hasIndex
argument_list|()
condition|?
literal|3
else|:
literal|1
operator|)
return|;
block|}
if|else if
condition|(
name|frame_type
operator|==
name|Const
operator|.
name|SAME_LOCALS_1_STACK_ITEM_FRAME_EXTENDED
condition|)
block|{
return|return
literal|3
operator|+
operator|(
name|types_of_stack_items
index|[
literal|0
index|]
operator|.
name|hasIndex
argument_list|()
condition|?
literal|3
else|:
literal|1
operator|)
return|;
block|}
if|else if
condition|(
name|frame_type
operator|>=
name|Const
operator|.
name|CHOP_FRAME
operator|&&
name|frame_type
operator|<=
name|Const
operator|.
name|CHOP_FRAME_MAX
condition|)
block|{
return|return
literal|3
return|;
block|}
if|else if
condition|(
name|frame_type
operator|==
name|Const
operator|.
name|SAME_FRAME_EXTENDED
condition|)
block|{
return|return
literal|3
return|;
block|}
if|else if
condition|(
name|frame_type
operator|>=
name|Const
operator|.
name|APPEND_FRAME
operator|&&
name|frame_type
operator|<=
name|Const
operator|.
name|APPEND_FRAME_MAX
condition|)
block|{
name|int
name|len
init|=
literal|3
decl_stmt|;
for|for
control|(
name|StackMapType
name|types_of_local
range|:
name|types_of_locals
control|)
block|{
name|len
operator|+=
name|types_of_local
operator|.
name|hasIndex
argument_list|()
condition|?
literal|3
else|:
literal|1
expr_stmt|;
block|}
return|return
name|len
return|;
block|}
if|else if
condition|(
name|frame_type
operator|==
name|Const
operator|.
name|FULL_FRAME
condition|)
block|{
name|int
name|len
init|=
literal|7
decl_stmt|;
for|for
control|(
name|StackMapType
name|types_of_local
range|:
name|types_of_locals
control|)
block|{
name|len
operator|+=
name|types_of_local
operator|.
name|hasIndex
argument_list|()
condition|?
literal|3
else|:
literal|1
expr_stmt|;
block|}
for|for
control|(
name|StackMapType
name|types_of_stack_item
range|:
name|types_of_stack_items
control|)
block|{
name|len
operator|+=
name|types_of_stack_item
operator|.
name|hasIndex
argument_list|()
condition|?
literal|3
else|:
literal|1
expr_stmt|;
block|}
return|return
name|len
return|;
block|}
else|else
block|{
throw|throw
operator|new
name|RuntimeException
argument_list|(
literal|"Invalid StackMap frame_type: "
operator|+
name|frame_type
argument_list|)
throw|;
block|}
block|}
specifier|public
name|void
name|setFrameType
parameter_list|(
specifier|final
name|int
name|f
parameter_list|)
block|{
if|if
condition|(
name|f
operator|>=
name|Const
operator|.
name|SAME_FRAME
operator|&&
name|f
operator|<=
name|Const
operator|.
name|SAME_FRAME_MAX
condition|)
block|{
name|byte_code_offset
operator|=
name|f
operator|-
name|Const
operator|.
name|SAME_FRAME
expr_stmt|;
block|}
if|else if
condition|(
name|f
operator|>=
name|Const
operator|.
name|SAME_LOCALS_1_STACK_ITEM_FRAME
operator|&&
name|f
operator|<=
name|Const
operator|.
name|SAME_LOCALS_1_STACK_ITEM_FRAME_MAX
condition|)
block|{
name|byte_code_offset
operator|=
name|f
operator|-
name|Const
operator|.
name|SAME_LOCALS_1_STACK_ITEM_FRAME
expr_stmt|;
block|}
if|else if
condition|(
name|f
operator|==
name|Const
operator|.
name|SAME_LOCALS_1_STACK_ITEM_FRAME_EXTENDED
condition|)
block|{
comment|// CHECKSTYLE IGNORE EmptyBlock
block|}
if|else if
condition|(
name|f
operator|>=
name|Const
operator|.
name|CHOP_FRAME
operator|&&
name|f
operator|<=
name|Const
operator|.
name|CHOP_FRAME_MAX
condition|)
block|{
comment|// CHECKSTYLE IGNORE EmptyBlock
block|}
if|else if
condition|(
name|f
operator|==
name|Const
operator|.
name|SAME_FRAME_EXTENDED
condition|)
block|{
comment|// CHECKSTYLE IGNORE EmptyBlock
block|}
if|else if
condition|(
name|f
operator|>=
name|Const
operator|.
name|APPEND_FRAME
operator|&&
name|f
operator|<=
name|Const
operator|.
name|APPEND_FRAME_MAX
condition|)
block|{
comment|// CHECKSTYLE IGNORE EmptyBlock
block|}
if|else if
condition|(
name|f
operator|==
name|Const
operator|.
name|FULL_FRAME
condition|)
block|{
comment|// CHECKSTYLE IGNORE EmptyBlock
block|}
else|else
block|{
throw|throw
operator|new
name|RuntimeException
argument_list|(
literal|"Invalid StackMap frame_type"
argument_list|)
throw|;
block|}
name|frame_type
operator|=
name|f
expr_stmt|;
block|}
specifier|public
name|int
name|getFrameType
parameter_list|()
block|{
return|return
name|frame_type
return|;
block|}
specifier|public
name|void
name|setByteCodeOffset
parameter_list|(
specifier|final
name|int
name|new_offset
parameter_list|)
block|{
if|if
condition|(
name|new_offset
operator|<
literal|0
operator|||
name|new_offset
operator|>
literal|32767
condition|)
block|{
throw|throw
operator|new
name|RuntimeException
argument_list|(
literal|"Invalid StackMap offset: "
operator|+
name|new_offset
argument_list|)
throw|;
block|}
if|if
condition|(
name|frame_type
operator|>=
name|Const
operator|.
name|SAME_FRAME
operator|&&
name|frame_type
operator|<=
name|Const
operator|.
name|SAME_FRAME_MAX
condition|)
block|{
if|if
condition|(
name|new_offset
operator|>
name|Const
operator|.
name|SAME_FRAME_MAX
condition|)
block|{
name|frame_type
operator|=
name|Const
operator|.
name|SAME_FRAME_EXTENDED
expr_stmt|;
block|}
else|else
block|{
name|frame_type
operator|=
name|new_offset
expr_stmt|;
block|}
block|}
if|else if
condition|(
name|frame_type
operator|>=
name|Const
operator|.
name|SAME_LOCALS_1_STACK_ITEM_FRAME
operator|&&
name|frame_type
operator|<=
name|Const
operator|.
name|SAME_LOCALS_1_STACK_ITEM_FRAME_MAX
condition|)
block|{
if|if
condition|(
name|new_offset
operator|>
name|Const
operator|.
name|SAME_FRAME_MAX
condition|)
block|{
name|frame_type
operator|=
name|Const
operator|.
name|SAME_LOCALS_1_STACK_ITEM_FRAME_EXTENDED
expr_stmt|;
block|}
else|else
block|{
name|frame_type
operator|=
name|Const
operator|.
name|SAME_LOCALS_1_STACK_ITEM_FRAME
operator|+
name|new_offset
expr_stmt|;
block|}
block|}
if|else if
condition|(
name|frame_type
operator|==
name|Const
operator|.
name|SAME_LOCALS_1_STACK_ITEM_FRAME_EXTENDED
condition|)
block|{
comment|// CHECKSTYLE IGNORE EmptyBlock
block|}
if|else if
condition|(
name|frame_type
operator|>=
name|Const
operator|.
name|CHOP_FRAME
operator|&&
name|frame_type
operator|<=
name|Const
operator|.
name|CHOP_FRAME_MAX
condition|)
block|{
comment|// CHECKSTYLE IGNORE EmptyBlock
block|}
if|else if
condition|(
name|frame_type
operator|==
name|Const
operator|.
name|SAME_FRAME_EXTENDED
condition|)
block|{
comment|// CHECKSTYLE IGNORE EmptyBlock
block|}
if|else if
condition|(
name|frame_type
operator|>=
name|Const
operator|.
name|APPEND_FRAME
operator|&&
name|frame_type
operator|<=
name|Const
operator|.
name|APPEND_FRAME_MAX
condition|)
block|{
comment|// CHECKSTYLE IGNORE EmptyBlock
block|}
if|else if
condition|(
name|frame_type
operator|==
name|Const
operator|.
name|FULL_FRAME
condition|)
block|{
comment|// CHECKSTYLE IGNORE EmptyBlock
block|}
else|else
block|{
throw|throw
operator|new
name|RuntimeException
argument_list|(
literal|"Invalid StackMap frame_type: "
operator|+
name|frame_type
argument_list|)
throw|;
block|}
name|byte_code_offset
operator|=
name|new_offset
expr_stmt|;
block|}
comment|/**      * Update the distance (as an offset delta) from this StackMap      * entry to the next.  Note that this might cause the the      * frame type to change.  Note also that delta may be negative.      *      * @param int offset delta      */
specifier|public
name|void
name|updateByteCodeOffset
parameter_list|(
specifier|final
name|int
name|delta
parameter_list|)
block|{
name|setByteCodeOffset
argument_list|(
name|byte_code_offset
operator|+
name|delta
argument_list|)
expr_stmt|;
block|}
specifier|public
name|int
name|getByteCodeOffset
parameter_list|()
block|{
return|return
name|byte_code_offset
return|;
block|}
annotation|@
name|java
operator|.
name|lang
operator|.
name|Deprecated
specifier|public
name|void
name|setNumberOfLocals
parameter_list|(
specifier|final
name|int
name|n
parameter_list|)
block|{
comment|// TODO unused
block|}
specifier|public
name|int
name|getNumberOfLocals
parameter_list|()
block|{
return|return
name|types_of_locals
operator|.
name|length
return|;
block|}
specifier|public
name|void
name|setTypesOfLocals
parameter_list|(
specifier|final
name|StackMapType
index|[]
name|types
parameter_list|)
block|{
name|types_of_locals
operator|=
name|types
operator|!=
literal|null
condition|?
name|types
else|:
operator|new
name|StackMapType
index|[
literal|0
index|]
expr_stmt|;
block|}
specifier|public
name|StackMapType
index|[]
name|getTypesOfLocals
parameter_list|()
block|{
return|return
name|types_of_locals
return|;
block|}
annotation|@
name|java
operator|.
name|lang
operator|.
name|Deprecated
specifier|public
name|void
name|setNumberOfStackItems
parameter_list|(
specifier|final
name|int
name|n
parameter_list|)
block|{
comment|// TODO unused
block|}
specifier|public
name|int
name|getNumberOfStackItems
parameter_list|()
block|{
return|return
name|types_of_stack_items
operator|.
name|length
return|;
block|}
specifier|public
name|void
name|setTypesOfStackItems
parameter_list|(
specifier|final
name|StackMapType
index|[]
name|types
parameter_list|)
block|{
name|types_of_stack_items
operator|=
name|types
operator|!=
literal|null
condition|?
name|types
else|:
operator|new
name|StackMapType
index|[
literal|0
index|]
expr_stmt|;
block|}
specifier|public
name|StackMapType
index|[]
name|getTypesOfStackItems
parameter_list|()
block|{
return|return
name|types_of_stack_items
return|;
block|}
comment|/**      * @return deep copy of this object      */
specifier|public
name|StackMapEntry
name|copy
parameter_list|()
block|{
name|StackMapEntry
name|e
decl_stmt|;
try|try
block|{
name|e
operator|=
operator|(
name|StackMapEntry
operator|)
name|clone
argument_list|()
expr_stmt|;
block|}
catch|catch
parameter_list|(
name|CloneNotSupportedException
name|ex
parameter_list|)
block|{
throw|throw
operator|new
name|Error
argument_list|(
literal|"Clone Not Supported"
argument_list|)
throw|;
block|}
name|e
operator|.
name|types_of_locals
operator|=
operator|new
name|StackMapType
index|[
name|types_of_locals
operator|.
name|length
index|]
expr_stmt|;
for|for
control|(
name|int
name|i
init|=
literal|0
init|;
name|i
operator|<
name|types_of_locals
operator|.
name|length
condition|;
name|i
operator|++
control|)
block|{
name|e
operator|.
name|types_of_locals
index|[
name|i
index|]
operator|=
name|types_of_locals
index|[
name|i
index|]
operator|.
name|copy
argument_list|()
expr_stmt|;
block|}
name|e
operator|.
name|types_of_stack_items
operator|=
operator|new
name|StackMapType
index|[
name|types_of_stack_items
operator|.
name|length
index|]
expr_stmt|;
for|for
control|(
name|int
name|i
init|=
literal|0
init|;
name|i
operator|<
name|types_of_stack_items
operator|.
name|length
condition|;
name|i
operator|++
control|)
block|{
name|e
operator|.
name|types_of_stack_items
index|[
name|i
index|]
operator|=
name|types_of_stack_items
index|[
name|i
index|]
operator|.
name|copy
argument_list|()
expr_stmt|;
block|}
return|return
name|e
return|;
block|}
comment|/**      * Called by objects that are traversing the nodes of the tree implicitely      * defined by the contents of a Java class. I.e., the hierarchy of methods,      * fields, attributes, etc. spawns a tree of objects.      *      * @param v Visitor object      */
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
name|visitStackMapEntry
argument_list|(
name|this
argument_list|)
expr_stmt|;
block|}
comment|/**      * @return Constant pool used by this object.      */
specifier|public
specifier|final
name|ConstantPool
name|getConstantPool
parameter_list|()
block|{
return|return
name|constant_pool
return|;
block|}
comment|/**      * @param constant_pool Constant pool to be used for this object.      */
specifier|public
specifier|final
name|void
name|setConstantPool
parameter_list|(
specifier|final
name|ConstantPool
name|constant_pool
parameter_list|)
block|{
name|this
operator|.
name|constant_pool
operator|=
name|constant_pool
expr_stmt|;
block|}
block|}
end_class

end_unit

