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
name|Constants
import|;
end_import

begin_comment
comment|/**  * This class is derived from<em>Attribute</em> and represents a constant   * value, i.e., a default value for initializing a class field.  * This class is instantiated by the<em>Attribute.readAttribute()</em> method.  *  * @version $Id$  * @see     Attribute  */
end_comment

begin_class
specifier|public
specifier|final
class|class
name|ConstantValue
extends|extends
name|Attribute
block|{
specifier|private
specifier|static
specifier|final
name|long
name|serialVersionUID
init|=
operator|-
literal|5668999920978520157L
decl_stmt|;
specifier|private
specifier|final
name|int
name|constantvalue_index
decl_stmt|;
comment|/**      * Initialize from another object. Note that both objects use the same      * references (shallow copy). Use clone() for a physical copy.      */
specifier|public
name|ConstantValue
parameter_list|(
name|ConstantValue
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
name|getConstantValueIndex
argument_list|()
argument_list|,
name|c
operator|.
name|getConstantPool
argument_list|()
argument_list|)
expr_stmt|;
block|}
comment|/**      * Construct object from input stream.      * @param name_index Name index in constant pool      * @param length Content length in bytes      * @param input Input stream      * @param constant_pool Array of constants      * @throws IOException      */
name|ConstantValue
parameter_list|(
name|int
name|name_index
parameter_list|,
name|int
name|length
parameter_list|,
name|DataInput
name|input
parameter_list|,
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
name|input
operator|.
name|readUnsignedShort
argument_list|()
argument_list|,
name|constant_pool
argument_list|)
expr_stmt|;
block|}
comment|/**      * @param name_index Name index in constant pool      * @param length Content length in bytes      * @param constantvalue_index Index in constant pool      * @param constant_pool Array of constants      */
specifier|public
name|ConstantValue
parameter_list|(
name|int
name|name_index
parameter_list|,
name|int
name|length
parameter_list|,
name|int
name|constantvalue_index
parameter_list|,
name|ConstantPool
name|constant_pool
parameter_list|)
block|{
name|super
argument_list|(
name|Constants
operator|.
name|ATTR_CONSTANT_VALUE
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
name|constantvalue_index
operator|=
name|constantvalue_index
expr_stmt|;
block|}
comment|/**      * Called by objects that are traversing the nodes of the tree implicitely      * defined by the contents of a Java class. I.e., the hierarchy of methods,      * fields, attributes, etc. spawns a tree of objects.      *      * @param v Visitor object      */
annotation|@
name|Override
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
name|visitConstantValue
argument_list|(
name|this
argument_list|)
expr_stmt|;
block|}
comment|/**      * Dump constant value attribute to file stream on binary format.      *      * @param file Output file stream      * @throws IOException      */
annotation|@
name|Override
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
name|constantvalue_index
argument_list|)
expr_stmt|;
block|}
comment|/**      * @return Index in constant pool of constant value.      */
specifier|public
specifier|final
name|int
name|getConstantValueIndex
parameter_list|()
block|{
return|return
name|constantvalue_index
return|;
block|}
comment|/**      * @return String representation of constant value.      */
annotation|@
name|Override
specifier|public
specifier|final
name|String
name|toString
parameter_list|()
block|{
name|Constant
name|c
init|=
name|super
operator|.
name|getConstantPool
argument_list|()
operator|.
name|getConstant
argument_list|(
name|constantvalue_index
argument_list|)
decl_stmt|;
name|String
name|buf
decl_stmt|;
name|int
name|i
decl_stmt|;
comment|// Print constant to string depending on its type
switch|switch
condition|(
name|c
operator|.
name|getTag
argument_list|()
condition|)
block|{
case|case
name|Constants
operator|.
name|CONSTANT_Long
case|:
name|buf
operator|=
name|String
operator|.
name|valueOf
argument_list|(
operator|(
operator|(
name|ConstantLong
operator|)
name|c
operator|)
operator|.
name|getBytes
argument_list|()
argument_list|)
expr_stmt|;
break|break;
case|case
name|Constants
operator|.
name|CONSTANT_Float
case|:
name|buf
operator|=
name|String
operator|.
name|valueOf
argument_list|(
operator|(
operator|(
name|ConstantFloat
operator|)
name|c
operator|)
operator|.
name|getBytes
argument_list|()
argument_list|)
expr_stmt|;
break|break;
case|case
name|Constants
operator|.
name|CONSTANT_Double
case|:
name|buf
operator|=
name|String
operator|.
name|valueOf
argument_list|(
operator|(
operator|(
name|ConstantDouble
operator|)
name|c
operator|)
operator|.
name|getBytes
argument_list|()
argument_list|)
expr_stmt|;
break|break;
case|case
name|Constants
operator|.
name|CONSTANT_Integer
case|:
name|buf
operator|=
name|String
operator|.
name|valueOf
argument_list|(
operator|(
operator|(
name|ConstantInteger
operator|)
name|c
operator|)
operator|.
name|getBytes
argument_list|()
argument_list|)
expr_stmt|;
break|break;
case|case
name|Constants
operator|.
name|CONSTANT_String
case|:
name|i
operator|=
operator|(
operator|(
name|ConstantString
operator|)
name|c
operator|)
operator|.
name|getStringIndex
argument_list|()
expr_stmt|;
name|c
operator|=
name|super
operator|.
name|getConstantPool
argument_list|()
operator|.
name|getConstant
argument_list|(
name|i
argument_list|,
name|Constants
operator|.
name|CONSTANT_Utf8
argument_list|)
expr_stmt|;
name|buf
operator|=
literal|"\""
operator|+
name|Utility
operator|.
name|convertString
argument_list|(
operator|(
operator|(
name|ConstantUtf8
operator|)
name|c
operator|)
operator|.
name|getBytes
argument_list|()
argument_list|)
operator|+
literal|"\""
expr_stmt|;
break|break;
default|default:
throw|throw
operator|new
name|IllegalStateException
argument_list|(
literal|"Type of ConstValue invalid: "
operator|+
name|c
argument_list|)
throw|;
block|}
return|return
name|buf
return|;
block|}
comment|/**      * @return deep copy of this attribute      */
annotation|@
name|Override
specifier|public
name|Attribute
name|copy
parameter_list|(
name|ConstantPool
name|_constant_pool
parameter_list|)
block|{
name|ConstantValue
name|c
init|=
operator|(
name|ConstantValue
operator|)
name|clone
argument_list|()
decl_stmt|;
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
block|}
end_class

end_unit

