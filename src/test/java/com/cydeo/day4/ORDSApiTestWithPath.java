package com.cydeo.day4;

import com.cydeo.utilities.HRTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;


public class ORDSApiTestWithPath extends HRTestBase {

    @DisplayName("GET request to countries with Path Method")
    @Test
    public void test1() {

        Response response = given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"region_id\":2}")
                .when()
                .get("/countries");

        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.contentType());

        //print limit result
        System.out.println("response.path(\"limit\") = " + response.path("limit"));

        //print hasMore
        System.out.println("response.path(\"hasMore\") = " + response.path("hasMore"));

        //print first CountryID
        String firstCountryID = response.path("items[0].country_id");
        System.out.println("firstCountryID = " + firstCountryID);

        //print first country name:
        String firstCountryName = response.path("items[0].country_name");
        System.out.println("firstCountryName = " + firstCountryName);


        //print second country name:
        String secondCountryName = response.path("items[1].country_name");
        System.out.println("secondCountryName = " + secondCountryName);

        String thirdHref = response.path("items[2].links[0].href");
        System.out.println("thirdHref = " + thirdHref);
//work with indexes which is starting from 0


        //get me all country names
        List<String> countryNames = response.path("items.country_name");
        System.out.println("countryNames = " + countryNames);


        //assert that all regions_id are equal to 2
        List<Integer> allRegionsIDs = response.path("items.region_id");

        for (Integer regionsID : allRegionsIDs) {
            System.out.println("regionsID = " + regionsID);
            assertEquals(2, regionsID);
        }

    }
      @Test
     public void test2(){
        Response response = given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"job_id\":\"IT_PROG\"}")
                .when().get("/employees");

        assertEquals(200,response.statusCode());

        assertEquals("application/json",response.contentType());

      //make sure we have only IT_PROG as a job_id
        List<String> allJobIds = response.path("items.job_id");

        for (String jobID : allJobIds) {
            System.out.println("jobID = " + jobID);
            assertEquals("IT_PROG", jobID);

        }

    }





}
