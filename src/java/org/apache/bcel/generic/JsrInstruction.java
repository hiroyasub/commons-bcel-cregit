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

begin_comment
comment|/* ====================================================================  * The Apache Software License, Version 1.1  *  * Copyright (c) 2001 The Apache Software Foundation.  All rights  * reserved.  *  * Redistribution and use in source and binary forms, with or without  * modification, are permitted provided that the following conditions  * are met:  *  * 1. Redistributions of source code must retain the above copyright  *    notice, this list of conditions and the following disclaimer.  *  * 2. Redistributions in binary form must reproduce the above copyright  *    notice, this list of conditions and the following disclaimer in  *    the documentation and/or other materials provided with the  *    distribution.  *  * 3. The end-user documentation included with the redistribution,  *    if any, must include the following acknowledgment:  *       "This product includes software developed by the  *        Apache Software Foundation (http://www.apache.org/)."  *    Alternately, this acknowledgment may appear in the software itself,  *    if and wherever such third-party acknowledgments normally appear.  *  * 4. The names "Apache" and "Apache Software Foundation" and  *    "Apache BCEL" must not be used to endorse or promote products  *    derived from this software without prior written permission. For  *    written permission, please contact apache@apache.org.  *  * 5. Products derived from this software may not be called "Apache",  *    "Apache BCEL", nor may "Apache" appear in their name, without  *    prior written permission of the Apache Software Foundation.  *  * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED  * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES  * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE  * DISCLAIMED.  IN NO EVENT SHALL THE APACHE SOFTWARE FOUNDATION OR  * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,  * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT  * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF  * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND  * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,  * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT  * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF  * SUCH DAMAGE.  * ====================================================================  *  * This software consists of voluntary contributions made by many  * individuals on behalf of the Apache Software Foundation.  For more  * information on the Apache Software Foundation, please see  *<http://www.apache.org/>.  */
end_comment

begin_comment
comment|/**   * Super class for JSR - Jump to subroutine  *  * @version $Id$  * @author<A HREF="mailto:markus.dahm@berlin.de">M. Dahm</A>  */
end_comment

begin_class
specifier|public
specifier|abstract
class|class
name|JsrInstruction
extends|extends
name|BranchInstruction
implements|implements
name|UnconditionalBranch
implements|,
name|TypedInstruction
implements|,
name|StackProducer
block|{
name|JsrInstruction
parameter_list|(
name|short
name|opcode
parameter_list|,
name|InstructionHandle
name|target
parameter_list|)
block|{
name|super
argument_list|(
name|opcode
argument_list|,
name|target
argument_list|)
expr_stmt|;
block|}
comment|/**    * Empty constructor needed for the Class.newInstance() statement in    * Instruction.readInstruction(). Not to be used otherwise.    */
name|JsrInstruction
parameter_list|()
block|{
block|}
comment|/** @return return address type    */
specifier|public
name|Type
name|getType
parameter_list|(
name|ConstantPoolGen
name|cp
parameter_list|)
block|{
return|return
operator|new
name|ReturnaddressType
argument_list|(
name|physicalSuccessor
argument_list|()
argument_list|)
return|;
block|}
comment|/**    * Returns an InstructionHandle to the physical successor    * of this JsrInstruction.<B>For this method to work,    * this JsrInstruction object must not be shared between    * multiple InstructionHandle objects!</B>    * Formally, there must not be InstructionHandle objects    * i, j where i != j and i.getInstruction() == this ==    * j.getInstruction().    * @return an InstructionHandle to the "next" instruction that    * will be executed when RETurned from a subroutine.    */
specifier|public
name|InstructionHandle
name|physicalSuccessor
parameter_list|()
block|{
name|InstructionHandle
name|ih
init|=
name|this
operator|.
name|target
decl_stmt|;
comment|// Rewind!
while|while
condition|(
name|ih
operator|.
name|getPrev
argument_list|()
operator|!=
literal|null
condition|)
name|ih
operator|=
name|ih
operator|.
name|getPrev
argument_list|()
expr_stmt|;
comment|// Find the handle for "this" JsrInstruction object.
while|while
condition|(
name|ih
operator|.
name|getInstruction
argument_list|()
operator|!=
name|this
condition|)
name|ih
operator|=
name|ih
operator|.
name|getNext
argument_list|()
expr_stmt|;
name|InstructionHandle
name|toThis
init|=
name|ih
decl_stmt|;
while|while
condition|(
name|ih
operator|!=
literal|null
condition|)
block|{
name|ih
operator|=
name|ih
operator|.
name|getNext
argument_list|()
expr_stmt|;
if|if
condition|(
operator|(
name|ih
operator|!=
literal|null
operator|)
operator|&&
operator|(
name|ih
operator|.
name|getInstruction
argument_list|()
operator|==
name|this
operator|)
condition|)
throw|throw
operator|new
name|RuntimeException
argument_list|(
literal|"physicalSuccessor() called on a shared JsrInstruction."
argument_list|)
throw|;
block|}
comment|// Return the physical successor
return|return
name|toThis
operator|.
name|getNext
argument_list|()
return|;
block|}
block|}
end_class

end_unit

