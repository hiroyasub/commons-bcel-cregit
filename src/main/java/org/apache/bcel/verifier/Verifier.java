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
name|verifier
package|;
end_package

begin_import
import|import
name|java
operator|.
name|util
operator|.
name|ArrayList
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
name|List
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
name|verifier
operator|.
name|statics
operator|.
name|Pass1Verifier
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
name|verifier
operator|.
name|statics
operator|.
name|Pass2Verifier
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
name|verifier
operator|.
name|statics
operator|.
name|Pass3aVerifier
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
name|verifier
operator|.
name|structurals
operator|.
name|Pass3bVerifier
import|;
end_import

begin_import
import|import
name|org
operator|.
name|apache
operator|.
name|commons
operator|.
name|lang3
operator|.
name|ArrayUtils
import|;
end_import

begin_comment
comment|/**  * A Verifier instance is there to verify a class file according to The Java Virtual  * Machine Specification, 2nd Edition.  *  * Pass-3b-verification includes pass-3a-verification;  * pass-3a-verification includes pass-2-verification;  * pass-2-verification includes pass-1-verification.  *  * A Verifier creates PassVerifier instances to perform the actual verification.  * Verifier instances are usually generated by the VerifierFactory.  *  * @see VerifierFactory  * @see PassVerifier  */
end_comment

begin_class
specifier|public
class|class
name|Verifier
block|{
comment|/**      * Verifies class files.      * This is a simple demonstration of how the API of BCEL's      * class file verifier "JustIce" may be used.      * You should supply command-line arguments which are      * fully qualified namea of the classes to verify. These class files      * must be somewhere in your CLASSPATH (refer to Sun's      * documentation for questions about this) or you must have put the classes      * into the BCEL Repository yourself (via 'addClass(JavaClass)').      */
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
name|System
operator|.
name|out
operator|.
name|println
argument_list|(
literal|"JustIce by Enver Haase, (C) 2001-2002.\n<http://bcel.sourceforge.net>\n<https://commons.apache.org/bcel>\n"
argument_list|)
expr_stmt|;
for|for
control|(
name|int
name|index
init|=
literal|0
init|;
name|index
operator|<
name|args
operator|.
name|length
condition|;
name|index
operator|++
control|)
block|{
try|try
block|{
if|if
condition|(
name|args
index|[
name|index
index|]
operator|.
name|endsWith
argument_list|(
literal|".class"
argument_list|)
condition|)
block|{
specifier|final
name|int
name|dotclasspos
init|=
name|args
index|[
name|index
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
name|index
index|]
operator|=
name|args
index|[
name|index
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
block|}
name|args
index|[
name|index
index|]
operator|=
name|args
index|[
name|index
index|]
operator|.
name|replace
argument_list|(
literal|'/'
argument_list|,
literal|'.'
argument_list|)
expr_stmt|;
name|System
operator|.
name|out
operator|.
name|println
argument_list|(
literal|"Now verifying: "
operator|+
name|args
index|[
name|index
index|]
operator|+
literal|"\n"
argument_list|)
expr_stmt|;
name|verifyType
argument_list|(
name|args
index|[
name|index
index|]
argument_list|)
expr_stmt|;
name|org
operator|.
name|apache
operator|.
name|bcel
operator|.
name|Repository
operator|.
name|clearCache
argument_list|()
expr_stmt|;
name|System
operator|.
name|gc
argument_list|()
expr_stmt|;
block|}
catch|catch
parameter_list|(
specifier|final
name|ClassNotFoundException
name|e
parameter_list|)
block|{
name|e
operator|.
name|printStackTrace
argument_list|()
expr_stmt|;
block|}
block|}
block|}
specifier|static
name|void
name|verifyType
parameter_list|(
specifier|final
name|String
name|fullyQualifiedClassName
parameter_list|)
throws|throws
name|ClassNotFoundException
block|{
specifier|final
name|Verifier
name|verifier
init|=
name|VerifierFactory
operator|.
name|getVerifier
argument_list|(
name|fullyQualifiedClassName
argument_list|)
decl_stmt|;
name|VerificationResult
name|verificationResult
decl_stmt|;
name|verificationResult
operator|=
name|verifier
operator|.
name|doPass1
argument_list|()
expr_stmt|;
name|System
operator|.
name|out
operator|.
name|println
argument_list|(
literal|"Pass 1:\n"
operator|+
name|verificationResult
argument_list|)
expr_stmt|;
name|verificationResult
operator|=
name|verifier
operator|.
name|doPass2
argument_list|()
expr_stmt|;
name|System
operator|.
name|out
operator|.
name|println
argument_list|(
literal|"Pass 2:\n"
operator|+
name|verificationResult
argument_list|)
expr_stmt|;
if|if
condition|(
name|verificationResult
operator|==
name|VerificationResult
operator|.
name|VR_OK
condition|)
block|{
specifier|final
name|JavaClass
name|jc
init|=
name|org
operator|.
name|apache
operator|.
name|bcel
operator|.
name|Repository
operator|.
name|lookupClass
argument_list|(
name|fullyQualifiedClassName
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
name|verificationResult
operator|=
name|verifier
operator|.
name|doPass3a
argument_list|(
name|i
argument_list|)
expr_stmt|;
name|System
operator|.
name|out
operator|.
name|println
argument_list|(
literal|"Pass 3a, method number "
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
name|verificationResult
argument_list|)
expr_stmt|;
name|verificationResult
operator|=
name|verifier
operator|.
name|doPass3b
argument_list|(
name|i
argument_list|)
expr_stmt|;
name|System
operator|.
name|out
operator|.
name|println
argument_list|(
literal|"Pass 3b, method number "
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
name|verificationResult
argument_list|)
expr_stmt|;
block|}
block|}
name|System
operator|.
name|out
operator|.
name|println
argument_list|(
literal|"Warnings:"
argument_list|)
expr_stmt|;
specifier|final
name|String
index|[]
name|warnings
init|=
name|verifier
operator|.
name|getMessages
argument_list|()
decl_stmt|;
if|if
condition|(
name|warnings
operator|.
name|length
operator|==
literal|0
condition|)
block|{
name|System
operator|.
name|out
operator|.
name|println
argument_list|(
literal|"<none>"
argument_list|)
expr_stmt|;
block|}
for|for
control|(
specifier|final
name|String
name|warning
range|:
name|warnings
control|)
block|{
name|System
operator|.
name|out
operator|.
name|println
argument_list|(
name|warning
argument_list|)
expr_stmt|;
block|}
name|System
operator|.
name|out
operator|.
name|println
argument_list|(
literal|"\n"
argument_list|)
expr_stmt|;
comment|// avoid swapping.
name|verifier
operator|.
name|flush
argument_list|()
expr_stmt|;
block|}
comment|/**      * The name of the class this verifier operates on.      */
specifier|private
specifier|final
name|String
name|classname
decl_stmt|;
comment|/** A Pass1Verifier for this Verifier instance. */
specifier|private
name|Pass1Verifier
name|p1v
decl_stmt|;
comment|/** A Pass2Verifier for this Verifier instance. */
specifier|private
name|Pass2Verifier
name|p2v
decl_stmt|;
comment|/** The Pass3aVerifiers for this Verifier instance. Key: Interned string specifying the method number. */
specifier|private
specifier|final
name|Map
argument_list|<
name|String
argument_list|,
name|Pass3aVerifier
argument_list|>
name|p3avs
init|=
operator|new
name|HashMap
argument_list|<>
argument_list|()
decl_stmt|;
comment|/** The Pass3bVerifiers for this Verifier instance. Key: Interned string specifying the method number. */
specifier|private
specifier|final
name|Map
argument_list|<
name|String
argument_list|,
name|Pass3bVerifier
argument_list|>
name|p3bvs
init|=
operator|new
name|HashMap
argument_list|<>
argument_list|()
decl_stmt|;
comment|/**      * Instantiation is done by the VerifierFactory.      *      * @see VerifierFactory      */
name|Verifier
parameter_list|(
specifier|final
name|String
name|fully_qualified_classname
parameter_list|)
block|{
name|classname
operator|=
name|fully_qualified_classname
expr_stmt|;
name|flush
argument_list|()
expr_stmt|;
block|}
comment|/** Returns the VerificationResult for the given pass. */
specifier|public
name|VerificationResult
name|doPass1
parameter_list|()
block|{
if|if
condition|(
name|p1v
operator|==
literal|null
condition|)
block|{
name|p1v
operator|=
operator|new
name|Pass1Verifier
argument_list|(
name|this
argument_list|)
expr_stmt|;
block|}
return|return
name|p1v
operator|.
name|verify
argument_list|()
return|;
block|}
comment|/** Returns the VerificationResult for the given pass. */
specifier|public
name|VerificationResult
name|doPass2
parameter_list|()
block|{
if|if
condition|(
name|p2v
operator|==
literal|null
condition|)
block|{
name|p2v
operator|=
operator|new
name|Pass2Verifier
argument_list|(
name|this
argument_list|)
expr_stmt|;
block|}
return|return
name|p2v
operator|.
name|verify
argument_list|()
return|;
block|}
comment|/** Returns the VerificationResult for the given pass. */
specifier|public
name|VerificationResult
name|doPass3a
parameter_list|(
specifier|final
name|int
name|method_no
parameter_list|)
block|{
specifier|final
name|String
name|key
init|=
name|Integer
operator|.
name|toString
argument_list|(
name|method_no
argument_list|)
decl_stmt|;
name|Pass3aVerifier
name|p3av
decl_stmt|;
name|p3av
operator|=
name|p3avs
operator|.
name|get
argument_list|(
name|key
argument_list|)
expr_stmt|;
if|if
condition|(
name|p3avs
operator|.
name|get
argument_list|(
name|key
argument_list|)
operator|==
literal|null
condition|)
block|{
name|p3av
operator|=
operator|new
name|Pass3aVerifier
argument_list|(
name|this
argument_list|,
name|method_no
argument_list|)
expr_stmt|;
name|p3avs
operator|.
name|put
argument_list|(
name|key
argument_list|,
name|p3av
argument_list|)
expr_stmt|;
block|}
return|return
name|p3av
operator|.
name|verify
argument_list|()
return|;
block|}
comment|/** Returns the VerificationResult for the given pass. */
specifier|public
name|VerificationResult
name|doPass3b
parameter_list|(
specifier|final
name|int
name|method_no
parameter_list|)
block|{
specifier|final
name|String
name|key
init|=
name|Integer
operator|.
name|toString
argument_list|(
name|method_no
argument_list|)
decl_stmt|;
name|Pass3bVerifier
name|p3bv
decl_stmt|;
name|p3bv
operator|=
name|p3bvs
operator|.
name|get
argument_list|(
name|key
argument_list|)
expr_stmt|;
if|if
condition|(
name|p3bvs
operator|.
name|get
argument_list|(
name|key
argument_list|)
operator|==
literal|null
condition|)
block|{
name|p3bv
operator|=
operator|new
name|Pass3bVerifier
argument_list|(
name|this
argument_list|,
name|method_no
argument_list|)
expr_stmt|;
name|p3bvs
operator|.
name|put
argument_list|(
name|key
argument_list|,
name|p3bv
argument_list|)
expr_stmt|;
block|}
return|return
name|p3bv
operator|.
name|verify
argument_list|()
return|;
block|}
comment|/**      * Forget everything known about the class file; that means, really      * start a new verification of a possibly different class file from      * BCEL's repository.      *      */
specifier|public
name|void
name|flush
parameter_list|()
block|{
name|p1v
operator|=
literal|null
expr_stmt|;
name|p2v
operator|=
literal|null
expr_stmt|;
name|p3avs
operator|.
name|clear
argument_list|()
expr_stmt|;
name|p3bvs
operator|.
name|clear
argument_list|()
expr_stmt|;
block|}
comment|/**      * Returns the name of the class this verifier operates on.      * This is particularly interesting when this verifier was created      * recursively by another Verifier and you got a reference to this      * Verifier by the getVerifiers() method of the VerifierFactory.      * @see VerifierFactory      */
specifier|public
specifier|final
name|String
name|getClassName
parameter_list|()
block|{
return|return
name|classname
return|;
block|}
comment|/**      * This returns all the (warning) messages collected during verification.      * A prefix shows from which verifying pass a message originates.      */
specifier|public
name|String
index|[]
name|getMessages
parameter_list|()
throws|throws
name|ClassNotFoundException
block|{
specifier|final
name|List
argument_list|<
name|String
argument_list|>
name|messages
init|=
operator|new
name|ArrayList
argument_list|<>
argument_list|()
decl_stmt|;
if|if
condition|(
name|p1v
operator|!=
literal|null
condition|)
block|{
specifier|final
name|String
index|[]
name|p1m
init|=
name|p1v
operator|.
name|getMessages
argument_list|()
decl_stmt|;
for|for
control|(
specifier|final
name|String
name|element
range|:
name|p1m
control|)
block|{
name|messages
operator|.
name|add
argument_list|(
literal|"Pass 1: "
operator|+
name|element
argument_list|)
expr_stmt|;
block|}
block|}
if|if
condition|(
name|p2v
operator|!=
literal|null
condition|)
block|{
specifier|final
name|String
index|[]
name|p2m
init|=
name|p2v
operator|.
name|getMessages
argument_list|()
decl_stmt|;
for|for
control|(
specifier|final
name|String
name|element
range|:
name|p2m
control|)
block|{
name|messages
operator|.
name|add
argument_list|(
literal|"Pass 2: "
operator|+
name|element
argument_list|)
expr_stmt|;
block|}
block|}
for|for
control|(
specifier|final
name|Pass3aVerifier
name|pv
range|:
name|p3avs
operator|.
name|values
argument_list|()
control|)
block|{
specifier|final
name|String
index|[]
name|p3am
init|=
name|pv
operator|.
name|getMessages
argument_list|()
decl_stmt|;
specifier|final
name|int
name|meth
init|=
name|pv
operator|.
name|getMethodNo
argument_list|()
decl_stmt|;
for|for
control|(
specifier|final
name|String
name|element
range|:
name|p3am
control|)
block|{
name|messages
operator|.
name|add
argument_list|(
literal|"Pass 3a, method "
operator|+
name|meth
operator|+
literal|" ('"
operator|+
name|org
operator|.
name|apache
operator|.
name|bcel
operator|.
name|Repository
operator|.
name|lookupClass
argument_list|(
name|classname
argument_list|)
operator|.
name|getMethods
argument_list|()
index|[
name|meth
index|]
operator|+
literal|"'): "
operator|+
name|element
argument_list|)
expr_stmt|;
block|}
block|}
for|for
control|(
specifier|final
name|Pass3bVerifier
name|pv
range|:
name|p3bvs
operator|.
name|values
argument_list|()
control|)
block|{
specifier|final
name|String
index|[]
name|p3bm
init|=
name|pv
operator|.
name|getMessages
argument_list|()
decl_stmt|;
specifier|final
name|int
name|meth
init|=
name|pv
operator|.
name|getMethodNo
argument_list|()
decl_stmt|;
for|for
control|(
specifier|final
name|String
name|element
range|:
name|p3bm
control|)
block|{
name|messages
operator|.
name|add
argument_list|(
literal|"Pass 3b, method "
operator|+
name|meth
operator|+
literal|" ('"
operator|+
name|org
operator|.
name|apache
operator|.
name|bcel
operator|.
name|Repository
operator|.
name|lookupClass
argument_list|(
name|classname
argument_list|)
operator|.
name|getMethods
argument_list|()
index|[
name|meth
index|]
operator|+
literal|"'): "
operator|+
name|element
argument_list|)
expr_stmt|;
block|}
block|}
return|return
name|messages
operator|.
name|toArray
argument_list|(
name|ArrayUtils
operator|.
name|EMPTY_STRING_ARRAY
argument_list|)
return|;
block|}
block|}
end_class

end_unit

