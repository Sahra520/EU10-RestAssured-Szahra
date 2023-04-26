package com.cydeo.Day3;



import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanTestsWithParameters {


    //BeforeAll is annotation equals to @BeforeClass in TestNG, we use with static method name
    @BeforeAll
    public static void init() {
        //save baseUrl inside this variable, so we don't need to type each http method
        baseURI = "http://54.226.109.52:8000";
    }

    @DisplayName("GET request to /api/spartans/{id} with ID 5")
    @Test
    public void test1(){
        //Given: accept type is json
        //And id parameter value is 5
        Response response = given()
                                .accept(ContentType.JSON)
                .and()
                                   .pathParam("id",5)
                .when().
                                get("/api/spartans/{id}");

        //When user sends GET request to /api/spartans/{id}
        //Then response status code should be 200
        assertEquals(200,response.statusCode());

        //And response content-type: application/json
        assertEquals("application/json", response.contentType());

        //And "Blythe" should be in response payload/body
        assertTrue(response.body().asString().contains("Blythe"));
    }

    @Test
    public void test2(){

        //Given: accept type is JSON
        //And ID parameter value is 500
        Response response = given()
                .accept(ContentType.JSON)
                .and()
                .pathParam("id",500)
                .when().
                get("/api/spartans/{id}");
        //When user sends GET request to /api/spartans/{id}

        //Then response status code should be 404
        assertEquals(404,response.statusCode());

        //And response ContentType: application/json
        assertEquals("application/json", response.contentType());

        //And "Not Found" message should be in response payload
        assertTrue(response.body().asString().contains("Not Found"));
    }

    @DisplayName("GET request to /api/spartans/search with Query Params")
    @Test
    public void test3(){
        //Given: accept type is JSON
        //And query parameter values are:
        //gender: Female
        //nameContains: e
        //When user send GET request to /api/spartans/search
        Response response = given()
                .log().all()
                .accept(ContentType.JSON)
                .and()
                .queryParam("nameContains", "e")
                .and()
                .queryParam("gender", "Female")
                .when()
                .get("/api/spartans/search");

        //Then status code should be 200
        assertEquals(200,response.statusCode());

        //And response ContentType: application/json
        assertEquals("application/json", response.contentType());

        //And "Female" should be in response payload/body
        assertTrue(response.body().asString().contains("Female"));

        //And "Janetta" should be in response payload(body)
        assertTrue(response.body().asString().contains("Janette"));
    }


}

