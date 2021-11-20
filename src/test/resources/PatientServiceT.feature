Feature: Patient Service
  As a Developer
  I want to add Patient through API
  So that It can be available to applications

  Background:
    Given The Patient Endpoint "http://localhost:%d/api/v1/patients" is available

  @patient-adding
  Scenario: Add Patient
    When A Patient Post Request is sent with values 12345678, "Patrick", "Ortiz", 19, "Av. Arequipa"
    Then A Response with Status 200 is received for Patient
    And A Patient Resource with values 12345678, "Patrick", "Ortiz", 19, "Av. Arequipa" is included in Response Body

  @patient-duplicated
  Scenario: Add Patient with existing Dni
    Given A Patient Resource with values 12345678, "Patrick", "Ortiz", 19, "Av. Arequipa" is already stored
    When A Patient Post Request is sent with values 12345678, "Patrick", "Ortiz", 19, "Av. Arequipa"
    Then A Response with Status 400 is received for Patient
    And A Message with values "A Patient with the same dni exist" is include in Response Body for Patient
