package com.cydeo.day6;

import com.cydeo.pojo.Search;
import com.cydeo.pojo.Spartan;
import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
public class SpartanPojoGetRequestTest extends SpartanTestBase {

    @DisplayName("GET one spartan and convert it to Spartan Object")
    @Test
    public void oneSpartanPojo(){

        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id",15)
                .when().get("/api/spartans/{id}")
                .then().statusCode(200)
                .log().all()
                .extract().response();


        //Deserialize --> JSON TO POJO (java custom class) //POJO=Plain Old Java Objects
        //2 different way to do this
        //1. using as() method
        //we convert json response to spartan object with the help of jackson
        //as() method uses jackson to deserialize (converting JSON to Java class)
        Spartan spartan15 = response.as(Spartan.class);
        System.out.println(spartan15);
        System.out.println("spartan15.getId() = " + spartan15.getId());
        System.out.println("spartan15.getGender() = " + spartan15.getGender());
        System.out.println("spartan15.getName() = " + spartan15.getName());
        System.out.println("spartan15.getPhone() = " + spartan15.getPhone());



        //second way of deserialize json to java
        //2. using JSONPath to deserialize to custom class
        JsonPath jsonPath = response.jsonPath();

        Spartan s15 = jsonPath.getObject("",Spartan.class);
        System.out.println(s15);
        System.out.println("s15.getName() = " + s15.getName());
        System.out.println("s15.getId() = " + s15.getId());
        System.out.println("s15.getGender() = " + s15.getGender());
        System.out.println("s15.getPhone() = " + s15.getPhone());

    }

    @DisplayName("GET one spartan from search endpoint result and use POJO")
    @Test
    public void spartanSearchWithPojo(){//POJO=Plain Old Java Objects

        //  /spartans/search?nameContains=a&gender=Male
        // send request to above endpoint and save first object with type Spartan Pojo

        JsonPath jsonPath = given().accept(ContentType.JSON)
                .and().queryParams("nameContains", "a",
                        "gender", "Male")
                .when().get("/api/spartans/search")
                .then().statusCode(200)
                .extract().jsonPath();

        System.out.println("--------------");
        //get the first spartan from content list and put inside spartan object

        Spartan s1 = jsonPath.getObject("content[0]", Spartan.class);//using index number

        System.out.println("s1 = " + s1);
        System.out.println("s1.getName() = " + s1.getName());
        System.out.println("s1.getGender() = " + s1.getGender());
    }

    @Test
    public void test3(){

        Response response = given().accept(ContentType.JSON)
                .and().queryParams("nameContains", "a",
                        "gender", "Male")
                .when().get("/api/spartans/search")
                .then().statusCode(200)
                .extract().response();

        Search searchResult = response.as(Search.class);

        System.out.println(searchResult.getContent().get(0).getName());
        System.out.println(searchResult.getContent().get(0).getGender());
    }

   @DisplayName("GET /spartans/search and save as List<Spartan>")
    @Test
    public void test4(){
       List<Spartan> spartanList = given().accept(ContentType.JSON)
               .and().queryParams("nameContains", "a",
                       "gender", "Male")
               .when().get("/api/spartans/search")
               .then().statusCode(200)
               .extract().jsonPath().getList("content", Spartan.class);

       System.out.println(spartanList.get(1).getName());
       System.out.println(spartanList.get(1).getGender());

   }

   //





}
