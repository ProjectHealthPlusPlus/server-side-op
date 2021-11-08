package com.opencode.healthplusplus.test.step;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

public class SpecialtyServiceTStepDefinitions {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    private int randomServerPort;

    private String endPointPath;
    private ResponseEntity<String> responseEntity;

    @Given("The Specialty Endpoint {string} is available")
    public void theSpecialtyEndpointIsAvailable(String endPointPath) {
        this.endPointPath = String.format(endPointPath, randomServerPort);
    }

    @When("A Specialty Post Request is sent with values {string}, {string}")
    public void aSpecialtyPostRequestIsSentWithValues(String name, String description) {
        CreateSpecialtyResource resource = new CreateSpecialtyResource()
                .withName(name)
                .withDescription(description);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<CreateSpecialtyResource> request = new HttpEntity<>(resource, headers);
        responseEntity = testRestTemplate.postForEntity(endPointPath, request, String.class);
    }

    @Then("A Response with Status {int} is received for Specialty")
    public void aResponseWithStatusIsReceivedForSpecialty(int expectedStatusCode) {
        int actualStatusCode = responseEntity.getStatusCodeValue();
        assertThat(expectedStatusCode).isEqualTo(actualStatusCode);
    }

    @And("A Specialty Resource with values {string}, {string} is included in Response Body")
    public void aSpecialtyResourceWithValuesIsIncludedInResponseBody(String name, String description) {
        SpecialtyResource expectedResource = new SpecialtyResource()
                .withName(name)
                .withDescription(description);
        String value = responseEntity.getBody();
        ObjectMapper mapper = new ObjectMapper();
        SpecialtyResource actualResource;
        try {
            actualResource = mapper.readValue(value, SpecialtyResource.class);
        } catch (JsonProcessingException | NullPointerException e) {
            actualResource = new SpecialtyResource();
        }
        expectedResource.setId(actualResource.getId());
        assertThat(expectedResource).usingRecursiveComparison()
                .isEqualTo(actualResource);
    }

    @Given("A Specialty Resource with values {string}, {string} is already stored")
    public void aSpecialtyResourceWithValuesIsAlreadyStored(String name, String description) {
        CreateSpecialtyResource resource = new CreateSpecialtyResource()
                .withName(name)
                .withDescription(description);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<CreateSpecialtyResource> request = new HttpEntity<>(resource, headers);
        responseEntity = testRestTemplate.postForEntity(endPointPath, request, String.class);
    }

    @And("A Message with values {string} is include in Response Body for Specialty")
    public void aMessageWithValuesIsIncludeInResponseBodyForSpecialty(String expectedMessage) {
        String responseBody = responseEntity.getBody();
        assertThat(responseBody).contains(expectedMessage);
    }
}
