package com.cydeo.day7;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;


import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class SpartanPostRequestDemo extends SpartanTestBase {

    /*Swagger documentations has options which is called "Try it out",
    allow us to send request from browser. It generates the response and also something called "CURL"
     */

/*
Given: accept type is and ContentType is JSON
And request body is:
{
"gender": "Male",
"name": "Severus",
"phone": 8877445596
}
When uer sends POST request to '/api/spartans'
Then status code 201
And ContentType should be application/json
And json payload/response/body should contain:
"A Spartan is Born!"
and same data what is posted
 */

    @Test
    public void postMethod1(){
        String requestJsonBody = "{\"gender\":\"Male\",\n"+
                "\"name\": \"Severus\",\n" +
                "\"phone\": 8877445596}";

        Response response = given().accept(ContentType.JSON).and()
                .contentType(ContentType.JSON)
                .body(requestJsonBody)
                .when()
                .post("/api/spartans");

        //verify status code
        assertThat(response.statusCode(),is(201));
        assertThat(response.contentType(), is("application/json"));

        String expectedResponseMessage = "A Spartan is Born!";
        assertThat(response.path("success"),is(expectedResponseMessage));
    }
}
