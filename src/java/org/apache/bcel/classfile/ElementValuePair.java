begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
begin_comment
comment|/*  * Copyright  2000-2004 The Apache Software Foundation  *  *  Licensed under the Apache License, Version 2.0 (the "License");  *  you may not use this file except in compliance with the License.  *  You may obtain a copy of the License at  *  *      http://www.apache.org/licenses/LICENSE-2.0  *  *  Unless required by applicable law or agreed to in writing, software  *  distributed under the License is distributed on an "AS IS" BASIS,  *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  *  See the License for the specific language governing permissions and  *  limitations under the License.  *  */
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

begin_import
import|import
name|java
operator|.
name|io
operator|.
name|DataInputStream
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

begin_comment
comment|/**  * an annotation's element value pair  *   * @version $Id: ElementValuePair  * @author<A HREF="mailto:dbrosius@qis.net">D. Brosius</A>  */
end_comment

begin_class
specifier|public
class|class
name|ElementValuePair
block|{
specifier|private
name|int
name|element_name_index
decl_stmt|;
specifier|private
name|ElementValue
name|value
decl_stmt|;
comment|/**      * Construct object from file stream.      * @param file Input stream      * @param constant_pool the constant pool      * @throws IOException      */
name|ElementValuePair
parameter_list|(
name|DataInputStream
name|file
parameter_list|,
name|ConstantPool
name|constant_pool
parameter_list|)
throws|throws
name|IOException
block|{
name|element_name_index
operator|=
operator|(
name|file
operator|.
name|readUnsignedShort
argument_list|()
operator|)
expr_stmt|;
name|value
operator|=
operator|new
name|ElementValue
argument_list|(
name|file
argument_list|,
name|constant_pool
argument_list|)
expr_stmt|;
block|}
block|}
end_class

end_unit

