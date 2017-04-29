package com.onlineSchool.Controllerstest;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import junit.framework.TestCase;

public class TeacherTest extends TestCase{
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://localhost:8080/register";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testTeacher() throws Exception {
    driver.get(baseUrl + "/register");
    driver.findElement(By.name("username")).clear();
    driver.findElement(By.name("username")).sendKeys("shosho");
    driver.findElement(By.name("email")).clear();
    driver.findElement(By.name("email")).sendKeys("ashrakat.mokhtar@lol.com");
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys("asasasas");
    driver.findElement(By.name("birthday")).clear();
    driver.findElement(By.name("birthday")).sendKeys("17/10/1996");
    new Select(driver.findElement(By.id("Gender"))).selectByVisibleText("Female");
    driver.findElement(By.cssSelector("input[name=\"Sign up\"]")).click();
    driver.findElement(By.name("email")).clear();
    driver.findElement(By.name("email")).sendKeys("ashrakat.mokhtar@lol.com");
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys("asasasas");
    driver.findElement(By.id("signin")).click();
    driver.findElement(By.linkText("Create Course")).click();
    driver.findElement(By.name("courseName")).clear();
    driver.findElement(By.name("courseName")).sendKeys("Language");
    driver.findElement(By.cssSelector("input[name=\"cCourse\"]")).click();
    driver.findElement(By.linkText("Create Game")).click();
    driver.findElement(By.name("gameName")).clear();
    driver.findElement(By.name("gameName")).sendKeys("Math is fun");
    new Select(driver.findElement(By.id("courseName"))).selectByVisibleText("Language");
    new Select(driver.findElement(By.id("courseName"))).selectByVisibleText("Math");
    driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
    driver.findElement(By.id("Header")).clear();
    driver.findElement(By.id("Header")).sendKeys("1+1=2");
    driver.findElement(By.id("Answer")).click();
    driver.findElement(By.cssSelector("#create > div > button[type=\"button\"]")).click();
    driver.findElement(By.id("Header")).clear();
    driver.findElement(By.id("Header")).sendKeys("1/3 = 3");
    // ERROR: Caught exception [Error: Dom locators are not implemented yet!]
    driver.findElement(By.cssSelector("#create > div > button[type=\"button\"]")).click();
    driver.findElement(By.id("Header")).clear();
    driver.findElement(By.id("Header")).sendKeys("1^10 = 1");
    driver.findElement(By.id("Answer")).click();
    driver.findElement(By.name("cGame")).click();
    driver.findElement(By.xpath("(//p[@id='courses'])[3]")).click();
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
