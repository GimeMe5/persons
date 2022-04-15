package dao;

import model.Good;
import model.Sale;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


/**
 * @author Artyom Kulagin
 */
public class SessionSource {

    private static SessionFactory sessionFactory;

    private SessionSource() {
    }

    public static SessionFactory buildSessionFactory() {

        if (sessionFactory==null) {
            sessionFactory = new Configuration()
                    .configure()
                    .addAnnotatedClass(Good.class)
                    .addAnnotatedClass(Sale.class)
                    .buildSessionFactory();
        }
        return sessionFactory;
    }

}
