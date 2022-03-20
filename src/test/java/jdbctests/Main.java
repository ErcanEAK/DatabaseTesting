package jdbctests;

import java.sql.*;

public class Main {

    public static void main(String[] args) throws SQLException {

        String dburl= "jdbc:oracle:thin:@18.233.164.111:1521:xe";
        String dbUsername = "hr";
        String dbPassword = "hr";

        //creat connection
        Connection connection = DriverManager.getConnection(dburl,dbUsername,dbPassword);
        //statemanet
        Statement statement = connection.createStatement();
        //run query and get result in resultset object
        ResultSet resultSet = statement.executeQuery("select * from departments");

        /*//movw to first row
        resultSet.next();
        System.out.println(resultSet.getString("region_name"));
        //getting with coulumn index
        System.out.println(resultSet.getString(2));
        System.out.println(resultSet.getInt(1) + " - " + resultSet.getString("region_name"));

        resultSet.next();        //move to pointer

        System.out.println(resultSet.getString("region_name"));
        System.out.println(resultSet.getString(2));

        System.out.println(resultSet.getString(1) + " - " + resultSet.getString("region_name"));
*/

        /*while (resultSet.next()){       //tüm listeyi almak için loop görevi görüyor while ve next method.

            System.out.println(resultSet.getString(1) + " - " +resultSet.getString(2) + " - "+ resultSet.getString(3)+" - "+resultSet.getString(4));
        }

*/
        resultSet.next();
        System.out.println(resultSet.getString(4)+ " - "+ resultSet.getString("manager_id"));
        resultSet.next();
        System.out.println(resultSet.getString(4)+ " - "+ resultSet.getString("manager_id"));

        //close all connection
        resultSet.close();
        statement.close();
        connection.close();





    }
}
