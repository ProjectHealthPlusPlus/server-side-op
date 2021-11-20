Feature: Clinic Service
  As a Developer
  I want to add Clinic through API
  So that It can be available to applications

  Background:
    Given The Clinic Endpoint "http://localhost:%d/api/v1/clinics" is available

    And Clinic is already stored
      | Id | Address          | City |  Country |
      | 1  | Av. Javier Prado | Lima |  Peru    |

  @patient-adding
  Scenario: Add Clinic
    When A Clinic Post Request is sent with values 1
    Then A Response with Status 500 is received for Clinic
    And A Clinic Resource with values 1 is included in Response Body

  @patient-duplicated
  Scenario: Add Clinic with Location from other Clinic
    Given A Clinic Resource with values 1 is already stored
    When A Clinic Post Request is sent with values 1
    Then A Response with Status 500 is received for Clinic
    And A Message with values "A Clinic with the same Location exit" is include in Response Body for Clinic
