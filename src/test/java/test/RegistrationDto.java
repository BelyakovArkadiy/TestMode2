package test;


import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.util.Locale;


class RegistrationDto {


    public static RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(9999)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    public static void postUser(User registration) {
        RestAssured.given()
                .spec(requestSpecification)
                .body(registration)
                .when()
                .post("/api/system/users")
                .then()
                .statusCode(200);
    }


    public static User setActiveUser() {
        Faker faker = new Faker(new Locale("eng"));
        String login = faker.name().fullName();
        String password = faker.internet().password();
        String status = "active";
        User registration = new User(login, password, status);
        postUser(registration);
        return registration;
    }

    public static User setBlockedUser() {
        String login = "Artur Platonov";
        String password = "Password123";
        String status = "blocked";
        User registration = new User(login, password, status);
        postUser(registration);
        return registration;
    }

    public static User setIncorrectPassword() {
        Faker faker = new Faker(new Locale("eng"));
        String login = "Artur Platonov";
        String password = faker.internet().password();
        String status = "active";
        User registration = new User(login, password, status);
        postUser(registration);
        return registration;
    }

    public static User setIncorrectLogin() {
        Faker faker = new Faker(new Locale("eng"));
        String login = faker.name().fullName();
        String password = "Password123";
        String status = "active";
        User registration = new User(login, password, status);
        postUser(registration);
        return registration;
    }



}



