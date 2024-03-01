package Utils;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UserFunctions {

	private WebDriver driver;
	private final int WAIT_SECONDS = Integer.valueOf(SeleniumUtils.fetchUserDetails("GLOBAL_WAIT"));
	public static PropertiesManager properties = PropertiesManager.getInstance();
	private final By USERNAME_TEXT_FIELD = By.xpath("//input[@placeholder='Username'] | //input[@placeholder='Email']");
	private final By PASSWORD_TEXT_FIELD = By.xpath("//input[@placeholder='Password']");
	private final By LOGIN_BUTTON = By
			.xpath("//button[@class='btn btn-info btn-lg btn-block text-uppercase waves-effect waves-light']");
	private final By LOGOUT_BUTTON = By.xpath("//*[text()='Disconnect '] | //span[text()='Disconnect']");
	//private final String AM_ENVIRONMENT = TestBase.properties.getConfig("ASSETMANAGER_URL");
	//private final String BANK_ENVIRONMENT = TestBase.properties.getConfig("BANK_URL");
	//private final String ADMIN_ENVIRONMENT = TestBase.properties.getConfig("ADMIN_URL");
	//private final String JSONPATH = System.getProperty("user.dir") + TestBase.properties.getConfig("JSON_USERS_PATH");

	public UserFunctions(WebDriver driver) {
		this.driver = driver;
	}

	public void enterUserName(String username) {
		SeleniumUtils.waitForElementPresence(driver, USERNAME_TEXT_FIELD, WAIT_SECONDS).sendKeys(username);
	}

	public void enterPassword(String password) {
		SeleniumUtils.waitForElementPresence(driver, PASSWORD_TEXT_FIELD, WAIT_SECONDS).sendKeys(password);
	}

	public void clickLogin() {
		SeleniumUtils.waitForElementPresence(driver, LOGIN_BUTTON, WAIT_SECONDS).click();
	}

	public void clickLogout() {
		SeleniumUtils.waitForElementVisibility(driver, LOGOUT_BUTTON, WAIT_SECONDS).click();
	}

	public String getLogoutText() throws Exception {
		try {
			return SeleniumUtils.waitForElementVisibility(driver, LOGOUT_BUTTON, WAIT_SECONDS).getText();
		} catch (Exception e) {
			throw e;
		}
	}

	// This method is used when user is passed as parameters. It will internally
	/*
	 * // fetch the user details based on user public void
	 * enterDetailsAndLogin(String user) throws Exception { try { GenericUtils
	 * genericUtils = new GenericUtils(); HashMap<String, String> userDetails =
	 * genericUtils.getUserDetailsFromJSON(JSONPATH, user);
	 * enterUserName(userDetails.get("username"));
	 * enterPassword(userDetails.get("password")); clickLogin(); //TestBase.logInfo(
	 * //String.format(properties.getLogMessage("UserLoginSuccessful"),
	 * userDetails.get("username"))); } catch (Exception e) { e.printStackTrace();
	 * throw e; } }
	 */

	// This method is used when userName and password are passed as parameters
	public boolean enterDetailsAndLoginUsingCredentials(String userName, String password) throws Exception {
		try {
			enterUserName(userName);
			enterPassword(password);
			clickLogin();
			if (SeleniumUtils.waitForElementVisibility(driver, LOGOUT_BUTTON, WAIT_SECONDS).isDisplayed()) {
				//TestBase.logInfo(String.format(properties.getLogMessage("UserLoginSuccessful"), userName));
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	// This method is used when userName and password are passed as parameters
	// having status is Disabled
	public boolean enterDetailsAndLoginUsingDisabledUserCredentials(String userName, String password,
			String homePageURL) throws Exception {
		boolean flag = false;
		SeleniumUtils objSeleniumUtils;
		try {
			objSeleniumUtils = new SeleniumUtils();
			enterUserName(userName);
			enterPassword(password);
			clickLogin();
			if (objSeleniumUtils.getCurrentPageURL(driver).equalsIgnoreCase(homePageURL)) {
				return true;
			}
		} catch (Exception e) {
			throw e;
		}
		return flag;
	}

	// This method is used when portal and user is passed as parameters. It will
	// internally fetch the user details based on Asset Manager, Bank or Admin
	// portal
	/*
	 * public void loginUser(String portal, String user) throws Exception { try { if
	 * (portal.equals(TestBase.properties.getConstant("AssetManager"))) {
	 * driver.get(AM_ENVIRONMENT); enterDetailsAndLogin(user); } if
	 * (portal.equals(TestBase.properties.getConstant("Bank"))) {
	 * driver.get(BANK_ENVIRONMENT); enterDetailsAndLogin(user); } if
	 * (portal.equals(TestBase.properties.getConstant("Admin"))) {
	 * driver.get(ADMIN_ENVIRONMENT); enterDetailsAndLogin(user); } } catch
	 * (Exception e) { throw e; } }
	 */
	/*
	 * // This method is used when userName and password are passed as parameters //
	 * having status is Disabled public boolean
	 * enterDetailsAndLoginUsingDisabledUserCredentials(String userName, String
	 * password, String homePageURL) throws Exception { boolean flag = false;
	 * SeleniumUtils objSeleniumUtils; try { objSeleniumUtils = new SeleniumUtils();
	 * enterUserName(userName); enterPassword(password); clickLogin(); if
	 * (objSeleniumUtils.getCurrentPageURL(driver).equalsIgnoreCase(homePageURL)) {
	 * return true; } } catch (Exception e) { throw e; } return flag; }
	 */

	// This is used to Logout
	public void LogOut() throws Exception {
		try {
			clickLogout();
		} catch (Exception e) {
			throw e;
		}
	}
}
