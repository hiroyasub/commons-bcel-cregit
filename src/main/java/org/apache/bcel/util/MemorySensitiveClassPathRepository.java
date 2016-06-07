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
name|IOException
import|;
end_import

begin_import
import|import
name|java
operator|.
name|io
operator|.
name|InputStream
import|;
end_import

begin_import
import|import
name|java
operator|.
name|lang
operator|.
name|ref
operator|.
name|SoftReference
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
name|java
operator|.
name|util
operator|.
name|Map
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
name|JavaClass
import|;
end_import

begin_comment
comment|/**  * This repository is used in situations where a Class is created outside the realm of a ClassLoader. Classes are loaded from the file systems using the paths  * specified in the given class path. By default, this is the value returned by ClassPath.getClassPath(). This repository holds onto classes with  * SoftReferences, and will reload as needed, in cases where memory sizes are important.<br>  *  * @see org.apache.bcel.Repository  */
end_comment

begin_class
specifier|public
class|class
name|MemorySensitiveClassPathRepository
implements|implements
name|Repository
block|{
specifier|private
name|ClassPath
name|_path
init|=
literal|null
decl_stmt|;
specifier|private
specifier|final
name|Map
argument_list|<
name|String
argument_list|,
name|SoftReference
argument_list|<
name|JavaClass
argument_list|>
argument_list|>
name|_loadedClasses
init|=
operator|new
name|HashMap
argument_list|<>
argument_list|()
decl_stmt|;
comment|// CLASSNAME X JAVACLASS
specifier|public
name|MemorySensitiveClassPathRepository
parameter_list|(
specifier|final
name|ClassPath
name|path
parameter_list|)
block|{
name|_path
operator|=
name|path
expr_stmt|;
block|}
comment|/**      * Store a new JavaClass instance into this Repository.      */
annotation|@
name|Override
specifier|public
name|void
name|storeClass
parameter_list|(
specifier|final
name|JavaClass
name|clazz
parameter_list|)
block|{
name|_loadedClasses
operator|.
name|put
argument_list|(
name|clazz
operator|.
name|getClassName
argument_list|()
argument_list|,
operator|new
name|SoftReference
argument_list|<>
argument_list|(
name|clazz
argument_list|)
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
comment|/**      * Remove class from repository      */
annotation|@
name|Override
specifier|public
name|void
name|removeClass
parameter_list|(
specifier|final
name|JavaClass
name|clazz
parameter_list|)
block|{
name|_loadedClasses
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
comment|/**      * Find an already defined (cached) JavaClass object by name.      */
annotation|@
name|Override
specifier|public
name|JavaClass
name|findClass
parameter_list|(
specifier|final
name|String
name|className
parameter_list|)
block|{
name|SoftReference
argument_list|<
name|JavaClass
argument_list|>
name|ref
init|=
name|_loadedClasses
operator|.
name|get
argument_list|(
name|className
argument_list|)
decl_stmt|;
if|if
condition|(
name|ref
operator|==
literal|null
condition|)
block|{
return|return
literal|null
return|;
block|}
return|return
name|ref
operator|.
name|get
argument_list|()
return|;
block|}
comment|/**      * Find a JavaClass object by name. If it is already in this Repository, the Repository version is returned. Otherwise, the Repository's classpath is      * searched for the class (and it is added to the Repository if found).      *      * @param className      *            the name of the class      * @return the JavaClass object      * @throws ClassNotFoundException      *             if the class is not in the Repository, and could not be found on the classpath      */
annotation|@
name|Override
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
if|if
condition|(
operator|(
name|className
operator|==
literal|null
operator|)
operator|||
name|className
operator|.
name|equals
argument_list|(
literal|""
argument_list|)
condition|)
block|{
throw|throw
operator|new
name|IllegalArgumentException
argument_list|(
literal|"Invalid class name "
operator|+
name|className
argument_list|)
throw|;
block|}
name|className
operator|=
name|className
operator|.
name|replace
argument_list|(
literal|'/'
argument_list|,
literal|'.'
argument_list|)
expr_stmt|;
comment|// Just in case, canonical form
name|JavaClass
name|clazz
init|=
name|findClass
argument_list|(
name|className
argument_list|)
decl_stmt|;
if|if
condition|(
name|clazz
operator|!=
literal|null
condition|)
block|{
return|return
name|clazz
return|;
block|}
try|try
block|{
return|return
name|loadClass
argument_list|(
name|_path
operator|.
name|getInputStream
argument_list|(
name|className
argument_list|)
argument_list|,
name|className
argument_list|)
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
literal|"Exception while looking for class "
operator|+
name|className
operator|+
literal|": "
operator|+
name|e
argument_list|,
name|e
argument_list|)
throw|;
block|}
block|}
comment|/**      * Find the JavaClass object for a runtime Class object. If a class with the same name is already in this Repository, the Repository version is returned.      * Otherwise, getResourceAsStream() is called on the Class object to find the class's representation. If the representation is found, it is added to the      * Repository.      *      * @see Class      * @param clazz      *            the runtime Class object      * @return JavaClass object for given runtime class      * @throws ClassNotFoundException      *             if the class is not in the Repository, and its representation could not be found      */
annotation|@
name|Override
specifier|public
name|JavaClass
name|loadClass
parameter_list|(
specifier|final
name|Class
argument_list|<
name|?
argument_list|>
name|clazz
parameter_list|)
throws|throws
name|ClassNotFoundException
block|{
name|InputStream
name|clsStream
init|=
literal|null
decl_stmt|;
try|try
block|{
name|String
name|className
init|=
name|clazz
operator|.
name|getName
argument_list|()
decl_stmt|;
name|JavaClass
name|repositoryClass
init|=
name|findClass
argument_list|(
name|className
argument_list|)
decl_stmt|;
if|if
condition|(
name|repositoryClass
operator|!=
literal|null
condition|)
block|{
return|return
name|repositoryClass
return|;
block|}
name|String
name|name
init|=
name|className
decl_stmt|;
name|int
name|i
init|=
name|name
operator|.
name|lastIndexOf
argument_list|(
literal|'.'
argument_list|)
decl_stmt|;
if|if
condition|(
name|i
operator|>
literal|0
condition|)
block|{
name|name
operator|=
name|name
operator|.
name|substring
argument_list|(
name|i
operator|+
literal|1
argument_list|)
expr_stmt|;
block|}
name|clsStream
operator|=
name|clazz
operator|.
name|getResourceAsStream
argument_list|(
name|name
operator|+
literal|".class"
argument_list|)
expr_stmt|;
return|return
name|loadClass
argument_list|(
name|clsStream
argument_list|,
name|className
argument_list|)
return|;
block|}
finally|finally
block|{
try|try
block|{
if|if
condition|(
name|clsStream
operator|!=
literal|null
condition|)
block|{
name|clsStream
operator|.
name|close
argument_list|()
expr_stmt|;
block|}
block|}
catch|catch
parameter_list|(
name|IOException
name|ioe
parameter_list|)
block|{
comment|// don't care
block|}
block|}
block|}
specifier|private
name|JavaClass
name|loadClass
parameter_list|(
specifier|final
name|InputStream
name|is
parameter_list|,
specifier|final
name|String
name|className
parameter_list|)
throws|throws
name|ClassNotFoundException
block|{
try|try
block|{
if|if
condition|(
name|is
operator|!=
literal|null
condition|)
block|{
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
name|JavaClass
name|clazz
init|=
name|parser
operator|.
name|parse
argument_list|()
decl_stmt|;
name|storeClass
argument_list|(
name|clazz
argument_list|)
expr_stmt|;
return|return
name|clazz
return|;
block|}
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
literal|"Exception while looking for class "
operator|+
name|className
operator|+
literal|": "
operator|+
name|e
argument_list|,
name|e
argument_list|)
throw|;
block|}
finally|finally
block|{
if|if
condition|(
name|is
operator|!=
literal|null
condition|)
block|{
try|try
block|{
name|is
operator|.
name|close
argument_list|()
expr_stmt|;
block|}
catch|catch
parameter_list|(
name|IOException
name|e
parameter_list|)
block|{
comment|// ignored
block|}
block|}
block|}
throw|throw
operator|new
name|ClassNotFoundException
argument_list|(
literal|"SyntheticRepository could not load "
operator|+
name|className
argument_list|)
throw|;
block|}
comment|/**      * ClassPath associated with the Repository.      */
annotation|@
name|Override
specifier|public
name|ClassPath
name|getClassPath
parameter_list|()
block|{
return|return
name|_path
return|;
block|}
comment|/**      * Clear all entries from cache.      */
annotation|@
name|Override
specifier|public
name|void
name|clear
parameter_list|()
block|{
name|_loadedClasses
operator|.
name|clear
argument_list|()
expr_stmt|;
block|}
block|}
end_class

end_unit

