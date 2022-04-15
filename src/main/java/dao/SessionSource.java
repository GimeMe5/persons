package dao;

import org.hibernate.HibernateException;
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
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }
        return sessionFactory;
    }

}
