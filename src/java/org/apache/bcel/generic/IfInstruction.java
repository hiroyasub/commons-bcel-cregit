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

begin_comment
comment|/**  * Super class for the IFxxx family of instructions.  *  * @version $Id$  * @author<A HREF="mailto:m.dahm@gmx.de">M. Dahm</A>  */
end_comment

begin_class
specifier|public
specifier|abstract
class|class
name|IfInstruction
extends|extends
name|BranchInstruction
implements|implements
name|StackConsumer
block|{
comment|/**    * Empty constructor needed for the Class.newInstance() statement in    * Instruction.readInstruction(). Not to be used otherwise.    */
name|IfInstruction
parameter_list|()
block|{
block|}
comment|/**    * @param instruction Target instruction to branch to    */
specifier|protected
name|IfInstruction
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
comment|/**    * @return negation of instruction, e.g. IFEQ.negate() == IFNE    */
specifier|public
specifier|abstract
name|IfInstruction
name|negate
parameter_list|()
function_decl|;
block|}
end_class

end_unit

