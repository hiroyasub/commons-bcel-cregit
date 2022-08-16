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
name|bcel
operator|.
name|verifier
operator|.
name|statics
package|;
end_package

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
name|AnnotationDefault
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
name|AnnotationEntry
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
name|Annotations
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
name|BootstrapMethods
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
name|Code
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
name|CodeException
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
name|ConstantClass
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
name|ConstantDouble
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
name|ConstantDynamic
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
name|ConstantFieldref
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
name|ConstantFloat
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
name|ConstantInteger
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
name|ConstantInterfaceMethodref
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
name|ConstantInvokeDynamic
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
name|ConstantLong
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
name|ConstantMethodHandle
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
name|ConstantMethodType
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
name|ConstantMethodref
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
name|ConstantModule
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
name|ConstantNameAndType
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
name|ConstantPackage
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
name|ConstantPool
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
name|ConstantString
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
name|ConstantUtf8
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
name|ConstantValue
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
name|Deprecated
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
name|EnclosingMethod
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
name|ExceptionTable
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
name|Field
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
name|InnerClass
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
name|InnerClasses
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
name|JavaClass
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
name|LineNumber
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
name|LineNumberTable
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
name|LocalVariable
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
name|LocalVariableTable
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
name|LocalVariableTypeTable
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
name|Method
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
name|MethodParameters
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
name|NestMembers
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
name|Node
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
name|ParameterAnnotationEntry
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
name|ParameterAnnotations
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
name|Signature
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
name|SourceFile
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
name|StackMap
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
name|StackMapEntry
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
name|Synthetic
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
name|Unknown
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
name|verifier
operator|.
name|exc
operator|.
name|AssertionViolatedException
import|;
end_import

begin_comment
comment|/**  * BCEL's Node classes (those from the classfile API that<B>accept()</B> Visitor instances) have<B>toString()</B>  * methods that were not designed to be robust, this gap is closed by this class. When performing class file  * verification, it may be useful to output which entity (e.g. a<B>Code</B> instance) is not satisfying the verifier's  * constraints, but in this case it could be possible for the<B>toString()</B> method to throw a RuntimeException. A  * (new StringRepresentation(Node n)).toString() never throws any exception. Note that this class also serves as a  * placeholder for more sophisticated message handling in future versions of JustIce.  *  */
end_comment

begin_class
specifier|public
class|class
name|StringRepresentation
extends|extends
name|org
operator|.
name|apache
operator|.
name|bcel
operator|.
name|classfile
operator|.
name|EmptyVisitor
block|{
comment|/** The string representation, created by a visitXXX() method, output by toString(). */
specifier|private
name|String
name|tostring
decl_stmt|;
comment|/** The node we ask for its string representation. Not really needed; only for debug output. */
specifier|private
specifier|final
name|Node
name|n
decl_stmt|;
comment|/**      * Creates a new StringRepresentation object which is the representation of n.      *      * @param n The node to represent.      * @see #toString()      */
specifier|public
name|StringRepresentation
parameter_list|(
specifier|final
name|Node
name|n
parameter_list|)
block|{
name|this
operator|.
name|n
operator|=
name|n
expr_stmt|;
name|n
operator|.
name|accept
argument_list|(
name|this
argument_list|)
expr_stmt|;
comment|// assign a string representation to field 'tostring' if we know n's class.
block|}
comment|/**      * Returns the String representation.      */
annotation|@
name|Override
specifier|public
name|String
name|toString
parameter_list|()
block|{
comment|// The run-time check below is needed because we don't want to omit inheritance
comment|// of "EmptyVisitor" and provide a thousand empty methods.
comment|// However, in terms of performance this would be a better idea.
comment|// If some new "Node" is defined in BCEL (such as some concrete "Attribute"), we
comment|// want to know that this class has also to be adapted.
if|if
condition|(
name|tostring
operator|==
literal|null
condition|)
block|{
throw|throw
operator|new
name|AssertionViolatedException
argument_list|(
literal|"Please adapt '"
operator|+
name|getClass
argument_list|()
operator|+
literal|"' to deal with objects of class '"
operator|+
name|n
operator|.
name|getClass
argument_list|()
operator|+
literal|"'."
argument_list|)
throw|;
block|}
return|return
name|tostring
return|;
block|}
comment|/**      * Returns the String representation of the Node object obj; this is obj.toString() if it does not throw any      * RuntimeException, or else it is a string derived only from obj's class name.      */
specifier|private
name|String
name|toString
parameter_list|(
specifier|final
name|Node
name|obj
parameter_list|)
block|{
name|String
name|ret
decl_stmt|;
try|try
block|{
name|ret
operator|=
name|obj
operator|.
name|toString
argument_list|()
expr_stmt|;
block|}
catch|catch
parameter_list|(
specifier|final
name|RuntimeException
name|e
parameter_list|)
block|{
comment|// including ClassFormatException, trying to convert the "signature" of a ReturnaddressType LocalVariable
comment|// (shouldn't occur, but people do crazy things)
name|String
name|s
init|=
name|obj
operator|.
name|getClass
argument_list|()
operator|.
name|getName
argument_list|()
decl_stmt|;
name|s
operator|=
name|s
operator|.
name|substring
argument_list|(
name|s
operator|.
name|lastIndexOf
argument_list|(
literal|"."
argument_list|)
operator|+
literal|1
argument_list|)
expr_stmt|;
name|ret
operator|=
literal|"<<"
operator|+
name|s
operator|+
literal|">>"
expr_stmt|;
block|}
return|return
name|ret
return|;
block|}
comment|/**      * @since 6.0      */
annotation|@
name|Override
specifier|public
name|void
name|visitAnnotation
parameter_list|(
specifier|final
name|Annotations
name|obj
parameter_list|)
block|{
comment|// this is invoked whenever an annotation is found
comment|// when verifier is passed over a class
name|tostring
operator|=
name|toString
argument_list|(
name|obj
argument_list|)
expr_stmt|;
block|}
comment|/**      * @since 6.0      */
annotation|@
name|Override
specifier|public
name|void
name|visitAnnotationDefault
parameter_list|(
specifier|final
name|AnnotationDefault
name|obj
parameter_list|)
block|{
name|tostring
operator|=
name|toString
argument_list|(
name|obj
argument_list|)
expr_stmt|;
block|}
comment|/**      * @since 6.0      */
annotation|@
name|Override
specifier|public
name|void
name|visitAnnotationEntry
parameter_list|(
specifier|final
name|AnnotationEntry
name|obj
parameter_list|)
block|{
name|tostring
operator|=
name|toString
argument_list|(
name|obj
argument_list|)
expr_stmt|;
block|}
comment|/**      * @since 6.0      */
annotation|@
name|Override
specifier|public
name|void
name|visitBootstrapMethods
parameter_list|(
specifier|final
name|BootstrapMethods
name|obj
parameter_list|)
block|{
name|tostring
operator|=
name|toString
argument_list|(
name|obj
argument_list|)
expr_stmt|;
block|}
comment|////////////////////////////////
comment|// Visitor methods start here //
comment|////////////////////////////////
comment|// We don't of course need to call some default implementation:
comment|// e.g. we could also simply output "Code" instead of a possibly
comment|// lengthy Code attribute's toString().
annotation|@
name|Override
specifier|public
name|void
name|visitCode
parameter_list|(
specifier|final
name|Code
name|obj
parameter_list|)
block|{
comment|// tostring = toString(obj);
name|tostring
operator|=
literal|"<CODE>"
expr_stmt|;
comment|// We don't need real code outputs.
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitCodeException
parameter_list|(
specifier|final
name|CodeException
name|obj
parameter_list|)
block|{
name|tostring
operator|=
name|toString
argument_list|(
name|obj
argument_list|)
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitConstantClass
parameter_list|(
specifier|final
name|ConstantClass
name|obj
parameter_list|)
block|{
name|tostring
operator|=
name|toString
argument_list|(
name|obj
argument_list|)
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitConstantDouble
parameter_list|(
specifier|final
name|ConstantDouble
name|obj
parameter_list|)
block|{
name|tostring
operator|=
name|toString
argument_list|(
name|obj
argument_list|)
expr_stmt|;
block|}
comment|/**      * @since 6.6.0      */
annotation|@
name|Override
specifier|public
name|void
name|visitConstantDynamic
parameter_list|(
specifier|final
name|ConstantDynamic
name|obj
parameter_list|)
block|{
name|tostring
operator|=
name|toString
argument_list|(
name|obj
argument_list|)
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitConstantFieldref
parameter_list|(
specifier|final
name|ConstantFieldref
name|obj
parameter_list|)
block|{
name|tostring
operator|=
name|toString
argument_list|(
name|obj
argument_list|)
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitConstantFloat
parameter_list|(
specifier|final
name|ConstantFloat
name|obj
parameter_list|)
block|{
name|tostring
operator|=
name|toString
argument_list|(
name|obj
argument_list|)
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitConstantInteger
parameter_list|(
specifier|final
name|ConstantInteger
name|obj
parameter_list|)
block|{
name|tostring
operator|=
name|toString
argument_list|(
name|obj
argument_list|)
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitConstantInterfaceMethodref
parameter_list|(
specifier|final
name|ConstantInterfaceMethodref
name|obj
parameter_list|)
block|{
name|tostring
operator|=
name|toString
argument_list|(
name|obj
argument_list|)
expr_stmt|;
block|}
comment|/**      * @since 6.0      */
annotation|@
name|Override
specifier|public
name|void
name|visitConstantInvokeDynamic
parameter_list|(
specifier|final
name|ConstantInvokeDynamic
name|obj
parameter_list|)
block|{
name|tostring
operator|=
name|toString
argument_list|(
name|obj
argument_list|)
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitConstantLong
parameter_list|(
specifier|final
name|ConstantLong
name|obj
parameter_list|)
block|{
name|tostring
operator|=
name|toString
argument_list|(
name|obj
argument_list|)
expr_stmt|;
block|}
comment|/**      * @since 6.0      */
annotation|@
name|Override
specifier|public
name|void
name|visitConstantMethodHandle
parameter_list|(
specifier|final
name|ConstantMethodHandle
name|obj
parameter_list|)
block|{
name|tostring
operator|=
name|toString
argument_list|(
name|obj
argument_list|)
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitConstantMethodref
parameter_list|(
specifier|final
name|ConstantMethodref
name|obj
parameter_list|)
block|{
name|tostring
operator|=
name|toString
argument_list|(
name|obj
argument_list|)
expr_stmt|;
block|}
comment|/**      * @since 6.0      */
annotation|@
name|Override
specifier|public
name|void
name|visitConstantMethodType
parameter_list|(
specifier|final
name|ConstantMethodType
name|obj
parameter_list|)
block|{
name|tostring
operator|=
name|toString
argument_list|(
name|obj
argument_list|)
expr_stmt|;
block|}
comment|/**      * @since 6.6.0      */
annotation|@
name|Override
specifier|public
name|void
name|visitConstantModule
parameter_list|(
specifier|final
name|ConstantModule
name|obj
parameter_list|)
block|{
name|tostring
operator|=
name|toString
argument_list|(
name|obj
argument_list|)
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitConstantNameAndType
parameter_list|(
specifier|final
name|ConstantNameAndType
name|obj
parameter_list|)
block|{
name|tostring
operator|=
name|toString
argument_list|(
name|obj
argument_list|)
expr_stmt|;
block|}
comment|/**      * @since 6.6.0      */
annotation|@
name|Override
specifier|public
name|void
name|visitConstantPackage
parameter_list|(
specifier|final
name|ConstantPackage
name|obj
parameter_list|)
block|{
name|tostring
operator|=
name|toString
argument_list|(
name|obj
argument_list|)
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitConstantPool
parameter_list|(
specifier|final
name|ConstantPool
name|obj
parameter_list|)
block|{
name|tostring
operator|=
name|toString
argument_list|(
name|obj
argument_list|)
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitConstantString
parameter_list|(
specifier|final
name|ConstantString
name|obj
parameter_list|)
block|{
name|tostring
operator|=
name|toString
argument_list|(
name|obj
argument_list|)
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitConstantUtf8
parameter_list|(
specifier|final
name|ConstantUtf8
name|obj
parameter_list|)
block|{
name|tostring
operator|=
name|toString
argument_list|(
name|obj
argument_list|)
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitConstantValue
parameter_list|(
specifier|final
name|ConstantValue
name|obj
parameter_list|)
block|{
name|tostring
operator|=
name|toString
argument_list|(
name|obj
argument_list|)
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitDeprecated
parameter_list|(
specifier|final
name|Deprecated
name|obj
parameter_list|)
block|{
name|tostring
operator|=
name|toString
argument_list|(
name|obj
argument_list|)
expr_stmt|;
block|}
comment|/**      * @since 6.0      */
annotation|@
name|Override
specifier|public
name|void
name|visitEnclosingMethod
parameter_list|(
specifier|final
name|EnclosingMethod
name|obj
parameter_list|)
block|{
name|tostring
operator|=
name|toString
argument_list|(
name|obj
argument_list|)
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitExceptionTable
parameter_list|(
specifier|final
name|ExceptionTable
name|obj
parameter_list|)
block|{
name|tostring
operator|=
name|toString
argument_list|(
name|obj
argument_list|)
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitField
parameter_list|(
specifier|final
name|Field
name|obj
parameter_list|)
block|{
name|tostring
operator|=
name|toString
argument_list|(
name|obj
argument_list|)
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitInnerClass
parameter_list|(
specifier|final
name|InnerClass
name|obj
parameter_list|)
block|{
name|tostring
operator|=
name|toString
argument_list|(
name|obj
argument_list|)
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitInnerClasses
parameter_list|(
specifier|final
name|InnerClasses
name|obj
parameter_list|)
block|{
name|tostring
operator|=
name|toString
argument_list|(
name|obj
argument_list|)
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitJavaClass
parameter_list|(
specifier|final
name|JavaClass
name|obj
parameter_list|)
block|{
name|tostring
operator|=
name|toString
argument_list|(
name|obj
argument_list|)
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitLineNumber
parameter_list|(
specifier|final
name|LineNumber
name|obj
parameter_list|)
block|{
name|tostring
operator|=
name|toString
argument_list|(
name|obj
argument_list|)
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitLineNumberTable
parameter_list|(
specifier|final
name|LineNumberTable
name|obj
parameter_list|)
block|{
name|tostring
operator|=
literal|"<LineNumberTable: "
operator|+
name|toString
argument_list|(
name|obj
argument_list|)
operator|+
literal|">"
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitLocalVariable
parameter_list|(
specifier|final
name|LocalVariable
name|obj
parameter_list|)
block|{
name|tostring
operator|=
name|toString
argument_list|(
name|obj
argument_list|)
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitLocalVariableTable
parameter_list|(
specifier|final
name|LocalVariableTable
name|obj
parameter_list|)
block|{
name|tostring
operator|=
literal|"<LocalVariableTable: "
operator|+
name|toString
argument_list|(
name|obj
argument_list|)
operator|+
literal|">"
expr_stmt|;
block|}
comment|/**      * @since 6.0      */
annotation|@
name|Override
specifier|public
name|void
name|visitLocalVariableTypeTable
parameter_list|(
specifier|final
name|LocalVariableTypeTable
name|obj
parameter_list|)
block|{
comment|// this is invoked whenever a local variable type is found
comment|// when verifier is passed over a class
name|tostring
operator|=
name|toString
argument_list|(
name|obj
argument_list|)
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitMethod
parameter_list|(
specifier|final
name|Method
name|obj
parameter_list|)
block|{
name|tostring
operator|=
name|toString
argument_list|(
name|obj
argument_list|)
expr_stmt|;
block|}
comment|/**      * @since 6.0      */
annotation|@
name|Override
specifier|public
name|void
name|visitMethodParameters
parameter_list|(
specifier|final
name|MethodParameters
name|obj
parameter_list|)
block|{
name|tostring
operator|=
name|toString
argument_list|(
name|obj
argument_list|)
expr_stmt|;
block|}
comment|/**      * @since 6.4.0      */
annotation|@
name|Override
specifier|public
name|void
name|visitNestMembers
parameter_list|(
specifier|final
name|NestMembers
name|obj
parameter_list|)
block|{
name|tostring
operator|=
name|toString
argument_list|(
name|obj
argument_list|)
expr_stmt|;
block|}
comment|/**      * @since 6.0      */
annotation|@
name|Override
specifier|public
name|void
name|visitParameterAnnotation
parameter_list|(
specifier|final
name|ParameterAnnotations
name|obj
parameter_list|)
block|{
name|tostring
operator|=
name|toString
argument_list|(
name|obj
argument_list|)
expr_stmt|;
block|}
comment|/**      * @since 6.0      */
annotation|@
name|Override
specifier|public
name|void
name|visitParameterAnnotationEntry
parameter_list|(
specifier|final
name|ParameterAnnotationEntry
name|obj
parameter_list|)
block|{
name|tostring
operator|=
name|toString
argument_list|(
name|obj
argument_list|)
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitSignature
parameter_list|(
specifier|final
name|Signature
name|obj
parameter_list|)
block|{
name|tostring
operator|=
name|toString
argument_list|(
name|obj
argument_list|)
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitSourceFile
parameter_list|(
specifier|final
name|SourceFile
name|obj
parameter_list|)
block|{
name|tostring
operator|=
name|toString
argument_list|(
name|obj
argument_list|)
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitStackMap
parameter_list|(
specifier|final
name|StackMap
name|obj
parameter_list|)
block|{
name|tostring
operator|=
name|toString
argument_list|(
name|obj
argument_list|)
expr_stmt|;
block|}
comment|/**      * @since 6.0      */
annotation|@
name|Override
specifier|public
name|void
name|visitStackMapEntry
parameter_list|(
specifier|final
name|StackMapEntry
name|obj
parameter_list|)
block|{
name|tostring
operator|=
name|toString
argument_list|(
name|obj
argument_list|)
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitSynthetic
parameter_list|(
specifier|final
name|Synthetic
name|obj
parameter_list|)
block|{
name|tostring
operator|=
name|toString
argument_list|(
name|obj
argument_list|)
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|visitUnknown
parameter_list|(
specifier|final
name|Unknown
name|obj
parameter_list|)
block|{
name|tostring
operator|=
name|toString
argument_list|(
name|obj
argument_list|)
expr_stmt|;
block|}
block|}
end_class

end_unit

