package TestBase;

import java.net.MalformedURLException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

public class TestBaseForOtherTests {

	public WebDriver driver;
	ChromeOptions chromeOptions;

	
	@BeforeTest(alwaysRun = true)
	@Parameters({ "browser" })
	public void getDriver(String browser) throws MalformedURLException {

		try {
			System.out.println("Test is running on " + browser);
			if (browser.equals("firefox")) {
				System.setProperty("webdriver.gecko.driver",
						System.getProperty("user.dir") + "//src//test//resources//geckodriver");
				driver = new FirefoxDriver();
			} else if (browser.equals("chrome")) {
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "//src//test//resources//chromedriver");
				chromeOptions = new ChromeOptions();
				chromeOptions.addArguments("--remote-allow-origins=*");
				driver = new ChromeDriver(chromeOptions);
			} else if (browser.equals("edge")) {
				System.setProperty("webdriver.edge.driver",
						System.getProperty("user.dir") + "//src//test//resources//msedgedriver");
				driver = new EdgeDriver();
			}
			driver.manage().window().maximize();
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
			driver.get("https://www.saucedemo.com/");
		} catch (Exception e) {
			throw e;
		}
	}

	@AfterTest
	public void closeBrowser() {
		driver.quit();
	}
	
}
