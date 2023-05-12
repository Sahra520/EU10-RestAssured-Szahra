package com.cydeo.day6;

import com.cydeo.pojo.Employee;
import com.cydeo.pojo.Link;
import com.cydeo.pojo.Region;
import com.cydeo.pojo.Regions;
import com.cydeo.utilities.HRTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ORDSPojoGETRequestTest extends HRTestBase {

    @Test
    public void regionTest(){

       JsonPath jsonPath = get("/regions").then().statusCode(200).extract().jsonPath();

       Region region1 = jsonPath.getObject("items[0]", Region.class);

       System.out.println(region1);

        System.out.println("region1.getRegion_name() = " + region1.getRegName());

        System.out.println("region1.getRegionId() = " + region1.getRegionId());

        System.out.println("region1.getLinks() = " + region1.getLinks());

        System.out.println("region1.getLinks().get(0) = " + region1.getLinks().get(0));

        Link link1 = region1.getLinks().get(0);
        System.out.println("link1.getHref() = " + link1.getHref());


    }


    @DisplayName("GET request to  /employees and only get couple of values as a Pojo class")
    @Test
    public void employeeGet(){

        Employee employee1 =
                get("/employees").then().statusCode(200)
                        .extract().jsonPath().getObject("items[0]", Employee.class);

        System.out.println("employee1 = " + employee1);

    }

    /*
    send a GET request to regions
    verify that regions ids are 1,2,3,4
    verify that regions names are Europe, Americas, Asia, Middle East and Africa
    verify that count is 4
    try to use pojo as much as possible
    ignore non-used fields
     */


    @DisplayName("GET request to region only some fields test")
    @Test
    public void regionPojoTest(){
        //send a Get request and save everything inside the regions object
        //since we prepare pojo also for the all properties,
        // we don't need to use any path so as()method is enough
        Regions regions =
                get("/regions").then().statusCode(200)
                        .extract().response().as( Regions.class);

        //from here pure Java

        //verify count is 4
        assertThat(regions.getCount(),is(4));

        //create empty list t store values
        List<String>regionNames = new ArrayList<>();
        List<Integer>regionIds = new ArrayList<>();

        //get list of regions out of regions object
        List<Region>items = regions.getItems();

        //regions.getItems.iter--> shortcut
        //loop through each of the region, save their ids and names to empty list that we prepare
        for (Region region: items) {
            regionIds.add(region.getRegionId());
            regionNames.add(region.getRegName());
        }

        //prepare expected result
        List<Integer> expectedRegionsIds = Arrays.asList(1,2,3,4);
        List<String> expectedRegionNames = Arrays.asList("Europe", "Americas", "Asia", "Middle East and Africa");

        //compare two result
        assertThat(regionIds,is(expectedRegionsIds));
        assertThat(regionNames,is(expectedRegionNames));
    }



}
