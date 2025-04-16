package tests;

import static org.hamcrest.Matchers.lessThan;

import org.slf4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

public abstract class AbstractTest {

    public Logger log() {
        return org.slf4j.LoggerFactory.getLogger(this.getClass());
    }

    @BeforeMethod
    public void logStart(Method method) {
        String description = method.getAnnotation(org.testng.annotations.Test.class).description();
        log().info(">>>>>>> Starting test: {} <<<<<<<", description);
    }

    @AfterMethod
    public void logEnd(Method method) {
        String description = method.getAnnotation(org.testng.annotations.Test.class).description();
        log().info("<<<<<<< Finished test: {} >>>>>>>", description);
    }

    protected void verifyStatusCode(Response response, Integer expectedStatusCode) {
        response.then().statusCode(expectedStatusCode);
        log().info("Status code is {} as expected.", expectedStatusCode);
    }

    protected void verifyResponseTime(Response response, long expectedResponseTime) {
        response.then().time(lessThan(expectedResponseTime));
        log().info("Request time is not more than {} milliseconds.", expectedResponseTime);
    }

    protected void verifyResponseSchema(Response response, String schemaPath) {
        response.then().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(schemaPath));
        log().info("Schema is verified.");
    }
}
