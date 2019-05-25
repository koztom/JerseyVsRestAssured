package com.hascode.mto.rest;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.jayway.restassured.RestAssured.expect;
import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

public class UserServiceTestUsingRestAssured extends FunctionalTest {

    @Test
    public void tesFetchAll() {
        List<User> list = given()
                .contentType(JSON)
                .when()
                .get("/users")
                .then().extract().body().as(new ArrayList<User>().getClass());

        assertThat(list, hasSize(3));
    }

    @Test
    public void testGetById() {
        expect().body("id", equalTo(100)).body("name", equalTo("me"))
                .body("email", equalTo("me@gmail.com"))
                .when().get("/users/user/100").then().extract();
    }

    @Test
    public void testCreate() {
        User user = new User(105, "Ramesh", "myemail@gmail.com");
        given().
                contentType("application/json").
                body(user).
                when().
                post("/users").then().
                statusCode(201);
    }

    @Test
    public void testUpdate() {
        User user = new User(105, "Ramesh", "myemail@gmail.com");
        given().
                contentType("application/json").
                body(user).
                when().
                put("/users/user/101").then().
                statusCode(204);
    }

    @Test
    public void testDelete() {
        given().
                when().
                delete("/users/user/100").then().
                statusCode(204);
    }
}
