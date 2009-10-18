begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
begin_comment
comment|/*  * Copyright  2000-2009 The Apache Software Foundation  *  *  Licensed under the Apache License, Version 2.0 (the "License");   *  you may not use this file except in compliance with the License.  *  You may obtain a copy of the License at  *  *      http://www.apache.org/licenses/LICENSE-2.0  *  *  Unless required by applicable law or agreed to in writing, software  *  distributed under the License is distributed on an "AS IS" BASIS,  *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  *  See the License for the specific language governing permissions and  *  limitations under the License.   *  */
end_comment

begin_import
import|import
name|org
operator|.
name|apache
operator|.
name|bcel
operator|.
name|Repository
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
name|ClassGen
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
name|FieldGen
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
name|MethodGen
import|;
end_import

begin_comment
comment|/**  * Test BCEL if an input file is identical to the outfile generated  * with BCEL. Of course there may some small differences, e.g., because  * BCEL generates local variable tables by default.  *  * Try to:  *<pre> % java id<someclass> % java listclass -code<someclass>&gt; foo % java listclass -code<someclass>.clazz&gt; bar % diff foo bar | more  *<pre>  *  * @version $Id$  * @author<A HREF="mailto:m.dahm@gmx.de">M. Dahm</A>  */
end_comment

begin_class
specifier|public
class|class
name|id
block|{
specifier|public
specifier|static
name|void
name|main
parameter_list|(
name|String
index|[]
name|argv
parameter_list|)
throws|throws
name|Exception
block|{
name|JavaClass
name|clazz
init|=
literal|null
decl_stmt|;
if|if
condition|(
operator|(
name|clazz
operator|=
name|Repository
operator|.
name|lookupClass
argument_list|(
name|argv
index|[
literal|0
index|]
argument_list|)
operator|)
operator|==
literal|null
condition|)
block|{
name|clazz
operator|=
operator|new
name|ClassParser
argument_list|(
name|argv
index|[
literal|0
index|]
argument_list|)
operator|.
name|parse
argument_list|()
expr_stmt|;
comment|// May throw IOException
block|}
name|ClassGen
name|cg
init|=
operator|new
name|ClassGen
argument_list|(
name|clazz
argument_list|)
decl_stmt|;
name|Method
index|[]
name|methods
init|=
name|clazz
operator|.
name|getMethods
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
name|methods
operator|.
name|length
condition|;
name|i
operator|++
control|)
block|{
name|MethodGen
name|mg
init|=
operator|new
name|MethodGen
argument_list|(
name|methods
index|[
name|i
index|]
argument_list|,
name|cg
operator|.
name|getClassName
argument_list|()
argument_list|,
name|cg
operator|.
name|getConstantPool
argument_list|()
argument_list|)
decl_stmt|;
name|cg
operator|.
name|replaceMethod
argument_list|(
name|methods
index|[
name|i
index|]
argument_list|,
name|mg
operator|.
name|getMethod
argument_list|()
argument_list|)
expr_stmt|;
block|}
name|Field
index|[]
name|fields
init|=
name|clazz
operator|.
name|getFields
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
name|fields
operator|.
name|length
condition|;
name|i
operator|++
control|)
block|{
name|FieldGen
name|fg
init|=
operator|new
name|FieldGen
argument_list|(
name|fields
index|[
name|i
index|]
argument_list|,
name|cg
operator|.
name|getConstantPool
argument_list|()
argument_list|)
decl_stmt|;
name|cg
operator|.
name|replaceField
argument_list|(
name|fields
index|[
name|i
index|]
argument_list|,
name|fg
operator|.
name|getField
argument_list|()
argument_list|)
expr_stmt|;
block|}
name|cg
operator|.
name|getJavaClass
argument_list|()
operator|.
name|dump
argument_list|(
name|clazz
operator|.
name|getClassName
argument_list|()
operator|+
literal|".clazz"
argument_list|)
expr_stmt|;
block|}
block|}
end_class

end_unit

