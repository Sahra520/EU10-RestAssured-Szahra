package com.cydeo.day5;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class JsonToJavaTest extends SpartanTestBase {

    @DisplayName("GET one Spartan and deserialize to Map")
    @Test
    public void oneSpartanToMap(){

        Response response = given().pathParam("id",15)
                .when().get("/api/spartans/{id}")
                .then().statusCode(200).extract().response();


//in order to as() method we have to add some dependencies in pom.xml file


        //get the json and convert it to the Map
        Map<String,Object> jsonMap = response.as(Map.class);

        System.out.println(jsonMap.toString());
        //{id=15, name=Meta, gender=Female, phone=1938695106}
        //it is Map not json


        //after we got the map, we can use hamcrest or junit assertions to do assertion
        String actualName = (String) jsonMap.get("name");
        assertThat(actualName,is("Meta"));

    }


    @DisplayName("GET all spartans to JAVA data structure")
    @Test
    public void getAllSpartan(){

        Response response = get("/api/spartans")
                .then().statusCode(200).extract().response();

        //we need to convert json to java which is deserialized
        List<Map<String,Object>> jsonList = response.as(List.class);//API part

        System.out.println(jsonList.get(0));//{id=1, name=Meade, gender=Male, phone=3584128232}
                            //java part
        System.out.println("jsonList.get(1).get(\"name\") = " + jsonList.get(1).get("name"));

        Map<String,Object>spartan3 = jsonList.get(2);
        System.out.println(spartan3);


    }
}
