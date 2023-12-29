package tests;

import org.junit.jupiter.api.Test;

import models.UserDataModel;
import models.UserListModel;
import models.UserRegModel;
import models.UserRespModel;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static specs.UserDataSpec.userReqSpec;
import static specs.UserDataSpec.userRespSpec;
import static specs.UserRegistrationSpec.regReqSpec;
import static specs.UserRegistrationSpec.regRespSpec;


public class TestRest extends TestBase {
  @Test
  void checkUserListUserTest() {
    UserListModel listModel = new UserListModel();

    UserListModel response = step("List of users", () ->
        given(userReqSpec)
            .when()
            .get("/users?page=2")
            .then()
            .statusCode(200)
            .extract().as(UserListModel.class));

    step("Verify result", () ->
        assertAll(
            () -> assertEquals(2, response.getPage()),
            () -> assertEquals(2, response.getTotal_pages()),
            () -> assertEquals(12, response.getTotal()),
            () -> assertEquals(6, response.getPer_page())
        ));

  }

  @Test
  void checkUserDataTest() {
    given()
        .when()
        .get("/users?page=2")
        .then()
        .statusCode(200)
        .body("data.id", contains(7, 8, 9, 10, 11, 12),
            "data.email", contains("michael.lawson@reqres.in", "lindsay.ferguson@reqres.in", "tobias.funke@reqres.in", "byron.fields@reqres.in", "george.edwards@reqres.in", "rachel.howell@reqres.in"),
            "data.first_name", contains("Michael", "Lindsay", "Tobias", "Byron", "George", "Rachel"),
            "data.last_name", contains("Lawson", "Ferguson", "Funke", "Fields", "Edwards", "Howell"),
            "data.avatar", contains("https://reqres.in/img/faces/7-image.jpg", "https://reqres.in/img/faces/8-image.jpg", "https://reqres.in/img/faces/9-image.jpg", "https://reqres.in/img/faces/10-image.jpg", "https://reqres.in/img/faces/11-image.jpg", "https://reqres.in/img/faces/12-image.jpg")
        );
  }

  @Test
  void delayTest() {
    given()
        .when()
        .get("/users?delay=3")
        .then()
        .statusCode(200)
        .body("data.id", contains(1, 2, 3, 4, 5, 6),
            "data.email", contains("george.bluth@reqres.in", "janet.weaver@reqres.in", "emma.wong@reqres.in", "eve.holt@reqres.in", "charles.morris@reqres.in", "tracey.ramos@reqres.in"),
            "data.first_name", contains("George", "Janet", "Emma", "Eve", "Charles", "Tracey"),
            "data.last_name", contains("Bluth", "Weaver", "Wong", "Holt", "Morris", "Ramos"),
            "data.avatar", contains("https://reqres.in/img/faces/1-image.jpg", "https://reqres.in/img/faces/2-image.jpg", "https://reqres.in/img/faces/3-image.jpg", "https://reqres.in/img/faces/4-image.jpg", "https://reqres.in/img/faces/5-image.jpg", "https://reqres.in/img/faces/6-image.jpg")
        );
  }

  @Test
  void updateTest() {
    UserDataModel dataModel = new UserDataModel();
    dataModel.setName("morpheus");
    dataModel.setJob("zion resident");

    given()
        .body(dataModel)
        .contentType(JSON)
        .when()
        .put("/users/2")
        .then()
        .statusCode(200);

  }

  @Test
  void UpdateTest2() {
    UserDataModel dataModel = new UserDataModel();
    dataModel.setName("morpheus");
    dataModel.setJob("zion resident");

    UserRespModel response = step("Update user", () ->
        given(userReqSpec)
            .body(dataModel)
            .when()
            .put("/users/2")
            .then()
            .spec(userRespSpec)
            .extract().as(UserRespModel.class));

    step("Verify result", () ->
        assertAll(
            () -> assertEquals("morpheus", response.getName()),
            () -> assertEquals("zion resident", response.getJob())
        ));
  }

  @Test
  void createTest() {
    UserDataModel dataModel = new UserDataModel();
    dataModel.setName("morpheus");
    dataModel.setJob("leader");

    given()
        .body(dataModel)
        .contentType(JSON)
        .when()
        .post("/users")
        .then()
        .statusCode(201);
  }

  @Test
  void deleteTest() {
    given()
        .when()
        .delete("/users/2")
        .then()
        .statusCode(204);
  }

  @Test
  void registerUserTest() {
    UserRegModel userBody = new UserRegModel();
    userBody.setEmail("eve.holt@reqres.in");
    userBody.setPassword("pistol");

    UserRegModel response = step("Register user", () ->
        given(regReqSpec)
            .body(userBody)
            .when()
            .post("/register")
            .then()
            .spec(regRespSpec)
            .extract().as(UserRegModel.class));

    step("Verify result", () ->
        assertAll(
            () -> assertEquals("4", response.getId()),
            () -> assertEquals("QpwL5tke4Pnpja7X4", response.getToken())
        ));
  }

}