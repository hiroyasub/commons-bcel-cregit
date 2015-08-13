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

begin_comment
comment|/**   * FCMPL - Compare floats: value1&lt; value2  *<PRE>Stack: ..., value1, value2 -&gt; ..., result</PRE>  *  * @version $Id$  */
end_comment

begin_class
specifier|public
class|class
name|FCMPL
extends|extends
name|Instruction
implements|implements
name|TypedInstruction
implements|,
name|StackProducer
implements|,
name|StackConsumer
block|{
specifier|private
specifier|static
specifier|final
name|long
name|serialVersionUID
init|=
operator|-
literal|5283096582947056142L
decl_stmt|;
specifier|public
name|FCMPL
parameter_list|()
block|{
name|super
argument_list|(
name|org
operator|.
name|apache
operator|.
name|commons
operator|.
name|bcel6
operator|.
name|Constants
operator|.
name|FCMPL
argument_list|,
operator|(
name|short
operator|)
literal|1
argument_list|)
expr_stmt|;
block|}
comment|/** @return Type.FLOAT      */
annotation|@
name|Override
specifier|public
name|Type
name|getType
parameter_list|(
name|ConstantPoolGen
name|cp
parameter_list|)
block|{
return|return
name|Type
operator|.
name|FLOAT
return|;
block|}
comment|/**      * Call corresponding visitor method(s). The order is:      * Call visitor methods of implemented interfaces first, then      * call methods according to the class hierarchy in descending order,      * i.e., the most specific visitXXX() call comes last.      *      * @param v Visitor object      */
annotation|@
name|Override
specifier|public
name|void
name|accept
parameter_list|(
name|Visitor
name|v
parameter_list|)
block|{
name|v
operator|.
name|visitTypedInstruction
argument_list|(
name|this
argument_list|)
expr_stmt|;
name|v
operator|.
name|visitStackProducer
argument_list|(
name|this
argument_list|)
expr_stmt|;
name|v
operator|.
name|visitStackConsumer
argument_list|(
name|this
argument_list|)
expr_stmt|;
name|v
operator|.
name|visitFCMPL
argument_list|(
name|this
argument_list|)
expr_stmt|;
block|}
block|}
end_class

end_unit

