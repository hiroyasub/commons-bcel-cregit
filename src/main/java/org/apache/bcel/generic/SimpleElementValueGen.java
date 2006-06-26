begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
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
name|classfile
operator|.
name|ConstantDouble
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
name|ConstantFloat
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
name|ConstantInteger
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
name|ConstantLong
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
name|ConstantUtf8
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
name|ElementValue
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
name|SimpleElementValue
import|;
end_import

begin_class
specifier|public
class|class
name|SimpleElementValueGen
extends|extends
name|ElementValueGen
block|{
comment|// For primitive types and string type, this points to the value entry in
comment|// the cpGen
comment|// For 'class' this points to the class entry in the cpGen
specifier|private
name|int
name|idx
decl_stmt|;
comment|// ctors for each supported type... type could be inferred but for now lets
comment|// force it to be passed
comment|/** 	 * Protected ctor used for deserialization, doesn't *put* an entry in the 	 * constant pool, assumes the one at the supplied index is correct. 	 */
specifier|protected
name|SimpleElementValueGen
parameter_list|(
name|int
name|type
parameter_list|,
name|int
name|idx
parameter_list|,
name|ConstantPoolGen
name|cpGen
parameter_list|)
block|{
name|super
argument_list|(
name|type
argument_list|,
name|cpGen
argument_list|)
expr_stmt|;
name|this
operator|.
name|idx
operator|=
name|idx
expr_stmt|;
block|}
specifier|public
name|SimpleElementValueGen
parameter_list|(
name|int
name|type
parameter_list|,
name|ConstantPoolGen
name|cpGen
parameter_list|,
name|int
name|value
parameter_list|)
block|{
name|super
argument_list|(
name|type
argument_list|,
name|cpGen
argument_list|)
expr_stmt|;
name|idx
operator|=
name|cpGen
operator|.
name|addInteger
argument_list|(
name|value
argument_list|)
expr_stmt|;
block|}
specifier|public
name|SimpleElementValueGen
parameter_list|(
name|int
name|type
parameter_list|,
name|ConstantPoolGen
name|cpGen
parameter_list|,
name|long
name|value
parameter_list|)
block|{
name|super
argument_list|(
name|type
argument_list|,
name|cpGen
argument_list|)
expr_stmt|;
name|idx
operator|=
name|cpGen
operator|.
name|addLong
argument_list|(
name|value
argument_list|)
expr_stmt|;
block|}
specifier|public
name|SimpleElementValueGen
parameter_list|(
name|int
name|type
parameter_list|,
name|ConstantPoolGen
name|cpGen
parameter_list|,
name|double
name|value
parameter_list|)
block|{
name|super
argument_list|(
name|type
argument_list|,
name|cpGen
argument_list|)
expr_stmt|;
name|idx
operator|=
name|cpGen
operator|.
name|addDouble
argument_list|(
name|value
argument_list|)
expr_stmt|;
block|}
specifier|public
name|SimpleElementValueGen
parameter_list|(
name|int
name|type
parameter_list|,
name|ConstantPoolGen
name|cpGen
parameter_list|,
name|float
name|value
parameter_list|)
block|{
name|super
argument_list|(
name|type
argument_list|,
name|cpGen
argument_list|)
expr_stmt|;
name|idx
operator|=
name|cpGen
operator|.
name|addFloat
argument_list|(
name|value
argument_list|)
expr_stmt|;
block|}
specifier|public
name|SimpleElementValueGen
parameter_list|(
name|int
name|type
parameter_list|,
name|ConstantPoolGen
name|cpGen
parameter_list|,
name|short
name|value
parameter_list|)
block|{
name|super
argument_list|(
name|type
argument_list|,
name|cpGen
argument_list|)
expr_stmt|;
name|idx
operator|=
name|cpGen
operator|.
name|addInteger
argument_list|(
name|value
argument_list|)
expr_stmt|;
block|}
specifier|public
name|SimpleElementValueGen
parameter_list|(
name|int
name|type
parameter_list|,
name|ConstantPoolGen
name|cpGen
parameter_list|,
name|byte
name|value
parameter_list|)
block|{
name|super
argument_list|(
name|type
argument_list|,
name|cpGen
argument_list|)
expr_stmt|;
name|idx
operator|=
name|cpGen
operator|.
name|addInteger
argument_list|(
name|value
argument_list|)
expr_stmt|;
block|}
specifier|public
name|SimpleElementValueGen
parameter_list|(
name|int
name|type
parameter_list|,
name|ConstantPoolGen
name|cpGen
parameter_list|,
name|char
name|value
parameter_list|)
block|{
name|super
argument_list|(
name|type
argument_list|,
name|cpGen
argument_list|)
expr_stmt|;
name|idx
operator|=
name|cpGen
operator|.
name|addInteger
argument_list|(
name|value
argument_list|)
expr_stmt|;
block|}
specifier|public
name|SimpleElementValueGen
parameter_list|(
name|int
name|type
parameter_list|,
name|ConstantPoolGen
name|cpGen
parameter_list|,
name|boolean
name|value
parameter_list|)
block|{
name|super
argument_list|(
name|type
argument_list|,
name|cpGen
argument_list|)
expr_stmt|;
if|if
condition|(
name|value
condition|)
name|idx
operator|=
name|cpGen
operator|.
name|addInteger
argument_list|(
literal|1
argument_list|)
expr_stmt|;
else|else
name|idx
operator|=
name|cpGen
operator|.
name|addInteger
argument_list|(
literal|0
argument_list|)
expr_stmt|;
block|}
specifier|public
name|SimpleElementValueGen
parameter_list|(
name|int
name|type
parameter_list|,
name|ConstantPoolGen
name|cpGen
parameter_list|,
name|String
name|value
parameter_list|)
block|{
name|super
argument_list|(
name|type
argument_list|,
name|cpGen
argument_list|)
expr_stmt|;
name|idx
operator|=
name|cpGen
operator|.
name|addUtf8
argument_list|(
name|value
argument_list|)
expr_stmt|;
block|}
comment|/** 	 * The boolean controls whether we copy info from the 'old' constant pool to 	 * the 'new'. You need to use this ctor if the annotation is being copied 	 * from one file to another. 	 */
specifier|public
name|SimpleElementValueGen
parameter_list|(
name|SimpleElementValue
name|value
parameter_list|,
name|ConstantPoolGen
name|cpool
parameter_list|,
name|boolean
name|copyPoolEntries
parameter_list|)
block|{
name|super
argument_list|(
name|value
operator|.
name|getElementValueType
argument_list|()
argument_list|,
name|cpool
argument_list|)
expr_stmt|;
if|if
condition|(
operator|!
name|copyPoolEntries
condition|)
block|{
comment|// J5ASSERT: Could assert value.stringifyValue() is the same as
comment|// cpool.getConstant(SimpleElementValuevalue.getIndex())
name|idx
operator|=
name|value
operator|.
name|getIndex
argument_list|()
expr_stmt|;
block|}
else|else
block|{
switch|switch
condition|(
name|value
operator|.
name|getElementValueType
argument_list|()
condition|)
block|{
case|case
name|STRING
case|:
name|idx
operator|=
name|cpool
operator|.
name|addUtf8
argument_list|(
name|value
operator|.
name|getValueString
argument_list|()
argument_list|)
expr_stmt|;
break|break;
case|case
name|PRIMITIVE_INT
case|:
name|idx
operator|=
name|cpool
operator|.
name|addInteger
argument_list|(
name|value
operator|.
name|getValueInt
argument_list|()
argument_list|)
expr_stmt|;
break|break;
case|case
name|PRIMITIVE_BYTE
case|:
name|idx
operator|=
name|cpool
operator|.
name|addInteger
argument_list|(
name|value
operator|.
name|getValueByte
argument_list|()
argument_list|)
expr_stmt|;
break|break;
case|case
name|PRIMITIVE_CHAR
case|:
name|idx
operator|=
name|cpool
operator|.
name|addInteger
argument_list|(
name|value
operator|.
name|getValueChar
argument_list|()
argument_list|)
expr_stmt|;
break|break;
case|case
name|PRIMITIVE_LONG
case|:
name|idx
operator|=
name|cpool
operator|.
name|addLong
argument_list|(
name|value
operator|.
name|getValueLong
argument_list|()
argument_list|)
expr_stmt|;
break|break;
case|case
name|PRIMITIVE_FLOAT
case|:
name|idx
operator|=
name|cpool
operator|.
name|addFloat
argument_list|(
name|value
operator|.
name|getValueFloat
argument_list|()
argument_list|)
expr_stmt|;
break|break;
case|case
name|PRIMITIVE_DOUBLE
case|:
name|idx
operator|=
name|cpool
operator|.
name|addDouble
argument_list|(
name|value
operator|.
name|getValueDouble
argument_list|()
argument_list|)
expr_stmt|;
break|break;
case|case
name|PRIMITIVE_BOOLEAN
case|:
if|if
condition|(
name|value
operator|.
name|getValueBoolean
argument_list|()
condition|)
block|{
name|idx
operator|=
name|cpool
operator|.
name|addInteger
argument_list|(
literal|1
argument_list|)
expr_stmt|;
block|}
else|else
block|{
name|idx
operator|=
name|cpool
operator|.
name|addInteger
argument_list|(
literal|0
argument_list|)
expr_stmt|;
block|}
break|break;
case|case
name|PRIMITIVE_SHORT
case|:
name|idx
operator|=
name|cpool
operator|.
name|addInteger
argument_list|(
name|value
operator|.
name|getValueShort
argument_list|()
argument_list|)
expr_stmt|;
break|break;
default|default:
throw|throw
operator|new
name|RuntimeException
argument_list|(
literal|"SimpleElementValueGen class does not know how "
operator|+
literal|"to copy this type "
operator|+
name|type
argument_list|)
throw|;
block|}
block|}
block|}
comment|/** 	 * Return immutable variant 	 */
specifier|public
name|ElementValue
name|getElementValue
parameter_list|()
block|{
return|return
operator|new
name|SimpleElementValue
argument_list|(
name|type
argument_list|,
name|idx
argument_list|,
name|cpGen
operator|.
name|getConstantPool
argument_list|()
argument_list|)
return|;
block|}
specifier|public
name|int
name|getIndex
parameter_list|()
block|{
return|return
name|idx
return|;
block|}
specifier|public
name|String
name|getValueString
parameter_list|()
block|{
if|if
condition|(
name|type
operator|!=
name|STRING
condition|)
throw|throw
operator|new
name|RuntimeException
argument_list|(
literal|"Dont call getValueString() on a non STRING ElementValue"
argument_list|)
throw|;
name|ConstantUtf8
name|c
init|=
operator|(
name|ConstantUtf8
operator|)
name|cpGen
operator|.
name|getConstant
argument_list|(
name|idx
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
name|type
operator|!=
name|PRIMITIVE_INT
condition|)
throw|throw
operator|new
name|RuntimeException
argument_list|(
literal|"Dont call getValueString() on a non STRING ElementValue"
argument_list|)
throw|;
name|ConstantInteger
name|c
init|=
operator|(
name|ConstantInteger
operator|)
name|cpGen
operator|.
name|getConstant
argument_list|(
name|idx
argument_list|)
decl_stmt|;
return|return
name|c
operator|.
name|getBytes
argument_list|()
return|;
block|}
comment|// Whatever kind of value it is, return it as a string
specifier|public
name|String
name|stringifyValue
parameter_list|()
block|{
switch|switch
condition|(
name|type
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
name|cpGen
operator|.
name|getConstant
argument_list|(
name|idx
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
name|cpGen
operator|.
name|getConstant
argument_list|(
name|idx
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
name|cpGen
operator|.
name|getConstant
argument_list|(
name|idx
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
name|cpGen
operator|.
name|getConstant
argument_list|(
name|idx
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
name|cpGen
operator|.
name|getConstant
argument_list|(
name|idx
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
name|cpGen
operator|.
name|getConstant
argument_list|(
name|idx
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
name|cpGen
operator|.
name|getConstant
argument_list|(
name|idx
argument_list|)
decl_stmt|;
return|return
name|Integer
operator|.
name|toString
argument_list|(
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
name|cpGen
operator|.
name|getConstant
argument_list|(
name|idx
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
return|return
literal|"false"
return|;
if|if
condition|(
name|bo
operator|.
name|getBytes
argument_list|()
operator|!=
literal|0
condition|)
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
name|cpGen
operator|.
name|getConstant
argument_list|(
name|idx
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
literal|"SimpleElementValueGen class does not know how to stringify type "
operator|+
name|type
argument_list|)
throw|;
block|}
block|}
specifier|public
name|void
name|dump
parameter_list|(
name|DataOutputStream
name|dos
parameter_list|)
throws|throws
name|IOException
block|{
name|dos
operator|.
name|writeByte
argument_list|(
name|type
argument_list|)
expr_stmt|;
comment|// u1 kind of value
switch|switch
condition|(
name|type
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
name|idx
argument_list|)
expr_stmt|;
break|break;
default|default:
throw|throw
operator|new
name|RuntimeException
argument_list|(
literal|"SimpleElementValueGen doesnt know how to write out type "
operator|+
name|type
argument_list|)
throw|;
block|}
block|}
block|}
end_class

end_unit

