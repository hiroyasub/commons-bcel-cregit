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
name|statics
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
name|Const
import|;
end_import

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
name|Type
import|;
end_import

begin_comment
comment|/**  * This class represents the upper half of a DOUBLE variable.  * @version $Id$  */
end_comment

begin_class
specifier|public
specifier|final
class|class
name|DOUBLE_Upper
extends|extends
name|Type
block|{
comment|/** The one and only instance of this class. */
specifier|private
specifier|static
specifier|final
name|DOUBLE_Upper
name|singleton
init|=
operator|new
name|DOUBLE_Upper
argument_list|()
decl_stmt|;
comment|/** The constructor; this class must not be instantiated from the outside. */
specifier|private
name|DOUBLE_Upper
parameter_list|()
block|{
name|super
argument_list|(
name|Const
operator|.
name|T_UNKNOWN
argument_list|,
literal|"Double_Upper"
argument_list|)
expr_stmt|;
block|}
comment|/** Use this method to get the single instance of this class. */
specifier|public
specifier|static
name|DOUBLE_Upper
name|theInstance
parameter_list|()
block|{
return|return
name|singleton
return|;
block|}
block|}
end_class

end_unit

