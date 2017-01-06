package indexing;

import database.DataBase;
import filesio.FileOperations;
import links.Links;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
//import org.hibernate.Session;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by ameya on 19/9/16.
 */
public class Indexer implements Runnable{
    private static final String LOCATION = "/home/ameya/IdeaProjects/crawl/";
    IndexWriter writer = null;
    FileOperations fileOps;
    DataBase db;


    public Indexer(DataBase db){
        this.db = db;
    }

    public Indexer(String indexDir, DataBase db){
        try {
            Directory dir = FSDirectory.open(new File(indexDir));
            Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_47);
            IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_47, analyzer);
            writer = new IndexWriter(dir, config);

        }catch(IOException e){
            e.printStackTrace();
        }
        fileOps = new FileOperations();
        this.db = db;
    }

    public void index(String dataDir){
        File[] files = new File(dataDir).listFiles();

        for(File f : files) {
            indexFile(f);
            try {
                fileOps.fileSave(LOCATION + "indexed", f.getName(), fileOps.getFileData(f.getCanonicalPath()));
            }catch(IOException e){
                e.printStackTrace();
            }
            f.delete();
        }
    }

    public void indexFile(File f){
        try {
            Document doc = new Document();;
            doc.add(new TextField("contents", new FileReader(f)));
            String filename = fileOps.filenameToLink(f.getName());
            System.out.println("Indexing : " + filename + " " + Thread.currentThread().getName());
            doc.add(new TextField("filename", filename, Field.Store.YES));
            doc.add(new TextField("filepath", f.getCanonicalPath(), Field.Store.YES));
            writer.addDocument(doc);
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void close(){
        try {
            writer.commit();
            writer.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void run(){

    }

    /*public List<Links> getLinks(){
        Session session = db.getCurrentSession();
        db.beginTransaction();
        List<Links> links = session.createQuery("from Links where indexed = 0").list();
        db.commit();
        return links;
    }

    public void updateLink(String linkName){
        Session session = db.getCurrentSession();
        session.beginTransaction();
        String query = "update Links set indexed = 1 where link = '" + linkName + "'";
        session.createQuery(query).executeUpdate();
        db.commit();


        System.out.println(query);
    }*/

    public static void main(String[] args){
        //DataBase db = DataBase.getDataBase(Links.class);
        //Indexer i = new Indexer(db);
        ExecutorService es = Executors.newFixedThreadPool(1);
        Indexer i = new Indexer("/home/ameya/IdeaProjects/crawl/index", null);
        i.index(LOCATION + "data");
        i.close();

        /*while(true) {
            es.submit(i);
            //es.shutdown();
        }*/

        //db.connectionClose();
    }
}
