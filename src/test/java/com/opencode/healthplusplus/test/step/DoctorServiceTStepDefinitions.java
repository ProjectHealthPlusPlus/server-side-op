package com.opencode.healthplusplus.test.step;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencode.healthplusplus.meeting.domain.entity.Clinic;
import com.opencode.healthplusplus.meeting.domain.entity.Location;
import com.opencode.healthplusplus.profile.domain.entity.Specialty;
import com.opencode.healthplusplus.profile.resource.CreateDoctorResource;
import com.opencode.healthplusplus.profile.resource.DoctorResource;
import com.opencode.healthplusplus.profile.resource.PatientResource;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class DoctorServiceTStepDefinitions {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    private int randomServerPort;

    private String endPointPath;
    private ResponseEntity<String> responseEntity;

    private Specialty specialty;
    private List<Specialty> specialties;
    private Clinic clinic;
    private List<Clinic> clinics;

    @Given("The Doctor Endpoint {string} is available")
    public void theDoctorEndpointIsAvailable(String endPointPath) {
        this.endPointPath = String.format(endPointPath, randomServerPort);
    }

    @And("Specialties are already stored")
    public void specialtiesAreAlreadyStored(DataTable table) {
        List<Map<String, String>> rows = table.asMaps(String.class, String.class);

        specialties = new ArrayList<>();
        specialty = new Specialty();

        for (Map<String, String> columns : rows) {
            specialty.setId(Long.parseLong(columns.get("Id")));
            specialty.setName(columns.get("Name"));
            specialty.setDescription(columns.get("Description"));
            specialties.add(specialty);
        }
    }

    @And("Clinics are already stored")
    public void clinicsAreAlreadyStored(DataTable table) {
        List<Map<String, String>> rows = table.asMaps(String.class, String.class);

        clinics = new ArrayList<>();
        clinic = new Clinic();

        for (Map<String, String> columns : rows) {
            clinic.setId(Long.parseLong(columns.get("Id")));
            clinic.setLocation(new Location().withId(Long.parseLong(columns.get("LocationId"))));
            clinics.add(clinic);
        }
    }

    @When("A Doctor Post Request is sent with values {int}, {string}, {string}, {int}, {int}, {int}")
    public void aDoctorPostRequestIsSentWithValues(int dni, String name, String lastName, int age, int specialtyId, int clinicId) {
        CreateDoctorResource resource = new CreateDoctorResource();

        List<Long> specialtiesId = new ArrayList<>();
        specialtiesId.add((long) specialtyId);
        List<Long> clinicsId = new ArrayList<>();
        clinicsId.add((long) clinicId);

        resource.setDni(dni);
        resource.setName(name);
        resource.setLastName(lastName);
        resource.setAge(age);
//        resource.setClinicsId(clinicsId);
//        resource.setSpecialtiesId(specialtiesId);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<CreateDoctorResource> request = new HttpEntity<>(resource, headers);
        responseEntity = testRestTemplate.postForEntity(endPointPath, request, String.class);
    }

    @Then("A Response with Status {int} is received for Doctor")
    public void aResponseWithStatusIsReceivedForDoctor(int expectedStatusCode) {
        int actualStatusCode = responseEntity.getStatusCodeValue();
        assertThat(expectedStatusCode).isEqualTo(actualStatusCode);
    }


    @And("A Doctor Resource with values {int}, {string}, {string}, {int}, {int}, {int} is included in Response Body")
    public void aDoctorResourceWithValuesIsIncludedInResponseBody(int dni, String name, String lastName, int age, int specialtyId, int clinicId) {
        DoctorResource expectedResource = new DoctorResource();
        expectedResource.setDni(dni);
        expectedResource.setName(name);
        expectedResource.setLastName(lastName);
        expectedResource.setAge(age);
        //expectedResource.setClinics(clinics);
        //expectedResource.setSpecialties(specialties);
        String value = responseEntity.getBody();
        ObjectMapper mapper = new ObjectMapper();
        DoctorResource actualResource;
        try {
            actualResource = mapper.readValue(value, DoctorResource.class);
        } catch (JsonProcessingException | NullPointerException e) {
            actualResource = new DoctorResource();
        }
        expectedResource.setId(actualResource.getId());
        assertThat(expectedResource).usingRecursiveComparison()
                .isEqualTo(actualResource);
    }

    @Given("A Doctor Resource with values {int}, {string}, {string}, {int}, {int}, {int} is already stored")
    public void aDoctorResourceWithValuesIsAlreadyStored(int dni, String name, String lastName, int age, int specialtyId, int clinicId) {
        CreateDoctorResource resource = new CreateDoctorResource();
        List<Long> specialtiesId = new ArrayList<>();
        specialtiesId.add((long) specialtyId);
        List<Long> clinicsId = new ArrayList<>();
        clinicsId.add((long) clinicId);

        resource.setDni(dni);
        resource.setName(name);
        resource.setLastName(lastName);
        resource.setAge(age);
//        resource.setClinicsId(specialtiesId);
//        resource.setSpecialtiesId(clinicsId);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<CreateDoctorResource> request = new HttpEntity<>(resource, headers);
        responseEntity = testRestTemplate.postForEntity(endPointPath, request, String.class);
    }

    @And("A Message with values {string} is include in Response Body for Doctor")
    public void aMessageWithValuesIsIncludeInResponseBodyForDoctor(String expectedMessage) {
        String responseBody = responseEntity.getBody();
        assertThat(responseBody).contains(expectedMessage);
    }
}
