package com.cydeo.Day2;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class HrGetRequest {


    //BeforeAll is a annotation equals to @BeforeClass in TestNG, we use with static method name
    @BeforeAll
    public static void init(){
        RestAssured.baseURI="http://54.226.109.52:1000/ords/hr";
    }
}
