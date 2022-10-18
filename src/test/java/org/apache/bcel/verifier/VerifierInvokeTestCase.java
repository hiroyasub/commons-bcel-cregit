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
name|Test
import|;
end_import

begin_class
specifier|public
class|class
name|VerifierInvokeTestCase
extends|extends
name|AbstractVerifierTestCase
block|{
annotation|@
name|Test
specifier|public
name|void
name|testLegalInvokeInterface
parameter_list|()
throws|throws
name|ClassNotFoundException
block|{
name|assertVerifyOK
argument_list|(
literal|"TestLegalInvokeInterface01"
argument_list|,
literal|"Verification of invokeinterface on method defined in superinterface must pass."
argument_list|)
expr_stmt|;
block|}
annotation|@
name|Test
specifier|public
name|void
name|testLegalInvokeSpecial
parameter_list|()
throws|throws
name|ClassNotFoundException
block|{
name|assertVerifyOK
argument_list|(
literal|"TestLegalInvokeSpecial01"
argument_list|,
literal|"Verification of invokespecial on method defined in superclass must pass."
argument_list|)
expr_stmt|;
name|assertVerifyOK
argument_list|(
literal|"TestLegalInvokeSpecial02"
argument_list|,
literal|"Verification of invokespecial on method defined in superclass must pass."
argument_list|)
expr_stmt|;
block|}
annotation|@
name|Test
specifier|public
name|void
name|testLegalInvokeStatic
parameter_list|()
throws|throws
name|ClassNotFoundException
block|{
name|assertVerifyOK
argument_list|(
literal|"TestLegalInvokeStatic01"
argument_list|,
literal|"Verification of invokestatic on method defined in superclass must pass."
argument_list|)
expr_stmt|;
block|}
annotation|@
name|Test
specifier|public
name|void
name|testLegalInvokeVirtual
parameter_list|()
throws|throws
name|ClassNotFoundException
block|{
name|assertVerifyOK
argument_list|(
literal|"TestLegalInvokeVirtual01"
argument_list|,
literal|"Verification of invokevirtual on method defined in superclass must pass."
argument_list|)
expr_stmt|;
name|assertVerifyOK
argument_list|(
literal|"TestLegalInvokeVirtual02"
argument_list|,
literal|"Verification of invokevirtual on method defined in superinterface must pass."
argument_list|)
expr_stmt|;
block|}
block|}
end_class

end_unit

