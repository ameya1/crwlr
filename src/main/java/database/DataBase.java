package database;

/*import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

/**
 * Created by ameya on 17/9/16.
 */
/*
public class DataBase {

    private static DataBase databaseConnector = null;
    private SessionFactory factory;
    private Session session;

    private DataBase(Class annotatedClass){
        this.factory = new Configuration()
                                    .configure("hibernate.cfg.xml")
                                    .addAnnotatedClass(annotatedClass)
                                    .buildSessionFactory();
    }

    public static DataBase getDataBase(Class annotatedClass){
        if(databaseConnector == null)
            databaseConnector = new DataBase(annotatedClass);
        return databaseConnector;
    }

    public Session getCurrentSession(){
        session = factory.getCurrentSession();
        return session ;
    }

    public void beginTransaction(){
        session.beginTransaction();
    }

    public void save(Object o){
        session.save(o);
    }

    public void commit(){
        session.getTransaction().commit();
    }

    public int truncate(String tableName){
        String sql = String.format("DELETE FROM %s", tableName);
        this.session = this.factory.getCurrentSession();
        this.session.beginTransaction();
        Query query = this.session.createQuery(sql);
        return query.executeUpdate();
    }

    public void connectionClose(){
        this.factory.close();
    }
}
*/