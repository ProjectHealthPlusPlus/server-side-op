Feature: Doctor Service
  As a Developer
  I want to add Doctor through API
  So that It can be available to applications

  Background:
    Given The Doctor Endpoint "http://localhost:%d/api/v1/doctors" is available

    And Specialties are already stored
      | Id | Name             | Description |
      | 1  | Cardiology       | Heart       |
      | 1  | Medicine General | All General |
    And Clinics are already stored
      | Id | LocationId |
      | 1  | 1          |

  @patient-adding
  Scenario: Add Doctor
    When A Doctor Post Request is sent with values 87654321, "John", "Smith", 20, 1, 1
#    | Age | Dni       | Name | Lastname  |
#    | 20  | 87654321  | Jhon | Smith     |
    Then A Response with Status 500 is received for Doctor
    And A Doctor Resource with values 87654321, "John", "Smith", 20, 1, 1 is included in Response Body

  @patient-duplicated
  Scenario: Add Doctor with existing Dni
    Given A Doctor Resource with values 87654321, "John", "Smith", 20, 1, 1 is already stored
    When A Doctor Post Request is sent with values 87654321, "John", "Smith", 20, 1, 1
    Then A Response with Status 400 is received for Doctor
    And A Message with values "A Doctor with the same dni exist" is include in Response Body for Doctor