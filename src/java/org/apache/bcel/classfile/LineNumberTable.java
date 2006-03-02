begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
begin_comment
comment|/*  * Copyright  2000-2004 The Apache Software Foundation  *  *  Licensed under the Apache License, Version 2.0 (the "License");   *  you may not use this file except in compliance with the License.  *  You may obtain a copy of the License at  *  *      http://www.apache.org/licenses/LICENSE-2.0  *  *  Unless required by applicable law or agreed to in writing, software  *  distributed under the License is distributed on an "AS IS" BASIS,  *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  *  See the License for the specific language governing permissions and  *  limitations under the License.   *  */
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
name|org
operator|.
name|apache
operator|.
name|bcel
operator|.
name|Constants
import|;
end_import

begin_comment
comment|/**  * This class represents a table of line numbers for debugging  * purposes. This attribute is used by the<em>Code</em> attribute. It  * contains pairs of PCs and line numbers.  *  * @version $Id$  * @author<A HREF="mailto:m.dahm@gmx.de">M. Dahm</A>  * @see     Code  * @see LineNumber  */
end_comment

begin_class
specifier|public
specifier|final
class|class
name|LineNumberTable
extends|extends
name|Attribute
block|{
specifier|private
name|int
name|line_number_table_length
decl_stmt|;
specifier|private
name|LineNumber
index|[]
name|line_number_table
decl_stmt|;
comment|// Table of line/numbers pairs
comment|/*    * Initialize from another object. Note that both objects use the same    * references (shallow copy). Use copy() for a physical copy.    */
specifier|public
name|LineNumberTable
parameter_list|(
name|LineNumberTable
name|c
parameter_list|)
block|{
name|this
argument_list|(
name|c
operator|.
name|getNameIndex
argument_list|()
argument_list|,
name|c
operator|.
name|getLength
argument_list|()
argument_list|,
name|c
operator|.
name|getLineNumberTable
argument_list|()
argument_list|,
name|c
operator|.
name|getConstantPool
argument_list|()
argument_list|)
expr_stmt|;
block|}
comment|/*    * @param name_index Index of name    * @param length Content length in bytes    * @param line_number_table Table of line/numbers pairs    * @param constant_pool Array of constants    */
specifier|public
name|LineNumberTable
parameter_list|(
name|int
name|name_index
parameter_list|,
name|int
name|length
parameter_list|,
name|LineNumber
index|[]
name|line_number_table
parameter_list|,
name|ConstantPool
name|constant_pool
parameter_list|)
block|{
name|super
argument_list|(
name|Constants
operator|.
name|ATTR_LINE_NUMBER_TABLE
argument_list|,
name|name_index
argument_list|,
name|length
argument_list|,
name|constant_pool
argument_list|)
expr_stmt|;
name|setLineNumberTable
argument_list|(
name|line_number_table
argument_list|)
expr_stmt|;
block|}
comment|/**    * Construct object from file stream.    * @param name_index Index of name    * @param length Content length in bytes    * @param file Input stream    * @param constant_pool Array of constants    * @throws IOException    */
name|LineNumberTable
parameter_list|(
name|int
name|name_index
parameter_list|,
name|int
name|length
parameter_list|,
name|DataInputStream
name|file
parameter_list|,
name|ConstantPool
name|constant_pool
parameter_list|)
throws|throws
name|IOException
block|{
name|this
argument_list|(
name|name_index
argument_list|,
name|length
argument_list|,
operator|(
name|LineNumber
index|[]
operator|)
literal|null
argument_list|,
name|constant_pool
argument_list|)
expr_stmt|;
name|line_number_table_length
operator|=
operator|(
name|file
operator|.
name|readUnsignedShort
argument_list|()
operator|)
expr_stmt|;
name|line_number_table
operator|=
operator|new
name|LineNumber
index|[
name|line_number_table_length
index|]
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
name|line_number_table_length
condition|;
name|i
operator|++
control|)
name|line_number_table
index|[
name|i
index|]
operator|=
operator|new
name|LineNumber
argument_list|(
name|file
argument_list|)
expr_stmt|;
block|}
comment|/**    * Called by objects that are traversing the nodes of the tree implicitely    * defined by the contents of a Java class. I.e., the hierarchy of methods,    * fields, attributes, etc. spawns a tree of objects.    *    * @param v Visitor object    */
specifier|public
name|void
name|accept
parameter_list|(
name|Visitor
name|v
parameter_list|)
block|{
name|v
operator|.
name|visitLineNumberTable
argument_list|(
name|this
argument_list|)
expr_stmt|;
block|}
comment|/**    * Dump line number table attribute to file stream in binary format.    *    * @param file Output file stream    * @throws IOException    */
specifier|public
specifier|final
name|void
name|dump
parameter_list|(
name|DataOutputStream
name|file
parameter_list|)
throws|throws
name|IOException
block|{
name|super
operator|.
name|dump
argument_list|(
name|file
argument_list|)
expr_stmt|;
name|file
operator|.
name|writeShort
argument_list|(
name|line_number_table_length
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
name|line_number_table_length
condition|;
name|i
operator|++
control|)
name|line_number_table
index|[
name|i
index|]
operator|.
name|dump
argument_list|(
name|file
argument_list|)
expr_stmt|;
block|}
comment|/**    * @return Array of (pc offset, line number) pairs.    */
specifier|public
specifier|final
name|LineNumber
index|[]
name|getLineNumberTable
parameter_list|()
block|{
return|return
name|line_number_table
return|;
block|}
comment|/**    * @param line_number_table the line number entries for this table    */
specifier|public
specifier|final
name|void
name|setLineNumberTable
parameter_list|(
name|LineNumber
index|[]
name|line_number_table
parameter_list|)
block|{
name|this
operator|.
name|line_number_table
operator|=
name|line_number_table
expr_stmt|;
name|line_number_table_length
operator|=
operator|(
name|line_number_table
operator|==
literal|null
operator|)
condition|?
literal|0
else|:
name|line_number_table
operator|.
name|length
expr_stmt|;
block|}
comment|/**    * @return String representation.    */
specifier|public
specifier|final
name|String
name|toString
parameter_list|()
block|{
name|StringBuffer
name|buf
init|=
operator|new
name|StringBuffer
argument_list|()
decl_stmt|;
name|StringBuffer
name|line
init|=
operator|new
name|StringBuffer
argument_list|()
decl_stmt|;
name|String
name|newLine
init|=
name|System
operator|.
name|getProperty
argument_list|(
literal|"line.separator"
argument_list|,
literal|"\n"
argument_list|)
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
name|line_number_table_length
condition|;
name|i
operator|++
control|)
block|{
name|line
operator|.
name|append
argument_list|(
name|line_number_table
index|[
name|i
index|]
operator|.
name|toString
argument_list|()
argument_list|)
expr_stmt|;
if|if
condition|(
name|i
operator|<
name|line_number_table_length
operator|-
literal|1
condition|)
block|{
name|line
operator|.
name|append
argument_list|(
literal|", "
argument_list|)
expr_stmt|;
block|}
if|if
condition|(
name|line
operator|.
name|length
argument_list|()
operator|>
literal|72
condition|)
block|{
name|line
operator|.
name|append
argument_list|(
name|newLine
argument_list|)
expr_stmt|;
name|buf
operator|.
name|append
argument_list|(
name|line
operator|.
name|toString
argument_list|()
argument_list|)
expr_stmt|;
name|line
operator|.
name|setLength
argument_list|(
literal|0
argument_list|)
expr_stmt|;
block|}
block|}
name|buf
operator|.
name|append
argument_list|(
name|line
argument_list|)
expr_stmt|;
return|return
name|buf
operator|.
name|toString
argument_list|()
return|;
block|}
comment|/**    * Map byte code positions to source code lines.    *    * @param pos byte code offset    * @return corresponding line in source code    */
specifier|public
name|int
name|getSourceLine
parameter_list|(
name|int
name|pos
parameter_list|)
block|{
name|int
name|l
init|=
literal|0
decl_stmt|,
name|r
init|=
name|line_number_table_length
operator|-
literal|1
decl_stmt|;
if|if
condition|(
name|r
operator|<
literal|0
condition|)
comment|// array is empty
return|return
operator|-
literal|1
return|;
name|int
name|min_index
init|=
operator|-
literal|1
decl_stmt|,
name|min
init|=
operator|-
literal|1
decl_stmt|;
comment|/* Do a binary search since the array is ordered.      */
do|do
block|{
name|int
name|i
init|=
operator|(
name|l
operator|+
name|r
operator|)
operator|/
literal|2
decl_stmt|;
name|int
name|j
init|=
name|line_number_table
index|[
name|i
index|]
operator|.
name|getStartPC
argument_list|()
decl_stmt|;
if|if
condition|(
name|j
operator|==
name|pos
condition|)
return|return
name|line_number_table
index|[
name|i
index|]
operator|.
name|getLineNumber
argument_list|()
return|;
if|else if
condition|(
name|pos
operator|<
name|j
condition|)
comment|// else constrain search area
name|r
operator|=
name|i
operator|-
literal|1
expr_stmt|;
else|else
comment|// pos> j
name|l
operator|=
name|i
operator|+
literal|1
expr_stmt|;
comment|/* If exact match can't be found (which is the most common case)        * return the line number that corresponds to the greatest index less        * than pos.        */
if|if
condition|(
name|j
argument_list|<
name|pos
operator|&&
name|j
argument_list|>
name|min
condition|)
block|{
name|min
operator|=
name|j
expr_stmt|;
name|min_index
operator|=
name|i
expr_stmt|;
block|}
block|}
do|while
condition|(
name|l
operator|<=
name|r
condition|)
do|;
comment|/* It's possible that we did not find any valid entry for the bytecode      * offset we were looking for.      */
if|if
condition|(
name|min_index
operator|<
literal|0
condition|)
return|return
operator|-
literal|1
return|;
return|return
name|line_number_table
index|[
name|min_index
index|]
operator|.
name|getLineNumber
argument_list|()
return|;
block|}
comment|/**    * @return deep copy of this attribute    */
specifier|public
name|Attribute
name|copy
parameter_list|(
name|ConstantPool
name|_constant_pool
parameter_list|)
block|{
name|LineNumberTable
name|c
init|=
operator|(
name|LineNumberTable
operator|)
name|clone
argument_list|()
decl_stmt|;
name|c
operator|.
name|line_number_table
operator|=
operator|new
name|LineNumber
index|[
name|line_number_table_length
index|]
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
name|line_number_table_length
condition|;
name|i
operator|++
control|)
name|c
operator|.
name|line_number_table
index|[
name|i
index|]
operator|=
name|line_number_table
index|[
name|i
index|]
operator|.
name|copy
argument_list|()
expr_stmt|;
name|c
operator|.
name|constant_pool
operator|=
name|_constant_pool
expr_stmt|;
return|return
name|c
return|;
block|}
specifier|public
specifier|final
name|int
name|getTableLength
parameter_list|()
block|{
return|return
name|line_number_table_length
return|;
block|}
block|}
end_class

end_unit

