
package stepdefinitions;

import utilities.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.en.*;
import org.testng.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Set;



public class GoogleSearchSteps
{
    WebDriver driver = DriverFactory.getDriver(System.getProperty("browser", "chrome"));

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    String originalWindow;

    // WebElement collection
    final static String LINK_SEARCHED_TOPIC_XPATH = "//h3[contains(text(),'Top cloud providers: AWS, Microsoft Azure, and ...')]";

    @Given("I launch the browser and open {string}")
    public void i_launch_the_browser_and_open(String url) throws InterruptedException {
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(120));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(120));
        driver.get(url);
        originalWindow = driver.getWindowHandle();

    }

    @When("I search for {string}")
    public void i_search_for(String query) throws InterruptedException {
        Thread.sleep(20000);
        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("q")));
        searchBox.sendKeys(query + Keys.ENTER);

    }

    @When("I scroll and click the zdnet.com link from the results")
    public void i_click_zdnet_link() throws InterruptedException {
        Thread.sleep(50000);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(LINK_SEARCHED_TOPIC_XPATH)));
        driver.findElement(By.xpath(LINK_SEARCHED_TOPIC_XPATH)).click();

    }

    @Then("I should see the following cloud providers listed:")
    public void i_should_see_cloud_providers(io.cucumber.datatable.DataTable dataTable) throws InterruptedException {
        Thread.sleep(50000);
        List<String> expectedProviders = dataTable.asList();
        for (String provider : expectedProviders) {
            boolean isPresent = driver.getPageSource().contains(provider);
            Assert.assertTrue(isPresent, "Expected provider not found: " + provider);
        }

    }

    @When("I click the {string} button")
    public void i_click_the_button(String buttonText) throws InterruptedException {
        Thread.sleep(30000);
        WebElement element = driver.findElement(By.xpath("//descendant::div/a[span[contains(text(),'Amazon Web Services')]]/following-sibling::div/descendant::div/span[contains(text(),'View at')]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        Thread.sleep(500);
        WebElement button;
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//descendant::div/a[span[contains(text(),'Amazon Web Services')]]/following-sibling::div/descendant::div/span[contains(text(),'View at')]")));
        button=driver.findElement( By.xpath("//descendant::div/a[span[contains(text(),'Amazon Web Services')]]/following-sibling::div/descendant::div/span[contains(text(),'View at')]"));


    }

    @Then("a new tab should open with URL {string}")
    public void a_new_tab_should_open(String expectedUrl) throws InterruptedException {
        Thread.sleep(30000);
        //wait.until(ExpectedConditions.numberOfWindowsToBe(2));
        Set<String> windows = driver.getWindowHandles();
        for (String window : windows) {
            if (!window.equals(originalWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }
        wait.until(ExpectedConditions.urlContains("aws.amazon.com"));
        Assert.assertEquals(driver.getCurrentUrl(), expectedUrl);

    }

    @Then("the page should contain the text {string}")
    public void page_should_contain_text(String text) throws InterruptedException {
        Thread.sleep(900000);
        boolean isPresent = driver.getPageSource().contains(text);
        Assert.assertTrue(isPresent, "Expected text not found on AWS page");

    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
