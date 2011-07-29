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
name|java
operator|.
name|io
operator|.
name|Serializable
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
comment|/**  * This class represents the type of a local variable or item on stack  * used in the StackMap entries.  *  * @version $Id$  * @author<A HREF="mailto:m.dahm@gmx.de">M. Dahm</A>  * @see     StackMapEntry  * @see     StackMap  * @see     Constants  */
end_comment

begin_class
specifier|public
specifier|final
class|class
name|StackMapType
implements|implements
name|Cloneable
implements|,
name|Serializable
block|{
specifier|private
specifier|static
specifier|final
name|long
name|serialVersionUID
init|=
literal|1L
decl_stmt|;
specifier|private
name|byte
name|type
decl_stmt|;
specifier|private
name|int
name|index
init|=
operator|-
literal|1
decl_stmt|;
comment|// Index to CONSTANT_Class or offset
specifier|private
name|ConstantPool
name|constant_pool
decl_stmt|;
comment|/**      * Construct object from file stream.      * @param file Input stream      * @throws IOException      */
name|StackMapType
parameter_list|(
name|DataInput
name|file
parameter_list|,
name|ConstantPool
name|constant_pool
parameter_list|)
throws|throws
name|IOException
block|{
name|this
argument_list|(
name|file
operator|.
name|readByte
argument_list|()
argument_list|,
operator|-
literal|1
argument_list|,
name|constant_pool
argument_list|)
expr_stmt|;
if|if
condition|(
name|hasIndex
argument_list|()
condition|)
block|{
name|setIndex
argument_list|(
name|file
operator|.
name|readShort
argument_list|()
argument_list|)
expr_stmt|;
block|}
name|setConstantPool
argument_list|(
name|constant_pool
argument_list|)
expr_stmt|;
block|}
comment|/**      * @param type type tag as defined in the Constants interface      * @param index index to constant pool, or byte code offset      */
specifier|public
name|StackMapType
parameter_list|(
name|byte
name|type
parameter_list|,
name|int
name|index
parameter_list|,
name|ConstantPool
name|constant_pool
parameter_list|)
block|{
name|setType
argument_list|(
name|type
argument_list|)
expr_stmt|;
name|setIndex
argument_list|(
name|index
argument_list|)
expr_stmt|;
name|setConstantPool
argument_list|(
name|constant_pool
argument_list|)
expr_stmt|;
block|}
specifier|public
name|void
name|setType
parameter_list|(
name|byte
name|t
parameter_list|)
block|{
if|if
condition|(
operator|(
name|t
operator|<
name|Constants
operator|.
name|ITEM_Bogus
operator|)
operator|||
operator|(
name|t
operator|>
name|Constants
operator|.
name|ITEM_NewObject
operator|)
condition|)
block|{
throw|throw
operator|new
name|RuntimeException
argument_list|(
literal|"Illegal type for StackMapType: "
operator|+
name|t
argument_list|)
throw|;
block|}
name|type
operator|=
name|t
expr_stmt|;
block|}
specifier|public
name|byte
name|getType
parameter_list|()
block|{
return|return
name|type
return|;
block|}
specifier|public
name|void
name|setIndex
parameter_list|(
name|int
name|t
parameter_list|)
block|{
name|index
operator|=
name|t
expr_stmt|;
block|}
comment|/** @return index to constant pool if type == ITEM_Object, or offset      * in byte code, if type == ITEM_NewObject, and -1 otherwise      */
specifier|public
name|int
name|getIndex
parameter_list|()
block|{
return|return
name|index
return|;
block|}
comment|/**      * Dump type entries to file.      *      * @param file Output file stream      * @throws IOException      */
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
name|writeByte
argument_list|(
name|type
argument_list|)
expr_stmt|;
if|if
condition|(
name|hasIndex
argument_list|()
condition|)
block|{
name|file
operator|.
name|writeShort
argument_list|(
name|getIndex
argument_list|()
argument_list|)
expr_stmt|;
block|}
block|}
comment|/** @return true, if type is either ITEM_Object or ITEM_NewObject      */
specifier|public
specifier|final
name|boolean
name|hasIndex
parameter_list|()
block|{
return|return
operator|(
operator|(
name|type
operator|==
name|Constants
operator|.
name|ITEM_Object
operator|)
operator|||
operator|(
name|type
operator|==
name|Constants
operator|.
name|ITEM_NewObject
operator|)
operator|)
return|;
block|}
specifier|private
name|String
name|printIndex
parameter_list|()
block|{
if|if
condition|(
name|type
operator|==
name|Constants
operator|.
name|ITEM_Object
condition|)
block|{
if|if
condition|(
name|index
operator|<
literal|0
condition|)
block|{
return|return
literal|", class=<unknown>"
return|;
block|}
return|return
literal|", class="
operator|+
name|constant_pool
operator|.
name|constantToString
argument_list|(
name|index
argument_list|,
name|Constants
operator|.
name|CONSTANT_Class
argument_list|)
return|;
block|}
if|else if
condition|(
name|type
operator|==
name|Constants
operator|.
name|ITEM_NewObject
condition|)
block|{
return|return
literal|", offset="
operator|+
name|index
return|;
block|}
else|else
block|{
return|return
literal|""
return|;
block|}
block|}
comment|/**      * @return String representation      */
annotation|@
name|Override
specifier|public
specifier|final
name|String
name|toString
parameter_list|()
block|{
return|return
literal|"(type="
operator|+
name|Constants
operator|.
name|ITEM_NAMES
index|[
name|type
index|]
operator|+
name|printIndex
argument_list|()
operator|+
literal|")"
return|;
block|}
comment|/**      * @return deep copy of this object      */
specifier|public
name|StackMapType
name|copy
parameter_list|()
block|{
try|try
block|{
return|return
operator|(
name|StackMapType
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
block|}
return|return
literal|null
return|;
block|}
comment|/**      * @return Constant pool used by this object.      */
specifier|public
specifier|final
name|ConstantPool
name|getConstantPool
parameter_list|()
block|{
return|return
name|constant_pool
return|;
block|}
comment|/**      * @param constant_pool Constant pool to be used for this object.      */
specifier|public
specifier|final
name|void
name|setConstantPool
parameter_list|(
name|ConstantPool
name|constant_pool
parameter_list|)
block|{
name|this
operator|.
name|constant_pool
operator|=
name|constant_pool
expr_stmt|;
block|}
block|}
end_class

end_unit

