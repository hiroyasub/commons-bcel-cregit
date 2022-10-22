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
name|classfile
package|;
end_package

begin_import
import|import
name|java
operator|.
name|io
operator|.
name|DataInput
import|;
end_import

begin_import
import|import
name|java
operator|.
name|io
operator|.
name|IOException
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
name|Const
import|;
end_import

begin_comment
comment|/**  * Represents a parameter annotation that is represented in the class file but is not provided to the JVM.  *  * @since 6.0  */
end_comment

begin_class
specifier|public
class|class
name|RuntimeInvisibleParameterAnnotations
extends|extends
name|ParameterAnnotations
block|{
comment|/**      * @param nameIndex Index pointing to the name<em>Code</em>      * @param length Content length in bytes      * @param input Input stream      * @param constantPool Array of constants      */
specifier|public
name|RuntimeInvisibleParameterAnnotations
parameter_list|(
specifier|final
name|int
name|nameIndex
parameter_list|,
specifier|final
name|int
name|length
parameter_list|,
specifier|final
name|DataInput
name|input
parameter_list|,
specifier|final
name|ConstantPool
name|constantPool
parameter_list|)
throws|throws
name|IOException
block|{
name|super
argument_list|(
name|Const
operator|.
name|ATTR_RUNTIME_INVISIBLE_PARAMETER_ANNOTATIONS
argument_list|,
name|nameIndex
argument_list|,
name|length
argument_list|,
name|input
argument_list|,
name|constantPool
argument_list|)
expr_stmt|;
block|}
block|}
end_class

end_unit

