#  README: BDD Cucumber Java Automation – Cloud Platform Search
🧪 Project Overview
This is a BDD (Behavior-Driven Development) automation project using Cucumber with Java and Selenium WebDriver. It automates a search scenario on Google and verifies content across multiple web pages, including zdnet.com and aws.amazon.com.

🔍 Test Scenario Summary
The following test steps are automated:

1. Navigate to Google UK using Chrome, Edge, or Firefox.

2. Search for: list of cloud platforms.

3. Scroll through the results until a link from www.zdnet.com is visible.

4. Click on the zdnet.com link.

5. On the ZDNet page, verify that the following cloud providers are listed:

a. Amazon Web Services

b. Microsoft Azure

c. Google Cloud Platform

6. Click the “View at AWS” button:

7. Validate that a new tab opens.

8. Confirm the tab opens: https://aws.amazon.com/what-is-aws/

9. Check that the page contains the text: “Cloud computing with AWS”

🛠️ Technology Stack
> Java

> Cucumber (Gherkin syntax)

> Selenium WebDriver

> JUnit/TestNG (choose based on your setup)

> Maven or Gradle for build and dependency management

> WebDriverManager (optional, to manage browser drivers)

🚀 Getting Started
Prerequisites
> Java 11 or later

> Maven

> IntelliJ IDEA (or any IDE)

> Chrome, Firefox, or Edge installed

Installation
1. Clone the repo:
git clone https://github.com/venkat2186/search-result.git
cd search-result
2. Import the project into IntelliJ as a Maven or Gradle project.

3. Run the tests via:
mvn test

📁 Project Structure
src
└── test
    ├── java
    │   └── steps         # Step definitions
    │   └── runners       # Test runner classes
    │   └── utils         # WebDriver, config utilities
    └── resources
        └── features      # Gherkin feature files
        
📄 Sample Feature File (search.feature)

gherkin
Feature: Validate cloud platforms from Google search and AWS page

  Scenario: Verify ZDNet lists top cloud providers and AWS landing page
    Given I open "https://www.google.co.uk" in "Chrome"
    When I search for "list of cloud platforms"
    And I scroll to find a search result from "zdnet.com"
    And I click on the "zdnet.com" link
    Then I should see the following cloud providers listed:
      | Amazon Web Services     |
      | Microsoft Azure         |
      | Google Cloud Platform   |
    When I click the "View at AWS" button
    Then a new browser tab should open with URL "https://aws.amazon.com/what-is-aws/"
    And the page should contain "Cloud computing with AWS"
