package com.cydeo.day5;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
public class HamcrestMatchersIntro {

    @Test
    public void simpleTest1() {

        //actual 5+5
        assertThat(5 + 5, is(10));
        assertThat(5 + 5, equalTo(10));
        //Matchers has 2 overloaded version
        //first that accept actual value
        //second that accept another matchers
        //below examples is method is accepting another matchers equal to make it readable

        assertThat(5 + 5, is(equalTo(10)));

        //for negative matchers
        assertThat(5 + 5, not(9));
        assertThat(5 + 5, is(not(7)));
        assertThat(5 + 5, is(not(equalTo(9))));
        //all of them are returning true and same thing

        //number comparison
        //greaterThanOrEqualTo();
        //greaterThan();
        //lessThan();
        //lessThanOrEqualTo();
        assertThat(5 + 5, is(greaterThan(9)));

    }
        @DisplayName("Assertion with String")
        @Test
        public void stringHamcrest(){
            String text = "EU10 is learning Hamcrest";

            //checking for equality is same as numbers
            assertThat(text,is("EU10 is learning Hamcrest"));
            assertThat(text,is(equalTo("EU10 is learning Hamcrest")));
            assertThat(text,equalTo("EU10 is learning Hamcrest"));


            //check if this text starts with EU10
            assertThat(text,startsWith("EU10"));
            //now do it in case-insensitive manner
            assertThat(text, startsWithIgnoringCase("eu10"));
            //endsWith();
            assertThat(text,endsWith("rest"));

            //check if text contains String "learning"
            assertThat(text,containsString("learning"));

            //with ignoring case
            assertThat(text,containsStringIgnoringCase("LEARNING"));

            String str = "  ";
            //check if above str is blank
            assertThat(str,blankString());

            //check if trimmed str is empty string
            assertThat(str.trim(),emptyString());
    }

    @DisplayName("Hamcrest for Collection")
    @Test
    public void testCollection(){
        List<Integer> listOfNumbers = Arrays.asList(2,25,1,12,34,1,3,314,34,13);
        //check size of th list
        assertThat(listOfNumbers,hasSize(10));

        //check if this list hasItem 77
        assertThat(listOfNumbers, hasItem(25));

        //check if this list hasItems()
        assertThat(listOfNumbers,hasItems(34,314,25));

        //check if all numbers greater than 0
        assertThat(listOfNumbers,everyItem(greaterThan(0)));

    }
}
