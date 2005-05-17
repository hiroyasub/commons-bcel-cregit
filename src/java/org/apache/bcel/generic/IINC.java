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
name|generic
package|;
end_package

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
name|util
operator|.
name|ByteSequence
import|;
end_import

begin_comment
comment|/**  * IINC - Increment local variable by constant  *  * @version $Id$  * @author<A HREF="mailto:m.dahm@gmx.de">M. Dahm</A>  */
end_comment

begin_class
specifier|public
class|class
name|IINC
extends|extends
name|LocalVariableInstruction
block|{
specifier|private
name|boolean
name|wide
decl_stmt|;
specifier|private
name|int
name|c
decl_stmt|;
comment|/**    * Empty constructor needed for the Class.newInstance() statement in    * Instruction.readInstruction(). Not to be used otherwise.    */
name|IINC
parameter_list|()
block|{
block|}
comment|/**    * @param n index of local variable    * @param c increment factor    */
specifier|public
name|IINC
parameter_list|(
name|int
name|n
parameter_list|,
name|int
name|c
parameter_list|)
block|{
name|super
argument_list|()
expr_stmt|;
comment|// Default behaviour of LocalVariableInstruction causes error
name|this
operator|.
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
name|IINC
expr_stmt|;
name|this
operator|.
name|length
operator|=
operator|(
name|short
operator|)
literal|3
expr_stmt|;
name|setIndex
argument_list|(
name|n
argument_list|)
expr_stmt|;
comment|// May set wide as side effect
name|setIncrement
argument_list|(
name|c
argument_list|)
expr_stmt|;
block|}
comment|/**    * Dump instruction as byte code to stream out.    * @param out Output stream    */
specifier|public
name|void
name|dump
parameter_list|(
name|DataOutputStream
name|out
parameter_list|)
throws|throws
name|IOException
block|{
if|if
condition|(
name|wide
condition|)
comment|// Need WIDE prefix ?
name|out
operator|.
name|writeByte
argument_list|(
name|org
operator|.
name|apache
operator|.
name|bcel
operator|.
name|Constants
operator|.
name|WIDE
argument_list|)
expr_stmt|;
name|out
operator|.
name|writeByte
argument_list|(
name|opcode
argument_list|)
expr_stmt|;
if|if
condition|(
name|wide
condition|)
block|{
name|out
operator|.
name|writeShort
argument_list|(
name|n
argument_list|)
expr_stmt|;
name|out
operator|.
name|writeShort
argument_list|(
name|c
argument_list|)
expr_stmt|;
block|}
else|else
block|{
name|out
operator|.
name|writeByte
argument_list|(
name|n
argument_list|)
expr_stmt|;
name|out
operator|.
name|writeByte
argument_list|(
name|c
argument_list|)
expr_stmt|;
block|}
block|}
specifier|private
specifier|final
name|void
name|setWide
parameter_list|()
block|{
name|wide
operator|=
operator|(
name|n
operator|>
name|org
operator|.
name|apache
operator|.
name|bcel
operator|.
name|Constants
operator|.
name|MAX_BYTE
operator|)
operator|||
operator|(
name|Math
operator|.
name|abs
argument_list|(
name|c
argument_list|)
operator|>
name|Byte
operator|.
name|MAX_VALUE
operator|)
expr_stmt|;
if|if
condition|(
name|wide
condition|)
block|{
name|length
operator|=
literal|6
expr_stmt|;
comment|// wide byte included
block|}
else|else
block|{
name|length
operator|=
literal|3
expr_stmt|;
block|}
block|}
comment|/**    * Read needed data (e.g. index) from file.    */
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
name|this
operator|.
name|wide
operator|=
name|wide
expr_stmt|;
if|if
condition|(
name|wide
condition|)
block|{
name|length
operator|=
literal|6
expr_stmt|;
name|n
operator|=
name|bytes
operator|.
name|readUnsignedShort
argument_list|()
expr_stmt|;
name|c
operator|=
name|bytes
operator|.
name|readShort
argument_list|()
expr_stmt|;
block|}
else|else
block|{
name|length
operator|=
literal|3
expr_stmt|;
name|n
operator|=
name|bytes
operator|.
name|readUnsignedByte
argument_list|()
expr_stmt|;
name|c
operator|=
name|bytes
operator|.
name|readByte
argument_list|()
expr_stmt|;
block|}
block|}
comment|/**    * @return mnemonic for instruction    */
specifier|public
name|String
name|toString
parameter_list|(
name|boolean
name|verbose
parameter_list|)
block|{
return|return
name|super
operator|.
name|toString
argument_list|(
name|verbose
argument_list|)
operator|+
literal|" "
operator|+
name|c
return|;
block|}
comment|/**    * Set index of local variable.    */
specifier|public
specifier|final
name|void
name|setIndex
parameter_list|(
name|int
name|n
parameter_list|)
block|{
if|if
condition|(
name|n
operator|<
literal|0
condition|)
throw|throw
operator|new
name|ClassGenException
argument_list|(
literal|"Negative index value: "
operator|+
name|n
argument_list|)
throw|;
name|this
operator|.
name|n
operator|=
name|n
expr_stmt|;
name|setWide
argument_list|()
expr_stmt|;
block|}
comment|/**    * @return increment factor    */
specifier|public
specifier|final
name|int
name|getIncrement
parameter_list|()
block|{
return|return
name|c
return|;
block|}
comment|/**    * Set increment factor.    */
specifier|public
specifier|final
name|void
name|setIncrement
parameter_list|(
name|int
name|c
parameter_list|)
block|{
name|this
operator|.
name|c
operator|=
name|c
expr_stmt|;
name|setWide
argument_list|()
expr_stmt|;
block|}
comment|/** @return int type    */
specifier|public
name|Type
name|getType
parameter_list|(
name|ConstantPoolGen
name|cp
parameter_list|)
block|{
return|return
name|Type
operator|.
name|INT
return|;
block|}
comment|/**    * Call corresponding visitor method(s). The order is:    * Call visitor methods of implemented interfaces first, then    * call methods according to the class hierarchy in descending order,    * i.e., the most specific visitXXX() call comes last.    *    * @param v Visitor object    */
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
name|visitLocalVariableInstruction
argument_list|(
name|this
argument_list|)
expr_stmt|;
name|v
operator|.
name|visitIINC
argument_list|(
name|this
argument_list|)
expr_stmt|;
block|}
block|}
end_class

end_unit

