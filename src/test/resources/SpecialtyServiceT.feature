Feature: Specialty Adding
  As a Developer
  I want to add Specialty through API
  So that It can be available to applications

  Background:
    Given The Endpoint "http://localhost:%d/api/v1/posts" is available

    @specialty-adding
    Scenario: Add Specialty
      When A Specialty Post Request is sent with values "Cardiology", "The branch of medicine that deals with diseases and abnormalities of the heart."
      Then A Response with Status 200 is received
      And A Specialty Resource with values "Cardiology", "The branch of medicine that deals with diseases and abnormalities of the heart." is included in Response Body

    @specialty-duplicated
    Scenario: Add Specialty with existing Name
      Given A Specialty Resource with values "Cardiology", "The branch of medicine that deals with diseases and abnormalities of the heart." is already stored
      When A Specialty Post Request is sent with values "Cardiology", "The branch of medicine that deals with diseases and abnormalities of the heart."
      Then A Response with Status 400 is received
      And A Message with values "A Specialty with the same name exist" is include in Response Body
