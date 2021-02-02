begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
begin_comment
comment|/*  * Licensed to the Apache Software Foundation (ASF) under one or more  * contributor license agreements.  See the NOTICE file distributed with  * this work for additional information regarding copyright ownership.  * The ASF licenses this file to You under the Apache License, Version 2.0  * (the "License"); you may not use this file except in compliance with  * the License.  You may obtain a copy of the License at  *  *   http://www.apache.org/licenses/LICENSE-2.0  *  * Unless required by applicable law or agreed to in writing, software  * distributed under the License is distributed on an "AS IS" BASIS,  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  * See the License for the specific language governing permissions and  * limitations under the License.  *  */
end_comment

begin_package
package|package
name|org
operator|.
name|apache
operator|.
name|bcel
package|;
end_package

begin_class
specifier|public
class|class
name|NanoTimer
block|{
specifier|private
name|long
name|timeNanos
init|=
literal|0
decl_stmt|;
specifier|public
name|NanoTimer
name|start
parameter_list|()
block|{
name|timeNanos
operator|-=
name|System
operator|.
name|nanoTime
argument_list|()
expr_stmt|;
return|return
name|this
return|;
block|}
specifier|public
name|void
name|stop
parameter_list|()
block|{
name|timeNanos
operator|+=
name|System
operator|.
name|nanoTime
argument_list|()
expr_stmt|;
block|}
specifier|public
name|void
name|subtract
parameter_list|(
specifier|final
name|NanoTimer
name|o
parameter_list|)
block|{
name|timeNanos
operator|-=
name|o
operator|.
name|timeNanos
expr_stmt|;
block|}
specifier|public
name|void
name|reset
parameter_list|()
block|{
name|timeNanos
operator|=
literal|0
expr_stmt|;
block|}
comment|/**      * May ony be called after stop has been called as many times as start.      */
annotation|@
name|Override
specifier|public
name|String
name|toString
parameter_list|()
block|{
return|return
operator|(
operator|(
name|double
operator|)
name|timeNanos
operator|/
literal|1000000000
operator|)
operator|+
literal|" s"
return|;
block|}
block|}
end_class

end_unit

