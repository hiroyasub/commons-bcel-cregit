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
package|;
end_package

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
name|List
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
name|java
operator|.
name|util
operator|.
name|Vector
import|;
end_import

begin_comment
comment|/**  * This class produces instances of the Verifier class. Its purpose is to make  * sure that they are singleton instances with respect to the class name they  * operate on. That means, for every class (represented by a unique fully qualified  * class name) there is exactly one Verifier.  *  * @version $Id$  * @see Verifier  */
end_comment

begin_class
specifier|public
class|class
name|VerifierFactory
block|{
comment|/**      * The HashMap that holds the data about the already-constructed Verifier instances.      */
specifier|private
specifier|static
specifier|final
name|Map
argument_list|<
name|String
argument_list|,
name|Verifier
argument_list|>
name|hashMap
init|=
operator|new
name|HashMap
argument_list|<>
argument_list|()
decl_stmt|;
comment|/**      * The VerifierFactoryObserver instances that observe the VerifierFactory.      */
specifier|private
specifier|static
specifier|final
name|List
argument_list|<
name|VerifierFactoryObserver
argument_list|>
name|observers
init|=
operator|new
name|Vector
argument_list|<>
argument_list|()
decl_stmt|;
comment|/**      * The VerifierFactory is not instantiable.      */
specifier|private
name|VerifierFactory
parameter_list|()
block|{
block|}
comment|/**      * Returns the (only) verifier responsible for the class with the given name.      * Possibly a new Verifier object is transparently created.      * @return the (only) verifier responsible for the class with the given name.      */
specifier|public
specifier|static
name|Verifier
name|getVerifier
parameter_list|(
specifier|final
name|String
name|fully_qualified_classname
parameter_list|)
block|{
name|Verifier
name|v
init|=
name|hashMap
operator|.
name|get
argument_list|(
name|fully_qualified_classname
argument_list|)
decl_stmt|;
if|if
condition|(
name|v
operator|==
literal|null
condition|)
block|{
name|v
operator|=
operator|new
name|Verifier
argument_list|(
name|fully_qualified_classname
argument_list|)
expr_stmt|;
name|hashMap
operator|.
name|put
argument_list|(
name|fully_qualified_classname
argument_list|,
name|v
argument_list|)
expr_stmt|;
name|notify
argument_list|(
name|fully_qualified_classname
argument_list|)
expr_stmt|;
block|}
return|return
name|v
return|;
block|}
comment|/**      * Notifies the observers of a newly generated Verifier.      */
specifier|private
specifier|static
name|void
name|notify
parameter_list|(
specifier|final
name|String
name|fully_qualified_classname
parameter_list|)
block|{
comment|// notify the observers
for|for
control|(
name|VerifierFactoryObserver
name|vfo
range|:
name|observers
control|)
block|{
name|vfo
operator|.
name|update
argument_list|(
name|fully_qualified_classname
argument_list|)
expr_stmt|;
block|}
block|}
comment|/**      * Returns all Verifier instances created so far.      * This is useful when a Verifier recursively lets      * the VerifierFactory create other Verifier instances      * and if you want to verify the transitive hull of      * referenced class files.      */
specifier|public
specifier|static
name|Verifier
index|[]
name|getVerifiers
parameter_list|()
block|{
name|Verifier
index|[]
name|vs
init|=
operator|new
name|Verifier
index|[
name|hashMap
operator|.
name|values
argument_list|()
operator|.
name|size
argument_list|()
index|]
decl_stmt|;
return|return
name|hashMap
operator|.
name|values
argument_list|()
operator|.
name|toArray
argument_list|(
name|vs
argument_list|)
return|;
comment|// Because vs is big enough, vs is used to store the values into and returned!
block|}
comment|/**      * Adds the VerifierFactoryObserver o to the list of observers.      */
specifier|public
specifier|static
name|void
name|attach
parameter_list|(
specifier|final
name|VerifierFactoryObserver
name|o
parameter_list|)
block|{
name|observers
operator|.
name|add
argument_list|(
name|o
argument_list|)
expr_stmt|;
block|}
comment|/**      * Removes the VerifierFactoryObserver o from the list of observers.      */
specifier|public
specifier|static
name|void
name|detach
parameter_list|(
specifier|final
name|VerifierFactoryObserver
name|o
parameter_list|)
block|{
name|observers
operator|.
name|remove
argument_list|(
name|o
argument_list|)
expr_stmt|;
block|}
block|}
end_class

end_unit

