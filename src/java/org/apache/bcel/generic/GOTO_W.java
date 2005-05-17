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
comment|/**   * GOTO_W - Branch always (to relative offset, not absolute address)  *  * @version $Id$  * @author<A HREF="mailto:m.dahm@gmx.de">M. Dahm</A>  */
end_comment

begin_class
specifier|public
class|class
name|GOTO_W
extends|extends
name|GotoInstruction
block|{
comment|/**    * Empty constructor needed for the Class.newInstance() statement in    * Instruction.readInstruction(). Not to be used otherwise.    */
name|GOTO_W
parameter_list|()
block|{
block|}
specifier|public
name|GOTO_W
parameter_list|(
name|InstructionHandle
name|target
parameter_list|)
block|{
name|super
argument_list|(
name|org
operator|.
name|apache
operator|.
name|bcel
operator|.
name|Constants
operator|.
name|GOTO_W
argument_list|,
name|target
argument_list|)
expr_stmt|;
name|length
operator|=
literal|5
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
name|index
operator|=
name|getTargetOffset
argument_list|()
expr_stmt|;
name|out
operator|.
name|writeByte
argument_list|(
name|opcode
argument_list|)
expr_stmt|;
name|out
operator|.
name|writeInt
argument_list|(
name|index
argument_list|)
expr_stmt|;
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
name|index
operator|=
name|bytes
operator|.
name|readInt
argument_list|()
expr_stmt|;
name|length
operator|=
literal|5
expr_stmt|;
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
name|visitUnconditionalBranch
argument_list|(
name|this
argument_list|)
expr_stmt|;
name|v
operator|.
name|visitBranchInstruction
argument_list|(
name|this
argument_list|)
expr_stmt|;
name|v
operator|.
name|visitGotoInstruction
argument_list|(
name|this
argument_list|)
expr_stmt|;
name|v
operator|.
name|visitGOTO_W
argument_list|(
name|this
argument_list|)
expr_stmt|;
block|}
block|}
end_class

end_unit

