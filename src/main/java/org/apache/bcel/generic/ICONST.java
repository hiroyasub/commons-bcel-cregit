begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
begin_comment
comment|/*  * Licensed to the Apache Software Foundation (ASF) under one or more  * contributor license agreements.  See the NOTICE file distributed with  * this work for additional information regarding copyright ownership.  * The ASF licenses this file to You under the Apache License, Version 2.0  * (the "License"); you may not use this file except in compliance with  * the License.  You may obtain a copy of the License at  *  *      http://www.apache.org/licenses/LICENSE-2.0  *  *  Unless required by applicable law or agreed to in writing, software  *  distributed under the License is distributed on an "AS IS" BASIS,  *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  *  See the License for the specific language governing permissions and  *  limitations under the License.   *  */
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
comment|/**   * ICONST - Push value between -1, ..., 5, other values cause an exception  *  *<PRE>Stack: ... -&gt; ...,</PRE>  *  * @version $Id$  * @author<A HREF="mailto:m.dahm@gmx.de">M. Dahm</A>  */
end_comment

begin_class
specifier|public
class|class
name|ICONST
extends|extends
name|Instruction
implements|implements
name|ConstantPushInstruction
block|{
specifier|private
specifier|static
specifier|final
name|long
name|serialVersionUID
init|=
operator|-
literal|174595933747784635L
decl_stmt|;
specifier|private
name|int
name|value
decl_stmt|;
comment|/**      * Empty constructor needed for the Class.newInstance() statement in      * Instruction.readInstruction(). Not to be used otherwise.      */
name|ICONST
parameter_list|()
block|{
block|}
specifier|public
name|ICONST
parameter_list|(
name|int
name|i
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
name|Constants
operator|.
name|ICONST_0
argument_list|,
operator|(
name|short
operator|)
literal|1
argument_list|)
expr_stmt|;
if|if
condition|(
operator|(
name|i
operator|>=
operator|-
literal|1
operator|)
operator|&&
operator|(
name|i
operator|<=
literal|5
operator|)
condition|)
block|{
name|opcode
operator|=
operator|(
name|short
operator|)
operator|(
name|org
operator|.
name|apache
operator|.
name|bcel
operator|.
name|Constants
operator|.
name|ICONST_0
operator|+
name|i
operator|)
expr_stmt|;
comment|// Even works for i == -1
block|}
else|else
block|{
throw|throw
operator|new
name|ClassGenException
argument_list|(
literal|"ICONST can be used only for value between -1 and 5: "
operator|+
name|i
argument_list|)
throw|;
block|}
name|value
operator|=
name|i
expr_stmt|;
block|}
specifier|public
name|Number
name|getValue
parameter_list|()
block|{
return|return
name|Integer
operator|.
name|valueOf
argument_list|(
name|value
argument_list|)
return|;
block|}
comment|/** @return Type.INT      */
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
name|INT
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
name|visitPushInstruction
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
name|visitTypedInstruction
argument_list|(
name|this
argument_list|)
expr_stmt|;
name|v
operator|.
name|visitConstantPushInstruction
argument_list|(
name|this
argument_list|)
expr_stmt|;
name|v
operator|.
name|visitICONST
argument_list|(
name|this
argument_list|)
expr_stmt|;
block|}
block|}
end_class

end_unit

