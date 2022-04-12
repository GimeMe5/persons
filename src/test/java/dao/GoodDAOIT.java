package dao;

import model.Good;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

/**
 * @author Artyom Kulagin
 */
public class GoodDAOIT {


    private GoodDao goodDao;

    @BeforeEach
    public void init() {
        goodDao = GoodDao.getInstance();
    }

    private static final Good GOOD = new Good(500, "testname", 500);

    @Test
    public void shouldReturnCurrentGood() throws SQLException {
        String injectTemporaryGood = "INSERT INTO goods(id,name,price) values(500,'testname',500)";
        String deleteTemporaryGood = "DELETE FROM goods WHERE id=500";
        Connection connection = DataSource.getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate(injectTemporaryGood);

        Good good = goodDao.find(500).get();
        assertEquals(GOOD, good);

        statement.executeUpdate(deleteTemporaryGood);
    }

    @Test
    public void shouldReturnCurrentListOfGoods() throws SQLException {
        List<Good> goods = goodDao.findAll();

        assertInstanceOf(List.class, goods);
        assertInstanceOf(Good.class, goods.get(0));
    }
}
