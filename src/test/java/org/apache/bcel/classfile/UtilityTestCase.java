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
name|classfile
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
name|assertEquals
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

begin_class
specifier|public
class|class
name|UtilityTestCase
block|{
annotation|@
name|Test
specifier|public
name|void
name|testSignatureToStringWithGenerics
parameter_list|()
throws|throws
name|Exception
block|{
comment|// tests for BCEL-197
name|assertEquals
argument_list|(
literal|"java.util.Map<X, java.util.List<Y>>"
argument_list|,
name|Utility
operator|.
name|signatureToString
argument_list|(
literal|"Ljava/util/Map<TX;Ljava/util/List<TY;>;>;"
argument_list|)
argument_list|,
literal|"generic signature"
argument_list|)
expr_stmt|;
name|assertEquals
argument_list|(
literal|"java.util.Set<? extends java.nio.file.OpenOption>"
argument_list|,
name|Utility
operator|.
name|signatureToString
argument_list|(
literal|"Ljava/util/Set<+Ljava/nio/file/OpenOption;>;"
argument_list|)
argument_list|,
literal|"generic signature"
argument_list|)
expr_stmt|;
name|assertEquals
argument_list|(
literal|"java.nio.file.attribute.FileAttribute<?>[]"
argument_list|,
name|Utility
operator|.
name|signatureToString
argument_list|(
literal|"[Ljava/nio/file/attribute/FileAttribute<*>;"
argument_list|)
argument_list|,
literal|"generic signature"
argument_list|)
expr_stmt|;
comment|// tests for BCEL-286
name|assertEquals
argument_list|(
literal|"boofcv.alg.tracker.tld.TldTracker<boofcv.struct.image.ImageGray<boofcv.struct.image.GrayU8>, boofcv.struct.image.GrayI<boofcv.struct.image.GrayU8>>"
argument_list|,
name|Utility
operator|.
name|signatureToString
argument_list|(
literal|"Lboofcv/alg/tracker/tld/TldTracker<Lboofcv/struct/image/ImageGray<Lboofcv/struct/image/GrayU8;>;Lboofcv/struct/image/GrayI<Lboofcv/struct/image/GrayU8;>;>;"
argument_list|)
argument_list|,
literal|"generic signature"
argument_list|)
expr_stmt|;
name|assertEquals
argument_list|(
literal|"java.util.Map<?, ?>"
argument_list|,
name|Utility
operator|.
name|signatureToString
argument_list|(
literal|"Ljava/util/Map<**>;"
argument_list|)
argument_list|,
literal|"generic signature"
argument_list|)
expr_stmt|;
name|assertEquals
argument_list|(
literal|"com.jme3.util.IntMap<T>.IntMapIterator"
argument_list|,
name|Utility
operator|.
name|signatureToString
argument_list|(
literal|"Lcom/jme3/util/IntMap<TT;>.IntMapIterator;"
argument_list|)
argument_list|,
literal|"generic signature"
argument_list|)
expr_stmt|;
comment|// tests for BCEL-279
name|assertEquals
argument_list|(
literal|"<T extends java.lang.Object>(com.google.common.io.ByteProcessor<T>, int)T"
argument_list|,
name|Utility
operator|.
name|signatureToString
argument_list|(
literal|"<T:Ljava/lang/Object;>(Lcom/google/common/io/ByteProcessor<TT;>;I)TT;"
argument_list|,
literal|false
argument_list|)
argument_list|,
literal|"type parameters signature"
argument_list|)
expr_stmt|;
name|assertEquals
argument_list|(
literal|"<T extends Object>(com.google.common.io.ByteProcessor<T>, int)T"
argument_list|,
name|Utility
operator|.
name|signatureToString
argument_list|(
literal|"<T:Ljava/lang/Object;>(Lcom/google/common/io/ByteProcessor<TT;>;I)TT;"
argument_list|,
literal|true
argument_list|)
argument_list|,
literal|"type parameters signature"
argument_list|)
expr_stmt|;
name|assertEquals
argument_list|(
literal|"<M extends java.lang.reflect.AccessibleObject& java.lang.reflect.Member>(M)void"
argument_list|,
name|Utility
operator|.
name|signatureToString
argument_list|(
literal|"<M:Ljava/lang/reflect/AccessibleObject;:Ljava/lang/reflect/Member;>(TM;)V"
argument_list|)
argument_list|,
literal|"type parameters signature"
argument_list|)
expr_stmt|;
name|assertEquals
argument_list|(
literal|"<K1 extends K, V1 extends V>()com.google.common.cache.Weigher<K1, V1>"
argument_list|,
name|Utility
operator|.
name|signatureToString
argument_list|(
literal|"<K1:TK;V1:TV;>()Lcom/google/common/cache/Weigher<TK1;TV1;>;"
argument_list|)
argument_list|,
literal|"type parameters signature"
argument_list|)
expr_stmt|;
name|assertEquals
argument_list|(
literal|"<K1 extends K, V1 extends V>(com.google.common.cache.Weigher<? super K1, ? super V1>)com.google.common.cache.CacheBuilder<K1, V1>"
argument_list|,
name|Utility
operator|.
name|signatureToString
argument_list|(
literal|"<K1:TK;V1:TV;>(Lcom/google/common/cache/Weigher<-TK1;-TV1;>;)Lcom/google/common/cache/CacheBuilder<TK1;TV1;>;"
argument_list|)
argument_list|,
literal|"type parameters signature"
argument_list|)
expr_stmt|;
name|assertEquals
argument_list|(
literal|"<N extends java.lang.Object, E extends java.lang.Object> extends java.lang.Object implements com.google.common.graph.Network<N, E>"
argument_list|,
name|Utility
operator|.
name|signatureToString
argument_list|(
literal|"<N:Ljava/lang/Object;E:Ljava/lang/Object;>Ljava/lang/Object;Lcom/google/common/graph/Network<TN;TE;>;"
argument_list|,
literal|false
argument_list|)
argument_list|,
literal|"class signature"
argument_list|)
expr_stmt|;
name|assertEquals
argument_list|(
literal|"<K extends Object, V extends Object> extends Object"
argument_list|,
name|Utility
operator|.
name|signatureToString
argument_list|(
literal|"<K:Ljava/lang/Object;V:Ljava/lang/Object;>Ljava/lang/Object;"
argument_list|)
argument_list|,
literal|"class signature"
argument_list|)
expr_stmt|;
block|}
block|}
end_class

end_unit

