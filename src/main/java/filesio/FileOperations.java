package filesio;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ameya on 20/9/16.
 */
public class FileOperations {

    //private static List<String> links = new ArrayList<String>();
    private boolean createDirectory;


    public FileOperations(){
        createDirectory = true;
    }

    public void fileSave(String directoryName, String fileName, String text){

        if(createDirectory){
            new File(directoryName).mkdirs();
            createDirectory = false;
        }
        String name = linkToFilename(fileName, directoryName);
        File file = new File(name);
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(file));
            out.write(text);
            String link = filenameToLink(file.getName());
            //System.out.println(link);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getFileData(String fileName){
        try {
            File f = new File(fileName);
            BufferedReader br = new BufferedReader(new FileReader(f));
            String line;
            StringBuffer text = new StringBuffer();
            while((line = br.readLine()) != null){
                text.append(line);
                text.append("\n");
            }
            return text.toString();
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        return "";
    }

    public String linkToFilename(String link){
        String name = link.replaceAll("/", "%40");
        return name;
    }

    public String linkToFilename(String link, String directoryName){
        String name = link.replaceAll("/", "%40");
        String loc = directoryName + "/" + name + ".txt";
        return loc;
    }

    public String filenameToLink(String filename){
        String link = filename.replaceAll("%40", "/").replaceAll(".txt", "");
        return link;
    }


    /*public static void createDirectory(String directory){

        new File(directory).mkdirs();
        for(String url : links){
            Document doc = null;
            try {
                doc = Jsoup.connect(url)
                        .userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/37.0.2062.124 Safari/537.36")
                        .referrer("http://www.duckduckgo.com")
                        .timeout(12000)
                        .followRedirects(true)
                        .get();


                System.out.println(doc.title());
            } catch (IOException e) {
                e.printStackTrace();
            }

            fileSave(loc, doc.html());
        }
        new File("index").mkdirs();
    }*/
}
