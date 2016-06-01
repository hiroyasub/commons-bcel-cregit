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
name|commons
operator|.
name|bcel6
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
name|commons
operator|.
name|bcel6
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
name|commons
operator|.
name|bcel6
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
name|commons
operator|.
name|bcel6
operator|.
name|classfile
operator|.
name|JavaClass
import|;
end_import

begin_comment
comment|/**  * Super class for object and array types.  *  * @version $Id$  */
end_comment

begin_class
specifier|public
specifier|abstract
class|class
name|ReferenceType
extends|extends
name|Type
block|{
specifier|protected
name|ReferenceType
parameter_list|(
specifier|final
name|byte
name|t
parameter_list|,
specifier|final
name|String
name|s
parameter_list|)
block|{
name|super
argument_list|(
name|t
argument_list|,
name|s
argument_list|)
expr_stmt|;
block|}
comment|/** Class is non-abstract but not instantiable from the outside      */
name|ReferenceType
parameter_list|()
block|{
name|super
argument_list|(
name|Const
operator|.
name|T_OBJECT
argument_list|,
literal|"<null object>"
argument_list|)
expr_stmt|;
block|}
comment|/**      * Return true iff this type is castable to another type t as defined in      * the JVM specification.  The case where this is Type.NULL is not      * defined (see the CHECKCAST definition in the JVM specification).      * However, because e.g. CHECKCAST doesn't throw a      * ClassCastException when casting a null reference to any Object,      * true is returned in this case.      *      * @throws ClassNotFoundException if any classes or interfaces required      *  to determine assignment compatibility can't be found      */
specifier|public
name|boolean
name|isCastableTo
parameter_list|(
specifier|final
name|Type
name|t
parameter_list|)
throws|throws
name|ClassNotFoundException
block|{
if|if
condition|(
name|this
operator|.
name|equals
argument_list|(
name|Type
operator|.
name|NULL
argument_list|)
condition|)
block|{
return|return
name|t
operator|instanceof
name|ReferenceType
return|;
comment|// If this is ever changed in isAssignmentCompatible()
block|}
return|return
name|isAssignmentCompatibleWith
argument_list|(
name|t
argument_list|)
return|;
comment|/* Yes, it's true: It's the same definition.          * See vmspec2 AASTORE / CHECKCAST definitions.          */
block|}
comment|/**      * Return true iff this is assignment compatible with another type t      * as defined in the JVM specification; see the AASTORE definition      * there.      * @throws ClassNotFoundException if any classes or interfaces required      *  to determine assignment compatibility can't be found      */
specifier|public
name|boolean
name|isAssignmentCompatibleWith
parameter_list|(
specifier|final
name|Type
name|t
parameter_list|)
throws|throws
name|ClassNotFoundException
block|{
if|if
condition|(
operator|!
operator|(
name|t
operator|instanceof
name|ReferenceType
operator|)
condition|)
block|{
return|return
literal|false
return|;
block|}
name|ReferenceType
name|T
init|=
operator|(
name|ReferenceType
operator|)
name|t
decl_stmt|;
if|if
condition|(
name|this
operator|.
name|equals
argument_list|(
name|Type
operator|.
name|NULL
argument_list|)
condition|)
block|{
return|return
literal|true
return|;
comment|// This is not explicitely stated, but clear. Isn't it?
block|}
comment|/* If this is a class type then          */
if|if
condition|(
operator|(
name|this
operator|instanceof
name|ObjectType
operator|)
operator|&&
operator|(
operator|(
operator|(
name|ObjectType
operator|)
name|this
operator|)
operator|.
name|referencesClassExact
argument_list|()
operator|)
condition|)
block|{
comment|/* If T is a class type, then this must be the same class as T,              or this must be a subclass of T;              */
if|if
condition|(
operator|(
name|T
operator|instanceof
name|ObjectType
operator|)
operator|&&
operator|(
operator|(
operator|(
name|ObjectType
operator|)
name|T
operator|)
operator|.
name|referencesClassExact
argument_list|()
operator|)
condition|)
block|{
if|if
condition|(
name|this
operator|.
name|equals
argument_list|(
name|T
argument_list|)
condition|)
block|{
return|return
literal|true
return|;
block|}
if|if
condition|(
name|Repository
operator|.
name|instanceOf
argument_list|(
operator|(
operator|(
name|ObjectType
operator|)
name|this
operator|)
operator|.
name|getClassName
argument_list|()
argument_list|,
operator|(
operator|(
name|ObjectType
operator|)
name|T
operator|)
operator|.
name|getClassName
argument_list|()
argument_list|)
condition|)
block|{
return|return
literal|true
return|;
block|}
block|}
comment|/* If T is an interface type, this must implement interface T.              */
if|if
condition|(
operator|(
name|T
operator|instanceof
name|ObjectType
operator|)
operator|&&
operator|(
operator|(
operator|(
name|ObjectType
operator|)
name|T
operator|)
operator|.
name|referencesInterfaceExact
argument_list|()
operator|)
condition|)
block|{
if|if
condition|(
name|Repository
operator|.
name|implementationOf
argument_list|(
operator|(
operator|(
name|ObjectType
operator|)
name|this
operator|)
operator|.
name|getClassName
argument_list|()
argument_list|,
operator|(
operator|(
name|ObjectType
operator|)
name|T
operator|)
operator|.
name|getClassName
argument_list|()
argument_list|)
condition|)
block|{
return|return
literal|true
return|;
block|}
block|}
block|}
comment|/* If this is an interface type, then:          */
if|if
condition|(
operator|(
name|this
operator|instanceof
name|ObjectType
operator|)
operator|&&
operator|(
operator|(
operator|(
name|ObjectType
operator|)
name|this
operator|)
operator|.
name|referencesInterfaceExact
argument_list|()
operator|)
condition|)
block|{
comment|/* If T is a class type, then T must be Object (ï¿½2.4.7).              */
if|if
condition|(
operator|(
name|T
operator|instanceof
name|ObjectType
operator|)
operator|&&
operator|(
operator|(
operator|(
name|ObjectType
operator|)
name|T
operator|)
operator|.
name|referencesClassExact
argument_list|()
operator|)
condition|)
block|{
if|if
condition|(
name|T
operator|.
name|equals
argument_list|(
name|Type
operator|.
name|OBJECT
argument_list|)
condition|)
block|{
return|return
literal|true
return|;
block|}
block|}
comment|/* If T is an interface type, then T must be the same interface              * as this or a superinterface of this (ï¿½2.13.2).              */
if|if
condition|(
operator|(
name|T
operator|instanceof
name|ObjectType
operator|)
operator|&&
operator|(
operator|(
operator|(
name|ObjectType
operator|)
name|T
operator|)
operator|.
name|referencesInterfaceExact
argument_list|()
operator|)
condition|)
block|{
if|if
condition|(
name|this
operator|.
name|equals
argument_list|(
name|T
argument_list|)
condition|)
block|{
return|return
literal|true
return|;
block|}
if|if
condition|(
name|Repository
operator|.
name|implementationOf
argument_list|(
operator|(
operator|(
name|ObjectType
operator|)
name|this
operator|)
operator|.
name|getClassName
argument_list|()
argument_list|,
operator|(
operator|(
name|ObjectType
operator|)
name|T
operator|)
operator|.
name|getClassName
argument_list|()
argument_list|)
condition|)
block|{
return|return
literal|true
return|;
block|}
block|}
block|}
comment|/* If this is an array type, namely, the type SC[], that is, an          * array of components of type SC, then:          */
if|if
condition|(
name|this
operator|instanceof
name|ArrayType
condition|)
block|{
comment|/* If T is a class type, then T must be Object (ï¿½2.4.7).              */
if|if
condition|(
operator|(
name|T
operator|instanceof
name|ObjectType
operator|)
operator|&&
operator|(
operator|(
operator|(
name|ObjectType
operator|)
name|T
operator|)
operator|.
name|referencesClassExact
argument_list|()
operator|)
condition|)
block|{
if|if
condition|(
name|T
operator|.
name|equals
argument_list|(
name|Type
operator|.
name|OBJECT
argument_list|)
condition|)
block|{
return|return
literal|true
return|;
block|}
block|}
comment|/* If T is an array type TC[], that is, an array of components              * of type TC, then one of the following must be true:              */
if|if
condition|(
name|T
operator|instanceof
name|ArrayType
condition|)
block|{
comment|/* TC and SC are the same primitive type (ï¿½2.4.1).                  */
name|Type
name|sc
init|=
operator|(
operator|(
name|ArrayType
operator|)
name|this
operator|)
operator|.
name|getElementType
argument_list|()
decl_stmt|;
name|Type
name|tc
init|=
operator|(
operator|(
name|ArrayType
operator|)
name|T
operator|)
operator|.
name|getElementType
argument_list|()
decl_stmt|;
if|if
condition|(
name|sc
operator|instanceof
name|BasicType
operator|&&
name|tc
operator|instanceof
name|BasicType
operator|&&
name|sc
operator|.
name|equals
argument_list|(
name|tc
argument_list|)
condition|)
block|{
return|return
literal|true
return|;
block|}
comment|/* TC and SC are reference types (ï¿½2.4.6), and type SC is                  * assignable to TC by these runtime rules.                  */
if|if
condition|(
name|tc
operator|instanceof
name|ReferenceType
operator|&&
name|sc
operator|instanceof
name|ReferenceType
operator|&&
operator|(
operator|(
name|ReferenceType
operator|)
name|sc
operator|)
operator|.
name|isAssignmentCompatibleWith
argument_list|(
name|tc
argument_list|)
condition|)
block|{
return|return
literal|true
return|;
block|}
block|}
comment|/* If T is an interface type, T must be one of the interfaces implemented by arrays (ï¿½2.15). */
comment|// TODO: Check if this is still valid or find a way to dynamically find out which
comment|// interfaces arrays implement. However, as of the JVM specification edition 2, there
comment|// are at least two different pages where assignment compatibility is defined and
comment|// on one of them "interfaces implemented by arrays" is exchanged with "'Cloneable' or
comment|// 'java.io.Serializable'"
if|if
condition|(
operator|(
name|T
operator|instanceof
name|ObjectType
operator|)
operator|&&
operator|(
operator|(
operator|(
name|ObjectType
operator|)
name|T
operator|)
operator|.
name|referencesInterfaceExact
argument_list|()
operator|)
condition|)
block|{
for|for
control|(
name|String
name|element
range|:
name|Const
operator|.
name|getInterfacesImplementedByArrays
argument_list|()
control|)
block|{
if|if
condition|(
name|T
operator|.
name|equals
argument_list|(
name|ObjectType
operator|.
name|getInstance
argument_list|(
name|element
argument_list|)
argument_list|)
condition|)
block|{
return|return
literal|true
return|;
block|}
block|}
block|}
block|}
return|return
literal|false
return|;
comment|// default.
block|}
comment|/**      * This commutative operation returns the first common superclass (narrowest ReferenceType      * referencing a class, not an interface).      * If one of the types is a superclass of the other, the former is returned.      * If "this" is Type.NULL, then t is returned.      * If t is Type.NULL, then "this" is returned.      * If "this" equals t ['this.equals(t)'] "this" is returned.      * If "this" or t is an ArrayType, then Type.OBJECT is returned;      * unless their dimensions match. Then an ArrayType of the same      * number of dimensions is returned, with its basic type being the      * first common super class of the basic types of "this" and t.      * If "this" or t is a ReferenceType referencing an interface, then Type.OBJECT is returned.      * If not all of the two classes' superclasses cannot be found, "null" is returned.      * See the JVM specification edition 2, "ï¿½4.9.2 The Bytecode Verifier".      *      * @throws ClassNotFoundException on failure to find superclasses of this      *  type, or the type passed as a parameter      */
specifier|public
name|ReferenceType
name|getFirstCommonSuperclass
parameter_list|(
specifier|final
name|ReferenceType
name|t
parameter_list|)
throws|throws
name|ClassNotFoundException
block|{
if|if
condition|(
name|this
operator|.
name|equals
argument_list|(
name|Type
operator|.
name|NULL
argument_list|)
condition|)
block|{
return|return
name|t
return|;
block|}
if|if
condition|(
name|t
operator|.
name|equals
argument_list|(
name|Type
operator|.
name|NULL
argument_list|)
condition|)
block|{
return|return
name|this
return|;
block|}
if|if
condition|(
name|this
operator|.
name|equals
argument_list|(
name|t
argument_list|)
condition|)
block|{
return|return
name|this
return|;
comment|/*              * TODO: Above sounds a little arbitrary. On the other hand, there is              * no object referenced by Type.NULL so we can also say all the objects              * referenced by Type.NULL were derived from java.lang.Object.              * However, the Java Language's "instanceof" operator proves us wrong:              * "null" is not referring to an instance of java.lang.Object :)              */
block|}
comment|/* This code is from a bug report by Konstantin Shagin<konst@cs.technion.ac.il> */
if|if
condition|(
operator|(
name|this
operator|instanceof
name|ArrayType
operator|)
operator|&&
operator|(
name|t
operator|instanceof
name|ArrayType
operator|)
condition|)
block|{
name|ArrayType
name|arrType1
init|=
operator|(
name|ArrayType
operator|)
name|this
decl_stmt|;
name|ArrayType
name|arrType2
init|=
operator|(
name|ArrayType
operator|)
name|t
decl_stmt|;
if|if
condition|(
operator|(
name|arrType1
operator|.
name|getDimensions
argument_list|()
operator|==
name|arrType2
operator|.
name|getDimensions
argument_list|()
operator|)
operator|&&
name|arrType1
operator|.
name|getBasicType
argument_list|()
operator|instanceof
name|ObjectType
operator|&&
name|arrType2
operator|.
name|getBasicType
argument_list|()
operator|instanceof
name|ObjectType
condition|)
block|{
return|return
operator|new
name|ArrayType
argument_list|(
operator|(
operator|(
name|ObjectType
operator|)
name|arrType1
operator|.
name|getBasicType
argument_list|()
operator|)
operator|.
name|getFirstCommonSuperclass
argument_list|(
operator|(
name|ObjectType
operator|)
name|arrType2
operator|.
name|getBasicType
argument_list|()
argument_list|)
argument_list|,
name|arrType1
operator|.
name|getDimensions
argument_list|()
argument_list|)
return|;
block|}
block|}
if|if
condition|(
operator|(
name|this
operator|instanceof
name|ArrayType
operator|)
operator|||
operator|(
name|t
operator|instanceof
name|ArrayType
operator|)
condition|)
block|{
return|return
name|Type
operator|.
name|OBJECT
return|;
comment|// TODO: Is there a proof of OBJECT being the direct ancestor of every ArrayType?
block|}
if|if
condition|(
operator|(
operator|(
name|this
operator|instanceof
name|ObjectType
operator|)
operator|&&
operator|(
operator|(
name|ObjectType
operator|)
name|this
operator|)
operator|.
name|referencesInterfaceExact
argument_list|()
operator|)
operator|||
operator|(
operator|(
name|t
operator|instanceof
name|ObjectType
operator|)
operator|&&
operator|(
operator|(
name|ObjectType
operator|)
name|t
operator|)
operator|.
name|referencesInterfaceExact
argument_list|()
operator|)
condition|)
block|{
return|return
name|Type
operator|.
name|OBJECT
return|;
comment|// TODO: The above line is correct comparing to the vmspec2. But one could
comment|// make class file verification a bit stronger here by using the notion of
comment|// superinterfaces or even castability or assignment compatibility.
block|}
comment|// this and t are ObjectTypes, see above.
name|ObjectType
name|thiz
init|=
operator|(
name|ObjectType
operator|)
name|this
decl_stmt|;
name|ObjectType
name|other
init|=
operator|(
name|ObjectType
operator|)
name|t
decl_stmt|;
name|JavaClass
index|[]
name|thiz_sups
init|=
name|Repository
operator|.
name|getSuperClasses
argument_list|(
name|thiz
operator|.
name|getClassName
argument_list|()
argument_list|)
decl_stmt|;
name|JavaClass
index|[]
name|other_sups
init|=
name|Repository
operator|.
name|getSuperClasses
argument_list|(
name|other
operator|.
name|getClassName
argument_list|()
argument_list|)
decl_stmt|;
if|if
condition|(
operator|(
name|thiz_sups
operator|==
literal|null
operator|)
operator|||
operator|(
name|other_sups
operator|==
literal|null
operator|)
condition|)
block|{
return|return
literal|null
return|;
block|}
comment|// Waaahh...
name|JavaClass
index|[]
name|this_sups
init|=
operator|new
name|JavaClass
index|[
name|thiz_sups
operator|.
name|length
operator|+
literal|1
index|]
decl_stmt|;
name|JavaClass
index|[]
name|t_sups
init|=
operator|new
name|JavaClass
index|[
name|other_sups
operator|.
name|length
operator|+
literal|1
index|]
decl_stmt|;
name|System
operator|.
name|arraycopy
argument_list|(
name|thiz_sups
argument_list|,
literal|0
argument_list|,
name|this_sups
argument_list|,
literal|1
argument_list|,
name|thiz_sups
operator|.
name|length
argument_list|)
expr_stmt|;
name|System
operator|.
name|arraycopy
argument_list|(
name|other_sups
argument_list|,
literal|0
argument_list|,
name|t_sups
argument_list|,
literal|1
argument_list|,
name|other_sups
operator|.
name|length
argument_list|)
expr_stmt|;
name|this_sups
index|[
literal|0
index|]
operator|=
name|Repository
operator|.
name|lookupClass
argument_list|(
name|thiz
operator|.
name|getClassName
argument_list|()
argument_list|)
expr_stmt|;
name|t_sups
index|[
literal|0
index|]
operator|=
name|Repository
operator|.
name|lookupClass
argument_list|(
name|other
operator|.
name|getClassName
argument_list|()
argument_list|)
expr_stmt|;
for|for
control|(
name|JavaClass
name|t_sup
range|:
name|t_sups
control|)
block|{
for|for
control|(
name|JavaClass
name|this_sup
range|:
name|this_sups
control|)
block|{
if|if
condition|(
name|this_sup
operator|.
name|equals
argument_list|(
name|t_sup
argument_list|)
condition|)
block|{
return|return
name|ObjectType
operator|.
name|getInstance
argument_list|(
name|this_sup
operator|.
name|getClassName
argument_list|()
argument_list|)
return|;
block|}
block|}
block|}
comment|// Huh? Did you ask for Type.OBJECT's superclass??
return|return
literal|null
return|;
block|}
comment|/**      * This commutative operation returns the first common superclass (narrowest ReferenceType      * referencing a class, not an interface).      * If one of the types is a superclass of the other, the former is returned.      * If "this" is Type.NULL, then t is returned.      * If t is Type.NULL, then "this" is returned.      * If "this" equals t ['this.equals(t)'] "this" is returned.      * If "this" or t is an ArrayType, then Type.OBJECT is returned.      * If "this" or t is a ReferenceType referencing an interface, then Type.OBJECT is returned.      * If not all of the two classes' superclasses cannot be found, "null" is returned.      * See the JVM specification edition 2, "ï¿½4.9.2 The Bytecode Verifier".      *      * @deprecated use getFirstCommonSuperclass(ReferenceType t) which has      *             slightly changed semantics.      * @throws ClassNotFoundException on failure to find superclasses of this      *  type, or the type passed as a parameter      */
annotation|@
name|Deprecated
specifier|public
name|ReferenceType
name|firstCommonSuperclass
parameter_list|(
specifier|final
name|ReferenceType
name|t
parameter_list|)
throws|throws
name|ClassNotFoundException
block|{
if|if
condition|(
name|this
operator|.
name|equals
argument_list|(
name|Type
operator|.
name|NULL
argument_list|)
condition|)
block|{
return|return
name|t
return|;
block|}
if|if
condition|(
name|t
operator|.
name|equals
argument_list|(
name|Type
operator|.
name|NULL
argument_list|)
condition|)
block|{
return|return
name|this
return|;
block|}
if|if
condition|(
name|this
operator|.
name|equals
argument_list|(
name|t
argument_list|)
condition|)
block|{
return|return
name|this
return|;
comment|/*              * TODO: Above sounds a little arbitrary. On the other hand, there is              * no object referenced by Type.NULL so we can also say all the objects              * referenced by Type.NULL were derived from java.lang.Object.              * However, the Java Language's "instanceof" operator proves us wrong:              * "null" is not referring to an instance of java.lang.Object :)              */
block|}
if|if
condition|(
operator|(
name|this
operator|instanceof
name|ArrayType
operator|)
operator|||
operator|(
name|t
operator|instanceof
name|ArrayType
operator|)
condition|)
block|{
return|return
name|Type
operator|.
name|OBJECT
return|;
comment|// TODO: Is there a proof of OBJECT being the direct ancestor of every ArrayType?
block|}
if|if
condition|(
operator|(
operator|(
name|this
operator|instanceof
name|ObjectType
operator|)
operator|&&
operator|(
operator|(
name|ObjectType
operator|)
name|this
operator|)
operator|.
name|referencesInterface
argument_list|()
operator|)
operator|||
operator|(
operator|(
name|t
operator|instanceof
name|ObjectType
operator|)
operator|&&
operator|(
operator|(
name|ObjectType
operator|)
name|t
operator|)
operator|.
name|referencesInterface
argument_list|()
operator|)
condition|)
block|{
return|return
name|Type
operator|.
name|OBJECT
return|;
comment|// TODO: The above line is correct comparing to the vmspec2. But one could
comment|// make class file verification a bit stronger here by using the notion of
comment|// superinterfaces or even castability or assignment compatibility.
block|}
comment|// this and t are ObjectTypes, see above.
name|ObjectType
name|thiz
init|=
operator|(
name|ObjectType
operator|)
name|this
decl_stmt|;
name|ObjectType
name|other
init|=
operator|(
name|ObjectType
operator|)
name|t
decl_stmt|;
name|JavaClass
index|[]
name|thiz_sups
init|=
name|Repository
operator|.
name|getSuperClasses
argument_list|(
name|thiz
operator|.
name|getClassName
argument_list|()
argument_list|)
decl_stmt|;
name|JavaClass
index|[]
name|other_sups
init|=
name|Repository
operator|.
name|getSuperClasses
argument_list|(
name|other
operator|.
name|getClassName
argument_list|()
argument_list|)
decl_stmt|;
if|if
condition|(
operator|(
name|thiz_sups
operator|==
literal|null
operator|)
operator|||
operator|(
name|other_sups
operator|==
literal|null
operator|)
condition|)
block|{
return|return
literal|null
return|;
block|}
comment|// Waaahh...
name|JavaClass
index|[]
name|this_sups
init|=
operator|new
name|JavaClass
index|[
name|thiz_sups
operator|.
name|length
operator|+
literal|1
index|]
decl_stmt|;
name|JavaClass
index|[]
name|t_sups
init|=
operator|new
name|JavaClass
index|[
name|other_sups
operator|.
name|length
operator|+
literal|1
index|]
decl_stmt|;
name|System
operator|.
name|arraycopy
argument_list|(
name|thiz_sups
argument_list|,
literal|0
argument_list|,
name|this_sups
argument_list|,
literal|1
argument_list|,
name|thiz_sups
operator|.
name|length
argument_list|)
expr_stmt|;
name|System
operator|.
name|arraycopy
argument_list|(
name|other_sups
argument_list|,
literal|0
argument_list|,
name|t_sups
argument_list|,
literal|1
argument_list|,
name|other_sups
operator|.
name|length
argument_list|)
expr_stmt|;
name|this_sups
index|[
literal|0
index|]
operator|=
name|Repository
operator|.
name|lookupClass
argument_list|(
name|thiz
operator|.
name|getClassName
argument_list|()
argument_list|)
expr_stmt|;
name|t_sups
index|[
literal|0
index|]
operator|=
name|Repository
operator|.
name|lookupClass
argument_list|(
name|other
operator|.
name|getClassName
argument_list|()
argument_list|)
expr_stmt|;
for|for
control|(
name|JavaClass
name|t_sup
range|:
name|t_sups
control|)
block|{
for|for
control|(
name|JavaClass
name|this_sup
range|:
name|this_sups
control|)
block|{
if|if
condition|(
name|this_sup
operator|.
name|equals
argument_list|(
name|t_sup
argument_list|)
condition|)
block|{
return|return
name|ObjectType
operator|.
name|getInstance
argument_list|(
name|this_sup
operator|.
name|getClassName
argument_list|()
argument_list|)
return|;
block|}
block|}
block|}
comment|// Huh? Did you ask for Type.OBJECT's superclass??
return|return
literal|null
return|;
block|}
block|}
end_class

end_unit

