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
package|;
end_package

begin_import
import|import
name|javax
operator|.
name|swing
operator|.
name|event
operator|.
name|ListDataEvent
import|;
end_import

begin_comment
comment|/**  * This class implements an adapter; it implements both a Swing ListModel and  * a VerifierFactoryObserver.  *  * @version $Id$  * @author Enver Haase  */
end_comment

begin_class
specifier|public
class|class
name|VerifierFactoryListModel
implements|implements
name|org
operator|.
name|apache
operator|.
name|bcel
operator|.
name|verifier
operator|.
name|VerifierFactoryObserver
implements|,
name|javax
operator|.
name|swing
operator|.
name|ListModel
block|{
specifier|private
name|java
operator|.
name|util
operator|.
name|ArrayList
name|listeners
init|=
operator|new
name|java
operator|.
name|util
operator|.
name|ArrayList
argument_list|()
decl_stmt|;
specifier|private
name|java
operator|.
name|util
operator|.
name|TreeSet
name|cache
init|=
operator|new
name|java
operator|.
name|util
operator|.
name|TreeSet
argument_list|()
decl_stmt|;
specifier|public
name|VerifierFactoryListModel
parameter_list|()
block|{
name|VerifierFactory
operator|.
name|attach
argument_list|(
name|this
argument_list|)
expr_stmt|;
name|update
argument_list|(
literal|null
argument_list|)
expr_stmt|;
comment|// fill cache.
block|}
specifier|public
specifier|synchronized
name|void
name|update
parameter_list|(
name|String
name|s
parameter_list|)
block|{
name|int
name|size
init|=
name|listeners
operator|.
name|size
argument_list|()
decl_stmt|;
name|Verifier
index|[]
name|verifiers
init|=
name|VerifierFactory
operator|.
name|getVerifiers
argument_list|()
decl_stmt|;
name|int
name|num_of_verifiers
init|=
name|verifiers
operator|.
name|length
decl_stmt|;
name|cache
operator|.
name|clear
argument_list|()
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
name|num_of_verifiers
condition|;
name|i
operator|++
control|)
block|{
name|cache
operator|.
name|add
argument_list|(
name|verifiers
index|[
name|i
index|]
operator|.
name|getClassName
argument_list|()
argument_list|)
expr_stmt|;
block|}
for|for
control|(
name|int
name|i
init|=
literal|0
init|;
name|i
operator|<
name|size
condition|;
name|i
operator|++
control|)
block|{
name|ListDataEvent
name|e
init|=
operator|new
name|ListDataEvent
argument_list|(
name|this
argument_list|,
name|ListDataEvent
operator|.
name|CONTENTS_CHANGED
argument_list|,
literal|0
argument_list|,
name|num_of_verifiers
operator|-
literal|1
argument_list|)
decl_stmt|;
operator|(
operator|(
name|javax
operator|.
name|swing
operator|.
name|event
operator|.
name|ListDataListener
operator|)
operator|(
name|listeners
operator|.
name|get
argument_list|(
name|i
argument_list|)
operator|)
operator|)
operator|.
name|contentsChanged
argument_list|(
name|e
argument_list|)
expr_stmt|;
block|}
block|}
specifier|public
specifier|synchronized
name|void
name|addListDataListener
parameter_list|(
name|javax
operator|.
name|swing
operator|.
name|event
operator|.
name|ListDataListener
name|l
parameter_list|)
block|{
name|listeners
operator|.
name|add
argument_list|(
name|l
argument_list|)
expr_stmt|;
block|}
specifier|public
specifier|synchronized
name|void
name|removeListDataListener
parameter_list|(
name|javax
operator|.
name|swing
operator|.
name|event
operator|.
name|ListDataListener
name|l
parameter_list|)
block|{
name|listeners
operator|.
name|remove
argument_list|(
name|l
argument_list|)
expr_stmt|;
block|}
specifier|public
specifier|synchronized
name|int
name|getSize
parameter_list|()
block|{
return|return
name|cache
operator|.
name|size
argument_list|()
return|;
block|}
specifier|public
specifier|synchronized
name|Object
name|getElementAt
parameter_list|(
name|int
name|index
parameter_list|)
block|{
return|return
operator|(
name|cache
operator|.
name|toArray
argument_list|()
operator|)
index|[
name|index
index|]
return|;
block|}
block|}
end_class

end_unit

