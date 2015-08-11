begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
begin_comment
comment|/*  * Licensed to the Apache Software Foundation (ASF) under one or more  * contributor license agreements.  See the NOTICE file distributed with  * this work for additional information regarding copyright ownership.  * The ASF licenses this file to You under the Apache License, Version 2.0  * (the "License"); you may not use this file except in compliance with  * the License.  You may obtain a copy of the License at  *  *   http://www.apache.org/licenses/LICENSE-2.0  *  * Unless required by applicable law or agreed to in writing, software  * distributed under the License is distributed on an "AS IS" BASIS,  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  * See the License for the specific language governing permissions and  * limitations under the License.  *   */
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
name|tests
package|;
end_package

begin_class
specifier|public
class|class
name|TestLegalInvokeInterface01
block|{
specifier|public
specifier|static
name|void
name|test1
parameter_list|(
name|Interface01
name|t
parameter_list|)
block|{
name|t
operator|.
name|run
argument_list|()
expr_stmt|;
block|}
block|}
end_class

begin_interface
interface|interface
name|Interface01
extends|extends
name|Runnable
block|{      }
end_interface

end_unit

