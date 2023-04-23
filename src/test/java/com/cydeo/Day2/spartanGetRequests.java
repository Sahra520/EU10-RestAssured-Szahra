package com.cydeo.Day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class spartanGetRequests {

    String baseUrl = "http://54.226.109.52:8000";

    //Given: Accept type application/json
    //When user send Get request to api/spartans end point
    //Then status code must 200
    //And response Content Type must be application/json
    //And response body should include spartan result

    @Test
    public void test1(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .when()
                .get(baseUrl+"/api/spartans");

        //printing status code from response object
        System.out.println("response.statusCode() = " + response.statusCode());

        //printing response content type from response object
        System.out.println("response.contentType() = " + response.contentType());

        //print whole response body
        response.prettyPrint();

        //How to do API testing then?
        //verify status code is 200
        Assertions.assertEquals(response.statusCode(),200);

        //verify content type is application/json
        Assertions.assertEquals(response.contentType(),"application/json");
    }
}
