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
name|verifier
operator|.
name|tests
package|;
end_package

begin_class
specifier|public
class|class
name|TestReturn02
block|{
specifier|public
specifier|static
name|String
name|test1
parameter_list|(
name|char
index|[]
name|data
parameter_list|,
name|int
name|offset
parameter_list|,
name|int
name|count
parameter_list|)
block|{
return|return
operator|new
name|String
argument_list|(
name|data
argument_list|,
name|offset
argument_list|,
name|count
argument_list|)
return|;
block|}
specifier|public
specifier|static
name|Object
name|test2
parameter_list|()
block|{
return|return
operator|new
name|Object
argument_list|()
return|;
block|}
specifier|public
specifier|static
name|boolean
name|test3
parameter_list|()
block|{
return|return
literal|true
return|;
block|}
specifier|public
specifier|static
name|byte
name|test4
parameter_list|()
block|{
return|return
literal|1
return|;
block|}
specifier|public
specifier|static
name|short
name|test5
parameter_list|()
block|{
return|return
literal|1
return|;
block|}
specifier|public
specifier|static
name|char
name|test6
parameter_list|()
block|{
return|return
literal|'a'
return|;
block|}
specifier|public
specifier|static
name|int
name|test7
parameter_list|()
block|{
return|return
literal|1
return|;
block|}
specifier|public
specifier|static
name|long
name|test8
parameter_list|()
block|{
return|return
literal|1L
return|;
block|}
specifier|public
specifier|static
name|float
name|test9
parameter_list|()
block|{
return|return
literal|1.0f
return|;
block|}
specifier|public
specifier|static
name|double
name|test10
parameter_list|()
block|{
return|return
literal|1.0
return|;
block|}
specifier|public
specifier|static
name|Object
name|test11
parameter_list|()
block|{
return|return
literal|null
return|;
block|}
block|}
end_class

end_unit

