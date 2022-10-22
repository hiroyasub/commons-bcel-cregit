begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
begin_comment
comment|/*  * Licensed to the Apache Software Foundation (ASF) under one or more  * contributor license agreements.  See the NOTICE file distributed with  * this work for additional information regarding copyright ownership.  * The ASF licenses this file to You under the Apache License, Version 2.0  * (the "License"); you may not use this file except in compliance with  * the License.  You may obtain a copy of the License at  *  *      http://www.apache.org/licenses/LICENSE-2.0  *  *  Unless required by applicable law or agreed to in writing, software  *  distributed under the License is distributed on an "AS IS" BASIS,  *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  *  See the License for the specific language governing permissions and  *  limitations under the License.  */
end_comment

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
name|org
operator|.
name|apache
operator|.
name|bcel
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
name|ASTORE
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
name|ArrayType
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
name|GOTO
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
name|InstructionConst
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
name|InstructionFactory
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
name|ObjectType
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
name|PUSH
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

begin_comment
comment|/**  * Create HelloWorld class:  *  *<PRE>  * import java.io.*;  *  * public class HelloWorld {  *     public static void main(String[] argv) {  *         BufferedReader in = new BufferedReader(new InputStreamReader(System.in));  *         String name = null;  *  *         try {  *             System.out.print("Please enter your name> ");  *             name = in.readLine();  *         } catch (IOException e) {  *             System.out.println(e);  *             return;  *         }  *  *         System.out.println("Hello, " + name);  *     }  * }  *</PRE>  */
end_comment

begin_class
specifier|public
class|class
name|HelloWorldBuilder
block|{
specifier|public
specifier|static
name|void
name|main
parameter_list|(
specifier|final
name|String
index|[]
name|argv
parameter_list|)
block|{
specifier|final
name|ClassGen
name|cg
init|=
operator|new
name|ClassGen
argument_list|(
literal|"HelloWorld"
argument_list|,
literal|"java.lang.Object"
argument_list|,
literal|"<generated>"
argument_list|,
name|Const
operator|.
name|ACC_PUBLIC
operator||
name|Const
operator|.
name|ACC_SUPER
argument_list|,
literal|null
argument_list|)
decl_stmt|;
specifier|final
name|ConstantPoolGen
name|cp
init|=
name|cg
operator|.
name|getConstantPool
argument_list|()
decl_stmt|;
comment|// cg creates constant pool
specifier|final
name|InstructionList
name|il
init|=
operator|new
name|InstructionList
argument_list|()
decl_stmt|;
specifier|final
name|MethodGen
name|mg
init|=
operator|new
name|MethodGen
argument_list|(
name|Const
operator|.
name|ACC_STATIC
operator||
name|Const
operator|.
name|ACC_PUBLIC
argument_list|,
comment|// access flags
name|Type
operator|.
name|VOID
argument_list|,
comment|// return type
operator|new
name|Type
index|[]
block|{
comment|// argument types
operator|new
name|ArrayType
argument_list|(
name|Type
operator|.
name|STRING
argument_list|,
literal|1
argument_list|)
block|}
argument_list|,
operator|new
name|String
index|[]
block|{
literal|"argv"
block|}
argument_list|,
comment|// arg names
literal|"main"
argument_list|,
literal|"HelloWorld"
argument_list|,
comment|// method, class
name|il
argument_list|,
name|cp
argument_list|)
decl_stmt|;
specifier|final
name|InstructionFactory
name|factory
init|=
operator|new
name|InstructionFactory
argument_list|(
name|cg
argument_list|)
decl_stmt|;
specifier|final
name|ObjectType
name|iStream
init|=
operator|new
name|ObjectType
argument_list|(
literal|"java.io.InputStream"
argument_list|)
decl_stmt|;
specifier|final
name|ObjectType
name|pStream
init|=
operator|new
name|ObjectType
argument_list|(
literal|"java.io.PrintStream"
argument_list|)
decl_stmt|;
comment|// Create BufferedReader object and store it in local variable `in'.
name|il
operator|.
name|append
argument_list|(
name|factory
operator|.
name|createNew
argument_list|(
literal|"java.io.BufferedReader"
argument_list|)
argument_list|)
expr_stmt|;
name|il
operator|.
name|append
argument_list|(
name|InstructionConst
operator|.
name|DUP
argument_list|)
expr_stmt|;
comment|// Use predefined constant, i.e. flyweight
name|il
operator|.
name|append
argument_list|(
name|factory
operator|.
name|createNew
argument_list|(
literal|"java.io.InputStreamReader"
argument_list|)
argument_list|)
expr_stmt|;
name|il
operator|.
name|append
argument_list|(
name|InstructionConst
operator|.
name|DUP
argument_list|)
expr_stmt|;
name|il
operator|.
name|append
argument_list|(
name|factory
operator|.
name|createFieldAccess
argument_list|(
literal|"java.lang.System"
argument_list|,
literal|"in"
argument_list|,
name|iStream
argument_list|,
name|Const
operator|.
name|GETSTATIC
argument_list|)
argument_list|)
expr_stmt|;
comment|// Call constructors, i.e. BufferedReader(InputStreamReader())
name|il
operator|.
name|append
argument_list|(
name|factory
operator|.
name|createInvoke
argument_list|(
literal|"java.io.InputStreamReader"
argument_list|,
literal|"<init>"
argument_list|,
name|Type
operator|.
name|VOID
argument_list|,
operator|new
name|Type
index|[]
block|{
name|iStream
block|}
argument_list|,
name|Const
operator|.
name|INVOKESPECIAL
argument_list|)
argument_list|)
expr_stmt|;
name|il
operator|.
name|append
argument_list|(
name|factory
operator|.
name|createInvoke
argument_list|(
literal|"java.io.BufferedReader"
argument_list|,
literal|"<init>"
argument_list|,
name|Type
operator|.
name|VOID
argument_list|,
operator|new
name|Type
index|[]
block|{
operator|new
name|ObjectType
argument_list|(
literal|"java.io.Reader"
argument_list|)
block|}
argument_list|,
name|Const
operator|.
name|INVOKESPECIAL
argument_list|)
argument_list|)
expr_stmt|;
comment|// Create local variable `in'
name|LocalVariableGen
name|lg
init|=
name|mg
operator|.
name|addLocalVariable
argument_list|(
literal|"in"
argument_list|,
operator|new
name|ObjectType
argument_list|(
literal|"java.io.BufferedReader"
argument_list|)
argument_list|,
literal|null
argument_list|,
literal|null
argument_list|)
decl_stmt|;
specifier|final
name|int
name|in
init|=
name|lg
operator|.
name|getIndex
argument_list|()
decl_stmt|;
name|lg
operator|.
name|setStart
argument_list|(
name|il
operator|.
name|append
argument_list|(
operator|new
name|ASTORE
argument_list|(
name|in
argument_list|)
argument_list|)
argument_list|)
expr_stmt|;
comment|// `i' valid from here
comment|// Create local variable `name'
name|lg
operator|=
name|mg
operator|.
name|addLocalVariable
argument_list|(
literal|"name"
argument_list|,
name|Type
operator|.
name|STRING
argument_list|,
literal|null
argument_list|,
literal|null
argument_list|)
expr_stmt|;
specifier|final
name|int
name|name
init|=
name|lg
operator|.
name|getIndex
argument_list|()
decl_stmt|;
name|il
operator|.
name|append
argument_list|(
name|InstructionConst
operator|.
name|ACONST_NULL
argument_list|)
expr_stmt|;
name|lg
operator|.
name|setStart
argument_list|(
name|il
operator|.
name|append
argument_list|(
operator|new
name|ASTORE
argument_list|(
name|name
argument_list|)
argument_list|)
argument_list|)
expr_stmt|;
comment|// `name' valid from here
comment|// try { ...
specifier|final
name|InstructionHandle
name|tryStart
init|=
name|il
operator|.
name|append
argument_list|(
name|factory
operator|.
name|createFieldAccess
argument_list|(
literal|"java.lang.System"
argument_list|,
literal|"out"
argument_list|,
name|pStream
argument_list|,
name|Const
operator|.
name|GETSTATIC
argument_list|)
argument_list|)
decl_stmt|;
name|il
operator|.
name|append
argument_list|(
operator|new
name|PUSH
argument_list|(
name|cp
argument_list|,
literal|"Please enter your name> "
argument_list|)
argument_list|)
expr_stmt|;
name|il
operator|.
name|append
argument_list|(
name|factory
operator|.
name|createInvoke
argument_list|(
literal|"java.io.PrintStream"
argument_list|,
literal|"print"
argument_list|,
name|Type
operator|.
name|VOID
argument_list|,
operator|new
name|Type
index|[]
block|{
name|Type
operator|.
name|STRING
block|}
argument_list|,
name|Const
operator|.
name|INVOKEVIRTUAL
argument_list|)
argument_list|)
expr_stmt|;
name|il
operator|.
name|append
argument_list|(
operator|new
name|ALOAD
argument_list|(
name|in
argument_list|)
argument_list|)
expr_stmt|;
name|il
operator|.
name|append
argument_list|(
name|factory
operator|.
name|createInvoke
argument_list|(
literal|"java.io.BufferedReader"
argument_list|,
literal|"readLine"
argument_list|,
name|Type
operator|.
name|STRING
argument_list|,
name|Type
operator|.
name|NO_ARGS
argument_list|,
name|Const
operator|.
name|INVOKEVIRTUAL
argument_list|)
argument_list|)
expr_stmt|;
name|il
operator|.
name|append
argument_list|(
operator|new
name|ASTORE
argument_list|(
name|name
argument_list|)
argument_list|)
expr_stmt|;
comment|// Upon normal execution we jump behind exception handler, the target address is not known yet.
specifier|final
name|GOTO
name|g
init|=
operator|new
name|GOTO
argument_list|(
literal|null
argument_list|)
decl_stmt|;
specifier|final
name|InstructionHandle
name|tryEnd
init|=
name|il
operator|.
name|append
argument_list|(
name|g
argument_list|)
decl_stmt|;
comment|/*          * } catch() { ... } Add exception handler: print exception and return from method          */
specifier|final
name|InstructionHandle
name|handler
init|=
name|il
operator|.
name|append
argument_list|(
name|factory
operator|.
name|createFieldAccess
argument_list|(
literal|"java.lang.System"
argument_list|,
literal|"out"
argument_list|,
name|pStream
argument_list|,
name|Const
operator|.
name|GETSTATIC
argument_list|)
argument_list|)
decl_stmt|;
comment|// Little trick in order not to save exception object temporarily
name|il
operator|.
name|append
argument_list|(
name|InstructionConst
operator|.
name|SWAP
argument_list|)
expr_stmt|;
name|il
operator|.
name|append
argument_list|(
name|factory
operator|.
name|createInvoke
argument_list|(
literal|"java.io.PrintStream"
argument_list|,
literal|"println"
argument_list|,
name|Type
operator|.
name|VOID
argument_list|,
operator|new
name|Type
index|[]
block|{
name|Type
operator|.
name|OBJECT
block|}
argument_list|,
name|Const
operator|.
name|INVOKEVIRTUAL
argument_list|)
argument_list|)
expr_stmt|;
name|il
operator|.
name|append
argument_list|(
name|InstructionConst
operator|.
name|RETURN
argument_list|)
expr_stmt|;
name|mg
operator|.
name|addExceptionHandler
argument_list|(
name|tryStart
argument_list|,
name|tryEnd
argument_list|,
name|handler
argument_list|,
operator|new
name|ObjectType
argument_list|(
literal|"java.io.IOException"
argument_list|)
argument_list|)
expr_stmt|;
comment|// Normal code continues, now we can set the branch target of the GOTO that jumps over the handler code.
specifier|final
name|InstructionHandle
name|ih
init|=
name|il
operator|.
name|append
argument_list|(
name|factory
operator|.
name|createFieldAccess
argument_list|(
literal|"java.lang.System"
argument_list|,
literal|"out"
argument_list|,
name|pStream
argument_list|,
name|Const
operator|.
name|GETSTATIC
argument_list|)
argument_list|)
decl_stmt|;
name|g
operator|.
name|setTarget
argument_list|(
name|ih
argument_list|)
expr_stmt|;
comment|// String concatenation compiles to StringBuffer operations.
name|il
operator|.
name|append
argument_list|(
name|factory
operator|.
name|createNew
argument_list|(
name|Type
operator|.
name|STRINGBUFFER
argument_list|)
argument_list|)
expr_stmt|;
name|il
operator|.
name|append
argument_list|(
name|InstructionConst
operator|.
name|DUP
argument_list|)
expr_stmt|;
name|il
operator|.
name|append
argument_list|(
operator|new
name|PUSH
argument_list|(
name|cp
argument_list|,
literal|"Hello, "
argument_list|)
argument_list|)
expr_stmt|;
name|il
operator|.
name|append
argument_list|(
name|factory
operator|.
name|createInvoke
argument_list|(
literal|"java.lang.StringBuffer"
argument_list|,
literal|"<init>"
argument_list|,
name|Type
operator|.
name|VOID
argument_list|,
operator|new
name|Type
index|[]
block|{
name|Type
operator|.
name|STRING
block|}
argument_list|,
name|Const
operator|.
name|INVOKESPECIAL
argument_list|)
argument_list|)
expr_stmt|;
name|il
operator|.
name|append
argument_list|(
operator|new
name|ALOAD
argument_list|(
name|name
argument_list|)
argument_list|)
expr_stmt|;
comment|// Concatenate strings using a StringBuffer and print them.
name|il
operator|.
name|append
argument_list|(
name|factory
operator|.
name|createInvoke
argument_list|(
literal|"java.lang.StringBuffer"
argument_list|,
literal|"append"
argument_list|,
name|Type
operator|.
name|STRINGBUFFER
argument_list|,
operator|new
name|Type
index|[]
block|{
name|Type
operator|.
name|STRING
block|}
argument_list|,
name|Const
operator|.
name|INVOKEVIRTUAL
argument_list|)
argument_list|)
expr_stmt|;
name|il
operator|.
name|append
argument_list|(
name|factory
operator|.
name|createInvoke
argument_list|(
literal|"java.lang.StringBuffer"
argument_list|,
literal|"toString"
argument_list|,
name|Type
operator|.
name|STRING
argument_list|,
name|Type
operator|.
name|NO_ARGS
argument_list|,
name|Const
operator|.
name|INVOKEVIRTUAL
argument_list|)
argument_list|)
expr_stmt|;
name|il
operator|.
name|append
argument_list|(
name|factory
operator|.
name|createInvoke
argument_list|(
literal|"java.io.PrintStream"
argument_list|,
literal|"println"
argument_list|,
name|Type
operator|.
name|VOID
argument_list|,
operator|new
name|Type
index|[]
block|{
name|Type
operator|.
name|STRING
block|}
argument_list|,
name|Const
operator|.
name|INVOKEVIRTUAL
argument_list|)
argument_list|)
expr_stmt|;
name|il
operator|.
name|append
argument_list|(
name|InstructionConst
operator|.
name|RETURN
argument_list|)
expr_stmt|;
name|mg
operator|.
name|setMaxStack
argument_list|(
literal|5
argument_list|)
expr_stmt|;
comment|// Needed stack size
name|cg
operator|.
name|addMethod
argument_list|(
name|mg
operator|.
name|getMethod
argument_list|()
argument_list|)
expr_stmt|;
name|il
operator|.
name|dispose
argument_list|()
expr_stmt|;
comment|// Reuse instruction handles
comment|// Add public<init> method, i.e. empty constructor
name|cg
operator|.
name|addEmptyConstructor
argument_list|(
name|Const
operator|.
name|ACC_PUBLIC
argument_list|)
expr_stmt|;
comment|// Get JavaClass object and dump it to file.
try|try
block|{
name|cg
operator|.
name|getJavaClass
argument_list|()
operator|.
name|dump
argument_list|(
literal|"HelloWorld.class"
argument_list|)
expr_stmt|;
block|}
catch|catch
parameter_list|(
specifier|final
name|IOException
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
block|}
block|}
block|}
end_class

end_unit

