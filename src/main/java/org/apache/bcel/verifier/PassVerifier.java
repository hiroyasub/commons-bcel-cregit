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

begin_import
import|import
name|java
operator|.
name|util
operator|.
name|ArrayList
import|;
end_import

begin_import
import|import
name|java
operator|.
name|util
operator|.
name|List
import|;
end_import

begin_comment
comment|/**  * A PassVerifier actually verifies a class file; it is instantiated  * by a Verifier.  * The verification should conform with a certain pass as described  * in The Java Virtual Machine Specification, 2nd edition.  * This book describes four passes. Pass one means loading the  * class and verifying a few static constraints. Pass two actually  * verifies some other constraints that could enforce loading in  * referenced class files. Pass three is the first pass that actually  * checks constraints in the code array of a method in the class file;  * it has two parts with the first verifying static constraints and  * the second part verifying structural constraints (where a data flow  * analysis is used for). The fourth pass, finally, performs checks  * that can only be done at run-time.  * JustIce does not have a run-time pass, but certain constraints that  * are usually delayed until run-time for performance reasons are also  * checked during the second part of pass three.  * PassVerifier instances perform caching.  * That means, if you really want a new verification run of a certain  * pass you must use a new instance of a given PassVerifier.  *  * @see Verifier  * @see #verify()  */
end_comment

begin_class
specifier|public
specifier|abstract
class|class
name|PassVerifier
block|{
comment|/** The (warning) messages. */
specifier|private
specifier|final
name|List
argument_list|<
name|String
argument_list|>
name|messages
init|=
operator|new
name|ArrayList
argument_list|<>
argument_list|()
decl_stmt|;
comment|/** The VerificationResult cache. */
specifier|private
name|VerificationResult
name|verificationResult
decl_stmt|;
comment|/**      * This method runs a verification pass conforming to the      * Java Virtual Machine Specification, 2nd edition, on a      * class file.      * PassVerifier instances perform caching;      * i.e. if the verify() method once determined a VerificationResult,      * then this result may be returned after every invocation of this      * method instead of running the verification pass anew; likewise with      * the result of getMessages().      *      * @see #getMessages()      * @see #addMessage(String)      */
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
comment|/**      * This method adds a (warning) message to the message pool of this      * PassVerifier. This method is normally only internally used by      * BCEL's class file verifier "JustIce" and should not be used from      * the outside.      *      * @see #getMessages()      */
specifier|public
name|void
name|addMessage
parameter_list|(
specifier|final
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
comment|/**      * Returns the (warning) messages that this PassVerifier accumulated      * during its do_verify()ing work.      *      * @see #addMessage(String)      * @see #do_verify()      */
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
return|return
name|messages
operator|.
name|toArray
argument_list|(
operator|new
name|String
index|[
literal|0
index|]
argument_list|)
return|;
block|}
block|}
end_class

end_unit

