begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
begin_comment
comment|/*  * Copyright  2000-2009 The Apache Software Foundation  *  *  Licensed under the Apache License, Version 2.0 (the "License");   *  you may not use this file except in compliance with the License.  *  You may obtain a copy of the License at  *  *      http://www.apache.org/licenses/LICENSE-2.0  *  *  Unless required by applicable law or agreed to in writing, software  *  distributed under the License is distributed on an "AS IS" BASIS,  *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  *  See the License for the specific language governing permissions and  *  limitations under the License.   *  */
end_comment

begin_package
package|package
name|Mini
package|;
end_package

begin_import
import|import
name|java
operator|.
name|util
operator|.
name|Vector
import|;
end_import

begin_comment
comment|/**  * For efficiency and convenience reasons we want our own hash table. It does  * not conform to java.util.Dictionary(yet).  *  * That environment contains all function definitions and identifiers.  * Hash keys are Strings (identifiers), which are mapped to a table index.  *  * The table consists of `SIZE' fields which have `SLOTS' subfields. Thus   * the maximum number of storable items is `SLOTS' * `SIZE'.  *  * @version $Id$  * @author<A HREF="mailto:m.dahm@gmx.de">M. Dahm</A>  */
end_comment

begin_class
specifier|public
class|class
name|Environment
implements|implements
name|Cloneable
block|{
specifier|private
specifier|static
specifier|final
name|int
name|SIZE
init|=
literal|127
decl_stmt|;
comment|// Prime number large enough for most cases
specifier|private
specifier|static
specifier|final
name|int
name|SLOTS
init|=
literal|3
decl_stmt|;
comment|// Number of slots of each field
specifier|private
name|int
name|size
decl_stmt|;
comment|// The table is an array of
specifier|private
name|Vector
index|[]
name|table
decl_stmt|;
comment|// Vectors
specifier|private
name|int
name|elements
init|=
literal|0
decl_stmt|;
specifier|public
name|Environment
parameter_list|(
name|int
name|size
parameter_list|)
block|{
name|this
operator|.
name|size
operator|=
name|size
expr_stmt|;
name|table
operator|=
operator|new
name|Vector
index|[
name|size
index|]
expr_stmt|;
block|}
specifier|private
name|Environment
parameter_list|(
name|Vector
index|[]
name|table
parameter_list|)
block|{
name|size
operator|=
name|table
operator|.
name|length
expr_stmt|;
name|this
operator|.
name|table
operator|=
name|table
expr_stmt|;
block|}
specifier|public
name|Environment
parameter_list|()
block|{
name|this
argument_list|(
name|SIZE
argument_list|)
expr_stmt|;
block|}
specifier|private
specifier|final
name|int
name|hashCode
parameter_list|(
name|String
name|key
parameter_list|)
block|{
return|return
name|Math
operator|.
name|abs
argument_list|(
name|key
operator|.
name|hashCode
argument_list|()
argument_list|)
operator|%
name|size
return|;
block|}
comment|/**    * Inserts macro into table or overwrite old contents if it    * was already stored.    */
specifier|public
name|void
name|put
parameter_list|(
name|EnvEntry
name|obj
parameter_list|)
block|{
name|int
name|hash
decl_stmt|;
name|Vector
name|v
decl_stmt|;
name|String
name|key
init|=
name|obj
operator|.
name|getHashKey
argument_list|()
decl_stmt|;
name|hash
operator|=
name|hashCode
argument_list|(
name|key
argument_list|)
expr_stmt|;
name|v
operator|=
name|table
index|[
name|hash
index|]
expr_stmt|;
name|elements
operator|++
expr_stmt|;
comment|// Count
if|if
condition|(
name|v
operator|==
literal|null
condition|)
block|{
name|table
index|[
name|hash
index|]
operator|=
name|v
operator|=
operator|new
name|Vector
argument_list|(
name|SLOTS
argument_list|)
expr_stmt|;
block|}
else|else
block|{
try|try
block|{
name|int
name|index
init|=
name|lookup
argument_list|(
name|v
argument_list|,
name|key
argument_list|)
decl_stmt|;
if|if
condition|(
name|index
operator|>=
literal|0
condition|)
block|{
name|v
operator|.
name|setElementAt
argument_list|(
name|obj
argument_list|,
name|index
argument_list|)
expr_stmt|;
comment|// Overwrite
return|return;
block|}
block|}
catch|catch
parameter_list|(
name|ArrayIndexOutOfBoundsException
name|e
parameter_list|)
block|{
block|}
block|}
comment|// Not found in Vector -> add it
name|v
operator|.
name|addElement
argument_list|(
name|obj
argument_list|)
expr_stmt|;
block|}
comment|/** Get entry from hash table.    */
specifier|public
name|EnvEntry
name|get
parameter_list|(
name|String
name|key
parameter_list|)
block|{
name|int
name|hash
decl_stmt|;
name|Vector
name|v
decl_stmt|;
name|EnvEntry
name|entry
init|=
literal|null
decl_stmt|;
name|hash
operator|=
name|hashCode
argument_list|(
name|key
argument_list|)
expr_stmt|;
name|v
operator|=
name|table
index|[
name|hash
index|]
expr_stmt|;
if|if
condition|(
name|v
operator|==
literal|null
condition|)
block|{
return|return
literal|null
return|;
block|}
try|try
block|{
name|int
name|index
init|=
name|lookup
argument_list|(
name|v
argument_list|,
name|key
argument_list|)
decl_stmt|;
if|if
condition|(
name|index
operator|>=
literal|0
condition|)
block|{
name|entry
operator|=
operator|(
name|EnvEntry
operator|)
name|v
operator|.
name|elementAt
argument_list|(
name|index
argument_list|)
expr_stmt|;
block|}
block|}
catch|catch
parameter_list|(
name|ArrayIndexOutOfBoundsException
name|e
parameter_list|)
block|{
block|}
return|return
name|entry
return|;
block|}
comment|/**    * Delete an object if it does exist.    */
specifier|public
name|void
name|delete
parameter_list|(
name|String
name|key
parameter_list|)
block|{
name|int
name|hash
decl_stmt|;
name|Vector
name|v
decl_stmt|;
name|hash
operator|=
name|hashCode
argument_list|(
name|key
argument_list|)
expr_stmt|;
name|v
operator|=
name|table
index|[
name|hash
index|]
expr_stmt|;
if|if
condition|(
name|v
operator|==
literal|null
condition|)
block|{
return|return;
block|}
try|try
block|{
name|int
name|index
init|=
name|lookup
argument_list|(
name|v
argument_list|,
name|key
argument_list|)
decl_stmt|;
if|if
condition|(
name|index
operator|>=
literal|0
condition|)
block|{
name|elements
operator|--
expr_stmt|;
comment|// Count
name|v
operator|.
name|removeElementAt
argument_list|(
name|index
argument_list|)
expr_stmt|;
block|}
block|}
catch|catch
parameter_list|(
name|ArrayIndexOutOfBoundsException
name|e
parameter_list|)
block|{
block|}
block|}
specifier|private
specifier|static
specifier|final
name|int
name|lookup
parameter_list|(
name|Vector
name|v
parameter_list|,
name|String
name|key
parameter_list|)
throws|throws
name|ArrayIndexOutOfBoundsException
block|{
name|int
name|len
init|=
name|v
operator|.
name|size
argument_list|()
decl_stmt|;
for|for
control|(
name|int
name|i
init|=
literal|0
init|;
name|i
operator|<
name|len
condition|;
name|i
operator|++
control|)
block|{
name|EnvEntry
name|entry
init|=
operator|(
name|EnvEntry
operator|)
name|v
operator|.
name|elementAt
argument_list|(
name|i
argument_list|)
decl_stmt|;
if|if
condition|(
name|entry
operator|.
name|getHashKey
argument_list|()
operator|.
name|equals
argument_list|(
name|key
argument_list|)
condition|)
block|{
return|return
name|i
return|;
block|}
block|}
return|return
operator|-
literal|1
return|;
block|}
specifier|public
name|Object
name|clone
parameter_list|()
block|{
name|Vector
index|[]
name|copy
init|=
operator|new
name|Vector
index|[
name|size
index|]
decl_stmt|;
for|for
control|(
name|int
name|i
init|=
literal|0
init|;
name|i
operator|<
name|size
condition|;
name|i
operator|++
control|)
block|{
if|if
condition|(
name|table
index|[
name|i
index|]
operator|!=
literal|null
condition|)
block|{
name|copy
index|[
name|i
index|]
operator|=
operator|(
name|Vector
operator|)
name|table
index|[
name|i
index|]
operator|.
name|clone
argument_list|()
expr_stmt|;
comment|// Copies references
comment|/* 	int len = table[i].size();  	copy[i] = new Vector(len); 	try { 	  for(int j=0; j< len; j++) 	    copy[i].addElement(table[i].elementAt(j)); 	} catch(ArrayIndexOutOfBoundsException e) {}*/
block|}
block|}
return|return
operator|new
name|Environment
argument_list|(
name|copy
argument_list|)
return|;
block|}
specifier|public
name|String
name|toString
parameter_list|()
block|{
name|StringBuffer
name|buf
init|=
operator|new
name|StringBuffer
argument_list|()
decl_stmt|;
for|for
control|(
name|int
name|i
init|=
literal|0
init|;
name|i
operator|<
name|size
condition|;
name|i
operator|++
control|)
block|{
if|if
condition|(
name|table
index|[
name|i
index|]
operator|!=
literal|null
condition|)
block|{
name|buf
operator|.
name|append
argument_list|(
name|table
index|[
name|i
index|]
operator|+
literal|"\n"
argument_list|)
expr_stmt|;
block|}
block|}
return|return
name|buf
operator|.
name|toString
argument_list|()
return|;
block|}
specifier|public
name|EnvEntry
index|[]
name|getEntries
parameter_list|()
block|{
name|EnvEntry
index|[]
name|entries
init|=
operator|new
name|EnvEntry
index|[
name|elements
index|]
decl_stmt|;
name|int
name|k
init|=
literal|0
decl_stmt|;
name|Vector
name|v
decl_stmt|;
for|for
control|(
name|int
name|i
init|=
literal|0
init|;
name|i
operator|<
name|size
condition|;
name|i
operator|++
control|)
block|{
if|if
condition|(
operator|(
name|v
operator|=
name|table
index|[
name|i
index|]
operator|)
operator|!=
literal|null
condition|)
block|{
name|int
name|len
init|=
name|v
operator|.
name|size
argument_list|()
decl_stmt|;
try|try
block|{
for|for
control|(
name|int
name|j
init|=
literal|0
init|;
name|j
operator|<
name|len
condition|;
name|j
operator|++
control|)
block|{
name|entries
index|[
name|k
operator|++
index|]
operator|=
operator|(
name|EnvEntry
operator|)
name|v
operator|.
name|elementAt
argument_list|(
name|j
argument_list|)
expr_stmt|;
block|}
block|}
catch|catch
parameter_list|(
name|ArrayIndexOutOfBoundsException
name|e
parameter_list|)
block|{
block|}
block|}
block|}
return|return
name|entries
return|;
block|}
block|}
end_class

end_unit

