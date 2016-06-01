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

begin_comment
comment|/**  * Visitor with empty method bodies, can be extended and used in conjunction  * with the DescendingVisitor class, e.g. By courtesy of David Spencer.  *   * @see DescendingVisitor  * @version $Id$  */
end_comment

begin_class
specifier|public
class|class
name|EmptyVisitor
implements|implements
name|Visitor
block|{
specifier|protected
name|EmptyVisitor
parameter_list|()
block|{
block|}
comment|/**      * @since 6.0      */
annotation|@
name|Override
specifier|public
name|void
name|visitAnnotation
parameter_list|(
specifier|final
name|Annotations
name|obj
parameter_list|)
block|{
block|}
comment|/**      * @since 6.0      */
annotation|@
name|Override
specifier|public
name|void
name|visitParameterAnnotation
parameter_list|(
specifier|final
name|ParameterAnnotations
name|obj
parameter_list|)
block|{
block|}
comment|/**      * @since 6.0      */
annotation|@
name|Override
specifier|public
name|void
name|visitAnnotationEntry
parameter_list|(
specifier|final
name|AnnotationEntry
name|obj
parameter_list|)
block|{
block|}
comment|/**      * @since 6.0      */
annotation|@
name|Override
specifier|public
name|void
name|visitAnnotationDefault
parameter_list|(
specifier|final
name|AnnotationDefault
name|obj
parameter_list|)
block|{
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitCode
parameter_list|(
specifier|final
name|Code
name|obj
parameter_list|)
block|{
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitCodeException
parameter_list|(
specifier|final
name|CodeException
name|obj
parameter_list|)
block|{
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitConstantClass
parameter_list|(
specifier|final
name|ConstantClass
name|obj
parameter_list|)
block|{
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitConstantDouble
parameter_list|(
specifier|final
name|ConstantDouble
name|obj
parameter_list|)
block|{
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitConstantFieldref
parameter_list|(
specifier|final
name|ConstantFieldref
name|obj
parameter_list|)
block|{
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitConstantFloat
parameter_list|(
specifier|final
name|ConstantFloat
name|obj
parameter_list|)
block|{
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitConstantInteger
parameter_list|(
specifier|final
name|ConstantInteger
name|obj
parameter_list|)
block|{
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitConstantInterfaceMethodref
parameter_list|(
specifier|final
name|ConstantInterfaceMethodref
name|obj
parameter_list|)
block|{
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitConstantInvokeDynamic
parameter_list|(
specifier|final
name|ConstantInvokeDynamic
name|obj
parameter_list|)
block|{
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitConstantLong
parameter_list|(
specifier|final
name|ConstantLong
name|obj
parameter_list|)
block|{
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitConstantMethodref
parameter_list|(
specifier|final
name|ConstantMethodref
name|obj
parameter_list|)
block|{
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitConstantNameAndType
parameter_list|(
specifier|final
name|ConstantNameAndType
name|obj
parameter_list|)
block|{
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitConstantPool
parameter_list|(
specifier|final
name|ConstantPool
name|obj
parameter_list|)
block|{
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitConstantString
parameter_list|(
specifier|final
name|ConstantString
name|obj
parameter_list|)
block|{
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitConstantUtf8
parameter_list|(
specifier|final
name|ConstantUtf8
name|obj
parameter_list|)
block|{
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitConstantValue
parameter_list|(
specifier|final
name|ConstantValue
name|obj
parameter_list|)
block|{
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitDeprecated
parameter_list|(
specifier|final
name|Deprecated
name|obj
parameter_list|)
block|{
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitExceptionTable
parameter_list|(
specifier|final
name|ExceptionTable
name|obj
parameter_list|)
block|{
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitField
parameter_list|(
specifier|final
name|Field
name|obj
parameter_list|)
block|{
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitInnerClass
parameter_list|(
specifier|final
name|InnerClass
name|obj
parameter_list|)
block|{
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitInnerClasses
parameter_list|(
specifier|final
name|InnerClasses
name|obj
parameter_list|)
block|{
block|}
comment|/**      * @since 6.0      */
annotation|@
name|Override
specifier|public
name|void
name|visitBootstrapMethods
parameter_list|(
specifier|final
name|BootstrapMethods
name|obj
parameter_list|)
block|{
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitJavaClass
parameter_list|(
specifier|final
name|JavaClass
name|obj
parameter_list|)
block|{
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitLineNumber
parameter_list|(
specifier|final
name|LineNumber
name|obj
parameter_list|)
block|{
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitLineNumberTable
parameter_list|(
specifier|final
name|LineNumberTable
name|obj
parameter_list|)
block|{
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitLocalVariable
parameter_list|(
specifier|final
name|LocalVariable
name|obj
parameter_list|)
block|{
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitLocalVariableTable
parameter_list|(
specifier|final
name|LocalVariableTable
name|obj
parameter_list|)
block|{
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitMethod
parameter_list|(
specifier|final
name|Method
name|obj
parameter_list|)
block|{
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitSignature
parameter_list|(
specifier|final
name|Signature
name|obj
parameter_list|)
block|{
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitSourceFile
parameter_list|(
specifier|final
name|SourceFile
name|obj
parameter_list|)
block|{
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitSynthetic
parameter_list|(
specifier|final
name|Synthetic
name|obj
parameter_list|)
block|{
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitUnknown
parameter_list|(
specifier|final
name|Unknown
name|obj
parameter_list|)
block|{
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitStackMap
parameter_list|(
specifier|final
name|StackMap
name|obj
parameter_list|)
block|{
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitStackMapEntry
parameter_list|(
specifier|final
name|StackMapEntry
name|obj
parameter_list|)
block|{
block|}
comment|/**      * @since 6.0     @Override     public void visitStackMapTable(StackMapTable obj)     {     }      */
comment|/**      * @since 6.0     @Override     public void visitStackMapTableEntry(StackMapTableEntry obj)     {     }      */
comment|/**      * @since 6.0      */
annotation|@
name|Override
specifier|public
name|void
name|visitEnclosingMethod
parameter_list|(
specifier|final
name|EnclosingMethod
name|obj
parameter_list|)
block|{
block|}
comment|/**      * @since 6.0      */
annotation|@
name|Override
specifier|public
name|void
name|visitLocalVariableTypeTable
parameter_list|(
specifier|final
name|LocalVariableTypeTable
name|obj
parameter_list|)
block|{
block|}
comment|/**      * @since 6.0      */
annotation|@
name|Override
specifier|public
name|void
name|visitMethodParameters
parameter_list|(
specifier|final
name|MethodParameters
name|obj
parameter_list|)
block|{
block|}
comment|/**      * @since 6.0      */
annotation|@
name|Override
specifier|public
name|void
name|visitConstantMethodType
parameter_list|(
specifier|final
name|ConstantMethodType
name|obj
parameter_list|)
block|{
block|}
comment|/**      * @since 6.0      */
annotation|@
name|Override
specifier|public
name|void
name|visitConstantMethodHandle
parameter_list|(
specifier|final
name|ConstantMethodHandle
name|constantMethodHandle
parameter_list|)
block|{
block|}
comment|/**      * @since 6.0      */
annotation|@
name|Override
specifier|public
name|void
name|visitParameterAnnotationEntry
parameter_list|(
specifier|final
name|ParameterAnnotationEntry
name|parameterAnnotationEntry
parameter_list|)
block|{
block|}
block|}
end_class

end_unit

