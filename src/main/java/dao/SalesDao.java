package dao;

import model.Sale;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static dao.SessionSource.buildSessionFactory;

/**
 * @author Artyom Kulagin
 */
public class SalesDao implements Dao<Sale, Integer> {

    private static Session session;

    public final static Logger logger = LoggerFactory.getLogger(GoodDao.class);

    @Override
    public void save(Sale sale) {
        session = buildSessionFactory().openSession();
        session.save(sale);
        logger.info("Good " + sale.getId() + " successfully created");
        session.close();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Sale> findAll() {
        session = buildSessionFactory().openSession();
        List<Sale> sales = session.createQuery("SELECT * FROM sales").list();
        session.close();

        return sales;
    }

    @Override
    public Sale findById(Integer id) {
        session = buildSessionFactory().openSession();
        Sale sale = session.get(Sale.class, id);
        session.close();

        return sale;
    }

    @Override
    public void delete(Integer id) {
        session = buildSessionFactory().openSession();
        Sale sale = findById(id);
        session.remove(sale);
        session.close();
    }

    @Override
    public void update(Sale sale) {
        try {
            session = buildSessionFactory().openSession();
            session.beginTransaction();
            Sale loadedSale = (Sale) session.get(Sale.class, sale.getId());
            loadedSale.setCount(sale.getCount());
            loadedSale.setGoodId(sale.getGoodId());
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
