package jdbctests;

import org.testng.annotations.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class listOfMApExample {

    String dburl= "jdbc:oracle:thin:@18.233.164.111:1521:xe";
    String dbUsername = "hr";
    String dbPassword = "hr";


    @Test
            public void MetaDataExample() throws SQLException {
        //creat connection
        Connection connection = DriverManager.getConnection(dburl, dbUsername, dbPassword);
        //statemanet
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        //run query and get result in resultset object
        ResultSet resultSet = statement.executeQuery("select * from employees");

        DatabaseMetaData dbMetaData = connection.getMetaData();

        List<Map<String, Object>> queryData = new ArrayList<>();

        Map<String,Object> row1 = new HashMap<>();
        row1.put("first_name","steven");
        row1.put("last_name", "King");
        row1.put("salary",24000);
        row1.put("job-id","ad_pres");
        System.out.println(row1.toString());

        Map<String,Object> row2 = new HashMap<>();
        row2.put("first_name","neena");
        row2.put("last_name", "Kochhar");
        row2.put("salary",17000);
        row2.put("job-id","ad_vp");
        System.out.println(row2.toString());

        System.out.println(row1.get("salary"));

        //adding rows
        queryData.add(row1);
        queryData.add(row2);

        //get the stecven name from direcly list
        System.out.println(queryData.get(0).get("last_name"));


        //close all connection
        resultSet.close();
        statement.close();
        connection.close();
    }
    @Test
    public void MetaDataExample2() throws SQLException {
        //creat connection
        Connection connection = DriverManager.getConnection(dburl, dbUsername, dbPassword);
        //statemanet
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        //run query and get result in resultset object
        ResultSet resultSet = statement.executeQuery("select first_name, last_name,salary, Job_id from employees where rownum<6");

        DatabaseMetaData dbMetaData = connection.getMetaData();

        ResultSetMetaData rsMetaData = resultSet.getMetaData();

        List<Map<String, Object>> queryData = new ArrayList<>();


        resultSet.next();
        Map<String,Object> row1 = new HashMap<>();

                row1.put(rsMetaData.getColumnName(1), resultSet.getString(1));
        row1.put(rsMetaData.getColumnName(2), resultSet.getString(2));
        row1.put(rsMetaData.getColumnName(3), resultSet.getString(3));
        row1.put(rsMetaData.getColumnName(4), resultSet.getString(4));

        System.out.println(row1.toString());

        Map<String,Object> row2 = new HashMap<>();
        resultSet.next();

        row1.put(rsMetaData.getColumnName(1), resultSet.getString(1));
        row1.put(rsMetaData.getColumnName(2), resultSet.getString(2));
        row1.put(rsMetaData.getColumnName(3), resultSet.getString(3));
        row1.put(rsMetaData.getColumnName(4), resultSet.getString(4));
        System.out.println(row2.toString());

        System.out.println(row2.get("salary"));

        //adding rows
        queryData.add(row1);
        queryData.add(row2);

        //get the stecven name from direcly list
        System.out.println(queryData.get(0).get("last_name"));


        //close all connection
        resultSet.close();
        statement.close();
        connection.close();
    }
}
