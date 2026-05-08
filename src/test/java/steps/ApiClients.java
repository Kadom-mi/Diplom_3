package steps;

import io.qameta.allure.Step;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.User;
import static io.restassured.RestAssured.given;

public final class ApiClients {

    private static final String BASE_URI = "https://stellarburgers.education-services.ru";
    private static final String AUTH_REGISTER = "/api/auth/register";
    private static final String AUTH_LOGIN = "/api/auth/login";
    private static final String AUTH_USER = "/api/auth/user";

    private static final RequestSpecification REQUEST_SPEC = new RequestSpecBuilder()
            .setBaseUri(BASE_URI)
            .setContentType(ContentType.JSON)
            .build();

    private ApiClients() {
        throw new UnsupportedOperationException("ApiClients is a static utility class");
    }

    @Step("Создание нового пользователя")
    public static Response createUser(User user) {
        return given()
                .spec(REQUEST_SPEC)
                .body(user)
                .post(AUTH_REGISTER);
    }

    @Step("Авторизация пользователя")
    public static Response loginUser(User user) {
        return given()
                .spec(REQUEST_SPEC)
                .body(user)
                .post(AUTH_LOGIN);
    }

    @Step("Удаление пользователя")
    public static Response deleteUser(String accessToken) {
        return given()
                .spec(REQUEST_SPEC)
                .auth().oauth2(accessToken.replace("Bearer ", ""))
                .delete(AUTH_USER);
    }

    public static String getAccessToken(Response loginResponse) {
        return loginResponse.jsonPath().getString("accessToken");
    }
}