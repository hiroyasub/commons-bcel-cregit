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
comment|/**  * This class represents a (PC offset, line number) pair, i.e., a line number in  * the source that corresponds to a relative address in the byte code. This  * is used for debugging purposes.  *  * @version $Id$  * @author<A HREF="mailto:markus.dahm@berlin.de">M. Dahm</A>  * @see     LineNumberTable  */
end_comment

begin_class
specifier|public
specifier|final
class|class
name|LineNumber
implements|implements
name|Cloneable
implements|,
name|Node
block|{
specifier|private
name|int
name|start_pc
decl_stmt|;
comment|// Program Counter (PC) corresponds to line
specifier|private
name|int
name|line_number
decl_stmt|;
comment|// number in source file
comment|/**    * Initialize from another object.    */
specifier|public
name|LineNumber
parameter_list|(
name|LineNumber
name|c
parameter_list|)
block|{
name|this
argument_list|(
name|c
operator|.
name|getStartPC
argument_list|()
argument_list|,
name|c
operator|.
name|getLineNumber
argument_list|()
argument_list|)
expr_stmt|;
block|}
comment|/**    * Construct object from file stream.    * @param file Input stream    * @throws IOException    */
name|LineNumber
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
name|readUnsignedShort
argument_list|()
argument_list|,
name|file
operator|.
name|readUnsignedShort
argument_list|()
argument_list|)
expr_stmt|;
block|}
comment|/**    * @param start_pc Program Counter (PC) corresponds to    * @param line_number line number in source file    */
specifier|public
name|LineNumber
parameter_list|(
name|int
name|start_pc
parameter_list|,
name|int
name|line_number
parameter_list|)
block|{
name|this
operator|.
name|start_pc
operator|=
name|start_pc
expr_stmt|;
name|this
operator|.
name|line_number
operator|=
name|line_number
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
name|visitLineNumber
argument_list|(
name|this
argument_list|)
expr_stmt|;
block|}
comment|/**    * Dump line number/pc pair to file stream in binary format.    *    * @param file Output file stream    * @throws IOException    */
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
name|start_pc
argument_list|)
expr_stmt|;
name|file
operator|.
name|writeShort
argument_list|(
name|line_number
argument_list|)
expr_stmt|;
block|}
comment|/**    * @return Corresponding source line    */
specifier|public
specifier|final
name|int
name|getLineNumber
parameter_list|()
block|{
return|return
name|line_number
return|;
block|}
comment|/**    * @return PC in code    */
specifier|public
specifier|final
name|int
name|getStartPC
parameter_list|()
block|{
return|return
name|start_pc
return|;
block|}
comment|/**    * @param line_number.    */
specifier|public
specifier|final
name|void
name|setLineNumber
parameter_list|(
name|int
name|line_number
parameter_list|)
block|{
name|this
operator|.
name|line_number
operator|=
name|line_number
expr_stmt|;
block|}
comment|/**    * @param start_pc.    */
specifier|public
specifier|final
name|void
name|setStartPC
parameter_list|(
name|int
name|start_pc
parameter_list|)
block|{
name|this
operator|.
name|start_pc
operator|=
name|start_pc
expr_stmt|;
block|}
comment|/**    * @return String representation    */
specifier|public
specifier|final
name|String
name|toString
parameter_list|()
block|{
return|return
literal|"LineNumber("
operator|+
name|start_pc
operator|+
literal|", "
operator|+
name|line_number
operator|+
literal|")"
return|;
block|}
comment|/**    * @return deep copy of this object    */
specifier|public
name|LineNumber
name|copy
parameter_list|()
block|{
try|try
block|{
return|return
operator|(
name|LineNumber
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
block|}
end_class

end_unit

