begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
begin_comment
comment|/*  * Licensed to the Apache Software Foundation (ASF) under one or more  * contributor license agreements.  See the NOTICE file distributed with  * this work for additional information regarding copyright ownership.  * The ASF licenses this file to You under the Apache License, Version 2.0  * (the "License"); you may not use this file except in compliance with  * the License.  You may obtain a copy of the License at  *  *      http://www.apache.org/licenses/LICENSE-2.0  *  *  Unless required by applicable law or agreed to in writing, software  *  distributed under the License is distributed on an "AS IS" BASIS,  *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  *  See the License for the specific language governing permissions and  *  limitations under the License.  */
end_comment

begin_package
package|package
name|org
operator|.
name|apache
operator|.
name|bcel
operator|.
name|generic
package|;
end_package

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
name|fail
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
name|util
operator|.
name|SyntheticRepository
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
name|params
operator|.
name|ParameterizedTest
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
name|params
operator|.
name|provider
operator|.
name|ValueSource
import|;
end_import

begin_class
class|class
name|EmptyVisitorTestCase
block|{
comment|/*      * https://docs.oracle.com/javase/specs/jvms/se17/html/jvms-6.html#jvms-6.2      */
specifier|private
specifier|static
specifier|final
name|String
name|RESERVED_OPCODE
init|=
literal|"Reserved opcode"
decl_stmt|;
annotation|@
name|ParameterizedTest
annotation|@
name|ValueSource
argument_list|(
name|strings
operator|=
block|{
comment|// @formatter:off
literal|"java.math.BigInteger"
block|,
comment|// contains instructions [AALOAD, AASTORE, ACONST_NULL, ALOAD, ANEWARRAY, ARETURN, ARRAYLENGTH, ASTORE, ATHROW, BALOAD, BASTORE, BIPUSH, CALOAD, CHECKCAST, D2I, DADD, DALOAD, DASTORE, DCONST, DDIV, DMUL, DRETURN, DSUB, DUP, DUP2, DUP_X2, FCONST, FRETURN, GETFIELD, GETSTATIC, GOTO, I2B, I2D, I2L, IADD, IALOAD, IAND, IASTORE, ICONST, IDIV, IFEQ, IFGE, IFGT, IFLE, IFLT, IFNE, IFNONNULL, IFNULL, IF_ACMPNE, IF_ICMPEQ, IF_ICMPGE, IF_ICMPGT, IF_ICMPLE, IF_ICMPLT, IF_ICMPNE, IINC, ILOAD, IMUL, INEG, INSTANCEOF, INVOKESPECIAL, INVOKESTATIC, INVOKEVIRTUAL, IOR, IREM, IRETURN, ISHL, ISHR, ISTORE, ISUB, IUSHR, IXOR, L2D, L2F, L2I, LADD, LALOAD, LAND, LASTORE, LCMP, LCONST, LDC, LDC2_W, LDC_W, LDIV, LLOAD, LMUL, LNEG, LOOKUPSWITCH, LOR, LREM, LRETURN, LSHL, LSHR, LSTORE, LSUB, LUSHR, NEW, NEWARRAY, POP, PUTFIELD, PUTSTATIC, RETURN, SIPUSH]
literal|"java.math.BigDecimal"
block|,
comment|// contains instructions [CASTORE, D2L, DLOAD, FALOAD, FASTORE, FDIV, FMUL, I2S, IF_ACMPEQ, LXOR, MONITORENTER, MONITOREXIT, TABLESWITCH]
literal|"java.awt.Color"
block|,
comment|// contains instructions [D2F, DCMPG, DCMPL, F2D, F2I, FADD, FCMPG, FCMPL, FLOAD, FSTORE, FSUB, I2F, INVOKEDYNAMIC]
literal|"java.util.Map"
block|,
comment|// contains instruction INVOKEINTERFACE
literal|"java.io.Bits"
block|,
comment|// contains instruction I2C
literal|"java.io.BufferedInputStream"
block|,
comment|// contains instruction DUP_X1
literal|"java.io.StreamTokenizer"
block|,
comment|// contains instruction DNEG, DSTORE
literal|"java.lang.Float"
block|,
comment|// contains instruction F2L
literal|"java.lang.invoke.LambdaForm"
block|,
comment|// contains instruction MULTIANEWARRAY,
literal|"java.nio.Bits"
block|,
comment|// contains instruction POP2,
literal|"java.nio.HeapShortBuffer"
block|,
comment|// contains instruction SALOAD, SASTORE
literal|"java.awt.GradientPaintContext"
block|,
comment|// contains instruction DREM
literal|"java.util.concurrent.atomic.DoubleAccumulator"
block|,
comment|// contains instruction DUP2_X1
literal|"java.util.Hashtable"
block|,
comment|// contains instruction FNEG
literal|"javax.swing.text.html.CSS"
block|,
comment|// contains instruction DUP2_X2
literal|"org.apache.bcel.generic.LargeJump"
block|,
comment|// contains instruction GOTO_W
literal|"org.apache.commons.lang.SerializationUtils"
comment|// contains instruction JSR
comment|// @formatter:on
block|}
argument_list|)
specifier|public
name|void
name|test
parameter_list|(
specifier|final
name|String
name|className
parameter_list|)
throws|throws
name|ClassNotFoundException
block|{
specifier|final
name|JavaClass
name|javaClass
init|=
name|SyntheticRepository
operator|.
name|getInstance
argument_list|()
operator|.
name|loadClass
argument_list|(
name|className
argument_list|)
decl_stmt|;
for|for
control|(
specifier|final
name|Method
name|method
range|:
name|javaClass
operator|.
name|getMethods
argument_list|()
control|)
block|{
specifier|final
name|Code
name|code
init|=
name|method
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
name|InstructionList
name|instructionList
init|=
operator|new
name|InstructionList
argument_list|(
name|code
operator|.
name|getCode
argument_list|()
argument_list|)
decl_stmt|;
for|for
control|(
specifier|final
name|InstructionHandle
name|instructionHandle
range|:
name|instructionList
control|)
block|{
name|instructionHandle
operator|.
name|accept
argument_list|(
operator|new
name|EmptyVisitor
argument_list|()
block|{
annotation|@
name|Override
specifier|public
name|void
name|visitBREAKPOINT
parameter_list|(
specifier|final
name|BREAKPOINT
name|obj
parameter_list|)
block|{
name|fail
argument_list|(
name|RESERVED_OPCODE
argument_list|)
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitIMPDEP1
parameter_list|(
specifier|final
name|IMPDEP1
name|obj
parameter_list|)
block|{
name|fail
argument_list|(
name|RESERVED_OPCODE
argument_list|)
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitIMPDEP2
parameter_list|(
specifier|final
name|IMPDEP2
name|obj
parameter_list|)
block|{
name|fail
argument_list|(
name|RESERVED_OPCODE
argument_list|)
expr_stmt|;
block|}
block|}
argument_list|)
expr_stmt|;
block|}
block|}
block|}
block|}
block|}
end_class

end_unit

