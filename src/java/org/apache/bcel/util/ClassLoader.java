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
name|util
operator|.
name|Hashtable
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

begin_import
import|import
name|org
operator|.
name|apache
operator|.
name|bcel
operator|.
name|*
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
comment|/**  *<p>Drop in replacement for the standard class loader of the JVM. You can use it  * in conjunction with the JavaWrapper to dynamically modify/create classes  * as they're requested.</p>  *  *<p>This class loader recognizes special requests in a distinct  * format, i.e., when the name of the requested class contains with  * "$$BCEL$$" it calls the createClass() method with that name  * (everything bevor the $$BCEL$$ is considered to be the package  * name. You can subclass the class loader and override that  * method. "Normal" classes class can be modified by overriding the  * modifyClass() method which is called just before defineClass().</p>  *  *<p>There may be a number of packages where you have to use the  * default class loader (which may also be faster). You can define the  * set of packages where to use the system class loader in the  * constructor. The default value contains "java.", "sun.",  * "javax."</p>  *  * @version $Id$  * @author<A HREF="mailto:m.dahm@gmx.de">M. Dahm</A>  * @see JavaWrapper  * @see ClassPath  */
end_comment

begin_class
specifier|public
class|class
name|ClassLoader
extends|extends
name|java
operator|.
name|lang
operator|.
name|ClassLoader
block|{
specifier|public
specifier|static
specifier|final
name|String
index|[]
name|DEFAULT_IGNORED_PACKAGES
init|=
block|{
literal|"java."
block|,
literal|"javax."
block|,
literal|"sun."
block|}
decl_stmt|;
specifier|private
name|Hashtable
name|classes
init|=
operator|new
name|Hashtable
argument_list|()
decl_stmt|;
comment|// Hashtable is synchronized thus thread-safe
specifier|private
name|String
index|[]
name|ignored_packages
decl_stmt|;
specifier|private
name|Repository
name|repository
init|=
name|SyntheticRepository
operator|.
name|getInstance
argument_list|()
decl_stmt|;
specifier|private
name|java
operator|.
name|lang
operator|.
name|ClassLoader
name|deferTo
init|=
name|ClassLoader
operator|.
name|getSystemClassLoader
argument_list|()
decl_stmt|;
comment|/** Ignored packages are by default ( "java.", "sun.",    * "javax."), i.e. loaded by system class loader    */
specifier|public
name|ClassLoader
parameter_list|()
block|{
name|this
argument_list|(
name|DEFAULT_IGNORED_PACKAGES
argument_list|)
expr_stmt|;
block|}
comment|/** @param deferTo delegate class loader to use for ignored packages    */
specifier|public
name|ClassLoader
parameter_list|(
name|java
operator|.
name|lang
operator|.
name|ClassLoader
name|deferTo
parameter_list|)
block|{
name|this
argument_list|()
expr_stmt|;
name|this
operator|.
name|deferTo
operator|=
name|deferTo
expr_stmt|;
name|this
operator|.
name|repository
operator|=
operator|new
name|ClassLoaderRepository
argument_list|(
name|deferTo
argument_list|)
expr_stmt|;
block|}
comment|/** @param ignored_packages classes contained in these packages will be loaded    * with the system class loader    */
specifier|public
name|ClassLoader
parameter_list|(
name|String
index|[]
name|ignored_packages
parameter_list|)
block|{
name|this
operator|.
name|ignored_packages
operator|=
name|ignored_packages
expr_stmt|;
block|}
comment|/** @param ignored_packages classes contained in these packages will be loaded    * with the system class loader    * @param deferTo delegate class loader to use for ignored packages    */
specifier|public
name|ClassLoader
parameter_list|(
name|java
operator|.
name|lang
operator|.
name|ClassLoader
name|deferTo
parameter_list|,
name|String
index|[]
name|ignored_packages
parameter_list|)
block|{
name|this
argument_list|(
name|ignored_packages
argument_list|)
expr_stmt|;
name|this
operator|.
name|deferTo
operator|=
name|deferTo
expr_stmt|;
name|this
operator|.
name|repository
operator|=
operator|new
name|ClassLoaderRepository
argument_list|(
name|deferTo
argument_list|)
expr_stmt|;
block|}
specifier|protected
name|Class
name|loadClass
parameter_list|(
name|String
name|class_name
parameter_list|,
name|boolean
name|resolve
parameter_list|)
throws|throws
name|ClassNotFoundException
block|{
name|Class
name|cl
init|=
literal|null
decl_stmt|;
comment|/* First try: lookup hash table.      */
if|if
condition|(
operator|(
name|cl
operator|=
operator|(
name|Class
operator|)
name|classes
operator|.
name|get
argument_list|(
name|class_name
argument_list|)
operator|)
operator|==
literal|null
condition|)
block|{
comment|/* Second try: Load system class using system class loader. You better        * don't mess around with them.        */
for|for
control|(
name|int
name|i
init|=
literal|0
init|;
name|i
operator|<
name|ignored_packages
operator|.
name|length
condition|;
name|i
operator|++
control|)
block|{
if|if
condition|(
name|class_name
operator|.
name|startsWith
argument_list|(
name|ignored_packages
index|[
name|i
index|]
argument_list|)
condition|)
block|{
name|cl
operator|=
name|deferTo
operator|.
name|loadClass
argument_list|(
name|class_name
argument_list|)
expr_stmt|;
break|break;
block|}
block|}
if|if
condition|(
name|cl
operator|==
literal|null
condition|)
block|{
name|JavaClass
name|clazz
init|=
literal|null
decl_stmt|;
comment|/* Third try: Special request? 	 */
if|if
condition|(
name|class_name
operator|.
name|indexOf
argument_list|(
literal|"$$BCEL$$"
argument_list|)
operator|>=
literal|0
condition|)
name|clazz
operator|=
name|createClass
argument_list|(
name|class_name
argument_list|)
expr_stmt|;
else|else
block|{
comment|// Fourth try: Load classes via repository
if|if
condition|(
operator|(
name|clazz
operator|=
name|repository
operator|.
name|loadClass
argument_list|(
name|class_name
argument_list|)
operator|)
operator|!=
literal|null
condition|)
block|{
name|clazz
operator|=
name|modifyClass
argument_list|(
name|clazz
argument_list|)
expr_stmt|;
block|}
else|else
throw|throw
operator|new
name|ClassNotFoundException
argument_list|(
name|class_name
argument_list|)
throw|;
block|}
if|if
condition|(
name|clazz
operator|!=
literal|null
condition|)
block|{
name|byte
index|[]
name|bytes
init|=
name|clazz
operator|.
name|getBytes
argument_list|()
decl_stmt|;
name|cl
operator|=
name|defineClass
argument_list|(
name|class_name
argument_list|,
name|bytes
argument_list|,
literal|0
argument_list|,
name|bytes
operator|.
name|length
argument_list|)
expr_stmt|;
block|}
else|else
comment|// Fourth try: Use default class loader
name|cl
operator|=
name|Class
operator|.
name|forName
argument_list|(
name|class_name
argument_list|)
expr_stmt|;
block|}
if|if
condition|(
name|resolve
condition|)
name|resolveClass
argument_list|(
name|cl
argument_list|)
expr_stmt|;
block|}
name|classes
operator|.
name|put
argument_list|(
name|class_name
argument_list|,
name|cl
argument_list|)
expr_stmt|;
return|return
name|cl
return|;
block|}
comment|/** Override this method if you want to alter a class before it gets actually    * loaded. Does nothing by default.    */
specifier|protected
name|JavaClass
name|modifyClass
parameter_list|(
name|JavaClass
name|clazz
parameter_list|)
block|{
return|return
name|clazz
return|;
block|}
comment|/**     * Override this method to create you own classes on the fly. The    * name contains the special token $$BCEL$$. Everything before that    * token is consddered to be a package name. You can encode you own    * arguments into the subsequent string. You must regard however not    * to use any "illegal" characters, i.e., characters that may not    * appear in a Java class name too<br>    *    * The default implementation interprets the string as a encoded compressed    * Java class, unpacks and decodes it with the Utility.decode() method, and    * parses the resulting byte array and returns the resulting JavaClass object.    *    * @param class_name compressed byte code with "$$BCEL$$" in it    */
specifier|protected
name|JavaClass
name|createClass
parameter_list|(
name|String
name|class_name
parameter_list|)
block|{
name|int
name|index
init|=
name|class_name
operator|.
name|indexOf
argument_list|(
literal|"$$BCEL$$"
argument_list|)
decl_stmt|;
name|String
name|real_name
init|=
name|class_name
operator|.
name|substring
argument_list|(
name|index
operator|+
literal|8
argument_list|)
decl_stmt|;
name|JavaClass
name|clazz
init|=
literal|null
decl_stmt|;
try|try
block|{
name|byte
index|[]
name|bytes
init|=
name|Utility
operator|.
name|decode
argument_list|(
name|real_name
argument_list|,
literal|true
argument_list|)
decl_stmt|;
name|ClassParser
name|parser
init|=
operator|new
name|ClassParser
argument_list|(
operator|new
name|ByteArrayInputStream
argument_list|(
name|bytes
argument_list|)
argument_list|,
literal|"foo"
argument_list|)
decl_stmt|;
name|clazz
operator|=
name|parser
operator|.
name|parse
argument_list|()
expr_stmt|;
block|}
catch|catch
parameter_list|(
name|Throwable
name|e
parameter_list|)
block|{
name|e
operator|.
name|printStackTrace
argument_list|()
expr_stmt|;
return|return
literal|null
return|;
block|}
comment|// Adapt the class name to the passed value
name|ConstantPool
name|cp
init|=
name|clazz
operator|.
name|getConstantPool
argument_list|()
decl_stmt|;
name|ConstantClass
name|cl
init|=
operator|(
name|ConstantClass
operator|)
name|cp
operator|.
name|getConstant
argument_list|(
name|clazz
operator|.
name|getClassNameIndex
argument_list|()
argument_list|,
name|Constants
operator|.
name|CONSTANT_Class
argument_list|)
decl_stmt|;
name|ConstantUtf8
name|name
init|=
operator|(
name|ConstantUtf8
operator|)
name|cp
operator|.
name|getConstant
argument_list|(
name|cl
operator|.
name|getNameIndex
argument_list|()
argument_list|,
name|Constants
operator|.
name|CONSTANT_Utf8
argument_list|)
decl_stmt|;
name|name
operator|.
name|setBytes
argument_list|(
name|class_name
operator|.
name|replace
argument_list|(
literal|'.'
argument_list|,
literal|'/'
argument_list|)
argument_list|)
expr_stmt|;
return|return
name|clazz
return|;
block|}
block|}
end_class

end_unit

