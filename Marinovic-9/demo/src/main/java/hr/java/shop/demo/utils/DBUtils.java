package hr.java.shop.demo.utils;

import hr.java.shop.demo.domain.Category;
import hr.java.shop.demo.domain.Item;

import java.io.FileReader;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DBUtils {

    private static final String DATABASE_FILE = "dat/dbCredentials.properties";

    private static Connection connectToDatabase() throws Exception {
        Properties properties = new Properties();

        properties.load(new FileReader(DATABASE_FILE));

        String databaseURL = properties.getProperty("dbURL");
        String databaseUserName = properties.getProperty("dbUserName");
        String databasePassword = properties.getProperty("dbPassword");

        Connection connection = DriverManager.getConnection(databaseURL,
                databaseUserName,databasePassword);

        return connection;
    }

    private static void closeConnectionToDatabase(Connection con) throws SQLException {
        con.close();
    }

    //@Override
    public static List<Category> readCategories() {

        List<Category> categoryList = new ArrayList<>();

        try {
            Connection con = connectToDatabase();

            String queryString = "SELECT * FROM CATEGORY";
            Statement query = con.createStatement();
            ResultSet rs = query.executeQuery(queryString);

            while(rs.next()) {
                String name = rs.getString("NAME");
                Long id = rs.getLong("ID");
                String description = rs.getString("DESCRIPTION");

                Category newClassroom = new Category(name, 0 , description);
                categoryList.add(newClassroom);
            }

            closeConnectionToDatabase(con);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return categoryList;
    }

    public static List<Category> readItems(List<Category> categoryList) {


        List<Item> itemList = new ArrayList<>();

        try {
            Connection con = connectToDatabase();

            String queryString = "SELECT * FROM ITEM";
            Statement query = con.createStatement();
            ResultSet rs = query.executeQuery(queryString);

            while(rs.next()) {

                Long id = rs.getLong("ID");
                Long categoryId = rs.getLong("CATEGORY_ID");
                String itemName = rs.getString("NAME");
                BigDecimal itemWidth = rs.getBigDecimal("WIDTH");
                BigDecimal itemHeight = rs.getBigDecimal("HEIGHT");
                BigDecimal itemLength = rs.getBigDecimal("LENGTH");
                BigDecimal itemProductionCost = rs.getBigDecimal("PRODUCTION_COST");
                BigDecimal itemSellingPrice = rs.getBigDecimal("SELLING_PRICE");

                Item newItem = new Item(itemName,id.intValue(),
                        categoryList.get(categoryId.intValue() - 1),
                        itemWidth,
                        itemHeight,
                        itemLength,
                        itemProductionCost,
                        itemSellingPrice,
                        BigDecimal.ZERO);

                itemList.add(newItem);
            }

            closeConnectionToDatabase(con);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return categoryList;
    }

}
