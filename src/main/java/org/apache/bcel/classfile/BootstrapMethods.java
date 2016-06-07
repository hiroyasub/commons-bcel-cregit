begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
begin_comment
comment|/*  * Licensed to the Apache Software Foundation (ASF) under one or more  * contributor license agreements.  See the NOTICE file distributed with  * this work for additional information regarding copyright ownership.  * The ASF licenses this file to You under the Apache License, Version 2.0  * (the "License"); you may not use this file except in compliance with  * the License.  You may obtain a copy of the License at  *  *      http://www.apache.org/licenses/LICENSE-2.0  *  *  Unless required by applicable law or agreed to in writing, software  *  distributed under the License is distributed on an "AS IS" BASIS,  *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  *  See the License for the specific language governing permissions and  *  limitations under the License.  */
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
name|DataInput
import|;
end_import

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
name|Const
import|;
end_import

begin_comment
comment|/**  * This class represents a BootstrapMethods attribute.  *  * @see<a href="http://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.7.23">  * The class File Format : The BootstrapMethods Attribute</a>  * @since 6.0  */
end_comment

begin_class
specifier|public
class|class
name|BootstrapMethods
extends|extends
name|Attribute
block|{
specifier|private
name|BootstrapMethod
index|[]
name|bootstrap_methods
decl_stmt|;
comment|// TODO this could be made final (setter is not used)
comment|/**      * Initialize from another object. Note that both objects use the same      * references (shallow copy). Use clone() for a physical copy.      */
specifier|public
name|BootstrapMethods
parameter_list|(
specifier|final
name|BootstrapMethods
name|c
parameter_list|)
block|{
name|this
argument_list|(
name|c
operator|.
name|getNameIndex
argument_list|()
argument_list|,
name|c
operator|.
name|getLength
argument_list|()
argument_list|,
name|c
operator|.
name|getBootstrapMethods
argument_list|()
argument_list|,
name|c
operator|.
name|getConstantPool
argument_list|()
argument_list|)
expr_stmt|;
block|}
comment|/**      * @param name_index Index in constant pool to CONSTANT_Utf8      * @param length Content length in bytes      * @param bootstrap_methods array of bootstrap methods      * @param constant_pool Array of constants      */
specifier|public
name|BootstrapMethods
parameter_list|(
specifier|final
name|int
name|name_index
parameter_list|,
specifier|final
name|int
name|length
parameter_list|,
specifier|final
name|BootstrapMethod
index|[]
name|bootstrap_methods
parameter_list|,
specifier|final
name|ConstantPool
name|constant_pool
parameter_list|)
block|{
name|super
argument_list|(
name|Const
operator|.
name|ATTR_BOOTSTRAP_METHODS
argument_list|,
name|name_index
argument_list|,
name|length
argument_list|,
name|constant_pool
argument_list|)
expr_stmt|;
name|this
operator|.
name|bootstrap_methods
operator|=
name|bootstrap_methods
expr_stmt|;
block|}
comment|/**      * Construct object from Input stream.      *      * @param name_index Index in constant pool to CONSTANT_Utf8      * @param length Content length in bytes      * @param input Input stream      * @param constant_pool Array of constants      * @throws IOException      */
name|BootstrapMethods
parameter_list|(
specifier|final
name|int
name|name_index
parameter_list|,
specifier|final
name|int
name|length
parameter_list|,
specifier|final
name|DataInput
name|input
parameter_list|,
specifier|final
name|ConstantPool
name|constant_pool
parameter_list|)
throws|throws
name|IOException
block|{
name|this
argument_list|(
name|name_index
argument_list|,
name|length
argument_list|,
operator|(
name|BootstrapMethod
index|[]
operator|)
literal|null
argument_list|,
name|constant_pool
argument_list|)
expr_stmt|;
name|int
name|num_bootstrap_methods
init|=
name|input
operator|.
name|readUnsignedShort
argument_list|()
decl_stmt|;
name|bootstrap_methods
operator|=
operator|new
name|BootstrapMethod
index|[
name|num_bootstrap_methods
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
name|num_bootstrap_methods
condition|;
name|i
operator|++
control|)
block|{
name|bootstrap_methods
index|[
name|i
index|]
operator|=
operator|new
name|BootstrapMethod
argument_list|(
name|input
argument_list|)
expr_stmt|;
block|}
block|}
comment|/**      * @return array of bootstrap method "records"      */
specifier|public
specifier|final
name|BootstrapMethod
index|[]
name|getBootstrapMethods
parameter_list|()
block|{
return|return
name|bootstrap_methods
return|;
block|}
comment|/**      * @param bootstrap_methods the array of bootstrap methods      */
specifier|public
specifier|final
name|void
name|setBootstrapMethods
parameter_list|(
specifier|final
name|BootstrapMethod
index|[]
name|bootstrap_methods
parameter_list|)
block|{
name|this
operator|.
name|bootstrap_methods
operator|=
name|bootstrap_methods
expr_stmt|;
block|}
comment|/**      * @param v Visitor object      */
annotation|@
name|Override
specifier|public
name|void
name|accept
parameter_list|(
specifier|final
name|Visitor
name|v
parameter_list|)
block|{
name|v
operator|.
name|visitBootstrapMethods
argument_list|(
name|this
argument_list|)
expr_stmt|;
block|}
comment|/**      * @return deep copy of this attribute      */
annotation|@
name|Override
specifier|public
name|BootstrapMethods
name|copy
parameter_list|(
specifier|final
name|ConstantPool
name|_constant_pool
parameter_list|)
block|{
name|BootstrapMethods
name|c
init|=
operator|(
name|BootstrapMethods
operator|)
name|clone
argument_list|()
decl_stmt|;
name|c
operator|.
name|bootstrap_methods
operator|=
operator|new
name|BootstrapMethod
index|[
name|bootstrap_methods
operator|.
name|length
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
name|bootstrap_methods
operator|.
name|length
condition|;
name|i
operator|++
control|)
block|{
name|c
operator|.
name|bootstrap_methods
index|[
name|i
index|]
operator|=
name|bootstrap_methods
index|[
name|i
index|]
operator|.
name|copy
argument_list|()
expr_stmt|;
block|}
name|c
operator|.
name|setConstantPool
argument_list|(
name|_constant_pool
argument_list|)
expr_stmt|;
return|return
name|c
return|;
block|}
comment|/**      * Dump bootstrap methods attribute to file stream in binary format.      *      * @param file Output file stream      * @throws IOException      */
annotation|@
name|Override
specifier|public
specifier|final
name|void
name|dump
parameter_list|(
specifier|final
name|DataOutputStream
name|file
parameter_list|)
throws|throws
name|IOException
block|{
name|super
operator|.
name|dump
argument_list|(
name|file
argument_list|)
expr_stmt|;
name|file
operator|.
name|writeShort
argument_list|(
name|bootstrap_methods
operator|.
name|length
argument_list|)
expr_stmt|;
for|for
control|(
name|BootstrapMethod
name|bootstrap_method
range|:
name|bootstrap_methods
control|)
block|{
name|bootstrap_method
operator|.
name|dump
argument_list|(
name|file
argument_list|)
expr_stmt|;
block|}
block|}
comment|/**      * @return String representation.      */
annotation|@
name|Override
specifier|public
specifier|final
name|String
name|toString
parameter_list|()
block|{
name|StringBuilder
name|buf
init|=
operator|new
name|StringBuilder
argument_list|()
decl_stmt|;
name|buf
operator|.
name|append
argument_list|(
literal|"BootstrapMethods("
argument_list|)
expr_stmt|;
name|buf
operator|.
name|append
argument_list|(
name|bootstrap_methods
operator|.
name|length
argument_list|)
expr_stmt|;
name|buf
operator|.
name|append
argument_list|(
literal|"):\n"
argument_list|)
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
name|bootstrap_methods
operator|.
name|length
condition|;
name|i
operator|++
control|)
block|{
name|buf
operator|.
name|append
argument_list|(
literal|"  "
argument_list|)
operator|.
name|append
argument_list|(
name|i
argument_list|)
operator|.
name|append
argument_list|(
literal|": "
argument_list|)
expr_stmt|;
name|buf
operator|.
name|append
argument_list|(
name|bootstrap_methods
index|[
name|i
index|]
operator|.
name|toString
argument_list|(
name|super
operator|.
name|getConstantPool
argument_list|()
argument_list|)
argument_list|)
operator|.
name|append
argument_list|(
literal|"\n"
argument_list|)
expr_stmt|;
block|}
return|return
name|buf
operator|.
name|toString
argument_list|()
return|;
block|}
block|}
end_class

end_unit

