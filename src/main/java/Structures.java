import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by ameya on 17/9/16.
 */
public class Structures {
    private Queue<String> urlToVisit;
    private Set<String> visitedUrl;

    public Structures(String seedUrl){
        urlToVisit = new ConcurrentLinkedQueue<String>();
        visitedUrl = new TreeSet<String>();
        urlToVisit.offer(seedUrl);
    }

    public void addUrlToVisit(String url){
        urlToVisit.offer(url);
    }

    public String getUrlToVisit(){
        return urlToVisit.poll();
    }

    public int getUrltoVisitSize(){
        return urlToVisit.size();
    }

    public boolean isVisitedUrl(String url){
        return visitedUrl.contains(url);
    }

    public void addVisitedUrl(String url){
        this.visitedUrl.add(url);
    }

    public int visitedUrlSetSize(){
        return visitedUrl.size();
    }
    public int urlToVisitQueueSize(){
        return urlToVisit.size();
    }

}
