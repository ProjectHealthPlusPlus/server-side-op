Feature: Clinic Location Service
  As a Developer
  I want to add ClinicLocation through API
  So that It can be available to applications

  Background:
    Given The ClinicLocation Endpoint "http://localhost:%d/api/v1/clinicLocations" is available

  @clinicLocation-adding
  Scenario: Add ClinicLocation
    When A ClinicLocation Post Request is sent with values "Av. Abcd", "Lima", "Peru"
    Then A Response with Status 200 is received for ClinicLocation
    And A ClinicLocation Resource with values "Av. Abcd", "Lima", "Peru" is included in Response Body

  @clinicLocation-invalid-address
  Scenario: Add ClinicLocation with blank Address
    When A ClinicLocation Post Request is sent with values "", "Lima", "Peru"
    Then A Response with Status 400 is received for ClinicLocation
    And A Message with values "Not all constraints satisfied for" is include in Response Body for ClinicLocation