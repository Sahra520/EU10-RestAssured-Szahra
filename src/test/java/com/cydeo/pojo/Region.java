package com.cydeo.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
@Getter
@Setter
@ToString
public class Region {

    @JsonProperty("region_id")//by using Jackson annotation we can convert json to special variable name
    private int regionId;
    //if your jsonKey and variable name is not matching, you can map it with @JsonProperty("region_id")
    //so we use @JsonProperty annotation that comes from jackson, we provide the jsonKey that we want to map,
    //and we put on top of the field that we create connection
    //not matching each variable we have to write @JsonProperty separately

    @JsonProperty("region_name")
    private String regName;

    @JsonProperty("links")
    private List<Link> links;
//to get ready code: write to chrome: jsontopojo, it will navigate jsonshcema2pojo.org
//deserialize-break chain
}
