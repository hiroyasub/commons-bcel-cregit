begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
begin_import
import|import
name|java
operator|.
name|io
operator|.
name|*
import|;
end_import

begin_import
import|import
name|java
operator|.
name|util
operator|.
name|Iterator
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
name|util
operator|.
name|InstructionFinder
import|;
end_import

begin_comment
comment|/**  * Remove NOPs from given class  *  * @version $Id$  * @author<A HREF="mailto:m.dahm@gmx.de">M. Dahm</A>  */
end_comment

begin_class
specifier|public
class|class
name|Peephole
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
block|{
try|try
block|{
comment|/* Load the class from CLASSPATH.        */
name|JavaClass
name|clazz
init|=
name|Repository
operator|.
name|lookupClass
argument_list|(
name|argv
index|[
literal|0
index|]
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
name|ConstantPoolGen
name|cp
init|=
operator|new
name|ConstantPoolGen
argument_list|(
name|clazz
operator|.
name|getConstantPool
argument_list|()
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
name|methods
operator|.
name|length
condition|;
name|i
operator|++
control|)
block|{
if|if
condition|(
operator|!
operator|(
name|methods
index|[
name|i
index|]
operator|.
name|isAbstract
argument_list|()
operator|||
name|methods
index|[
name|i
index|]
operator|.
name|isNative
argument_list|()
operator|)
condition|)
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
name|clazz
operator|.
name|getClassName
argument_list|()
argument_list|,
name|cp
argument_list|)
decl_stmt|;
name|Method
name|stripped
init|=
name|removeNOPs
argument_list|(
name|mg
argument_list|)
decl_stmt|;
if|if
condition|(
name|stripped
operator|!=
literal|null
condition|)
comment|// Any NOPs stripped?
name|methods
index|[
name|i
index|]
operator|=
name|stripped
expr_stmt|;
comment|// Overwrite with stripped method
block|}
block|}
comment|/* Dump the class to<class name>_.class        */
name|clazz
operator|.
name|setConstantPool
argument_list|(
name|cp
operator|.
name|getFinalConstantPool
argument_list|()
argument_list|)
expr_stmt|;
name|clazz
operator|.
name|dump
argument_list|(
name|clazz
operator|.
name|getClassName
argument_list|()
operator|+
literal|"_.class"
argument_list|)
expr_stmt|;
block|}
catch|catch
parameter_list|(
name|Exception
name|e
parameter_list|)
block|{
name|e
operator|.
name|printStackTrace
argument_list|()
expr_stmt|;
block|}
block|}
specifier|private
specifier|static
specifier|final
name|Method
name|removeNOPs
parameter_list|(
name|MethodGen
name|mg
parameter_list|)
block|{
name|InstructionList
name|il
init|=
name|mg
operator|.
name|getInstructionList
argument_list|()
decl_stmt|;
name|InstructionFinder
name|f
init|=
operator|new
name|InstructionFinder
argument_list|(
name|il
argument_list|)
decl_stmt|;
name|String
name|pat
init|=
literal|"NOP+"
decl_stmt|;
comment|// Find at least one NOP
name|InstructionHandle
name|next
init|=
literal|null
decl_stmt|;
name|int
name|count
init|=
literal|0
decl_stmt|;
for|for
control|(
name|Iterator
name|e
init|=
name|f
operator|.
name|search
argument_list|(
name|pat
argument_list|)
init|;
name|e
operator|.
name|hasNext
argument_list|()
condition|;
control|)
block|{
name|InstructionHandle
index|[]
name|match
init|=
operator|(
name|InstructionHandle
index|[]
operator|)
name|e
operator|.
name|next
argument_list|()
decl_stmt|;
name|InstructionHandle
name|first
init|=
name|match
index|[
literal|0
index|]
decl_stmt|;
name|InstructionHandle
name|last
init|=
name|match
index|[
name|match
operator|.
name|length
operator|-
literal|1
index|]
decl_stmt|;
comment|/* Some nasty Java compilers may add NOP at end of method.        */
if|if
condition|(
operator|(
name|next
operator|=
name|last
operator|.
name|getNext
argument_list|()
operator|)
operator|==
literal|null
condition|)
break|break;
name|count
operator|+=
name|match
operator|.
name|length
expr_stmt|;
comment|/* Delete NOPs and redirect any references to them to the following        * (non-nop) instruction.        */
try|try
block|{
name|il
operator|.
name|delete
argument_list|(
name|first
argument_list|,
name|last
argument_list|)
expr_stmt|;
block|}
catch|catch
parameter_list|(
name|TargetLostException
name|e2
parameter_list|)
block|{
name|InstructionHandle
index|[]
name|targets
init|=
name|e2
operator|.
name|getTargets
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
name|targets
operator|.
name|length
condition|;
name|i
operator|++
control|)
block|{
name|InstructionTargeter
index|[]
name|targeters
init|=
name|targets
index|[
name|i
index|]
operator|.
name|getTargeters
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
name|targeters
operator|.
name|length
condition|;
name|j
operator|++
control|)
name|targeters
index|[
name|j
index|]
operator|.
name|updateTarget
argument_list|(
name|targets
index|[
name|i
index|]
argument_list|,
name|next
argument_list|)
expr_stmt|;
block|}
block|}
block|}
name|Method
name|m
init|=
literal|null
decl_stmt|;
if|if
condition|(
name|count
operator|>
literal|0
condition|)
block|{
name|System
operator|.
name|out
operator|.
name|println
argument_list|(
literal|"Removed "
operator|+
name|count
operator|+
literal|" NOP instructions from method "
operator|+
name|mg
operator|.
name|getName
argument_list|()
argument_list|)
expr_stmt|;
name|m
operator|=
name|mg
operator|.
name|getMethod
argument_list|()
expr_stmt|;
block|}
name|il
operator|.
name|dispose
argument_list|()
expr_stmt|;
comment|// Reuse instruction handles
return|return
name|m
return|;
block|}
block|}
end_class

end_unit

