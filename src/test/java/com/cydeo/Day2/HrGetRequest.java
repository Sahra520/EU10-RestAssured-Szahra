package com.cydeo.Day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class HrGetRequest {


    //BeforeAll is annotation equals to @BeforeClass in TestNG, we use with static method name
    @BeforeAll
    public static void init(){
        //save baseUrl inside this variable, so we don't need to type each http method
        baseURI = "http://54.226.109.52:1000/ords/hr";
    }


    @DisplayName("GET request to /regions")
    @Test
    public void test1(){
        Response response = get("/regions");

        //print the status code
        System.out.println("response.statusCode() = " + response.statusCode());
    }


    @DisplayName("GET request to /regions/2")
    @Test
    public void test2(){
        //Given: Accept type application/json
        //When user send Get request to regions/2
        Response response = given().accept(ContentType.JSON)
                .when()
                .get("/regions/2");

        //Then status code must 200
        System.out.println("response.statusCode() = " + response.statusCode());
        //verify status code
        assertEquals(200,response.statusCode());

        //And contentType equals to application/json
        System.out.println("response.contentType() = " + response.contentType());
        //verify ContentType
        assertEquals("application/json",response.contentType());

        //print full body
        response.prettyPrint();

        //And response body contains Americas
        assertTrue(response.getBody().asString().contains("Americas"));
    }
}
