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
name|commons
operator|.
name|bcel6
operator|.
name|verifier
operator|.
name|statics
package|;
end_package

begin_import
import|import
name|java
operator|.
name|util
operator|.
name|Hashtable
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
name|Type
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
name|verifier
operator|.
name|exc
operator|.
name|LocalVariableInfoInconsistentException
import|;
end_import

begin_comment
comment|/**  * A utility class holding the information about  * the name and the type of a local variable in  * a given slot (== index). This information  * often changes in course of byte code offsets.  *  * @version $Id$  */
end_comment

begin_class
specifier|public
class|class
name|LocalVariableInfo
block|{
comment|/** The types database. KEY: String representing the offset integer. */
specifier|private
specifier|final
name|Hashtable
argument_list|<
name|String
argument_list|,
name|Type
argument_list|>
name|types
init|=
operator|new
name|Hashtable
argument_list|<>
argument_list|()
decl_stmt|;
comment|/** The names database. KEY: String representing the offset integer. */
specifier|private
specifier|final
name|Hashtable
argument_list|<
name|String
argument_list|,
name|String
argument_list|>
name|names
init|=
operator|new
name|Hashtable
argument_list|<>
argument_list|()
decl_stmt|;
comment|/**      * Adds a name of a local variable and a certain slot to our 'names'      * (Hashtable) database.      */
specifier|private
name|void
name|setName
parameter_list|(
name|int
name|offset
parameter_list|,
name|String
name|name
parameter_list|)
block|{
name|names
operator|.
name|put
argument_list|(
name|Integer
operator|.
name|toString
argument_list|(
name|offset
argument_list|)
argument_list|,
name|name
argument_list|)
expr_stmt|;
block|}
comment|/**      * Adds a type of a local variable and a certain slot to our 'types'      * (Hashtable) database.      */
specifier|private
name|void
name|setType
parameter_list|(
name|int
name|offset
parameter_list|,
name|Type
name|t
parameter_list|)
block|{
name|types
operator|.
name|put
argument_list|(
name|Integer
operator|.
name|toString
argument_list|(
name|offset
argument_list|)
argument_list|,
name|t
argument_list|)
expr_stmt|;
block|}
comment|/**      * Returns the type of the local variable that uses this local      * variable slot at the given bytecode offset.      * Care for legal bytecode offsets yourself, otherwise the return value      * might be wrong.      * May return 'null' if nothing is known about the type of this local      * variable slot at the given bytecode offset.      */
specifier|public
name|Type
name|getType
parameter_list|(
name|int
name|offset
parameter_list|)
block|{
return|return
name|types
operator|.
name|get
argument_list|(
name|Integer
operator|.
name|toString
argument_list|(
name|offset
argument_list|)
argument_list|)
return|;
block|}
comment|/**      * Returns the name of the local variable that uses this local      * variable slot at the given bytecode offset.      * Care for legal bytecode offsets yourself, otherwise the return value      * might be wrong.      * May return 'null' if nothing is known about the type of this local      * variable slot at the given bytecode offset.      */
specifier|public
name|String
name|getName
parameter_list|(
name|int
name|offset
parameter_list|)
block|{
return|return
name|names
operator|.
name|get
argument_list|(
name|Integer
operator|.
name|toString
argument_list|(
name|offset
argument_list|)
argument_list|)
return|;
block|}
comment|/**      * Adds some information about this local variable (slot).      * @throws LocalVariableInfoInconsistentException if the new information conflicts      *         with already gathered information.      */
specifier|public
name|void
name|add
parameter_list|(
name|String
name|name
parameter_list|,
name|int
name|startpc
parameter_list|,
name|int
name|length
parameter_list|,
name|Type
name|t
parameter_list|)
throws|throws
name|LocalVariableInfoInconsistentException
block|{
for|for
control|(
name|int
name|i
init|=
name|startpc
init|;
name|i
operator|<=
name|startpc
operator|+
name|length
condition|;
name|i
operator|++
control|)
block|{
comment|// incl/incl-notation!
name|add
argument_list|(
name|i
argument_list|,
name|name
argument_list|,
name|t
argument_list|)
expr_stmt|;
block|}
block|}
comment|/**      * Adds information about name and type for a given offset.      * @throws LocalVariableInfoInconsistentException if the new information conflicts      *         with already gathered information.      */
specifier|private
name|void
name|add
parameter_list|(
name|int
name|offset
parameter_list|,
name|String
name|name
parameter_list|,
name|Type
name|t
parameter_list|)
throws|throws
name|LocalVariableInfoInconsistentException
block|{
if|if
condition|(
name|getName
argument_list|(
name|offset
argument_list|)
operator|!=
literal|null
condition|)
block|{
if|if
condition|(
operator|!
name|getName
argument_list|(
name|offset
argument_list|)
operator|.
name|equals
argument_list|(
name|name
argument_list|)
condition|)
block|{
throw|throw
operator|new
name|LocalVariableInfoInconsistentException
argument_list|(
literal|"At bytecode offset '"
operator|+
name|offset
operator|+
literal|"' a local variable has two different names: '"
operator|+
name|getName
argument_list|(
name|offset
argument_list|)
operator|+
literal|"' and '"
operator|+
name|name
operator|+
literal|"'."
argument_list|)
throw|;
block|}
block|}
if|if
condition|(
name|getType
argument_list|(
name|offset
argument_list|)
operator|!=
literal|null
condition|)
block|{
if|if
condition|(
operator|!
name|getType
argument_list|(
name|offset
argument_list|)
operator|.
name|equals
argument_list|(
name|t
argument_list|)
condition|)
block|{
throw|throw
operator|new
name|LocalVariableInfoInconsistentException
argument_list|(
literal|"At bytecode offset '"
operator|+
name|offset
operator|+
literal|"' a local variable has two different types: '"
operator|+
name|getType
argument_list|(
name|offset
argument_list|)
operator|+
literal|"' and '"
operator|+
name|t
operator|+
literal|"'."
argument_list|)
throw|;
block|}
block|}
name|setName
argument_list|(
name|offset
argument_list|,
name|name
argument_list|)
expr_stmt|;
name|setType
argument_list|(
name|offset
argument_list|,
name|t
argument_list|)
expr_stmt|;
block|}
block|}
end_class

end_unit

