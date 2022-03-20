package apitests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.awt.*;
import java.util.List;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class hrApiWithPath {

    @BeforeClass
    public void beforeclass(){
        baseURI= ConfigurationReader.get("hr_api_url");
    }
    @Test
    public void getCountriesWithpath(){

        Response response = given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"region_id\":2}")
                .when().get("countries");

        assertEquals(response.statusCode(),200);

        //print limit value
        System.out.println("response.path(\"limit\") = " + response.path("limit"));

        System.out.println("response.path(\"hasMore\").toString() = " + response.path("hasMore").toString());

        String firstCountrId= response.path("items.country_id[0]");

        System.out.println("firstCountrId = " + firstCountrId);

        String brzCountrId= response.path("items.country_name[1]");

        System.out.println("brzCountrId = " + brzCountrId);

        String link2 = response.path("items.links[2].href[0]"); //array içinde array var.index number kullanıyroz
        System.out.println("link2 = " + link2);                        //bunların içinde "." nokta ile child a geçiyoruz

        List<String > countryNames= response.path("items.country_name");
        System.out.println("countryNames = " + countryNames);

        //assert that all regions id's equal to 2

        List<Integer> regionIds = response.path("items.region_id");

        for (int regionId : regionIds) {
            System.out.println(regionId);
            assertEquals(regionId,2);

        }
    }
    @Test
    public void test2(){
        Response response = given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"job_id\":\"IT_PROG\"}")
                .when().get("employees");

            List<String> jobIds= response.path("items.job_id");

        for (String jobId : jobIds) {

            System.out.println("jobId = " + jobId);
            assertEquals(jobId,"IT_PROG");

        }


    }
}
