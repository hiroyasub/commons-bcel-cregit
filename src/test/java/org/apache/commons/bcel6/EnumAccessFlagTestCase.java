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
name|classfile
operator|.
name|JavaClass
import|;
end_import

begin_class
specifier|public
class|class
name|EnumAccessFlagTestCase
extends|extends
name|AbstractTestCase
block|{
comment|/**      * An enumerated type, once compiled, should result in a class file that is      * marked such that we can determine from the access flags (through BCEL)      * that it was originally an enum type declaration.      */
specifier|public
name|void
name|testEnumClassSaysItIs
parameter_list|()
throws|throws
name|ClassNotFoundException
block|{
name|JavaClass
name|clazz
init|=
name|getTestClass
argument_list|(
name|PACKAGE_BASE_NAME
operator|+
literal|".data.SimpleEnum"
argument_list|)
decl_stmt|;
name|assertTrue
argument_list|(
literal|"Expected SimpleEnum class to say it was an enum - but it didn't !"
argument_list|,
name|clazz
operator|.
name|isEnum
argument_list|()
argument_list|)
expr_stmt|;
name|clazz
operator|=
name|getTestClass
argument_list|(
name|PACKAGE_BASE_NAME
operator|+
literal|".data.SimpleClass"
argument_list|)
expr_stmt|;
name|assertTrue
argument_list|(
literal|"Expected SimpleClass class to say it was not an enum - but it didn't !"
argument_list|,
operator|!
name|clazz
operator|.
name|isEnum
argument_list|()
argument_list|)
expr_stmt|;
block|}
block|}
end_class

end_unit

