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
comment|/**   * This class represents a constant pool reference to an interface method.  *  * @version $Id$  * @author<A HREF="mailto:m.dahm@gmx.de">M. Dahm</A>  */
end_comment

begin_class
specifier|public
specifier|final
class|class
name|ConstantInterfaceMethodref
extends|extends
name|ConstantCP
block|{
comment|/**      * Initialize from another object.      */
specifier|public
name|ConstantInterfaceMethodref
parameter_list|(
name|ConstantInterfaceMethodref
name|c
parameter_list|)
block|{
name|super
argument_list|(
name|Constants
operator|.
name|CONSTANT_InterfaceMethodref
argument_list|,
name|c
operator|.
name|getClassIndex
argument_list|()
argument_list|,
name|c
operator|.
name|getNameAndTypeIndex
argument_list|()
argument_list|)
expr_stmt|;
block|}
comment|/**      * Initialize instance from file data.      *      * @param file input stream      * @throws IOException      */
name|ConstantInterfaceMethodref
parameter_list|(
name|DataInputStream
name|file
parameter_list|)
throws|throws
name|IOException
block|{
name|super
argument_list|(
name|Constants
operator|.
name|CONSTANT_InterfaceMethodref
argument_list|,
name|file
argument_list|)
expr_stmt|;
block|}
comment|/**      * @param class_index Reference to the class containing the method      * @param name_and_type_index and the method signature      */
specifier|public
name|ConstantInterfaceMethodref
parameter_list|(
name|int
name|class_index
parameter_list|,
name|int
name|name_and_type_index
parameter_list|)
block|{
name|super
argument_list|(
name|Constants
operator|.
name|CONSTANT_InterfaceMethodref
argument_list|,
name|class_index
argument_list|,
name|name_and_type_index
argument_list|)
expr_stmt|;
block|}
comment|/**      * Called by objects that are traversing the nodes of the tree implicitely      * defined by the contents of a Java class. I.e., the hierarchy of methods,      * fields, attributes, etc. spawns a tree of objects.      *      * @param v Visitor object      */
specifier|public
name|void
name|accept
parameter_list|(
name|Visitor
name|v
parameter_list|)
block|{
name|v
operator|.
name|visitConstantInterfaceMethodref
argument_list|(
name|this
argument_list|)
expr_stmt|;
block|}
block|}
end_class

end_unit
