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
name|bcel
operator|.
name|generic
package|;
end_package

begin_comment
comment|/**  * Denotes an unparameterized instruction to store a value into a local variable,  * e.g. ISTORE.  *  * @version $Id$  */
end_comment

begin_class
specifier|public
specifier|abstract
class|class
name|StoreInstruction
extends|extends
name|LocalVariableInstruction
implements|implements
name|PopInstruction
block|{
comment|/**      * Empty constructor needed for the Class.newInstance() statement in      * Instruction.readInstruction(). Not to be used otherwise.      * tag and length are defined in readInstruction and initFromFile, respectively.      */
name|StoreInstruction
parameter_list|(
specifier|final
name|short
name|canon_tag
parameter_list|,
specifier|final
name|short
name|c_tag
parameter_list|)
block|{
name|super
argument_list|(
name|canon_tag
argument_list|,
name|c_tag
argument_list|)
expr_stmt|;
block|}
comment|/**      * @param opcode Instruction opcode      * @param c_tag Instruction number for compact version, ASTORE_0, e.g.      * @param n local variable index (unsigned short)      */
specifier|protected
name|StoreInstruction
parameter_list|(
specifier|final
name|short
name|opcode
parameter_list|,
specifier|final
name|short
name|c_tag
parameter_list|,
specifier|final
name|int
name|n
parameter_list|)
block|{
name|super
argument_list|(
name|opcode
argument_list|,
name|c_tag
argument_list|,
name|n
argument_list|)
expr_stmt|;
block|}
comment|/**      * Call corresponding visitor method(s). The order is:      * Call visitor methods of implemented interfaces first, then      * call methods according to the class hierarchy in descending order,      * i.e., the most specific visitXXX() call comes last.      *      * @param v Visitor object      */
annotation|@
name|Override
specifier|public
name|void
name|accept
parameter_list|(
specifier|final
name|Visitor
name|v
parameter_list|)
block|{
name|v
operator|.
name|visitStackConsumer
argument_list|(
name|this
argument_list|)
expr_stmt|;
name|v
operator|.
name|visitPopInstruction
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
name|visitLocalVariableInstruction
argument_list|(
name|this
argument_list|)
expr_stmt|;
name|v
operator|.
name|visitStoreInstruction
argument_list|(
name|this
argument_list|)
expr_stmt|;
block|}
block|}
end_class

end_unit
