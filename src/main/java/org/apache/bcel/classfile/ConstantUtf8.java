begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
begin_comment
comment|/*  * Licensed to the Apache Software Foundation (ASF) under one or more  * contributor license agreements.  See the NOTICE file distributed with  * this work for additional information regarding copyright ownership.  * The ASF licenses this file to You under the Apache License, Version 2.0  * (the "License"); you may not use this file except in compliance with  * the License.  You may obtain a copy of the License at  *  *      http://www.apache.org/licenses/LICENSE-2.0  *  *  Unless required by applicable law or agreed to in writing, software  *  distributed under the License is distributed on an "AS IS" BASIS,  *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  *  See the License for the specific language governing permissions and  *  limitations under the License.  *  */
end_comment

begin_package
package|package
name|org
operator|.
name|apache
operator|.
name|bcel
operator|.
name|classfile
package|;
end_package

begin_import
import|import
name|java
operator|.
name|io
operator|.
name|DataInput
import|;
end_import

begin_import
import|import
name|java
operator|.
name|io
operator|.
name|DataOutputStream
import|;
end_import

begin_import
import|import
name|java
operator|.
name|io
operator|.
name|IOException
import|;
end_import

begin_import
import|import
name|java
operator|.
name|util
operator|.
name|HashMap
import|;
end_import

begin_import
import|import
name|java
operator|.
name|util
operator|.
name|LinkedHashMap
import|;
end_import

begin_import
import|import
name|java
operator|.
name|util
operator|.
name|Map
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
name|Constants
import|;
end_import

begin_comment
comment|/**   * This class is derived from the abstract   *<A HREF="org.apache.bcel.classfile.Constant.html">Constant</A> class   * and represents a reference to a Utf8 encoded string.  *  * @version $Id$  * @author<A HREF="mailto:m.dahm@gmx.de">M. Dahm</A>  * @see     Constant  */
end_comment

begin_class
specifier|public
specifier|final
class|class
name|ConstantUtf8
extends|extends
name|Constant
block|{
specifier|private
specifier|static
specifier|final
name|long
name|serialVersionUID
init|=
operator|-
literal|8709101585611518985L
decl_stmt|;
specifier|private
specifier|final
name|String
name|bytes
decl_stmt|;
specifier|private
specifier|static
specifier|final
name|int
name|MAX_CACHE_ENTRIES
init|=
literal|20000
decl_stmt|;
specifier|private
specifier|static
specifier|final
name|int
name|INITIAL_CACHE_CAPACITY
init|=
operator|(
name|int
operator|)
operator|(
name|MAX_CACHE_ENTRIES
operator|/
literal|0.75
operator|)
decl_stmt|;
specifier|private
specifier|static
name|HashMap
argument_list|<
name|String
argument_list|,
name|ConstantUtf8
argument_list|>
name|cache
decl_stmt|;
specifier|private
specifier|static
name|int
name|considered
init|=
literal|0
decl_stmt|;
specifier|private
specifier|static
name|int
name|hits
init|=
literal|0
decl_stmt|;
specifier|private
specifier|static
name|int
name|skipped
init|=
literal|0
decl_stmt|;
specifier|private
specifier|static
name|int
name|created
init|=
literal|0
decl_stmt|;
specifier|final
specifier|static
name|boolean
name|BCEL_STATISTICS
init|=
name|Boolean
operator|.
name|getBoolean
argument_list|(
literal|"bcel.statistics"
argument_list|)
decl_stmt|;
specifier|final
specifier|static
name|boolean
name|BCEL_DONT_CACHE
init|=
name|Boolean
operator|.
name|getBoolean
argument_list|(
literal|"bcel.dontCache"
argument_list|)
decl_stmt|;
static|static
block|{
if|if
condition|(
name|BCEL_STATISTICS
condition|)
block|{
name|Runtime
operator|.
name|getRuntime
argument_list|()
operator|.
name|addShutdownHook
argument_list|(
operator|new
name|Thread
argument_list|()
block|{
annotation|@
name|Override
specifier|public
name|void
name|run
parameter_list|()
block|{
name|System
operator|.
name|err
operator|.
name|println
argument_list|(
literal|"Cache hit "
operator|+
name|hits
operator|+
literal|"/"
operator|+
name|considered
operator|+
literal|", "
operator|+
name|skipped
operator|+
literal|" skipped"
argument_list|)
expr_stmt|;
name|System
operator|.
name|err
operator|.
name|println
argument_list|(
literal|"Total of "
operator|+
name|created
operator|+
literal|" ConstantUtf8 objects created"
argument_list|)
expr_stmt|;
block|}
block|}
argument_list|)
expr_stmt|;
block|}
block|}
specifier|public
specifier|static
specifier|synchronized
name|ConstantUtf8
name|getCachedInstance
parameter_list|(
name|String
name|s
parameter_list|)
block|{
if|if
condition|(
name|BCEL_DONT_CACHE
operator|||
name|s
operator|.
name|length
argument_list|()
operator|>
literal|200
condition|)
block|{
name|skipped
operator|++
expr_stmt|;
return|return
operator|new
name|ConstantUtf8
argument_list|(
name|s
argument_list|)
return|;
block|}
name|considered
operator|++
expr_stmt|;
if|if
condition|(
name|cache
operator|==
literal|null
condition|)
block|{
name|cache
operator|=
operator|new
name|LinkedHashMap
argument_list|<
name|String
argument_list|,
name|ConstantUtf8
argument_list|>
argument_list|(
name|INITIAL_CACHE_CAPACITY
argument_list|,
literal|0.75f
argument_list|,
literal|true
argument_list|)
block|{
specifier|private
specifier|static
specifier|final
name|long
name|serialVersionUID
init|=
operator|-
literal|8506975356158971766L
decl_stmt|;
annotation|@
name|Override
specifier|protected
name|boolean
name|removeEldestEntry
parameter_list|(
name|Map
operator|.
name|Entry
argument_list|<
name|String
argument_list|,
name|ConstantUtf8
argument_list|>
name|eldest
parameter_list|)
block|{
return|return
name|size
argument_list|()
operator|>
name|MAX_CACHE_ENTRIES
return|;
block|}
block|}
expr_stmt|;
block|}
name|ConstantUtf8
name|result
init|=
name|cache
operator|.
name|get
argument_list|(
name|s
argument_list|)
decl_stmt|;
if|if
condition|(
name|result
operator|!=
literal|null
condition|)
block|{
name|hits
operator|++
expr_stmt|;
return|return
name|result
return|;
block|}
name|result
operator|=
operator|new
name|ConstantUtf8
argument_list|(
name|s
argument_list|)
expr_stmt|;
name|cache
operator|.
name|put
argument_list|(
name|s
argument_list|,
name|result
argument_list|)
expr_stmt|;
return|return
name|result
return|;
block|}
specifier|public
specifier|static
name|ConstantUtf8
name|getInstance
parameter_list|(
name|String
name|s
parameter_list|)
block|{
return|return
operator|new
name|ConstantUtf8
argument_list|(
name|s
argument_list|)
return|;
block|}
specifier|public
specifier|static
name|ConstantUtf8
name|getInstance
parameter_list|(
name|DataInput
name|input
parameter_list|)
throws|throws
name|IOException
block|{
return|return
name|getInstance
argument_list|(
name|input
operator|.
name|readUTF
argument_list|()
argument_list|)
return|;
block|}
comment|/**      * Initialize from another object.      */
specifier|public
name|ConstantUtf8
parameter_list|(
name|ConstantUtf8
name|c
parameter_list|)
block|{
name|this
argument_list|(
name|c
operator|.
name|getBytes
argument_list|()
argument_list|)
expr_stmt|;
block|}
comment|/**      * Initialize instance from file data.      *      * @param file Input stream      * @throws IOException      */
name|ConstantUtf8
parameter_list|(
name|DataInput
name|file
parameter_list|)
throws|throws
name|IOException
block|{
name|super
argument_list|(
name|Constants
operator|.
name|CONSTANT_Utf8
argument_list|)
expr_stmt|;
name|bytes
operator|=
name|file
operator|.
name|readUTF
argument_list|()
expr_stmt|;
name|created
operator|++
expr_stmt|;
block|}
comment|/**      * @param bytes Data      */
specifier|public
name|ConstantUtf8
parameter_list|(
name|String
name|bytes
parameter_list|)
block|{
name|super
argument_list|(
name|Constants
operator|.
name|CONSTANT_Utf8
argument_list|)
expr_stmt|;
if|if
condition|(
name|bytes
operator|==
literal|null
condition|)
block|{
throw|throw
operator|new
name|IllegalArgumentException
argument_list|(
literal|"bytes must not be null!"
argument_list|)
throw|;
block|}
name|this
operator|.
name|bytes
operator|=
name|bytes
expr_stmt|;
name|created
operator|++
expr_stmt|;
block|}
comment|/**      * Called by objects that are traversing the nodes of the tree implicitely      * defined by the contents of a Java class. I.e., the hierarchy of methods,      * fields, attributes, etc. spawns a tree of objects.      *      * @param v Visitor object      */
annotation|@
name|Override
specifier|public
name|void
name|accept
parameter_list|(
name|Visitor
name|v
parameter_list|)
block|{
name|v
operator|.
name|visitConstantUtf8
argument_list|(
name|this
argument_list|)
expr_stmt|;
block|}
comment|/**      * Dump String in Utf8 format to file stream.      *      * @param file Output file stream      * @throws IOException      */
annotation|@
name|Override
specifier|public
specifier|final
name|void
name|dump
parameter_list|(
name|DataOutputStream
name|file
parameter_list|)
throws|throws
name|IOException
block|{
name|file
operator|.
name|writeByte
argument_list|(
name|tag
argument_list|)
expr_stmt|;
name|file
operator|.
name|writeUTF
argument_list|(
name|bytes
argument_list|)
expr_stmt|;
block|}
comment|/**      * @return Data converted to string.      */
specifier|public
specifier|final
name|String
name|getBytes
parameter_list|()
block|{
return|return
name|bytes
return|;
block|}
comment|/**      * @param bytes the raw bytes of this Utf-8      * @deprecated      */
annotation|@
name|java
operator|.
name|lang
operator|.
name|Deprecated
specifier|public
specifier|final
name|void
name|setBytes
parameter_list|(
name|String
name|bytes
parameter_list|)
block|{
throw|throw
operator|new
name|UnsupportedOperationException
argument_list|()
throw|;
block|}
comment|/**      * @return String representation      */
annotation|@
name|Override
specifier|public
specifier|final
name|String
name|toString
parameter_list|()
block|{
return|return
name|super
operator|.
name|toString
argument_list|()
operator|+
literal|"(\""
operator|+
name|Utility
operator|.
name|replace
argument_list|(
name|bytes
argument_list|,
literal|"\n"
argument_list|,
literal|"\\n"
argument_list|)
operator|+
literal|"\")"
return|;
block|}
block|}
end_class

end_unit

