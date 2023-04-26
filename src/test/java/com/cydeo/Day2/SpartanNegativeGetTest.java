package com.cydeo.Day2;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.baseURI;



import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
public class SpartanNegativeGetTest {


    //BeforeAll is annotation equals to @BeforeClass in TestNG, we use with static method name
    @BeforeAll
    public static void init(){
        //save baseUrl inside this variable, so we don't need to type each http method
        baseURI = "http://54.226.109.52:8000";
    }

    @DisplayName("GET request to api/spartans/10")
    @Test
    public void task(){
        //Given: accept type application/xml
        //When user send GET request to api/spartans/10 end point

        Response response = given()
                        .accept(ContentType.XML)
                .when()
                        .get("/api/spartans/10");

        //Then status code must be 406
        assertEquals(406, response.statusCode());


        assertEquals("application/xml;charset=UTF-8",response.contentType());

        response.prettyPrint();
        System.out.println("response.statusCode() = " + response.statusCode());
        //And response ContentType must be application/xml




    }
}
