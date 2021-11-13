package com.opencode.healthplusplus.test.step;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencode.healthplusplus.meeting.resource.ClinicLocationResource;
import com.opencode.healthplusplus.meeting.resource.CreateClinicLocationResource;
import com.opencode.healthplusplus.profile.resource.CreateSpecialtyResource;
import com.opencode.healthplusplus.profile.resource.SpecialtyResource;
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
    public void aClinicLocationPostRequestIsSentWithValues(String address, String capitalCity, String country) {
        CreateClinicLocationResource resource = new CreateClinicLocationResource()
                .withAddress(address)
                .withCountry(country)
                .withCapitalCity(capitalCity);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<CreateClinicLocationResource> request = new HttpEntity<>(resource, headers);
        responseEntity = testRestTemplate.postForEntity(endPointPath, request, String.class);
    }

    @Then("A Response with Status {int} is received for Location")
    public void aResponseWithStatusIsReceivedForClinicLocation(int expectedStatusCode) {
        int actualStatusCode = responseEntity.getStatusCodeValue();
        assertThat(expectedStatusCode).isEqualTo(actualStatusCode);
    }

    @And("A Location Resource with values {string}, {string}, {string} is included in Response Body")
    public void aClinicLocationResourceWithValuesIsIncludedInResponseBody(String address, String capitalCity, String country) {
        ClinicLocationResource expectedResource = new ClinicLocationResource()
                .withAddress(address)
                .withCountry(country)
                .withCapitalCity(capitalCity);
        String value = responseEntity.getBody();
        ObjectMapper mapper = new ObjectMapper();
        ClinicLocationResource actualResource;
        try {
            actualResource = mapper.readValue(value, ClinicLocationResource.class);
        } catch (JsonProcessingException | NullPointerException e) {
            actualResource = new ClinicLocationResource();
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
