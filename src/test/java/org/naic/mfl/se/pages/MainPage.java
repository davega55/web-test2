package org.naic.mfl.se.pages;

import org.naic.mfl.se.framework.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;

public class MainPage extends BasePage {


    public MainPage(WebDriver driver){
        super(driver);
        driver.get("http://automationpractice.com/index.php");
        waitForElementToAppearWebElement(By.className("login")).click();
    }

    public void setEmail(String email){
        driver.findElement(By.id("email_create")).sendKeys(email);
    }

    public void clickSubmit(){
        driver.findElement(By.id("SubmitCreate")).click();
        waitForElementToAppearWebElement(By.id("id_gender2")).click();
    }

    public void setName(String name) {
        driver.findElement(By.id("customer_firstname")).sendKeys(name);
    }

    public void setSurName(String surname){
        driver.findElement(By.id("customer_lastname")).sendKeys(surname);
    }

    public void setPassword(String password){
        driver.findElement(By.id("passwd")).sendKeys(password);
    }

   public void setDay(String day){
        Select select = new Select(driver.findElement(By.id("days")));
        select.selectByValue(day);
    }

    public void setMonth(String month){
        Select select = new Select(driver.findElement(By.id("months")));
        select.selectByValue(month);
    }

    public void setYear(String year){
        Select select = new Select(driver.findElement(By.id("years")));
        select.selectByValue(year);
    }

    public void setCompany(String company){
        driver.findElement(By.id("company")).sendKeys(company);
    }

    public void setAddress1(String address1){
        driver.findElement(By.id("address1")).sendKeys(address1);
    }

    public void setAddress2(String address2){
        driver.findElement(By.id("address2")).sendKeys(address2);
    }

    public void setCity(String city){
        driver.findElement(By.id("city")).sendKeys(city);
    }

    public void setState(String state){
        Select select = new Select(driver.findElement(By.id("id_state")));
        select.selectByVisibleText(state);
    }

    public void setPostalCode(String postalCode){
        driver.findElement(By.id("postcode")).sendKeys(postalCode);
    }

    public void setOther(String other){
        driver.findElement(By.id("other")).sendKeys(other);
    }

    public void setPhone(String phone){
        driver.findElement(By.id("phone")).sendKeys(phone);
    }

    public void setPhoneMobile(String phoneMobile){ driver.findElement(By.id("phone_mobile")).sendKeys(phoneMobile); }

    public void setAlias(String alias){
        driver.findElement(By.id("alias")).sendKeys(alias);
    }

    public WebElement clickRegister() {
        driver.findElement(By.id("submitAccount")).click();
        WebElement heading = waitForElementToAppearWebElement(By.cssSelector("h1"));
        return heading;
    }
}
