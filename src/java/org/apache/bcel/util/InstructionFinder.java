begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
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

begin_comment
comment|/* ====================================================================  * The Apache Software License, Version 1.1  *  * Copyright (c) 2001 The Apache Software Foundation.  All rights  * reserved.  *  * Redistribution and use in source and binary forms, with or without  * modification, are permitted provided that the following conditions  * are met:  *  * 1. Redistributions of source code must retain the above copyright  *    notice, this list of conditions and the following disclaimer.  *  * 2. Redistributions in binary form must reproduce the above copyright  *    notice, this list of conditions and the following disclaimer in  *    the documentation and/or other materials provided with the  *    distribution.  *  * 3. The end-user documentation included with the redistribution,  *    if any, must include the following acknowledgment:  *       "This product includes software developed by the  *        Apache Software Foundation (http://www.apache.org/)."  *    Alternately, this acknowledgment may appear in the software itself,  *    if and wherever such third-party acknowledgments normally appear.  *  * 4. The names "Apache" and "Apache Software Foundation" and  *    "Apache BCEL" must not be used to endorse or promote products  *    derived from this software without prior written permission. For  *    written permission, please contact apache@apache.org.  *  * 5. Products derived from this software may not be called "Apache",  *    "Apache BCEL", nor may "Apache" appear in their name, without  *    prior written permission of the Apache Software Foundation.  *  * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED  * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES  * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE  * DISCLAIMED.  IN NO EVENT SHALL THE APACHE SOFTWARE FOUNDATION OR  * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,  * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT  * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF  * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND  * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,  * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT  * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF  * SUCH DAMAGE.  * ====================================================================  *  * This software consists of voluntary contributions made by many  * individuals on behalf of the Apache Software Foundation.  For more  * information on the Apache Software Foundation, please see  *<http://www.apache.org/>.  */
end_comment

begin_import
import|import
name|java
operator|.
name|util
operator|.
name|*
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
name|generic
operator|.
name|*
import|;
end_import

begin_import
import|import
name|org
operator|.
name|apache
operator|.
name|regexp
operator|.
name|*
import|;
end_import

begin_comment
comment|/**   * InstructionFinder is a tool to search for given instructions patterns,  * i.e., match sequences of instructions in an instruction list via  * regular expressions. This can be used, e.g., in order to implement  * a peep hole optimizer that looks for code patterns and replaces  * them with faster equivalents.  *  *<p>This class internally uses the<a href="http://jakarta.apache.org/regexp/">  * Regexp</a> package to search for regular expressions.  *  * A typical application would look like this:<pre>     InstructionFinder f   = new InstructionFinder(il);     String            pat = "IfInstruction ICONST_0 GOTO ICONST_1 NOP (IFEQ|IFNE)";          for(Iterator i = f.search(pat, constraint); i.hasNext(); ) {       InstructionHandle[] match = (InstructionHandle[])i.next();       ...       il.delete(match[1], match[5]);       ...     }</pre>  * @version $Id$  * @author<A HREF="mailto:m.dahm@gmx.de">M. Dahm</A>  * @see Instruction  * @see InstructionList  */
end_comment

begin_class
specifier|public
class|class
name|InstructionFinder
block|{
specifier|private
specifier|static
specifier|final
name|int
name|OFFSET
init|=
literal|32767
decl_stmt|;
comment|// char + OFFSET is outside of LATIN-1
specifier|private
specifier|static
specifier|final
name|int
name|NO_OPCODES
init|=
literal|256
decl_stmt|;
comment|// Potential number, some are not used
specifier|private
specifier|static
specifier|final
name|HashMap
name|map
init|=
operator|new
name|HashMap
argument_list|()
decl_stmt|;
comment|// Map<String,Pattern>
specifier|private
name|InstructionList
name|il
decl_stmt|;
specifier|private
name|String
name|il_string
decl_stmt|;
comment|// instruction list as string
specifier|private
name|InstructionHandle
index|[]
name|handles
decl_stmt|;
comment|// map instruction list to array
comment|/**    * @param il instruction list to search for given patterns    */
specifier|public
name|InstructionFinder
parameter_list|(
name|InstructionList
name|il
parameter_list|)
block|{
name|this
operator|.
name|il
operator|=
name|il
expr_stmt|;
name|reread
argument_list|()
expr_stmt|;
block|}
comment|/**    * Reread the instruction list, e.g., after you've altered the list upon a match.    */
specifier|public
specifier|final
name|void
name|reread
parameter_list|()
block|{
name|int
name|size
init|=
name|il
operator|.
name|getLength
argument_list|()
decl_stmt|;
name|char
index|[]
name|buf
init|=
operator|new
name|char
index|[
name|size
index|]
decl_stmt|;
comment|// Create a string with length equal to il length
name|handles
operator|=
name|il
operator|.
name|getInstructionHandles
argument_list|()
expr_stmt|;
comment|// Map opcodes to characters
for|for
control|(
name|int
name|i
init|=
literal|0
init|;
name|i
operator|<
name|size
condition|;
name|i
operator|++
control|)
name|buf
index|[
name|i
index|]
operator|=
name|makeChar
argument_list|(
name|handles
index|[
name|i
index|]
operator|.
name|getInstruction
argument_list|()
operator|.
name|getOpcode
argument_list|()
argument_list|)
expr_stmt|;
name|il_string
operator|=
operator|new
name|String
argument_list|(
name|buf
argument_list|)
expr_stmt|;
block|}
comment|/**    * Map symbolic instruction names like "getfield" to a single character.    *    * @param pattern instruction pattern in lower case    * @return encoded string for a pattern such as "BranchInstruction".    */
specifier|private
specifier|static
specifier|final
name|String
name|mapName
parameter_list|(
name|String
name|pattern
parameter_list|)
block|{
name|String
name|result
init|=
operator|(
name|String
operator|)
name|map
operator|.
name|get
argument_list|(
name|pattern
argument_list|)
decl_stmt|;
if|if
condition|(
name|result
operator|!=
literal|null
condition|)
return|return
name|result
return|;
for|for
control|(
name|short
name|i
init|=
literal|0
init|;
name|i
operator|<
name|NO_OPCODES
condition|;
name|i
operator|++
control|)
if|if
condition|(
name|pattern
operator|.
name|equals
argument_list|(
name|Constants
operator|.
name|OPCODE_NAMES
index|[
name|i
index|]
argument_list|)
condition|)
return|return
literal|""
operator|+
name|makeChar
argument_list|(
name|i
argument_list|)
return|;
throw|throw
operator|new
name|RuntimeException
argument_list|(
literal|"Instruction unknown: "
operator|+
name|pattern
argument_list|)
throw|;
block|}
comment|/**    * Replace symbolic names of instructions with the appropiate character and remove    * all white space from string. Meta characters such as +, * are ignored.    *    * @param pattern The pattern to compile    * @return translated regular expression string    */
specifier|private
specifier|static
specifier|final
name|String
name|compilePattern
parameter_list|(
name|String
name|pattern
parameter_list|)
block|{
name|String
name|lower
init|=
name|pattern
operator|.
name|toLowerCase
argument_list|()
decl_stmt|;
name|StringBuffer
name|buf
init|=
operator|new
name|StringBuffer
argument_list|()
decl_stmt|;
name|int
name|size
init|=
name|pattern
operator|.
name|length
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
name|size
condition|;
name|i
operator|++
control|)
block|{
name|char
name|ch
init|=
name|lower
operator|.
name|charAt
argument_list|(
name|i
argument_list|)
decl_stmt|;
if|if
condition|(
name|Character
operator|.
name|isLetterOrDigit
argument_list|(
name|ch
argument_list|)
condition|)
block|{
name|StringBuffer
name|name
init|=
operator|new
name|StringBuffer
argument_list|()
decl_stmt|;
while|while
condition|(
operator|(
name|Character
operator|.
name|isLetterOrDigit
argument_list|(
name|ch
argument_list|)
operator|||
name|ch
operator|==
literal|'_'
operator|)
operator|&&
name|i
operator|<
name|size
condition|)
block|{
name|name
operator|.
name|append
argument_list|(
name|ch
argument_list|)
expr_stmt|;
if|if
condition|(
operator|++
name|i
operator|<
name|size
condition|)
name|ch
operator|=
name|lower
operator|.
name|charAt
argument_list|(
name|i
argument_list|)
expr_stmt|;
else|else
break|break;
block|}
name|i
operator|--
expr_stmt|;
name|buf
operator|.
name|append
argument_list|(
name|mapName
argument_list|(
name|name
operator|.
name|toString
argument_list|()
argument_list|)
argument_list|)
expr_stmt|;
block|}
if|else if
condition|(
operator|!
name|Character
operator|.
name|isWhitespace
argument_list|(
name|ch
argument_list|)
condition|)
name|buf
operator|.
name|append
argument_list|(
name|ch
argument_list|)
expr_stmt|;
block|}
return|return
name|buf
operator|.
name|toString
argument_list|()
return|;
block|}
comment|/**    * @return the matched piece of code as an array of instruction (handles)    */
specifier|private
name|InstructionHandle
index|[]
name|getMatch
parameter_list|(
name|int
name|matched_from
parameter_list|,
name|int
name|match_length
parameter_list|)
block|{
name|InstructionHandle
index|[]
name|match
init|=
operator|new
name|InstructionHandle
index|[
name|match_length
index|]
decl_stmt|;
name|System
operator|.
name|arraycopy
argument_list|(
name|handles
argument_list|,
name|matched_from
argument_list|,
name|match
argument_list|,
literal|0
argument_list|,
name|match_length
argument_list|)
expr_stmt|;
return|return
name|match
return|;
block|}
comment|/**    * Search for the given pattern in the instruction list. You can search for any valid    * opcode via its symbolic name, e.g. "istore". You can also use a super class or    * an interface name to match a whole set of instructions, e.g. "BranchInstruction" or    * "LoadInstruction". "istore" is also an alias for all "istore_x" instructions. Additional    * aliases are "if" for "ifxx", "if_icmp" for "if_icmpxx", "if_acmp" for "if_acmpxx".    *    * Consecutive instruction names must be separated by white space which will be removed    * during the compilation of the pattern.    *    * For the rest the usual pattern matching rules for regular expressions apply.<P>    * Example pattern:    *<pre>      search("BranchInstruction NOP ((IfInstruction|GOTO)+ ISTORE Instruction)*");    *</pre>    *    *<p>If you alter the instruction list upon a match such that other    * matching areas are affected, you should call reread() to update    * the finder and call search() again, because the matches are cached.    *    * @param pattern the instruction pattern to search for, where case is ignored    * @param from where to start the search in the instruction list    * @param constraint optional CodeConstraint to check the found code pattern for    * user-defined constraints    * @return iterator of matches where e.nextElement() returns an array of instruction handles    * describing the matched area     */
specifier|public
specifier|final
name|Iterator
name|search
parameter_list|(
name|String
name|pattern
parameter_list|,
name|InstructionHandle
name|from
parameter_list|,
name|CodeConstraint
name|constraint
parameter_list|)
block|{
name|String
name|search
init|=
name|compilePattern
argument_list|(
name|pattern
argument_list|)
decl_stmt|;
name|int
name|start
init|=
operator|-
literal|1
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
name|handles
operator|.
name|length
condition|;
name|i
operator|++
control|)
block|{
if|if
condition|(
name|handles
index|[
name|i
index|]
operator|==
name|from
condition|)
block|{
name|start
operator|=
name|i
expr_stmt|;
comment|// Where to start search from (index)
break|break;
block|}
block|}
if|if
condition|(
name|start
operator|==
operator|-
literal|1
condition|)
throw|throw
operator|new
name|ClassGenException
argument_list|(
literal|"Instruction handle "
operator|+
name|from
operator|+
literal|" not found in instruction list."
argument_list|)
throw|;
try|try
block|{
name|RE
name|regex
init|=
operator|new
name|RE
argument_list|(
name|search
argument_list|)
decl_stmt|;
name|ArrayList
name|matches
init|=
operator|new
name|ArrayList
argument_list|()
decl_stmt|;
while|while
condition|(
name|start
operator|<
name|il_string
operator|.
name|length
argument_list|()
operator|&&
name|regex
operator|.
name|match
argument_list|(
name|il_string
argument_list|,
name|start
argument_list|)
condition|)
block|{
name|int
name|startExpr
init|=
name|regex
operator|.
name|getParenStart
argument_list|(
literal|0
argument_list|)
decl_stmt|;
name|int
name|endExpr
init|=
name|regex
operator|.
name|getParenEnd
argument_list|(
literal|0
argument_list|)
decl_stmt|;
name|int
name|lenExpr
init|=
name|regex
operator|.
name|getParenLength
argument_list|(
literal|0
argument_list|)
decl_stmt|;
name|InstructionHandle
index|[]
name|match
init|=
name|getMatch
argument_list|(
name|startExpr
argument_list|,
name|lenExpr
argument_list|)
decl_stmt|;
if|if
condition|(
operator|(
name|constraint
operator|==
literal|null
operator|)
operator|||
name|constraint
operator|.
name|checkCode
argument_list|(
name|match
argument_list|)
condition|)
name|matches
operator|.
name|add
argument_list|(
name|match
argument_list|)
expr_stmt|;
name|start
operator|=
name|endExpr
expr_stmt|;
block|}
return|return
name|matches
operator|.
name|iterator
argument_list|()
return|;
block|}
catch|catch
parameter_list|(
name|RESyntaxException
name|e
parameter_list|)
block|{
name|System
operator|.
name|err
operator|.
name|println
argument_list|(
name|e
argument_list|)
expr_stmt|;
block|}
return|return
literal|null
return|;
block|}
comment|/**    * Start search beginning from the start of the given instruction list.    *    * @param pattern the instruction pattern to search for, where case is ignored    * @return iterator of matches where e.nextElement()    * returns an array of instruction handles describing the matched    * area    */
specifier|public
specifier|final
name|Iterator
name|search
parameter_list|(
name|String
name|pattern
parameter_list|)
block|{
return|return
name|search
argument_list|(
name|pattern
argument_list|,
name|il
operator|.
name|getStart
argument_list|()
argument_list|,
literal|null
argument_list|)
return|;
block|}
comment|/**    * Start search beginning from `from'.    *    * @param pattern the instruction pattern to search for, where case is ignored    * @param from where to start the search in the instruction list    * @return  iterator of matches where e.nextElement() returns an array of instruction handles    * describing the matched area    */
specifier|public
specifier|final
name|Iterator
name|search
parameter_list|(
name|String
name|pattern
parameter_list|,
name|InstructionHandle
name|from
parameter_list|)
block|{
return|return
name|search
argument_list|(
name|pattern
argument_list|,
name|from
argument_list|,
literal|null
argument_list|)
return|;
block|}
comment|/**    * Start search beginning from the start of the given instruction list.    * Check found matches with the constraint object.    *    * @param pattern the instruction pattern to search for, case is ignored    * @param constraint constraints to be checked on matching code    * @return instruction handle or `null' if the match failed    */
specifier|public
specifier|final
name|Iterator
name|search
parameter_list|(
name|String
name|pattern
parameter_list|,
name|CodeConstraint
name|constraint
parameter_list|)
block|{
return|return
name|search
argument_list|(
name|pattern
argument_list|,
name|il
operator|.
name|getStart
argument_list|()
argument_list|,
name|constraint
argument_list|)
return|;
block|}
comment|/**    * Convert opcode number to char.    */
specifier|private
specifier|static
specifier|final
name|char
name|makeChar
parameter_list|(
name|short
name|opcode
parameter_list|)
block|{
return|return
operator|(
name|char
operator|)
operator|(
name|opcode
operator|+
name|OFFSET
operator|)
return|;
block|}
comment|/**    * @return the inquired instruction list    */
specifier|public
specifier|final
name|InstructionList
name|getInstructionList
parameter_list|()
block|{
return|return
name|il
return|;
block|}
comment|/**    * Code patterns found may be checked using an additional    * user-defined constraint object whether they really match the needed criterion.    * I.e., check constraints that can not expressed with regular expressions.    *    */
specifier|public
interface|interface
name|CodeConstraint
block|{
comment|/**      * @param match array of instructions matching the requested pattern      * @return true if the matched area is really useful      */
specifier|public
name|boolean
name|checkCode
parameter_list|(
name|InstructionHandle
index|[]
name|match
parameter_list|)
function_decl|;
block|}
comment|// Initialize pattern map
static|static
block|{
name|map
operator|.
name|put
argument_list|(
literal|"arithmeticinstruction"
argument_list|,
literal|"(irem|lrem|iand|ior|ineg|isub|lneg|fneg|fmul|ldiv|fadd|lxor|frem|idiv|land|ixor|ishr|fsub|lshl|fdiv|iadd|lor|dmul|lsub|ishl|imul|lmul|lushr|dneg|iushr|lshr|ddiv|drem|dadd|ladd|dsub)"
argument_list|)
expr_stmt|;
name|map
operator|.
name|put
argument_list|(
literal|"invokeinstruction"
argument_list|,
literal|"(invokevirtual|invokeinterface|invokestatic|invokespecial)"
argument_list|)
expr_stmt|;
name|map
operator|.
name|put
argument_list|(
literal|"arrayinstruction"
argument_list|,
literal|"(baload|aastore|saload|caload|fastore|lastore|iaload|castore|iastore|aaload|bastore|sastore|faload|laload|daload|dastore)"
argument_list|)
expr_stmt|;
name|map
operator|.
name|put
argument_list|(
literal|"gotoinstruction"
argument_list|,
literal|"(goto|goto_w)"
argument_list|)
expr_stmt|;
name|map
operator|.
name|put
argument_list|(
literal|"conversioninstruction"
argument_list|,
literal|"(d2l|l2d|i2s|d2i|l2i|i2b|l2f|d2f|f2i|i2d|i2l|f2d|i2c|f2l|i2f)"
argument_list|)
expr_stmt|;
name|map
operator|.
name|put
argument_list|(
literal|"localvariableinstruction"
argument_list|,
literal|"(fstore|iinc|lload|dstore|dload|iload|aload|astore|istore|fload|lstore)"
argument_list|)
expr_stmt|;
name|map
operator|.
name|put
argument_list|(
literal|"loadinstruction"
argument_list|,
literal|"(fload|dload|lload|iload|aload)"
argument_list|)
expr_stmt|;
name|map
operator|.
name|put
argument_list|(
literal|"fieldinstruction"
argument_list|,
literal|"(getfield|putstatic|getstatic|putfield)"
argument_list|)
expr_stmt|;
name|map
operator|.
name|put
argument_list|(
literal|"cpinstruction"
argument_list|,
literal|"(ldc2_w|invokeinterface|multianewarray|putstatic|instanceof|getstatic|checkcast|getfield|invokespecial|ldc_w|invokestatic|invokevirtual|putfield|ldc|new|anewarray)"
argument_list|)
expr_stmt|;
name|map
operator|.
name|put
argument_list|(
literal|"stackinstruction"
argument_list|,
literal|"(dup2|swap|dup2_x2|pop|pop2|dup|dup2_x1|dup_x2|dup_x1)"
argument_list|)
expr_stmt|;
name|map
operator|.
name|put
argument_list|(
literal|"branchinstruction"
argument_list|,
literal|"(ifle|if_acmpne|if_icmpeq|if_acmpeq|ifnonnull|goto_w|iflt|ifnull|if_icmpne|tableswitch|if_icmple|ifeq|if_icmplt|jsr_w|if_icmpgt|ifgt|jsr|goto|ifne|ifge|lookupswitch|if_icmpge)"
argument_list|)
expr_stmt|;
name|map
operator|.
name|put
argument_list|(
literal|"returninstruction"
argument_list|,
literal|"(lreturn|ireturn|freturn|dreturn|areturn|return)"
argument_list|)
expr_stmt|;
name|map
operator|.
name|put
argument_list|(
literal|"storeinstruction"
argument_list|,
literal|"(istore|fstore|dstore|astore|lstore)"
argument_list|)
expr_stmt|;
name|map
operator|.
name|put
argument_list|(
literal|"select"
argument_list|,
literal|"(tableswitch|lookupswitch)"
argument_list|)
expr_stmt|;
name|map
operator|.
name|put
argument_list|(
literal|"ifinstruction"
argument_list|,
literal|"(ifeq|ifgt|if_icmpne|if_icmpeq|ifge|ifnull|ifne|if_icmple|if_icmpge|if_acmpeq|if_icmplt|if_acmpne|ifnonnull|iflt|if_icmpgt|ifle)"
argument_list|)
expr_stmt|;
name|map
operator|.
name|put
argument_list|(
literal|"jsrinstruction"
argument_list|,
literal|"(jsr|jsr_w)"
argument_list|)
expr_stmt|;
name|map
operator|.
name|put
argument_list|(
literal|"variablelengthinstruction"
argument_list|,
literal|"(tableswitch|jsr|goto|lookupswitch)"
argument_list|)
expr_stmt|;
name|map
operator|.
name|put
argument_list|(
literal|"unconditionalbranch"
argument_list|,
literal|"(goto|jsr|jsr_w|athrow|goto_w)"
argument_list|)
expr_stmt|;
name|map
operator|.
name|put
argument_list|(
literal|"constantpushinstruction"
argument_list|,
literal|"(dconst|bipush|sipush|fconst|iconst|lconst)"
argument_list|)
expr_stmt|;
name|map
operator|.
name|put
argument_list|(
literal|"typedinstruction"
argument_list|,
literal|"(imul|lsub|aload|fload|lor|new|aaload|fcmpg|iand|iaload|lrem|idiv|d2l|isub|dcmpg|dastore|ret|f2d|f2i|drem|iinc|i2c|checkcast|frem|lreturn|astore|lushr|daload|dneg|fastore|istore|lshl|ldiv|lstore|areturn|ishr|ldc_w|invokeinterface|aastore|lxor|ishl|l2d|i2f|return|faload|sipush|iushr|caload|instanceof|invokespecial|putfield|fmul|ireturn|laload|d2f|lneg|ixor|i2l|fdiv|lastore|multianewarray|i2b|getstatic|i2d|putstatic|fcmpl|saload|ladd|irem|dload|jsr_w|dconst|dcmpl|fsub|freturn|ldc|aconst_null|castore|lmul|ldc2_w|dadd|iconst|f2l|ddiv|dstore|land|jsr|anewarray|dmul|bipush|dsub|sastore|d2i|i2s|lshr|iadd|l2i|lload|bastore|fstore|fneg|iload|fadd|baload|fconst|ior|ineg|dreturn|l2f|lconst|getfield|invokevirtual|invokestatic|iastore)"
argument_list|)
expr_stmt|;
name|map
operator|.
name|put
argument_list|(
literal|"popinstruction"
argument_list|,
literal|"(fstore|dstore|pop|pop2|astore|putstatic|istore|lstore)"
argument_list|)
expr_stmt|;
name|map
operator|.
name|put
argument_list|(
literal|"allocationinstruction"
argument_list|,
literal|"(multianewarray|new|anewarray|newarray)"
argument_list|)
expr_stmt|;
name|map
operator|.
name|put
argument_list|(
literal|"indexedinstruction"
argument_list|,
literal|"(lload|lstore|fload|ldc2_w|invokeinterface|multianewarray|astore|dload|putstatic|instanceof|getstatic|checkcast|getfield|invokespecial|dstore|istore|iinc|ldc_w|ret|fstore|invokestatic|iload|putfield|invokevirtual|ldc|new|aload|anewarray)"
argument_list|)
expr_stmt|;
name|map
operator|.
name|put
argument_list|(
literal|"pushinstruction"
argument_list|,
literal|"(dup|lload|dup2|bipush|fload|ldc2_w|sipush|lconst|fconst|dload|getstatic|ldc_w|aconst_null|dconst|iload|ldc|iconst|aload)"
argument_list|)
expr_stmt|;
name|map
operator|.
name|put
argument_list|(
literal|"stackproducer"
argument_list|,
literal|"(imul|lsub|aload|fload|lor|new|aaload|fcmpg|iand|iaload|lrem|idiv|d2l|isub|dcmpg|dup|f2d|f2i|drem|i2c|checkcast|frem|lushr|daload|dneg|lshl|ldiv|ishr|ldc_w|invokeinterface|lxor|ishl|l2d|i2f|faload|sipush|iushr|caload|instanceof|invokespecial|fmul|laload|d2f|lneg|ixor|i2l|fdiv|getstatic|i2b|swap|i2d|dup2|fcmpl|saload|ladd|irem|dload|jsr_w|dconst|dcmpl|fsub|ldc|arraylength|aconst_null|tableswitch|lmul|ldc2_w|iconst|dadd|f2l|ddiv|land|jsr|anewarray|dmul|bipush|dsub|d2i|newarray|i2s|lshr|iadd|lload|l2i|fneg|iload|fadd|baload|fconst|lookupswitch|ior|ineg|lconst|l2f|getfield|invokevirtual|invokestatic)"
argument_list|)
expr_stmt|;
name|map
operator|.
name|put
argument_list|(
literal|"stackconsumer"
argument_list|,
literal|"(imul|lsub|lor|iflt|fcmpg|if_icmpgt|iand|ifeq|if_icmplt|lrem|ifnonnull|idiv|d2l|isub|dcmpg|dastore|if_icmpeq|f2d|f2i|drem|i2c|checkcast|frem|lreturn|astore|lushr|pop2|monitorexit|dneg|fastore|istore|lshl|ldiv|lstore|areturn|if_icmpge|ishr|monitorenter|invokeinterface|aastore|lxor|ishl|l2d|i2f|return|iushr|instanceof|invokespecial|fmul|ireturn|d2f|lneg|ixor|pop|i2l|ifnull|fdiv|lastore|i2b|if_acmpeq|ifge|swap|i2d|putstatic|fcmpl|ladd|irem|dcmpl|fsub|freturn|ifgt|castore|lmul|dadd|f2l|ddiv|dstore|land|if_icmpne|if_acmpne|dmul|dsub|sastore|ifle|d2i|i2s|lshr|iadd|l2i|bastore|fstore|fneg|fadd|ior|ineg|ifne|dreturn|l2f|if_icmple|getfield|invokevirtual|invokestatic|iastore)"
argument_list|)
expr_stmt|;
name|map
operator|.
name|put
argument_list|(
literal|"exceptionthrower"
argument_list|,
literal|"(irem|lrem|laload|putstatic|baload|dastore|areturn|getstatic|ldiv|anewarray|iastore|castore|idiv|saload|lastore|fastore|putfield|lreturn|caload|getfield|return|aastore|freturn|newarray|instanceof|multianewarray|athrow|faload|iaload|aaload|dreturn|monitorenter|checkcast|bastore|arraylength|new|invokevirtual|sastore|ldc_w|ireturn|invokespecial|monitorexit|invokeinterface|ldc|invokestatic|daload)"
argument_list|)
expr_stmt|;
name|map
operator|.
name|put
argument_list|(
literal|"loadclass"
argument_list|,
literal|"(multianewarray|invokeinterface|instanceof|invokespecial|putfield|checkcast|putstatic|invokevirtual|new|getstatic|invokestatic|getfield|anewarray)"
argument_list|)
expr_stmt|;
name|map
operator|.
name|put
argument_list|(
literal|"instructiontargeter"
argument_list|,
literal|"(ifle|if_acmpne|if_icmpeq|if_acmpeq|ifnonnull|goto_w|iflt|ifnull|if_icmpne|tableswitch|if_icmple|ifeq|if_icmplt|jsr_w|if_icmpgt|ifgt|jsr|goto|ifne|ifge|lookupswitch|if_icmpge)"
argument_list|)
expr_stmt|;
comment|// Some aliases
name|map
operator|.
name|put
argument_list|(
literal|"if_icmp"
argument_list|,
literal|"(if_icmpne|if_icmpeq|if_icmple|if_icmpge|if_icmplt|if_icmpgt)"
argument_list|)
expr_stmt|;
name|map
operator|.
name|put
argument_list|(
literal|"if_acmp"
argument_list|,
literal|"(if_acmpeq|if_acmpne)"
argument_list|)
expr_stmt|;
name|map
operator|.
name|put
argument_list|(
literal|"if"
argument_list|,
literal|"(ifeq|ifne|iflt|ifge|ifgt|ifle)"
argument_list|)
expr_stmt|;
comment|// Precompile some aliases first
name|map
operator|.
name|put
argument_list|(
literal|"iconst"
argument_list|,
name|precompile
argument_list|(
name|Constants
operator|.
name|ICONST_0
argument_list|,
name|Constants
operator|.
name|ICONST_5
argument_list|,
name|Constants
operator|.
name|ICONST_M1
argument_list|)
argument_list|)
expr_stmt|;
name|map
operator|.
name|put
argument_list|(
literal|"lconst"
argument_list|,
operator|new
name|String
argument_list|(
operator|new
name|char
index|[]
block|{
literal|'('
block|,
name|makeChar
argument_list|(
name|Constants
operator|.
name|LCONST_0
argument_list|)
block|,
literal|'|'
block|,
name|makeChar
argument_list|(
name|Constants
operator|.
name|LCONST_1
argument_list|)
block|,
literal|')'
block|}
argument_list|)
argument_list|)
expr_stmt|;
name|map
operator|.
name|put
argument_list|(
literal|"dconst"
argument_list|,
operator|new
name|String
argument_list|(
operator|new
name|char
index|[]
block|{
literal|'('
block|,
name|makeChar
argument_list|(
name|Constants
operator|.
name|DCONST_0
argument_list|)
block|,
literal|'|'
block|,
name|makeChar
argument_list|(
name|Constants
operator|.
name|DCONST_1
argument_list|)
block|,
literal|')'
block|}
argument_list|)
argument_list|)
expr_stmt|;
name|map
operator|.
name|put
argument_list|(
literal|"fconst"
argument_list|,
operator|new
name|String
argument_list|(
operator|new
name|char
index|[]
block|{
literal|'('
block|,
name|makeChar
argument_list|(
name|Constants
operator|.
name|FCONST_0
argument_list|)
block|,
literal|'|'
block|,
name|makeChar
argument_list|(
name|Constants
operator|.
name|FCONST_1
argument_list|)
block|,
literal|')'
block|}
argument_list|)
argument_list|)
expr_stmt|;
name|map
operator|.
name|put
argument_list|(
literal|"iload"
argument_list|,
name|precompile
argument_list|(
name|Constants
operator|.
name|ILOAD_0
argument_list|,
name|Constants
operator|.
name|ILOAD_3
argument_list|,
name|Constants
operator|.
name|ILOAD
argument_list|)
argument_list|)
expr_stmt|;
name|map
operator|.
name|put
argument_list|(
literal|"dload"
argument_list|,
name|precompile
argument_list|(
name|Constants
operator|.
name|DLOAD_0
argument_list|,
name|Constants
operator|.
name|DLOAD_3
argument_list|,
name|Constants
operator|.
name|DLOAD
argument_list|)
argument_list|)
expr_stmt|;
name|map
operator|.
name|put
argument_list|(
literal|"fload"
argument_list|,
name|precompile
argument_list|(
name|Constants
operator|.
name|FLOAD_0
argument_list|,
name|Constants
operator|.
name|FLOAD_3
argument_list|,
name|Constants
operator|.
name|FLOAD
argument_list|)
argument_list|)
expr_stmt|;
name|map
operator|.
name|put
argument_list|(
literal|"aload"
argument_list|,
name|precompile
argument_list|(
name|Constants
operator|.
name|ALOAD_0
argument_list|,
name|Constants
operator|.
name|ALOAD_3
argument_list|,
name|Constants
operator|.
name|ALOAD
argument_list|)
argument_list|)
expr_stmt|;
name|map
operator|.
name|put
argument_list|(
literal|"istore"
argument_list|,
name|precompile
argument_list|(
name|Constants
operator|.
name|ISTORE_0
argument_list|,
name|Constants
operator|.
name|ISTORE_3
argument_list|,
name|Constants
operator|.
name|ISTORE
argument_list|)
argument_list|)
expr_stmt|;
name|map
operator|.
name|put
argument_list|(
literal|"dstore"
argument_list|,
name|precompile
argument_list|(
name|Constants
operator|.
name|DSTORE_0
argument_list|,
name|Constants
operator|.
name|DSTORE_3
argument_list|,
name|Constants
operator|.
name|DSTORE
argument_list|)
argument_list|)
expr_stmt|;
name|map
operator|.
name|put
argument_list|(
literal|"fstore"
argument_list|,
name|precompile
argument_list|(
name|Constants
operator|.
name|FSTORE_0
argument_list|,
name|Constants
operator|.
name|FSTORE_3
argument_list|,
name|Constants
operator|.
name|FSTORE
argument_list|)
argument_list|)
expr_stmt|;
name|map
operator|.
name|put
argument_list|(
literal|"astore"
argument_list|,
name|precompile
argument_list|(
name|Constants
operator|.
name|ASTORE_0
argument_list|,
name|Constants
operator|.
name|ASTORE_3
argument_list|,
name|Constants
operator|.
name|ASTORE
argument_list|)
argument_list|)
expr_stmt|;
comment|// Compile strings
for|for
control|(
name|Iterator
name|i
init|=
name|map
operator|.
name|keySet
argument_list|()
operator|.
name|iterator
argument_list|()
init|;
name|i
operator|.
name|hasNext
argument_list|()
condition|;
control|)
block|{
name|String
name|key
init|=
operator|(
name|String
operator|)
name|i
operator|.
name|next
argument_list|()
decl_stmt|;
name|String
name|value
init|=
operator|(
name|String
operator|)
name|map
operator|.
name|get
argument_list|(
name|key
argument_list|)
decl_stmt|;
name|char
name|ch
init|=
name|value
operator|.
name|charAt
argument_list|(
literal|1
argument_list|)
decl_stmt|;
comment|// Omit already precompiled patterns
if|if
condition|(
name|ch
operator|<
name|OFFSET
condition|)
block|{
name|map
operator|.
name|put
argument_list|(
name|key
argument_list|,
name|compilePattern
argument_list|(
name|value
argument_list|)
argument_list|)
expr_stmt|;
comment|// precompile all patterns
block|}
block|}
comment|// Add instruction alias to match anything
name|StringBuffer
name|buf
init|=
operator|new
name|StringBuffer
argument_list|(
literal|"("
argument_list|)
decl_stmt|;
for|for
control|(
name|short
name|i
init|=
literal|0
init|;
name|i
operator|<
name|NO_OPCODES
condition|;
name|i
operator|++
control|)
block|{
if|if
condition|(
name|Constants
operator|.
name|NO_OF_OPERANDS
index|[
name|i
index|]
operator|!=
name|Constants
operator|.
name|UNDEFINED
condition|)
block|{
comment|// Not an invalid opcode
name|buf
operator|.
name|append
argument_list|(
name|makeChar
argument_list|(
name|i
argument_list|)
argument_list|)
expr_stmt|;
if|if
condition|(
name|i
operator|<
name|NO_OPCODES
operator|-
literal|1
condition|)
name|buf
operator|.
name|append
argument_list|(
literal|'|'
argument_list|)
expr_stmt|;
block|}
block|}
name|buf
operator|.
name|append
argument_list|(
literal|')'
argument_list|)
expr_stmt|;
name|map
operator|.
name|put
argument_list|(
literal|"instruction"
argument_list|,
name|buf
operator|.
name|toString
argument_list|()
argument_list|)
expr_stmt|;
block|}
specifier|private
specifier|static
name|String
name|precompile
parameter_list|(
name|short
name|from
parameter_list|,
name|short
name|to
parameter_list|,
name|short
name|extra
parameter_list|)
block|{
name|StringBuffer
name|buf
init|=
operator|new
name|StringBuffer
argument_list|(
literal|"("
argument_list|)
decl_stmt|;
for|for
control|(
name|short
name|i
init|=
name|from
init|;
name|i
operator|<=
name|to
condition|;
name|i
operator|++
control|)
block|{
name|buf
operator|.
name|append
argument_list|(
name|makeChar
argument_list|(
name|i
argument_list|)
argument_list|)
expr_stmt|;
name|buf
operator|.
name|append
argument_list|(
literal|'|'
argument_list|)
expr_stmt|;
block|}
name|buf
operator|.
name|append
argument_list|(
name|makeChar
argument_list|(
name|extra
argument_list|)
argument_list|)
expr_stmt|;
name|buf
operator|.
name|append
argument_list|(
literal|")"
argument_list|)
expr_stmt|;
return|return
name|buf
operator|.
name|toString
argument_list|()
return|;
block|}
comment|/*    * Internal debugging routines.    */
specifier|private
specifier|static
specifier|final
name|String
name|pattern2string
parameter_list|(
name|String
name|pattern
parameter_list|)
block|{
return|return
name|pattern2string
argument_list|(
name|pattern
argument_list|,
literal|true
argument_list|)
return|;
block|}
specifier|private
specifier|static
specifier|final
name|String
name|pattern2string
parameter_list|(
name|String
name|pattern
parameter_list|,
name|boolean
name|make_string
parameter_list|)
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
literal|0
init|;
name|i
operator|<
name|pattern
operator|.
name|length
argument_list|()
condition|;
name|i
operator|++
control|)
block|{
name|char
name|ch
init|=
name|pattern
operator|.
name|charAt
argument_list|(
name|i
argument_list|)
decl_stmt|;
if|if
condition|(
name|ch
operator|>=
name|OFFSET
condition|)
block|{
if|if
condition|(
name|make_string
condition|)
name|buf
operator|.
name|append
argument_list|(
name|Constants
operator|.
name|OPCODE_NAMES
index|[
name|ch
operator|-
name|OFFSET
index|]
argument_list|)
expr_stmt|;
else|else
name|buf
operator|.
name|append
argument_list|(
operator|(
name|int
operator|)
operator|(
name|ch
operator|-
name|OFFSET
operator|)
argument_list|)
expr_stmt|;
block|}
else|else
name|buf
operator|.
name|append
argument_list|(
name|ch
argument_list|)
expr_stmt|;
block|}
return|return
name|buf
operator|.
name|toString
argument_list|()
return|;
block|}
block|}
end_class

end_unit

