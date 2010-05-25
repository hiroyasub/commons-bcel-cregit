begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
begin_comment
comment|/*  * Licensed to the Apache Software Foundation (ASF) under one or more  * contributor license agreements.  See the NOTICE file distributed with  * this work for additional information regarding copyright ownership.  * The ASF licenses this file to You under the Apache License, Version 2.0  * (the "License"); you may not use this file except in compliance with  * the License.  You may obtain a copy of the License at  *  *      http://www.apache.org/licenses/LICENSE-2.0  *  *  Unless required by applicable law or agreed to in writing, software  *  distributed under the License is distributed on an "AS IS" BASIS,  *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  *  See the License for the specific language governing permissions and  *  limitations under the License.   *  */
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
name|FileOutputStream
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
name|PrintWriter
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
name|Code
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
name|ConstantValue
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
name|ExceptionTable
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
comment|/**  * Convert methods and fields into HTML file.  *  * @version $Id$  * @author<A HREF="mailto:m.dahm@gmx.de">M. Dahm</A>  *   */
end_comment

begin_class
specifier|final
class|class
name|MethodHTML
implements|implements
name|org
operator|.
name|apache
operator|.
name|bcel
operator|.
name|Constants
block|{
specifier|private
name|String
name|class_name
decl_stmt|;
comment|// name of current class
specifier|private
name|PrintWriter
name|file
decl_stmt|;
comment|// file to write to
specifier|private
name|ConstantHTML
name|constant_html
decl_stmt|;
specifier|private
name|AttributeHTML
name|attribute_html
decl_stmt|;
name|MethodHTML
parameter_list|(
name|String
name|dir
parameter_list|,
name|String
name|class_name
parameter_list|,
name|Method
index|[]
name|methods
parameter_list|,
name|Field
index|[]
name|fields
parameter_list|,
name|ConstantHTML
name|constant_html
parameter_list|,
name|AttributeHTML
name|attribute_html
parameter_list|)
throws|throws
name|IOException
block|{
name|this
operator|.
name|class_name
operator|=
name|class_name
expr_stmt|;
name|this
operator|.
name|attribute_html
operator|=
name|attribute_html
expr_stmt|;
name|this
operator|.
name|constant_html
operator|=
name|constant_html
expr_stmt|;
name|file
operator|=
operator|new
name|PrintWriter
argument_list|(
operator|new
name|FileOutputStream
argument_list|(
name|dir
operator|+
name|class_name
operator|+
literal|"_methods.html"
argument_list|)
argument_list|)
expr_stmt|;
name|file
operator|.
name|println
argument_list|(
literal|"<HTML><BODY BGCOLOR=\"#C0C0C0\"><TABLE BORDER=0>"
argument_list|)
expr_stmt|;
name|file
operator|.
name|println
argument_list|(
literal|"<TR><TH ALIGN=LEFT>Access&nbsp;flags</TH><TH ALIGN=LEFT>Type</TH>"
operator|+
literal|"<TH ALIGN=LEFT>Field&nbsp;name</TH></TR>"
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
name|fields
operator|.
name|length
condition|;
name|i
operator|++
control|)
block|{
name|writeField
argument_list|(
name|fields
index|[
name|i
index|]
argument_list|)
expr_stmt|;
block|}
name|file
operator|.
name|println
argument_list|(
literal|"</TABLE>"
argument_list|)
expr_stmt|;
name|file
operator|.
name|println
argument_list|(
literal|"<TABLE BORDER=0><TR><TH ALIGN=LEFT>Access&nbsp;flags</TH>"
operator|+
literal|"<TH ALIGN=LEFT>Return&nbsp;type</TH><TH ALIGN=LEFT>Method&nbsp;name</TH>"
operator|+
literal|"<TH ALIGN=LEFT>Arguments</TH></TR>"
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
name|methods
operator|.
name|length
condition|;
name|i
operator|++
control|)
block|{
name|writeMethod
argument_list|(
name|methods
index|[
name|i
index|]
argument_list|,
name|i
argument_list|)
expr_stmt|;
block|}
name|file
operator|.
name|println
argument_list|(
literal|"</TABLE></BODY></HTML>"
argument_list|)
expr_stmt|;
name|file
operator|.
name|close
argument_list|()
expr_stmt|;
block|}
comment|/**      * Print field of class.      *      * @param field field to print      * @exception java.io.IOException      */
specifier|private
name|void
name|writeField
parameter_list|(
name|Field
name|field
parameter_list|)
throws|throws
name|IOException
block|{
name|String
name|type
init|=
name|Utility
operator|.
name|signatureToString
argument_list|(
name|field
operator|.
name|getSignature
argument_list|()
argument_list|)
decl_stmt|;
name|String
name|name
init|=
name|field
operator|.
name|getName
argument_list|()
decl_stmt|;
name|String
name|access
init|=
name|Utility
operator|.
name|accessToString
argument_list|(
name|field
operator|.
name|getAccessFlags
argument_list|()
argument_list|)
decl_stmt|;
name|Attribute
index|[]
name|attributes
decl_stmt|;
name|access
operator|=
name|Utility
operator|.
name|replace
argument_list|(
name|access
argument_list|,
literal|" "
argument_list|,
literal|"&nbsp;"
argument_list|)
expr_stmt|;
name|file
operator|.
name|print
argument_list|(
literal|"<TR><TD><FONT COLOR=\"#FF0000\">"
operator|+
name|access
operator|+
literal|"</FONT></TD>\n<TD>"
operator|+
name|Class2HTML
operator|.
name|referenceType
argument_list|(
name|type
argument_list|)
operator|+
literal|"</TD><TD><A NAME=\"field"
operator|+
name|name
operator|+
literal|"\">"
operator|+
name|name
operator|+
literal|"</A></TD>"
argument_list|)
expr_stmt|;
name|attributes
operator|=
name|field
operator|.
name|getAttributes
argument_list|()
expr_stmt|;
comment|// Write them to the Attributes.html file with anchor "<name>[<i>]"
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
name|attribute_html
operator|.
name|writeAttribute
argument_list|(
name|attributes
index|[
name|i
index|]
argument_list|,
name|name
operator|+
literal|"@"
operator|+
name|i
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
name|attributes
operator|.
name|length
condition|;
name|i
operator|++
control|)
block|{
if|if
condition|(
name|attributes
index|[
name|i
index|]
operator|.
name|getTag
argument_list|()
operator|==
name|ATTR_CONSTANT_VALUE
condition|)
block|{
comment|// Default value
name|String
name|str
init|=
operator|(
operator|(
name|ConstantValue
operator|)
name|attributes
index|[
name|i
index|]
operator|)
operator|.
name|toString
argument_list|()
decl_stmt|;
comment|// Reference attribute in _attributes.html
name|file
operator|.
name|print
argument_list|(
literal|"<TD>=<A HREF=\""
operator|+
name|class_name
operator|+
literal|"_attributes.html#"
operator|+
name|name
operator|+
literal|"@"
operator|+
name|i
operator|+
literal|"\" TARGET=\"Attributes\">"
operator|+
name|str
operator|+
literal|"</TD>\n"
argument_list|)
expr_stmt|;
break|break;
block|}
block|}
name|file
operator|.
name|println
argument_list|(
literal|"</TR>"
argument_list|)
expr_stmt|;
block|}
specifier|private
specifier|final
name|void
name|writeMethod
parameter_list|(
name|Method
name|method
parameter_list|,
name|int
name|method_number
parameter_list|)
throws|throws
name|IOException
block|{
comment|// Get raw signature
name|String
name|signature
init|=
name|method
operator|.
name|getSignature
argument_list|()
decl_stmt|;
comment|// Get array of strings containing the argument types
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
comment|// Get method name
name|String
name|name
init|=
name|method
operator|.
name|getName
argument_list|()
decl_stmt|,
name|html_name
decl_stmt|;
comment|// Get method's access flags
name|String
name|access
init|=
name|Utility
operator|.
name|accessToString
argument_list|(
name|method
operator|.
name|getAccessFlags
argument_list|()
argument_list|)
decl_stmt|;
comment|// Get the method's attributes, the Code Attribute in particular
name|Attribute
index|[]
name|attributes
init|=
name|method
operator|.
name|getAttributes
argument_list|()
decl_stmt|;
comment|/* HTML doesn't like names like<clinit> and spaces are places to break          * lines. Both we don't want...          */
name|access
operator|=
name|Utility
operator|.
name|replace
argument_list|(
name|access
argument_list|,
literal|" "
argument_list|,
literal|"&nbsp;"
argument_list|)
expr_stmt|;
name|html_name
operator|=
name|Class2HTML
operator|.
name|toHTML
argument_list|(
name|name
argument_list|)
expr_stmt|;
name|file
operator|.
name|print
argument_list|(
literal|"<TR VALIGN=TOP><TD><FONT COLOR=\"#FF0000\"><A NAME=method"
operator|+
name|method_number
operator|+
literal|">"
operator|+
name|access
operator|+
literal|"</A></FONT></TD>"
argument_list|)
expr_stmt|;
name|file
operator|.
name|print
argument_list|(
literal|"<TD>"
operator|+
name|Class2HTML
operator|.
name|referenceType
argument_list|(
name|type
argument_list|)
operator|+
literal|"</TD><TD>"
operator|+
literal|"<A HREF="
operator|+
name|class_name
operator|+
literal|"_code.html#method"
operator|+
name|method_number
operator|+
literal|" TARGET=Code>"
operator|+
name|html_name
operator|+
literal|"</A></TD>\n<TD>("
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
name|args
operator|.
name|length
condition|;
name|i
operator|++
control|)
block|{
name|file
operator|.
name|print
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
name|file
operator|.
name|print
argument_list|(
literal|", "
argument_list|)
expr_stmt|;
block|}
block|}
name|file
operator|.
name|print
argument_list|(
literal|")</TD></TR>"
argument_list|)
expr_stmt|;
comment|// Check for thrown exceptions
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
name|attribute_html
operator|.
name|writeAttribute
argument_list|(
name|attributes
index|[
name|i
index|]
argument_list|,
literal|"method"
operator|+
name|method_number
operator|+
literal|"@"
operator|+
name|i
argument_list|,
name|method_number
argument_list|)
expr_stmt|;
name|byte
name|tag
init|=
name|attributes
index|[
name|i
index|]
operator|.
name|getTag
argument_list|()
decl_stmt|;
if|if
condition|(
name|tag
operator|==
name|ATTR_EXCEPTIONS
condition|)
block|{
name|file
operator|.
name|print
argument_list|(
literal|"<TR VALIGN=TOP><TD COLSPAN=2></TD><TH ALIGN=LEFT>throws</TH><TD>"
argument_list|)
expr_stmt|;
name|int
index|[]
name|exceptions
init|=
operator|(
operator|(
name|ExceptionTable
operator|)
name|attributes
index|[
name|i
index|]
operator|)
operator|.
name|getExceptionIndexTable
argument_list|()
decl_stmt|;
for|for
control|(
name|int
name|j
init|=
literal|0
init|;
name|j
operator|<
name|exceptions
operator|.
name|length
condition|;
name|j
operator|++
control|)
block|{
name|file
operator|.
name|print
argument_list|(
name|constant_html
operator|.
name|referenceConstant
argument_list|(
name|exceptions
index|[
name|j
index|]
argument_list|)
argument_list|)
expr_stmt|;
if|if
condition|(
name|j
operator|<
name|exceptions
operator|.
name|length
operator|-
literal|1
condition|)
block|{
name|file
operator|.
name|print
argument_list|(
literal|", "
argument_list|)
expr_stmt|;
block|}
block|}
name|file
operator|.
name|println
argument_list|(
literal|"</TD></TR>"
argument_list|)
expr_stmt|;
block|}
if|else if
condition|(
name|tag
operator|==
name|ATTR_CODE
condition|)
block|{
name|Attribute
index|[]
name|c_a
init|=
operator|(
operator|(
name|Code
operator|)
name|attributes
index|[
name|i
index|]
operator|)
operator|.
name|getAttributes
argument_list|()
decl_stmt|;
for|for
control|(
name|int
name|j
init|=
literal|0
init|;
name|j
operator|<
name|c_a
operator|.
name|length
condition|;
name|j
operator|++
control|)
block|{
name|attribute_html
operator|.
name|writeAttribute
argument_list|(
name|c_a
index|[
name|j
index|]
argument_list|,
literal|"method"
operator|+
name|method_number
operator|+
literal|"@"
operator|+
name|i
operator|+
literal|"@"
operator|+
name|j
argument_list|,
name|method_number
argument_list|)
expr_stmt|;
block|}
block|}
block|}
block|}
block|}
end_class

end_unit

