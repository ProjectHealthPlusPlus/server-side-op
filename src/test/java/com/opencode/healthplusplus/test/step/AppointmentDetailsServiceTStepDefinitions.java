package com.opencode.healthplusplus.test.step;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencode.healthplusplus.health.domain.entity.Diagnostic;
import com.opencode.healthplusplus.meeting.resource.AppointmentDetailsResource;
import com.opencode.healthplusplus.meeting.resource.CreateAppointmentDetailsResource;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class AppointmentDetailsServiceTStepDefinitions {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    private int randomServerPort;

    private String endPointPath;
    private ResponseEntity<String> responseEntity;

    private Diagnostic diagnostic;

    @Given("The AppointmentDetails Endpoint {string} is available")
    public void theAppointmentDetailsEndpointIsAvailable(String endPointPath) {
        this.endPointPath = String.format(endPointPath, randomServerPort);
    }

    @And("Diagnostic is already stored")
    public void diagnosticIsAlreadyStored(DataTable table) throws ParseException {
        List<Map<String, String>> rows = table.asMaps(String.class, String.class);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

        diagnostic = new Diagnostic();

        for (Map<String, String> columns : rows) {
            diagnostic.setId(Long.parseLong(columns.get("Id")));
            diagnostic.setPublishDate(format.parse(columns.get("PublishDate")));
            diagnostic.setDescription(columns.get("Description"));
        }
    }

    @When("A AppointmentDetails Post Request is sent with values {string}, {string}, {string}, {string}, {int}")
    public void aAppointmentDetailsPostRequestIsSentWithValues(String patientStartedAt, String doctorStartedAt, String patientEndedAt, String doctorEndedAt, int diagnosticId) throws ParseException {
        CreateAppointmentDetailsResource resource = new CreateAppointmentDetailsResource();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

        resource.setPatientStartedAt(format.parse(patientStartedAt));
        resource.setDoctorStartedAt(format.parse(doctorStartedAt));
        resource.setPatientEndedAt(format.parse(patientEndedAt));
        resource.setDoctorEndedAt(format.parse(doctorEndedAt));
        resource.setDiagnosticId((long) diagnosticId);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<CreateAppointmentDetailsResource> request = new HttpEntity<>(resource, headers);
        responseEntity = testRestTemplate.postForEntity(endPointPath, request, String.class);

    }

    @Then("A Response with Status {int} is received for AppointmentDetails")
    public void aResponseWithStatusIsReceivedForAppointmentDetails(int expectedStatusCode) {
        int actualStatusCode = responseEntity.getStatusCodeValue();
        assertThat(expectedStatusCode).isEqualTo(actualStatusCode);
    }

    @And("A AppointmentDetails Resource with values {string}, {string}, {string}, {string}, {int} is included in Response Body")
    public void aAppointmentDetailsResourceWithValuesIsIncludedInResponseBody(String patientStartedAt, String doctorStartedAt, String patientEndedAt, String doctorEndedAt, int diagnosticId) throws ParseException {
        AppointmentDetailsResource expectedResource = new AppointmentDetailsResource();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

        expectedResource.setPatientStartedAt(format.parse(patientStartedAt));
        expectedResource.setDoctorStartedAt(format.parse(doctorStartedAt));
        expectedResource.setPatientEndedAt(format.parse(patientEndedAt));
        expectedResource.setDoctorEndedAt(format.parse(doctorEndedAt));

        String value = responseEntity.getBody();
        ObjectMapper mapper = new ObjectMapper();
        AppointmentDetailsResource actualResource;
        try {
            actualResource = mapper.readValue(value, AppointmentDetailsResource.class);
        } catch (JsonProcessingException | NullPointerException e) {
            actualResource = new AppointmentDetailsResource();
        }
        expectedResource.setId(actualResource.getId());
        assertThat(expectedResource).usingRecursiveComparison()
                .isEqualTo(actualResource);
    }

    @And("A Message with values {string} is include in Response Body for AppointmentDetails")
    public void aMessageWithValuesIsIncludeInResponseBodyForAppointmentDetails(String expectedMessage) {
        String responseBody = responseEntity.getBody();
        assertThat(responseBody).contains(expectedMessage);
    }

}
