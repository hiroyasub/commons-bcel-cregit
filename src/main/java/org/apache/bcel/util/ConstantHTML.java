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
name|util
package|;
end_package

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
name|PrintWriter
import|;
end_import

begin_import
import|import
name|java
operator|.
name|nio
operator|.
name|charset
operator|.
name|Charset
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
name|Const
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
name|ConstantClass
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
name|ConstantFieldref
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
name|ConstantInterfaceMethodref
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
name|ConstantMethodref
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
name|ConstantNameAndType
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
name|ConstantString
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
name|classfile
operator|.
name|Utility
import|;
end_import

begin_comment
comment|/**  * Convert constant pool into HTML file.  */
end_comment

begin_class
specifier|final
class|class
name|ConstantHTML
block|{
specifier|private
specifier|final
name|String
name|className
decl_stmt|;
comment|// name of current class
specifier|private
specifier|final
name|String
name|classPackage
decl_stmt|;
comment|// name of package
specifier|private
specifier|final
name|ConstantPool
name|constantPool
decl_stmt|;
comment|// reference to constant pool
specifier|private
specifier|final
name|PrintWriter
name|printWriter
decl_stmt|;
comment|// file to write to
specifier|private
specifier|final
name|String
index|[]
name|constantRef
decl_stmt|;
comment|// String to return for cp[i]
specifier|private
specifier|final
name|Constant
index|[]
name|constants
decl_stmt|;
comment|// The constants in the cp
specifier|private
specifier|final
name|Method
index|[]
name|methods
decl_stmt|;
name|ConstantHTML
parameter_list|(
specifier|final
name|String
name|dir
parameter_list|,
specifier|final
name|String
name|class_name
parameter_list|,
specifier|final
name|String
name|class_package
parameter_list|,
specifier|final
name|Method
index|[]
name|methods
parameter_list|,
specifier|final
name|ConstantPool
name|constant_pool
parameter_list|,
specifier|final
name|Charset
name|charset
parameter_list|)
throws|throws
name|IOException
block|{
name|this
operator|.
name|className
operator|=
name|class_name
expr_stmt|;
name|this
operator|.
name|classPackage
operator|=
name|class_package
expr_stmt|;
name|this
operator|.
name|constantPool
operator|=
name|constant_pool
expr_stmt|;
name|this
operator|.
name|methods
operator|=
name|methods
expr_stmt|;
name|constants
operator|=
name|constant_pool
operator|.
name|getConstantPool
argument_list|()
expr_stmt|;
try|try
init|(
name|PrintWriter
name|newPrintWriter
init|=
operator|new
name|PrintWriter
argument_list|(
name|dir
operator|+
name|class_name
operator|+
literal|"_cp.html"
argument_list|,
name|charset
operator|.
name|name
argument_list|()
argument_list|)
init|)
block|{
name|printWriter
operator|=
name|newPrintWriter
expr_stmt|;
name|constantRef
operator|=
operator|new
name|String
index|[
name|constants
operator|.
name|length
index|]
expr_stmt|;
name|constantRef
index|[
literal|0
index|]
operator|=
literal|"&lt;unknown&gt;"
expr_stmt|;
name|printWriter
operator|.
name|print
argument_list|(
literal|"<HTML><head><meta charset=\""
argument_list|)
expr_stmt|;
name|printWriter
operator|.
name|print
argument_list|(
name|charset
operator|.
name|name
argument_list|()
argument_list|)
expr_stmt|;
name|printWriter
operator|.
name|println
argument_list|(
literal|"\"></head>"
argument_list|)
expr_stmt|;
name|printWriter
operator|.
name|println
argument_list|(
literal|"<BODY BGCOLOR=\"#C0C0C0\"><TABLE BORDER=0>"
argument_list|)
expr_stmt|;
comment|// Loop through constants, constants[0] is reserved
for|for
control|(
name|int
name|i
init|=
literal|1
init|;
name|i
operator|<
name|constants
operator|.
name|length
condition|;
name|i
operator|++
control|)
block|{
if|if
condition|(
name|i
operator|%
literal|2
operator|==
literal|0
condition|)
block|{
name|printWriter
operator|.
name|print
argument_list|(
literal|"<TR BGCOLOR=\"#C0C0C0\"><TD>"
argument_list|)
expr_stmt|;
block|}
else|else
block|{
name|printWriter
operator|.
name|print
argument_list|(
literal|"<TR BGCOLOR=\"#A0A0A0\"><TD>"
argument_list|)
expr_stmt|;
block|}
if|if
condition|(
name|constants
index|[
name|i
index|]
operator|!=
literal|null
condition|)
block|{
name|writeConstant
argument_list|(
name|i
argument_list|)
expr_stmt|;
block|}
name|printWriter
operator|.
name|print
argument_list|(
literal|"</TD></TR>\n"
argument_list|)
expr_stmt|;
block|}
name|printWriter
operator|.
name|println
argument_list|(
literal|"</TABLE></BODY></HTML>"
argument_list|)
expr_stmt|;
block|}
block|}
specifier|private
name|int
name|getMethodNumber
parameter_list|(
specifier|final
name|String
name|str
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
name|methods
operator|.
name|length
condition|;
name|i
operator|++
control|)
block|{
specifier|final
name|String
name|cmp
init|=
name|methods
index|[
name|i
index|]
operator|.
name|getName
argument_list|()
operator|+
name|methods
index|[
name|i
index|]
operator|.
name|getSignature
argument_list|()
decl_stmt|;
if|if
condition|(
name|cmp
operator|.
name|equals
argument_list|(
name|str
argument_list|)
condition|)
block|{
return|return
name|i
return|;
block|}
block|}
return|return
operator|-
literal|1
return|;
block|}
name|String
name|referenceConstant
parameter_list|(
specifier|final
name|int
name|index
parameter_list|)
block|{
return|return
name|constantRef
index|[
name|index
index|]
return|;
block|}
specifier|private
name|void
name|writeConstant
parameter_list|(
specifier|final
name|int
name|index
parameter_list|)
block|{
specifier|final
name|byte
name|tag
init|=
name|constants
index|[
name|index
index|]
operator|.
name|getTag
argument_list|()
decl_stmt|;
name|int
name|class_index
decl_stmt|;
name|int
name|name_index
decl_stmt|;
name|String
name|ref
decl_stmt|;
comment|// The header is always the same
name|printWriter
operator|.
name|println
argument_list|(
literal|"<H4><A NAME=cp"
operator|+
name|index
operator|+
literal|">"
operator|+
name|index
operator|+
literal|"</A> "
operator|+
name|Const
operator|.
name|getConstantName
argument_list|(
name|tag
argument_list|)
operator|+
literal|"</H4>"
argument_list|)
expr_stmt|;
comment|/*          * For every constant type get the needed parameters and print them appropiately          */
switch|switch
condition|(
name|tag
condition|)
block|{
case|case
name|Const
operator|.
name|CONSTANT_InterfaceMethodref
case|:
case|case
name|Const
operator|.
name|CONSTANT_Methodref
case|:
comment|// Get class_index and name_and_type_index, depending on type
if|if
condition|(
name|tag
operator|==
name|Const
operator|.
name|CONSTANT_Methodref
condition|)
block|{
specifier|final
name|ConstantMethodref
name|c
init|=
operator|(
name|ConstantMethodref
operator|)
name|constantPool
operator|.
name|getConstant
argument_list|(
name|index
argument_list|,
name|Const
operator|.
name|CONSTANT_Methodref
argument_list|)
decl_stmt|;
name|class_index
operator|=
name|c
operator|.
name|getClassIndex
argument_list|()
expr_stmt|;
name|name_index
operator|=
name|c
operator|.
name|getNameAndTypeIndex
argument_list|()
expr_stmt|;
block|}
else|else
block|{
specifier|final
name|ConstantInterfaceMethodref
name|c1
init|=
operator|(
name|ConstantInterfaceMethodref
operator|)
name|constantPool
operator|.
name|getConstant
argument_list|(
name|index
argument_list|,
name|Const
operator|.
name|CONSTANT_InterfaceMethodref
argument_list|)
decl_stmt|;
name|class_index
operator|=
name|c1
operator|.
name|getClassIndex
argument_list|()
expr_stmt|;
name|name_index
operator|=
name|c1
operator|.
name|getNameAndTypeIndex
argument_list|()
expr_stmt|;
block|}
comment|// Get method name and its class
specifier|final
name|String
name|method_name
init|=
name|constantPool
operator|.
name|constantToString
argument_list|(
name|name_index
argument_list|,
name|Const
operator|.
name|CONSTANT_NameAndType
argument_list|)
decl_stmt|;
specifier|final
name|String
name|html_method_name
init|=
name|Class2HTML
operator|.
name|toHTML
argument_list|(
name|method_name
argument_list|)
decl_stmt|;
comment|// Partially compacted class name, i.e., / -> .
specifier|final
name|String
name|method_class
init|=
name|constantPool
operator|.
name|constantToString
argument_list|(
name|class_index
argument_list|,
name|Const
operator|.
name|CONSTANT_Class
argument_list|)
decl_stmt|;
name|String
name|short_method_class
init|=
name|Utility
operator|.
name|compactClassName
argument_list|(
name|method_class
argument_list|)
decl_stmt|;
comment|// I.e., remove java.lang.
name|short_method_class
operator|=
name|Utility
operator|.
name|compactClassName
argument_list|(
name|short_method_class
argument_list|,
name|classPackage
operator|+
literal|"."
argument_list|,
literal|true
argument_list|)
expr_stmt|;
comment|// Remove class package prefix
comment|// Get method signature
specifier|final
name|ConstantNameAndType
name|c2
init|=
operator|(
name|ConstantNameAndType
operator|)
name|constantPool
operator|.
name|getConstant
argument_list|(
name|name_index
argument_list|,
name|Const
operator|.
name|CONSTANT_NameAndType
argument_list|)
decl_stmt|;
specifier|final
name|String
name|signature
init|=
name|constantPool
operator|.
name|constantToString
argument_list|(
name|c2
operator|.
name|getSignatureIndex
argument_list|()
argument_list|,
name|Const
operator|.
name|CONSTANT_Utf8
argument_list|)
decl_stmt|;
comment|// Get array of strings containing the argument types
specifier|final
name|String
index|[]
name|args
init|=
name|Utility
operator|.
name|methodSignatureArgumentTypes
argument_list|(
name|signature
argument_list|,
literal|false
argument_list|)
decl_stmt|;
comment|// Get return type string
specifier|final
name|String
name|type
init|=
name|Utility
operator|.
name|methodSignatureReturnType
argument_list|(
name|signature
argument_list|,
literal|false
argument_list|)
decl_stmt|;
specifier|final
name|String
name|ret_type
init|=
name|Class2HTML
operator|.
name|referenceType
argument_list|(
name|type
argument_list|)
decl_stmt|;
specifier|final
name|StringBuilder
name|buf
init|=
operator|new
name|StringBuilder
argument_list|(
literal|"("
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
name|args
operator|.
name|length
condition|;
name|i
operator|++
control|)
block|{
name|buf
operator|.
name|append
argument_list|(
name|Class2HTML
operator|.
name|referenceType
argument_list|(
name|args
index|[
name|i
index|]
argument_list|)
argument_list|)
expr_stmt|;
if|if
condition|(
name|i
operator|<
name|args
operator|.
name|length
operator|-
literal|1
condition|)
block|{
name|buf
operator|.
name|append
argument_list|(
literal|",&nbsp;"
argument_list|)
expr_stmt|;
block|}
block|}
name|buf
operator|.
name|append
argument_list|(
literal|")"
argument_list|)
expr_stmt|;
specifier|final
name|String
name|arg_types
init|=
name|buf
operator|.
name|toString
argument_list|()
decl_stmt|;
if|if
condition|(
name|method_class
operator|.
name|equals
argument_list|(
name|className
argument_list|)
condition|)
block|{
name|ref
operator|=
literal|"<A HREF=\""
operator|+
name|className
operator|+
literal|"_code.html#method"
operator|+
name|getMethodNumber
argument_list|(
name|method_name
operator|+
name|signature
argument_list|)
operator|+
literal|"\" TARGET=Code>"
operator|+
name|html_method_name
operator|+
literal|"</A>"
expr_stmt|;
block|}
else|else
block|{
name|ref
operator|=
literal|"<A HREF=\""
operator|+
name|method_class
operator|+
literal|".html"
operator|+
literal|"\" TARGET=_top>"
operator|+
name|short_method_class
operator|+
literal|"</A>."
operator|+
name|html_method_name
expr_stmt|;
block|}
name|constantRef
index|[
name|index
index|]
operator|=
name|ret_type
operator|+
literal|"&nbsp;<A HREF=\""
operator|+
name|className
operator|+
literal|"_cp.html#cp"
operator|+
name|class_index
operator|+
literal|"\" TARGET=Constants>"
operator|+
name|short_method_class
operator|+
literal|"</A>.<A HREF=\""
operator|+
name|className
operator|+
literal|"_cp.html#cp"
operator|+
name|index
operator|+
literal|"\" TARGET=ConstantPool>"
operator|+
name|html_method_name
operator|+
literal|"</A>&nbsp;"
operator|+
name|arg_types
expr_stmt|;
name|printWriter
operator|.
name|println
argument_list|(
literal|"<P><TT>"
operator|+
name|ret_type
operator|+
literal|"&nbsp;"
operator|+
name|ref
operator|+
name|arg_types
operator|+
literal|"&nbsp;</TT>\n<UL>"
operator|+
literal|"<LI><A HREF=\"#cp"
operator|+
name|class_index
operator|+
literal|"\">Class index("
operator|+
name|class_index
operator|+
literal|")</A>\n"
operator|+
literal|"<LI><A HREF=\"#cp"
operator|+
name|name_index
operator|+
literal|"\">NameAndType index("
operator|+
name|name_index
operator|+
literal|")</A></UL>"
argument_list|)
expr_stmt|;
break|break;
case|case
name|Const
operator|.
name|CONSTANT_Fieldref
case|:
comment|// Get class_index and name_and_type_index
specifier|final
name|ConstantFieldref
name|c3
init|=
operator|(
name|ConstantFieldref
operator|)
name|constantPool
operator|.
name|getConstant
argument_list|(
name|index
argument_list|,
name|Const
operator|.
name|CONSTANT_Fieldref
argument_list|)
decl_stmt|;
name|class_index
operator|=
name|c3
operator|.
name|getClassIndex
argument_list|()
expr_stmt|;
name|name_index
operator|=
name|c3
operator|.
name|getNameAndTypeIndex
argument_list|()
expr_stmt|;
comment|// Get method name and its class (compacted)
specifier|final
name|String
name|field_class
init|=
name|constantPool
operator|.
name|constantToString
argument_list|(
name|class_index
argument_list|,
name|Const
operator|.
name|CONSTANT_Class
argument_list|)
decl_stmt|;
name|String
name|short_field_class
init|=
name|Utility
operator|.
name|compactClassName
argument_list|(
name|field_class
argument_list|)
decl_stmt|;
comment|// I.e., remove java.lang.
name|short_field_class
operator|=
name|Utility
operator|.
name|compactClassName
argument_list|(
name|short_field_class
argument_list|,
name|classPackage
operator|+
literal|"."
argument_list|,
literal|true
argument_list|)
expr_stmt|;
comment|// Remove class package prefix
specifier|final
name|String
name|field_name
init|=
name|constantPool
operator|.
name|constantToString
argument_list|(
name|name_index
argument_list|,
name|Const
operator|.
name|CONSTANT_NameAndType
argument_list|)
decl_stmt|;
if|if
condition|(
name|field_class
operator|.
name|equals
argument_list|(
name|className
argument_list|)
condition|)
block|{
name|ref
operator|=
literal|"<A HREF=\""
operator|+
name|field_class
operator|+
literal|"_methods.html#field"
operator|+
name|field_name
operator|+
literal|"\" TARGET=Methods>"
operator|+
name|field_name
operator|+
literal|"</A>"
expr_stmt|;
block|}
else|else
block|{
name|ref
operator|=
literal|"<A HREF=\""
operator|+
name|field_class
operator|+
literal|".html\" TARGET=_top>"
operator|+
name|short_field_class
operator|+
literal|"</A>."
operator|+
name|field_name
operator|+
literal|"\n"
expr_stmt|;
block|}
name|constantRef
index|[
name|index
index|]
operator|=
literal|"<A HREF=\""
operator|+
name|className
operator|+
literal|"_cp.html#cp"
operator|+
name|class_index
operator|+
literal|"\" TARGET=Constants>"
operator|+
name|short_field_class
operator|+
literal|"</A>.<A HREF=\""
operator|+
name|className
operator|+
literal|"_cp.html#cp"
operator|+
name|index
operator|+
literal|"\" TARGET=ConstantPool>"
operator|+
name|field_name
operator|+
literal|"</A>"
expr_stmt|;
name|printWriter
operator|.
name|println
argument_list|(
literal|"<P><TT>"
operator|+
name|ref
operator|+
literal|"</TT><BR>\n"
operator|+
literal|"<UL>"
operator|+
literal|"<LI><A HREF=\"#cp"
operator|+
name|class_index
operator|+
literal|"\">Class("
operator|+
name|class_index
operator|+
literal|")</A><BR>\n"
operator|+
literal|"<LI><A HREF=\"#cp"
operator|+
name|name_index
operator|+
literal|"\">NameAndType("
operator|+
name|name_index
operator|+
literal|")</A></UL>"
argument_list|)
expr_stmt|;
break|break;
case|case
name|Const
operator|.
name|CONSTANT_Class
case|:
specifier|final
name|ConstantClass
name|c4
init|=
operator|(
name|ConstantClass
operator|)
name|constantPool
operator|.
name|getConstant
argument_list|(
name|index
argument_list|,
name|Const
operator|.
name|CONSTANT_Class
argument_list|)
decl_stmt|;
name|name_index
operator|=
name|c4
operator|.
name|getNameIndex
argument_list|()
expr_stmt|;
specifier|final
name|String
name|class_name2
init|=
name|constantPool
operator|.
name|constantToString
argument_list|(
name|index
argument_list|,
name|tag
argument_list|)
decl_stmt|;
comment|// / -> .
name|String
name|short_class_name
init|=
name|Utility
operator|.
name|compactClassName
argument_list|(
name|class_name2
argument_list|)
decl_stmt|;
comment|// I.e., remove java.lang.
name|short_class_name
operator|=
name|Utility
operator|.
name|compactClassName
argument_list|(
name|short_class_name
argument_list|,
name|classPackage
operator|+
literal|"."
argument_list|,
literal|true
argument_list|)
expr_stmt|;
comment|// Remove class package prefix
name|ref
operator|=
literal|"<A HREF=\""
operator|+
name|class_name2
operator|+
literal|".html\" TARGET=_top>"
operator|+
name|short_class_name
operator|+
literal|"</A>"
expr_stmt|;
name|constantRef
index|[
name|index
index|]
operator|=
literal|"<A HREF=\""
operator|+
name|className
operator|+
literal|"_cp.html#cp"
operator|+
name|index
operator|+
literal|"\" TARGET=ConstantPool>"
operator|+
name|short_class_name
operator|+
literal|"</A>"
expr_stmt|;
name|printWriter
operator|.
name|println
argument_list|(
literal|"<P><TT>"
operator|+
name|ref
operator|+
literal|"</TT><UL>"
operator|+
literal|"<LI><A HREF=\"#cp"
operator|+
name|name_index
operator|+
literal|"\">Name index("
operator|+
name|name_index
operator|+
literal|")</A></UL>\n"
argument_list|)
expr_stmt|;
break|break;
case|case
name|Const
operator|.
name|CONSTANT_String
case|:
specifier|final
name|ConstantString
name|c5
init|=
operator|(
name|ConstantString
operator|)
name|constantPool
operator|.
name|getConstant
argument_list|(
name|index
argument_list|,
name|Const
operator|.
name|CONSTANT_String
argument_list|)
decl_stmt|;
name|name_index
operator|=
name|c5
operator|.
name|getStringIndex
argument_list|()
expr_stmt|;
specifier|final
name|String
name|str
init|=
name|Class2HTML
operator|.
name|toHTML
argument_list|(
name|constantPool
operator|.
name|constantToString
argument_list|(
name|index
argument_list|,
name|tag
argument_list|)
argument_list|)
decl_stmt|;
name|printWriter
operator|.
name|println
argument_list|(
literal|"<P><TT>"
operator|+
name|str
operator|+
literal|"</TT><UL>"
operator|+
literal|"<LI><A HREF=\"#cp"
operator|+
name|name_index
operator|+
literal|"\">Name index("
operator|+
name|name_index
operator|+
literal|")</A></UL>\n"
argument_list|)
expr_stmt|;
break|break;
case|case
name|Const
operator|.
name|CONSTANT_NameAndType
case|:
specifier|final
name|ConstantNameAndType
name|c6
init|=
operator|(
name|ConstantNameAndType
operator|)
name|constantPool
operator|.
name|getConstant
argument_list|(
name|index
argument_list|,
name|Const
operator|.
name|CONSTANT_NameAndType
argument_list|)
decl_stmt|;
name|name_index
operator|=
name|c6
operator|.
name|getNameIndex
argument_list|()
expr_stmt|;
specifier|final
name|int
name|signature_index
init|=
name|c6
operator|.
name|getSignatureIndex
argument_list|()
decl_stmt|;
name|printWriter
operator|.
name|println
argument_list|(
literal|"<P><TT>"
operator|+
name|Class2HTML
operator|.
name|toHTML
argument_list|(
name|constantPool
operator|.
name|constantToString
argument_list|(
name|index
argument_list|,
name|tag
argument_list|)
argument_list|)
operator|+
literal|"</TT><UL>"
operator|+
literal|"<LI><A HREF=\"#cp"
operator|+
name|name_index
operator|+
literal|"\">Name index("
operator|+
name|name_index
operator|+
literal|")</A>\n"
operator|+
literal|"<LI><A HREF=\"#cp"
operator|+
name|signature_index
operator|+
literal|"\">Signature index("
operator|+
name|signature_index
operator|+
literal|")</A></UL>\n"
argument_list|)
expr_stmt|;
break|break;
default|default:
name|printWriter
operator|.
name|println
argument_list|(
literal|"<P><TT>"
operator|+
name|Class2HTML
operator|.
name|toHTML
argument_list|(
name|constantPool
operator|.
name|constantToString
argument_list|(
name|index
argument_list|,
name|tag
argument_list|)
argument_list|)
operator|+
literal|"</TT>\n"
argument_list|)
expr_stmt|;
block|}
comment|// switch
block|}
block|}
end_class

end_unit

