begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
begin_comment
comment|/*  * Copyright  2000-2004 The Apache Software Foundation  *  *  Licensed under the Apache License, Version 2.0 (the "License");   *  you may not use this file except in compliance with the License.  *  You may obtain a copy of the License at  *  *      http://www.apache.org/licenses/LICENSE-2.0  *  *  Unless required by applicable law or agreed to in writing, software  *  distributed under the License is distributed on an "AS IS" BASIS,  *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  *  See the License for the specific language governing permissions and  *  limitations under the License.   *  */
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
name|java
operator|.
name|io
operator|.
name|*
import|;
end_import

begin_import
import|import
name|java
operator|.
name|util
operator|.
name|*
import|;
end_import

begin_comment
comment|/**  * This class represents a reference to an unknown (i.e.,  * application-specific) attribute of a class.  It is instantiated from the  *<em>Attribute.readAttribute()</em> method.  Applications that need to  * read in application-specific attributes should create an<a  * href="./AttributeReader.html">AttributeReader</a> implementation and  * attach it via<a  * href="./Attribute.html#addAttributeReader(java.lang.String,  * org.apache.bcel.classfile.AttributeReader)">Attribute.addAttributeReader</a>.   *  * @version $Id$  * @see org.apache.bcel.classfile.Attribute  * @see org.apache.bcel.classfile.AttributeReader  * @author<A HREF="mailto:m.dahm@gmx.de">M. Dahm</A>  */
end_comment

begin_class
specifier|public
specifier|final
class|class
name|Unknown
extends|extends
name|Attribute
block|{
specifier|private
name|byte
index|[]
name|bytes
decl_stmt|;
specifier|private
name|String
name|name
decl_stmt|;
specifier|private
specifier|static
name|HashMap
name|unknown_attributes
init|=
operator|new
name|HashMap
argument_list|()
decl_stmt|;
comment|/** @return array of unknown attributes, but just one for each kind.    */
specifier|static
name|Unknown
index|[]
name|getUnknownAttributes
parameter_list|()
block|{
name|Unknown
index|[]
name|unknowns
init|=
operator|new
name|Unknown
index|[
name|unknown_attributes
operator|.
name|size
argument_list|()
index|]
decl_stmt|;
name|Iterator
name|entries
init|=
name|unknown_attributes
operator|.
name|values
argument_list|()
operator|.
name|iterator
argument_list|()
decl_stmt|;
for|for
control|(
name|int
name|i
init|=
literal|0
init|;
name|entries
operator|.
name|hasNext
argument_list|()
condition|;
name|i
operator|++
control|)
name|unknowns
index|[
name|i
index|]
operator|=
operator|(
name|Unknown
operator|)
name|entries
operator|.
name|next
argument_list|()
expr_stmt|;
name|unknown_attributes
operator|.
name|clear
argument_list|()
expr_stmt|;
return|return
name|unknowns
return|;
block|}
comment|/**    * Initialize from another object. Note that both objects use the same    * references (shallow copy). Use clone() for a physical copy.    */
specifier|public
name|Unknown
parameter_list|(
name|Unknown
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
name|getBytes
argument_list|()
argument_list|,
name|c
operator|.
name|getConstantPool
argument_list|()
argument_list|)
expr_stmt|;
block|}
comment|/**    * Create a non-standard attribute.    *    * @param name_index Index in constant pool    * @param length Content length in bytes    * @param bytes Attribute contents    * @param constant_pool Array of constants    */
specifier|public
name|Unknown
parameter_list|(
name|int
name|name_index
parameter_list|,
name|int
name|length
parameter_list|,
name|byte
index|[]
name|bytes
parameter_list|,
name|ConstantPool
name|constant_pool
parameter_list|)
block|{
name|super
argument_list|(
name|Constants
operator|.
name|ATTR_UNKNOWN
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
name|bytes
operator|=
name|bytes
expr_stmt|;
name|name
operator|=
operator|(
operator|(
name|ConstantUtf8
operator|)
name|constant_pool
operator|.
name|getConstant
argument_list|(
name|name_index
argument_list|,
name|Constants
operator|.
name|CONSTANT_Utf8
argument_list|)
operator|)
operator|.
name|getBytes
argument_list|()
expr_stmt|;
name|unknown_attributes
operator|.
name|put
argument_list|(
name|name
argument_list|,
name|this
argument_list|)
expr_stmt|;
block|}
comment|/**    * Construct object from file stream.    * @param name_index Index in constant pool    * @param length Content length in bytes    * @param file Input stream    * @param constant_pool Array of constants    * @throws IOException    */
name|Unknown
parameter_list|(
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
name|name_index
argument_list|,
name|length
argument_list|,
operator|(
name|byte
index|[]
operator|)
literal|null
argument_list|,
name|constant_pool
argument_list|)
expr_stmt|;
if|if
condition|(
name|length
operator|>
literal|0
condition|)
block|{
name|bytes
operator|=
operator|new
name|byte
index|[
name|length
index|]
expr_stmt|;
name|file
operator|.
name|readFully
argument_list|(
name|bytes
argument_list|)
expr_stmt|;
block|}
block|}
comment|/**    * Called by objects that are traversing the nodes of the tree implicitely    * defined by the contents of a Java class. I.e., the hierarchy of methods,    * fields, attributes, etc. spawns a tree of objects.    *    * @param v Visitor object    */
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
name|visitUnknown
argument_list|(
name|this
argument_list|)
expr_stmt|;
block|}
comment|/**    * Dump unknown bytes to file stream.    *    * @param file Output file stream    * @throws IOException    */
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
name|super
operator|.
name|dump
argument_list|(
name|file
argument_list|)
expr_stmt|;
if|if
condition|(
name|length
operator|>
literal|0
condition|)
name|file
operator|.
name|write
argument_list|(
name|bytes
argument_list|,
literal|0
argument_list|,
name|length
argument_list|)
expr_stmt|;
block|}
comment|/**    * @return data bytes.    */
specifier|public
specifier|final
name|byte
index|[]
name|getBytes
parameter_list|()
block|{
return|return
name|bytes
return|;
block|}
comment|/**    * @return name of attribute.    */
specifier|public
specifier|final
name|String
name|getName
parameter_list|()
block|{
return|return
name|name
return|;
block|}
comment|/**    * @param bytes.    */
specifier|public
specifier|final
name|void
name|setBytes
parameter_list|(
name|byte
index|[]
name|bytes
parameter_list|)
block|{
name|this
operator|.
name|bytes
operator|=
name|bytes
expr_stmt|;
block|}
comment|/**    * @return String representation.    */
specifier|public
specifier|final
name|String
name|toString
parameter_list|()
block|{
if|if
condition|(
name|length
operator|==
literal|0
operator|||
name|bytes
operator|==
literal|null
condition|)
return|return
literal|"(Unknown attribute "
operator|+
name|name
operator|+
literal|")"
return|;
name|String
name|hex
decl_stmt|;
if|if
condition|(
name|length
operator|>
literal|10
condition|)
block|{
name|byte
index|[]
name|tmp
init|=
operator|new
name|byte
index|[
literal|10
index|]
decl_stmt|;
name|System
operator|.
name|arraycopy
argument_list|(
name|bytes
argument_list|,
literal|0
argument_list|,
name|tmp
argument_list|,
literal|0
argument_list|,
literal|10
argument_list|)
expr_stmt|;
name|hex
operator|=
name|Utility
operator|.
name|toHexString
argument_list|(
name|tmp
argument_list|)
operator|+
literal|"... (truncated)"
expr_stmt|;
block|}
else|else
name|hex
operator|=
name|Utility
operator|.
name|toHexString
argument_list|(
name|bytes
argument_list|)
expr_stmt|;
return|return
literal|"(Unknown attribute "
operator|+
name|name
operator|+
literal|": "
operator|+
name|hex
operator|+
literal|")"
return|;
block|}
comment|/**    * @return deep copy of this attribute    */
specifier|public
name|Attribute
name|copy
parameter_list|(
name|ConstantPool
name|constant_pool
parameter_list|)
block|{
name|Unknown
name|c
init|=
operator|(
name|Unknown
operator|)
name|clone
argument_list|()
decl_stmt|;
if|if
condition|(
name|bytes
operator|!=
literal|null
condition|)
name|c
operator|.
name|bytes
operator|=
name|bytes
operator|.
name|clone
argument_list|()
expr_stmt|;
name|c
operator|.
name|constant_pool
operator|=
name|constant_pool
expr_stmt|;
return|return
name|c
return|;
block|}
block|}
end_class

end_unit

