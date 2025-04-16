package tests;

import static config.RestConfig.MAX_RESPONSE_TIME;
import static constant.Contants.POI_SCHEMA_PATH;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.lessThanOrEqualTo;

import client.PoiClient;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class GetPoiTests extends AbstractTest {

    private final PoiClient client = new PoiClient();

    @Test(description = "Verify POI Response: schema, distance and number of results")
    public void verifyPOIResponse() {
        double maxValidDistance = 10;
        double latitude = 51.5074;
        double longitude = 0.1278;
        int maxResults = 5;

        Response response = client.getPOIs(latitude, longitude, maxValidDistance, maxResults);

        verifyStatusCode(response, SC_OK);
        verifyResponseTime(response, MAX_RESPONSE_TIME);
        verifyResponseSchema(response, POI_SCHEMA_PATH);

        var actualResultsSize = response.jsonPath().getList("$").size();
        assertThat(String.format("There should be %s results", maxResults), actualResultsSize == maxResults);
        log().info("Number of results is verified. Actual: {} Expected: {}", actualResultsSize, maxResults);

        response.jsonPath().getList("AddressInfo.Distance", Double.class).forEach(distance ->
                assertThat(String.format("Actual distance %s is great than or expected %s", distance, maxValidDistance),
                        distance, lessThanOrEqualTo(maxValidDistance)));
        log().info("Distance is verified for each result.Actual distanse is not more than {}", maxValidDistance);
    }
}
