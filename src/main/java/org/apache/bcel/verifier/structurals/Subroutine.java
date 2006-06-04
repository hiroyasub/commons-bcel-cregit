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
comment|/**  * This interface defines properties of JVM bytecode subroutines.  * Note that it is 'abused' to maintain the top-level code in a  * consistent fashion, too.  *  * @version $Id$  * @author Enver Haase  */
end_comment

begin_interface
specifier|public
interface|interface
name|Subroutine
block|{
comment|/** 	 * Returns all the JsrInstructions that have the 	 * first instruction of this subroutine as their target. 	 *<B>Must not be invoked on the 'top-level subroutine'.</B> 	 */
specifier|public
name|InstructionHandle
index|[]
name|getEnteringJsrInstructions
parameter_list|()
function_decl|;
comment|/** 	 * Returns the one and only RET that leaves the subroutine. 	 * Note that JustIce has a pretty rigid notion of a subroutine. 	 *<B>Must not be invoked on the 'top-level subroutine'.</B> 	 * 	 * @see org.apache.bcel.verifier.structurals.Subroutines 	 */
specifier|public
name|InstructionHandle
name|getLeavingRET
parameter_list|()
function_decl|;
comment|/** 	 * Returns all instructions that together form this subroutine. 	 * Note that an instruction is part of exactly one subroutine 	 * (the top-level code is considered to be a special subroutine) - 	 * else it is not reachable at all (dead code). 	 */
specifier|public
name|InstructionHandle
index|[]
name|getInstructions
parameter_list|()
function_decl|;
comment|/** 	 * Returns if the given InstructionHandle refers to an instruction 	 * that is part of this subroutine. This is a convenience method 	 * that saves iteration over the InstructionHandle objects returned 	 * by getInstructions(). 	 * 	 * @see #getInstructions() 	 */
specifier|public
name|boolean
name|contains
parameter_list|(
name|InstructionHandle
name|inst
parameter_list|)
function_decl|;
comment|/** 	 * Returns an int[] containing the indices of the local variable slots 	 * accessed by this Subroutine (read-accessed, write-accessed or both); 	 * local variables referenced by subroutines of this subroutine are 	 * not included. 	 * 	 * @see #getRecursivelyAccessedLocalsIndices() 	 */
specifier|public
name|int
index|[]
name|getAccessedLocalsIndices
parameter_list|()
function_decl|;
comment|/** 	 * Returns an int[] containing the indices of the local variable slots 	 * accessed by this Subroutine (read-accessed, write-accessed or both); 	 * local variables referenced by subroutines of this subroutine are 	 * included. 	 * 	 * @see #getAccessedLocalsIndices() 	 */
specifier|public
name|int
index|[]
name|getRecursivelyAccessedLocalsIndices
parameter_list|()
function_decl|;
comment|/** 	 * Returns the subroutines that are directly called from this subroutine. 	 */
specifier|public
name|Subroutine
index|[]
name|subSubs
parameter_list|()
function_decl|;
block|}
end_interface

end_unit

