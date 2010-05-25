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
name|classfile
package|;
end_package

begin_import
import|import
name|java
operator|.
name|io
operator|.
name|DataOutputStream
import|;
end_import

begin_import
import|import
name|java
operator|.
name|io
operator|.
name|IOException
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
name|Constants
import|;
end_import

begin_class
specifier|public
class|class
name|ClassElementValue
extends|extends
name|ElementValue
block|{
comment|// For primitive types and string type, this points to the value entry in
comment|// the cpool
comment|// For 'class' this points to the class entry in the cpool
specifier|private
name|int
name|idx
decl_stmt|;
specifier|public
name|ClassElementValue
parameter_list|(
name|int
name|type
parameter_list|,
name|int
name|idx
parameter_list|,
name|ConstantPool
name|cpool
parameter_list|)
block|{
name|super
argument_list|(
name|type
argument_list|,
name|cpool
argument_list|)
expr_stmt|;
name|this
operator|.
name|idx
operator|=
name|idx
expr_stmt|;
block|}
specifier|public
name|int
name|getIndex
parameter_list|()
block|{
return|return
name|idx
return|;
block|}
specifier|public
name|String
name|getClassString
parameter_list|()
block|{
name|ConstantUtf8
name|c
init|=
operator|(
name|ConstantUtf8
operator|)
name|cpool
operator|.
name|getConstant
argument_list|(
name|idx
argument_list|,
name|Constants
operator|.
name|CONSTANT_Utf8
argument_list|)
decl_stmt|;
return|return
name|c
operator|.
name|getBytes
argument_list|()
return|;
block|}
specifier|public
name|String
name|stringifyValue
parameter_list|()
block|{
name|ConstantUtf8
name|cu8
init|=
operator|(
name|ConstantUtf8
operator|)
name|cpool
operator|.
name|getConstant
argument_list|(
name|idx
argument_list|,
name|Constants
operator|.
name|CONSTANT_Utf8
argument_list|)
decl_stmt|;
return|return
name|cu8
operator|.
name|getBytes
argument_list|()
return|;
block|}
specifier|public
name|void
name|dump
parameter_list|(
name|DataOutputStream
name|dos
parameter_list|)
throws|throws
name|IOException
block|{
name|dos
operator|.
name|writeByte
argument_list|(
name|type
argument_list|)
expr_stmt|;
comment|// u1 kind of value
name|dos
operator|.
name|writeShort
argument_list|(
name|idx
argument_list|)
expr_stmt|;
block|}
block|}
end_class

end_unit

