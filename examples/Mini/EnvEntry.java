begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
begin_package
package|package
name|Mini
package|;
end_package

begin_comment
comment|/**  * Entry in environment.  *  * @version $Id$  * @author<A HREF="http://www.berlin.de/~markus.dahm/">M. Dahm</A>  */
end_comment

begin_interface
specifier|public
interface|interface
name|EnvEntry
block|{
specifier|public
name|String
name|getHashKey
parameter_list|()
function_decl|;
specifier|public
name|int
name|getLine
parameter_list|()
function_decl|;
specifier|public
name|int
name|getColumn
parameter_list|()
function_decl|;
block|}
end_interface

end_unit

