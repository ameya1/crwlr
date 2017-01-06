//import database.DataBase;
import links.Links;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by ameya on 17/9/16.
 */
public class Main {
    public static void main(String[] a){

        /**/

        List<String> seedUrl = new ArrayList<String>();
        seedUrl.add("https://www.quora.com");
        seedUrl.add("https://www.digg.com");
        seedUrl.add("https://www.youtube.com");
        seedUrl.add("https://www.reddit.com");
        //DataBase db = DataBase.getDataBase(Links.class);
        int size = seedUrl.size();
        Crawler[] c = new Crawler[size];
        Structures[] s = new Structures[size];
        for(int i = 0; i < size; i++)
            s[i] = new Structures(seedUrl.get(i));

        for(int i = 0; i < size; i++)
            c[i] = new Crawler(s[i], null);

        ExecutorService es = Executors.newFixedThreadPool(10);
        for(int i = 0; i < size; i++)
            es.submit(c[i]);

        /**/

        /*Structures s = new Structures("https://www.digg.com");
        //DataBase db = DataBase.getDataBase(Links.class);
        Crawler c = new Crawler(s, null);
        ExecutorService es = Executors.newFixedThreadPool(1);
        es.submit(c);
        //db.connectionClose();
        es.shutdown();
        //c.crawl();
        //db.connectionClose();



        /*db.getCurrentSession();
        db.beginTransaction();
        links l1 = new links("Abc");
        db.save(l1);
        db.commit();*/


        /*db.truncate("Links");
        db.commit();
        db.connectionClose();*/
    }
}
