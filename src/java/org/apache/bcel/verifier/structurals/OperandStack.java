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
name|verifier
operator|.
name|structurals
package|;
end_package

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
name|verifier
operator|.
name|exc
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
name|*
import|;
end_import

begin_comment
comment|/**  * This class implements a stack used for symbolic JVM stack simulation.  * [It's used an an operand stack substitute.]  * Elements of this stack are org.apache.bcel.generic.Type objects.  *  * @version $Id$  * @author<A HREF="http://www.inf.fu-berlin.de/~ehaase"/>Enver Haase</A>  */
end_comment

begin_class
specifier|public
class|class
name|OperandStack
block|{
comment|/** We hold the stack information here. */
specifier|private
name|ArrayList
name|stack
init|=
operator|new
name|ArrayList
argument_list|()
decl_stmt|;
comment|/** The maximum number of stack slots this OperandStack instance may hold. */
specifier|private
name|int
name|maxStack
decl_stmt|;
comment|/** 	 * Creates an empty stack with a maximum of maxStack slots. 	 */
specifier|public
name|OperandStack
parameter_list|(
name|int
name|maxStack
parameter_list|)
block|{
name|this
operator|.
name|maxStack
operator|=
name|maxStack
expr_stmt|;
block|}
comment|/** 	 * Creates an otherwise empty stack with a maximum of maxStack slots and 	 * the ObjectType 'obj' at the top. 	 */
specifier|public
name|OperandStack
parameter_list|(
name|int
name|maxStack
parameter_list|,
name|ObjectType
name|obj
parameter_list|)
block|{
name|this
operator|.
name|maxStack
operator|=
name|maxStack
expr_stmt|;
name|this
operator|.
name|push
argument_list|(
name|obj
argument_list|)
expr_stmt|;
block|}
comment|/** 	 * Returns a deep copy of this object; that means, the clone operates 	 * on a new stack. However, the Type objects on the stack are 	 * shared. 	 */
specifier|protected
name|Object
name|clone
parameter_list|()
block|{
name|OperandStack
name|newstack
init|=
operator|new
name|OperandStack
argument_list|(
name|this
operator|.
name|maxStack
argument_list|)
decl_stmt|;
name|newstack
operator|.
name|stack
operator|=
operator|(
name|ArrayList
operator|)
name|this
operator|.
name|stack
operator|.
name|clone
argument_list|()
expr_stmt|;
return|return
name|newstack
return|;
block|}
comment|/** 	 * Clears the stack. 	 */
specifier|public
name|void
name|clear
parameter_list|()
block|{
name|stack
operator|=
operator|new
name|ArrayList
argument_list|()
expr_stmt|;
block|}
comment|/** 	 * Returns true if and only if this OperandStack 	 * equals another, meaning equal lengths and equal 	 * objects on the stacks. 	 */
specifier|public
name|boolean
name|equals
parameter_list|(
name|Object
name|o
parameter_list|)
block|{
if|if
condition|(
operator|!
operator|(
name|o
operator|instanceof
name|OperandStack
operator|)
condition|)
return|return
literal|false
return|;
name|OperandStack
name|s
init|=
operator|(
name|OperandStack
operator|)
name|o
decl_stmt|;
return|return
name|this
operator|.
name|stack
operator|.
name|equals
argument_list|(
name|s
operator|.
name|stack
argument_list|)
return|;
block|}
comment|/** 	 * Returns a (typed!) clone of this. 	 * 	 * @see #clone() 	 */
specifier|public
name|OperandStack
name|getClone
parameter_list|()
block|{
return|return
operator|(
name|OperandStack
operator|)
name|this
operator|.
name|clone
argument_list|()
return|;
block|}
comment|/** 	 * Returns true IFF this OperandStack is empty.    */
specifier|public
name|boolean
name|isEmpty
parameter_list|()
block|{
return|return
name|stack
operator|.
name|isEmpty
argument_list|()
return|;
block|}
comment|/** 	 * Returns the number of stack slots this stack can hold. 	 */
specifier|public
name|int
name|maxStack
parameter_list|()
block|{
return|return
name|this
operator|.
name|maxStack
return|;
block|}
comment|/** 	 * Returns the element on top of the stack. The element is not popped off the stack! 	 */
specifier|public
name|Type
name|peek
parameter_list|()
block|{
return|return
name|peek
argument_list|(
literal|0
argument_list|)
return|;
block|}
comment|/**    * Returns the element that's i elements below the top element; that means,    * iff i==0 the top element is returned. The element is not popped off the stack!    */
specifier|public
name|Type
name|peek
parameter_list|(
name|int
name|i
parameter_list|)
block|{
return|return
operator|(
name|Type
operator|)
name|stack
operator|.
name|get
argument_list|(
name|size
argument_list|()
operator|-
name|i
operator|-
literal|1
argument_list|)
return|;
block|}
comment|/** 	 * Returns the element on top of the stack. The element is popped off the stack. 	 */
specifier|public
name|Type
name|pop
parameter_list|()
block|{
name|Type
name|e
init|=
operator|(
name|Type
operator|)
name|stack
operator|.
name|remove
argument_list|(
name|size
argument_list|()
operator|-
literal|1
argument_list|)
decl_stmt|;
return|return
name|e
return|;
block|}
comment|/** 	 * Pops i elements off the stack. ALWAYS RETURNS "null"!!! 	 */
specifier|public
name|Type
name|pop
parameter_list|(
name|int
name|i
parameter_list|)
block|{
for|for
control|(
name|int
name|j
init|=
literal|0
init|;
name|j
operator|<
name|i
condition|;
name|j
operator|++
control|)
block|{
name|pop
argument_list|()
expr_stmt|;
block|}
return|return
literal|null
return|;
block|}
comment|/** 	 * Pushes a Type object onto the stack. 	 */
specifier|public
name|void
name|push
parameter_list|(
name|Type
name|type
parameter_list|)
block|{
if|if
condition|(
name|type
operator|==
literal|null
condition|)
throw|throw
operator|new
name|AssertionViolatedException
argument_list|(
literal|"Cannot push NULL onto OperandStack."
argument_list|)
throw|;
if|if
condition|(
name|type
operator|==
name|Type
operator|.
name|BOOLEAN
operator|||
name|type
operator|==
name|Type
operator|.
name|CHAR
operator|||
name|type
operator|==
name|Type
operator|.
name|BYTE
operator|||
name|type
operator|==
name|Type
operator|.
name|SHORT
condition|)
block|{
throw|throw
operator|new
name|AssertionViolatedException
argument_list|(
literal|"The OperandStack does not know about '"
operator|+
name|type
operator|+
literal|"'; use Type.INT instead."
argument_list|)
throw|;
block|}
if|if
condition|(
name|slotsUsed
argument_list|()
operator|>=
name|maxStack
condition|)
block|{
throw|throw
operator|new
name|AssertionViolatedException
argument_list|(
literal|"OperandStack too small, should have thrown proper Exception elsewhere. Stack: "
operator|+
name|this
argument_list|)
throw|;
block|}
name|stack
operator|.
name|add
argument_list|(
name|type
argument_list|)
expr_stmt|;
block|}
comment|/** 	 * Returns the size of this OperandStack; that means, how many Type objects there are. 	 */
name|int
name|size
parameter_list|()
block|{
return|return
name|stack
operator|.
name|size
argument_list|()
return|;
block|}
comment|/** 	 * Returns the number of stack slots used. 	 * @see #maxStack() 	 */
specifier|public
name|int
name|slotsUsed
parameter_list|()
block|{
comment|/*  XXX change this to a better implementation using a variable 		    that keeps track of the actual slotsUsed()-value monitoring 		    all push()es and pop()s. 		*/
name|int
name|slots
init|=
literal|0
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
name|stack
operator|.
name|size
argument_list|()
condition|;
name|i
operator|++
control|)
block|{
name|slots
operator|+=
name|peek
argument_list|(
name|i
argument_list|)
operator|.
name|getSize
argument_list|()
expr_stmt|;
block|}
return|return
name|slots
return|;
block|}
comment|/** 	 * Returns a String representation of this OperandStack instance. 	 */
specifier|public
name|String
name|toString
parameter_list|()
block|{
name|String
name|s
init|=
literal|"Slots used: "
operator|+
name|slotsUsed
argument_list|()
operator|+
literal|" MaxStack: "
operator|+
name|maxStack
operator|+
literal|".\n"
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
argument_list|()
condition|;
name|i
operator|++
control|)
block|{
name|s
operator|+=
name|peek
argument_list|(
name|i
argument_list|)
operator|+
literal|" (Size: "
operator|+
name|peek
argument_list|(
name|i
argument_list|)
operator|.
name|getSize
argument_list|()
operator|+
literal|")\n"
expr_stmt|;
block|}
return|return
name|s
return|;
block|}
comment|/** 	 * Merges another stack state into this instance's stack state. 	 * See the Java Virtual Machine Specification, Second Edition, page 146: 4.9.2 	 * for details. 	 */
specifier|public
name|void
name|merge
parameter_list|(
name|OperandStack
name|s
parameter_list|)
block|{
try|try
block|{
if|if
condition|(
operator|(
name|slotsUsed
argument_list|()
operator|!=
name|s
operator|.
name|slotsUsed
argument_list|()
operator|)
operator|||
operator|(
name|size
argument_list|()
operator|!=
name|s
operator|.
name|size
argument_list|()
operator|)
condition|)
throw|throw
operator|new
name|StructuralCodeConstraintException
argument_list|(
literal|"Cannot merge stacks of different size:\nOperandStack A:\n"
operator|+
name|this
operator|+
literal|"\nOperandStack B:\n"
operator|+
name|s
argument_list|)
throw|;
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
argument_list|()
condition|;
name|i
operator|++
control|)
block|{
comment|// If the object _was_ initialized and we're supposed to merge
comment|// in some uninitialized object, we reject the code (see vmspec2, 4.9.4, last paragraph).
if|if
condition|(
operator|(
operator|!
operator|(
name|stack
operator|.
name|get
argument_list|(
name|i
argument_list|)
operator|instanceof
name|UninitializedObjectType
operator|)
operator|)
operator|&&
operator|(
name|s
operator|.
name|stack
operator|.
name|get
argument_list|(
name|i
argument_list|)
operator|instanceof
name|UninitializedObjectType
operator|)
condition|)
block|{
throw|throw
operator|new
name|StructuralCodeConstraintException
argument_list|(
literal|"Backwards branch with an uninitialized object on the stack detected."
argument_list|)
throw|;
block|}
comment|// Even harder, we're not initialized but are supposed to broaden
comment|// the known object type
if|if
condition|(
operator|(
operator|!
operator|(
name|stack
operator|.
name|get
argument_list|(
name|i
argument_list|)
operator|.
name|equals
argument_list|(
name|s
operator|.
name|stack
operator|.
name|get
argument_list|(
name|i
argument_list|)
argument_list|)
operator|)
operator|)
operator|&&
operator|(
name|stack
operator|.
name|get
argument_list|(
name|i
argument_list|)
operator|instanceof
name|UninitializedObjectType
operator|)
operator|&&
operator|(
operator|!
operator|(
name|s
operator|.
name|stack
operator|.
name|get
argument_list|(
name|i
argument_list|)
operator|instanceof
name|UninitializedObjectType
operator|)
operator|)
condition|)
block|{
throw|throw
operator|new
name|StructuralCodeConstraintException
argument_list|(
literal|"Backwards branch with an uninitialized object on the stack detected."
argument_list|)
throw|;
block|}
comment|// on the other hand...
if|if
condition|(
name|stack
operator|.
name|get
argument_list|(
name|i
argument_list|)
operator|instanceof
name|UninitializedObjectType
condition|)
block|{
comment|//if we have an uninitialized object here
if|if
condition|(
operator|!
operator|(
name|s
operator|.
name|stack
operator|.
name|get
argument_list|(
name|i
argument_list|)
operator|instanceof
name|UninitializedObjectType
operator|)
condition|)
block|{
comment|//that has been initialized by now
name|stack
operator|.
name|set
argument_list|(
name|i
argument_list|,
operator|(
operator|(
name|UninitializedObjectType
operator|)
operator|(
name|stack
operator|.
name|get
argument_list|(
name|i
argument_list|)
operator|)
operator|)
operator|.
name|getInitialized
argument_list|()
argument_list|)
expr_stmt|;
comment|//note that.
block|}
block|}
if|if
condition|(
operator|!
name|stack
operator|.
name|get
argument_list|(
name|i
argument_list|)
operator|.
name|equals
argument_list|(
name|s
operator|.
name|stack
operator|.
name|get
argument_list|(
name|i
argument_list|)
argument_list|)
condition|)
block|{
if|if
condition|(
operator|(
name|stack
operator|.
name|get
argument_list|(
name|i
argument_list|)
operator|instanceof
name|ReferenceType
operator|)
operator|&&
operator|(
name|s
operator|.
name|stack
operator|.
name|get
argument_list|(
name|i
argument_list|)
operator|instanceof
name|ReferenceType
operator|)
condition|)
block|{
name|stack
operator|.
name|set
argument_list|(
name|i
argument_list|,
operator|(
operator|(
name|ReferenceType
operator|)
name|stack
operator|.
name|get
argument_list|(
name|i
argument_list|)
operator|)
operator|.
name|getFirstCommonSuperclass
argument_list|(
operator|(
name|ReferenceType
operator|)
operator|(
name|s
operator|.
name|stack
operator|.
name|get
argument_list|(
name|i
argument_list|)
operator|)
argument_list|)
argument_list|)
expr_stmt|;
block|}
else|else
block|{
throw|throw
operator|new
name|StructuralCodeConstraintException
argument_list|(
literal|"Cannot merge stacks of different types:\nStack A:\n"
operator|+
name|this
operator|+
literal|"\nStack B:\n"
operator|+
name|s
argument_list|)
throw|;
block|}
block|}
block|}
block|}
catch|catch
parameter_list|(
name|ClassNotFoundException
name|e
parameter_list|)
block|{
comment|// FIXME: maybe not the best way to handle this
throw|throw
operator|new
name|AssertionViolatedException
argument_list|(
literal|"Missing class: "
operator|+
name|e
operator|.
name|toString
argument_list|()
argument_list|)
throw|;
block|}
block|}
comment|/** 	 * Replaces all occurences of u in this OperandStack instance 	 * with an "initialized" ObjectType. 	 */
specifier|public
name|void
name|initializeObject
parameter_list|(
name|UninitializedObjectType
name|u
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
name|stack
operator|.
name|size
argument_list|()
condition|;
name|i
operator|++
control|)
block|{
if|if
condition|(
name|stack
operator|.
name|get
argument_list|(
name|i
argument_list|)
operator|==
name|u
condition|)
block|{
name|stack
operator|.
name|set
argument_list|(
name|i
argument_list|,
name|u
operator|.
name|getInitialized
argument_list|()
argument_list|)
expr_stmt|;
block|}
block|}
block|}
block|}
end_class

end_unit

