package com.cydeo.day7;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class Homework {

    //create one SpartanUtil.class
    //create a static method that returns Map<String,Object>
    //with dummy information
    //use faker library(add as a dependency) to assign each time different information
    //for name, gender, phone number.
    //Then use your method for creating spartan as a Map, dynamically.
    //use SpartanPostRequestDemo class 2.test way

    @DisplayName("POST with Map to JSON")
    @Test
    public void postMethod2(){
        //create a Map to keep Json body information

        Map<String,Object> requestJsonMap = new LinkedHashMap<>();
        requestJsonMap.put("gender", "Male");
        requestJsonMap.put("name", "Marcus");
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

        assertThat(response.path("data.name"), is("Marcus"));
        assertThat(response.path("data.gender"),is("Male"));
        assertThat(response.path("data.phone"),is(8877445596L));

        response.prettyPrint();

    }
}
