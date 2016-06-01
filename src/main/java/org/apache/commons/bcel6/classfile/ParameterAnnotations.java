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

begin_comment
comment|/**  * base class for parameter annotations  *   * @version $Id: ParameterAnnotations  * @since 6.0  */
end_comment

begin_class
specifier|public
specifier|abstract
class|class
name|ParameterAnnotations
extends|extends
name|Attribute
block|{
comment|/** Table of parameter annotations */
specifier|private
name|ParameterAnnotationEntry
index|[]
name|parameter_annotation_table
decl_stmt|;
comment|/**      * @param parameter_annotation_type the subclass type of the parameter annotation      * @param name_index Index pointing to the name<em>Code</em>      * @param length Content length in bytes      * @param input Input stream      * @param constant_pool Array of constants      */
name|ParameterAnnotations
parameter_list|(
specifier|final
name|byte
name|parameter_annotation_type
parameter_list|,
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
name|parameter_annotation_type
argument_list|,
name|name_index
argument_list|,
name|length
argument_list|,
operator|(
name|ParameterAnnotationEntry
index|[]
operator|)
literal|null
argument_list|,
name|constant_pool
argument_list|)
expr_stmt|;
name|int
name|num_parameters
init|=
name|input
operator|.
name|readUnsignedByte
argument_list|()
decl_stmt|;
name|parameter_annotation_table
operator|=
operator|new
name|ParameterAnnotationEntry
index|[
name|num_parameters
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
name|num_parameters
condition|;
name|i
operator|++
control|)
block|{
name|parameter_annotation_table
index|[
name|i
index|]
operator|=
operator|new
name|ParameterAnnotationEntry
argument_list|(
name|input
argument_list|,
name|constant_pool
argument_list|)
expr_stmt|;
block|}
block|}
comment|/**      * @param parameter_annotation_type the subclass type of the parameter annotation      * @param name_index Index pointing to the name<em>Code</em>      * @param length Content length in bytes      * @param parameter_annotation_table the actual parameter annotations      * @param constant_pool Array of constants      */
specifier|public
name|ParameterAnnotations
parameter_list|(
specifier|final
name|byte
name|parameter_annotation_type
parameter_list|,
specifier|final
name|int
name|name_index
parameter_list|,
specifier|final
name|int
name|length
parameter_list|,
specifier|final
name|ParameterAnnotationEntry
index|[]
name|parameter_annotation_table
parameter_list|,
specifier|final
name|ConstantPool
name|constant_pool
parameter_list|)
block|{
name|super
argument_list|(
name|parameter_annotation_type
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
name|parameter_annotation_table
operator|=
name|parameter_annotation_table
expr_stmt|;
block|}
comment|/**      * Called by objects that are traversing the nodes of the tree implicitely      * defined by the contents of a Java class. I.e., the hierarchy of methods,      * fields, attributes, etc. spawns a tree of objects.      *      * @param v Visitor object      */
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
name|visitParameterAnnotation
argument_list|(
name|this
argument_list|)
expr_stmt|;
block|}
comment|/**      * @param parameter_annotation_table the entries to set in this parameter annotation      */
specifier|public
specifier|final
name|void
name|setParameterAnnotationTable
parameter_list|(
specifier|final
name|ParameterAnnotationEntry
index|[]
name|parameter_annotation_table
parameter_list|)
block|{
name|this
operator|.
name|parameter_annotation_table
operator|=
name|parameter_annotation_table
expr_stmt|;
block|}
comment|/**      * @return the parameter annotation entry table      */
specifier|public
specifier|final
name|ParameterAnnotationEntry
index|[]
name|getParameterAnnotationTable
parameter_list|()
block|{
return|return
name|parameter_annotation_table
return|;
block|}
comment|/**      * returns the array of parameter annotation entries in this parameter annotation      */
specifier|public
name|ParameterAnnotationEntry
index|[]
name|getParameterAnnotationEntries
parameter_list|()
block|{
return|return
name|parameter_annotation_table
return|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|dump
parameter_list|(
specifier|final
name|DataOutputStream
name|dos
parameter_list|)
throws|throws
name|IOException
block|{
name|super
operator|.
name|dump
argument_list|(
name|dos
argument_list|)
expr_stmt|;
name|dos
operator|.
name|writeByte
argument_list|(
name|parameter_annotation_table
operator|.
name|length
argument_list|)
expr_stmt|;
for|for
control|(
name|ParameterAnnotationEntry
name|element
range|:
name|parameter_annotation_table
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
comment|/**      * @return deep copy of this attribute      */
annotation|@
name|Override
specifier|public
name|Attribute
name|copy
parameter_list|(
specifier|final
name|ConstantPool
name|constant_pool
parameter_list|)
block|{
return|return
operator|(
name|Attribute
operator|)
name|clone
argument_list|()
return|;
block|}
block|}
end_class

end_unit

