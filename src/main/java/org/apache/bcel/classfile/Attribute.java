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
name|java
operator|.
name|util
operator|.
name|HashMap
import|;
end_import

begin_import
import|import
name|java
operator|.
name|util
operator|.
name|Map
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

begin_comment
comment|/**  * Abstract super class for<em>Attribute</em> objects. Currently the  *<em>ConstantValue</em>,<em>SourceFile</em>,<em>Code</em>,  *<em>Exceptiontable</em>,<em>LineNumberTable</em>,  *<em>LocalVariableTable</em>,<em>InnerClasses</em> and  *<em>Synthetic</em> attributes are supported. The<em>Unknown</em>  * attribute stands for non-standard-attributes.  *   * @version $Id$  * @author<A HREF="mailto:m.dahm@gmx.de">M. Dahm</A>  * @see ConstantValue  * @see SourceFile  * @see Code  * @see Unknown  * @see ExceptionTable  * @see LineNumberTable  * @see LocalVariableTable  * @see InnerClasses  * @see Synthetic  * @see Deprecated  * @see Signature  */
end_comment

begin_class
specifier|public
specifier|abstract
class|class
name|Attribute
implements|implements
name|Cloneable
implements|,
name|Node
implements|,
name|Serializable
block|{
specifier|private
specifier|static
specifier|final
name|long
name|serialVersionUID
init|=
operator|-
literal|1707826820310002955L
decl_stmt|;
specifier|protected
name|int
name|name_index
decl_stmt|;
comment|// Points to attribute name in constant pool
specifier|protected
name|int
name|length
decl_stmt|;
comment|// Content length of attribute field
specifier|protected
name|byte
name|tag
decl_stmt|;
comment|// Tag to distiguish subclasses
specifier|protected
name|ConstantPool
name|constant_pool
decl_stmt|;
specifier|protected
name|Attribute
parameter_list|(
name|byte
name|tag
parameter_list|,
name|int
name|name_index
parameter_list|,
name|int
name|length
parameter_list|,
name|ConstantPool
name|constant_pool
parameter_list|)
block|{
name|this
operator|.
name|tag
operator|=
name|tag
expr_stmt|;
name|this
operator|.
name|name_index
operator|=
name|name_index
expr_stmt|;
name|this
operator|.
name|length
operator|=
name|length
expr_stmt|;
name|this
operator|.
name|constant_pool
operator|=
name|constant_pool
expr_stmt|;
block|}
comment|/** 	 * Called by objects that are traversing the nodes of the tree implicitely 	 * defined by the contents of a Java class. I.e., the hierarchy of methods, 	 * fields, attributes, etc. spawns a tree of objects. 	 *  	 * @param v 	 *            Visitor object 	 */
specifier|public
specifier|abstract
name|void
name|accept
parameter_list|(
name|Visitor
name|v
parameter_list|)
function_decl|;
comment|/** 	 * Dump attribute to file stream in binary format. 	 *  	 * @param file 	 *            Output file stream 	 * @throws IOException 	 */
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
name|name_index
argument_list|)
expr_stmt|;
name|file
operator|.
name|writeInt
argument_list|(
name|length
argument_list|)
expr_stmt|;
block|}
specifier|private
specifier|static
specifier|final
name|Map
argument_list|<
name|String
argument_list|,
name|AttributeReader
argument_list|>
name|readers
init|=
operator|new
name|HashMap
argument_list|<
name|String
argument_list|,
name|AttributeReader
argument_list|>
argument_list|()
decl_stmt|;
comment|/** 	 * Add an Attribute reader capable of parsing (user-defined) attributes 	 * named "name". You should not add readers for the standard attributes such 	 * as "LineNumberTable", because those are handled internally. 	 *  	 * @param name 	 *            the name of the attribute as stored in the class file 	 * @param r 	 *            the reader object 	 */
specifier|public
specifier|static
name|void
name|addAttributeReader
parameter_list|(
name|String
name|name
parameter_list|,
name|AttributeReader
name|r
parameter_list|)
block|{
name|readers
operator|.
name|put
argument_list|(
name|name
argument_list|,
name|r
argument_list|)
expr_stmt|;
block|}
comment|/** 	 * Remove attribute reader 	 *  	 * @param name 	 *            the name of the attribute as stored in the class file 	 */
specifier|public
specifier|static
name|void
name|removeAttributeReader
parameter_list|(
name|String
name|name
parameter_list|)
block|{
name|readers
operator|.
name|remove
argument_list|(
name|name
argument_list|)
expr_stmt|;
block|}
comment|/* 	 * Class method reads one attribute from the input data stream. This method 	 * must not be accessible from the outside. It is called by the Field and 	 * Method constructor methods. 	 *  	 * @see Field 	 * @see Method @param file Input stream @param constant_pool Array of 	 *      constants @return Attribute @throws IOException @throws 	 *      ClassFormatException 	 */
specifier|public
specifier|static
specifier|final
name|Attribute
name|readAttribute
parameter_list|(
name|DataInputStream
name|file
parameter_list|,
name|ConstantPool
name|constant_pool
parameter_list|)
throws|throws
name|IOException
throws|,
name|ClassFormatException
block|{
name|ConstantUtf8
name|c
decl_stmt|;
name|String
name|name
decl_stmt|;
name|int
name|name_index
decl_stmt|;
name|int
name|length
decl_stmt|;
name|byte
name|tag
init|=
name|Constants
operator|.
name|ATTR_UNKNOWN
decl_stmt|;
comment|// Unknown attribute
comment|// Get class name from constant pool via `name_index' indirection
name|name_index
operator|=
name|file
operator|.
name|readUnsignedShort
argument_list|()
expr_stmt|;
name|c
operator|=
operator|(
name|ConstantUtf8
operator|)
name|constant_pool
operator|.
name|getConstant
argument_list|(
name|name_index
argument_list|,
name|Constants
operator|.
name|CONSTANT_Utf8
argument_list|)
expr_stmt|;
name|name
operator|=
name|c
operator|.
name|getBytes
argument_list|()
expr_stmt|;
comment|// Length of data in bytes
name|length
operator|=
name|file
operator|.
name|readInt
argument_list|()
expr_stmt|;
comment|// Compare strings to find known attribute
comment|// System.out.println(name);
for|for
control|(
name|byte
name|i
init|=
literal|0
init|;
name|i
operator|<
name|Constants
operator|.
name|KNOWN_ATTRIBUTES
condition|;
name|i
operator|++
control|)
block|{
if|if
condition|(
name|name
operator|.
name|equals
argument_list|(
name|Constants
operator|.
name|ATTRIBUTE_NAMES
index|[
name|i
index|]
argument_list|)
condition|)
block|{
name|tag
operator|=
name|i
expr_stmt|;
comment|// found!
break|break;
block|}
block|}
comment|// Call proper constructor, depending on `tag'
switch|switch
condition|(
name|tag
condition|)
block|{
case|case
name|Constants
operator|.
name|ATTR_UNKNOWN
case|:
name|AttributeReader
name|r
init|=
name|readers
operator|.
name|get
argument_list|(
name|name
argument_list|)
decl_stmt|;
if|if
condition|(
name|r
operator|!=
literal|null
condition|)
block|{
return|return
name|r
operator|.
name|createAttribute
argument_list|(
name|name_index
argument_list|,
name|length
argument_list|,
name|file
argument_list|,
name|constant_pool
argument_list|)
return|;
block|}
return|return
operator|new
name|Unknown
argument_list|(
name|name_index
argument_list|,
name|length
argument_list|,
name|file
argument_list|,
name|constant_pool
argument_list|)
return|;
case|case
name|Constants
operator|.
name|ATTR_CONSTANT_VALUE
case|:
return|return
operator|new
name|ConstantValue
argument_list|(
name|name_index
argument_list|,
name|length
argument_list|,
name|file
argument_list|,
name|constant_pool
argument_list|)
return|;
case|case
name|Constants
operator|.
name|ATTR_SOURCE_FILE
case|:
return|return
operator|new
name|SourceFile
argument_list|(
name|name_index
argument_list|,
name|length
argument_list|,
name|file
argument_list|,
name|constant_pool
argument_list|)
return|;
case|case
name|Constants
operator|.
name|ATTR_CODE
case|:
return|return
operator|new
name|Code
argument_list|(
name|name_index
argument_list|,
name|length
argument_list|,
name|file
argument_list|,
name|constant_pool
argument_list|)
return|;
case|case
name|Constants
operator|.
name|ATTR_EXCEPTIONS
case|:
return|return
operator|new
name|ExceptionTable
argument_list|(
name|name_index
argument_list|,
name|length
argument_list|,
name|file
argument_list|,
name|constant_pool
argument_list|)
return|;
case|case
name|Constants
operator|.
name|ATTR_LINE_NUMBER_TABLE
case|:
return|return
operator|new
name|LineNumberTable
argument_list|(
name|name_index
argument_list|,
name|length
argument_list|,
name|file
argument_list|,
name|constant_pool
argument_list|)
return|;
case|case
name|Constants
operator|.
name|ATTR_LOCAL_VARIABLE_TABLE
case|:
return|return
operator|new
name|LocalVariableTable
argument_list|(
name|name_index
argument_list|,
name|length
argument_list|,
name|file
argument_list|,
name|constant_pool
argument_list|)
return|;
case|case
name|Constants
operator|.
name|ATTR_INNER_CLASSES
case|:
return|return
operator|new
name|InnerClasses
argument_list|(
name|name_index
argument_list|,
name|length
argument_list|,
name|file
argument_list|,
name|constant_pool
argument_list|)
return|;
case|case
name|Constants
operator|.
name|ATTR_SYNTHETIC
case|:
return|return
operator|new
name|Synthetic
argument_list|(
name|name_index
argument_list|,
name|length
argument_list|,
name|file
argument_list|,
name|constant_pool
argument_list|)
return|;
case|case
name|Constants
operator|.
name|ATTR_DEPRECATED
case|:
return|return
operator|new
name|Deprecated
argument_list|(
name|name_index
argument_list|,
name|length
argument_list|,
name|file
argument_list|,
name|constant_pool
argument_list|)
return|;
case|case
name|Constants
operator|.
name|ATTR_PMG
case|:
return|return
operator|new
name|PMGClass
argument_list|(
name|name_index
argument_list|,
name|length
argument_list|,
name|file
argument_list|,
name|constant_pool
argument_list|)
return|;
case|case
name|Constants
operator|.
name|ATTR_SIGNATURE
case|:
return|return
operator|new
name|Signature
argument_list|(
name|name_index
argument_list|,
name|length
argument_list|,
name|file
argument_list|,
name|constant_pool
argument_list|)
return|;
case|case
name|Constants
operator|.
name|ATTR_STACK_MAP
case|:
return|return
operator|new
name|StackMap
argument_list|(
name|name_index
argument_list|,
name|length
argument_list|,
name|file
argument_list|,
name|constant_pool
argument_list|)
return|;
case|case
name|Constants
operator|.
name|ATTR_RUNTIME_VISIBLE_ANNOTATIONS
case|:
return|return
operator|new
name|RuntimeVisibleAnnotations
argument_list|(
name|name_index
argument_list|,
name|length
argument_list|,
name|file
argument_list|,
name|constant_pool
argument_list|)
return|;
case|case
name|Constants
operator|.
name|ATTR_RUNTIMEIN_VISIBLE_ANNOTATIONS
case|:
return|return
operator|new
name|RuntimeInvisibleAnnotations
argument_list|(
name|name_index
argument_list|,
name|length
argument_list|,
name|file
argument_list|,
name|constant_pool
argument_list|)
return|;
case|case
name|Constants
operator|.
name|ATTR_RUNTIME_VISIBLE_PARAMETER_ANNOTATIONS
case|:
return|return
operator|new
name|RuntimeVisibleParameterAnnotations
argument_list|(
name|name_index
argument_list|,
name|length
argument_list|,
name|file
argument_list|,
name|constant_pool
argument_list|)
return|;
case|case
name|Constants
operator|.
name|ATTR_RUNTIMEIN_VISIBLE_PARAMETER_ANNOTATIONS
case|:
return|return
operator|new
name|RuntimeInvisibleParameterAnnotations
argument_list|(
name|name_index
argument_list|,
name|length
argument_list|,
name|file
argument_list|,
name|constant_pool
argument_list|)
return|;
case|case
name|Constants
operator|.
name|ATTR_ANNOTATION_DEFAULT
case|:
return|return
operator|new
name|AnnotationDefault
argument_list|(
name|name_index
argument_list|,
name|length
argument_list|,
name|file
argument_list|,
name|constant_pool
argument_list|)
return|;
case|case
name|Constants
operator|.
name|ATTR_LOCAL_VARIABLE_TYPE_TABLE
case|:
return|return
operator|new
name|LocalVariableTypeTable
argument_list|(
name|name_index
argument_list|,
name|length
argument_list|,
name|file
argument_list|,
name|constant_pool
argument_list|)
return|;
case|case
name|Constants
operator|.
name|ATTR_ENCLOSING_METHOD
case|:
return|return
operator|new
name|EnclosingMethod
argument_list|(
name|name_index
argument_list|,
name|length
argument_list|,
name|file
argument_list|,
name|constant_pool
argument_list|)
return|;
case|case
name|Constants
operator|.
name|ATTR_STACK_MAP_TABLE
case|:
return|return
operator|new
name|StackMapTable
argument_list|(
name|name_index
argument_list|,
name|length
argument_list|,
name|file
argument_list|,
name|constant_pool
argument_list|)
return|;
default|default:
comment|// Never reached
throw|throw
operator|new
name|IllegalStateException
argument_list|(
literal|"Unrecognized attribute type tag parsed: "
operator|+
name|tag
argument_list|)
throw|;
block|}
block|}
comment|/** 	 * @return Name of attribute 	 */
specifier|public
name|String
name|getName
parameter_list|()
block|{
name|ConstantUtf8
name|c
init|=
operator|(
name|ConstantUtf8
operator|)
name|constant_pool
operator|.
name|getConstant
argument_list|(
name|name_index
argument_list|,
name|Constants
operator|.
name|CONSTANT_Utf8
argument_list|)
decl_stmt|;
return|return
name|c
operator|.
name|getBytes
argument_list|()
return|;
block|}
comment|/** 	 * @return Length of attribute field in bytes. 	 */
specifier|public
specifier|final
name|int
name|getLength
parameter_list|()
block|{
return|return
name|length
return|;
block|}
comment|/** 	 * @param length 	 *            length in bytes. 	 */
specifier|public
specifier|final
name|void
name|setLength
parameter_list|(
name|int
name|length
parameter_list|)
block|{
name|this
operator|.
name|length
operator|=
name|length
expr_stmt|;
block|}
comment|/** 	 * @param name_index 	 *            of attribute. 	 */
specifier|public
specifier|final
name|void
name|setNameIndex
parameter_list|(
name|int
name|name_index
parameter_list|)
block|{
name|this
operator|.
name|name_index
operator|=
name|name_index
expr_stmt|;
block|}
comment|/** 	 * @return Name index in constant pool of attribute name. 	 */
specifier|public
specifier|final
name|int
name|getNameIndex
parameter_list|()
block|{
return|return
name|name_index
return|;
block|}
comment|/** 	 * @return Tag of attribute, i.e., its type. Value may not be altered, thus 	 *         there is no setTag() method. 	 */
specifier|public
specifier|final
name|byte
name|getTag
parameter_list|()
block|{
return|return
name|tag
return|;
block|}
comment|/** 	 * @return Constant pool used by this object. 	 * @see ConstantPool 	 */
specifier|public
specifier|final
name|ConstantPool
name|getConstantPool
parameter_list|()
block|{
return|return
name|constant_pool
return|;
block|}
comment|/** 	 * @param constant_pool 	 *            Constant pool to be used for this object. 	 * @see ConstantPool 	 */
specifier|public
specifier|final
name|void
name|setConstantPool
parameter_list|(
name|ConstantPool
name|constant_pool
parameter_list|)
block|{
name|this
operator|.
name|constant_pool
operator|=
name|constant_pool
expr_stmt|;
block|}
comment|/** 	 * Use copy() if you want to have a deep copy(), i.e., with all references 	 * copied correctly. 	 *  	 * @return shallow copy of this attribute 	 */
specifier|public
name|Object
name|clone
parameter_list|()
block|{
name|Object
name|o
init|=
literal|null
decl_stmt|;
try|try
block|{
name|o
operator|=
name|super
operator|.
name|clone
argument_list|()
expr_stmt|;
block|}
catch|catch
parameter_list|(
name|CloneNotSupportedException
name|e
parameter_list|)
block|{
name|e
operator|.
name|printStackTrace
argument_list|()
expr_stmt|;
comment|// Never occurs
block|}
return|return
name|o
return|;
block|}
comment|/** 	 * @return deep copy of this attribute 	 */
specifier|public
specifier|abstract
name|Attribute
name|copy
parameter_list|(
name|ConstantPool
name|_constant_pool
parameter_list|)
function_decl|;
comment|/** 	 * @return attribute name. 	 */
specifier|public
name|String
name|toString
parameter_list|()
block|{
return|return
name|Constants
operator|.
name|ATTRIBUTE_NAMES
index|[
name|tag
index|]
return|;
block|}
block|}
end_class

end_unit

