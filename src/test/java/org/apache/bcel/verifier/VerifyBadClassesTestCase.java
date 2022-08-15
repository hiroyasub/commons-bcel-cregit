begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
begin_comment
comment|/*  * Licensed to the Apache Software Foundation (ASF) under one or more  * contributor license agreements.  See the NOTICE file distributed with  * this work for additional information regarding copyright ownership.  * The ASF licenses this file to You under the Apache License, Version 2.0  * (the "License"); you may not use this file except in compliance with  * the License.  You may obtain a copy of the License at  *  *   http://www.apache.org/licenses/LICENSE-2.0  *  * Unless required by applicable law or agreed to in writing, software  * distributed under the License is distributed on an "AS IS" BASIS,  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  * See the License for the specific language governing permissions and  * limitations under the License.  */
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
import|import static
name|org
operator|.
name|junit
operator|.
name|jupiter
operator|.
name|api
operator|.
name|Assertions
operator|.
name|assertTrue
import|;
end_import

begin_import
import|import static
name|org
operator|.
name|junit
operator|.
name|jupiter
operator|.
name|api
operator|.
name|Assertions
operator|.
name|fail
import|;
end_import

begin_import
import|import
name|java
operator|.
name|io
operator|.
name|ByteArrayOutputStream
import|;
end_import

begin_import
import|import
name|java
operator|.
name|io
operator|.
name|File
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
name|ArrayList
import|;
end_import

begin_import
import|import
name|java
operator|.
name|util
operator|.
name|Arrays
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
name|org
operator|.
name|apache
operator|.
name|commons
operator|.
name|exec
operator|.
name|CommandLine
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
name|exec
operator|.
name|DefaultExecuteResultHandler
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
name|exec
operator|.
name|DefaultExecutor
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
name|exec
operator|.
name|ExecuteException
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
name|exec
operator|.
name|ExecuteWatchdog
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
name|exec
operator|.
name|PumpStreamHandler
import|;
end_import

begin_import
import|import
name|org
operator|.
name|junit
operator|.
name|jupiter
operator|.
name|api
operator|.
name|Test
import|;
end_import

begin_comment
comment|/**  * Test a number of BCEL issues related to running the Verifier on  * a bad or malformed .class file and having it die with an exception  * rather than report a verification failure.  */
end_comment

begin_class
specifier|public
class|class
name|VerifyBadClassesTestCase
block|{
specifier|private
name|List
argument_list|<
name|String
argument_list|>
name|buildVerifyCommand
parameter_list|(
specifier|final
name|String
name|className
parameter_list|,
specifier|final
name|String
name|testDir
parameter_list|)
block|{
specifier|final
name|List
argument_list|<
name|String
argument_list|>
name|command
init|=
operator|new
name|ArrayList
argument_list|<>
argument_list|()
decl_stmt|;
name|command
operator|.
name|add
argument_list|(
literal|"java"
argument_list|)
expr_stmt|;
name|command
operator|.
name|add
argument_list|(
literal|"-ea"
argument_list|)
expr_stmt|;
name|command
operator|.
name|add
argument_list|(
literal|"-classpath"
argument_list|)
expr_stmt|;
name|command
operator|.
name|add
argument_list|(
name|System
operator|.
name|getProperty
argument_list|(
literal|"java.class.path"
argument_list|)
operator|+
literal|":"
operator|+
name|testDir
argument_list|)
expr_stmt|;
name|command
operator|.
name|add
argument_list|(
literal|"org.apache.bcel.verifier.Verifier"
argument_list|)
expr_stmt|;
name|command
operator|.
name|add
argument_list|(
name|className
argument_list|)
expr_stmt|;
return|return
name|command
return|;
block|}
comment|/**      * Runs the given command synchronously in the given directory. If the      * command completes normally, returns a {@link Status} object capturing the command, exit status,      * and output from the process.      *      * @param command the command to be run in the process      * @return a String capturing the error output of executing the command      * @throws ExecuteException if executor fails      * @throws IOException if executor fails      */
specifier|private
name|String
name|run
parameter_list|(
specifier|final
name|List
argument_list|<
name|String
argument_list|>
name|command
parameter_list|)
throws|throws
name|ExecuteException
throws|,
name|IOException
block|{
comment|/** The process timeout in milliseconds. Defaults to 30 seconds. */
specifier|final
name|long
name|timeout
init|=
literal|30
operator|*
literal|1000
decl_stmt|;
specifier|final
name|String
index|[]
name|args
init|=
name|command
operator|.
name|toArray
argument_list|(
operator|new
name|String
index|[
literal|0
index|]
argument_list|)
decl_stmt|;
specifier|final
name|CommandLine
name|cmdLine
init|=
operator|new
name|CommandLine
argument_list|(
name|args
index|[
literal|0
index|]
argument_list|)
decl_stmt|;
comment|// constructor requires executable name
name|cmdLine
operator|.
name|addArguments
argument_list|(
name|Arrays
operator|.
name|copyOfRange
argument_list|(
name|args
argument_list|,
literal|1
argument_list|,
name|args
operator|.
name|length
argument_list|)
argument_list|)
expr_stmt|;
specifier|final
name|DefaultExecuteResultHandler
name|resultHandler
init|=
operator|new
name|DefaultExecuteResultHandler
argument_list|()
decl_stmt|;
specifier|final
name|DefaultExecutor
name|executor
init|=
operator|new
name|DefaultExecutor
argument_list|()
decl_stmt|;
specifier|final
name|ExecuteWatchdog
name|watchdog
init|=
operator|new
name|ExecuteWatchdog
argument_list|(
name|timeout
argument_list|)
decl_stmt|;
name|executor
operator|.
name|setWatchdog
argument_list|(
name|watchdog
argument_list|)
expr_stmt|;
specifier|final
name|ByteArrayOutputStream
name|outStream
init|=
operator|new
name|ByteArrayOutputStream
argument_list|()
decl_stmt|;
specifier|final
name|ByteArrayOutputStream
name|errStream
init|=
operator|new
name|ByteArrayOutputStream
argument_list|()
decl_stmt|;
specifier|final
name|PumpStreamHandler
name|streamHandler
init|=
operator|new
name|PumpStreamHandler
argument_list|(
name|outStream
argument_list|,
name|errStream
argument_list|)
decl_stmt|;
name|executor
operator|.
name|setStreamHandler
argument_list|(
name|streamHandler
argument_list|)
expr_stmt|;
name|executor
operator|.
name|execute
argument_list|(
name|cmdLine
argument_list|,
name|resultHandler
argument_list|)
expr_stmt|;
name|int
name|exitValue
init|=
operator|-
literal|1
decl_stmt|;
try|try
block|{
name|resultHandler
operator|.
name|waitFor
argument_list|()
expr_stmt|;
name|exitValue
operator|=
name|resultHandler
operator|.
name|getExitValue
argument_list|()
expr_stmt|;
block|}
catch|catch
parameter_list|(
specifier|final
name|InterruptedException
name|e
parameter_list|)
block|{
comment|// Ignore exception, but watchdog.killedProcess() records that the process timed out.
block|}
specifier|final
name|boolean
name|timedOut
init|=
name|executor
operator|.
name|isFailure
argument_list|(
name|exitValue
argument_list|)
operator|&&
name|watchdog
operator|.
name|killedProcess
argument_list|()
decl_stmt|;
if|if
condition|(
name|timedOut
condition|)
block|{
return|return
literal|"Command timed out."
return|;
block|}
comment|//return "stdout: " + outStream.toString() + "\nstderr: " + errStream.toString();
return|return
name|errStream
operator|.
name|toString
argument_list|()
return|;
block|}
comment|/**      * BCEL-303: AssertionViolatedException in Pass 3A Verification of invoke instructions      */
annotation|@
name|Test
specifier|public
name|void
name|testB303
parameter_list|()
block|{
name|testVerify
argument_list|(
literal|"issue303/example"
argument_list|,
literal|"A"
argument_list|)
expr_stmt|;
block|}
comment|/**      * BCEL-307: ClassFormatException thrown in Pass 3A verification      */
annotation|@
name|Test
specifier|public
name|void
name|testB307
parameter_list|()
block|{
name|testVerify
argument_list|(
literal|"issue307/example"
argument_list|,
literal|"A"
argument_list|)
expr_stmt|;
block|}
comment|/**      * BCEL-308: NullPointerException in Verifier Pass 3A      */
annotation|@
name|Test
specifier|public
name|void
name|testB308
parameter_list|()
block|{
name|testVerify
argument_list|(
literal|"issue308"
argument_list|,
literal|"Hello"
argument_list|)
expr_stmt|;
block|}
comment|/**      * BCEL-309: NegativeArraySizeException when Code attribute length is negative      */
annotation|@
name|Test
specifier|public
name|void
name|testB309
parameter_list|()
block|{
name|testVerify
argument_list|(
literal|"issue309"
argument_list|,
literal|"Hello"
argument_list|)
expr_stmt|;
block|}
comment|/**      * BCEL-310: ArrayIndexOutOfBounds in Verifier Pass 3A      */
annotation|@
name|Test
specifier|public
name|void
name|testB310
parameter_list|()
block|{
name|testVerify
argument_list|(
literal|"issue310"
argument_list|,
literal|"Hello"
argument_list|)
expr_stmt|;
block|}
comment|/**      * BCEL-311: ClassCastException in Verifier Pass 2      */
annotation|@
name|Test
specifier|public
name|void
name|testB311
parameter_list|()
block|{
name|testVerify
argument_list|(
literal|"issue311"
argument_list|,
literal|"Hello"
argument_list|)
expr_stmt|;
block|}
comment|/**      * BCEL-312: AssertionViolation: INTERNAL ERROR Please adapt StringRepresentation      *           to deal with ConstantPackage in Verifier Pass 2      */
annotation|@
name|Test
specifier|public
name|void
name|testB312
parameter_list|()
block|{
name|testVerify
argument_list|(
literal|"issue312"
argument_list|,
literal|"Hello"
argument_list|)
expr_stmt|;
block|}
comment|/**      * BCEL-313: ClassFormatException: Invalid signature: Ljava/lang/String)V in Verifier Pass 3A      */
annotation|@
name|Test
specifier|public
name|void
name|testB313
parameter_list|()
block|{
name|testVerify
argument_list|(
literal|"issue313"
argument_list|,
literal|"Hello"
argument_list|)
expr_stmt|;
block|}
comment|/**      * BCEL-337: StringIndexOutOfBounds in Pass 2 Verification of empty method names in the constant pool      */
annotation|@
name|Test
specifier|public
name|void
name|testB337
parameter_list|()
block|{
name|testVerify
argument_list|(
literal|"issue337/example"
argument_list|,
literal|"A"
argument_list|)
expr_stmt|;
block|}
comment|/**      * Note that the test classes are bad or malformed and this causes the      * animal-sniffer-maven-plugin to fail during the build/verification      * process. I was not able to figure out the right incantations to get      * it to ignore these files.  Hence, their file extension is '.classx'      * to avoid this problem.  As part of the test process we rename them      * to '.class' and then back to '.classx' after the test.  If we can      * get animal-sniffer to ignore the files, these steps could be omitted.      */
specifier|private
name|void
name|testVerify
parameter_list|(
specifier|final
name|String
name|directory
parameter_list|,
specifier|final
name|String
name|className
parameter_list|)
block|{
specifier|final
name|String
name|baseDir
init|=
literal|"target/test-classes"
decl_stmt|;
specifier|final
name|String
name|testDir
init|=
name|baseDir
operator|+
operator|(
name|directory
operator|.
name|isEmpty
argument_list|()
condition|?
literal|""
else|:
literal|"/"
operator|+
name|directory
operator|)
decl_stmt|;
specifier|final
name|File
name|origFile
init|=
operator|new
name|File
argument_list|(
name|testDir
operator|+
literal|"/"
operator|+
name|className
operator|+
literal|".classx"
argument_list|)
decl_stmt|;
specifier|final
name|File
name|testFile
init|=
operator|new
name|File
argument_list|(
name|testDir
operator|+
literal|"/"
operator|+
name|className
operator|+
literal|".class"
argument_list|)
decl_stmt|;
if|if
condition|(
operator|!
name|origFile
operator|.
name|renameTo
argument_list|(
name|testFile
argument_list|)
condition|)
block|{
name|fail
argument_list|(
literal|"Failed to rename orig file"
argument_list|)
expr_stmt|;
block|}
name|String
name|result
decl_stmt|;
try|try
block|{
name|result
operator|=
name|run
argument_list|(
name|buildVerifyCommand
argument_list|(
name|className
argument_list|,
name|testDir
argument_list|)
argument_list|)
expr_stmt|;
block|}
catch|catch
parameter_list|(
specifier|final
name|Exception
name|e
parameter_list|)
block|{
name|result
operator|=
name|e
operator|.
name|getMessage
argument_list|()
expr_stmt|;
block|}
if|if
condition|(
operator|!
name|testFile
operator|.
name|renameTo
argument_list|(
name|origFile
argument_list|)
condition|)
block|{
name|fail
argument_list|(
literal|"Failed to rename test file"
argument_list|)
expr_stmt|;
block|}
name|assertTrue
argument_list|(
name|result
operator|.
name|isEmpty
argument_list|()
argument_list|,
name|result
argument_list|)
expr_stmt|;
block|}
block|}
end_class

end_unit

