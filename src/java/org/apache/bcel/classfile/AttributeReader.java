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

begin_comment
comment|/**  * Unknown (non-standard) attributes may be read via user-defined factory  * objects that can be registered with the Attribute.addAttributeReader  * method. These factory objects should implement this interface.   * @see Attribute  * @version $Id$  * @author<A HREF="mailto:m.dahm@gmx.de">M. Dahm</A>  */
end_comment

begin_interface
specifier|public
interface|interface
name|AttributeReader
block|{
comment|/**      When this attribute reader is added via the static method      Attribute.addAttributeReader, an attribute name is associated with it.      As the class file parser parses attributes, it will call various      AttributeReaders based on the name of the attributes it is      constructing.       @param name_index An index into the constant pool, indexing a      ConstantUtf8 that represents the name of the attribute.       @param length The length of the data contained in the attribute.  This      is written into the constant pool and should agree with what the      factory expects the length to be.       @param file This is the data input stream that the factory needs to read      its data from.       @param constant_pool This is the constant pool associated with the      Attribute that we are constructing.       @return The user-defined AttributeReader should take this data and use      it to construct an attribute.  In the case of errors, a null can be      returned which will cause the parsing of the class file to fail.       @see Attribute#addAttributeReader( String, AttributeReader )   */
specifier|public
name|Attribute
name|createAttribute
parameter_list|(
name|int
name|name_index
parameter_list|,
name|int
name|length
parameter_list|,
name|java
operator|.
name|io
operator|.
name|DataInputStream
name|file
parameter_list|,
name|ConstantPool
name|constant_pool
parameter_list|)
function_decl|;
block|}
end_interface

end_unit

