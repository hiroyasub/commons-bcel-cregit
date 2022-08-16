begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
begin_comment
comment|/*  * Licensed to the Apache Software Foundation (ASF) under one or more  * contributor license agreements.  See the NOTICE file distributed with  * this work for additional information regarding copyright ownership.  * The ASF licenses this file to You under the Apache License, Version 2.0  * (the "License"); you may not use this file except in compliance with  * the License.  You may obtain a copy of the License at  *  *      http://www.apache.org/licenses/LICENSE-2.0  *  *  Unless required by applicable law or agreed to in writing, software  *  distributed under the License is distributed on an "AS IS" BASIS,  *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  *  See the License for the specific language governing permissions and  *  limitations under the License.  *  */
end_comment

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
comment|/**  *  */
end_comment

begin_class
specifier|public
class|class
name|ASTTerm
extends|extends
name|ASTExpr
block|{
specifier|public
specifier|static
name|Node
name|jjtCreate
parameter_list|(
specifier|final
name|MiniParser
name|p
parameter_list|,
specifier|final
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
comment|// Generated methods
name|ASTTerm
parameter_list|(
specifier|final
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
specifier|final
name|MiniParser
name|p
parameter_list|,
specifier|final
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
comment|// Inherited closeNode(), dump()
comment|/**      * Drop this node, if kind == -1, because then it has just one child node and may be safely replaced with it.      */
annotation|@
name|Override
specifier|public
name|ASTExpr
name|traverse
parameter_list|(
specifier|final
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
block|{
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
block|}
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

