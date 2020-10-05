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
name|java
operator|.
name|io
operator|.
name|IOException
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
name|verifier
operator|.
name|tests
operator|.
name|TestReturn01Creator
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
name|verifier
operator|.
name|tests
operator|.
name|TestReturn03Creator
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
name|api
operator|.
name|Test
import|;
end_import

begin_class
specifier|public
class|class
name|VerifierReturnTestCase
extends|extends
name|AbstractVerifierTestCase
block|{
annotation|@
name|Test
specifier|public
name|void
name|testInvalidReturn
parameter_list|()
throws|throws
name|IOException
block|{
operator|new
name|TestReturn01Creator
argument_list|()
operator|.
name|create
argument_list|()
expr_stmt|;
name|assertVerifyRejected
argument_list|(
literal|"TestReturn01"
argument_list|,
literal|"Verification of a void method that returns an object must fail."
argument_list|)
expr_stmt|;
operator|new
name|TestReturn03Creator
argument_list|()
operator|.
name|create
argument_list|()
expr_stmt|;
name|assertVerifyRejected
argument_list|(
literal|"TestReturn03"
argument_list|,
literal|"Verification of an int method that returns null must fail."
argument_list|)
expr_stmt|;
block|}
annotation|@
name|Test
specifier|public
name|void
name|testValidReturn
parameter_list|()
block|{
name|assertVerifyOK
argument_list|(
literal|"TestReturn02"
argument_list|,
literal|"Verification of a method that returns a newly created object must pass."
argument_list|)
expr_stmt|;
name|assertVerifyOK
argument_list|(
literal|"TestArray01"
argument_list|,
literal|"Verification of a method that returns an array must pass."
argument_list|)
expr_stmt|;
block|}
block|}
end_class

end_unit

