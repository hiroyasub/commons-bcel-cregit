begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
begin_comment
comment|/*  * Copyright  2000-2004 The Apache Software Foundation  *  *  Licensed under the Apache License, Version 2.0 (the "License");   *  you may not use this file except in compliance with the License.  *  You may obtain a copy of the License at  *  *      http://www.apache.org/licenses/LICENSE-2.0  *  *  Unless required by applicable law or agreed to in writing, software  *  distributed under the License is distributed on an "AS IS" BASIS,  *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  *  See the License for the specific language governing permissions and  *  limitations under the License.   *  */
end_comment

begin_package
package|package
name|org
operator|.
name|apache
operator|.
name|bcel
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

begin_import
import|import
name|org
operator|.
name|apache
operator|.
name|bcel
operator|.
name|util
operator|.
name|*
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
comment|/**  * The repository maintains informations about class interdependencies, e.g.,  * whether a class is a sub-class of another. Delegates actual class loading  * to SyntheticRepository with current class path by default.  *  * @see org.apache.bcel.util.Repository  * @see org.apache.bcel.util.SyntheticRepository  *  * @version $Id$  * @author<A HREF="mailto:m.dahm@gmx.de">M. Dahm</A>  */
end_comment

begin_class
specifier|public
specifier|abstract
class|class
name|Repository
block|{
specifier|private
specifier|static
name|org
operator|.
name|apache
operator|.
name|bcel
operator|.
name|util
operator|.
name|Repository
name|_repository
init|=
name|SyntheticRepository
operator|.
name|getInstance
argument_list|()
decl_stmt|;
comment|/** @return currently used repository instance    */
specifier|public
specifier|static
name|org
operator|.
name|apache
operator|.
name|bcel
operator|.
name|util
operator|.
name|Repository
name|getRepository
parameter_list|()
block|{
return|return
name|_repository
return|;
block|}
comment|/** Set repository instance to be used for class loading    */
specifier|public
specifier|static
name|void
name|setRepository
parameter_list|(
name|org
operator|.
name|apache
operator|.
name|bcel
operator|.
name|util
operator|.
name|Repository
name|rep
parameter_list|)
block|{
name|_repository
operator|=
name|rep
expr_stmt|;
block|}
comment|/** Lookup class somewhere found on your CLASSPATH, or whereever the    * repository instance looks for it.    *    * @return class object for given fully qualified class name    * @throws ClassNotFoundException if the class could not be found or    * parsed correctly    */
specifier|public
specifier|static
name|JavaClass
name|lookupClass
parameter_list|(
name|String
name|class_name
parameter_list|)
throws|throws
name|ClassNotFoundException
block|{
return|return
name|_repository
operator|.
name|loadClass
argument_list|(
name|class_name
argument_list|)
return|;
block|}
comment|/**    * Try to find class source using the internal repository instance.    * @see Class    * @return JavaClass object for given runtime class    * @throws ClassNotFoundException if the class could not be found or    * parsed correctly    */
specifier|public
specifier|static
name|JavaClass
name|lookupClass
parameter_list|(
name|Class
name|clazz
parameter_list|)
throws|throws
name|ClassNotFoundException
block|{
return|return
name|_repository
operator|.
name|loadClass
argument_list|(
name|clazz
argument_list|)
return|;
block|}
comment|/**    * @return class file object for given Java class by looking on the    *  system class path; returns null if the class file can't be    *  found    */
specifier|public
specifier|static
name|ClassPath
operator|.
name|ClassFile
name|lookupClassFile
parameter_list|(
name|String
name|class_name
parameter_list|)
block|{
try|try
block|{
name|ClassPath
name|path
init|=
name|_repository
operator|.
name|getClassPath
argument_list|()
decl_stmt|;
if|if
condition|(
name|path
operator|!=
literal|null
condition|)
block|{
return|return
name|path
operator|.
name|getClassFile
argument_list|(
name|class_name
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
catch|catch
parameter_list|(
name|IOException
name|e
parameter_list|)
block|{
return|return
literal|null
return|;
block|}
block|}
comment|/** Clear the repository.    */
specifier|public
specifier|static
name|void
name|clearCache
parameter_list|()
block|{
name|_repository
operator|.
name|clear
argument_list|()
expr_stmt|;
block|}
comment|/**    * Add clazz to repository if there isn't an equally named class already in there.    *    * @return old entry in repository    */
specifier|public
specifier|static
name|JavaClass
name|addClass
parameter_list|(
name|JavaClass
name|clazz
parameter_list|)
block|{
name|JavaClass
name|old
init|=
name|_repository
operator|.
name|findClass
argument_list|(
name|clazz
operator|.
name|getClassName
argument_list|()
argument_list|)
decl_stmt|;
name|_repository
operator|.
name|storeClass
argument_list|(
name|clazz
argument_list|)
expr_stmt|;
return|return
name|old
return|;
block|}
comment|/**    * Remove class with given (fully qualified) name from repository.    */
specifier|public
specifier|static
name|void
name|removeClass
parameter_list|(
name|String
name|clazz
parameter_list|)
block|{
name|_repository
operator|.
name|removeClass
argument_list|(
name|_repository
operator|.
name|findClass
argument_list|(
name|clazz
argument_list|)
argument_list|)
expr_stmt|;
block|}
comment|/**    * Remove given class from repository.    */
specifier|public
specifier|static
name|void
name|removeClass
parameter_list|(
name|JavaClass
name|clazz
parameter_list|)
block|{
name|_repository
operator|.
name|removeClass
argument_list|(
name|clazz
argument_list|)
expr_stmt|;
block|}
comment|/**    * @return list of super classes of clazz in ascending order, i.e.,    * Object is always the last element    * @throws ClassNotFoundException if any of the superclasses can't be found    */
specifier|public
specifier|static
name|JavaClass
index|[]
name|getSuperClasses
parameter_list|(
name|JavaClass
name|clazz
parameter_list|)
throws|throws
name|ClassNotFoundException
block|{
return|return
name|clazz
operator|.
name|getSuperClasses
argument_list|()
return|;
block|}
comment|/**    * @return list of super classes of clazz in ascending order, i.e.,    * Object is always the last element.    * @throws ClassNotFoundException if the named class or any of its    *  superclasses can't be found    */
specifier|public
specifier|static
name|JavaClass
index|[]
name|getSuperClasses
parameter_list|(
name|String
name|class_name
parameter_list|)
throws|throws
name|ClassNotFoundException
block|{
name|JavaClass
name|jc
init|=
name|lookupClass
argument_list|(
name|class_name
argument_list|)
decl_stmt|;
return|return
name|getSuperClasses
argument_list|(
name|jc
argument_list|)
return|;
block|}
comment|/**    * @return all interfaces implemented by class and its super    * classes and the interfaces that those interfaces extend, and so on.    * (Some people call this a transitive hull).    * @throws ClassNotFoundException if any of the class's    *  superclasses or superinterfaces can't be found    */
specifier|public
specifier|static
name|JavaClass
index|[]
name|getInterfaces
parameter_list|(
name|JavaClass
name|clazz
parameter_list|)
throws|throws
name|ClassNotFoundException
block|{
return|return
name|clazz
operator|.
name|getAllInterfaces
argument_list|()
return|;
block|}
comment|/**    * @return all interfaces implemented by class and its super    * classes and the interfaces that extend those interfaces, and so on    * @throws ClassNotFoundException if the named class can't be found,    *   or if any of its superclasses or superinterfaces can't be found    */
specifier|public
specifier|static
name|JavaClass
index|[]
name|getInterfaces
parameter_list|(
name|String
name|class_name
parameter_list|)
throws|throws
name|ClassNotFoundException
block|{
return|return
name|getInterfaces
argument_list|(
name|lookupClass
argument_list|(
name|class_name
argument_list|)
argument_list|)
return|;
block|}
comment|/**    * Equivalent to runtime "instanceof" operator.    * @return true, if clazz is an instance of super_class    * @throws ClassNotFoundException if any superclasses or superinterfaces    *   of clazz can't be found    */
specifier|public
specifier|static
name|boolean
name|instanceOf
parameter_list|(
name|JavaClass
name|clazz
parameter_list|,
name|JavaClass
name|super_class
parameter_list|)
throws|throws
name|ClassNotFoundException
block|{
return|return
name|clazz
operator|.
name|instanceOf
argument_list|(
name|super_class
argument_list|)
return|;
block|}
comment|/**    * @return true, if clazz is an instance of super_class    * @throws ClassNotFoundException if either clazz or super_class    *   can't be found    */
specifier|public
specifier|static
name|boolean
name|instanceOf
parameter_list|(
name|String
name|clazz
parameter_list|,
name|String
name|super_class
parameter_list|)
throws|throws
name|ClassNotFoundException
block|{
return|return
name|instanceOf
argument_list|(
name|lookupClass
argument_list|(
name|clazz
argument_list|)
argument_list|,
name|lookupClass
argument_list|(
name|super_class
argument_list|)
argument_list|)
return|;
block|}
comment|/**    * @return true, if clazz is an instance of super_class    * @throws ClassNotFoundException if super_class can't be found    */
specifier|public
specifier|static
name|boolean
name|instanceOf
parameter_list|(
name|JavaClass
name|clazz
parameter_list|,
name|String
name|super_class
parameter_list|)
throws|throws
name|ClassNotFoundException
block|{
return|return
name|instanceOf
argument_list|(
name|clazz
argument_list|,
name|lookupClass
argument_list|(
name|super_class
argument_list|)
argument_list|)
return|;
block|}
comment|/**    * @return true, if clazz is an instance of super_class    * @throws ClassNotFoundException if clazz can't be found    */
specifier|public
specifier|static
name|boolean
name|instanceOf
parameter_list|(
name|String
name|clazz
parameter_list|,
name|JavaClass
name|super_class
parameter_list|)
throws|throws
name|ClassNotFoundException
block|{
return|return
name|instanceOf
argument_list|(
name|lookupClass
argument_list|(
name|clazz
argument_list|)
argument_list|,
name|super_class
argument_list|)
return|;
block|}
comment|/**    * @return true, if clazz is an implementation of interface inter    * @throws ClassNotFoundException if any superclasses or superinterfaces    *   of clazz can't be found    */
specifier|public
specifier|static
name|boolean
name|implementationOf
parameter_list|(
name|JavaClass
name|clazz
parameter_list|,
name|JavaClass
name|inter
parameter_list|)
throws|throws
name|ClassNotFoundException
block|{
return|return
name|clazz
operator|.
name|implementationOf
argument_list|(
name|inter
argument_list|)
return|;
block|}
comment|/**    * @return true, if clazz is an implementation of interface inter    * @throws ClassNotFoundException if clazz, inter, or any superclasses    *   or superinterfaces of clazz can't be found    */
specifier|public
specifier|static
name|boolean
name|implementationOf
parameter_list|(
name|String
name|clazz
parameter_list|,
name|String
name|inter
parameter_list|)
throws|throws
name|ClassNotFoundException
block|{
return|return
name|implementationOf
argument_list|(
name|lookupClass
argument_list|(
name|clazz
argument_list|)
argument_list|,
name|lookupClass
argument_list|(
name|inter
argument_list|)
argument_list|)
return|;
block|}
comment|/**    * @return true, if clazz is an implementation of interface inter    * @throws ClassNotFoundException if inter or any superclasses    *   or superinterfaces of clazz can't be found    */
specifier|public
specifier|static
name|boolean
name|implementationOf
parameter_list|(
name|JavaClass
name|clazz
parameter_list|,
name|String
name|inter
parameter_list|)
throws|throws
name|ClassNotFoundException
block|{
return|return
name|implementationOf
argument_list|(
name|clazz
argument_list|,
name|lookupClass
argument_list|(
name|inter
argument_list|)
argument_list|)
return|;
block|}
comment|/**    * @return true, if clazz is an implementation of interface inter    * @throws ClassNotFoundException if clazz or any superclasses or    *   superinterfaces of clazz can't be found    */
specifier|public
specifier|static
name|boolean
name|implementationOf
parameter_list|(
name|String
name|clazz
parameter_list|,
name|JavaClass
name|inter
parameter_list|)
throws|throws
name|ClassNotFoundException
block|{
return|return
name|implementationOf
argument_list|(
name|lookupClass
argument_list|(
name|clazz
argument_list|)
argument_list|,
name|inter
argument_list|)
return|;
block|}
block|}
end_class

end_unit

