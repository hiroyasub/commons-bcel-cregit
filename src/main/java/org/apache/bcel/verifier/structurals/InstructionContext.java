begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
begin_comment
comment|/*  * Licensed to the Apache Software Foundation (ASF) under one or more  * contributor license agreements.  See the NOTICE file distributed with  * this work for additional information regarding copyright ownership.  * The ASF licenses this file to You under the Apache License, Version 2.0  * (the "License"); you may not use this file except in compliance with  * the License.  You may obtain a copy of the License at  *  *      http://www.apache.org/licenses/LICENSE-2.0  *  *  Unless required by applicable law or agreed to in writing, software  *  distributed under the License is distributed on an "AS IS" BASIS,  *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  *  See the License for the specific language governing permissions and  *  limitations under the License.   *  */
end_comment

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

begin_comment
comment|/**  * An InstructionContext offers convenient access  * to information like control flow successors and  * such.  *  * @version $Id$  * @author Enver Haase  */
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
name|Frame
name|getInFrame
parameter_list|()
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

