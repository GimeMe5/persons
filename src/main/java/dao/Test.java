package dao;

import model.Good;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Artyom Kulagin
 */
public class Test {

    public static void main(String[] args) throws SQLException {
        GoodDao.getInstance().save(new Good("carrot", 40));
        List<Good> list = GoodDao.getInstance().findAll();
        for (Good good : list) {
            System.out.println(good);
        }
    }
}
