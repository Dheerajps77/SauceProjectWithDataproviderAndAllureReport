package TestBase;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

public class TestBase {

	protected static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<RemoteWebDriver>();
	public static String remote_url = "http://localhost:4445/";
	public Capabilities capabilities;

	@Parameters({ "browser" })
	@BeforeTest
	public void setDriver(String browser) throws MalformedURLException {

		System.out.println("Test is running on " + browser);

		if (browser.equals("firefox")) {
			capabilities = new FirefoxOptions();
		} else if (browser.equals("chrome")) {
			capabilities = new ChromeOptions();
		} else if (browser.equals("edge")) {
			capabilities = new EdgeOptions();
		}

		driver.set(new RemoteWebDriver(new URL(remote_url), capabilities));
		driver.get().get("https://opensource-demo.orangehrmlive.com/");
		driver.get().manage().window().maximize();
		driver.get().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver.get(), Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='divUsername']/span")));
	}

	public WebDriver getDriver() {
		return driver.get();
	}

	@AfterMethod
	public void closeBrowser() {
		driver.get().quit();
		driver.remove();
	}
}
