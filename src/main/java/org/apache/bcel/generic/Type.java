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
name|generic
package|;
end_package

begin_import
import|import
name|java
operator|.
name|util
operator|.
name|ArrayList
import|;
end_import

begin_import
import|import
name|java
operator|.
name|util
operator|.
name|Arrays
import|;
end_import

begin_import
import|import
name|java
operator|.
name|util
operator|.
name|List
import|;
end_import

begin_import
import|import
name|java
operator|.
name|util
operator|.
name|Objects
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

begin_import
import|import
name|org
operator|.
name|apache
operator|.
name|bcel
operator|.
name|classfile
operator|.
name|ClassFormatException
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
name|classfile
operator|.
name|InvalidMethodSignatureException
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
name|classfile
operator|.
name|Utility
import|;
end_import

begin_comment
comment|/**  * Abstract super class for all possible java types, namely basic types such as int, object types like String and array  * types, e.g. int[]  */
end_comment

begin_class
specifier|public
specifier|abstract
class|class
name|Type
block|{
comment|/**      * Predefined constants      */
specifier|public
specifier|static
specifier|final
name|BasicType
name|VOID
init|=
operator|new
name|BasicType
argument_list|(
name|Const
operator|.
name|T_VOID
argument_list|)
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|BasicType
name|BOOLEAN
init|=
operator|new
name|BasicType
argument_list|(
name|Const
operator|.
name|T_BOOLEAN
argument_list|)
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|BasicType
name|INT
init|=
operator|new
name|BasicType
argument_list|(
name|Const
operator|.
name|T_INT
argument_list|)
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|BasicType
name|SHORT
init|=
operator|new
name|BasicType
argument_list|(
name|Const
operator|.
name|T_SHORT
argument_list|)
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|BasicType
name|BYTE
init|=
operator|new
name|BasicType
argument_list|(
name|Const
operator|.
name|T_BYTE
argument_list|)
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|BasicType
name|LONG
init|=
operator|new
name|BasicType
argument_list|(
name|Const
operator|.
name|T_LONG
argument_list|)
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|BasicType
name|DOUBLE
init|=
operator|new
name|BasicType
argument_list|(
name|Const
operator|.
name|T_DOUBLE
argument_list|)
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|BasicType
name|FLOAT
init|=
operator|new
name|BasicType
argument_list|(
name|Const
operator|.
name|T_FLOAT
argument_list|)
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|BasicType
name|CHAR
init|=
operator|new
name|BasicType
argument_list|(
name|Const
operator|.
name|T_CHAR
argument_list|)
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|ObjectType
name|OBJECT
init|=
operator|new
name|ObjectType
argument_list|(
literal|"java.lang.Object"
argument_list|)
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|ObjectType
name|CLASS
init|=
operator|new
name|ObjectType
argument_list|(
literal|"java.lang.Class"
argument_list|)
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|ObjectType
name|STRING
init|=
operator|new
name|ObjectType
argument_list|(
literal|"java.lang.String"
argument_list|)
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|ObjectType
name|STRINGBUFFER
init|=
operator|new
name|ObjectType
argument_list|(
literal|"java.lang.StringBuffer"
argument_list|)
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|ObjectType
name|THROWABLE
init|=
operator|new
name|ObjectType
argument_list|(
literal|"java.lang.Throwable"
argument_list|)
decl_stmt|;
comment|/**      * Empty array.      */
specifier|public
specifier|static
specifier|final
name|Type
index|[]
name|NO_ARGS
init|=
block|{}
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|ReferenceType
name|NULL
init|=
operator|new
name|ReferenceType
argument_list|()
block|{     }
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|Type
name|UNKNOWN
init|=
operator|new
name|Type
argument_list|(
name|Const
operator|.
name|T_UNKNOWN
argument_list|,
literal|"<unknown object>"
argument_list|)
block|{     }
decl_stmt|;
specifier|private
specifier|static
specifier|final
name|ThreadLocal
argument_list|<
name|Integer
argument_list|>
name|CONSUMED_CHARS
init|=
name|ThreadLocal
operator|.
name|withInitial
argument_list|(
parameter_list|()
lambda|->
name|Integer
operator|.
name|valueOf
argument_list|(
literal|0
argument_list|)
argument_list|)
decl_stmt|;
comment|// int consumed_chars=0; // Remember position in string, see getArgumentTypes
specifier|static
name|int
name|consumed
parameter_list|(
specifier|final
name|int
name|coded
parameter_list|)
block|{
return|return
name|coded
operator|>>
literal|2
return|;
block|}
specifier|static
name|int
name|encode
parameter_list|(
specifier|final
name|int
name|size
parameter_list|,
specifier|final
name|int
name|consumed
parameter_list|)
block|{
return|return
name|consumed
operator|<<
literal|2
operator||
name|size
return|;
block|}
comment|/**      * Convert arguments of a method (signature) to an array of Type objects.      *      * @param signature signature string such as (Ljava/lang/String;)V      * @return array of argument types      */
specifier|public
specifier|static
name|Type
index|[]
name|getArgumentTypes
parameter_list|(
specifier|final
name|String
name|signature
parameter_list|)
block|{
specifier|final
name|List
argument_list|<
name|Type
argument_list|>
name|vec
init|=
operator|new
name|ArrayList
argument_list|<>
argument_list|()
decl_stmt|;
name|int
name|index
decl_stmt|;
try|try
block|{
comment|// Skip any type arguments to read argument declarations between '(' and ')'
name|index
operator|=
name|signature
operator|.
name|indexOf
argument_list|(
literal|'('
argument_list|)
operator|+
literal|1
expr_stmt|;
if|if
condition|(
name|index
operator|<=
literal|0
condition|)
block|{
throw|throw
operator|new
name|InvalidMethodSignatureException
argument_list|(
name|signature
argument_list|)
throw|;
block|}
while|while
condition|(
name|signature
operator|.
name|charAt
argument_list|(
name|index
argument_list|)
operator|!=
literal|')'
condition|)
block|{
name|vec
operator|.
name|add
argument_list|(
name|getType
argument_list|(
name|signature
operator|.
name|substring
argument_list|(
name|index
argument_list|)
argument_list|)
argument_list|)
expr_stmt|;
comment|// corrected concurrent private static field access
name|index
operator|+=
name|unwrap
argument_list|(
name|CONSUMED_CHARS
argument_list|)
expr_stmt|;
comment|// update position
block|}
block|}
catch|catch
parameter_list|(
specifier|final
name|StringIndexOutOfBoundsException
name|e
parameter_list|)
block|{
comment|// Should never occur
throw|throw
operator|new
name|InvalidMethodSignatureException
argument_list|(
name|signature
argument_list|,
name|e
argument_list|)
throw|;
block|}
specifier|final
name|Type
index|[]
name|types
init|=
operator|new
name|Type
index|[
name|vec
operator|.
name|size
argument_list|()
index|]
decl_stmt|;
name|vec
operator|.
name|toArray
argument_list|(
name|types
argument_list|)
expr_stmt|;
return|return
name|types
return|;
block|}
specifier|static
name|int
name|getArgumentTypesSize
parameter_list|(
specifier|final
name|String
name|signature
parameter_list|)
block|{
name|int
name|res
init|=
literal|0
decl_stmt|;
name|int
name|index
decl_stmt|;
try|try
block|{
comment|// Skip any type arguments to read argument declarations between '(' and ')'
name|index
operator|=
name|signature
operator|.
name|indexOf
argument_list|(
literal|'('
argument_list|)
operator|+
literal|1
expr_stmt|;
if|if
condition|(
name|index
operator|<=
literal|0
condition|)
block|{
throw|throw
operator|new
name|InvalidMethodSignatureException
argument_list|(
name|signature
argument_list|)
throw|;
block|}
while|while
condition|(
name|signature
operator|.
name|charAt
argument_list|(
name|index
argument_list|)
operator|!=
literal|')'
condition|)
block|{
specifier|final
name|int
name|coded
init|=
name|getTypeSize
argument_list|(
name|signature
operator|.
name|substring
argument_list|(
name|index
argument_list|)
argument_list|)
decl_stmt|;
name|res
operator|+=
name|size
argument_list|(
name|coded
argument_list|)
expr_stmt|;
name|index
operator|+=
name|consumed
argument_list|(
name|coded
argument_list|)
expr_stmt|;
block|}
block|}
catch|catch
parameter_list|(
specifier|final
name|StringIndexOutOfBoundsException
name|e
parameter_list|)
block|{
comment|// Should never occur
throw|throw
operator|new
name|InvalidMethodSignatureException
argument_list|(
name|signature
argument_list|,
name|e
argument_list|)
throw|;
block|}
return|return
name|res
return|;
block|}
comment|/**      * Convert type to Java method signature, e.g. int[] f(java.lang.String x) becomes (Ljava/lang/String;)[I      *      * @param returnType what the method returns      * @param argTypes what are the argument types      * @return method signature for given type(s).      */
specifier|public
specifier|static
name|String
name|getMethodSignature
parameter_list|(
specifier|final
name|Type
name|returnType
parameter_list|,
specifier|final
name|Type
index|[]
name|argTypes
parameter_list|)
block|{
specifier|final
name|StringBuilder
name|buf
init|=
operator|new
name|StringBuilder
argument_list|(
literal|"("
argument_list|)
decl_stmt|;
if|if
condition|(
name|argTypes
operator|!=
literal|null
condition|)
block|{
for|for
control|(
specifier|final
name|Type
name|argType
range|:
name|argTypes
control|)
block|{
name|buf
operator|.
name|append
argument_list|(
name|argType
operator|.
name|getSignature
argument_list|()
argument_list|)
expr_stmt|;
block|}
block|}
name|buf
operator|.
name|append
argument_list|(
literal|')'
argument_list|)
expr_stmt|;
name|buf
operator|.
name|append
argument_list|(
name|returnType
operator|.
name|getSignature
argument_list|()
argument_list|)
expr_stmt|;
return|return
name|buf
operator|.
name|toString
argument_list|()
return|;
block|}
comment|/**      * Convert return value of a method (signature) to a Type object.      *      * @param signature signature string such as (Ljava/lang/String;)V      * @return return type      */
specifier|public
specifier|static
name|Type
name|getReturnType
parameter_list|(
specifier|final
name|String
name|signature
parameter_list|)
block|{
try|try
block|{
comment|// Read return type after ')'
specifier|final
name|int
name|index
init|=
name|signature
operator|.
name|lastIndexOf
argument_list|(
literal|')'
argument_list|)
operator|+
literal|1
decl_stmt|;
return|return
name|getType
argument_list|(
name|signature
operator|.
name|substring
argument_list|(
name|index
argument_list|)
argument_list|)
return|;
block|}
catch|catch
parameter_list|(
specifier|final
name|StringIndexOutOfBoundsException
name|e
parameter_list|)
block|{
comment|// Should never occur
throw|throw
operator|new
name|InvalidMethodSignatureException
argument_list|(
name|signature
argument_list|,
name|e
argument_list|)
throw|;
block|}
block|}
specifier|static
name|int
name|getReturnTypeSize
parameter_list|(
specifier|final
name|String
name|signature
parameter_list|)
block|{
specifier|final
name|int
name|index
init|=
name|signature
operator|.
name|lastIndexOf
argument_list|(
literal|')'
argument_list|)
operator|+
literal|1
decl_stmt|;
return|return
name|Type
operator|.
name|size
argument_list|(
name|getTypeSize
argument_list|(
name|signature
operator|.
name|substring
argument_list|(
name|index
argument_list|)
argument_list|)
argument_list|)
return|;
block|}
specifier|public
specifier|static
name|String
name|getSignature
parameter_list|(
specifier|final
name|java
operator|.
name|lang
operator|.
name|reflect
operator|.
name|Method
name|meth
parameter_list|)
block|{
specifier|final
name|StringBuilder
name|sb
init|=
operator|new
name|StringBuilder
argument_list|(
literal|"("
argument_list|)
decl_stmt|;
specifier|final
name|Class
argument_list|<
name|?
argument_list|>
index|[]
name|params
init|=
name|meth
operator|.
name|getParameterTypes
argument_list|()
decl_stmt|;
comment|// avoid clone
for|for
control|(
specifier|final
name|Class
argument_list|<
name|?
argument_list|>
name|param
range|:
name|params
control|)
block|{
name|sb
operator|.
name|append
argument_list|(
name|getType
argument_list|(
name|param
argument_list|)
operator|.
name|getSignature
argument_list|()
argument_list|)
expr_stmt|;
block|}
name|sb
operator|.
name|append
argument_list|(
literal|")"
argument_list|)
expr_stmt|;
name|sb
operator|.
name|append
argument_list|(
name|getType
argument_list|(
name|meth
operator|.
name|getReturnType
argument_list|()
argument_list|)
operator|.
name|getSignature
argument_list|()
argument_list|)
expr_stmt|;
return|return
name|sb
operator|.
name|toString
argument_list|()
return|;
block|}
comment|/**      * Convert runtime java.lang.Class to BCEL Type object.      *      * @param cls Java class      * @return corresponding Type object      */
specifier|public
specifier|static
name|Type
name|getType
parameter_list|(
specifier|final
name|Class
argument_list|<
name|?
argument_list|>
name|cls
parameter_list|)
block|{
name|Objects
operator|.
name|requireNonNull
argument_list|(
name|cls
argument_list|,
literal|"cls"
argument_list|)
expr_stmt|;
comment|/*          * That's an amzingly easy case, because getName() returns the signature. That's what we would have liked anyway.          */
if|if
condition|(
name|cls
operator|.
name|isArray
argument_list|()
condition|)
block|{
return|return
name|getType
argument_list|(
name|cls
operator|.
name|getName
argument_list|()
argument_list|)
return|;
block|}
if|if
condition|(
operator|!
name|cls
operator|.
name|isPrimitive
argument_list|()
condition|)
block|{
comment|// "Real" class
return|return
name|ObjectType
operator|.
name|getInstance
argument_list|(
name|cls
operator|.
name|getName
argument_list|()
argument_list|)
return|;
block|}
if|if
condition|(
name|cls
operator|==
name|Integer
operator|.
name|TYPE
condition|)
block|{
return|return
name|INT
return|;
block|}
if|if
condition|(
name|cls
operator|==
name|Void
operator|.
name|TYPE
condition|)
block|{
return|return
name|VOID
return|;
block|}
if|if
condition|(
name|cls
operator|==
name|Double
operator|.
name|TYPE
condition|)
block|{
return|return
name|DOUBLE
return|;
block|}
if|if
condition|(
name|cls
operator|==
name|Float
operator|.
name|TYPE
condition|)
block|{
return|return
name|FLOAT
return|;
block|}
if|if
condition|(
name|cls
operator|==
name|Boolean
operator|.
name|TYPE
condition|)
block|{
return|return
name|BOOLEAN
return|;
block|}
if|if
condition|(
name|cls
operator|==
name|Byte
operator|.
name|TYPE
condition|)
block|{
return|return
name|BYTE
return|;
block|}
if|if
condition|(
name|cls
operator|==
name|Short
operator|.
name|TYPE
condition|)
block|{
return|return
name|SHORT
return|;
block|}
if|if
condition|(
name|cls
operator|==
name|Long
operator|.
name|TYPE
condition|)
block|{
return|return
name|LONG
return|;
block|}
if|if
condition|(
name|cls
operator|==
name|Character
operator|.
name|TYPE
condition|)
block|{
return|return
name|CHAR
return|;
block|}
throw|throw
operator|new
name|IllegalStateException
argument_list|(
literal|"Unknown primitive type "
operator|+
name|cls
argument_list|)
throw|;
block|}
comment|/**      * Convert signature to a Type object.      *      * @param signature signature string such as Ljava/lang/String;      * @return type object      */
specifier|public
specifier|static
name|Type
name|getType
parameter_list|(
specifier|final
name|String
name|signature
parameter_list|)
throws|throws
name|StringIndexOutOfBoundsException
block|{
specifier|final
name|byte
name|type
init|=
name|Utility
operator|.
name|typeOfSignature
argument_list|(
name|signature
argument_list|)
decl_stmt|;
if|if
condition|(
name|type
operator|<=
name|Const
operator|.
name|T_VOID
condition|)
block|{
comment|// corrected concurrent private static field access
name|wrap
argument_list|(
name|CONSUMED_CHARS
argument_list|,
literal|1
argument_list|)
expr_stmt|;
return|return
name|BasicType
operator|.
name|getType
argument_list|(
name|type
argument_list|)
return|;
block|}
if|if
condition|(
name|type
operator|!=
name|Const
operator|.
name|T_ARRAY
condition|)
block|{
comment|// type == T_REFERENCE
comment|// Utility.typeSignatureToString understands how to parse generic types.
specifier|final
name|String
name|parsedSignature
init|=
name|Utility
operator|.
name|typeSignatureToString
argument_list|(
name|signature
argument_list|,
literal|false
argument_list|)
decl_stmt|;
name|wrap
argument_list|(
name|CONSUMED_CHARS
argument_list|,
name|parsedSignature
operator|.
name|length
argument_list|()
operator|+
literal|2
argument_list|)
expr_stmt|;
comment|// "Lblabla;" 'L' and ';' are removed
return|return
name|ObjectType
operator|.
name|getInstance
argument_list|(
name|Utility
operator|.
name|pathToPackage
argument_list|(
name|parsedSignature
argument_list|)
argument_list|)
return|;
block|}
name|int
name|dim
init|=
literal|0
decl_stmt|;
do|do
block|{
comment|// Count dimensions
name|dim
operator|++
expr_stmt|;
block|}
do|while
condition|(
name|signature
operator|.
name|charAt
argument_list|(
name|dim
argument_list|)
operator|==
literal|'['
condition|)
do|;
comment|// Recurse, but just once, if the signature is ok
specifier|final
name|Type
name|t
init|=
name|getType
argument_list|(
name|signature
operator|.
name|substring
argument_list|(
name|dim
argument_list|)
argument_list|)
decl_stmt|;
comment|// corrected concurrent private static field access
comment|// consumed_chars += dim; // update counter - is replaced by
specifier|final
name|int
name|temp
init|=
name|unwrap
argument_list|(
name|CONSUMED_CHARS
argument_list|)
operator|+
name|dim
decl_stmt|;
name|wrap
argument_list|(
name|CONSUMED_CHARS
argument_list|,
name|temp
argument_list|)
expr_stmt|;
return|return
operator|new
name|ArrayType
argument_list|(
name|t
argument_list|,
name|dim
argument_list|)
return|;
block|}
comment|/**      * Convert runtime java.lang.Class[] to BCEL Type objects.      *      * @param classes an array of runtime class objects      * @return array of corresponding Type objects      */
specifier|public
specifier|static
name|Type
index|[]
name|getTypes
parameter_list|(
specifier|final
name|Class
argument_list|<
name|?
argument_list|>
index|[]
name|classes
parameter_list|)
block|{
specifier|final
name|Type
index|[]
name|ret
init|=
operator|new
name|Type
index|[
name|classes
operator|.
name|length
index|]
decl_stmt|;
name|Arrays
operator|.
name|setAll
argument_list|(
name|ret
argument_list|,
name|i
lambda|->
name|getType
argument_list|(
name|classes
index|[
name|i
index|]
argument_list|)
argument_list|)
expr_stmt|;
return|return
name|ret
return|;
block|}
specifier|static
name|int
name|getTypeSize
parameter_list|(
specifier|final
name|String
name|signature
parameter_list|)
throws|throws
name|StringIndexOutOfBoundsException
block|{
specifier|final
name|byte
name|type
init|=
name|Utility
operator|.
name|typeOfSignature
argument_list|(
name|signature
argument_list|)
decl_stmt|;
if|if
condition|(
name|type
operator|<=
name|Const
operator|.
name|T_VOID
condition|)
block|{
return|return
name|encode
argument_list|(
name|BasicType
operator|.
name|getType
argument_list|(
name|type
argument_list|)
operator|.
name|getSize
argument_list|()
argument_list|,
literal|1
argument_list|)
return|;
block|}
if|if
condition|(
name|type
operator|==
name|Const
operator|.
name|T_ARRAY
condition|)
block|{
name|int
name|dim
init|=
literal|0
decl_stmt|;
do|do
block|{
comment|// Count dimensions
name|dim
operator|++
expr_stmt|;
block|}
do|while
condition|(
name|signature
operator|.
name|charAt
argument_list|(
name|dim
argument_list|)
operator|==
literal|'['
condition|)
do|;
comment|// Recurse, but just once, if the signature is ok
specifier|final
name|int
name|consumed
init|=
name|consumed
argument_list|(
name|getTypeSize
argument_list|(
name|signature
operator|.
name|substring
argument_list|(
name|dim
argument_list|)
argument_list|)
argument_list|)
decl_stmt|;
return|return
name|encode
argument_list|(
literal|1
argument_list|,
name|dim
operator|+
name|consumed
argument_list|)
return|;
block|}
specifier|final
name|int
name|index
init|=
name|signature
operator|.
name|indexOf
argument_list|(
literal|';'
argument_list|)
decl_stmt|;
comment|// Look for closing ';'
if|if
condition|(
name|index
operator|<
literal|0
condition|)
block|{
throw|throw
operator|new
name|ClassFormatException
argument_list|(
literal|"Invalid signature: "
operator|+
name|signature
argument_list|)
throw|;
block|}
return|return
name|encode
argument_list|(
literal|1
argument_list|,
name|index
operator|+
literal|1
argument_list|)
return|;
block|}
specifier|static
name|int
name|size
parameter_list|(
specifier|final
name|int
name|coded
parameter_list|)
block|{
return|return
name|coded
operator|&
literal|3
return|;
block|}
specifier|private
specifier|static
name|int
name|unwrap
parameter_list|(
specifier|final
name|ThreadLocal
argument_list|<
name|Integer
argument_list|>
name|tl
parameter_list|)
block|{
return|return
name|tl
operator|.
name|get
argument_list|()
operator|.
name|intValue
argument_list|()
return|;
block|}
specifier|private
specifier|static
name|void
name|wrap
parameter_list|(
specifier|final
name|ThreadLocal
argument_list|<
name|Integer
argument_list|>
name|tl
parameter_list|,
specifier|final
name|int
name|value
parameter_list|)
block|{
name|tl
operator|.
name|set
argument_list|(
name|Integer
operator|.
name|valueOf
argument_list|(
name|value
argument_list|)
argument_list|)
expr_stmt|;
block|}
comment|/**      * @deprecated (since 6.0) will be made private; do not access directly, use getter/setter      */
annotation|@
name|Deprecated
specifier|protected
name|byte
name|type
decl_stmt|;
comment|// TODO should be final (and private)
comment|/**      * @deprecated (since 6.0) will be made private; do not access directly, use getter/setter      */
annotation|@
name|Deprecated
specifier|protected
name|String
name|signature
decl_stmt|;
comment|// signature for the type TODO should be private
specifier|protected
name|Type
parameter_list|(
specifier|final
name|byte
name|type
parameter_list|,
specifier|final
name|String
name|signature
parameter_list|)
block|{
name|this
operator|.
name|type
operator|=
name|type
expr_stmt|;
name|this
operator|.
name|signature
operator|=
name|signature
expr_stmt|;
block|}
comment|/**      * @return whether the Types are equal      */
annotation|@
name|Override
specifier|public
name|boolean
name|equals
parameter_list|(
specifier|final
name|Object
name|o
parameter_list|)
block|{
if|if
condition|(
name|o
operator|instanceof
name|Type
condition|)
block|{
specifier|final
name|Type
name|t
init|=
operator|(
name|Type
operator|)
name|o
decl_stmt|;
return|return
name|type
operator|==
name|t
operator|.
name|type
operator|&&
name|signature
operator|.
name|equals
argument_list|(
name|t
operator|.
name|signature
argument_list|)
return|;
block|}
return|return
literal|false
return|;
block|}
specifier|public
name|String
name|getClassName
parameter_list|()
block|{
return|return
name|toString
argument_list|()
return|;
block|}
comment|/**      * @return signature for given type.      */
specifier|public
name|String
name|getSignature
parameter_list|()
block|{
return|return
name|signature
return|;
block|}
comment|/**      * @return stack size of this type (2 for long and double, 0 for void, 1 otherwise)      */
specifier|public
name|int
name|getSize
parameter_list|()
block|{
switch|switch
condition|(
name|type
condition|)
block|{
case|case
name|Const
operator|.
name|T_DOUBLE
case|:
case|case
name|Const
operator|.
name|T_LONG
case|:
return|return
literal|2
return|;
case|case
name|Const
operator|.
name|T_VOID
case|:
return|return
literal|0
return|;
default|default:
return|return
literal|1
return|;
block|}
block|}
comment|/**      * @return type as defined in Constants      */
specifier|public
name|byte
name|getType
parameter_list|()
block|{
return|return
name|type
return|;
block|}
comment|/**      * @return hashcode of Type      */
annotation|@
name|Override
specifier|public
name|int
name|hashCode
parameter_list|()
block|{
return|return
name|type
operator|^
name|signature
operator|.
name|hashCode
argument_list|()
return|;
block|}
comment|/**      * boolean, short and char variable are considered as int in the stack or local variable area. Returns {@link Type#INT}      * for {@link Type#BOOLEAN}, {@link Type#SHORT} or {@link Type#CHAR}, otherwise returns the given type.      *      * @since 6.0      */
specifier|public
name|Type
name|normalizeForStackOrLocal
parameter_list|()
block|{
if|if
condition|(
name|this
operator|==
name|Type
operator|.
name|BOOLEAN
operator|||
name|this
operator|==
name|Type
operator|.
name|BYTE
operator|||
name|this
operator|==
name|Type
operator|.
name|SHORT
operator|||
name|this
operator|==
name|Type
operator|.
name|CHAR
condition|)
block|{
return|return
name|Type
operator|.
name|INT
return|;
block|}
return|return
name|this
return|;
block|}
comment|/*      * Currently only used by the ArrayType constructor. The signature has a complicated dependency on other parameter so      * it's tricky to do it in a call to the super ctor.      */
name|void
name|setSignature
parameter_list|(
specifier|final
name|String
name|signature
parameter_list|)
block|{
name|this
operator|.
name|signature
operator|=
name|signature
expr_stmt|;
block|}
comment|/**      * @return Type string, e.g. 'int[]'      */
annotation|@
name|Override
specifier|public
name|String
name|toString
parameter_list|()
block|{
return|return
name|this
operator|.
name|equals
argument_list|(
name|Type
operator|.
name|NULL
argument_list|)
operator|||
name|type
operator|>=
name|Const
operator|.
name|T_UNKNOWN
condition|?
name|signature
else|:
name|Utility
operator|.
name|signatureToString
argument_list|(
name|signature
argument_list|,
literal|false
argument_list|)
return|;
block|}
block|}
end_class

end_unit

