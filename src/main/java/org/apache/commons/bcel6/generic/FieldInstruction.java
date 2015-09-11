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
name|classfile
operator|.
name|ConstantPool
import|;
end_import

begin_comment
comment|/**  * Super class for the GET/PUTxxx family of instructions.  *  * @version $Id$  */
end_comment

begin_class
specifier|public
specifier|abstract
class|class
name|FieldInstruction
extends|extends
name|FieldOrMethod
block|{
comment|/**      * Empty constructor needed for the Class.newInstance() statement in      * Instruction.readInstruction(). Not to be used otherwise.      */
name|FieldInstruction
parameter_list|()
block|{
block|}
comment|/**      * @param index to constant pool      */
specifier|protected
name|FieldInstruction
parameter_list|(
name|short
name|opcode
parameter_list|,
name|int
name|index
parameter_list|)
block|{
name|super
argument_list|(
name|opcode
argument_list|,
name|index
argument_list|)
expr_stmt|;
block|}
comment|/**      * @return mnemonic for instruction with symbolic references resolved      */
annotation|@
name|Override
specifier|public
name|String
name|toString
parameter_list|(
name|ConstantPool
name|cp
parameter_list|)
block|{
return|return
name|org
operator|.
name|apache
operator|.
name|commons
operator|.
name|bcel6
operator|.
name|Const
operator|.
name|getOpcodeName
argument_list|(
name|super
operator|.
name|getOpcode
argument_list|()
argument_list|)
operator|+
literal|" "
operator|+
name|cp
operator|.
name|constantToString
argument_list|(
name|super
operator|.
name|getIndex
argument_list|()
argument_list|,
name|org
operator|.
name|apache
operator|.
name|commons
operator|.
name|bcel6
operator|.
name|Const
operator|.
name|CONSTANT_Fieldref
argument_list|)
return|;
block|}
comment|/** @return size of field (1 or 2)      */
specifier|protected
name|int
name|getFieldSize
parameter_list|(
name|ConstantPoolGen
name|cpg
parameter_list|)
block|{
return|return
name|Type
operator|.
name|size
argument_list|(
name|Type
operator|.
name|getTypeSize
argument_list|(
name|getSignature
argument_list|(
name|cpg
argument_list|)
argument_list|)
argument_list|)
return|;
block|}
comment|/** @return return type of referenced field      */
annotation|@
name|Override
specifier|public
name|Type
name|getType
parameter_list|(
name|ConstantPoolGen
name|cpg
parameter_list|)
block|{
return|return
name|getFieldType
argument_list|(
name|cpg
argument_list|)
return|;
block|}
comment|/** @return type of field      */
specifier|public
name|Type
name|getFieldType
parameter_list|(
name|ConstantPoolGen
name|cpg
parameter_list|)
block|{
return|return
name|Type
operator|.
name|getType
argument_list|(
name|getSignature
argument_list|(
name|cpg
argument_list|)
argument_list|)
return|;
block|}
comment|/** @return name of referenced field.      */
specifier|public
name|String
name|getFieldName
parameter_list|(
name|ConstantPoolGen
name|cpg
parameter_list|)
block|{
return|return
name|getName
argument_list|(
name|cpg
argument_list|)
return|;
block|}
block|}
end_class

end_unit

