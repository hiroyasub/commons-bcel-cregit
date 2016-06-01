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
comment|/**  * @since 6.0  */
end_comment

begin_class
specifier|public
class|class
name|SimpleElementValue
extends|extends
name|ElementValue
block|{
specifier|private
name|int
name|index
decl_stmt|;
specifier|public
name|SimpleElementValue
parameter_list|(
specifier|final
name|int
name|type
parameter_list|,
specifier|final
name|int
name|index
parameter_list|,
specifier|final
name|ConstantPool
name|cpool
parameter_list|)
block|{
name|super
argument_list|(
name|type
argument_list|,
name|cpool
argument_list|)
expr_stmt|;
name|this
operator|.
name|index
operator|=
name|index
expr_stmt|;
block|}
comment|/**      * @return Value entry index in the cpool      */
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
name|String
name|getValueString
parameter_list|()
block|{
if|if
condition|(
name|super
operator|.
name|getType
argument_list|()
operator|!=
name|STRING
condition|)
block|{
throw|throw
operator|new
name|RuntimeException
argument_list|(
literal|"Dont call getValueString() on a non STRING ElementValue"
argument_list|)
throw|;
block|}
name|ConstantUtf8
name|c
init|=
operator|(
name|ConstantUtf8
operator|)
name|super
operator|.
name|getConstantPool
argument_list|()
operator|.
name|getConstant
argument_list|(
name|getIndex
argument_list|()
argument_list|,
name|Const
operator|.
name|CONSTANT_Utf8
argument_list|)
decl_stmt|;
return|return
name|c
operator|.
name|getBytes
argument_list|()
return|;
block|}
specifier|public
name|int
name|getValueInt
parameter_list|()
block|{
if|if
condition|(
name|super
operator|.
name|getType
argument_list|()
operator|!=
name|PRIMITIVE_INT
condition|)
block|{
throw|throw
operator|new
name|RuntimeException
argument_list|(
literal|"Dont call getValueString() on a non STRING ElementValue"
argument_list|)
throw|;
block|}
name|ConstantInteger
name|c
init|=
operator|(
name|ConstantInteger
operator|)
name|super
operator|.
name|getConstantPool
argument_list|()
operator|.
name|getConstant
argument_list|(
name|getIndex
argument_list|()
argument_list|,
name|Const
operator|.
name|CONSTANT_Integer
argument_list|)
decl_stmt|;
return|return
name|c
operator|.
name|getBytes
argument_list|()
return|;
block|}
specifier|public
name|byte
name|getValueByte
parameter_list|()
block|{
if|if
condition|(
name|super
operator|.
name|getType
argument_list|()
operator|!=
name|PRIMITIVE_BYTE
condition|)
block|{
throw|throw
operator|new
name|RuntimeException
argument_list|(
literal|"Dont call getValueByte() on a non BYTE ElementValue"
argument_list|)
throw|;
block|}
name|ConstantInteger
name|c
init|=
operator|(
name|ConstantInteger
operator|)
name|super
operator|.
name|getConstantPool
argument_list|()
operator|.
name|getConstant
argument_list|(
name|getIndex
argument_list|()
argument_list|,
name|Const
operator|.
name|CONSTANT_Integer
argument_list|)
decl_stmt|;
return|return
operator|(
name|byte
operator|)
name|c
operator|.
name|getBytes
argument_list|()
return|;
block|}
specifier|public
name|char
name|getValueChar
parameter_list|()
block|{
if|if
condition|(
name|super
operator|.
name|getType
argument_list|()
operator|!=
name|PRIMITIVE_CHAR
condition|)
block|{
throw|throw
operator|new
name|RuntimeException
argument_list|(
literal|"Dont call getValueChar() on a non CHAR ElementValue"
argument_list|)
throw|;
block|}
name|ConstantInteger
name|c
init|=
operator|(
name|ConstantInteger
operator|)
name|super
operator|.
name|getConstantPool
argument_list|()
operator|.
name|getConstant
argument_list|(
name|getIndex
argument_list|()
argument_list|,
name|Const
operator|.
name|CONSTANT_Integer
argument_list|)
decl_stmt|;
return|return
operator|(
name|char
operator|)
name|c
operator|.
name|getBytes
argument_list|()
return|;
block|}
specifier|public
name|long
name|getValueLong
parameter_list|()
block|{
if|if
condition|(
name|super
operator|.
name|getType
argument_list|()
operator|!=
name|PRIMITIVE_LONG
condition|)
block|{
throw|throw
operator|new
name|RuntimeException
argument_list|(
literal|"Dont call getValueLong() on a non LONG ElementValue"
argument_list|)
throw|;
block|}
name|ConstantLong
name|j
init|=
operator|(
name|ConstantLong
operator|)
name|super
operator|.
name|getConstantPool
argument_list|()
operator|.
name|getConstant
argument_list|(
name|getIndex
argument_list|()
argument_list|)
decl_stmt|;
return|return
name|j
operator|.
name|getBytes
argument_list|()
return|;
block|}
specifier|public
name|float
name|getValueFloat
parameter_list|()
block|{
if|if
condition|(
name|super
operator|.
name|getType
argument_list|()
operator|!=
name|PRIMITIVE_FLOAT
condition|)
block|{
throw|throw
operator|new
name|RuntimeException
argument_list|(
literal|"Dont call getValueFloat() on a non FLOAT ElementValue"
argument_list|)
throw|;
block|}
name|ConstantFloat
name|f
init|=
operator|(
name|ConstantFloat
operator|)
name|super
operator|.
name|getConstantPool
argument_list|()
operator|.
name|getConstant
argument_list|(
name|getIndex
argument_list|()
argument_list|)
decl_stmt|;
return|return
name|f
operator|.
name|getBytes
argument_list|()
return|;
block|}
specifier|public
name|double
name|getValueDouble
parameter_list|()
block|{
if|if
condition|(
name|super
operator|.
name|getType
argument_list|()
operator|!=
name|PRIMITIVE_DOUBLE
condition|)
block|{
throw|throw
operator|new
name|RuntimeException
argument_list|(
literal|"Dont call getValueDouble() on a non DOUBLE ElementValue"
argument_list|)
throw|;
block|}
name|ConstantDouble
name|d
init|=
operator|(
name|ConstantDouble
operator|)
name|super
operator|.
name|getConstantPool
argument_list|()
operator|.
name|getConstant
argument_list|(
name|getIndex
argument_list|()
argument_list|)
decl_stmt|;
return|return
name|d
operator|.
name|getBytes
argument_list|()
return|;
block|}
specifier|public
name|boolean
name|getValueBoolean
parameter_list|()
block|{
if|if
condition|(
name|super
operator|.
name|getType
argument_list|()
operator|!=
name|PRIMITIVE_BOOLEAN
condition|)
block|{
throw|throw
operator|new
name|RuntimeException
argument_list|(
literal|"Dont call getValueBoolean() on a non BOOLEAN ElementValue"
argument_list|)
throw|;
block|}
name|ConstantInteger
name|bo
init|=
operator|(
name|ConstantInteger
operator|)
name|super
operator|.
name|getConstantPool
argument_list|()
operator|.
name|getConstant
argument_list|(
name|getIndex
argument_list|()
argument_list|)
decl_stmt|;
return|return
name|bo
operator|.
name|getBytes
argument_list|()
operator|!=
literal|0
return|;
block|}
specifier|public
name|short
name|getValueShort
parameter_list|()
block|{
if|if
condition|(
name|super
operator|.
name|getType
argument_list|()
operator|!=
name|PRIMITIVE_SHORT
condition|)
block|{
throw|throw
operator|new
name|RuntimeException
argument_list|(
literal|"Dont call getValueShort() on a non SHORT ElementValue"
argument_list|)
throw|;
block|}
name|ConstantInteger
name|s
init|=
operator|(
name|ConstantInteger
operator|)
name|super
operator|.
name|getConstantPool
argument_list|()
operator|.
name|getConstant
argument_list|(
name|getIndex
argument_list|()
argument_list|)
decl_stmt|;
return|return
operator|(
name|short
operator|)
name|s
operator|.
name|getBytes
argument_list|()
return|;
block|}
annotation|@
name|Override
specifier|public
name|String
name|toString
parameter_list|()
block|{
return|return
name|stringifyValue
argument_list|()
return|;
block|}
comment|// Whatever kind of value it is, return it as a string
annotation|@
name|Override
specifier|public
name|String
name|stringifyValue
parameter_list|()
block|{
name|ConstantPool
name|cpool
init|=
name|super
operator|.
name|getConstantPool
argument_list|()
decl_stmt|;
specifier|final
name|int
name|_type
init|=
name|super
operator|.
name|getType
argument_list|()
decl_stmt|;
switch|switch
condition|(
name|_type
condition|)
block|{
case|case
name|PRIMITIVE_INT
case|:
name|ConstantInteger
name|c
init|=
operator|(
name|ConstantInteger
operator|)
name|cpool
operator|.
name|getConstant
argument_list|(
name|getIndex
argument_list|()
argument_list|,
name|Const
operator|.
name|CONSTANT_Integer
argument_list|)
decl_stmt|;
return|return
name|Integer
operator|.
name|toString
argument_list|(
name|c
operator|.
name|getBytes
argument_list|()
argument_list|)
return|;
case|case
name|PRIMITIVE_LONG
case|:
name|ConstantLong
name|j
init|=
operator|(
name|ConstantLong
operator|)
name|cpool
operator|.
name|getConstant
argument_list|(
name|getIndex
argument_list|()
argument_list|,
name|Const
operator|.
name|CONSTANT_Long
argument_list|)
decl_stmt|;
return|return
name|Long
operator|.
name|toString
argument_list|(
name|j
operator|.
name|getBytes
argument_list|()
argument_list|)
return|;
case|case
name|PRIMITIVE_DOUBLE
case|:
name|ConstantDouble
name|d
init|=
operator|(
name|ConstantDouble
operator|)
name|cpool
operator|.
name|getConstant
argument_list|(
name|getIndex
argument_list|()
argument_list|,
name|Const
operator|.
name|CONSTANT_Double
argument_list|)
decl_stmt|;
return|return
name|Double
operator|.
name|toString
argument_list|(
name|d
operator|.
name|getBytes
argument_list|()
argument_list|)
return|;
case|case
name|PRIMITIVE_FLOAT
case|:
name|ConstantFloat
name|f
init|=
operator|(
name|ConstantFloat
operator|)
name|cpool
operator|.
name|getConstant
argument_list|(
name|getIndex
argument_list|()
argument_list|,
name|Const
operator|.
name|CONSTANT_Float
argument_list|)
decl_stmt|;
return|return
name|Float
operator|.
name|toString
argument_list|(
name|f
operator|.
name|getBytes
argument_list|()
argument_list|)
return|;
case|case
name|PRIMITIVE_SHORT
case|:
name|ConstantInteger
name|s
init|=
operator|(
name|ConstantInteger
operator|)
name|cpool
operator|.
name|getConstant
argument_list|(
name|getIndex
argument_list|()
argument_list|,
name|Const
operator|.
name|CONSTANT_Integer
argument_list|)
decl_stmt|;
return|return
name|Integer
operator|.
name|toString
argument_list|(
name|s
operator|.
name|getBytes
argument_list|()
argument_list|)
return|;
case|case
name|PRIMITIVE_BYTE
case|:
name|ConstantInteger
name|b
init|=
operator|(
name|ConstantInteger
operator|)
name|cpool
operator|.
name|getConstant
argument_list|(
name|getIndex
argument_list|()
argument_list|,
name|Const
operator|.
name|CONSTANT_Integer
argument_list|)
decl_stmt|;
return|return
name|Integer
operator|.
name|toString
argument_list|(
name|b
operator|.
name|getBytes
argument_list|()
argument_list|)
return|;
case|case
name|PRIMITIVE_CHAR
case|:
name|ConstantInteger
name|ch
init|=
operator|(
name|ConstantInteger
operator|)
name|cpool
operator|.
name|getConstant
argument_list|(
name|getIndex
argument_list|()
argument_list|,
name|Const
operator|.
name|CONSTANT_Integer
argument_list|)
decl_stmt|;
return|return
name|String
operator|.
name|valueOf
argument_list|(
operator|(
name|char
operator|)
name|ch
operator|.
name|getBytes
argument_list|()
argument_list|)
return|;
case|case
name|PRIMITIVE_BOOLEAN
case|:
name|ConstantInteger
name|bo
init|=
operator|(
name|ConstantInteger
operator|)
name|cpool
operator|.
name|getConstant
argument_list|(
name|getIndex
argument_list|()
argument_list|,
name|Const
operator|.
name|CONSTANT_Integer
argument_list|)
decl_stmt|;
if|if
condition|(
name|bo
operator|.
name|getBytes
argument_list|()
operator|==
literal|0
condition|)
block|{
return|return
literal|"false"
return|;
block|}
return|return
literal|"true"
return|;
case|case
name|STRING
case|:
name|ConstantUtf8
name|cu8
init|=
operator|(
name|ConstantUtf8
operator|)
name|cpool
operator|.
name|getConstant
argument_list|(
name|getIndex
argument_list|()
argument_list|,
name|Const
operator|.
name|CONSTANT_Utf8
argument_list|)
decl_stmt|;
return|return
name|cu8
operator|.
name|getBytes
argument_list|()
return|;
default|default:
throw|throw
operator|new
name|RuntimeException
argument_list|(
literal|"SimpleElementValue class does not know how to stringify type "
operator|+
name|_type
argument_list|)
throw|;
block|}
block|}
annotation|@
name|Override
specifier|public
name|void
name|dump
parameter_list|(
specifier|final
name|DataOutputStream
name|dos
parameter_list|)
throws|throws
name|IOException
block|{
specifier|final
name|int
name|_type
init|=
name|super
operator|.
name|getType
argument_list|()
decl_stmt|;
name|dos
operator|.
name|writeByte
argument_list|(
name|_type
argument_list|)
expr_stmt|;
comment|// u1 kind of value
switch|switch
condition|(
name|_type
condition|)
block|{
case|case
name|PRIMITIVE_INT
case|:
case|case
name|PRIMITIVE_BYTE
case|:
case|case
name|PRIMITIVE_CHAR
case|:
case|case
name|PRIMITIVE_FLOAT
case|:
case|case
name|PRIMITIVE_LONG
case|:
case|case
name|PRIMITIVE_BOOLEAN
case|:
case|case
name|PRIMITIVE_SHORT
case|:
case|case
name|PRIMITIVE_DOUBLE
case|:
case|case
name|STRING
case|:
name|dos
operator|.
name|writeShort
argument_list|(
name|getIndex
argument_list|()
argument_list|)
expr_stmt|;
break|break;
default|default:
throw|throw
operator|new
name|RuntimeException
argument_list|(
literal|"SimpleElementValue doesnt know how to write out type "
operator|+
name|_type
argument_list|)
throw|;
block|}
block|}
block|}
end_class

end_unit

