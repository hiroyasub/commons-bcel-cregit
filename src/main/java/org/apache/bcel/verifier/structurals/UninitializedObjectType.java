begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
begin_comment
comment|/*  * Licensed to the Apache Software Foundation (ASF) under one or more  * contributor license agreements.  See the NOTICE file distributed with  * this work for additional information regarding copyright ownership.  * The ASF licenses this file to You under the Apache License, Version 2.0  * (the "License"); you may not use this file except in compliance with  * the License.  You may obtain a copy of the License at  *  *      http://www.apache.org/licenses/LICENSE-2.0  *  *  Unless required by applicable law or agreed to in writing, software  *  distributed under the License is distributed on an "AS IS" BASIS,  *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  *  See the License for the specific language governing permissions and  *  limitations under the License.   *  */
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
name|structurals
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
name|Constants
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
name|generic
operator|.
name|ObjectType
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
name|generic
operator|.
name|ReferenceType
import|;
end_import

begin_comment
comment|/**  * This class represents an uninitialized object type; see The Java  * Virtual Machine Specification, Second Edition, page 147: 4.9.4 for  * more details.  *  * @version $Id$  * @author Enver Haase  */
end_comment

begin_class
specifier|public
class|class
name|UninitializedObjectType
extends|extends
name|ReferenceType
implements|implements
name|Constants
block|{
specifier|private
specifier|static
specifier|final
name|long
name|serialVersionUID
init|=
operator|-
literal|1228341777713117641L
decl_stmt|;
comment|/** The "initialized" version. */
specifier|private
name|ObjectType
name|initialized
decl_stmt|;
comment|/** Creates a new instance. */
specifier|public
name|UninitializedObjectType
parameter_list|(
name|ObjectType
name|t
parameter_list|)
block|{
name|super
argument_list|(
name|T_UNKNOWN
argument_list|,
literal|"<UNINITIALIZED OBJECT OF TYPE '"
operator|+
name|t
operator|.
name|getClassName
argument_list|()
operator|+
literal|"'>"
argument_list|)
expr_stmt|;
name|initialized
operator|=
name|t
expr_stmt|;
block|}
comment|/**      * Returns the ObjectType of the same class as the one of the uninitialized object      * represented by this UninitializedObjectType instance.      */
specifier|public
name|ObjectType
name|getInitialized
parameter_list|()
block|{
return|return
name|initialized
return|;
block|}
comment|/** @return a hash code value for the object.      */
annotation|@
name|Override
specifier|public
name|int
name|hashCode
parameter_list|()
block|{
return|return
name|initialized
operator|.
name|hashCode
argument_list|()
return|;
block|}
comment|/**      * Returns true on equality of this and o.      * Equality means the ObjectType instances of "initialized"      * equal one another in this and the o instance.      *      */
annotation|@
name|Override
specifier|public
name|boolean
name|equals
parameter_list|(
name|Object
name|o
parameter_list|)
block|{
if|if
condition|(
operator|!
operator|(
name|o
operator|instanceof
name|UninitializedObjectType
operator|)
condition|)
block|{
return|return
literal|false
return|;
block|}
return|return
name|initialized
operator|.
name|equals
argument_list|(
operator|(
operator|(
name|UninitializedObjectType
operator|)
name|o
operator|)
operator|.
name|initialized
argument_list|)
return|;
block|}
block|}
end_class

end_unit

