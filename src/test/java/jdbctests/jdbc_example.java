package jdbctests;

import org.testng.annotations.Test;

import java.sql.*;

public class jdbc_example {

    String dburl= "jdbc:oracle:thin:@18.233.164.111:1521:xe";
    String dbUsername = "hr";
    String dbPassword = "hr";

    @Test
    public void test1() throws SQLException {

        //creat connection
        Connection connection = DriverManager.getConnection(dburl,dbUsername,dbPassword);
        //statemanet
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        //run query and get result in resultset object
        ResultSet resultSet = statement.executeQuery("select * from departments");

        resultSet.last();
        int rowCount = resultSet.getRow();
        System.out.println(rowCount);

        resultSet.beforeFirst();

        while (resultSet.next()){
            System.out.println(resultSet.getString(2));
        }


        //close all connection
        resultSet.close();
        statement.close();
        connection.close();

    }
    @Test
    public void MetaDateExample() throws SQLException {

        //creat connection
        Connection connection = DriverManager.getConnection(dburl,dbUsername,dbPassword);
        //statemanet
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        //run query and get result in resultset object
        ResultSet resultSet = statement.executeQuery("select * from employees");

        DatabaseMetaData dbMetaData = connection.getMetaData();

        System.out.println("user ="+ dbMetaData.getUserName());
        System.out.println("database product Name= "+dbMetaData.getDatabaseProductName());
        System.out.println("database Product Version= "+dbMetaData.getDatabaseProductVersion());
        System.out.println("Driver Name= "+dbMetaData.getDriverName());
        System.out.println("Driver Version= "+dbMetaData.getDriverVersion());

        //get object resultset
        ResultSetMetaData rsMetaData = resultSet.getMetaData();

            int colCount = rsMetaData.getColumnCount();
        System.out.println(colCount);

        System.out.println(rsMetaData.getColumnName(1));
        System.out.println(rsMetaData.getColumnName(2));

        int i =0;
        for (int j = 1; j <= colCount; j++) {
            System.out.println(rsMetaData.getColumnName(j));
        }



        //close all connection
        resultSet.close();
        statement.close();
        connection.close();

    }


}
