begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
begin_comment
comment|/*  * Licensed to the Apache Software Foundation (ASF) under one or more  * contributor license agreements.  See the NOTICE file distributed with  * this work for additional information regarding copyright ownership.  * The ASF licenses this file to You under the Apache License, Version 2.0  * (the "License"); you may not use this file except in compliance with  * the License.  You may obtain a copy of the License at  *  *      http://www.apache.org/licenses/LICENSE-2.0  *  *  Unless required by applicable law or agreed to in writing, software  *  distributed under the License is distributed on an "AS IS" BASIS,  *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  *  See the License for the specific language governing permissions and  *  limitations under the License.  *  */
end_comment

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
name|ClassParser
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
name|Constant
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
name|JavaClass
import|;
end_import

begin_comment
comment|/**  * Patch all Utf8 constants in the given class file<em>file</em>.class  * and save the result in _<em>file</em>.class.  *  * Usage: patch<oldstring><newstring> files  *  */
end_comment

begin_class
specifier|public
class|class
name|patchclass
block|{
specifier|public
specifier|static
name|void
name|main
parameter_list|(
specifier|final
name|String
index|[]
name|argv
parameter_list|)
throws|throws
name|Exception
block|{
specifier|final
name|String
index|[]
name|file_name
init|=
operator|new
name|String
index|[
name|argv
operator|.
name|length
index|]
decl_stmt|;
name|int
name|files
init|=
literal|0
decl_stmt|;
if|if
condition|(
name|argv
operator|.
name|length
operator|<
literal|3
condition|)
block|{
name|System
operator|.
name|err
operator|.
name|println
argument_list|(
literal|"Usage: patch<oldstring><newstring> file1.class ..."
argument_list|)
expr_stmt|;
name|System
operator|.
name|exit
argument_list|(
operator|-
literal|1
argument_list|)
expr_stmt|;
block|}
for|for
control|(
name|int
name|i
init|=
literal|2
init|;
name|i
operator|<
name|argv
operator|.
name|length
condition|;
name|i
operator|++
control|)
block|{
name|file_name
index|[
name|files
operator|++
index|]
operator|=
name|argv
index|[
name|i
index|]
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
name|files
condition|;
name|i
operator|++
control|)
block|{
specifier|final
name|ClassParser
name|parser
init|=
operator|new
name|ClassParser
argument_list|(
name|file_name
index|[
name|i
index|]
argument_list|)
decl_stmt|;
specifier|final
name|JavaClass
name|java_class
init|=
name|parser
operator|.
name|parse
argument_list|()
decl_stmt|;
name|patchIt
argument_list|(
name|argv
index|[
literal|0
index|]
argument_list|,
name|argv
index|[
literal|1
index|]
argument_list|,
name|java_class
operator|.
name|getConstantPool
argument_list|()
operator|.
name|getConstantPool
argument_list|()
argument_list|)
expr_stmt|;
comment|// Dump the changed class to a new file
name|java_class
operator|.
name|dump
argument_list|(
literal|"_"
operator|+
name|file_name
index|[
name|i
index|]
argument_list|)
expr_stmt|;
name|System
operator|.
name|out
operator|.
name|println
argument_list|(
literal|"Results saved in: _"
operator|+
name|file_name
index|[
name|i
index|]
argument_list|)
expr_stmt|;
block|}
block|}
comment|/*      * Replace all occurences of string "<em>old</em>" with      * "<em>replacement</em>" in all Utf8 constants      */
specifier|private
specifier|static
name|void
name|patchIt
parameter_list|(
specifier|final
name|String
name|old
parameter_list|,
specifier|final
name|String
name|replacement
parameter_list|,
specifier|final
name|Constant
index|[]
name|constant_pool
parameter_list|)
block|{
name|ConstantUtf8
name|c
decl_stmt|;
name|String
name|str
decl_stmt|;
name|int
name|index
decl_stmt|,
name|old_index
decl_stmt|;
name|StringBuffer
name|buf
decl_stmt|;
comment|// Loop through constant pool
for|for
control|(
name|short
name|i
init|=
literal|0
init|;
name|i
operator|<
name|constant_pool
operator|.
name|length
condition|;
name|i
operator|++
control|)
block|{
if|if
condition|(
name|constant_pool
index|[
name|i
index|]
operator|instanceof
name|ConstantUtf8
condition|)
block|{
comment|// Utf8 string found
try|try
block|{
name|c
operator|=
operator|(
name|ConstantUtf8
operator|)
name|constant_pool
index|[
name|i
index|]
expr_stmt|;
comment|// Get the string
name|str
operator|=
name|c
operator|.
name|getBytes
argument_list|()
expr_stmt|;
if|if
condition|(
operator|(
name|index
operator|=
name|str
operator|.
name|indexOf
argument_list|(
name|old
argument_list|)
operator|)
operator|!=
operator|-
literal|1
condition|)
block|{
comment|// `old' found in str
name|buf
operator|=
operator|new
name|StringBuffer
argument_list|()
expr_stmt|;
comment|// target buffer
name|old_index
operator|=
literal|0
expr_stmt|;
comment|// String start offset
comment|// While we have something to replace
while|while
condition|(
operator|(
name|index
operator|=
name|str
operator|.
name|indexOf
argument_list|(
name|old
argument_list|,
name|old_index
argument_list|)
operator|)
operator|!=
operator|-
literal|1
condition|)
block|{
name|buf
operator|.
name|append
argument_list|(
name|str
operator|.
name|substring
argument_list|(
name|old_index
argument_list|,
name|index
argument_list|)
argument_list|)
expr_stmt|;
comment|// append prefix
name|buf
operator|.
name|append
argument_list|(
name|replacement
argument_list|)
expr_stmt|;
comment|// append `replacement'
name|old_index
operator|=
name|index
operator|+
name|old
operator|.
name|length
argument_list|()
expr_stmt|;
comment|// Skip `old'.length chars
block|}
name|buf
operator|.
name|append
argument_list|(
name|str
operator|.
name|substring
argument_list|(
name|old_index
argument_list|)
argument_list|)
expr_stmt|;
comment|// append rest of string
name|str
operator|=
name|buf
operator|.
name|toString
argument_list|()
expr_stmt|;
comment|// Finally push the new string back to the constant pool
name|c
operator|=
operator|new
name|ConstantUtf8
argument_list|(
name|str
argument_list|)
expr_stmt|;
name|constant_pool
index|[
name|i
index|]
operator|=
name|c
expr_stmt|;
block|}
block|}
catch|catch
parameter_list|(
specifier|final
name|StringIndexOutOfBoundsException
name|e
parameter_list|)
block|{
comment|// Should not occur
name|System
operator|.
name|err
operator|.
name|println
argument_list|(
name|e
argument_list|)
expr_stmt|;
block|}
block|}
block|}
block|}
block|}
end_class

end_unit

