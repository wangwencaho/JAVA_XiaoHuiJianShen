package com.atguigu.java.ai.langchain4j;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;

import dev.langchain4j.data.document.parser.apache.pdfbox.ApachePdfBoxDocumentParser;
import dev.langchain4j.data.document.splitter.DocumentByParagraphSplitter;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.onnx.HuggingFaceTokenizer;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RAGTest {
//        @Test
//        public void testReadDocument() {
//            Document document = FileSystemDocumentLoader.loadDocument("D:/spring-ai-tongyiqianwen/JavaAI/src/main/resources/t.txt");
//            System.out.println(document.text());

//            // 加载单个文档
//            Document document = FileSystemDocumentLoader.loadDocument("E:/knowledge/file.txt", new TextDocumentParser());
//            // 从一个目录中加载所有文档
//            List<Document> documents = FileSystemDocumentLoader.loadDocuments("E:/knowledge", new TextDocumentParser());
//            // 从一个目录中加载所有的.txt文档
//            PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:*.txt");
//            List<Document> documents = FileSystemDocumentLoader.loadDocuments("E:/knowledge", pathMatcher, new TextDocumentParser());
//            // 从一个目录及其子目录中加载所有文档
//            List<Document> documents = FileSystemDocumentLoader.loadDocumentsRecursively("E:/knowledge", new TextDocumentParser());
//        }

//    @Test
//    public void testParsePDF() {
//        Document document = FileSystemDocumentLoader.loadDocument(
//                "src/main/resources/Rag/云慧健身课程房间信息.pdf",
//                new ApachePdfBoxDocumentParser()
//        );
//        System.out.println(document.text());
//        System.out.println(document.metadata());
//    }

    @Test
    public void testReadDocumentAndStore() {
        Document document = FileSystemDocumentLoader.loadDocument("src/main/resources/Rag/人工智能.md");

        InMemoryEmbeddingStore<TextSegment> embeddingStore = new InMemoryEmbeddingStore<>();


        EmbeddingStoreIngestor.ingest(document, embeddingStore);

        System.out.println(embeddingStore);
    }

    @Test
    public void testDocumentSplitter() {
//使用FileSystemDocumentLoader读取指定目录下的知识库文档
//并使用默认的文档解析器对文档进行解析(TextDocumentParser)
        Document document = FileSystemDocumentLoader.loadDocument("src/main/resources/Rag/人工智能.md");
//为了简单起见，我们暂时使用基于内存的向量存储
        InMemoryEmbeddingStore<TextSegment> embeddingStore = new InMemoryEmbeddingStore<>
                        ();
//自定义文档分割器
//按段落分割文档：每个片段包含不超过 300个token，并且有 30个token的重叠部分保证连贯性
//注意：当段落长度总和小于设定的最大长度时，就不会有重叠的必要。
        DocumentByParagraphSplitter documentSplitter = new DocumentByParagraphSplitter(
                300,
                30,
//token分词器：按token计算
                new HuggingFaceTokenizer());
//按字符计算
//DocumentByParagraphSplitter documentSplitter = newDocumentByParagraphSplitter(300, 30);
        EmbeddingStoreIngestor
                .builder()
                .embeddingStore(embeddingStore)
                .documentSplitter(documentSplitter)
                .build()
                .ingest(document);
    }
}