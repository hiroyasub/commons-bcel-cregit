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
package|;
end_package

begin_comment
comment|/**  * A VerificationResult is what a PassVerifier returns  * after verifying.  *  * @version $Id$  */
end_comment

begin_class
specifier|public
class|class
name|VerificationResult
block|{
comment|/**      * Constant to indicate verification has not been tried yet.      * This happens if some earlier verification pass did not return VERIFIED_OK.      */
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
comment|/**      * This string is the canonical message for verifications that have not been tried yet.      * This happens if some earlier verification pass did not return {@link #VERIFIED_OK}.      */
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
comment|/**      * Canonical VerificationResult for not-yet-tried verifications.      * This happens if some earlier verification pass did not return {@link #VERIFIED_OK}.      */
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
specifier|final
name|int
name|numeric
decl_stmt|;
comment|/** The detailed message. */
specifier|private
specifier|final
name|String
name|detailMessage
decl_stmt|;
comment|/** The usual constructor. */
specifier|public
name|VerificationResult
parameter_list|(
specifier|final
name|int
name|status
parameter_list|,
specifier|final
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
comment|/**      * Returns one one the {@link #VERIFIED_OK}, {@link #VERIFIED_NOTYET},      * {@link #VERIFIED_REJECTED} constants.      */
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
comment|/**      * @return a hash code value for the object.      */
annotation|@
name|Override
specifier|public
name|int
name|hashCode
parameter_list|()
block|{
return|return
name|numeric
operator|^
name|detailMessage
operator|.
name|hashCode
argument_list|()
return|;
block|}
comment|/**      * Returns if two VerificationResult instances are equal.      */
annotation|@
name|Override
specifier|public
name|boolean
name|equals
parameter_list|(
specifier|final
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
block|{
return|return
literal|false
return|;
block|}
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
name|other
operator|.
name|numeric
operator|==
name|this
operator|.
name|numeric
operator|)
operator|&&
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
return|;
block|}
comment|/**      * Returns a String representation of the VerificationResult.      */
annotation|@
name|Override
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
block|{
name|ret
operator|=
literal|"VERIFIED_NOTYET"
expr_stmt|;
block|}
if|if
condition|(
name|numeric
operator|==
name|VERIFIED_OK
condition|)
block|{
name|ret
operator|=
literal|"VERIFIED_OK"
expr_stmt|;
block|}
if|if
condition|(
name|numeric
operator|==
name|VERIFIED_REJECTED
condition|)
block|{
name|ret
operator|=
literal|"VERIFIED_REJECTED"
expr_stmt|;
block|}
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

