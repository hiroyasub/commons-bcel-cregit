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
name|ExceptionConst
import|;
end_import

begin_comment
comment|/**  * GETSTATIC - Fetch static field from class  *   *<PRE>  * Stack: ..., -&gt; ..., value  *</PRE>  *   * OR  *   *<PRE>  * Stack: ..., -&gt; ..., value.word1, value.word2  *</PRE>  *  */
end_comment

begin_class
specifier|public
class|class
name|GETSTATIC
extends|extends
name|FieldInstruction
implements|implements
name|PushInstruction
implements|,
name|ExceptionThrower
block|{
comment|/**      * Empty constructor needed for Instruction.readInstruction. Not to be used otherwise.      */
name|GETSTATIC
parameter_list|()
block|{
block|}
specifier|public
name|GETSTATIC
parameter_list|(
specifier|final
name|int
name|index
parameter_list|)
block|{
name|super
argument_list|(
name|Const
operator|.
name|GETSTATIC
argument_list|,
name|index
argument_list|)
expr_stmt|;
block|}
comment|/**      * Call corresponding visitor method(s). The order is: Call visitor methods of implemented interfaces first, then call      * methods according to the class hierarchy in descending order, i.e., the most specific visitXXX() call comes last.      *      * @param v Visitor object      */
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
name|visitExceptionThrower
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
name|visitLoadClass
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
name|visitFieldOrMethod
argument_list|(
name|this
argument_list|)
expr_stmt|;
name|v
operator|.
name|visitFieldInstruction
argument_list|(
name|this
argument_list|)
expr_stmt|;
name|v
operator|.
name|visitGETSTATIC
argument_list|(
name|this
argument_list|)
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|Class
argument_list|<
name|?
argument_list|>
index|[]
name|getExceptions
parameter_list|()
block|{
return|return
name|ExceptionConst
operator|.
name|createExceptions
argument_list|(
name|ExceptionConst
operator|.
name|EXCS
operator|.
name|EXCS_FIELD_AND_METHOD_RESOLUTION
argument_list|,
name|ExceptionConst
operator|.
name|INCOMPATIBLE_CLASS_CHANGE_ERROR
argument_list|)
return|;
block|}
annotation|@
name|Override
specifier|public
name|int
name|produceStack
parameter_list|(
specifier|final
name|ConstantPoolGen
name|cpg
parameter_list|)
block|{
return|return
name|getFieldSize
argument_list|(
name|cpg
argument_list|)
return|;
block|}
block|}
end_class

end_unit

