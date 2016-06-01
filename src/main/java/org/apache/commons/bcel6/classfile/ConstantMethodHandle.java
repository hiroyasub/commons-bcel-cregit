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
name|commons
operator|.
name|bcel6
operator|.
name|Const
import|;
end_import

begin_comment
comment|/**   * This class is derived from the abstract {@link Constant}  * and represents a reference to a method handle.  *   * @see     Constant  * @since 6.0  */
end_comment

begin_class
specifier|public
specifier|final
class|class
name|ConstantMethodHandle
extends|extends
name|Constant
block|{
specifier|private
name|int
name|reference_kind
decl_stmt|;
specifier|private
name|int
name|reference_index
decl_stmt|;
comment|/**      * Initialize from another object.      */
specifier|public
name|ConstantMethodHandle
parameter_list|(
specifier|final
name|ConstantMethodHandle
name|c
parameter_list|)
block|{
name|this
argument_list|(
name|c
operator|.
name|getReferenceKind
argument_list|()
argument_list|,
name|c
operator|.
name|getReferenceIndex
argument_list|()
argument_list|)
expr_stmt|;
block|}
comment|/**      * Initialize instance from file data.      *      * @param file Input stream      * @throws IOException      */
name|ConstantMethodHandle
parameter_list|(
specifier|final
name|DataInput
name|file
parameter_list|)
throws|throws
name|IOException
block|{
name|this
argument_list|(
name|file
operator|.
name|readUnsignedByte
argument_list|()
argument_list|,
name|file
operator|.
name|readUnsignedShort
argument_list|()
argument_list|)
expr_stmt|;
block|}
specifier|public
name|ConstantMethodHandle
parameter_list|(
specifier|final
name|int
name|reference_kind
parameter_list|,
specifier|final
name|int
name|reference_index
parameter_list|)
block|{
name|super
argument_list|(
name|Const
operator|.
name|CONSTANT_MethodHandle
argument_list|)
expr_stmt|;
name|this
operator|.
name|reference_kind
operator|=
name|reference_kind
expr_stmt|;
name|this
operator|.
name|reference_index
operator|=
name|reference_index
expr_stmt|;
block|}
comment|/**      * Called by objects that are traversing the nodes of the tree implicitly      * defined by the contents of a Java class. I.e., the hierarchy of methods,      * fields, attributes, etc. spawns a tree of objects.      *      * @param v Visitor object      */
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
name|visitConstantMethodHandle
argument_list|(
name|this
argument_list|)
expr_stmt|;
block|}
comment|/**      * Dump method kind and index to file stream in binary format.      *      * @param file Output file stream      * @throws IOException      */
annotation|@
name|Override
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
name|writeByte
argument_list|(
name|super
operator|.
name|getTag
argument_list|()
argument_list|)
expr_stmt|;
name|file
operator|.
name|writeByte
argument_list|(
name|reference_kind
argument_list|)
expr_stmt|;
name|file
operator|.
name|writeShort
argument_list|(
name|reference_index
argument_list|)
expr_stmt|;
block|}
specifier|public
name|int
name|getReferenceKind
parameter_list|()
block|{
return|return
name|reference_kind
return|;
block|}
specifier|public
name|void
name|setReferenceKind
parameter_list|(
specifier|final
name|int
name|reference_kind
parameter_list|)
block|{
name|this
operator|.
name|reference_kind
operator|=
name|reference_kind
expr_stmt|;
block|}
specifier|public
name|int
name|getReferenceIndex
parameter_list|()
block|{
return|return
name|reference_index
return|;
block|}
specifier|public
name|void
name|setReferenceIndex
parameter_list|(
specifier|final
name|int
name|reference_index
parameter_list|)
block|{
name|this
operator|.
name|reference_index
operator|=
name|reference_index
expr_stmt|;
block|}
comment|/**      * @return String representation      */
annotation|@
name|Override
specifier|public
specifier|final
name|String
name|toString
parameter_list|()
block|{
return|return
name|super
operator|.
name|toString
argument_list|()
operator|+
literal|"(reference_kind = "
operator|+
name|reference_kind
operator|+
literal|", reference_index = "
operator|+
name|reference_index
operator|+
literal|")"
return|;
block|}
block|}
end_class

end_unit

