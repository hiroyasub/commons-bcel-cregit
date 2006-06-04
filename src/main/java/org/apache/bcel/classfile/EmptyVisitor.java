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
specifier|public
name|void
name|visitAnnotation
parameter_list|(
name|Annotations
name|obj
parameter_list|)
block|{
block|}
specifier|public
name|void
name|visitParameterAnnotation
parameter_list|(
name|ParameterAnnotations
name|obj
parameter_list|)
block|{
block|}
specifier|public
name|void
name|visitAnnotationEntry
parameter_list|(
name|AnnotationEntry
name|obj
parameter_list|)
block|{
block|}
specifier|public
name|void
name|visitAnnotationDefault
parameter_list|(
name|AnnotationDefault
name|obj
parameter_list|)
block|{
block|}
specifier|public
name|void
name|visitCode
parameter_list|(
name|Code
name|obj
parameter_list|)
block|{
block|}
specifier|public
name|void
name|visitCodeException
parameter_list|(
name|CodeException
name|obj
parameter_list|)
block|{
block|}
specifier|public
name|void
name|visitConstantClass
parameter_list|(
name|ConstantClass
name|obj
parameter_list|)
block|{
block|}
specifier|public
name|void
name|visitConstantDouble
parameter_list|(
name|ConstantDouble
name|obj
parameter_list|)
block|{
block|}
specifier|public
name|void
name|visitConstantFieldref
parameter_list|(
name|ConstantFieldref
name|obj
parameter_list|)
block|{
block|}
specifier|public
name|void
name|visitConstantFloat
parameter_list|(
name|ConstantFloat
name|obj
parameter_list|)
block|{
block|}
specifier|public
name|void
name|visitConstantInteger
parameter_list|(
name|ConstantInteger
name|obj
parameter_list|)
block|{
block|}
specifier|public
name|void
name|visitConstantInterfaceMethodref
parameter_list|(
name|ConstantInterfaceMethodref
name|obj
parameter_list|)
block|{
block|}
specifier|public
name|void
name|visitConstantLong
parameter_list|(
name|ConstantLong
name|obj
parameter_list|)
block|{
block|}
specifier|public
name|void
name|visitConstantMethodref
parameter_list|(
name|ConstantMethodref
name|obj
parameter_list|)
block|{
block|}
specifier|public
name|void
name|visitConstantNameAndType
parameter_list|(
name|ConstantNameAndType
name|obj
parameter_list|)
block|{
block|}
specifier|public
name|void
name|visitConstantPool
parameter_list|(
name|ConstantPool
name|obj
parameter_list|)
block|{
block|}
specifier|public
name|void
name|visitConstantString
parameter_list|(
name|ConstantString
name|obj
parameter_list|)
block|{
block|}
specifier|public
name|void
name|visitConstantUtf8
parameter_list|(
name|ConstantUtf8
name|obj
parameter_list|)
block|{
block|}
specifier|public
name|void
name|visitConstantValue
parameter_list|(
name|ConstantValue
name|obj
parameter_list|)
block|{
block|}
specifier|public
name|void
name|visitDeprecated
parameter_list|(
name|Deprecated
name|obj
parameter_list|)
block|{
block|}
specifier|public
name|void
name|visitExceptionTable
parameter_list|(
name|ExceptionTable
name|obj
parameter_list|)
block|{
block|}
specifier|public
name|void
name|visitField
parameter_list|(
name|Field
name|obj
parameter_list|)
block|{
block|}
specifier|public
name|void
name|visitInnerClass
parameter_list|(
name|InnerClass
name|obj
parameter_list|)
block|{
block|}
specifier|public
name|void
name|visitInnerClasses
parameter_list|(
name|InnerClasses
name|obj
parameter_list|)
block|{
block|}
specifier|public
name|void
name|visitJavaClass
parameter_list|(
name|JavaClass
name|obj
parameter_list|)
block|{
block|}
specifier|public
name|void
name|visitLineNumber
parameter_list|(
name|LineNumber
name|obj
parameter_list|)
block|{
block|}
specifier|public
name|void
name|visitLineNumberTable
parameter_list|(
name|LineNumberTable
name|obj
parameter_list|)
block|{
block|}
specifier|public
name|void
name|visitLocalVariable
parameter_list|(
name|LocalVariable
name|obj
parameter_list|)
block|{
block|}
specifier|public
name|void
name|visitLocalVariableTable
parameter_list|(
name|LocalVariableTable
name|obj
parameter_list|)
block|{
block|}
specifier|public
name|void
name|visitMethod
parameter_list|(
name|Method
name|obj
parameter_list|)
block|{
block|}
specifier|public
name|void
name|visitSignature
parameter_list|(
name|Signature
name|obj
parameter_list|)
block|{
block|}
specifier|public
name|void
name|visitSourceFile
parameter_list|(
name|SourceFile
name|obj
parameter_list|)
block|{
block|}
specifier|public
name|void
name|visitSynthetic
parameter_list|(
name|Synthetic
name|obj
parameter_list|)
block|{
block|}
specifier|public
name|void
name|visitUnknown
parameter_list|(
name|Unknown
name|obj
parameter_list|)
block|{
block|}
specifier|public
name|void
name|visitStackMap
parameter_list|(
name|StackMap
name|obj
parameter_list|)
block|{
block|}
specifier|public
name|void
name|visitStackMapEntry
parameter_list|(
name|StackMapEntry
name|obj
parameter_list|)
block|{
block|}
specifier|public
name|void
name|visitEnclosingMethod
parameter_list|(
name|EnclosingMethod
name|obj
parameter_list|)
block|{
block|}
specifier|public
name|void
name|visitLocalVariableTypeTable
parameter_list|(
name|LocalVariableTypeTable
name|obj
parameter_list|)
block|{
block|}
block|}
end_class

end_unit

