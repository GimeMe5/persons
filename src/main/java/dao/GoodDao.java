package dao;

import model.Good;
import org.hibernate.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

import static dao.SessionSource.buildSessionFactory;

/**
 * @author Artyom Kulagin
 */
public class GoodDao implements Dao<Good, Integer> {

    private static Session session;

    public final static Logger logger = LoggerFactory.getLogger(GoodDao.class);

    @Override
    public void save(Good good) {
        session = buildSessionFactory().openSession();
        session.beginTransaction();
        session.save(good);
        session.getTransaction().commit();
        logger.info("Good " + good.getName() + " successfully created");
    }

    @Override
    public List<Good> findAll() {
         session = buildSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Good> goods = session.createQuery("FROM Good",Good.class).getResultList();
        session.getTransaction().commit();
        return goods;
    }

    @Override
    public Good findById(Integer id) {
        session = buildSessionFactory().openSession();
        Good good = session.get(Good.class, id);
        session.close();

        return good;
    }

    @Override
    public void delete(Integer id) {
        session = buildSessionFactory().openSession();
        Good good = findById(id);
        session.remove(good);
        session.close();
    }

    @Override
    public void update(Good good) {
        try {
            session = buildSessionFactory().openSession();
            session.beginTransaction();
            Good loadedGood = session.get(Good.class, good.getId());
            loadedGood.setName(good.getName());
            loadedGood.setPrice(good.getPrice());
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
