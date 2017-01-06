package links;

//import javax.persistence.*;

/**
 * Created by ameya on 17/9/16.
 */

//@Entity
//@Table(name="links")
public class Links {

    //@Id
    //@GeneratedValue(strategy= GenerationType.IDENTITY)
    //@Column(name="id")
    long id;

    //@Column(name="link")
    String link;

    //@Column(name="indexed")
    int indexed;

    Links(){}

    public Links(String link, int indexed) {
        this.link = link;
        this.indexed = indexed;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getIndexed() {
        return indexed;
    }

    public void setIndexed(int indexed) {
        this.indexed = indexed;
    }
}
