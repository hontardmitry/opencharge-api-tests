package config;

import static util.PropertyUtil.getProperty;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class RestConfig {
    private RestConfig() {
    }

    public static final long MAX_RESPONSE_TIME = Long.parseLong(getProperty("max.response.time.ms"));
    private static final String BASE_URL = getProperty("base.url");
    private static final String API_KEY = getProperty("api.key");

    public static RequestSpecification getRequestSpec() {
        return new RequestSpecBuilder()
                .setContentType("application/json")
                .addFilter(new LoggingFilter())
                .setBaseUri(BASE_URL)
                .addQueryParam("key", API_KEY)
                .setRelaxedHTTPSValidation()
                .addHeader("User-Agent", "PostmanRuntime/7.32.3")
                .build();
    }
}
