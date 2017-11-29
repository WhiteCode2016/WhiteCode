package com.whitecode;

import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
import java.nio.file.Paths;

/**
 * Created by White on 2017/11/29.
 */
public class Indexer {

    private Integer ids[]={1,2,3};
    private String citys[]={"青岛","南京","上海"};
    private String descs[]={
            "青岛是一个漂亮的城市。",
            "南京是一个文化的城市。",
            "上海是一个繁华的城市。"
    };

    private Directory dir;

    // 实例化 IndexWriter
    private IndexWriter getWriter() throws IOException {
        SmartChineseAnalyzer analyzer = new SmartChineseAnalyzer();
        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        IndexWriter indexWriter = new IndexWriter(dir,config);
        return indexWriter;
    }

    private void index(String indexDir) throws IOException {
        dir = FSDirectory.open(Paths.get(indexDir));
        IndexWriter writer = getWriter();
        for (int i = 0; i < ids.length; i++) {
            Document doc = new Document();
            doc.add(new TextField("id",ids[i].toString(),Field.Store.YES));
            doc.add(new StringField("city",citys[i],Field.Store.YES));
            doc.add(new TextField("desc",descs[i],Field.Store.YES));
            writer.addDocument(doc);
        }
        writer.close();
    }

    public static void main(String[] args) throws IOException {
        new Indexer().index("E://lucene");
        System.out.println("Success Indexer");
    }
}
