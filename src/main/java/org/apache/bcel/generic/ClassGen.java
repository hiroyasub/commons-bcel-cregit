begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
begin_comment
comment|/*  * Licensed to the Apache Software Foundation (ASF) under one or more  * contributor license agreements.  See the NOTICE file distributed with  * this work for additional information regarding copyright ownership.  * The ASF licenses this file to You under the Apache License, Version 2.0  * (the "License"); you may not use this file except in compliance with  * the License.  You may obtain a copy of the License at  *  *      http://www.apache.org/licenses/LICENSE-2.0  *  *  Unless required by applicable law or agreed to in writing, software  *  distributed under the License is distributed on an "AS IS" BASIS,  *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  *  See the License for the specific language governing permissions and  *  limitations under the License.   *  */
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
name|java
operator|.
name|util
operator|.
name|ArrayList
import|;
end_import

begin_import
import|import
name|java
operator|.
name|util
operator|.
name|Iterator
import|;
end_import

begin_import
import|import
name|java
operator|.
name|util
operator|.
name|List
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
name|Constants
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
name|AccessFlags
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
name|AnnotationEntry
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
name|Annotations
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
name|Attribute
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
name|Field
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
name|Method
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
name|RuntimeInvisibleAnnotations
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
name|RuntimeVisibleAnnotations
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
name|SourceFile
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
name|BCELComparator
import|;
end_import

begin_comment
comment|/**   * Template class for building up a java class. May be initialized with an  * existing java class (file).  *  * @see JavaClass  * @version $Id$  * @author<A HREF="mailto:m.dahm@gmx.de">M. Dahm</A>  */
end_comment

begin_class
specifier|public
class|class
name|ClassGen
extends|extends
name|AccessFlags
implements|implements
name|Cloneable
block|{
specifier|private
specifier|static
specifier|final
name|long
name|serialVersionUID
init|=
literal|6880879387392827211L
decl_stmt|;
comment|/* Corresponds to the fields found in a JavaClass object.      */
specifier|private
name|String
name|class_name
decl_stmt|,
name|super_class_name
decl_stmt|,
name|file_name
decl_stmt|;
specifier|private
name|int
name|class_name_index
init|=
operator|-
literal|1
decl_stmt|,
name|superclass_name_index
init|=
operator|-
literal|1
decl_stmt|;
specifier|private
name|int
name|major
init|=
name|Constants
operator|.
name|MAJOR_1_1
decl_stmt|,
name|minor
init|=
name|Constants
operator|.
name|MINOR_1_1
decl_stmt|;
specifier|private
name|ConstantPoolGen
name|cp
decl_stmt|;
comment|// Template for building up constant pool
comment|// ArrayLists instead of arrays to gather fields, methods, etc.
specifier|private
name|List
argument_list|<
name|Field
argument_list|>
name|field_vec
init|=
operator|new
name|ArrayList
argument_list|<
name|Field
argument_list|>
argument_list|()
decl_stmt|;
specifier|private
name|List
argument_list|<
name|Method
argument_list|>
name|method_vec
init|=
operator|new
name|ArrayList
argument_list|<
name|Method
argument_list|>
argument_list|()
decl_stmt|;
specifier|private
name|List
argument_list|<
name|Attribute
argument_list|>
name|attribute_vec
init|=
operator|new
name|ArrayList
argument_list|<
name|Attribute
argument_list|>
argument_list|()
decl_stmt|;
specifier|private
name|List
argument_list|<
name|String
argument_list|>
name|interface_vec
init|=
operator|new
name|ArrayList
argument_list|<
name|String
argument_list|>
argument_list|()
decl_stmt|;
specifier|private
name|List
argument_list|<
name|AnnotationEntryGen
argument_list|>
name|annotation_vec
init|=
operator|new
name|ArrayList
argument_list|<
name|AnnotationEntryGen
argument_list|>
argument_list|()
decl_stmt|;
specifier|private
specifier|static
name|BCELComparator
name|_cmp
init|=
operator|new
name|BCELComparator
argument_list|()
block|{
specifier|public
name|boolean
name|equals
parameter_list|(
name|Object
name|o1
parameter_list|,
name|Object
name|o2
parameter_list|)
block|{
name|ClassGen
name|THIS
init|=
operator|(
name|ClassGen
operator|)
name|o1
decl_stmt|;
name|ClassGen
name|THAT
init|=
operator|(
name|ClassGen
operator|)
name|o2
decl_stmt|;
return|return
name|THIS
operator|.
name|getClassName
argument_list|()
operator|.
name|equals
argument_list|(
name|THAT
operator|.
name|getClassName
argument_list|()
argument_list|)
return|;
block|}
specifier|public
name|int
name|hashCode
parameter_list|(
name|Object
name|o
parameter_list|)
block|{
name|ClassGen
name|THIS
init|=
operator|(
name|ClassGen
operator|)
name|o
decl_stmt|;
return|return
name|THIS
operator|.
name|getClassName
argument_list|()
operator|.
name|hashCode
argument_list|()
return|;
block|}
block|}
decl_stmt|;
comment|/** Convenience constructor to set up some important values initially.      *      * @param class_name fully qualified class name      * @param super_class_name fully qualified superclass name      * @param file_name source file name      * @param access_flags access qualifiers      * @param interfaces implemented interfaces      * @param cp constant pool to use      */
specifier|public
name|ClassGen
parameter_list|(
name|String
name|class_name
parameter_list|,
name|String
name|super_class_name
parameter_list|,
name|String
name|file_name
parameter_list|,
name|int
name|access_flags
parameter_list|,
name|String
index|[]
name|interfaces
parameter_list|,
name|ConstantPoolGen
name|cp
parameter_list|)
block|{
name|this
operator|.
name|class_name
operator|=
name|class_name
expr_stmt|;
name|this
operator|.
name|super_class_name
operator|=
name|super_class_name
expr_stmt|;
name|this
operator|.
name|file_name
operator|=
name|file_name
expr_stmt|;
name|this
operator|.
name|access_flags
operator|=
name|access_flags
expr_stmt|;
name|this
operator|.
name|cp
operator|=
name|cp
expr_stmt|;
comment|// Put everything needed by default into the constant pool and the vectors
if|if
condition|(
name|file_name
operator|!=
literal|null
condition|)
block|{
name|addAttribute
argument_list|(
operator|new
name|SourceFile
argument_list|(
name|cp
operator|.
name|addUtf8
argument_list|(
literal|"SourceFile"
argument_list|)
argument_list|,
literal|2
argument_list|,
name|cp
operator|.
name|addUtf8
argument_list|(
name|file_name
argument_list|)
argument_list|,
name|cp
operator|.
name|getConstantPool
argument_list|()
argument_list|)
argument_list|)
expr_stmt|;
block|}
name|class_name_index
operator|=
name|cp
operator|.
name|addClass
argument_list|(
name|class_name
argument_list|)
expr_stmt|;
name|superclass_name_index
operator|=
name|cp
operator|.
name|addClass
argument_list|(
name|super_class_name
argument_list|)
expr_stmt|;
if|if
condition|(
name|interfaces
operator|!=
literal|null
condition|)
block|{
for|for
control|(
name|int
name|i
init|=
literal|0
init|;
name|i
operator|<
name|interfaces
operator|.
name|length
condition|;
name|i
operator|++
control|)
block|{
name|addInterface
argument_list|(
name|interfaces
index|[
name|i
index|]
argument_list|)
expr_stmt|;
block|}
block|}
block|}
comment|/** Convenience constructor to set up some important values initially.      *      * @param class_name fully qualified class name      * @param super_class_name fully qualified superclass name      * @param file_name source file name      * @param access_flags access qualifiers      * @param interfaces implemented interfaces      */
specifier|public
name|ClassGen
parameter_list|(
name|String
name|class_name
parameter_list|,
name|String
name|super_class_name
parameter_list|,
name|String
name|file_name
parameter_list|,
name|int
name|access_flags
parameter_list|,
name|String
index|[]
name|interfaces
parameter_list|)
block|{
name|this
argument_list|(
name|class_name
argument_list|,
name|super_class_name
argument_list|,
name|file_name
argument_list|,
name|access_flags
argument_list|,
name|interfaces
argument_list|,
operator|new
name|ConstantPoolGen
argument_list|()
argument_list|)
expr_stmt|;
block|}
comment|/**      * Initialize with existing class.      * @param clazz JavaClass object (e.g. read from file)      */
specifier|public
name|ClassGen
parameter_list|(
name|JavaClass
name|clazz
parameter_list|)
block|{
name|class_name_index
operator|=
name|clazz
operator|.
name|getClassNameIndex
argument_list|()
expr_stmt|;
name|superclass_name_index
operator|=
name|clazz
operator|.
name|getSuperclassNameIndex
argument_list|()
expr_stmt|;
name|class_name
operator|=
name|clazz
operator|.
name|getClassName
argument_list|()
expr_stmt|;
name|super_class_name
operator|=
name|clazz
operator|.
name|getSuperclassName
argument_list|()
expr_stmt|;
name|file_name
operator|=
name|clazz
operator|.
name|getSourceFileName
argument_list|()
expr_stmt|;
name|access_flags
operator|=
name|clazz
operator|.
name|getAccessFlags
argument_list|()
expr_stmt|;
name|cp
operator|=
operator|new
name|ConstantPoolGen
argument_list|(
name|clazz
operator|.
name|getConstantPool
argument_list|()
argument_list|)
expr_stmt|;
name|major
operator|=
name|clazz
operator|.
name|getMajor
argument_list|()
expr_stmt|;
name|minor
operator|=
name|clazz
operator|.
name|getMinor
argument_list|()
expr_stmt|;
name|Attribute
index|[]
name|attributes
init|=
name|clazz
operator|.
name|getAttributes
argument_list|()
decl_stmt|;
comment|// J5TODO: Could make unpacking lazy, done on first reference
name|AnnotationEntryGen
index|[]
name|annotations
init|=
name|unpackAnnotations
argument_list|(
name|attributes
argument_list|)
decl_stmt|;
name|Method
index|[]
name|methods
init|=
name|clazz
operator|.
name|getMethods
argument_list|()
decl_stmt|;
name|Field
index|[]
name|fields
init|=
name|clazz
operator|.
name|getFields
argument_list|()
decl_stmt|;
name|String
index|[]
name|interfaces
init|=
name|clazz
operator|.
name|getInterfaceNames
argument_list|()
decl_stmt|;
for|for
control|(
name|int
name|i
init|=
literal|0
init|;
name|i
operator|<
name|interfaces
operator|.
name|length
condition|;
name|i
operator|++
control|)
block|{
name|addInterface
argument_list|(
name|interfaces
index|[
name|i
index|]
argument_list|)
expr_stmt|;
block|}
for|for
control|(
name|int
name|i
init|=
literal|0
init|;
name|i
operator|<
name|attributes
operator|.
name|length
condition|;
name|i
operator|++
control|)
block|{
if|if
condition|(
operator|!
operator|(
name|attributes
index|[
name|i
index|]
operator|instanceof
name|Annotations
operator|)
condition|)
block|{
name|addAttribute
argument_list|(
name|attributes
index|[
name|i
index|]
argument_list|)
expr_stmt|;
block|}
block|}
for|for
control|(
name|int
name|i
init|=
literal|0
init|;
name|i
operator|<
name|annotations
operator|.
name|length
condition|;
name|i
operator|++
control|)
block|{
name|addAnnotationEntry
argument_list|(
name|annotations
index|[
name|i
index|]
argument_list|)
expr_stmt|;
block|}
for|for
control|(
name|int
name|i
init|=
literal|0
init|;
name|i
operator|<
name|methods
operator|.
name|length
condition|;
name|i
operator|++
control|)
block|{
name|addMethod
argument_list|(
name|methods
index|[
name|i
index|]
argument_list|)
expr_stmt|;
block|}
for|for
control|(
name|int
name|i
init|=
literal|0
init|;
name|i
operator|<
name|fields
operator|.
name|length
condition|;
name|i
operator|++
control|)
block|{
name|addField
argument_list|(
name|fields
index|[
name|i
index|]
argument_list|)
expr_stmt|;
block|}
block|}
comment|/** 	 * Look for attributes representing annotations and unpack them. 	 */
specifier|private
name|AnnotationEntryGen
index|[]
name|unpackAnnotations
parameter_list|(
name|Attribute
index|[]
name|attrs
parameter_list|)
block|{
name|List
argument_list|<
name|AnnotationEntryGen
argument_list|>
name|annotationGenObjs
init|=
operator|new
name|ArrayList
argument_list|<
name|AnnotationEntryGen
argument_list|>
argument_list|()
decl_stmt|;
for|for
control|(
name|int
name|i
init|=
literal|0
init|;
name|i
operator|<
name|attrs
operator|.
name|length
condition|;
name|i
operator|++
control|)
block|{
name|Attribute
name|attr
init|=
name|attrs
index|[
name|i
index|]
decl_stmt|;
if|if
condition|(
name|attr
operator|instanceof
name|RuntimeVisibleAnnotations
condition|)
block|{
name|RuntimeVisibleAnnotations
name|rva
init|=
operator|(
name|RuntimeVisibleAnnotations
operator|)
name|attr
decl_stmt|;
name|AnnotationEntry
index|[]
name|annos
init|=
name|rva
operator|.
name|getAnnotationEntries
argument_list|()
decl_stmt|;
for|for
control|(
name|int
name|j
init|=
literal|0
init|;
name|j
operator|<
name|annos
operator|.
name|length
condition|;
name|j
operator|++
control|)
block|{
name|AnnotationEntry
name|a
init|=
name|annos
index|[
name|j
index|]
decl_stmt|;
name|annotationGenObjs
operator|.
name|add
argument_list|(
operator|new
name|AnnotationEntryGen
argument_list|(
name|a
argument_list|,
name|getConstantPool
argument_list|()
argument_list|,
literal|false
argument_list|)
argument_list|)
expr_stmt|;
block|}
block|}
if|else 				if
condition|(
name|attr
operator|instanceof
name|RuntimeInvisibleAnnotations
condition|)
block|{
name|RuntimeInvisibleAnnotations
name|ria
init|=
operator|(
name|RuntimeInvisibleAnnotations
operator|)
name|attr
decl_stmt|;
name|AnnotationEntry
index|[]
name|annos
init|=
name|ria
operator|.
name|getAnnotationEntries
argument_list|()
decl_stmt|;
for|for
control|(
name|int
name|j
init|=
literal|0
init|;
name|j
operator|<
name|annos
operator|.
name|length
condition|;
name|j
operator|++
control|)
block|{
name|AnnotationEntry
name|a
init|=
name|annos
index|[
name|j
index|]
decl_stmt|;
name|annotationGenObjs
operator|.
name|add
argument_list|(
operator|new
name|AnnotationEntryGen
argument_list|(
name|a
argument_list|,
name|getConstantPool
argument_list|()
argument_list|,
literal|false
argument_list|)
argument_list|)
expr_stmt|;
block|}
block|}
block|}
return|return
name|annotationGenObjs
operator|.
name|toArray
argument_list|(
operator|new
name|AnnotationEntryGen
index|[
name|annotationGenObjs
operator|.
name|size
argument_list|()
index|]
argument_list|)
return|;
block|}
comment|/**      * @return the (finally) built up Java class object.      */
specifier|public
name|JavaClass
name|getJavaClass
parameter_list|()
block|{
name|int
index|[]
name|interfaces
init|=
name|getInterfaces
argument_list|()
decl_stmt|;
name|Field
index|[]
name|fields
init|=
name|getFields
argument_list|()
decl_stmt|;
name|Method
index|[]
name|methods
init|=
name|getMethods
argument_list|()
decl_stmt|;
name|Attribute
index|[]
name|attributes
init|=
literal|null
decl_stmt|;
if|if
condition|(
name|annotation_vec
operator|.
name|isEmpty
argument_list|()
condition|)
block|{
name|attributes
operator|=
name|getAttributes
argument_list|()
expr_stmt|;
block|}
else|else
block|{
comment|// TODO: Sometime later, trash any attributes called 'RuntimeVisibleAnnotations' or 'RuntimeInvisibleAnnotations'
name|Attribute
index|[]
name|annAttributes
init|=
name|Utility
operator|.
name|getAnnotationAttributes
argument_list|(
name|cp
argument_list|,
name|annotation_vec
argument_list|)
decl_stmt|;
name|attributes
operator|=
operator|new
name|Attribute
index|[
name|attribute_vec
operator|.
name|size
argument_list|()
operator|+
name|annAttributes
operator|.
name|length
index|]
expr_stmt|;
name|attribute_vec
operator|.
name|toArray
argument_list|(
name|attributes
argument_list|)
expr_stmt|;
name|System
operator|.
name|arraycopy
argument_list|(
name|annAttributes
argument_list|,
literal|0
argument_list|,
name|attributes
argument_list|,
name|attribute_vec
operator|.
name|size
argument_list|()
argument_list|,
name|annAttributes
operator|.
name|length
argument_list|)
expr_stmt|;
block|}
comment|// Must be last since the above calls may still add something to it
name|ConstantPool
name|_cp
init|=
name|this
operator|.
name|cp
operator|.
name|getFinalConstantPool
argument_list|()
decl_stmt|;
return|return
operator|new
name|JavaClass
argument_list|(
name|class_name_index
argument_list|,
name|superclass_name_index
argument_list|,
name|file_name
argument_list|,
name|major
argument_list|,
name|minor
argument_list|,
name|access_flags
argument_list|,
name|_cp
argument_list|,
name|interfaces
argument_list|,
name|fields
argument_list|,
name|methods
argument_list|,
name|attributes
argument_list|)
return|;
block|}
comment|/**      * Add an interface to this class, i.e., this class has to implement it.      * @param name interface to implement (fully qualified class name)      */
specifier|public
name|void
name|addInterface
parameter_list|(
name|String
name|name
parameter_list|)
block|{
name|interface_vec
operator|.
name|add
argument_list|(
name|name
argument_list|)
expr_stmt|;
block|}
comment|/**      * Remove an interface from this class.      * @param name interface to remove (fully qualified name)      */
specifier|public
name|void
name|removeInterface
parameter_list|(
name|String
name|name
parameter_list|)
block|{
name|interface_vec
operator|.
name|remove
argument_list|(
name|name
argument_list|)
expr_stmt|;
block|}
comment|/**      * @return major version number of class file      */
specifier|public
name|int
name|getMajor
parameter_list|()
block|{
return|return
name|major
return|;
block|}
comment|/** Set major version number of class file, default value is 45 (JDK 1.1)      * @param major major version number      */
specifier|public
name|void
name|setMajor
parameter_list|(
name|int
name|major
parameter_list|)
block|{
name|this
operator|.
name|major
operator|=
name|major
expr_stmt|;
block|}
comment|/** Set minor version number of class file, default value is 3 (JDK 1.1)      * @param minor minor version number      */
specifier|public
name|void
name|setMinor
parameter_list|(
name|int
name|minor
parameter_list|)
block|{
name|this
operator|.
name|minor
operator|=
name|minor
expr_stmt|;
block|}
comment|/**      * @return minor version number of class file      */
specifier|public
name|int
name|getMinor
parameter_list|()
block|{
return|return
name|minor
return|;
block|}
comment|/**      * Add an attribute to this class.      * @param a attribute to add      */
specifier|public
name|void
name|addAttribute
parameter_list|(
name|Attribute
name|a
parameter_list|)
block|{
name|attribute_vec
operator|.
name|add
argument_list|(
name|a
argument_list|)
expr_stmt|;
block|}
specifier|public
name|void
name|addAnnotationEntry
parameter_list|(
name|AnnotationEntryGen
name|a
parameter_list|)
block|{
name|annotation_vec
operator|.
name|add
argument_list|(
name|a
argument_list|)
expr_stmt|;
block|}
comment|/**      * Add a method to this class.      * @param m method to add      */
specifier|public
name|void
name|addMethod
parameter_list|(
name|Method
name|m
parameter_list|)
block|{
name|method_vec
operator|.
name|add
argument_list|(
name|m
argument_list|)
expr_stmt|;
block|}
comment|/**      * Convenience method.      *      * Add an empty constructor to this class that does nothing but calling super().      * @param access_flags rights for constructor      */
specifier|public
name|void
name|addEmptyConstructor
parameter_list|(
name|int
name|access_flags
parameter_list|)
block|{
name|InstructionList
name|il
init|=
operator|new
name|InstructionList
argument_list|()
decl_stmt|;
name|il
operator|.
name|append
argument_list|(
name|InstructionConstants
operator|.
name|THIS
argument_list|)
expr_stmt|;
comment|// Push `this'
name|il
operator|.
name|append
argument_list|(
operator|new
name|INVOKESPECIAL
argument_list|(
name|cp
operator|.
name|addMethodref
argument_list|(
name|super_class_name
argument_list|,
literal|"<init>"
argument_list|,
literal|"()V"
argument_list|)
argument_list|)
argument_list|)
expr_stmt|;
name|il
operator|.
name|append
argument_list|(
name|InstructionConstants
operator|.
name|RETURN
argument_list|)
expr_stmt|;
name|MethodGen
name|mg
init|=
operator|new
name|MethodGen
argument_list|(
name|access_flags
argument_list|,
name|Type
operator|.
name|VOID
argument_list|,
name|Type
operator|.
name|NO_ARGS
argument_list|,
literal|null
argument_list|,
literal|"<init>"
argument_list|,
name|class_name
argument_list|,
name|il
argument_list|,
name|cp
argument_list|)
decl_stmt|;
name|mg
operator|.
name|setMaxStack
argument_list|(
literal|1
argument_list|)
expr_stmt|;
name|addMethod
argument_list|(
name|mg
operator|.
name|getMethod
argument_list|()
argument_list|)
expr_stmt|;
block|}
comment|/**      * Add a field to this class.      * @param f field to add      */
specifier|public
name|void
name|addField
parameter_list|(
name|Field
name|f
parameter_list|)
block|{
name|field_vec
operator|.
name|add
argument_list|(
name|f
argument_list|)
expr_stmt|;
block|}
specifier|public
name|boolean
name|containsField
parameter_list|(
name|Field
name|f
parameter_list|)
block|{
return|return
name|field_vec
operator|.
name|contains
argument_list|(
name|f
argument_list|)
return|;
block|}
comment|/** @return field object with given name, or null      */
specifier|public
name|Field
name|containsField
parameter_list|(
name|String
name|name
parameter_list|)
block|{
for|for
control|(
name|Iterator
argument_list|<
name|Field
argument_list|>
name|e
init|=
name|field_vec
operator|.
name|iterator
argument_list|()
init|;
name|e
operator|.
name|hasNext
argument_list|()
condition|;
control|)
block|{
name|Field
name|f
init|=
name|e
operator|.
name|next
argument_list|()
decl_stmt|;
if|if
condition|(
name|f
operator|.
name|getName
argument_list|()
operator|.
name|equals
argument_list|(
name|name
argument_list|)
condition|)
block|{
return|return
name|f
return|;
block|}
block|}
return|return
literal|null
return|;
block|}
comment|/** @return method object with given name and signature, or null      */
specifier|public
name|Method
name|containsMethod
parameter_list|(
name|String
name|name
parameter_list|,
name|String
name|signature
parameter_list|)
block|{
for|for
control|(
name|Iterator
argument_list|<
name|Method
argument_list|>
name|e
init|=
name|method_vec
operator|.
name|iterator
argument_list|()
init|;
name|e
operator|.
name|hasNext
argument_list|()
condition|;
control|)
block|{
name|Method
name|m
init|=
name|e
operator|.
name|next
argument_list|()
decl_stmt|;
if|if
condition|(
name|m
operator|.
name|getName
argument_list|()
operator|.
name|equals
argument_list|(
name|name
argument_list|)
operator|&&
name|m
operator|.
name|getSignature
argument_list|()
operator|.
name|equals
argument_list|(
name|signature
argument_list|)
condition|)
block|{
return|return
name|m
return|;
block|}
block|}
return|return
literal|null
return|;
block|}
comment|/**      * Remove an attribute from this class.      * @param a attribute to remove      */
specifier|public
name|void
name|removeAttribute
parameter_list|(
name|Attribute
name|a
parameter_list|)
block|{
name|attribute_vec
operator|.
name|remove
argument_list|(
name|a
argument_list|)
expr_stmt|;
block|}
comment|/**      * Remove a method from this class.      * @param m method to remove      */
specifier|public
name|void
name|removeMethod
parameter_list|(
name|Method
name|m
parameter_list|)
block|{
name|method_vec
operator|.
name|remove
argument_list|(
name|m
argument_list|)
expr_stmt|;
block|}
comment|/** Replace given method with new one. If the old one does not exist      * add the new_ method to the class anyway.      */
specifier|public
name|void
name|replaceMethod
parameter_list|(
name|Method
name|old
parameter_list|,
name|Method
name|new_
parameter_list|)
block|{
if|if
condition|(
name|new_
operator|==
literal|null
condition|)
block|{
throw|throw
operator|new
name|ClassGenException
argument_list|(
literal|"Replacement method must not be null"
argument_list|)
throw|;
block|}
name|int
name|i
init|=
name|method_vec
operator|.
name|indexOf
argument_list|(
name|old
argument_list|)
decl_stmt|;
if|if
condition|(
name|i
operator|<
literal|0
condition|)
block|{
name|method_vec
operator|.
name|add
argument_list|(
name|new_
argument_list|)
expr_stmt|;
block|}
else|else
block|{
name|method_vec
operator|.
name|set
argument_list|(
name|i
argument_list|,
name|new_
argument_list|)
expr_stmt|;
block|}
block|}
comment|/** Replace given field with new one. If the old one does not exist      * add the new_ field to the class anyway.      */
specifier|public
name|void
name|replaceField
parameter_list|(
name|Field
name|old
parameter_list|,
name|Field
name|new_
parameter_list|)
block|{
if|if
condition|(
name|new_
operator|==
literal|null
condition|)
block|{
throw|throw
operator|new
name|ClassGenException
argument_list|(
literal|"Replacement method must not be null"
argument_list|)
throw|;
block|}
name|int
name|i
init|=
name|field_vec
operator|.
name|indexOf
argument_list|(
name|old
argument_list|)
decl_stmt|;
if|if
condition|(
name|i
operator|<
literal|0
condition|)
block|{
name|field_vec
operator|.
name|add
argument_list|(
name|new_
argument_list|)
expr_stmt|;
block|}
else|else
block|{
name|field_vec
operator|.
name|set
argument_list|(
name|i
argument_list|,
name|new_
argument_list|)
expr_stmt|;
block|}
block|}
comment|/**      * Remove a field to this class.      * @param f field to remove      */
specifier|public
name|void
name|removeField
parameter_list|(
name|Field
name|f
parameter_list|)
block|{
name|field_vec
operator|.
name|remove
argument_list|(
name|f
argument_list|)
expr_stmt|;
block|}
specifier|public
name|String
name|getClassName
parameter_list|()
block|{
return|return
name|class_name
return|;
block|}
specifier|public
name|String
name|getSuperclassName
parameter_list|()
block|{
return|return
name|super_class_name
return|;
block|}
specifier|public
name|String
name|getFileName
parameter_list|()
block|{
return|return
name|file_name
return|;
block|}
specifier|public
name|void
name|setClassName
parameter_list|(
name|String
name|name
parameter_list|)
block|{
name|class_name
operator|=
name|name
operator|.
name|replace
argument_list|(
literal|'/'
argument_list|,
literal|'.'
argument_list|)
expr_stmt|;
name|class_name_index
operator|=
name|cp
operator|.
name|addClass
argument_list|(
name|name
argument_list|)
expr_stmt|;
block|}
specifier|public
name|void
name|setSuperclassName
parameter_list|(
name|String
name|name
parameter_list|)
block|{
name|super_class_name
operator|=
name|name
operator|.
name|replace
argument_list|(
literal|'/'
argument_list|,
literal|'.'
argument_list|)
expr_stmt|;
name|superclass_name_index
operator|=
name|cp
operator|.
name|addClass
argument_list|(
name|name
argument_list|)
expr_stmt|;
block|}
specifier|public
name|Method
index|[]
name|getMethods
parameter_list|()
block|{
return|return
name|method_vec
operator|.
name|toArray
argument_list|(
operator|new
name|Method
index|[
name|method_vec
operator|.
name|size
argument_list|()
index|]
argument_list|)
return|;
block|}
specifier|public
name|void
name|setMethods
parameter_list|(
name|Method
index|[]
name|methods
parameter_list|)
block|{
name|method_vec
operator|.
name|clear
argument_list|()
expr_stmt|;
for|for
control|(
name|int
name|m
init|=
literal|0
init|;
name|m
operator|<
name|methods
operator|.
name|length
condition|;
name|m
operator|++
control|)
block|{
name|addMethod
argument_list|(
name|methods
index|[
name|m
index|]
argument_list|)
expr_stmt|;
block|}
block|}
specifier|public
name|void
name|setMethodAt
parameter_list|(
name|Method
name|method
parameter_list|,
name|int
name|pos
parameter_list|)
block|{
name|method_vec
operator|.
name|set
argument_list|(
name|pos
argument_list|,
name|method
argument_list|)
expr_stmt|;
block|}
specifier|public
name|Method
name|getMethodAt
parameter_list|(
name|int
name|pos
parameter_list|)
block|{
return|return
name|method_vec
operator|.
name|get
argument_list|(
name|pos
argument_list|)
return|;
block|}
specifier|public
name|String
index|[]
name|getInterfaceNames
parameter_list|()
block|{
name|int
name|size
init|=
name|interface_vec
operator|.
name|size
argument_list|()
decl_stmt|;
name|String
index|[]
name|interfaces
init|=
operator|new
name|String
index|[
name|size
index|]
decl_stmt|;
name|interface_vec
operator|.
name|toArray
argument_list|(
name|interfaces
argument_list|)
expr_stmt|;
return|return
name|interfaces
return|;
block|}
specifier|public
name|int
index|[]
name|getInterfaces
parameter_list|()
block|{
name|int
name|size
init|=
name|interface_vec
operator|.
name|size
argument_list|()
decl_stmt|;
name|int
index|[]
name|interfaces
init|=
operator|new
name|int
index|[
name|size
index|]
decl_stmt|;
for|for
control|(
name|int
name|i
init|=
literal|0
init|;
name|i
operator|<
name|size
condition|;
name|i
operator|++
control|)
block|{
name|interfaces
index|[
name|i
index|]
operator|=
name|cp
operator|.
name|addClass
argument_list|(
name|interface_vec
operator|.
name|get
argument_list|(
name|i
argument_list|)
argument_list|)
expr_stmt|;
block|}
return|return
name|interfaces
return|;
block|}
specifier|public
name|Field
index|[]
name|getFields
parameter_list|()
block|{
return|return
name|field_vec
operator|.
name|toArray
argument_list|(
operator|new
name|Field
index|[
name|field_vec
operator|.
name|size
argument_list|()
index|]
argument_list|)
return|;
block|}
specifier|public
name|Attribute
index|[]
name|getAttributes
parameter_list|()
block|{
return|return
name|attribute_vec
operator|.
name|toArray
argument_list|(
operator|new
name|Attribute
index|[
name|attribute_vec
operator|.
name|size
argument_list|()
index|]
argument_list|)
return|;
block|}
comment|//  J5TODO: Should we make calling unpackAnnotations() lazy and put it in here?
specifier|public
name|AnnotationEntryGen
index|[]
name|getAnnotationEntries
parameter_list|()
block|{
return|return
name|annotation_vec
operator|.
name|toArray
argument_list|(
operator|new
name|AnnotationEntryGen
index|[
name|annotation_vec
operator|.
name|size
argument_list|()
index|]
argument_list|)
return|;
block|}
specifier|public
name|ConstantPoolGen
name|getConstantPool
parameter_list|()
block|{
return|return
name|cp
return|;
block|}
specifier|public
name|void
name|setConstantPool
parameter_list|(
name|ConstantPoolGen
name|constant_pool
parameter_list|)
block|{
name|cp
operator|=
name|constant_pool
expr_stmt|;
block|}
specifier|public
name|void
name|setClassNameIndex
parameter_list|(
name|int
name|class_name_index
parameter_list|)
block|{
name|this
operator|.
name|class_name_index
operator|=
name|class_name_index
expr_stmt|;
name|class_name
operator|=
name|cp
operator|.
name|getConstantPool
argument_list|()
operator|.
name|getConstantString
argument_list|(
name|class_name_index
argument_list|,
name|Constants
operator|.
name|CONSTANT_Class
argument_list|)
operator|.
name|replace
argument_list|(
literal|'/'
argument_list|,
literal|'.'
argument_list|)
expr_stmt|;
block|}
specifier|public
name|void
name|setSuperclassNameIndex
parameter_list|(
name|int
name|superclass_name_index
parameter_list|)
block|{
name|this
operator|.
name|superclass_name_index
operator|=
name|superclass_name_index
expr_stmt|;
name|super_class_name
operator|=
name|cp
operator|.
name|getConstantPool
argument_list|()
operator|.
name|getConstantString
argument_list|(
name|superclass_name_index
argument_list|,
name|Constants
operator|.
name|CONSTANT_Class
argument_list|)
operator|.
name|replace
argument_list|(
literal|'/'
argument_list|,
literal|'.'
argument_list|)
expr_stmt|;
block|}
specifier|public
name|int
name|getSuperclassNameIndex
parameter_list|()
block|{
return|return
name|superclass_name_index
return|;
block|}
specifier|public
name|int
name|getClassNameIndex
parameter_list|()
block|{
return|return
name|class_name_index
return|;
block|}
specifier|private
name|List
argument_list|<
name|ClassObserver
argument_list|>
name|observers
decl_stmt|;
comment|/** Add observer for this object.      */
specifier|public
name|void
name|addObserver
parameter_list|(
name|ClassObserver
name|o
parameter_list|)
block|{
if|if
condition|(
name|observers
operator|==
literal|null
condition|)
block|{
name|observers
operator|=
operator|new
name|ArrayList
argument_list|<
name|ClassObserver
argument_list|>
argument_list|()
expr_stmt|;
block|}
name|observers
operator|.
name|add
argument_list|(
name|o
argument_list|)
expr_stmt|;
block|}
comment|/** Remove observer for this object.      */
specifier|public
name|void
name|removeObserver
parameter_list|(
name|ClassObserver
name|o
parameter_list|)
block|{
if|if
condition|(
name|observers
operator|!=
literal|null
condition|)
block|{
name|observers
operator|.
name|remove
argument_list|(
name|o
argument_list|)
expr_stmt|;
block|}
block|}
comment|/** Call notify() method on all observers. This method is not called      * automatically whenever the state has changed, but has to be      * called by the user after he has finished editing the object.      */
specifier|public
name|void
name|update
parameter_list|()
block|{
if|if
condition|(
name|observers
operator|!=
literal|null
condition|)
block|{
for|for
control|(
name|Iterator
argument_list|<
name|ClassObserver
argument_list|>
name|e
init|=
name|observers
operator|.
name|iterator
argument_list|()
init|;
name|e
operator|.
name|hasNext
argument_list|()
condition|;
control|)
block|{
name|e
operator|.
name|next
argument_list|()
operator|.
name|notify
argument_list|(
name|this
argument_list|)
expr_stmt|;
block|}
block|}
block|}
specifier|public
name|Object
name|clone
parameter_list|()
block|{
try|try
block|{
return|return
name|super
operator|.
name|clone
argument_list|()
return|;
block|}
catch|catch
parameter_list|(
name|CloneNotSupportedException
name|e
parameter_list|)
block|{
name|System
operator|.
name|err
operator|.
name|println
argument_list|(
name|e
argument_list|)
expr_stmt|;
return|return
literal|null
return|;
block|}
block|}
comment|/**      * @return Comparison strategy object      */
specifier|public
specifier|static
name|BCELComparator
name|getComparator
parameter_list|()
block|{
return|return
name|_cmp
return|;
block|}
comment|/**      * @param comparator Comparison strategy object      */
specifier|public
specifier|static
name|void
name|setComparator
parameter_list|(
name|BCELComparator
name|comparator
parameter_list|)
block|{
name|_cmp
operator|=
name|comparator
expr_stmt|;
block|}
comment|/**      * Return value as defined by given BCELComparator strategy.      * By default two ClassGen objects are said to be equal when      * their class names are equal.      *       * @see java.lang.Object#equals(java.lang.Object)      */
specifier|public
name|boolean
name|equals
parameter_list|(
name|Object
name|obj
parameter_list|)
block|{
return|return
name|_cmp
operator|.
name|equals
argument_list|(
name|this
argument_list|,
name|obj
argument_list|)
return|;
block|}
comment|/**      * Return value as defined by given BCELComparator strategy.      * By default return the hashcode of the class name.      *       * @see java.lang.Object#hashCode()      */
specifier|public
name|int
name|hashCode
parameter_list|()
block|{
return|return
name|_cmp
operator|.
name|hashCode
argument_list|(
name|this
argument_list|)
return|;
block|}
block|}
end_class

end_unit

