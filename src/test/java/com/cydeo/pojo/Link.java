package com.cydeo.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter//needs lombok dependency
@ToString
public class Link {

    private String rel;
    private String href;
    //variables must be match with HR ORDS data's contents

    //to get ready code: write to chrome: jsontopojo, it will navigate jsonshcema2pojo.org


}
