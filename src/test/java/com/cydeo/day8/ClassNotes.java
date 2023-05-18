package com.cydeo.day8;

public class ClassNotes {

    //AUTHENTICATION = verifies if you are who you say you are

    //AUTHORIZATION = verifies if you have access/permission to certain actions(API)

    //http://54.226.109.52:7000/swagger-ui.html#/Spartan_Controller  --> click API DOC
    //to sign in write :admin(username)  and admin(password)

    //admin admin
    //user user
    //editor editor

    //401 --> invalid credentials, api doesn't know who you are
    //403 --> valid credentials, but not enough access level/permission to do action

    // or  VISITOR/guest

    //in the POSTMAN we have to write  spartan IP:7000/api/spartans
    //then click authorization, then choose Basic Auth,
    //write username: user and password: user
    //then we got status code 200


    //https://cybertek-reservation-api-docs.herokuapp.com/#introduction

    /*
    Error Code	Name	Meaning
400	Bad Request	your request is invalid.
401	Unauthorized	your authorization token is wrong.
403	Forbidden	the resource requested is hidden for you.
404	Not Found	the specified resource could not be found.
409	Conflict	indicates that the request could not be processed because of conflict in the current state of the resource.
422	Unprocessable Entity	your request structure is right, but something wrong with info you're passing to the service.
429	Too Many Requests	you're requesting too many resources, slow down.
500	Internal Server Error	we had a problem with our server, try again later.
     */

    /*
    AUTHENTICATION and AUTHORIZATION

AUTHENTICATION --> verifies if you are who you say you are.

AUTHORIZATION --> verifies if you have access/permission to certain actions(API)

Spartan we have 3 different user types:
username - password
user - user
editor - editor
admin - admin

guest
401 --> invalid credentials, api doesn't know who you are ?
403 --> valid credentials, but not enough access level/permission to do action

spartan running at port 7000 uses basic auth we can send our username and password by click in authorization tab on the Postman
 and select basic auth

for spartan application we have 3 different users,
and only admin can delete spartan based on the documentation.
if you don't provide and username and password you will get 401 because api doesn't know who you are.
 but let's say you use user - user as a credentials and try to delete you will get 403 because api knows you,
 but you don't have right to delete your friends.

BOOKIT

https://cybertek-reservation-api-qa.herokuapp.com/sign?email=sbirdbj@fc2.com&password=asenorval

token
eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMzkiLCJhdWQiOiJzdHVkZW50LXRlYW0tbGVhZGVyIn0._vM1-eRoS7SsHu6T-QPdJoEdA8LSwnxUvvTTbhV-8ms

For BOOKIT api, it requires us to pass accessToken with each request that we send.
in Postman, we can add as bearer token and use it

postman is creating the request headerheader and adding the token as following;

Authorization: Bearer your-token

you can see your token by clicking hidden headers.


RBAC -- Role based access control

HOW TO SEND Basic Auth(Spartan) or Bearer(Bookit) token with RestAssured Library ?

---------------------------------------------

200-get, 201 post,204 put,patch delete

-------------------------------------------

CRUD operations
Create     :       Post
Read       :     Get
Update     :    Put/Patch  //put update one spartan, patch update partial spartan
Delete     :     Delete
     */
}
//https://cybertek-reservation-api-docs.herokuapp.com/#introduction


