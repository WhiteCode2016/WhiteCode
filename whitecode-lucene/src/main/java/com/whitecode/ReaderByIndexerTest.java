package com.whitecode;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Paths;

/**
 * Created by White on 2017/11/29.
 */
public class ReaderByIndexerTest {
    private static void search(String indexDir,String par) throws Exception {
        // 得到索引文件的路径
        Directory dir = FSDirectory.open(Paths.get(indexDir));
        // 获取路径下的所有文件
        IndexReader reader = DirectoryReader.open(dir);
        // 建立索引查询器
        IndexSearcher searcher = new IndexSearcher(reader);
        // 中文分词器
        SmartChineseAnalyzer analyzer = new SmartChineseAnalyzer();

        QueryParser parser = new QueryParser("desc",analyzer);
        Query query = parser.parse(par);

        //计算索引开始时间
        long start = System.currentTimeMillis();
        TopDocs topDocs = searcher.search(query,10);
        //索引结束时间
        long end = System.currentTimeMillis();
        System.out.println("匹配"+par+",总共花费了"+(end-start)+"毫秒,共查到"+topDocs.totalHits+"条记录。");

        // 高亮显示start
        // 算分
        QueryScorer scorer = new QueryScorer(query);
        // 显示得分高的片段
        Fragmenter fragmenter = new SimpleSpanFragmenter(scorer);
        SimpleHTMLFormatter simpleHTMLFormatter = new SimpleHTMLFormatter("<b><font color='red'>","</font></b>");

        Highlighter highlighter = new Highlighter(simpleHTMLFormatter,scorer);
        highlighter.setTextFragmenter(fragmenter);
        // 高亮显示end

        for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
            Document document = searcher.doc(scoreDoc.doc);
            System.out.println(document.get("city"));
            System.out.println(document.get("desc"));
            String desc = document.get("desc");
            if (desc != null) {
                TokenStream tokenStream = analyzer.tokenStream("desc",new StringReader(desc));
                System.out.println(highlighter.getBestFragment(tokenStream,desc));
            }
        }
        reader.close();
    }

    public static void main(String[] args) {
        try {
            search("E://lucene","南京文明");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
