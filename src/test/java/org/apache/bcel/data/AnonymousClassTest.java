begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
begin_package
package|package
name|org
operator|.
name|apache
operator|.
name|bcel
operator|.
name|data
package|;
end_package

begin_class
specifier|public
class|class
name|AnonymousClassTest
block|{
specifier|public
name|void
name|foo
parameter_list|()
block|{
operator|new
name|Runnable
argument_list|()
block|{
specifier|public
name|void
name|run
parameter_list|()
block|{
block|}
empty_stmt|;
block|}
operator|.
name|run
argument_list|()
expr_stmt|;
block|}
class|class
name|X
block|{ 	}
specifier|static
class|class
name|Y
block|{ 	}
block|}
end_class

end_unit

