import java.util.regex.Pattern;

/**
 * Created by ameya on 17/9/16.
 */
public class PatternMatch {
    private static final Pattern FILTERS = Pattern.compile(".*(.*(\\.(css|js|bmp|gif|jpe?g"
            + "|png|tiff?|mid|mp2|mp3|mp4"
            + "|wav|avi|mov|mpeg|ram|m4v|pdf"
            + "|rm|smil|wmv|swf|wma|zip|rar|gz))$)");

    public static boolean isValidUrl(String url){
        if(!FILTERS.matcher(url).matches())
            return true;
        else
            return false;
    }
}
