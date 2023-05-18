package com.cydeo.utilities;

import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;

public abstract class SpartanAuthTestBase {

    @BeforeAll
    public static void init() {
        //save baseUrl inside this variable, so we don't need to type each http method
        baseURI = "http://54.226.109.52:7000";



        // DBUtils.createConnection(dbUrl,dbUsername,dbPassword);
    }
}
