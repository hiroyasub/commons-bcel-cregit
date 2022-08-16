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
name|verifier
operator|.
name|exc
package|;
end_package

begin_comment
comment|/**  * Instances of this class are thrown by BCEL's class file verifier "JustIce" when a class file to verify does not pass  * the verification pass 3 because of a violation of a static constraint as described in the Java Virtual Machine  * Specification, Second edition, 4.8.1, pages 133-137. The static constraints checking part of pass 3 is called pass 3a  * in JustIce. Static constraints on the operands of instructions in the code array are checked late in pass 3a and are  * described on page 134-137 in the Java Virtual Machine Specification, Second Edition.  *  */
end_comment

begin_class
specifier|public
class|class
name|StaticCodeInstructionOperandConstraintException
extends|extends
name|StaticCodeConstraintException
block|{
specifier|private
specifier|static
specifier|final
name|long
name|serialVersionUID
init|=
literal|4780787099381933487L
decl_stmt|;
specifier|public
name|StaticCodeInstructionOperandConstraintException
parameter_list|(
specifier|final
name|String
name|message
parameter_list|)
block|{
name|super
argument_list|(
name|message
argument_list|)
expr_stmt|;
block|}
block|}
end_class

end_unit

