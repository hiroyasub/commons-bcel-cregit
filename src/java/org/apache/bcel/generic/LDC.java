begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
begin_comment
comment|/*  * Copyright  2000-2004 The Apache Software Foundation  *  *  Licensed under the Apache License, Version 2.0 (the "License");   *  you may not use this file except in compliance with the License.  *  You may obtain a copy of the License at  *  *      http://www.apache.org/licenses/LICENSE-2.0  *  *  Unless required by applicable law or agreed to in writing, software  *  distributed under the License is distributed on an "AS IS" BASIS,  *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  *  See the License for the specific language governing permissions and  *  limitations under the License.   *  */
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
name|*
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
comment|/**   * LDC - Push item from constant pool.  *  *<PRE>Stack: ... -&gt; ..., item</PRE>  *  * @version $Id$  * @author<A HREF="mailto:m.dahm@gmx.de">M. Dahm</A>  */
end_comment

begin_class
specifier|public
class|class
name|LDC
extends|extends
name|CPInstruction
implements|implements
name|PushInstruction
implements|,
name|ExceptionThrower
implements|,
name|TypedInstruction
block|{
comment|/**    * Empty constructor needed for the Class.newInstance() statement in    * Instruction.readInstruction(). Not to be used otherwise.    */
name|LDC
parameter_list|()
block|{
block|}
specifier|public
name|LDC
parameter_list|(
name|int
name|index
parameter_list|)
block|{
name|super
argument_list|(
name|org
operator|.
name|apache
operator|.
name|bcel
operator|.
name|Constants
operator|.
name|LDC_W
argument_list|,
name|index
argument_list|)
expr_stmt|;
name|setSize
argument_list|()
expr_stmt|;
block|}
comment|// Adjust to proper size
specifier|protected
specifier|final
name|void
name|setSize
parameter_list|()
block|{
if|if
condition|(
name|index
operator|<=
name|org
operator|.
name|apache
operator|.
name|bcel
operator|.
name|Constants
operator|.
name|MAX_BYTE
condition|)
block|{
comment|// Fits in one byte?
name|opcode
operator|=
name|org
operator|.
name|apache
operator|.
name|bcel
operator|.
name|Constants
operator|.
name|LDC
expr_stmt|;
name|length
operator|=
literal|2
expr_stmt|;
block|}
else|else
block|{
name|opcode
operator|=
name|org
operator|.
name|apache
operator|.
name|bcel
operator|.
name|Constants
operator|.
name|LDC_W
expr_stmt|;
name|length
operator|=
literal|3
expr_stmt|;
block|}
block|}
comment|/**    * Dump instruction as byte code to stream out.    * @param out Output stream    */
specifier|public
name|void
name|dump
parameter_list|(
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
name|opcode
argument_list|)
expr_stmt|;
if|if
condition|(
name|length
operator|==
literal|2
condition|)
name|out
operator|.
name|writeByte
argument_list|(
name|index
argument_list|)
expr_stmt|;
else|else
comment|// Applies for LDC_W
name|out
operator|.
name|writeShort
argument_list|(
name|index
argument_list|)
expr_stmt|;
block|}
comment|/**    * Set the index to constant pool and adjust size.    */
specifier|public
specifier|final
name|void
name|setIndex
parameter_list|(
name|int
name|index
parameter_list|)
block|{
name|super
operator|.
name|setIndex
argument_list|(
name|index
argument_list|)
expr_stmt|;
name|setSize
argument_list|()
expr_stmt|;
block|}
comment|/**    * Read needed data (e.g. index) from file.    */
specifier|protected
name|void
name|initFromFile
parameter_list|(
name|ByteSequence
name|bytes
parameter_list|,
name|boolean
name|wide
parameter_list|)
throws|throws
name|IOException
block|{
name|length
operator|=
literal|2
expr_stmt|;
name|index
operator|=
name|bytes
operator|.
name|readUnsignedByte
argument_list|()
expr_stmt|;
block|}
specifier|public
name|Object
name|getValue
parameter_list|(
name|ConstantPoolGen
name|cpg
parameter_list|)
block|{
name|org
operator|.
name|apache
operator|.
name|bcel
operator|.
name|classfile
operator|.
name|Constant
name|c
init|=
name|cpg
operator|.
name|getConstantPool
argument_list|()
operator|.
name|getConstant
argument_list|(
name|index
argument_list|)
decl_stmt|;
switch|switch
condition|(
name|c
operator|.
name|getTag
argument_list|()
condition|)
block|{
case|case
name|org
operator|.
name|apache
operator|.
name|bcel
operator|.
name|Constants
operator|.
name|CONSTANT_String
case|:
name|int
name|i
init|=
operator|(
operator|(
name|org
operator|.
name|apache
operator|.
name|bcel
operator|.
name|classfile
operator|.
name|ConstantString
operator|)
name|c
operator|)
operator|.
name|getStringIndex
argument_list|()
decl_stmt|;
name|c
operator|=
name|cpg
operator|.
name|getConstantPool
argument_list|()
operator|.
name|getConstant
argument_list|(
name|i
argument_list|)
expr_stmt|;
return|return
operator|(
operator|(
name|org
operator|.
name|apache
operator|.
name|bcel
operator|.
name|classfile
operator|.
name|ConstantUtf8
operator|)
name|c
operator|)
operator|.
name|getBytes
argument_list|()
return|;
case|case
name|org
operator|.
name|apache
operator|.
name|bcel
operator|.
name|Constants
operator|.
name|CONSTANT_Float
case|:
return|return
operator|new
name|Float
argument_list|(
operator|(
operator|(
name|org
operator|.
name|apache
operator|.
name|bcel
operator|.
name|classfile
operator|.
name|ConstantFloat
operator|)
name|c
operator|)
operator|.
name|getBytes
argument_list|()
argument_list|)
return|;
case|case
name|org
operator|.
name|apache
operator|.
name|bcel
operator|.
name|Constants
operator|.
name|CONSTANT_Integer
case|:
return|return
operator|new
name|Integer
argument_list|(
operator|(
operator|(
name|org
operator|.
name|apache
operator|.
name|bcel
operator|.
name|classfile
operator|.
name|ConstantInteger
operator|)
name|c
operator|)
operator|.
name|getBytes
argument_list|()
argument_list|)
return|;
case|case
name|org
operator|.
name|apache
operator|.
name|bcel
operator|.
name|Constants
operator|.
name|CONSTANT_Class
case|:
return|return
operator|(
name|org
operator|.
name|apache
operator|.
name|bcel
operator|.
name|classfile
operator|.
name|ConstantClass
operator|)
name|c
return|;
default|default:
comment|// Never reached
throw|throw
operator|new
name|RuntimeException
argument_list|(
literal|"Unknown or invalid constant type at "
operator|+
name|index
argument_list|)
throw|;
block|}
block|}
specifier|public
name|Type
name|getType
parameter_list|(
name|ConstantPoolGen
name|cpg
parameter_list|)
block|{
switch|switch
condition|(
name|cpg
operator|.
name|getConstantPool
argument_list|()
operator|.
name|getConstant
argument_list|(
name|index
argument_list|)
operator|.
name|getTag
argument_list|()
condition|)
block|{
case|case
name|org
operator|.
name|apache
operator|.
name|bcel
operator|.
name|Constants
operator|.
name|CONSTANT_String
case|:
return|return
name|Type
operator|.
name|STRING
return|;
case|case
name|org
operator|.
name|apache
operator|.
name|bcel
operator|.
name|Constants
operator|.
name|CONSTANT_Float
case|:
return|return
name|Type
operator|.
name|FLOAT
return|;
case|case
name|org
operator|.
name|apache
operator|.
name|bcel
operator|.
name|Constants
operator|.
name|CONSTANT_Integer
case|:
return|return
name|Type
operator|.
name|INT
return|;
case|case
name|org
operator|.
name|apache
operator|.
name|bcel
operator|.
name|Constants
operator|.
name|CONSTANT_Class
case|:
return|return
name|Type
operator|.
name|CLASS
return|;
default|default:
comment|// Never reached
throw|throw
operator|new
name|RuntimeException
argument_list|(
literal|"Unknown or invalid constant type at "
operator|+
name|index
argument_list|)
throw|;
block|}
block|}
specifier|public
name|Class
index|[]
name|getExceptions
parameter_list|()
block|{
return|return
name|org
operator|.
name|apache
operator|.
name|bcel
operator|.
name|ExceptionConstants
operator|.
name|EXCS_STRING_RESOLUTION
return|;
block|}
comment|/**    * Call corresponding visitor method(s). The order is:    * Call visitor methods of implemented interfaces first, then    * call methods according to the class hierarchy in descending order,    * i.e., the most specific visitXXX() call comes last.    *    * @param v Visitor object    */
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
name|visitStackProducer
argument_list|(
name|this
argument_list|)
expr_stmt|;
name|v
operator|.
name|visitPushInstruction
argument_list|(
name|this
argument_list|)
expr_stmt|;
name|v
operator|.
name|visitExceptionThrower
argument_list|(
name|this
argument_list|)
expr_stmt|;
name|v
operator|.
name|visitTypedInstruction
argument_list|(
name|this
argument_list|)
expr_stmt|;
name|v
operator|.
name|visitCPInstruction
argument_list|(
name|this
argument_list|)
expr_stmt|;
name|v
operator|.
name|visitLDC
argument_list|(
name|this
argument_list|)
expr_stmt|;
block|}
block|}
end_class

end_unit

