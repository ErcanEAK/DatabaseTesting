package apitests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matcher;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class simplegetrequest {

        String hrUrl= "http://3.93.150.56:1000/ords/hr/regions";
    @Test
    public void test1(){

        Response response = RestAssured.get(hrUrl);

        System.out.println(response.statusCode());

        //print the json body
        response.prettyPrint();


    }
    /*given accept type is json
    WHEN USER SENDS GET REQUEST TO REGİONS  ENDPOİTNG
    then response status code must be 200
    and body is json format*
     */
    @Test
    public void test2(){
        Response response = given().accept(ContentType.JSON)
                            .when().get(hrUrl);

        //verify response status is 200
        Assert.assertEquals(response.statusCode(),200);

        System.out.println(response.contentType());

        //is body json, verify that
        Assert.assertEquals(response.contentType(),"application/json");

    }
    @Test
    public void teest3(){
        RestAssured.given().accept(ContentType.JSON)
                .when().get(hrUrl).then()
                .assertThat().statusCode(200)
                .and().contentType("application/json");

    }
        /*given accept type is json
        when user sends get request to regions/2
        then response status code must be 200
        and body is json format
        and response body contains Americas
         */

    @Test
    public void test4(){
        Response response = given().accept(ContentType.JSON)
                .when().get(hrUrl + "/2");

        Assert.assertEquals(response.getStatusCode(),200);

        Assert.assertEquals(response.contentType(),"application/json");

        Assert.assertTrue(response.body().asString().contains("Americas"));

                /*then()
                .assertThat().statusCode(200)
                .and().contentType("application/json")
                .body(Matcher<Ame>);
*/


    }
}
