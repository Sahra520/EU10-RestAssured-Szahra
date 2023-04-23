package com.cydeo.Day1;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.protocol.ResponseServer;
import org.junit.jupiter.api.Test;

public class SimpleGetRequest {

    String url = "http://54.226.109.52:8000/api/spartans";

    @Test
    public void test1(){

        Response response = RestAssured.get(url);

        //print response status code
        System.out.println(response.statusCode());
        /*200-->passed*/


        //print status bode
        response.prettyPrint();
        /*
        [
    {
        "id": 1,
        "name": "Meade",
        "gender": "Male",
        "phone": 3584128232
    },
    {
        "id": 2,
        "name": "Nels",
        "gender": "Male",
        "phone": 4218971348
    },
.......................
 }
]
         */

    }




    @Test
    public void test2(){
        Response response = RestAssured.get(url);

        System.out.println(response.prettyPrint());
    }




}





