begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
begin_package
package|package
name|org
operator|.
name|apache
operator|.
name|bcel
operator|.
name|util
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
name|classfile
operator|.
name|JavaClass
import|;
end_import

begin_comment
comment|/**  * Abstract definition of a class repository. Instances may be used  * to load classes from different sources and may be used in the  * Repository.setRpeository method.  *  * @see org.apache.bcel.Repository  * @version $Id$  * @author<A HREF="mailto:markus.dahm@berlin.de">M. Dahm</A>  * @author David Dixon-Peugh  */
end_comment

begin_interface
specifier|public
interface|interface
name|Repository
extends|extends
name|java
operator|.
name|io
operator|.
name|Serializable
block|{
comment|/**    * Store the provided class under "clazz.getClassName()"     */
specifier|public
name|void
name|storeClass
parameter_list|(
name|JavaClass
name|clazz
parameter_list|)
function_decl|;
comment|/**    * Remove class from repository    */
specifier|public
name|void
name|removeClass
parameter_list|(
name|JavaClass
name|clazz
parameter_list|)
function_decl|;
comment|/**    * Find the class with the name provided, if the class    * isn't there, return NULL.    */
specifier|public
name|JavaClass
name|findClass
parameter_list|(
name|String
name|className
parameter_list|)
function_decl|;
comment|/**    * Find the class with the name provided, if the class    * isn't there, make an attempt to load it.    */
specifier|public
name|JavaClass
name|loadClass
parameter_list|(
name|String
name|className
parameter_list|)
throws|throws
name|java
operator|.
name|lang
operator|.
name|ClassNotFoundException
function_decl|;
comment|/**    * Find the JavaClass instance for the given run-time class object    */
specifier|public
name|JavaClass
name|loadClass
parameter_list|(
name|Class
name|clazz
parameter_list|)
throws|throws
name|java
operator|.
name|lang
operator|.
name|ClassNotFoundException
function_decl|;
comment|/** Clear all entries from cache.    */
specifier|public
name|void
name|clear
parameter_list|()
function_decl|;
block|}
end_interface

end_unit

