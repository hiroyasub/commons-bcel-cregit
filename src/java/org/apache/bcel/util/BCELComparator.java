begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
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

begin_comment
comment|/**  * Used for BCEL comparison strategy  *   * @author<A HREF="mailto:m.dahm@gmx.de">M. Dahm</A>  * @version $Id$  */
end_comment

begin_interface
specifier|public
interface|interface
name|BCELComparator
block|{
comment|/** 	 * Compare two objects and return what THIS.equals(THAT) should return 	 *  	 * @param THIS 	 * @param THAT 	 * @return true if and only if THIS equals THAT 	 */
specifier|public
name|boolean
name|equals
parameter_list|(
name|Object
name|THIS
parameter_list|,
name|Object
name|THAT
parameter_list|)
function_decl|;
comment|/** 	 * Return hashcode for THIS.hashCode() 	 *  	 * @param THIS 	 * @return hashcode for THIS.hashCode() 	 */
specifier|public
name|int
name|hashCode
parameter_list|(
name|Object
name|THIS
parameter_list|)
function_decl|;
block|}
end_interface

end_unit

