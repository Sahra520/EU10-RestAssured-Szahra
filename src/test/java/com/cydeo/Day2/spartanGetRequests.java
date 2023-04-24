package com.cydeo.Day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
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
        Assertions.assertEquals(200,response.statusCode());

        //verify content type is application/json
        Assertions.assertEquals("application/json",response.contentType());
    }


    @Test
    public void test2(){
        //Given: accept is application/json
        //When user sends a get request to api/spartans/3
        //Then status code should be 200
        //And contentType should be application/json
        //And json body should contain Fidole

        Response response = RestAssured.given().accept(ContentType.JSON)
                .when()
                .get(baseUrl+"/api/spartans/3");

        //Then status code should be 200
        Assertions.assertEquals(200,response.statusCode());

        //And contentType should be application/json
        Assertions.assertEquals("application/json",response.contentType());

        //And json body should contain Fidole
        Assertions.assertTrue(response.getBody().asString().contains("Fidole"));

    }

    @DisplayName("Get request to a/api/hello")
    @Test
    public void test3(){

        //Given no headers provided
        //When user send Get request to /api/hello
        Response response = RestAssured.when()
                .get(baseUrl+"/api/hello");

        //Then status code should be 200
        Assertions.assertEquals(200,response.statusCode());


        //And contentType header should be "text/plain;charset=UTF-8"
        Assertions.assertEquals("text/plain;charset=UTF-8",response.contentType());

        //And header should contain date
        //verify we have headers name date
        //we use hasHeaderWithName() method to verify header exist or not
        Assertions.assertTrue(response.headers().hasHeaderWithName("Date"));

        //And Content-Length should be 17
        Assertions.assertEquals("17",response.header("Content-Length"));
        System.out.println("response.header(\"Content-Length\") = " + response.header("Content-Length"));
        //how to get header from response using header key?
        //we use response.header(String headerName) method to get any header value
        System.out.println(response.header("Date"));

        //And body should be "Hello from Sparta"
        Assertions.assertEquals(("Hello from Sparta"),response.body().asString());



    }
}
