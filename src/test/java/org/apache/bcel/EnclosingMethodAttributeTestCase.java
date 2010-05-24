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
name|ConstantPool
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
name|EnclosingMethod
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
name|util
operator|.
name|SyntheticRepository
import|;
end_import

begin_class
specifier|public
class|class
name|EnclosingMethodAttributeTestCase
extends|extends
name|AbstractTestCase
block|{
comment|/** 	 * Verify for an inner class declared inside the 'main' method that the 	 * enclosing method attribute is set correctly. 	 */
specifier|public
name|void
name|testCheckMethodLevelNamedInnerClass
parameter_list|()
throws|throws
name|ClassNotFoundException
block|{
name|JavaClass
name|clazz
init|=
name|getTestClass
argument_list|(
literal|"org.apache.bcel.data.AttributeTestClassEM01$1S"
argument_list|)
decl_stmt|;
name|ConstantPool
name|pool
init|=
name|clazz
operator|.
name|getConstantPool
argument_list|()
decl_stmt|;
name|Attribute
index|[]
name|encMethodAttrs
init|=
name|findAttribute
argument_list|(
literal|"EnclosingMethod"
argument_list|,
name|clazz
argument_list|)
decl_stmt|;
name|assertTrue
argument_list|(
literal|"Expected 1 EnclosingMethod attribute but found "
operator|+
name|encMethodAttrs
operator|.
name|length
argument_list|,
name|encMethodAttrs
operator|.
name|length
operator|==
literal|1
argument_list|)
expr_stmt|;
name|EnclosingMethod
name|em
init|=
operator|(
name|EnclosingMethod
operator|)
name|encMethodAttrs
index|[
literal|0
index|]
decl_stmt|;
name|String
name|enclosingClassName
init|=
name|em
operator|.
name|getEnclosingClass
argument_list|()
operator|.
name|getBytes
argument_list|(
name|pool
argument_list|)
decl_stmt|;
name|String
name|enclosingMethodName
init|=
name|em
operator|.
name|getEnclosingMethod
argument_list|()
operator|.
name|getName
argument_list|(
name|pool
argument_list|)
decl_stmt|;
name|assertTrue
argument_list|(
literal|"Expected class name to be 'org/apache/bcel/data/AttributeTestClassEM01' but was "
operator|+
name|enclosingClassName
argument_list|,
name|enclosingClassName
operator|.
name|equals
argument_list|(
literal|"org/apache/bcel/data/AttributeTestClassEM01"
argument_list|)
argument_list|)
expr_stmt|;
name|assertTrue
argument_list|(
literal|"Expected method name to be 'main' but was "
operator|+
name|enclosingMethodName
argument_list|,
name|enclosingMethodName
operator|.
name|equals
argument_list|(
literal|"main"
argument_list|)
argument_list|)
expr_stmt|;
block|}
comment|/** 	 * Verify for an inner class declared at the type level that the 	 * EnclosingMethod attribute is set correctly (i.e. to a null value) 	 */
specifier|public
name|void
name|testCheckClassLevelNamedInnerClass
parameter_list|()
throws|throws
name|ClassNotFoundException
block|{
name|JavaClass
name|clazz
init|=
name|getTestClass
argument_list|(
literal|"org.apache.bcel.data.AttributeTestClassEM02$1"
argument_list|)
decl_stmt|;
name|ConstantPool
name|pool
init|=
name|clazz
operator|.
name|getConstantPool
argument_list|()
decl_stmt|;
name|Attribute
index|[]
name|encMethodAttrs
init|=
name|findAttribute
argument_list|(
literal|"EnclosingMethod"
argument_list|,
name|clazz
argument_list|)
decl_stmt|;
name|assertTrue
argument_list|(
literal|"Expected 1 EnclosingMethod attribute but found "
operator|+
name|encMethodAttrs
operator|.
name|length
argument_list|,
name|encMethodAttrs
operator|.
name|length
operator|==
literal|1
argument_list|)
expr_stmt|;
name|EnclosingMethod
name|em
init|=
operator|(
name|EnclosingMethod
operator|)
name|encMethodAttrs
index|[
literal|0
index|]
decl_stmt|;
name|String
name|enclosingClassName
init|=
name|em
operator|.
name|getEnclosingClass
argument_list|()
operator|.
name|getBytes
argument_list|(
name|pool
argument_list|)
decl_stmt|;
name|assertTrue
argument_list|(
literal|"The class is not within a method, so method_index should be null, but it is "
operator|+
name|em
operator|.
name|getEnclosingMethodIndex
argument_list|()
argument_list|,
name|em
operator|.
name|getEnclosingMethodIndex
argument_list|()
operator|==
literal|0
argument_list|)
expr_stmt|;
name|assertTrue
argument_list|(
literal|"Expected class name to be 'org/apache/bcel/data/AttributeTestClassEM02' but was "
operator|+
name|enclosingClassName
argument_list|,
name|enclosingClassName
operator|.
name|equals
argument_list|(
literal|"org/apache/bcel/data/AttributeTestClassEM02"
argument_list|)
argument_list|)
expr_stmt|;
block|}
comment|/** 	 * Check that we can save and load the attribute correctly. 	 */
specifier|public
name|void
name|testAttributeSerializtion
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
literal|"org.apache.bcel.data.AttributeTestClassEM02$1"
argument_list|)
decl_stmt|;
name|ConstantPool
name|pool
init|=
name|clazz
operator|.
name|getConstantPool
argument_list|()
decl_stmt|;
name|Attribute
index|[]
name|encMethodAttrs
init|=
name|findAttribute
argument_list|(
literal|"EnclosingMethod"
argument_list|,
name|clazz
argument_list|)
decl_stmt|;
name|assertTrue
argument_list|(
literal|"Expected 1 EnclosingMethod attribute but found "
operator|+
name|encMethodAttrs
operator|.
name|length
argument_list|,
name|encMethodAttrs
operator|.
name|length
operator|==
literal|1
argument_list|)
expr_stmt|;
comment|// Write it out
name|File
name|tfile
init|=
name|createTestdataFile
argument_list|(
literal|"AttributeTestClassEM02$1.class"
argument_list|)
decl_stmt|;
name|clazz
operator|.
name|dump
argument_list|(
name|tfile
argument_list|)
expr_stmt|;
comment|// Read in the new version and check it is OK
name|SyntheticRepository
name|repos2
init|=
name|createRepos
argument_list|(
literal|"."
argument_list|)
decl_stmt|;
name|JavaClass
name|clazz2
init|=
name|repos2
operator|.
name|loadClass
argument_list|(
literal|"AttributeTestClassEM02$1"
argument_list|)
decl_stmt|;
name|EnclosingMethod
name|em
init|=
operator|(
name|EnclosingMethod
operator|)
name|encMethodAttrs
index|[
literal|0
index|]
decl_stmt|;
name|String
name|enclosingClassName
init|=
name|em
operator|.
name|getEnclosingClass
argument_list|()
operator|.
name|getBytes
argument_list|(
name|pool
argument_list|)
decl_stmt|;
name|assertTrue
argument_list|(
literal|"The class is not within a method, so method_index should be null, but it is "
operator|+
name|em
operator|.
name|getEnclosingMethodIndex
argument_list|()
argument_list|,
name|em
operator|.
name|getEnclosingMethodIndex
argument_list|()
operator|==
literal|0
argument_list|)
expr_stmt|;
name|assertTrue
argument_list|(
literal|"Expected class name to be 'org/apache/bcel/data/AttributeTestClassEM02' but was "
operator|+
name|enclosingClassName
argument_list|,
name|enclosingClassName
operator|.
name|equals
argument_list|(
literal|"org/apache/bcel/data/AttributeTestClassEM02"
argument_list|)
argument_list|)
expr_stmt|;
name|tfile
operator|.
name|deleteOnExit
argument_list|()
expr_stmt|;
block|}
block|}
end_class

end_unit

