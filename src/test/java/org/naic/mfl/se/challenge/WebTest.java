package org.naic.mfl.se.challenge;

import org.naic.mfl.se.framework.BasePage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.naic.mfl.se.pages.*;

import java.util.Date;

public class WebTest{
    WebDriver driver;
    WebDriverWait wait;

    String existingUserEmail = "mflsqe@naic.org";
    String existingUserPassword = "mflsqe1234";

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void signInTest() {
        String timestamp = String.valueOf(new Date().getTime());
        String email = "hf_challenge_" + timestamp + "@hf" + timestamp.substring(7) + ".com";
        String name = "Firstname";
        String surname = "Lastname";

        MainPage page = new MainPage(driver);

        page.setEmail(email);
        page.clickSubmit();
        page.setName(name);
        page.setSurName(surname);
        page.setPassword("Querty");

        page.setDay("1");
        page.setMonth("1");
        page.setYear("2000");

        page.setCompany("Company");
        page.setAddress1("Qwerty 123");
        page.setAddress2("zxcyb");
        page.setCity("Qwerty");

        page.setState("Colorado");
        page.setPostalCode("12345");
        page.setOther("Qwerty");
        page.setPhone("12345123123");
        page.setPhoneMobile("12345123123");
        page.setAlias("hf");
        WebElement heading = page.clickRegister();

        Assert.assertEquals(heading.getText(), "MY ACCOUNT");
        Assert.assertEquals(driver.findElement(By.className("account")).getText(), name + " " + surname);
        Assert.assertTrue(driver.findElement(By.className("info-account")).getText().contains("Welcome to your account."));
        Assert.assertTrue(driver.findElement(By.className("logout")).isDisplayed());
        Assert.assertTrue(driver.getCurrentUrl().contains("controller=my-account"));
    }

    @Test
    public void logInTest() {
        String fullName = "mfl sqe";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("login"))).click();
        driver.findElement(By.id("email")).sendKeys(existingUserEmail);
        driver.findElement(By.id("passwd")).sendKeys(existingUserPassword);
        driver.findElement(By.id("SubmitLogin")).click();
        WebElement heading = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1")));

        Assert.assertEquals("MY ACCOUNT", heading.getText());
        Assert.assertEquals(fullName, driver.findElement(By.className("account")).getText());
        Assert.assertTrue(driver.findElement(By.className("info-account")).getText().contains("Welcome to your account."));
        Assert.assertTrue(driver.findElement(By.className("logout")).isDisplayed());
        Assert.assertTrue(driver.getCurrentUrl().contains("controller=my-account"));
    }

    @Test
    public void checkoutTest() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("login"))).click();
        driver.findElement(By.id("email")).sendKeys(existingUserEmail);
        driver.findElement(By.id("passwd")).sendKeys(existingUserPassword);
        driver.findElement(By.id("SubmitLogin")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Women"))).click();
        driver.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li[1]/div/div[1]/div/a[1]/img")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("Submit"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Proceed to checkout"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Proceed to checkout"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("processAddress"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("uniform-cgv"))).click();
        driver.findElement(By.name("processCarrier")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("bankwire"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='cart_navigation']/button"))).click();
        WebElement heading = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1")));
        Assert.assertEquals("ORDER CONFIRMATION", heading.getText());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='step_done step_done_last four']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@id='step_end' and @class='step_current last']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//*[@class='cheque-indent']/strong")).getText().contains("Your order on My Store is complete."));
        Assert.assertTrue(driver.getCurrentUrl().contains("controller=order-confirmation"));
    }

    @AfterClass
    public void closeDriver()
    {
        driver.close();
    }
}
