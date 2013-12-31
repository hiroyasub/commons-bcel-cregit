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
name|File
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
name|classfile
operator|.
name|Field
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
name|JavaClass
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
name|ElementValuePair
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
name|FieldGen
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
name|util
operator|.
name|SyntheticRepository
import|;
end_import

begin_class
specifier|public
class|class
name|FieldAnnotationsTestCase
extends|extends
name|AbstractTestCase
block|{
comment|/** 	 * Check field AnnotationEntrys are retrievable. 	 */
specifier|public
name|void
name|testFieldAnnotationEntrys
parameter_list|()
throws|throws
name|ClassNotFoundException
block|{
name|JavaClass
name|clazz
init|=
name|getTestClass
argument_list|(
literal|"org.apache.bcel.data.AnnotatedFields"
argument_list|)
decl_stmt|;
comment|// TODO L...;?
name|checkAnnotatedField
argument_list|(
name|clazz
argument_list|,
literal|"i"
argument_list|,
literal|"Lorg/apache/bcel/data/SimpleAnnotation;"
argument_list|,
literal|"id"
argument_list|,
literal|"1"
argument_list|)
expr_stmt|;
name|checkAnnotatedField
argument_list|(
name|clazz
argument_list|,
literal|"s"
argument_list|,
literal|"Lorg/apache/bcel/data/SimpleAnnotation;"
argument_list|,
literal|"id"
argument_list|,
literal|"2"
argument_list|)
expr_stmt|;
block|}
comment|/** 	 * Check field AnnotationEntrys (de)serialize ok. 	 */
specifier|public
name|void
name|testFieldAnnotationEntrysReadWrite
parameter_list|()
throws|throws
name|ClassNotFoundException
throws|,
name|IOException
block|{
name|JavaClass
name|clazz
init|=
name|getTestClass
argument_list|(
literal|"org.apache.bcel.data.AnnotatedFields"
argument_list|)
decl_stmt|;
name|checkAnnotatedField
argument_list|(
name|clazz
argument_list|,
literal|"i"
argument_list|,
literal|"Lorg/apache/bcel/data/SimpleAnnotation;"
argument_list|,
literal|"id"
argument_list|,
literal|"1"
argument_list|)
expr_stmt|;
name|checkAnnotatedField
argument_list|(
name|clazz
argument_list|,
literal|"s"
argument_list|,
literal|"Lorg/apache/bcel/data/SimpleAnnotation;"
argument_list|,
literal|"id"
argument_list|,
literal|"2"
argument_list|)
expr_stmt|;
comment|// Write it out
name|File
name|tfile
init|=
name|createTestdataFile
argument_list|(
literal|"AnnotatedFields.class"
argument_list|)
decl_stmt|;
name|clazz
operator|.
name|dump
argument_list|(
name|tfile
argument_list|)
expr_stmt|;
name|SyntheticRepository
name|repos2
init|=
name|createRepos
argument_list|(
literal|"."
argument_list|)
decl_stmt|;
name|repos2
operator|.
name|loadClass
argument_list|(
literal|"AnnotatedFields"
argument_list|)
expr_stmt|;
name|checkAnnotatedField
argument_list|(
name|clazz
argument_list|,
literal|"i"
argument_list|,
literal|"Lorg/apache/bcel/data/SimpleAnnotation;"
argument_list|,
literal|"id"
argument_list|,
literal|"1"
argument_list|)
expr_stmt|;
name|checkAnnotatedField
argument_list|(
name|clazz
argument_list|,
literal|"s"
argument_list|,
literal|"Lorg/apache/bcel/data/SimpleAnnotation;"
argument_list|,
literal|"id"
argument_list|,
literal|"2"
argument_list|)
expr_stmt|;
name|assertTrue
argument_list|(
name|tfile
operator|.
name|delete
argument_list|()
argument_list|)
expr_stmt|;
block|}
comment|/** 	 * Check we can load in a class, modify its field AnnotationEntrys, save it, 	 * reload it and everything is correct. 	 */
specifier|public
name|void
name|testFieldAnnotationModification
parameter_list|()
throws|throws
name|ClassNotFoundException
block|{
name|boolean
name|dbg
init|=
literal|false
decl_stmt|;
name|JavaClass
name|clazz
init|=
name|getTestClass
argument_list|(
literal|"org.apache.bcel.data.AnnotatedFields"
argument_list|)
decl_stmt|;
name|ClassGen
name|clg
init|=
operator|new
name|ClassGen
argument_list|(
name|clazz
argument_list|)
decl_stmt|;
name|Field
name|f
init|=
name|clg
operator|.
name|getFields
argument_list|()
index|[
literal|0
index|]
decl_stmt|;
if|if
condition|(
name|dbg
condition|)
block|{
name|System
operator|.
name|err
operator|.
name|println
argument_list|(
literal|"Field in freshly constructed class is: "
operator|+
name|f
argument_list|)
expr_stmt|;
block|}
if|if
condition|(
name|dbg
condition|)
block|{
name|System
operator|.
name|err
operator|.
name|println
argument_list|(
literal|"AnnotationEntrys on field are: "
operator|+
name|dumpAnnotationEntries
argument_list|(
name|f
operator|.
name|getAnnotationEntries
argument_list|()
argument_list|)
argument_list|)
expr_stmt|;
block|}
name|AnnotationEntryGen
name|fruitBasedAnnotationEntry
init|=
name|createFruitAnnotationEntry
argument_list|(
name|clg
operator|.
name|getConstantPool
argument_list|()
argument_list|,
literal|"Tomato"
argument_list|,
literal|false
argument_list|)
decl_stmt|;
name|FieldGen
name|fg
init|=
operator|new
name|FieldGen
argument_list|(
name|f
argument_list|,
name|clg
operator|.
name|getConstantPool
argument_list|()
argument_list|)
decl_stmt|;
if|if
condition|(
name|dbg
condition|)
block|{
name|System
operator|.
name|err
operator|.
name|println
argument_list|(
literal|"Adding AnnotationEntry to the field"
argument_list|)
expr_stmt|;
block|}
name|fg
operator|.
name|addAnnotationEntry
argument_list|(
name|fruitBasedAnnotationEntry
argument_list|)
expr_stmt|;
if|if
condition|(
name|dbg
condition|)
block|{
name|System
operator|.
name|err
operator|.
name|println
argument_list|(
literal|"FieldGen (mutable field) is "
operator|+
name|fg
argument_list|)
expr_stmt|;
block|}
if|if
condition|(
name|dbg
condition|)
block|{
name|System
operator|.
name|err
operator|.
name|println
argument_list|(
literal|"with AnnotationEntrys: "
operator|+
name|dumpAnnotationEntries
argument_list|(
name|fg
operator|.
name|getAnnotationEntries
argument_list|()
argument_list|)
argument_list|)
expr_stmt|;
block|}
if|if
condition|(
name|dbg
condition|)
block|{
name|System
operator|.
name|err
operator|.
name|println
argument_list|(
literal|"Replacing original field with new field that has extra AnnotationEntry"
argument_list|)
expr_stmt|;
block|}
name|clg
operator|.
name|removeField
argument_list|(
name|f
argument_list|)
expr_stmt|;
name|clg
operator|.
name|addField
argument_list|(
name|fg
operator|.
name|getField
argument_list|()
argument_list|)
expr_stmt|;
name|f
operator|=
name|clg
operator|.
name|getFields
argument_list|()
index|[
literal|1
index|]
expr_stmt|;
comment|// there are two fields in the class, removing
comment|// and readding has changed the order
comment|// so this time index [1] is the 'int i' field
if|if
condition|(
name|dbg
condition|)
block|{
name|System
operator|.
name|err
operator|.
name|println
argument_list|(
literal|"Field now looks like this: "
operator|+
name|f
argument_list|)
expr_stmt|;
block|}
if|if
condition|(
name|dbg
condition|)
block|{
name|System
operator|.
name|err
operator|.
name|println
argument_list|(
literal|"With AnnotationEntrys: "
operator|+
name|dumpAnnotationEntries
argument_list|(
name|f
operator|.
name|getAnnotationEntries
argument_list|()
argument_list|)
argument_list|)
expr_stmt|;
block|}
name|assertTrue
argument_list|(
literal|"Should be 2 AnnotationEntrys on this field, but there are "
operator|+
name|f
operator|.
name|getAnnotationEntries
argument_list|()
operator|.
name|length
argument_list|,
name|f
operator|.
name|getAnnotationEntries
argument_list|()
operator|.
name|length
operator|==
literal|2
argument_list|)
expr_stmt|;
block|}
comment|// helper methods
specifier|public
name|void
name|checkAnnotatedField
parameter_list|(
name|JavaClass
name|clazz
parameter_list|,
name|String
name|fieldname
parameter_list|,
name|String
name|AnnotationEntryName
parameter_list|,
name|String
name|AnnotationEntryElementName
parameter_list|,
name|String
name|AnnotationEntryElementValue
parameter_list|)
block|{
name|Field
index|[]
name|fields
init|=
name|clazz
operator|.
name|getFields
argument_list|()
decl_stmt|;
for|for
control|(
name|Field
name|f
range|:
name|fields
control|)
block|{
name|AnnotationEntry
index|[]
name|fieldAnnotationEntrys
init|=
name|f
operator|.
name|getAnnotationEntries
argument_list|()
decl_stmt|;
if|if
condition|(
name|f
operator|.
name|getName
argument_list|()
operator|.
name|equals
argument_list|(
name|fieldname
argument_list|)
condition|)
block|{
name|checkAnnotationEntry
argument_list|(
name|fieldAnnotationEntrys
index|[
literal|0
index|]
argument_list|,
name|AnnotationEntryName
argument_list|,
name|AnnotationEntryElementName
argument_list|,
name|AnnotationEntryElementValue
argument_list|)
expr_stmt|;
block|}
block|}
block|}
specifier|private
name|void
name|checkAnnotationEntry
parameter_list|(
name|AnnotationEntry
name|a
parameter_list|,
name|String
name|name
parameter_list|,
name|String
name|elementname
parameter_list|,
name|String
name|elementvalue
parameter_list|)
block|{
name|assertTrue
argument_list|(
literal|"Expected AnnotationEntry to have name "
operator|+
name|name
operator|+
literal|" but it had name "
operator|+
name|a
operator|.
name|getAnnotationType
argument_list|()
argument_list|,
name|a
operator|.
name|getAnnotationType
argument_list|()
operator|.
name|equals
argument_list|(
name|name
argument_list|)
argument_list|)
expr_stmt|;
name|assertTrue
argument_list|(
literal|"Expected AnnotationEntry to have one element but it had "
operator|+
name|a
operator|.
name|getElementValuePairs
argument_list|()
operator|.
name|length
argument_list|,
name|a
operator|.
name|getElementValuePairs
argument_list|()
operator|.
name|length
operator|==
literal|1
argument_list|)
expr_stmt|;
name|ElementValuePair
name|envp
init|=
name|a
operator|.
name|getElementValuePairs
argument_list|()
index|[
literal|0
index|]
decl_stmt|;
name|assertTrue
argument_list|(
literal|"Expected element name "
operator|+
name|elementname
operator|+
literal|" but was "
operator|+
name|envp
operator|.
name|getNameString
argument_list|()
argument_list|,
name|elementname
operator|.
name|equals
argument_list|(
name|envp
operator|.
name|getNameString
argument_list|()
argument_list|)
argument_list|)
expr_stmt|;
name|assertTrue
argument_list|(
literal|"Expected element value "
operator|+
name|elementvalue
operator|+
literal|" but was "
operator|+
name|envp
operator|.
name|getValue
argument_list|()
operator|.
name|stringifyValue
argument_list|()
argument_list|,
name|elementvalue
operator|.
name|equals
argument_list|(
name|envp
operator|.
name|getValue
argument_list|()
operator|.
name|stringifyValue
argument_list|()
argument_list|)
argument_list|)
expr_stmt|;
block|}
comment|// helper methods
specifier|public
name|void
name|checkValue
parameter_list|(
name|AnnotationEntry
name|a
parameter_list|,
name|String
name|name
parameter_list|,
name|String
name|tostring
parameter_list|)
block|{
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
name|getElementValuePairs
argument_list|()
operator|.
name|length
condition|;
name|i
operator|++
control|)
block|{
name|ElementValuePair
name|element
init|=
name|a
operator|.
name|getElementValuePairs
argument_list|()
index|[
name|i
index|]
decl_stmt|;
if|if
condition|(
name|element
operator|.
name|getNameString
argument_list|()
operator|.
name|equals
argument_list|(
name|name
argument_list|)
condition|)
block|{
if|if
condition|(
operator|!
name|element
operator|.
name|getValue
argument_list|()
operator|.
name|stringifyValue
argument_list|()
operator|.
name|equals
argument_list|(
name|tostring
argument_list|)
condition|)
block|{
name|fail
argument_list|(
literal|"Expected element "
operator|+
name|name
operator|+
literal|" to have value "
operator|+
name|tostring
operator|+
literal|" but it had value "
operator|+
name|element
operator|.
name|getValue
argument_list|()
operator|.
name|stringifyValue
argument_list|()
argument_list|)
expr_stmt|;
block|}
return|return;
block|}
block|}
name|fail
argument_list|(
literal|"Didnt find named element "
operator|+
name|name
argument_list|)
expr_stmt|;
block|}
block|}
end_class

end_unit

