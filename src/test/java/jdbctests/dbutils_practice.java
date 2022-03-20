package jdbctests;

import org.testng.annotations.Test;
import utilities.DBUtils;

import java.util.List;
import java.util.Map;

public class dbutils_practice {

    @Test
    public void test1(){

        //create connections
        DBUtils.createConnection();
        List<Map<String, Object> >queryResult = DBUtils.getQueryResultMap("select * from departments");

        for (Map<String, Object> map : queryResult) {
            System.out.println(map.toString());

        }

        //close connections
        DBUtils.destroy();

    }
    @Test
    public void test2(){

        //create connections
        DBUtils.createConnection();
        Map<String, Object> rowMap = DBUtils.getRowMap("select first_name, last_name, salary,job_id from employees where employee_id=100");

        System.out.println(rowMap.toString());

        //close connections
        DBUtils.destroy();

    }
}
