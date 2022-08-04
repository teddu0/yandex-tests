package api.yandex.tests;

import static io.restassured.RestAssured.given;

public class TestBase {
    public void createNewFolder() {
        given()
                .param("path", "NewFolder")
                .when().header("Authorization", "AQAAAABjkj-gAADLW9jV3Hy2OkU_sg7ZWt6dJP0")
                .put("https://cloud-api.yandex.net/v1/disk/resources")
                .then()
                .statusCode(201)
                .extract().response().prettyPrint();
    }

    public void getCreatedFolder() {
        given()
                .param("path", "NewFolder")
                .when()
                .header("Authorization", "AQAAAABjkj-gAADLW9jV3Hy2OkU_sg7ZWt6dJP0")
                .get("https://cloud-api.yandex.net/v1/disk/resources")
                .then()
                .extract().response().prettyPrint();
    }

    public void deleteCreatedFolder() {
        given()
                .param("path", "NewFolder")
                .when().header("Authorization", "AQAAAABjkj-gAADLW9jV3Hy2OkU_sg7ZWt6dJP0")
                .delete("https://cloud-api.yandex.net/v1/disk/resources")
                .then()
                .statusCode(204);
    }
}
