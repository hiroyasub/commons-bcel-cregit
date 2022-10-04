begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
begin_comment
comment|/*  * Licensed to the Apache Software Foundation (ASF) under one or more  * contributor license agreements.  See the NOTICE file distributed with  * this work for additional information regarding copyright ownership.  * The ASF licenses this file to You under the Apache License, Version 2.0  * (the "License"); you may not use this file except in compliance with  * the License.  You may obtain a copy of the License at  *  *   http://www.apache.org/licenses/LICENSE-2.0  *  * Unless required by applicable law or agreed to in writing, software  * distributed under the License is distributed on an "AS IS" BASIS,  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  * See the License for the specific language governing permissions and  * limitations under the License.  *  */
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
import|import static
name|org
operator|.
name|junit
operator|.
name|jupiter
operator|.
name|api
operator|.
name|Assertions
operator|.
name|assertEquals
import|;
end_import

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
name|java
operator|.
name|io
operator|.
name|UncheckedIOException
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
name|Method
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
name|ClassPath
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
specifier|abstract
class|class
name|AbstractTestCase
block|{
specifier|private
specifier|static
specifier|final
name|boolean
name|verbose
init|=
literal|false
decl_stmt|;
specifier|protected
specifier|static
specifier|final
name|String
name|PACKAGE_BASE_NAME
init|=
name|AbstractTestCase
operator|.
name|class
operator|.
name|getPackage
argument_list|()
operator|.
name|getName
argument_list|()
decl_stmt|;
comment|// Location of test data
specifier|protected
specifier|static
specifier|final
name|File
name|TESTDATA
init|=
operator|new
name|File
argument_list|(
literal|"target"
argument_list|,
literal|"testdata"
argument_list|)
decl_stmt|;
comment|// package base name in signature format, i.e. with '/' separators instead of '.'
specifier|protected
specifier|static
specifier|final
name|String
name|PACKAGE_BASE_SIG
init|=
name|PACKAGE_BASE_NAME
operator|.
name|replace
argument_list|(
literal|'.'
argument_list|,
literal|'/'
argument_list|)
decl_stmt|;
specifier|public
name|AnnotationEntryGen
name|createFruitAnnotationEntry
parameter_list|(
specifier|final
name|ConstantPoolGen
name|cp
parameter_list|,
specifier|final
name|String
name|aFruit
parameter_list|,
specifier|final
name|boolean
name|visibility
parameter_list|)
block|{
specifier|final
name|SimpleElementValueGen
name|evg
init|=
operator|new
name|SimpleElementValueGen
argument_list|(
name|ElementValueGen
operator|.
name|STRING
argument_list|,
name|cp
argument_list|,
name|aFruit
argument_list|)
decl_stmt|;
specifier|final
name|ElementValuePairGen
name|nvGen
init|=
operator|new
name|ElementValuePairGen
argument_list|(
literal|"fruit"
argument_list|,
name|evg
argument_list|,
name|cp
argument_list|)
decl_stmt|;
specifier|final
name|ObjectType
name|t
init|=
operator|new
name|ObjectType
argument_list|(
literal|"SimpleStringAnnotation"
argument_list|)
decl_stmt|;
specifier|final
name|List
argument_list|<
name|ElementValuePairGen
argument_list|>
name|elements
init|=
operator|new
name|ArrayList
argument_list|<>
argument_list|()
decl_stmt|;
name|elements
operator|.
name|add
argument_list|(
name|nvGen
argument_list|)
expr_stmt|;
return|return
operator|new
name|AnnotationEntryGen
argument_list|(
name|t
argument_list|,
name|elements
argument_list|,
name|visibility
argument_list|,
name|cp
argument_list|)
return|;
block|}
specifier|public
name|SyntheticRepository
name|createRepos
parameter_list|(
specifier|final
name|String
name|cpentry
parameter_list|)
block|{
try|try
init|(
name|ClassPath
name|cp
init|=
operator|new
name|ClassPath
argument_list|(
literal|"target"
operator|+
name|File
operator|.
name|separator
operator|+
literal|"testdata"
operator|+
name|File
operator|.
name|separator
operator|+
name|cpentry
operator|+
name|File
operator|.
name|separator
argument_list|)
init|)
block|{
return|return
name|SyntheticRepository
operator|.
name|getInstance
argument_list|(
name|cp
argument_list|)
return|;
block|}
catch|catch
parameter_list|(
specifier|final
name|IOException
name|e
parameter_list|)
block|{
throw|throw
operator|new
name|UncheckedIOException
argument_list|(
name|e
argument_list|)
throw|;
block|}
block|}
comment|/**      * @param name      * @return Path to file under the TESTDATA directory      */
specifier|protected
name|File
name|createTestdataFile
parameter_list|(
specifier|final
name|String
name|name
parameter_list|)
block|{
return|return
operator|new
name|File
argument_list|(
name|TESTDATA
argument_list|,
name|name
argument_list|)
return|;
block|}
specifier|protected
name|String
name|dumpAnnotationEntries
parameter_list|(
specifier|final
name|AnnotationEntry
index|[]
name|as
parameter_list|)
block|{
specifier|final
name|StringBuilder
name|result
init|=
operator|new
name|StringBuilder
argument_list|()
decl_stmt|;
name|result
operator|.
name|append
argument_list|(
literal|"["
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
name|as
operator|.
name|length
condition|;
name|i
operator|++
control|)
block|{
specifier|final
name|AnnotationEntry
name|annotation
init|=
name|as
index|[
name|i
index|]
decl_stmt|;
name|result
operator|.
name|append
argument_list|(
name|annotation
operator|.
name|toShortString
argument_list|()
argument_list|)
expr_stmt|;
if|if
condition|(
name|i
operator|+
literal|1
operator|<
name|as
operator|.
name|length
condition|)
block|{
name|result
operator|.
name|append
argument_list|(
literal|","
argument_list|)
expr_stmt|;
block|}
block|}
name|result
operator|.
name|append
argument_list|(
literal|"]"
argument_list|)
expr_stmt|;
return|return
name|result
operator|.
name|toString
argument_list|()
return|;
block|}
specifier|protected
name|String
name|dumpAnnotationEntries
parameter_list|(
specifier|final
name|AnnotationEntryGen
index|[]
name|as
parameter_list|)
block|{
specifier|final
name|StringBuilder
name|result
init|=
operator|new
name|StringBuilder
argument_list|()
decl_stmt|;
name|result
operator|.
name|append
argument_list|(
literal|"["
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
name|as
operator|.
name|length
condition|;
name|i
operator|++
control|)
block|{
specifier|final
name|AnnotationEntryGen
name|annotation
init|=
name|as
index|[
name|i
index|]
decl_stmt|;
name|result
operator|.
name|append
argument_list|(
name|annotation
operator|.
name|toShortString
argument_list|()
argument_list|)
expr_stmt|;
if|if
condition|(
name|i
operator|+
literal|1
operator|<
name|as
operator|.
name|length
condition|)
block|{
name|result
operator|.
name|append
argument_list|(
literal|","
argument_list|)
expr_stmt|;
block|}
block|}
name|result
operator|.
name|append
argument_list|(
literal|"]"
argument_list|)
expr_stmt|;
return|return
name|result
operator|.
name|toString
argument_list|()
return|;
block|}
specifier|protected
name|Attribute
name|findAttribute
parameter_list|(
specifier|final
name|String
name|name
parameter_list|,
specifier|final
name|Attribute
index|[]
name|all
parameter_list|)
block|{
specifier|final
name|List
argument_list|<
name|Attribute
argument_list|>
name|chosenAttrsList
init|=
operator|new
name|ArrayList
argument_list|<>
argument_list|()
decl_stmt|;
for|for
control|(
specifier|final
name|Attribute
name|element
range|:
name|all
control|)
block|{
if|if
condition|(
name|verbose
condition|)
block|{
name|System
operator|.
name|err
operator|.
name|println
argument_list|(
literal|"Attribute: "
operator|+
name|element
operator|.
name|getName
argument_list|()
argument_list|)
expr_stmt|;
block|}
if|if
condition|(
name|element
operator|.
name|getName
argument_list|()
operator|.
name|equals
argument_list|(
name|name
argument_list|)
condition|)
block|{
name|chosenAttrsList
operator|.
name|add
argument_list|(
name|element
argument_list|)
expr_stmt|;
block|}
block|}
name|assertEquals
argument_list|(
literal|1
argument_list|,
name|chosenAttrsList
operator|.
name|size
argument_list|()
argument_list|,
literal|"Wrong number of matches"
argument_list|)
expr_stmt|;
return|return
name|chosenAttrsList
operator|.
name|get
argument_list|(
literal|0
argument_list|)
return|;
block|}
specifier|protected
name|Attribute
index|[]
name|findAttribute
parameter_list|(
specifier|final
name|String
name|name
parameter_list|,
specifier|final
name|JavaClass
name|clazz
parameter_list|)
block|{
specifier|final
name|Attribute
index|[]
name|all
init|=
name|clazz
operator|.
name|getAttributes
argument_list|()
decl_stmt|;
specifier|final
name|List
argument_list|<
name|Attribute
argument_list|>
name|chosenAttrsList
init|=
operator|new
name|ArrayList
argument_list|<>
argument_list|()
decl_stmt|;
for|for
control|(
specifier|final
name|Attribute
name|element
range|:
name|all
control|)
block|{
if|if
condition|(
name|verbose
condition|)
block|{
name|System
operator|.
name|err
operator|.
name|println
argument_list|(
literal|"Attribute: "
operator|+
name|element
operator|.
name|getName
argument_list|()
argument_list|)
expr_stmt|;
block|}
if|if
condition|(
name|element
operator|.
name|getName
argument_list|()
operator|.
name|equals
argument_list|(
name|name
argument_list|)
condition|)
block|{
name|chosenAttrsList
operator|.
name|add
argument_list|(
name|element
argument_list|)
expr_stmt|;
block|}
block|}
return|return
name|chosenAttrsList
operator|.
name|toArray
argument_list|(
name|Attribute
operator|.
name|EMPTY_ARRAY
argument_list|)
return|;
block|}
specifier|protected
name|Method
name|getMethod
parameter_list|(
specifier|final
name|JavaClass
name|cl
parameter_list|,
specifier|final
name|String
name|methodname
parameter_list|)
block|{
specifier|final
name|Method
index|[]
name|methods
init|=
name|cl
operator|.
name|getMethods
argument_list|()
decl_stmt|;
for|for
control|(
specifier|final
name|Method
name|m
range|:
name|methods
control|)
block|{
if|if
condition|(
name|m
operator|.
name|getName
argument_list|()
operator|.
name|equals
argument_list|(
name|methodname
argument_list|)
condition|)
block|{
return|return
name|m
return|;
block|}
block|}
return|return
literal|null
return|;
block|}
specifier|protected
name|JavaClass
name|getTestClass
parameter_list|(
specifier|final
name|String
name|name
parameter_list|)
throws|throws
name|ClassNotFoundException
block|{
return|return
name|SyntheticRepository
operator|.
name|getInstance
argument_list|()
operator|.
name|loadClass
argument_list|(
name|name
argument_list|)
return|;
block|}
comment|/**      * Delete a file under the TESTDATA directory      *      * @param name      * @return      */
specifier|protected
name|boolean
name|wipe
parameter_list|(
specifier|final
name|String
name|name
parameter_list|)
block|{
return|return
operator|new
name|File
argument_list|(
name|TESTDATA
argument_list|,
name|name
argument_list|)
operator|.
name|delete
argument_list|()
return|;
block|}
comment|/**      * Delete a directory and file under the TESTDATA directory      *      * @param dir      * @param name      * @return true if the file was deleted      */
specifier|protected
name|boolean
name|wipe
parameter_list|(
specifier|final
name|String
name|dir
parameter_list|,
specifier|final
name|String
name|name
parameter_list|)
block|{
comment|// The parameter is relative to the TESTDATA dir
specifier|final
name|boolean
name|b
init|=
name|wipe
argument_list|(
name|dir
operator|+
name|File
operator|.
name|separator
operator|+
name|name
argument_list|)
decl_stmt|;
specifier|final
name|File
name|testDir
init|=
operator|new
name|File
argument_list|(
name|TESTDATA
argument_list|,
name|dir
argument_list|)
decl_stmt|;
specifier|final
name|String
index|[]
name|files
init|=
name|testDir
operator|.
name|list
argument_list|()
decl_stmt|;
if|if
condition|(
name|files
operator|==
literal|null
operator|||
name|files
operator|.
name|length
operator|==
literal|0
condition|)
block|{
if|if
condition|(
operator|!
name|testDir
operator|.
name|delete
argument_list|()
condition|)
block|{
name|System
operator|.
name|err
operator|.
name|println
argument_list|(
literal|"Failed to remove: "
operator|+
name|testDir
argument_list|)
expr_stmt|;
block|}
block|}
else|else
block|{
name|System
operator|.
name|err
operator|.
name|println
argument_list|(
literal|"Non-empty directory: "
operator|+
name|testDir
argument_list|)
expr_stmt|;
block|}
return|return
name|b
return|;
block|}
block|}
end_class

end_unit

