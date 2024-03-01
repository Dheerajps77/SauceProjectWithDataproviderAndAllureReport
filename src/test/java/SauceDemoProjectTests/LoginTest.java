package SauceDemoProjectTests;

import java.util.ArrayList;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import SauceDemoProjectPages.LoginPage;
import TestBase.TestBaseForOtherTests;
import Utils.TestExecutionListener;
import io.qameta.allure.Description;


/*@Listeners({TestExecutionListener.class})*/
public class LoginTest extends TestBaseForOtherTests {
	
	LoginPage loginPage;
	ArrayList<String> userCredsList=new ArrayList<String>();
	
	@Description("Verify the login button using dataprovider")
	@Test(enabled=true, dataProvider = "UserDataDataprovider")
	public void verifyLoginTest(String userName, String password) throws Exception
	{
		try {
				loginPage.enterUserNameInTextBox(userName);
				loginPage.enterUserPasswordInTextBox(password);
				loginPage.clickOnLoginButton();
				loginPage.clickOnHamburgerButton();
				loginPage.clickOnLogoutButton();
			
		} catch (Exception e) {
			throw e;
		}
	}
	
	@DataProvider(name="UserDataDataprovider")
	public Object[][] userData(){
		Object[][] userDataValues = null;
		try {
			loginPage=new LoginPage(driver);
			userCredsList=loginPage.getUserName();
			Object[][] userDataValues1={
				{userCredsList.get(1), "secret_sauce"},
				{userCredsList.get(2), "secret_sauce"},
				{userCredsList.get(3), "secret_sauce"},
				{userCredsList.get(4), "secret_sauce"},
				{userCredsList.get(5), "secret_sauce"},
				{userCredsList.get(6), "secret_sauce"}
			}; 
			userDataValues =userDataValues1;
		} catch (Exception e) {
			throw e;
		}
		return userDataValues;
		
	}

}
