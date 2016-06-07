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
name|java
operator|.
name|util
operator|.
name|Arrays
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
comment|/**  * This class represents a bootstrap method attribute, i.e., the bootstrap  * method ref, the number of bootstrap arguments and an array of the  * bootstrap arguments.  *   * @see<a href="http://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.7.23">  * The class File Format : The BootstrapMethods Attribute</a>  * @since 6.0  */
end_comment

begin_class
specifier|public
class|class
name|BootstrapMethod
implements|implements
name|Cloneable
block|{
comment|/** Index of the CONSTANT_MethodHandle_info structure in the constant_pool table */
specifier|private
name|int
name|bootstrap_method_ref
decl_stmt|;
comment|/** Array of references to the constant_pool table */
specifier|private
name|int
index|[]
name|bootstrap_arguments
decl_stmt|;
comment|/**      * Initialize from another object.      */
specifier|public
name|BootstrapMethod
parameter_list|(
specifier|final
name|BootstrapMethod
name|c
parameter_list|)
block|{
name|this
argument_list|(
name|c
operator|.
name|getBootstrapMethodRef
argument_list|()
argument_list|,
name|c
operator|.
name|getBootstrapArguments
argument_list|()
argument_list|)
expr_stmt|;
block|}
comment|/**      * Construct object from input stream.      *       * @param input Input stream      * @throws IOException      */
name|BootstrapMethod
parameter_list|(
specifier|final
name|DataInput
name|input
parameter_list|)
throws|throws
name|IOException
block|{
name|this
argument_list|(
name|input
operator|.
name|readUnsignedShort
argument_list|()
argument_list|,
name|input
operator|.
name|readUnsignedShort
argument_list|()
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
name|bootstrap_arguments
operator|.
name|length
condition|;
name|i
operator|++
control|)
block|{
name|bootstrap_arguments
index|[
name|i
index|]
operator|=
name|input
operator|.
name|readUnsignedShort
argument_list|()
expr_stmt|;
block|}
block|}
comment|// helper method
specifier|private
name|BootstrapMethod
parameter_list|(
specifier|final
name|int
name|bootstrap_method_ref
parameter_list|,
specifier|final
name|int
name|num_bootstrap_arguments
parameter_list|)
block|{
name|this
argument_list|(
name|bootstrap_method_ref
argument_list|,
operator|new
name|int
index|[
name|num_bootstrap_arguments
index|]
argument_list|)
expr_stmt|;
block|}
comment|/**      * @param bootstrap_method_ref int index into constant_pool of CONSTANT_MethodHandle      * @param bootstrap_arguments int[] indices into constant_pool of CONSTANT_<type>_info      */
specifier|public
name|BootstrapMethod
parameter_list|(
specifier|final
name|int
name|bootstrap_method_ref
parameter_list|,
specifier|final
name|int
index|[]
name|bootstrap_arguments
parameter_list|)
block|{
name|this
operator|.
name|bootstrap_method_ref
operator|=
name|bootstrap_method_ref
expr_stmt|;
name|this
operator|.
name|bootstrap_arguments
operator|=
name|bootstrap_arguments
expr_stmt|;
block|}
comment|/**      * @return index into constant_pool of bootstrap_method      */
specifier|public
name|int
name|getBootstrapMethodRef
parameter_list|()
block|{
return|return
name|bootstrap_method_ref
return|;
block|}
comment|/**      * @param bootstrap_method_ref int index into constant_pool of CONSTANT_MethodHandle      */
specifier|public
name|void
name|setBootstrapMethodRef
parameter_list|(
specifier|final
name|int
name|bootstrap_method_ref
parameter_list|)
block|{
name|this
operator|.
name|bootstrap_method_ref
operator|=
name|bootstrap_method_ref
expr_stmt|;
block|}
comment|/**      * @return int[] of bootstrap_method indices into constant_pool of CONSTANT_<type>_info      */
specifier|public
name|int
index|[]
name|getBootstrapArguments
parameter_list|()
block|{
return|return
name|bootstrap_arguments
return|;
block|}
comment|/**      * @return count of number of boostrap arguments      */
specifier|public
name|int
name|getNumBootstrapArguments
parameter_list|()
block|{
return|return
name|bootstrap_arguments
operator|.
name|length
return|;
block|}
comment|/**      * @param bootstrap_arguments int[] indices into constant_pool of CONSTANT_<type>_info      */
specifier|public
name|void
name|setBootstrapArguments
parameter_list|(
specifier|final
name|int
index|[]
name|bootstrap_arguments
parameter_list|)
block|{
name|this
operator|.
name|bootstrap_arguments
operator|=
name|bootstrap_arguments
expr_stmt|;
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
return|return
literal|"BootstrapMethod("
operator|+
name|bootstrap_method_ref
operator|+
literal|", "
operator|+
name|bootstrap_arguments
operator|.
name|length
operator|+
literal|", "
operator|+
name|Arrays
operator|.
name|toString
argument_list|(
name|bootstrap_arguments
argument_list|)
operator|+
literal|")"
return|;
block|}
comment|/**      * @return Resolved string representation      */
specifier|public
specifier|final
name|String
name|toString
parameter_list|(
specifier|final
name|ConstantPool
name|constant_pool
parameter_list|)
block|{
name|StringBuilder
name|buf
init|=
operator|new
name|StringBuilder
argument_list|()
decl_stmt|;
name|String
name|bootstrap_method_name
decl_stmt|;
name|bootstrap_method_name
operator|=
name|constant_pool
operator|.
name|constantToString
argument_list|(
name|bootstrap_method_ref
argument_list|,
name|Const
operator|.
name|CONSTANT_MethodHandle
argument_list|)
expr_stmt|;
name|buf
operator|.
name|append
argument_list|(
name|Utility
operator|.
name|compactClassName
argument_list|(
name|bootstrap_method_name
argument_list|)
argument_list|)
expr_stmt|;
specifier|final
name|int
name|num_bootstrap_arguments
init|=
name|bootstrap_arguments
operator|.
name|length
decl_stmt|;
if|if
condition|(
name|num_bootstrap_arguments
operator|>
literal|0
condition|)
block|{
name|buf
operator|.
name|append
argument_list|(
literal|"\n     Method Arguments:"
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
name|num_bootstrap_arguments
condition|;
name|i
operator|++
control|)
block|{
name|buf
operator|.
name|append
argument_list|(
literal|"\n     "
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
name|constant_pool
operator|.
name|constantToString
argument_list|(
name|constant_pool
operator|.
name|getConstant
argument_list|(
name|bootstrap_arguments
index|[
name|i
index|]
argument_list|)
argument_list|)
argument_list|)
expr_stmt|;
block|}
block|}
return|return
name|buf
operator|.
name|toString
argument_list|()
return|;
block|}
comment|/**      * Dump object to file stream in binary format.      *      * @param file Output file stream      * @throws IOException      */
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
name|file
operator|.
name|writeShort
argument_list|(
name|bootstrap_method_ref
argument_list|)
expr_stmt|;
name|file
operator|.
name|writeShort
argument_list|(
name|bootstrap_arguments
operator|.
name|length
argument_list|)
expr_stmt|;
for|for
control|(
name|int
name|bootstrap_argument
range|:
name|bootstrap_arguments
control|)
block|{
name|file
operator|.
name|writeShort
argument_list|(
name|bootstrap_argument
argument_list|)
expr_stmt|;
block|}
block|}
comment|/**      * @return deep copy of this object      */
specifier|public
name|BootstrapMethod
name|copy
parameter_list|()
block|{
try|try
block|{
return|return
operator|(
name|BootstrapMethod
operator|)
name|clone
argument_list|()
return|;
block|}
catch|catch
parameter_list|(
name|CloneNotSupportedException
name|e
parameter_list|)
block|{
comment|// TODO should this throw?
block|}
return|return
literal|null
return|;
block|}
block|}
end_class

end_unit

