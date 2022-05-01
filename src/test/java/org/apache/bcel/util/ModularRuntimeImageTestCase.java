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
name|nio
operator|.
name|file
operator|.
name|Path
import|;
end_import

begin_import
import|import
name|java
operator|.
name|util
operator|.
name|List
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
name|lang3
operator|.
name|JavaVersion
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
name|lang3
operator|.
name|SystemUtils
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
name|BeforeAll
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
name|assertFalse
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
name|assertTrue
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
name|Assumptions
operator|.
name|assumeTrue
import|;
end_import

begin_comment
comment|/**  * Tests {@link ModularRuntimeImage}.  */
end_comment

begin_class
specifier|public
class|class
name|ModularRuntimeImageTestCase
block|{
annotation|@
name|BeforeAll
specifier|public
specifier|static
name|void
name|before
parameter_list|()
block|{
name|assumeTrue
argument_list|(
name|SystemUtils
operator|.
name|isJavaVersionAtLeast
argument_list|(
name|JavaVersion
operator|.
name|JAVA_9
argument_list|)
argument_list|)
expr_stmt|;
block|}
annotation|@
name|ParameterizedTest
annotation|@
name|MethodSource
argument_list|(
literal|"org.apache.bcel.generic.JdkGenericDumpTestCase#findJavaHomes"
argument_list|)
specifier|public
name|void
name|testListJreModule
parameter_list|(
specifier|final
name|ModularRuntimeImage
name|modularRuntimeImage
parameter_list|)
throws|throws
name|IOException
block|{
specifier|final
name|List
argument_list|<
name|Path
argument_list|>
name|listEntries
init|=
name|modularRuntimeImage
operator|.
name|list
argument_list|(
name|ModularRuntimeImage
operator|.
name|MODULES_PATH
operator|+
literal|"/java.base"
argument_list|)
decl_stmt|;
name|assertFalse
argument_list|(
name|listEntries
operator|.
name|isEmpty
argument_list|()
argument_list|)
expr_stmt|;
name|assertTrue
argument_list|(
name|listEntries
operator|.
name|toString
argument_list|()
operator|.
name|indexOf
argument_list|(
literal|"/java.base"
argument_list|)
operator|>
operator|-
literal|1
argument_list|)
expr_stmt|;
block|}
annotation|@
name|ParameterizedTest
annotation|@
name|MethodSource
argument_list|(
literal|"org.apache.bcel.generic.JdkGenericDumpTestCase#findJavaHomes"
argument_list|)
specifier|public
name|void
name|testListJreModulePackageDir
parameter_list|(
specifier|final
name|ModularRuntimeImage
name|modularRuntimeImage
parameter_list|)
throws|throws
name|IOException
block|{
specifier|final
name|List
argument_list|<
name|Path
argument_list|>
name|listEntries
init|=
name|modularRuntimeImage
operator|.
name|list
argument_list|(
name|ModularRuntimeImage
operator|.
name|MODULES_PATH
operator|+
literal|"/java.base/java/lang"
argument_list|)
decl_stmt|;
name|assertFalse
argument_list|(
name|listEntries
operator|.
name|isEmpty
argument_list|()
argument_list|)
expr_stmt|;
name|assertTrue
argument_list|(
name|listEntries
operator|.
name|toString
argument_list|()
operator|.
name|indexOf
argument_list|(
literal|"/java.base/java/lang/String.class"
argument_list|)
operator|>
operator|-
literal|1
argument_list|)
expr_stmt|;
block|}
annotation|@
name|ParameterizedTest
annotation|@
name|MethodSource
argument_list|(
literal|"org.apache.bcel.generic.JdkGenericDumpTestCase#findJavaHomes"
argument_list|)
specifier|public
name|void
name|testListJreModules
parameter_list|(
specifier|final
name|ModularRuntimeImage
name|modularRuntimeImage
parameter_list|)
throws|throws
name|IOException
block|{
specifier|final
name|List
argument_list|<
name|Path
argument_list|>
name|listEntries
init|=
name|modularRuntimeImage
operator|.
name|list
argument_list|(
name|ModularRuntimeImage
operator|.
name|MODULES_PATH
argument_list|)
decl_stmt|;
name|assertFalse
argument_list|(
name|listEntries
operator|.
name|isEmpty
argument_list|()
argument_list|)
expr_stmt|;
name|assertTrue
argument_list|(
name|listEntries
operator|.
name|toString
argument_list|()
operator|.
name|indexOf
argument_list|(
literal|"/java.base"
argument_list|)
operator|>
operator|-
literal|1
argument_list|)
expr_stmt|;
block|}
annotation|@
name|ParameterizedTest
annotation|@
name|MethodSource
argument_list|(
literal|"org.apache.bcel.generic.JdkGenericDumpTestCase#findJavaHomes"
argument_list|)
specifier|public
name|void
name|testListJrePackages
parameter_list|(
specifier|final
name|ModularRuntimeImage
name|modularRuntimeImage
parameter_list|)
throws|throws
name|IOException
block|{
specifier|final
name|List
argument_list|<
name|Path
argument_list|>
name|listEntries
init|=
name|modularRuntimeImage
operator|.
name|list
argument_list|(
name|ModularRuntimeImage
operator|.
name|PACKAGES_PATH
argument_list|)
decl_stmt|;
name|assertFalse
argument_list|(
name|listEntries
operator|.
name|isEmpty
argument_list|()
argument_list|)
expr_stmt|;
name|assertTrue
argument_list|(
name|listEntries
operator|.
name|toString
argument_list|()
operator|.
name|indexOf
argument_list|(
literal|"java.lang"
argument_list|)
operator|>
operator|-
literal|1
argument_list|)
expr_stmt|;
block|}
block|}
end_class

end_unit

