begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
begin_comment
comment|/*  * Licensed to the Apache Software Foundation (ASF) under one or more  * contributor license agreements.  See the NOTICE file distributed with  * this work for additional information regarding copyright ownership.  * The ASF licenses this file to You under the Apache License, Version 2.0  * (the "License"); you may not use this file except in compliance with  * the License.  You may obtain a copy of the License at  *  *   http://www.apache.org/licenses/LICENSE-2.0  *  * Unless required by applicable law or agreed to in writing, software  * distributed under the License is distributed on an "AS IS" BASIS,  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  * See the License for the specific language governing permissions and  * limitations under the License.  *  */
end_comment

begin_package
package|package
name|org
operator|.
name|apache
operator|.
name|bcel
operator|.
name|util
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
name|java
operator|.
name|io
operator|.
name|InputStream
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
name|AbstractTestCase
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
name|assertNotNull
import|;
end_import

begin_class
specifier|public
class|class
name|ClassPathTestCase
extends|extends
name|AbstractTestCase
block|{
annotation|@
name|Test
specifier|public
name|void
name|testGetClassFile
parameter_list|()
throws|throws
name|IOException
block|{
name|assertNotNull
argument_list|(
name|ClassPath
operator|.
name|SYSTEM_CLASS_PATH
operator|.
name|getClassFile
argument_list|(
literal|"java.lang.String"
argument_list|)
argument_list|)
expr_stmt|;
block|}
annotation|@
name|Test
specifier|public
name|void
name|testGetResource
parameter_list|()
block|{
name|assertNotNull
argument_list|(
name|ClassPath
operator|.
name|SYSTEM_CLASS_PATH
operator|.
name|getResource
argument_list|(
literal|"java/lang/String.class"
argument_list|)
argument_list|)
expr_stmt|;
block|}
annotation|@
name|Test
specifier|public
name|void
name|testGetResourceAsStream
parameter_list|()
throws|throws
name|IOException
block|{
try|try
init|(
specifier|final
name|InputStream
name|inputStream
init|=
name|ClassPath
operator|.
name|SYSTEM_CLASS_PATH
operator|.
name|getResourceAsStream
argument_list|(
literal|"java/lang/String.class"
argument_list|)
init|)
block|{
name|assertNotNull
argument_list|(
name|inputStream
argument_list|)
expr_stmt|;
block|}
block|}
block|}
end_class

end_unit

