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
name|bcel
operator|.
name|Constants
import|;
end_import

begin_comment
comment|/**  * represents one annotation in the annotation table  *   * @version $Id: AnnotationEntry  * @author<A HREF="mailto:dbrosius@qis.net">D. Brosius</A>  * @since 5.2  */
end_comment

begin_class
specifier|public
class|class
name|AnnotationEntry
implements|implements
name|Node
implements|,
name|Constants
block|{
specifier|private
name|int
name|type_index
decl_stmt|;
specifier|private
name|int
name|num_element_value_pairs
decl_stmt|;
specifier|private
name|List
name|element_value_pairs
decl_stmt|;
specifier|private
name|ConstantPool
name|constant_pool
decl_stmt|;
specifier|private
name|boolean
name|isRuntimeVisible
decl_stmt|;
comment|/**      * Construct object from file stream.      * @param file Input stream      * @throws IOException      */
specifier|public
name|AnnotationEntry
parameter_list|(
name|int
name|type_index
parameter_list|,
name|ConstantPool
name|constant_pool
parameter_list|,
name|boolean
name|isRuntimeVisible
parameter_list|)
block|{
name|this
operator|.
name|type_index
operator|=
name|type_index
expr_stmt|;
name|this
operator|.
name|constant_pool
operator|=
name|constant_pool
expr_stmt|;
name|this
operator|.
name|isRuntimeVisible
operator|=
name|isRuntimeVisible
expr_stmt|;
block|}
specifier|public
specifier|static
name|AnnotationEntry
name|read
parameter_list|(
name|DataInputStream
name|file
parameter_list|,
name|ConstantPool
name|constant_pool
parameter_list|,
name|boolean
name|isRuntimeVisible
parameter_list|)
throws|throws
name|IOException
block|{
name|AnnotationEntry
name|annotationEntry
init|=
operator|new
name|AnnotationEntry
argument_list|(
name|file
operator|.
name|readUnsignedShort
argument_list|()
argument_list|,
name|constant_pool
argument_list|,
name|isRuntimeVisible
argument_list|)
decl_stmt|;
name|annotationEntry
operator|.
name|num_element_value_pairs
operator|=
operator|(
name|file
operator|.
name|readUnsignedShort
argument_list|()
operator|)
expr_stmt|;
name|annotationEntry
operator|.
name|element_value_pairs
operator|=
operator|new
name|ArrayList
argument_list|()
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
name|annotationEntry
operator|.
name|num_element_value_pairs
condition|;
name|i
operator|++
control|)
block|{
name|annotationEntry
operator|.
name|element_value_pairs
operator|.
name|add
argument_list|(
operator|new
name|ElementValuePair
argument_list|(
name|file
operator|.
name|readUnsignedShort
argument_list|()
argument_list|,
name|ElementValue
operator|.
name|readElementValue
argument_list|(
name|file
argument_list|,
name|constant_pool
argument_list|)
argument_list|,
name|constant_pool
argument_list|)
argument_list|)
expr_stmt|;
block|}
return|return
name|annotationEntry
return|;
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
comment|//	    v.visitAnnotationEntry(this);
block|}
comment|/**      * @return the annotation type name      */
specifier|public
name|String
name|getAnnotationType
parameter_list|()
block|{
name|ConstantUtf8
name|c
decl_stmt|;
name|c
operator|=
operator|(
name|ConstantUtf8
operator|)
name|constant_pool
operator|.
name|getConstant
argument_list|(
name|type_index
argument_list|,
name|CONSTANT_Utf8
argument_list|)
expr_stmt|;
return|return
name|c
operator|.
name|getBytes
argument_list|()
return|;
block|}
comment|/**      * @return the annotation type index      */
specifier|public
name|int
name|getAnnotationTypeIndex
parameter_list|()
block|{
return|return
name|type_index
return|;
block|}
comment|/**      * @return the number of element value pairs in this annotation entry      */
specifier|public
specifier|final
name|int
name|getNumElementValuePairs
parameter_list|()
block|{
return|return
name|num_element_value_pairs
return|;
block|}
comment|/**      * @return the element value pairs in this annotation entry      */
specifier|public
name|ElementValuePair
index|[]
name|getElementValuePairs
parameter_list|()
block|{
comment|// TOFO return List
return|return
operator|(
name|ElementValuePair
index|[]
operator|)
name|element_value_pairs
operator|.
name|toArray
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
block|{
comment|// TODO Auto-generated method stub
block|}
specifier|public
name|boolean
name|isRuntimeVisible
parameter_list|()
block|{
return|return
name|isRuntimeVisible
return|;
block|}
specifier|public
name|void
name|addElementNameValuePair
parameter_list|(
name|ElementValuePair
name|elementNameValuePair
parameter_list|)
block|{
name|element_value_pairs
operator|.
name|add
argument_list|(
name|elementNameValuePair
argument_list|)
expr_stmt|;
block|}
block|}
end_class

end_unit

