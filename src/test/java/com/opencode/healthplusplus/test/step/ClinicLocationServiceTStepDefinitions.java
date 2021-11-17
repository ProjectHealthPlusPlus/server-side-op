package com.opencode.healthplusplus.test.step;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencode.healthplusplus.meeting.resource.LocationResource;
import com.opencode.healthplusplus.meeting.resource.CreateLocationResource;
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

import static org.assertj.core.api.Assertions.assertThat;

public class ClinicLocationServiceTStepDefinitions {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    private int randomServerPort;

    private String endPointPath;
    private ResponseEntity<String> responseEntity;

    @Given("The Location Endpoint {string} is available")
    public void theClinicLocationEndpointIsAvailable(String endPointPath) {
        this.endPointPath = String.format(endPointPath, randomServerPort);
    }

    @When("A Location Post Request is sent with values {string}, {string}, {string}")
    public void aClinicLocationPostRequestIsSentWithValues(String address, String city, String country) {
        CreateLocationResource resource = new CreateLocationResource()
                .withAddress(address)
                .withCountry(country)
                .withCity(city);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<CreateLocationResource> request = new HttpEntity<>(resource, headers);
        responseEntity = testRestTemplate.postForEntity(endPointPath, request, String.class);
    }

    @Then("A Response with Status {int} is received for Location")
    public void aResponseWithStatusIsReceivedForClinicLocation(int expectedStatusCode) {
        int actualStatusCode = responseEntity.getStatusCodeValue();
        assertThat(expectedStatusCode).isEqualTo(actualStatusCode);
    }

    @And("A Location Resource with values {string}, {string}, {string} is included in Response Body")
    public void aClinicLocationResourceWithValuesIsIncludedInResponseBody(String address, String city, String country) {
        LocationResource expectedResource = new LocationResource()
                .withAddress(address)
                .withCountry(country)
                .withCity(city);
        String value = responseEntity.getBody();
        ObjectMapper mapper = new ObjectMapper();
        LocationResource actualResource;
        try {
            actualResource = mapper.readValue(value, LocationResource.class);
        } catch (JsonProcessingException | NullPointerException e) {
            actualResource = new LocationResource();
        }
        expectedResource.setId(actualResource.getId());
        assertThat(expectedResource).usingRecursiveComparison()
                .isEqualTo(actualResource);
    }

    @And("A Message with values {string} is include in Response Body for Location")
    public void aMessageWithValuesIsIncludeInResponseBodyForClinicLocation(String expectedMessage) {
        String responseBody = responseEntity.getBody();
        assertThat(responseBody).contains(expectedMessage);
    }

}
