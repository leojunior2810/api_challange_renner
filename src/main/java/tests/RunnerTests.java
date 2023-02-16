package tests;

import static io.restassured.RestAssured.given;
import base.BaseTest;
import static org.hamcrest.Matchers.*;
import model.User;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RunnerTests extends BaseTest {

    private static String USER_ID;
    @Test
    @Order(1)
    public void createUser() {
        User user = new User();
        user.setName("João");
        user.setJob("Analyst");

        USER_ID = given().
                body(user).
        when().
                post("/users").
        then().
                statusCode(201).
                body("name", is("João")).
                body("job", is("Analyst")).
                extract().path("id");

    }
    @Test
    @Order(2)
    public void updateUser() {
        User user = new User();
        user.setName("João Alterado");
        user.setJob("Analyst Alterado");
        given().
                body(user).
                pathParam("id", USER_ID).
        when().
                patch("/users/{id}").
        then().
                statusCode(200).
                body("name", is("João Alterado")).
                body("job", is("Analyst Alterado"));
    }
    @Test
    @Order(3)
    public void getAllUsers() {
        given().
        when().
                get("/users").
        then().
                statusCode(200);
    }
    @Test
    @Order(3)
    public void getSingleUser() {
        given().
                pathParam("singleId", 2).
        when().
                get("/users/{singleId}").
        then().
                statusCode(200).
                body("data.email", containsString("@reqres.in"));
    }
}



