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
name|verifier
operator|.
name|structurals
package|;
end_package

begin_comment
comment|/**  * A placeholder class that can be used to create an ObjectType of which  * has some of the properties arrays have. They implement java.lang.Cloneable  * and java.io.Serializable and they extend java.lang.Object.  *  */
end_comment

begin_class
specifier|public
class|class
name|GenericArray
extends|extends
name|java
operator|.
name|lang
operator|.
name|Object
implements|implements
name|java
operator|.
name|lang
operator|.
name|Cloneable
implements|,
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
literal|1991183963515237894L
decl_stmt|;
annotation|@
name|Override
specifier|protected
name|Object
name|clone
parameter_list|()
throws|throws
name|CloneNotSupportedException
block|{
return|return
name|super
operator|.
name|clone
argument_list|()
return|;
block|}
block|}
end_class

end_unit

