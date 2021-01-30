package com.deko.tests.RetailFinance;

import com.deko.testing.robot.backoffice.BackofficeRobot;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class BackofficeLoginTest {

    private WebDriver driver;
    String firstName = "Ajay";
    String surName = "Patel";  //Actually meant to be Pitla
    String userName = firstName + "." + surName;
    String password = "DekoQA2020";

    @BeforeTest
    public void setupDriver() {
        WebDriverManager.chromedriver().setup();
        this.driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterTest
    public void testTearDown() {
        driver.close();
    }

    @AfterSuite
    public void suiteTearDown() {
        driver.quit();
    }

    @Test(priority = 0)
    public void backOfficeLoginPageLoadSuccess() {
        BackofficeRobot robot = new BackofficeRobot(driver);
        robot
                .openBackofficeLoginPage()
                .verifyBackofficeUrl();

        Assert.assertTrue(robot.verifyBackofficeUrl());
    }

    @Test(priority = 1)
    public void backOfficeLoginUnsuccessful() {
        BackofficeRobot robot = new BackofficeRobot(driver);
        robot
                .openBackofficeLoginPage()
                .fillLoginUsername("Invalid" + userName)
                .fillLoginPassword(password)
                .submitLoginForm()
                .verifySignInError();

        Assert.assertTrue(robot.verifySignInError());
    }

    @Test(priority = 2)
    public void signInDisabled() {

        BackofficeRobot robot = new BackofficeRobot(driver);
        robot
                .openBackofficeLoginPage()
                .verifySignInButtonDisabled();

        Assert.assertTrue(robot.verifySignInButtonDisabled());

    }

    @Test(priority = 3)
    public void backOfficeAppPasswordReset() {
        BackofficeRobot robot = new BackofficeRobot(driver);
        robot
                .openBackofficeLoginPage()
                .clickForgottenPasswordLink()
                .fillResetPasswordField(userName)
                .resetPassword()
                .verifyResetPasswordSuccess();

        Assert.assertTrue(robot.verifyResetPasswordSuccess());
        robot.clickResetSignInButton()
                .verifyBackofficeUrl();
    }

    @Test(priority = 4)
    public void backOfficeLoginSuccessful() {
        BackofficeRobot robot = new BackofficeRobot(driver);

        robot
                .openBackofficeLoginPage()
                .fillLoginUsername(userName)
                .fillLoginPassword(password)
                .submitLoginForm()
                .verifySuccessfulLogin(firstName);

        Assert.assertTrue(robot.verifySuccessfulLogin(firstName));
    }
}