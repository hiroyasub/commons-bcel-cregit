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

begin_comment
comment|/**   * Super class for GOTO  *  * @version $Id$  */
end_comment

begin_class
specifier|public
specifier|abstract
class|class
name|GotoInstruction
extends|extends
name|BranchInstruction
implements|implements
name|UnconditionalBranch
block|{
specifier|private
specifier|static
specifier|final
name|long
name|serialVersionUID
init|=
operator|-
literal|2882435228056875173L
decl_stmt|;
name|GotoInstruction
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
comment|/**      * Empty constructor needed for the Class.newInstance() statement in      * Instruction.readInstruction(). Not to be used otherwise.      */
name|GotoInstruction
parameter_list|()
block|{
block|}
block|}
end_class

end_unit

