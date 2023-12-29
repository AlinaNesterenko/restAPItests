package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static helpers.CustomAllureListeners.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.http.ContentType.JSON;

public class UserRegistrationSpec {

  public static RequestSpecification regReqSpec = with()
      .filter(withCustomTemplates())
      .log().uri()
      .log().method()
      .contentType(JSON);

  public static ResponseSpecification regRespSpec = new ResponseSpecBuilder()
      .log(LogDetail.STATUS)
      .log(LogDetail.BODY)
      .expectStatusCode(200)
      .build();
}