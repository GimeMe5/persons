package dao;

import model.Sale;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Artyom Kulagin
 */
public class SalesDao implements Dao<Sale,Integer> {
    private SalesDao() {
    }

    private static SalesDao instance;

    public static SalesDao getInstance() {
        if (instance == null) {
            instance = new SalesDao();
        }
        return instance;
    }

    @Override
    public Optional<Sale> find(Integer id) throws SQLException {
        String sql = "SELECT * FROM sales WHERE id = ?";
        int count = 0, goodId = 0;
        Connection connection = DataSource.getConnection();

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            id = resultSet.getInt("id");
            count = resultSet.getInt("count");
            goodId = resultSet.getInt("good_id");
        }
        return Optional.of(new Sale(id, count,goodId));
    }

    @Override
    public List<Sale> findAll() throws SQLException {
        List<Sale> sales = new ArrayList<>();
        String sql = "SELECT * FROM sales";


        Connection connection = DataSource.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            int count = resultSet.getInt("count");
            int goodId = resultSet.getInt("good_id");

            Sale sale = new Sale(id, count, goodId);
            sales.add(sale);
        }
        return sales;
    }

    @Override
    public boolean save(Sale o) throws SQLException {
        String saleSql = "INSERT INTO sales (id,count,good_id) VALUES (?,?,?)";
        String goodsSalesSql = "INSERT INTO goods_sales (goods_id,sales_id) VALUES (?,?)";

        Connection connection = DataSource.getConnection();

        connection.setAutoCommit(false);
        connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        PreparedStatement saleStatement = connection.prepareStatement(saleSql);
        saleStatement.setInt(1, o.getId());
        saleStatement.setInt(2, o.getCount());
        saleStatement.setInt(3, o.getGoodId());
        boolean first = saleStatement.executeUpdate() > 0;
        PreparedStatement goodSaleStatement = connection.prepareStatement(goodsSalesSql);
        goodSaleStatement.setInt(1, o.getGoodId());
        goodSaleStatement.setInt(2, o.getId());
        boolean second = goodSaleStatement.executeUpdate() > 0;

        connection.commit();
        connection.setAutoCommit(true);

        return first&second;
    }

    @Override
    public boolean update(Sale o) throws SQLException {
        String sql = "UPDATE sales SET count = ?,good_id=? WHERE id=?";

        Connection connection = DataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, o.getCount());
        statement.setInt(2, o.getGoodId());
        statement.setInt(3, o.getId());

        return statement.executeUpdate() > 0;
    }

    @Override
    public boolean delete(Sale o) throws SQLException {
        String deleteFromSales = "DELETE FROM sales WHERE id = ?";
        String deleteFromGoodsSales = "DELETE FROM goods_sales WHERE sales_id = ? ";
        Connection connection = DataSource.getConnection();
        PreparedStatement salesStatement = connection.prepareStatement(deleteFromSales);
        PreparedStatement goodsSalesStatement = connection.prepareStatement(deleteFromGoodsSales);

        connection.setAutoCommit(false);
        connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        salesStatement.setInt(1, o.getId());
        goodsSalesStatement.setInt(1, o.getId());
        boolean first = goodsSalesStatement.executeUpdate() > 0;
        boolean second = salesStatement.executeUpdate() > 0;
        connection.commit();
        connection.setAutoCommit(true);

        return first & second;
    }
}
