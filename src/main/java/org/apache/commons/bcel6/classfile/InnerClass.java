begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
begin_comment
comment|/*  * Licensed to the Apache Software Foundation (ASF) under one or more  * contributor license agreements.  See the NOTICE file distributed with  * this work for additional information regarding copyright ownership.  * The ASF licenses this file to You under the Apache License, Version 2.0  * (the "License"); you may not use this file except in compliance with  * the License.  You may obtain a copy of the License at  *  *      http://www.apache.org/licenses/LICENSE-2.0  *  *  Unless required by applicable law or agreed to in writing, software  *  distributed under the License is distributed on an "AS IS" BASIS,  *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  *  See the License for the specific language governing permissions and  *  limitations under the License.  *  */
end_comment

begin_package
package|package
name|org
operator|.
name|apache
operator|.
name|commons
operator|.
name|bcel6
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
name|commons
operator|.
name|bcel6
operator|.
name|Const
import|;
end_import

begin_comment
comment|/**   * This class represents a inner class attribute, i.e., the class  * indices of the inner and outer classes, the name and the attributes  * of the inner class.  *  * @version $Id$  * @see InnerClasses  */
end_comment

begin_class
specifier|public
specifier|final
class|class
name|InnerClass
implements|implements
name|Cloneable
implements|,
name|Node
block|{
specifier|private
name|int
name|inner_class_index
decl_stmt|;
specifier|private
name|int
name|outer_class_index
decl_stmt|;
specifier|private
name|int
name|inner_name_index
decl_stmt|;
specifier|private
name|int
name|inner_access_flags
decl_stmt|;
comment|/**      * Initialize from another object.      */
specifier|public
name|InnerClass
parameter_list|(
name|InnerClass
name|c
parameter_list|)
block|{
name|this
argument_list|(
name|c
operator|.
name|getInnerClassIndex
argument_list|()
argument_list|,
name|c
operator|.
name|getOuterClassIndex
argument_list|()
argument_list|,
name|c
operator|.
name|getInnerNameIndex
argument_list|()
argument_list|,
name|c
operator|.
name|getInnerAccessFlags
argument_list|()
argument_list|)
expr_stmt|;
block|}
comment|/**      * Construct object from file stream.      * @param file Input stream      * @throws IOException      */
name|InnerClass
parameter_list|(
name|DataInput
name|file
parameter_list|)
throws|throws
name|IOException
block|{
name|this
argument_list|(
name|file
operator|.
name|readUnsignedShort
argument_list|()
argument_list|,
name|file
operator|.
name|readUnsignedShort
argument_list|()
argument_list|,
name|file
operator|.
name|readUnsignedShort
argument_list|()
argument_list|,
name|file
operator|.
name|readUnsignedShort
argument_list|()
argument_list|)
expr_stmt|;
block|}
comment|/**      * @param inner_class_index Class index in constant pool of inner class      * @param outer_class_index Class index in constant pool of outer class      * @param inner_name_index  Name index in constant pool of inner class      * @param inner_access_flags Access flags of inner class      */
specifier|public
name|InnerClass
parameter_list|(
name|int
name|inner_class_index
parameter_list|,
name|int
name|outer_class_index
parameter_list|,
name|int
name|inner_name_index
parameter_list|,
name|int
name|inner_access_flags
parameter_list|)
block|{
name|this
operator|.
name|inner_class_index
operator|=
name|inner_class_index
expr_stmt|;
name|this
operator|.
name|outer_class_index
operator|=
name|outer_class_index
expr_stmt|;
name|this
operator|.
name|inner_name_index
operator|=
name|inner_name_index
expr_stmt|;
name|this
operator|.
name|inner_access_flags
operator|=
name|inner_access_flags
expr_stmt|;
block|}
comment|/**      * Called by objects that are traversing the nodes of the tree implicitely      * defined by the contents of a Java class. I.e., the hierarchy of methods,      * fields, attributes, etc. spawns a tree of objects.      *      * @param v Visitor object      */
annotation|@
name|Override
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
name|visitInnerClass
argument_list|(
name|this
argument_list|)
expr_stmt|;
block|}
comment|/**      * Dump inner class attribute to file stream in binary format.      *      * @param file Output file stream      * @throws IOException      */
specifier|public
specifier|final
name|void
name|dump
parameter_list|(
name|DataOutputStream
name|file
parameter_list|)
throws|throws
name|IOException
block|{
name|file
operator|.
name|writeShort
argument_list|(
name|inner_class_index
argument_list|)
expr_stmt|;
name|file
operator|.
name|writeShort
argument_list|(
name|outer_class_index
argument_list|)
expr_stmt|;
name|file
operator|.
name|writeShort
argument_list|(
name|inner_name_index
argument_list|)
expr_stmt|;
name|file
operator|.
name|writeShort
argument_list|(
name|inner_access_flags
argument_list|)
expr_stmt|;
block|}
comment|/**      * @return access flags of inner class.      */
specifier|public
specifier|final
name|int
name|getInnerAccessFlags
parameter_list|()
block|{
return|return
name|inner_access_flags
return|;
block|}
comment|/**      * @return class index of inner class.      */
specifier|public
specifier|final
name|int
name|getInnerClassIndex
parameter_list|()
block|{
return|return
name|inner_class_index
return|;
block|}
comment|/**      * @return name index of inner class.      */
specifier|public
specifier|final
name|int
name|getInnerNameIndex
parameter_list|()
block|{
return|return
name|inner_name_index
return|;
block|}
comment|/**      * @return class index of outer class.      */
specifier|public
specifier|final
name|int
name|getOuterClassIndex
parameter_list|()
block|{
return|return
name|outer_class_index
return|;
block|}
comment|/**      * @param inner_access_flags access flags for this inner class      */
specifier|public
specifier|final
name|void
name|setInnerAccessFlags
parameter_list|(
name|int
name|inner_access_flags
parameter_list|)
block|{
name|this
operator|.
name|inner_access_flags
operator|=
name|inner_access_flags
expr_stmt|;
block|}
comment|/**      * @param inner_class_index index into the constant pool for this class      */
specifier|public
specifier|final
name|void
name|setInnerClassIndex
parameter_list|(
name|int
name|inner_class_index
parameter_list|)
block|{
name|this
operator|.
name|inner_class_index
operator|=
name|inner_class_index
expr_stmt|;
block|}
comment|/**      * @param inner_name_index index into the constant pool for this class's name      */
specifier|public
specifier|final
name|void
name|setInnerNameIndex
parameter_list|(
name|int
name|inner_name_index
parameter_list|)
block|{
comment|// TODO unused
name|this
operator|.
name|inner_name_index
operator|=
name|inner_name_index
expr_stmt|;
block|}
comment|/**      * @param outer_class_index index into the constant pool for the owning class      */
specifier|public
specifier|final
name|void
name|setOuterClassIndex
parameter_list|(
name|int
name|outer_class_index
parameter_list|)
block|{
comment|// TODO unused
name|this
operator|.
name|outer_class_index
operator|=
name|outer_class_index
expr_stmt|;
block|}
comment|/**      * @return String representation.      */
annotation|@
name|Override
specifier|public
specifier|final
name|String
name|toString
parameter_list|()
block|{
return|return
literal|"InnerClass("
operator|+
name|inner_class_index
operator|+
literal|", "
operator|+
name|outer_class_index
operator|+
literal|", "
operator|+
name|inner_name_index
operator|+
literal|", "
operator|+
name|inner_access_flags
operator|+
literal|")"
return|;
block|}
comment|/**      * @return Resolved string representation      */
specifier|public
specifier|final
name|String
name|toString
parameter_list|(
name|ConstantPool
name|constant_pool
parameter_list|)
block|{
name|String
name|outer_class_name
decl_stmt|;
name|String
name|inner_name
decl_stmt|;
name|String
name|inner_class_name
init|=
name|constant_pool
operator|.
name|getConstantString
argument_list|(
name|inner_class_index
argument_list|,
name|Const
operator|.
name|CONSTANT_Class
argument_list|)
decl_stmt|;
name|inner_class_name
operator|=
name|Utility
operator|.
name|compactClassName
argument_list|(
name|inner_class_name
argument_list|)
expr_stmt|;
if|if
condition|(
name|outer_class_index
operator|!=
literal|0
condition|)
block|{
name|outer_class_name
operator|=
name|constant_pool
operator|.
name|getConstantString
argument_list|(
name|outer_class_index
argument_list|,
name|Const
operator|.
name|CONSTANT_Class
argument_list|)
expr_stmt|;
name|outer_class_name
operator|=
literal|" of class "
operator|+
name|Utility
operator|.
name|compactClassName
argument_list|(
name|outer_class_name
argument_list|)
expr_stmt|;
block|}
else|else
block|{
name|outer_class_name
operator|=
literal|""
expr_stmt|;
block|}
if|if
condition|(
name|inner_name_index
operator|!=
literal|0
condition|)
block|{
name|inner_name
operator|=
operator|(
operator|(
name|ConstantUtf8
operator|)
name|constant_pool
operator|.
name|getConstant
argument_list|(
name|inner_name_index
argument_list|,
name|Const
operator|.
name|CONSTANT_Utf8
argument_list|)
operator|)
operator|.
name|getBytes
argument_list|()
expr_stmt|;
block|}
else|else
block|{
name|inner_name
operator|=
literal|"(anonymous)"
expr_stmt|;
block|}
name|String
name|access
init|=
name|Utility
operator|.
name|accessToString
argument_list|(
name|inner_access_flags
argument_list|,
literal|true
argument_list|)
decl_stmt|;
name|access
operator|=
name|access
operator|.
name|equals
argument_list|(
literal|""
argument_list|)
condition|?
literal|""
else|:
operator|(
name|access
operator|+
literal|" "
operator|)
expr_stmt|;
return|return
literal|"  "
operator|+
name|access
operator|+
name|inner_name
operator|+
literal|"=class "
operator|+
name|inner_class_name
operator|+
name|outer_class_name
return|;
block|}
comment|/**      * @return deep copy of this object      */
specifier|public
name|InnerClass
name|copy
parameter_list|()
block|{
try|try
block|{
return|return
operator|(
name|InnerClass
operator|)
name|clone
argument_list|()
return|;
block|}
catch|catch
parameter_list|(
name|CloneNotSupportedException
name|e
parameter_list|)
block|{
comment|// TODO should this throw?
block|}
return|return
literal|null
return|;
block|}
block|}
end_class

end_unit

