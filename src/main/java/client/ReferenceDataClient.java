package client;

import static io.restassured.RestAssured.given;

import config.RestConfig;

import io.restassured.response.Response;

public class ReferenceDataClient {

    private static final String REFERENCE_DATA_ENDPOINT = "/referencedata/";

    public Response getReferenceData() {
        return given()
                .spec(RestConfig.getRequestSpec())
                .get(REFERENCE_DATA_ENDPOINT);
    }
}
