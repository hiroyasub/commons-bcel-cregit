begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
begin_comment
comment|/*  * Licensed to the Apache Software Foundation (ASF) under one or more  * contributor license agreements.  See the NOTICE file distributed with  * this work for additional information regarding copyright ownership.  * The ASF licenses this file to You under the Apache License, Version 2.0  * (the "License"); you may not use this file except in compliance with  * the License.  You may obtain a copy of the License at  *  *   http://www.apache.org/licenses/LICENSE-2.0  *  * Unless required by applicable law or agreed to in writing, software  * distributed under the License is distributed on an "AS IS" BASIS,  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  * See the License for the specific language governing permissions and  * limitations under the License.  *   */
end_comment

begin_package
package|package
name|org
operator|.
name|apache
operator|.
name|bcel
package|;
end_package

begin_import
import|import
name|java
operator|.
name|io
operator|.
name|ByteArrayInputStream
import|;
end_import

begin_import
import|import
name|java
operator|.
name|io
operator|.
name|ByteArrayOutputStream
import|;
end_import

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
name|java
operator|.
name|util
operator|.
name|Vector
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
name|Annotations
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
name|Attribute
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
name|RuntimeInvisibleAnnotations
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
name|RuntimeVisibleAnnotations
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
name|Utility
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
name|generic
operator|.
name|AnnotationEntryGen
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
name|generic
operator|.
name|ClassGen
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
name|generic
operator|.
name|ConstantPoolGen
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
name|generic
operator|.
name|ElementValueGen
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
name|generic
operator|.
name|ElementValuePairGen
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
name|generic
operator|.
name|ObjectType
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
name|generic
operator|.
name|SimpleElementValueGen
import|;
end_import

begin_class
specifier|public
class|class
name|AnnotationGenTestCase
extends|extends
name|AbstractTestCase
block|{
specifier|private
name|ClassGen
name|createClassGen
parameter_list|(
name|String
name|classname
parameter_list|)
block|{
return|return
operator|new
name|ClassGen
argument_list|(
name|classname
argument_list|,
literal|"java.lang.Object"
argument_list|,
literal|"<generated>"
argument_list|,
name|Constants
operator|.
name|ACC_PUBLIC
operator||
name|Constants
operator|.
name|ACC_SUPER
argument_list|,
literal|null
argument_list|)
return|;
block|}
comment|/** 	 * Programmatically construct an mutable annotation (AnnotationGen) object. 	 */
specifier|public
name|void
name|testConstructMutableAnnotation
parameter_list|()
block|{
comment|// Create the containing class
name|ClassGen
name|cg
init|=
name|createClassGen
argument_list|(
literal|"HelloWorld"
argument_list|)
decl_stmt|;
name|ConstantPoolGen
name|cp
init|=
name|cg
operator|.
name|getConstantPool
argument_list|()
decl_stmt|;
comment|// Create the simple primitive value '4' of type 'int'
name|SimpleElementValueGen
name|evg
init|=
operator|new
name|SimpleElementValueGen
argument_list|(
name|ElementValueGen
operator|.
name|PRIMITIVE_INT
argument_list|,
name|cp
argument_list|,
literal|4
argument_list|)
decl_stmt|;
comment|// Give it a name, call it 'id'
name|ElementValuePairGen
name|nvGen
init|=
operator|new
name|ElementValuePairGen
argument_list|(
literal|"id"
argument_list|,
name|evg
argument_list|,
name|cp
argument_list|)
decl_stmt|;
comment|// Check it looks right
name|assertTrue
argument_list|(
literal|"Should include string 'id=4' but says: "
operator|+
name|nvGen
operator|.
name|toString
argument_list|()
argument_list|,
name|nvGen
operator|.
name|toString
argument_list|()
operator|.
name|indexOf
argument_list|(
literal|"id=4"
argument_list|)
operator|!=
operator|-
literal|1
argument_list|)
expr_stmt|;
name|ObjectType
name|t
init|=
operator|new
name|ObjectType
argument_list|(
literal|"SimpleAnnotation"
argument_list|)
decl_stmt|;
name|List
argument_list|<
name|ElementValuePairGen
argument_list|>
name|elements
init|=
operator|new
name|ArrayList
argument_list|<
name|ElementValuePairGen
argument_list|>
argument_list|()
decl_stmt|;
name|elements
operator|.
name|add
argument_list|(
name|nvGen
argument_list|)
expr_stmt|;
comment|// Build an annotation of type 'SimpleAnnotation' with 'id=4' as the
comment|// only value :)
name|AnnotationEntryGen
name|a
init|=
operator|new
name|AnnotationEntryGen
argument_list|(
name|t
argument_list|,
name|elements
argument_list|,
literal|true
argument_list|,
name|cp
argument_list|)
decl_stmt|;
comment|// Check we can save and load it ok
name|checkSerialize
argument_list|(
name|a
argument_list|,
name|cp
argument_list|)
expr_stmt|;
block|}
specifier|public
name|void
name|testVisibleInvisibleAnnotationGen
parameter_list|()
block|{
comment|// Create the containing class
name|ClassGen
name|cg
init|=
name|createClassGen
argument_list|(
literal|"HelloWorld"
argument_list|)
decl_stmt|;
name|ConstantPoolGen
name|cp
init|=
name|cg
operator|.
name|getConstantPool
argument_list|()
decl_stmt|;
comment|// Create the simple primitive value '4' of type 'int'
name|SimpleElementValueGen
name|evg
init|=
operator|new
name|SimpleElementValueGen
argument_list|(
name|ElementValueGen
operator|.
name|PRIMITIVE_INT
argument_list|,
name|cp
argument_list|,
literal|4
argument_list|)
decl_stmt|;
comment|// Give it a name, call it 'id'
name|ElementValuePairGen
name|nvGen
init|=
operator|new
name|ElementValuePairGen
argument_list|(
literal|"id"
argument_list|,
name|evg
argument_list|,
name|cp
argument_list|)
decl_stmt|;
comment|// Check it looks right
name|assertTrue
argument_list|(
literal|"Should include string 'id=4' but says: "
operator|+
name|nvGen
operator|.
name|toString
argument_list|()
argument_list|,
name|nvGen
operator|.
name|toString
argument_list|()
operator|.
name|indexOf
argument_list|(
literal|"id=4"
argument_list|)
operator|!=
operator|-
literal|1
argument_list|)
expr_stmt|;
name|ObjectType
name|t
init|=
operator|new
name|ObjectType
argument_list|(
literal|"SimpleAnnotation"
argument_list|)
decl_stmt|;
name|List
argument_list|<
name|ElementValuePairGen
argument_list|>
name|elements
init|=
operator|new
name|ArrayList
argument_list|<
name|ElementValuePairGen
argument_list|>
argument_list|()
decl_stmt|;
name|elements
operator|.
name|add
argument_list|(
name|nvGen
argument_list|)
expr_stmt|;
comment|// Build a RV annotation of type 'SimpleAnnotation' with 'id=4' as the
comment|// only value :)
name|AnnotationEntryGen
name|a
init|=
operator|new
name|AnnotationEntryGen
argument_list|(
name|t
argument_list|,
name|elements
argument_list|,
literal|true
argument_list|,
name|cp
argument_list|)
decl_stmt|;
name|Vector
argument_list|<
name|AnnotationEntryGen
argument_list|>
name|v
init|=
operator|new
name|Vector
argument_list|<
name|AnnotationEntryGen
argument_list|>
argument_list|()
decl_stmt|;
name|v
operator|.
name|add
argument_list|(
name|a
argument_list|)
expr_stmt|;
name|Attribute
index|[]
name|attributes
init|=
name|Utility
operator|.
name|getAnnotationAttributes
argument_list|(
name|cp
argument_list|,
name|v
argument_list|)
decl_stmt|;
name|boolean
name|foundRV
init|=
literal|false
decl_stmt|;
for|for
control|(
name|Attribute
name|attribute
range|:
name|attributes
control|)
block|{
if|if
condition|(
name|attribute
operator|instanceof
name|RuntimeVisibleAnnotations
condition|)
block|{
name|assertTrue
argument_list|(
operator|(
operator|(
name|Annotations
operator|)
name|attribute
operator|)
operator|.
name|isRuntimeVisible
argument_list|()
argument_list|)
expr_stmt|;
name|foundRV
operator|=
literal|true
expr_stmt|;
block|}
block|}
name|assertTrue
argument_list|(
literal|"Should have seen a RuntimeVisibleAnnotation"
argument_list|,
name|foundRV
argument_list|)
expr_stmt|;
comment|// Build a RIV annotation of type 'SimpleAnnotation' with 'id=4' as the
comment|// only value :)
name|AnnotationEntryGen
name|a2
init|=
operator|new
name|AnnotationEntryGen
argument_list|(
name|t
argument_list|,
name|elements
argument_list|,
literal|false
argument_list|,
name|cp
argument_list|)
decl_stmt|;
name|Vector
argument_list|<
name|AnnotationEntryGen
argument_list|>
name|v2
init|=
operator|new
name|Vector
argument_list|<
name|AnnotationEntryGen
argument_list|>
argument_list|()
decl_stmt|;
name|v2
operator|.
name|add
argument_list|(
name|a2
argument_list|)
expr_stmt|;
name|Attribute
index|[]
name|attributes2
init|=
name|Utility
operator|.
name|getAnnotationAttributes
argument_list|(
name|cp
argument_list|,
name|v2
argument_list|)
decl_stmt|;
name|boolean
name|foundRIV
init|=
literal|false
decl_stmt|;
for|for
control|(
name|Attribute
name|attribute
range|:
name|attributes2
control|)
block|{
if|if
condition|(
name|attribute
operator|instanceof
name|RuntimeInvisibleAnnotations
condition|)
block|{
name|assertFalse
argument_list|(
operator|(
operator|(
name|Annotations
operator|)
name|attribute
operator|)
operator|.
name|isRuntimeVisible
argument_list|()
argument_list|)
expr_stmt|;
name|foundRIV
operator|=
literal|true
expr_stmt|;
block|}
block|}
name|assertTrue
argument_list|(
literal|"Should have seen a RuntimeInvisibleAnnotation"
argument_list|,
name|foundRIV
argument_list|)
expr_stmt|;
block|}
specifier|private
name|void
name|checkSerialize
parameter_list|(
name|AnnotationEntryGen
name|a
parameter_list|,
name|ConstantPoolGen
name|cpg
parameter_list|)
block|{
try|try
block|{
name|String
name|beforeName
init|=
name|a
operator|.
name|getTypeName
argument_list|()
decl_stmt|;
name|ByteArrayOutputStream
name|baos
init|=
operator|new
name|ByteArrayOutputStream
argument_list|()
decl_stmt|;
name|DataOutputStream
name|dos
init|=
operator|new
name|DataOutputStream
argument_list|(
name|baos
argument_list|)
decl_stmt|;
name|a
operator|.
name|dump
argument_list|(
name|dos
argument_list|)
expr_stmt|;
name|dos
operator|.
name|flush
argument_list|()
expr_stmt|;
name|dos
operator|.
name|close
argument_list|()
expr_stmt|;
name|byte
index|[]
name|bs
init|=
name|baos
operator|.
name|toByteArray
argument_list|()
decl_stmt|;
name|ByteArrayInputStream
name|bais
init|=
operator|new
name|ByteArrayInputStream
argument_list|(
name|bs
argument_list|)
decl_stmt|;
name|DataInputStream
name|dis
init|=
operator|new
name|DataInputStream
argument_list|(
name|bais
argument_list|)
decl_stmt|;
name|AnnotationEntryGen
name|annAfter
init|=
name|AnnotationEntryGen
operator|.
name|read
argument_list|(
name|dis
argument_list|,
name|cpg
argument_list|,
name|a
operator|.
name|isRuntimeVisible
argument_list|()
argument_list|)
decl_stmt|;
name|dis
operator|.
name|close
argument_list|()
expr_stmt|;
name|String
name|afterName
init|=
name|annAfter
operator|.
name|getTypeName
argument_list|()
decl_stmt|;
if|if
condition|(
operator|!
name|beforeName
operator|.
name|equals
argument_list|(
name|afterName
argument_list|)
condition|)
block|{
name|fail
argument_list|(
literal|"Deserialization failed: before type='"
operator|+
name|beforeName
operator|+
literal|"' after type='"
operator|+
name|afterName
operator|+
literal|"'"
argument_list|)
expr_stmt|;
block|}
if|if
condition|(
name|a
operator|.
name|getValues
argument_list|()
operator|.
name|size
argument_list|()
operator|!=
name|annAfter
operator|.
name|getValues
argument_list|()
operator|.
name|size
argument_list|()
condition|)
block|{
name|fail
argument_list|(
literal|"Different numbers of element name value pairs?? "
operator|+
name|a
operator|.
name|getValues
argument_list|()
operator|.
name|size
argument_list|()
operator|+
literal|"!="
operator|+
name|annAfter
operator|.
name|getValues
argument_list|()
operator|.
name|size
argument_list|()
argument_list|)
expr_stmt|;
block|}
for|for
control|(
name|int
name|i
init|=
literal|0
init|;
name|i
operator|<
name|a
operator|.
name|getValues
argument_list|()
operator|.
name|size
argument_list|()
condition|;
name|i
operator|++
control|)
block|{
name|ElementValuePairGen
name|beforeElement
init|=
name|a
operator|.
name|getValues
argument_list|()
operator|.
name|get
argument_list|(
name|i
argument_list|)
decl_stmt|;
name|ElementValuePairGen
name|afterElement
init|=
name|annAfter
operator|.
name|getValues
argument_list|()
operator|.
name|get
argument_list|(
name|i
argument_list|)
decl_stmt|;
if|if
condition|(
operator|!
name|beforeElement
operator|.
name|getNameString
argument_list|()
operator|.
name|equals
argument_list|(
name|afterElement
operator|.
name|getNameString
argument_list|()
argument_list|)
condition|)
block|{
name|fail
argument_list|(
literal|"Different names?? "
operator|+
name|beforeElement
operator|.
name|getNameString
argument_list|()
operator|+
literal|"!="
operator|+
name|afterElement
operator|.
name|getNameString
argument_list|()
argument_list|)
expr_stmt|;
block|}
block|}
block|}
catch|catch
parameter_list|(
name|IOException
name|ioe
parameter_list|)
block|{
name|fail
argument_list|(
literal|"Unexpected exception whilst checking serialization: "
operator|+
name|ioe
argument_list|)
expr_stmt|;
block|}
block|}
block|}
end_class

end_unit

