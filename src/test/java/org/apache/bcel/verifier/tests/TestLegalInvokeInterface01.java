begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
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
name|tests
package|;
end_package

begin_class
specifier|public
class|class
name|TestLegalInvokeInterface01
block|{
specifier|public
specifier|static
name|void
name|test1
parameter_list|(
name|Interface01
name|t
parameter_list|)
block|{
name|t
operator|.
name|run
argument_list|()
expr_stmt|;
block|}
block|}
end_class

begin_interface
interface|interface
name|Interface01
extends|extends
name|Runnable
block|{      }
end_interface

end_unit

