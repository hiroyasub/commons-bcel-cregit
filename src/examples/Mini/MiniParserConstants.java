begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
begin_comment
comment|/*  * Copyright  2000-2009 The Apache Software Foundation  *  *  Licensed under the Apache License, Version 2.0 (the "License");   *  you may not use this file except in compliance with the License.  *  You may obtain a copy of the License at  *  *      http://www.apache.org/licenses/LICENSE-2.0  *  *  Unless required by applicable law or agreed to in writing, software  *  distributed under the License is distributed on an "AS IS" BASIS,  *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  *  See the License for the specific language governing permissions and  *  limitations under the License.   *  */
end_comment

begin_comment
comment|/* Generated By:JJTree&JavaCC: Do not edit this line. MiniParserConstants.java */
end_comment

begin_package
package|package
name|Mini
package|;
end_package

begin_interface
specifier|public
interface|interface
name|MiniParserConstants
block|{
name|int
name|EOF
init|=
literal|0
decl_stmt|;
name|int
name|SINGLE_LINE_COMMENT
init|=
literal|7
decl_stmt|;
name|int
name|GT
init|=
literal|16
decl_stmt|;
name|int
name|LT
init|=
literal|17
decl_stmt|;
name|int
name|GEQ
init|=
literal|18
decl_stmt|;
name|int
name|LEQ
init|=
literal|19
decl_stmt|;
name|int
name|EQ
init|=
literal|20
decl_stmt|;
name|int
name|NEQ
init|=
literal|21
decl_stmt|;
name|int
name|NOT
init|=
literal|22
decl_stmt|;
name|int
name|FALSE
init|=
literal|23
decl_stmt|;
name|int
name|TRUE
init|=
literal|24
decl_stmt|;
name|int
name|AND
init|=
literal|25
decl_stmt|;
name|int
name|OR
init|=
literal|26
decl_stmt|;
name|int
name|PLUS
init|=
literal|27
decl_stmt|;
name|int
name|MINUS
init|=
literal|28
decl_stmt|;
name|int
name|MULT
init|=
literal|29
decl_stmt|;
name|int
name|MOD
init|=
literal|30
decl_stmt|;
name|int
name|DIV
init|=
literal|31
decl_stmt|;
name|int
name|LPAREN
init|=
literal|32
decl_stmt|;
name|int
name|RPAREN
init|=
literal|33
decl_stmt|;
name|int
name|ASSIGN
init|=
literal|34
decl_stmt|;
name|int
name|COMMA
init|=
literal|35
decl_stmt|;
name|int
name|READ
init|=
literal|36
decl_stmt|;
name|int
name|WRITE
init|=
literal|37
decl_stmt|;
name|int
name|DIGIT
init|=
literal|38
decl_stmt|;
name|int
name|LETTER
init|=
literal|39
decl_stmt|;
name|int
name|IDENT
init|=
literal|40
decl_stmt|;
name|int
name|INTEGER
init|=
literal|41
decl_stmt|;
name|int
name|STRING
init|=
literal|42
decl_stmt|;
name|int
name|DEFAULT
init|=
literal|0
decl_stmt|;
name|int
name|SINGLE_LINE_COMMENT_STATE
init|=
literal|1
decl_stmt|;
name|String
index|[]
name|tokenImage
init|=
block|{
literal|"<EOF>"
block|,
literal|"\" \""
block|,
literal|"\"\\t\""
block|,
literal|"\"\\n\""
block|,
literal|"\"\\r\""
block|,
literal|"\"\\f\""
block|,
literal|"\"--\""
block|,
literal|"<SINGLE_LINE_COMMENT>"
block|,
literal|"<token of kind 8>"
block|,
literal|"\"FUN\""
block|,
literal|"\"IF\""
block|,
literal|"\"THEN\""
block|,
literal|"\"ELSE\""
block|,
literal|"\"FI\""
block|,
literal|"\"LET\""
block|,
literal|"\"IN\""
block|,
literal|"\">\""
block|,
literal|"\"<\""
block|,
literal|"\">=\""
block|,
literal|"\"<=\""
block|,
literal|"\"==\""
block|,
literal|"\"!=\""
block|,
literal|"\"!\""
block|,
literal|"\"FALSE\""
block|,
literal|"\"TRUE\""
block|,
literal|"\"AND\""
block|,
literal|"\"OR\""
block|,
literal|"\"+\""
block|,
literal|"\"-\""
block|,
literal|"\"*\""
block|,
literal|"\"%\""
block|,
literal|"\"/\""
block|,
literal|"\"(\""
block|,
literal|"\")\""
block|,
literal|"\"=\""
block|,
literal|"\",\""
block|,
literal|"\"READ\""
block|,
literal|"\"WRITE\""
block|,
literal|"<DIGIT>"
block|,
literal|"<LETTER>"
block|,
literal|"<IDENT>"
block|,
literal|"<INTEGER>"
block|,
literal|"<STRING>"
block|,   }
decl_stmt|;
block|}
end_interface

end_unit
