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
specifier|static
specifier|final
name|long
name|serialVersionUID
init|=
operator|-
literal|6857272667645328384L
decl_stmt|;
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
comment|/**      * @return list of instructions still being targeted.      */
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

