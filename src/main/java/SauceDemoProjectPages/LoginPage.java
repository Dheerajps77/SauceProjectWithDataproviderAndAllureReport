package SauceDemoProjectPages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Utils.SeleniumUtils;
import io.qameta.allure.Step;

public class LoginPage {
	WebDriver driver;
	SeleniumUtils seleniumUtils = new SeleniumUtils();

	public LoginPage(WebDriver driver1) {
		driver = driver1;
	}

	private static final By USER_NAME_TEXTFIELD = By.xpath("//input[@id='user-name']");
	private static final By USER_PWD_TEXTFIELD = By.xpath("//input[@id='password']");
	private static final By USER_NAME_VALUES = By.xpath("//div[@id='login_credentials']/br");
	private static final By LOGIN_BTN = By.xpath("//input[@type='submit']");
	private static final By HAMBURGER_BTN = By.xpath("//button[@id='react-burger-menu-btn']");
	private static final By LOGOUT_BTN = By.xpath("//a[@id='logout_sidebar_link']");
	ArrayList<String> userNameOfList;

	public ArrayList<String> getUserName() {
		userNameOfList = new ArrayList<String>();
		try {
			String loginCreds = driver.findElement(By.xpath("//div[@id='login_credentials']")).getText();
			String[] arrayOfCreds = loginCreds.split(":")[1].split("\n");
			int len = arrayOfCreds.length;
			for (int i = 0; i <= len - 1; i++) {
				userNameOfList.add(arrayOfCreds[i]);
			}
		} catch (Exception e) {
			throw e;
		}
		return userNameOfList;
	}

	@Step("Click on Login button")
	public void clickOnLoginButton() {
		try {
			SeleniumUtils.waitForElementPresence(driver, LOGIN_BTN, 20);
			driver.findElement(LOGIN_BTN).click();
		} catch (Exception e) {
			throw e;
		}
	}
	@Step("Click on Hamburger button")
	public void clickOnHamburgerButton() throws Exception {
		try {
			SeleniumUtils.waitForElementPresence(driver, HAMBURGER_BTN, 20);
			driver.findElement(HAMBURGER_BTN).click();
			Thread.sleep(2000);
		} catch (Exception e) {
			throw e;
		}
	}
	
	@Step("Click on Logout button")
	public void clickOnLogoutButton() throws Exception {
		try {
			SeleniumUtils.waitForElementPresence(driver, LOGOUT_BTN, 20);
			driver.findElement(LOGOUT_BTN).click();
			Thread.sleep(2000);
		} catch (Exception e) {
			throw e;
		}
	}

	@Step("Enter UserName in Username Textbox")
	public void enterUserNameInTextBox(String userName) throws Exception {
		try {
			SeleniumUtils.waitForElementPresence(driver, USER_NAME_TEXTFIELD, 5);
			SeleniumUtils.clickAndEnterText(driver, USER_NAME_TEXTFIELD, 5, userName);
			//driver.findElement(USER_NAME_TEXTFIELD).sendKeys(userName);
		} catch (Exception e) {
			throw e;
		}
	}
	
	@Step("Enter Password in Password Textbox")
	public void enterUserPasswordInTextBox(String userPassword) throws Exception {
		try {
			SeleniumUtils.waitForElementPresence(driver, USER_PWD_TEXTFIELD, 5);
			SeleniumUtils.clickAndEnterText(driver, USER_PWD_TEXTFIELD, 5, userPassword);
		} catch (Exception e) {
			throw e;
		}
	}
}
