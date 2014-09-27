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
name|Stack
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

begin_comment
comment|/**   * Utility class implementing a (typesafe) stack of JavaClass objects.  *  * @version $Id$  * @author<A HREF="mailto:m.dahm@gmx.de">M. Dahm</A>   * @see Stack  */
end_comment

begin_class
specifier|public
class|class
name|ClassStack
implements|implements
name|java
operator|.
name|io
operator|.
name|Serializable
block|{
specifier|private
specifier|static
specifier|final
name|long
name|serialVersionUID
init|=
literal|6126079269396985982L
decl_stmt|;
specifier|private
specifier|final
name|Stack
argument_list|<
name|JavaClass
argument_list|>
name|stack
init|=
operator|new
name|Stack
argument_list|<
name|JavaClass
argument_list|>
argument_list|()
decl_stmt|;
specifier|public
name|void
name|push
parameter_list|(
name|JavaClass
name|clazz
parameter_list|)
block|{
name|stack
operator|.
name|push
argument_list|(
name|clazz
argument_list|)
expr_stmt|;
block|}
specifier|public
name|JavaClass
name|pop
parameter_list|()
block|{
return|return
name|stack
operator|.
name|pop
argument_list|()
return|;
block|}
specifier|public
name|JavaClass
name|top
parameter_list|()
block|{
return|return
name|stack
operator|.
name|peek
argument_list|()
return|;
block|}
specifier|public
name|boolean
name|empty
parameter_list|()
block|{
return|return
name|stack
operator|.
name|empty
argument_list|()
return|;
block|}
block|}
end_class

end_unit

