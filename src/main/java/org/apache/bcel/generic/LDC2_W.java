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
name|bcel
operator|.
name|generic
package|;
end_package

begin_comment
comment|/**   * LDC2_W - Push long or double from constant pool  *  *<PRE>Stack: ... -&gt; ..., item.word1, item.word2</PRE>  *  * @version $Id$  */
end_comment

begin_class
specifier|public
class|class
name|LDC2_W
extends|extends
name|CPInstruction
implements|implements
name|PushInstruction
block|{
comment|/**      * Empty constructor needed for the Class.newInstance() statement in      * Instruction.readInstruction(). Not to be used otherwise.      */
name|LDC2_W
parameter_list|()
block|{
block|}
specifier|public
name|LDC2_W
parameter_list|(
specifier|final
name|int
name|index
parameter_list|)
block|{
name|super
argument_list|(
name|org
operator|.
name|apache
operator|.
name|bcel
operator|.
name|Const
operator|.
name|LDC2_W
argument_list|,
name|index
argument_list|)
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|Type
name|getType
parameter_list|(
specifier|final
name|ConstantPoolGen
name|cpg
parameter_list|)
block|{
switch|switch
condition|(
name|cpg
operator|.
name|getConstantPool
argument_list|()
operator|.
name|getConstant
argument_list|(
name|super
operator|.
name|getIndex
argument_list|()
argument_list|)
operator|.
name|getTag
argument_list|()
condition|)
block|{
case|case
name|org
operator|.
name|apache
operator|.
name|bcel
operator|.
name|Const
operator|.
name|CONSTANT_Long
case|:
return|return
name|Type
operator|.
name|LONG
return|;
case|case
name|org
operator|.
name|apache
operator|.
name|bcel
operator|.
name|Const
operator|.
name|CONSTANT_Double
case|:
return|return
name|Type
operator|.
name|DOUBLE
return|;
default|default:
comment|// Never reached
throw|throw
operator|new
name|RuntimeException
argument_list|(
literal|"Unknown constant type "
operator|+
name|super
operator|.
name|getOpcode
argument_list|()
argument_list|)
throw|;
block|}
block|}
specifier|public
name|Number
name|getValue
parameter_list|(
specifier|final
name|ConstantPoolGen
name|cpg
parameter_list|)
block|{
name|org
operator|.
name|apache
operator|.
name|bcel
operator|.
name|classfile
operator|.
name|Constant
name|c
init|=
name|cpg
operator|.
name|getConstantPool
argument_list|()
operator|.
name|getConstant
argument_list|(
name|super
operator|.
name|getIndex
argument_list|()
argument_list|)
decl_stmt|;
switch|switch
condition|(
name|c
operator|.
name|getTag
argument_list|()
condition|)
block|{
case|case
name|org
operator|.
name|apache
operator|.
name|bcel
operator|.
name|Const
operator|.
name|CONSTANT_Long
case|:
return|return
name|Long
operator|.
name|valueOf
argument_list|(
operator|(
operator|(
name|org
operator|.
name|apache
operator|.
name|bcel
operator|.
name|classfile
operator|.
name|ConstantLong
operator|)
name|c
operator|)
operator|.
name|getBytes
argument_list|()
argument_list|)
return|;
case|case
name|org
operator|.
name|apache
operator|.
name|bcel
operator|.
name|Const
operator|.
name|CONSTANT_Double
case|:
return|return
operator|new
name|Double
argument_list|(
operator|(
operator|(
name|org
operator|.
name|apache
operator|.
name|bcel
operator|.
name|classfile
operator|.
name|ConstantDouble
operator|)
name|c
operator|)
operator|.
name|getBytes
argument_list|()
argument_list|)
return|;
default|default:
comment|// Never reached
throw|throw
operator|new
name|RuntimeException
argument_list|(
literal|"Unknown or invalid constant type at "
operator|+
name|super
operator|.
name|getIndex
argument_list|()
argument_list|)
throw|;
block|}
block|}
comment|/**      * Call corresponding visitor method(s). The order is:      * Call visitor methods of implemented interfaces first, then      * call methods according to the class hierarchy in descending order,      * i.e., the most specific visitXXX() call comes last.      *      * @param v Visitor object      */
annotation|@
name|Override
specifier|public
name|void
name|accept
parameter_list|(
specifier|final
name|Visitor
name|v
parameter_list|)
block|{
name|v
operator|.
name|visitStackProducer
argument_list|(
name|this
argument_list|)
expr_stmt|;
name|v
operator|.
name|visitPushInstruction
argument_list|(
name|this
argument_list|)
expr_stmt|;
name|v
operator|.
name|visitTypedInstruction
argument_list|(
name|this
argument_list|)
expr_stmt|;
name|v
operator|.
name|visitCPInstruction
argument_list|(
name|this
argument_list|)
expr_stmt|;
name|v
operator|.
name|visitLDC2_W
argument_list|(
name|this
argument_list|)
expr_stmt|;
block|}
block|}
end_class

end_unit

