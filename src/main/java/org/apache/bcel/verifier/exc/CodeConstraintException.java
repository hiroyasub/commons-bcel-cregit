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
name|exc
package|;
end_package

begin_comment
comment|/**  * Instances of this class are thrown by BCEL's class file verifier "JustIce" when a class file does not pass the  * verification pass 3. Note that the pass 3 used by "JustIce" involves verification that is usually delayed to pass 4.  *  */
end_comment

begin_class
specifier|public
specifier|abstract
class|class
name|CodeConstraintException
extends|extends
name|VerificationException
block|{
specifier|private
specifier|static
specifier|final
name|long
name|serialVersionUID
init|=
operator|-
literal|7265388214714996640L
decl_stmt|;
comment|/**      * Constructs a new CodeConstraintException with null as its error message string.      */
name|CodeConstraintException
parameter_list|()
block|{
block|}
comment|/**      * Constructs a new CodeConstraintException with the specified error message.      */
name|CodeConstraintException
parameter_list|(
specifier|final
name|String
name|message
parameter_list|)
block|{
name|super
argument_list|(
name|message
argument_list|)
expr_stmt|;
block|}
block|}
end_class

end_unit

