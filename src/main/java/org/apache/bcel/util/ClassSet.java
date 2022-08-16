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
name|util
operator|.
name|Collection
import|;
end_import

begin_import
import|import
name|java
operator|.
name|util
operator|.
name|HashMap
import|;
end_import

begin_import
import|import
name|java
operator|.
name|util
operator|.
name|Map
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
name|commons
operator|.
name|lang3
operator|.
name|ArrayUtils
import|;
end_import

begin_comment
comment|/**  * Utility class implementing a (typesafe) set of JavaClass objects. Since JavaClass has no equals() method, the name of  * the class is used for comparison.  *  * @see ClassStack  */
end_comment

begin_class
specifier|public
class|class
name|ClassSet
block|{
specifier|private
specifier|final
name|Map
argument_list|<
name|String
argument_list|,
name|JavaClass
argument_list|>
name|map
init|=
operator|new
name|HashMap
argument_list|<>
argument_list|()
decl_stmt|;
specifier|public
name|boolean
name|add
parameter_list|(
specifier|final
name|JavaClass
name|clazz
parameter_list|)
block|{
name|boolean
name|result
init|=
literal|false
decl_stmt|;
if|if
condition|(
operator|!
name|map
operator|.
name|containsKey
argument_list|(
name|clazz
operator|.
name|getClassName
argument_list|()
argument_list|)
condition|)
block|{
name|result
operator|=
literal|true
expr_stmt|;
name|map
operator|.
name|put
argument_list|(
name|clazz
operator|.
name|getClassName
argument_list|()
argument_list|,
name|clazz
argument_list|)
expr_stmt|;
block|}
return|return
name|result
return|;
block|}
specifier|public
name|boolean
name|empty
parameter_list|()
block|{
return|return
name|map
operator|.
name|isEmpty
argument_list|()
return|;
block|}
specifier|public
name|String
index|[]
name|getClassNames
parameter_list|()
block|{
return|return
name|map
operator|.
name|keySet
argument_list|()
operator|.
name|toArray
argument_list|(
name|ArrayUtils
operator|.
name|EMPTY_STRING_ARRAY
argument_list|)
return|;
block|}
specifier|public
name|void
name|remove
parameter_list|(
specifier|final
name|JavaClass
name|clazz
parameter_list|)
block|{
name|map
operator|.
name|remove
argument_list|(
name|clazz
operator|.
name|getClassName
argument_list|()
argument_list|)
expr_stmt|;
block|}
specifier|public
name|JavaClass
index|[]
name|toArray
parameter_list|()
block|{
specifier|final
name|Collection
argument_list|<
name|JavaClass
argument_list|>
name|values
init|=
name|map
operator|.
name|values
argument_list|()
decl_stmt|;
specifier|final
name|JavaClass
index|[]
name|classes
init|=
operator|new
name|JavaClass
index|[
name|values
operator|.
name|size
argument_list|()
index|]
decl_stmt|;
name|values
operator|.
name|toArray
argument_list|(
name|classes
argument_list|)
expr_stmt|;
return|return
name|classes
return|;
block|}
block|}
end_class

end_unit

