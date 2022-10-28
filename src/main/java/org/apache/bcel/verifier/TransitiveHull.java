begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
begin_comment
comment|/*  * Licensed to the Apache Software Foundation (ASF) under one or more  * contributor license agreements.  See the NOTICE file distributed with  * this work for additional information regarding copyright ownership.  * The ASF licenses this file to You under the Apache License, Version 2.0  * (the "License"); you may not use this file except in compliance with  * the License.  You may obtain a copy of the License at  *  *      http://www.apache.org/licenses/LICENSE-2.0  *  *  Unless required by applicable law or agreed to in writing, software  *  distributed under the License is distributed on an "AS IS" BASIS,  *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  *  See the License for the specific language governing permissions and  *  limitations under the License.  */
end_comment

begin_package
package|package
name|org
operator|.
name|apache
operator|.
name|bcel
operator|.
name|verifier
package|;
end_package

begin_import
import|import
name|org
operator|.
name|apache
operator|.
name|bcel
operator|.
name|Repository
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
name|classfile
operator|.
name|JavaClass
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
name|classfile
operator|.
name|Utility
import|;
end_import

begin_comment
comment|/**  * This class has a main method implementing a demonstration program of how to use the VerifierFactoryObserver. It  * transitively verifies all class files encountered; this may take up a lot of time and, more notably, memory.  */
end_comment

begin_class
specifier|public
class|class
name|TransitiveHull
implements|implements
name|VerifierFactoryObserver
block|{
comment|/**      * This method implements a demonstration program of how to use the VerifierFactoryObserver. It transitively verifies      * all class files encountered; this may take up a lot of time and, more notably, memory.      */
specifier|public
specifier|static
name|void
name|main
parameter_list|(
specifier|final
name|String
index|[]
name|args
parameter_list|)
block|{
if|if
condition|(
name|args
operator|.
name|length
operator|!=
literal|1
condition|)
block|{
name|System
operator|.
name|out
operator|.
name|println
argument_list|(
literal|"Need exactly one argument: The root class to verify."
argument_list|)
expr_stmt|;
name|System
operator|.
name|exit
argument_list|(
literal|1
argument_list|)
expr_stmt|;
block|}
specifier|final
name|int
name|dotclasspos
init|=
name|args
index|[
literal|0
index|]
operator|.
name|lastIndexOf
argument_list|(
literal|".class"
argument_list|)
decl_stmt|;
if|if
condition|(
name|dotclasspos
operator|!=
operator|-
literal|1
condition|)
block|{
name|args
index|[
literal|0
index|]
operator|=
name|args
index|[
literal|0
index|]
operator|.
name|substring
argument_list|(
literal|0
argument_list|,
name|dotclasspos
argument_list|)
expr_stmt|;
block|}
name|args
index|[
literal|0
index|]
operator|=
name|Utility
operator|.
name|pathToPackage
argument_list|(
name|args
index|[
literal|0
index|]
argument_list|)
expr_stmt|;
specifier|final
name|TransitiveHull
name|th
init|=
operator|new
name|TransitiveHull
argument_list|()
decl_stmt|;
name|VerifierFactory
operator|.
name|attach
argument_list|(
name|th
argument_list|)
expr_stmt|;
name|VerifierFactory
operator|.
name|getVerifier
argument_list|(
name|args
index|[
literal|0
index|]
argument_list|)
expr_stmt|;
comment|// the observer is called back and does the actual trick.
name|VerifierFactory
operator|.
name|detach
argument_list|(
name|th
argument_list|)
expr_stmt|;
block|}
comment|/** Used for indentation. */
specifier|private
name|int
name|indent
decl_stmt|;
comment|/** Not publicly instantiable. */
specifier|private
name|TransitiveHull
parameter_list|()
block|{
block|}
comment|/* Implementing VerifierFactoryObserver. */
annotation|@
name|Override
specifier|public
name|void
name|update
parameter_list|(
specifier|final
name|String
name|className
parameter_list|)
block|{
name|System
operator|.
name|gc
argument_list|()
expr_stmt|;
comment|// avoid swapping if possible.
for|for
control|(
name|int
name|i
init|=
literal|0
init|;
name|i
operator|<
name|indent
condition|;
name|i
operator|++
control|)
block|{
name|System
operator|.
name|out
operator|.
name|print
argument_list|(
literal|" "
argument_list|)
expr_stmt|;
block|}
name|System
operator|.
name|out
operator|.
name|println
argument_list|(
name|className
argument_list|)
expr_stmt|;
name|indent
operator|+=
literal|1
expr_stmt|;
specifier|final
name|Verifier
name|v
init|=
name|VerifierFactory
operator|.
name|getVerifier
argument_list|(
name|className
argument_list|)
decl_stmt|;
name|VerificationResult
name|vr
decl_stmt|;
name|vr
operator|=
name|v
operator|.
name|doPass1
argument_list|()
expr_stmt|;
if|if
condition|(
name|vr
operator|!=
name|VerificationResult
operator|.
name|VR_OK
condition|)
block|{
name|System
operator|.
name|out
operator|.
name|println
argument_list|(
literal|"Pass 1:\n"
operator|+
name|vr
argument_list|)
expr_stmt|;
block|}
name|vr
operator|=
name|v
operator|.
name|doPass2
argument_list|()
expr_stmt|;
if|if
condition|(
name|vr
operator|!=
name|VerificationResult
operator|.
name|VR_OK
condition|)
block|{
name|System
operator|.
name|out
operator|.
name|println
argument_list|(
literal|"Pass 2:\n"
operator|+
name|vr
argument_list|)
expr_stmt|;
block|}
if|if
condition|(
name|vr
operator|==
name|VerificationResult
operator|.
name|VR_OK
condition|)
block|{
try|try
block|{
specifier|final
name|JavaClass
name|jc
init|=
name|Repository
operator|.
name|lookupClass
argument_list|(
name|v
operator|.
name|getClassName
argument_list|()
argument_list|)
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
name|jc
operator|.
name|getMethods
argument_list|()
operator|.
name|length
condition|;
name|i
operator|++
control|)
block|{
name|vr
operator|=
name|v
operator|.
name|doPass3a
argument_list|(
name|i
argument_list|)
expr_stmt|;
if|if
condition|(
name|vr
operator|!=
name|VerificationResult
operator|.
name|VR_OK
condition|)
block|{
name|System
operator|.
name|out
operator|.
name|println
argument_list|(
name|v
operator|.
name|getClassName
argument_list|()
operator|+
literal|", Pass 3a, method "
operator|+
name|i
operator|+
literal|" ['"
operator|+
name|jc
operator|.
name|getMethods
argument_list|()
index|[
name|i
index|]
operator|+
literal|"']:\n"
operator|+
name|vr
argument_list|)
expr_stmt|;
block|}
name|vr
operator|=
name|v
operator|.
name|doPass3b
argument_list|(
name|i
argument_list|)
expr_stmt|;
if|if
condition|(
name|vr
operator|!=
name|VerificationResult
operator|.
name|VR_OK
condition|)
block|{
name|System
operator|.
name|out
operator|.
name|println
argument_list|(
name|v
operator|.
name|getClassName
argument_list|()
operator|+
literal|", Pass 3b, method "
operator|+
name|i
operator|+
literal|" ['"
operator|+
name|jc
operator|.
name|getMethods
argument_list|()
index|[
name|i
index|]
operator|+
literal|"']:\n"
operator|+
name|vr
argument_list|)
expr_stmt|;
block|}
block|}
block|}
catch|catch
parameter_list|(
specifier|final
name|ClassNotFoundException
name|e
parameter_list|)
block|{
name|System
operator|.
name|err
operator|.
name|println
argument_list|(
literal|"Could not find class "
operator|+
name|v
operator|.
name|getClassName
argument_list|()
operator|+
literal|" in Repository"
argument_list|)
expr_stmt|;
block|}
block|}
name|indent
operator|-=
literal|1
expr_stmt|;
block|}
block|}
end_class

end_unit

