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
name|*
import|;
end_import

begin_comment
comment|/**  * This interface defines properties of JVM bytecode subroutines.  * Note that it is 'abused' to maintain the top-level code in a  * consistent fashion, too.  *  * @version $Id$  * @author<A HREF="http://www.inf.fu-berlin.de/~ehaase"/>Enver Haase</A>  */
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

