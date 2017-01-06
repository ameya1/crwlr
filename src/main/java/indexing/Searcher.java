package indexing;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import java.io.File;
import java.io.IOException;

/**
 * Created by ameya on 19/9/16.
 */
public class Searcher {
    public void search(String indexDir, String q) {
        try {
            Directory dir = FSDirectory.open(new File(indexDir));
            IndexReader ir = DirectoryReader.open(dir);
            IndexSearcher is = new IndexSearcher(ir);
            Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_47);
            QueryParser parser = new QueryParser(Version.LUCENE_47, "contents", analyzer);
            Query query = parser.parse(q);

            TopDocs hits = is.search(query, 1000);
            for(ScoreDoc sc : hits.scoreDocs){
                Document doc = is.doc(sc.doc);
                System.out.println( doc.get("filename"));
            }
        }catch(IOException e){
            e.printStackTrace();
        }catch(ParseException e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args){
        Searcher s = new Searcher();
        s.search("/home/ameya/IdeaProjects/crawl/index", "name");
    }
}
