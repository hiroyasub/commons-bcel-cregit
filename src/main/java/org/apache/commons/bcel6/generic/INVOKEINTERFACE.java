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
name|ExceptionConstants
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
name|ConstantPool
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
name|util
operator|.
name|ByteSequence
import|;
end_import

begin_comment
comment|/**   * INVOKEINTERFACE - Invoke interface method  *<PRE>Stack: ..., objectref, [arg1, [arg2 ...]] -&gt; ...</PRE>  *  * @version $Id$  */
end_comment

begin_class
specifier|public
specifier|final
class|class
name|INVOKEINTERFACE
extends|extends
name|InvokeInstruction
block|{
specifier|private
specifier|static
specifier|final
name|long
name|serialVersionUID
init|=
literal|8198753714085379482L
decl_stmt|;
specifier|private
name|int
name|nargs
decl_stmt|;
comment|// Number of arguments on stack (number of stack slots), called "count" in vmspec2
comment|/**      * Empty constructor needed for the Class.newInstance() statement in      * Instruction.readInstruction(). Not to be used otherwise.      */
name|INVOKEINTERFACE
parameter_list|()
block|{
block|}
specifier|public
name|INVOKEINTERFACE
parameter_list|(
name|int
name|index
parameter_list|,
name|int
name|nargs
parameter_list|)
block|{
name|super
argument_list|(
name|Constants
operator|.
name|INVOKEINTERFACE
argument_list|,
name|index
argument_list|)
expr_stmt|;
name|length
operator|=
literal|5
expr_stmt|;
if|if
condition|(
name|nargs
operator|<
literal|1
condition|)
block|{
throw|throw
operator|new
name|ClassGenException
argument_list|(
literal|"Number of arguments must be> 0 "
operator|+
name|nargs
argument_list|)
throw|;
block|}
name|this
operator|.
name|nargs
operator|=
name|nargs
expr_stmt|;
block|}
comment|/**      * Dump instruction as byte code to stream out.      * @param out Output stream      */
annotation|@
name|Override
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
name|out
operator|.
name|writeShort
argument_list|(
name|super
operator|.
name|getIndex
argument_list|()
argument_list|)
expr_stmt|;
name|out
operator|.
name|writeByte
argument_list|(
name|nargs
argument_list|)
expr_stmt|;
name|out
operator|.
name|writeByte
argument_list|(
literal|0
argument_list|)
expr_stmt|;
block|}
comment|/**      * The<B>count</B> argument according to the Java Language Specification,      * Second Edition.      */
specifier|public
name|int
name|getCount
parameter_list|()
block|{
return|return
name|nargs
return|;
block|}
comment|/**      * Read needed data (i.e., index) from file.      */
annotation|@
name|Override
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
name|super
operator|.
name|initFromFile
argument_list|(
name|bytes
argument_list|,
name|wide
argument_list|)
expr_stmt|;
name|length
operator|=
literal|5
expr_stmt|;
name|nargs
operator|=
name|bytes
operator|.
name|readUnsignedByte
argument_list|()
expr_stmt|;
name|bytes
operator|.
name|readByte
argument_list|()
expr_stmt|;
comment|// Skip 0 byte
block|}
comment|/**      * @return mnemonic for instruction with symbolic references resolved      */
annotation|@
name|Override
specifier|public
name|String
name|toString
parameter_list|(
name|ConstantPool
name|cp
parameter_list|)
block|{
return|return
name|super
operator|.
name|toString
argument_list|(
name|cp
argument_list|)
operator|+
literal|" "
operator|+
name|nargs
return|;
block|}
annotation|@
name|Override
specifier|public
name|int
name|consumeStack
parameter_list|(
name|ConstantPoolGen
name|cpg
parameter_list|)
block|{
comment|// nargs is given in byte-code
return|return
name|nargs
return|;
comment|// nargs includes this reference
block|}
annotation|@
name|Override
specifier|public
name|Class
argument_list|<
name|?
argument_list|>
index|[]
name|getExceptions
parameter_list|()
block|{
return|return
name|ExceptionConstants
operator|.
name|createExceptions
argument_list|(
name|ExceptionConstants
operator|.
name|EXCS
operator|.
name|EXCS_INTERFACE_METHOD_RESOLUTION
argument_list|,
name|ExceptionConstants
operator|.
name|UNSATISFIED_LINK_ERROR
argument_list|,
name|ExceptionConstants
operator|.
name|ABSTRACT_METHOD_ERROR
argument_list|,
name|ExceptionConstants
operator|.
name|ILLEGAL_ACCESS_ERROR
argument_list|,
name|ExceptionConstants
operator|.
name|INCOMPATIBLE_CLASS_CHANGE_ERROR
argument_list|)
return|;
block|}
comment|/**      * Call corresponding visitor method(s). The order is:      * Call visitor methods of implemented interfaces first, then      * call methods according to the class hierarchy in descending order,      * i.e., the most specific visitXXX() call comes last.      *      * @param v Visitor object      */
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
name|visitStackConsumer
argument_list|(
name|this
argument_list|)
expr_stmt|;
name|v
operator|.
name|visitStackProducer
argument_list|(
name|this
argument_list|)
expr_stmt|;
name|v
operator|.
name|visitLoadClass
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
name|visitFieldOrMethod
argument_list|(
name|this
argument_list|)
expr_stmt|;
name|v
operator|.
name|visitInvokeInstruction
argument_list|(
name|this
argument_list|)
expr_stmt|;
name|v
operator|.
name|visitINVOKEINTERFACE
argument_list|(
name|this
argument_list|)
expr_stmt|;
block|}
block|}
end_class

end_unit

