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

begin_comment
comment|/**  * Constants for the project, mostly defined in the JVM specification.  *  * @deprecated (since 6.0) DO NOT USE - use {@link Const} instead.  */
end_comment

begin_interface
annotation|@
name|Deprecated
specifier|public
interface|interface
name|Constants
block|{
comment|/**      * Major version number of class files for Java 1.1.      *      * @see #MINOR_1_1      */
name|short
name|MAJOR_1_1
init|=
name|Const
operator|.
name|MAJOR_1_1
decl_stmt|;
comment|/**      * Minor version number of class files for Java 1.1.      *      * @see #MAJOR_1_1      */
name|short
name|MINOR_1_1
init|=
name|Const
operator|.
name|MINOR_1_1
decl_stmt|;
comment|/**      * Major version number of class files for Java 1.2.      *      * @see #MINOR_1_2      */
name|short
name|MAJOR_1_2
init|=
name|Const
operator|.
name|MAJOR_1_2
decl_stmt|;
comment|/**      * Minor version number of class files for Java 1.2.      *      * @see #MAJOR_1_2      */
name|short
name|MINOR_1_2
init|=
name|Const
operator|.
name|MINOR_1_2
decl_stmt|;
comment|/**      * Major version number of class files for Java 1.2.      *      * @see #MINOR_1_2      */
name|short
name|MAJOR_1_3
init|=
name|Const
operator|.
name|MAJOR_1_3
decl_stmt|;
comment|/**      * Minor version number of class files for Java 1.3.      *      * @see #MAJOR_1_3      */
name|short
name|MINOR_1_3
init|=
name|Const
operator|.
name|MINOR_1_3
decl_stmt|;
comment|/**      * Major version number of class files for Java 1.3.      *      * @see #MINOR_1_3      */
name|short
name|MAJOR_1_4
init|=
name|Const
operator|.
name|MAJOR_1_4
decl_stmt|;
comment|/**      * Minor version number of class files for Java 1.4.      *      * @see #MAJOR_1_4      */
name|short
name|MINOR_1_4
init|=
name|Const
operator|.
name|MINOR_1_4
decl_stmt|;
comment|/**      * Major version number of class files for Java 1.4.      *      * @see #MINOR_1_4      */
name|short
name|MAJOR_1_5
init|=
name|Const
operator|.
name|MAJOR_1_5
decl_stmt|;
comment|/**      * Minor version number of class files for Java 1.5.      *      * @see #MAJOR_1_5      */
name|short
name|MINOR_1_5
init|=
name|Const
operator|.
name|MINOR_1_5
decl_stmt|;
comment|/**      * Default major version number. Class file is for Java 1.1.      *      * @see #MAJOR_1_1      */
name|short
name|MAJOR
init|=
name|Const
operator|.
name|MAJOR
decl_stmt|;
comment|/**      * Default major version number. Class file is for Java 1.1.      *      * @see #MAJOR_1_1      */
name|short
name|MINOR
init|=
name|Const
operator|.
name|MINOR
decl_stmt|;
comment|/**      * Maximum value for an unsigned short.      */
name|int
name|MAX_SHORT
init|=
name|Const
operator|.
name|MAX_SHORT
decl_stmt|;
comment|// 2^16 - 1
comment|/**      * Maximum value for an unsigned byte.      */
name|int
name|MAX_BYTE
init|=
name|Const
operator|.
name|MAX_BYTE
decl_stmt|;
comment|// 2^8 - 1
comment|/**      * One of the access flags for fields, methods, or classes.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.5"> Flag definitions for Fields      *      in the Java Virtual Machine Specification (Java SE 8 Edition).</a>      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.6"> Flag definitions for Methods      *      in the Java Virtual Machine Specification (Java SE 8 Edition).</a>      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.7.6-300-D.1-D.1"> Flag      *      definitions for Classes in the Java Virtual Machine Specification (Java SE 8 Edition).</a>      */
name|short
name|ACC_PUBLIC
init|=
name|Const
operator|.
name|ACC_PUBLIC
decl_stmt|;
comment|/**      * One of the access flags for fields, methods, or classes.      *      * @see #ACC_PUBLIC      */
name|short
name|ACC_PRIVATE
init|=
name|Const
operator|.
name|ACC_PRIVATE
decl_stmt|;
comment|/**      * One of the access flags for fields, methods, or classes.      *      * @see #ACC_PUBLIC      */
name|short
name|ACC_PROTECTED
init|=
name|Const
operator|.
name|ACC_PROTECTED
decl_stmt|;
comment|/**      * One of the access flags for fields, methods, or classes.      *      * @see #ACC_PUBLIC      */
name|short
name|ACC_STATIC
init|=
name|Const
operator|.
name|ACC_STATIC
decl_stmt|;
comment|/**      * One of the access flags for fields, methods, or classes.      *      * @see #ACC_PUBLIC      */
name|short
name|ACC_FINAL
init|=
name|Const
operator|.
name|ACC_FINAL
decl_stmt|;
comment|/**      * One of the access flags for fields, methods, or classes.      *      * @see #ACC_PUBLIC      */
name|short
name|ACC_SYNCHRONIZED
init|=
name|Const
operator|.
name|ACC_SYNCHRONIZED
decl_stmt|;
comment|/**      * One of the access flags for fields, methods, or classes.      *      * @see #ACC_PUBLIC      */
name|short
name|ACC_VOLATILE
init|=
name|Const
operator|.
name|ACC_VOLATILE
decl_stmt|;
comment|/**      * One of the access flags for fields, methods, or classes.      *      * @see #ACC_PUBLIC      */
name|short
name|ACC_BRIDGE
init|=
name|Const
operator|.
name|ACC_BRIDGE
decl_stmt|;
comment|/**      * One of the access flags for fields, methods, or classes.      *      * @see #ACC_PUBLIC      */
name|short
name|ACC_TRANSIENT
init|=
name|Const
operator|.
name|ACC_TRANSIENT
decl_stmt|;
comment|/**      * One of the access flags for fields, methods, or classes.      *      * @see #ACC_PUBLIC      */
name|short
name|ACC_VARARGS
init|=
name|Const
operator|.
name|ACC_VARARGS
decl_stmt|;
comment|/**      * One of the access flags for fields, methods, or classes.      *      * @see #ACC_PUBLIC      */
name|short
name|ACC_NATIVE
init|=
name|Const
operator|.
name|ACC_NATIVE
decl_stmt|;
comment|/**      * One of the access flags for fields, methods, or classes.      *      * @see #ACC_PUBLIC      */
name|short
name|ACC_INTERFACE
init|=
name|Const
operator|.
name|ACC_INTERFACE
decl_stmt|;
comment|/**      * One of the access flags for fields, methods, or classes.      *      * @see #ACC_PUBLIC      */
name|short
name|ACC_ABSTRACT
init|=
name|Const
operator|.
name|ACC_ABSTRACT
decl_stmt|;
comment|/**      * One of the access flags for fields, methods, or classes.      *      * @see #ACC_PUBLIC      */
name|short
name|ACC_STRICT
init|=
name|Const
operator|.
name|ACC_STRICT
decl_stmt|;
comment|/**      * One of the access flags for fields, methods, or classes.      *      * @see #ACC_PUBLIC      */
name|short
name|ACC_SYNTHETIC
init|=
name|Const
operator|.
name|ACC_SYNTHETIC
decl_stmt|;
comment|/**      * One of the access flags for fields, methods, or classes.      *      * @see #ACC_PUBLIC      */
name|short
name|ACC_ANNOTATION
init|=
name|Const
operator|.
name|ACC_ANNOTATION
decl_stmt|;
comment|/**      * One of the access flags for fields, methods, or classes.      *      * @see #ACC_PUBLIC      */
name|short
name|ACC_ENUM
init|=
name|Const
operator|.
name|ACC_ENUM
decl_stmt|;
comment|// Applies to classes compiled by new compilers only
comment|/**      * One of the access flags for fields, methods, or classes.      *      * @see #ACC_PUBLIC      */
name|short
name|ACC_SUPER
init|=
name|Const
operator|.
name|ACC_SUPER
decl_stmt|;
comment|/**      * One of the access flags for fields, methods, or classes.      *      * @see #ACC_PUBLIC      */
name|short
name|MAX_ACC_FLAG
init|=
name|Const
operator|.
name|MAX_ACC_FLAG
decl_stmt|;
comment|/** The names of the access flags. */
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
block|}
decl_stmt|;
comment|/** Marks a constant pool entry as type UTF-8. */
name|byte
name|CONSTANT_Utf8
init|=
name|Const
operator|.
name|CONSTANT_Utf8
decl_stmt|;
comment|/** Marks a constant pool entry as type Integer. */
name|byte
name|CONSTANT_Integer
init|=
name|Const
operator|.
name|CONSTANT_Integer
decl_stmt|;
comment|/** Marks a constant pool entry as type Float. */
name|byte
name|CONSTANT_Float
init|=
name|Const
operator|.
name|CONSTANT_Float
decl_stmt|;
comment|/** Marks a constant pool entry as type Long. */
name|byte
name|CONSTANT_Long
init|=
name|Const
operator|.
name|CONSTANT_Long
decl_stmt|;
comment|/** Marks a constant pool entry as type Double. */
name|byte
name|CONSTANT_Double
init|=
name|Const
operator|.
name|CONSTANT_Double
decl_stmt|;
comment|/** Marks a constant pool entry as a Class. */
name|byte
name|CONSTANT_Class
init|=
name|Const
operator|.
name|CONSTANT_Class
decl_stmt|;
comment|/** Marks a constant pool entry as a Field Reference. */
name|byte
name|CONSTANT_Fieldref
init|=
name|Const
operator|.
name|CONSTANT_Fieldref
decl_stmt|;
comment|/** Marks a constant pool entry as type String. */
name|byte
name|CONSTANT_String
init|=
name|Const
operator|.
name|CONSTANT_String
decl_stmt|;
comment|/** Marks a constant pool entry as a Method Reference. */
name|byte
name|CONSTANT_Methodref
init|=
name|Const
operator|.
name|CONSTANT_Methodref
decl_stmt|;
comment|/** Marks a constant pool entry as an Interface Method Reference. */
name|byte
name|CONSTANT_InterfaceMethodref
init|=
name|Const
operator|.
name|CONSTANT_InterfaceMethodref
decl_stmt|;
comment|/** Marks a constant pool entry as a name and type. */
name|byte
name|CONSTANT_NameAndType
init|=
name|Const
operator|.
name|CONSTANT_NameAndType
decl_stmt|;
comment|/** The names of the types of entries in a constant pool. */
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
block|}
decl_stmt|;
comment|/**      * The name of the static initializer, also called&quot;class initialization method&quot; or&quot;interface      * initialization method&quot;. This is&quot;&lt;clinit&gt;&quot;.      */
name|String
name|STATIC_INITIALIZER_NAME
init|=
name|Const
operator|.
name|STATIC_INITIALIZER_NAME
decl_stmt|;
comment|/**      * The name of every constructor method in a class, also called&quot;instance initialization method&quot;. This is      *&quot;&lt;init&gt;&quot;.      */
name|String
name|CONSTRUCTOR_NAME
init|=
name|Const
operator|.
name|CONSTRUCTOR_NAME
decl_stmt|;
comment|/** The names of the interfaces implemented by arrays */
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
comment|/**      * One of the limitations of the Java Virtual Machine.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.11"> The Java Virtual Machine      *      Specification, Second Edition, page 152, chapter 4.10.</a>      */
name|int
name|MAX_CP_ENTRIES
init|=
name|Const
operator|.
name|MAX_CP_ENTRIES
decl_stmt|;
comment|/**      * One of the limitations of the Java Virtual Machine.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.11"> The Java Virtual Machine      *      Specification, Second Edition, page 152, chapter 4.10.</a>      */
name|int
name|MAX_CODE_SIZE
init|=
name|Const
operator|.
name|MAX_CODE_SIZE
decl_stmt|;
comment|// bytes
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|NOP
init|=
name|Const
operator|.
name|NOP
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|ACONST_NULL
init|=
name|Const
operator|.
name|ACONST_NULL
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|ICONST_M1
init|=
name|Const
operator|.
name|ICONST_M1
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|ICONST_0
init|=
name|Const
operator|.
name|ICONST_0
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|ICONST_1
init|=
name|Const
operator|.
name|ICONST_1
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|ICONST_2
init|=
name|Const
operator|.
name|ICONST_2
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|ICONST_3
init|=
name|Const
operator|.
name|ICONST_3
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|ICONST_4
init|=
name|Const
operator|.
name|ICONST_4
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|ICONST_5
init|=
name|Const
operator|.
name|ICONST_5
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|LCONST_0
init|=
name|Const
operator|.
name|LCONST_0
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|LCONST_1
init|=
name|Const
operator|.
name|LCONST_1
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|FCONST_0
init|=
name|Const
operator|.
name|FCONST_0
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|FCONST_1
init|=
name|Const
operator|.
name|FCONST_1
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|FCONST_2
init|=
name|Const
operator|.
name|FCONST_2
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|DCONST_0
init|=
name|Const
operator|.
name|DCONST_0
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|DCONST_1
init|=
name|Const
operator|.
name|DCONST_1
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|BIPUSH
init|=
name|Const
operator|.
name|BIPUSH
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|SIPUSH
init|=
name|Const
operator|.
name|SIPUSH
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|LDC
init|=
name|Const
operator|.
name|LDC
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|LDC_W
init|=
name|Const
operator|.
name|LDC_W
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|LDC2_W
init|=
name|Const
operator|.
name|LDC2_W
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|ILOAD
init|=
name|Const
operator|.
name|ILOAD
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|LLOAD
init|=
name|Const
operator|.
name|LLOAD
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|FLOAD
init|=
name|Const
operator|.
name|FLOAD
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|DLOAD
init|=
name|Const
operator|.
name|DLOAD
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|ALOAD
init|=
name|Const
operator|.
name|ALOAD
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|ILOAD_0
init|=
name|Const
operator|.
name|ILOAD_0
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|ILOAD_1
init|=
name|Const
operator|.
name|ILOAD_1
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|ILOAD_2
init|=
name|Const
operator|.
name|ILOAD_2
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|ILOAD_3
init|=
name|Const
operator|.
name|ILOAD_3
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|LLOAD_0
init|=
name|Const
operator|.
name|LLOAD_0
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|LLOAD_1
init|=
name|Const
operator|.
name|LLOAD_1
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|LLOAD_2
init|=
name|Const
operator|.
name|LLOAD_2
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|LLOAD_3
init|=
name|Const
operator|.
name|LLOAD_3
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|FLOAD_0
init|=
name|Const
operator|.
name|FLOAD_0
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|FLOAD_1
init|=
name|Const
operator|.
name|FLOAD_1
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|FLOAD_2
init|=
name|Const
operator|.
name|FLOAD_2
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|FLOAD_3
init|=
name|Const
operator|.
name|FLOAD_3
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|DLOAD_0
init|=
name|Const
operator|.
name|DLOAD_0
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|DLOAD_1
init|=
name|Const
operator|.
name|DLOAD_1
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|DLOAD_2
init|=
name|Const
operator|.
name|DLOAD_2
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|DLOAD_3
init|=
name|Const
operator|.
name|DLOAD_3
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|ALOAD_0
init|=
name|Const
operator|.
name|ALOAD_0
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|ALOAD_1
init|=
name|Const
operator|.
name|ALOAD_1
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|ALOAD_2
init|=
name|Const
operator|.
name|ALOAD_2
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|ALOAD_3
init|=
name|Const
operator|.
name|ALOAD_3
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|IALOAD
init|=
name|Const
operator|.
name|IALOAD
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|LALOAD
init|=
name|Const
operator|.
name|LALOAD
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|FALOAD
init|=
name|Const
operator|.
name|FALOAD
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|DALOAD
init|=
name|Const
operator|.
name|DALOAD
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|AALOAD
init|=
name|Const
operator|.
name|AALOAD
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|BALOAD
init|=
name|Const
operator|.
name|BALOAD
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|CALOAD
init|=
name|Const
operator|.
name|CALOAD
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|SALOAD
init|=
name|Const
operator|.
name|SALOAD
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|ISTORE
init|=
name|Const
operator|.
name|ISTORE
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|LSTORE
init|=
name|Const
operator|.
name|LSTORE
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|FSTORE
init|=
name|Const
operator|.
name|FSTORE
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|DSTORE
init|=
name|Const
operator|.
name|DSTORE
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|ASTORE
init|=
name|Const
operator|.
name|ASTORE
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|ISTORE_0
init|=
name|Const
operator|.
name|ISTORE_0
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|ISTORE_1
init|=
name|Const
operator|.
name|ISTORE_1
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|ISTORE_2
init|=
name|Const
operator|.
name|ISTORE_2
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|ISTORE_3
init|=
name|Const
operator|.
name|ISTORE_3
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|LSTORE_0
init|=
name|Const
operator|.
name|LSTORE_0
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|LSTORE_1
init|=
name|Const
operator|.
name|LSTORE_1
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|LSTORE_2
init|=
name|Const
operator|.
name|LSTORE_2
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|LSTORE_3
init|=
name|Const
operator|.
name|LSTORE_3
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|FSTORE_0
init|=
name|Const
operator|.
name|FSTORE_0
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|FSTORE_1
init|=
name|Const
operator|.
name|FSTORE_1
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|FSTORE_2
init|=
name|Const
operator|.
name|FSTORE_2
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|FSTORE_3
init|=
name|Const
operator|.
name|FSTORE_3
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|DSTORE_0
init|=
name|Const
operator|.
name|DSTORE_0
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|DSTORE_1
init|=
name|Const
operator|.
name|DSTORE_1
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|DSTORE_2
init|=
name|Const
operator|.
name|DSTORE_2
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|DSTORE_3
init|=
name|Const
operator|.
name|DSTORE_3
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|ASTORE_0
init|=
name|Const
operator|.
name|ASTORE_0
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|ASTORE_1
init|=
name|Const
operator|.
name|ASTORE_1
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|ASTORE_2
init|=
name|Const
operator|.
name|ASTORE_2
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|ASTORE_3
init|=
name|Const
operator|.
name|ASTORE_3
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|IASTORE
init|=
name|Const
operator|.
name|IASTORE
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|LASTORE
init|=
name|Const
operator|.
name|LASTORE
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|FASTORE
init|=
name|Const
operator|.
name|FASTORE
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|DASTORE
init|=
name|Const
operator|.
name|DASTORE
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|AASTORE
init|=
name|Const
operator|.
name|AASTORE
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|BASTORE
init|=
name|Const
operator|.
name|BASTORE
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|CASTORE
init|=
name|Const
operator|.
name|CASTORE
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|SASTORE
init|=
name|Const
operator|.
name|SASTORE
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|POP
init|=
name|Const
operator|.
name|POP
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|POP2
init|=
name|Const
operator|.
name|POP2
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|DUP
init|=
name|Const
operator|.
name|DUP
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|DUP_X1
init|=
name|Const
operator|.
name|DUP_X1
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|DUP_X2
init|=
name|Const
operator|.
name|DUP_X2
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|DUP2
init|=
name|Const
operator|.
name|DUP2
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|DUP2_X1
init|=
name|Const
operator|.
name|DUP2_X1
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|DUP2_X2
init|=
name|Const
operator|.
name|DUP2_X2
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|SWAP
init|=
name|Const
operator|.
name|SWAP
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|IADD
init|=
name|Const
operator|.
name|IADD
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|LADD
init|=
name|Const
operator|.
name|LADD
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|FADD
init|=
name|Const
operator|.
name|FADD
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|DADD
init|=
name|Const
operator|.
name|DADD
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|ISUB
init|=
name|Const
operator|.
name|ISUB
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|LSUB
init|=
name|Const
operator|.
name|LSUB
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|FSUB
init|=
name|Const
operator|.
name|FSUB
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|DSUB
init|=
name|Const
operator|.
name|DSUB
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|IMUL
init|=
name|Const
operator|.
name|IMUL
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|LMUL
init|=
name|Const
operator|.
name|LMUL
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|FMUL
init|=
name|Const
operator|.
name|FMUL
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|DMUL
init|=
name|Const
operator|.
name|DMUL
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|IDIV
init|=
name|Const
operator|.
name|IDIV
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|LDIV
init|=
name|Const
operator|.
name|LDIV
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|FDIV
init|=
name|Const
operator|.
name|FDIV
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|DDIV
init|=
name|Const
operator|.
name|DDIV
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|IREM
init|=
name|Const
operator|.
name|IREM
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|LREM
init|=
name|Const
operator|.
name|LREM
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|FREM
init|=
name|Const
operator|.
name|FREM
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|DREM
init|=
name|Const
operator|.
name|DREM
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|INEG
init|=
name|Const
operator|.
name|INEG
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|LNEG
init|=
name|Const
operator|.
name|LNEG
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|FNEG
init|=
name|Const
operator|.
name|FNEG
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|DNEG
init|=
name|Const
operator|.
name|DNEG
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|ISHL
init|=
name|Const
operator|.
name|ISHL
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|LSHL
init|=
name|Const
operator|.
name|LSHL
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|ISHR
init|=
name|Const
operator|.
name|ISHR
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|LSHR
init|=
name|Const
operator|.
name|LSHR
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|IUSHR
init|=
name|Const
operator|.
name|IUSHR
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|LUSHR
init|=
name|Const
operator|.
name|LUSHR
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|IAND
init|=
name|Const
operator|.
name|IAND
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|LAND
init|=
name|Const
operator|.
name|LAND
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|IOR
init|=
name|Const
operator|.
name|IOR
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|LOR
init|=
name|Const
operator|.
name|LOR
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|IXOR
init|=
name|Const
operator|.
name|IXOR
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|LXOR
init|=
name|Const
operator|.
name|LXOR
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|IINC
init|=
name|Const
operator|.
name|IINC
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|I2L
init|=
name|Const
operator|.
name|I2L
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|I2F
init|=
name|Const
operator|.
name|I2F
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|I2D
init|=
name|Const
operator|.
name|I2D
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|L2I
init|=
name|Const
operator|.
name|L2I
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|L2F
init|=
name|Const
operator|.
name|L2F
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|L2D
init|=
name|Const
operator|.
name|L2D
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|F2I
init|=
name|Const
operator|.
name|F2I
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|F2L
init|=
name|Const
operator|.
name|F2L
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|F2D
init|=
name|Const
operator|.
name|F2D
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|D2I
init|=
name|Const
operator|.
name|D2I
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|D2L
init|=
name|Const
operator|.
name|D2L
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|D2F
init|=
name|Const
operator|.
name|D2F
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|I2B
init|=
name|Const
operator|.
name|I2B
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|INT2BYTE
init|=
name|Const
operator|.
name|INT2BYTE
decl_stmt|;
comment|// Old notion
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|I2C
init|=
name|Const
operator|.
name|I2C
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|INT2CHAR
init|=
name|Const
operator|.
name|INT2CHAR
decl_stmt|;
comment|// Old notion
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|I2S
init|=
name|Const
operator|.
name|I2S
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|INT2SHORT
init|=
name|Const
operator|.
name|INT2SHORT
decl_stmt|;
comment|// Old notion
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|LCMP
init|=
name|Const
operator|.
name|LCMP
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|FCMPL
init|=
name|Const
operator|.
name|FCMPL
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|FCMPG
init|=
name|Const
operator|.
name|FCMPG
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|DCMPL
init|=
name|Const
operator|.
name|DCMPL
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|DCMPG
init|=
name|Const
operator|.
name|DCMPG
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|IFEQ
init|=
name|Const
operator|.
name|IFEQ
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|IFNE
init|=
name|Const
operator|.
name|IFNE
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|IFLT
init|=
name|Const
operator|.
name|IFLT
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|IFGE
init|=
name|Const
operator|.
name|IFGE
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|IFGT
init|=
name|Const
operator|.
name|IFGT
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|IFLE
init|=
name|Const
operator|.
name|IFLE
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|IF_ICMPEQ
init|=
name|Const
operator|.
name|IF_ICMPEQ
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|IF_ICMPNE
init|=
name|Const
operator|.
name|IF_ICMPNE
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|IF_ICMPLT
init|=
name|Const
operator|.
name|IF_ICMPLT
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|IF_ICMPGE
init|=
name|Const
operator|.
name|IF_ICMPGE
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|IF_ICMPGT
init|=
name|Const
operator|.
name|IF_ICMPGT
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|IF_ICMPLE
init|=
name|Const
operator|.
name|IF_ICMPLE
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|IF_ACMPEQ
init|=
name|Const
operator|.
name|IF_ACMPEQ
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|IF_ACMPNE
init|=
name|Const
operator|.
name|IF_ACMPNE
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|GOTO
init|=
name|Const
operator|.
name|GOTO
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|JSR
init|=
name|Const
operator|.
name|JSR
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|RET
init|=
name|Const
operator|.
name|RET
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|TABLESWITCH
init|=
name|Const
operator|.
name|TABLESWITCH
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|LOOKUPSWITCH
init|=
name|Const
operator|.
name|LOOKUPSWITCH
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|IRETURN
init|=
name|Const
operator|.
name|IRETURN
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|LRETURN
init|=
name|Const
operator|.
name|LRETURN
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|FRETURN
init|=
name|Const
operator|.
name|FRETURN
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|DRETURN
init|=
name|Const
operator|.
name|DRETURN
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|ARETURN
init|=
name|Const
operator|.
name|ARETURN
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|RETURN
init|=
name|Const
operator|.
name|RETURN
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|GETSTATIC
init|=
name|Const
operator|.
name|GETSTATIC
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|PUTSTATIC
init|=
name|Const
operator|.
name|PUTSTATIC
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|GETFIELD
init|=
name|Const
operator|.
name|GETFIELD
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|PUTFIELD
init|=
name|Const
operator|.
name|PUTFIELD
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|INVOKEVIRTUAL
init|=
name|Const
operator|.
name|INVOKEVIRTUAL
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|INVOKESPECIAL
init|=
name|Const
operator|.
name|INVOKESPECIAL
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|INVOKENONVIRTUAL
init|=
name|Const
operator|.
name|INVOKENONVIRTUAL
decl_stmt|;
comment|// Old name in JDK 1.0
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|INVOKESTATIC
init|=
name|Const
operator|.
name|INVOKESTATIC
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|INVOKEINTERFACE
init|=
name|Const
operator|.
name|INVOKEINTERFACE
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|INVOKEDYNAMIC
init|=
name|Const
operator|.
name|INVOKEDYNAMIC
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|NEW
init|=
name|Const
operator|.
name|NEW
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|NEWARRAY
init|=
name|Const
operator|.
name|NEWARRAY
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|ANEWARRAY
init|=
name|Const
operator|.
name|ANEWARRAY
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|ARRAYLENGTH
init|=
name|Const
operator|.
name|ARRAYLENGTH
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|ATHROW
init|=
name|Const
operator|.
name|ATHROW
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|CHECKCAST
init|=
name|Const
operator|.
name|CHECKCAST
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|INSTANCEOF
init|=
name|Const
operator|.
name|INSTANCEOF
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|MONITORENTER
init|=
name|Const
operator|.
name|MONITORENTER
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|MONITOREXIT
init|=
name|Const
operator|.
name|MONITOREXIT
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|WIDE
init|=
name|Const
operator|.
name|WIDE
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|MULTIANEWARRAY
init|=
name|Const
operator|.
name|MULTIANEWARRAY
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|IFNULL
init|=
name|Const
operator|.
name|IFNULL
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|IFNONNULL
init|=
name|Const
operator|.
name|IFNONNULL
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|GOTO_W
init|=
name|Const
operator|.
name|GOTO_W
decl_stmt|;
comment|/**      * Java VM opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"> Opcode definitions in The      *      Java Virtual Machine Specification</a>      */
name|short
name|JSR_W
init|=
name|Const
operator|.
name|JSR_W
decl_stmt|;
comment|/**      * JVM internal opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.2"> Reserved opcodes in the Java      *      Virtual Machine Specification</a>      */
name|short
name|BREAKPOINT
init|=
name|Const
operator|.
name|BREAKPOINT
decl_stmt|;
comment|/**      * JVM internal opcode.      *      * @see<a href=      *      "https://web.archive.org/web/20120108031230/http://java.sun.com/docs/books/jvms/first_edition/html/Quick.doc.html">      *      Specification of _quick opcodes in the Java Virtual Machine Specification (version 1)</a>      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se5.0/html/ChangesAppendix.doc.html#448885"> Why the _quick      *      opcodes were removed from the second version of the Java Virtual Machine Specification.</a>      */
name|short
name|LDC_QUICK
init|=
name|Const
operator|.
name|LDC_QUICK
decl_stmt|;
comment|/**      * JVM internal opcode.      *      * @see<a href=      *      "https://web.archive.org/web/20120108031230/http://java.sun.com/docs/books/jvms/first_edition/html/Quick.doc.html">      *      Specification of _quick opcodes in the Java Virtual Machine Specification (version 1)</a>      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se5.0/html/ChangesAppendix.doc.html#448885"> Why the _quick      *      opcodes were removed from the second version of the Java Virtual Machine Specification.</a>      */
name|short
name|LDC_W_QUICK
init|=
name|Const
operator|.
name|LDC_W_QUICK
decl_stmt|;
comment|/**      * JVM internal opcode.      *      * @see<a href=      *      "https://web.archive.org/web/20120108031230/http://java.sun.com/docs/books/jvms/first_edition/html/Quick.doc.html">      *      Specification of _quick opcodes in the Java Virtual Machine Specification (version 1)</a>      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se5.0/html/ChangesAppendix.doc.html#448885"> Why the _quick      *      opcodes were removed from the second version of the Java Virtual Machine Specification.</a>      */
name|short
name|LDC2_W_QUICK
init|=
name|Const
operator|.
name|LDC2_W_QUICK
decl_stmt|;
comment|/**      * JVM internal opcode.      *      * @see<a href=      *      "https://web.archive.org/web/20120108031230/http://java.sun.com/docs/books/jvms/first_edition/html/Quick.doc.html">      *      Specification of _quick opcodes in the Java Virtual Machine Specification (version 1)</a>      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se5.0/html/ChangesAppendix.doc.html#448885"> Why the _quick      *      opcodes were removed from the second version of the Java Virtual Machine Specification.</a>      */
name|short
name|GETFIELD_QUICK
init|=
name|Const
operator|.
name|GETFIELD_QUICK
decl_stmt|;
comment|/**      * JVM internal opcode.      *      * @see<a href=      *      "https://web.archive.org/web/20120108031230/http://java.sun.com/docs/books/jvms/first_edition/html/Quick.doc.html">      *      Specification of _quick opcodes in the Java Virtual Machine Specification (version 1)</a>      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se5.0/html/ChangesAppendix.doc.html#448885"> Why the _quick      *      opcodes were removed from the second version of the Java Virtual Machine Specification.</a>      */
name|short
name|PUTFIELD_QUICK
init|=
name|Const
operator|.
name|PUTFIELD_QUICK
decl_stmt|;
comment|/**      * JVM internal opcode.      *      * @see<a href=      *      "https://web.archive.org/web/20120108031230/http://java.sun.com/docs/books/jvms/first_edition/html/Quick.doc.html">      *      Specification of _quick opcodes in the Java Virtual Machine Specification (version 1)</a>      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se5.0/html/ChangesAppendix.doc.html#448885"> Why the _quick      *      opcodes were removed from the second version of the Java Virtual Machine Specification.</a>      */
name|short
name|GETFIELD2_QUICK
init|=
name|Const
operator|.
name|GETFIELD2_QUICK
decl_stmt|;
comment|/**      * JVM internal opcode.      *      * @see<a href=      *      "https://web.archive.org/web/20120108031230/http://java.sun.com/docs/books/jvms/first_edition/html/Quick.doc.html">      *      Specification of _quick opcodes in the Java Virtual Machine Specification (version 1)</a>      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se5.0/html/ChangesAppendix.doc.html#448885"> Why the _quick      *      opcodes were removed from the second version of the Java Virtual Machine Specification.</a>      */
name|short
name|PUTFIELD2_QUICK
init|=
name|Const
operator|.
name|PUTFIELD2_QUICK
decl_stmt|;
comment|/**      * JVM internal opcode.      *      * @see<a href=      *      "https://web.archive.org/web/20120108031230/http://java.sun.com/docs/books/jvms/first_edition/html/Quick.doc.html">      *      Specification of _quick opcodes in the Java Virtual Machine Specification (version 1)</a>      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se5.0/html/ChangesAppendix.doc.html#448885"> Why the _quick      *      opcodes were removed from the second version of the Java Virtual Machine Specification.</a>      */
name|short
name|GETSTATIC_QUICK
init|=
name|Const
operator|.
name|GETSTATIC_QUICK
decl_stmt|;
comment|/**      * JVM internal opcode.      *      * @see<a href=      *      "https://web.archive.org/web/20120108031230/http://java.sun.com/docs/books/jvms/first_edition/html/Quick.doc.html">      *      Specification of _quick opcodes in the Java Virtual Machine Specification (version 1)</a>      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se5.0/html/ChangesAppendix.doc.html#448885"> Why the _quick      *      opcodes were removed from the second version of the Java Virtual Machine Specification.</a>      */
name|short
name|PUTSTATIC_QUICK
init|=
name|Const
operator|.
name|PUTSTATIC_QUICK
decl_stmt|;
comment|/**      * JVM internal opcode.      *      * @see<a href=      *      "https://web.archive.org/web/20120108031230/http://java.sun.com/docs/books/jvms/first_edition/html/Quick.doc.html">      *      Specification of _quick opcodes in the Java Virtual Machine Specification (version 1)</a>      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se5.0/html/ChangesAppendix.doc.html#448885"> Why the _quick      *      opcodes were removed from the second version of the Java Virtual Machine Specification.</a>      */
name|short
name|GETSTATIC2_QUICK
init|=
name|Const
operator|.
name|GETSTATIC2_QUICK
decl_stmt|;
comment|/**      * JVM internal opcode.      *      * @see<a href=      *      "https://web.archive.org/web/20120108031230/http://java.sun.com/docs/books/jvms/first_edition/html/Quick.doc.html">      *      Specification of _quick opcodes in the Java Virtual Machine Specification (version 1)</a>      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se5.0/html/ChangesAppendix.doc.html#448885"> Why the _quick      *      opcodes were removed from the second version of the Java Virtual Machine Specification.</a>      */
name|short
name|PUTSTATIC2_QUICK
init|=
name|Const
operator|.
name|PUTSTATIC2_QUICK
decl_stmt|;
comment|/**      * JVM internal opcode.      *      * @see<a href=      *      "https://web.archive.org/web/20120108031230/http://java.sun.com/docs/books/jvms/first_edition/html/Quick.doc.html">      *      Specification of _quick opcodes in the Java Virtual Machine Specification (version 1)</a>      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se5.0/html/ChangesAppendix.doc.html#448885"> Why the _quick      *      opcodes were removed from the second version of the Java Virtual Machine Specification.</a>      */
name|short
name|INVOKEVIRTUAL_QUICK
init|=
name|Const
operator|.
name|INVOKEVIRTUAL_QUICK
decl_stmt|;
comment|/**      * JVM internal opcode.      *      * @see<a href=      *      "https://web.archive.org/web/20120108031230/http://java.sun.com/docs/books/jvms/first_edition/html/Quick.doc.html">      *      Specification of _quick opcodes in the Java Virtual Machine Specification (version 1)</a>      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se5.0/html/ChangesAppendix.doc.html#448885"> Why the _quick      *      opcodes were removed from the second version of the Java Virtual Machine Specification.</a>      */
name|short
name|INVOKENONVIRTUAL_QUICK
init|=
name|Const
operator|.
name|INVOKENONVIRTUAL_QUICK
decl_stmt|;
comment|/**      * JVM internal opcode.      *      * @see<a href=      *      "https://web.archive.org/web/20120108031230/http://java.sun.com/docs/books/jvms/first_edition/html/Quick.doc.html">      *      Specification of _quick opcodes in the Java Virtual Machine Specification (version 1)</a>      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se5.0/html/ChangesAppendix.doc.html#448885"> Why the _quick      *      opcodes were removed from the second version of the Java Virtual Machine Specification.</a>      */
name|short
name|INVOKESUPER_QUICK
init|=
name|Const
operator|.
name|INVOKESUPER_QUICK
decl_stmt|;
comment|/**      * JVM internal opcode.      *      * @see<a href=      *      "https://web.archive.org/web/20120108031230/http://java.sun.com/docs/books/jvms/first_edition/html/Quick.doc.html">      *      Specification of _quick opcodes in the Java Virtual Machine Specification (version 1)</a>      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se5.0/html/ChangesAppendix.doc.html#448885"> Why the _quick      *      opcodes were removed from the second version of the Java Virtual Machine Specification.</a>      */
name|short
name|INVOKESTATIC_QUICK
init|=
name|Const
operator|.
name|INVOKESTATIC_QUICK
decl_stmt|;
comment|/**      * JVM internal opcode.      *      * @see<a href=      *      "https://web.archive.org/web/20120108031230/http://java.sun.com/docs/books/jvms/first_edition/html/Quick.doc.html">      *      Specification of _quick opcodes in the Java Virtual Machine Specification (version 1)</a>      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se5.0/html/ChangesAppendix.doc.html#448885"> Why the _quick      *      opcodes were removed from the second version of the Java Virtual Machine Specification.</a>      */
name|short
name|INVOKEINTERFACE_QUICK
init|=
name|Const
operator|.
name|INVOKEINTERFACE_QUICK
decl_stmt|;
comment|/**      * JVM internal opcode.      *      * @see<a href=      *      "https://web.archive.org/web/20120108031230/http://java.sun.com/docs/books/jvms/first_edition/html/Quick.doc.html">      *      Specification of _quick opcodes in the Java Virtual Machine Specification (version 1)</a>      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se5.0/html/ChangesAppendix.doc.html#448885"> Why the _quick      *      opcodes were removed from the second version of the Java Virtual Machine Specification.</a>      */
name|short
name|INVOKEVIRTUALOBJECT_QUICK
init|=
name|Const
operator|.
name|INVOKEVIRTUALOBJECT_QUICK
decl_stmt|;
comment|/**      * JVM internal opcode.      *      * @see<a href=      *      "https://web.archive.org/web/20120108031230/http://java.sun.com/docs/books/jvms/first_edition/html/Quick.doc.html">      *      Specification of _quick opcodes in the Java Virtual Machine Specification (version 1)</a>      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se5.0/html/ChangesAppendix.doc.html#448885"> Why the _quick      *      opcodes were removed from the second version of the Java Virtual Machine Specification.</a>      */
name|short
name|NEW_QUICK
init|=
name|Const
operator|.
name|NEW_QUICK
decl_stmt|;
comment|/**      * JVM internal opcode.      *      * @see<a href=      *      "https://web.archive.org/web/20120108031230/http://java.sun.com/docs/books/jvms/first_edition/html/Quick.doc.html">      *      Specification of _quick opcodes in the Java Virtual Machine Specification (version 1)</a>      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se5.0/html/ChangesAppendix.doc.html#448885"> Why the _quick      *      opcodes were removed from the second version of the Java Virtual Machine Specification.</a>      */
name|short
name|ANEWARRAY_QUICK
init|=
name|Const
operator|.
name|ANEWARRAY_QUICK
decl_stmt|;
comment|/**      * JVM internal opcode.      *      * @see<a href=      *      "https://web.archive.org/web/20120108031230/http://java.sun.com/docs/books/jvms/first_edition/html/Quick.doc.html">      *      Specification of _quick opcodes in the Java Virtual Machine Specification (version 1)</a>      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se5.0/html/ChangesAppendix.doc.html#448885"> Why the _quick      *      opcodes were removed from the second version of the Java Virtual Machine Specification.</a>      */
name|short
name|MULTIANEWARRAY_QUICK
init|=
name|Const
operator|.
name|MULTIANEWARRAY_QUICK
decl_stmt|;
comment|/**      * JVM internal opcode.      *      * @see<a href=      *      "https://web.archive.org/web/20120108031230/http://java.sun.com/docs/books/jvms/first_edition/html/Quick.doc.html">      *      Specification of _quick opcodes in the Java Virtual Machine Specification (version 1)</a>      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se5.0/html/ChangesAppendix.doc.html#448885"> Why the _quick      *      opcodes were removed from the second version of the Java Virtual Machine Specification.</a>      */
name|short
name|CHECKCAST_QUICK
init|=
name|Const
operator|.
name|CHECKCAST_QUICK
decl_stmt|;
comment|/**      * JVM internal opcode.      *      * @see<a href=      *      "https://web.archive.org/web/20120108031230/http://java.sun.com/docs/books/jvms/first_edition/html/Quick.doc.html">      *      Specification of _quick opcodes in the Java Virtual Machine Specification (version 1)</a>      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se5.0/html/ChangesAppendix.doc.html#448885"> Why the _quick      *      opcodes were removed from the second version of the Java Virtual Machine Specification.</a>      */
name|short
name|INSTANCEOF_QUICK
init|=
name|Const
operator|.
name|INSTANCEOF_QUICK
decl_stmt|;
comment|/**      * JVM internal opcode.      *      * @see<a href=      *      "https://web.archive.org/web/20120108031230/http://java.sun.com/docs/books/jvms/first_edition/html/Quick.doc.html">      *      Specification of _quick opcodes in the Java Virtual Machine Specification (version 1)</a>      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se5.0/html/ChangesAppendix.doc.html#448885"> Why the _quick      *      opcodes were removed from the second version of the Java Virtual Machine Specification.</a>      */
name|short
name|INVOKEVIRTUAL_QUICK_W
init|=
name|Const
operator|.
name|INVOKEVIRTUAL_QUICK_W
decl_stmt|;
comment|/**      * JVM internal opcode.      *      * @see<a href=      *      "https://web.archive.org/web/20120108031230/http://java.sun.com/docs/books/jvms/first_edition/html/Quick.doc.html">      *      Specification of _quick opcodes in the Java Virtual Machine Specification (version 1)</a>      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se5.0/html/ChangesAppendix.doc.html#448885"> Why the _quick      *      opcodes were removed from the second version of the Java Virtual Machine Specification.</a>      */
name|short
name|GETFIELD_QUICK_W
init|=
name|Const
operator|.
name|GETFIELD_QUICK_W
decl_stmt|;
comment|/**      * JVM internal opcode.      *      * @see<a href=      *      "https://web.archive.org/web/20120108031230/http://java.sun.com/docs/books/jvms/first_edition/html/Quick.doc.html">      *      Specification of _quick opcodes in the Java Virtual Machine Specification (version 1)</a>      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se5.0/html/ChangesAppendix.doc.html#448885"> Why the _quick      *      opcodes were removed from the second version of the Java Virtual Machine Specification.</a>      */
name|short
name|PUTFIELD_QUICK_W
init|=
name|Const
operator|.
name|PUTFIELD_QUICK_W
decl_stmt|;
comment|/**      * JVM internal opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.2"> Reserved opcodes in the Java      *      Virtual Machine Specification</a>      */
name|short
name|IMPDEP1
init|=
name|Const
operator|.
name|IMPDEP1
decl_stmt|;
comment|/**      * JVM internal opcode.      *      * @see<a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.2"> Reserved opcodes in the Java      *      Virtual Machine Specification</a>      */
name|short
name|IMPDEP2
init|=
name|Const
operator|.
name|IMPDEP2
decl_stmt|;
comment|/**      * BCEL virtual instruction for pushing an arbitrary data type onto the stack. Will be converted to the appropriate JVM      * opcode when the class is dumped.      */
name|short
name|PUSH
init|=
name|Const
operator|.
name|PUSH
decl_stmt|;
comment|/**      * BCEL virtual instruction for either LOOKUPSWITCH or TABLESWITCH. Will be converted to the appropriate JVM opcode when      * the class is dumped.      */
name|short
name|SWITCH
init|=
name|Const
operator|.
name|SWITCH
decl_stmt|;
comment|/** Illegal opcode. */
name|short
name|UNDEFINED
init|=
name|Const
operator|.
name|UNDEFINED
decl_stmt|;
comment|/** Illegal opcode. */
name|short
name|UNPREDICTABLE
init|=
name|Const
operator|.
name|UNPREDICTABLE
decl_stmt|;
comment|/** Illegal opcode. */
name|short
name|RESERVED
init|=
name|Const
operator|.
name|RESERVED
decl_stmt|;
comment|/** Mnemonic for an illegal opcode. */
name|String
name|ILLEGAL_OPCODE
init|=
name|Const
operator|.
name|ILLEGAL_OPCODE
decl_stmt|;
comment|/** Mnemonic for an illegal type. */
name|String
name|ILLEGAL_TYPE
init|=
name|Const
operator|.
name|ILLEGAL_TYPE
decl_stmt|;
comment|/** Boolean data type. */
name|byte
name|T_BOOLEAN
init|=
name|Const
operator|.
name|T_BOOLEAN
decl_stmt|;
comment|/** Char data type. */
name|byte
name|T_CHAR
init|=
name|Const
operator|.
name|T_CHAR
decl_stmt|;
comment|/** Float data type. */
name|byte
name|T_FLOAT
init|=
name|Const
operator|.
name|T_FLOAT
decl_stmt|;
comment|/** Double data type. */
name|byte
name|T_DOUBLE
init|=
name|Const
operator|.
name|T_DOUBLE
decl_stmt|;
comment|/** Byte data type. */
name|byte
name|T_BYTE
init|=
name|Const
operator|.
name|T_BYTE
decl_stmt|;
comment|/** Short data type. */
name|byte
name|T_SHORT
init|=
name|Const
operator|.
name|T_SHORT
decl_stmt|;
comment|/** Int data type. */
name|byte
name|T_INT
init|=
name|Const
operator|.
name|T_INT
decl_stmt|;
comment|/** Long data type. */
name|byte
name|T_LONG
init|=
name|Const
operator|.
name|T_LONG
decl_stmt|;
comment|/** Void data type (non-standard). */
name|byte
name|T_VOID
init|=
name|Const
operator|.
name|T_VOID
decl_stmt|;
comment|// Non-standard
comment|/** Array data type. */
name|byte
name|T_ARRAY
init|=
name|Const
operator|.
name|T_ARRAY
decl_stmt|;
comment|/** Object data type. */
name|byte
name|T_OBJECT
init|=
name|Const
operator|.
name|T_OBJECT
decl_stmt|;
comment|/** Reference data type (deprecated). */
name|byte
name|T_REFERENCE
init|=
name|Const
operator|.
name|T_REFERENCE
decl_stmt|;
comment|// Deprecated
comment|/** Unknown data type. */
name|byte
name|T_UNKNOWN
init|=
name|Const
operator|.
name|T_UNKNOWN
decl_stmt|;
comment|/** Address data type. */
name|byte
name|T_ADDRESS
init|=
name|Const
operator|.
name|T_ADDRESS
decl_stmt|;
comment|/**      * The primitive type names corresponding to the T_XX constants, e.g., TYPE_NAMES[T_INT] = "int"      */
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
name|short
index|[]
name|NO_OF_OPERANDS
init|=
name|Const
operator|.
name|NO_OF_OPERANDS
decl_stmt|;
comment|/**      * How the byte code operands are to be interpreted for each opcode. Indexed by opcode. TYPE_OF_OPERANDS[ILOAD] = an      * array of shorts describing the data types for the instruction.      */
name|short
index|[]
index|[]
name|TYPE_OF_OPERANDS
init|=
name|Const
operator|.
name|TYPE_OF_OPERANDS
decl_stmt|;
comment|/**      * Names of opcodes. Indexed by opcode. OPCODE_NAMES[ALOAD] = "aload".      */
name|String
index|[]
name|OPCODE_NAMES
init|=
name|Const
operator|.
name|OPCODE_NAMES
decl_stmt|;
comment|/**      * Number of words consumed on operand stack by instructions. Indexed by opcode. CONSUME_STACK[FALOAD] = number of words      * consumed from the stack by a faload instruction.      */
name|int
index|[]
name|CONSUME_STACK
init|=
name|Const
operator|.
name|CONSUME_STACK
decl_stmt|;
comment|/**      * Number of words produced onto operand stack by instructions. Indexed by opcode. CONSUME_STACK[DALOAD] = number of      * words consumed from the stack by a daload instruction.      */
name|int
index|[]
name|PRODUCE_STACK
init|=
name|Const
operator|.
name|PRODUCE_STACK
decl_stmt|;
comment|/**      * Attributes and their corresponding names.      */
name|byte
name|ATTR_UNKNOWN
init|=
name|Const
operator|.
name|ATTR_UNKNOWN
decl_stmt|;
name|byte
name|ATTR_SOURCE_FILE
init|=
name|Const
operator|.
name|ATTR_SOURCE_FILE
decl_stmt|;
name|byte
name|ATTR_CONSTANT_VALUE
init|=
name|Const
operator|.
name|ATTR_CONSTANT_VALUE
decl_stmt|;
name|byte
name|ATTR_CODE
init|=
name|Const
operator|.
name|ATTR_CODE
decl_stmt|;
name|byte
name|ATTR_EXCEPTIONS
init|=
name|Const
operator|.
name|ATTR_EXCEPTIONS
decl_stmt|;
name|byte
name|ATTR_LINE_NUMBER_TABLE
init|=
name|Const
operator|.
name|ATTR_LINE_NUMBER_TABLE
decl_stmt|;
name|byte
name|ATTR_LOCAL_VARIABLE_TABLE
init|=
name|Const
operator|.
name|ATTR_LOCAL_VARIABLE_TABLE
decl_stmt|;
name|byte
name|ATTR_INNER_CLASSES
init|=
name|Const
operator|.
name|ATTR_INNER_CLASSES
decl_stmt|;
name|byte
name|ATTR_SYNTHETIC
init|=
name|Const
operator|.
name|ATTR_SYNTHETIC
decl_stmt|;
name|byte
name|ATTR_DEPRECATED
init|=
name|Const
operator|.
name|ATTR_DEPRECATED
decl_stmt|;
name|byte
name|ATTR_PMG
init|=
name|Const
operator|.
name|ATTR_PMG
decl_stmt|;
name|byte
name|ATTR_SIGNATURE
init|=
name|Const
operator|.
name|ATTR_SIGNATURE
decl_stmt|;
name|byte
name|ATTR_STACK_MAP
init|=
name|Const
operator|.
name|ATTR_STACK_MAP
decl_stmt|;
name|byte
name|ATTR_RUNTIMEVISIBLE_ANNOTATIONS
init|=
literal|12
decl_stmt|;
name|byte
name|ATTR_RUNTIMEINVISIBLE_ANNOTATIONS
init|=
literal|13
decl_stmt|;
name|byte
name|ATTR_RUNTIMEVISIBLE_PARAMETER_ANNOTATIONS
init|=
literal|14
decl_stmt|;
name|byte
name|ATTR_RUNTIMEINVISIBLE_PARAMETER_ANNOTATIONS
init|=
literal|15
decl_stmt|;
name|byte
name|ATTR_ANNOTATION_DEFAULT
init|=
literal|16
decl_stmt|;
name|short
name|KNOWN_ATTRIBUTES
init|=
literal|12
decl_stmt|;
comment|// should be 17
comment|// TODO: mutable public array!!
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
block|}
decl_stmt|;
comment|/**      * Constants used in the StackMap attribute.      */
name|byte
name|ITEM_Bogus
init|=
name|Const
operator|.
name|ITEM_Bogus
decl_stmt|;
name|byte
name|ITEM_Integer
init|=
name|Const
operator|.
name|ITEM_Integer
decl_stmt|;
name|byte
name|ITEM_Float
init|=
name|Const
operator|.
name|ITEM_Float
decl_stmt|;
name|byte
name|ITEM_Double
init|=
name|Const
operator|.
name|ITEM_Double
decl_stmt|;
name|byte
name|ITEM_Long
init|=
name|Const
operator|.
name|ITEM_Long
decl_stmt|;
name|byte
name|ITEM_Null
init|=
name|Const
operator|.
name|ITEM_Null
decl_stmt|;
name|byte
name|ITEM_InitObject
init|=
name|Const
operator|.
name|ITEM_InitObject
decl_stmt|;
name|byte
name|ITEM_Object
init|=
name|Const
operator|.
name|ITEM_Object
decl_stmt|;
name|byte
name|ITEM_NewObject
init|=
name|Const
operator|.
name|ITEM_NewObject
decl_stmt|;
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
block|}
end_interface

end_unit

