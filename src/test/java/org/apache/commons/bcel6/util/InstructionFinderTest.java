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
operator|.
name|util
package|;
end_package

begin_import
import|import
name|java
operator|.
name|util
operator|.
name|Iterator
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
name|bcel6
operator|.
name|AbstractTestCase
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
name|bcel6
operator|.
name|generic
operator|.
name|IADD
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
name|bcel6
operator|.
name|generic
operator|.
name|ILOAD
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
name|bcel6
operator|.
name|generic
operator|.
name|ISTORE
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
name|bcel6
operator|.
name|generic
operator|.
name|InstructionHandle
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
name|bcel6
operator|.
name|generic
operator|.
name|InstructionList
import|;
end_import

begin_class
specifier|public
class|class
name|InstructionFinderTest
extends|extends
name|AbstractTestCase
block|{
specifier|public
name|void
name|testSearch
parameter_list|()
block|{
name|InstructionList
name|il
init|=
operator|new
name|InstructionList
argument_list|()
decl_stmt|;
name|il
operator|.
name|append
argument_list|(
operator|new
name|ILOAD
argument_list|(
literal|1
argument_list|)
argument_list|)
expr_stmt|;
name|il
operator|.
name|append
argument_list|(
operator|new
name|ILOAD
argument_list|(
literal|2
argument_list|)
argument_list|)
expr_stmt|;
name|il
operator|.
name|append
argument_list|(
operator|new
name|IADD
argument_list|()
argument_list|)
expr_stmt|;
name|il
operator|.
name|append
argument_list|(
operator|new
name|ISTORE
argument_list|(
literal|3
argument_list|)
argument_list|)
expr_stmt|;
name|InstructionFinder
name|finder
init|=
operator|new
name|InstructionFinder
argument_list|(
name|il
argument_list|)
decl_stmt|;
name|Iterator
argument_list|<
name|?
argument_list|>
name|it
init|=
name|finder
operator|.
name|search
argument_list|(
literal|"ILOAD IADD"
argument_list|,
name|il
operator|.
name|getInstructionHandles
argument_list|()
index|[
literal|0
index|]
argument_list|,
literal|null
argument_list|)
decl_stmt|;
name|InstructionHandle
index|[]
name|ihs
init|=
operator|(
name|InstructionHandle
index|[]
operator|)
name|it
operator|.
name|next
argument_list|()
decl_stmt|;
name|assertEquals
argument_list|(
literal|2
argument_list|,
name|ihs
operator|.
name|length
argument_list|)
expr_stmt|;
name|assertEquals
argument_list|(
name|ihs
index|[
literal|0
index|]
operator|.
name|getInstruction
argument_list|()
argument_list|,
operator|new
name|ILOAD
argument_list|(
literal|2
argument_list|)
argument_list|)
expr_stmt|;
name|assertEquals
argument_list|(
name|ihs
index|[
literal|1
index|]
operator|.
name|getInstruction
argument_list|()
argument_list|,
operator|new
name|IADD
argument_list|()
argument_list|)
expr_stmt|;
block|}
block|}
end_class

end_unit

