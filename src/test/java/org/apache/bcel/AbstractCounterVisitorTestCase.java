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
name|DescendingVisitor
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
name|apache
operator|.
name|bcel
operator|.
name|visitors
operator|.
name|CounterVisitor
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
name|BeforeEach
import|;
end_import

begin_class
specifier|public
specifier|abstract
class|class
name|AbstractCounterVisitorTestCase
extends|extends
name|AbstractTestCase
block|{
specifier|protected
specifier|abstract
name|JavaClass
name|getTestClass
parameter_list|()
throws|throws
name|ClassNotFoundException
function_decl|;
specifier|private
name|CounterVisitor
name|visitor
init|=
literal|null
decl_stmt|;
annotation|@
name|BeforeEach
specifier|public
name|void
name|setUp
parameter_list|()
throws|throws
name|ClassNotFoundException
block|{
name|visitor
operator|=
operator|new
name|CounterVisitor
argument_list|()
expr_stmt|;
operator|new
name|DescendingVisitor
argument_list|(
name|getTestClass
argument_list|()
argument_list|,
name|getVisitor
argument_list|()
argument_list|)
operator|.
name|visit
argument_list|()
expr_stmt|;
block|}
specifier|public
name|CounterVisitor
name|getVisitor
parameter_list|()
block|{
if|if
condition|(
name|visitor
operator|==
literal|null
condition|)
block|{
name|visitor
operator|=
operator|new
name|CounterVisitor
argument_list|()
expr_stmt|;
block|}
return|return
name|visitor
return|;
block|}
specifier|public
name|void
name|setVisitor
parameter_list|(
specifier|final
name|CounterVisitor
name|visitor
parameter_list|)
block|{
name|this
operator|.
name|visitor
operator|=
name|visitor
expr_stmt|;
block|}
block|}
end_class

end_unit

