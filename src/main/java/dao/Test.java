package dao;

import model.Good;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * @author Artyom Kulagin
 */
public class Test {
    public static void main(String[] args) {
        SessionFactory factory = SessionSource.buildSessionFactory();
        Session session = factory.openSession();
        session.beginTransaction();
        List<Good> goods = session.createQuery("FROM Good",Good.class).getResultList();

        session.getTransaction().commit();
        session.close();

        for (Good good : goods) {
            System.out.println(good.getName());
        }
    }
}
