begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
begin_comment
comment|/*  * Copyright  2000-2009 The Apache Software Foundation  *  *  Licensed under the Apache License, Version 2.0 (the "License");  *  you may not use this file except in compliance with the License.  *  You may obtain a copy of the License at  *  *      http://www.apache.org/licenses/LICENSE-2.0  *  *  Unless required by applicable law or agreed to in writing, software  *  distributed under the License is distributed on an "AS IS" BASIS,  *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  *  See the License for the specific language governing permissions and  *  limitations under the License.  *  */
end_comment

begin_package
package|package
name|org
operator|.
name|apache
operator|.
name|bcel
operator|.
name|classfile
package|;
end_package

begin_comment
comment|/**   * This interface denotes those constants that have a "natural" value,  * such as ConstantLong, ConstantString, etc..  *  * @version $Id$  * @author<A HREF="mailto:m.dahm@gmx.de">M. Dahm</A>  * @see     Constant  */
end_comment

begin_interface
specifier|public
interface|interface
name|ConstantObject
block|{
comment|/** @return object representing the constant, e.g., Long for ConstantLong      */
specifier|public
specifier|abstract
name|Object
name|getConstantValue
parameter_list|(
name|ConstantPool
name|cp
parameter_list|)
function_decl|;
block|}
end_interface

end_unit

