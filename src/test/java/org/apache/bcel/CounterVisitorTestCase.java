begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
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
name|ClassPath
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
name|SyntheticRepository
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
name|JavaClass
name|javaClass
init|=
name|SyntheticRepository
operator|.
name|getInstance
argument_list|(
operator|new
name|ClassPath
argument_list|(
literal|"file://F:/GSoC/Dmitriy/bcel-j5/target/test-classes/"
argument_list|)
argument_list|)
operator|.
name|loadClass
argument_list|(
literal|"org.apache.bcel.data.package-info"
argument_list|)
decl_stmt|;
name|System
operator|.
name|out
operator|.
name|println
argument_list|(
name|javaClass
operator|.
name|isAbstract
argument_list|()
argument_list|)
expr_stmt|;
return|return
name|javaClass
return|;
block|}
specifier|public
name|void
name|testAnnotationsCount
parameter_list|()
block|{
name|System
operator|.
name|out
operator|.
name|println
argument_list|(
literal|"AnnotationsCount = "
operator|+
name|getVisitor
argument_list|()
operator|.
name|annotationCount
argument_list|)
expr_stmt|;
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
name|System
operator|.
name|out
operator|.
name|println
argument_list|(
literal|"AnnotationDefaultCount = "
operator|+
name|getVisitor
argument_list|()
operator|.
name|annotationDefaultCount
argument_list|)
expr_stmt|;
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
name|System
operator|.
name|out
operator|.
name|println
argument_list|(
literal|"AnnotationEntryCount = "
operator|+
name|getVisitor
argument_list|()
operator|.
name|annotationEntryCount
argument_list|)
expr_stmt|;
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
name|System
operator|.
name|out
operator|.
name|println
argument_list|(
literal|"CodeCount = "
operator|+
name|getVisitor
argument_list|()
operator|.
name|codeCount
argument_list|)
expr_stmt|;
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
name|System
operator|.
name|out
operator|.
name|println
argument_list|(
literal|"CodeExceptionCount = "
operator|+
name|getVisitor
argument_list|()
operator|.
name|codeExceptionCount
argument_list|)
expr_stmt|;
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
name|System
operator|.
name|out
operator|.
name|println
argument_list|(
literal|"ConstantClassCount = "
operator|+
name|getVisitor
argument_list|()
operator|.
name|constantClassCount
argument_list|)
expr_stmt|;
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
name|System
operator|.
name|out
operator|.
name|println
argument_list|(
literal|"ConstantDoubleCount = "
operator|+
name|getVisitor
argument_list|()
operator|.
name|constantDoubleCount
argument_list|)
expr_stmt|;
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
name|System
operator|.
name|out
operator|.
name|println
argument_list|(
literal|"ConstantFieldrefCount = "
operator|+
name|getVisitor
argument_list|()
operator|.
name|constantFieldrefCount
argument_list|)
expr_stmt|;
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
name|System
operator|.
name|out
operator|.
name|println
argument_list|(
literal|"ConstantFloatCount = "
operator|+
name|getVisitor
argument_list|()
operator|.
name|constantFloatCount
argument_list|)
expr_stmt|;
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
name|System
operator|.
name|out
operator|.
name|println
argument_list|(
literal|"ConstantIntegerCount = "
operator|+
name|getVisitor
argument_list|()
operator|.
name|constantIntegerCount
argument_list|)
expr_stmt|;
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
name|System
operator|.
name|out
operator|.
name|println
argument_list|(
literal|"ConstantInterfaceMethodrefCount = "
operator|+
name|getVisitor
argument_list|()
operator|.
name|constantInterfaceMethodrefCount
argument_list|)
expr_stmt|;
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
name|System
operator|.
name|out
operator|.
name|println
argument_list|(
literal|"ConstantLongCount = "
operator|+
name|getVisitor
argument_list|()
operator|.
name|constantLongCount
argument_list|)
expr_stmt|;
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
name|System
operator|.
name|out
operator|.
name|println
argument_list|(
literal|"ConstantMethodrefCount = "
operator|+
name|getVisitor
argument_list|()
operator|.
name|constantMethodrefCount
argument_list|)
expr_stmt|;
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
name|System
operator|.
name|out
operator|.
name|println
argument_list|(
literal|"ConstantNameAndTypeCount = "
operator|+
name|getVisitor
argument_list|()
operator|.
name|constantNameAndTypeCount
argument_list|)
expr_stmt|;
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
name|System
operator|.
name|out
operator|.
name|println
argument_list|(
literal|"ConstantPoolCount = "
operator|+
name|getVisitor
argument_list|()
operator|.
name|constantPoolCount
argument_list|)
expr_stmt|;
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
name|System
operator|.
name|out
operator|.
name|println
argument_list|(
literal|"ConstantStringCount = "
operator|+
name|getVisitor
argument_list|()
operator|.
name|constantStringCount
argument_list|)
expr_stmt|;
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
name|System
operator|.
name|out
operator|.
name|println
argument_list|(
literal|"ConstantValueCount = "
operator|+
name|getVisitor
argument_list|()
operator|.
name|constantValueCount
argument_list|)
expr_stmt|;
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
name|System
operator|.
name|out
operator|.
name|println
argument_list|(
literal|"DeprecatedCount = "
operator|+
name|getVisitor
argument_list|()
operator|.
name|deprecatedCount
argument_list|)
expr_stmt|;
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
name|System
operator|.
name|out
operator|.
name|println
argument_list|(
literal|"EnclosingMethodCount = "
operator|+
name|getVisitor
argument_list|()
operator|.
name|enclosingMethodCount
argument_list|)
expr_stmt|;
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
name|System
operator|.
name|out
operator|.
name|println
argument_list|(
literal|"ExceptionTableCount = "
operator|+
name|getVisitor
argument_list|()
operator|.
name|exceptionTableCount
argument_list|)
expr_stmt|;
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
name|System
operator|.
name|out
operator|.
name|println
argument_list|(
literal|"FieldCount = "
operator|+
name|getVisitor
argument_list|()
operator|.
name|fieldCount
argument_list|)
expr_stmt|;
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
name|System
operator|.
name|out
operator|.
name|println
argument_list|(
literal|"InnerClassCount = "
operator|+
name|getVisitor
argument_list|()
operator|.
name|innerClassCount
argument_list|)
expr_stmt|;
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
name|System
operator|.
name|out
operator|.
name|println
argument_list|(
literal|"InnerClassesCount = "
operator|+
name|getVisitor
argument_list|()
operator|.
name|innerClassesCount
argument_list|)
expr_stmt|;
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
name|System
operator|.
name|out
operator|.
name|println
argument_list|(
literal|"JavaClassCount = "
operator|+
name|getVisitor
argument_list|()
operator|.
name|javaClassCount
argument_list|)
expr_stmt|;
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
name|System
operator|.
name|out
operator|.
name|println
argument_list|(
literal|"LineNumberCount = "
operator|+
name|getVisitor
argument_list|()
operator|.
name|lineNumberCount
argument_list|)
expr_stmt|;
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
name|System
operator|.
name|out
operator|.
name|println
argument_list|(
literal|"LineNumberTableCount = "
operator|+
name|getVisitor
argument_list|()
operator|.
name|lineNumberTableCount
argument_list|)
expr_stmt|;
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
name|System
operator|.
name|out
operator|.
name|println
argument_list|(
literal|"LocalVariableCount = "
operator|+
name|getVisitor
argument_list|()
operator|.
name|localVariableCount
argument_list|)
expr_stmt|;
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
name|System
operator|.
name|out
operator|.
name|println
argument_list|(
literal|"LocalVariableTableCount = "
operator|+
name|getVisitor
argument_list|()
operator|.
name|localVariableTableCount
argument_list|)
expr_stmt|;
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
name|System
operator|.
name|out
operator|.
name|println
argument_list|(
literal|"LocalVariableTypeTableCount = "
operator|+
name|getVisitor
argument_list|()
operator|.
name|localVariableTypeTableCount
argument_list|)
expr_stmt|;
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
name|System
operator|.
name|out
operator|.
name|println
argument_list|(
literal|"MethodCount = "
operator|+
name|getVisitor
argument_list|()
operator|.
name|methodCount
argument_list|)
expr_stmt|;
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
name|System
operator|.
name|out
operator|.
name|println
argument_list|(
literal|"ParameterAnnotationCount = "
operator|+
name|getVisitor
argument_list|()
operator|.
name|methodCount
argument_list|)
expr_stmt|;
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
name|System
operator|.
name|out
operator|.
name|println
argument_list|(
literal|"SignatureCount = "
operator|+
name|getVisitor
argument_list|()
operator|.
name|signatureAnnotationCount
argument_list|)
expr_stmt|;
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
name|System
operator|.
name|out
operator|.
name|println
argument_list|(
literal|"SourceFileCount = "
operator|+
name|getVisitor
argument_list|()
operator|.
name|sourceFileCount
argument_list|)
expr_stmt|;
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
name|System
operator|.
name|out
operator|.
name|println
argument_list|(
literal|"StackMapCount = "
operator|+
name|getVisitor
argument_list|()
operator|.
name|stackMapCount
argument_list|)
expr_stmt|;
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
name|System
operator|.
name|out
operator|.
name|println
argument_list|(
literal|"StackMapEntryCount = "
operator|+
name|getVisitor
argument_list|()
operator|.
name|stackMapEntryCount
argument_list|)
expr_stmt|;
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
name|System
operator|.
name|out
operator|.
name|println
argument_list|(
literal|"SyntheticCount = "
operator|+
name|getVisitor
argument_list|()
operator|.
name|syntheticCount
argument_list|)
expr_stmt|;
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
name|System
operator|.
name|out
operator|.
name|println
argument_list|(
literal|"UnknownCount = "
operator|+
name|getVisitor
argument_list|()
operator|.
name|unknownCount
argument_list|)
expr_stmt|;
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

