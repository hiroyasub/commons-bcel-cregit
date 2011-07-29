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
name|generic
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
name|org
operator|.
name|apache
operator|.
name|bcel
operator|.
name|util
operator|.
name|ByteSequence
import|;
end_import

begin_comment
comment|/**   * LDC_W - Push item from constant pool (wide index)  *  *<PRE>Stack: ... -&gt; ..., item.word1, item.word2</PRE>  *  * @version $Id$  * @author<A HREF="mailto:m.dahm@gmx.de">M. Dahm</A>  */
end_comment

begin_class
specifier|public
class|class
name|LDC_W
extends|extends
name|LDC
block|{
specifier|private
specifier|static
specifier|final
name|long
name|serialVersionUID
init|=
operator|-
literal|8040188785844554411L
decl_stmt|;
comment|/**      * Empty constructor needed for the Class.newInstance() statement in      * Instruction.readInstruction(). Not to be used otherwise.      */
name|LDC_W
parameter_list|()
block|{
block|}
specifier|public
name|LDC_W
parameter_list|(
name|int
name|index
parameter_list|)
block|{
name|super
argument_list|(
name|index
argument_list|)
expr_stmt|;
block|}
comment|/**      * Read needed data (i.e., index) from file.      */
annotation|@
name|Override
specifier|protected
name|void
name|initFromFile
parameter_list|(
name|ByteSequence
name|bytes
parameter_list|,
name|boolean
name|wide
parameter_list|)
throws|throws
name|IOException
block|{
name|setIndex
argument_list|(
name|bytes
operator|.
name|readUnsignedShort
argument_list|()
argument_list|)
expr_stmt|;
comment|// Override just in case it has been changed
name|opcode
operator|=
name|org
operator|.
name|apache
operator|.
name|bcel
operator|.
name|Constants
operator|.
name|LDC_W
expr_stmt|;
name|length
operator|=
literal|3
expr_stmt|;
block|}
block|}
end_class

end_unit

