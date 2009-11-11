begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
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
name|io
operator|.
name|File
import|;
end_import

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
name|List
import|;
end_import

begin_import
import|import
name|junit
operator|.
name|framework
operator|.
name|TestCase
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
name|Attribute
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
name|generic
operator|.
name|AnnotationEntryGen
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
name|ElementValueGen
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
name|ElementValuePairGen
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
name|ObjectType
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
name|SimpleElementValueGen
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
name|util
operator|.
name|ClassPath
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
name|util
operator|.
name|SyntheticRepository
import|;
end_import

begin_class
specifier|public
specifier|abstract
class|class
name|AbstractTestCase
extends|extends
name|TestCase
block|{
specifier|private
name|boolean
name|verbose
init|=
literal|false
decl_stmt|;
specifier|protected
name|File
name|createTestdataFile
parameter_list|(
name|String
name|name
parameter_list|)
block|{
return|return
operator|new
name|File
argument_list|(
literal|"target"
operator|+
name|File
operator|.
name|separator
operator|+
literal|"testdata"
operator|+
name|File
operator|.
name|separator
operator|+
name|name
argument_list|)
return|;
block|}
specifier|protected
name|JavaClass
name|getTestClass
parameter_list|(
name|String
name|name
parameter_list|)
throws|throws
name|ClassNotFoundException
block|{
return|return
name|SyntheticRepository
operator|.
name|getInstance
argument_list|()
operator|.
name|loadClass
argument_list|(
name|name
argument_list|)
return|;
block|}
specifier|protected
name|Method
name|getMethod
parameter_list|(
name|JavaClass
name|cl
parameter_list|,
name|String
name|methodname
parameter_list|)
block|{
name|Method
index|[]
name|methods
init|=
name|cl
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
name|Method
name|m
init|=
name|methods
index|[
name|i
index|]
decl_stmt|;
if|if
condition|(
name|m
operator|.
name|getName
argument_list|()
operator|.
name|equals
argument_list|(
name|methodname
argument_list|)
condition|)
block|{
return|return
name|m
return|;
block|}
block|}
return|return
literal|null
return|;
block|}
specifier|protected
name|boolean
name|wipe
parameter_list|(
name|String
name|name
parameter_list|)
block|{
return|return
operator|new
name|File
argument_list|(
literal|"target"
operator|+
name|File
operator|.
name|separator
operator|+
literal|"testdata"
operator|+
name|File
operator|.
name|separator
operator|+
name|name
argument_list|)
operator|.
name|delete
argument_list|()
return|;
block|}
specifier|protected
name|boolean
name|wipe
parameter_list|(
name|String
name|dir
parameter_list|,
name|String
name|name
parameter_list|)
block|{
name|boolean
name|b
init|=
name|wipe
argument_list|(
name|dir
operator|+
name|File
operator|.
name|separator
operator|+
name|name
argument_list|)
decl_stmt|;
name|String
index|[]
name|files
init|=
operator|new
name|File
argument_list|(
name|dir
argument_list|)
operator|.
name|list
argument_list|()
decl_stmt|;
if|if
condition|(
name|files
operator|==
literal|null
operator|||
name|files
operator|.
name|length
operator|==
literal|0
condition|)
block|{
operator|new
name|File
argument_list|(
name|dir
argument_list|)
operator|.
name|delete
argument_list|()
expr_stmt|;
comment|// Why does this not succeed? stupid thing
block|}
return|return
name|b
return|;
block|}
specifier|public
name|SyntheticRepository
name|createRepos
parameter_list|(
name|String
name|cpentry
parameter_list|)
block|{
name|ClassPath
name|cp
init|=
operator|new
name|ClassPath
argument_list|(
literal|"target"
operator|+
name|File
operator|.
name|separator
operator|+
literal|"testdata"
operator|+
name|File
operator|.
name|separator
operator|+
name|cpentry
operator|+
name|File
operator|.
name|separator
argument_list|)
decl_stmt|;
return|return
name|SyntheticRepository
operator|.
name|getInstance
argument_list|(
name|cp
argument_list|)
return|;
block|}
specifier|protected
name|Attribute
index|[]
name|findAttribute
parameter_list|(
name|String
name|name
parameter_list|,
name|JavaClass
name|clazz
parameter_list|)
block|{
name|Attribute
index|[]
name|all
init|=
name|clazz
operator|.
name|getAttributes
argument_list|()
decl_stmt|;
name|List
name|chosenAttrsList
init|=
operator|new
name|ArrayList
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
name|all
operator|.
name|length
condition|;
name|i
operator|++
control|)
block|{
if|if
condition|(
name|verbose
condition|)
name|System
operator|.
name|err
operator|.
name|println
argument_list|(
literal|"Attribute: "
operator|+
name|all
index|[
name|i
index|]
operator|.
name|getName
argument_list|()
argument_list|)
expr_stmt|;
if|if
condition|(
name|all
index|[
name|i
index|]
operator|.
name|getName
argument_list|()
operator|.
name|equals
argument_list|(
name|name
argument_list|)
condition|)
name|chosenAttrsList
operator|.
name|add
argument_list|(
name|all
index|[
name|i
index|]
argument_list|)
expr_stmt|;
block|}
return|return
operator|(
name|Attribute
index|[]
operator|)
name|chosenAttrsList
operator|.
name|toArray
argument_list|(
operator|new
name|Attribute
index|[]
block|{}
argument_list|)
return|;
block|}
specifier|protected
name|Attribute
name|findAttribute
parameter_list|(
name|String
name|name
parameter_list|,
name|Attribute
index|[]
name|all
parameter_list|)
block|{
name|List
name|chosenAttrsList
init|=
operator|new
name|ArrayList
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
name|all
operator|.
name|length
condition|;
name|i
operator|++
control|)
block|{
if|if
condition|(
name|verbose
condition|)
name|System
operator|.
name|err
operator|.
name|println
argument_list|(
literal|"Attribute: "
operator|+
name|all
index|[
name|i
index|]
operator|.
name|getName
argument_list|()
argument_list|)
expr_stmt|;
if|if
condition|(
name|all
index|[
name|i
index|]
operator|.
name|getName
argument_list|()
operator|.
name|equals
argument_list|(
name|name
argument_list|)
condition|)
name|chosenAttrsList
operator|.
name|add
argument_list|(
name|all
index|[
name|i
index|]
argument_list|)
expr_stmt|;
block|}
name|assertTrue
argument_list|(
literal|"Should be one match: "
operator|+
name|chosenAttrsList
operator|.
name|size
argument_list|()
argument_list|,
name|chosenAttrsList
operator|.
name|size
argument_list|()
operator|==
literal|1
argument_list|)
expr_stmt|;
return|return
operator|(
name|Attribute
operator|)
name|chosenAttrsList
operator|.
name|get
argument_list|(
literal|0
argument_list|)
return|;
block|}
specifier|protected
name|String
name|dumpAttributes
parameter_list|(
name|Attribute
index|[]
name|as
parameter_list|)
block|{
name|StringBuffer
name|result
init|=
operator|new
name|StringBuffer
argument_list|()
decl_stmt|;
name|result
operator|.
name|append
argument_list|(
literal|"AttributeArray:["
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
name|as
operator|.
name|length
condition|;
name|i
operator|++
control|)
block|{
name|Attribute
name|attr
init|=
name|as
index|[
name|i
index|]
decl_stmt|;
name|result
operator|.
name|append
argument_list|(
name|attr
operator|.
name|toString
argument_list|()
argument_list|)
expr_stmt|;
if|if
condition|(
name|i
operator|+
literal|1
operator|<
name|as
operator|.
name|length
condition|)
name|result
operator|.
name|append
argument_list|(
literal|","
argument_list|)
expr_stmt|;
block|}
name|result
operator|.
name|append
argument_list|(
literal|"]"
argument_list|)
expr_stmt|;
return|return
name|result
operator|.
name|toString
argument_list|()
return|;
block|}
specifier|protected
name|String
name|dumpAnnotationEntries
parameter_list|(
name|AnnotationEntry
index|[]
name|as
parameter_list|)
block|{
name|StringBuffer
name|result
init|=
operator|new
name|StringBuffer
argument_list|()
decl_stmt|;
name|result
operator|.
name|append
argument_list|(
literal|"["
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
name|as
operator|.
name|length
condition|;
name|i
operator|++
control|)
block|{
name|AnnotationEntry
name|annotation
init|=
name|as
index|[
name|i
index|]
decl_stmt|;
name|result
operator|.
name|append
argument_list|(
name|annotation
operator|.
name|toShortString
argument_list|()
argument_list|)
expr_stmt|;
if|if
condition|(
name|i
operator|+
literal|1
operator|<
name|as
operator|.
name|length
condition|)
name|result
operator|.
name|append
argument_list|(
literal|","
argument_list|)
expr_stmt|;
block|}
name|result
operator|.
name|append
argument_list|(
literal|"]"
argument_list|)
expr_stmt|;
return|return
name|result
operator|.
name|toString
argument_list|()
return|;
block|}
specifier|protected
name|String
name|dumpAnnotationEntries
parameter_list|(
name|AnnotationEntryGen
index|[]
name|as
parameter_list|)
block|{
name|StringBuffer
name|result
init|=
operator|new
name|StringBuffer
argument_list|()
decl_stmt|;
name|result
operator|.
name|append
argument_list|(
literal|"["
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
name|as
operator|.
name|length
condition|;
name|i
operator|++
control|)
block|{
name|AnnotationEntryGen
name|annotation
init|=
name|as
index|[
name|i
index|]
decl_stmt|;
name|result
operator|.
name|append
argument_list|(
name|annotation
operator|.
name|toShortString
argument_list|()
argument_list|)
expr_stmt|;
if|if
condition|(
name|i
operator|+
literal|1
operator|<
name|as
operator|.
name|length
condition|)
name|result
operator|.
name|append
argument_list|(
literal|","
argument_list|)
expr_stmt|;
block|}
name|result
operator|.
name|append
argument_list|(
literal|"]"
argument_list|)
expr_stmt|;
return|return
name|result
operator|.
name|toString
argument_list|()
return|;
block|}
specifier|public
name|AnnotationEntryGen
name|createFruitAnnotationEntry
parameter_list|(
name|ConstantPoolGen
name|cp
parameter_list|,
name|String
name|aFruit
parameter_list|,
name|boolean
name|visibility
parameter_list|)
block|{
name|SimpleElementValueGen
name|evg
init|=
operator|new
name|SimpleElementValueGen
argument_list|(
name|ElementValueGen
operator|.
name|STRING
argument_list|,
name|cp
argument_list|,
name|aFruit
argument_list|)
decl_stmt|;
name|ElementValuePairGen
name|nvGen
init|=
operator|new
name|ElementValuePairGen
argument_list|(
literal|"fruit"
argument_list|,
name|evg
argument_list|,
name|cp
argument_list|)
decl_stmt|;
name|ObjectType
name|t
init|=
operator|new
name|ObjectType
argument_list|(
literal|"SimpleStringAnnotation"
argument_list|)
decl_stmt|;
name|List
name|elements
init|=
operator|new
name|ArrayList
argument_list|()
decl_stmt|;
name|elements
operator|.
name|add
argument_list|(
name|nvGen
argument_list|)
expr_stmt|;
return|return
operator|new
name|AnnotationEntryGen
argument_list|(
name|t
argument_list|,
name|elements
argument_list|,
name|visibility
argument_list|,
name|cp
argument_list|)
return|;
block|}
block|}
end_class

end_unit
