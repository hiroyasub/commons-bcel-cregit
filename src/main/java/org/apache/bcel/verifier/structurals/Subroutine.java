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
name|verifier
operator|.
name|structurals
package|;
end_package

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
comment|/**  * This interface defines properties of JVM bytecode subroutines. Note that it is 'abused' to maintain the top-level  * code in a consistent fashion, too.  *  */
end_comment

begin_interface
specifier|public
interface|interface
name|Subroutine
block|{
comment|/**      * Returns all the JsrInstructions that have the first instruction of this subroutine as their target.<B>Must not      * be invoked on the 'top-level subroutine'.</B>      *       * @return The JsrInstructions that have the first instruction of this subroutine as their target.      */
name|InstructionHandle
index|[]
name|getEnteringJsrInstructions
parameter_list|()
function_decl|;
comment|/**      * Returns the one and only RET that leaves the subroutine. Note that JustIce has a pretty rigid notion of a      * subroutine.<B>Must not be invoked on the 'top-level subroutine'.</B>      *       * @return The one and only RET that leaves the subroutine.      *      * @see Subroutines      */
name|InstructionHandle
name|getLeavingRET
parameter_list|()
function_decl|;
comment|/**      * Returns all instructions that together form this subroutine. Note that an instruction is part of exactly one      * subroutine (the top-level code is considered to be a special subroutine) - else it is not reachable at all (dead      * code).      *       * @return All instructions that together form this subroutine.      */
name|InstructionHandle
index|[]
name|getInstructions
parameter_list|()
function_decl|;
comment|/**      * Returns if the given InstructionHandle refers to an instruction that is part of this subroutine. This is a      * convenience method that saves iteration over the InstructionHandle objects returned by getInstructions().      *       * @param inst The InstructionHandle to test.      * @return Whether the given InstructionHandle refers to an instruction that is part of this subroutine.      *      * @see #getInstructions()      */
name|boolean
name|contains
parameter_list|(
name|InstructionHandle
name|inst
parameter_list|)
function_decl|;
comment|/**      * Returns an int[] containing the indices of the local variable slots accessed by this Subroutine (read-accessed,      * write-accessed or both); local variables referenced by subroutines of this subroutine are not included.      *       * @return An int[] containing the indices of the local variable slots.      * @see #getRecursivelyAccessedLocalsIndices()      */
name|int
index|[]
name|getAccessedLocalsIndices
parameter_list|()
function_decl|;
comment|/**      * Returns an int[] containing the indices of the local variable slots accessed by this Subroutine (read-accessed,      * write-accessed or both); local variables referenced by subroutines of this subroutine are included.      *       * @return An int[] containing the indices of the local variable slots.      * @see #getAccessedLocalsIndices()      */
name|int
index|[]
name|getRecursivelyAccessedLocalsIndices
parameter_list|()
function_decl|;
comment|/**      * Returns the subroutines that are directly called from this subroutine.      *       * @return The subroutines that are directly called from this subroutine.      */
name|Subroutine
index|[]
name|subSubs
parameter_list|()
function_decl|;
block|}
end_interface

end_unit

