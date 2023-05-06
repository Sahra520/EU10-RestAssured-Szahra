package com.cydeo.day5;

import com.cydeo.utilities.HRTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ORDSHamcrestTest extends HRTestBase {

    @DisplayName("GET request to Employees IT_PROG endpoint and chaining")
    @Test
    public void employeesTest() {
        //send a GET request to employees endpoint with query parameter job_id IT_PROG
        //verify each job_id is IT_PROG
        //verify first names are...(find proper method to check list against list)
        /*verify emails without checking order
        (provide emails in different order, just make sure it has same emails)*/
        List<String> names = Arrays.asList("Alexander", "Bruce", "David", "Valli", "Diana");//expected names

        given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"job_id\":\"IT_PROG\"}")//most important one, try to use this
                .when().get("/employees")
                .then()
                .statusCode(200)
                .body("items.job_id", everyItem(equalTo("IT_PROG")))
                //to get names directly
                //"Alexander","Bruce","David","Valli","Diana"
                //verify order is the same
                .body("items.first_name", containsInRelativeOrder(
                        "Alexander", "Bruce", "David", "Valli", "Diana"))//contains with order
                .body("items.email", containsInAnyOrder(
                        "AHUNOLD", "BERNST", "DAUSTIN", "VPATABAL", "DLORENTZ"))//contains without order
                .body("items.first_name", equalToObject(names));//equality of lists assertion
        //equalTO() also works
    }


    @Test
    public void employeesTest2(){
        //we want to chain and get response object
        Response response = given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"job_id\":\"IT_PROG\"}")//most important one, try to use this
                .when().get("/employees")
                .then()
                .statusCode(200)
                .body("items.job_id", everyItem(equalTo("IT_PROG"))).extract().response();

        response.prettyPrint();

    }


    @Test
    public void employeesTest3(){
        //we want to chain and get response object
        int statusCode = given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"job_id\":\"IT_PROG\"}")//most important one, try to use this
                .when().get("/employees")
                .then()
                .statusCode(200)
                .body("items.job_id", everyItem(equalTo("IT_PROG"))).extract().response().statusCode();



    }


    @Test
    public void employeesTest4(){
        //we want to chain and get extract().jsonPath()
        JsonPath jsonPath = given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"job_id\":\"IT_PROG\"}")//most important one, try to use this
                .when().get("/employees")
                .then()
                .statusCode(200)
                .body("items.job_id", everyItem(equalTo("IT_PROG"))).extract().jsonPath();

        //assert that we have only 5 first_names
        assertThat(jsonPath.getList("items.first_name"),hasSize(5));

        //assert first_names order
        assertThat(jsonPath.getList("items.first_name"),containsInRelativeOrder("Alexander", "Bruce", "David", "Valli", "Diana"));


    //extract() --> method that allow us to get response object after we use then() method.



    }
}
