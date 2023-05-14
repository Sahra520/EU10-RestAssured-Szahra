package com.cydeo.day7;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class PutAndPatchRequestDemo extends SpartanTestBase {


    @DisplayName("Put request to one spartan for update with Map")
    @Test
    public void PUTRequest(){

        //just like POST request we have different options to send body, we will go with Map

        Map<String, Object> putRequestMap = new LinkedHashMap<>();

        putRequestMap.put("name", "Yusuf");
        putRequestMap.put("gender", "Male");
        putRequestMap.put("phone", 9876754321L);

        given().contentType(ContentType.JSON)//hey api, I'm sending Json body
                .body(putRequestMap).log().body()
                .and().pathParam("id",157)
                .when().put("/api/spartans/{id}")
                .then()
                .statusCode(204);


        /*
        send a request after update, make sure updated field changed, or the new info matching
        with requestBody that we send
         */
    }


    @DisplayName("PATCH request to one spartan for partial update with Map")
    @Test
    public void PATCHRequest() {

        //just like POST request we have different options to send body, we will go with Map

        Map<String, Object> putRequestMap = new LinkedHashMap<>();

        putRequestMap.put("phone", 8767543219L);

        given().contentType(ContentType.JSON)//hey api, I'm sending Json body
                .body(putRequestMap).log().body()
                .and().pathParam("id", 157)
                .when().patch("/api/spartans/{id}")
                .then()
                .statusCode(204);

    }

    @DisplayName("DELETE one spartan")
    @Test
    public void deleteSpartan(){
            int idToDelete = 110;

        given().pathParam("id",idToDelete)
                .when().delete("/api/spartans/{id}")
                .then().statusCode(204);//VERIFIED: status code is 404

        //send a GET request after you delete make sure you're getting 404
    }


    }
//https://restful-booker.herokuapp.com/apidoc/index.html