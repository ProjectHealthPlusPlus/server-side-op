Feature: AppointmentDetails Service
  As a Developer
  I want to add AppointmentDetails through API
  So that It can be available to applications

  Background:
    Given The AppointmentDetails Endpoint "http://localhost:%d/api/v1/appointmentDetails" is available

    And Diagnostic is already stored
      | Id | PublishDate              | Description |
      | 1  | 2021-20-17T06:51:19.661Z | Heart       |

  @patient-adding
  Scenario: Add AppointmentDetails
    When A AppointmentDetails Post Request is sent with values "2021-20-17T06:51:19.661Z", "2021-20-17T06:51:19.661Z", "2021-20-17T06:51:19.661Z", "2021-20-17T06:51:19.661Z", 1
    Then A Response with Status 404 is received for AppointmentDetails
    And A AppointmentDetails Resource with values "2021-20-17T06:51:19.661Z", "2021-20-17T06:51:19.661Z", "2021-20-17T06:51:19.661Z", "2021-20-17T06:51:19.661Z", 1 is included in Response Body

  @patient-duplicated
  Scenario: Add AppointmentDetails with invalid Diagnostic Id
    When A AppointmentDetails Post Request is sent with values "2021-20-17T06:51:19.661Z", "2021-20-17T06:51:19.661Z", "2021-20-17T06:51:19.661Z", "2021-20-17T06:51:19.661Z", 0
    Then A Response with Status 404 is received for AppointmentDetails
    And A Message with values "Diagnostic with id 0 not found." is include in Response Body for AppointmentDetails
