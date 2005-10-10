begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
begin_comment
comment|/*  * Copyright  2000-2004 The Apache Software Foundation  *  *  Licensed under the Apache License, Version 2.0 (the "License");  *  you may not use this file except in compliance with the License.  *  You may obtain a copy of the License at  *  *      http://www.apache.org/licenses/LICENSE-2.0  *  *  Unless required by applicable law or agreed to in writing, software  *  distributed under the License is distributed on an "AS IS" BASIS,  *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  *  See the License for the specific language governing permissions and  *  limitations under the License.  *  */
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
name|java
operator|.
name|io
operator|.
name|Serializable
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
comment|/**  * This class represents the constant pool, i.e., a table of constants, of  * a parsed classfile. It may contain null references, due to the JVM  * specification that skips an entry after an 8-byte constant (double,  * long) entry.  Those interested in generating constant pools  * programatically should see<a href="../generic/ConstantPoolGen.html">  * ConstantPoolGen</a>.   * @version $Id$  * @see     Constant  * @see     org.apache.bcel.generic.ConstantPoolGen  * @author<A HREF="mailto:m.dahm@gmx.de">M. Dahm</A>  */
end_comment

begin_class
specifier|public
class|class
name|ConstantPool
implements|implements
name|Cloneable
implements|,
name|Node
implements|,
name|Serializable
block|{
specifier|private
name|int
name|constant_pool_count
decl_stmt|;
specifier|private
name|Constant
index|[]
name|constant_pool
decl_stmt|;
comment|/**    * @param constant_pool Array of constants    */
specifier|public
name|ConstantPool
parameter_list|(
name|Constant
index|[]
name|constant_pool
parameter_list|)
block|{
name|setConstantPool
argument_list|(
name|constant_pool
argument_list|)
expr_stmt|;
block|}
comment|/**    * Read constants from given file stream.    *    * @param file Input stream    * @throws IOException    * @throws ClassFormatException    */
name|ConstantPool
parameter_list|(
name|DataInputStream
name|file
parameter_list|)
throws|throws
name|IOException
throws|,
name|ClassFormatException
block|{
name|byte
name|tag
decl_stmt|;
name|constant_pool_count
operator|=
name|file
operator|.
name|readUnsignedShort
argument_list|()
expr_stmt|;
name|constant_pool
operator|=
operator|new
name|Constant
index|[
name|constant_pool_count
index|]
expr_stmt|;
comment|/* constant_pool[0] is unused by the compiler and may be used freely      * by the implementation.      */
for|for
control|(
name|int
name|i
init|=
literal|1
init|;
name|i
operator|<
name|constant_pool_count
condition|;
name|i
operator|++
control|)
block|{
name|constant_pool
index|[
name|i
index|]
operator|=
name|Constant
operator|.
name|readConstant
argument_list|(
name|file
argument_list|)
expr_stmt|;
comment|/* Quote from the JVM specification:        * "All eight byte constants take up two spots in the constant pool.        * If this is the n'th byte in the constant pool, then the next item        * will be numbered n+2"        *         * Thus we have to increment the index counter.        */
name|tag
operator|=
name|constant_pool
index|[
name|i
index|]
operator|.
name|getTag
argument_list|()
expr_stmt|;
if|if
condition|(
operator|(
name|tag
operator|==
name|Constants
operator|.
name|CONSTANT_Double
operator|)
operator|||
operator|(
name|tag
operator|==
name|Constants
operator|.
name|CONSTANT_Long
operator|)
condition|)
name|i
operator|++
expr_stmt|;
block|}
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
name|visitConstantPool
argument_list|(
name|this
argument_list|)
expr_stmt|;
block|}
comment|/**    * Resolve constant to a string representation.    *    * @param  c Constant to be printed    * @return String representation    */
specifier|public
name|String
name|constantToString
parameter_list|(
name|Constant
name|c
parameter_list|)
throws|throws
name|ClassFormatException
block|{
name|String
name|str
decl_stmt|;
name|int
name|i
decl_stmt|;
name|byte
name|tag
init|=
name|c
operator|.
name|getTag
argument_list|()
decl_stmt|;
switch|switch
condition|(
name|tag
condition|)
block|{
case|case
name|Constants
operator|.
name|CONSTANT_Class
case|:
name|i
operator|=
operator|(
operator|(
name|ConstantClass
operator|)
name|c
operator|)
operator|.
name|getNameIndex
argument_list|()
expr_stmt|;
name|c
operator|=
name|getConstant
argument_list|(
name|i
argument_list|,
name|Constants
operator|.
name|CONSTANT_Utf8
argument_list|)
expr_stmt|;
name|str
operator|=
name|Utility
operator|.
name|compactClassName
argument_list|(
operator|(
operator|(
name|ConstantUtf8
operator|)
name|c
operator|)
operator|.
name|getBytes
argument_list|()
argument_list|,
literal|false
argument_list|)
expr_stmt|;
break|break;
case|case
name|Constants
operator|.
name|CONSTANT_String
case|:
name|i
operator|=
operator|(
operator|(
name|ConstantString
operator|)
name|c
operator|)
operator|.
name|getStringIndex
argument_list|()
expr_stmt|;
name|c
operator|=
name|getConstant
argument_list|(
name|i
argument_list|,
name|Constants
operator|.
name|CONSTANT_Utf8
argument_list|)
expr_stmt|;
name|str
operator|=
literal|"\""
operator|+
name|escape
argument_list|(
operator|(
operator|(
name|ConstantUtf8
operator|)
name|c
operator|)
operator|.
name|getBytes
argument_list|()
argument_list|)
operator|+
literal|"\""
expr_stmt|;
break|break;
case|case
name|Constants
operator|.
name|CONSTANT_Utf8
case|:
name|str
operator|=
operator|(
operator|(
name|ConstantUtf8
operator|)
name|c
operator|)
operator|.
name|getBytes
argument_list|()
expr_stmt|;
break|break;
case|case
name|Constants
operator|.
name|CONSTANT_Double
case|:
name|str
operator|=
literal|""
operator|+
operator|(
operator|(
name|ConstantDouble
operator|)
name|c
operator|)
operator|.
name|getBytes
argument_list|()
expr_stmt|;
break|break;
case|case
name|Constants
operator|.
name|CONSTANT_Float
case|:
name|str
operator|=
literal|""
operator|+
operator|(
operator|(
name|ConstantFloat
operator|)
name|c
operator|)
operator|.
name|getBytes
argument_list|()
expr_stmt|;
break|break;
case|case
name|Constants
operator|.
name|CONSTANT_Long
case|:
name|str
operator|=
literal|""
operator|+
operator|(
operator|(
name|ConstantLong
operator|)
name|c
operator|)
operator|.
name|getBytes
argument_list|()
expr_stmt|;
break|break;
case|case
name|Constants
operator|.
name|CONSTANT_Integer
case|:
name|str
operator|=
literal|""
operator|+
operator|(
operator|(
name|ConstantInteger
operator|)
name|c
operator|)
operator|.
name|getBytes
argument_list|()
expr_stmt|;
break|break;
case|case
name|Constants
operator|.
name|CONSTANT_NameAndType
case|:
name|str
operator|=
operator|(
name|constantToString
argument_list|(
operator|(
operator|(
name|ConstantNameAndType
operator|)
name|c
operator|)
operator|.
name|getNameIndex
argument_list|()
argument_list|,
name|Constants
operator|.
name|CONSTANT_Utf8
argument_list|)
operator|+
literal|" "
operator|+
name|constantToString
argument_list|(
operator|(
operator|(
name|ConstantNameAndType
operator|)
name|c
operator|)
operator|.
name|getSignatureIndex
argument_list|()
argument_list|,
name|Constants
operator|.
name|CONSTANT_Utf8
argument_list|)
operator|)
expr_stmt|;
break|break;
case|case
name|Constants
operator|.
name|CONSTANT_InterfaceMethodref
case|:
case|case
name|Constants
operator|.
name|CONSTANT_Methodref
case|:
case|case
name|Constants
operator|.
name|CONSTANT_Fieldref
case|:
name|str
operator|=
operator|(
name|constantToString
argument_list|(
operator|(
operator|(
name|ConstantCP
operator|)
name|c
operator|)
operator|.
name|getClassIndex
argument_list|()
argument_list|,
name|Constants
operator|.
name|CONSTANT_Class
argument_list|)
operator|+
literal|"."
operator|+
name|constantToString
argument_list|(
operator|(
operator|(
name|ConstantCP
operator|)
name|c
operator|)
operator|.
name|getNameAndTypeIndex
argument_list|()
argument_list|,
name|Constants
operator|.
name|CONSTANT_NameAndType
argument_list|)
operator|)
expr_stmt|;
break|break;
default|default:
comment|// Never reached
throw|throw
operator|new
name|RuntimeException
argument_list|(
literal|"Unknown constant type "
operator|+
name|tag
argument_list|)
throw|;
block|}
return|return
name|str
return|;
block|}
specifier|private
specifier|static
specifier|final
name|String
name|escape
parameter_list|(
name|String
name|str
parameter_list|)
block|{
name|int
name|len
init|=
name|str
operator|.
name|length
argument_list|()
decl_stmt|;
name|StringBuffer
name|buf
init|=
operator|new
name|StringBuffer
argument_list|(
name|len
operator|+
literal|5
argument_list|)
decl_stmt|;
name|char
index|[]
name|ch
init|=
name|str
operator|.
name|toCharArray
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
name|len
condition|;
name|i
operator|++
control|)
block|{
switch|switch
condition|(
name|ch
index|[
name|i
index|]
condition|)
block|{
case|case
literal|'\n'
case|:
name|buf
operator|.
name|append
argument_list|(
literal|"\\n"
argument_list|)
expr_stmt|;
break|break;
case|case
literal|'\r'
case|:
name|buf
operator|.
name|append
argument_list|(
literal|"\\r"
argument_list|)
expr_stmt|;
break|break;
case|case
literal|'\t'
case|:
name|buf
operator|.
name|append
argument_list|(
literal|"\\t"
argument_list|)
expr_stmt|;
break|break;
case|case
literal|'\b'
case|:
name|buf
operator|.
name|append
argument_list|(
literal|"\\b"
argument_list|)
expr_stmt|;
break|break;
case|case
literal|'"'
case|:
name|buf
operator|.
name|append
argument_list|(
literal|"\\\""
argument_list|)
expr_stmt|;
break|break;
default|default:
name|buf
operator|.
name|append
argument_list|(
name|ch
index|[
name|i
index|]
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
comment|/**    * Retrieve constant at `index' from constant pool and resolve it to    * a string representation.    *    * @param  index of constant in constant pool    * @param  tag expected type    * @return String representation    */
specifier|public
name|String
name|constantToString
parameter_list|(
name|int
name|index
parameter_list|,
name|byte
name|tag
parameter_list|)
throws|throws
name|ClassFormatException
block|{
name|Constant
name|c
init|=
name|getConstant
argument_list|(
name|index
argument_list|,
name|tag
argument_list|)
decl_stmt|;
return|return
name|constantToString
argument_list|(
name|c
argument_list|)
return|;
block|}
comment|/**     * Dump constant pool to file stream in binary format.    *    * @param file Output file stream    * @throws IOException    */
specifier|public
name|void
name|dump
parameter_list|(
name|DataOutputStream
name|file
parameter_list|)
throws|throws
name|IOException
block|{
name|file
operator|.
name|writeShort
argument_list|(
name|constant_pool_count
argument_list|)
expr_stmt|;
for|for
control|(
name|int
name|i
init|=
literal|1
init|;
name|i
operator|<
name|constant_pool_count
condition|;
name|i
operator|++
control|)
if|if
condition|(
name|constant_pool
index|[
name|i
index|]
operator|!=
literal|null
condition|)
name|constant_pool
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
comment|/**    * Get constant from constant pool.    *    * @param  index Index in constant pool    * @return Constant value    * @see    Constant    */
specifier|public
name|Constant
name|getConstant
parameter_list|(
name|int
name|index
parameter_list|)
block|{
if|if
condition|(
name|index
operator|>=
name|constant_pool
operator|.
name|length
operator|||
name|index
operator|<
literal|0
condition|)
throw|throw
operator|new
name|ClassFormatException
argument_list|(
literal|"Invalid constant pool reference: "
operator|+
name|index
operator|+
literal|". Constant pool size is: "
operator|+
name|constant_pool
operator|.
name|length
argument_list|)
throw|;
return|return
name|constant_pool
index|[
name|index
index|]
return|;
block|}
comment|/**    * Get constant from constant pool and check whether it has the    * expected type.    *    * @param  index Index in constant pool    * @param  tag Tag of expected constant, i.e., its type    * @return Constant value    * @see    Constant    * @throws  ClassFormatException    */
specifier|public
name|Constant
name|getConstant
parameter_list|(
name|int
name|index
parameter_list|,
name|byte
name|tag
parameter_list|)
throws|throws
name|ClassFormatException
block|{
name|Constant
name|c
decl_stmt|;
name|c
operator|=
name|getConstant
argument_list|(
name|index
argument_list|)
expr_stmt|;
if|if
condition|(
name|c
operator|==
literal|null
condition|)
throw|throw
operator|new
name|ClassFormatException
argument_list|(
literal|"Constant pool at index "
operator|+
name|index
operator|+
literal|" is null."
argument_list|)
throw|;
if|if
condition|(
name|c
operator|.
name|getTag
argument_list|()
operator|!=
name|tag
condition|)
throw|throw
operator|new
name|ClassFormatException
argument_list|(
literal|"Expected class `"
operator|+
name|Constants
operator|.
name|CONSTANT_NAMES
index|[
name|tag
index|]
operator|+
literal|"' at index "
operator|+
name|index
operator|+
literal|" and got "
operator|+
name|c
argument_list|)
throw|;
return|return
name|c
return|;
block|}
comment|/**    * @return Array of constants.    * @see    Constant    */
specifier|public
name|Constant
index|[]
name|getConstantPool
parameter_list|()
block|{
return|return
name|constant_pool
return|;
block|}
comment|/**    * Get string from constant pool and bypass the indirection of     * `ConstantClass' and `ConstantString' objects. I.e. these classes have    * an index field that points to another entry of the constant pool of    * type `ConstantUtf8' which contains the real data.    *    * @param  index Index in constant pool    * @param  tag Tag of expected constant, either ConstantClass or ConstantString    * @return Contents of string reference    * @see    ConstantClass    * @see    ConstantString    * @throws  ClassFormatException    */
specifier|public
name|String
name|getConstantString
parameter_list|(
name|int
name|index
parameter_list|,
name|byte
name|tag
parameter_list|)
throws|throws
name|ClassFormatException
block|{
name|Constant
name|c
decl_stmt|;
name|int
name|i
decl_stmt|;
name|c
operator|=
name|getConstant
argument_list|(
name|index
argument_list|,
name|tag
argument_list|)
expr_stmt|;
comment|/* This switch() is not that elegant, since the two classes have the      * same contents, they just differ in the name of the index      * field variable.      * But we want to stick to the JVM naming conventions closely though      * we could have solved these more elegantly by using the same      * variable name or by subclassing.      */
switch|switch
condition|(
name|tag
condition|)
block|{
case|case
name|Constants
operator|.
name|CONSTANT_Class
case|:
name|i
operator|=
operator|(
operator|(
name|ConstantClass
operator|)
name|c
operator|)
operator|.
name|getNameIndex
argument_list|()
expr_stmt|;
break|break;
case|case
name|Constants
operator|.
name|CONSTANT_String
case|:
name|i
operator|=
operator|(
operator|(
name|ConstantString
operator|)
name|c
operator|)
operator|.
name|getStringIndex
argument_list|()
expr_stmt|;
break|break;
default|default:
throw|throw
operator|new
name|RuntimeException
argument_list|(
literal|"getConstantString called with illegal tag "
operator|+
name|tag
argument_list|)
throw|;
block|}
comment|// Finally get the string from the constant pool
name|c
operator|=
name|getConstant
argument_list|(
name|i
argument_list|,
name|Constants
operator|.
name|CONSTANT_Utf8
argument_list|)
expr_stmt|;
return|return
operator|(
operator|(
name|ConstantUtf8
operator|)
name|c
operator|)
operator|.
name|getBytes
argument_list|()
return|;
block|}
comment|/**    * @return Length of constant pool.    */
specifier|public
name|int
name|getLength
parameter_list|()
block|{
return|return
name|constant_pool_count
return|;
block|}
comment|/**    * @param constant Constant to set    */
specifier|public
name|void
name|setConstant
parameter_list|(
name|int
name|index
parameter_list|,
name|Constant
name|constant
parameter_list|)
block|{
name|constant_pool
index|[
name|index
index|]
operator|=
name|constant
expr_stmt|;
block|}
comment|/**    * @param constant_pool    */
specifier|public
name|void
name|setConstantPool
parameter_list|(
name|Constant
index|[]
name|constant_pool
parameter_list|)
block|{
name|this
operator|.
name|constant_pool
operator|=
name|constant_pool
expr_stmt|;
name|constant_pool_count
operator|=
operator|(
name|constant_pool
operator|==
literal|null
operator|)
condition|?
literal|0
else|:
name|constant_pool
operator|.
name|length
expr_stmt|;
block|}
comment|/**    * @return String representation.    */
specifier|public
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
for|for
control|(
name|int
name|i
init|=
literal|1
init|;
name|i
operator|<
name|constant_pool_count
condition|;
name|i
operator|++
control|)
name|buf
operator|.
name|append
argument_list|(
name|i
argument_list|)
operator|.
name|append
argument_list|(
literal|")"
argument_list|)
operator|.
name|append
argument_list|(
name|constant_pool
index|[
name|i
index|]
argument_list|)
operator|.
name|append
argument_list|(
literal|"\n"
argument_list|)
expr_stmt|;
return|return
name|buf
operator|.
name|toString
argument_list|()
return|;
block|}
comment|/**    * @return deep copy of this constant pool    */
specifier|public
name|ConstantPool
name|copy
parameter_list|()
block|{
name|ConstantPool
name|c
init|=
literal|null
decl_stmt|;
try|try
block|{
name|c
operator|=
operator|(
name|ConstantPool
operator|)
name|clone
argument_list|()
expr_stmt|;
name|c
operator|.
name|constant_pool
operator|=
operator|new
name|Constant
index|[
name|constant_pool_count
index|]
expr_stmt|;
for|for
control|(
name|int
name|i
init|=
literal|1
init|;
name|i
operator|<
name|constant_pool_count
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
operator|!=
literal|null
condition|)
name|c
operator|.
name|constant_pool
index|[
name|i
index|]
operator|=
name|constant_pool
index|[
name|i
index|]
operator|.
name|copy
argument_list|()
expr_stmt|;
block|}
block|}
catch|catch
parameter_list|(
name|CloneNotSupportedException
name|e
parameter_list|)
block|{
block|}
return|return
name|c
return|;
block|}
block|}
end_class

end_unit

