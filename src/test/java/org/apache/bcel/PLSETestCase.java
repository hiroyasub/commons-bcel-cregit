begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
begin_comment
comment|/*  * Licensed to the Apache Software Foundation (ASF) under one or more  * contributor license agreements.  See the NOTICE file distributed with  * this work for additional information regarding copyright ownership.  * The ASF licenses this file to You under the Apache License, Version 2.0  * (the "License"); you may not use this file except in compliance with  * the License.  You may obtain a copy of the License at  *  *   http://www.apache.org/licenses/LICENSE-2.0  *  * Unless required by applicable law or agreed to in writing, software  * distributed under the License is distributed on an "AS IS" BASIS,  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  * See the License for the specific language governing permissions and  * limitations under the License.  *  */
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
name|Code
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
name|LineNumber
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
name|LineNumberTable
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
name|LocalVariable
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
name|LocalVariableTable
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
name|generic
operator|.
name|ClassGen
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
name|generic
operator|.
name|ConstantPoolGen
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
name|generic
operator|.
name|InstructionHandle
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
name|generic
operator|.
name|InstructionList
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
name|generic
operator|.
name|InvokeInstruction
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
name|generic
operator|.
name|MethodGen
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
name|generic
operator|.
name|Type
import|;
end_import

begin_import
import|import
name|org
operator|.
name|junit
operator|.
name|jupiter
operator|.
name|api
operator|.
name|Test
import|;
end_import

begin_import
import|import static
name|org
operator|.
name|junit
operator|.
name|jupiter
operator|.
name|api
operator|.
name|Assertions
operator|.
name|assertEquals
import|;
end_import

begin_import
import|import static
name|org
operator|.
name|junit
operator|.
name|jupiter
operator|.
name|api
operator|.
name|Assertions
operator|.
name|assertFalse
import|;
end_import

begin_import
import|import
name|java
operator|.
name|util
operator|.
name|stream
operator|.
name|Stream
import|;
end_import

begin_class
specifier|public
class|class
name|PLSETestCase
extends|extends
name|AbstractTestCase
block|{
comment|/**      * BCEL-208: A couple of methods in MethodGen.java need to test for      * an empty instruction list.      */
annotation|@
name|Test
specifier|public
name|void
name|testB208
parameter_list|()
throws|throws
name|ClassNotFoundException
block|{
specifier|final
name|JavaClass
name|clazz
init|=
name|getTestClass
argument_list|(
name|PACKAGE_BASE_NAME
operator|+
literal|".data.PLSETestClass"
argument_list|)
decl_stmt|;
specifier|final
name|ClassGen
name|gen
init|=
operator|new
name|ClassGen
argument_list|(
name|clazz
argument_list|)
decl_stmt|;
specifier|final
name|ConstantPoolGen
name|pool
init|=
name|gen
operator|.
name|getConstantPool
argument_list|()
decl_stmt|;
specifier|final
name|Method
name|m
init|=
name|gen
operator|.
name|getMethodAt
argument_list|(
literal|1
argument_list|)
decl_stmt|;
specifier|final
name|MethodGen
name|mg
init|=
operator|new
name|MethodGen
argument_list|(
name|m
argument_list|,
name|gen
operator|.
name|getClassName
argument_list|()
argument_list|,
name|pool
argument_list|)
decl_stmt|;
name|mg
operator|.
name|setInstructionList
argument_list|(
literal|null
argument_list|)
expr_stmt|;
name|mg
operator|.
name|addLocalVariable
argument_list|(
literal|"local2"
argument_list|,
name|Type
operator|.
name|INT
argument_list|,
literal|null
argument_list|,
literal|null
argument_list|)
expr_stmt|;
comment|// currently, this will cause null pointer exception
name|mg
operator|.
name|getLocalVariableTable
argument_list|(
name|pool
argument_list|)
expr_stmt|;
block|}
comment|/**      * BCEL-79:      */
annotation|@
name|Test
specifier|public
name|void
name|testB79
parameter_list|()
throws|throws
name|ClassNotFoundException
block|{
specifier|final
name|JavaClass
name|clazz
init|=
name|getTestClass
argument_list|(
name|PACKAGE_BASE_NAME
operator|+
literal|".data.PLSETestClass"
argument_list|)
decl_stmt|;
specifier|final
name|ClassGen
name|gen
init|=
operator|new
name|ClassGen
argument_list|(
name|clazz
argument_list|)
decl_stmt|;
specifier|final
name|ConstantPoolGen
name|pool
init|=
name|gen
operator|.
name|getConstantPool
argument_list|()
decl_stmt|;
specifier|final
name|Method
name|m
init|=
name|gen
operator|.
name|getMethodAt
argument_list|(
literal|2
argument_list|)
decl_stmt|;
specifier|final
name|LocalVariableTable
name|lvt
init|=
name|m
operator|.
name|getLocalVariableTable
argument_list|()
decl_stmt|;
comment|//System.out.println(lvt);
comment|//System.out.println(lvt.getTableLength());
specifier|final
name|MethodGen
name|mg
init|=
operator|new
name|MethodGen
argument_list|(
name|m
argument_list|,
name|gen
operator|.
name|getClassName
argument_list|()
argument_list|,
name|pool
argument_list|)
decl_stmt|;
specifier|final
name|LocalVariableTable
name|new_lvt
init|=
name|mg
operator|.
name|getLocalVariableTable
argument_list|(
name|mg
operator|.
name|getConstantPool
argument_list|()
argument_list|)
decl_stmt|;
comment|//System.out.println(new_lvt);
name|assertEquals
argument_list|(
name|lvt
operator|.
name|getTableLength
argument_list|()
argument_list|,
name|new_lvt
operator|.
name|getTableLength
argument_list|()
argument_list|,
literal|"number of locals"
argument_list|)
expr_stmt|;
block|}
comment|/**      * BCEL-262:      */
annotation|@
name|Test
specifier|public
name|void
name|testB262
parameter_list|()
throws|throws
name|ClassNotFoundException
block|{
specifier|final
name|JavaClass
name|clazz
init|=
name|getTestClass
argument_list|(
name|PACKAGE_BASE_NAME
operator|+
literal|".data.PLSETestEnum"
argument_list|)
decl_stmt|;
specifier|final
name|ClassGen
name|gen
init|=
operator|new
name|ClassGen
argument_list|(
name|clazz
argument_list|)
decl_stmt|;
specifier|final
name|ConstantPoolGen
name|pool
init|=
name|gen
operator|.
name|getConstantPool
argument_list|()
decl_stmt|;
comment|// get the values() method
specifier|final
name|Method
name|m
init|=
name|gen
operator|.
name|getMethodAt
argument_list|(
literal|0
argument_list|)
decl_stmt|;
specifier|final
name|MethodGen
name|mg
init|=
operator|new
name|MethodGen
argument_list|(
name|m
argument_list|,
name|gen
operator|.
name|getClassName
argument_list|()
argument_list|,
name|pool
argument_list|)
decl_stmt|;
specifier|final
name|InstructionList
name|il
init|=
name|mg
operator|.
name|getInstructionList
argument_list|()
decl_stmt|;
comment|// get the invokevirtual instruction
specifier|final
name|InstructionHandle
name|ih
init|=
name|il
operator|.
name|findHandle
argument_list|(
literal|3
argument_list|)
decl_stmt|;
specifier|final
name|InvokeInstruction
name|ii
init|=
operator|(
name|InvokeInstruction
operator|)
name|ih
operator|.
name|getInstruction
argument_list|()
decl_stmt|;
comment|// without fix, the getClassName() will throw:
comment|//   java.lang.IllegalArgumentException: Cannot be used on an array type
specifier|final
name|String
name|cn
init|=
name|ii
operator|.
name|getClassName
argument_list|(
name|pool
argument_list|)
decl_stmt|;
name|assertEquals
argument_list|(
literal|"[Lorg.apache.bcel.data.PLSETestEnum;"
argument_list|,
name|cn
argument_list|)
expr_stmt|;
block|}
comment|/**      * BCEL-295:      */
annotation|@
name|Test
specifier|public
name|void
name|testB295
parameter_list|()
throws|throws
name|Exception
block|{
specifier|final
name|JavaClass
name|clazz
init|=
name|getTestClass
argument_list|(
name|PACKAGE_BASE_NAME
operator|+
literal|".data.PLSETestClass2"
argument_list|)
decl_stmt|;
specifier|final
name|ClassGen
name|cg
init|=
operator|new
name|ClassGen
argument_list|(
name|clazz
argument_list|)
decl_stmt|;
specifier|final
name|ConstantPoolGen
name|pool
init|=
name|cg
operator|.
name|getConstantPool
argument_list|()
decl_stmt|;
specifier|final
name|Method
name|m
init|=
name|cg
operator|.
name|getMethodAt
argument_list|(
literal|1
argument_list|)
decl_stmt|;
comment|// 'main'
specifier|final
name|LocalVariableTable
name|lvt
init|=
name|m
operator|.
name|getLocalVariableTable
argument_list|()
decl_stmt|;
specifier|final
name|LocalVariable
name|lv
init|=
name|lvt
operator|.
name|getLocalVariable
argument_list|(
literal|2
argument_list|,
literal|4
argument_list|)
decl_stmt|;
comment|// 'i'
comment|//System.out.println(lv);
specifier|final
name|MethodGen
name|mg
init|=
operator|new
name|MethodGen
argument_list|(
name|m
argument_list|,
name|cg
operator|.
name|getClassName
argument_list|()
argument_list|,
name|pool
argument_list|)
decl_stmt|;
specifier|final
name|LocalVariableTable
name|new_lvt
init|=
name|mg
operator|.
name|getLocalVariableTable
argument_list|(
name|mg
operator|.
name|getConstantPool
argument_list|()
argument_list|)
decl_stmt|;
specifier|final
name|LocalVariable
name|new_lv
init|=
name|new_lvt
operator|.
name|getLocalVariable
argument_list|(
literal|2
argument_list|,
literal|4
argument_list|)
decl_stmt|;
comment|// 'i'
comment|//System.out.println(new_lv);
name|assertEquals
argument_list|(
name|lv
operator|.
name|getLength
argument_list|()
argument_list|,
name|new_lv
operator|.
name|getLength
argument_list|()
argument_list|,
literal|"live range length"
argument_list|)
expr_stmt|;
block|}
comment|/**      * BCEL-361: LineNumber.toString() treats code offset as signed      */
annotation|@
name|Test
specifier|public
name|void
name|testB361
parameter_list|()
throws|throws
name|Exception
block|{
specifier|final
name|JavaClass
name|clazz
init|=
name|getTestClass
argument_list|(
name|PACKAGE_BASE_NAME
operator|+
literal|".data.LargeMethod"
argument_list|)
decl_stmt|;
specifier|final
name|Method
index|[]
name|methods
init|=
name|clazz
operator|.
name|getMethods
argument_list|()
decl_stmt|;
specifier|final
name|Method
name|m
init|=
name|methods
index|[
literal|0
index|]
decl_stmt|;
comment|//System.out.println(m.getName());
specifier|final
name|Code
name|code
init|=
name|m
operator|.
name|getCode
argument_list|()
decl_stmt|;
specifier|final
name|LineNumberTable
name|lnt
init|=
name|code
operator|.
name|getLineNumberTable
argument_list|()
decl_stmt|;
specifier|final
name|LineNumber
index|[]
name|lineNumbers
init|=
name|lnt
operator|.
name|getLineNumberTable
argument_list|()
decl_stmt|;
specifier|final
name|String
name|data
init|=
name|lineNumbers
index|[
name|lineNumbers
operator|.
name|length
operator|-
literal|1
index|]
operator|.
name|toString
argument_list|()
decl_stmt|;
comment|//System.out.println(data);
comment|//System.out.println(data.contains("-"));
name|assertFalse
argument_list|(
name|data
operator|.
name|contains
argument_list|(
literal|"-"
argument_list|)
argument_list|,
literal|"code offsets must be positive"
argument_list|)
expr_stmt|;
name|Stream
operator|.
name|of
argument_list|(
name|lineNumbers
argument_list|)
operator|.
name|forEach
argument_list|(
name|ln
lambda|->
name|assertFalse
argument_list|(
name|ln
operator|.
name|getLineNumber
argument_list|()
operator|<
literal|0
argument_list|)
argument_list|)
expr_stmt|;
name|Stream
operator|.
name|of
argument_list|(
name|lineNumbers
argument_list|)
operator|.
name|forEach
argument_list|(
name|ln
lambda|->
name|assertFalse
argument_list|(
name|ln
operator|.
name|getStartPC
argument_list|()
operator|<
literal|0
argument_list|)
argument_list|)
expr_stmt|;
block|}
comment|/**      * Test to improve BCEL tests code coverage for classfile/Utility.java.      */
annotation|@
name|Test
specifier|public
name|void
name|testCoverage
parameter_list|()
throws|throws
name|ClassNotFoundException
throws|,
name|java
operator|.
name|io
operator|.
name|IOException
block|{
comment|// load a class with a wide variety of byte codes - including tableswitch and lookupswitch
specifier|final
name|JavaClass
name|clazz
init|=
name|getTestClass
argument_list|(
name|PACKAGE_BASE_NAME
operator|+
literal|".data.ConstantPoolX"
argument_list|)
decl_stmt|;
for|for
control|(
specifier|final
name|Method
name|m
range|:
name|clazz
operator|.
name|getMethods
argument_list|()
control|)
block|{
specifier|final
name|String
name|signature
init|=
name|m
operator|.
name|getSignature
argument_list|()
decl_stmt|;
name|Utility
operator|.
name|methodTypeToSignature
argument_list|(
name|Utility
operator|.
name|methodSignatureReturnType
argument_list|(
name|signature
argument_list|)
argument_list|,
name|Utility
operator|.
name|methodSignatureArgumentTypes
argument_list|(
name|signature
argument_list|)
argument_list|)
expr_stmt|;
comment|// discard result
specifier|final
name|Code
name|code
init|=
name|m
operator|.
name|getCode
argument_list|()
decl_stmt|;
if|if
condition|(
name|code
operator|!=
literal|null
condition|)
block|{
specifier|final
name|String
name|encoded
init|=
name|Utility
operator|.
name|encode
argument_list|(
name|code
operator|.
name|getCode
argument_list|()
argument_list|,
literal|true
argument_list|)
decl_stmt|;
comment|// following statement will throw exeception without classfile/Utility.encode fix
name|Utility
operator|.
name|decode
argument_list|(
name|encoded
argument_list|,
literal|true
argument_list|)
expr_stmt|;
comment|// discard result
name|code
operator|.
name|toString
argument_list|()
expr_stmt|;
comment|// discard result
block|}
block|}
block|}
block|}
end_class

end_unit

