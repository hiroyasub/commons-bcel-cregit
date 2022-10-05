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
comment|/**  * This abstract class provides a logic of a loading {@link JavaClass} objects class names via {@link ClassPath}.  *  *<p>  * Subclasses can choose caching strategy of the objects by implementing the abstract methods (e.g.,  * {@link #storeClass(JavaClass)} and {@link #findClass(String)}).  *</p>  *  * @since 6.4.0  */
end_comment

begin_class
specifier|abstract
class|class
name|AbstractClassPathRepository
implements|implements
name|Repository
block|{
specifier|private
specifier|final
name|ClassPath
name|classPath
decl_stmt|;
name|AbstractClassPathRepository
parameter_list|(
specifier|final
name|ClassPath
name|classPath
parameter_list|)
block|{
name|this
operator|.
name|classPath
operator|=
name|classPath
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
specifier|abstract
name|void
name|clear
parameter_list|()
function_decl|;
annotation|@
name|Override
specifier|public
specifier|abstract
name|JavaClass
name|findClass
parameter_list|(
specifier|final
name|String
name|className
parameter_list|)
function_decl|;
annotation|@
name|Override
specifier|public
name|ClassPath
name|getClassPath
parameter_list|()
block|{
return|return
name|classPath
return|;
block|}
comment|/**      * Finds the JavaClass object for a runtime Class object. If a class with the same name is already in this Repository,      * the Repository version is returned. Otherwise, getResourceAsStream() is called on the Class object to find the      * class's representation. If the representation is found, it is added to the Repository.      *      * @see Class      * @param clazz the runtime Class object      * @return JavaClass object for given runtime class      * @throws ClassNotFoundException if the class is not in the Repository, and its representation could not be found      */
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
specifier|final
name|String
name|className
init|=
name|clazz
operator|.
name|getName
argument_list|()
decl_stmt|;
specifier|final
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
specifier|final
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
try|try
init|(
name|InputStream
name|clsStream
init|=
name|clazz
operator|.
name|getResourceAsStream
argument_list|(
name|name
operator|+
literal|".class"
argument_list|)
init|)
block|{
return|return
name|loadClass
argument_list|(
name|clsStream
argument_list|,
name|className
argument_list|)
return|;
block|}
catch|catch
parameter_list|(
specifier|final
name|IOException
name|e
parameter_list|)
block|{
return|return
literal|null
return|;
block|}
block|}
specifier|private
name|JavaClass
name|loadClass
parameter_list|(
specifier|final
name|InputStream
name|inputStream
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
name|inputStream
operator|!=
literal|null
condition|)
block|{
specifier|final
name|ClassParser
name|parser
init|=
operator|new
name|ClassParser
argument_list|(
name|inputStream
argument_list|,
name|className
argument_list|)
decl_stmt|;
specifier|final
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
specifier|final
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
throw|throw
operator|new
name|ClassNotFoundException
argument_list|(
literal|"ClassRepository could not load "
operator|+
name|className
argument_list|)
throw|;
block|}
comment|/**      * Finds a JavaClass object by name. If it is already in this Repository, the Repository version is returned. Otherwise,      * the Repository's classpath is searched for the class (and it is added to the Repository if found).      *      * @param className the name of the class      * @return the JavaClass object      * @throws ClassNotFoundException if the class is not in the Repository, and could not be found on the classpath      */
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
name|className
operator|==
literal|null
operator|||
name|className
operator|.
name|isEmpty
argument_list|()
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
name|Utility
operator|.
name|pathToPackage
argument_list|(
name|className
argument_list|)
expr_stmt|;
comment|// Just in case, canonical form
specifier|final
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
init|(
name|InputStream
name|inputStream
init|=
name|classPath
operator|.
name|getInputStream
argument_list|(
name|className
argument_list|)
init|)
block|{
return|return
name|loadClass
argument_list|(
name|inputStream
argument_list|,
name|className
argument_list|)
return|;
block|}
catch|catch
parameter_list|(
specifier|final
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
annotation|@
name|Override
specifier|public
specifier|abstract
name|void
name|removeClass
parameter_list|(
specifier|final
name|JavaClass
name|javaClass
parameter_list|)
function_decl|;
annotation|@
name|Override
specifier|public
specifier|abstract
name|void
name|storeClass
parameter_list|(
specifier|final
name|JavaClass
name|javaClass
parameter_list|)
function_decl|;
block|}
end_class

end_unit

