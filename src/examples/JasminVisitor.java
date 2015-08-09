begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
begin_comment
comment|/*  * Licensed to the Apache Software Foundation (ASF) under one or more  * contributor license agreements.  See the NOTICE file distributed with  * this work for additional information regarding copyright ownership.  * The ASF licenses this file to You under the Apache License, Version 2.0  * (the "License"); you may not use this file except in compliance with  * the License.  You may obtain a copy of the License at  *  *      http://www.apache.org/licenses/LICENSE-2.0  *  *  Unless required by applicable law or agreed to in writing, software  *  distributed under the License is distributed on an "AS IS" BASIS,  *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  *  See the License for the specific language governing permissions and  *  limitations under the License.  *  */
end_comment

begin_import
import|import
name|java
operator|.
name|io
operator|.
name|File
import|;
end_import

begin_import
import|import
name|java
operator|.
name|io
operator|.
name|FileOutputStream
import|;
end_import

begin_import
import|import
name|java
operator|.
name|io
operator|.
name|OutputStream
import|;
end_import

begin_import
import|import
name|java
operator|.
name|io
operator|.
name|PrintWriter
import|;
end_import

begin_import
import|import
name|java
operator|.
name|util
operator|.
name|Date
import|;
end_import

begin_import
import|import
name|java
operator|.
name|util
operator|.
name|Hashtable
import|;
end_import

begin_import
import|import
name|java
operator|.
name|util
operator|.
name|StringTokenizer
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
name|Constants
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
name|Repository
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
name|Attribute
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
name|ClassParser
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
name|Utility
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
name|generic
operator|.
name|BranchHandle
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
name|generic
operator|.
name|BranchInstruction
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
name|generic
operator|.
name|CodeExceptionGen
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
name|commons
operator|.
name|bcel6
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
name|commons
operator|.
name|bcel6
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
name|commons
operator|.
name|bcel6
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
name|commons
operator|.
name|bcel6
operator|.
name|generic
operator|.
name|LineNumberGen
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
name|commons
operator|.
name|bcel6
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
name|commons
operator|.
name|bcel6
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
name|commons
operator|.
name|bcel6
operator|.
name|generic
operator|.
name|Select
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
name|generic
operator|.
name|TABLESWITCH
import|;
end_import

begin_comment
comment|/**  * Disassemble Java class object into the<a href="http://jasmin.sourceforge.net">  * Jasmin</a> format.  *  * @author<A HREF="mailto:m.dahm@gmx.de">M. Dahm</A>  * @version $Id$  */
end_comment

begin_class
specifier|public
class|class
name|JasminVisitor
extends|extends
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
name|EmptyVisitor
block|{
specifier|private
name|JavaClass
name|clazz
decl_stmt|;
specifier|private
name|PrintWriter
name|out
decl_stmt|;
specifier|private
name|String
name|class_name
decl_stmt|;
specifier|private
name|ConstantPoolGen
name|cp
decl_stmt|;
specifier|public
name|JasminVisitor
parameter_list|(
name|JavaClass
name|clazz
parameter_list|,
name|OutputStream
name|out
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
name|out
operator|=
operator|new
name|PrintWriter
argument_list|(
name|out
argument_list|)
expr_stmt|;
name|this
operator|.
name|class_name
operator|=
name|clazz
operator|.
name|getClassName
argument_list|()
expr_stmt|;
name|this
operator|.
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
block|}
comment|/**      * Start traversal using DefaultVisitor pattern.      */
specifier|public
name|void
name|disassemble
parameter_list|()
block|{
operator|new
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
name|DescendingVisitor
argument_list|(
name|clazz
argument_list|,
name|this
argument_list|)
operator|.
name|visit
argument_list|()
expr_stmt|;
name|out
operator|.
name|close
argument_list|()
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitJavaClass
parameter_list|(
name|JavaClass
name|clazz
parameter_list|)
block|{
name|out
operator|.
name|println
argument_list|(
literal|";; Produced by JasminVisitor (BCEL)"
argument_list|)
expr_stmt|;
name|out
operator|.
name|println
argument_list|(
literal|";; http://commons.apache.org/bcel/"
argument_list|)
expr_stmt|;
name|out
operator|.
name|println
argument_list|(
literal|";; "
operator|+
operator|new
name|Date
argument_list|()
operator|+
literal|"\n"
argument_list|)
expr_stmt|;
name|out
operator|.
name|println
argument_list|(
literal|".source "
operator|+
name|clazz
operator|.
name|getSourceFileName
argument_list|()
argument_list|)
expr_stmt|;
name|out
operator|.
name|println
argument_list|(
literal|"."
operator|+
name|Utility
operator|.
name|classOrInterface
argument_list|(
name|clazz
operator|.
name|getAccessFlags
argument_list|()
argument_list|)
operator|+
literal|" "
operator|+
name|Utility
operator|.
name|accessToString
argument_list|(
name|clazz
operator|.
name|getAccessFlags
argument_list|()
argument_list|,
literal|true
argument_list|)
operator|+
literal|" "
operator|+
name|clazz
operator|.
name|getClassName
argument_list|()
operator|.
name|replace
argument_list|(
literal|'.'
argument_list|,
literal|'/'
argument_list|)
argument_list|)
expr_stmt|;
name|out
operator|.
name|println
argument_list|(
literal|".super "
operator|+
name|clazz
operator|.
name|getSuperclassName
argument_list|()
operator|.
name|replace
argument_list|(
literal|'.'
argument_list|,
literal|'/'
argument_list|)
argument_list|)
expr_stmt|;
for|for
control|(
name|String
name|iface
range|:
name|clazz
operator|.
name|getInterfaceNames
argument_list|()
control|)
block|{
name|out
operator|.
name|println
argument_list|(
literal|".implements "
operator|+
name|iface
operator|.
name|replace
argument_list|(
literal|'.'
argument_list|,
literal|'/'
argument_list|)
argument_list|)
expr_stmt|;
block|}
name|out
operator|.
name|print
argument_list|(
literal|"\n"
argument_list|)
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
name|out
operator|.
name|print
argument_list|(
literal|".field "
operator|+
name|Utility
operator|.
name|accessToString
argument_list|(
name|field
operator|.
name|getAccessFlags
argument_list|()
argument_list|)
operator|+
literal|" \""
operator|+
name|field
operator|.
name|getName
argument_list|()
operator|+
literal|"\""
operator|+
name|field
operator|.
name|getSignature
argument_list|()
argument_list|)
expr_stmt|;
if|if
condition|(
name|field
operator|.
name|getAttributes
argument_list|()
operator|.
name|length
operator|==
literal|0
condition|)
block|{
name|out
operator|.
name|print
argument_list|(
literal|"\n"
argument_list|)
expr_stmt|;
block|}
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
name|out
operator|.
name|println
argument_list|(
literal|" = "
operator|+
name|cv
argument_list|)
expr_stmt|;
block|}
specifier|private
name|Method
name|_method
decl_stmt|;
comment|/**      * Unfortunately Jasmin expects ".end method" after each method. Thus we've to check      * for every of the method's attributes if it's the last one and print ".end method"      * then.      */
specifier|private
name|void
name|printEndMethod
parameter_list|(
name|Attribute
name|attr
parameter_list|)
block|{
name|Attribute
index|[]
name|attributes
init|=
name|_method
operator|.
name|getAttributes
argument_list|()
decl_stmt|;
if|if
condition|(
name|attr
operator|==
name|attributes
index|[
name|attributes
operator|.
name|length
operator|-
literal|1
index|]
condition|)
block|{
name|out
operator|.
name|println
argument_list|(
literal|".end method"
argument_list|)
expr_stmt|;
block|}
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
name|printEndMethod
argument_list|(
name|attribute
argument_list|)
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
if|if
condition|(
name|_method
operator|!=
literal|null
condition|)
block|{
name|printEndMethod
argument_list|(
name|attribute
argument_list|)
expr_stmt|;
block|}
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
name|this
operator|.
name|_method
operator|=
name|method
expr_stmt|;
comment|// Remember for use in subsequent visitXXX calls
name|out
operator|.
name|println
argument_list|(
literal|"\n.method "
operator|+
name|Utility
operator|.
name|accessToString
argument_list|(
name|_method
operator|.
name|getAccessFlags
argument_list|()
argument_list|)
operator|+
literal|" "
operator|+
name|_method
operator|.
name|getName
argument_list|()
operator|+
name|_method
operator|.
name|getSignature
argument_list|()
argument_list|)
expr_stmt|;
name|Attribute
index|[]
name|attributes
init|=
name|_method
operator|.
name|getAttributes
argument_list|()
decl_stmt|;
if|if
condition|(
operator|(
name|attributes
operator|==
literal|null
operator|)
operator|||
operator|(
name|attributes
operator|.
name|length
operator|==
literal|0
operator|)
condition|)
block|{
name|out
operator|.
name|println
argument_list|(
literal|".end method"
argument_list|)
expr_stmt|;
block|}
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitExceptionTable
parameter_list|(
name|ExceptionTable
name|e
parameter_list|)
block|{
for|for
control|(
name|String
name|name
range|:
name|e
operator|.
name|getExceptionNames
argument_list|()
control|)
block|{
name|out
operator|.
name|println
argument_list|(
literal|".throws "
operator|+
name|name
operator|.
name|replace
argument_list|(
literal|'.'
argument_list|,
literal|'/'
argument_list|)
argument_list|)
expr_stmt|;
block|}
name|printEndMethod
argument_list|(
name|e
argument_list|)
expr_stmt|;
block|}
specifier|private
name|Hashtable
argument_list|<
name|InstructionHandle
argument_list|,
name|String
argument_list|>
name|map
decl_stmt|;
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
name|int
name|label_counter
init|=
literal|0
decl_stmt|;
name|out
operator|.
name|println
argument_list|(
literal|".limit stack "
operator|+
name|code
operator|.
name|getMaxStack
argument_list|()
argument_list|)
expr_stmt|;
name|out
operator|.
name|println
argument_list|(
literal|".limit locals "
operator|+
name|code
operator|.
name|getMaxLocals
argument_list|()
argument_list|)
expr_stmt|;
name|MethodGen
name|mg
init|=
operator|new
name|MethodGen
argument_list|(
name|_method
argument_list|,
name|class_name
argument_list|,
name|cp
argument_list|)
decl_stmt|;
name|InstructionList
name|il
init|=
name|mg
operator|.
name|getInstructionList
argument_list|()
decl_stmt|;
name|InstructionHandle
index|[]
name|ihs
init|=
name|il
operator|.
name|getInstructionHandles
argument_list|()
decl_stmt|;
comment|/* Pass 1: Give all referenced instruction handles a symbolic name, i.e. a      * label.      */
name|map
operator|=
operator|new
name|Hashtable
argument_list|<
name|InstructionHandle
argument_list|,
name|String
argument_list|>
argument_list|()
expr_stmt|;
for|for
control|(
name|InstructionHandle
name|ih1
range|:
name|ihs
control|)
block|{
if|if
condition|(
name|ih1
operator|instanceof
name|BranchHandle
condition|)
block|{
name|BranchInstruction
name|bi
init|=
operator|(
name|BranchInstruction
operator|)
name|ih1
operator|.
name|getInstruction
argument_list|()
decl_stmt|;
if|if
condition|(
name|bi
operator|instanceof
name|Select
condition|)
block|{
comment|// Special cases LOOKUPSWITCH and TABLESWITCH
for|for
control|(
name|InstructionHandle
name|target
range|:
operator|(
operator|(
name|Select
operator|)
name|bi
operator|)
operator|.
name|getTargets
argument_list|()
control|)
block|{
name|put
argument_list|(
name|target
argument_list|,
literal|"Label"
operator|+
name|label_counter
operator|++
operator|+
literal|":"
argument_list|)
expr_stmt|;
block|}
block|}
name|InstructionHandle
name|ih
init|=
name|bi
operator|.
name|getTarget
argument_list|()
decl_stmt|;
name|put
argument_list|(
name|ih
argument_list|,
literal|"Label"
operator|+
name|label_counter
operator|++
operator|+
literal|":"
argument_list|)
expr_stmt|;
block|}
block|}
name|LocalVariableGen
index|[]
name|lvs
init|=
name|mg
operator|.
name|getLocalVariables
argument_list|()
decl_stmt|;
for|for
control|(
name|LocalVariableGen
name|lv
range|:
name|lvs
control|)
block|{
name|InstructionHandle
name|ih
init|=
name|lv
operator|.
name|getStart
argument_list|()
decl_stmt|;
name|put
argument_list|(
name|ih
argument_list|,
literal|"Label"
operator|+
name|label_counter
operator|++
operator|+
literal|":"
argument_list|)
expr_stmt|;
name|ih
operator|=
name|lv
operator|.
name|getEnd
argument_list|()
expr_stmt|;
name|put
argument_list|(
name|ih
argument_list|,
literal|"Label"
operator|+
name|label_counter
operator|++
operator|+
literal|":"
argument_list|)
expr_stmt|;
block|}
name|CodeExceptionGen
index|[]
name|ehs
init|=
name|mg
operator|.
name|getExceptionHandlers
argument_list|()
decl_stmt|;
for|for
control|(
name|CodeExceptionGen
name|c
range|:
name|ehs
control|)
block|{
name|InstructionHandle
name|ih
init|=
name|c
operator|.
name|getStartPC
argument_list|()
decl_stmt|;
name|put
argument_list|(
name|ih
argument_list|,
literal|"Label"
operator|+
name|label_counter
operator|++
operator|+
literal|":"
argument_list|)
expr_stmt|;
name|ih
operator|=
name|c
operator|.
name|getEndPC
argument_list|()
expr_stmt|;
name|put
argument_list|(
name|ih
argument_list|,
literal|"Label"
operator|+
name|label_counter
operator|++
operator|+
literal|":"
argument_list|)
expr_stmt|;
name|ih
operator|=
name|c
operator|.
name|getHandlerPC
argument_list|()
expr_stmt|;
name|put
argument_list|(
name|ih
argument_list|,
literal|"Label"
operator|+
name|label_counter
operator|++
operator|+
literal|":"
argument_list|)
expr_stmt|;
block|}
name|LineNumberGen
index|[]
name|lns
init|=
name|mg
operator|.
name|getLineNumbers
argument_list|()
decl_stmt|;
for|for
control|(
name|LineNumberGen
name|ln
range|:
name|lns
control|)
block|{
name|InstructionHandle
name|ih
init|=
name|ln
operator|.
name|getInstruction
argument_list|()
decl_stmt|;
name|put
argument_list|(
name|ih
argument_list|,
literal|".line "
operator|+
name|ln
operator|.
name|getSourceLine
argument_list|()
argument_list|)
expr_stmt|;
block|}
comment|// Pass 2: Output code.
for|for
control|(
name|LocalVariableGen
name|l
range|:
name|lvs
control|)
block|{
name|out
operator|.
name|println
argument_list|(
literal|".var "
operator|+
name|l
operator|.
name|getIndex
argument_list|()
operator|+
literal|" is "
operator|+
name|l
operator|.
name|getName
argument_list|()
operator|+
literal|" "
operator|+
name|l
operator|.
name|getType
argument_list|()
operator|.
name|getSignature
argument_list|()
operator|+
literal|" from "
operator|+
name|get
argument_list|(
name|l
operator|.
name|getStart
argument_list|()
argument_list|)
operator|+
literal|" to "
operator|+
name|get
argument_list|(
name|l
operator|.
name|getEnd
argument_list|()
argument_list|)
argument_list|)
expr_stmt|;
block|}
name|out
operator|.
name|print
argument_list|(
literal|"\n"
argument_list|)
expr_stmt|;
for|for
control|(
name|InstructionHandle
name|ih
range|:
name|ihs
control|)
block|{
name|Instruction
name|inst
init|=
name|ih
operator|.
name|getInstruction
argument_list|()
decl_stmt|;
name|String
name|str
init|=
name|map
operator|.
name|get
argument_list|(
name|ih
argument_list|)
decl_stmt|;
if|if
condition|(
name|str
operator|!=
literal|null
condition|)
block|{
name|out
operator|.
name|println
argument_list|(
name|str
argument_list|)
expr_stmt|;
block|}
if|if
condition|(
name|inst
operator|instanceof
name|BranchInstruction
condition|)
block|{
if|if
condition|(
name|inst
operator|instanceof
name|Select
condition|)
block|{
comment|// Special cases LOOKUPSWITCH and TABLESWITCH
name|Select
name|s
init|=
operator|(
name|Select
operator|)
name|inst
decl_stmt|;
name|int
index|[]
name|matchs
init|=
name|s
operator|.
name|getMatchs
argument_list|()
decl_stmt|;
name|InstructionHandle
index|[]
name|targets
init|=
name|s
operator|.
name|getTargets
argument_list|()
decl_stmt|;
if|if
condition|(
name|s
operator|instanceof
name|TABLESWITCH
condition|)
block|{
name|out
operator|.
name|println
argument_list|(
literal|"\ttableswitch "
operator|+
name|matchs
index|[
literal|0
index|]
operator|+
literal|" "
operator|+
name|matchs
index|[
name|matchs
operator|.
name|length
operator|-
literal|1
index|]
argument_list|)
expr_stmt|;
for|for
control|(
name|InstructionHandle
name|target
range|:
name|targets
control|)
block|{
name|out
operator|.
name|println
argument_list|(
literal|"\t\t"
operator|+
name|get
argument_list|(
name|target
argument_list|)
argument_list|)
expr_stmt|;
block|}
block|}
else|else
block|{
comment|// LOOKUPSWITCH
name|out
operator|.
name|println
argument_list|(
literal|"\tlookupswitch "
argument_list|)
expr_stmt|;
for|for
control|(
name|int
name|j
init|=
literal|0
init|;
name|j
operator|<
name|targets
operator|.
name|length
condition|;
name|j
operator|++
control|)
block|{
name|out
operator|.
name|println
argument_list|(
literal|"\t\t"
operator|+
name|matchs
index|[
name|j
index|]
operator|+
literal|" : "
operator|+
name|get
argument_list|(
name|targets
index|[
name|j
index|]
argument_list|)
argument_list|)
expr_stmt|;
block|}
block|}
name|out
operator|.
name|println
argument_list|(
literal|"\t\tdefault: "
operator|+
name|get
argument_list|(
name|s
operator|.
name|getTarget
argument_list|()
argument_list|)
argument_list|)
expr_stmt|;
comment|// Applies for both
block|}
else|else
block|{
name|BranchInstruction
name|bi
init|=
operator|(
name|BranchInstruction
operator|)
name|inst
decl_stmt|;
name|ih
operator|=
name|bi
operator|.
name|getTarget
argument_list|()
expr_stmt|;
name|str
operator|=
name|get
argument_list|(
name|ih
argument_list|)
expr_stmt|;
name|out
operator|.
name|println
argument_list|(
literal|"\t"
operator|+
name|Constants
operator|.
name|OPCODE_NAMES
index|[
name|bi
operator|.
name|getOpcode
argument_list|()
index|]
operator|+
literal|" "
operator|+
name|str
argument_list|)
expr_stmt|;
block|}
block|}
else|else
block|{
name|out
operator|.
name|println
argument_list|(
literal|"\t"
operator|+
name|inst
operator|.
name|toString
argument_list|(
name|cp
operator|.
name|getConstantPool
argument_list|()
argument_list|)
argument_list|)
expr_stmt|;
block|}
block|}
name|out
operator|.
name|print
argument_list|(
literal|"\n"
argument_list|)
expr_stmt|;
for|for
control|(
name|CodeExceptionGen
name|c
range|:
name|ehs
control|)
block|{
name|ObjectType
name|caught
init|=
name|c
operator|.
name|getCatchType
argument_list|()
decl_stmt|;
name|String
name|class_name
init|=
operator|(
name|caught
operator|==
literal|null
operator|)
condition|?
comment|// catch any exception, used when compiling finally
literal|"all"
else|:
name|caught
operator|.
name|getClassName
argument_list|()
operator|.
name|replace
argument_list|(
literal|'.'
argument_list|,
literal|'/'
argument_list|)
decl_stmt|;
name|out
operator|.
name|println
argument_list|(
literal|".catch "
operator|+
name|class_name
operator|+
literal|" from "
operator|+
name|get
argument_list|(
name|c
operator|.
name|getStartPC
argument_list|()
argument_list|)
operator|+
literal|" to "
operator|+
name|get
argument_list|(
name|c
operator|.
name|getEndPC
argument_list|()
argument_list|)
operator|+
literal|" using "
operator|+
name|get
argument_list|(
name|c
operator|.
name|getHandlerPC
argument_list|()
argument_list|)
argument_list|)
expr_stmt|;
block|}
name|printEndMethod
argument_list|(
name|code
argument_list|)
expr_stmt|;
block|}
specifier|private
name|String
name|get
parameter_list|(
name|InstructionHandle
name|ih
parameter_list|)
block|{
name|String
name|str
init|=
operator|new
name|StringTokenizer
argument_list|(
name|map
operator|.
name|get
argument_list|(
name|ih
argument_list|)
argument_list|,
literal|"\n"
argument_list|)
operator|.
name|nextToken
argument_list|()
decl_stmt|;
return|return
name|str
operator|.
name|substring
argument_list|(
literal|0
argument_list|,
name|str
operator|.
name|length
argument_list|()
operator|-
literal|1
argument_list|)
return|;
block|}
specifier|private
name|void
name|put
parameter_list|(
name|InstructionHandle
name|ih
parameter_list|,
name|String
name|line
parameter_list|)
block|{
name|String
name|str
init|=
name|map
operator|.
name|get
argument_list|(
name|ih
argument_list|)
decl_stmt|;
if|if
condition|(
name|str
operator|==
literal|null
condition|)
block|{
name|map
operator|.
name|put
argument_list|(
name|ih
argument_list|,
name|line
argument_list|)
expr_stmt|;
block|}
else|else
block|{
if|if
condition|(
name|line
operator|.
name|startsWith
argument_list|(
literal|"Label"
argument_list|)
operator|||
name|str
operator|.
name|endsWith
argument_list|(
name|line
argument_list|)
condition|)
block|{
return|return;
block|}
name|map
operator|.
name|put
argument_list|(
name|ih
argument_list|,
name|str
operator|+
literal|"\n"
operator|+
name|line
argument_list|)
expr_stmt|;
comment|// append
block|}
block|}
specifier|public
specifier|static
name|void
name|main
parameter_list|(
name|String
index|[]
name|argv
parameter_list|)
throws|throws
name|Exception
block|{
name|JavaClass
name|java_class
decl_stmt|;
if|if
condition|(
name|argv
operator|.
name|length
operator|==
literal|0
condition|)
block|{
name|System
operator|.
name|err
operator|.
name|println
argument_list|(
literal|"disassemble: No input files specified"
argument_list|)
expr_stmt|;
return|return;
block|}
for|for
control|(
name|String
name|arg
range|:
name|argv
control|)
block|{
if|if
condition|(
operator|(
name|java_class
operator|=
name|Repository
operator|.
name|lookupClass
argument_list|(
name|arg
argument_list|)
operator|)
operator|==
literal|null
condition|)
block|{
name|java_class
operator|=
operator|new
name|ClassParser
argument_list|(
name|arg
argument_list|)
operator|.
name|parse
argument_list|()
expr_stmt|;
block|}
name|String
name|class_name
init|=
name|java_class
operator|.
name|getClassName
argument_list|()
decl_stmt|;
name|int
name|index
init|=
name|class_name
operator|.
name|lastIndexOf
argument_list|(
literal|'.'
argument_list|)
decl_stmt|;
name|String
name|path
init|=
name|class_name
operator|.
name|substring
argument_list|(
literal|0
argument_list|,
name|index
operator|+
literal|1
argument_list|)
operator|.
name|replace
argument_list|(
literal|'.'
argument_list|,
name|File
operator|.
name|separatorChar
argument_list|)
decl_stmt|;
name|class_name
operator|=
name|class_name
operator|.
name|substring
argument_list|(
name|index
operator|+
literal|1
argument_list|)
expr_stmt|;
if|if
condition|(
operator|!
name|path
operator|.
name|equals
argument_list|(
literal|""
argument_list|)
condition|)
block|{
name|File
name|f
init|=
operator|new
name|File
argument_list|(
name|path
argument_list|)
decl_stmt|;
name|f
operator|.
name|mkdirs
argument_list|()
expr_stmt|;
block|}
name|String
name|name
init|=
name|path
operator|+
name|class_name
operator|+
literal|".j"
decl_stmt|;
name|FileOutputStream
name|out
init|=
operator|new
name|FileOutputStream
argument_list|(
name|name
argument_list|)
decl_stmt|;
operator|new
name|JasminVisitor
argument_list|(
name|java_class
argument_list|,
name|out
argument_list|)
operator|.
name|disassemble
argument_list|()
expr_stmt|;
name|System
operator|.
name|out
operator|.
name|println
argument_list|(
literal|"File dumped to: "
operator|+
name|name
argument_list|)
expr_stmt|;
block|}
block|}
block|}
end_class

end_unit

