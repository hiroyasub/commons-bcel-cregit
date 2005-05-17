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
name|util
package|;
end_package

begin_import
import|import
name|java
operator|.
name|io
operator|.
name|OutputStream
import|;
end_import

begin_import
import|import
name|java
operator|.
name|io
operator|.
name|PrintWriter
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

begin_import
import|import
name|org
operator|.
name|apache
operator|.
name|bcel
operator|.
name|Repository
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
name|ClassParser
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
name|Utility
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
name|generic
operator|.
name|ArrayType
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
name|generic
operator|.
name|ConstantPoolGen
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
name|generic
operator|.
name|MethodGen
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
name|generic
operator|.
name|Type
import|;
end_import

begin_comment
comment|/**   * This class takes a given JavaClass object and converts it to a  * Java program that creates that very class using BCEL. This  * gives new users of BCEL a useful example showing how things  * are done with BCEL. It does not cover all features of BCEL,  * but tries to mimic hand-written code as close as possible.  *  * @version $Id$  * @author<A HREF="mailto:m.dahm@gmx.de">M. Dahm</A>   */
end_comment

begin_class
specifier|public
class|class
name|BCELifier
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
specifier|private
specifier|static
specifier|final
name|int
name|FLAG_FOR_UNKNOWN
init|=
operator|-
literal|1
decl_stmt|;
specifier|private
specifier|static
specifier|final
name|int
name|FLAG_FOR_CLASS
init|=
literal|0
decl_stmt|;
specifier|private
specifier|static
specifier|final
name|int
name|FLAG_FOR_METHOD
init|=
literal|1
decl_stmt|;
specifier|private
name|JavaClass
name|_clazz
decl_stmt|;
specifier|private
name|PrintWriter
name|_out
decl_stmt|;
specifier|private
name|ConstantPoolGen
name|_cp
decl_stmt|;
comment|/** @param clazz Java class to "decompile"    * @param out where to output Java program    */
specifier|public
name|BCELifier
parameter_list|(
name|JavaClass
name|clazz
parameter_list|,
name|OutputStream
name|out
parameter_list|)
block|{
name|_clazz
operator|=
name|clazz
expr_stmt|;
name|_out
operator|=
operator|new
name|PrintWriter
argument_list|(
name|out
argument_list|)
expr_stmt|;
name|_cp
operator|=
operator|new
name|ConstantPoolGen
argument_list|(
name|_clazz
operator|.
name|getConstantPool
argument_list|()
argument_list|)
expr_stmt|;
block|}
comment|/** Start Java code generation    */
specifier|public
name|void
name|start
parameter_list|()
block|{
name|visitJavaClass
argument_list|(
name|_clazz
argument_list|)
expr_stmt|;
name|_out
operator|.
name|flush
argument_list|()
expr_stmt|;
block|}
specifier|public
name|void
name|visitJavaClass
parameter_list|(
name|JavaClass
name|clazz
parameter_list|)
block|{
name|String
name|class_name
init|=
name|clazz
operator|.
name|getClassName
argument_list|()
decl_stmt|;
name|String
name|super_name
init|=
name|clazz
operator|.
name|getSuperclassName
argument_list|()
decl_stmt|;
name|String
name|package_name
init|=
name|clazz
operator|.
name|getPackageName
argument_list|()
decl_stmt|;
name|String
name|inter
init|=
name|Utility
operator|.
name|printArray
argument_list|(
name|clazz
operator|.
name|getInterfaceNames
argument_list|()
argument_list|,
literal|false
argument_list|,
literal|true
argument_list|)
decl_stmt|;
if|if
condition|(
operator|!
literal|""
operator|.
name|equals
argument_list|(
name|package_name
argument_list|)
condition|)
block|{
name|class_name
operator|=
name|class_name
operator|.
name|substring
argument_list|(
name|package_name
operator|.
name|length
argument_list|()
operator|+
literal|1
argument_list|)
expr_stmt|;
name|_out
operator|.
name|println
argument_list|(
literal|"package "
operator|+
name|package_name
operator|+
literal|";"
argument_list|)
expr_stmt|;
name|_out
operator|.
name|println
argument_list|()
expr_stmt|;
block|}
name|_out
operator|.
name|println
argument_list|(
literal|"import org.apache.bcel.generic.*;"
argument_list|)
expr_stmt|;
name|_out
operator|.
name|println
argument_list|(
literal|"import org.apache.bcel.classfile.*;"
argument_list|)
expr_stmt|;
name|_out
operator|.
name|println
argument_list|(
literal|"import org.apache.bcel.*;"
argument_list|)
expr_stmt|;
name|_out
operator|.
name|println
argument_list|(
literal|"import java.io.*;"
argument_list|)
expr_stmt|;
name|_out
operator|.
name|println
argument_list|()
expr_stmt|;
name|_out
operator|.
name|println
argument_list|(
literal|"public class "
operator|+
name|class_name
operator|+
literal|"Creator implements Constants {"
argument_list|)
expr_stmt|;
name|_out
operator|.
name|println
argument_list|(
literal|"  private InstructionFactory _factory;"
argument_list|)
expr_stmt|;
name|_out
operator|.
name|println
argument_list|(
literal|"  private ConstantPoolGen    _cp;"
argument_list|)
expr_stmt|;
name|_out
operator|.
name|println
argument_list|(
literal|"  private ClassGen           _cg;"
argument_list|)
expr_stmt|;
name|_out
operator|.
name|println
argument_list|()
expr_stmt|;
name|_out
operator|.
name|println
argument_list|(
literal|"  public "
operator|+
name|class_name
operator|+
literal|"Creator() {"
argument_list|)
expr_stmt|;
name|_out
operator|.
name|println
argument_list|(
literal|"    _cg = new ClassGen(\""
operator|+
operator|(
operator|(
literal|""
operator|.
name|equals
argument_list|(
name|package_name
argument_list|)
operator|)
condition|?
name|class_name
else|:
name|package_name
operator|+
literal|"."
operator|+
name|class_name
operator|)
operator|+
literal|"\", \""
operator|+
name|super_name
operator|+
literal|"\", "
operator|+
literal|"\""
operator|+
name|clazz
operator|.
name|getSourceFileName
argument_list|()
operator|+
literal|"\", "
operator|+
name|printFlags
argument_list|(
name|clazz
operator|.
name|getAccessFlags
argument_list|()
argument_list|,
name|FLAG_FOR_CLASS
argument_list|)
operator|+
literal|", "
operator|+
literal|"new String[] { "
operator|+
name|inter
operator|+
literal|" });"
argument_list|)
expr_stmt|;
name|_out
operator|.
name|println
argument_list|()
expr_stmt|;
name|_out
operator|.
name|println
argument_list|(
literal|"    _cp = _cg.getConstantPool();"
argument_list|)
expr_stmt|;
name|_out
operator|.
name|println
argument_list|(
literal|"    _factory = new InstructionFactory(_cg, _cp);"
argument_list|)
expr_stmt|;
name|_out
operator|.
name|println
argument_list|(
literal|"  }"
argument_list|)
expr_stmt|;
name|_out
operator|.
name|println
argument_list|()
expr_stmt|;
name|printCreate
argument_list|()
expr_stmt|;
name|Field
index|[]
name|fields
init|=
name|clazz
operator|.
name|getFields
argument_list|()
decl_stmt|;
if|if
condition|(
name|fields
operator|.
name|length
operator|>
literal|0
condition|)
block|{
name|_out
operator|.
name|println
argument_list|(
literal|"  private void createFields() {"
argument_list|)
expr_stmt|;
name|_out
operator|.
name|println
argument_list|(
literal|"    FieldGen field;"
argument_list|)
expr_stmt|;
for|for
control|(
name|int
name|i
init|=
literal|0
init|;
name|i
operator|<
name|fields
operator|.
name|length
condition|;
name|i
operator|++
control|)
block|{
name|fields
index|[
name|i
index|]
operator|.
name|accept
argument_list|(
name|this
argument_list|)
expr_stmt|;
block|}
name|_out
operator|.
name|println
argument_list|(
literal|"  }"
argument_list|)
expr_stmt|;
name|_out
operator|.
name|println
argument_list|()
expr_stmt|;
block|}
name|Method
index|[]
name|methods
init|=
name|clazz
operator|.
name|getMethods
argument_list|()
decl_stmt|;
for|for
control|(
name|int
name|i
init|=
literal|0
init|;
name|i
operator|<
name|methods
operator|.
name|length
condition|;
name|i
operator|++
control|)
block|{
name|_out
operator|.
name|println
argument_list|(
literal|"  private void createMethod_"
operator|+
name|i
operator|+
literal|"() {"
argument_list|)
expr_stmt|;
name|methods
index|[
name|i
index|]
operator|.
name|accept
argument_list|(
name|this
argument_list|)
expr_stmt|;
name|_out
operator|.
name|println
argument_list|(
literal|"  }"
argument_list|)
expr_stmt|;
name|_out
operator|.
name|println
argument_list|()
expr_stmt|;
block|}
name|printMain
argument_list|()
expr_stmt|;
name|_out
operator|.
name|println
argument_list|(
literal|"}"
argument_list|)
expr_stmt|;
block|}
specifier|private
name|void
name|printCreate
parameter_list|()
block|{
name|_out
operator|.
name|println
argument_list|(
literal|"  public void create(OutputStream out) throws IOException {"
argument_list|)
expr_stmt|;
name|Field
index|[]
name|fields
init|=
name|_clazz
operator|.
name|getFields
argument_list|()
decl_stmt|;
if|if
condition|(
name|fields
operator|.
name|length
operator|>
literal|0
condition|)
block|{
name|_out
operator|.
name|println
argument_list|(
literal|"    createFields();"
argument_list|)
expr_stmt|;
block|}
name|Method
index|[]
name|methods
init|=
name|_clazz
operator|.
name|getMethods
argument_list|()
decl_stmt|;
for|for
control|(
name|int
name|i
init|=
literal|0
init|;
name|i
operator|<
name|methods
operator|.
name|length
condition|;
name|i
operator|++
control|)
block|{
name|_out
operator|.
name|println
argument_list|(
literal|"    createMethod_"
operator|+
name|i
operator|+
literal|"();"
argument_list|)
expr_stmt|;
block|}
name|_out
operator|.
name|println
argument_list|(
literal|"    _cg.getJavaClass().dump(out);"
argument_list|)
expr_stmt|;
name|_out
operator|.
name|println
argument_list|(
literal|"  }"
argument_list|)
expr_stmt|;
name|_out
operator|.
name|println
argument_list|()
expr_stmt|;
block|}
specifier|private
name|void
name|printMain
parameter_list|()
block|{
name|String
name|class_name
init|=
name|_clazz
operator|.
name|getClassName
argument_list|()
decl_stmt|;
name|_out
operator|.
name|println
argument_list|(
literal|"  public static void main(String[] args) throws Exception {"
argument_list|)
expr_stmt|;
name|_out
operator|.
name|println
argument_list|(
literal|"    "
operator|+
name|class_name
operator|+
literal|"Creator creator = new "
operator|+
name|class_name
operator|+
literal|"Creator();"
argument_list|)
expr_stmt|;
name|_out
operator|.
name|println
argument_list|(
literal|"    creator.create(new FileOutputStream(\""
operator|+
name|class_name
operator|+
literal|".class\"));"
argument_list|)
expr_stmt|;
name|_out
operator|.
name|println
argument_list|(
literal|"  }"
argument_list|)
expr_stmt|;
block|}
specifier|public
name|void
name|visitField
parameter_list|(
name|Field
name|field
parameter_list|)
block|{
name|_out
operator|.
name|println
argument_list|()
expr_stmt|;
name|_out
operator|.
name|println
argument_list|(
literal|"    field = new FieldGen("
operator|+
name|printFlags
argument_list|(
name|field
operator|.
name|getAccessFlags
argument_list|()
argument_list|)
operator|+
literal|", "
operator|+
name|printType
argument_list|(
name|field
operator|.
name|getSignature
argument_list|()
argument_list|)
operator|+
literal|", \""
operator|+
name|field
operator|.
name|getName
argument_list|()
operator|+
literal|"\", _cp);"
argument_list|)
expr_stmt|;
name|ConstantValue
name|cv
init|=
name|field
operator|.
name|getConstantValue
argument_list|()
decl_stmt|;
if|if
condition|(
name|cv
operator|!=
literal|null
condition|)
block|{
name|String
name|value
init|=
name|cv
operator|.
name|toString
argument_list|()
decl_stmt|;
name|_out
operator|.
name|println
argument_list|(
literal|"    field.setInitValue("
operator|+
name|value
operator|+
literal|")"
argument_list|)
expr_stmt|;
block|}
name|_out
operator|.
name|println
argument_list|(
literal|"    _cg.addField(field.getField());"
argument_list|)
expr_stmt|;
block|}
specifier|public
name|void
name|visitMethod
parameter_list|(
name|Method
name|method
parameter_list|)
block|{
name|MethodGen
name|mg
init|=
operator|new
name|MethodGen
argument_list|(
name|method
argument_list|,
name|_clazz
operator|.
name|getClassName
argument_list|()
argument_list|,
name|_cp
argument_list|)
decl_stmt|;
name|Type
name|result_type
init|=
name|mg
operator|.
name|getReturnType
argument_list|()
decl_stmt|;
name|Type
index|[]
name|arg_types
init|=
name|mg
operator|.
name|getArgumentTypes
argument_list|()
decl_stmt|;
name|_out
operator|.
name|println
argument_list|(
literal|"    InstructionList il = new InstructionList();"
argument_list|)
expr_stmt|;
name|_out
operator|.
name|println
argument_list|(
literal|"    MethodGen method = new MethodGen("
operator|+
name|printFlags
argument_list|(
name|method
operator|.
name|getAccessFlags
argument_list|()
argument_list|,
name|FLAG_FOR_METHOD
argument_list|)
operator|+
literal|", "
operator|+
name|printType
argument_list|(
name|result_type
argument_list|)
operator|+
literal|", "
operator|+
name|printArgumentTypes
argument_list|(
name|arg_types
argument_list|)
operator|+
literal|", "
operator|+
literal|"new String[] { "
operator|+
name|Utility
operator|.
name|printArray
argument_list|(
name|mg
operator|.
name|getArgumentNames
argument_list|()
argument_list|,
literal|false
argument_list|,
literal|true
argument_list|)
operator|+
literal|" }, \""
operator|+
name|method
operator|.
name|getName
argument_list|()
operator|+
literal|"\", \""
operator|+
name|_clazz
operator|.
name|getClassName
argument_list|()
operator|+
literal|"\", il, _cp);"
argument_list|)
expr_stmt|;
name|_out
operator|.
name|println
argument_list|()
expr_stmt|;
name|BCELFactory
name|factory
init|=
operator|new
name|BCELFactory
argument_list|(
name|mg
argument_list|,
name|_out
argument_list|)
decl_stmt|;
name|factory
operator|.
name|start
argument_list|()
expr_stmt|;
name|_out
operator|.
name|println
argument_list|(
literal|"    method.setMaxStack();"
argument_list|)
expr_stmt|;
name|_out
operator|.
name|println
argument_list|(
literal|"    method.setMaxLocals();"
argument_list|)
expr_stmt|;
name|_out
operator|.
name|println
argument_list|(
literal|"    _cg.addMethod(method.getMethod());"
argument_list|)
expr_stmt|;
name|_out
operator|.
name|println
argument_list|(
literal|"    il.dispose();"
argument_list|)
expr_stmt|;
block|}
specifier|static
name|String
name|printFlags
parameter_list|(
name|int
name|flags
parameter_list|)
block|{
return|return
name|printFlags
argument_list|(
name|flags
argument_list|,
name|FLAG_FOR_UNKNOWN
argument_list|)
return|;
block|}
specifier|static
name|String
name|printFlags
parameter_list|(
name|int
name|flags
parameter_list|,
name|int
name|reason
parameter_list|)
block|{
if|if
condition|(
name|flags
operator|==
literal|0
condition|)
return|return
literal|"0"
return|;
name|StringBuffer
name|buf
init|=
operator|new
name|StringBuffer
argument_list|()
decl_stmt|;
for|for
control|(
name|int
name|i
init|=
literal|0
init|,
name|pow
init|=
literal|1
init|;
name|i
operator|<=
name|Constants
operator|.
name|MAX_ACC_FLAG
condition|;
name|i
operator|++
control|)
block|{
if|if
condition|(
operator|(
name|flags
operator|&
name|pow
operator|)
operator|!=
literal|0
condition|)
block|{
if|if
condition|(
operator|(
name|pow
operator|==
name|Constants
operator|.
name|ACC_SYNCHRONIZED
operator|)
operator|&&
operator|(
name|reason
operator|==
name|FLAG_FOR_CLASS
operator|)
condition|)
name|buf
operator|.
name|append
argument_list|(
literal|"ACC_SUPER | "
argument_list|)
expr_stmt|;
if|else if
condition|(
operator|(
name|pow
operator|==
name|Constants
operator|.
name|ACC_VOLATILE
operator|)
operator|&&
operator|(
name|reason
operator|==
name|FLAG_FOR_METHOD
operator|)
condition|)
name|buf
operator|.
name|append
argument_list|(
literal|"ACC_BRIDGE | "
argument_list|)
expr_stmt|;
if|else if
condition|(
operator|(
name|pow
operator|==
name|Constants
operator|.
name|ACC_TRANSIENT
operator|)
operator|&&
operator|(
name|reason
operator|==
name|FLAG_FOR_METHOD
operator|)
condition|)
name|buf
operator|.
name|append
argument_list|(
literal|"ACC_VARARGS | "
argument_list|)
expr_stmt|;
else|else
name|buf
operator|.
name|append
argument_list|(
literal|"ACC_"
operator|+
name|Constants
operator|.
name|ACCESS_NAMES
index|[
name|i
index|]
operator|.
name|toUpperCase
argument_list|()
operator|+
literal|" | "
argument_list|)
expr_stmt|;
block|}
name|pow
operator|<<=
literal|1
expr_stmt|;
block|}
name|String
name|str
init|=
name|buf
operator|.
name|toString
argument_list|()
decl_stmt|;
return|return
name|str
operator|.
name|substring
argument_list|(
literal|0
argument_list|,
name|str
operator|.
name|length
argument_list|()
operator|-
literal|3
argument_list|)
return|;
block|}
specifier|static
name|String
name|printArgumentTypes
parameter_list|(
name|Type
index|[]
name|arg_types
parameter_list|)
block|{
if|if
condition|(
name|arg_types
operator|.
name|length
operator|==
literal|0
condition|)
return|return
literal|"Type.NO_ARGS"
return|;
name|StringBuffer
name|args
init|=
operator|new
name|StringBuffer
argument_list|()
decl_stmt|;
for|for
control|(
name|int
name|i
init|=
literal|0
init|;
name|i
operator|<
name|arg_types
operator|.
name|length
condition|;
name|i
operator|++
control|)
block|{
name|args
operator|.
name|append
argument_list|(
name|printType
argument_list|(
name|arg_types
index|[
name|i
index|]
argument_list|)
argument_list|)
expr_stmt|;
if|if
condition|(
name|i
operator|<
name|arg_types
operator|.
name|length
operator|-
literal|1
condition|)
name|args
operator|.
name|append
argument_list|(
literal|", "
argument_list|)
expr_stmt|;
block|}
return|return
literal|"new Type[] { "
operator|+
name|args
operator|.
name|toString
argument_list|()
operator|+
literal|" }"
return|;
block|}
specifier|static
name|String
name|printType
parameter_list|(
name|Type
name|type
parameter_list|)
block|{
return|return
name|printType
argument_list|(
name|type
operator|.
name|getSignature
argument_list|()
argument_list|)
return|;
block|}
specifier|static
name|String
name|printType
parameter_list|(
name|String
name|signature
parameter_list|)
block|{
name|Type
name|type
init|=
name|Type
operator|.
name|getType
argument_list|(
name|signature
argument_list|)
decl_stmt|;
name|byte
name|t
init|=
name|type
operator|.
name|getType
argument_list|()
decl_stmt|;
if|if
condition|(
name|t
operator|<=
name|Constants
operator|.
name|T_VOID
condition|)
block|{
return|return
literal|"Type."
operator|+
name|Constants
operator|.
name|TYPE_NAMES
index|[
name|t
index|]
operator|.
name|toUpperCase
argument_list|()
return|;
block|}
if|else if
condition|(
name|type
operator|.
name|toString
argument_list|()
operator|.
name|equals
argument_list|(
literal|"java.lang.String"
argument_list|)
condition|)
block|{
return|return
literal|"Type.STRING"
return|;
block|}
if|else if
condition|(
name|type
operator|.
name|toString
argument_list|()
operator|.
name|equals
argument_list|(
literal|"java.lang.Object"
argument_list|)
condition|)
block|{
return|return
literal|"Type.OBJECT"
return|;
block|}
if|else if
condition|(
name|type
operator|.
name|toString
argument_list|()
operator|.
name|equals
argument_list|(
literal|"java.lang.StringBuffer"
argument_list|)
condition|)
block|{
return|return
literal|"Type.STRINGBUFFER"
return|;
block|}
if|else if
condition|(
name|type
operator|instanceof
name|ArrayType
condition|)
block|{
name|ArrayType
name|at
init|=
operator|(
name|ArrayType
operator|)
name|type
decl_stmt|;
return|return
literal|"new ArrayType("
operator|+
name|printType
argument_list|(
name|at
operator|.
name|getBasicType
argument_list|()
argument_list|)
operator|+
literal|", "
operator|+
name|at
operator|.
name|getDimensions
argument_list|()
operator|+
literal|")"
return|;
block|}
else|else
block|{
return|return
literal|"new ObjectType(\""
operator|+
name|Utility
operator|.
name|signatureToString
argument_list|(
name|signature
argument_list|,
literal|false
argument_list|)
operator|+
literal|"\")"
return|;
block|}
block|}
comment|/** Default main method    */
specifier|public
specifier|static
name|void
name|main
parameter_list|(
name|String
index|[]
name|argv
parameter_list|)
throws|throws
name|Exception
block|{
name|JavaClass
name|java_class
decl_stmt|;
name|String
name|name
init|=
name|argv
index|[
literal|0
index|]
decl_stmt|;
if|if
condition|(
operator|(
name|java_class
operator|=
name|Repository
operator|.
name|lookupClass
argument_list|(
name|name
argument_list|)
operator|)
operator|==
literal|null
condition|)
name|java_class
operator|=
operator|new
name|ClassParser
argument_list|(
name|name
argument_list|)
operator|.
name|parse
argument_list|()
expr_stmt|;
comment|// May throw IOException
name|BCELifier
name|bcelifier
init|=
operator|new
name|BCELifier
argument_list|(
name|java_class
argument_list|,
name|System
operator|.
name|out
argument_list|)
decl_stmt|;
name|bcelifier
operator|.
name|start
argument_list|()
expr_stmt|;
block|}
block|}
end_class

end_unit

