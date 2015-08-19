begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
begin_comment
comment|/*  * Licensed to the Apache Software Foundation (ASF) under one or more  * contributor license agreements.  See the NOTICE file distributed with  * this work for additional information regarding copyright ownership.  * The ASF licenses this file to You under the Apache License, Version 2.0  * (the "License"); you may not use this file except in compliance with  * the License.  You may obtain a copy of the License at  *  *   http://www.apache.org/licenses/LICENSE-2.0  *  * Unless required by applicable law or agreed to in writing, software  * distributed under the License is distributed on an "AS IS" BASIS,  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  * See the License for the specific language governing permissions and  * limitations under the License.  *   */
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
name|visitors
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
name|classfile
operator|.
name|AnnotationDefault
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
name|AnnotationEntry
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
name|Annotations
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
name|BootstrapMethods
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
name|Code
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
name|CodeException
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
name|ConstantClass
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
name|ConstantDouble
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
name|ConstantFieldref
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
name|ConstantFloat
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
name|ConstantInteger
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
name|ConstantInterfaceMethodref
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
name|ConstantInvokeDynamic
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
name|ConstantLong
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
name|ConstantMethodref
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
name|ConstantNameAndType
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
name|ConstantPool
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
name|ConstantString
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
name|ConstantUtf8
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
name|ConstantValue
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
name|Deprecated
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
name|EnclosingMethod
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
name|ExceptionTable
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
name|Field
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
name|InnerClass
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
name|InnerClasses
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
name|LineNumber
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
name|LineNumberTable
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
name|LocalVariable
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
name|LocalVariableTable
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
name|LocalVariableTypeTable
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
name|Method
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
name|MethodParameters
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
name|ParameterAnnotations
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
name|Signature
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
name|SourceFile
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
name|StackMap
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
name|StackMapEntry
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
name|StackMapTable
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
name|StackMapTableEntry
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
name|Synthetic
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
name|Unknown
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
name|Visitor
import|;
end_import

begin_class
specifier|public
class|class
name|CounterVisitor
implements|implements
name|Visitor
block|{
specifier|public
name|int
name|unknownCount
init|=
literal|0
decl_stmt|;
specifier|public
name|int
name|syntheticCount
init|=
literal|0
decl_stmt|;
specifier|public
name|int
name|stackMapEntryCount
init|=
literal|0
decl_stmt|;
specifier|public
name|int
name|stackMapCount
init|=
literal|0
decl_stmt|;
specifier|public
name|int
name|sourceFileCount
init|=
literal|0
decl_stmt|;
specifier|public
name|int
name|signatureAnnotationCount
init|=
literal|0
decl_stmt|;
specifier|public
name|int
name|parameterAnnotationCount
init|=
literal|0
decl_stmt|;
specifier|public
name|int
name|methodCount
init|=
literal|0
decl_stmt|;
specifier|public
name|int
name|localVariableTypeTableCount
init|=
literal|0
decl_stmt|;
specifier|public
name|int
name|localVariableTableCount
init|=
literal|0
decl_stmt|;
specifier|public
name|int
name|localVariableCount
init|=
literal|0
decl_stmt|;
specifier|public
name|int
name|lineNumberTableCount
init|=
literal|0
decl_stmt|;
specifier|public
name|int
name|lineNumberCount
init|=
literal|0
decl_stmt|;
specifier|public
name|int
name|javaClassCount
init|=
literal|0
decl_stmt|;
specifier|public
name|int
name|innerClassesCount
init|=
literal|0
decl_stmt|;
specifier|public
name|int
name|innerClassCount
init|=
literal|0
decl_stmt|;
specifier|public
name|int
name|fieldCount
init|=
literal|0
decl_stmt|;
specifier|public
name|int
name|exceptionTableCount
init|=
literal|0
decl_stmt|;
specifier|public
name|int
name|enclosingMethodCount
init|=
literal|0
decl_stmt|;
specifier|public
name|int
name|deprecatedCount
init|=
literal|0
decl_stmt|;
specifier|public
name|int
name|constantValueCount
init|=
literal|0
decl_stmt|;
specifier|public
name|int
name|constantUtf8Count
init|=
literal|0
decl_stmt|;
specifier|public
name|int
name|constantStringCount
init|=
literal|0
decl_stmt|;
specifier|public
name|int
name|constantNameAndTypeCount
init|=
literal|0
decl_stmt|;
specifier|public
name|int
name|constantPoolCount
init|=
literal|0
decl_stmt|;
specifier|public
name|int
name|constantMethodrefCount
init|=
literal|0
decl_stmt|;
specifier|public
name|int
name|constantLongCount
init|=
literal|0
decl_stmt|;
specifier|public
name|int
name|constantIntegerCount
init|=
literal|0
decl_stmt|;
specifier|public
name|int
name|constantInterfaceMethodrefCount
init|=
literal|0
decl_stmt|;
specifier|public
name|int
name|constantFloatCount
init|=
literal|0
decl_stmt|;
specifier|public
name|int
name|constantFieldrefCount
init|=
literal|0
decl_stmt|;
specifier|public
name|int
name|constantClassCount
init|=
literal|0
decl_stmt|;
specifier|public
name|int
name|constantDoubleCount
init|=
literal|0
decl_stmt|;
specifier|public
name|int
name|codeExceptionCount
init|=
literal|0
decl_stmt|;
specifier|public
name|int
name|codeCount
init|=
literal|0
decl_stmt|;
specifier|public
name|int
name|annotationEntryCount
init|=
literal|0
decl_stmt|;
specifier|public
name|int
name|annotationDefaultCount
init|=
literal|0
decl_stmt|;
specifier|public
name|int
name|annotationCount
init|=
literal|0
decl_stmt|;
comment|/** @since 6.0 */
specifier|public
name|int
name|stackMapTableCount
init|=
literal|0
decl_stmt|;
comment|/** @since 6.0 */
specifier|public
name|int
name|stackMapTableEntryCount
init|=
literal|0
decl_stmt|;
comment|/** @since 6.0 */
specifier|public
name|int
name|bootstrapMethodsCount
init|=
literal|0
decl_stmt|;
comment|/** @since 6.0 */
specifier|public
name|int
name|methodParametersCount
init|=
literal|0
decl_stmt|;
comment|/** @since 6.0 */
specifier|public
name|int
name|constantInvokeDynamic
init|=
literal|0
decl_stmt|;
annotation|@
name|Override
specifier|public
name|void
name|visitAnnotation
parameter_list|(
name|Annotations
name|obj
parameter_list|)
block|{
name|annotationCount
operator|++
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
name|annotationDefaultCount
operator|++
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitAnnotationEntry
parameter_list|(
name|AnnotationEntry
name|obj
parameter_list|)
block|{
name|annotationEntryCount
operator|++
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitCode
parameter_list|(
name|Code
name|obj
parameter_list|)
block|{
name|codeCount
operator|++
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitCodeException
parameter_list|(
name|CodeException
name|obj
parameter_list|)
block|{
name|codeExceptionCount
operator|++
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitConstantClass
parameter_list|(
name|ConstantClass
name|obj
parameter_list|)
block|{
name|constantClassCount
operator|++
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitConstantDouble
parameter_list|(
name|ConstantDouble
name|obj
parameter_list|)
block|{
name|constantDoubleCount
operator|++
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitConstantFieldref
parameter_list|(
name|ConstantFieldref
name|obj
parameter_list|)
block|{
name|constantFieldrefCount
operator|++
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitConstantFloat
parameter_list|(
name|ConstantFloat
name|obj
parameter_list|)
block|{
name|constantFloatCount
operator|++
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitConstantInteger
parameter_list|(
name|ConstantInteger
name|obj
parameter_list|)
block|{
name|constantIntegerCount
operator|++
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitConstantInterfaceMethodref
parameter_list|(
name|ConstantInterfaceMethodref
name|obj
parameter_list|)
block|{
name|constantInterfaceMethodrefCount
operator|++
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitConstantLong
parameter_list|(
name|ConstantLong
name|obj
parameter_list|)
block|{
name|constantLongCount
operator|++
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitConstantMethodref
parameter_list|(
name|ConstantMethodref
name|obj
parameter_list|)
block|{
name|constantMethodrefCount
operator|++
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitConstantNameAndType
parameter_list|(
name|ConstantNameAndType
name|obj
parameter_list|)
block|{
name|constantNameAndTypeCount
operator|++
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitConstantPool
parameter_list|(
name|ConstantPool
name|obj
parameter_list|)
block|{
name|constantPoolCount
operator|++
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitConstantString
parameter_list|(
name|ConstantString
name|obj
parameter_list|)
block|{
name|constantStringCount
operator|++
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitConstantUtf8
parameter_list|(
name|ConstantUtf8
name|obj
parameter_list|)
block|{
name|constantUtf8Count
operator|++
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitConstantValue
parameter_list|(
name|ConstantValue
name|obj
parameter_list|)
block|{
name|constantValueCount
operator|++
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitDeprecated
parameter_list|(
name|Deprecated
name|obj
parameter_list|)
block|{
name|deprecatedCount
operator|++
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
name|enclosingMethodCount
operator|++
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitExceptionTable
parameter_list|(
name|ExceptionTable
name|obj
parameter_list|)
block|{
name|exceptionTableCount
operator|++
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitField
parameter_list|(
name|Field
name|obj
parameter_list|)
block|{
name|fieldCount
operator|++
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitInnerClass
parameter_list|(
name|InnerClass
name|obj
parameter_list|)
block|{
name|innerClassCount
operator|++
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitInnerClasses
parameter_list|(
name|InnerClasses
name|obj
parameter_list|)
block|{
name|innerClassesCount
operator|++
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitJavaClass
parameter_list|(
name|JavaClass
name|obj
parameter_list|)
block|{
name|javaClassCount
operator|++
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitLineNumber
parameter_list|(
name|LineNumber
name|obj
parameter_list|)
block|{
name|lineNumberCount
operator|++
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitLineNumberTable
parameter_list|(
name|LineNumberTable
name|obj
parameter_list|)
block|{
name|lineNumberTableCount
operator|++
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitLocalVariable
parameter_list|(
name|LocalVariable
name|obj
parameter_list|)
block|{
name|localVariableCount
operator|++
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitLocalVariableTable
parameter_list|(
name|LocalVariableTable
name|obj
parameter_list|)
block|{
name|localVariableTableCount
operator|++
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
name|localVariableTypeTableCount
operator|++
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitMethod
parameter_list|(
name|Method
name|obj
parameter_list|)
block|{
name|methodCount
operator|++
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
name|parameterAnnotationCount
operator|++
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitSignature
parameter_list|(
name|Signature
name|obj
parameter_list|)
block|{
name|signatureAnnotationCount
operator|++
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitSourceFile
parameter_list|(
name|SourceFile
name|obj
parameter_list|)
block|{
name|sourceFileCount
operator|++
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitStackMap
parameter_list|(
name|StackMap
name|obj
parameter_list|)
block|{
name|stackMapCount
operator|++
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitStackMapEntry
parameter_list|(
name|StackMapEntry
name|obj
parameter_list|)
block|{
name|stackMapEntryCount
operator|++
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitSynthetic
parameter_list|(
name|Synthetic
name|obj
parameter_list|)
block|{
name|syntheticCount
operator|++
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitUnknown
parameter_list|(
name|Unknown
name|obj
parameter_list|)
block|{
name|unknownCount
operator|++
expr_stmt|;
block|}
comment|/** @since 6.0 */
annotation|@
name|Override
specifier|public
name|void
name|visitStackMapTable
parameter_list|(
name|StackMapTable
name|obj
parameter_list|)
block|{
name|stackMapTableCount
operator|++
expr_stmt|;
block|}
comment|/** @since 6.0 */
annotation|@
name|Override
specifier|public
name|void
name|visitStackMapTableEntry
parameter_list|(
name|StackMapTableEntry
name|obj
parameter_list|)
block|{
name|stackMapTableEntryCount
operator|++
expr_stmt|;
block|}
comment|/** @since 6.0 */
annotation|@
name|Override
specifier|public
name|void
name|visitBootstrapMethods
parameter_list|(
name|BootstrapMethods
name|obj
parameter_list|)
block|{
name|bootstrapMethodsCount
operator|++
expr_stmt|;
block|}
comment|/** @since 6.0 */
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
name|methodParametersCount
operator|++
expr_stmt|;
block|}
comment|/** @since 6.0 */
annotation|@
name|Override
specifier|public
name|void
name|visitConstantInvokeDynamic
parameter_list|(
name|ConstantInvokeDynamic
name|obj
parameter_list|)
block|{
name|constantInvokeDynamic
operator|++
expr_stmt|;
block|}
block|}
end_class

end_unit

