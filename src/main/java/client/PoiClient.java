package client;

import static io.restassured.RestAssured.given;

import config.RestConfig;

import io.restassured.response.Response;

public class PoiClient {

    private static final String LATITUDE = "latitude";
    private static final String LONGITUDE = "longitude";
    private static final String DISTANCE = "distance";
    private static final String MAX_RESULTS = "maxresults";
    private static final String POI_ENDPOINT = "/poi/";

    public Response getPOIs(double lat, double lon, double distance, int maxResults) {
        return given()
                .spec(RestConfig.getRequestSpec())
                .queryParam(LATITUDE, lat)
                .queryParam(LONGITUDE, lon)
                .queryParam(DISTANCE, distance)
                .queryParam(MAX_RESULTS, maxResults)
                .get(POI_ENDPOINT);
    }
}
