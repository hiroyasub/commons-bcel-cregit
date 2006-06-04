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

begin_comment
comment|/**  * represents an annotation that is represented in the class file  * but is not provided to the JVM.  *   * @version $Id: RuntimeInvisibleAnnotations  * @author<A HREF="mailto:dbrosius@qis.net">D. Brosius</A>  * @since 5.2  */
end_comment

begin_class
specifier|public
class|class
name|RuntimeInvisibleAnnotations
extends|extends
name|Annotations
block|{
comment|/**      * @param name_index Index pointing to the name<em>Code</em>      * @param length Content length in bytes      * @param file Input stream      * @param constant_pool Array of constants      */
name|RuntimeInvisibleAnnotations
parameter_list|(
name|int
name|name_index
parameter_list|,
name|int
name|length
parameter_list|,
name|DataInputStream
name|file
parameter_list|,
name|ConstantPool
name|constant_pool
parameter_list|)
throws|throws
name|IOException
block|{
name|super
argument_list|(
name|Constants
operator|.
name|ATTR_RUNTIMEIN_VISIBLE_ANNOTATIONS
argument_list|,
name|name_index
argument_list|,
name|length
argument_list|,
name|file
argument_list|,
name|constant_pool
argument_list|)
expr_stmt|;
block|}
comment|/**      * @return deep copy of this attribute      */
specifier|public
name|Attribute
name|copy
parameter_list|(
name|ConstantPool
name|constant_pool
parameter_list|)
block|{
name|Annotations
name|c
init|=
operator|(
name|Annotations
operator|)
name|clone
argument_list|()
decl_stmt|;
return|return
name|c
return|;
block|}
block|}
end_class

end_unit

