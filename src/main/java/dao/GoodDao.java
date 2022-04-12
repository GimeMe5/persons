package dao;

import model.Good;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Artyom Kulagin
 */
public class GoodDao implements Dao<Good, Integer> {
    public GoodDao() {
    }

//    private static GoodDao instance;
//
//    public static GoodDao getInstance() {
//        if (instance == null) {
//            instance = new GoodDao();
//        }
//        return instance;
//    }

    @Override
    public Optional<Good> find(Integer id) throws SQLException {
        String sql = "SELECT * FROM goods WHERE id = ?";
        int price = 0;
        String name = "";
        Connection connection = DataSource.getConnection();

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            id = resultSet.getInt("id");
            name = resultSet.getString("name");
            price = resultSet.getInt("price");
        }
        return Optional.of(new Good(id, name, price));
    }

    @Override
    public List<Good> findAll() throws SQLException {
        List<Good> goods = new ArrayList<>();
        String sql = "SELECT * FROM goods";

        Connection connection = DataSource.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            int price = resultSet.getInt("price");

            Good good = new Good(id, name, price);
            goods.add(good);
        }
        return goods;
    }

    @Override
    public boolean save(Good o) throws SQLException {
        String sql = "INSERT INTO goods (name,price) VALUES (?,?)";

        Connection connection = DataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, o.getName());
        statement.setInt(2, o.getPrice());

        return statement.executeUpdate() > 0;
    }

    @Override
    public boolean update(Good o) throws SQLException {
        String sql = "UPDATE goods SET name = ?, price = ? WHERE id=?";

        Connection connection = DataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, o.getName());
        statement.setInt(2, o.getPrice());
        statement.setInt(3, o.getId());

        return statement.executeUpdate() > 0;
    }

    @Override
    public boolean delete(Good o) throws SQLException {
        String deleteFromGoods = "DELETE FROM goods WHERE id = ?";
        String deleteFromGoodsSales = "DELETE FROM goods_sales WHERE goods_id = ? ";
        Connection connection = DataSource.getConnection();
        PreparedStatement goodsStatement = connection.prepareStatement(deleteFromGoods);
        PreparedStatement goodsSalesStatement = connection.prepareStatement(deleteFromGoodsSales);

        connection.setAutoCommit(false);
        connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        goodsStatement.setInt(1, o.getId());
        goodsSalesStatement.setInt(1, o.getId());
        boolean first = goodsSalesStatement.executeUpdate() > 0;
        boolean second = goodsStatement.executeUpdate() > 0;
        connection.commit();
        connection.setAutoCommit(true);

        return first & second;
    }
}
