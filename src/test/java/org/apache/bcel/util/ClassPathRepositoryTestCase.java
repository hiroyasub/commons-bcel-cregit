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
name|org
operator|.
name|apache
operator|.
name|bcel
operator|.
name|classfile
operator|.
name|JavaClass
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
name|assertEquals
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
name|assertNull
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
name|assertThrows
import|;
end_import

begin_comment
comment|/**  * Tests {@link ClassPathRepository}, {@link MemorySensitiveClassPathRepository}, and {@link  * LruCacheClassPathRepository} for their common attributes of caching.  *  *<p>Without memory scarcity, these classes behave in the same manner.  */
end_comment

begin_class
specifier|public
class|class
name|ClassPathRepositoryTestCase
block|{
specifier|private
name|void
name|verifyCaching
parameter_list|(
specifier|final
name|AbstractClassPathRepository
name|repository
parameter_list|)
throws|throws
name|ClassNotFoundException
block|{
comment|// Tests loadClass()
specifier|final
name|JavaClass
name|class1
init|=
name|repository
operator|.
name|loadClass
argument_list|(
literal|"java.lang.String"
argument_list|)
decl_stmt|;
name|assertNotNull
argument_list|(
name|class1
argument_list|)
expr_stmt|;
specifier|final
name|JavaClass
name|class2
init|=
name|repository
operator|.
name|loadClass
argument_list|(
literal|"java/lang/Long"
argument_list|)
decl_stmt|;
comment|// Slashes should work
name|assertNotNull
argument_list|(
name|class2
argument_list|)
expr_stmt|;
comment|// Tests findClass()
name|assertEquals
argument_list|(
name|class1
argument_list|,
name|repository
operator|.
name|findClass
argument_list|(
literal|"java.lang.String"
argument_list|)
argument_list|)
expr_stmt|;
name|assertEquals
argument_list|(
name|class2
argument_list|,
name|repository
operator|.
name|findClass
argument_list|(
literal|"java.lang.Long"
argument_list|)
argument_list|)
expr_stmt|;
comment|// Tests removeClass()
name|repository
operator|.
name|removeClass
argument_list|(
name|class1
argument_list|)
expr_stmt|;
name|assertNull
argument_list|(
name|repository
operator|.
name|findClass
argument_list|(
literal|"java.lang.String"
argument_list|)
argument_list|)
expr_stmt|;
comment|// Tests clear()
name|repository
operator|.
name|clear
argument_list|()
expr_stmt|;
name|assertNull
argument_list|(
name|repository
operator|.
name|findClass
argument_list|(
literal|"java.lang.Long"
argument_list|)
argument_list|)
expr_stmt|;
block|}
annotation|@
name|Test
specifier|public
name|void
name|testClassPathRepository
parameter_list|()
throws|throws
name|ClassNotFoundException
throws|,
name|IOException
block|{
try|try
init|(
specifier|final
name|ClassPath
name|classPath
init|=
operator|new
name|ClassPath
argument_list|(
literal|""
argument_list|)
init|)
block|{
name|verifyCaching
argument_list|(
operator|new
name|ClassPathRepository
argument_list|(
name|classPath
argument_list|)
argument_list|)
expr_stmt|;
block|}
block|}
annotation|@
name|Test
specifier|public
name|void
name|testMemorySensitiveClassPathRepository
parameter_list|()
throws|throws
name|ClassNotFoundException
throws|,
name|IOException
block|{
try|try
init|(
specifier|final
name|ClassPath
name|classPath
init|=
operator|new
name|ClassPath
argument_list|(
literal|""
argument_list|)
init|)
block|{
name|verifyCaching
argument_list|(
operator|new
name|MemorySensitiveClassPathRepository
argument_list|(
name|classPath
argument_list|)
argument_list|)
expr_stmt|;
block|}
block|}
annotation|@
name|Test
specifier|public
name|void
name|testLruCacheClassPathRepository
parameter_list|()
throws|throws
name|ClassNotFoundException
throws|,
name|IOException
block|{
try|try
init|(
specifier|final
name|ClassPath
name|classPath
init|=
operator|new
name|ClassPath
argument_list|(
literal|""
argument_list|)
init|)
block|{
name|verifyCaching
argument_list|(
operator|new
name|LruCacheClassPathRepository
argument_list|(
name|classPath
argument_list|,
literal|10
argument_list|)
argument_list|)
expr_stmt|;
block|}
block|}
annotation|@
name|Test
specifier|public
name|void
name|testClassPath
parameter_list|()
throws|throws
name|IOException
block|{
try|try
init|(
specifier|final
name|ClassPath
name|classPath
init|=
operator|new
name|ClassPath
argument_list|(
literal|""
argument_list|)
init|)
block|{
specifier|final
name|ClassPathRepository
name|repository
init|=
operator|new
name|ClassPathRepository
argument_list|(
name|classPath
argument_list|)
decl_stmt|;
name|assertEquals
argument_list|(
name|classPath
argument_list|,
name|repository
operator|.
name|getClassPath
argument_list|()
argument_list|)
expr_stmt|;
block|}
block|}
annotation|@
name|Test
specifier|public
name|void
name|testNoClassNotFound
parameter_list|()
throws|throws
name|IOException
block|{
try|try
init|(
specifier|final
name|ClassPath
name|classPath
init|=
operator|new
name|ClassPath
argument_list|(
literal|""
argument_list|)
init|)
block|{
specifier|final
name|ClassPathRepository
name|repository
init|=
operator|new
name|ClassPathRepository
argument_list|(
name|classPath
argument_list|)
decl_stmt|;
name|assertThrows
argument_list|(
name|ClassNotFoundException
operator|.
name|class
argument_list|,
parameter_list|()
lambda|->
name|repository
operator|.
name|loadClass
argument_list|(
literal|"no.such.Class"
argument_list|)
argument_list|)
expr_stmt|;
block|}
block|}
annotation|@
name|Test
specifier|public
name|void
name|testClassWithoutPackage
parameter_list|()
throws|throws
name|IOException
block|{
try|try
init|(
specifier|final
name|ClassPath
name|classPath
init|=
operator|new
name|ClassPath
argument_list|(
literal|""
argument_list|)
init|)
block|{
specifier|final
name|ClassPathRepository
name|repository
init|=
operator|new
name|ClassPathRepository
argument_list|(
name|classPath
argument_list|)
decl_stmt|;
name|assertThrows
argument_list|(
name|ClassNotFoundException
operator|.
name|class
argument_list|,
parameter_list|()
lambda|->
name|repository
operator|.
name|loadClass
argument_list|(
literal|"ClassXYZ"
argument_list|)
argument_list|)
expr_stmt|;
block|}
block|}
annotation|@
name|Test
specifier|public
name|void
name|testEmptyInput
parameter_list|()
throws|throws
name|IOException
block|{
try|try
init|(
specifier|final
name|ClassPath
name|classPath
init|=
operator|new
name|ClassPath
argument_list|(
literal|""
argument_list|)
init|)
block|{
specifier|final
name|ClassPathRepository
name|repository
init|=
operator|new
name|ClassPathRepository
argument_list|(
name|classPath
argument_list|)
decl_stmt|;
name|assertThrows
argument_list|(
name|IllegalArgumentException
operator|.
name|class
argument_list|,
parameter_list|()
lambda|->
name|repository
operator|.
name|loadClass
argument_list|(
literal|""
argument_list|)
argument_list|)
expr_stmt|;
block|}
block|}
annotation|@
name|Test
specifier|public
name|void
name|testNullInput
parameter_list|()
throws|throws
name|IOException
block|{
try|try
init|(
specifier|final
name|ClassPath
name|classPath
init|=
operator|new
name|ClassPath
argument_list|(
literal|""
argument_list|)
init|)
block|{
specifier|final
name|ClassPathRepository
name|repository
init|=
operator|new
name|ClassPathRepository
argument_list|(
name|classPath
argument_list|)
decl_stmt|;
name|assertThrows
argument_list|(
name|IllegalArgumentException
operator|.
name|class
argument_list|,
parameter_list|()
lambda|->
name|repository
operator|.
name|loadClass
argument_list|(
operator|(
name|String
operator|)
literal|null
argument_list|)
argument_list|)
expr_stmt|;
block|}
block|}
block|}
end_class

end_unit

