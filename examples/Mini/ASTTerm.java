begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
begin_comment
comment|/* Generated By:JJTree: Do not edit this line. ASTTerm.java */
end_comment

begin_comment
comment|/* JJT: 0.3pre1 */
end_comment

begin_package
package|package
name|Mini
package|;
end_package

begin_comment
comment|/**  *  * @version $Id$  * @author<A HREF="mailto:m.dahm@gmx.de">M. Dahm</A>  */
end_comment

begin_class
specifier|public
class|class
name|ASTTerm
extends|extends
name|ASTExpr
block|{
comment|// Generated methods
name|ASTTerm
parameter_list|(
name|int
name|id
parameter_list|)
block|{
name|super
argument_list|(
name|id
argument_list|)
expr_stmt|;
block|}
name|ASTTerm
parameter_list|(
name|MiniParser
name|p
parameter_list|,
name|int
name|id
parameter_list|)
block|{
name|super
argument_list|(
name|p
argument_list|,
name|id
argument_list|)
expr_stmt|;
block|}
specifier|public
specifier|static
name|Node
name|jjtCreate
parameter_list|(
name|MiniParser
name|p
parameter_list|,
name|int
name|id
parameter_list|)
block|{
return|return
operator|new
name|ASTTerm
argument_list|(
name|p
argument_list|,
name|id
argument_list|)
return|;
block|}
comment|// Inherited closeNode(), dump()
comment|/**    * Drop this node, if kind == -1, because then it has just one child node    * and may be safely replaced with it.    */
specifier|public
name|ASTExpr
name|traverse
parameter_list|(
name|Environment
name|env
parameter_list|)
block|{
if|if
condition|(
name|kind
operator|==
operator|-
literal|1
condition|)
comment|// Drop it
return|return
name|exprs
index|[
literal|0
index|]
operator|.
name|traverse
argument_list|(
name|env
argument_list|)
return|;
else|else
comment|// Or convert it to Expr node, copy children
return|return
operator|new
name|ASTExpr
argument_list|(
name|exprs
argument_list|,
name|kind
argument_list|,
name|line
argument_list|,
name|column
argument_list|)
operator|.
name|traverse
argument_list|(
name|env
argument_list|)
return|;
block|}
block|}
end_class

end_unit

