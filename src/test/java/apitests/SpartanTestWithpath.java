package apitests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

import java.util.List;

public class SpartanTestWithpath {
    @BeforeClass
    public void beforeclass(){
        baseURI="http://3.93.150.56:8000";
    }
 /*
   Given accept type is json
   And path param id is 10
   When user sends a get request to "api/spartans/{id}"
   Then status code is 200
   And content-type is "application/json;charset=UTF-8"
   And response payload values match the following:
           id is 10,
           name is "Lorenza",
           gender is "Female",
           phone is 3312820936
    */
    @Test
    public void getOneSpartan_path(){
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 10)
                .when().get("/api/spartans/{id}");

        Assert.assertEquals(response.statusCode(),200);
        Assert.assertEquals(response.contentType(),"application/json");

        //response.prettyPrint();
        //printing each key value in json body
        System.out.println(response.path("id").toString());
        System.out.println(response.path("name").toString());
        System.out.println(response.path("gender").toString());
        System.out.println(response.path("phone").toString());

        //save json key values
        int id= response.path("id");
        String name= response.path("name");
        String gender= response.path("gender");
        long phone= response.path("phone");

        System.out.println(id);
        System.out.println("name = " + name);
        System.out.println("gender = " + gender);
        System.out.println("phone = " + phone);

        Assert.assertEquals(id,10);
        Assert.assertEquals(name,"Lorenza");
        Assert.assertEquals(gender,"Female");
        Assert.assertEquals(phone, 3312820936L);

    }
    @Test
    public void getAllsSpartanWithPAth(){

        Response response = given().accept(ContentType.JSON)
                .when().get("api/spartans");

        assertEquals(response.statusCode(),200);
        assertEquals(response.getHeader("Content-Type"),"application/json");

        int firstId= response.path("id[0]");
        System.out.println("firstId = " + firstId);

        String firstName= response.path("name[0]");
        System.out.println("firstName = " + firstName);

        int lastId= response.path("id[-1]");
        System.out.println("lastId = " + lastId);

        String lastFirstName= response.path("name[-1]");
        System.out.println("lastFirstName = " + lastFirstName);

        //print all names
        List<String > names = response.path("name");
        System.out.println("names = " + names);

    }

}
