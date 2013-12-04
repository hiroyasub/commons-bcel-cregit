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

begin_interface
specifier|public
interface|interface
name|VisitorSupportsInvokeDynamic
extends|extends
name|Visitor
block|{
name|void
name|visitINVOKEDYNAMIC
parameter_list|(
name|INVOKEDYNAMIC
name|obj
parameter_list|)
function_decl|;
block|}
end_interface

end_unit

