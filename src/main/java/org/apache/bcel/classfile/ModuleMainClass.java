begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
begin_comment
comment|/*  * Licensed to the Apache Software Foundation (ASF) under one or more  * contributor license agreements.  See the NOTICE file distributed with  * this work for additional information regarding copyright ownership.  * The ASF licenses this file to You under the Apache License, Version 2.0  * (the "License"); you may not use this file except in compliance with  * the License.  You may obtain a copy of the License at  *  *      http://www.apache.org/licenses/LICENSE-2.0  *  *  Unless required by applicable law or agreed to in writing, software  *  distributed under the License is distributed on an "AS IS" BASIS,  *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  *  See the License for the specific language governing permissions and  *  limitations under the License.  */
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
name|DataInput
import|;
end_import

begin_import
import|import
name|java
operator|.
name|io
operator|.
name|DataOutputStream
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
name|Const
import|;
end_import

begin_comment
comment|/**  * This class is derived from<em>Attribute</em> and indicates the main class of a module.  * There may be at most one ModuleMainClass attribute in a ClassFile structure.  *  * @see     Attribute  */
end_comment

begin_class
specifier|public
specifier|final
class|class
name|ModuleMainClass
extends|extends
name|Attribute
block|{
specifier|private
name|int
name|mainClassIndex
decl_stmt|;
comment|/**      * Initialize from another object. Note that both objects use the same      * references (shallow copy). Use copy() for a physical copy.      */
specifier|public
name|ModuleMainClass
parameter_list|(
specifier|final
name|ModuleMainClass
name|c
parameter_list|)
block|{
name|this
argument_list|(
name|c
operator|.
name|getNameIndex
argument_list|()
argument_list|,
name|c
operator|.
name|getLength
argument_list|()
argument_list|,
name|c
operator|.
name|getHostClassIndex
argument_list|()
argument_list|,
name|c
operator|.
name|getConstantPool
argument_list|()
argument_list|)
expr_stmt|;
block|}
comment|/**      * @param name_index Index in constant pool      * @param length Content length in bytes      * @param mainClassIndex Host class index      * @param constantPool Array of constants      */
specifier|public
name|ModuleMainClass
parameter_list|(
specifier|final
name|int
name|name_index
parameter_list|,
specifier|final
name|int
name|length
parameter_list|,
specifier|final
name|int
name|mainClassIndex
parameter_list|,
specifier|final
name|ConstantPool
name|constantPool
parameter_list|)
block|{
name|super
argument_list|(
name|Const
operator|.
name|ATTR_NEST_MEMBERS
argument_list|,
name|name_index
argument_list|,
name|length
argument_list|,
name|constantPool
argument_list|)
expr_stmt|;
name|this
operator|.
name|mainClassIndex
operator|=
name|mainClassIndex
expr_stmt|;
block|}
comment|/**      * Construct object from input stream.      * @param nameIndex Index in constant pool      * @param length Content length in bytes      * @param input Input stream      * @param constantPool Array of constants      * @throws IOException      */
name|ModuleMainClass
parameter_list|(
specifier|final
name|int
name|nameIndex
parameter_list|,
specifier|final
name|int
name|length
parameter_list|,
specifier|final
name|DataInput
name|input
parameter_list|,
specifier|final
name|ConstantPool
name|constantPool
parameter_list|)
throws|throws
name|IOException
block|{
name|this
argument_list|(
name|nameIndex
argument_list|,
name|length
argument_list|,
literal|0
argument_list|,
name|constantPool
argument_list|)
expr_stmt|;
name|mainClassIndex
operator|=
name|input
operator|.
name|readUnsignedShort
argument_list|()
expr_stmt|;
block|}
comment|/**      * Called by objects that are traversing the nodes of the tree implicitly      * defined by the contents of a Java class. I.e., the hierarchy of methods,      * fields, attributes, etc. spawns a tree of objects.      *      * @param v Visitor object      */
annotation|@
name|Override
specifier|public
name|void
name|accept
parameter_list|(
specifier|final
name|Visitor
name|v
parameter_list|)
block|{
name|v
operator|.
name|visitModuleMainClass
argument_list|(
name|this
argument_list|)
expr_stmt|;
block|}
comment|/**      * Dump ModuleMainClass attribute to file stream in binary format.      *      * @param file Output file stream      * @throws IOException if an I/O error occurs.      */
annotation|@
name|Override
specifier|public
name|void
name|dump
parameter_list|(
specifier|final
name|DataOutputStream
name|file
parameter_list|)
throws|throws
name|IOException
block|{
name|super
operator|.
name|dump
argument_list|(
name|file
argument_list|)
expr_stmt|;
name|file
operator|.
name|writeShort
argument_list|(
name|mainClassIndex
argument_list|)
expr_stmt|;
block|}
comment|/**      * @return index into constant pool of host class name.      */
specifier|public
name|int
name|getHostClassIndex
parameter_list|()
block|{
return|return
name|mainClassIndex
return|;
block|}
comment|/**      * @param mainClassIndex the host class index      */
specifier|public
name|void
name|setHostClassIndex
parameter_list|(
specifier|final
name|int
name|mainClassIndex
parameter_list|)
block|{
name|this
operator|.
name|mainClassIndex
operator|=
name|mainClassIndex
expr_stmt|;
block|}
comment|/**      * @return String representation      */
annotation|@
name|Override
specifier|public
name|String
name|toString
parameter_list|()
block|{
specifier|final
name|StringBuilder
name|buf
init|=
operator|new
name|StringBuilder
argument_list|()
decl_stmt|;
name|buf
operator|.
name|append
argument_list|(
literal|"ModuleMainClass: "
argument_list|)
expr_stmt|;
specifier|final
name|String
name|class_name
init|=
name|super
operator|.
name|getConstantPool
argument_list|()
operator|.
name|getConstantString
argument_list|(
name|mainClassIndex
argument_list|,
name|Const
operator|.
name|CONSTANT_Class
argument_list|)
decl_stmt|;
name|buf
operator|.
name|append
argument_list|(
name|Utility
operator|.
name|compactClassName
argument_list|(
name|class_name
argument_list|,
literal|false
argument_list|)
argument_list|)
expr_stmt|;
return|return
name|buf
operator|.
name|toString
argument_list|()
return|;
block|}
comment|/**      * @return deep copy of this attribute      */
annotation|@
name|Override
specifier|public
name|Attribute
name|copy
parameter_list|(
specifier|final
name|ConstantPool
name|_constant_pool
parameter_list|)
block|{
specifier|final
name|ModuleMainClass
name|c
init|=
operator|(
name|ModuleMainClass
operator|)
name|clone
argument_list|()
decl_stmt|;
name|c
operator|.
name|setConstantPool
argument_list|(
name|_constant_pool
argument_list|)
expr_stmt|;
return|return
name|c
return|;
block|}
block|}
end_class

end_unit

