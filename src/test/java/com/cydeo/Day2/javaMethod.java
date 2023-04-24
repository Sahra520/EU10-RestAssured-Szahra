package com.cydeo.Day2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

public class javaMethod {
    String url = "http://54.226.109.52:8000/api/spartans";


    @Test
    public void
    lotto_resource_returns_200_with_expected_id_and_winners() {

        Response response = RestAssured.get(url);


        when().
                get("/lotto/{id}", 5).
                then().
                statusCode(200).
                body("lotto.lottoId", equalTo(5),
                        "lotto.winners.winnerId", hasItems(23, 54));

    }
}
