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
name|java
operator|.
name|io
operator|.
name|DataOutputStream
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
name|commons
operator|.
name|bcel6
operator|.
name|classfile
operator|.
name|AnnotationElementValue
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
name|ElementValue
import|;
end_import

begin_comment
comment|/**  * @since 6.0  */
end_comment

begin_class
specifier|public
class|class
name|AnnotationElementValueGen
extends|extends
name|ElementValueGen
block|{
comment|// For annotation element values, this is the annotation
specifier|private
specifier|final
name|AnnotationEntryGen
name|a
decl_stmt|;
specifier|public
name|AnnotationElementValueGen
parameter_list|(
name|AnnotationEntryGen
name|a
parameter_list|,
name|ConstantPoolGen
name|cpool
parameter_list|)
block|{
name|super
argument_list|(
name|ANNOTATION
argument_list|,
name|cpool
argument_list|)
expr_stmt|;
name|this
operator|.
name|a
operator|=
name|a
expr_stmt|;
block|}
specifier|public
name|AnnotationElementValueGen
parameter_list|(
name|int
name|type
parameter_list|,
name|AnnotationEntryGen
name|annotation
parameter_list|,
name|ConstantPoolGen
name|cpool
parameter_list|)
block|{
name|super
argument_list|(
name|type
argument_list|,
name|cpool
argument_list|)
expr_stmt|;
if|if
condition|(
name|type
operator|!=
name|ANNOTATION
condition|)
block|{
throw|throw
operator|new
name|RuntimeException
argument_list|(
literal|"Only element values of type annotation can be built with this ctor - type specified: "
operator|+
name|type
argument_list|)
throw|;
block|}
name|this
operator|.
name|a
operator|=
name|annotation
expr_stmt|;
block|}
specifier|public
name|AnnotationElementValueGen
parameter_list|(
name|AnnotationElementValue
name|value
parameter_list|,
name|ConstantPoolGen
name|cpool
parameter_list|,
name|boolean
name|copyPoolEntries
parameter_list|)
block|{
name|super
argument_list|(
name|ANNOTATION
argument_list|,
name|cpool
argument_list|)
expr_stmt|;
name|a
operator|=
operator|new
name|AnnotationEntryGen
argument_list|(
name|value
operator|.
name|getAnnotationEntry
argument_list|()
argument_list|,
name|cpool
argument_list|,
name|copyPoolEntries
argument_list|)
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|dump
parameter_list|(
name|DataOutputStream
name|dos
parameter_list|)
throws|throws
name|IOException
block|{
name|dos
operator|.
name|writeByte
argument_list|(
name|super
operator|.
name|getElementValueType
argument_list|()
argument_list|)
expr_stmt|;
comment|// u1 type of value (ANNOTATION == '@')
name|a
operator|.
name|dump
argument_list|(
name|dos
argument_list|)
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|String
name|stringifyValue
parameter_list|()
block|{
throw|throw
operator|new
name|RuntimeException
argument_list|(
literal|"Not implemented yet"
argument_list|)
throw|;
block|}
comment|/**      * Return immutable variant of this AnnotationElementValueGen      */
annotation|@
name|Override
specifier|public
name|ElementValue
name|getElementValue
parameter_list|()
block|{
return|return
operator|new
name|AnnotationElementValue
argument_list|(
name|super
operator|.
name|getElementValueType
argument_list|()
argument_list|,
name|a
operator|.
name|getAnnotation
argument_list|()
argument_list|,
name|getConstantPool
argument_list|()
operator|.
name|getConstantPool
argument_list|()
argument_list|)
return|;
block|}
specifier|public
name|AnnotationEntryGen
name|getAnnotation
parameter_list|()
block|{
return|return
name|a
return|;
block|}
block|}
end_class

end_unit

