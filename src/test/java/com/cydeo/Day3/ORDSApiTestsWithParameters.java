package com.cydeo.Day3;



import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;


public class ORDSApiTestsWithParameters {

    //BeforeAll is annotation equals to @BeforeClass in TestNG, we use with static method name
    @BeforeAll
    public static void init(){
        //save baseUrl inside this variable, so we don't need to type each http method
        baseURI = "http://54.226.109.52:1000/ords/hr";
    }

@DisplayName("GET request to /countries with Query Param")
    @Test
    public void test1(){
        //Given: accept type is JSON
        //And parameters: q = {"region_id":2}
        //When user send a GET request to "/countries"
        Response response = given().accept(ContentType.JSON)
                .and().queryParam("q","{\"region_id\":2}")
                .log().all()
                .when()
                .get("/countries");

        //Then status code is 200
        assertEquals(200,response.statusCode());

        //And ContentType is application/json
        assertEquals("application/json",response.header("Content-Type"));

        //And Payload should contain "United States of America"
        assertTrue(response.body().asString().contains("United States of America"));

        response.prettyPrint();

        //{"region_id":2}
    }

    @DisplayName("GET request to /employees with Query Param")
    @Test
    public void test2(){
        //send a GET request to employees and get only employees who work as  IT_PROG

        Response response = given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"job_id\": \"IT_PROG\"}")
                .and().queryParam("q","{\"salary\": 10000}")
                .when().log().all()
                .get("/employees");

        //Then status code is 200
        assertEquals(200,response.statusCode());

        //And ContentType is application/json
        assertEquals("application/json",response.contentType());

        //And Payload should contain "United States of America"
        assertTrue(response.body().asString().contains("IT_PROG"));

        response.prettyPrint();
    }
}
