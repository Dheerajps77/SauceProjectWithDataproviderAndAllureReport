package Utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import TestBase.TestBaseForOtherTests;
import io.qameta.allure.Attachment;

public class TestExecutionListener extends TestBaseForOtherTests implements ITestListener {

	//TestBaseForOtherTests baseForOtherTests=new TestBaseForOtherTests();
	@Attachment(value = "Screenshot of {0}", type = "image/png")
	public byte[] saveScreenshot(String name, WebDriver driver) {
		return (byte[]) ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	}

	@Override
	public void onTestFailure(ITestResult result) {
		saveScreenshot(result.getName(), driver);
	}

	/*
	 * @Attachment(value = "screenshot", type = "png") public static String
	 * ScreenshotCapture(WebDriver driver, String screenshotName) { String
	 * screenshotPath = ""; try { Date date = (Date)
	 * Calendar.getInstance().getTime(); DateFormat dateFormat = new
	 * SimpleDateFormat("yyyy-MM-dd' 'HH mm ss"); String strDate =
	 * dateFormat.format(date); screenshotPath = System.getProperty("user.dir") +
	 * "/Screenshots/" + screenshotName + strDate + ".png";
	 * 
	 * TakesScreenshot ts = (TakesScreenshot) driver; File imageCapture =
	 * ts.getScreenshotAs(OutputType.FILE); File imageDestination = new
	 * File(screenshotPath); FileUtils.copyFile(imageCapture, imageDestination); }
	 * catch (Exception e) { e.printStackTrace(); } return screenshotPath; }
	 * 
	 * // Text attachments for Allure
	 * 
	 * @Attachment(value = "Page screenshot", type = "image/png") public static
	 * byte[] saveScreenshotPNG (WebDriver driver) { return
	 * ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
	 */
}
