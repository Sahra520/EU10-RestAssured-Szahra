package com.cydeo.day7;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.util.LinkedHashMap;
import java.util.Map;

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
                //what we are asking from api which is JSON response
                .contentType(ContentType.JSON)//what we are sending to api, which is JSON also
                .body(requestJsonBody)
                .when()
                //.log().all()
                .post("/api/spartans");

        //verify status code
        assertThat(response.statusCode(),is(201));
        assertThat(response.contentType(), is("application/json"));

        String expectedResponseMessage = "A Spartan is Born!";
        assertThat(response.path("success"),is(expectedResponseMessage));

        assertThat(response.path("data.name"), is("Severus"));
        assertThat(response.path("data.gender"),is("Male"));
        assertThat(response.path("data.phone"),is(8877445596L));
    }



    @DisplayName("POST with Map to JSON")
    @Test
    public void postMethod2(){
        //create a Map to keep Json body information
        Map<String,Object> requestJsonMap = new LinkedHashMap<>();
        requestJsonMap.put("gender", "Male");
        requestJsonMap.put("name", "Severus");
        requestJsonMap.put("phone", 8877445596L);

        Response response = given().accept(ContentType.JSON).and()
                //what we are asking from api which is JSON response
                .contentType(ContentType.JSON)//what we are sending to api, which is JSON also
                .body(requestJsonMap).log().all()
                .when()
                .post("/api/spartans");

        //verify status code
        assertThat(response.statusCode(),is(201));
        assertThat(response.contentType(), is("application/json"));

        String expectedResponseMessage = "A Spartan is Born!";
        assertThat(response.path("success"),is(expectedResponseMessage));

        assertThat(response.path("data.name"), is("Severus"));
        assertThat(response.path("data.gender"),is("Male"));
        assertThat(response.path("data.phone"),is(8877445596L));

        response.prettyPrint();

    }
}
