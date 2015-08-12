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
name|java
operator|.
name|io
operator|.
name|Serializable
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

begin_comment
comment|/**  * This class represents a local variable within a method. It contains its  * scope, name, signature and index on the method's frame.  *  * @version $Id$  * @see     LocalVariableTable  */
end_comment

begin_class
specifier|public
specifier|final
class|class
name|LocalVariable
implements|implements
name|Constants
implements|,
name|Cloneable
implements|,
name|Node
implements|,
name|Serializable
block|{
specifier|private
specifier|static
specifier|final
name|long
name|serialVersionUID
init|=
operator|-
literal|51081099265972179L
decl_stmt|;
specifier|private
specifier|final
name|int
name|start_pc
decl_stmt|;
comment|// Range in which the variable is valid
specifier|private
specifier|final
name|int
name|length
decl_stmt|;
specifier|private
specifier|final
name|int
name|name_index
decl_stmt|;
comment|// Index in constant pool of variable name
specifier|private
specifier|final
name|int
name|signature_index
decl_stmt|;
comment|// Index of variable signature
specifier|private
specifier|final
name|int
name|index
decl_stmt|;
comment|/* Variable is `index'th local variable on      * this method's frame.      */
specifier|private
name|ConstantPool
name|constant_pool
decl_stmt|;
comment|/**      * Initialize from another object. Note that both objects use the same      * references (shallow copy). Use copy() for a physical copy.      */
specifier|public
name|LocalVariable
parameter_list|(
name|LocalVariable
name|c
parameter_list|)
block|{
name|this
argument_list|(
name|c
operator|.
name|getStartPC
argument_list|()
argument_list|,
name|c
operator|.
name|getLength
argument_list|()
argument_list|,
name|c
operator|.
name|getNameIndex
argument_list|()
argument_list|,
name|c
operator|.
name|getSignatureIndex
argument_list|()
argument_list|,
name|c
operator|.
name|getIndex
argument_list|()
argument_list|,
name|c
operator|.
name|getConstantPool
argument_list|()
argument_list|)
expr_stmt|;
block|}
comment|/**      * Construct object from file stream.      * @param file Input stream      * @throws IOException      */
name|LocalVariable
parameter_list|(
name|DataInput
name|file
parameter_list|,
name|ConstantPool
name|constant_pool
parameter_list|)
throws|throws
name|IOException
block|{
name|this
argument_list|(
name|file
operator|.
name|readUnsignedShort
argument_list|()
argument_list|,
name|file
operator|.
name|readUnsignedShort
argument_list|()
argument_list|,
name|file
operator|.
name|readUnsignedShort
argument_list|()
argument_list|,
name|file
operator|.
name|readUnsignedShort
argument_list|()
argument_list|,
name|file
operator|.
name|readUnsignedShort
argument_list|()
argument_list|,
name|constant_pool
argument_list|)
expr_stmt|;
block|}
comment|/**      * @param start_pc Range in which the variable      * @param length ... is valid      * @param name_index Index in constant pool of variable name      * @param signature_index Index of variable's signature      * @param index Variable is `index'th local variable on the method's frame      * @param constant_pool Array of constants      */
specifier|public
name|LocalVariable
parameter_list|(
name|int
name|start_pc
parameter_list|,
name|int
name|length
parameter_list|,
name|int
name|name_index
parameter_list|,
name|int
name|signature_index
parameter_list|,
name|int
name|index
parameter_list|,
name|ConstantPool
name|constant_pool
parameter_list|)
block|{
name|this
operator|.
name|start_pc
operator|=
name|start_pc
expr_stmt|;
name|this
operator|.
name|length
operator|=
name|length
expr_stmt|;
name|this
operator|.
name|name_index
operator|=
name|name_index
expr_stmt|;
name|this
operator|.
name|signature_index
operator|=
name|signature_index
expr_stmt|;
name|this
operator|.
name|index
operator|=
name|index
expr_stmt|;
name|this
operator|.
name|constant_pool
operator|=
name|constant_pool
expr_stmt|;
block|}
comment|/**      * Called by objects that are traversing the nodes of the tree implicitely      * defined by the contents of a Java class. I.e., the hierarchy of methods,      * fields, attributes, etc. spawns a tree of objects.      *      * @param v Visitor object      */
specifier|public
name|void
name|accept
parameter_list|(
name|Visitor
name|v
parameter_list|)
block|{
name|v
operator|.
name|visitLocalVariable
argument_list|(
name|this
argument_list|)
expr_stmt|;
block|}
comment|/**      * Dump local variable to file stream in binary format.      *      * @param file Output file stream      * @throws IOException      */
specifier|public
specifier|final
name|void
name|dump
parameter_list|(
name|DataOutputStream
name|file
parameter_list|)
throws|throws
name|IOException
block|{
name|file
operator|.
name|writeShort
argument_list|(
name|start_pc
argument_list|)
expr_stmt|;
name|file
operator|.
name|writeShort
argument_list|(
name|length
argument_list|)
expr_stmt|;
name|file
operator|.
name|writeShort
argument_list|(
name|name_index
argument_list|)
expr_stmt|;
name|file
operator|.
name|writeShort
argument_list|(
name|signature_index
argument_list|)
expr_stmt|;
name|file
operator|.
name|writeShort
argument_list|(
name|index
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
comment|/**      * @return Variable is valid within getStartPC() .. getStartPC()+getLength()      */
specifier|public
specifier|final
name|int
name|getLength
parameter_list|()
block|{
return|return
name|length
return|;
block|}
comment|/**      * @return Variable name.      */
specifier|public
specifier|final
name|String
name|getName
parameter_list|()
block|{
name|ConstantUtf8
name|c
decl_stmt|;
name|c
operator|=
operator|(
name|ConstantUtf8
operator|)
name|constant_pool
operator|.
name|getConstant
argument_list|(
name|name_index
argument_list|,
name|CONSTANT_Utf8
argument_list|)
expr_stmt|;
return|return
name|c
operator|.
name|getBytes
argument_list|()
return|;
block|}
comment|/**      * @return Index in constant pool of variable name.      */
specifier|public
specifier|final
name|int
name|getNameIndex
parameter_list|()
block|{
return|return
name|name_index
return|;
block|}
comment|/**      * @return Signature.      */
specifier|public
specifier|final
name|String
name|getSignature
parameter_list|()
block|{
name|ConstantUtf8
name|c
decl_stmt|;
name|c
operator|=
operator|(
name|ConstantUtf8
operator|)
name|constant_pool
operator|.
name|getConstant
argument_list|(
name|signature_index
argument_list|,
name|CONSTANT_Utf8
argument_list|)
expr_stmt|;
return|return
name|c
operator|.
name|getBytes
argument_list|()
return|;
block|}
comment|/**      * @return Index in constant pool of variable signature.      */
specifier|public
specifier|final
name|int
name|getSignatureIndex
parameter_list|()
block|{
return|return
name|signature_index
return|;
block|}
comment|/**      * @return index of register where variable is stored      */
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
comment|/**      * @return Start of range where he variable is valid      */
specifier|public
specifier|final
name|int
name|getStartPC
parameter_list|()
block|{
return|return
name|start_pc
return|;
block|}
comment|/**      * @return string representation.      */
annotation|@
name|Override
specifier|public
specifier|final
name|String
name|toString
parameter_list|()
block|{
name|String
name|name
init|=
name|getName
argument_list|()
decl_stmt|,
name|signature
init|=
name|Utility
operator|.
name|signatureToString
argument_list|(
name|getSignature
argument_list|()
argument_list|)
decl_stmt|;
return|return
literal|"LocalVariable(start_pc = "
operator|+
name|start_pc
operator|+
literal|", length = "
operator|+
name|length
operator|+
literal|", index = "
operator|+
name|index
operator|+
literal|":"
operator|+
name|signature
operator|+
literal|" "
operator|+
name|name
operator|+
literal|")"
return|;
block|}
comment|/**      * @return deep copy of this object      */
specifier|public
name|LocalVariable
name|copy
parameter_list|()
block|{
try|try
block|{
return|return
operator|(
name|LocalVariable
operator|)
name|clone
argument_list|()
return|;
block|}
catch|catch
parameter_list|(
name|CloneNotSupportedException
name|e
parameter_list|)
block|{
block|}
return|return
literal|null
return|;
block|}
block|}
end_class

end_unit

