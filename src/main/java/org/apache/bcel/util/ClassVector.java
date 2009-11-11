begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
begin_comment
comment|/*  * Copyright  2000-2009 The Apache Software Foundation  *  *  Licensed under the Apache License, Version 2.0 (the "License");   *  you may not use this file except in compliance with the License.  *  You may obtain a copy of the License at  *  *      http://www.apache.org/licenses/LICENSE-2.0  *  *  Unless required by applicable law or agreed to in writing, software  *  distributed under the License is distributed on an "AS IS" BASIS,  *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  *  See the License for the specific language governing permissions and  *  limitations under the License.   *  */
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
name|ArrayList
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
name|bcel
operator|.
name|classfile
operator|.
name|JavaClass
import|;
end_import

begin_comment
comment|/**   * Utility class implementing a (typesafe) collection of JavaClass  * objects. Contains the most important methods of a Vector.  *  * @version $Id$  * @author<A HREF="mailto:m.dahm@gmx.de">M. Dahm</A>   *   * @deprecated as of 5.1.1 - 7/17/2005  */
end_comment

begin_class
specifier|public
class|class
name|ClassVector
implements|implements
name|java
operator|.
name|io
operator|.
name|Serializable
block|{
specifier|protected
name|List
name|vec
init|=
operator|new
name|ArrayList
argument_list|()
decl_stmt|;
specifier|public
name|void
name|addElement
parameter_list|(
name|JavaClass
name|clazz
parameter_list|)
block|{
name|vec
operator|.
name|add
argument_list|(
name|clazz
argument_list|)
expr_stmt|;
block|}
specifier|public
name|JavaClass
name|elementAt
parameter_list|(
name|int
name|index
parameter_list|)
block|{
return|return
operator|(
name|JavaClass
operator|)
name|vec
operator|.
name|get
argument_list|(
name|index
argument_list|)
return|;
block|}
specifier|public
name|void
name|removeElementAt
parameter_list|(
name|int
name|index
parameter_list|)
block|{
name|vec
operator|.
name|remove
argument_list|(
name|index
argument_list|)
expr_stmt|;
block|}
specifier|public
name|JavaClass
index|[]
name|toArray
parameter_list|()
block|{
name|JavaClass
index|[]
name|classes
init|=
operator|new
name|JavaClass
index|[
name|vec
operator|.
name|size
argument_list|()
index|]
decl_stmt|;
name|vec
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
