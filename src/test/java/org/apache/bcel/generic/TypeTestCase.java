begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
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
name|junit
operator|.
name|framework
operator|.
name|TestCase
import|;
end_import

begin_class
specifier|public
class|class
name|TypeTestCase
extends|extends
name|TestCase
block|{
specifier|public
name|void
name|testBCEL243
parameter_list|()
block|{
comment|// expectedValue = "Ljava/util/Map<TX;Ljava/util/List<TY;>;>;";
comment|// The line commented out above is the correct expected value; however,
comment|// the constructor for ObjectType is yet another place where BCEL does
comment|// not understand generics so we need to substitute the modified value below.
name|String
name|expectedValue
init|=
literal|"Ljava/util/Map<X, java/util/List<Y>>;"
decl_stmt|;
name|String
name|actualValue
init|=
operator|(
name|Type
operator|.
name|getType
argument_list|(
literal|"Ljava/util/Map<TX;Ljava/util/List<TY;>;>;"
argument_list|)
operator|)
operator|.
name|getSignature
argument_list|()
decl_stmt|;
name|assertEquals
argument_list|(
literal|"Type.getType"
argument_list|,
name|expectedValue
argument_list|,
name|actualValue
argument_list|)
expr_stmt|;
block|}
block|}
end_class

end_unit

