begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
begin_comment
comment|/*  * Copyright  2000-2004 The Apache Software Foundation  *  *  Licensed under the Apache License, Version 2.0 (the "License");  *  you may not use this file except in compliance with the License.  *  You may obtain a copy of the License at  *  *      http://www.apache.org/licenses/LICENSE-2.0  *  *  Unless required by applicable law or agreed to in writing, software  *  distributed under the License is distributed on an "AS IS" BASIS,  *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  *  See the License for the specific language governing permissions and  *  limitations under the License.  *  */
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
name|DataInputStream
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
comment|/**  * base class for annotations  *   * @version $Id: Annotations  * @author<A HREF="mailto:dbrosius@qis.net">D. Brosius</A>  * @since 5.2  */
end_comment

begin_class
specifier|public
specifier|abstract
class|class
name|Annotations
extends|extends
name|Attribute
block|{
specifier|private
name|int
name|annotation_table_length
decl_stmt|;
specifier|private
name|AnnotationEntry
index|[]
name|annotation_table
decl_stmt|;
comment|// Table of annotations
comment|/**      * @param annotation_type the subclass type of the annotation      * @param name_index Index pointing to the name<em>Code</em>      * @param length Content length in bytes      * @param file Input stream      * @param constant_pool Array of constants      */
name|Annotations
parameter_list|(
name|byte
name|annotation_type
parameter_list|,
name|int
name|name_index
parameter_list|,
name|int
name|length
parameter_list|,
name|DataInputStream
name|file
parameter_list|,
name|ConstantPool
name|constant_pool
parameter_list|)
throws|throws
name|IOException
block|{
name|this
argument_list|(
name|annotation_type
argument_list|,
name|name_index
argument_list|,
name|length
argument_list|,
operator|(
name|AnnotationEntry
index|[]
operator|)
literal|null
argument_list|,
name|constant_pool
argument_list|)
expr_stmt|;
name|annotation_table_length
operator|=
operator|(
name|file
operator|.
name|readUnsignedShort
argument_list|()
operator|)
expr_stmt|;
name|annotation_table
operator|=
operator|new
name|AnnotationEntry
index|[
name|annotation_table_length
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
name|annotation_table_length
condition|;
name|i
operator|++
control|)
block|{
name|annotation_table
index|[
name|i
index|]
operator|=
operator|new
name|AnnotationEntry
argument_list|(
name|file
argument_list|,
name|constant_pool
argument_list|)
expr_stmt|;
block|}
block|}
comment|/**      * @param annotation_type the subclass type of the annotation      * @param name_index Index pointing to the name<em>Code</em>      * @param length Content length in bytes      * @param annotation_table the actual annotations      * @param constant_pool Array of constants      */
specifier|public
name|Annotations
parameter_list|(
name|byte
name|annotation_type
parameter_list|,
name|int
name|name_index
parameter_list|,
name|int
name|length
parameter_list|,
name|AnnotationEntry
index|[]
name|annotation_table
parameter_list|,
name|ConstantPool
name|constant_pool
parameter_list|)
block|{
name|super
argument_list|(
name|annotation_type
argument_list|,
name|name_index
argument_list|,
name|length
argument_list|,
name|constant_pool
argument_list|)
expr_stmt|;
name|setAnnotationTable
argument_list|(
name|annotation_table
argument_list|)
expr_stmt|;
block|}
comment|/**      * Called by objects that are traversing the nodes of the tree implicitely      * defined by the contents of a Java class. I.e., the hierarchy of methods,      * fields, attributes, etc. spawns a tree of objects.      *      * @param v Visitor object      */
specifier|public
name|void
name|accept
parameter_list|(
name|Visitor
name|v
parameter_list|)
block|{
name|v
operator|.
name|visitAnnotation
argument_list|(
name|this
argument_list|)
expr_stmt|;
block|}
comment|/**      * @param annotation_table the entries to set in this annotation      */
specifier|public
specifier|final
name|void
name|setAnnotationTable
parameter_list|(
name|AnnotationEntry
index|[]
name|annotation_table
parameter_list|)
block|{
name|this
operator|.
name|annotation_table
operator|=
name|annotation_table
expr_stmt|;
name|annotation_table_length
operator|=
operator|(
name|annotation_table
operator|==
literal|null
operator|)
condition|?
literal|0
else|:
name|annotation_table
operator|.
name|length
expr_stmt|;
block|}
comment|/**      * @return the annotation entry table      */
specifier|public
specifier|final
name|AnnotationEntry
index|[]
name|getAnnotationTable
parameter_list|()
block|{
return|return
name|annotation_table
return|;
block|}
comment|/**      * returns the array of annotation entries in this annotation      */
specifier|public
name|AnnotationEntry
index|[]
name|getAnnotationEntries
parameter_list|()
block|{
return|return
name|annotation_table
return|;
block|}
comment|/**      * @return the number of annotation entries in this annotation      */
specifier|public
specifier|final
name|int
name|getNumAnnotations
parameter_list|()
block|{
return|return
name|annotation_table_length
return|;
block|}
block|}
end_class

end_unit

