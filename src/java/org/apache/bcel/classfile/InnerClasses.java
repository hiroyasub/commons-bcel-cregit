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

begin_comment
comment|/**  * This class is derived from<em>Attribute</em> and denotes that this class  * is an Inner class of another.  * to the source file of this class.  * It is instantiated from the<em>Attribute.readAttribute()</em> method.  *  * @version $Id$  * @author<A HREF="mailto:m.dahm@gmx.de">M. Dahm</A>  * @see     Attribute  */
end_comment

begin_class
specifier|public
specifier|final
class|class
name|InnerClasses
extends|extends
name|Attribute
block|{
specifier|private
name|InnerClass
index|[]
name|inner_classes
decl_stmt|;
specifier|private
name|int
name|number_of_classes
decl_stmt|;
comment|/**    * Initialize from another object. Note that both objects use the same    * references (shallow copy). Use clone() for a physical copy.    */
specifier|public
name|InnerClasses
parameter_list|(
name|InnerClasses
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
name|getInnerClasses
argument_list|()
argument_list|,
name|c
operator|.
name|getConstantPool
argument_list|()
argument_list|)
expr_stmt|;
block|}
comment|/**    * @param name_index Index in constant pool to CONSTANT_Utf8    * @param length Content length in bytes    * @param inner_classes array of inner classes attributes    * @param constant_pool Array of constants    */
specifier|public
name|InnerClasses
parameter_list|(
name|int
name|name_index
parameter_list|,
name|int
name|length
parameter_list|,
name|InnerClass
index|[]
name|inner_classes
parameter_list|,
name|ConstantPool
name|constant_pool
parameter_list|)
block|{
name|super
argument_list|(
name|Constants
operator|.
name|ATTR_INNER_CLASSES
argument_list|,
name|name_index
argument_list|,
name|length
argument_list|,
name|constant_pool
argument_list|)
expr_stmt|;
name|setInnerClasses
argument_list|(
name|inner_classes
argument_list|)
expr_stmt|;
block|}
comment|/**    * Construct object from file stream.    *    * @param name_index Index in constant pool to CONSTANT_Utf8    * @param length Content length in bytes    * @param file Input stream    * @param constant_pool Array of constants    * @throws IOException    */
name|InnerClasses
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
name|InnerClass
index|[]
operator|)
literal|null
argument_list|,
name|constant_pool
argument_list|)
expr_stmt|;
name|number_of_classes
operator|=
name|file
operator|.
name|readUnsignedShort
argument_list|()
expr_stmt|;
name|inner_classes
operator|=
operator|new
name|InnerClass
index|[
name|number_of_classes
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
name|number_of_classes
condition|;
name|i
operator|++
control|)
name|inner_classes
index|[
name|i
index|]
operator|=
operator|new
name|InnerClass
argument_list|(
name|file
argument_list|)
expr_stmt|;
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
name|visitInnerClasses
argument_list|(
name|this
argument_list|)
expr_stmt|;
block|}
comment|/**    * Dump source file attribute to file stream in binary format.    *    * @param file Output file stream    * @throws IOException    */
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
name|file
operator|.
name|writeShort
argument_list|(
name|number_of_classes
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
name|number_of_classes
condition|;
name|i
operator|++
control|)
name|inner_classes
index|[
name|i
index|]
operator|.
name|dump
argument_list|(
name|file
argument_list|)
expr_stmt|;
block|}
comment|/**    * @return array of inner class "records"    */
specifier|public
specifier|final
name|InnerClass
index|[]
name|getInnerClasses
parameter_list|()
block|{
return|return
name|inner_classes
return|;
block|}
comment|/**    * @param inner_classes.    */
specifier|public
specifier|final
name|void
name|setInnerClasses
parameter_list|(
name|InnerClass
index|[]
name|inner_classes
parameter_list|)
block|{
name|this
operator|.
name|inner_classes
operator|=
name|inner_classes
expr_stmt|;
name|number_of_classes
operator|=
operator|(
name|inner_classes
operator|==
literal|null
operator|)
condition|?
literal|0
else|:
name|inner_classes
operator|.
name|length
expr_stmt|;
block|}
comment|/**    * @return String representation.    */
specifier|public
specifier|final
name|String
name|toString
parameter_list|()
block|{
name|StringBuffer
name|buf
init|=
operator|new
name|StringBuffer
argument_list|()
decl_stmt|;
for|for
control|(
name|int
name|i
init|=
literal|0
init|;
name|i
operator|<
name|number_of_classes
condition|;
name|i
operator|++
control|)
name|buf
operator|.
name|append
argument_list|(
name|inner_classes
index|[
name|i
index|]
operator|.
name|toString
argument_list|(
name|constant_pool
argument_list|)
operator|+
literal|"\n"
argument_list|)
expr_stmt|;
return|return
name|buf
operator|.
name|toString
argument_list|()
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
name|InnerClasses
name|c
init|=
operator|(
name|InnerClasses
operator|)
name|clone
argument_list|()
decl_stmt|;
name|c
operator|.
name|inner_classes
operator|=
operator|new
name|InnerClass
index|[
name|number_of_classes
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
name|number_of_classes
condition|;
name|i
operator|++
control|)
name|c
operator|.
name|inner_classes
index|[
name|i
index|]
operator|=
name|inner_classes
index|[
name|i
index|]
operator|.
name|copy
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

