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

begin_import
import|import
name|java
operator|.
name|io
operator|.
name|DataInputStream
import|;
end_import

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
name|java
operator|.
name|util
operator|.
name|ArrayList
import|;
end_import

begin_import
import|import
name|java
operator|.
name|util
operator|.
name|List
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
name|AnnotationEntry
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
name|ConstantUtf8
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
name|ElementValuePair
import|;
end_import

begin_class
specifier|public
class|class
name|AnnotationEntryGen
block|{
specifier|private
name|int
name|typeIndex
decl_stmt|;
specifier|private
name|List
argument_list|<
name|ElementValuePairGen
argument_list|>
name|evs
decl_stmt|;
specifier|private
name|ConstantPoolGen
name|cpool
decl_stmt|;
specifier|private
name|boolean
name|isRuntimeVisible
init|=
literal|false
decl_stmt|;
comment|/**      * Here we are taking a fixed annotation of type Annotation and building a      * modifiable AnnotationGen object. If the pool passed in is for a different      * class file, then copyPoolEntries should have been passed as true as that      * will force us to do a deep copy of the annotation and move the cpool      * entries across. We need to copy the type and the element name value pairs      * and the visibility.      */
specifier|public
name|AnnotationEntryGen
parameter_list|(
name|AnnotationEntry
name|a
parameter_list|,
name|ConstantPoolGen
name|cpool
parameter_list|,
name|boolean
name|copyPoolEntries
parameter_list|)
block|{
name|this
operator|.
name|cpool
operator|=
name|cpool
expr_stmt|;
if|if
condition|(
name|copyPoolEntries
condition|)
block|{
name|typeIndex
operator|=
name|cpool
operator|.
name|addUtf8
argument_list|(
name|a
operator|.
name|getAnnotationType
argument_list|()
argument_list|)
expr_stmt|;
block|}
else|else
block|{
name|typeIndex
operator|=
name|a
operator|.
name|getAnnotationTypeIndex
argument_list|()
expr_stmt|;
block|}
name|isRuntimeVisible
operator|=
name|a
operator|.
name|isRuntimeVisible
argument_list|()
expr_stmt|;
name|evs
operator|=
name|copyValues
argument_list|(
name|a
operator|.
name|getElementValuePairs
argument_list|()
argument_list|,
name|cpool
argument_list|,
name|copyPoolEntries
argument_list|)
expr_stmt|;
block|}
specifier|private
name|List
argument_list|<
name|ElementValuePairGen
argument_list|>
name|copyValues
parameter_list|(
name|ElementValuePair
index|[]
name|in
parameter_list|,
name|ConstantPoolGen
name|cpool
parameter_list|,
name|boolean
name|copyPoolEntries
parameter_list|)
block|{
name|List
argument_list|<
name|ElementValuePairGen
argument_list|>
name|out
init|=
operator|new
name|ArrayList
argument_list|<
name|ElementValuePairGen
argument_list|>
argument_list|()
decl_stmt|;
name|int
name|l
init|=
name|in
operator|.
name|length
decl_stmt|;
for|for
control|(
name|ElementValuePair
name|nvp
range|:
name|in
control|)
block|{
name|out
operator|.
name|add
argument_list|(
operator|new
name|ElementValuePairGen
argument_list|(
name|nvp
argument_list|,
name|cpool
argument_list|,
name|copyPoolEntries
argument_list|)
argument_list|)
expr_stmt|;
block|}
return|return
name|out
return|;
block|}
specifier|private
name|AnnotationEntryGen
parameter_list|(
name|ConstantPoolGen
name|cpool
parameter_list|)
block|{
name|this
operator|.
name|cpool
operator|=
name|cpool
expr_stmt|;
block|}
comment|/**      * Retrieve an immutable version of this AnnotationGen      */
specifier|public
name|AnnotationEntry
name|getAnnotation
parameter_list|()
block|{
name|AnnotationEntry
name|a
init|=
operator|new
name|AnnotationEntry
argument_list|(
name|typeIndex
argument_list|,
name|cpool
operator|.
name|getConstantPool
argument_list|()
argument_list|,
name|isRuntimeVisible
argument_list|)
decl_stmt|;
for|for
control|(
name|ElementValuePairGen
name|element
range|:
name|evs
control|)
block|{
name|a
operator|.
name|addElementNameValuePair
argument_list|(
name|element
operator|.
name|getElementNameValuePair
argument_list|()
argument_list|)
expr_stmt|;
block|}
return|return
name|a
return|;
block|}
specifier|public
name|AnnotationEntryGen
parameter_list|(
name|ObjectType
name|type
parameter_list|,
name|List
argument_list|<
name|ElementValuePairGen
argument_list|>
name|elements
parameter_list|,
name|boolean
name|vis
parameter_list|,
name|ConstantPoolGen
name|cpool
parameter_list|)
block|{
name|this
operator|.
name|cpool
operator|=
name|cpool
expr_stmt|;
name|this
operator|.
name|typeIndex
operator|=
name|cpool
operator|.
name|addUtf8
argument_list|(
name|type
operator|.
name|getSignature
argument_list|()
argument_list|)
expr_stmt|;
name|evs
operator|=
name|elements
expr_stmt|;
name|isRuntimeVisible
operator|=
name|vis
expr_stmt|;
block|}
specifier|public
specifier|static
name|AnnotationEntryGen
name|read
parameter_list|(
name|DataInputStream
name|dis
parameter_list|,
name|ConstantPoolGen
name|cpool
parameter_list|,
name|boolean
name|b
parameter_list|)
throws|throws
name|IOException
block|{
name|AnnotationEntryGen
name|a
init|=
operator|new
name|AnnotationEntryGen
argument_list|(
name|cpool
argument_list|)
decl_stmt|;
name|a
operator|.
name|typeIndex
operator|=
name|dis
operator|.
name|readUnsignedShort
argument_list|()
expr_stmt|;
name|int
name|elemValuePairCount
init|=
name|dis
operator|.
name|readUnsignedShort
argument_list|()
decl_stmt|;
for|for
control|(
name|int
name|i
init|=
literal|0
init|;
name|i
operator|<
name|elemValuePairCount
condition|;
name|i
operator|++
control|)
block|{
name|int
name|nidx
init|=
name|dis
operator|.
name|readUnsignedShort
argument_list|()
decl_stmt|;
name|a
operator|.
name|addElementNameValuePair
argument_list|(
operator|new
name|ElementValuePairGen
argument_list|(
name|nidx
argument_list|,
name|ElementValueGen
operator|.
name|readElementValue
argument_list|(
name|dis
argument_list|,
name|cpool
argument_list|)
argument_list|,
name|cpool
argument_list|)
argument_list|)
expr_stmt|;
block|}
name|a
operator|.
name|isRuntimeVisible
argument_list|(
name|b
argument_list|)
expr_stmt|;
return|return
name|a
return|;
block|}
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
name|writeShort
argument_list|(
name|typeIndex
argument_list|)
expr_stmt|;
comment|// u2 index of type name in cpool
name|dos
operator|.
name|writeShort
argument_list|(
name|evs
operator|.
name|size
argument_list|()
argument_list|)
expr_stmt|;
comment|// u2 element_value pair count
for|for
control|(
name|ElementValuePairGen
name|envp
range|:
name|evs
control|)
block|{
name|envp
operator|.
name|dump
argument_list|(
name|dos
argument_list|)
expr_stmt|;
block|}
block|}
specifier|public
name|void
name|addElementNameValuePair
parameter_list|(
name|ElementValuePairGen
name|evp
parameter_list|)
block|{
if|if
condition|(
name|evs
operator|==
literal|null
condition|)
block|{
name|evs
operator|=
operator|new
name|ArrayList
argument_list|<
name|ElementValuePairGen
argument_list|>
argument_list|()
expr_stmt|;
block|}
name|evs
operator|.
name|add
argument_list|(
name|evp
argument_list|)
expr_stmt|;
block|}
specifier|public
name|int
name|getTypeIndex
parameter_list|()
block|{
return|return
name|typeIndex
return|;
block|}
specifier|public
specifier|final
name|String
name|getTypeSignature
parameter_list|()
block|{
comment|// ConstantClass c = (ConstantClass)cpool.getConstant(typeIndex);
name|ConstantUtf8
name|utf8
init|=
operator|(
name|ConstantUtf8
operator|)
name|cpool
operator|.
name|getConstant
argument_list|(
name|typeIndex
comment|/* c.getNameIndex() */
argument_list|)
decl_stmt|;
return|return
name|utf8
operator|.
name|getBytes
argument_list|()
return|;
block|}
specifier|public
specifier|final
name|String
name|getTypeName
parameter_list|()
block|{
return|return
name|getTypeSignature
argument_list|()
return|;
comment|// BCELBUG: Should I use this instead?
comment|// Utility.signatureToString(getTypeSignature());
block|}
comment|/**      * Returns list of ElementNameValuePair objects      */
specifier|public
name|List
argument_list|<
name|ElementValuePairGen
argument_list|>
name|getValues
parameter_list|()
block|{
return|return
name|evs
return|;
block|}
annotation|@
name|Override
specifier|public
name|String
name|toString
parameter_list|()
block|{
name|StringBuilder
name|s
init|=
operator|new
name|StringBuilder
argument_list|(
literal|32
argument_list|)
decl_stmt|;
name|s
operator|.
name|append
argument_list|(
literal|"AnnotationGen:["
operator|+
name|getTypeName
argument_list|()
operator|+
literal|" #"
operator|+
name|evs
operator|.
name|size
argument_list|()
operator|+
literal|" {"
argument_list|)
expr_stmt|;
for|for
control|(
name|int
name|i
init|=
literal|0
init|;
name|i
operator|<
name|evs
operator|.
name|size
argument_list|()
condition|;
name|i
operator|++
control|)
block|{
name|s
operator|.
name|append
argument_list|(
name|evs
operator|.
name|get
argument_list|(
name|i
argument_list|)
argument_list|)
expr_stmt|;
if|if
condition|(
name|i
operator|+
literal|1
operator|<
name|evs
operator|.
name|size
argument_list|()
condition|)
block|{
name|s
operator|.
name|append
argument_list|(
literal|","
argument_list|)
expr_stmt|;
block|}
block|}
name|s
operator|.
name|append
argument_list|(
literal|"}]"
argument_list|)
expr_stmt|;
return|return
name|s
operator|.
name|toString
argument_list|()
return|;
block|}
specifier|public
name|String
name|toShortString
parameter_list|()
block|{
name|StringBuilder
name|s
init|=
operator|new
name|StringBuilder
argument_list|()
decl_stmt|;
name|s
operator|.
name|append
argument_list|(
literal|"@"
operator|+
name|getTypeName
argument_list|()
operator|+
literal|"("
argument_list|)
expr_stmt|;
for|for
control|(
name|int
name|i
init|=
literal|0
init|;
name|i
operator|<
name|evs
operator|.
name|size
argument_list|()
condition|;
name|i
operator|++
control|)
block|{
name|s
operator|.
name|append
argument_list|(
name|evs
operator|.
name|get
argument_list|(
name|i
argument_list|)
argument_list|)
expr_stmt|;
if|if
condition|(
name|i
operator|+
literal|1
operator|<
name|evs
operator|.
name|size
argument_list|()
condition|)
block|{
name|s
operator|.
name|append
argument_list|(
literal|","
argument_list|)
expr_stmt|;
block|}
block|}
name|s
operator|.
name|append
argument_list|(
literal|")"
argument_list|)
expr_stmt|;
return|return
name|s
operator|.
name|toString
argument_list|()
return|;
block|}
specifier|private
name|void
name|isRuntimeVisible
parameter_list|(
name|boolean
name|b
parameter_list|)
block|{
name|isRuntimeVisible
operator|=
name|b
expr_stmt|;
block|}
specifier|public
name|boolean
name|isRuntimeVisible
parameter_list|()
block|{
return|return
name|isRuntimeVisible
return|;
block|}
block|}
end_class

end_unit

