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
comment|/**  * This class represents the method info structure, i.e., the representation   * for a method in the class. See JVM specification for details.  * A method has access flags, a name, a signature and a number of attributes.  *  * @version $Id$  * @author<A HREF="mailto:markus.dahm@berlin.de">M. Dahm</A>  */
end_comment

begin_class
specifier|public
specifier|final
class|class
name|Method
extends|extends
name|FieldOrMethod
block|{
comment|/**    * Empty constructor, all attributes have to be defined via `setXXX'    * methods. Use at your own risk.    */
specifier|public
name|Method
parameter_list|()
block|{
block|}
comment|/**    * Initialize from another object. Note that both objects use the same    * references (shallow copy). Use clone() for a physical copy.    */
specifier|public
name|Method
parameter_list|(
name|Method
name|c
parameter_list|)
block|{
name|super
argument_list|(
name|c
argument_list|)
expr_stmt|;
block|}
comment|/**    * Construct object from file stream.    * @param file Input stream    * @throws IOException    * @throws ClassFormatException    */
name|Method
parameter_list|(
name|DataInputStream
name|file
parameter_list|,
name|ConstantPool
name|constant_pool
parameter_list|)
throws|throws
name|IOException
throws|,
name|ClassFormatException
block|{
name|super
argument_list|(
name|file
argument_list|,
name|constant_pool
argument_list|)
expr_stmt|;
block|}
comment|/**    * @param access_flags Access rights of method    * @param name_index Points to field name in constant pool    * @param signature_index Points to encoded signature    * @param attributes Collection of attributes    * @param constant_pool Array of constants    */
specifier|public
name|Method
parameter_list|(
name|int
name|access_flags
parameter_list|,
name|int
name|name_index
parameter_list|,
name|int
name|signature_index
parameter_list|,
name|Attribute
index|[]
name|attributes
parameter_list|,
name|ConstantPool
name|constant_pool
parameter_list|)
block|{
name|super
argument_list|(
name|access_flags
argument_list|,
name|name_index
argument_list|,
name|signature_index
argument_list|,
name|attributes
argument_list|,
name|constant_pool
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
name|visitMethod
argument_list|(
name|this
argument_list|)
expr_stmt|;
block|}
comment|/**    * @return Code attribute of method, if any    */
specifier|public
specifier|final
name|Code
name|getCode
parameter_list|()
block|{
for|for
control|(
name|int
name|i
init|=
literal|0
init|;
name|i
operator|<
name|attributes_count
condition|;
name|i
operator|++
control|)
if|if
condition|(
name|attributes
index|[
name|i
index|]
operator|instanceof
name|Code
condition|)
return|return
operator|(
name|Code
operator|)
name|attributes
index|[
name|i
index|]
return|;
return|return
literal|null
return|;
block|}
comment|/**    * @return ExceptionTable attribute of method, if any, i.e., list all    * exceptions the method may throw not exception handlers!    */
specifier|public
specifier|final
name|ExceptionTable
name|getExceptionTable
parameter_list|()
block|{
for|for
control|(
name|int
name|i
init|=
literal|0
init|;
name|i
operator|<
name|attributes_count
condition|;
name|i
operator|++
control|)
if|if
condition|(
name|attributes
index|[
name|i
index|]
operator|instanceof
name|ExceptionTable
condition|)
return|return
operator|(
name|ExceptionTable
operator|)
name|attributes
index|[
name|i
index|]
return|;
return|return
literal|null
return|;
block|}
comment|/** @return LocalVariableTable of code attribute if any, i.e. the call is forwarded    * to the Code atribute.    */
specifier|public
specifier|final
name|LocalVariableTable
name|getLocalVariableTable
parameter_list|()
block|{
name|Code
name|code
init|=
name|getCode
argument_list|()
decl_stmt|;
if|if
condition|(
name|code
operator|!=
literal|null
condition|)
return|return
name|code
operator|.
name|getLocalVariableTable
argument_list|()
return|;
else|else
return|return
literal|null
return|;
block|}
comment|/** @return LineNumberTable of code attribute if any, i.e. the call is forwarded    * to the Code atribute.    */
specifier|public
specifier|final
name|LineNumberTable
name|getLineNumberTable
parameter_list|()
block|{
name|Code
name|code
init|=
name|getCode
argument_list|()
decl_stmt|;
if|if
condition|(
name|code
operator|!=
literal|null
condition|)
return|return
name|code
operator|.
name|getLineNumberTable
argument_list|()
return|;
else|else
return|return
literal|null
return|;
block|}
comment|/**    * Return string representation close to declaration format,    * `public static void main(String[] args) throws IOException', e.g.    *    * @return String representation of the method.    */
specifier|public
specifier|final
name|String
name|toString
parameter_list|()
block|{
name|ConstantUtf8
name|c
decl_stmt|;
name|String
name|name
decl_stmt|,
name|signature
decl_stmt|,
name|access
decl_stmt|;
comment|// Short cuts to constant pool
name|StringBuffer
name|buf
decl_stmt|;
name|access
operator|=
name|Utility
operator|.
name|accessToString
argument_list|(
name|access_flags
argument_list|)
expr_stmt|;
comment|// Get name and signature from constant pool
name|c
operator|=
operator|(
name|ConstantUtf8
operator|)
name|constant_pool
operator|.
name|getConstant
argument_list|(
name|signature_index
argument_list|,
name|Constants
operator|.
name|CONSTANT_Utf8
argument_list|)
expr_stmt|;
name|signature
operator|=
name|c
operator|.
name|getBytes
argument_list|()
expr_stmt|;
name|c
operator|=
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
expr_stmt|;
name|name
operator|=
name|c
operator|.
name|getBytes
argument_list|()
expr_stmt|;
name|signature
operator|=
name|Utility
operator|.
name|methodSignatureToString
argument_list|(
name|signature
argument_list|,
name|name
argument_list|,
name|access
argument_list|,
literal|true
argument_list|,
name|getLocalVariableTable
argument_list|()
argument_list|)
expr_stmt|;
name|buf
operator|=
operator|new
name|StringBuffer
argument_list|(
name|signature
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
name|attributes_count
condition|;
name|i
operator|++
control|)
block|{
name|Attribute
name|a
init|=
name|attributes
index|[
name|i
index|]
decl_stmt|;
if|if
condition|(
operator|!
operator|(
operator|(
name|a
operator|instanceof
name|Code
operator|)
operator|||
operator|(
name|a
operator|instanceof
name|ExceptionTable
operator|)
operator|)
condition|)
name|buf
operator|.
name|append
argument_list|(
literal|" ["
operator|+
name|a
operator|.
name|toString
argument_list|()
operator|+
literal|"]"
argument_list|)
expr_stmt|;
block|}
name|ExceptionTable
name|e
init|=
name|getExceptionTable
argument_list|()
decl_stmt|;
if|if
condition|(
name|e
operator|!=
literal|null
condition|)
block|{
name|String
name|str
init|=
name|e
operator|.
name|toString
argument_list|()
decl_stmt|;
if|if
condition|(
operator|!
name|str
operator|.
name|equals
argument_list|(
literal|""
argument_list|)
condition|)
name|buf
operator|.
name|append
argument_list|(
literal|"\n\t\tthrows "
operator|+
name|str
argument_list|)
expr_stmt|;
block|}
return|return
name|buf
operator|.
name|toString
argument_list|()
return|;
block|}
comment|/**    * @return deep copy of this method    */
specifier|public
specifier|final
name|Method
name|copy
parameter_list|(
name|ConstantPool
name|constant_pool
parameter_list|)
block|{
return|return
operator|(
name|Method
operator|)
name|copy_
argument_list|(
name|constant_pool
argument_list|)
return|;
block|}
block|}
end_class

end_unit

