package com.cydeo.utilities;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;

public abstract class SpartanTestBase {

    //BeforeAll is annotation equals to @BeforeClass in TestNG, we use with static method name
    @BeforeAll
    public static void init() {
        //save baseUrl inside this variable, so we don't need to type each http method
        baseURI = "http://54.226.109.52:8000";

        String dbUrl = "jdbc:oracle:thin:@54.226.109.52:1521:xe";
        String dbUsername = "SP";
        String dbPassword = "SP";

       // DBUtils.createConnection(dbUrl,dbUsername,dbPassword);
    }

    @AfterAll
    public static void tearDown(){
        //DBUtils.destroy();
    }
}
