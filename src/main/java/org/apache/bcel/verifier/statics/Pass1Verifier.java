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
operator|.
name|statics
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
name|ClassFormatException
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
name|PassVerifier
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
name|VerificationResult
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
name|Verifier
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
name|exc
operator|.
name|LoadingException
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
name|exc
operator|.
name|Utility
import|;
end_import

begin_comment
comment|/**  * This PassVerifier verifies a class file according to pass 1 as  * described in The Java Virtual Machine Specification, 2nd edition.  * More detailed information is to be found at the do_verify() method's  * documentation.  *  * @see #do_verify()  */
end_comment

begin_class
specifier|public
specifier|final
class|class
name|Pass1Verifier
extends|extends
name|PassVerifier
block|{
comment|/**      * DON'T USE THIS EVEN PRIVATELY! USE getJavaClass() INSTEAD.      * @see #getJavaClass()      */
specifier|private
name|JavaClass
name|jc
decl_stmt|;
comment|/**      * The Verifier that created this.      */
specifier|private
specifier|final
name|Verifier
name|myOwner
decl_stmt|;
comment|/**      * Used to load in and return the myOwner-matching JavaClass object when needed.      * Avoids loading in a class file when it's not really needed!      */
specifier|private
name|JavaClass
name|getJavaClass
parameter_list|()
block|{
if|if
condition|(
name|jc
operator|==
literal|null
condition|)
block|{
try|try
block|{
name|jc
operator|=
name|Repository
operator|.
name|lookupClass
argument_list|(
name|myOwner
operator|.
name|getClassName
argument_list|()
argument_list|)
expr_stmt|;
block|}
catch|catch
parameter_list|(
specifier|final
name|ClassNotFoundException
name|e
parameter_list|)
block|{
comment|// FIXME: currently, Pass1Verifier treats jc == null as a special
comment|// case, so we don't need to do anything here.  A better solution
comment|// would be to simply throw the ClassNotFoundException
comment|// out of this method.
block|}
block|}
return|return
name|jc
return|;
block|}
comment|/**      * Should only be instantiated by a Verifier.      *      * @see Verifier      */
specifier|public
name|Pass1Verifier
parameter_list|(
specifier|final
name|Verifier
name|owner
parameter_list|)
block|{
name|myOwner
operator|=
name|owner
expr_stmt|;
block|}
comment|/**      * Pass-one verification basically means loading in a class file.      * The Java Virtual Machine Specification is not too precise about      * what makes the difference between passes one and two.      * The answer is that only pass one is performed on a class file as      * long as its resolution is not requested; whereas pass two and      * pass three are performed during the resolution process.      * Only four constraints to be checked are explicitly stated by      * The Java Virtual Machine Specification, 2nd edition:      *<UL>      *<LI>The first four bytes must contain the right magic number (0xCAFEBABE).      *<LI>All recognized attributes must be of the proper length.      *<LI>The class file must not be truncated or have extra bytes at the end.      *<LI>The constant pool must not contain any superficially unrecognizable information.      *</UL>      * A more in-depth documentation of what pass one should do was written by      *<A HREF=mailto:pwfong@cs.sfu.ca>Philip W. L. Fong</A>:      *<UL>      *<LI> the file should not be truncated.      *<LI> the file should not have extra bytes at the end.      *<LI> all variable-length structures should be well-formatted:      *<UL>      *<LI> there should only be constant_pool_count-1 many entries in the constant pool.      *<LI> all constant pool entries should have size the same as indicated by their type tag.      *<LI> there are exactly interfaces_count many entries in the interfaces array of the class file.      *<LI> there are exactly fields_count many entries in the fields array of the class file.      *<LI> there are exactly methods_count many entries in the methods array of the class file.      *<LI> there are exactly attributes_count many entries in the attributes array of the class file,      *        fields, methods, and code attribute.      *<LI> there should be exactly attribute_length many bytes in each attribute.      *        Inconsistency between attribute_length and the actually size of the attribute content should be uncovered.      *        For example, in an Exceptions attribute, the actual number of exceptions as required by the number_of_exceptions field      *        might yeild an attribute size that doesn't match the attribute_length. Such an anomaly should be detected.      *<LI> all attributes should have proper length. In particular, under certain context (e.g. while parsing method_info),      *        recognizable attributes (e.g. "Code" attribute) should have correct format (e.g. attribute_length is 2).      *</UL>      *<LI> Also, certain constant values are checked for validity:      *<UL>      *<LI> The magic number should be 0xCAFEBABE.      *<LI> The major and minor version numbers are valid.      *<LI> All the constant pool type tags are recognizable.      *<LI> All undocumented access flags are masked off before use. Strictly speaking, this is not really a check.      *<LI> The field this_class should point to a string that represents a legal non-array class name,      *        and this name should be the same as the class file being loaded.      *<LI> the field super_class should point to a string that represents a legal non-array class name.      *<LI> Because some of the above checks require cross referencing the constant pool entries,      *        guards are set up to make sure that the referenced entries are of the right type and the indices      *        are within the legal range (0&lt; index&lt; constant_pool_count).      *</UL>      *<LI> Extra checks done in pass 1:      *<UL>      *<LI> the constant values of static fields should have the same type as the fields.      *<LI> the number of words in a parameter list does not exceed 255 and locals_max.      *<LI> the name and signature of fields and methods are verified to be of legal format.      *</UL>      *</UL>      * (From the Paper<A HREF="http://www.cs.sfu.ca/people/GradStudents/pwfong/personal/JVM/pass1/">      * The Mysterious Pass One, first draft, September 2, 1997</A>.)      *      *<P>However, most of this is done by parsing a class file or generating a class file into BCEL's internal data structure.      *<B>Therefore, all that is really done here is look up the class file from BCEL's repository.</B>      * This is also motivated by the fact that some omitted things      * (like the check for extra bytes at the end of the class file) are handy when actually using BCEL to repair a class file      * (otherwise you would not be able to load it into BCEL).</P>      *      * @see org.apache.bcel.Repository      * @see org.apache.bcel.Const#JVM_CLASSFILE_MAGIC      */
annotation|@
name|Override
specifier|public
name|VerificationResult
name|do_verify
parameter_list|()
block|{
name|JavaClass
name|jc
decl_stmt|;
try|try
block|{
name|jc
operator|=
name|getJavaClass
argument_list|()
expr_stmt|;
comment|//loads in the class file if not already done.
comment|/* If we find more constraints to check, we should do this in an own method. */
if|if
condition|(
name|jc
operator|!=
literal|null
operator|&&
operator|!
name|myOwner
operator|.
name|getClassName
argument_list|()
operator|.
name|equals
argument_list|(
name|jc
operator|.
name|getClassName
argument_list|()
argument_list|)
condition|)
block|{
comment|// This should maybe caught by BCEL: In case of renamed .class files we get wrong
comment|// JavaClass objects here.
throw|throw
operator|new
name|LoadingException
argument_list|(
literal|"Wrong name: the internal name of the .class file '"
operator|+
name|jc
operator|.
name|getClassName
argument_list|()
operator|+
literal|"' does not match the file's name '"
operator|+
name|myOwner
operator|.
name|getClassName
argument_list|()
operator|+
literal|"'."
argument_list|)
throw|;
block|}
block|}
catch|catch
parameter_list|(
specifier|final
name|LoadingException
decl||
name|ClassFormatException
name|e
parameter_list|)
block|{
return|return
operator|new
name|VerificationResult
argument_list|(
name|VerificationResult
operator|.
name|VERIFIED_REJECTED
argument_list|,
name|e
operator|.
name|getMessage
argument_list|()
argument_list|)
return|;
block|}
catch|catch
parameter_list|(
specifier|final
name|RuntimeException
name|e
parameter_list|)
block|{
comment|// BCEL does not catch every possible RuntimeException; e.g. if
comment|// a constant pool index is referenced that does not exist.
return|return
operator|new
name|VerificationResult
argument_list|(
name|VerificationResult
operator|.
name|VERIFIED_REJECTED
argument_list|,
literal|"Parsing via BCEL did not succeed. "
operator|+
name|e
operator|.
name|getClass
argument_list|()
operator|.
name|getName
argument_list|()
operator|+
literal|" occured:\n"
operator|+
name|Utility
operator|.
name|getStackTrace
argument_list|(
name|e
argument_list|)
argument_list|)
return|;
block|}
if|if
condition|(
name|jc
operator|!=
literal|null
condition|)
block|{
return|return
name|VerificationResult
operator|.
name|VR_OK
return|;
block|}
comment|//TODO: Maybe change Repository's behavior to throw a LoadingException instead of just returning "null"
comment|//      if a class file cannot be found or in another way be looked up.
return|return
operator|new
name|VerificationResult
argument_list|(
name|VerificationResult
operator|.
name|VERIFIED_REJECTED
argument_list|,
literal|"Repository.lookup() failed. FILE NOT FOUND?"
argument_list|)
return|;
block|}
comment|/**      * Currently this returns an empty array of String.      * One could parse the error messages of BCEL      * (written to java.lang.System.err) when loading      * a class file such as detecting unknown attributes      * or trailing garbage at the end of a class file.      * However, Markus Dahm does not like the idea so this      * method is currently useless and therefore marked as      *<B>TODO</B>.      */
annotation|@
name|Override
specifier|public
name|String
index|[]
name|getMessages
parameter_list|()
block|{
comment|// This method is only here to override the javadoc-comment.
return|return
name|super
operator|.
name|getMessages
argument_list|()
return|;
block|}
block|}
end_class

end_unit

