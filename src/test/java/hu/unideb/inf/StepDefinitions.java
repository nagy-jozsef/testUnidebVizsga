package hu.unideb.inf;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class StepDefinitions {

    static WebDriver driver;

    static {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        // A program az egyes lépéseket lehet, hogy túl gyorsan hajtja végre,
        // ezért pl. egy gomb megnyomása után nem talál egy elemet, mert a hozzá tartozó oldal még nem töltődött be
        // Ezzel a beállítással elérjük, hogy a megadott ideig próbálkozzon és csak ennek lejárta után fusson hibára
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Given("YourLogo home page opened")
    public void yourlogo_home_page_opened() {
        driver.get("http://automationpractice.com");
        //throw new io.cucumber.java.PendingException();
    }

    @Given("Contact us link clicked")
    public void contact_us_link_clicked() {
        driver.findElement(By.id("contact-link")).click();
        //throw new io.cucumber.java.PendingException();
    }

    @Given("The Send button is clicked")
    public void the_Send_button_is_clicked() {
        driver.findElement(By.id("submitMessage")).click();
        //throw new io.cucumber.java.PendingException();
    }

    @Then("An invalid email address error message is shown")
    public void an_invalid_email_address_error_message_is_shown() {
        // az xpath kifejezés a Chrome-ból kimásolható a kérdéses elem vonatkozásában
        List<WebElement> elements = driver.findElements(By.xpath("//*[@id=\"center_column\"]/div/ol/li"));
        // az Assert osztállyal vizsgálom, hogy megfelel-e az eredmény az elvártnak
        // ha hamis, akkor a teszt megbukott, a végrehajtás megszakad
        Assert.assertNotEquals(0, elements.size());
        // ha igaz (azaz nem 0 az elemek száma, akkor megvizsgálom a szöveget
        Assert.assertEquals("Invalid email address.", elements.get(0).getText());
        //throw new io.cucumber.java.PendingException();
    }

    @And("The My Account link is clicked")
    public void theMyAccountLinkIsClicked() {
        driver.findElement(By.xpath("//*[@id=\"footer\"]/div/section[5]/h4/a")).click();
    }

    @Given("The Sign In button is clicked")
    public void theSignInButtonIsClicked() {
        driver.findElement(By.id("SubmitLogin")).click();
    }

    @Then("An email address required error message is shown")
    public void anEmailAddressRequiredErrorMessageIsShown() {
        List<WebElement> elements = driver.findElements(By.xpath("//*[@id=\"center_column\"]/div[1]/ol/li"));
        Assert.assertNotEquals(0, elements.size());
        Assert.assertEquals("An email address required.", elements.get(0).getText());
    }

    @Given("The {string} is filled {string}")
    public void theFieldIsFilledValue(String field, String value) {
        driver.findElement(By.id(field)).sendKeys(value);
    }

    @Then("A {string} error is shown")
    public void anMsgErrorIsShown(String msg) {
        List<WebElement> elements = driver.findElements(By.xpath("//*[@id=\"center_column\"]/div[1]/ol/li"));
        Assert.assertNotEquals(0, elements.size());
        Assert.assertEquals(msg, elements.get(0).getText());
    }

    @Given("The subject is selected: Customer service")
    public void theSubjectIsSelectedCustomerService() {
        driver.findElement(By.id("id_contact")).sendKeys("C\n");
    }

    @Then("A {string} message is shown")
    public void anMsgMessageIsShown(String msg) {
        List<WebElement> elements = driver.findElements(By.xpath("//*[@id=\"center_column\"]/p"));
        Assert.assertNotEquals(0, elements.size());
        Assert.assertEquals(msg, elements.get(0).getText());
    }

    @Then("A MY ACCOUNT title is shown")
    public void aMYACCOUNTTitleIsShown() {
        List<WebElement> elements = driver.findElements(By.xpath("//*[@id=\"center_column\"]/h1"));
        Assert.assertNotEquals(0, elements.size());
        Assert.assertEquals("MY ACCOUNT", elements.get(0).getText());
    }
}
