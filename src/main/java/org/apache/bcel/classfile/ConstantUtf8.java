begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
begin_comment
comment|/*  * Copyright  2000-2009 The Apache Software Foundation  *  *  Licensed under the Apache License, Version 2.0 (the "License");   *  you may not use this file except in compliance with the License.  *  You may obtain a copy of the License at  *  *      http://www.apache.org/licenses/LICENSE-2.0  *  *  Unless required by applicable law or agreed to in writing, software  *  distributed under the License is distributed on an "AS IS" BASIS,  *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  *  See the License for the specific language governing permissions and  *  limitations under the License.   *  */
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
name|Constants
import|;
end_import

begin_comment
comment|/**   * This class is derived from the abstract   *<A HREF="org.apache.bcel.classfile.Constant.html">Constant</A> class   * and represents a reference to a Utf8 encoded string.  *  * @version $Id$  * @author<A HREF="mailto:m.dahm@gmx.de">M. Dahm</A>  * @see     Constant  */
end_comment

begin_class
specifier|public
specifier|final
class|class
name|ConstantUtf8
extends|extends
name|Constant
block|{
specifier|private
name|String
name|bytes
decl_stmt|;
comment|/**      * Initialize from another object.      */
specifier|public
name|ConstantUtf8
parameter_list|(
name|ConstantUtf8
name|c
parameter_list|)
block|{
name|this
argument_list|(
name|c
operator|.
name|getBytes
argument_list|()
argument_list|)
expr_stmt|;
block|}
comment|/**      * Initialize instance from file data.      *      * @param file Input stream      * @throws IOException      */
name|ConstantUtf8
parameter_list|(
name|DataInput
name|file
parameter_list|)
throws|throws
name|IOException
block|{
name|super
argument_list|(
name|Constants
operator|.
name|CONSTANT_Utf8
argument_list|)
expr_stmt|;
name|bytes
operator|=
name|file
operator|.
name|readUTF
argument_list|()
expr_stmt|;
block|}
comment|/**      * @param bytes Data      */
specifier|public
name|ConstantUtf8
parameter_list|(
name|String
name|bytes
parameter_list|)
block|{
name|super
argument_list|(
name|Constants
operator|.
name|CONSTANT_Utf8
argument_list|)
expr_stmt|;
if|if
condition|(
name|bytes
operator|==
literal|null
condition|)
block|{
throw|throw
operator|new
name|IllegalArgumentException
argument_list|(
literal|"bytes must not be null!"
argument_list|)
throw|;
block|}
name|this
operator|.
name|bytes
operator|=
name|bytes
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
name|visitConstantUtf8
argument_list|(
name|this
argument_list|)
expr_stmt|;
block|}
comment|/**      * Dump String in Utf8 format to file stream.      *      * @param file Output file stream      * @throws IOException      */
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
name|writeUTF
argument_list|(
name|bytes
argument_list|)
expr_stmt|;
block|}
comment|/**      * @return Data converted to string.      */
specifier|public
specifier|final
name|String
name|getBytes
parameter_list|()
block|{
return|return
name|bytes
return|;
block|}
comment|/**      * @param bytes the raw bytes of this Utf-8      */
specifier|public
specifier|final
name|void
name|setBytes
parameter_list|(
name|String
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
comment|/**      * @return String representation      */
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
literal|"(\""
operator|+
name|Utility
operator|.
name|replace
argument_list|(
name|bytes
argument_list|,
literal|"\n"
argument_list|,
literal|"\\n"
argument_list|)
operator|+
literal|"\")"
return|;
block|}
block|}
end_class

end_unit

