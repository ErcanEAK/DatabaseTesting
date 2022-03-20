package apitests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.Map;

import static org.hamcrest.Matchers.*;
import static org.testng.Assert.*;
import static io.restassured.RestAssured.*;

import static io.restassured.RestAssured.baseURI;

public class jsonTojavaCollection {
    @BeforeClass
    public void beforeclass(){
        baseURI="http://3.93.150.56:8000";
    }
    @Test
    public void SpartanToMap(){
        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 15)
                .when().get("/api/spartans/{id}");

        assertEquals(response.statusCode(),200);

        Map<String, Object> jsonDataMap= response.body().as(Map.class);
        System.out.println("jsonDataMap = " + jsonDataMap);

        String name = (String) jsonDataMap.get("name");
        assertEquals(name,"Meta");

        BigDecimal phone = new BigDecimal(String.valueOf(jsonDataMap.get("phone")));
        System.out.println("phone = " + phone);

    }
}
