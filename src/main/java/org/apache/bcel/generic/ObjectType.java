begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
begin_comment
comment|/*  * Licensed to the Apache Software Foundation (ASF) under one or more  * contributor license agreements.  See the NOTICE file distributed with  * this work for additional information regarding copyright ownership.  * The ASF licenses this file to You under the Apache License, Version 2.0  * (the "License"); you may not use this file except in compliance with  * the License.  You may obtain a copy of the License at  *  *      http://www.apache.org/licenses/LICENSE-2.0  *  *  Unless required by applicable law or agreed to in writing, software  *  distributed under the License is distributed on an "AS IS" BASIS,  *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  *  See the License for the specific language governing permissions and  *  limitations under the License.  */
end_comment

begin_package
package|package
name|org
operator|.
name|apache
operator|.
name|bcel
operator|.
name|generic
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
name|Repository
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
comment|/**  * Denotes reference such as java.lang.String.  */
end_comment

begin_class
specifier|public
class|class
name|ObjectType
extends|extends
name|ReferenceType
block|{
comment|/**      * Constructs a new instance.      *      * @param className fully qualified class name, e.g. java.lang.String      * @return a new instance.      * @since 6.0      */
specifier|public
specifier|static
name|ObjectType
name|getInstance
parameter_list|(
specifier|final
name|String
name|className
parameter_list|)
block|{
return|return
operator|new
name|ObjectType
argument_list|(
name|className
argument_list|)
return|;
block|}
specifier|private
specifier|final
name|String
name|className
decl_stmt|;
comment|// Class name of type
comment|/**      * Constructs a new instance.      *      * @param className fully qualified class name, e.g. java.lang.String      */
specifier|public
name|ObjectType
parameter_list|(
specifier|final
name|String
name|className
parameter_list|)
block|{
name|super
argument_list|(
name|Const
operator|.
name|T_REFERENCE
argument_list|,
literal|"L"
operator|+
name|Utility
operator|.
name|packageToPath
argument_list|(
name|className
argument_list|)
operator|+
literal|";"
argument_list|)
expr_stmt|;
name|this
operator|.
name|className
operator|=
name|Utility
operator|.
name|pathToPackage
argument_list|(
name|className
argument_list|)
expr_stmt|;
block|}
comment|/**      * Java Virtual Machine Specification edition 2, ï¿½ 5.4.4 Access Control      *      * @throws ClassNotFoundException if the class referenced by this type can't be found      */
specifier|public
name|boolean
name|accessibleTo
parameter_list|(
specifier|final
name|ObjectType
name|accessor
parameter_list|)
throws|throws
name|ClassNotFoundException
block|{
specifier|final
name|JavaClass
name|jc
init|=
name|Repository
operator|.
name|lookupClass
argument_list|(
name|className
argument_list|)
decl_stmt|;
if|if
condition|(
name|jc
operator|.
name|isPublic
argument_list|()
condition|)
block|{
return|return
literal|true
return|;
block|}
specifier|final
name|JavaClass
name|acc
init|=
name|Repository
operator|.
name|lookupClass
argument_list|(
name|accessor
operator|.
name|className
argument_list|)
decl_stmt|;
return|return
name|acc
operator|.
name|getPackageName
argument_list|()
operator|.
name|equals
argument_list|(
name|jc
operator|.
name|getPackageName
argument_list|()
argument_list|)
return|;
block|}
comment|/**      * @return true if both type objects refer to the same class.      */
annotation|@
name|Override
specifier|public
name|boolean
name|equals
parameter_list|(
specifier|final
name|Object
name|type
parameter_list|)
block|{
return|return
name|type
operator|instanceof
name|ObjectType
operator|&&
operator|(
operator|(
name|ObjectType
operator|)
name|type
operator|)
operator|.
name|className
operator|.
name|equals
argument_list|(
name|className
argument_list|)
return|;
block|}
comment|/**      * @return name of referenced class      */
annotation|@
name|Override
specifier|public
name|String
name|getClassName
parameter_list|()
block|{
return|return
name|className
return|;
block|}
comment|/**      * @return a hash code value for the object.      */
annotation|@
name|Override
specifier|public
name|int
name|hashCode
parameter_list|()
block|{
return|return
name|className
operator|.
name|hashCode
argument_list|()
return|;
block|}
comment|/**      * If "this" doesn't reference a class, it references an interface or a non-existant entity.      * @deprecated (since 6.0) this method returns an inaccurate result if the class or interface referenced cannot be      *             found: use referencesClassExact() instead      */
annotation|@
name|Deprecated
specifier|public
name|boolean
name|referencesClass
parameter_list|()
block|{
try|try
block|{
specifier|final
name|JavaClass
name|jc
init|=
name|Repository
operator|.
name|lookupClass
argument_list|(
name|className
argument_list|)
decl_stmt|;
return|return
name|jc
operator|.
name|isClass
argument_list|()
return|;
block|}
catch|catch
parameter_list|(
specifier|final
name|ClassNotFoundException
name|e
parameter_list|)
block|{
return|return
literal|false
return|;
block|}
block|}
comment|/**      * Return true if this type references a class, false if it references an interface.      *      * @return true if the type references a class, false if it references an interface      * @throws ClassNotFoundException if the class or interface referenced by this type can't be found      */
specifier|public
name|boolean
name|referencesClassExact
parameter_list|()
throws|throws
name|ClassNotFoundException
block|{
specifier|final
name|JavaClass
name|jc
init|=
name|Repository
operator|.
name|lookupClass
argument_list|(
name|className
argument_list|)
decl_stmt|;
return|return
name|jc
operator|.
name|isClass
argument_list|()
return|;
block|}
comment|/**      * If "this" doesn't reference an interface, it references a class or a non-existant entity.      *      * @deprecated (since 6.0) this method returns an inaccurate result if the class or interface referenced cannot be      *             found: use referencesInterfaceExact() instead      */
annotation|@
name|Deprecated
specifier|public
name|boolean
name|referencesInterface
parameter_list|()
block|{
try|try
block|{
specifier|final
name|JavaClass
name|jc
init|=
name|Repository
operator|.
name|lookupClass
argument_list|(
name|className
argument_list|)
decl_stmt|;
return|return
operator|!
name|jc
operator|.
name|isClass
argument_list|()
return|;
block|}
catch|catch
parameter_list|(
specifier|final
name|ClassNotFoundException
name|e
parameter_list|)
block|{
return|return
literal|false
return|;
block|}
block|}
comment|/**      * Return true if this type references an interface, false if it references a class.      *      * @return true if the type references an interface, false if it references a class      * @throws ClassNotFoundException if the class or interface referenced by this type can't be found      */
specifier|public
name|boolean
name|referencesInterfaceExact
parameter_list|()
throws|throws
name|ClassNotFoundException
block|{
specifier|final
name|JavaClass
name|jc
init|=
name|Repository
operator|.
name|lookupClass
argument_list|(
name|className
argument_list|)
decl_stmt|;
return|return
operator|!
name|jc
operator|.
name|isClass
argument_list|()
return|;
block|}
comment|/**      * Return true if this type is a subclass of given ObjectType.      *      * @throws ClassNotFoundException if any of this class's superclasses can't be found      */
specifier|public
name|boolean
name|subclassOf
parameter_list|(
specifier|final
name|ObjectType
name|superclass
parameter_list|)
throws|throws
name|ClassNotFoundException
block|{
if|if
condition|(
name|this
operator|.
name|referencesInterfaceExact
argument_list|()
operator|||
name|superclass
operator|.
name|referencesInterfaceExact
argument_list|()
condition|)
block|{
return|return
literal|false
return|;
block|}
return|return
name|Repository
operator|.
name|instanceOf
argument_list|(
name|this
operator|.
name|className
argument_list|,
name|superclass
operator|.
name|className
argument_list|)
return|;
block|}
block|}
end_class

end_unit

