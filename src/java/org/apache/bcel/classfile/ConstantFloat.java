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
comment|/**   * This class is derived from the abstract   *<A HREF="org.apache.bcel.classfile.Constant.html">Constant</A> class   * and represents a reference to a float object.  *  * @version $Id$  * @author<A HREF="mailto:m.dahm@gmx.de">M. Dahm</A>  * @see     Constant  */
end_comment

begin_class
specifier|public
specifier|final
class|class
name|ConstantFloat
extends|extends
name|Constant
implements|implements
name|ConstantObject
block|{
specifier|private
name|float
name|bytes
decl_stmt|;
comment|/**     * @param bytes Data    */
specifier|public
name|ConstantFloat
parameter_list|(
name|float
name|bytes
parameter_list|)
block|{
name|super
argument_list|(
name|Constants
operator|.
name|CONSTANT_Float
argument_list|)
expr_stmt|;
name|this
operator|.
name|bytes
operator|=
name|bytes
expr_stmt|;
block|}
comment|/**    * Initialize from another object. Note that both objects use the same    * references (shallow copy). Use clone() for a physical copy.    */
specifier|public
name|ConstantFloat
parameter_list|(
name|ConstantFloat
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
comment|/**     * Initialize instance from file data.    *    * @param file Input stream    * @throws IOException    */
name|ConstantFloat
parameter_list|(
name|DataInputStream
name|file
parameter_list|)
throws|throws
name|IOException
block|{
name|this
argument_list|(
name|file
operator|.
name|readFloat
argument_list|()
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
name|visitConstantFloat
argument_list|(
name|this
argument_list|)
expr_stmt|;
block|}
comment|/**    * Dump constant float to file stream in binary format.    *    * @param file Output file stream    * @throws IOException    */
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
name|writeFloat
argument_list|(
name|bytes
argument_list|)
expr_stmt|;
block|}
comment|/**    * @return data, i.e., 4 bytes.    */
specifier|public
specifier|final
name|float
name|getBytes
parameter_list|()
block|{
return|return
name|bytes
return|;
block|}
comment|/**    * @param bytes.    */
specifier|public
specifier|final
name|void
name|setBytes
parameter_list|(
name|float
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
return|return
name|super
operator|.
name|toString
argument_list|()
operator|+
literal|"(bytes = "
operator|+
name|bytes
operator|+
literal|")"
return|;
block|}
comment|/** @return Float object    */
specifier|public
name|Object
name|getConstantValue
parameter_list|(
name|ConstantPool
name|cp
parameter_list|)
block|{
return|return
operator|new
name|Float
argument_list|(
name|bytes
argument_list|)
return|;
block|}
block|}
end_class

end_unit

