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
name|generic
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
name|commons
operator|.
name|bcel6
operator|.
name|classfile
operator|.
name|ArrayElementValue
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
name|classfile
operator|.
name|ElementValue
import|;
end_import

begin_comment
comment|/**  * @since 6.0  */
end_comment

begin_class
specifier|public
class|class
name|ArrayElementValueGen
extends|extends
name|ElementValueGen
block|{
comment|// J5TODO: Should we make this an array or a list? A list would be easier to
comment|// modify ...
specifier|private
specifier|final
name|List
argument_list|<
name|ElementValueGen
argument_list|>
name|evalues
decl_stmt|;
specifier|public
name|ArrayElementValueGen
parameter_list|(
name|ConstantPoolGen
name|cp
parameter_list|)
block|{
name|super
argument_list|(
name|ARRAY
argument_list|,
name|cp
argument_list|)
expr_stmt|;
name|evalues
operator|=
operator|new
name|ArrayList
argument_list|<
name|ElementValueGen
argument_list|>
argument_list|()
expr_stmt|;
block|}
specifier|public
name|ArrayElementValueGen
parameter_list|(
name|int
name|type
parameter_list|,
name|ElementValue
index|[]
name|datums
parameter_list|,
name|ConstantPoolGen
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
if|if
condition|(
name|type
operator|!=
name|ARRAY
condition|)
block|{
throw|throw
operator|new
name|RuntimeException
argument_list|(
literal|"Only element values of type array can be built with this ctor - type specified: "
operator|+
name|type
argument_list|)
throw|;
block|}
name|this
operator|.
name|evalues
operator|=
operator|new
name|ArrayList
argument_list|<
name|ElementValueGen
argument_list|>
argument_list|()
expr_stmt|;
for|for
control|(
name|ElementValue
name|datum
range|:
name|datums
control|)
block|{
name|evalues
operator|.
name|add
argument_list|(
name|ElementValueGen
operator|.
name|copy
argument_list|(
name|datum
argument_list|,
name|cpool
argument_list|,
literal|true
argument_list|)
argument_list|)
expr_stmt|;
block|}
block|}
comment|/**      * Return immutable variant of this ArrayElementValueGen      */
annotation|@
name|Override
specifier|public
name|ElementValue
name|getElementValue
parameter_list|()
block|{
name|ElementValue
index|[]
name|immutableData
init|=
operator|new
name|ElementValue
index|[
name|evalues
operator|.
name|size
argument_list|()
index|]
decl_stmt|;
name|int
name|i
init|=
literal|0
decl_stmt|;
for|for
control|(
name|ElementValueGen
name|element
range|:
name|evalues
control|)
block|{
name|immutableData
index|[
name|i
operator|++
index|]
operator|=
name|element
operator|.
name|getElementValue
argument_list|()
expr_stmt|;
block|}
return|return
operator|new
name|ArrayElementValue
argument_list|(
name|type
argument_list|,
name|immutableData
argument_list|,
name|cpGen
operator|.
name|getConstantPool
argument_list|()
argument_list|)
return|;
block|}
comment|/**      * @param value      * @param cpool      */
specifier|public
name|ArrayElementValueGen
parameter_list|(
name|ArrayElementValue
name|value
parameter_list|,
name|ConstantPoolGen
name|cpool
parameter_list|,
name|boolean
name|copyPoolEntries
parameter_list|)
block|{
name|super
argument_list|(
name|ARRAY
argument_list|,
name|cpool
argument_list|)
expr_stmt|;
name|evalues
operator|=
operator|new
name|ArrayList
argument_list|<
name|ElementValueGen
argument_list|>
argument_list|()
expr_stmt|;
name|ElementValue
index|[]
name|in
init|=
name|value
operator|.
name|getElementValuesArray
argument_list|()
decl_stmt|;
for|for
control|(
name|ElementValue
name|element
range|:
name|in
control|)
block|{
name|evalues
operator|.
name|add
argument_list|(
name|ElementValueGen
operator|.
name|copy
argument_list|(
name|element
argument_list|,
name|cpool
argument_list|,
name|copyPoolEntries
argument_list|)
argument_list|)
expr_stmt|;
block|}
block|}
annotation|@
name|Override
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
comment|// u1 type of value (ARRAY == '[')
name|dos
operator|.
name|writeShort
argument_list|(
name|evalues
operator|.
name|size
argument_list|()
argument_list|)
expr_stmt|;
for|for
control|(
name|ElementValueGen
name|element
range|:
name|evalues
control|)
block|{
name|element
operator|.
name|dump
argument_list|(
name|dos
argument_list|)
expr_stmt|;
block|}
block|}
annotation|@
name|Override
specifier|public
name|String
name|stringifyValue
parameter_list|()
block|{
name|StringBuilder
name|sb
init|=
operator|new
name|StringBuilder
argument_list|()
decl_stmt|;
name|sb
operator|.
name|append
argument_list|(
literal|"["
argument_list|)
expr_stmt|;
name|String
name|comma
init|=
literal|""
decl_stmt|;
for|for
control|(
name|ElementValueGen
name|element
range|:
name|evalues
control|)
block|{
name|sb
operator|.
name|append
argument_list|(
name|comma
argument_list|)
expr_stmt|;
name|comma
operator|=
literal|","
expr_stmt|;
name|sb
operator|.
name|append
argument_list|(
name|element
operator|.
name|stringifyValue
argument_list|()
argument_list|)
expr_stmt|;
block|}
name|sb
operator|.
name|append
argument_list|(
literal|"]"
argument_list|)
expr_stmt|;
return|return
name|sb
operator|.
name|toString
argument_list|()
return|;
block|}
specifier|public
name|List
argument_list|<
name|ElementValueGen
argument_list|>
name|getElementValues
parameter_list|()
block|{
return|return
name|evalues
return|;
block|}
specifier|public
name|int
name|getElementValuesSize
parameter_list|()
block|{
return|return
name|evalues
operator|.
name|size
argument_list|()
return|;
block|}
specifier|public
name|void
name|addElement
parameter_list|(
name|ElementValueGen
name|gen
parameter_list|)
block|{
name|evalues
operator|.
name|add
argument_list|(
name|gen
argument_list|)
expr_stmt|;
block|}
block|}
end_class

end_unit
