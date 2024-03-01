package SauceDemoProjectTests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import TestBase.TestBase;


public class Selenium4ParallelDemo extends TestBase {

	@Test
	public void validCredentials() {

		getDriver().findElement(By.xpath("//*[@id='txtUsername']")).sendKeys("Admin");
		getDriver().findElement(By.xpath("//*[@id='txtPassword']")).sendKeys("admin123");
		getDriver().findElement(By.xpath("//*[@id='btnLogin']")).click();
		String newPageText = getDriver().findElement(By.xpath("//*[@id='content']/div/div[1]/h1")).getText();
		System.out.println("newPageText :" + newPageText);
		assertEquals(newPageText, "Dashboard");

	}

	@Test
	public void invalidCredentials() {

		getDriver().findElement(By.xpath("//*[@id='txtUsername']")).sendKeys("1234");
		getDriver().findElement(By.xpath("//*[@id='txtPassword']")).sendKeys("12342");
		getDriver().findElement(By.xpath("//*[@id='btnLogin']")).click();
		String actualErrorMessage = getDriver().findElement(By.xpath("//*[@id='spanMessage']")).getText();
		System.out.println("Actual ErrorMessage :" + actualErrorMessage);
		assertEquals(actualErrorMessage, "Invalid credentials");

	}

	@Test
	public void loginPageHeading() {

		String loginText = getDriver().findElement(By.xpath("//*[@id='logInPanelHeading']")).getText();
		System.out.println("Actual loginText :" + loginText);
		assertEquals(loginText, "LOGIN Panel");

	}

	@Test
	public void forgotPasswordPageHeading() {

		getDriver().findElement(By.xpath("//*[@id='forgotPasswordLink']/a")).click();
		String forgetPasswordTitle = getDriver().findElement(By.xpath(" //*[@id='content']/div[1]/div[2]/h1"))
				.getText();
		System.out.println("Actual Page Title of Forgot Password Page :" + forgetPasswordTitle);
		assertEquals(forgetPasswordTitle, "Forgot Your Password?");
	}

	@Test
	public void verifyLinkedIn() {

		Boolean linkedInIcon = getDriver().findElement(By.xpath("//*[@id='social-icons']/a[1]/img")).isEnabled();
		System.out.println("Actual linkedIn Text :" + linkedInIcon);
		assertTrue(linkedInIcon);

	}

}
