begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
begin_package
package|package
name|org
operator|.
name|apache
operator|.
name|bcel
operator|.
name|verifier
operator|.
name|structurals
package|;
end_package

begin_comment
comment|/* ====================================================================  * The Apache Software License, Version 1.1  *  * Copyright (c) 2001 The Apache Software Foundation.  All rights  * reserved.  *  * Redistribution and use in source and binary forms, with or without  * modification, are permitted provided that the following conditions  * are met:  *  * 1. Redistributions of source code must retain the above copyright  *    notice, this list of conditions and the following disclaimer.  *  * 2. Redistributions in binary form must reproduce the above copyright  *    notice, this list of conditions and the following disclaimer in  *    the documentation and/or other materials provided with the  *    distribution.  *  * 3. The end-user documentation included with the redistribution,  *    if any, must include the following acknowledgment:  *       "This product includes software developed by the  *        Apache Software Foundation (http://www.apache.org/)."  *    Alternately, this acknowledgment may appear in the software itself,  *    if and wherever such third-party acknowledgments normally appear.  *  * 4. The names "Apache" and "Apache Software Foundation" and  *    "Apache BCEL" must not be used to endorse or promote products  *    derived from this software without prior written permission. For  *    written permission, please contact apache@apache.org.  *  * 5. Products derived from this software may not be called "Apache",  *    "Apache BCEL", nor may "Apache" appear in their name, without  *    prior written permission of the Apache Software Foundation.  *  * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED  * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES  * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE  * DISCLAIMED.  IN NO EVENT SHALL THE APACHE SOFTWARE FOUNDATION OR  * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,  * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT  * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF  * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND  * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,  * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT  * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF  * SUCH DAMAGE.  * ====================================================================  *  * This software consists of voluntary contributions made by many  * individuals on behalf of the Apache Software Foundation.  For more  * information on the Apache Software Foundation, please see  *<http://www.apache.org/>.  */
end_comment

begin_import
import|import
name|org
operator|.
name|apache
operator|.
name|bcel
operator|.
name|generic
operator|.
name|InstructionHandle
import|;
end_import

begin_import
import|import
name|java
operator|.
name|util
operator|.
name|ArrayList
import|;
end_import

begin_import
import|import
name|java
operator|.
name|util
operator|.
name|Iterator
import|;
end_import

begin_comment
comment|/**  * An InstructionContext offers convenient access  * to information like control flow successors and  * such.  *  * @version $Id$  * @author<A HREF="http://www.inf.fu-berlin.de/~ehaase"/>Enver Haase</A>  */
end_comment

begin_interface
specifier|public
interface|interface
name|InstructionContext
block|{
comment|/** 	 * The getTag and setTag methods may be used for 	 * temporary flagging, such as graph colouring. 	 * Nothing in the InstructionContext object depends 	 * on the value of the tag. JustIce does not use it. 	 *  	 * @see #setTag(int tag) 	 */
specifier|public
name|int
name|getTag
parameter_list|()
function_decl|;
comment|/** 	 * The getTag and setTag methods may be used for 	 * temporary flagging, such as graph colouring. 	 * Nothing in the InstructionContext object depends 	 * on the value of the tag. JustIce does not use it. 	 *  	 * @see #getTag() 	 */
specifier|public
name|void
name|setTag
parameter_list|(
name|int
name|tag
parameter_list|)
function_decl|;
comment|/** 	 * This method symbolically executes the Instruction 	 * held in the InstructionContext. 	 * It "merges in" the incoming execution frame situation 	 * (see The Java Virtual Machine Specification, 2nd 	 * edition, page 146). 	 * By so doing, the outgoing execution frame situation 	 * is calculated. 	 * 	 * This method is JustIce-specific and is usually of 	 * no sense for users of the ControlFlowGraph class. 	 * They should use getInstruction().accept(Visitor), 	 * possibly in conjunction with the ExecutionVisitor. 	 *  	 * 	 * @see ControlFlowGraph 	 * @see ExecutionVisitor 	 * @see #getOutFrame(ArrayList) 	 * @return true -  if and only if the "outgoing" frame situation 	 * changed from the one before execute()ing. 	 */
name|boolean
name|execute
parameter_list|(
name|Frame
name|inFrame
parameter_list|,
name|ArrayList
name|executionPredecessors
parameter_list|,
name|InstConstraintVisitor
name|icv
parameter_list|,
name|ExecutionVisitor
name|ev
parameter_list|)
function_decl|;
comment|/** 	 * This method returns the outgoing execution frame situation; 	 * therefore<B>it has to be calculated by execute(Frame, ArrayList) 	 * first.</B> 	 * 	 * @see #execute(Frame, ArrayList, InstConstraintVisitor, ExecutionVisitor) 	 */
name|Frame
name|getOutFrame
parameter_list|(
name|ArrayList
name|executionPredecessors
parameter_list|)
function_decl|;
comment|/** 	 * Returns the InstructionHandle this InstructionContext is wrapped around. 	 * 	 * @return The InstructionHandle this InstructionContext is wrapped around. 	 */
name|InstructionHandle
name|getInstruction
parameter_list|()
function_decl|;
comment|/** 	 * Returns the usual control flow successors. 	 * @see #getExceptionHandlers() 	 */
name|InstructionContext
index|[]
name|getSuccessors
parameter_list|()
function_decl|;
comment|/** 	 * Returns the exception handlers that protect this instruction. 	 * They are special control flow successors. 	 */
name|ExceptionHandler
index|[]
name|getExceptionHandlers
parameter_list|()
function_decl|;
block|}
end_interface

end_unit

