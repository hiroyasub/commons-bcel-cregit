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

begin_comment
comment|/**  * A VerificationResult is what a PassVerifier returns  * after verifying.  *  * @version $Id$  * @author<A HREF="http://www.inf.fu-berlin.de/~ehaase"/>Enver Haase</A>  *  */
end_comment

begin_class
specifier|public
class|class
name|VerificationResult
block|{
comment|/** 	 * Constant to indicate verification has not been tried yet. 	 * This happens if some earlier verification pass did not return VERIFIED_OK. 	 */
specifier|public
specifier|static
specifier|final
name|int
name|VERIFIED_NOTYET
init|=
literal|0
decl_stmt|;
comment|/** Constant to indicate verification was passed. */
specifier|public
specifier|static
specifier|final
name|int
name|VERIFIED_OK
init|=
literal|1
decl_stmt|;
comment|/** Constant to indicate verfication failed. */
specifier|public
specifier|static
specifier|final
name|int
name|VERIFIED_REJECTED
init|=
literal|2
decl_stmt|;
comment|/** 	 * This string is the canonical message for verifications that have not been tried yet. 	 * This happens if some earlier verification pass did not return VERIFIED_OK. 	 */
specifier|private
specifier|static
specifier|final
name|String
name|VERIFIED_NOTYET_MSG
init|=
literal|"Not yet verified."
decl_stmt|;
comment|/** This string is the canonical message for passed verification passes. */
specifier|private
specifier|static
specifier|final
name|String
name|VERIFIED_OK_MSG
init|=
literal|"Passed verification."
decl_stmt|;
comment|/** 	 * Canonical VerificationResult for not-yet-tried verifications. 	 * This happens if some earlier verification pass did not return VERIFIED_OK. 	 */
specifier|public
specifier|static
specifier|final
name|VerificationResult
name|VR_NOTYET
init|=
operator|new
name|VerificationResult
argument_list|(
name|VERIFIED_NOTYET
argument_list|,
name|VERIFIED_NOTYET_MSG
argument_list|)
decl_stmt|;
comment|/** Canonical VerificationResult for passed verifications. */
specifier|public
specifier|static
specifier|final
name|VerificationResult
name|VR_OK
init|=
operator|new
name|VerificationResult
argument_list|(
name|VERIFIED_OK
argument_list|,
name|VERIFIED_OK_MSG
argument_list|)
decl_stmt|;
comment|/** The numeric status. */
specifier|private
name|int
name|numeric
decl_stmt|;
comment|/** The detailed message. */
specifier|private
name|String
name|detailMessage
decl_stmt|;
comment|/** The usual constructor. */
specifier|public
name|VerificationResult
parameter_list|(
name|int
name|status
parameter_list|,
name|String
name|message
parameter_list|)
block|{
name|numeric
operator|=
name|status
expr_stmt|;
name|detailMessage
operator|=
name|message
expr_stmt|;
block|}
comment|/** Returns one one the VERIFIED_OK, VERIFIED_NOTYET, VERIFIED_REJECTED constants. */
specifier|public
name|int
name|getStatus
parameter_list|()
block|{
return|return
name|numeric
return|;
block|}
comment|/** Returns a detailed message. */
specifier|public
name|String
name|getMessage
parameter_list|()
block|{
return|return
name|detailMessage
return|;
block|}
comment|/** 	 * Returns if two VerificationResult instances are equal. 	 */
specifier|public
name|boolean
name|equals
parameter_list|(
name|Object
name|o
parameter_list|)
block|{
if|if
condition|(
operator|!
operator|(
name|o
operator|instanceof
name|VerificationResult
operator|)
condition|)
return|return
literal|false
return|;
name|VerificationResult
name|other
init|=
operator|(
name|VerificationResult
operator|)
name|o
decl_stmt|;
return|return
operator|(
operator|(
name|other
operator|.
name|numeric
operator|==
name|this
operator|.
name|numeric
operator|)
operator|&&
operator|(
name|other
operator|.
name|detailMessage
operator|.
name|equals
argument_list|(
name|this
operator|.
name|detailMessage
argument_list|)
operator|)
operator|)
return|;
block|}
comment|/** 	 * Returns a String representation of the VerificationResult. 	 */
specifier|public
name|String
name|toString
parameter_list|()
block|{
name|String
name|ret
init|=
literal|""
decl_stmt|;
if|if
condition|(
name|numeric
operator|==
name|VERIFIED_NOTYET
condition|)
name|ret
operator|=
literal|"VERIFIED_NOTYET"
expr_stmt|;
if|if
condition|(
name|numeric
operator|==
name|VERIFIED_OK
condition|)
name|ret
operator|=
literal|"VERIFIED_OK"
expr_stmt|;
if|if
condition|(
name|numeric
operator|==
name|VERIFIED_REJECTED
condition|)
name|ret
operator|=
literal|"VERIFIED_REJECTED"
expr_stmt|;
name|ret
operator|+=
literal|"\n"
operator|+
name|detailMessage
operator|+
literal|"\n"
expr_stmt|;
return|return
name|ret
return|;
block|}
block|}
end_class

end_unit

