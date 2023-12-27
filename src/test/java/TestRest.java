import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.*;

public class TestRest extends TestBase {
  @Test
  void checkUserListUserTest() {
    given()
        .when()
        .get("/users?page=2")
        .then()
        .statusCode(200)
        .body("page", equalTo(2),
            "per_page", equalTo(6),
            "total", equalTo(12),
            "total_pages", equalTo(2)
        );
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
        .body("data.id", contains(7, 8, 9, 10, 11, 12),
            "data.email", contains("michael.lawson@reqres.in", "lindsay.ferguson@reqres.in", "tobias.funke@reqres.in", "byron.fields@reqres.in", "george.edwards@reqres.in", "rachel.howell@reqres.in"),
            "data.first_name", contains("Michael", "Lindsay", "Tobias", "Byron", "George", "Rachel"),
            "data.last_name", contains("Lawson", "Ferguson", "Funke", "Fields", "Edwards", "Howell"),
            "data.avatar", contains("https://reqres.in/img/faces/7-image.jpg", "https://reqres.in/img/faces/8-image.jpg", "https://reqres.in/img/faces/9-image.jpg", "https://reqres.in/img/faces/10-image.jpg", "https://reqres.in/img/faces/11-image.jpg", "https://reqres.in/img/faces/12-image.jpg")
        );
  }

  @Test
  void updateTest() {
    given()
        .body("{\"name\": \"morpheus\", \"job\": \"leader\"}")
        .contentType(JSON)
        .when()
        .put("/users/2")
        .then()
        .statusCode(200);

  }

  @Test
  void createTest() {
    given()
        .body("{\"name\": \"morpheus\",\"job\": \"leader\"}")
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

}