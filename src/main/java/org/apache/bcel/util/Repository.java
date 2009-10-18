begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
begin_comment
comment|/*  * Copyright  2000-2009 The Apache Software Foundation  *  *  Licensed under the Apache License, Version 2.0 (the "License");   *  you may not use this file except in compliance with the License.  *  You may obtain a copy of the License at  *  *      http://www.apache.org/licenses/LICENSE-2.0  *  *  Unless required by applicable law or agreed to in writing, software  *  distributed under the License is distributed on an "AS IS" BASIS,  *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  *  See the License for the specific language governing permissions and  *  limitations under the License.   *  */
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
comment|/**  * Abstract definition of a class repository. Instances may be used  * to load classes from different sources and may be used in the  * Repository.setRepository method.  *  * @see org.apache.bcel.Repository  * @version $Id$  * @author<A HREF="mailto:m.dahm@gmx.de">M. Dahm</A>  * @author David Dixon-Peugh  */
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
comment|/**      * Store the provided class under "clazz.getClassName()"       */
specifier|public
name|void
name|storeClass
parameter_list|(
name|JavaClass
name|clazz
parameter_list|)
function_decl|;
comment|/**      * Remove class from repository      */
specifier|public
name|void
name|removeClass
parameter_list|(
name|JavaClass
name|clazz
parameter_list|)
function_decl|;
comment|/**      * Find the class with the name provided, if the class      * isn't there, return NULL.      */
specifier|public
name|JavaClass
name|findClass
parameter_list|(
name|String
name|className
parameter_list|)
function_decl|;
comment|/**      * Find the class with the name provided, if the class      * isn't there, make an attempt to load it.      */
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
comment|/**      * Find the JavaClass instance for the given run-time class object      */
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
comment|/** Clear all entries from cache.      */
specifier|public
name|void
name|clear
parameter_list|()
function_decl|;
comment|/** Get the ClassPath associated with this Repository      */
specifier|public
name|ClassPath
name|getClassPath
parameter_list|()
function_decl|;
block|}
end_interface

end_unit

