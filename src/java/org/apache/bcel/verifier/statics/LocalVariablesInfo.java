begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
begin_comment
comment|/*  * Copyright  2000-2004 The Apache Software Foundation  *  *  Licensed under the Apache License, Version 2.0 (the "License");   *  you may not use this file except in compliance with the License.  *  You may obtain a copy of the License at  *  *      http://www.apache.org/licenses/LICENSE-2.0  *  *  Unless required by applicable law or agreed to in writing, software  *  distributed under the License is distributed on an "AS IS" BASIS,  *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  *  See the License for the specific language governing permissions and  *  limitations under the License.   *  */
end_comment

begin_package
package|package
name|org
operator|.
name|apache
operator|.
name|bcel
operator|.
name|verifier
operator|.
name|statics
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
name|bcel
operator|.
name|verifier
operator|.
name|exc
operator|.
name|*
import|;
end_import

begin_comment
comment|/**  * A utility class holding the information about  * the names and the types of the local variables in  * a given method.  *  * @version $Id$  * @author<A HREF="http://www.inf.fu-berlin.de/~ehaase"/>Enver Haase</A>  */
end_comment

begin_class
specifier|public
class|class
name|LocalVariablesInfo
block|{
comment|/** The information about the local variables is stored here. */
specifier|private
name|LocalVariableInfo
index|[]
name|localVariableInfos
decl_stmt|;
comment|/** The constructor. */
name|LocalVariablesInfo
parameter_list|(
name|int
name|max_locals
parameter_list|)
block|{
name|localVariableInfos
operator|=
operator|new
name|LocalVariableInfo
index|[
name|max_locals
index|]
expr_stmt|;
for|for
control|(
name|int
name|i
init|=
literal|0
init|;
name|i
operator|<
name|max_locals
condition|;
name|i
operator|++
control|)
block|{
name|localVariableInfos
index|[
name|i
index|]
operator|=
operator|new
name|LocalVariableInfo
argument_list|()
expr_stmt|;
block|}
block|}
comment|/** Returns the LocalVariableInfo for the given slot. */
specifier|public
name|LocalVariableInfo
name|getLocalVariableInfo
parameter_list|(
name|int
name|slot
parameter_list|)
block|{
if|if
condition|(
name|slot
operator|<
literal|0
operator|||
name|slot
operator|>=
name|localVariableInfos
operator|.
name|length
condition|)
block|{
throw|throw
operator|new
name|AssertionViolatedException
argument_list|(
literal|"Slot number for local variable information out of range."
argument_list|)
throw|;
block|}
return|return
name|localVariableInfos
index|[
name|slot
index|]
return|;
block|}
comment|/** 	 * Adds information about the local variable in slot 'slot'. Automatically  	 * adds information for slot+1 if 't' is Type.LONG or Type.DOUBLE. 	 * @throws LocalVariableInfoInconsistentException if the new information conflicts 	 *         with already gathered information. 	 */
specifier|public
name|void
name|add
parameter_list|(
name|int
name|slot
parameter_list|,
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
comment|// The add operation on LocalVariableInfo may throw the '...Inconsistent...' exception, we don't throw it explicitely here.
if|if
condition|(
name|slot
operator|<
literal|0
operator|||
name|slot
operator|>=
name|localVariableInfos
operator|.
name|length
condition|)
block|{
throw|throw
operator|new
name|AssertionViolatedException
argument_list|(
literal|"Slot number for local variable information out of range."
argument_list|)
throw|;
block|}
name|localVariableInfos
index|[
name|slot
index|]
operator|.
name|add
argument_list|(
name|name
argument_list|,
name|startpc
argument_list|,
name|length
argument_list|,
name|t
argument_list|)
expr_stmt|;
if|if
condition|(
name|t
operator|==
name|Type
operator|.
name|LONG
condition|)
name|localVariableInfos
index|[
name|slot
operator|+
literal|1
index|]
operator|.
name|add
argument_list|(
name|name
argument_list|,
name|startpc
argument_list|,
name|length
argument_list|,
name|LONG_Upper
operator|.
name|theInstance
argument_list|()
argument_list|)
expr_stmt|;
if|if
condition|(
name|t
operator|==
name|Type
operator|.
name|DOUBLE
condition|)
name|localVariableInfos
index|[
name|slot
operator|+
literal|1
index|]
operator|.
name|add
argument_list|(
name|name
argument_list|,
name|startpc
argument_list|,
name|length
argument_list|,
name|DOUBLE_Upper
operator|.
name|theInstance
argument_list|()
argument_list|)
expr_stmt|;
block|}
block|}
end_class

end_unit

