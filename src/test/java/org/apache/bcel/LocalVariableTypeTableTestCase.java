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
name|generic
operator|.
name|ACONST_NULL
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
name|ALOAD
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
name|GETSTATIC
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
name|INVOKEVIRTUAL
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
name|Instruction
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
name|LocalVariableGen
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
name|Test
import|;
end_import

begin_import
import|import
name|java
operator|.
name|io
operator|.
name|IOException
import|;
end_import

begin_import
import|import
name|java
operator|.
name|lang
operator|.
name|reflect
operator|.
name|InvocationTargetException
import|;
end_import

begin_import
import|import
name|java
operator|.
name|util
operator|.
name|LinkedList
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

begin_class
specifier|public
class|class
name|LocalVariableTypeTableTestCase
extends|extends
name|AbstractTestCase
block|{
specifier|public
class|class
name|TestClassLoader
extends|extends
name|ClassLoader
block|{
specifier|public
name|TestClassLoader
parameter_list|(
name|ClassLoader
name|parent
parameter_list|)
block|{
name|super
argument_list|(
name|parent
argument_list|)
expr_stmt|;
block|}
specifier|public
name|Class
argument_list|<
name|?
argument_list|>
name|findClass
parameter_list|(
name|String
name|name
parameter_list|,
name|byte
index|[]
name|bytes
parameter_list|)
block|{
return|return
name|defineClass
argument_list|(
name|name
argument_list|,
name|bytes
argument_list|,
literal|0
argument_list|,
name|bytes
operator|.
name|length
argument_list|)
return|;
block|}
block|}
annotation|@
name|Test
specifier|public
name|void
name|testWithGenericArguement
parameter_list|()
throws|throws
name|ClassNotFoundException
throws|,
name|NoSuchMethodException
throws|,
name|InvocationTargetException
throws|,
name|IllegalAccessException
throws|,
name|IOException
block|{
name|String
name|targetClass
init|=
name|PACKAGE_BASE_NAME
operator|+
literal|".data.SimpleClassHasMethodIncludeGenericArgument"
decl_stmt|;
name|TestClassLoader
name|loader
init|=
operator|new
name|TestClassLoader
argument_list|(
name|getClass
argument_list|()
operator|.
name|getClassLoader
argument_list|()
argument_list|)
decl_stmt|;
name|Class
name|cls
init|=
name|loader
operator|.
name|findClass
argument_list|(
name|targetClass
argument_list|,
name|getBytesFromClass
argument_list|(
name|targetClass
argument_list|)
argument_list|)
decl_stmt|;
name|java
operator|.
name|lang
operator|.
name|reflect
operator|.
name|Method
name|method
init|=
name|cls
operator|.
name|getDeclaredMethod
argument_list|(
literal|"a"
argument_list|,
name|String
operator|.
name|class
argument_list|,
name|List
operator|.
name|class
argument_list|)
decl_stmt|;
name|method
operator|.
name|invoke
argument_list|(
literal|null
argument_list|,
literal|"a1"
argument_list|,
operator|new
name|LinkedList
argument_list|<
name|String
argument_list|>
argument_list|()
argument_list|)
expr_stmt|;
name|method
operator|=
name|cls
operator|.
name|getDeclaredMethod
argument_list|(
literal|"b"
argument_list|,
name|String
operator|.
name|class
argument_list|,
name|List
operator|.
name|class
argument_list|)
expr_stmt|;
name|method
operator|.
name|invoke
argument_list|(
literal|null
argument_list|,
literal|"b1"
argument_list|,
operator|new
name|LinkedList
argument_list|<
name|String
argument_list|>
argument_list|()
argument_list|)
expr_stmt|;
name|method
operator|=
name|cls
operator|.
name|getDeclaredMethod
argument_list|(
literal|"c"
argument_list|,
name|String
operator|.
name|class
argument_list|,
name|String
operator|.
name|class
argument_list|)
expr_stmt|;
name|method
operator|.
name|invoke
argument_list|(
literal|null
argument_list|,
literal|"c1"
argument_list|,
literal|"c2"
argument_list|)
expr_stmt|;
name|method
operator|=
name|cls
operator|.
name|getDeclaredMethod
argument_list|(
literal|"d"
argument_list|,
name|List
operator|.
name|class
argument_list|,
name|String
operator|.
name|class
argument_list|)
expr_stmt|;
name|method
operator|.
name|invoke
argument_list|(
literal|null
argument_list|,
operator|new
name|LinkedList
argument_list|<
name|String
argument_list|>
argument_list|()
argument_list|,
literal|"d2"
argument_list|)
expr_stmt|;
block|}
specifier|private
name|byte
index|[]
name|getBytesFromClass
parameter_list|(
name|String
name|className
parameter_list|)
throws|throws
name|ClassNotFoundException
throws|,
name|IOException
block|{
name|JavaClass
name|clazz
init|=
name|getTestClass
argument_list|(
name|className
argument_list|)
decl_stmt|;
name|ConstantPoolGen
name|cp
init|=
operator|new
name|ConstantPoolGen
argument_list|(
name|clazz
operator|.
name|getConstantPool
argument_list|()
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
name|Method
name|method
init|=
name|methods
index|[
name|i
index|]
decl_stmt|;
if|if
condition|(
operator|!
name|method
operator|.
name|isNative
argument_list|()
operator|&&
operator|!
name|method
operator|.
name|isAbstract
argument_list|()
condition|)
block|{
name|methods
index|[
name|i
index|]
operator|=
name|injection
argument_list|(
name|clazz
argument_list|,
name|method
argument_list|,
name|cp
argument_list|,
name|findFirstStringLocalVariableOffset
argument_list|(
name|method
argument_list|)
argument_list|)
expr_stmt|;
block|}
block|}
name|clazz
operator|.
name|setConstantPool
argument_list|(
name|cp
operator|.
name|getFinalConstantPool
argument_list|()
argument_list|)
expr_stmt|;
return|return
name|clazz
operator|.
name|getBytes
argument_list|()
return|;
block|}
specifier|public
name|Method
name|injection
parameter_list|(
name|JavaClass
name|clazz
parameter_list|,
name|Method
name|method
parameter_list|,
name|ConstantPoolGen
name|cp
parameter_list|,
name|int
name|firstStringOffset
parameter_list|)
block|{
name|MethodGen
name|methodGen
init|=
operator|new
name|MethodGen
argument_list|(
name|method
argument_list|,
name|clazz
operator|.
name|getClassName
argument_list|()
argument_list|,
name|cp
argument_list|)
decl_stmt|;
name|InstructionList
name|instructionList
init|=
name|methodGen
operator|.
name|getInstructionList
argument_list|()
decl_stmt|;
name|instructionList
operator|.
name|insert
argument_list|(
name|instructionList
operator|.
name|getStart
argument_list|()
argument_list|,
name|makeWillBeAddedInstructionList
argument_list|(
name|methodGen
argument_list|,
name|firstStringOffset
argument_list|)
argument_list|)
expr_stmt|;
name|methodGen
operator|.
name|setMaxStack
argument_list|()
expr_stmt|;
name|methodGen
operator|.
name|setMaxLocals
argument_list|()
expr_stmt|;
name|method
operator|=
name|methodGen
operator|.
name|getMethod
argument_list|()
expr_stmt|;
name|instructionList
operator|.
name|dispose
argument_list|()
expr_stmt|;
return|return
name|method
return|;
block|}
specifier|public
name|InstructionList
name|makeWillBeAddedInstructionList
parameter_list|(
name|MethodGen
name|methodGen
parameter_list|,
name|int
name|firstStringOffset
parameter_list|)
block|{
if|if
condition|(
name|firstStringOffset
operator|==
operator|-
literal|1
condition|)
block|{
return|return
operator|new
name|InstructionList
argument_list|()
return|;
block|}
name|LocalVariableGen
name|localVariableGen
init|=
name|methodGen
operator|.
name|getLocalVariables
argument_list|()
index|[
name|firstStringOffset
index|]
decl_stmt|;
name|Instruction
name|instruction
decl_stmt|;
if|if
condition|(
name|localVariableGen
operator|!=
literal|null
condition|)
block|{
name|instruction
operator|=
operator|new
name|ALOAD
argument_list|(
name|localVariableGen
operator|.
name|getIndex
argument_list|()
argument_list|)
expr_stmt|;
block|}
else|else
block|{
name|instruction
operator|=
operator|new
name|ACONST_NULL
argument_list|()
expr_stmt|;
block|}
return|return
name|createPrintln
argument_list|(
name|methodGen
operator|.
name|getConstantPool
argument_list|()
argument_list|,
name|instruction
argument_list|)
return|;
block|}
specifier|public
name|InstructionList
name|createPrintln
parameter_list|(
name|ConstantPoolGen
name|cp
parameter_list|,
name|Instruction
name|instruction
parameter_list|)
block|{
specifier|final
name|InstructionList
name|il
init|=
operator|new
name|InstructionList
argument_list|()
decl_stmt|;
specifier|final
name|int
name|out
init|=
name|cp
operator|.
name|addFieldref
argument_list|(
literal|"java.lang.System"
argument_list|,
literal|"out"
argument_list|,
literal|"Ljava/io/PrintStream;"
argument_list|)
decl_stmt|;
specifier|final
name|int
name|println
init|=
name|cp
operator|.
name|addMethodref
argument_list|(
literal|"java.io.PrintStream"
argument_list|,
literal|"println"
argument_list|,
literal|"(Ljava/lang/String;)V"
argument_list|)
decl_stmt|;
name|il
operator|.
name|append
argument_list|(
operator|new
name|GETSTATIC
argument_list|(
name|out
argument_list|)
argument_list|)
expr_stmt|;
name|il
operator|.
name|append
argument_list|(
name|instruction
argument_list|)
expr_stmt|;
name|il
operator|.
name|append
argument_list|(
operator|new
name|INVOKEVIRTUAL
argument_list|(
name|println
argument_list|)
argument_list|)
expr_stmt|;
return|return
name|il
return|;
block|}
specifier|public
name|int
name|findFirstStringLocalVariableOffset
parameter_list|(
name|Method
name|method
parameter_list|)
block|{
name|Type
index|[]
name|argumentTypes
init|=
name|method
operator|.
name|getArgumentTypes
argument_list|()
decl_stmt|;
name|int
name|offset
init|=
operator|-
literal|1
decl_stmt|;
for|for
control|(
name|int
name|i
init|=
literal|0
init|,
name|count
init|=
name|argumentTypes
operator|.
name|length
init|;
name|i
operator|<
name|count
condition|;
name|i
operator|++
control|)
block|{
if|if
condition|(
name|Type
operator|.
name|STRING
operator|.
name|getSignature
argument_list|()
operator|.
name|equals
argument_list|(
name|argumentTypes
index|[
name|i
index|]
operator|.
name|getSignature
argument_list|()
argument_list|)
condition|)
block|{
if|if
condition|(
name|method
operator|.
name|isStatic
argument_list|()
condition|)
block|{
name|offset
operator|=
name|i
expr_stmt|;
block|}
else|else
block|{
name|offset
operator|=
name|i
operator|+
literal|1
expr_stmt|;
block|}
break|break;
block|}
block|}
return|return
name|offset
return|;
block|}
block|}
end_class

end_unit

