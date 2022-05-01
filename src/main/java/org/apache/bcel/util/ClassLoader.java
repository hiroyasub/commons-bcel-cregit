begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
begin_comment
comment|/*  * Licensed to the Apache Software Foundation (ASF) under one or more  * contributor license agreements.  See the NOTICE file distributed with  * this work for additional information regarding copyright ownership.  * The ASF licenses this file to You under the Apache License, Version 2.0  * (the "License"); you may not use this file except in compliance with  * the License.  You may obtain a copy of the License at  *  *      http://www.apache.org/licenses/LICENSE-2.0  *  *  Unless required by applicable law or agreed to in writing, software  *  distributed under the License is distributed on an "AS IS" BASIS,  *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  *  See the License for the specific language governing permissions and  *  limitations under the License.  *  */
end_comment

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

begin_import
import|import
name|java
operator|.
name|io
operator|.
name|ByteArrayInputStream
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
name|java
operator|.
name|util
operator|.
name|Hashtable
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
name|Const
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
name|ClassParser
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
name|ConstantClass
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
name|ConstantPool
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
name|ConstantUtf8
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
name|JavaClass
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
name|Utility
import|;
end_import

begin_comment
comment|/**  *<p>Drop in replacement for the standard class loader of the JVM. You can use it  * in conjunction with the JavaWrapper to dynamically modify/create classes  * as they're requested.</p>  *  *<p>This class loader recognizes special requests in a distinct  * format, i.e., when the name of the requested class contains with  * "$$BCEL$$" it calls the createClass() method with that name  * (everything bevor the $$BCEL$$ is considered to be the package  * name. You can subclass the class loader and override that  * method. "Normal" classes class can be modified by overriding the  * modifyClass() method which is called just before defineClass().</p>  *  *<p>There may be a number of packages where you have to use the  * default class loader (which may also be faster). You can define the  * set of packages where to use the system class loader in the  * constructor. The default value contains "java.", "sun.",  * "javax."</p>  *  * @see JavaWrapper  * @see ClassPath  * @deprecated 6.0 Do not use - does not work  */
end_comment

begin_class
annotation|@
name|Deprecated
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
specifier|private
specifier|static
specifier|final
name|String
name|BCEL_TOKEN
init|=
literal|"$$BCEL$$"
decl_stmt|;
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
specifier|final
name|Hashtable
argument_list|<
name|String
argument_list|,
name|Class
argument_list|<
name|?
argument_list|>
argument_list|>
name|classes
init|=
operator|new
name|Hashtable
argument_list|<>
argument_list|()
decl_stmt|;
comment|// Hashtable is synchronized thus thread-safe
specifier|private
specifier|final
name|String
index|[]
name|ignoredPackages
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
comment|/** Ignored packages are by default ( "java.", "sun.",      * "javax."), i.e. loaded by system class loader      */
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
comment|/** @param deferTo delegate class loader to use for ignored packages      */
specifier|public
name|ClassLoader
parameter_list|(
specifier|final
name|java
operator|.
name|lang
operator|.
name|ClassLoader
name|deferTo
parameter_list|)
block|{
name|super
argument_list|(
name|deferTo
argument_list|)
expr_stmt|;
name|this
operator|.
name|ignoredPackages
operator|=
name|DEFAULT_IGNORED_PACKAGES
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
comment|/** @param ignored_packages classes contained in these packages will be loaded      * with the system class loader      * @param deferTo delegate class loader to use for ignored packages      */
specifier|public
name|ClassLoader
parameter_list|(
specifier|final
name|java
operator|.
name|lang
operator|.
name|ClassLoader
name|deferTo
parameter_list|,
specifier|final
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
name|repository
operator|=
operator|new
name|ClassLoaderRepository
argument_list|(
name|deferTo
argument_list|)
expr_stmt|;
block|}
comment|/** @param ignored_packages classes contained in these packages will be loaded      * with the system class loader      */
specifier|public
name|ClassLoader
parameter_list|(
specifier|final
name|String
index|[]
name|ignored_packages
parameter_list|)
block|{
name|this
operator|.
name|ignoredPackages
operator|=
name|ignored_packages
expr_stmt|;
block|}
comment|/**      * Override this method to create you own classes on the fly. The      * name contains the special token $$BCEL$$. Everything before that      * token is considered to be a package name. You can encode your own      * arguments into the subsequent string. You must ensure however not      * to use any "illegal" characters, i.e., characters that may not      * appear in a Java class name too      *<p>      * The default implementation interprets the string as a encoded compressed      * Java class, unpacks and decodes it with the Utility.decode() method, and      * parses the resulting byte array and returns the resulting JavaClass object.      *</p>      *      * @param class_name compressed byte code with "$$BCEL$$" in it      */
specifier|protected
name|JavaClass
name|createClass
parameter_list|(
specifier|final
name|String
name|class_name
parameter_list|)
block|{
specifier|final
name|int
name|index
init|=
name|class_name
operator|.
name|indexOf
argument_list|(
name|BCEL_TOKEN
argument_list|)
decl_stmt|;
specifier|final
name|String
name|real_name
init|=
name|class_name
operator|.
name|substring
argument_list|(
name|index
operator|+
name|BCEL_TOKEN
operator|.
name|length
argument_list|()
argument_list|)
decl_stmt|;
name|JavaClass
name|clazz
init|=
literal|null
decl_stmt|;
try|try
block|{
specifier|final
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
specifier|final
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
specifier|final
name|IOException
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
specifier|final
name|ConstantPool
name|cp
init|=
name|clazz
operator|.
name|getConstantPool
argument_list|()
decl_stmt|;
specifier|final
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
name|Const
operator|.
name|CONSTANT_Class
argument_list|)
decl_stmt|;
specifier|final
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
name|Const
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
annotation|@
name|Override
specifier|protected
name|Class
argument_list|<
name|?
argument_list|>
name|loadClass
parameter_list|(
specifier|final
name|String
name|class_name
parameter_list|,
specifier|final
name|boolean
name|resolve
parameter_list|)
throws|throws
name|ClassNotFoundException
block|{
name|Class
argument_list|<
name|?
argument_list|>
name|cl
init|=
literal|null
decl_stmt|;
comment|/* First try: lookup hash table.          */
if|if
condition|(
operator|(
name|cl
operator|=
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
comment|/* Second try: Load system class using system class loader. You better              * don't mess around with them.              */
for|for
control|(
specifier|final
name|String
name|ignored_package
range|:
name|ignoredPackages
control|)
block|{
if|if
condition|(
name|class_name
operator|.
name|startsWith
argument_list|(
name|ignored_package
argument_list|)
condition|)
block|{
name|cl
operator|=
name|getParent
argument_list|()
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
comment|/* Third try: Special request?                  */
if|if
condition|(
name|class_name
operator|.
name|contains
argument_list|(
name|BCEL_TOKEN
argument_list|)
condition|)
block|{
name|clazz
operator|=
name|createClass
argument_list|(
name|class_name
argument_list|)
expr_stmt|;
block|}
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
operator|==
literal|null
condition|)
block|{
throw|throw
operator|new
name|ClassNotFoundException
argument_list|(
name|class_name
argument_list|)
throw|;
block|}
name|clazz
operator|=
name|modifyClass
argument_list|(
name|clazz
argument_list|)
expr_stmt|;
block|}
if|if
condition|(
name|clazz
operator|!=
literal|null
condition|)
block|{
specifier|final
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
block|{
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
block|}
if|if
condition|(
name|resolve
condition|)
block|{
name|resolveClass
argument_list|(
name|cl
argument_list|)
expr_stmt|;
block|}
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
comment|/** Override this method if you want to alter a class before it gets actually      * loaded. Does nothing by default.      */
specifier|protected
name|JavaClass
name|modifyClass
parameter_list|(
specifier|final
name|JavaClass
name|clazz
parameter_list|)
block|{
return|return
name|clazz
return|;
block|}
block|}
end_class

end_unit

