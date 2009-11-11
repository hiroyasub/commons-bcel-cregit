begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
begin_comment
comment|/*  * Copyright  2000-2009 The Apache Software Foundation  *  *  Licensed under the Apache License, Version 2.0 (the "License");   *  you may not use this file except in compliance with the License.  *  You may obtain a copy of the License at  *  *      http://www.apache.org/licenses/LICENSE-2.0  *  *  Unless required by applicable law or agreed to in writing, software  *  distributed under the License is distributed on an "AS IS" BASIS,  *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  *  See the License for the specific language governing permissions and  *  limitations under the License.   *  */
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
comment|/**  * Denotes an unparameterized instruction to produce a value on top of the stack,  * such as ILOAD, LDC, SIPUSH, DUP, ICONST, etc.  *  * @version $Id$  * @author<A HREF="mailto:m.dahm@gmx.de">M. Dahm</A>   * @see ILOAD  * @see ICONST  * @see LDC  * @see DUP  * @see SIPUSH  * @see GETSTATIC  */
end_comment

begin_interface
specifier|public
interface|interface
name|PushInstruction
extends|extends
name|StackProducer
block|{ }
end_interface

end_unit
