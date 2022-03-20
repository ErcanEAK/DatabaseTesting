package jdbctests;

import org.testng.annotations.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class dynamic_list {
    String dburl = "jdbc:oracle:thin:@18.233.164.111:1521:xe";
    String dbUsername = "hr";
    String dbPassword = "hr";


    @Test
    public void MetaDataExample() throws SQLException {
        //creat connection
        Connection connection = DriverManager.getConnection(dburl, dbUsername, dbPassword);
        //statemanet
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        //run query and get result in resultset object
        ResultSet resultSet = statement.executeQuery("select * from countries");

        ResultSetMetaData rsMetaData = resultSet.getMetaData();

        List<Map<String, Object>> queryData = new ArrayList<>();

        //number of column
        int colCount = rsMetaData.getColumnCount();

        while (resultSet.next()) {
            Map<String, Object> row = new HashMap<>();

            //some code here to put inform
            for (int i = 1; i <= colCount; i++) {
                row.put(rsMetaData.getColumnName(i), resultSet.getObject(i));

            }

            //add your map to ypur lsit
            queryData.add(row);

        }
        for (Map<String, Object> row : queryData) {
            System.out.println(row.toString());
        }
        //close all connection
        resultSet.close();
        statement.close();
        connection.close();
    }

}
