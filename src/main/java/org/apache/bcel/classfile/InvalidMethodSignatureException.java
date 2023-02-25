begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
begin_comment
comment|/*  * Licensed to the Apache Software Foundation (ASF) under one or more  * contributor license agreements.  See the NOTICE file distributed with  * this work for additional information regarding copyright ownership.  * The ASF licenses this file to You under the Apache License, Version 2.0  * (the "License"); you may not use this file except in compliance with  * the License.  You may obtain a copy of the License at  *  *      http://www.apache.org/licenses/LICENSE-2.0  *  *  Unless required by applicable law or agreed to in writing, software  *  distributed under the License is distributed on an "AS IS" BASIS,  *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  *  See the License for the specific language governing permissions and  *  limitations under the License.  */
end_comment

begin_package
package|package
name|org
operator|.
name|apache
operator|.
name|bcel
operator|.
name|classfile
package|;
end_package

begin_comment
comment|/**  * Thrown when the BCEL attempts to read a class file and determines that a class is malformed or otherwise cannot be interpreted as a class file.  *  * @since 6.8.0  */
end_comment

begin_class
specifier|public
class|class
name|InvalidMethodSignatureException
extends|extends
name|ClassFormatException
block|{
specifier|private
specifier|static
specifier|final
name|long
name|serialVersionUID
init|=
literal|1L
decl_stmt|;
comment|/**      * Constructs a new instance with the specified invalid signature as the message.      *      * @param signature The invalid signature is saved for later retrieval by the {@link #getMessage()} method.      */
specifier|public
name|InvalidMethodSignatureException
parameter_list|(
specifier|final
name|String
name|signature
parameter_list|)
block|{
name|super
argument_list|(
name|signature
argument_list|)
expr_stmt|;
block|}
comment|/**      * Constructs a new instance with the specified invalid signature as the message and a cause.      *      * @param signature The invalid signature is saved for later retrieval by the {@link #getMessage()} method.      * @param cause     the cause (which is saved for later retrieval by the {@link #getCause()} method). A {@code null} value is permitted, and indicates that      *                  the cause is nonexistent or unknown.      */
specifier|public
name|InvalidMethodSignatureException
parameter_list|(
specifier|final
name|String
name|signature
parameter_list|,
specifier|final
name|Throwable
name|cause
parameter_list|)
block|{
name|super
argument_list|(
name|signature
argument_list|,
name|cause
argument_list|)
expr_stmt|;
block|}
block|}
end_class

end_unit

