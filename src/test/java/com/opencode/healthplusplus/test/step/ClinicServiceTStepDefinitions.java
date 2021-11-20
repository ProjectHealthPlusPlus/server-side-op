package com.opencode.healthplusplus.test.step;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencode.healthplusplus.meeting.resource.ClinicResource;
import com.opencode.healthplusplus.meeting.resource.CreateClinicResource;
import com.opencode.healthplusplus.meeting.resource.LocationResource;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ClinicServiceTStepDefinitions {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    private int randomServerPort;

    private String endPointPath;
    private ResponseEntity<String> responseEntity;

    private LocationResource locationResource = new LocationResource();

    @Given("The Clinic Endpoint {string} is available")
    public void theClinicEndpointIsAvailable(String endPointPath) {
        this.endPointPath = String.format(endPointPath, randomServerPort);
    }

    @And("Clinic is already stored")
    public void clinicIsAlreadyStored(DataTable table) {
        List<Map<String, String>> rows = table.asMaps(String.class, String.class);

//        locationResource = new LocationResource();

        for (Map<String, String> columns : rows) {
            locationResource.setId(Long.parseLong(columns.get("Id")));
            locationResource.setAddress(columns.get("PublishDate"));
            locationResource.setCity(columns.get("City"));
            locationResource.setCountry(columns.get("Country"));
        }
    }

    @When("A Clinic Post Request is sent with values {int}")
    public void aClinicPostRequestIsSentWithValues(int locationId) {
        CreateClinicResource resource = new CreateClinicResource();
        resource.setLocationId((long) locationId);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<CreateClinicResource> request = new HttpEntity<>(resource, headers);
        responseEntity = testRestTemplate.postForEntity(endPointPath, request, String.class);

    }

    @Then("A Response with Status {int} is received for Clinic")
    public void aResponseWithStatusIsReceivedForClinic(int expectedStatusCode) {
        int actualStatusCode = responseEntity.getStatusCodeValue();
        assertThat(expectedStatusCode).isEqualTo(actualStatusCode);
    }

    @And("A Clinic Resource with values {int} is included in Response Body")
    public void aClinicResourceWithValuesIsIncludedInResponseBody(int locationId) {
        ClinicResource expectedResource = new ClinicResource();
        expectedResource.setLocation(locationResource);

        String value = responseEntity.getBody();
        ObjectMapper mapper = new ObjectMapper();
        ClinicResource actualResource;
        try {
            actualResource = mapper.readValue(value, ClinicResource.class);
        } catch (JsonProcessingException | NullPointerException e) {
            actualResource = new ClinicResource();
        }
        expectedResource.setId(actualResource.getId());
        assertThat(expectedResource).usingRecursiveComparison()
                .isEqualTo(actualResource);
    }

    @Given("A Clinic Resource with values {int} is already stored")
    public void aClinicResourceWithValuesIsAlreadyStored(int locationId) {
        CreateClinicResource resource = new CreateClinicResource();
        resource.setLocationId((long) locationId);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<CreateClinicResource> request = new HttpEntity<>(resource, headers);
        responseEntity = testRestTemplate.postForEntity(endPointPath, request, String.class);

    }

    @And("A Message with values {string} is include in Response Body for Clinic")
    public void aMessageWithValuesIsIncludeInResponseBodyForClinic(String expectedMessage) {
        String responseBody = responseEntity.getBody();
        assertThat(responseBody).contains(expectedMessage);
    }
}
