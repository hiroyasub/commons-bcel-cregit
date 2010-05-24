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

begin_class
specifier|public
class|class
name|CounterVisitorTestCase
extends|extends
name|AbstractCounterVisitorTestCase
block|{
specifier|protected
name|JavaClass
name|getTestClass
parameter_list|()
throws|throws
name|ClassNotFoundException
block|{
return|return
name|getTestClass
argument_list|(
literal|"org.apache.bcel.data.MarkedType"
argument_list|)
return|;
block|}
specifier|public
name|void
name|testAnnotationsCount
parameter_list|()
block|{
comment|// System.out
comment|// .println("AnnotationsCount = " + getVisitor().annotationCount);
name|assertTrue
argument_list|(
name|getVisitor
argument_list|()
operator|.
name|annotationCount
operator|==
literal|2
argument_list|)
expr_stmt|;
block|}
specifier|public
name|void
name|testAnnotationDefaultCount
parameter_list|()
block|{
comment|// System.out.println("AnnotationDefaultCount = "
comment|// + getVisitor().annotationDefaultCount);
name|assertTrue
argument_list|(
name|getVisitor
argument_list|()
operator|.
name|annotationDefaultCount
operator|==
literal|0
argument_list|)
expr_stmt|;
block|}
specifier|public
name|void
name|testAnnotationEntryCount
parameter_list|()
block|{
comment|// System.out.println("AnnotationEntryCount = "
comment|// + getVisitor().annotationEntryCount);
name|assertTrue
argument_list|(
name|getVisitor
argument_list|()
operator|.
name|annotationEntryCount
operator|==
literal|0
argument_list|)
expr_stmt|;
block|}
specifier|public
name|void
name|testCodeCount
parameter_list|()
block|{
comment|// System.out.println("CodeCount = " + getVisitor().codeCount);
name|assertTrue
argument_list|(
name|getVisitor
argument_list|()
operator|.
name|codeCount
operator|==
literal|1
argument_list|)
expr_stmt|;
block|}
specifier|public
name|void
name|testCodeExceptionCount
parameter_list|()
block|{
comment|// System.out.println("CodeExceptionCount = "
comment|// + getVisitor().codeExceptionCount);
name|assertTrue
argument_list|(
name|getVisitor
argument_list|()
operator|.
name|codeExceptionCount
operator|==
literal|0
argument_list|)
expr_stmt|;
block|}
specifier|public
name|void
name|testConstantClassCount
parameter_list|()
block|{
comment|// System.out.println("ConstantClassCount = "
comment|// + getVisitor().constantClassCount);
name|assertTrue
argument_list|(
name|getVisitor
argument_list|()
operator|.
name|constantClassCount
operator|==
literal|2
argument_list|)
expr_stmt|;
block|}
specifier|public
name|void
name|testConstantDoubleCount
parameter_list|()
block|{
comment|// System.out.println("ConstantDoubleCount = "
comment|// + getVisitor().constantDoubleCount);
name|assertTrue
argument_list|(
name|getVisitor
argument_list|()
operator|.
name|constantDoubleCount
operator|==
literal|0
argument_list|)
expr_stmt|;
block|}
specifier|public
name|void
name|testConstantFieldrefCount
parameter_list|()
block|{
comment|// System.out.println("ConstantFieldrefCount = "
comment|// + getVisitor().constantFieldrefCount);
name|assertTrue
argument_list|(
name|getVisitor
argument_list|()
operator|.
name|constantFieldrefCount
operator|==
literal|0
argument_list|)
expr_stmt|;
block|}
specifier|public
name|void
name|testConstantFloatCount
parameter_list|()
block|{
comment|// System.out.println("ConstantFloatCount = "
comment|// + getVisitor().constantFloatCount);
name|assertTrue
argument_list|(
name|getVisitor
argument_list|()
operator|.
name|constantFloatCount
operator|==
literal|0
argument_list|)
expr_stmt|;
block|}
specifier|public
name|void
name|testConstantIntegerCount
parameter_list|()
block|{
comment|// System.out.println("ConstantIntegerCount = "
comment|// + getVisitor().constantIntegerCount);
name|assertTrue
argument_list|(
name|getVisitor
argument_list|()
operator|.
name|constantIntegerCount
operator|==
literal|0
argument_list|)
expr_stmt|;
block|}
specifier|public
name|void
name|testConstantInterfaceMethodrefCount
parameter_list|()
block|{
comment|// System.out.println("ConstantInterfaceMethodrefCount = "
comment|// + getVisitor().constantInterfaceMethodrefCount);
name|assertTrue
argument_list|(
name|getVisitor
argument_list|()
operator|.
name|constantInterfaceMethodrefCount
operator|==
literal|0
argument_list|)
expr_stmt|;
block|}
specifier|public
name|void
name|testConstantLongCount
parameter_list|()
block|{
comment|// System.out.println("ConstantLongCount = "
comment|// + getVisitor().constantLongCount);
name|assertTrue
argument_list|(
name|getVisitor
argument_list|()
operator|.
name|constantLongCount
operator|==
literal|0
argument_list|)
expr_stmt|;
block|}
specifier|public
name|void
name|testConstantMethodrefCount
parameter_list|()
block|{
comment|// System.out.println("ConstantMethodrefCount = "
comment|// + getVisitor().constantMethodrefCount);
name|assertTrue
argument_list|(
name|getVisitor
argument_list|()
operator|.
name|constantMethodrefCount
operator|==
literal|1
argument_list|)
expr_stmt|;
block|}
specifier|public
name|void
name|testConstantNameAndTypeCount
parameter_list|()
block|{
comment|// System.out.println("ConstantNameAndTypeCount = "
comment|// + getVisitor().constantNameAndTypeCount);
name|assertTrue
argument_list|(
name|getVisitor
argument_list|()
operator|.
name|constantNameAndTypeCount
operator|==
literal|1
argument_list|)
expr_stmt|;
block|}
specifier|public
name|void
name|testConstantPoolCount
parameter_list|()
block|{
comment|// System.out.println("ConstantPoolCount = "
comment|// + getVisitor().constantPoolCount);
name|assertTrue
argument_list|(
name|getVisitor
argument_list|()
operator|.
name|constantPoolCount
operator|==
literal|1
argument_list|)
expr_stmt|;
block|}
specifier|public
name|void
name|testConstantStringCount
parameter_list|()
block|{
comment|// System.out.println("ConstantStringCount = "
comment|// + getVisitor().constantStringCount);
name|assertTrue
argument_list|(
name|getVisitor
argument_list|()
operator|.
name|constantStringCount
operator|==
literal|0
argument_list|)
expr_stmt|;
block|}
specifier|public
name|void
name|testConstantValueCount
parameter_list|()
block|{
comment|// System.out.println("ConstantValueCount = "
comment|// + getVisitor().constantValueCount);
name|assertTrue
argument_list|(
name|getVisitor
argument_list|()
operator|.
name|constantValueCount
operator|==
literal|0
argument_list|)
expr_stmt|;
block|}
specifier|public
name|void
name|testDeprecatedCount
parameter_list|()
block|{
comment|// System.out.println("DeprecatedCount = " +
comment|// getVisitor().deprecatedCount);
name|assertTrue
argument_list|(
name|getVisitor
argument_list|()
operator|.
name|deprecatedCount
operator|==
literal|0
argument_list|)
expr_stmt|;
block|}
specifier|public
name|void
name|testEnclosingMethodCount
parameter_list|()
block|{
comment|// System.out.println("EnclosingMethodCount = "
comment|// + getVisitor().enclosingMethodCount);
name|assertTrue
argument_list|(
name|getVisitor
argument_list|()
operator|.
name|enclosingMethodCount
operator|==
literal|0
argument_list|)
expr_stmt|;
block|}
specifier|public
name|void
name|testExceptionTableCount
parameter_list|()
block|{
comment|// System.out.println("ExceptionTableCount = "
comment|// + getVisitor().exceptionTableCount);
name|assertTrue
argument_list|(
name|getVisitor
argument_list|()
operator|.
name|exceptionTableCount
operator|==
literal|0
argument_list|)
expr_stmt|;
block|}
specifier|public
name|void
name|testFieldCount
parameter_list|()
block|{
comment|// System.out.println("FieldCount = " + getVisitor().fieldCount);
name|assertTrue
argument_list|(
name|getVisitor
argument_list|()
operator|.
name|fieldCount
operator|==
literal|0
argument_list|)
expr_stmt|;
block|}
specifier|public
name|void
name|testInnerClassCount
parameter_list|()
block|{
comment|// System.out.println("InnerClassCount = " +
comment|// getVisitor().innerClassCount);
name|assertTrue
argument_list|(
name|getVisitor
argument_list|()
operator|.
name|innerClassCount
operator|==
literal|0
argument_list|)
expr_stmt|;
block|}
specifier|public
name|void
name|testInnerClassesCount
parameter_list|()
block|{
comment|// System.out.println("InnerClassesCount = "
comment|// + getVisitor().innerClassesCount);
name|assertTrue
argument_list|(
name|getVisitor
argument_list|()
operator|.
name|innerClassesCount
operator|==
literal|0
argument_list|)
expr_stmt|;
block|}
specifier|public
name|void
name|testJavaClassCount
parameter_list|()
block|{
comment|// System.out.println("JavaClassCount = " +
comment|// getVisitor().javaClassCount);
name|assertTrue
argument_list|(
name|getVisitor
argument_list|()
operator|.
name|javaClassCount
operator|==
literal|1
argument_list|)
expr_stmt|;
block|}
specifier|public
name|void
name|testLineNumberCount
parameter_list|()
block|{
comment|// System.out.println("LineNumberCount = " +
comment|// getVisitor().lineNumberCount);
name|assertTrue
argument_list|(
name|getVisitor
argument_list|()
operator|.
name|lineNumberCount
operator|==
literal|1
argument_list|)
expr_stmt|;
block|}
specifier|public
name|void
name|testLineNumberTableCount
parameter_list|()
block|{
comment|// System.out.println("LineNumberTableCount = "
comment|// + getVisitor().lineNumberTableCount);
name|assertTrue
argument_list|(
name|getVisitor
argument_list|()
operator|.
name|lineNumberTableCount
operator|==
literal|1
argument_list|)
expr_stmt|;
block|}
specifier|public
name|void
name|testLocalVariableCount
parameter_list|()
block|{
comment|// System.out.println("LocalVariableCount = "
comment|// + getVisitor().localVariableCount);
name|assertTrue
argument_list|(
name|getVisitor
argument_list|()
operator|.
name|localVariableCount
operator|==
literal|1
argument_list|)
expr_stmt|;
block|}
specifier|public
name|void
name|testLocalVariableTableCount
parameter_list|()
block|{
comment|// System.out.println("LocalVariableTableCount = "
comment|// + getVisitor().localVariableTableCount);
name|assertTrue
argument_list|(
name|getVisitor
argument_list|()
operator|.
name|localVariableTableCount
operator|==
literal|1
argument_list|)
expr_stmt|;
block|}
specifier|public
name|void
name|testLocalVariableTypeTableCount
parameter_list|()
block|{
comment|// System.out.println("LocalVariableTypeTableCount = "
comment|// + getVisitor().localVariableTypeTableCount);
name|assertTrue
argument_list|(
name|getVisitor
argument_list|()
operator|.
name|localVariableTypeTableCount
operator|==
literal|0
argument_list|)
expr_stmt|;
block|}
specifier|public
name|void
name|testMethodCount
parameter_list|()
block|{
comment|// System.out.println("MethodCount = " + getVisitor().methodCount);
name|assertTrue
argument_list|(
name|getVisitor
argument_list|()
operator|.
name|methodCount
operator|==
literal|1
argument_list|)
expr_stmt|;
block|}
specifier|public
name|void
name|testParameterAnnotationCount
parameter_list|()
block|{
comment|// System.out.println("ParameterAnnotationCount = "
comment|// + getVisitor().methodCount);
name|assertTrue
argument_list|(
name|getVisitor
argument_list|()
operator|.
name|methodCount
operator|==
literal|1
argument_list|)
expr_stmt|;
block|}
specifier|public
name|void
name|testSignatureCount
parameter_list|()
block|{
comment|// System.out.println("SignatureCount = "
comment|// + getVisitor().signatureAnnotationCount);
name|assertTrue
argument_list|(
name|getVisitor
argument_list|()
operator|.
name|signatureAnnotationCount
operator|==
literal|0
argument_list|)
expr_stmt|;
block|}
specifier|public
name|void
name|testSourceFileCount
parameter_list|()
block|{
comment|// System.out.println("SourceFileCount = " +
comment|// getVisitor().sourceFileCount);
name|assertTrue
argument_list|(
name|getVisitor
argument_list|()
operator|.
name|sourceFileCount
operator|==
literal|1
argument_list|)
expr_stmt|;
block|}
specifier|public
name|void
name|testStackMapCount
parameter_list|()
block|{
comment|// System.out.println("StackMapCount = " + getVisitor().stackMapCount);
name|assertTrue
argument_list|(
name|getVisitor
argument_list|()
operator|.
name|stackMapCount
operator|==
literal|0
argument_list|)
expr_stmt|;
block|}
specifier|public
name|void
name|testStackMapEntryCount
parameter_list|()
block|{
comment|// System.out.println("StackMapEntryCount = "
comment|// + getVisitor().stackMapEntryCount);
name|assertTrue
argument_list|(
name|getVisitor
argument_list|()
operator|.
name|stackMapEntryCount
operator|==
literal|0
argument_list|)
expr_stmt|;
block|}
specifier|public
name|void
name|testSyntheticCount
parameter_list|()
block|{
comment|// System.out.println("SyntheticCount = " +
comment|// getVisitor().syntheticCount);
name|assertTrue
argument_list|(
name|getVisitor
argument_list|()
operator|.
name|syntheticCount
operator|==
literal|0
argument_list|)
expr_stmt|;
block|}
specifier|public
name|void
name|testUnknownCount
parameter_list|()
block|{
comment|// System.out.println("UnknownCount = " + getVisitor().unknownCount);
name|assertTrue
argument_list|(
name|getVisitor
argument_list|()
operator|.
name|unknownCount
operator|==
literal|0
argument_list|)
expr_stmt|;
block|}
block|}
end_class

end_unit

