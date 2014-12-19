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
name|io
operator|.
name|Serializable
import|;
end_import

begin_comment
comment|/**  * Entry of the bootstrap_methods table.  *   * @see<a href="http://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.7.23">The class File Format : The BootstrapMethods Attribute</a>  * @since 6.0  */
end_comment

begin_class
specifier|public
class|class
name|BootstrapMethod
implements|implements
name|Serializable
implements|,
name|Cloneable
block|{
specifier|private
specifier|static
specifier|final
name|long
name|serialVersionUID
init|=
operator|-
literal|4517534834047695344L
decl_stmt|;
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
specifier|public
name|BootstrapMethod
parameter_list|()
block|{
block|}
comment|/**      * Construct object from input stream.      *       * @param input Input stream      * @throws IOException      * @throws ClassFormatException      */
name|BootstrapMethod
parameter_list|(
name|DataInput
name|input
parameter_list|)
throws|throws
name|IOException
throws|,
name|ClassFormatException
block|{
name|bootstrap_method_ref
operator|=
name|input
operator|.
name|readUnsignedShort
argument_list|()
expr_stmt|;
name|int
name|num_bootstrap_methods
init|=
name|input
operator|.
name|readUnsignedShort
argument_list|()
decl_stmt|;
name|bootstrap_arguments
operator|=
operator|new
name|int
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
specifier|public
name|int
name|getBootstrapMethodRef
parameter_list|()
block|{
return|return
name|bootstrap_method_ref
return|;
block|}
specifier|public
name|void
name|setBootstrapMethodRef
parameter_list|(
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
specifier|public
name|void
name|setBootstrapArguments
parameter_list|(
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
comment|/**      * Dump object to file stream on binary format.      *      * @param file Output file stream      * @throws IOException      */
specifier|public
specifier|final
name|void
name|dump
parameter_list|(
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
block|}
return|return
literal|null
return|;
block|}
block|}
end_class

end_unit

