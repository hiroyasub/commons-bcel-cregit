begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
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

begin_comment
comment|/* ====================================================================  * The Apache Software License, Version 1.1  *  * Copyright (c) 2001 The Apache Software Foundation.  All rights  * reserved.  *  * Redistribution and use in source and binary forms, with or without  * modification, are permitted provided that the following conditions  * are met:  *  * 1. Redistributions of source code must retain the above copyright  *    notice, this list of conditions and the following disclaimer.  *  * 2. Redistributions in binary form must reproduce the above copyright  *    notice, this list of conditions and the following disclaimer in  *    the documentation and/or other materials provided with the  *    distribution.  *  * 3. The end-user documentation included with the redistribution,  *    if any, must include the following acknowledgment:  *       "This product includes software developed by the  *        Apache Software Foundation (http://www.apache.org/)."  *    Alternately, this acknowledgment may appear in the software itself,  *    if and wherever such third-party acknowledgments normally appear.  *  * 4. The names "Apache" and "Apache Software Foundation" and  *    "Apache BCEL" must not be used to endorse or promote products  *    derived from this software without prior written permission. For  *    written permission, please contact apache@apache.org.  *  * 5. Products derived from this software may not be called "Apache",  *    "Apache BCEL", nor may "Apache" appear in their name, without  *    prior written permission of the Apache Software Foundation.  *  * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED  * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES  * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE  * DISCLAIMED.  IN NO EVENT SHALL THE APACHE SOFTWARE FOUNDATION OR  * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,  * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT  * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF  * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND  * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,  * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT  * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF  * SUCH DAMAGE.  * ====================================================================  *  * This software consists of voluntary contributions made by many  * individuals on behalf of the Apache Software Foundation.  For more  * information on the Apache Software Foundation, please see  *<http://www.apache.org/>.  */
end_comment

begin_comment
comment|/**  * Thrown by InstructionList.remove() when one or multiple disposed instruction  * are still being referenced by a InstructionTargeter object. I.e. the  * InstructionTargeter has to be notified that (one of) the InstructionHandle it  * is referencing is being removed from the InstructionList and thus not valid anymore.  *  * Making this an exception instead of a return value forces the user to handle  * these case explicitely in a try { ... } catch. The following code illustrates  * how this may be done:  *  *<PRE>  *     ...  *     try {  *	il.delete(start_ih, end_ih);  *     } catch(TargetLostException e) {  *       InstructionHandle[] targets = e.getTargets();  *	 for(int i=0; i< targets.length; i++) {  *	   InstructionTargeter[] targeters = targets[i].getTargeters();  *       *	   for(int j=0; j< targeters.length; j++)  *	     targeters[j].updateTarget(targets[i], new_target);  *       }  *     }  *</PRE>  *  * @see InstructionHandle  * @see InstructionList  * @see InstructionTargeter  * @version $Id$  * @author<A HREF="mailto:m.dahm@gmx.de">M. Dahm</A>  */
end_comment

begin_class
specifier|public
specifier|final
class|class
name|TargetLostException
extends|extends
name|Exception
block|{
specifier|private
name|InstructionHandle
index|[]
name|targets
decl_stmt|;
name|TargetLostException
parameter_list|(
name|InstructionHandle
index|[]
name|t
parameter_list|,
name|String
name|mesg
parameter_list|)
block|{
name|super
argument_list|(
name|mesg
argument_list|)
expr_stmt|;
name|targets
operator|=
name|t
expr_stmt|;
block|}
comment|/**    * @return list of instructions still being targeted.    */
specifier|public
name|InstructionHandle
index|[]
name|getTargets
parameter_list|()
block|{
return|return
name|targets
return|;
block|}
block|}
end_class

end_unit

