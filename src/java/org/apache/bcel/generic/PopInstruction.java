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

begin_comment
comment|/**  * Denotes an unparameterized instruction to pop a value on top from the stack,  * such as ISTORE, POP, PUTSTATIC.  *  * @version $Id$  * @author<A HREF="mailto:m.dahm@gmx.de">M. Dahm</A>  * @see ISTORE  * @see POP  */
end_comment

begin_interface
specifier|public
interface|interface
name|PopInstruction
extends|extends
name|StackConsumer
block|{ }
end_interface

end_unit

