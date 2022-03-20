package apitests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class SpartanGetRequest {

    String spartanUrl= "http://3.93.150.56:8000";

    @Test
    public void test1(){

        Response response= when().get(spartanUrl+"/api/spartans");
        System.out.println(response.statusCode());
        response.prettyPrint();


    }

    /*When users sends a get request to api/spartans/3
    Then statudcode should be 200
    and content typr should be application /json;charset=uft-8
    and json body should contain fidole
     */
    @Test
    public void test2(){

        Response response= when().get(spartanUrl+"/api/spartans/3");
        Assert.assertEquals(response.statusCode(),200);
        Assert.assertEquals(response.contentType(),"application/json");
        Assert.assertTrue(response.body().asString().contains("Fidole"));

    }
    /*given no headers provided
    When users sent  get request to /api/hello
    then response status code should be 200
    and content type header should be "text/plain;charset=UTF-8"
    and header should contain date
    and contentlenght should be 17
    and body should be "Hello from Sparta"
     */

    @Test
    public void test3(){
        //request
        Response response = when().get(spartanUrl + "/api/hello");
        //verify status code
        Assert.assertEquals(response.statusCode(),200);
        //verify content type
        Assert.assertEquals(response.contentType(),"text/plain;charset=UTF-8");
        //verify dtring length
        Assert.assertEquals(response.asString().length(),17);
        //other way
        //Assert.assertEquals(response.header("Content_Length"),"17");
        //verify string
        Assert.assertEquals(response.asString(),"Hello from Sparta");
        //verify we have headeres named date
        System.out.println(response.header("Content_Length"));
        Assert.assertTrue(response.headers().hasHeaderWithName("Date"));


    }



}
