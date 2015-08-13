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
name|classfile
package|;
end_package

begin_import
import|import
name|java
operator|.
name|util
operator|.
name|Stack
import|;
end_import

begin_comment
comment|/**  * Traverses a JavaClass with another Visitor object 'piggy-backed' that is  * applied to all components of a JavaClass object. I.e. this class supplies the  * traversal strategy, other classes can make use of it.  *   * @version $Id$  */
end_comment

begin_class
specifier|public
class|class
name|DescendingVisitor
implements|implements
name|Visitor
block|{
specifier|private
specifier|final
name|JavaClass
name|clazz
decl_stmt|;
specifier|private
specifier|final
name|Visitor
name|visitor
decl_stmt|;
specifier|private
specifier|final
name|Stack
argument_list|<
name|Object
argument_list|>
name|stack
init|=
operator|new
name|Stack
argument_list|<
name|Object
argument_list|>
argument_list|()
decl_stmt|;
comment|/**      * @return container of current entitity, i.e., predecessor during traversal      */
specifier|public
name|Object
name|predecessor
parameter_list|()
block|{
return|return
name|predecessor
argument_list|(
literal|0
argument_list|)
return|;
block|}
comment|/**      * @param level      *            nesting level, i.e., 0 returns the direct predecessor      * @return container of current entitity, i.e., predecessor during traversal      */
specifier|public
name|Object
name|predecessor
parameter_list|(
name|int
name|level
parameter_list|)
block|{
name|int
name|size
init|=
name|stack
operator|.
name|size
argument_list|()
decl_stmt|;
if|if
condition|(
operator|(
name|size
operator|<
literal|2
operator|)
operator|||
operator|(
name|level
operator|<
literal|0
operator|)
condition|)
block|{
return|return
literal|null
return|;
block|}
return|return
name|stack
operator|.
name|elementAt
argument_list|(
name|size
operator|-
operator|(
name|level
operator|+
literal|2
operator|)
argument_list|)
return|;
comment|// size - 1 == current
block|}
comment|/**      * @return current object      */
specifier|public
name|Object
name|current
parameter_list|()
block|{
return|return
name|stack
operator|.
name|peek
argument_list|()
return|;
block|}
comment|/**      * @param clazz      *            Class to traverse      * @param visitor      *            visitor object to apply to all components      */
specifier|public
name|DescendingVisitor
parameter_list|(
name|JavaClass
name|clazz
parameter_list|,
name|Visitor
name|visitor
parameter_list|)
block|{
name|this
operator|.
name|clazz
operator|=
name|clazz
expr_stmt|;
name|this
operator|.
name|visitor
operator|=
name|visitor
expr_stmt|;
block|}
comment|/**      * Start traversal.      */
specifier|public
name|void
name|visit
parameter_list|()
block|{
name|clazz
operator|.
name|accept
argument_list|(
name|this
argument_list|)
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitJavaClass
parameter_list|(
name|JavaClass
name|_clazz
parameter_list|)
block|{
name|stack
operator|.
name|push
argument_list|(
name|_clazz
argument_list|)
expr_stmt|;
name|_clazz
operator|.
name|accept
argument_list|(
name|visitor
argument_list|)
expr_stmt|;
name|Field
index|[]
name|fields
init|=
name|_clazz
operator|.
name|getFields
argument_list|()
decl_stmt|;
for|for
control|(
name|Field
name|field
range|:
name|fields
control|)
block|{
name|field
operator|.
name|accept
argument_list|(
name|this
argument_list|)
expr_stmt|;
block|}
name|Method
index|[]
name|methods
init|=
name|_clazz
operator|.
name|getMethods
argument_list|()
decl_stmt|;
for|for
control|(
name|Method
name|method
range|:
name|methods
control|)
block|{
name|method
operator|.
name|accept
argument_list|(
name|this
argument_list|)
expr_stmt|;
block|}
name|Attribute
index|[]
name|attributes
init|=
name|_clazz
operator|.
name|getAttributes
argument_list|()
decl_stmt|;
for|for
control|(
name|Attribute
name|attribute
range|:
name|attributes
control|)
block|{
name|attribute
operator|.
name|accept
argument_list|(
name|this
argument_list|)
expr_stmt|;
block|}
name|_clazz
operator|.
name|getConstantPool
argument_list|()
operator|.
name|accept
argument_list|(
name|this
argument_list|)
expr_stmt|;
name|stack
operator|.
name|pop
argument_list|()
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitAnnotation
parameter_list|(
name|Annotations
name|annotation
parameter_list|)
block|{
name|stack
operator|.
name|push
argument_list|(
name|annotation
argument_list|)
expr_stmt|;
name|annotation
operator|.
name|accept
argument_list|(
name|visitor
argument_list|)
expr_stmt|;
name|AnnotationEntry
index|[]
name|entries
init|=
name|annotation
operator|.
name|getAnnotationEntries
argument_list|()
decl_stmt|;
for|for
control|(
name|AnnotationEntry
name|entrie
range|:
name|entries
control|)
block|{
name|entrie
operator|.
name|accept
argument_list|(
name|this
argument_list|)
expr_stmt|;
block|}
name|stack
operator|.
name|pop
argument_list|()
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitAnnotationEntry
parameter_list|(
name|AnnotationEntry
name|annotationEntry
parameter_list|)
block|{
name|stack
operator|.
name|push
argument_list|(
name|annotationEntry
argument_list|)
expr_stmt|;
name|annotationEntry
operator|.
name|accept
argument_list|(
name|visitor
argument_list|)
expr_stmt|;
name|stack
operator|.
name|pop
argument_list|()
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitField
parameter_list|(
name|Field
name|field
parameter_list|)
block|{
name|stack
operator|.
name|push
argument_list|(
name|field
argument_list|)
expr_stmt|;
name|field
operator|.
name|accept
argument_list|(
name|visitor
argument_list|)
expr_stmt|;
name|Attribute
index|[]
name|attributes
init|=
name|field
operator|.
name|getAttributes
argument_list|()
decl_stmt|;
for|for
control|(
name|Attribute
name|attribute
range|:
name|attributes
control|)
block|{
name|attribute
operator|.
name|accept
argument_list|(
name|this
argument_list|)
expr_stmt|;
block|}
name|stack
operator|.
name|pop
argument_list|()
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitConstantValue
parameter_list|(
name|ConstantValue
name|cv
parameter_list|)
block|{
name|stack
operator|.
name|push
argument_list|(
name|cv
argument_list|)
expr_stmt|;
name|cv
operator|.
name|accept
argument_list|(
name|visitor
argument_list|)
expr_stmt|;
name|stack
operator|.
name|pop
argument_list|()
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitMethod
parameter_list|(
name|Method
name|method
parameter_list|)
block|{
name|stack
operator|.
name|push
argument_list|(
name|method
argument_list|)
expr_stmt|;
name|method
operator|.
name|accept
argument_list|(
name|visitor
argument_list|)
expr_stmt|;
name|Attribute
index|[]
name|attributes
init|=
name|method
operator|.
name|getAttributes
argument_list|()
decl_stmt|;
for|for
control|(
name|Attribute
name|attribute
range|:
name|attributes
control|)
block|{
name|attribute
operator|.
name|accept
argument_list|(
name|this
argument_list|)
expr_stmt|;
block|}
name|stack
operator|.
name|pop
argument_list|()
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitExceptionTable
parameter_list|(
name|ExceptionTable
name|table
parameter_list|)
block|{
name|stack
operator|.
name|push
argument_list|(
name|table
argument_list|)
expr_stmt|;
name|table
operator|.
name|accept
argument_list|(
name|visitor
argument_list|)
expr_stmt|;
name|stack
operator|.
name|pop
argument_list|()
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitCode
parameter_list|(
name|Code
name|code
parameter_list|)
block|{
name|stack
operator|.
name|push
argument_list|(
name|code
argument_list|)
expr_stmt|;
name|code
operator|.
name|accept
argument_list|(
name|visitor
argument_list|)
expr_stmt|;
name|CodeException
index|[]
name|table
init|=
name|code
operator|.
name|getExceptionTable
argument_list|()
decl_stmt|;
for|for
control|(
name|CodeException
name|element
range|:
name|table
control|)
block|{
name|element
operator|.
name|accept
argument_list|(
name|this
argument_list|)
expr_stmt|;
block|}
name|Attribute
index|[]
name|attributes
init|=
name|code
operator|.
name|getAttributes
argument_list|()
decl_stmt|;
for|for
control|(
name|Attribute
name|attribute
range|:
name|attributes
control|)
block|{
name|attribute
operator|.
name|accept
argument_list|(
name|this
argument_list|)
expr_stmt|;
block|}
name|stack
operator|.
name|pop
argument_list|()
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitCodeException
parameter_list|(
name|CodeException
name|ce
parameter_list|)
block|{
name|stack
operator|.
name|push
argument_list|(
name|ce
argument_list|)
expr_stmt|;
name|ce
operator|.
name|accept
argument_list|(
name|visitor
argument_list|)
expr_stmt|;
name|stack
operator|.
name|pop
argument_list|()
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitLineNumberTable
parameter_list|(
name|LineNumberTable
name|table
parameter_list|)
block|{
name|stack
operator|.
name|push
argument_list|(
name|table
argument_list|)
expr_stmt|;
name|table
operator|.
name|accept
argument_list|(
name|visitor
argument_list|)
expr_stmt|;
name|LineNumber
index|[]
name|numbers
init|=
name|table
operator|.
name|getLineNumberTable
argument_list|()
decl_stmt|;
for|for
control|(
name|LineNumber
name|number
range|:
name|numbers
control|)
block|{
name|number
operator|.
name|accept
argument_list|(
name|this
argument_list|)
expr_stmt|;
block|}
name|stack
operator|.
name|pop
argument_list|()
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitLineNumber
parameter_list|(
name|LineNumber
name|number
parameter_list|)
block|{
name|stack
operator|.
name|push
argument_list|(
name|number
argument_list|)
expr_stmt|;
name|number
operator|.
name|accept
argument_list|(
name|visitor
argument_list|)
expr_stmt|;
name|stack
operator|.
name|pop
argument_list|()
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitLocalVariableTable
parameter_list|(
name|LocalVariableTable
name|table
parameter_list|)
block|{
name|stack
operator|.
name|push
argument_list|(
name|table
argument_list|)
expr_stmt|;
name|table
operator|.
name|accept
argument_list|(
name|visitor
argument_list|)
expr_stmt|;
name|LocalVariable
index|[]
name|vars
init|=
name|table
operator|.
name|getLocalVariableTable
argument_list|()
decl_stmt|;
for|for
control|(
name|LocalVariable
name|var
range|:
name|vars
control|)
block|{
name|var
operator|.
name|accept
argument_list|(
name|this
argument_list|)
expr_stmt|;
block|}
name|stack
operator|.
name|pop
argument_list|()
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitStackMap
parameter_list|(
name|StackMap
name|table
parameter_list|)
block|{
name|stack
operator|.
name|push
argument_list|(
name|table
argument_list|)
expr_stmt|;
name|table
operator|.
name|accept
argument_list|(
name|visitor
argument_list|)
expr_stmt|;
name|StackMapEntry
index|[]
name|vars
init|=
name|table
operator|.
name|getStackMap
argument_list|()
decl_stmt|;
for|for
control|(
name|StackMapEntry
name|var
range|:
name|vars
control|)
block|{
name|var
operator|.
name|accept
argument_list|(
name|this
argument_list|)
expr_stmt|;
block|}
name|stack
operator|.
name|pop
argument_list|()
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitStackMapEntry
parameter_list|(
name|StackMapEntry
name|var
parameter_list|)
block|{
name|stack
operator|.
name|push
argument_list|(
name|var
argument_list|)
expr_stmt|;
name|var
operator|.
name|accept
argument_list|(
name|visitor
argument_list|)
expr_stmt|;
name|stack
operator|.
name|pop
argument_list|()
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitStackMapTable
parameter_list|(
name|StackMapTable
name|table
parameter_list|)
block|{
name|stack
operator|.
name|push
argument_list|(
name|table
argument_list|)
expr_stmt|;
name|table
operator|.
name|accept
argument_list|(
name|visitor
argument_list|)
expr_stmt|;
name|StackMapTableEntry
index|[]
name|vars
init|=
name|table
operator|.
name|getStackMapTable
argument_list|()
decl_stmt|;
for|for
control|(
name|StackMapTableEntry
name|var
range|:
name|vars
control|)
block|{
name|var
operator|.
name|accept
argument_list|(
name|this
argument_list|)
expr_stmt|;
block|}
name|stack
operator|.
name|pop
argument_list|()
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitStackMapTableEntry
parameter_list|(
name|StackMapTableEntry
name|var
parameter_list|)
block|{
name|stack
operator|.
name|push
argument_list|(
name|var
argument_list|)
expr_stmt|;
name|var
operator|.
name|accept
argument_list|(
name|visitor
argument_list|)
expr_stmt|;
name|stack
operator|.
name|pop
argument_list|()
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitLocalVariable
parameter_list|(
name|LocalVariable
name|var
parameter_list|)
block|{
name|stack
operator|.
name|push
argument_list|(
name|var
argument_list|)
expr_stmt|;
name|var
operator|.
name|accept
argument_list|(
name|visitor
argument_list|)
expr_stmt|;
name|stack
operator|.
name|pop
argument_list|()
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitConstantPool
parameter_list|(
name|ConstantPool
name|cp
parameter_list|)
block|{
name|stack
operator|.
name|push
argument_list|(
name|cp
argument_list|)
expr_stmt|;
name|cp
operator|.
name|accept
argument_list|(
name|visitor
argument_list|)
expr_stmt|;
name|Constant
index|[]
name|constants
init|=
name|cp
operator|.
name|getConstantPool
argument_list|()
decl_stmt|;
for|for
control|(
name|int
name|i
init|=
literal|1
init|;
name|i
operator|<
name|constants
operator|.
name|length
condition|;
name|i
operator|++
control|)
block|{
if|if
condition|(
name|constants
index|[
name|i
index|]
operator|!=
literal|null
condition|)
block|{
name|constants
index|[
name|i
index|]
operator|.
name|accept
argument_list|(
name|this
argument_list|)
expr_stmt|;
block|}
block|}
name|stack
operator|.
name|pop
argument_list|()
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitConstantClass
parameter_list|(
name|ConstantClass
name|constant
parameter_list|)
block|{
name|stack
operator|.
name|push
argument_list|(
name|constant
argument_list|)
expr_stmt|;
name|constant
operator|.
name|accept
argument_list|(
name|visitor
argument_list|)
expr_stmt|;
name|stack
operator|.
name|pop
argument_list|()
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitConstantDouble
parameter_list|(
name|ConstantDouble
name|constant
parameter_list|)
block|{
name|stack
operator|.
name|push
argument_list|(
name|constant
argument_list|)
expr_stmt|;
name|constant
operator|.
name|accept
argument_list|(
name|visitor
argument_list|)
expr_stmt|;
name|stack
operator|.
name|pop
argument_list|()
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitConstantFieldref
parameter_list|(
name|ConstantFieldref
name|constant
parameter_list|)
block|{
name|stack
operator|.
name|push
argument_list|(
name|constant
argument_list|)
expr_stmt|;
name|constant
operator|.
name|accept
argument_list|(
name|visitor
argument_list|)
expr_stmt|;
name|stack
operator|.
name|pop
argument_list|()
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitConstantFloat
parameter_list|(
name|ConstantFloat
name|constant
parameter_list|)
block|{
name|stack
operator|.
name|push
argument_list|(
name|constant
argument_list|)
expr_stmt|;
name|constant
operator|.
name|accept
argument_list|(
name|visitor
argument_list|)
expr_stmt|;
name|stack
operator|.
name|pop
argument_list|()
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitConstantInteger
parameter_list|(
name|ConstantInteger
name|constant
parameter_list|)
block|{
name|stack
operator|.
name|push
argument_list|(
name|constant
argument_list|)
expr_stmt|;
name|constant
operator|.
name|accept
argument_list|(
name|visitor
argument_list|)
expr_stmt|;
name|stack
operator|.
name|pop
argument_list|()
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitConstantInterfaceMethodref
parameter_list|(
name|ConstantInterfaceMethodref
name|constant
parameter_list|)
block|{
name|stack
operator|.
name|push
argument_list|(
name|constant
argument_list|)
expr_stmt|;
name|constant
operator|.
name|accept
argument_list|(
name|visitor
argument_list|)
expr_stmt|;
name|stack
operator|.
name|pop
argument_list|()
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitConstantInvokeDynamic
parameter_list|(
name|ConstantInvokeDynamic
name|constant
parameter_list|)
block|{
name|stack
operator|.
name|push
argument_list|(
name|constant
argument_list|)
expr_stmt|;
name|constant
operator|.
name|accept
argument_list|(
name|visitor
argument_list|)
expr_stmt|;
name|stack
operator|.
name|pop
argument_list|()
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitConstantLong
parameter_list|(
name|ConstantLong
name|constant
parameter_list|)
block|{
name|stack
operator|.
name|push
argument_list|(
name|constant
argument_list|)
expr_stmt|;
name|constant
operator|.
name|accept
argument_list|(
name|visitor
argument_list|)
expr_stmt|;
name|stack
operator|.
name|pop
argument_list|()
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitConstantMethodref
parameter_list|(
name|ConstantMethodref
name|constant
parameter_list|)
block|{
name|stack
operator|.
name|push
argument_list|(
name|constant
argument_list|)
expr_stmt|;
name|constant
operator|.
name|accept
argument_list|(
name|visitor
argument_list|)
expr_stmt|;
name|stack
operator|.
name|pop
argument_list|()
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitConstantNameAndType
parameter_list|(
name|ConstantNameAndType
name|constant
parameter_list|)
block|{
name|stack
operator|.
name|push
argument_list|(
name|constant
argument_list|)
expr_stmt|;
name|constant
operator|.
name|accept
argument_list|(
name|visitor
argument_list|)
expr_stmt|;
name|stack
operator|.
name|pop
argument_list|()
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitConstantString
parameter_list|(
name|ConstantString
name|constant
parameter_list|)
block|{
name|stack
operator|.
name|push
argument_list|(
name|constant
argument_list|)
expr_stmt|;
name|constant
operator|.
name|accept
argument_list|(
name|visitor
argument_list|)
expr_stmt|;
name|stack
operator|.
name|pop
argument_list|()
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitConstantUtf8
parameter_list|(
name|ConstantUtf8
name|constant
parameter_list|)
block|{
name|stack
operator|.
name|push
argument_list|(
name|constant
argument_list|)
expr_stmt|;
name|constant
operator|.
name|accept
argument_list|(
name|visitor
argument_list|)
expr_stmt|;
name|stack
operator|.
name|pop
argument_list|()
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitInnerClasses
parameter_list|(
name|InnerClasses
name|ic
parameter_list|)
block|{
name|stack
operator|.
name|push
argument_list|(
name|ic
argument_list|)
expr_stmt|;
name|ic
operator|.
name|accept
argument_list|(
name|visitor
argument_list|)
expr_stmt|;
name|InnerClass
index|[]
name|ics
init|=
name|ic
operator|.
name|getInnerClasses
argument_list|()
decl_stmt|;
for|for
control|(
name|InnerClass
name|ic2
range|:
name|ics
control|)
block|{
name|ic2
operator|.
name|accept
argument_list|(
name|this
argument_list|)
expr_stmt|;
block|}
name|stack
operator|.
name|pop
argument_list|()
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitInnerClass
parameter_list|(
name|InnerClass
name|inner
parameter_list|)
block|{
name|stack
operator|.
name|push
argument_list|(
name|inner
argument_list|)
expr_stmt|;
name|inner
operator|.
name|accept
argument_list|(
name|visitor
argument_list|)
expr_stmt|;
name|stack
operator|.
name|pop
argument_list|()
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitBootstrapMethods
parameter_list|(
name|BootstrapMethods
name|bm
parameter_list|)
block|{
name|stack
operator|.
name|push
argument_list|(
name|bm
argument_list|)
expr_stmt|;
name|bm
operator|.
name|accept
argument_list|(
name|visitor
argument_list|)
expr_stmt|;
comment|// BootstrapMethod[] bms = bm.getBootstrapMethods();
comment|// for (int i = 0; i< bms.length; i++)
comment|// {
comment|//     bms[i].accept(this);
comment|// }
name|stack
operator|.
name|pop
argument_list|()
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitDeprecated
parameter_list|(
name|Deprecated
name|attribute
parameter_list|)
block|{
name|stack
operator|.
name|push
argument_list|(
name|attribute
argument_list|)
expr_stmt|;
name|attribute
operator|.
name|accept
argument_list|(
name|visitor
argument_list|)
expr_stmt|;
name|stack
operator|.
name|pop
argument_list|()
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitSignature
parameter_list|(
name|Signature
name|attribute
parameter_list|)
block|{
name|stack
operator|.
name|push
argument_list|(
name|attribute
argument_list|)
expr_stmt|;
name|attribute
operator|.
name|accept
argument_list|(
name|visitor
argument_list|)
expr_stmt|;
name|stack
operator|.
name|pop
argument_list|()
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitSourceFile
parameter_list|(
name|SourceFile
name|attribute
parameter_list|)
block|{
name|stack
operator|.
name|push
argument_list|(
name|attribute
argument_list|)
expr_stmt|;
name|attribute
operator|.
name|accept
argument_list|(
name|visitor
argument_list|)
expr_stmt|;
name|stack
operator|.
name|pop
argument_list|()
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitSynthetic
parameter_list|(
name|Synthetic
name|attribute
parameter_list|)
block|{
name|stack
operator|.
name|push
argument_list|(
name|attribute
argument_list|)
expr_stmt|;
name|attribute
operator|.
name|accept
argument_list|(
name|visitor
argument_list|)
expr_stmt|;
name|stack
operator|.
name|pop
argument_list|()
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitUnknown
parameter_list|(
name|Unknown
name|attribute
parameter_list|)
block|{
name|stack
operator|.
name|push
argument_list|(
name|attribute
argument_list|)
expr_stmt|;
name|attribute
operator|.
name|accept
argument_list|(
name|visitor
argument_list|)
expr_stmt|;
name|stack
operator|.
name|pop
argument_list|()
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitAnnotationDefault
parameter_list|(
name|AnnotationDefault
name|obj
parameter_list|)
block|{
name|stack
operator|.
name|push
argument_list|(
name|obj
argument_list|)
expr_stmt|;
name|obj
operator|.
name|accept
argument_list|(
name|visitor
argument_list|)
expr_stmt|;
name|stack
operator|.
name|pop
argument_list|()
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitEnclosingMethod
parameter_list|(
name|EnclosingMethod
name|obj
parameter_list|)
block|{
name|stack
operator|.
name|push
argument_list|(
name|obj
argument_list|)
expr_stmt|;
name|obj
operator|.
name|accept
argument_list|(
name|visitor
argument_list|)
expr_stmt|;
name|stack
operator|.
name|pop
argument_list|()
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitLocalVariableTypeTable
parameter_list|(
name|LocalVariableTypeTable
name|obj
parameter_list|)
block|{
name|stack
operator|.
name|push
argument_list|(
name|obj
argument_list|)
expr_stmt|;
name|obj
operator|.
name|accept
argument_list|(
name|visitor
argument_list|)
expr_stmt|;
name|stack
operator|.
name|pop
argument_list|()
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitParameterAnnotation
parameter_list|(
name|ParameterAnnotations
name|obj
parameter_list|)
block|{
name|stack
operator|.
name|push
argument_list|(
name|obj
argument_list|)
expr_stmt|;
name|obj
operator|.
name|accept
argument_list|(
name|visitor
argument_list|)
expr_stmt|;
name|stack
operator|.
name|pop
argument_list|()
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitMethodParameters
parameter_list|(
name|MethodParameters
name|obj
parameter_list|)
block|{
name|stack
operator|.
name|push
argument_list|(
name|obj
argument_list|)
expr_stmt|;
name|obj
operator|.
name|accept
argument_list|(
name|visitor
argument_list|)
expr_stmt|;
name|stack
operator|.
name|pop
argument_list|()
expr_stmt|;
block|}
block|}
end_class

end_unit

