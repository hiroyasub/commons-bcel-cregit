begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
begin_comment
comment|/*  * Licensed to the Apache Software Foundation (ASF) under one or more  * contributor license agreements.  See the NOTICE file distributed with  * this work for additional information regarding copyright ownership.  * The ASF licenses this file to You under the Apache License, Version 2.0  * (the "License"); you may not use this file except in compliance with  * the License.  You may obtain a copy of the License at  *  *   http://www.apache.org/licenses/LICENSE-2.0  *  * Unless required by applicable law or agreed to in writing, software  * distributed under the License is distributed on an "AS IS" BASIS,  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  * See the License for the specific language governing permissions and  * limitations under the License.  */
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
name|org
operator|.
name|junit
operator|.
name|jupiter
operator|.
name|api
operator|.
name|Disabled
import|;
end_import

begin_import
import|import
name|org
operator|.
name|junit
operator|.
name|jupiter
operator|.
name|params
operator|.
name|ParameterizedTest
import|;
end_import

begin_import
import|import
name|org
operator|.
name|junit
operator|.
name|jupiter
operator|.
name|params
operator|.
name|provider
operator|.
name|MethodSource
import|;
end_import

begin_class
specifier|public
class|class
name|VerifyJavaHomesTestCase
extends|extends
name|AbstractVerifierTestCase
block|{
annotation|@
name|Disabled
argument_list|(
literal|"Run once in a while, it takes a long time."
argument_list|)
annotation|@
name|ParameterizedTest
comment|// @Execution(ExecutionMode.CONCURRENT)
annotation|@
name|MethodSource
argument_list|(
literal|"org.apache.bcel.generic.JavaHome#streamJarEntryClassName"
argument_list|)
specifier|public
name|void
name|testJarEntryClassName
parameter_list|(
specifier|final
name|String
name|name
parameter_list|)
throws|throws
name|ClassNotFoundException
block|{
comment|// System.out.println(jarEntry.getName());
comment|// Skip $ classes for now
if|if
condition|(
operator|!
name|name
operator|.
name|contains
argument_list|(
literal|"$"
argument_list|)
condition|)
block|{
name|doAllPasses
argument_list|(
name|name
argument_list|)
expr_stmt|;
block|}
block|}
block|}
end_class

end_unit

