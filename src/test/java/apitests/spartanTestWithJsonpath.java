package apitests;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;
import static org.testng.Assert.*;
import static io.restassured.RestAssured.*;

import static io.restassured.RestAssured.baseURI;

public class spartanTestWithJsonpath {
    @BeforeClass
    public void beforeclass(){
        baseURI="http://3.93.150.56:8000";
    }

    /* given accept type json
    and path param spatan id is 11
    when user sends request to /spartans/{id}
    then status code 200
    and content type is json
    and "id:11,
    "name:"Nona",
    "gender":"Female",
    "phone": 7959094216
     */
    @Test
    public void test1(){

        Response response =  given().accept(ContentType.JSON)
                .and().pathParam("id",11)
                .when().get("/api/spartans/{id}");

        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json");

        int id= response.path("id");
        String name=response.path("name");

        assertEquals(id,11);
        assertEquals(name,"Nona");

        //assigned responce to jsonpath
        JsonPath jsonPath =response.jsonPath();

        int idjson =jsonPath.getInt("id");
        String nameJson = jsonPath.getString("name");
        String gender = jsonPath.getString("gender");
        long phone = jsonPath.getLong("phone");

        System.out.println("idjson = " + idjson);
        System.out.println("nameJson = " + nameJson);
        System.out.println("gender = " + gender);
        System.out.println("phone = " + phone);

        //verificition
        assertEquals(idjson,11);
        assertEquals(name,"Nona");
        assertEquals(gender,"Female");
        assertEquals(phone,7959094216l);



    }


}
