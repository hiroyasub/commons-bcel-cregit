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
name|generic
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

begin_import
import|import static
name|org
operator|.
name|junit
operator|.
name|jupiter
operator|.
name|api
operator|.
name|Assertions
operator|.
name|assertEquals
import|;
end_import

begin_class
specifier|public
class|class
name|TypeTestCase
block|{
annotation|@
name|Test
specifier|public
name|void
name|testBCEL243
parameter_list|()
block|{
comment|// expectedValue = "Ljava/util/Map<TX;Ljava/util/List<TY;>;>;";
comment|// The line commented out above is the correct expected value; however,
comment|// the constructor for ObjectType is yet another place where BCEL does
comment|// not understand generics so we need to substitute the modified value below.
specifier|final
name|String
name|expectedValue
init|=
literal|"Ljava/util/Map<X, java/util/List<Y>>;"
decl_stmt|;
specifier|final
name|String
name|actualValue
init|=
operator|(
name|Type
operator|.
name|getType
argument_list|(
literal|"Ljava/util/Map<TX;Ljava/util/List<TY;>;>;"
argument_list|)
operator|)
operator|.
name|getSignature
argument_list|()
decl_stmt|;
name|assertEquals
argument_list|(
name|expectedValue
argument_list|,
name|actualValue
argument_list|,
literal|"Type.getType"
argument_list|)
expr_stmt|;
block|}
block|}
end_class

end_unit

