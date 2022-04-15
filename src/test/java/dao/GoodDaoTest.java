package dao;

import model.Good;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;


import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * @author Artyom Kulagin
 */
public class GoodDaoTest {

    private static SessionFactory sessionFactory;
    @Mock
    private Session session;


    @BeforeAll
    public static void setup() {
        sessionFactory = SessionSource.buildSessionFactory();
    }

    @AfterAll
    public static void down() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }

    @BeforeEach
    public void openSession() {
        session = sessionFactory.openSession();
    }

    @AfterEach
    public void closeSession() {
        if (session != null) {
            session.close();
        }
    }

    @Test
    public void shouldSaveCurrentGood() {
        session.beginTransaction();
        int id = 100;
        Good goodIn = new Good( id,"1", 1);
        session.save(goodIn);
        Good goodOut = session.find(Good.class, goodIn.getId());
        session.getTransaction().commit();

        assertEquals(goodIn.getName(), goodOut.getName());

        session.beginTransaction();
        session.delete(goodIn);
        session.getTransaction().commit();
    }
}
