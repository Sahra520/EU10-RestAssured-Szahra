package com.cydeo.day5;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class HamcrestMatchersApiTest {

    //pure API test-->if we want to be Api tester, it is pure API Test

    @DisplayName("OneSpartan with Hamcrest chaining")
    @Test
    public void test1(){
        //Given: accept type is json
        //And path param id is 15
        //When user sends a GET request to spartans/{id}
        //Then status code is 200
        //And contentType is Json
        //And json data has following
              // "id": 15;,
              // "name": "Meta",
              // "gender": "Female"
              // "phone": 1938695106

        given()
                .accept(ContentType.JSON)
                .and().pathParam("id",15)
                .when()
                .get("http://54.226.109.52:8000/api/spartans/{id}")
                .then()
                .statusCode(200)
                .and()
                .contentType("application/json")
                .body("id",is(equalTo(15)),
                "name", equalTo("Meta"),
                        "gender",is(equalTo("Female")),
                        "phone",is(1938695106));
    }


    @DisplayName("CBTraining Teacher request with chaining and matchers")
    @Test
    public void teacherData(){
        given().accept(ContentType.JSON)
                .and()
                .pathParam("id",10430)
                .when()
                .get("\"https://api.training.cydeo.com/teacher/{id}")
                .then()
                .statusCode(200)
                .and()
                .contentType("application/json;charset=UTF-8")
                .and()
                .header("Content-Length",is("236"))
                .and()
                .header("Date",notNullValue())
                .and().assertThat()
                .body("teachers[0].firstName", is("Alexander"))
                .body("teachers[0].lastName",is("Syrup"))
                .body("teachers[0].gender", equalTo("male"));
    }


    @DisplayName("Get request to teacher/all and chaining")
    @Test
    public void teachersTest(){

        //verify Alexander,Darleen, Sean inside the all teachers
        given().accept(ContentType.JSON)
                .when()
                .get("\"https://api.training.cydeo.com/teacher/all")
                .then()
                .statusCode(200)
                .and()
                .body("teacher.firstName",hasItems("Alexander", "Darleen", "Sean","Jamal"));




    }

    @Test
    public void test3(){
        given()
                .accept(ContentType.JSON)
                .and()
                .pathParam("id",3)
                .when()
                .get("https://api.training.cydeo.com/teacher/{id}")
                .then()
                .statusCode(200)
                .and()
                .contentType("application/json;charset=UTF-8")
                .and()
                .header("Date",notNullValue())
                .and().assertThat()
                .body("teachers[0].firstName",is("Tet"))
                .body("teachers[0].lastName",is("DS"))
                .body("teachers[0].gender",equalTo("Male"));
    }
}
