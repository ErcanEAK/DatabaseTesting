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

public class CBTrainingWithJsonpath {
    @BeforeClass
    public void beforeclass(){
        baseURI= ConfigurationReader.get("cbt_api_url");
    }
    @Test
    public void test1(){
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 24668)
                .when().get("/student/{id}");

        assertEquals(response.statusCode(),200);

        JsonPath jsonPath = response.jsonPath();

        String firstName =jsonPath.getString("students.firstName[0]");
        System.out.println("firstName = " + firstName);

        String lastName= jsonPath.getString("students.lastName[0]");
        System.out.println("lastName = " + lastName);

        String phoneNums = jsonPath.getString("students.contact[0].contactId");
        System.out.println("phoneNums = " + phoneNums);

        String addressId=jsonPath.getString("students.company[0].address.addressId");
        System.out.println("city = " + addressId);
        assertEquals(addressId,"24608");

        int zipCode= jsonPath.getInt("students.company[0].address.zipCode");
        System.out.println("zipCode = " + zipCode);     //Jsonpath int veya string olmasına bakmıyor kendi hallediyoe
        assertEquals(zipCode,0);                //array olan durumda da hata vermiyor ilk sıradakini veriyor string dersen

        String firstName2= jsonPath.getString("students.firstName");
        System.out.println("firstName2 = " + firstName2);




    }
}
