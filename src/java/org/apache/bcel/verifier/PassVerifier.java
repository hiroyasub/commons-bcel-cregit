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
package|;
end_package

begin_comment
comment|/* ====================================================================  * The Apache Software License, Version 1.1  *  * Copyright (c) 2001 The Apache Software Foundation.  All rights  * reserved.  *  * Redistribution and use in source and binary forms, with or without  * modification, are permitted provided that the following conditions  * are met:  *  * 1. Redistributions of source code must retain the above copyright  *    notice, this list of conditions and the following disclaimer.  *  * 2. Redistributions in binary form must reproduce the above copyright  *    notice, this list of conditions and the following disclaimer in  *    the documentation and/or other materials provided with the  *    distribution.  *  * 3. The end-user documentation included with the redistribution,  *    if any, must include the following acknowledgment:  *       "This product includes software developed by the  *        Apache Software Foundation (http://www.apache.org/)."  *    Alternately, this acknowledgment may appear in the software itself,  *    if and wherever such third-party acknowledgments normally appear.  *  * 4. The names "Apache" and "Apache Software Foundation" and  *    "Apache BCEL" must not be used to endorse or promote products  *    derived from this software without prior written permission. For  *    written permission, please contact apache@apache.org.  *  * 5. Products derived from this software may not be called "Apache",  *    "Apache BCEL", nor may "Apache" appear in their name, without  *    prior written permission of the Apache Software Foundation.  *  * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED  * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES  * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE  * DISCLAIMED.  IN NO EVENT SHALL THE APACHE SOFTWARE FOUNDATION OR  * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,  * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT  * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF  * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND  * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,  * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT  * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF  * SUCH DAMAGE.  * ====================================================================  *  * This software consists of voluntary contributions made by many  * individuals on behalf of the Apache Software Foundation.  For more  * information on the Apache Software Foundation, please see  *<http://www.apache.org/>.  */
end_comment

begin_import
import|import
name|java
operator|.
name|util
operator|.
name|ArrayList
import|;
end_import

begin_comment
comment|/**  * A PassVerifier actually verifies a class file; it is instantiated  * by a Verifier.  * The verification should conform with a certain pass as described  * in The Java Virtual Machine Specification, 2nd edition.  * This book describes four passes. Pass one means loading the  * class and verifying a few static constraints. Pass two actually  * verifies some other constraints that could enforce loading in  * referenced class files. Pass three is the first pass that actually  * checks constraints in the code array of a method in the class file;  * it has two parts with the first verifying static constraints and  * the second part verifying structural constraints (where a data flow  * analysis is used for). The fourth pass, finally, performs checks  * that can only be done at run-time.  * JustIce does not have a run-time pass, but certain constraints that  * are usually delayed until run-time for performance reasons are also  * checked during the second part of pass three.  * PassVerifier instances perform caching.  * That means, if you really want a new verification run of a certain  * pass you must use a new instance of a given PassVerifier.  *  * @version $Id$  * @author<A HREF="http://www.inf.fu-berlin.de/~ehaase"/>Enver Haase</A>  * @see org.apache.bcel.verifier.Verifier  * @see #verify()  */
end_comment

begin_class
specifier|public
specifier|abstract
class|class
name|PassVerifier
block|{
comment|/** The (warning) messages. */
specifier|private
name|ArrayList
name|messages
init|=
operator|new
name|ArrayList
argument_list|()
decl_stmt|;
comment|//Type of elements: String
comment|/** The VerificationResult cache. */
specifier|private
name|VerificationResult
name|verificationResult
init|=
literal|null
decl_stmt|;
comment|/** 	 * This method runs a verification pass conforming to the 	 * Java Virtual Machine Specification, 2nd edition, on a 	 * class file. 	 * PassVerifier instances perform caching; 	 * i.e. if the verify() method once determined a VerificationResult, 	 * then this result may be returned after every invocation of this 	 * method instead of running the verification pass anew; likewise with 	 * the result of getMessages(). 	 * 	 * @see #getMessages() 	 * @see #addMessage(String) 	 */
specifier|public
name|VerificationResult
name|verify
parameter_list|()
block|{
if|if
condition|(
name|verificationResult
operator|==
literal|null
condition|)
block|{
name|verificationResult
operator|=
name|do_verify
argument_list|()
expr_stmt|;
block|}
return|return
name|verificationResult
return|;
block|}
comment|/** Does the real verification work, uncached. */
specifier|public
specifier|abstract
name|VerificationResult
name|do_verify
parameter_list|()
function_decl|;
comment|/** 	 * This method adds a (warning) message to the message pool of this 	 * PassVerifier. This method is normally only internally used by 	 * BCEL's class file verifier "JustIce" and should not be used from 	 * the outside.  	 * 	 * @see #getMessages() 	 */
specifier|public
name|void
name|addMessage
parameter_list|(
name|String
name|message
parameter_list|)
block|{
name|messages
operator|.
name|add
argument_list|(
name|message
argument_list|)
expr_stmt|;
block|}
comment|/** 	 * Returns the (warning) messages that this PassVerifier accumulated 	 * during its do_verify()ing work. 	 * 	 * @see #addMessage(String) 	 * @see #do_verify() 	 */
specifier|public
name|String
index|[]
name|getMessages
parameter_list|()
block|{
name|verify
argument_list|()
expr_stmt|;
comment|// create messages if not already done (cached!)
name|String
index|[]
name|ret
init|=
operator|new
name|String
index|[
name|messages
operator|.
name|size
argument_list|()
index|]
decl_stmt|;
for|for
control|(
name|int
name|i
init|=
literal|0
init|;
name|i
operator|<
name|messages
operator|.
name|size
argument_list|()
condition|;
name|i
operator|++
control|)
block|{
name|ret
index|[
name|i
index|]
operator|=
operator|(
name|String
operator|)
name|messages
operator|.
name|get
argument_list|(
name|i
argument_list|)
expr_stmt|;
block|}
return|return
name|ret
return|;
block|}
block|}
end_class

end_unit

