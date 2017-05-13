package com.onlineSchool;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import junit.framework.TestCase;

public class Teachertest1 extends TestCase{
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://localhost:8080/login";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testTeachertest1() throws Exception {
    driver.get(baseUrl + "/login");
    driver.findElement(By.name("email")).clear();
    driver.findElement(By.name("email")).sendKeys("ashrakat.mokhtar@yahoo.com");
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys("asasasas");
    driver.findElement(By.id("signin")).click();
    driver.findElement(By.linkText("Create Game")).click();
    driver.findElement(By.name("gameName")).clear();
    driver.findElement(By.name("gameName")).sendKeys("History of earth");
    driver.findElement(By.id("MCQ")).click();
    driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
    driver.findElement(By.id("Header")).clear();
    driver.findElement(By.id("Header")).sendKeys("the earth has been formed since");
    driver.findElement(By.id("Answer")).clear();
    driver.findElement(By.id("Answer")).sendKeys("60 million years");
    driver.findElement(By.id("Answer1")).clear();
    driver.findElement(By.id("Answer1")).sendKeys("60 million years");
    driver.findElement(By.id("Answer2")).clear();
    driver.findElement(By.id("Answer2")).sendKeys("15 milliion years");
    driver.findElement(By.id("Answer3")).clear();
    driver.findElement(By.id("Answer3")).sendKeys("1 million year");
    driver.findElement(By.id("Answer4")).clear();
    driver.findElement(By.id("Answer4")).sendKeys("bellion year");
    driver.findElement(By.name("cGame")).click();
    driver.findElement(By.id("courses")).click();
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
