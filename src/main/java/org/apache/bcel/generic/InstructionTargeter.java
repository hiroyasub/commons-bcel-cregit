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
comment|/**  * Denote that a class targets InstructionHandles within an InstructionList. Namely  * the following implementers:  *  * @see BranchHandle  * @see LocalVariableGen  * @see CodeExceptionGen  * @version $Id$  * @author<A HREF="mailto:m.dahm@gmx.de">M. Dahm</A>  */
end_comment

begin_interface
specifier|public
interface|interface
name|InstructionTargeter
block|{
comment|/**      * Checks whether this targeter targets the specified instruction handle.      */
name|boolean
name|containsTarget
parameter_list|(
name|InstructionHandle
name|ih
parameter_list|)
function_decl|;
comment|/**      * Replaces the target of this targeter from this old handle to the new handle.      *      * @param old_ih the old handle      * @param new_ih the new handle      * @throws ClassGenException if old_ih is not targeted by this object      */
name|void
name|updateTarget
parameter_list|(
name|InstructionHandle
name|old_ih
parameter_list|,
name|InstructionHandle
name|new_ih
parameter_list|)
throws|throws
name|ClassGenException
function_decl|;
block|}
end_interface

end_unit

