package com.opencode.healthplusplus.test.step;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencode.healthplusplus.profile.resource.CreatePatientResource;
import com.opencode.healthplusplus.profile.resource.PatientResource;
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

public class PatientServiceTStepDefinitions {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    private int randomServerPort;

    private String endPointPath;
    private ResponseEntity<String> responseEntity;

    @Given("The Patient Endpoint {string} is available")
    public void thePatientEndpointIsAvailable(String endPointPath) {
        this.endPointPath = String.format(endPointPath, randomServerPort);
    }

    @When("A Patient Post Request is sent with values {int}, {string}, {string}, {int}, {string}")
    public void aPatientPostRequestIsSentWithValues(int dni, String name, String lastName, int age, String address) {
        CreatePatientResource resource = new CreatePatientResource().withAddress(address);
        resource.setDni(dni);
        resource.setName(name);
        resource.setLastName(lastName);
        resource.setAge(age);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<CreatePatientResource> request = new HttpEntity<>(resource, headers);
        responseEntity = testRestTemplate.postForEntity(endPointPath, request, String.class);
    }

    @Then("A Response with Status {int} is received for Patient")
    public void aResponseWithStatusIsReceivedForPatient(int expectedStatusCode) {
        int actualStatusCode = responseEntity.getStatusCodeValue();
        assertThat(expectedStatusCode).isEqualTo(actualStatusCode);
    }

    @And("A Patient Resource with values {int}, {string}, {string}, {int}, {string} is included in Response Body")
    public void aPatientResourceWithValuesIsIncludedInResponseBody(int dni, String name, String lastName, int age, String address) {
        PatientResource expectedResource = new PatientResource().withAddress(address);
        expectedResource.setDni(dni);
        expectedResource.setName(name);
        expectedResource.setLastName(lastName);
        expectedResource.setAge(age);
        String value = responseEntity.getBody();
        ObjectMapper mapper = new ObjectMapper();
        PatientResource actualResource;
        try {
            actualResource = mapper.readValue(value, PatientResource.class);
        } catch (JsonProcessingException | NullPointerException e) {
            actualResource = new PatientResource();
        }
        expectedResource.setId(actualResource.getId());
        assertThat(expectedResource).usingRecursiveComparison()
                .isEqualTo(actualResource);
    }

    @Given("A Patient Resource with values {int}, {string}, {string}, {int}, {string} is already stored")
    public void aPatientResourceWithValuesIsAlreadyStored(int dni, String name, String lastName, int age, String address) {
        CreatePatientResource resource = new CreatePatientResource().withAddress(address);
        resource.setDni(dni);
        resource.setName(name);
        resource.setLastName(lastName);
        resource.setAge(age);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<CreatePatientResource> request = new HttpEntity<>(resource, headers);
        responseEntity = testRestTemplate.postForEntity(endPointPath, request, String.class);
    }

    @And("A Message with values {string} is include in Response Body for Patient")
    public void aMessageWithValuesIsIncludeInResponseBodyForPatient(String expectedMessage) {
        String responseBody = responseEntity.getBody();
        assertThat(responseBody).contains(expectedMessage);
    }
}
