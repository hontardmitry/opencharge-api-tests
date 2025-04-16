package tests;

import static config.RestConfig.MAX_RESPONSE_TIME;
import static constant.Contants.REFERENCE_DATA_SCHEMA_PATH;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

import client.ReferenceDataClient;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import io.qameta.allure.Feature;
import io.restassured.response.Response;

@Feature("Get Reference Data")
public class GetReferenceDataTests extends AbstractTest {

    private static final String FAST_CHARGER_TITLE = "Level 3:  High (Over 40kW)";
    private static final String SLOW_CHARGER_TITLE = "Level 1 : Low (Under 2kW)";
    private final ReferenceDataClient client = new ReferenceDataClient();

    @Test(description = "Verify Reference Data Response")
    public void verifyReferenceDataResponse() {
        Response response = client.getReferenceData();

        verifyStatusCode(response, SC_OK);
        verifyResponseTime(response, MAX_RESPONSE_TIME);
        verifyResponseSchema(response, REFERENCE_DATA_SCHEMA_PATH);


        List<String> chargerTitles = response.jsonPath().getList("ChargerTypes.Title");
        assertThat(chargerTitles, hasItems(FAST_CHARGER_TITLE, SLOW_CHARGER_TITLE));
        log().info("Charger titles are verified.");

        //Check that there are no duplicate IDs
        List<Integer> statusIds = response.jsonPath().getList("StatusTypes.ID");
        Set<Integer> uniqueIds = new HashSet<>(statusIds);
        assertThat(uniqueIds.size(), equalTo(statusIds.size()));
        log().info("Status IDs are verified to be unique.");
    }
}
