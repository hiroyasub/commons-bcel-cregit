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

begin_comment
comment|/**  * This class represents a stack map entry recording the types of  * local variables and the the of stack items at a given byte code offset.  * See CLDC specification §5.3.1.2  *  * @version $Id$  * @author<A HREF="mailto:m.dahm@gmx.de">M. Dahm</A>  * @see     StackMap  * @see     StackMapType  */
end_comment

begin_class
specifier|public
specifier|final
class|class
name|StackMapEntry
implements|implements
name|Cloneable
block|{
specifier|private
name|int
name|byte_code_offset
decl_stmt|;
specifier|private
name|int
name|number_of_locals
decl_stmt|;
specifier|private
name|StackMapType
index|[]
name|types_of_locals
decl_stmt|;
specifier|private
name|int
name|number_of_stack_items
decl_stmt|;
specifier|private
name|StackMapType
index|[]
name|types_of_stack_items
decl_stmt|;
specifier|private
name|ConstantPool
name|constant_pool
decl_stmt|;
comment|/**    * Construct object from file stream.    * @param file Input stream    * @throws IOException    */
name|StackMapEntry
parameter_list|(
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
name|file
operator|.
name|readShort
argument_list|()
argument_list|,
name|file
operator|.
name|readShort
argument_list|()
argument_list|,
literal|null
argument_list|,
operator|-
literal|1
argument_list|,
literal|null
argument_list|,
name|constant_pool
argument_list|)
expr_stmt|;
name|types_of_locals
operator|=
operator|new
name|StackMapType
index|[
name|number_of_locals
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
name|number_of_locals
condition|;
name|i
operator|++
control|)
name|types_of_locals
index|[
name|i
index|]
operator|=
operator|new
name|StackMapType
argument_list|(
name|file
argument_list|,
name|constant_pool
argument_list|)
expr_stmt|;
name|number_of_stack_items
operator|=
name|file
operator|.
name|readShort
argument_list|()
expr_stmt|;
name|types_of_stack_items
operator|=
operator|new
name|StackMapType
index|[
name|number_of_stack_items
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
name|number_of_stack_items
condition|;
name|i
operator|++
control|)
name|types_of_stack_items
index|[
name|i
index|]
operator|=
operator|new
name|StackMapType
argument_list|(
name|file
argument_list|,
name|constant_pool
argument_list|)
expr_stmt|;
block|}
specifier|public
name|StackMapEntry
parameter_list|(
name|int
name|byte_code_offset
parameter_list|,
name|int
name|number_of_locals
parameter_list|,
name|StackMapType
index|[]
name|types_of_locals
parameter_list|,
name|int
name|number_of_stack_items
parameter_list|,
name|StackMapType
index|[]
name|types_of_stack_items
parameter_list|,
name|ConstantPool
name|constant_pool
parameter_list|)
block|{
name|this
operator|.
name|byte_code_offset
operator|=
name|byte_code_offset
expr_stmt|;
name|this
operator|.
name|number_of_locals
operator|=
name|number_of_locals
expr_stmt|;
name|this
operator|.
name|types_of_locals
operator|=
name|types_of_locals
expr_stmt|;
name|this
operator|.
name|number_of_stack_items
operator|=
name|number_of_stack_items
expr_stmt|;
name|this
operator|.
name|types_of_stack_items
operator|=
name|types_of_stack_items
expr_stmt|;
name|this
operator|.
name|constant_pool
operator|=
name|constant_pool
expr_stmt|;
block|}
comment|/**    * Dump stack map entry    *    * @param file Output file stream    * @throws IOException    */
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
name|byte_code_offset
argument_list|)
expr_stmt|;
name|file
operator|.
name|writeShort
argument_list|(
name|number_of_locals
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
name|number_of_locals
condition|;
name|i
operator|++
control|)
name|types_of_locals
index|[
name|i
index|]
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
name|number_of_stack_items
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
name|number_of_stack_items
condition|;
name|i
operator|++
control|)
name|types_of_stack_items
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
literal|"(offset="
operator|+
name|byte_code_offset
argument_list|)
decl_stmt|;
if|if
condition|(
name|number_of_locals
operator|>
literal|0
condition|)
block|{
name|buf
operator|.
name|append
argument_list|(
literal|", locals={"
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
name|number_of_locals
condition|;
name|i
operator|++
control|)
block|{
name|buf
operator|.
name|append
argument_list|(
name|types_of_locals
index|[
name|i
index|]
argument_list|)
expr_stmt|;
if|if
condition|(
name|i
operator|<
name|number_of_locals
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
literal|"}"
argument_list|)
expr_stmt|;
block|}
if|if
condition|(
name|number_of_stack_items
operator|>
literal|0
condition|)
block|{
name|buf
operator|.
name|append
argument_list|(
literal|", stack items={"
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
name|number_of_stack_items
condition|;
name|i
operator|++
control|)
block|{
name|buf
operator|.
name|append
argument_list|(
name|types_of_stack_items
index|[
name|i
index|]
argument_list|)
expr_stmt|;
if|if
condition|(
name|i
operator|<
name|number_of_stack_items
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
literal|"}"
argument_list|)
expr_stmt|;
block|}
name|buf
operator|.
name|append
argument_list|(
literal|")"
argument_list|)
expr_stmt|;
return|return
name|buf
operator|.
name|toString
argument_list|()
return|;
block|}
specifier|public
name|void
name|setByteCodeOffset
parameter_list|(
name|int
name|b
parameter_list|)
block|{
name|byte_code_offset
operator|=
name|b
expr_stmt|;
block|}
specifier|public
name|int
name|getByteCodeOffset
parameter_list|()
block|{
return|return
name|byte_code_offset
return|;
block|}
specifier|public
name|void
name|setNumberOfLocals
parameter_list|(
name|int
name|n
parameter_list|)
block|{
name|number_of_locals
operator|=
name|n
expr_stmt|;
block|}
specifier|public
name|int
name|getNumberOfLocals
parameter_list|()
block|{
return|return
name|number_of_locals
return|;
block|}
specifier|public
name|void
name|setTypesOfLocals
parameter_list|(
name|StackMapType
index|[]
name|t
parameter_list|)
block|{
name|types_of_locals
operator|=
name|t
expr_stmt|;
block|}
specifier|public
name|StackMapType
index|[]
name|getTypesOfLocals
parameter_list|()
block|{
return|return
name|types_of_locals
return|;
block|}
specifier|public
name|void
name|setNumberOfStackItems
parameter_list|(
name|int
name|n
parameter_list|)
block|{
name|number_of_stack_items
operator|=
name|n
expr_stmt|;
block|}
specifier|public
name|int
name|getNumberOfStackItems
parameter_list|()
block|{
return|return
name|number_of_stack_items
return|;
block|}
specifier|public
name|void
name|setTypesOfStackItems
parameter_list|(
name|StackMapType
index|[]
name|t
parameter_list|)
block|{
name|types_of_stack_items
operator|=
name|t
expr_stmt|;
block|}
specifier|public
name|StackMapType
index|[]
name|getTypesOfStackItems
parameter_list|()
block|{
return|return
name|types_of_stack_items
return|;
block|}
comment|/**    * @return deep copy of this object    */
specifier|public
name|StackMapEntry
name|copy
parameter_list|()
block|{
try|try
block|{
return|return
operator|(
name|StackMapEntry
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
name|visitStackMapEntry
argument_list|(
name|this
argument_list|)
expr_stmt|;
block|}
comment|/**    * @return Constant pool used by this object.    */
specifier|public
specifier|final
name|ConstantPool
name|getConstantPool
parameter_list|()
block|{
return|return
name|constant_pool
return|;
block|}
comment|/**    * @param constant_pool Constant pool to be used for this object.    */
specifier|public
specifier|final
name|void
name|setConstantPool
parameter_list|(
name|ConstantPool
name|constant_pool
parameter_list|)
block|{
name|this
operator|.
name|constant_pool
operator|=
name|constant_pool
expr_stmt|;
block|}
block|}
end_class

end_unit

