package apitests;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;
import static org.testng.Assert.*;
import static io.restassured.RestAssured.*;
public class HamcrestMathcersApiTest {

    @Test
    public void oneSpartanWithhamcrest(){

          /*
      given accept type is Json
      And path param id is 15
      When user sends a get request to spartans/{id}
      Then status code is 200
      And content type is Json
      And json data has following
          "id": 15,
          "name": "Meta",
          "gender": "Female",
          "phone": 1938695106
       */
        given().accept(ContentType.JSON)
                .and().pathParam("id",15).
                when().get("http://3.93.150.56:8000/api/spartans/{id}")
                .then().statusCode(200)
                .and().assertThat().contentType(equalTo("application/json"))
                .and().assertThat().body("id",equalTo(15),
                        "name",equalTo("Meta"),
                        "gender",equalTo("Female"),
                        "phone",equalTo(1938695106));

    }
    @Test
    public void teacherData(){
        given().accept(ContentType.JSON)
                .and().pathParam("id",10423)
                .when().log().all().get("http://api.cybertektraining.com/teacher/{id}")
                .then().assertThat().statusCode(200)
                .and().contentType(equalTo("application/json;charset=UTF-8"))
                .and().header("Vary",equalTo("Accept-Encoding"))
                .and().header("Connection",equalTo("Keep-Alive"))
                .and().header("Date",notNullValue())
                .and().assertThat().body("teachers.firstName[0]",equalTo("Alexander"),
                        "teachers.lastName[0]",equalTo("Syrup"),
                                                 "teachers.gender[0]",equalTo("male"))
                .log().all();

    }
    @Test
    public void teachearsWithDepartments(){

        given().accept(ContentType.JSON)
                .and().pathParam("name","Computer")
                .when().log().all().get("http://api.cybertektraining.com/teacher/department/{name}")
                .then().statusCode(200).and()
                .contentType("application/json;charset=UTF-8")
                .body("teachers.firstName",hasItems("Alexander","Marteen"));
    }
}
