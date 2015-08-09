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
name|ConstantCP
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
name|ConstantNameAndType
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
name|ConstantPool
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
name|ConstantUtf8
import|;
end_import

begin_comment
comment|/**  * Super class for FieldOrMethod and INVOKEDYNAMIC, since they both have  * names and signatures   *  * @version $Id: FieldOrMethod.java 1481383 2013-05-11 17:34:32Z dbrosius $  * @author<A HREF="mailto:bill.pugh@gmail.com">Bill Pugh</A>  * @since 6.0  */
end_comment

begin_class
specifier|public
specifier|abstract
class|class
name|NameSignatureInstruction
extends|extends
name|CPInstruction
block|{
specifier|private
specifier|static
specifier|final
name|long
name|serialVersionUID
init|=
literal|1L
decl_stmt|;
specifier|public
name|NameSignatureInstruction
parameter_list|()
block|{
name|super
argument_list|()
expr_stmt|;
block|}
specifier|public
name|NameSignatureInstruction
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
specifier|public
name|ConstantNameAndType
name|getNameAndType
parameter_list|(
name|ConstantPoolGen
name|cpg
parameter_list|)
block|{
name|ConstantPool
name|cp
init|=
name|cpg
operator|.
name|getConstantPool
argument_list|()
decl_stmt|;
name|ConstantCP
name|cmr
init|=
operator|(
name|ConstantCP
operator|)
name|cp
operator|.
name|getConstant
argument_list|(
name|index
argument_list|)
decl_stmt|;
return|return
operator|(
name|ConstantNameAndType
operator|)
name|cp
operator|.
name|getConstant
argument_list|(
name|cmr
operator|.
name|getNameAndTypeIndex
argument_list|()
argument_list|)
return|;
block|}
comment|/** @return signature of referenced method/field.      */
specifier|public
name|String
name|getSignature
parameter_list|(
name|ConstantPoolGen
name|cpg
parameter_list|)
block|{
name|ConstantPool
name|cp
init|=
name|cpg
operator|.
name|getConstantPool
argument_list|()
decl_stmt|;
name|ConstantNameAndType
name|cnat
init|=
name|getNameAndType
argument_list|(
name|cpg
argument_list|)
decl_stmt|;
return|return
operator|(
operator|(
name|ConstantUtf8
operator|)
name|cp
operator|.
name|getConstant
argument_list|(
name|cnat
operator|.
name|getSignatureIndex
argument_list|()
argument_list|)
operator|)
operator|.
name|getBytes
argument_list|()
return|;
block|}
comment|/** @return name of referenced method/field.      */
specifier|public
name|String
name|getName
parameter_list|(
name|ConstantPoolGen
name|cpg
parameter_list|)
block|{
name|ConstantPool
name|cp
init|=
name|cpg
operator|.
name|getConstantPool
argument_list|()
decl_stmt|;
name|ConstantNameAndType
name|cnat
init|=
name|getNameAndType
argument_list|(
name|cpg
argument_list|)
decl_stmt|;
return|return
operator|(
operator|(
name|ConstantUtf8
operator|)
name|cp
operator|.
name|getConstant
argument_list|(
name|cnat
operator|.
name|getNameIndex
argument_list|()
argument_list|)
operator|)
operator|.
name|getBytes
argument_list|()
return|;
block|}
block|}
end_class

end_unit
