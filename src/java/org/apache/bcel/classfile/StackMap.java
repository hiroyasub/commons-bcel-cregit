begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
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

begin_comment
comment|/* ====================================================================  * The Apache Software License, Version 1.1  *  * Copyright (c) 2001 The Apache Software Foundation.  All rights  * reserved.  *  * Redistribution and use in source and binary forms, with or without  * modification, are permitted provided that the following conditions  * are met:  *  * 1. Redistributions of source code must retain the above copyright  *    notice, this list of conditions and the following disclaimer.  *  * 2. Redistributions in binary form must reproduce the above copyright  *    notice, this list of conditions and the following disclaimer in  *    the documentation and/or other materials provided with the  *    distribution.  *  * 3. The end-user documentation included with the redistribution,  *    if any, must include the following acknowledgment:  *       "This product includes software developed by the  *        Apache Software Foundation (http://www.apache.org/)."  *    Alternately, this acknowledgment may appear in the software itself,  *    if and wherever such third-party acknowledgments normally appear.  *  * 4. The names "Apache" and "Apache Software Foundation" and  *    "Apache BCEL" must not be used to endorse or promote products  *    derived from this software without prior written permission. For  *    written permission, please contact apache@apache.org.  *  * 5. Products derived from this software may not be called "Apache",  *    "Apache BCEL", nor may "Apache" appear in their name, without  *    prior written permission of the Apache Software Foundation.  *  * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED  * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES  * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE  * DISCLAIMED.  IN NO EVENT SHALL THE APACHE SOFTWARE FOUNDATION OR  * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,  * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT  * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF  * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND  * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,  * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT  * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF  * SUCH DAMAGE.  * ====================================================================  *  * This software consists of voluntary contributions made by many  * individuals on behalf of the Apache Software Foundation.  For more  * information on the Apache Software Foundation, please see  *<http://www.apache.org/>.  */
end_comment

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
comment|/**  * This class represents a stack map attribute used for  * preverification of Java classes for the<a  * href="http://java.sun.com/j2me/"> Java 2 Micro Edition</a>  * (J2ME). This attribute is used by the<a  * href="http://java.sun.com/products/cldc/">KVM</a> and contained  * within the Code attribute of a method. See CLDC specification  * §5.3.1.2  *  * @version $Id$  * @author<A HREF="mailto:markus.dahm@berlin.de">M. Dahm</A>  * @see     Code  * @see     StackMapEntry  * @see     StackMapType  */
end_comment

begin_class
specifier|public
specifier|final
class|class
name|StackMap
extends|extends
name|Attribute
implements|implements
name|Node
block|{
specifier|private
name|int
name|map_length
decl_stmt|;
specifier|private
name|StackMapEntry
index|[]
name|map
decl_stmt|;
comment|// Table of stack map entries
comment|/*    * @param name_index Index of name    * @param length Content length in bytes    * @param map Table of stack map entries    * @param constant_pool Array of constants    */
specifier|public
name|StackMap
parameter_list|(
name|int
name|name_index
parameter_list|,
name|int
name|length
parameter_list|,
name|StackMapEntry
index|[]
name|map
parameter_list|,
name|ConstantPool
name|constant_pool
parameter_list|)
block|{
name|super
argument_list|(
name|Constants
operator|.
name|ATTR_STACK_MAP
argument_list|,
name|name_index
argument_list|,
name|length
argument_list|,
name|constant_pool
argument_list|)
expr_stmt|;
name|setStackMap
argument_list|(
name|map
argument_list|)
expr_stmt|;
block|}
comment|/**    * Construct object from file stream.    * @param name_index Index of name    * @param length Content length in bytes    * @param file Input stream    * @throws IOException    * @param constant_pool Array of constants    */
name|StackMap
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
name|StackMapEntry
index|[]
operator|)
literal|null
argument_list|,
name|constant_pool
argument_list|)
expr_stmt|;
name|map_length
operator|=
name|file
operator|.
name|readUnsignedShort
argument_list|()
expr_stmt|;
name|map
operator|=
operator|new
name|StackMapEntry
index|[
name|map_length
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
name|map_length
condition|;
name|i
operator|++
control|)
name|map
index|[
name|i
index|]
operator|=
operator|new
name|StackMapEntry
argument_list|(
name|file
argument_list|,
name|constant_pool
argument_list|)
expr_stmt|;
block|}
comment|/**    * Dump line number table attribute to file stream in binary format.    *    * @param file Output file stream    * @throws IOException    */
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
name|map_length
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
name|map_length
condition|;
name|i
operator|++
control|)
name|map
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
comment|/**    * @return Array of stack map entries    */
specifier|public
specifier|final
name|StackMapEntry
index|[]
name|getStackMap
parameter_list|()
block|{
return|return
name|map
return|;
block|}
comment|/**    * @param map Array of stack map entries    */
specifier|public
specifier|final
name|void
name|setStackMap
parameter_list|(
name|StackMapEntry
index|[]
name|map
parameter_list|)
block|{
name|this
operator|.
name|map
operator|=
name|map
expr_stmt|;
name|map_length
operator|=
operator|(
name|map
operator|==
literal|null
operator|)
condition|?
literal|0
else|:
name|map
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
argument_list|(
literal|"StackMap("
argument_list|)
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
name|map_length
condition|;
name|i
operator|++
control|)
block|{
name|buf
operator|.
name|append
argument_list|(
name|map
index|[
name|i
index|]
operator|.
name|toString
argument_list|()
argument_list|)
expr_stmt|;
if|if
condition|(
name|i
operator|<
name|map_length
operator|-
literal|1
condition|)
name|buf
operator|.
name|append
argument_list|(
literal|", "
argument_list|)
expr_stmt|;
block|}
name|buf
operator|.
name|append
argument_list|(
literal|')'
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
name|StackMap
name|c
init|=
operator|(
name|StackMap
operator|)
name|clone
argument_list|()
decl_stmt|;
name|c
operator|.
name|map
operator|=
operator|new
name|StackMapEntry
index|[
name|map_length
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
name|map_length
condition|;
name|i
operator|++
control|)
name|c
operator|.
name|map
index|[
name|i
index|]
operator|=
name|map
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
name|visitStackMap
argument_list|(
name|this
argument_list|)
expr_stmt|;
block|}
specifier|public
specifier|final
name|int
name|getMapLength
parameter_list|()
block|{
return|return
name|map_length
return|;
block|}
block|}
end_class

end_unit

