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
name|commons
operator|.
name|bcel6
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
name|commons
operator|.
name|bcel6
operator|.
name|generic
operator|.
name|InstructionHandle
import|;
end_import

begin_import
import|import
name|org
operator|.
name|apache
operator|.
name|commons
operator|.
name|bcel6
operator|.
name|generic
operator|.
name|ObjectType
import|;
end_import

begin_comment
comment|/**  * This class represents an exception handler; that is, an ObjectType  * representing a subclass of java.lang.Throwable and the instruction  * the handler starts off (represented by an InstructionContext).  *   * @version $Id$  */
end_comment

begin_class
specifier|public
class|class
name|ExceptionHandler
block|{
comment|/** The type of the exception to catch. NULL means ANY. */
specifier|private
specifier|final
name|ObjectType
name|catchtype
decl_stmt|;
comment|/** The InstructionHandle where the handling begins. */
specifier|private
specifier|final
name|InstructionHandle
name|handlerpc
decl_stmt|;
comment|/** Leave instance creation to JustIce. */
name|ExceptionHandler
parameter_list|(
specifier|final
name|ObjectType
name|catch_type
parameter_list|,
specifier|final
name|InstructionHandle
name|handler_pc
parameter_list|)
block|{
name|catchtype
operator|=
name|catch_type
expr_stmt|;
name|handlerpc
operator|=
name|handler_pc
expr_stmt|;
block|}
comment|/**      * Returns the type of the exception that's handled.<B>'null' means 'ANY'.</B>      */
specifier|public
name|ObjectType
name|getExceptionType
parameter_list|()
block|{
return|return
name|catchtype
return|;
block|}
comment|/**      * Returns the InstructionHandle where the handler starts off.      */
specifier|public
name|InstructionHandle
name|getHandlerStart
parameter_list|()
block|{
return|return
name|handlerpc
return|;
block|}
block|}
end_class

end_unit

