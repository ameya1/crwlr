import database.DataBase;
import filesio.FileOperations;
import links.LinkController;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by ameya on 17/9/16.
 */
public class Crawler implements Runnable{
    private Structures structures;
    private DataBase dataBase;
    private LinkController linkController;
    private int count;
    private final int LIMIT;
    private boolean createThread;
    private FileOperations file;


    Crawler(Structures structures, DataBase dataBase){
        this.structures = structures;
        this.dataBase = dataBase;
        linkController = new LinkController();
        this.count = 0;
        this.LIMIT = 1000;
        createThread = true;
        file = new FileOperations();
    }

    public void run(){
        crawl();
    }

    public void crawl(){
        while(structures.getUrltoVisitSize() > 0 && count <= LIMIT){
            String url = structures.getUrlToVisit();
            if(PatternMatch.isValidUrl(url) && !structures.isVisitedUrl(url)){
                try {
                    System.out.println(url + " " + Thread.currentThread().getName() + " visited " + "set count : " + structures.visitedUrlSetSize() + " queue: " + structures.urlToVisitQueueSize());

                    count++;
                    Document doc = Jsoup.connect(url)
                            .userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/37.0.2062.124 Safari/537.36")
                            .referrer("http://www.duckduckgo.com")
                            .timeout(10 *1000)
                            .followRedirects(true)
                            .get();
                    file.fileSave("/home/ameya/IdeaProjects/crawl/data", url, doc.html());
                    //linkController.addLinks(dataBase, url);
                    Elements links = doc.select("a[href]");
                    structures.addVisitedUrl(url);
                    addNewLinksToVisit(links);
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
            if(createThread) {
                createThread = false;
                ExecutorService es = Executors.newFixedThreadPool(10);
                for (int i = 0; i < 10; i++)
                    es.submit(this);
                es.shutdown();
            }
        }
    }

    private void addNewLinksToVisit(Elements links){
        for(Element link : links){
            String url = link.attr("abs:href");
            if(!structures.isVisitedUrl(url) && (url.startsWith("http") || url.startsWith("https"))){
                structures.addUrlToVisit(url);
                //System.out.println(url + " " + Thread.currentThread().getName());
            }
        }
    }
}
