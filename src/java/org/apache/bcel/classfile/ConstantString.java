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
comment|/**   * This class is derived from the abstract   *<A HREF="org.apache.bcel.classfile.Constant.html">Constant</A> class   * and represents a reference to a String object.  *  * @version $Id$  * @author<A HREF="mailto:m.dahm@gmx.de">M. Dahm</A>  * @see     Constant  */
end_comment

begin_class
specifier|public
specifier|final
class|class
name|ConstantString
extends|extends
name|Constant
implements|implements
name|ConstantObject
block|{
specifier|private
name|int
name|string_index
decl_stmt|;
comment|// Identical to ConstantClass except for this name
comment|/**    * Initialize from another object.    */
specifier|public
name|ConstantString
parameter_list|(
name|ConstantString
name|c
parameter_list|)
block|{
name|this
argument_list|(
name|c
operator|.
name|getStringIndex
argument_list|()
argument_list|)
expr_stmt|;
block|}
comment|/**     * Initialize instance from file data.    *    * @param file Input stream    * @throws IOException    */
name|ConstantString
parameter_list|(
name|DataInputStream
name|file
parameter_list|)
throws|throws
name|IOException
block|{
name|this
argument_list|(
operator|(
name|int
operator|)
name|file
operator|.
name|readUnsignedShort
argument_list|()
argument_list|)
expr_stmt|;
block|}
comment|/**    * @param string_index Index of Constant_Utf8 in constant pool    */
specifier|public
name|ConstantString
parameter_list|(
name|int
name|string_index
parameter_list|)
block|{
name|super
argument_list|(
name|Constants
operator|.
name|CONSTANT_String
argument_list|)
expr_stmt|;
name|this
operator|.
name|string_index
operator|=
name|string_index
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
name|visitConstantString
argument_list|(
name|this
argument_list|)
expr_stmt|;
block|}
comment|/**    * Dump constant field reference to file stream in binary format.    *    * @param file Output file stream    * @throws IOException    */
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
name|writeByte
argument_list|(
name|tag
argument_list|)
expr_stmt|;
name|file
operator|.
name|writeShort
argument_list|(
name|string_index
argument_list|)
expr_stmt|;
block|}
comment|/**    * @return Index in constant pool of the string (ConstantUtf8).    */
specifier|public
specifier|final
name|int
name|getStringIndex
parameter_list|()
block|{
return|return
name|string_index
return|;
block|}
comment|/**    * @param string_index.    */
specifier|public
specifier|final
name|void
name|setStringIndex
parameter_list|(
name|int
name|string_index
parameter_list|)
block|{
name|this
operator|.
name|string_index
operator|=
name|string_index
expr_stmt|;
block|}
comment|/**    * @return String representation.    */
specifier|public
specifier|final
name|String
name|toString
parameter_list|()
block|{
return|return
name|super
operator|.
name|toString
argument_list|()
operator|+
literal|"(string_index = "
operator|+
name|string_index
operator|+
literal|")"
return|;
block|}
comment|/** @return String object    */
specifier|public
name|Object
name|getConstantValue
parameter_list|(
name|ConstantPool
name|cp
parameter_list|)
block|{
name|Constant
name|c
init|=
name|cp
operator|.
name|getConstant
argument_list|(
name|string_index
argument_list|,
name|Constants
operator|.
name|CONSTANT_Utf8
argument_list|)
decl_stmt|;
return|return
operator|(
operator|(
name|ConstantUtf8
operator|)
name|c
operator|)
operator|.
name|getBytes
argument_list|()
return|;
block|}
comment|/** @return dereferenced string    */
specifier|public
name|String
name|getBytes
parameter_list|(
name|ConstantPool
name|cp
parameter_list|)
block|{
return|return
operator|(
name|String
operator|)
name|getConstantValue
argument_list|(
name|cp
argument_list|)
return|;
block|}
block|}
end_class

end_unit

