package com.whitecode;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.store.SimpleFSDirectory;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;

/**
 * Created by White on 2017/11/28.
 */
public class LuceneDemo {

    public static void main(String[] args) throws IOException {
        long startTime = System.currentTimeMillis();
        System.out.println("*****************检索开始**********************");
        RAMDirectory directory = new RAMDirectory();
        FSDirectory directory1 = new SimpleFSDirectory(FileSystems.getDefault().getPath("", "index"));
        IndexWriterConfig writerConfig = new IndexWriterConfig(new StandardAnalyzer());
        IndexWriter writer = new IndexWriter(directory,writerConfig);
        Document doc = new Document();
        doc.add(new Field("name","lin zhengle", TextField.TYPE_STORED));
        doc.add(new Field("address","中国上海", TextField.TYPE_STORED));
        doc.add(new Field("dosomething","I am learning lucene", TextField.TYPE_STORED));
        writer.addDocument(doc);
        writer.close();

        DirectoryReader ireader = DirectoryReader.open(directory);
        IndexSearcher searcher = new IndexSearcher(ireader);
        Query query = new TermQuery(new Term("dosomething", "lucene"));
        TopDocs rs = searcher.search(query, 100);
        long endTime = System.currentTimeMillis();
        System.out.println("总共花费" + (endTime - startTime) + "毫秒，检索到" + rs.totalHits + "条记录。");
        for (int i = 0; i < rs.scoreDocs.length; i++) {
            // rs.scoreDocs[i].doc 是获取索引中的标志位id, 从0开始记录
            Document firstHit = searcher.doc(rs.scoreDocs[i].doc);
            System.out.println("name:" + firstHit.getField("name").stringValue());
            System.out.println("address:" + firstHit.getField("address").stringValue());
            System.out.println("dosomething:" + firstHit.getField("dosomething").stringValue());
        }

        directory.close();
        System.out.println("*****************检索结束**********************");
    }
}
