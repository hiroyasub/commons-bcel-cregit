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
name|Map
import|;
end_import

begin_import
import|import
name|java
operator|.
name|util
operator|.
name|HashMap
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
name|classfile
operator|.
name|*
import|;
end_import

begin_comment
comment|/**  * The repository maintains information about which classes have  * been loaded.  *  * It loads its data from the ClassLoader implementation  * passed into its constructor.  */
end_comment

begin_class
specifier|public
class|class
name|ClassLoaderRepository
implements|implements
name|Repository
block|{
specifier|private
name|java
operator|.
name|lang
operator|.
name|ClassLoader
name|loader
decl_stmt|;
specifier|private
name|Map
name|loadedClasses
init|=
operator|new
name|HashMap
argument_list|()
decl_stmt|;
comment|// CLASSNAME X JAVACLASS
specifier|public
name|ClassLoaderRepository
parameter_list|(
name|java
operator|.
name|lang
operator|.
name|ClassLoader
name|loader
parameter_list|)
block|{
name|this
operator|.
name|loader
operator|=
name|loader
expr_stmt|;
block|}
comment|/**      * Store a new JavaClass into this Repository.      */
specifier|public
name|void
name|storeClass
parameter_list|(
name|JavaClass
name|clazz
parameter_list|)
block|{
name|loadedClasses
operator|.
name|put
argument_list|(
name|clazz
operator|.
name|getClassName
argument_list|()
argument_list|,
name|clazz
argument_list|)
expr_stmt|;
name|clazz
operator|.
name|setRepository
argument_list|(
name|this
argument_list|)
expr_stmt|;
block|}
comment|/**    * Remove class from repository    */
specifier|public
name|void
name|removeClass
parameter_list|(
name|JavaClass
name|clazz
parameter_list|)
block|{
name|loadedClasses
operator|.
name|remove
argument_list|(
name|clazz
operator|.
name|getClassName
argument_list|()
argument_list|)
expr_stmt|;
block|}
comment|/**      * Find an already defined JavaClass.      */
specifier|public
name|JavaClass
name|findClass
parameter_list|(
name|String
name|className
parameter_list|)
block|{
if|if
condition|(
name|loadedClasses
operator|.
name|containsKey
argument_list|(
name|className
argument_list|)
condition|)
block|{
return|return
operator|(
name|JavaClass
operator|)
name|loadedClasses
operator|.
name|get
argument_list|(
name|className
argument_list|)
return|;
block|}
else|else
block|{
return|return
literal|null
return|;
block|}
block|}
comment|/**      * Lookup a JavaClass object from the Class Name provided.      */
specifier|public
name|JavaClass
name|loadClass
parameter_list|(
name|String
name|className
parameter_list|)
throws|throws
name|ClassNotFoundException
block|{
name|String
name|classFile
init|=
name|className
operator|.
name|replace
argument_list|(
literal|'.'
argument_list|,
literal|'/'
argument_list|)
decl_stmt|;
name|JavaClass
name|RC
init|=
name|findClass
argument_list|(
name|className
argument_list|)
decl_stmt|;
if|if
condition|(
name|RC
operator|!=
literal|null
condition|)
block|{
return|return
name|RC
return|;
block|}
try|try
block|{
name|InputStream
name|is
init|=
name|loader
operator|.
name|getResourceAsStream
argument_list|(
name|classFile
operator|+
literal|".class"
argument_list|)
decl_stmt|;
if|if
condition|(
name|is
operator|==
literal|null
condition|)
block|{
throw|throw
operator|new
name|ClassNotFoundException
argument_list|(
name|className
operator|+
literal|" not found."
argument_list|)
throw|;
block|}
name|ClassParser
name|parser
init|=
operator|new
name|ClassParser
argument_list|(
name|is
argument_list|,
name|className
argument_list|)
decl_stmt|;
name|RC
operator|=
name|parser
operator|.
name|parse
argument_list|()
expr_stmt|;
name|storeClass
argument_list|(
name|RC
argument_list|)
expr_stmt|;
return|return
name|RC
return|;
block|}
catch|catch
parameter_list|(
name|IOException
name|e
parameter_list|)
block|{
throw|throw
operator|new
name|ClassNotFoundException
argument_list|(
name|e
operator|.
name|toString
argument_list|()
argument_list|)
throw|;
block|}
block|}
block|}
end_class

end_unit

