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
name|Constants
import|;
end_import

begin_comment
comment|/**   * This class represents a chunk of Java byte code contained in a  * method. It is instantiated by the  *<em>Attribute.readAttribute()</em> method. A<em>Code</em>  * attribute contains informations about operand stack, local  * variables, byte code and the exceptions handled within this  * method.  *  * This attribute has attributes itself, namely<em>LineNumberTable</em> which  * is used for debugging purposes and<em>LocalVariableTable</em> which   * contains information about the local variables.  *  * @version $Id$  * @see     Attribute  * @see     CodeException  * @see     LineNumberTable  * @see LocalVariableTable  */
end_comment

begin_class
specifier|public
specifier|final
class|class
name|Code
extends|extends
name|Attribute
block|{
specifier|private
name|int
name|max_stack
decl_stmt|;
comment|// Maximum size of stack used by this method  // TODO this could be made final (setter is not used)
specifier|private
name|int
name|max_locals
decl_stmt|;
comment|// Number of local variables  // TODO this could be made final (setter is not used)
specifier|private
name|byte
index|[]
name|code
decl_stmt|;
comment|// Actual byte code
specifier|private
name|CodeException
index|[]
name|exception_table
decl_stmt|;
comment|// Table of handled exceptions
specifier|private
name|Attribute
index|[]
name|attributes
decl_stmt|;
comment|// or LocalVariable
comment|/**      * Initialize from another object. Note that both objects use the same      * references (shallow copy). Use copy() for a physical copy.      */
specifier|public
name|Code
parameter_list|(
name|Code
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
name|getMaxStack
argument_list|()
argument_list|,
name|c
operator|.
name|getMaxLocals
argument_list|()
argument_list|,
name|c
operator|.
name|getCode
argument_list|()
argument_list|,
name|c
operator|.
name|getExceptionTable
argument_list|()
argument_list|,
name|c
operator|.
name|getAttributes
argument_list|()
argument_list|,
name|c
operator|.
name|getConstantPool
argument_list|()
argument_list|)
expr_stmt|;
block|}
comment|/**      * @param name_index Index pointing to the name<em>Code</em>      * @param length Content length in bytes      * @param file Input stream      * @param constant_pool Array of constants      */
name|Code
parameter_list|(
name|int
name|name_index
parameter_list|,
name|int
name|length
parameter_list|,
name|DataInput
name|file
parameter_list|,
name|ConstantPool
name|constant_pool
parameter_list|)
throws|throws
name|IOException
block|{
comment|// Initialize with some default values which will be overwritten later
name|this
argument_list|(
name|name_index
argument_list|,
name|length
argument_list|,
name|file
operator|.
name|readUnsignedShort
argument_list|()
argument_list|,
name|file
operator|.
name|readUnsignedShort
argument_list|()
argument_list|,
operator|(
name|byte
index|[]
operator|)
literal|null
argument_list|,
operator|(
name|CodeException
index|[]
operator|)
literal|null
argument_list|,
operator|(
name|Attribute
index|[]
operator|)
literal|null
argument_list|,
name|constant_pool
argument_list|)
expr_stmt|;
name|int
name|code_length
init|=
name|file
operator|.
name|readInt
argument_list|()
decl_stmt|;
name|code
operator|=
operator|new
name|byte
index|[
name|code_length
index|]
expr_stmt|;
comment|// Read byte code
name|file
operator|.
name|readFully
argument_list|(
name|code
argument_list|)
expr_stmt|;
comment|/* Read exception table that contains all regions where an exception          * handler is active, i.e., a try { ... } catch() block.          */
name|int
name|exception_table_length
init|=
name|file
operator|.
name|readUnsignedShort
argument_list|()
decl_stmt|;
name|exception_table
operator|=
operator|new
name|CodeException
index|[
name|exception_table_length
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
name|exception_table_length
condition|;
name|i
operator|++
control|)
block|{
name|exception_table
index|[
name|i
index|]
operator|=
operator|new
name|CodeException
argument_list|(
name|file
argument_list|)
expr_stmt|;
block|}
comment|/* Read all attributes, currently `LineNumberTable' and          * `LocalVariableTable'          */
name|int
name|attributes_count
init|=
name|file
operator|.
name|readUnsignedShort
argument_list|()
decl_stmt|;
name|attributes
operator|=
operator|new
name|Attribute
index|[
name|attributes_count
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
name|attributes_count
condition|;
name|i
operator|++
control|)
block|{
name|attributes
index|[
name|i
index|]
operator|=
name|Attribute
operator|.
name|readAttribute
argument_list|(
name|file
argument_list|,
name|constant_pool
argument_list|)
expr_stmt|;
block|}
comment|/* Adjust length, because of setAttributes in this(), s.b.  length          * is incorrect, because it didn't take the internal attributes          * into account yet! Very subtle bug, fixed in 3.1.1.          */
name|super
operator|.
name|setLength
argument_list|(
name|length
argument_list|)
expr_stmt|;
block|}
comment|/**      * @param name_index Index pointing to the name<em>Code</em>      * @param length Content length in bytes      * @param max_stack Maximum size of stack      * @param max_locals Number of local variables      * @param code Actual byte code      * @param exception_table Table of handled exceptions      * @param attributes Attributes of code: LineNumber or LocalVariable      * @param constant_pool Array of constants      */
specifier|public
name|Code
parameter_list|(
name|int
name|name_index
parameter_list|,
name|int
name|length
parameter_list|,
name|int
name|max_stack
parameter_list|,
name|int
name|max_locals
parameter_list|,
name|byte
index|[]
name|code
parameter_list|,
name|CodeException
index|[]
name|exception_table
parameter_list|,
name|Attribute
index|[]
name|attributes
parameter_list|,
name|ConstantPool
name|constant_pool
parameter_list|)
block|{
name|super
argument_list|(
name|Constants
operator|.
name|ATTR_CODE
argument_list|,
name|name_index
argument_list|,
name|length
argument_list|,
name|constant_pool
argument_list|)
expr_stmt|;
name|this
operator|.
name|max_stack
operator|=
name|max_stack
expr_stmt|;
name|this
operator|.
name|max_locals
operator|=
name|max_locals
expr_stmt|;
name|this
operator|.
name|code
operator|=
name|code
operator|!=
literal|null
condition|?
name|code
else|:
operator|new
name|byte
index|[
literal|0
index|]
expr_stmt|;
name|this
operator|.
name|exception_table
operator|=
name|exception_table
operator|!=
literal|null
condition|?
name|exception_table
else|:
operator|new
name|CodeException
index|[
literal|0
index|]
expr_stmt|;
name|this
operator|.
name|attributes
operator|=
name|attributes
operator|!=
literal|null
condition|?
name|attributes
else|:
operator|new
name|Attribute
index|[
literal|0
index|]
expr_stmt|;
name|super
operator|.
name|setLength
argument_list|(
name|calculateLength
argument_list|()
argument_list|)
expr_stmt|;
comment|// Adjust length
block|}
comment|/**      * Called by objects that are traversing the nodes of the tree implicitely      * defined by the contents of a Java class. I.e., the hierarchy of methods,      * fields, attributes, etc. spawns a tree of objects.      *      * @param v Visitor object      */
annotation|@
name|Override
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
name|visitCode
argument_list|(
name|this
argument_list|)
expr_stmt|;
block|}
comment|/**      * Dump code attribute to file stream in binary format.      *      * @param file Output file stream      * @throws IOException      */
annotation|@
name|Override
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
name|max_stack
argument_list|)
expr_stmt|;
name|file
operator|.
name|writeShort
argument_list|(
name|max_locals
argument_list|)
expr_stmt|;
name|file
operator|.
name|writeInt
argument_list|(
name|code
operator|.
name|length
argument_list|)
expr_stmt|;
name|file
operator|.
name|write
argument_list|(
name|code
argument_list|,
literal|0
argument_list|,
name|code
operator|.
name|length
argument_list|)
expr_stmt|;
name|file
operator|.
name|writeShort
argument_list|(
name|exception_table
operator|.
name|length
argument_list|)
expr_stmt|;
for|for
control|(
name|CodeException
name|exception
range|:
name|exception_table
control|)
block|{
name|exception
operator|.
name|dump
argument_list|(
name|file
argument_list|)
expr_stmt|;
block|}
name|file
operator|.
name|writeShort
argument_list|(
name|attributes
operator|.
name|length
argument_list|)
expr_stmt|;
for|for
control|(
name|Attribute
name|attribute
range|:
name|attributes
control|)
block|{
name|attribute
operator|.
name|dump
argument_list|(
name|file
argument_list|)
expr_stmt|;
block|}
block|}
comment|/**      * @return Collection of code attributes.      * @see Attribute      */
specifier|public
specifier|final
name|Attribute
index|[]
name|getAttributes
parameter_list|()
block|{
return|return
name|attributes
return|;
block|}
comment|/**      * @return LineNumberTable of Code, if it has one      */
specifier|public
name|LineNumberTable
name|getLineNumberTable
parameter_list|()
block|{
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
name|LineNumberTable
condition|)
block|{
return|return
operator|(
name|LineNumberTable
operator|)
name|attribute
return|;
block|}
block|}
return|return
literal|null
return|;
block|}
comment|/**      * @return LocalVariableTable of Code, if it has one      */
specifier|public
name|LocalVariableTable
name|getLocalVariableTable
parameter_list|()
block|{
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
name|LocalVariableTable
condition|)
block|{
return|return
operator|(
name|LocalVariableTable
operator|)
name|attribute
return|;
block|}
block|}
return|return
literal|null
return|;
block|}
comment|/**      * @return Actual byte code of the method.      */
specifier|public
specifier|final
name|byte
index|[]
name|getCode
parameter_list|()
block|{
return|return
name|code
return|;
block|}
comment|/**      * @return Table of handled exceptions.      * @see CodeException      */
specifier|public
specifier|final
name|CodeException
index|[]
name|getExceptionTable
parameter_list|()
block|{
return|return
name|exception_table
return|;
block|}
comment|/**      * @return Number of local variables.      */
specifier|public
specifier|final
name|int
name|getMaxLocals
parameter_list|()
block|{
return|return
name|max_locals
return|;
block|}
comment|/**      * @return Maximum size of stack used by this method.      */
specifier|public
specifier|final
name|int
name|getMaxStack
parameter_list|()
block|{
return|return
name|max_stack
return|;
block|}
comment|/**      * @return the internal length of this code attribute (minus the first 6 bytes)       * and excluding all its attributes      */
specifier|private
name|int
name|getInternalLength
parameter_list|()
block|{
return|return
literal|2
comment|/*max_stack*/
operator|+
literal|2
comment|/*max_locals*/
operator|+
literal|4
comment|/*code length*/
operator|+
name|code
operator|.
name|length
comment|/*byte-code*/
operator|+
literal|2
comment|/*exception-table length*/
operator|+
literal|8
operator|*
operator|(
name|exception_table
operator|==
literal|null
condition|?
literal|0
else|:
name|exception_table
operator|.
name|length
operator|)
comment|/* exception table */
operator|+
literal|2
comment|/* attributes count */
return|;
block|}
comment|/**      * @return the full size of this code attribute, minus its first 6 bytes,      * including the size of all its contained attributes      */
specifier|private
name|int
name|calculateLength
parameter_list|()
block|{
name|int
name|len
init|=
literal|0
decl_stmt|;
if|if
condition|(
name|attributes
operator|!=
literal|null
condition|)
block|{
for|for
control|(
name|Attribute
name|attribute
range|:
name|attributes
control|)
block|{
name|len
operator|+=
name|attribute
operator|.
name|getLength
argument_list|()
operator|+
literal|6
comment|/*attribute header size*/
expr_stmt|;
block|}
block|}
return|return
name|len
operator|+
name|getInternalLength
argument_list|()
return|;
block|}
comment|/**      * @param attributes the attributes to set for this Code      */
specifier|public
specifier|final
name|void
name|setAttributes
parameter_list|(
name|Attribute
index|[]
name|attributes
parameter_list|)
block|{
name|this
operator|.
name|attributes
operator|=
name|attributes
operator|!=
literal|null
condition|?
name|attributes
else|:
operator|new
name|Attribute
index|[
literal|0
index|]
expr_stmt|;
name|super
operator|.
name|setLength
argument_list|(
name|calculateLength
argument_list|()
argument_list|)
expr_stmt|;
comment|// Adjust length
block|}
comment|/**      * @param code byte code      */
specifier|public
specifier|final
name|void
name|setCode
parameter_list|(
name|byte
index|[]
name|code
parameter_list|)
block|{
name|this
operator|.
name|code
operator|=
name|code
operator|!=
literal|null
condition|?
name|code
else|:
operator|new
name|byte
index|[
literal|0
index|]
expr_stmt|;
name|super
operator|.
name|setLength
argument_list|(
name|calculateLength
argument_list|()
argument_list|)
expr_stmt|;
comment|// Adjust length
block|}
comment|/**      * @param exception_table exception table      */
specifier|public
specifier|final
name|void
name|setExceptionTable
parameter_list|(
name|CodeException
index|[]
name|exception_table
parameter_list|)
block|{
name|this
operator|.
name|exception_table
operator|=
name|exception_table
operator|!=
literal|null
condition|?
name|exception_table
else|:
operator|new
name|CodeException
index|[
literal|0
index|]
expr_stmt|;
name|super
operator|.
name|setLength
argument_list|(
name|calculateLength
argument_list|()
argument_list|)
expr_stmt|;
comment|// Adjust length
block|}
comment|/**      * @param max_locals maximum number of local variables      */
specifier|public
specifier|final
name|void
name|setMaxLocals
parameter_list|(
name|int
name|max_locals
parameter_list|)
block|{
name|this
operator|.
name|max_locals
operator|=
name|max_locals
expr_stmt|;
block|}
comment|/**      * @param max_stack maximum stack size      */
specifier|public
specifier|final
name|void
name|setMaxStack
parameter_list|(
name|int
name|max_stack
parameter_list|)
block|{
name|this
operator|.
name|max_stack
operator|=
name|max_stack
expr_stmt|;
block|}
comment|/**      * @return String representation of code chunk.      */
specifier|public
specifier|final
name|String
name|toString
parameter_list|(
name|boolean
name|verbose
parameter_list|)
block|{
name|StringBuilder
name|buf
init|=
operator|new
name|StringBuilder
argument_list|(
literal|100
argument_list|)
decl_stmt|;
name|buf
operator|.
name|append
argument_list|(
literal|"Code(max_stack = "
argument_list|)
operator|.
name|append
argument_list|(
name|max_stack
argument_list|)
operator|.
name|append
argument_list|(
literal|", max_locals = "
argument_list|)
operator|.
name|append
argument_list|(
name|max_locals
argument_list|)
operator|.
name|append
argument_list|(
literal|", code_length = "
argument_list|)
operator|.
name|append
argument_list|(
name|code
operator|.
name|length
argument_list|)
operator|.
name|append
argument_list|(
literal|")\n"
argument_list|)
operator|.
name|append
argument_list|(
name|Utility
operator|.
name|codeToString
argument_list|(
name|code
argument_list|,
name|super
operator|.
name|getConstantPool
argument_list|()
argument_list|,
literal|0
argument_list|,
operator|-
literal|1
argument_list|,
name|verbose
argument_list|)
argument_list|)
expr_stmt|;
if|if
condition|(
name|exception_table
operator|.
name|length
operator|>
literal|0
condition|)
block|{
name|buf
operator|.
name|append
argument_list|(
literal|"\nException handler(s) = \n"
argument_list|)
operator|.
name|append
argument_list|(
literal|"From\tTo\tHandler\tType\n"
argument_list|)
expr_stmt|;
for|for
control|(
name|CodeException
name|exception
range|:
name|exception_table
control|)
block|{
name|buf
operator|.
name|append
argument_list|(
name|exception
operator|.
name|toString
argument_list|(
name|super
operator|.
name|getConstantPool
argument_list|()
argument_list|,
name|verbose
argument_list|)
argument_list|)
operator|.
name|append
argument_list|(
literal|"\n"
argument_list|)
expr_stmt|;
block|}
block|}
if|if
condition|(
name|attributes
operator|.
name|length
operator|>
literal|0
condition|)
block|{
name|buf
operator|.
name|append
argument_list|(
literal|"\nAttribute(s) = "
argument_list|)
expr_stmt|;
for|for
control|(
name|Attribute
name|attribute
range|:
name|attributes
control|)
block|{
name|buf
operator|.
name|append
argument_list|(
literal|"\n"
argument_list|)
operator|.
name|append
argument_list|(
name|attribute
operator|.
name|toString
argument_list|()
argument_list|)
expr_stmt|;
block|}
block|}
return|return
name|buf
operator|.
name|toString
argument_list|()
return|;
block|}
comment|/**      * @return String representation of code chunk.      */
annotation|@
name|Override
specifier|public
specifier|final
name|String
name|toString
parameter_list|()
block|{
return|return
name|toString
argument_list|(
literal|true
argument_list|)
return|;
block|}
comment|/**      * @return deep copy of this attribute      *       * @param _constant_pool the constant pool to duplicate      */
annotation|@
name|Override
specifier|public
name|Attribute
name|copy
parameter_list|(
name|ConstantPool
name|_constant_pool
parameter_list|)
block|{
name|Code
name|c
init|=
operator|(
name|Code
operator|)
name|clone
argument_list|()
decl_stmt|;
if|if
condition|(
name|code
operator|!=
literal|null
condition|)
block|{
name|c
operator|.
name|code
operator|=
operator|new
name|byte
index|[
name|code
operator|.
name|length
index|]
expr_stmt|;
name|System
operator|.
name|arraycopy
argument_list|(
name|code
argument_list|,
literal|0
argument_list|,
name|c
operator|.
name|code
argument_list|,
literal|0
argument_list|,
name|code
operator|.
name|length
argument_list|)
expr_stmt|;
block|}
name|c
operator|.
name|setConstantPool
argument_list|(
name|_constant_pool
argument_list|)
expr_stmt|;
name|c
operator|.
name|exception_table
operator|=
operator|new
name|CodeException
index|[
name|exception_table
operator|.
name|length
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
name|exception_table
operator|.
name|length
condition|;
name|i
operator|++
control|)
block|{
name|c
operator|.
name|exception_table
index|[
name|i
index|]
operator|=
name|exception_table
index|[
name|i
index|]
operator|.
name|copy
argument_list|()
expr_stmt|;
block|}
name|c
operator|.
name|attributes
operator|=
operator|new
name|Attribute
index|[
name|attributes
operator|.
name|length
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
name|attributes
operator|.
name|length
condition|;
name|i
operator|++
control|)
block|{
name|c
operator|.
name|attributes
index|[
name|i
index|]
operator|=
name|attributes
index|[
name|i
index|]
operator|.
name|copy
argument_list|(
name|_constant_pool
argument_list|)
expr_stmt|;
block|}
return|return
name|c
return|;
block|}
block|}
end_class

end_unit

