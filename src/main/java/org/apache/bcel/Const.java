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
package|;
end_package

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
name|Collections
import|;
end_import

begin_comment
comment|/**  * Constants for the project, mostly defined in the JVM specification.  *  * @since 6.0 (intended to replace the Constants interface)  */
end_comment

begin_class
specifier|public
specifier|final
class|class
name|Const
block|{
comment|/**      * Java class file format Magic number (0xCAFEBABE)      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-4.html#jvms-4.1-200-A"> The ClassFile Structure      *      in The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|int
name|JVM_CLASSFILE_MAGIC
init|=
literal|0xCAFEBABE
decl_stmt|;
comment|/**      * Major version number of class files for Java 1.1.      *      * @see #MINOR_1_1      */
specifier|public
specifier|static
specifier|final
name|short
name|MAJOR_1_1
init|=
literal|45
decl_stmt|;
comment|/**      * Minor version number of class files for Java 1.1.      *      * @see #MAJOR_1_1      */
specifier|public
specifier|static
specifier|final
name|short
name|MINOR_1_1
init|=
literal|3
decl_stmt|;
comment|/**      * Major version number of class files for Java 1.2.      *      * @see #MINOR_1_2      */
specifier|public
specifier|static
specifier|final
name|short
name|MAJOR_1_2
init|=
literal|46
decl_stmt|;
comment|/**      * Minor version number of class files for Java 1.2.      *      * @see #MAJOR_1_2      */
specifier|public
specifier|static
specifier|final
name|short
name|MINOR_1_2
init|=
literal|0
decl_stmt|;
comment|/**      * Major version number of class files for Java 1.2.      *      * @see #MINOR_1_2      */
specifier|public
specifier|static
specifier|final
name|short
name|MAJOR_1_3
init|=
literal|47
decl_stmt|;
comment|/**      * Minor version number of class files for Java 1.3.      *      * @see #MAJOR_1_3      */
specifier|public
specifier|static
specifier|final
name|short
name|MINOR_1_3
init|=
literal|0
decl_stmt|;
comment|/**      * Major version number of class files for Java 1.3.      *      * @see #MINOR_1_3      */
specifier|public
specifier|static
specifier|final
name|short
name|MAJOR_1_4
init|=
literal|48
decl_stmt|;
comment|/**      * Minor version number of class files for Java 1.4.      *      * @see #MAJOR_1_4      */
specifier|public
specifier|static
specifier|final
name|short
name|MINOR_1_4
init|=
literal|0
decl_stmt|;
comment|/**      * Major version number of class files for Java 1.4.      *      * @see #MINOR_1_4      */
specifier|public
specifier|static
specifier|final
name|short
name|MAJOR_1_5
init|=
literal|49
decl_stmt|;
comment|/**      * Minor version number of class files for Java 1.5.      *      * @see #MAJOR_1_5      */
specifier|public
specifier|static
specifier|final
name|short
name|MINOR_1_5
init|=
literal|0
decl_stmt|;
comment|/**      * Major version number of class files for Java 1.6.      *      * @see #MINOR_1_6      */
specifier|public
specifier|static
specifier|final
name|short
name|MAJOR_1_6
init|=
literal|50
decl_stmt|;
comment|/**      * Minor version number of class files for Java 1.6.      *      * @see #MAJOR_1_6      */
specifier|public
specifier|static
specifier|final
name|short
name|MINOR_1_6
init|=
literal|0
decl_stmt|;
comment|/**      * Major version number of class files for Java 1.7.      *      * @see #MINOR_1_7      */
specifier|public
specifier|static
specifier|final
name|short
name|MAJOR_1_7
init|=
literal|51
decl_stmt|;
comment|/**      * Minor version number of class files for Java 1.7.      *      * @see #MAJOR_1_7      */
specifier|public
specifier|static
specifier|final
name|short
name|MINOR_1_7
init|=
literal|0
decl_stmt|;
comment|/**      * Major version number of class files for Java 1.8.      *      * @see #MINOR_1_8      */
specifier|public
specifier|static
specifier|final
name|short
name|MAJOR_1_8
init|=
literal|52
decl_stmt|;
comment|/**      * Minor version number of class files for Java 1.8.      *      * @see #MAJOR_1_8      */
specifier|public
specifier|static
specifier|final
name|short
name|MINOR_1_8
init|=
literal|0
decl_stmt|;
comment|/**      * Major version number of class files for Java 9.      *      * @see #MINOR_9      */
specifier|public
specifier|static
specifier|final
name|short
name|MAJOR_9
init|=
literal|53
decl_stmt|;
comment|/**      * Minor version number of class files for Java 9.      *      * @see #MAJOR_9      */
specifier|public
specifier|static
specifier|final
name|short
name|MINOR_9
init|=
literal|0
decl_stmt|;
comment|/**      * @deprecated Use {@link #MAJOR_9} instead      */
annotation|@
name|Deprecated
specifier|public
specifier|static
specifier|final
name|short
name|MAJOR_1_9
init|=
name|MAJOR_9
decl_stmt|;
comment|/**      * @deprecated Use {@link #MINOR_9} instead      */
annotation|@
name|Deprecated
specifier|public
specifier|static
specifier|final
name|short
name|MINOR_1_9
init|=
name|MINOR_9
decl_stmt|;
comment|/**      * Major version number of class files for Java 10.      *      * @see #MINOR_10      */
specifier|public
specifier|static
specifier|final
name|short
name|MAJOR_10
init|=
literal|54
decl_stmt|;
comment|/**      * Minor version number of class files for Java 10.      *      * @see #MAJOR_10      */
specifier|public
specifier|static
specifier|final
name|short
name|MINOR_10
init|=
literal|0
decl_stmt|;
comment|/**      * Major version number of class files for Java 11.      *      * @see #MINOR_11      */
specifier|public
specifier|static
specifier|final
name|short
name|MAJOR_11
init|=
literal|55
decl_stmt|;
comment|/**      * Minor version number of class files for Java 11.      *      * @see #MAJOR_11      */
specifier|public
specifier|static
specifier|final
name|short
name|MINOR_11
init|=
literal|0
decl_stmt|;
comment|/**      * Major version number of class files for Java 12.      *      * @see #MINOR_12      */
specifier|public
specifier|static
specifier|final
name|short
name|MAJOR_12
init|=
literal|56
decl_stmt|;
comment|/**      * Minor version number of class files for Java 12.      *      * @see #MAJOR_12      */
specifier|public
specifier|static
specifier|final
name|short
name|MINOR_12
init|=
literal|0
decl_stmt|;
comment|/**      * Major version number of class files for Java 13.      *      * @see #MINOR_13      */
specifier|public
specifier|static
specifier|final
name|short
name|MAJOR_13
init|=
literal|57
decl_stmt|;
comment|/**      * Minor version number of class files for Java 13.      *      * @see #MAJOR_13      */
specifier|public
specifier|static
specifier|final
name|short
name|MINOR_13
init|=
literal|0
decl_stmt|;
comment|/**      * Minor version number of class files for Java 14.      *      * @see #MAJOR_14      * @since 6.4.0      */
specifier|public
specifier|static
specifier|final
name|short
name|MINOR_14
init|=
literal|0
decl_stmt|;
comment|/**      * Minor version number of class files for Java 15.      *      * @see #MAJOR_15      * @since 6.6.0      */
specifier|public
specifier|static
specifier|final
name|short
name|MINOR_15
init|=
literal|0
decl_stmt|;
comment|/**      * Minor version number of class files for Java 16.      *      * @see #MAJOR_16      * @since 6.6.0      */
specifier|public
specifier|static
specifier|final
name|short
name|MINOR_16
init|=
literal|0
decl_stmt|;
comment|/**      * Minor version number of class files for Java 17.      *      * @see #MAJOR_17      * @since 6.6.0      */
specifier|public
specifier|static
specifier|final
name|short
name|MINOR_17
init|=
literal|0
decl_stmt|;
comment|/**      * Minor version number of class files for Java 18.      *      * @see #MAJOR_18      * @since 6.6.0      */
specifier|public
specifier|static
specifier|final
name|short
name|MINOR_18
init|=
literal|0
decl_stmt|;
comment|/**      * Minor version number of class files for Java 19.      *      * @see #MAJOR_19      * @since 6.6.0      */
specifier|public
specifier|static
specifier|final
name|short
name|MINOR_19
init|=
literal|0
decl_stmt|;
comment|/**      * Major version number of class files for Java 14.      *      * @see #MINOR_14      * @since 6.4.0      */
specifier|public
specifier|static
specifier|final
name|short
name|MAJOR_14
init|=
literal|58
decl_stmt|;
comment|/**      * Major version number of class files for Java 15.      *      * @see #MINOR_15      * @since 6.6.0      */
specifier|public
specifier|static
specifier|final
name|short
name|MAJOR_15
init|=
literal|59
decl_stmt|;
comment|/**      * Major version number of class files for Java 16.      *      * @see #MINOR_16      * @since 6.6.0      */
specifier|public
specifier|static
specifier|final
name|short
name|MAJOR_16
init|=
literal|60
decl_stmt|;
comment|/**      * Major version number of class files for Java 17.      *      * @see #MINOR_17      * @since 6.6.0      */
specifier|public
specifier|static
specifier|final
name|short
name|MAJOR_17
init|=
literal|61
decl_stmt|;
comment|/**      * Major version number of class files for Java 18.      *      * @see #MINOR_18      * @since 6.6.0      */
specifier|public
specifier|static
specifier|final
name|short
name|MAJOR_18
init|=
literal|62
decl_stmt|;
comment|/**      * Major version number of class files for Java 19.      *      * @see #MINOR_19      * @since 6.6.0      */
specifier|public
specifier|static
specifier|final
name|short
name|MAJOR_19
init|=
literal|63
decl_stmt|;
comment|/**      * Default major version number. Class file is for Java 1.1.      *      * @see #MAJOR_1_1      */
specifier|public
specifier|static
specifier|final
name|short
name|MAJOR
init|=
name|MAJOR_1_1
decl_stmt|;
comment|/**      * Default major version number. Class file is for Java 1.1.      *      * @see #MAJOR_1_1      */
specifier|public
specifier|static
specifier|final
name|short
name|MINOR
init|=
name|MINOR_1_1
decl_stmt|;
comment|/**      * Maximum value for an unsigned short.      */
specifier|public
specifier|static
specifier|final
name|int
name|MAX_SHORT
init|=
literal|65535
decl_stmt|;
comment|// 2^16 - 1
comment|/**      * Maximum value for an unsigned byte.      */
specifier|public
specifier|static
specifier|final
name|int
name|MAX_BYTE
init|=
literal|255
decl_stmt|;
comment|// 2^8 - 1
comment|/**      * One of the access flags for fields, methods, or classes.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se9/html/jvms-4.html#jvms-4.1-200-E.1"> Flag definitions for      *      Classes in the Java Virtual Machine Specification (Java SE 9 Edition).</a>      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se9/html/jvms-4.html#jvms-4.5"> Flag definitions for Fields      *      in the Java Virtual Machine Specification (Java SE 9 Edition).</a>      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se9/html/jvms-4.html#jvms-4.6"> Flag definitions for Methods      *      in the Java Virtual Machine Specification (Java SE 9 Edition).</a>      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se9/html/jvms-4.html#jvms-4.7.6-300-D.1-D.1"> Flag      *      definitions for Inner Classes in the Java Virtual Machine Specification (Java SE 9 Edition).</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|ACC_PUBLIC
init|=
literal|0x0001
decl_stmt|;
comment|/**      * One of the access flags for fields, methods, or classes.      *      * @see #ACC_PUBLIC      */
specifier|public
specifier|static
specifier|final
name|short
name|ACC_PRIVATE
init|=
literal|0x0002
decl_stmt|;
comment|/**      * One of the access flags for fields, methods, or classes.      *      * @see #ACC_PUBLIC      */
specifier|public
specifier|static
specifier|final
name|short
name|ACC_PROTECTED
init|=
literal|0x0004
decl_stmt|;
comment|/**      * One of the access flags for fields, methods, or classes.      *      * @see #ACC_PUBLIC      */
specifier|public
specifier|static
specifier|final
name|short
name|ACC_STATIC
init|=
literal|0x0008
decl_stmt|;
comment|/**      * One of the access flags for fields, methods, or classes.      *      * @see #ACC_PUBLIC      */
specifier|public
specifier|static
specifier|final
name|short
name|ACC_FINAL
init|=
literal|0x0010
decl_stmt|;
comment|/**      * One of the access flags for the Module attribute.      *      * @see #ACC_PUBLIC      */
specifier|public
specifier|static
specifier|final
name|short
name|ACC_OPEN
init|=
literal|0x0020
decl_stmt|;
comment|/**      * One of the access flags for classes.      *      * @see #ACC_PUBLIC      */
specifier|public
specifier|static
specifier|final
name|short
name|ACC_SUPER
init|=
literal|0x0020
decl_stmt|;
comment|/**      * One of the access flags for methods.      *      * @see #ACC_PUBLIC      */
specifier|public
specifier|static
specifier|final
name|short
name|ACC_SYNCHRONIZED
init|=
literal|0x0020
decl_stmt|;
comment|/**      * One of the access flags for the Module attribute.      *      * @see #ACC_PUBLIC      */
specifier|public
specifier|static
specifier|final
name|short
name|ACC_TRANSITIVE
init|=
literal|0x0020
decl_stmt|;
comment|/**      * One of the access flags for methods.      *      * @see #ACC_PUBLIC      */
specifier|public
specifier|static
specifier|final
name|short
name|ACC_BRIDGE
init|=
literal|0x0040
decl_stmt|;
comment|/**      * One of the access flags for the Module attribute.      *      * @see #ACC_PUBLIC      */
specifier|public
specifier|static
specifier|final
name|short
name|ACC_STATIC_PHASE
init|=
literal|0x0040
decl_stmt|;
comment|/**      * One of the access flags for fields.      *      * @see #ACC_PUBLIC      */
specifier|public
specifier|static
specifier|final
name|short
name|ACC_VOLATILE
init|=
literal|0x0040
decl_stmt|;
comment|/**      * One of the access flags for fields.      *      * @see #ACC_PUBLIC      */
specifier|public
specifier|static
specifier|final
name|short
name|ACC_TRANSIENT
init|=
literal|0x0080
decl_stmt|;
comment|/**      * One of the access flags for methods.      *      * @see #ACC_PUBLIC      */
specifier|public
specifier|static
specifier|final
name|short
name|ACC_VARARGS
init|=
literal|0x0080
decl_stmt|;
comment|/**      * One of the access flags for methods.      *      * @see #ACC_PUBLIC      */
specifier|public
specifier|static
specifier|final
name|short
name|ACC_NATIVE
init|=
literal|0x0100
decl_stmt|;
comment|/**      * One of the access flags for classes.      *      * @see #ACC_PUBLIC      */
specifier|public
specifier|static
specifier|final
name|short
name|ACC_INTERFACE
init|=
literal|0x0200
decl_stmt|;
comment|/**      * One of the access flags for methods or classes.      *      * @see #ACC_PUBLIC      */
specifier|public
specifier|static
specifier|final
name|short
name|ACC_ABSTRACT
init|=
literal|0x0400
decl_stmt|;
comment|/**      * One of the access flags for methods.      *      * @see #ACC_PUBLIC      */
specifier|public
specifier|static
specifier|final
name|short
name|ACC_STRICT
init|=
literal|0x0800
decl_stmt|;
comment|/**      * One of the access flags for fields, methods, classes, MethodParameter attribute, or Module attribute.      *      * @see #ACC_PUBLIC      */
specifier|public
specifier|static
specifier|final
name|short
name|ACC_SYNTHETIC
init|=
literal|0x1000
decl_stmt|;
comment|/**      * One of the access flags for classes.      *      * @see #ACC_PUBLIC      */
specifier|public
specifier|static
specifier|final
name|short
name|ACC_ANNOTATION
init|=
literal|0x2000
decl_stmt|;
comment|/**      * One of the access flags for fields or classes.      *      * @see #ACC_PUBLIC      */
specifier|public
specifier|static
specifier|final
name|short
name|ACC_ENUM
init|=
literal|0x4000
decl_stmt|;
comment|// Applies to classes compiled by new compilers only
comment|/**      * One of the access flags for MethodParameter or Module attributes.      *      * @see #ACC_PUBLIC      */
specifier|public
specifier|static
specifier|final
name|short
name|ACC_MANDATED
init|=
operator|(
name|short
operator|)
literal|0x8000
decl_stmt|;
comment|/**      * One of the access flags for classes.      *      * @see #ACC_PUBLIC      */
specifier|public
specifier|static
specifier|final
name|short
name|ACC_MODULE
init|=
operator|(
name|short
operator|)
literal|0x8000
decl_stmt|;
comment|/**      * One of the access flags for fields, methods, or classes.      *      * @see #ACC_PUBLIC      * @deprecated Use {@link #MAX_ACC_FLAG_I}      */
annotation|@
name|Deprecated
specifier|public
specifier|static
specifier|final
name|short
name|MAX_ACC_FLAG
init|=
name|ACC_ENUM
decl_stmt|;
comment|/**      * One of the access flags for fields, methods, or classes. ACC_MODULE is negative as a short.      *      * @see #ACC_PUBLIC      * @since 6.4.0      */
specifier|public
specifier|static
specifier|final
name|int
name|MAX_ACC_FLAG_I
init|=
literal|0x8000
decl_stmt|;
comment|// ACC_MODULE is negative as a short
comment|// Note that do to overloading:
comment|// 'synchronized' is for methods, might be 'open' (if Module), 'super' (if class), or 'transitive' (if Module).
comment|// 'volatile' is for fields, might be 'bridge' (if method) or 'static_phase' (if Module)
comment|// 'transient' is for fields, might be 'varargs' (if method)
comment|// 'module' is for classes, might be 'mandated' (if Module or MethodParameters)
comment|/**      * The names of the access flags.      */
specifier|private
specifier|static
specifier|final
name|String
index|[]
name|ACCESS_NAMES
init|=
block|{
literal|"public"
block|,
literal|"private"
block|,
literal|"protected"
block|,
literal|"static"
block|,
literal|"final"
block|,
literal|"synchronized"
block|,
literal|"volatile"
block|,
literal|"transient"
block|,
literal|"native"
block|,
literal|"interface"
block|,
literal|"abstract"
block|,
literal|"strictfp"
block|,
literal|"synthetic"
block|,
literal|"annotation"
block|,
literal|"enum"
block|,
literal|"module"
block|}
decl_stmt|;
comment|/** @since 6.0 */
specifier|public
specifier|static
specifier|final
name|int
name|ACCESS_NAMES_LENGTH
init|=
name|ACCESS_NAMES
operator|.
name|length
decl_stmt|;
comment|/**      * Marks a constant pool entry as type UTF-8.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.4.7"> The Constant Pool in The      *      Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|byte
name|CONSTANT_Utf8
init|=
literal|1
decl_stmt|;
comment|/*      * The description of the constant pool is at: https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.4      * References below are to the individual sections      */
comment|/**      * Marks a constant pool entry as type Integer.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.4.4"> The Constant Pool in The      *      Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|byte
name|CONSTANT_Integer
init|=
literal|3
decl_stmt|;
comment|/**      * Marks a constant pool entry as type Float.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.4.4"> The Constant Pool in The      *      Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|byte
name|CONSTANT_Float
init|=
literal|4
decl_stmt|;
comment|/**      * Marks a constant pool entry as type Long.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.4.5"> The Constant Pool in The      *      Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|byte
name|CONSTANT_Long
init|=
literal|5
decl_stmt|;
comment|/**      * Marks a constant pool entry as type Double.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.4.5"> The Constant Pool in The      *      Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|byte
name|CONSTANT_Double
init|=
literal|6
decl_stmt|;
comment|/**      * Marks a constant pool entry as a Class      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.4.1"> The Constant Pool in The      *      Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|byte
name|CONSTANT_Class
init|=
literal|7
decl_stmt|;
comment|/**      * Marks a constant pool entry as a Field Reference.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.4.2"> The Constant Pool in The      *      Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|byte
name|CONSTANT_Fieldref
init|=
literal|9
decl_stmt|;
comment|/**      * Marks a constant pool entry as type String      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.4.3"> The Constant Pool in The      *      Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|byte
name|CONSTANT_String
init|=
literal|8
decl_stmt|;
comment|/**      * Marks a constant pool entry as a Method Reference.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.4.2"> The Constant Pool in The      *      Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|byte
name|CONSTANT_Methodref
init|=
literal|10
decl_stmt|;
comment|/**      * Marks a constant pool entry as an Interface Method Reference.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.4.2"> The Constant Pool in The      *      Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|byte
name|CONSTANT_InterfaceMethodref
init|=
literal|11
decl_stmt|;
comment|/**      * Marks a constant pool entry as a name and type.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.4.6"> The Constant Pool in The      *      Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|byte
name|CONSTANT_NameAndType
init|=
literal|12
decl_stmt|;
comment|/**      * Marks a constant pool entry as a Method Handle.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.4.8"> The Constant Pool in The      *      Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|byte
name|CONSTANT_MethodHandle
init|=
literal|15
decl_stmt|;
comment|/**      * Marks a constant pool entry as a Method Type.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.4.9"> The Constant Pool in The      *      Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|byte
name|CONSTANT_MethodType
init|=
literal|16
decl_stmt|;
comment|/**      * Marks a constant pool entry as dynamically computed.      *      * @see<a href="https://bugs.openjdk.java.net/secure/attachment/74618/constant-dynamic.html"> Change request for JEP      *      309</a>      * @since 6.3      */
specifier|public
specifier|static
specifier|final
name|byte
name|CONSTANT_Dynamic
init|=
literal|17
decl_stmt|;
comment|/**      * Marks a constant pool entry as an Invoke Dynamic      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.4.10"> The Constant Pool in The      *      Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|byte
name|CONSTANT_InvokeDynamic
init|=
literal|18
decl_stmt|;
comment|/**      * Marks a constant pool entry as a Module Reference.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se9/html/jvms-4.html#jvms-4.4.11"> The Constant Pool in The      *      Java Virtual Machine Specification</a>      * @since 6.1      */
specifier|public
specifier|static
specifier|final
name|byte
name|CONSTANT_Module
init|=
literal|19
decl_stmt|;
comment|/**      * Marks a constant pool entry as a Package Reference.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se9/html/jvms-4.html#jvms-4.4.12"> The Constant Pool in The      *      Java Virtual Machine Specification</a>      * @since 6.1      */
specifier|public
specifier|static
specifier|final
name|byte
name|CONSTANT_Package
init|=
literal|20
decl_stmt|;
comment|/**      * The names of the types of entries in a constant pool. Use getConstantName instead      */
specifier|private
specifier|static
specifier|final
name|String
index|[]
name|CONSTANT_NAMES
init|=
block|{
literal|""
block|,
literal|"CONSTANT_Utf8"
block|,
literal|""
block|,
literal|"CONSTANT_Integer"
block|,
literal|"CONSTANT_Float"
block|,
literal|"CONSTANT_Long"
block|,
literal|"CONSTANT_Double"
block|,
literal|"CONSTANT_Class"
block|,
literal|"CONSTANT_String"
block|,
literal|"CONSTANT_Fieldref"
block|,
literal|"CONSTANT_Methodref"
block|,
literal|"CONSTANT_InterfaceMethodref"
block|,
literal|"CONSTANT_NameAndType"
block|,
literal|""
block|,
literal|""
block|,
literal|"CONSTANT_MethodHandle"
block|,
literal|"CONSTANT_MethodType"
block|,
literal|"CONSTANT_Dynamic"
block|,
literal|"CONSTANT_InvokeDynamic"
block|,
literal|"CONSTANT_Module"
block|,
literal|"CONSTANT_Package"
block|}
decl_stmt|;
comment|/**      * The name of the static initializer, also called&quot;class initialization method&quot; or&quot;interface      * initialization method&quot;. This is&quot;&lt;clinit&gt;&quot;.      */
specifier|public
specifier|static
specifier|final
name|String
name|STATIC_INITIALIZER_NAME
init|=
literal|"<clinit>"
decl_stmt|;
comment|/**      * The name of every constructor method in a class, also called&quot;instance initialization method&quot;. This is      *&quot;&lt;init&gt;&quot;.      */
specifier|public
specifier|static
specifier|final
name|String
name|CONSTRUCTOR_NAME
init|=
literal|"<init>"
decl_stmt|;
comment|/**      * The names of the interfaces implemented by arrays      */
specifier|private
specifier|static
specifier|final
name|String
index|[]
name|INTERFACES_IMPLEMENTED_BY_ARRAYS
init|=
block|{
literal|"java.lang.Cloneable"
block|,
literal|"java.io.Serializable"
block|}
decl_stmt|;
comment|/**      * Maximum Constant Pool entries. One of the limitations of the Java Virtual Machine.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.11-100-A"> The Java Virtual      *      Machine Specification, Java SE 8 Edition, page 330, chapter 4.11.</a>      */
specifier|public
specifier|static
specifier|final
name|int
name|MAX_CP_ENTRIES
init|=
literal|65535
decl_stmt|;
comment|/**      * Maximum code size (plus one; the code size must be LESS than this) One of the limitations of the Java Virtual      * Machine. Note vmspec2 page 152 ("Limitations") says: "The amount of code per non-native, non-abstract method is      * limited to 65536 bytes by the sizes of the indices in the exception_table of the Code attribute (Â§4.7.3), in the      * LineNumberTable attribute (Â§4.7.8), and in the LocalVariableTable attribute (Â§4.7.9)." However this should be taken      * as an upper limit rather than the defined maximum. On page 134 (4.8.1 Static Constants) of the same spec, it says:      * "The value of the code_length item must be less than 65536." The entry in the Limitations section has been removed      * from later versions of the spec; it is not present in the Java SE 8 edition.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.7.3-300-E"> The Java Virtual      *      Machine Specification, Java SE 8 Edition, page 104, chapter 4.7.</a>      */
specifier|public
specifier|static
specifier|final
name|int
name|MAX_CODE_SIZE
init|=
literal|65536
decl_stmt|;
comment|// bytes
comment|/**      * The maximum number of dimensions in an array ({@value}). One of the limitations of the Java Virtual Machine.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.3.2-150"> Field Descriptors in      *      The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|int
name|MAX_ARRAY_DIMENSIONS
init|=
literal|255
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.nop"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|NOP
init|=
literal|0
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.aconst_null"> Opcode      *      definitions in The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|ACONST_NULL
init|=
literal|1
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.iconst_i"> Opcode definitions      *      in The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|ICONST_M1
init|=
literal|2
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.iconst_i"> Opcode definitions      *      in The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|ICONST_0
init|=
literal|3
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.iconst_i"> Opcode definitions      *      in The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|ICONST_1
init|=
literal|4
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.iconst_i"> Opcode definitions      *      in The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|ICONST_2
init|=
literal|5
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.iconst_i"> Opcode definitions      *      in The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|ICONST_3
init|=
literal|6
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.iconst_i"> Opcode definitions      *      in The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|ICONST_4
init|=
literal|7
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.iconst_i"> Opcode definitions      *      in The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|ICONST_5
init|=
literal|8
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.lconst_l"> Opcode definitions      *      in The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|LCONST_0
init|=
literal|9
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.lconst_l"> Opcode definitions      *      in The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|LCONST_1
init|=
literal|10
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.fconst_f"> Opcode definitions      *      in The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|FCONST_0
init|=
literal|11
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.fconst_f"> Opcode definitions      *      in The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|FCONST_1
init|=
literal|12
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.fconst_f"> Opcode definitions      *      in The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|FCONST_2
init|=
literal|13
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.dconst_d"> Opcode definitions      *      in The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|DCONST_0
init|=
literal|14
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.dconst_d"> Opcode definitions      *      in The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|DCONST_1
init|=
literal|15
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.bipush"> Opcode definitions in      *      The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|BIPUSH
init|=
literal|16
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.sipush"> Opcode definitions in      *      The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|SIPUSH
init|=
literal|17
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.ldc"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|LDC
init|=
literal|18
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.ldc_w"> Opcode definitions in      *      The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|LDC_W
init|=
literal|19
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.ldc2_w"> Opcode definitions in      *      The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|LDC2_W
init|=
literal|20
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.iload"> Opcode definitions in      *      The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|ILOAD
init|=
literal|21
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.lload"> Opcode definitions in      *      The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|LLOAD
init|=
literal|22
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.fload"> Opcode definitions in      *      The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|FLOAD
init|=
literal|23
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.dload"> Opcode definitions in      *      The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|DLOAD
init|=
literal|24
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.aload"> Opcode definitions in      *      The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|ALOAD
init|=
literal|25
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.iload_n"> Opcode definitions in      *      The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|ILOAD_0
init|=
literal|26
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.iload_n"> Opcode definitions in      *      The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|ILOAD_1
init|=
literal|27
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.iload_n"> Opcode definitions in      *      The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|ILOAD_2
init|=
literal|28
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.iload_n"> Opcode definitions in      *      The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|ILOAD_3
init|=
literal|29
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.lload_n"> Opcode definitions in      *      The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|LLOAD_0
init|=
literal|30
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.lload_n"> Opcode definitions in      *      The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|LLOAD_1
init|=
literal|31
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.lload_n"> Opcode definitions in      *      The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|LLOAD_2
init|=
literal|32
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.lload_n"> Opcode definitions in      *      The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|LLOAD_3
init|=
literal|33
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.fload_n"> Opcode definitions in      *      The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|FLOAD_0
init|=
literal|34
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.fload_n"> Opcode definitions in      *      The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|FLOAD_1
init|=
literal|35
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.fload_n"> Opcode definitions in      *      The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|FLOAD_2
init|=
literal|36
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.fload_n"> Opcode definitions in      *      The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|FLOAD_3
init|=
literal|37
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.dload_n"> Opcode definitions in      *      The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|DLOAD_0
init|=
literal|38
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.dload_n"> Opcode definitions in      *      The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|DLOAD_1
init|=
literal|39
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.dload_n"> Opcode definitions in      *      The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|DLOAD_2
init|=
literal|40
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.dload_n"> Opcode definitions in      *      The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|DLOAD_3
init|=
literal|41
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.aload_n"> Opcode definitions in      *      The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|ALOAD_0
init|=
literal|42
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.aload_n"> Opcode definitions in      *      The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|ALOAD_1
init|=
literal|43
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.aload_n"> Opcode definitions in      *      The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|ALOAD_2
init|=
literal|44
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.aload_n"> Opcode definitions in      *      The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|ALOAD_3
init|=
literal|45
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.iaload"> Opcode definitions in      *      The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|IALOAD
init|=
literal|46
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.laload"> Opcode definitions in      *      The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|LALOAD
init|=
literal|47
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.faload"> Opcode definitions in      *      The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|FALOAD
init|=
literal|48
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.daload"> Opcode definitions in      *      The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|DALOAD
init|=
literal|49
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.aaload"> Opcode definitions in      *      The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|AALOAD
init|=
literal|50
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.baload"> Opcode definitions in      *      The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|BALOAD
init|=
literal|51
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.caload"> Opcode definitions in      *      The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|CALOAD
init|=
literal|52
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.saload"> Opcode definitions in      *      The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|SALOAD
init|=
literal|53
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.istore"> Opcode definitions in      *      The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|ISTORE
init|=
literal|54
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.lstore"> Opcode definitions in      *      The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|LSTORE
init|=
literal|55
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.fstore"> Opcode definitions in      *      The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|FSTORE
init|=
literal|56
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.dstore"> Opcode definitions in      *      The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|DSTORE
init|=
literal|57
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.astore"> Opcode definitions in      *      The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|ASTORE
init|=
literal|58
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.istore_n"> Opcode definitions      *      in The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|ISTORE_0
init|=
literal|59
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.istore_n"> Opcode definitions      *      in The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|ISTORE_1
init|=
literal|60
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.istore_n"> Opcode definitions      *      in The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|ISTORE_2
init|=
literal|61
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.istore_n"> Opcode definitions      *      in The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|ISTORE_3
init|=
literal|62
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.lstore_n"> Opcode definitions      *      in The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|LSTORE_0
init|=
literal|63
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.lstore_n"> Opcode definitions      *      in The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|LSTORE_1
init|=
literal|64
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.lstore_n"> Opcode definitions      *      in The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|LSTORE_2
init|=
literal|65
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.lstore_n"> Opcode definitions      *      in The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|LSTORE_3
init|=
literal|66
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.fstore_n"> Opcode definitions      *      in The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|FSTORE_0
init|=
literal|67
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.fstore_n"> Opcode definitions      *      in The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|FSTORE_1
init|=
literal|68
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.fstore_n"> Opcode definitions      *      in The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|FSTORE_2
init|=
literal|69
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.fstore_n"> Opcode definitions      *      in The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|FSTORE_3
init|=
literal|70
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.dstore_n"> Opcode definitions      *      in The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|DSTORE_0
init|=
literal|71
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.dstore_n"> Opcode definitions      *      in The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|DSTORE_1
init|=
literal|72
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.dstore_n"> Opcode definitions      *      in The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|DSTORE_2
init|=
literal|73
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.dstore_n"> Opcode definitions      *      in The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|DSTORE_3
init|=
literal|74
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.astore_n"> Opcode definitions      *      in The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|ASTORE_0
init|=
literal|75
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.astore_n"> Opcode definitions      *      in The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|ASTORE_1
init|=
literal|76
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.astore_n"> Opcode definitions      *      in The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|ASTORE_2
init|=
literal|77
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.astore_n"> Opcode definitions      *      in The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|ASTORE_3
init|=
literal|78
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.iastore"> Opcode definitions in      *      The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|IASTORE
init|=
literal|79
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.lastore"> Opcode definitions in      *      The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|LASTORE
init|=
literal|80
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.fastore"> Opcode definitions in      *      The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|FASTORE
init|=
literal|81
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.dastore"> Opcode definitions in      *      The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|DASTORE
init|=
literal|82
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.aastore"> Opcode definitions in      *      The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|AASTORE
init|=
literal|83
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.bastore"> Opcode definitions in      *      The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|BASTORE
init|=
literal|84
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.castore"> Opcode definitions in      *      The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|CASTORE
init|=
literal|85
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.sastore"> Opcode definitions in      *      The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|SASTORE
init|=
literal|86
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.pop"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|POP
init|=
literal|87
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.pop2"> Opcode definitions in      *      The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|POP2
init|=
literal|88
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.dup"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|DUP
init|=
literal|89
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.dup_x1"> Opcode definitions in      *      The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|DUP_X1
init|=
literal|90
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.dup_x2"> Opcode definitions in      *      The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|DUP_X2
init|=
literal|91
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.dup2"> Opcode definitions in      *      The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|DUP2
init|=
literal|92
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.dup2_x1"> Opcode definitions in      *      The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|DUP2_X1
init|=
literal|93
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.dup2_x2"> Opcode definitions in      *      The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|DUP2_X2
init|=
literal|94
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.swap"> Opcode definitions in      *      The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|SWAP
init|=
literal|95
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.iadd"> Opcode definitions in      *      The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|IADD
init|=
literal|96
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.ladd"> Opcode definitions in      *      The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|LADD
init|=
literal|97
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.fadd"> Opcode definitions in      *      The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|FADD
init|=
literal|98
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.dadd"> Opcode definitions in      *      The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|DADD
init|=
literal|99
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.isub"> Opcode definitions in      *      The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|ISUB
init|=
literal|100
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.lsub"> Opcode definitions in      *      The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|LSUB
init|=
literal|101
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.fsub"> Opcode definitions in      *      The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|FSUB
init|=
literal|102
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.dsub"> Opcode definitions in      *      The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|DSUB
init|=
literal|103
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.imul"> Opcode definitions in      *      The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|IMUL
init|=
literal|104
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.lmul"> Opcode definitions in      *      The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|LMUL
init|=
literal|105
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.fmul"> Opcode definitions in      *      The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|FMUL
init|=
literal|106
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.dmul"> Opcode definitions in      *      The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|DMUL
init|=
literal|107
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.idiv"> Opcode definitions in      *      The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|IDIV
init|=
literal|108
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.ldiv"> Opcode definitions in      *      The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|LDIV
init|=
literal|109
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.fdiv"> Opcode definitions in      *      The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|FDIV
init|=
literal|110
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.ddiv"> Opcode definitions in      *      The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|DDIV
init|=
literal|111
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.irem"> Opcode definitions in      *      The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|IREM
init|=
literal|112
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.lrem"> Opcode definitions in      *      The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|LREM
init|=
literal|113
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.frem"> Opcode definitions in      *      The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|FREM
init|=
literal|114
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.drem"> Opcode definitions in      *      The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|DREM
init|=
literal|115
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.ineg"> Opcode definitions in      *      The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|INEG
init|=
literal|116
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.lneg"> Opcode definitions in      *      The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|LNEG
init|=
literal|117
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.fneg"> Opcode definitions in      *      The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|FNEG
init|=
literal|118
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.dneg"> Opcode definitions in      *      The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|DNEG
init|=
literal|119
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.ishl"> Opcode definitions in      *      The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|ISHL
init|=
literal|120
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.lshl"> Opcode definitions in      *      The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|LSHL
init|=
literal|121
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.ishr"> Opcode definitions in      *      The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|ISHR
init|=
literal|122
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.lshr"> Opcode definitions in      *      The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|LSHR
init|=
literal|123
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.iushr"> Opcode definitions in      *      The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|IUSHR
init|=
literal|124
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.lushr"> Opcode definitions in      *      The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|LUSHR
init|=
literal|125
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.iand"> Opcode definitions in      *      The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|IAND
init|=
literal|126
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.land"> Opcode definitions in      *      The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|LAND
init|=
literal|127
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.ior"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|IOR
init|=
literal|128
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.lor"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|LOR
init|=
literal|129
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.ixor"> Opcode definitions in      *      The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|IXOR
init|=
literal|130
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.lxor"> Opcode definitions in      *      The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|LXOR
init|=
literal|131
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.iinc"> Opcode definitions in      *      The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|IINC
init|=
literal|132
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.i2l"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|I2L
init|=
literal|133
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.i2f"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|I2F
init|=
literal|134
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.i2d"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|I2D
init|=
literal|135
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.l2i"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|L2I
init|=
literal|136
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.l2f"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|L2F
init|=
literal|137
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.l2d"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|L2D
init|=
literal|138
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.f2i"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|F2I
init|=
literal|139
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.f2l"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|F2L
init|=
literal|140
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.f2d"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|F2D
init|=
literal|141
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.d2i"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|D2I
init|=
literal|142
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.d2l"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|D2L
init|=
literal|143
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.d2f"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|D2F
init|=
literal|144
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.i2b"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|I2B
init|=
literal|145
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|INT2BYTE
init|=
literal|145
decl_stmt|;
comment|// Old notation
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.i2c"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|I2C
init|=
literal|146
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|INT2CHAR
init|=
literal|146
decl_stmt|;
comment|// Old notation
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.i2s"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|I2S
init|=
literal|147
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|INT2SHORT
init|=
literal|147
decl_stmt|;
comment|// Old notation
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.lcmp"> Opcode definitions in      *      The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|LCMP
init|=
literal|148
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.fcmpl"> Opcode definitions in      *      The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|FCMPL
init|=
literal|149
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.fcmpg"> Opcode definitions in      *      The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|FCMPG
init|=
literal|150
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.dcmpl"> Opcode definitions in      *      The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|DCMPL
init|=
literal|151
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.dcmpg"> Opcode definitions in      *      The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|DCMPG
init|=
literal|152
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.ifeq"> Opcode definitions in      *      The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|IFEQ
init|=
literal|153
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.ifne"> Opcode definitions in      *      The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|IFNE
init|=
literal|154
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.iflt"> Opcode definitions in      *      The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|IFLT
init|=
literal|155
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.ifge"> Opcode definitions in      *      The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|IFGE
init|=
literal|156
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.ifgt"> Opcode definitions in      *      The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|IFGT
init|=
literal|157
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.ifle"> Opcode definitions in      *      The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|IFLE
init|=
literal|158
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.if_icmp_cond"> Opcode      *      definitions in The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|IF_ICMPEQ
init|=
literal|159
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.if_icmp_cond"> Opcode      *      definitions in The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|IF_ICMPNE
init|=
literal|160
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.if_icmp_cond"> Opcode      *      definitions in The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|IF_ICMPLT
init|=
literal|161
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.if_icmp_cond"> Opcode      *      definitions in The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|IF_ICMPGE
init|=
literal|162
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.if_icmp_cond"> Opcode      *      definitions in The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|IF_ICMPGT
init|=
literal|163
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.if_icmp_cond"> Opcode      *      definitions in The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|IF_ICMPLE
init|=
literal|164
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.if_acmp_cond"> Opcode      *      definitions in The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|IF_ACMPEQ
init|=
literal|165
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.if_acmp_cond"> Opcode      *      definitions in The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|IF_ACMPNE
init|=
literal|166
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.goto"> Opcode definitions in      *      The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|GOTO
init|=
literal|167
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.jsr"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|JSR
init|=
literal|168
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.ret"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|RET
init|=
literal|169
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.tableswitch"> Opcode      *      definitions in The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|TABLESWITCH
init|=
literal|170
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.lookupswitch"> Opcode      *      definitions in The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|LOOKUPSWITCH
init|=
literal|171
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.ireturn"> Opcode definitions in      *      The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|IRETURN
init|=
literal|172
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.lreturn"> Opcode definitions in      *      The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|LRETURN
init|=
literal|173
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.freturn"> Opcode definitions in      *      The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|FRETURN
init|=
literal|174
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.dreturn"> Opcode definitions in      *      The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|DRETURN
init|=
literal|175
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.areturn"> Opcode definitions in      *      The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|ARETURN
init|=
literal|176
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.return"> Opcode definitions in      *      The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|RETURN
init|=
literal|177
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.getstatic"> Opcode definitions      *      in The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|GETSTATIC
init|=
literal|178
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.putstatic"> Opcode definitions      *      in The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|PUTSTATIC
init|=
literal|179
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.getfield"> Opcode definitions      *      in The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|GETFIELD
init|=
literal|180
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.putfield"> Opcode definitions      *      in The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|PUTFIELD
init|=
literal|181
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.invokevirtual"> Opcode      *      definitions in The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|INVOKEVIRTUAL
init|=
literal|182
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.invokespecial"> Opcode      *      definitions in The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|INVOKESPECIAL
init|=
literal|183
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|INVOKENONVIRTUAL
init|=
literal|183
decl_stmt|;
comment|// Old name in JDK 1.0
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.invokestatic"> Opcode      *      definitions in The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|INVOKESTATIC
init|=
literal|184
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.invokeinterface"> Opcode      *      definitions in The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|INVOKEINTERFACE
init|=
literal|185
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.invokedynamic"> Opcode      *      definitions in The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|INVOKEDYNAMIC
init|=
literal|186
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.new"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|NEW
init|=
literal|187
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.newarray"> Opcode definitions      *      in The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|NEWARRAY
init|=
literal|188
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.anewarray"> Opcode definitions      *      in The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|ANEWARRAY
init|=
literal|189
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.arraylength"> Opcode      *      definitions in The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|ARRAYLENGTH
init|=
literal|190
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.athrow"> Opcode definitions in      *      The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|ATHROW
init|=
literal|191
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.checkcast"> Opcode definitions      *      in The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|CHECKCAST
init|=
literal|192
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.instanceof"> Opcode definitions      *      in The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|INSTANCEOF
init|=
literal|193
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.monitorenter"> Opcode      *      definitions in The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|MONITORENTER
init|=
literal|194
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.monitorexit"> Opcode      *      definitions in The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|MONITOREXIT
init|=
literal|195
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.wide"> Opcode definitions in      *      The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|WIDE
init|=
literal|196
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.multianewarray"> Opcode      *      definitions in The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|MULTIANEWARRAY
init|=
literal|197
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.ifnull"> Opcode definitions in      *      The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|IFNULL
init|=
literal|198
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.ifnonnull"> Opcode definitions      *      in The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|IFNONNULL
init|=
literal|199
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.goto_w"> Opcode definitions in      *      The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|GOTO_W
init|=
literal|200
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.jsr_w"> Opcode definitions in      *      The Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|JSR_W
init|=
literal|201
decl_stmt|;
comment|/**      * JVM internal opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.2"> Reserved opcodes in the Java      *      Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|BREAKPOINT
init|=
literal|202
decl_stmt|;
comment|/**      * JVM internal opcode.      *      * @see<a href=      *      "https://web.archive.org/web/20120108031230/http://java.sun.com/docs/books/jvms/first_edition/html/Quick.doc.html">      *      Specification of _quick opcodes in the Java Virtual Machine Specification (version 1)</a>      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se5.0/html/ChangesAppendix.doc.html#448885"> Why the _quick      *      opcodes were removed from the second version of the Java Virtual Machine Specification.</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|LDC_QUICK
init|=
literal|203
decl_stmt|;
comment|/**      * JVM internal opcode.      *      * @see<a href=      *      "https://web.archive.org/web/20120108031230/http://java.sun.com/docs/books/jvms/first_edition/html/Quick.doc.html">      *      Specification of _quick opcodes in the Java Virtual Machine Specification (version 1)</a>      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se5.0/html/ChangesAppendix.doc.html#448885"> Why the _quick      *      opcodes were removed from the second version of the Java Virtual Machine Specification.</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|LDC_W_QUICK
init|=
literal|204
decl_stmt|;
comment|/**      * JVM internal opcode.      *      * @see<a href=      *      "https://web.archive.org/web/20120108031230/http://java.sun.com/docs/books/jvms/first_edition/html/Quick.doc.html">      *      Specification of _quick opcodes in the Java Virtual Machine Specification (version 1)</a>      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se5.0/html/ChangesAppendix.doc.html#448885"> Why the _quick      *      opcodes were removed from the second version of the Java Virtual Machine Specification.</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|LDC2_W_QUICK
init|=
literal|205
decl_stmt|;
comment|/**      * JVM internal opcode.      *      * @see<a href=      *      "https://web.archive.org/web/20120108031230/http://java.sun.com/docs/books/jvms/first_edition/html/Quick.doc.html">      *      Specification of _quick opcodes in the Java Virtual Machine Specification (version 1)</a>      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se5.0/html/ChangesAppendix.doc.html#448885"> Why the _quick      *      opcodes were removed from the second version of the Java Virtual Machine Specification.</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|GETFIELD_QUICK
init|=
literal|206
decl_stmt|;
comment|/**      * JVM internal opcode.      *      * @see<a href=      *      "https://web.archive.org/web/20120108031230/http://java.sun.com/docs/books/jvms/first_edition/html/Quick.doc.html">      *      Specification of _quick opcodes in the Java Virtual Machine Specification (version 1)</a>      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se5.0/html/ChangesAppendix.doc.html#448885"> Why the _quick      *      opcodes were removed from the second version of the Java Virtual Machine Specification.</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|PUTFIELD_QUICK
init|=
literal|207
decl_stmt|;
comment|/**      * JVM internal opcode.      *      * @see<a href=      *      "https://web.archive.org/web/20120108031230/http://java.sun.com/docs/books/jvms/first_edition/html/Quick.doc.html">      *      Specification of _quick opcodes in the Java Virtual Machine Specification (version 1)</a>      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se5.0/html/ChangesAppendix.doc.html#448885"> Why the _quick      *      opcodes were removed from the second version of the Java Virtual Machine Specification.</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|GETFIELD2_QUICK
init|=
literal|208
decl_stmt|;
comment|/**      * JVM internal opcode.      *      * @see<a href=      *      "https://web.archive.org/web/20120108031230/http://java.sun.com/docs/books/jvms/first_edition/html/Quick.doc.html">      *      Specification of _quick opcodes in the Java Virtual Machine Specification (version 1)</a>      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se5.0/html/ChangesAppendix.doc.html#448885"> Why the _quick      *      opcodes were removed from the second version of the Java Virtual Machine Specification.</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|PUTFIELD2_QUICK
init|=
literal|209
decl_stmt|;
comment|/**      * JVM internal opcode.      *      * @see<a href=      *      "https://web.archive.org/web/20120108031230/http://java.sun.com/docs/books/jvms/first_edition/html/Quick.doc.html">      *      Specification of _quick opcodes in the Java Virtual Machine Specification (version 1)</a>      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se5.0/html/ChangesAppendix.doc.html#448885"> Why the _quick      *      opcodes were removed from the second version of the Java Virtual Machine Specification.</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|GETSTATIC_QUICK
init|=
literal|210
decl_stmt|;
comment|/**      * JVM internal opcode.      *      * @see<a href=      *      "https://web.archive.org/web/20120108031230/http://java.sun.com/docs/books/jvms/first_edition/html/Quick.doc.html">      *      Specification of _quick opcodes in the Java Virtual Machine Specification (version 1)</a>      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se5.0/html/ChangesAppendix.doc.html#448885"> Why the _quick      *      opcodes were removed from the second version of the Java Virtual Machine Specification.</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|PUTSTATIC_QUICK
init|=
literal|211
decl_stmt|;
comment|/**      * JVM internal opcode.      *      * @see<a href=      *      "https://web.archive.org/web/20120108031230/http://java.sun.com/docs/books/jvms/first_edition/html/Quick.doc.html">      *      Specification of _quick opcodes in the Java Virtual Machine Specification (version 1)</a>      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se5.0/html/ChangesAppendix.doc.html#448885"> Why the _quick      *      opcodes were removed from the second version of the Java Virtual Machine Specification.</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|GETSTATIC2_QUICK
init|=
literal|212
decl_stmt|;
comment|/**      * JVM internal opcode.      *      * @see<a href=      *      "https://web.archive.org/web/20120108031230/http://java.sun.com/docs/books/jvms/first_edition/html/Quick.doc.html">      *      Specification of _quick opcodes in the Java Virtual Machine Specification (version 1)</a>      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se5.0/html/ChangesAppendix.doc.html#448885"> Why the _quick      *      opcodes were removed from the second version of the Java Virtual Machine Specification.</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|PUTSTATIC2_QUICK
init|=
literal|213
decl_stmt|;
comment|/**      * JVM internal opcode.      *      * @see<a href=      *      "https://web.archive.org/web/20120108031230/http://java.sun.com/docs/books/jvms/first_edition/html/Quick.doc.html">      *      Specification of _quick opcodes in the Java Virtual Machine Specification (version 1)</a>      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se5.0/html/ChangesAppendix.doc.html#448885"> Why the _quick      *      opcodes were removed from the second version of the Java Virtual Machine Specification.</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|INVOKEVIRTUAL_QUICK
init|=
literal|214
decl_stmt|;
comment|/**      * JVM internal opcode.      *      * @see<a href=      *      "https://web.archive.org/web/20120108031230/http://java.sun.com/docs/books/jvms/first_edition/html/Quick.doc.html">      *      Specification of _quick opcodes in the Java Virtual Machine Specification (version 1)</a>      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se5.0/html/ChangesAppendix.doc.html#448885"> Why the _quick      *      opcodes were removed from the second version of the Java Virtual Machine Specification.</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|INVOKENONVIRTUAL_QUICK
init|=
literal|215
decl_stmt|;
comment|/**      * JVM internal opcode.      *      * @see<a href=      *      "https://web.archive.org/web/20120108031230/http://java.sun.com/docs/books/jvms/first_edition/html/Quick.doc.html">      *      Specification of _quick opcodes in the Java Virtual Machine Specification (version 1)</a>      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se5.0/html/ChangesAppendix.doc.html#448885"> Why the _quick      *      opcodes were removed from the second version of the Java Virtual Machine Specification.</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|INVOKESUPER_QUICK
init|=
literal|216
decl_stmt|;
comment|/**      * JVM internal opcode.      *      * @see<a href=      *      "https://web.archive.org/web/20120108031230/http://java.sun.com/docs/books/jvms/first_edition/html/Quick.doc.html">      *      Specification of _quick opcodes in the Java Virtual Machine Specification (version 1)</a>      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se5.0/html/ChangesAppendix.doc.html#448885"> Why the _quick      *      opcodes were removed from the second version of the Java Virtual Machine Specification.</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|INVOKESTATIC_QUICK
init|=
literal|217
decl_stmt|;
comment|/**      * JVM internal opcode.      *      * @see<a href=      *      "https://web.archive.org/web/20120108031230/http://java.sun.com/docs/books/jvms/first_edition/html/Quick.doc.html">      *      Specification of _quick opcodes in the Java Virtual Machine Specification (version 1)</a>      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se5.0/html/ChangesAppendix.doc.html#448885"> Why the _quick      *      opcodes were removed from the second version of the Java Virtual Machine Specification.</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|INVOKEINTERFACE_QUICK
init|=
literal|218
decl_stmt|;
comment|/**      * JVM internal opcode.      *      * @see<a href=      *      "https://web.archive.org/web/20120108031230/http://java.sun.com/docs/books/jvms/first_edition/html/Quick.doc.html">      *      Specification of _quick opcodes in the Java Virtual Machine Specification (version 1)</a>      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se5.0/html/ChangesAppendix.doc.html#448885"> Why the _quick      *      opcodes were removed from the second version of the Java Virtual Machine Specification.</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|INVOKEVIRTUALOBJECT_QUICK
init|=
literal|219
decl_stmt|;
comment|/**      * JVM internal opcode.      *      * @see<a href=      *      "https://web.archive.org/web/20120108031230/http://java.sun.com/docs/books/jvms/first_edition/html/Quick.doc.html">      *      Specification of _quick opcodes in the Java Virtual Machine Specification (version 1)</a>      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se5.0/html/ChangesAppendix.doc.html#448885"> Why the _quick      *      opcodes were removed from the second version of the Java Virtual Machine Specification.</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|NEW_QUICK
init|=
literal|221
decl_stmt|;
comment|/**      * JVM internal opcode.      *      * @see<a href=      *      "https://web.archive.org/web/20120108031230/http://java.sun.com/docs/books/jvms/first_edition/html/Quick.doc.html">      *      Specification of _quick opcodes in the Java Virtual Machine Specification (version 1)</a>      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se5.0/html/ChangesAppendix.doc.html#448885"> Why the _quick      *      opcodes were removed from the second version of the Java Virtual Machine Specification.</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|ANEWARRAY_QUICK
init|=
literal|222
decl_stmt|;
comment|/**      * JVM internal opcode.      *      * @see<a href=      *      "https://web.archive.org/web/20120108031230/http://java.sun.com/docs/books/jvms/first_edition/html/Quick.doc.html">      *      Specification of _quick opcodes in the Java Virtual Machine Specification (version 1)</a>      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se5.0/html/ChangesAppendix.doc.html#448885"> Why the _quick      *      opcodes were removed from the second version of the Java Virtual Machine Specification.</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|MULTIANEWARRAY_QUICK
init|=
literal|223
decl_stmt|;
comment|/**      * JVM internal opcode.      *      * @see<a href=      *      "https://web.archive.org/web/20120108031230/http://java.sun.com/docs/books/jvms/first_edition/html/Quick.doc.html">      *      Specification of _quick opcodes in the Java Virtual Machine Specification (version 1)</a>      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se5.0/html/ChangesAppendix.doc.html#448885"> Why the _quick      *      opcodes were removed from the second version of the Java Virtual Machine Specification.</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|CHECKCAST_QUICK
init|=
literal|224
decl_stmt|;
comment|/**      * JVM internal opcode.      *      * @see<a href=      *      "https://web.archive.org/web/20120108031230/http://java.sun.com/docs/books/jvms/first_edition/html/Quick.doc.html">      *      Specification of _quick opcodes in the Java Virtual Machine Specification (version 1)</a>      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se5.0/html/ChangesAppendix.doc.html#448885"> Why the _quick      *      opcodes were removed from the second version of the Java Virtual Machine Specification.</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|INSTANCEOF_QUICK
init|=
literal|225
decl_stmt|;
comment|/**      * JVM internal opcode.      *      * @see<a href=      *      "https://web.archive.org/web/20120108031230/http://java.sun.com/docs/books/jvms/first_edition/html/Quick.doc.html">      *      Specification of _quick opcodes in the Java Virtual Machine Specification (version 1)</a>      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se5.0/html/ChangesAppendix.doc.html#448885"> Why the _quick      *      opcodes were removed from the second version of the Java Virtual Machine Specification.</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|INVOKEVIRTUAL_QUICK_W
init|=
literal|226
decl_stmt|;
comment|/**      * JVM internal opcode.      *      * @see<a href=      *      "https://web.archive.org/web/20120108031230/http://java.sun.com/docs/books/jvms/first_edition/html/Quick.doc.html">      *      Specification of _quick opcodes in the Java Virtual Machine Specification (version 1)</a>      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se5.0/html/ChangesAppendix.doc.html#448885"> Why the _quick      *      opcodes were removed from the second version of the Java Virtual Machine Specification.</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|GETFIELD_QUICK_W
init|=
literal|227
decl_stmt|;
comment|/**      * JVM internal opcode.      *      * @see<a href=      *      "https://web.archive.org/web/20120108031230/http://java.sun.com/docs/books/jvms/first_edition/html/Quick.doc.html">      *      Specification of _quick opcodes in the Java Virtual Machine Specification (version 1)</a>      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se5.0/html/ChangesAppendix.doc.html#448885"> Why the _quick      *      opcodes were removed from the second version of the Java Virtual Machine Specification.</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|PUTFIELD_QUICK_W
init|=
literal|228
decl_stmt|;
comment|/**      * JVM internal opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.2"> Reserved opcodes in the Java      *      Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|IMPDEP1
init|=
literal|254
decl_stmt|;
comment|/**      * JVM internal opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.2"> Reserved opcodes in the Java      *      Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|short
name|IMPDEP2
init|=
literal|255
decl_stmt|;
comment|/**      * BCEL virtual instruction for pushing an arbitrary data type onto the stack. Will be converted to the appropriate JVM      * opcode when the class is dumped.      */
specifier|public
specifier|static
specifier|final
name|short
name|PUSH
init|=
literal|4711
decl_stmt|;
comment|/**      * BCEL virtual instruction for either LOOKUPSWITCH or TABLESWITCH. Will be converted to the appropriate JVM opcode when      * the class is dumped.      */
specifier|public
specifier|static
specifier|final
name|short
name|SWITCH
init|=
literal|4712
decl_stmt|;
comment|/** Illegal opcode. */
specifier|public
specifier|static
specifier|final
name|short
name|UNDEFINED
init|=
operator|-
literal|1
decl_stmt|;
comment|/** Illegal opcode. */
specifier|public
specifier|static
specifier|final
name|short
name|UNPREDICTABLE
init|=
operator|-
literal|2
decl_stmt|;
comment|/** Illegal opcode. */
specifier|public
specifier|static
specifier|final
name|short
name|RESERVED
init|=
operator|-
literal|3
decl_stmt|;
comment|/** Mnemonic for an illegal opcode. */
specifier|public
specifier|static
specifier|final
name|String
name|ILLEGAL_OPCODE
init|=
literal|"<illegal opcode>"
decl_stmt|;
comment|/** Mnemonic for an illegal type. */
specifier|public
specifier|static
specifier|final
name|String
name|ILLEGAL_TYPE
init|=
literal|"<illegal type>"
decl_stmt|;
comment|/**      * Boolean data type.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.9.1-120-P"> Static Constraints in      *      the Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|byte
name|T_BOOLEAN
init|=
literal|4
decl_stmt|;
comment|/**      * Char data type.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.9.1-120-P"> Static Constraints in      *      the Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|byte
name|T_CHAR
init|=
literal|5
decl_stmt|;
comment|/**      * Float data type.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.9.1-120-P"> Static Constraints in      *      the Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|byte
name|T_FLOAT
init|=
literal|6
decl_stmt|;
comment|/**      * Double data type.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.9.1-120-P"> Static Constraints in      *      the Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|byte
name|T_DOUBLE
init|=
literal|7
decl_stmt|;
comment|/**      * Byte data type.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.9.1-120-P"> Static Constraints in      *      the Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|byte
name|T_BYTE
init|=
literal|8
decl_stmt|;
comment|/**      * Short data type.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.9.1-120-P"> Static Constraints in      *      the Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|byte
name|T_SHORT
init|=
literal|9
decl_stmt|;
comment|/**      * Int data type.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.9.1-120-P"> Static Constraints in      *      the Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|byte
name|T_INT
init|=
literal|10
decl_stmt|;
comment|/**      * Long data type.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.9.1-120-P"> Static Constraints in      *      the Java Virtual Machine Specification</a>      */
specifier|public
specifier|static
specifier|final
name|byte
name|T_LONG
init|=
literal|11
decl_stmt|;
comment|/** Void data type (non-standard). */
specifier|public
specifier|static
specifier|final
name|byte
name|T_VOID
init|=
literal|12
decl_stmt|;
comment|// Non-standard
comment|/** Array data type. */
specifier|public
specifier|static
specifier|final
name|byte
name|T_ARRAY
init|=
literal|13
decl_stmt|;
comment|/** Object data type. */
specifier|public
specifier|static
specifier|final
name|byte
name|T_OBJECT
init|=
literal|14
decl_stmt|;
comment|/** Reference data type (deprecated). */
specifier|public
specifier|static
specifier|final
name|byte
name|T_REFERENCE
init|=
literal|14
decl_stmt|;
comment|// Deprecated
comment|/** Unknown data type. */
specifier|public
specifier|static
specifier|final
name|byte
name|T_UNKNOWN
init|=
literal|15
decl_stmt|;
comment|/** Address data type. */
specifier|public
specifier|static
specifier|final
name|byte
name|T_ADDRESS
init|=
literal|16
decl_stmt|;
comment|/**      * The primitive type names corresponding to the T_XX constants, e.g., TYPE_NAMES[T_INT] = "int"      */
specifier|private
specifier|static
specifier|final
name|String
index|[]
name|TYPE_NAMES
init|=
block|{
name|ILLEGAL_TYPE
block|,
name|ILLEGAL_TYPE
block|,
name|ILLEGAL_TYPE
block|,
name|ILLEGAL_TYPE
block|,
literal|"boolean"
block|,
literal|"char"
block|,
literal|"float"
block|,
literal|"double"
block|,
literal|"byte"
block|,
literal|"short"
block|,
literal|"int"
block|,
literal|"long"
block|,
literal|"void"
block|,
literal|"array"
block|,
literal|"object"
block|,
literal|"unknown"
block|,
literal|"address"
block|}
decl_stmt|;
comment|/**      * The primitive class names corresponding to the T_XX constants, e.g., CLASS_TYPE_NAMES[T_INT] = "java.lang.Integer"      */
specifier|private
specifier|static
specifier|final
name|String
index|[]
name|CLASS_TYPE_NAMES
init|=
block|{
name|ILLEGAL_TYPE
block|,
name|ILLEGAL_TYPE
block|,
name|ILLEGAL_TYPE
block|,
name|ILLEGAL_TYPE
block|,
literal|"java.lang.Boolean"
block|,
literal|"java.lang.Character"
block|,
literal|"java.lang.Float"
block|,
literal|"java.lang.Double"
block|,
literal|"java.lang.Byte"
block|,
literal|"java.lang.Short"
block|,
literal|"java.lang.Integer"
block|,
literal|"java.lang.Long"
block|,
literal|"java.lang.Void"
block|,
name|ILLEGAL_TYPE
block|,
name|ILLEGAL_TYPE
block|,
name|ILLEGAL_TYPE
block|,
name|ILLEGAL_TYPE
block|}
decl_stmt|;
comment|/**      * The signature characters corresponding to primitive types, e.g., SHORT_TYPE_NAMES[T_INT] = "I"      */
specifier|private
specifier|static
specifier|final
name|String
index|[]
name|SHORT_TYPE_NAMES
init|=
block|{
name|ILLEGAL_TYPE
block|,
name|ILLEGAL_TYPE
block|,
name|ILLEGAL_TYPE
block|,
name|ILLEGAL_TYPE
block|,
literal|"Z"
block|,
literal|"C"
block|,
literal|"F"
block|,
literal|"D"
block|,
literal|"B"
block|,
literal|"S"
block|,
literal|"I"
block|,
literal|"J"
block|,
literal|"V"
block|,
name|ILLEGAL_TYPE
block|,
name|ILLEGAL_TYPE
block|,
name|ILLEGAL_TYPE
block|}
decl_stmt|;
comment|/**      * Number of byte code operands for each opcode, i.e., number of bytes after the tag byte itself. Indexed by opcode, so      * NO_OF_OPERANDS[BIPUSH] = the number of operands for a bipush instruction.      */
specifier|static
specifier|final
name|short
index|[]
name|NO_OF_OPERANDS
init|=
block|{
literal|0
comment|/* nop */
block|,
literal|0
comment|/* aconst_null */
block|,
literal|0
comment|/* iconst_m1 */
block|,
literal|0
comment|/* iconst_0 */
block|,
literal|0
comment|/* iconst_1 */
block|,
literal|0
comment|/* iconst_2 */
block|,
literal|0
comment|/* iconst_3 */
block|,
literal|0
comment|/* iconst_4 */
block|,
literal|0
comment|/* iconst_5 */
block|,
literal|0
comment|/* lconst_0 */
block|,
literal|0
comment|/* lconst_1 */
block|,
literal|0
comment|/* fconst_0 */
block|,
literal|0
comment|/* fconst_1 */
block|,
literal|0
comment|/* fconst_2 */
block|,
literal|0
comment|/* dconst_0 */
block|,
literal|0
comment|/* dconst_1 */
block|,
literal|1
comment|/* bipush */
block|,
literal|2
comment|/* sipush */
block|,
literal|1
comment|/* ldc */
block|,
literal|2
comment|/* ldc_w */
block|,
literal|2
comment|/* ldc2_w */
block|,
literal|1
comment|/* iload */
block|,
literal|1
comment|/* lload */
block|,
literal|1
comment|/* fload */
block|,
literal|1
comment|/* dload */
block|,
literal|1
comment|/* aload */
block|,
literal|0
comment|/* iload_0 */
block|,
literal|0
comment|/* iload_1 */
block|,
literal|0
comment|/* iload_2 */
block|,
literal|0
comment|/* iload_3 */
block|,
literal|0
comment|/* lload_0 */
block|,
literal|0
comment|/* lload_1 */
block|,
literal|0
comment|/* lload_2 */
block|,
literal|0
comment|/* lload_3 */
block|,
literal|0
comment|/* fload_0 */
block|,
literal|0
comment|/* fload_1 */
block|,
literal|0
comment|/* fload_2 */
block|,
literal|0
comment|/* fload_3 */
block|,
literal|0
comment|/* dload_0 */
block|,
literal|0
comment|/* dload_1 */
block|,
literal|0
comment|/* dload_2 */
block|,
literal|0
comment|/* dload_3 */
block|,
literal|0
comment|/* aload_0 */
block|,
literal|0
comment|/* aload_1 */
block|,
literal|0
comment|/* aload_2 */
block|,
literal|0
comment|/* aload_3 */
block|,
literal|0
comment|/* iaload */
block|,
literal|0
comment|/* laload */
block|,
literal|0
comment|/* faload */
block|,
literal|0
comment|/* daload */
block|,
literal|0
comment|/* aaload */
block|,
literal|0
comment|/* baload */
block|,
literal|0
comment|/* caload */
block|,
literal|0
comment|/* saload */
block|,
literal|1
comment|/* istore */
block|,
literal|1
comment|/* lstore */
block|,
literal|1
comment|/* fstore */
block|,
literal|1
comment|/* dstore */
block|,
literal|1
comment|/* astore */
block|,
literal|0
comment|/* istore_0 */
block|,
literal|0
comment|/* istore_1 */
block|,
literal|0
comment|/* istore_2 */
block|,
literal|0
comment|/* istore_3 */
block|,
literal|0
comment|/* lstore_0 */
block|,
literal|0
comment|/* lstore_1 */
block|,
literal|0
comment|/* lstore_2 */
block|,
literal|0
comment|/* lstore_3 */
block|,
literal|0
comment|/* fstore_0 */
block|,
literal|0
comment|/* fstore_1 */
block|,
literal|0
comment|/* fstore_2 */
block|,
literal|0
comment|/* fstore_3 */
block|,
literal|0
comment|/* dstore_0 */
block|,
literal|0
comment|/* dstore_1 */
block|,
literal|0
comment|/* dstore_2 */
block|,
literal|0
comment|/* dstore_3 */
block|,
literal|0
comment|/* astore_0 */
block|,
literal|0
comment|/* astore_1 */
block|,
literal|0
comment|/* astore_2 */
block|,
literal|0
comment|/* astore_3 */
block|,
literal|0
comment|/* iastore */
block|,
literal|0
comment|/* lastore */
block|,
literal|0
comment|/* fastore */
block|,
literal|0
comment|/* dastore */
block|,
literal|0
comment|/* aastore */
block|,
literal|0
comment|/* bastore */
block|,
literal|0
comment|/* castore */
block|,
literal|0
comment|/* sastore */
block|,
literal|0
comment|/* pop */
block|,
literal|0
comment|/* pop2 */
block|,
literal|0
comment|/* dup */
block|,
literal|0
comment|/* dup_x1 */
block|,
literal|0
comment|/* dup_x2 */
block|,
literal|0
comment|/* dup2 */
block|,
literal|0
comment|/* dup2_x1 */
block|,
literal|0
comment|/* dup2_x2 */
block|,
literal|0
comment|/* swap */
block|,
literal|0
comment|/* iadd */
block|,
literal|0
comment|/* ladd */
block|,
literal|0
comment|/* fadd */
block|,
literal|0
comment|/* dadd */
block|,
literal|0
comment|/* isub */
block|,
literal|0
comment|/* lsub */
block|,
literal|0
comment|/* fsub */
block|,
literal|0
comment|/* dsub */
block|,
literal|0
comment|/* imul */
block|,
literal|0
comment|/* lmul */
block|,
literal|0
comment|/* fmul */
block|,
literal|0
comment|/* dmul */
block|,
literal|0
comment|/* idiv */
block|,
literal|0
comment|/* ldiv */
block|,
literal|0
comment|/* fdiv */
block|,
literal|0
comment|/* ddiv */
block|,
literal|0
comment|/* irem */
block|,
literal|0
comment|/* lrem */
block|,
literal|0
comment|/* frem */
block|,
literal|0
comment|/* drem */
block|,
literal|0
comment|/* ineg */
block|,
literal|0
comment|/* lneg */
block|,
literal|0
comment|/* fneg */
block|,
literal|0
comment|/* dneg */
block|,
literal|0
comment|/* ishl */
block|,
literal|0
comment|/* lshl */
block|,
literal|0
comment|/* ishr */
block|,
literal|0
comment|/* lshr */
block|,
literal|0
comment|/* iushr */
block|,
literal|0
comment|/* lushr */
block|,
literal|0
comment|/* iand */
block|,
literal|0
comment|/* land */
block|,
literal|0
comment|/* ior */
block|,
literal|0
comment|/* lor */
block|,
literal|0
comment|/* ixor */
block|,
literal|0
comment|/* lxor */
block|,
literal|2
comment|/* iinc */
block|,
literal|0
comment|/* i2l */
block|,
literal|0
comment|/* i2f */
block|,
literal|0
comment|/* i2d */
block|,
literal|0
comment|/* l2i */
block|,
literal|0
comment|/* l2f */
block|,
literal|0
comment|/* l2d */
block|,
literal|0
comment|/* f2i */
block|,
literal|0
comment|/* f2l */
block|,
literal|0
comment|/* f2d */
block|,
literal|0
comment|/* d2i */
block|,
literal|0
comment|/* d2l */
block|,
literal|0
comment|/* d2f */
block|,
literal|0
comment|/* i2b */
block|,
literal|0
comment|/* i2c */
block|,
literal|0
comment|/* i2s */
block|,
literal|0
comment|/* lcmp */
block|,
literal|0
comment|/* fcmpl */
block|,
literal|0
comment|/* fcmpg */
block|,
literal|0
comment|/* dcmpl */
block|,
literal|0
comment|/* dcmpg */
block|,
literal|2
comment|/* ifeq */
block|,
literal|2
comment|/* ifne */
block|,
literal|2
comment|/* iflt */
block|,
literal|2
comment|/* ifge */
block|,
literal|2
comment|/* ifgt */
block|,
literal|2
comment|/* ifle */
block|,
literal|2
comment|/* if_icmpeq */
block|,
literal|2
comment|/* if_icmpne */
block|,
literal|2
comment|/* if_icmplt */
block|,
literal|2
comment|/* if_icmpge */
block|,
literal|2
comment|/* if_icmpgt */
block|,
literal|2
comment|/* if_icmple */
block|,
literal|2
comment|/* if_acmpeq */
block|,
literal|2
comment|/* if_acmpne */
block|,
literal|2
comment|/* goto */
block|,
literal|2
comment|/* jsr */
block|,
literal|1
comment|/* ret */
block|,
name|UNPREDICTABLE
comment|/* tableswitch */
block|,
name|UNPREDICTABLE
comment|/* lookupswitch */
block|,
literal|0
comment|/* ireturn */
block|,
literal|0
comment|/* lreturn */
block|,
literal|0
comment|/* freturn */
block|,
literal|0
comment|/* dreturn */
block|,
literal|0
comment|/* areturn */
block|,
literal|0
comment|/* return */
block|,
literal|2
comment|/* getstatic */
block|,
literal|2
comment|/* putstatic */
block|,
literal|2
comment|/* getfield */
block|,
literal|2
comment|/* putfield */
block|,
literal|2
comment|/* invokevirtual */
block|,
literal|2
comment|/* invokespecial */
block|,
literal|2
comment|/* invokestatic */
block|,
literal|4
comment|/* invokeinterface */
block|,
literal|4
comment|/* invokedynamic */
block|,
literal|2
comment|/* new */
block|,
literal|1
comment|/* newarray */
block|,
literal|2
comment|/* anewarray */
block|,
literal|0
comment|/* arraylength */
block|,
literal|0
comment|/* athrow */
block|,
literal|2
comment|/* checkcast */
block|,
literal|2
comment|/* instanceof */
block|,
literal|0
comment|/* monitorenter */
block|,
literal|0
comment|/* monitorexit */
block|,
name|UNPREDICTABLE
comment|/* wide */
block|,
literal|3
comment|/* multianewarray */
block|,
literal|2
comment|/* ifnull */
block|,
literal|2
comment|/* ifnonnull */
block|,
literal|4
comment|/* goto_w */
block|,
literal|4
comment|/* jsr_w */
block|,
literal|0
comment|/* breakpoint */
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|RESERVED
comment|/* impdep1 */
block|,
name|RESERVED
comment|/* impdep2 */
block|}
decl_stmt|;
comment|/**      * How the byte code operands are to be interpreted for each opcode. Indexed by opcode. TYPE_OF_OPERANDS[ILOAD] = an      * array of shorts describing the data types for the instruction.      */
specifier|static
specifier|final
name|short
index|[]
index|[]
name|TYPE_OF_OPERANDS
init|=
block|{
block|{}
comment|/* nop */
block|,
block|{}
comment|/* aconst_null */
block|,
block|{}
comment|/* iconst_m1 */
block|,
block|{}
comment|/* iconst_0 */
block|,
block|{}
comment|/* iconst_1 */
block|,
block|{}
comment|/* iconst_2 */
block|,
block|{}
comment|/* iconst_3 */
block|,
block|{}
comment|/* iconst_4 */
block|,
block|{}
comment|/* iconst_5 */
block|,
block|{}
comment|/* lconst_0 */
block|,
block|{}
comment|/* lconst_1 */
block|,
block|{}
comment|/* fconst_0 */
block|,
block|{}
comment|/* fconst_1 */
block|,
block|{}
comment|/* fconst_2 */
block|,
block|{}
comment|/* dconst_0 */
block|,
block|{}
comment|/* dconst_1 */
block|,
block|{
name|T_BYTE
block|}
comment|/* bipush */
block|,
block|{
name|T_SHORT
block|}
comment|/* sipush */
block|,
block|{
name|T_BYTE
block|}
comment|/* ldc */
block|,
block|{
name|T_SHORT
block|}
comment|/* ldc_w */
block|,
block|{
name|T_SHORT
block|}
comment|/* ldc2_w */
block|,
block|{
name|T_BYTE
block|}
comment|/* iload */
block|,
block|{
name|T_BYTE
block|}
comment|/* lload */
block|,
block|{
name|T_BYTE
block|}
comment|/* fload */
block|,
block|{
name|T_BYTE
block|}
comment|/* dload */
block|,
block|{
name|T_BYTE
block|}
comment|/* aload */
block|,
block|{}
comment|/* iload_0 */
block|,
block|{}
comment|/* iload_1 */
block|,
block|{}
comment|/* iload_2 */
block|,
block|{}
comment|/* iload_3 */
block|,
block|{}
comment|/* lload_0 */
block|,
block|{}
comment|/* lload_1 */
block|,
block|{}
comment|/* lload_2 */
block|,
block|{}
comment|/* lload_3 */
block|,
block|{}
comment|/* fload_0 */
block|,
block|{}
comment|/* fload_1 */
block|,
block|{}
comment|/* fload_2 */
block|,
block|{}
comment|/* fload_3 */
block|,
block|{}
comment|/* dload_0 */
block|,
block|{}
comment|/* dload_1 */
block|,
block|{}
comment|/* dload_2 */
block|,
block|{}
comment|/* dload_3 */
block|,
block|{}
comment|/* aload_0 */
block|,
block|{}
comment|/* aload_1 */
block|,
block|{}
comment|/* aload_2 */
block|,
block|{}
comment|/* aload_3 */
block|,
block|{}
comment|/* iaload */
block|,
block|{}
comment|/* laload */
block|,
block|{}
comment|/* faload */
block|,
block|{}
comment|/* daload */
block|,
block|{}
comment|/* aaload */
block|,
block|{}
comment|/* baload */
block|,
block|{}
comment|/* caload */
block|,
block|{}
comment|/* saload */
block|,
block|{
name|T_BYTE
block|}
comment|/* istore */
block|,
block|{
name|T_BYTE
block|}
comment|/* lstore */
block|,
block|{
name|T_BYTE
block|}
comment|/* fstore */
block|,
block|{
name|T_BYTE
block|}
comment|/* dstore */
block|,
block|{
name|T_BYTE
block|}
comment|/* astore */
block|,
block|{}
comment|/* istore_0 */
block|,
block|{}
comment|/* istore_1 */
block|,
block|{}
comment|/* istore_2 */
block|,
block|{}
comment|/* istore_3 */
block|,
block|{}
comment|/* lstore_0 */
block|,
block|{}
comment|/* lstore_1 */
block|,
block|{}
comment|/* lstore_2 */
block|,
block|{}
comment|/* lstore_3 */
block|,
block|{}
comment|/* fstore_0 */
block|,
block|{}
comment|/* fstore_1 */
block|,
block|{}
comment|/* fstore_2 */
block|,
block|{}
comment|/* fstore_3 */
block|,
block|{}
comment|/* dstore_0 */
block|,
block|{}
comment|/* dstore_1 */
block|,
block|{}
comment|/* dstore_2 */
block|,
block|{}
comment|/* dstore_3 */
block|,
block|{}
comment|/* astore_0 */
block|,
block|{}
comment|/* astore_1 */
block|,
block|{}
comment|/* astore_2 */
block|,
block|{}
comment|/* astore_3 */
block|,
block|{}
comment|/* iastore */
block|,
block|{}
comment|/* lastore */
block|,
block|{}
comment|/* fastore */
block|,
block|{}
comment|/* dastore */
block|,
block|{}
comment|/* aastore */
block|,
block|{}
comment|/* bastore */
block|,
block|{}
comment|/* castore */
block|,
block|{}
comment|/* sastore */
block|,
block|{}
comment|/* pop */
block|,
block|{}
comment|/* pop2 */
block|,
block|{}
comment|/* dup */
block|,
block|{}
comment|/* dup_x1 */
block|,
block|{}
comment|/* dup_x2 */
block|,
block|{}
comment|/* dup2 */
block|,
block|{}
comment|/* dup2_x1 */
block|,
block|{}
comment|/* dup2_x2 */
block|,
block|{}
comment|/* swap */
block|,
block|{}
comment|/* iadd */
block|,
block|{}
comment|/* ladd */
block|,
block|{}
comment|/* fadd */
block|,
block|{}
comment|/* dadd */
block|,
block|{}
comment|/* isub */
block|,
block|{}
comment|/* lsub */
block|,
block|{}
comment|/* fsub */
block|,
block|{}
comment|/* dsub */
block|,
block|{}
comment|/* imul */
block|,
block|{}
comment|/* lmul */
block|,
block|{}
comment|/* fmul */
block|,
block|{}
comment|/* dmul */
block|,
block|{}
comment|/* idiv */
block|,
block|{}
comment|/* ldiv */
block|,
block|{}
comment|/* fdiv */
block|,
block|{}
comment|/* ddiv */
block|,
block|{}
comment|/* irem */
block|,
block|{}
comment|/* lrem */
block|,
block|{}
comment|/* frem */
block|,
block|{}
comment|/* drem */
block|,
block|{}
comment|/* ineg */
block|,
block|{}
comment|/* lneg */
block|,
block|{}
comment|/* fneg */
block|,
block|{}
comment|/* dneg */
block|,
block|{}
comment|/* ishl */
block|,
block|{}
comment|/* lshl */
block|,
block|{}
comment|/* ishr */
block|,
block|{}
comment|/* lshr */
block|,
block|{}
comment|/* iushr */
block|,
block|{}
comment|/* lushr */
block|,
block|{}
comment|/* iand */
block|,
block|{}
comment|/* land */
block|,
block|{}
comment|/* ior */
block|,
block|{}
comment|/* lor */
block|,
block|{}
comment|/* ixor */
block|,
block|{}
comment|/* lxor */
block|,
block|{
name|T_BYTE
block|,
name|T_BYTE
block|}
comment|/* iinc */
block|,
block|{}
comment|/* i2l */
block|,
block|{}
comment|/* i2f */
block|,
block|{}
comment|/* i2d */
block|,
block|{}
comment|/* l2i */
block|,
block|{}
comment|/* l2f */
block|,
block|{}
comment|/* l2d */
block|,
block|{}
comment|/* f2i */
block|,
block|{}
comment|/* f2l */
block|,
block|{}
comment|/* f2d */
block|,
block|{}
comment|/* d2i */
block|,
block|{}
comment|/* d2l */
block|,
block|{}
comment|/* d2f */
block|,
block|{}
comment|/* i2b */
block|,
block|{}
comment|/* i2c */
block|,
block|{}
comment|/* i2s */
block|,
block|{}
comment|/* lcmp */
block|,
block|{}
comment|/* fcmpl */
block|,
block|{}
comment|/* fcmpg */
block|,
block|{}
comment|/* dcmpl */
block|,
block|{}
comment|/* dcmpg */
block|,
block|{
name|T_SHORT
block|}
comment|/* ifeq */
block|,
block|{
name|T_SHORT
block|}
comment|/* ifne */
block|,
block|{
name|T_SHORT
block|}
comment|/* iflt */
block|,
block|{
name|T_SHORT
block|}
comment|/* ifge */
block|,
block|{
name|T_SHORT
block|}
comment|/* ifgt */
block|,
block|{
name|T_SHORT
block|}
comment|/* ifle */
block|,
block|{
name|T_SHORT
block|}
comment|/* if_icmpeq */
block|,
block|{
name|T_SHORT
block|}
comment|/* if_icmpne */
block|,
block|{
name|T_SHORT
block|}
comment|/* if_icmplt */
block|,
block|{
name|T_SHORT
block|}
comment|/* if_icmpge */
block|,
block|{
name|T_SHORT
block|}
comment|/* if_icmpgt */
block|,
block|{
name|T_SHORT
block|}
comment|/* if_icmple */
block|,
block|{
name|T_SHORT
block|}
comment|/* if_acmpeq */
block|,
block|{
name|T_SHORT
block|}
comment|/* if_acmpne */
block|,
block|{
name|T_SHORT
block|}
comment|/* goto */
block|,
block|{
name|T_SHORT
block|}
comment|/* jsr */
block|,
block|{
name|T_BYTE
block|}
comment|/* ret */
block|,
block|{}
comment|/* tableswitch */
block|,
block|{}
comment|/* lookupswitch */
block|,
block|{}
comment|/* ireturn */
block|,
block|{}
comment|/* lreturn */
block|,
block|{}
comment|/* freturn */
block|,
block|{}
comment|/* dreturn */
block|,
block|{}
comment|/* areturn */
block|,
block|{}
comment|/* return */
block|,
block|{
name|T_SHORT
block|}
comment|/* getstatic */
block|,
block|{
name|T_SHORT
block|}
comment|/* putstatic */
block|,
block|{
name|T_SHORT
block|}
comment|/* getfield */
block|,
block|{
name|T_SHORT
block|}
comment|/* putfield */
block|,
block|{
name|T_SHORT
block|}
comment|/* invokevirtual */
block|,
block|{
name|T_SHORT
block|}
comment|/* invokespecial */
block|,
block|{
name|T_SHORT
block|}
comment|/* invokestatic */
block|,
block|{
name|T_SHORT
block|,
name|T_BYTE
block|,
name|T_BYTE
block|}
comment|/* invokeinterface */
block|,
block|{
name|T_SHORT
block|,
name|T_BYTE
block|,
name|T_BYTE
block|}
comment|/* invokedynamic */
block|,
block|{
name|T_SHORT
block|}
comment|/* new */
block|,
block|{
name|T_BYTE
block|}
comment|/* newarray */
block|,
block|{
name|T_SHORT
block|}
comment|/* anewarray */
block|,
block|{}
comment|/* arraylength */
block|,
block|{}
comment|/* athrow */
block|,
block|{
name|T_SHORT
block|}
comment|/* checkcast */
block|,
block|{
name|T_SHORT
block|}
comment|/* instanceof */
block|,
block|{}
comment|/* monitorenter */
block|,
block|{}
comment|/* monitorexit */
block|,
block|{
name|T_BYTE
block|}
comment|/* wide */
block|,
block|{
name|T_SHORT
block|,
name|T_BYTE
block|}
comment|/* multianewarray */
block|,
block|{
name|T_SHORT
block|}
comment|/* ifnull */
block|,
block|{
name|T_SHORT
block|}
comment|/* ifnonnull */
block|,
block|{
name|T_INT
block|}
comment|/* goto_w */
block|,
block|{
name|T_INT
block|}
comment|/* jsr_w */
block|,
block|{}
comment|/* breakpoint */
block|,
block|{}
block|,
block|{}
block|,
block|{}
block|,
block|{}
block|,
block|{}
block|,
block|{}
block|,
block|{}
block|,
block|{}
block|,
block|{}
block|,
block|{}
block|,
block|{}
block|,
block|{}
block|,
block|{}
block|,
block|{}
block|,
block|{}
block|,
block|{}
block|,
block|{}
block|,
block|{}
block|,
block|{}
block|,
block|{}
block|,
block|{}
block|,
block|{}
block|,
block|{}
block|,
block|{}
block|,
block|{}
block|,
block|{}
block|,
block|{}
block|,
block|{}
block|,
block|{}
block|,
block|{}
block|,
block|{}
block|,
block|{}
block|,
block|{}
block|,
block|{}
block|,
block|{}
block|,
block|{}
block|,
block|{}
block|,
block|{}
block|,
block|{}
block|,
block|{}
block|,
block|{}
block|,
block|{}
block|,
block|{}
block|,
block|{}
block|,
block|{}
block|,
block|{}
block|,
block|{}
block|,
block|{}
block|,
block|{}
block|,
block|{}
block|,
block|{}
block|,
block|{}
comment|/* impdep1 */
block|,
block|{}
comment|/* impdep2 */
block|}
decl_stmt|;
comment|/**      * Names of opcodes. Indexed by opcode. OPCODE_NAMES[ALOAD] = "aload".      */
specifier|static
specifier|final
name|String
index|[]
name|OPCODE_NAMES
init|=
block|{
literal|"nop"
block|,
literal|"aconst_null"
block|,
literal|"iconst_m1"
block|,
literal|"iconst_0"
block|,
literal|"iconst_1"
block|,
literal|"iconst_2"
block|,
literal|"iconst_3"
block|,
literal|"iconst_4"
block|,
literal|"iconst_5"
block|,
literal|"lconst_0"
block|,
literal|"lconst_1"
block|,
literal|"fconst_0"
block|,
literal|"fconst_1"
block|,
literal|"fconst_2"
block|,
literal|"dconst_0"
block|,
literal|"dconst_1"
block|,
literal|"bipush"
block|,
literal|"sipush"
block|,
literal|"ldc"
block|,
literal|"ldc_w"
block|,
literal|"ldc2_w"
block|,
literal|"iload"
block|,
literal|"lload"
block|,
literal|"fload"
block|,
literal|"dload"
block|,
literal|"aload"
block|,
literal|"iload_0"
block|,
literal|"iload_1"
block|,
literal|"iload_2"
block|,
literal|"iload_3"
block|,
literal|"lload_0"
block|,
literal|"lload_1"
block|,
literal|"lload_2"
block|,
literal|"lload_3"
block|,
literal|"fload_0"
block|,
literal|"fload_1"
block|,
literal|"fload_2"
block|,
literal|"fload_3"
block|,
literal|"dload_0"
block|,
literal|"dload_1"
block|,
literal|"dload_2"
block|,
literal|"dload_3"
block|,
literal|"aload_0"
block|,
literal|"aload_1"
block|,
literal|"aload_2"
block|,
literal|"aload_3"
block|,
literal|"iaload"
block|,
literal|"laload"
block|,
literal|"faload"
block|,
literal|"daload"
block|,
literal|"aaload"
block|,
literal|"baload"
block|,
literal|"caload"
block|,
literal|"saload"
block|,
literal|"istore"
block|,
literal|"lstore"
block|,
literal|"fstore"
block|,
literal|"dstore"
block|,
literal|"astore"
block|,
literal|"istore_0"
block|,
literal|"istore_1"
block|,
literal|"istore_2"
block|,
literal|"istore_3"
block|,
literal|"lstore_0"
block|,
literal|"lstore_1"
block|,
literal|"lstore_2"
block|,
literal|"lstore_3"
block|,
literal|"fstore_0"
block|,
literal|"fstore_1"
block|,
literal|"fstore_2"
block|,
literal|"fstore_3"
block|,
literal|"dstore_0"
block|,
literal|"dstore_1"
block|,
literal|"dstore_2"
block|,
literal|"dstore_3"
block|,
literal|"astore_0"
block|,
literal|"astore_1"
block|,
literal|"astore_2"
block|,
literal|"astore_3"
block|,
literal|"iastore"
block|,
literal|"lastore"
block|,
literal|"fastore"
block|,
literal|"dastore"
block|,
literal|"aastore"
block|,
literal|"bastore"
block|,
literal|"castore"
block|,
literal|"sastore"
block|,
literal|"pop"
block|,
literal|"pop2"
block|,
literal|"dup"
block|,
literal|"dup_x1"
block|,
literal|"dup_x2"
block|,
literal|"dup2"
block|,
literal|"dup2_x1"
block|,
literal|"dup2_x2"
block|,
literal|"swap"
block|,
literal|"iadd"
block|,
literal|"ladd"
block|,
literal|"fadd"
block|,
literal|"dadd"
block|,
literal|"isub"
block|,
literal|"lsub"
block|,
literal|"fsub"
block|,
literal|"dsub"
block|,
literal|"imul"
block|,
literal|"lmul"
block|,
literal|"fmul"
block|,
literal|"dmul"
block|,
literal|"idiv"
block|,
literal|"ldiv"
block|,
literal|"fdiv"
block|,
literal|"ddiv"
block|,
literal|"irem"
block|,
literal|"lrem"
block|,
literal|"frem"
block|,
literal|"drem"
block|,
literal|"ineg"
block|,
literal|"lneg"
block|,
literal|"fneg"
block|,
literal|"dneg"
block|,
literal|"ishl"
block|,
literal|"lshl"
block|,
literal|"ishr"
block|,
literal|"lshr"
block|,
literal|"iushr"
block|,
literal|"lushr"
block|,
literal|"iand"
block|,
literal|"land"
block|,
literal|"ior"
block|,
literal|"lor"
block|,
literal|"ixor"
block|,
literal|"lxor"
block|,
literal|"iinc"
block|,
literal|"i2l"
block|,
literal|"i2f"
block|,
literal|"i2d"
block|,
literal|"l2i"
block|,
literal|"l2f"
block|,
literal|"l2d"
block|,
literal|"f2i"
block|,
literal|"f2l"
block|,
literal|"f2d"
block|,
literal|"d2i"
block|,
literal|"d2l"
block|,
literal|"d2f"
block|,
literal|"i2b"
block|,
literal|"i2c"
block|,
literal|"i2s"
block|,
literal|"lcmp"
block|,
literal|"fcmpl"
block|,
literal|"fcmpg"
block|,
literal|"dcmpl"
block|,
literal|"dcmpg"
block|,
literal|"ifeq"
block|,
literal|"ifne"
block|,
literal|"iflt"
block|,
literal|"ifge"
block|,
literal|"ifgt"
block|,
literal|"ifle"
block|,
literal|"if_icmpeq"
block|,
literal|"if_icmpne"
block|,
literal|"if_icmplt"
block|,
literal|"if_icmpge"
block|,
literal|"if_icmpgt"
block|,
literal|"if_icmple"
block|,
literal|"if_acmpeq"
block|,
literal|"if_acmpne"
block|,
literal|"goto"
block|,
literal|"jsr"
block|,
literal|"ret"
block|,
literal|"tableswitch"
block|,
literal|"lookupswitch"
block|,
literal|"ireturn"
block|,
literal|"lreturn"
block|,
literal|"freturn"
block|,
literal|"dreturn"
block|,
literal|"areturn"
block|,
literal|"return"
block|,
literal|"getstatic"
block|,
literal|"putstatic"
block|,
literal|"getfield"
block|,
literal|"putfield"
block|,
literal|"invokevirtual"
block|,
literal|"invokespecial"
block|,
literal|"invokestatic"
block|,
literal|"invokeinterface"
block|,
literal|"invokedynamic"
block|,
literal|"new"
block|,
literal|"newarray"
block|,
literal|"anewarray"
block|,
literal|"arraylength"
block|,
literal|"athrow"
block|,
literal|"checkcast"
block|,
literal|"instanceof"
block|,
literal|"monitorenter"
block|,
literal|"monitorexit"
block|,
literal|"wide"
block|,
literal|"multianewarray"
block|,
literal|"ifnull"
block|,
literal|"ifnonnull"
block|,
literal|"goto_w"
block|,
literal|"jsr_w"
block|,
literal|"breakpoint"
block|,
name|ILLEGAL_OPCODE
block|,
name|ILLEGAL_OPCODE
block|,
name|ILLEGAL_OPCODE
block|,
name|ILLEGAL_OPCODE
block|,
name|ILLEGAL_OPCODE
block|,
name|ILLEGAL_OPCODE
block|,
name|ILLEGAL_OPCODE
block|,
name|ILLEGAL_OPCODE
block|,
name|ILLEGAL_OPCODE
block|,
name|ILLEGAL_OPCODE
block|,
name|ILLEGAL_OPCODE
block|,
name|ILLEGAL_OPCODE
block|,
name|ILLEGAL_OPCODE
block|,
name|ILLEGAL_OPCODE
block|,
name|ILLEGAL_OPCODE
block|,
name|ILLEGAL_OPCODE
block|,
name|ILLEGAL_OPCODE
block|,
name|ILLEGAL_OPCODE
block|,
name|ILLEGAL_OPCODE
block|,
name|ILLEGAL_OPCODE
block|,
name|ILLEGAL_OPCODE
block|,
name|ILLEGAL_OPCODE
block|,
name|ILLEGAL_OPCODE
block|,
name|ILLEGAL_OPCODE
block|,
name|ILLEGAL_OPCODE
block|,
name|ILLEGAL_OPCODE
block|,
name|ILLEGAL_OPCODE
block|,
name|ILLEGAL_OPCODE
block|,
name|ILLEGAL_OPCODE
block|,
name|ILLEGAL_OPCODE
block|,
name|ILLEGAL_OPCODE
block|,
name|ILLEGAL_OPCODE
block|,
name|ILLEGAL_OPCODE
block|,
name|ILLEGAL_OPCODE
block|,
name|ILLEGAL_OPCODE
block|,
name|ILLEGAL_OPCODE
block|,
name|ILLEGAL_OPCODE
block|,
name|ILLEGAL_OPCODE
block|,
name|ILLEGAL_OPCODE
block|,
name|ILLEGAL_OPCODE
block|,
name|ILLEGAL_OPCODE
block|,
name|ILLEGAL_OPCODE
block|,
name|ILLEGAL_OPCODE
block|,
name|ILLEGAL_OPCODE
block|,
name|ILLEGAL_OPCODE
block|,
name|ILLEGAL_OPCODE
block|,
name|ILLEGAL_OPCODE
block|,
name|ILLEGAL_OPCODE
block|,
name|ILLEGAL_OPCODE
block|,
name|ILLEGAL_OPCODE
block|,
name|ILLEGAL_OPCODE
block|,
literal|"impdep1"
block|,
literal|"impdep2"
block|}
decl_stmt|;
comment|/**      * @since 6.0      */
specifier|public
specifier|static
specifier|final
name|int
name|OPCODE_NAMES_LENGTH
init|=
name|OPCODE_NAMES
operator|.
name|length
decl_stmt|;
comment|/**      * Number of words consumed on operand stack by instructions. Indexed by opcode. CONSUME_STACK[FALOAD] = number of words      * consumed from the stack by a faload instruction.      */
specifier|static
specifier|final
name|int
index|[]
name|CONSUME_STACK
init|=
block|{
literal|0
comment|/* nop */
block|,
literal|0
comment|/* aconst_null */
block|,
literal|0
comment|/* iconst_m1 */
block|,
literal|0
comment|/* iconst_0 */
block|,
literal|0
comment|/* iconst_1 */
block|,
literal|0
comment|/* iconst_2 */
block|,
literal|0
comment|/* iconst_3 */
block|,
literal|0
comment|/* iconst_4 */
block|,
literal|0
comment|/* iconst_5 */
block|,
literal|0
comment|/* lconst_0 */
block|,
literal|0
comment|/* lconst_1 */
block|,
literal|0
comment|/* fconst_0 */
block|,
literal|0
comment|/* fconst_1 */
block|,
literal|0
comment|/* fconst_2 */
block|,
literal|0
comment|/* dconst_0 */
block|,
literal|0
comment|/* dconst_1 */
block|,
literal|0
comment|/* bipush */
block|,
literal|0
comment|/* sipush */
block|,
literal|0
comment|/* ldc */
block|,
literal|0
comment|/* ldc_w */
block|,
literal|0
comment|/* ldc2_w */
block|,
literal|0
comment|/* iload */
block|,
literal|0
comment|/* lload */
block|,
literal|0
comment|/* fload */
block|,
literal|0
comment|/* dload */
block|,
literal|0
comment|/* aload */
block|,
literal|0
comment|/* iload_0 */
block|,
literal|0
comment|/* iload_1 */
block|,
literal|0
comment|/* iload_2 */
block|,
literal|0
comment|/* iload_3 */
block|,
literal|0
comment|/* lload_0 */
block|,
literal|0
comment|/* lload_1 */
block|,
literal|0
comment|/* lload_2 */
block|,
literal|0
comment|/* lload_3 */
block|,
literal|0
comment|/* fload_0 */
block|,
literal|0
comment|/* fload_1 */
block|,
literal|0
comment|/* fload_2 */
block|,
literal|0
comment|/* fload_3 */
block|,
literal|0
comment|/* dload_0 */
block|,
literal|0
comment|/* dload_1 */
block|,
literal|0
comment|/* dload_2 */
block|,
literal|0
comment|/* dload_3 */
block|,
literal|0
comment|/* aload_0 */
block|,
literal|0
comment|/* aload_1 */
block|,
literal|0
comment|/* aload_2 */
block|,
literal|0
comment|/* aload_3 */
block|,
literal|2
comment|/* iaload */
block|,
literal|2
comment|/* laload */
block|,
literal|2
comment|/* faload */
block|,
literal|2
comment|/* daload */
block|,
literal|2
comment|/* aaload */
block|,
literal|2
comment|/* baload */
block|,
literal|2
comment|/* caload */
block|,
literal|2
comment|/* saload */
block|,
literal|1
comment|/* istore */
block|,
literal|2
comment|/* lstore */
block|,
literal|1
comment|/* fstore */
block|,
literal|2
comment|/* dstore */
block|,
literal|1
comment|/* astore */
block|,
literal|1
comment|/* istore_0 */
block|,
literal|1
comment|/* istore_1 */
block|,
literal|1
comment|/* istore_2 */
block|,
literal|1
comment|/* istore_3 */
block|,
literal|2
comment|/* lstore_0 */
block|,
literal|2
comment|/* lstore_1 */
block|,
literal|2
comment|/* lstore_2 */
block|,
literal|2
comment|/* lstore_3 */
block|,
literal|1
comment|/* fstore_0 */
block|,
literal|1
comment|/* fstore_1 */
block|,
literal|1
comment|/* fstore_2 */
block|,
literal|1
comment|/* fstore_3 */
block|,
literal|2
comment|/* dstore_0 */
block|,
literal|2
comment|/* dstore_1 */
block|,
literal|2
comment|/* dstore_2 */
block|,
literal|2
comment|/* dstore_3 */
block|,
literal|1
comment|/* astore_0 */
block|,
literal|1
comment|/* astore_1 */
block|,
literal|1
comment|/* astore_2 */
block|,
literal|1
comment|/* astore_3 */
block|,
literal|3
comment|/* iastore */
block|,
literal|4
comment|/* lastore */
block|,
literal|3
comment|/* fastore */
block|,
literal|4
comment|/* dastore */
block|,
literal|3
comment|/* aastore */
block|,
literal|3
comment|/* bastore */
block|,
literal|3
comment|/* castore */
block|,
literal|3
comment|/* sastore */
block|,
literal|1
comment|/* pop */
block|,
literal|2
comment|/* pop2 */
block|,
literal|1
comment|/* dup */
block|,
literal|2
comment|/* dup_x1 */
block|,
literal|3
comment|/* dup_x2 */
block|,
literal|2
comment|/* dup2 */
block|,
literal|3
comment|/* dup2_x1 */
block|,
literal|4
comment|/* dup2_x2 */
block|,
literal|2
comment|/* swap */
block|,
literal|2
comment|/* iadd */
block|,
literal|4
comment|/* ladd */
block|,
literal|2
comment|/* fadd */
block|,
literal|4
comment|/* dadd */
block|,
literal|2
comment|/* isub */
block|,
literal|4
comment|/* lsub */
block|,
literal|2
comment|/* fsub */
block|,
literal|4
comment|/* dsub */
block|,
literal|2
comment|/* imul */
block|,
literal|4
comment|/* lmul */
block|,
literal|2
comment|/* fmul */
block|,
literal|4
comment|/* dmul */
block|,
literal|2
comment|/* idiv */
block|,
literal|4
comment|/* ldiv */
block|,
literal|2
comment|/* fdiv */
block|,
literal|4
comment|/* ddiv */
block|,
literal|2
comment|/* irem */
block|,
literal|4
comment|/* lrem */
block|,
literal|2
comment|/* frem */
block|,
literal|4
comment|/* drem */
block|,
literal|1
comment|/* ineg */
block|,
literal|2
comment|/* lneg */
block|,
literal|1
comment|/* fneg */
block|,
literal|2
comment|/* dneg */
block|,
literal|2
comment|/* ishl */
block|,
literal|3
comment|/* lshl */
block|,
literal|2
comment|/* ishr */
block|,
literal|3
comment|/* lshr */
block|,
literal|2
comment|/* iushr */
block|,
literal|3
comment|/* lushr */
block|,
literal|2
comment|/* iand */
block|,
literal|4
comment|/* land */
block|,
literal|2
comment|/* ior */
block|,
literal|4
comment|/* lor */
block|,
literal|2
comment|/* ixor */
block|,
literal|4
comment|/* lxor */
block|,
literal|0
comment|/* iinc */
block|,
literal|1
comment|/* i2l */
block|,
literal|1
comment|/* i2f */
block|,
literal|1
comment|/* i2d */
block|,
literal|2
comment|/* l2i */
block|,
literal|2
comment|/* l2f */
block|,
literal|2
comment|/* l2d */
block|,
literal|1
comment|/* f2i */
block|,
literal|1
comment|/* f2l */
block|,
literal|1
comment|/* f2d */
block|,
literal|2
comment|/* d2i */
block|,
literal|2
comment|/* d2l */
block|,
literal|2
comment|/* d2f */
block|,
literal|1
comment|/* i2b */
block|,
literal|1
comment|/* i2c */
block|,
literal|1
comment|/* i2s */
block|,
literal|4
comment|/* lcmp */
block|,
literal|2
comment|/* fcmpl */
block|,
literal|2
comment|/* fcmpg */
block|,
literal|4
comment|/* dcmpl */
block|,
literal|4
comment|/* dcmpg */
block|,
literal|1
comment|/* ifeq */
block|,
literal|1
comment|/* ifne */
block|,
literal|1
comment|/* iflt */
block|,
literal|1
comment|/* ifge */
block|,
literal|1
comment|/* ifgt */
block|,
literal|1
comment|/* ifle */
block|,
literal|2
comment|/* if_icmpeq */
block|,
literal|2
comment|/* if_icmpne */
block|,
literal|2
comment|/* if_icmplt */
block|,
literal|2
comment|/* if_icmpge */
block|,
literal|2
comment|/* if_icmpgt */
block|,
literal|2
comment|/* if_icmple */
block|,
literal|2
comment|/* if_acmpeq */
block|,
literal|2
comment|/* if_acmpne */
block|,
literal|0
comment|/* goto */
block|,
literal|0
comment|/* jsr */
block|,
literal|0
comment|/* ret */
block|,
literal|1
comment|/* tableswitch */
block|,
literal|1
comment|/* lookupswitch */
block|,
literal|1
comment|/* ireturn */
block|,
literal|2
comment|/* lreturn */
block|,
literal|1
comment|/* freturn */
block|,
literal|2
comment|/* dreturn */
block|,
literal|1
comment|/* areturn */
block|,
literal|0
comment|/* return */
block|,
literal|0
comment|/* getstatic */
block|,
name|UNPREDICTABLE
comment|/* putstatic */
block|,
literal|1
comment|/* getfield */
block|,
name|UNPREDICTABLE
comment|/* putfield */
block|,
name|UNPREDICTABLE
comment|/* invokevirtual */
block|,
name|UNPREDICTABLE
comment|/* invokespecial */
block|,
name|UNPREDICTABLE
comment|/* invokestatic */
block|,
name|UNPREDICTABLE
comment|/* invokeinterface */
block|,
name|UNPREDICTABLE
comment|/* invokedynamic */
block|,
literal|0
comment|/* new */
block|,
literal|1
comment|/* newarray */
block|,
literal|1
comment|/* anewarray */
block|,
literal|1
comment|/* arraylength */
block|,
literal|1
comment|/* athrow */
block|,
literal|1
comment|/* checkcast */
block|,
literal|1
comment|/* instanceof */
block|,
literal|1
comment|/* monitorenter */
block|,
literal|1
comment|/* monitorexit */
block|,
literal|0
comment|/* wide */
block|,
name|UNPREDICTABLE
comment|/* multianewarray */
block|,
literal|1
comment|/* ifnull */
block|,
literal|1
comment|/* ifnonnull */
block|,
literal|0
comment|/* goto_w */
block|,
literal|0
comment|/* jsr_w */
block|,
literal|0
comment|/* breakpoint */
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNPREDICTABLE
comment|/* impdep1 */
block|,
name|UNPREDICTABLE
comment|/* impdep2 */
block|}
decl_stmt|;
comment|/**      * Number of words produced onto operand stack by instructions. Indexed by opcode. CONSUME_STACK[DALOAD] = number of      * words consumed from the stack by a daload instruction.      */
specifier|static
specifier|final
name|int
index|[]
name|PRODUCE_STACK
init|=
block|{
literal|0
comment|/* nop */
block|,
literal|1
comment|/* aconst_null */
block|,
literal|1
comment|/* iconst_m1 */
block|,
literal|1
comment|/* iconst_0 */
block|,
literal|1
comment|/* iconst_1 */
block|,
literal|1
comment|/* iconst_2 */
block|,
literal|1
comment|/* iconst_3 */
block|,
literal|1
comment|/* iconst_4 */
block|,
literal|1
comment|/* iconst_5 */
block|,
literal|2
comment|/* lconst_0 */
block|,
literal|2
comment|/* lconst_1 */
block|,
literal|1
comment|/* fconst_0 */
block|,
literal|1
comment|/* fconst_1 */
block|,
literal|1
comment|/* fconst_2 */
block|,
literal|2
comment|/* dconst_0 */
block|,
literal|2
comment|/* dconst_1 */
block|,
literal|1
comment|/* bipush */
block|,
literal|1
comment|/* sipush */
block|,
literal|1
comment|/* ldc */
block|,
literal|1
comment|/* ldc_w */
block|,
literal|2
comment|/* ldc2_w */
block|,
literal|1
comment|/* iload */
block|,
literal|2
comment|/* lload */
block|,
literal|1
comment|/* fload */
block|,
literal|2
comment|/* dload */
block|,
literal|1
comment|/* aload */
block|,
literal|1
comment|/* iload_0 */
block|,
literal|1
comment|/* iload_1 */
block|,
literal|1
comment|/* iload_2 */
block|,
literal|1
comment|/* iload_3 */
block|,
literal|2
comment|/* lload_0 */
block|,
literal|2
comment|/* lload_1 */
block|,
literal|2
comment|/* lload_2 */
block|,
literal|2
comment|/* lload_3 */
block|,
literal|1
comment|/* fload_0 */
block|,
literal|1
comment|/* fload_1 */
block|,
literal|1
comment|/* fload_2 */
block|,
literal|1
comment|/* fload_3 */
block|,
literal|2
comment|/* dload_0 */
block|,
literal|2
comment|/* dload_1 */
block|,
literal|2
comment|/* dload_2 */
block|,
literal|2
comment|/* dload_3 */
block|,
literal|1
comment|/* aload_0 */
block|,
literal|1
comment|/* aload_1 */
block|,
literal|1
comment|/* aload_2 */
block|,
literal|1
comment|/* aload_3 */
block|,
literal|1
comment|/* iaload */
block|,
literal|2
comment|/* laload */
block|,
literal|1
comment|/* faload */
block|,
literal|2
comment|/* daload */
block|,
literal|1
comment|/* aaload */
block|,
literal|1
comment|/* baload */
block|,
literal|1
comment|/* caload */
block|,
literal|1
comment|/* saload */
block|,
literal|0
comment|/* istore */
block|,
literal|0
comment|/* lstore */
block|,
literal|0
comment|/* fstore */
block|,
literal|0
comment|/* dstore */
block|,
literal|0
comment|/* astore */
block|,
literal|0
comment|/* istore_0 */
block|,
literal|0
comment|/* istore_1 */
block|,
literal|0
comment|/* istore_2 */
block|,
literal|0
comment|/* istore_3 */
block|,
literal|0
comment|/* lstore_0 */
block|,
literal|0
comment|/* lstore_1 */
block|,
literal|0
comment|/* lstore_2 */
block|,
literal|0
comment|/* lstore_3 */
block|,
literal|0
comment|/* fstore_0 */
block|,
literal|0
comment|/* fstore_1 */
block|,
literal|0
comment|/* fstore_2 */
block|,
literal|0
comment|/* fstore_3 */
block|,
literal|0
comment|/* dstore_0 */
block|,
literal|0
comment|/* dstore_1 */
block|,
literal|0
comment|/* dstore_2 */
block|,
literal|0
comment|/* dstore_3 */
block|,
literal|0
comment|/* astore_0 */
block|,
literal|0
comment|/* astore_1 */
block|,
literal|0
comment|/* astore_2 */
block|,
literal|0
comment|/* astore_3 */
block|,
literal|0
comment|/* iastore */
block|,
literal|0
comment|/* lastore */
block|,
literal|0
comment|/* fastore */
block|,
literal|0
comment|/* dastore */
block|,
literal|0
comment|/* aastore */
block|,
literal|0
comment|/* bastore */
block|,
literal|0
comment|/* castore */
block|,
literal|0
comment|/* sastore */
block|,
literal|0
comment|/* pop */
block|,
literal|0
comment|/* pop2 */
block|,
literal|2
comment|/* dup */
block|,
literal|3
comment|/* dup_x1 */
block|,
literal|4
comment|/* dup_x2 */
block|,
literal|4
comment|/* dup2 */
block|,
literal|5
comment|/* dup2_x1 */
block|,
literal|6
comment|/* dup2_x2 */
block|,
literal|2
comment|/* swap */
block|,
literal|1
comment|/* iadd */
block|,
literal|2
comment|/* ladd */
block|,
literal|1
comment|/* fadd */
block|,
literal|2
comment|/* dadd */
block|,
literal|1
comment|/* isub */
block|,
literal|2
comment|/* lsub */
block|,
literal|1
comment|/* fsub */
block|,
literal|2
comment|/* dsub */
block|,
literal|1
comment|/* imul */
block|,
literal|2
comment|/* lmul */
block|,
literal|1
comment|/* fmul */
block|,
literal|2
comment|/* dmul */
block|,
literal|1
comment|/* idiv */
block|,
literal|2
comment|/* ldiv */
block|,
literal|1
comment|/* fdiv */
block|,
literal|2
comment|/* ddiv */
block|,
literal|1
comment|/* irem */
block|,
literal|2
comment|/* lrem */
block|,
literal|1
comment|/* frem */
block|,
literal|2
comment|/* drem */
block|,
literal|1
comment|/* ineg */
block|,
literal|2
comment|/* lneg */
block|,
literal|1
comment|/* fneg */
block|,
literal|2
comment|/* dneg */
block|,
literal|1
comment|/* ishl */
block|,
literal|2
comment|/* lshl */
block|,
literal|1
comment|/* ishr */
block|,
literal|2
comment|/* lshr */
block|,
literal|1
comment|/* iushr */
block|,
literal|2
comment|/* lushr */
block|,
literal|1
comment|/* iand */
block|,
literal|2
comment|/* land */
block|,
literal|1
comment|/* ior */
block|,
literal|2
comment|/* lor */
block|,
literal|1
comment|/* ixor */
block|,
literal|2
comment|/* lxor */
block|,
literal|0
comment|/* iinc */
block|,
literal|2
comment|/* i2l */
block|,
literal|1
comment|/* i2f */
block|,
literal|2
comment|/* i2d */
block|,
literal|1
comment|/* l2i */
block|,
literal|1
comment|/* l2f */
block|,
literal|2
comment|/* l2d */
block|,
literal|1
comment|/* f2i */
block|,
literal|2
comment|/* f2l */
block|,
literal|2
comment|/* f2d */
block|,
literal|1
comment|/* d2i */
block|,
literal|2
comment|/* d2l */
block|,
literal|1
comment|/* d2f */
block|,
literal|1
comment|/* i2b */
block|,
literal|1
comment|/* i2c */
block|,
literal|1
comment|/* i2s */
block|,
literal|1
comment|/* lcmp */
block|,
literal|1
comment|/* fcmpl */
block|,
literal|1
comment|/* fcmpg */
block|,
literal|1
comment|/* dcmpl */
block|,
literal|1
comment|/* dcmpg */
block|,
literal|0
comment|/* ifeq */
block|,
literal|0
comment|/* ifne */
block|,
literal|0
comment|/* iflt */
block|,
literal|0
comment|/* ifge */
block|,
literal|0
comment|/* ifgt */
block|,
literal|0
comment|/* ifle */
block|,
literal|0
comment|/* if_icmpeq */
block|,
literal|0
comment|/* if_icmpne */
block|,
literal|0
comment|/* if_icmplt */
block|,
literal|0
comment|/* if_icmpge */
block|,
literal|0
comment|/* if_icmpgt */
block|,
literal|0
comment|/* if_icmple */
block|,
literal|0
comment|/* if_acmpeq */
block|,
literal|0
comment|/* if_acmpne */
block|,
literal|0
comment|/* goto */
block|,
literal|1
comment|/* jsr */
block|,
literal|0
comment|/* ret */
block|,
literal|0
comment|/* tableswitch */
block|,
literal|0
comment|/* lookupswitch */
block|,
literal|0
comment|/* ireturn */
block|,
literal|0
comment|/* lreturn */
block|,
literal|0
comment|/* freturn */
block|,
literal|0
comment|/* dreturn */
block|,
literal|0
comment|/* areturn */
block|,
literal|0
comment|/* return */
block|,
name|UNPREDICTABLE
comment|/* getstatic */
block|,
literal|0
comment|/* putstatic */
block|,
name|UNPREDICTABLE
comment|/* getfield */
block|,
literal|0
comment|/* putfield */
block|,
name|UNPREDICTABLE
comment|/* invokevirtual */
block|,
name|UNPREDICTABLE
comment|/* invokespecial */
block|,
name|UNPREDICTABLE
comment|/* invokestatic */
block|,
name|UNPREDICTABLE
comment|/* invokeinterface */
block|,
name|UNPREDICTABLE
comment|/* invokedynamic */
block|,
literal|1
comment|/* new */
block|,
literal|1
comment|/* newarray */
block|,
literal|1
comment|/* anewarray */
block|,
literal|1
comment|/* arraylength */
block|,
literal|1
comment|/* athrow */
block|,
literal|1
comment|/* checkcast */
block|,
literal|1
comment|/* instanceof */
block|,
literal|0
comment|/* monitorenter */
block|,
literal|0
comment|/* monitorexit */
block|,
literal|0
comment|/* wide */
block|,
literal|1
comment|/* multianewarray */
block|,
literal|0
comment|/* ifnull */
block|,
literal|0
comment|/* ifnonnull */
block|,
literal|0
comment|/* goto_w */
block|,
literal|1
comment|/* jsr_w */
block|,
literal|0
comment|/* breakpoint */
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNDEFINED
block|,
name|UNPREDICTABLE
comment|/* impdep1 */
block|,
name|UNPREDICTABLE
comment|/* impdep2 */
block|}
decl_stmt|;
comment|/**      * Attributes and their corresponding names.      */
specifier|public
specifier|static
specifier|final
name|byte
name|ATTR_UNKNOWN
init|=
operator|-
literal|1
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|byte
name|ATTR_SOURCE_FILE
init|=
literal|0
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|byte
name|ATTR_CONSTANT_VALUE
init|=
literal|1
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|byte
name|ATTR_CODE
init|=
literal|2
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|byte
name|ATTR_EXCEPTIONS
init|=
literal|3
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|byte
name|ATTR_LINE_NUMBER_TABLE
init|=
literal|4
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|byte
name|ATTR_LOCAL_VARIABLE_TABLE
init|=
literal|5
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|byte
name|ATTR_INNER_CLASSES
init|=
literal|6
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|byte
name|ATTR_SYNTHETIC
init|=
literal|7
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|byte
name|ATTR_DEPRECATED
init|=
literal|8
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|byte
name|ATTR_PMG
init|=
literal|9
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|byte
name|ATTR_SIGNATURE
init|=
literal|10
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|byte
name|ATTR_STACK_MAP
init|=
literal|11
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|byte
name|ATTR_RUNTIME_VISIBLE_ANNOTATIONS
init|=
literal|12
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|byte
name|ATTR_RUNTIME_INVISIBLE_ANNOTATIONS
init|=
literal|13
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|byte
name|ATTR_RUNTIME_VISIBLE_PARAMETER_ANNOTATIONS
init|=
literal|14
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|byte
name|ATTR_RUNTIME_INVISIBLE_PARAMETER_ANNOTATIONS
init|=
literal|15
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|byte
name|ATTR_ANNOTATION_DEFAULT
init|=
literal|16
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|byte
name|ATTR_LOCAL_VARIABLE_TYPE_TABLE
init|=
literal|17
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|byte
name|ATTR_ENCLOSING_METHOD
init|=
literal|18
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|byte
name|ATTR_STACK_MAP_TABLE
init|=
literal|19
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|byte
name|ATTR_BOOTSTRAP_METHODS
init|=
literal|20
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|byte
name|ATTR_METHOD_PARAMETERS
init|=
literal|21
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|byte
name|ATTR_MODULE
init|=
literal|22
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|byte
name|ATTR_MODULE_PACKAGES
init|=
literal|23
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|byte
name|ATTR_MODULE_MAIN_CLASS
init|=
literal|24
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|byte
name|ATTR_NEST_HOST
init|=
literal|25
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|byte
name|ATTR_NEST_MEMBERS
init|=
literal|26
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|short
name|KNOWN_ATTRIBUTES
init|=
literal|27
decl_stmt|;
comment|// count of attributes
specifier|private
specifier|static
specifier|final
name|String
index|[]
name|ATTRIBUTE_NAMES
init|=
block|{
literal|"SourceFile"
block|,
literal|"ConstantValue"
block|,
literal|"Code"
block|,
literal|"Exceptions"
block|,
literal|"LineNumberTable"
block|,
literal|"LocalVariableTable"
block|,
literal|"InnerClasses"
block|,
literal|"Synthetic"
block|,
literal|"Deprecated"
block|,
literal|"PMGClass"
block|,
literal|"Signature"
block|,
literal|"StackMap"
block|,
literal|"RuntimeVisibleAnnotations"
block|,
literal|"RuntimeInvisibleAnnotations"
block|,
literal|"RuntimeVisibleParameterAnnotations"
block|,
literal|"RuntimeInvisibleParameterAnnotations"
block|,
literal|"AnnotationDefault"
block|,
literal|"LocalVariableTypeTable"
block|,
literal|"EnclosingMethod"
block|,
literal|"StackMapTable"
block|,
literal|"BootstrapMethods"
block|,
literal|"MethodParameters"
block|,
literal|"Module"
block|,
literal|"ModulePackages"
block|,
literal|"ModuleMainClass"
block|,
literal|"NestHost"
block|,
literal|"NestMembers"
block|}
decl_stmt|;
comment|/**      * Constants used in the StackMap attribute.      */
specifier|public
specifier|static
specifier|final
name|byte
name|ITEM_Bogus
init|=
literal|0
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|byte
name|ITEM_Integer
init|=
literal|1
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|byte
name|ITEM_Float
init|=
literal|2
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|byte
name|ITEM_Double
init|=
literal|3
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|byte
name|ITEM_Long
init|=
literal|4
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|byte
name|ITEM_Null
init|=
literal|5
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|byte
name|ITEM_InitObject
init|=
literal|6
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|byte
name|ITEM_Object
init|=
literal|7
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|byte
name|ITEM_NewObject
init|=
literal|8
decl_stmt|;
specifier|private
specifier|static
specifier|final
name|String
index|[]
name|ITEM_NAMES
init|=
block|{
literal|"Bogus"
block|,
literal|"Integer"
block|,
literal|"Float"
block|,
literal|"Double"
block|,
literal|"Long"
block|,
literal|"Null"
block|,
literal|"InitObject"
block|,
literal|"Object"
block|,
literal|"NewObject"
block|}
decl_stmt|;
comment|/**      * Constants used to identify StackMapEntry types.      *      * For those types which can specify a range, the constant names the lowest value.      */
specifier|public
specifier|static
specifier|final
name|int
name|SAME_FRAME
init|=
literal|0
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|int
name|SAME_LOCALS_1_STACK_ITEM_FRAME
init|=
literal|64
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|int
name|SAME_LOCALS_1_STACK_ITEM_FRAME_EXTENDED
init|=
literal|247
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|int
name|CHOP_FRAME
init|=
literal|248
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|int
name|SAME_FRAME_EXTENDED
init|=
literal|251
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|int
name|APPEND_FRAME
init|=
literal|252
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|int
name|FULL_FRAME
init|=
literal|255
decl_stmt|;
comment|/**      * Constants that define the maximum value of those constants which store ranges.      */
specifier|public
specifier|static
specifier|final
name|int
name|SAME_FRAME_MAX
init|=
literal|63
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|int
name|SAME_LOCALS_1_STACK_ITEM_FRAME_MAX
init|=
literal|127
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|int
name|CHOP_FRAME_MAX
init|=
literal|250
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|int
name|APPEND_FRAME_MAX
init|=
literal|254
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|byte
name|REF_getField
init|=
literal|1
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|byte
name|REF_getStatic
init|=
literal|2
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|byte
name|REF_putField
init|=
literal|3
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|byte
name|REF_putStatic
init|=
literal|4
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|byte
name|REF_invokeVirtual
init|=
literal|5
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|byte
name|REF_invokeStatic
init|=
literal|6
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|byte
name|REF_invokeSpecial
init|=
literal|7
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|byte
name|REF_newInvokeSpecial
init|=
literal|8
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|byte
name|REF_invokeInterface
init|=
literal|9
decl_stmt|;
comment|/**      * The names of the reference_kinds of a CONSTANT_MethodHandle_info.      */
specifier|private
specifier|static
specifier|final
name|String
index|[]
name|METHODHANDLE_NAMES
init|=
block|{
literal|""
block|,
literal|"getField"
block|,
literal|"getStatic"
block|,
literal|"putField"
block|,
literal|"putStatic"
block|,
literal|"invokeVirtual"
block|,
literal|"invokeStatic"
block|,
literal|"invokeSpecial"
block|,
literal|"newInvokeSpecial"
block|,
literal|"invokeInterface"
block|}
decl_stmt|;
comment|/**      * @param index      * @return the ACCESS_NAMES entry at the given index      * @since 6.0      */
specifier|public
specifier|static
name|String
name|getAccessName
parameter_list|(
specifier|final
name|int
name|index
parameter_list|)
block|{
return|return
name|ACCESS_NAMES
index|[
name|index
index|]
return|;
block|}
comment|/**      *      * @param index      * @return the attribute name      * @since 6.0      */
specifier|public
specifier|static
name|String
name|getAttributeName
parameter_list|(
specifier|final
name|int
name|index
parameter_list|)
block|{
return|return
name|ATTRIBUTE_NAMES
index|[
name|index
index|]
return|;
block|}
comment|/**      * The primitive class names corresponding to the T_XX constants, e.g., CLASS_TYPE_NAMES[T_INT] = "java.lang.Integer"      *      * @param index      * @return the class name      * @since 6.0      */
specifier|public
specifier|static
name|String
name|getClassTypeName
parameter_list|(
specifier|final
name|int
name|index
parameter_list|)
block|{
return|return
name|CLASS_TYPE_NAMES
index|[
name|index
index|]
return|;
block|}
comment|/**      *      * @param index      * @return the CONSTANT_NAMES entry at the given index      * @since 6.0      */
specifier|public
specifier|static
name|String
name|getConstantName
parameter_list|(
specifier|final
name|int
name|index
parameter_list|)
block|{
return|return
name|CONSTANT_NAMES
index|[
name|index
index|]
return|;
block|}
comment|// Constants defining the behavior of the Method Handles (JVMS ï¿½5.4.3.5)
comment|/**      *      * @param index      * @return Number of words consumed on operand stack      * @since 6.0      */
specifier|public
specifier|static
name|int
name|getConsumeStack
parameter_list|(
specifier|final
name|int
name|index
parameter_list|)
block|{
return|return
name|CONSUME_STACK
index|[
name|index
index|]
return|;
block|}
comment|/**      * @since 6.0      */
specifier|public
specifier|static
name|Iterable
argument_list|<
name|String
argument_list|>
name|getInterfacesImplementedByArrays
parameter_list|()
block|{
return|return
name|Collections
operator|.
name|unmodifiableList
argument_list|(
name|Arrays
operator|.
name|asList
argument_list|(
name|INTERFACES_IMPLEMENTED_BY_ARRAYS
argument_list|)
argument_list|)
return|;
block|}
comment|/**      *      * @param index      * @return the item name      * @since 6.0      */
specifier|public
specifier|static
name|String
name|getItemName
parameter_list|(
specifier|final
name|int
name|index
parameter_list|)
block|{
return|return
name|ITEM_NAMES
index|[
name|index
index|]
return|;
block|}
comment|/**      *      * @param index      * @return the method handle name      * @since 6.0      */
specifier|public
specifier|static
name|String
name|getMethodHandleName
parameter_list|(
specifier|final
name|int
name|index
parameter_list|)
block|{
return|return
name|METHODHANDLE_NAMES
index|[
name|index
index|]
return|;
block|}
comment|/**      *      * @param index      * @return Number of byte code operands      * @since 6.0      */
specifier|public
specifier|static
name|short
name|getNoOfOperands
parameter_list|(
specifier|final
name|int
name|index
parameter_list|)
block|{
return|return
name|NO_OF_OPERANDS
index|[
name|index
index|]
return|;
block|}
comment|/**      * @since 6.0      */
specifier|public
specifier|static
name|String
name|getOpcodeName
parameter_list|(
specifier|final
name|int
name|index
parameter_list|)
block|{
return|return
name|OPCODE_NAMES
index|[
name|index
index|]
return|;
block|}
comment|/**      * @since 6.0      */
specifier|public
specifier|static
name|short
name|getOperandType
parameter_list|(
specifier|final
name|int
name|opcode
parameter_list|,
specifier|final
name|int
name|index
parameter_list|)
block|{
return|return
name|TYPE_OF_OPERANDS
index|[
name|opcode
index|]
index|[
name|index
index|]
return|;
block|}
comment|/**      * @since 6.0      */
specifier|public
specifier|static
name|long
name|getOperandTypeCount
parameter_list|(
specifier|final
name|int
name|opcode
parameter_list|)
block|{
return|return
name|TYPE_OF_OPERANDS
index|[
name|opcode
index|]
operator|.
name|length
return|;
block|}
comment|/**      *      * @param index      * @return Number of words produced onto operand stack      * @since 6.0      */
specifier|public
specifier|static
name|int
name|getProduceStack
parameter_list|(
specifier|final
name|int
name|index
parameter_list|)
block|{
return|return
name|PRODUCE_STACK
index|[
name|index
index|]
return|;
block|}
comment|/**      *      * @param index      * @return the short type name      * @since 6.0      */
specifier|public
specifier|static
name|String
name|getShortTypeName
parameter_list|(
specifier|final
name|int
name|index
parameter_list|)
block|{
return|return
name|SHORT_TYPE_NAMES
index|[
name|index
index|]
return|;
block|}
comment|/**      * The primitive type names corresponding to the T_XX constants, e.g., TYPE_NAMES[T_INT] = "int"      *      * @param index      * @return the type name      * @since 6.0      */
specifier|public
specifier|static
name|String
name|getTypeName
parameter_list|(
specifier|final
name|int
name|index
parameter_list|)
block|{
return|return
name|TYPE_NAMES
index|[
name|index
index|]
return|;
block|}
specifier|private
name|Const
parameter_list|()
block|{
block|}
comment|// not instantiable
block|}
end_class

end_unit

