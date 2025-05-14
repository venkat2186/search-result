Feature: Google Cloud Platform Search and Verification

  Scenario: Verify cloud platforms listed in zdnet article
    Given I launch the browser and open "https://www.google.co.uk"
    When I search for "List of cloud platforms zdnet 2021"
    And I scroll and click the zdnet.com link from the results
    Then I should see the following cloud providers listed:
      | Amazon Web Services     |
      | Microsoft Azure         |
      | Google Cloud Platform   |
    When I click the "View at AWS" button
    Then a new tab should open with URL "https://aws.amazon.com/what-is-aws/"
    And the page should contain the text "Cloud computing with AWS"
