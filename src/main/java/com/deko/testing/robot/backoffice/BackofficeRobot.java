package com.deko.testing.robot.backoffice;

import com.deko.testing.robot.BaseRobot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BackofficeRobot extends BaseRobot {

    public BackofficeRobot(WebDriver driver) { super(driver);}

    @FindBy(name = "LoginForm")
    private WebElement loginForm;

    @FindBy(xpath = "/html/body/div[1]/div/div/div/div/form/div[2]/input")
    private WebElement usernameField;

    @FindBy(xpath = "/html/body/div/div/div/div/div/form/div[3]/input")
    private WebElement passwordField;

    @FindBy(xpath = "//*[@class = \"btn btn-link forgotten-link\"]")
    private WebElement forgotPasswordLink;

    @FindBy(xpath = "//*[@id=\"ng-app\"]/div[1]/div/div/div/div/form/div[6]/button")
    private WebElement signInButton;

    @FindBy(xpath = "/html/body/div/div/div/div/div/form/div[1]/div/div/p")
    private WebElement signInError;

    @FindBy(xpath = "/html/body/div/div/div/div/form/div[2]/input")
    private WebElement resetPasswordField;

    @FindBy(xpath = "/html/body/div/div/div/div/form/div[3]/div/button[2]/span")
    private WebElement resetButton;

    @FindBy(xpath = "/html/body/div/div/div/div/form/div[2]/div/button/span")
    private WebElement resetSignInButton;

    @FindBy(xpath = "/html/body/div/div/div/div/form/div[1]/div/div/p")
    private WebElement resetSuccessText;

    @FindBy(id = "top-bar")
    private WebElement backOfficeDashboardTopBar;

    private final String baseUrl = "https://release.dekopay.org/backoffice/v2/#/";

    public BackofficeRobot openBackofficeLoginPage() {
        goTo(baseUrl);
        wait.until(ExpectedConditions.visibilityOf(this.loginForm));
        return this;
    }

    public BackofficeRobot fillLoginUsername(String username) {
        type(usernameField, username);
        return this;
    }

    public BackofficeRobot fillLoginPassword(String password) {
        type(passwordField, password);
        return this;
    }

    public BackofficeRobot submitLoginForm() {
        waitUntilVisible(signInButton);
        click(signInButton);
        return this;
    }

    public BackofficeRobot clickForgottenPasswordLink() {
        click(forgotPasswordLink);
        waitUntilURLContains("reset");
        return this;
    }

    public BackofficeRobot fillResetPasswordField(String userName) {
        type(resetPasswordField, userName);
        return this;
    }

    public BackofficeRobot resetPassword() {
        click(resetButton);
        return this;
    }

    public BackofficeRobot clickResetSignInButton() {
        click(resetSignInButton);
        return this;
    }

    public boolean verifySignInError() {
        if (WebElementContains(signInError, "details you provided were incorrect")) {
            return true;
        }
        return false;
    }

    public boolean verifyBackofficeUrl() {
        if (verifyURLContains("backoffice")) {
            return true;
        }
        return false;
    }

    public boolean verifySuccessfulLogin(String firstName) {
        if (WebElementContains(backOfficeDashboardTopBar, firstName)) {
            return true;
        }
        return false;
    }

    public boolean verifyResetPasswordSuccess() {
        if (WebElementContains(resetSuccessText, "a password reset email has been sent to the connected email address")) {
            return true;
        }
        return false;
    }

    public boolean verifySignInButtonDisabled() {
        if (!signInButton.isEnabled()) {
            return true;
        }
        return false;
    }
}