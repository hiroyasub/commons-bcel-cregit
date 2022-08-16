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

begin_import
import|import
name|org
operator|.
name|apache
operator|.
name|commons
operator|.
name|lang3
operator|.
name|ArrayUtils
import|;
end_import

begin_comment
comment|/**  * This class is derived from<em>Attribute</em> and records the classes and interfaces that are authorized to claim  * membership in the nest hosted by the current class or interface. There may be at most one NestMembers attribute in a  * ClassFile structure.  *  * @see Attribute  */
end_comment

begin_class
specifier|public
specifier|final
class|class
name|NestMembers
extends|extends
name|Attribute
block|{
specifier|private
name|int
index|[]
name|classes
decl_stmt|;
comment|/**      * Construct object from input stream.      *       * @param name_index Index in constant pool      * @param length Content length in bytes      * @param input Input stream      * @param constant_pool Array of constants      * @throws IOException if an I/O error occurs.      */
name|NestMembers
parameter_list|(
specifier|final
name|int
name|name_index
parameter_list|,
specifier|final
name|int
name|length
parameter_list|,
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
name|name_index
argument_list|,
name|length
argument_list|,
operator|(
name|int
index|[]
operator|)
literal|null
argument_list|,
name|constant_pool
argument_list|)
expr_stmt|;
specifier|final
name|int
name|number_of_classes
init|=
name|input
operator|.
name|readUnsignedShort
argument_list|()
decl_stmt|;
name|classes
operator|=
operator|new
name|int
index|[
name|number_of_classes
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
name|number_of_classes
condition|;
name|i
operator|++
control|)
block|{
name|classes
index|[
name|i
index|]
operator|=
name|input
operator|.
name|readUnsignedShort
argument_list|()
expr_stmt|;
block|}
block|}
comment|/**      * @param name_index Index in constant pool      * @param length Content length in bytes      * @param classes Table of indices in constant pool      * @param constant_pool Array of constants      */
specifier|public
name|NestMembers
parameter_list|(
specifier|final
name|int
name|name_index
parameter_list|,
specifier|final
name|int
name|length
parameter_list|,
specifier|final
name|int
index|[]
name|classes
parameter_list|,
specifier|final
name|ConstantPool
name|constant_pool
parameter_list|)
block|{
name|super
argument_list|(
name|Const
operator|.
name|ATTR_NEST_MEMBERS
argument_list|,
name|name_index
argument_list|,
name|length
argument_list|,
name|constant_pool
argument_list|)
expr_stmt|;
name|this
operator|.
name|classes
operator|=
name|classes
operator|!=
literal|null
condition|?
name|classes
else|:
name|ArrayUtils
operator|.
name|EMPTY_INT_ARRAY
expr_stmt|;
block|}
comment|/**      * Initialize from another object. Note that both objects use the same references (shallow copy). Use copy() for a      * physical copy.      */
specifier|public
name|NestMembers
parameter_list|(
specifier|final
name|NestMembers
name|c
parameter_list|)
block|{
name|this
argument_list|(
name|c
operator|.
name|getNameIndex
argument_list|()
argument_list|,
name|c
operator|.
name|getLength
argument_list|()
argument_list|,
name|c
operator|.
name|getClasses
argument_list|()
argument_list|,
name|c
operator|.
name|getConstantPool
argument_list|()
argument_list|)
expr_stmt|;
block|}
comment|/**      * Called by objects that are traversing the nodes of the tree implicitely defined by the contents of a Java class.      * I.e., the hierarchy of methods, fields, attributes, etc. spawns a tree of objects.      *      * @param v Visitor object      */
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
name|visitNestMembers
argument_list|(
name|this
argument_list|)
expr_stmt|;
block|}
comment|/**      * @return deep copy of this attribute      */
annotation|@
name|Override
specifier|public
name|Attribute
name|copy
parameter_list|(
specifier|final
name|ConstantPool
name|_constant_pool
parameter_list|)
block|{
specifier|final
name|NestMembers
name|c
init|=
operator|(
name|NestMembers
operator|)
name|clone
argument_list|()
decl_stmt|;
if|if
condition|(
name|classes
operator|!=
literal|null
condition|)
block|{
name|c
operator|.
name|classes
operator|=
operator|new
name|int
index|[
name|classes
operator|.
name|length
index|]
expr_stmt|;
name|System
operator|.
name|arraycopy
argument_list|(
name|classes
argument_list|,
literal|0
argument_list|,
name|c
operator|.
name|classes
argument_list|,
literal|0
argument_list|,
name|classes
operator|.
name|length
argument_list|)
expr_stmt|;
block|}
name|c
operator|.
name|setConstantPool
argument_list|(
name|_constant_pool
argument_list|)
expr_stmt|;
return|return
name|c
return|;
block|}
comment|/**      * Dump NestMembers attribute to file stream in binary format.      *      * @param file Output file stream      * @throws IOException if an I/O error occurs.      */
annotation|@
name|Override
specifier|public
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
name|super
operator|.
name|dump
argument_list|(
name|file
argument_list|)
expr_stmt|;
name|file
operator|.
name|writeShort
argument_list|(
name|classes
operator|.
name|length
argument_list|)
expr_stmt|;
for|for
control|(
specifier|final
name|int
name|index
range|:
name|classes
control|)
block|{
name|file
operator|.
name|writeShort
argument_list|(
name|index
argument_list|)
expr_stmt|;
block|}
block|}
comment|/**      * @return array of indices into constant pool of class names.      */
specifier|public
name|int
index|[]
name|getClasses
parameter_list|()
block|{
return|return
name|classes
return|;
block|}
comment|/**      * @return string array of class names      */
specifier|public
name|String
index|[]
name|getClassNames
parameter_list|()
block|{
specifier|final
name|String
index|[]
name|names
init|=
operator|new
name|String
index|[
name|classes
operator|.
name|length
index|]
decl_stmt|;
for|for
control|(
name|int
name|i
init|=
literal|0
init|;
name|i
operator|<
name|classes
operator|.
name|length
condition|;
name|i
operator|++
control|)
block|{
name|names
index|[
name|i
index|]
operator|=
name|super
operator|.
name|getConstantPool
argument_list|()
operator|.
name|getConstantString
argument_list|(
name|classes
index|[
name|i
index|]
argument_list|,
name|Const
operator|.
name|CONSTANT_Class
argument_list|)
operator|.
name|replace
argument_list|(
literal|'/'
argument_list|,
literal|'.'
argument_list|)
expr_stmt|;
block|}
return|return
name|names
return|;
block|}
comment|/**      * @return Length of classes table.      */
specifier|public
name|int
name|getNumberClasses
parameter_list|()
block|{
return|return
name|classes
operator|==
literal|null
condition|?
literal|0
else|:
name|classes
operator|.
name|length
return|;
block|}
comment|/**      * @param classes the list of class indexes Also redefines number_of_classes according to table length.      */
specifier|public
name|void
name|setClasses
parameter_list|(
specifier|final
name|int
index|[]
name|classes
parameter_list|)
block|{
name|this
operator|.
name|classes
operator|=
name|classes
operator|!=
literal|null
condition|?
name|classes
else|:
name|ArrayUtils
operator|.
name|EMPTY_INT_ARRAY
expr_stmt|;
block|}
comment|/**      * @return String representation, i.e., a list of classes.      */
annotation|@
name|Override
specifier|public
name|String
name|toString
parameter_list|()
block|{
specifier|final
name|StringBuilder
name|buf
init|=
operator|new
name|StringBuilder
argument_list|()
decl_stmt|;
name|buf
operator|.
name|append
argument_list|(
literal|"NestMembers("
argument_list|)
expr_stmt|;
name|buf
operator|.
name|append
argument_list|(
name|classes
operator|.
name|length
argument_list|)
expr_stmt|;
name|buf
operator|.
name|append
argument_list|(
literal|"):\n"
argument_list|)
expr_stmt|;
for|for
control|(
specifier|final
name|int
name|index
range|:
name|classes
control|)
block|{
specifier|final
name|String
name|class_name
init|=
name|super
operator|.
name|getConstantPool
argument_list|()
operator|.
name|getConstantString
argument_list|(
name|index
argument_list|,
name|Const
operator|.
name|CONSTANT_Class
argument_list|)
decl_stmt|;
name|buf
operator|.
name|append
argument_list|(
literal|"  "
argument_list|)
operator|.
name|append
argument_list|(
name|Utility
operator|.
name|compactClassName
argument_list|(
name|class_name
argument_list|,
literal|false
argument_list|)
argument_list|)
operator|.
name|append
argument_list|(
literal|"\n"
argument_list|)
expr_stmt|;
block|}
return|return
name|buf
operator|.
name|substring
argument_list|(
literal|0
argument_list|,
name|buf
operator|.
name|length
argument_list|()
operator|-
literal|1
argument_list|)
return|;
comment|// remove the last newline
block|}
block|}
end_class

end_unit

