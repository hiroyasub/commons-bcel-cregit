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
name|classfile
operator|.
name|AnnotationEntry
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
name|ElementValuePair
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
name|SimpleElementValue
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
import|import
name|java
operator|.
name|io
operator|.
name|ByteArrayOutputStream
import|;
end_import

begin_import
import|import
name|java
operator|.
name|io
operator|.
name|DataOutputStream
import|;
end_import

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
name|assertArrayEquals
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

begin_comment
comment|/**  * Tests {@link AnnotationEntry}.  */
end_comment

begin_class
specifier|public
class|class
name|AnnotationEntryTest
block|{
annotation|@
name|Test
specifier|public
name|void
name|testAddElementNameValuePair
parameter_list|()
block|{
specifier|final
name|AnnotationEntry
name|annotationEntry
init|=
operator|new
name|AnnotationEntry
argument_list|(
literal|0
argument_list|,
literal|null
argument_list|,
literal|false
argument_list|)
decl_stmt|;
name|annotationEntry
operator|.
name|addElementNameValuePair
argument_list|(
operator|new
name|ElementValuePair
argument_list|(
literal|0
argument_list|,
operator|new
name|SimpleElementValue
argument_list|(
literal|0
argument_list|,
literal|0
argument_list|,
literal|null
argument_list|)
argument_list|,
literal|null
argument_list|)
argument_list|)
expr_stmt|;
name|assertEquals
argument_list|(
literal|1
argument_list|,
name|annotationEntry
operator|.
name|getNumElementValuePairs
argument_list|()
argument_list|)
expr_stmt|;
block|}
annotation|@
name|Test
specifier|public
name|void
name|testDump
parameter_list|()
throws|throws
name|IOException
block|{
specifier|final
name|ByteArrayOutputStream
name|out
init|=
operator|new
name|ByteArrayOutputStream
argument_list|()
decl_stmt|;
operator|new
name|AnnotationEntry
argument_list|(
literal|0
argument_list|,
literal|null
argument_list|,
literal|false
argument_list|)
operator|.
name|dump
argument_list|(
operator|new
name|DataOutputStream
argument_list|(
name|out
argument_list|)
argument_list|)
expr_stmt|;
name|assertArrayEquals
argument_list|(
operator|new
name|byte
index|[
literal|4
index|]
argument_list|,
name|out
operator|.
name|toByteArray
argument_list|()
argument_list|)
expr_stmt|;
block|}
annotation|@
name|Test
specifier|public
name|void
name|testGetElementValuePairs
parameter_list|()
block|{
name|assertEquals
argument_list|(
literal|0
argument_list|,
operator|new
name|AnnotationEntry
argument_list|(
literal|0
argument_list|,
literal|null
argument_list|,
literal|false
argument_list|)
operator|.
name|getElementValuePairs
argument_list|()
operator|.
name|length
argument_list|)
expr_stmt|;
block|}
annotation|@
name|Test
specifier|public
name|void
name|testGetNumElementValuePairs
parameter_list|()
block|{
name|assertEquals
argument_list|(
literal|0
argument_list|,
operator|new
name|AnnotationEntry
argument_list|(
literal|0
argument_list|,
literal|null
argument_list|,
literal|false
argument_list|)
operator|.
name|getNumElementValuePairs
argument_list|()
argument_list|)
expr_stmt|;
block|}
block|}
end_class

end_unit

