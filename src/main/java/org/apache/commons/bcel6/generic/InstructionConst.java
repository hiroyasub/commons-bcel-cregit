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
name|generic
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
name|Constants
import|;
end_import

begin_comment
comment|/**   * This interface contains shareable instruction objects.  *  * In order to save memory you can use some instructions multiply,  * since they have an immutable state and are directly derived from  * Instruction.  I.e. they have no instance fields that could be  * changed. Since some of these instructions like ICONST_0 occur  * very frequently this can save a lot of time and space. This  * feature is an adaptation of the FlyWeight design pattern, we  * just use an array instead of a factory.  *  * The Instructions can also accessed directly under their names, so  * it's possible to write il.append(Instruction.ICONST_0);  *  * @version $Id: InstructionConstants.java 1695415 2015-08-12 01:02:39Z chas $  */
end_comment

begin_class
specifier|public
specifier|final
class|class
name|InstructionConst
block|{
comment|/**       * Predefined instruction objects      */
comment|/*      * NOTE these are not currently immutable, because Instruction      * has mutable protected fields opcode and length.      */
specifier|public
specifier|static
specifier|final
name|Instruction
name|NOP
init|=
operator|new
name|NOP
argument_list|()
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|Instruction
name|ACONST_NULL
init|=
operator|new
name|ACONST_NULL
argument_list|()
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|Instruction
name|ICONST_M1
init|=
operator|new
name|ICONST
argument_list|(
operator|-
literal|1
argument_list|)
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|Instruction
name|ICONST_0
init|=
operator|new
name|ICONST
argument_list|(
literal|0
argument_list|)
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|Instruction
name|ICONST_1
init|=
operator|new
name|ICONST
argument_list|(
literal|1
argument_list|)
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|Instruction
name|ICONST_2
init|=
operator|new
name|ICONST
argument_list|(
literal|2
argument_list|)
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|Instruction
name|ICONST_3
init|=
operator|new
name|ICONST
argument_list|(
literal|3
argument_list|)
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|Instruction
name|ICONST_4
init|=
operator|new
name|ICONST
argument_list|(
literal|4
argument_list|)
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|Instruction
name|ICONST_5
init|=
operator|new
name|ICONST
argument_list|(
literal|5
argument_list|)
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|Instruction
name|LCONST_0
init|=
operator|new
name|LCONST
argument_list|(
literal|0
argument_list|)
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|Instruction
name|LCONST_1
init|=
operator|new
name|LCONST
argument_list|(
literal|1
argument_list|)
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|Instruction
name|FCONST_0
init|=
operator|new
name|FCONST
argument_list|(
literal|0
argument_list|)
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|Instruction
name|FCONST_1
init|=
operator|new
name|FCONST
argument_list|(
literal|1
argument_list|)
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|Instruction
name|FCONST_2
init|=
operator|new
name|FCONST
argument_list|(
literal|2
argument_list|)
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|Instruction
name|DCONST_0
init|=
operator|new
name|DCONST
argument_list|(
literal|0
argument_list|)
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|Instruction
name|DCONST_1
init|=
operator|new
name|DCONST
argument_list|(
literal|1
argument_list|)
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|ArrayInstruction
name|IALOAD
init|=
operator|new
name|IALOAD
argument_list|()
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|ArrayInstruction
name|LALOAD
init|=
operator|new
name|LALOAD
argument_list|()
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|ArrayInstruction
name|FALOAD
init|=
operator|new
name|FALOAD
argument_list|()
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|ArrayInstruction
name|DALOAD
init|=
operator|new
name|DALOAD
argument_list|()
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|ArrayInstruction
name|AALOAD
init|=
operator|new
name|AALOAD
argument_list|()
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|ArrayInstruction
name|BALOAD
init|=
operator|new
name|BALOAD
argument_list|()
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|ArrayInstruction
name|CALOAD
init|=
operator|new
name|CALOAD
argument_list|()
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|ArrayInstruction
name|SALOAD
init|=
operator|new
name|SALOAD
argument_list|()
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|ArrayInstruction
name|IASTORE
init|=
operator|new
name|IASTORE
argument_list|()
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|ArrayInstruction
name|LASTORE
init|=
operator|new
name|LASTORE
argument_list|()
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|ArrayInstruction
name|FASTORE
init|=
operator|new
name|FASTORE
argument_list|()
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|ArrayInstruction
name|DASTORE
init|=
operator|new
name|DASTORE
argument_list|()
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|ArrayInstruction
name|AASTORE
init|=
operator|new
name|AASTORE
argument_list|()
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|ArrayInstruction
name|BASTORE
init|=
operator|new
name|BASTORE
argument_list|()
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|ArrayInstruction
name|CASTORE
init|=
operator|new
name|CASTORE
argument_list|()
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|ArrayInstruction
name|SASTORE
init|=
operator|new
name|SASTORE
argument_list|()
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|StackInstruction
name|POP
init|=
operator|new
name|POP
argument_list|()
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|StackInstruction
name|POP2
init|=
operator|new
name|POP2
argument_list|()
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|StackInstruction
name|DUP
init|=
operator|new
name|DUP
argument_list|()
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|StackInstruction
name|DUP_X1
init|=
operator|new
name|DUP_X1
argument_list|()
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|StackInstruction
name|DUP_X2
init|=
operator|new
name|DUP_X2
argument_list|()
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|StackInstruction
name|DUP2
init|=
operator|new
name|DUP2
argument_list|()
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|StackInstruction
name|DUP2_X1
init|=
operator|new
name|DUP2_X1
argument_list|()
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|StackInstruction
name|DUP2_X2
init|=
operator|new
name|DUP2_X2
argument_list|()
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|StackInstruction
name|SWAP
init|=
operator|new
name|SWAP
argument_list|()
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|ArithmeticInstruction
name|IADD
init|=
operator|new
name|IADD
argument_list|()
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|ArithmeticInstruction
name|LADD
init|=
operator|new
name|LADD
argument_list|()
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|ArithmeticInstruction
name|FADD
init|=
operator|new
name|FADD
argument_list|()
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|ArithmeticInstruction
name|DADD
init|=
operator|new
name|DADD
argument_list|()
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|ArithmeticInstruction
name|ISUB
init|=
operator|new
name|ISUB
argument_list|()
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|ArithmeticInstruction
name|LSUB
init|=
operator|new
name|LSUB
argument_list|()
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|ArithmeticInstruction
name|FSUB
init|=
operator|new
name|FSUB
argument_list|()
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|ArithmeticInstruction
name|DSUB
init|=
operator|new
name|DSUB
argument_list|()
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|ArithmeticInstruction
name|IMUL
init|=
operator|new
name|IMUL
argument_list|()
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|ArithmeticInstruction
name|LMUL
init|=
operator|new
name|LMUL
argument_list|()
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|ArithmeticInstruction
name|FMUL
init|=
operator|new
name|FMUL
argument_list|()
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|ArithmeticInstruction
name|DMUL
init|=
operator|new
name|DMUL
argument_list|()
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|ArithmeticInstruction
name|IDIV
init|=
operator|new
name|IDIV
argument_list|()
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|ArithmeticInstruction
name|LDIV
init|=
operator|new
name|LDIV
argument_list|()
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|ArithmeticInstruction
name|FDIV
init|=
operator|new
name|FDIV
argument_list|()
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|ArithmeticInstruction
name|DDIV
init|=
operator|new
name|DDIV
argument_list|()
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|ArithmeticInstruction
name|IREM
init|=
operator|new
name|IREM
argument_list|()
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|ArithmeticInstruction
name|LREM
init|=
operator|new
name|LREM
argument_list|()
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|ArithmeticInstruction
name|FREM
init|=
operator|new
name|FREM
argument_list|()
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|ArithmeticInstruction
name|DREM
init|=
operator|new
name|DREM
argument_list|()
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|ArithmeticInstruction
name|INEG
init|=
operator|new
name|INEG
argument_list|()
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|ArithmeticInstruction
name|LNEG
init|=
operator|new
name|LNEG
argument_list|()
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|ArithmeticInstruction
name|FNEG
init|=
operator|new
name|FNEG
argument_list|()
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|ArithmeticInstruction
name|DNEG
init|=
operator|new
name|DNEG
argument_list|()
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|ArithmeticInstruction
name|ISHL
init|=
operator|new
name|ISHL
argument_list|()
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|ArithmeticInstruction
name|LSHL
init|=
operator|new
name|LSHL
argument_list|()
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|ArithmeticInstruction
name|ISHR
init|=
operator|new
name|ISHR
argument_list|()
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|ArithmeticInstruction
name|LSHR
init|=
operator|new
name|LSHR
argument_list|()
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|ArithmeticInstruction
name|IUSHR
init|=
operator|new
name|IUSHR
argument_list|()
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|ArithmeticInstruction
name|LUSHR
init|=
operator|new
name|LUSHR
argument_list|()
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|ArithmeticInstruction
name|IAND
init|=
operator|new
name|IAND
argument_list|()
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|ArithmeticInstruction
name|LAND
init|=
operator|new
name|LAND
argument_list|()
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|ArithmeticInstruction
name|IOR
init|=
operator|new
name|IOR
argument_list|()
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|ArithmeticInstruction
name|LOR
init|=
operator|new
name|LOR
argument_list|()
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|ArithmeticInstruction
name|IXOR
init|=
operator|new
name|IXOR
argument_list|()
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|ArithmeticInstruction
name|LXOR
init|=
operator|new
name|LXOR
argument_list|()
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|ConversionInstruction
name|I2L
init|=
operator|new
name|I2L
argument_list|()
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|ConversionInstruction
name|I2F
init|=
operator|new
name|I2F
argument_list|()
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|ConversionInstruction
name|I2D
init|=
operator|new
name|I2D
argument_list|()
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|ConversionInstruction
name|L2I
init|=
operator|new
name|L2I
argument_list|()
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|ConversionInstruction
name|L2F
init|=
operator|new
name|L2F
argument_list|()
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|ConversionInstruction
name|L2D
init|=
operator|new
name|L2D
argument_list|()
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|ConversionInstruction
name|F2I
init|=
operator|new
name|F2I
argument_list|()
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|ConversionInstruction
name|F2L
init|=
operator|new
name|F2L
argument_list|()
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|ConversionInstruction
name|F2D
init|=
operator|new
name|F2D
argument_list|()
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|ConversionInstruction
name|D2I
init|=
operator|new
name|D2I
argument_list|()
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|ConversionInstruction
name|D2L
init|=
operator|new
name|D2L
argument_list|()
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|ConversionInstruction
name|D2F
init|=
operator|new
name|D2F
argument_list|()
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|ConversionInstruction
name|I2B
init|=
operator|new
name|I2B
argument_list|()
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|ConversionInstruction
name|I2C
init|=
operator|new
name|I2C
argument_list|()
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|ConversionInstruction
name|I2S
init|=
operator|new
name|I2S
argument_list|()
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|Instruction
name|LCMP
init|=
operator|new
name|LCMP
argument_list|()
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|Instruction
name|FCMPL
init|=
operator|new
name|FCMPL
argument_list|()
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|Instruction
name|FCMPG
init|=
operator|new
name|FCMPG
argument_list|()
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|Instruction
name|DCMPL
init|=
operator|new
name|DCMPL
argument_list|()
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|Instruction
name|DCMPG
init|=
operator|new
name|DCMPG
argument_list|()
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|ReturnInstruction
name|IRETURN
init|=
operator|new
name|IRETURN
argument_list|()
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|ReturnInstruction
name|LRETURN
init|=
operator|new
name|LRETURN
argument_list|()
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|ReturnInstruction
name|FRETURN
init|=
operator|new
name|FRETURN
argument_list|()
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|ReturnInstruction
name|DRETURN
init|=
operator|new
name|DRETURN
argument_list|()
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|ReturnInstruction
name|ARETURN
init|=
operator|new
name|ARETURN
argument_list|()
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|ReturnInstruction
name|RETURN
init|=
operator|new
name|RETURN
argument_list|()
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|Instruction
name|ARRAYLENGTH
init|=
operator|new
name|ARRAYLENGTH
argument_list|()
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|Instruction
name|ATHROW
init|=
operator|new
name|ATHROW
argument_list|()
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|Instruction
name|MONITORENTER
init|=
operator|new
name|MONITORENTER
argument_list|()
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|Instruction
name|MONITOREXIT
init|=
operator|new
name|MONITOREXIT
argument_list|()
decl_stmt|;
comment|/** You can use these constants in multiple places safely, if you can guarantee      * that you will never alter their internal values, e.g. call setIndex().      */
specifier|public
specifier|static
specifier|final
name|LocalVariableInstruction
name|THIS
init|=
operator|new
name|ALOAD
argument_list|(
literal|0
argument_list|)
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|LocalVariableInstruction
name|ALOAD_0
init|=
name|THIS
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|LocalVariableInstruction
name|ALOAD_1
init|=
operator|new
name|ALOAD
argument_list|(
literal|1
argument_list|)
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|LocalVariableInstruction
name|ALOAD_2
init|=
operator|new
name|ALOAD
argument_list|(
literal|2
argument_list|)
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|LocalVariableInstruction
name|ILOAD_0
init|=
operator|new
name|ILOAD
argument_list|(
literal|0
argument_list|)
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|LocalVariableInstruction
name|ILOAD_1
init|=
operator|new
name|ILOAD
argument_list|(
literal|1
argument_list|)
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|LocalVariableInstruction
name|ILOAD_2
init|=
operator|new
name|ILOAD
argument_list|(
literal|2
argument_list|)
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|LocalVariableInstruction
name|ASTORE_0
init|=
operator|new
name|ASTORE
argument_list|(
literal|0
argument_list|)
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|LocalVariableInstruction
name|ASTORE_1
init|=
operator|new
name|ASTORE
argument_list|(
literal|1
argument_list|)
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|LocalVariableInstruction
name|ASTORE_2
init|=
operator|new
name|ASTORE
argument_list|(
literal|2
argument_list|)
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|LocalVariableInstruction
name|ISTORE_0
init|=
operator|new
name|ISTORE
argument_list|(
literal|0
argument_list|)
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|LocalVariableInstruction
name|ISTORE_1
init|=
operator|new
name|ISTORE
argument_list|(
literal|1
argument_list|)
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|LocalVariableInstruction
name|ISTORE_2
init|=
operator|new
name|ISTORE
argument_list|(
literal|2
argument_list|)
decl_stmt|;
comment|/** Get object via its opcode, for immutable instructions like      * branch instructions entries are set to null.      */
specifier|private
specifier|static
specifier|final
name|Instruction
index|[]
name|INSTRUCTIONS
init|=
operator|new
name|Instruction
index|[
literal|256
index|]
decl_stmt|;
static|static
block|{
name|INSTRUCTIONS
index|[
name|Constants
operator|.
name|NOP
index|]
operator|=
name|NOP
expr_stmt|;
name|INSTRUCTIONS
index|[
name|Constants
operator|.
name|ACONST_NULL
index|]
operator|=
name|ACONST_NULL
expr_stmt|;
name|INSTRUCTIONS
index|[
name|Constants
operator|.
name|ICONST_M1
index|]
operator|=
name|ICONST_M1
expr_stmt|;
name|INSTRUCTIONS
index|[
name|Constants
operator|.
name|ICONST_0
index|]
operator|=
name|ICONST_0
expr_stmt|;
name|INSTRUCTIONS
index|[
name|Constants
operator|.
name|ICONST_1
index|]
operator|=
name|ICONST_1
expr_stmt|;
name|INSTRUCTIONS
index|[
name|Constants
operator|.
name|ICONST_2
index|]
operator|=
name|ICONST_2
expr_stmt|;
name|INSTRUCTIONS
index|[
name|Constants
operator|.
name|ICONST_3
index|]
operator|=
name|ICONST_3
expr_stmt|;
name|INSTRUCTIONS
index|[
name|Constants
operator|.
name|ICONST_4
index|]
operator|=
name|ICONST_4
expr_stmt|;
name|INSTRUCTIONS
index|[
name|Constants
operator|.
name|ICONST_5
index|]
operator|=
name|ICONST_5
expr_stmt|;
name|INSTRUCTIONS
index|[
name|Constants
operator|.
name|LCONST_0
index|]
operator|=
name|LCONST_0
expr_stmt|;
name|INSTRUCTIONS
index|[
name|Constants
operator|.
name|LCONST_1
index|]
operator|=
name|LCONST_1
expr_stmt|;
name|INSTRUCTIONS
index|[
name|Constants
operator|.
name|FCONST_0
index|]
operator|=
name|FCONST_0
expr_stmt|;
name|INSTRUCTIONS
index|[
name|Constants
operator|.
name|FCONST_1
index|]
operator|=
name|FCONST_1
expr_stmt|;
name|INSTRUCTIONS
index|[
name|Constants
operator|.
name|FCONST_2
index|]
operator|=
name|FCONST_2
expr_stmt|;
name|INSTRUCTIONS
index|[
name|Constants
operator|.
name|DCONST_0
index|]
operator|=
name|DCONST_0
expr_stmt|;
name|INSTRUCTIONS
index|[
name|Constants
operator|.
name|DCONST_1
index|]
operator|=
name|DCONST_1
expr_stmt|;
name|INSTRUCTIONS
index|[
name|Constants
operator|.
name|IALOAD
index|]
operator|=
name|IALOAD
expr_stmt|;
name|INSTRUCTIONS
index|[
name|Constants
operator|.
name|LALOAD
index|]
operator|=
name|LALOAD
expr_stmt|;
name|INSTRUCTIONS
index|[
name|Constants
operator|.
name|FALOAD
index|]
operator|=
name|FALOAD
expr_stmt|;
name|INSTRUCTIONS
index|[
name|Constants
operator|.
name|DALOAD
index|]
operator|=
name|DALOAD
expr_stmt|;
name|INSTRUCTIONS
index|[
name|Constants
operator|.
name|AALOAD
index|]
operator|=
name|AALOAD
expr_stmt|;
name|INSTRUCTIONS
index|[
name|Constants
operator|.
name|BALOAD
index|]
operator|=
name|BALOAD
expr_stmt|;
name|INSTRUCTIONS
index|[
name|Constants
operator|.
name|CALOAD
index|]
operator|=
name|CALOAD
expr_stmt|;
name|INSTRUCTIONS
index|[
name|Constants
operator|.
name|SALOAD
index|]
operator|=
name|SALOAD
expr_stmt|;
name|INSTRUCTIONS
index|[
name|Constants
operator|.
name|IASTORE
index|]
operator|=
name|IASTORE
expr_stmt|;
name|INSTRUCTIONS
index|[
name|Constants
operator|.
name|LASTORE
index|]
operator|=
name|LASTORE
expr_stmt|;
name|INSTRUCTIONS
index|[
name|Constants
operator|.
name|FASTORE
index|]
operator|=
name|FASTORE
expr_stmt|;
name|INSTRUCTIONS
index|[
name|Constants
operator|.
name|DASTORE
index|]
operator|=
name|DASTORE
expr_stmt|;
name|INSTRUCTIONS
index|[
name|Constants
operator|.
name|AASTORE
index|]
operator|=
name|AASTORE
expr_stmt|;
name|INSTRUCTIONS
index|[
name|Constants
operator|.
name|BASTORE
index|]
operator|=
name|BASTORE
expr_stmt|;
name|INSTRUCTIONS
index|[
name|Constants
operator|.
name|CASTORE
index|]
operator|=
name|CASTORE
expr_stmt|;
name|INSTRUCTIONS
index|[
name|Constants
operator|.
name|SASTORE
index|]
operator|=
name|SASTORE
expr_stmt|;
name|INSTRUCTIONS
index|[
name|Constants
operator|.
name|POP
index|]
operator|=
name|POP
expr_stmt|;
name|INSTRUCTIONS
index|[
name|Constants
operator|.
name|POP2
index|]
operator|=
name|POP2
expr_stmt|;
name|INSTRUCTIONS
index|[
name|Constants
operator|.
name|DUP
index|]
operator|=
name|DUP
expr_stmt|;
name|INSTRUCTIONS
index|[
name|Constants
operator|.
name|DUP_X1
index|]
operator|=
name|DUP_X1
expr_stmt|;
name|INSTRUCTIONS
index|[
name|Constants
operator|.
name|DUP_X2
index|]
operator|=
name|DUP_X2
expr_stmt|;
name|INSTRUCTIONS
index|[
name|Constants
operator|.
name|DUP2
index|]
operator|=
name|DUP2
expr_stmt|;
name|INSTRUCTIONS
index|[
name|Constants
operator|.
name|DUP2_X1
index|]
operator|=
name|DUP2_X1
expr_stmt|;
name|INSTRUCTIONS
index|[
name|Constants
operator|.
name|DUP2_X2
index|]
operator|=
name|DUP2_X2
expr_stmt|;
name|INSTRUCTIONS
index|[
name|Constants
operator|.
name|SWAP
index|]
operator|=
name|SWAP
expr_stmt|;
name|INSTRUCTIONS
index|[
name|Constants
operator|.
name|IADD
index|]
operator|=
name|IADD
expr_stmt|;
name|INSTRUCTIONS
index|[
name|Constants
operator|.
name|LADD
index|]
operator|=
name|LADD
expr_stmt|;
name|INSTRUCTIONS
index|[
name|Constants
operator|.
name|FADD
index|]
operator|=
name|FADD
expr_stmt|;
name|INSTRUCTIONS
index|[
name|Constants
operator|.
name|DADD
index|]
operator|=
name|DADD
expr_stmt|;
name|INSTRUCTIONS
index|[
name|Constants
operator|.
name|ISUB
index|]
operator|=
name|ISUB
expr_stmt|;
name|INSTRUCTIONS
index|[
name|Constants
operator|.
name|LSUB
index|]
operator|=
name|LSUB
expr_stmt|;
name|INSTRUCTIONS
index|[
name|Constants
operator|.
name|FSUB
index|]
operator|=
name|FSUB
expr_stmt|;
name|INSTRUCTIONS
index|[
name|Constants
operator|.
name|DSUB
index|]
operator|=
name|DSUB
expr_stmt|;
name|INSTRUCTIONS
index|[
name|Constants
operator|.
name|IMUL
index|]
operator|=
name|IMUL
expr_stmt|;
name|INSTRUCTIONS
index|[
name|Constants
operator|.
name|LMUL
index|]
operator|=
name|LMUL
expr_stmt|;
name|INSTRUCTIONS
index|[
name|Constants
operator|.
name|FMUL
index|]
operator|=
name|FMUL
expr_stmt|;
name|INSTRUCTIONS
index|[
name|Constants
operator|.
name|DMUL
index|]
operator|=
name|DMUL
expr_stmt|;
name|INSTRUCTIONS
index|[
name|Constants
operator|.
name|IDIV
index|]
operator|=
name|IDIV
expr_stmt|;
name|INSTRUCTIONS
index|[
name|Constants
operator|.
name|LDIV
index|]
operator|=
name|LDIV
expr_stmt|;
name|INSTRUCTIONS
index|[
name|Constants
operator|.
name|FDIV
index|]
operator|=
name|FDIV
expr_stmt|;
name|INSTRUCTIONS
index|[
name|Constants
operator|.
name|DDIV
index|]
operator|=
name|DDIV
expr_stmt|;
name|INSTRUCTIONS
index|[
name|Constants
operator|.
name|IREM
index|]
operator|=
name|IREM
expr_stmt|;
name|INSTRUCTIONS
index|[
name|Constants
operator|.
name|LREM
index|]
operator|=
name|LREM
expr_stmt|;
name|INSTRUCTIONS
index|[
name|Constants
operator|.
name|FREM
index|]
operator|=
name|FREM
expr_stmt|;
name|INSTRUCTIONS
index|[
name|Constants
operator|.
name|DREM
index|]
operator|=
name|DREM
expr_stmt|;
name|INSTRUCTIONS
index|[
name|Constants
operator|.
name|INEG
index|]
operator|=
name|INEG
expr_stmt|;
name|INSTRUCTIONS
index|[
name|Constants
operator|.
name|LNEG
index|]
operator|=
name|LNEG
expr_stmt|;
name|INSTRUCTIONS
index|[
name|Constants
operator|.
name|FNEG
index|]
operator|=
name|FNEG
expr_stmt|;
name|INSTRUCTIONS
index|[
name|Constants
operator|.
name|DNEG
index|]
operator|=
name|DNEG
expr_stmt|;
name|INSTRUCTIONS
index|[
name|Constants
operator|.
name|ISHL
index|]
operator|=
name|ISHL
expr_stmt|;
name|INSTRUCTIONS
index|[
name|Constants
operator|.
name|LSHL
index|]
operator|=
name|LSHL
expr_stmt|;
name|INSTRUCTIONS
index|[
name|Constants
operator|.
name|ISHR
index|]
operator|=
name|ISHR
expr_stmt|;
name|INSTRUCTIONS
index|[
name|Constants
operator|.
name|LSHR
index|]
operator|=
name|LSHR
expr_stmt|;
name|INSTRUCTIONS
index|[
name|Constants
operator|.
name|IUSHR
index|]
operator|=
name|IUSHR
expr_stmt|;
name|INSTRUCTIONS
index|[
name|Constants
operator|.
name|LUSHR
index|]
operator|=
name|LUSHR
expr_stmt|;
name|INSTRUCTIONS
index|[
name|Constants
operator|.
name|IAND
index|]
operator|=
name|IAND
expr_stmt|;
name|INSTRUCTIONS
index|[
name|Constants
operator|.
name|LAND
index|]
operator|=
name|LAND
expr_stmt|;
name|INSTRUCTIONS
index|[
name|Constants
operator|.
name|IOR
index|]
operator|=
name|IOR
expr_stmt|;
name|INSTRUCTIONS
index|[
name|Constants
operator|.
name|LOR
index|]
operator|=
name|LOR
expr_stmt|;
name|INSTRUCTIONS
index|[
name|Constants
operator|.
name|IXOR
index|]
operator|=
name|IXOR
expr_stmt|;
name|INSTRUCTIONS
index|[
name|Constants
operator|.
name|LXOR
index|]
operator|=
name|LXOR
expr_stmt|;
name|INSTRUCTIONS
index|[
name|Constants
operator|.
name|I2L
index|]
operator|=
name|I2L
expr_stmt|;
name|INSTRUCTIONS
index|[
name|Constants
operator|.
name|I2F
index|]
operator|=
name|I2F
expr_stmt|;
name|INSTRUCTIONS
index|[
name|Constants
operator|.
name|I2D
index|]
operator|=
name|I2D
expr_stmt|;
name|INSTRUCTIONS
index|[
name|Constants
operator|.
name|L2I
index|]
operator|=
name|L2I
expr_stmt|;
name|INSTRUCTIONS
index|[
name|Constants
operator|.
name|L2F
index|]
operator|=
name|L2F
expr_stmt|;
name|INSTRUCTIONS
index|[
name|Constants
operator|.
name|L2D
index|]
operator|=
name|L2D
expr_stmt|;
name|INSTRUCTIONS
index|[
name|Constants
operator|.
name|F2I
index|]
operator|=
name|F2I
expr_stmt|;
name|INSTRUCTIONS
index|[
name|Constants
operator|.
name|F2L
index|]
operator|=
name|F2L
expr_stmt|;
name|INSTRUCTIONS
index|[
name|Constants
operator|.
name|F2D
index|]
operator|=
name|F2D
expr_stmt|;
name|INSTRUCTIONS
index|[
name|Constants
operator|.
name|D2I
index|]
operator|=
name|D2I
expr_stmt|;
name|INSTRUCTIONS
index|[
name|Constants
operator|.
name|D2L
index|]
operator|=
name|D2L
expr_stmt|;
name|INSTRUCTIONS
index|[
name|Constants
operator|.
name|D2F
index|]
operator|=
name|D2F
expr_stmt|;
name|INSTRUCTIONS
index|[
name|Constants
operator|.
name|I2B
index|]
operator|=
name|I2B
expr_stmt|;
name|INSTRUCTIONS
index|[
name|Constants
operator|.
name|I2C
index|]
operator|=
name|I2C
expr_stmt|;
name|INSTRUCTIONS
index|[
name|Constants
operator|.
name|I2S
index|]
operator|=
name|I2S
expr_stmt|;
name|INSTRUCTIONS
index|[
name|Constants
operator|.
name|LCMP
index|]
operator|=
name|LCMP
expr_stmt|;
name|INSTRUCTIONS
index|[
name|Constants
operator|.
name|FCMPL
index|]
operator|=
name|FCMPL
expr_stmt|;
name|INSTRUCTIONS
index|[
name|Constants
operator|.
name|FCMPG
index|]
operator|=
name|FCMPG
expr_stmt|;
name|INSTRUCTIONS
index|[
name|Constants
operator|.
name|DCMPL
index|]
operator|=
name|DCMPL
expr_stmt|;
name|INSTRUCTIONS
index|[
name|Constants
operator|.
name|DCMPG
index|]
operator|=
name|DCMPG
expr_stmt|;
name|INSTRUCTIONS
index|[
name|Constants
operator|.
name|IRETURN
index|]
operator|=
name|IRETURN
expr_stmt|;
name|INSTRUCTIONS
index|[
name|Constants
operator|.
name|LRETURN
index|]
operator|=
name|LRETURN
expr_stmt|;
name|INSTRUCTIONS
index|[
name|Constants
operator|.
name|FRETURN
index|]
operator|=
name|FRETURN
expr_stmt|;
name|INSTRUCTIONS
index|[
name|Constants
operator|.
name|DRETURN
index|]
operator|=
name|DRETURN
expr_stmt|;
name|INSTRUCTIONS
index|[
name|Constants
operator|.
name|ARETURN
index|]
operator|=
name|ARETURN
expr_stmt|;
name|INSTRUCTIONS
index|[
name|Constants
operator|.
name|RETURN
index|]
operator|=
name|RETURN
expr_stmt|;
name|INSTRUCTIONS
index|[
name|Constants
operator|.
name|ARRAYLENGTH
index|]
operator|=
name|ARRAYLENGTH
expr_stmt|;
name|INSTRUCTIONS
index|[
name|Constants
operator|.
name|ATHROW
index|]
operator|=
name|ATHROW
expr_stmt|;
name|INSTRUCTIONS
index|[
name|Constants
operator|.
name|MONITORENTER
index|]
operator|=
name|MONITORENTER
expr_stmt|;
name|INSTRUCTIONS
index|[
name|Constants
operator|.
name|MONITOREXIT
index|]
operator|=
name|MONITOREXIT
expr_stmt|;
block|}
specifier|private
name|InstructionConst
parameter_list|()
block|{
block|}
comment|// non-instantiable
comment|/**      * Gets the Instruction.      * @param index the index, e.g. {@link Constants#RETURN}      * @return the entry from the private INSTRUCTIONS table      */
specifier|public
specifier|static
name|Instruction
name|getInstruction
parameter_list|(
name|int
name|index
parameter_list|)
block|{
return|return
name|INSTRUCTIONS
index|[
name|index
index|]
return|;
block|}
block|}
end_class

end_unit

