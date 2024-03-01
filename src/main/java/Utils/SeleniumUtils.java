package Utils;

import java.awt.AWTException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class SeleniumUtils {

	public static PropertiesManager properties = PropertiesManager.getInstance();	

	public static void turnOffImplicitWaits(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
	}

	public static void turnOnImplicitWaits(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	}
	
	public static final WebElement waitForElementVisibility(WebDriver driver, By findByCondition, int waitInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, waitInSeconds);
		wait.until(ExpectedConditions.visibilityOfElementLocated(findByCondition));
		return driver.findElement(findByCondition);
	}

	public static final WebElement waitForElementPresence(WebDriver driver, By findByCondition, int waitInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, waitInSeconds);
		wait.until(ExpectedConditions.presenceOfElementLocated(findByCondition));
		return driver.findElement(findByCondition);
	}

	public static final WebElement waitForElementClickable(WebDriver driver, By findByCondition, int waitInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, waitInSeconds);
		wait.until(ExpectedConditions.elementToBeClickable(findByCondition));
		return driver.findElement(findByCondition);
	}

	public static int countWindow(WebDriver driver) {
		Set<String> windowHandle = driver.getWindowHandles();
		int count = windowHandle.size();
		return count;
	}

	public static void switchWindowByIndex(WebDriver driver, int windowno) {
		Set<String> window = driver.getWindowHandles();
		String switchToWindow=window.toArray()[windowno].toString();
		driver.switchTo().window(switchToWindow);
		// or at below
		//driver.switchTo().window(window.toArray()[windowno].toString());
	}

	public static void closeCurrentBrowserTab(WebDriver driver) {
		driver.close();
	}

	public static String getCurrentPageTitle(WebDriver driver) {
		return driver.getTitle();
	}

	public static String fetchUserDetails(String key) {

		FileInputStream file = null;
		try {
			file = new FileInputStream(System.getProperty("user.dir") + properties.getConfig("CONFIG_PROPERTIES_PATH"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Properties property = new Properties();
		try {
			property.load(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return property.getProperty(key);
	}

	public static long getLoadTimeInSeconds(WebDriver driver, By waitForLoadElement, int waitInSeconds) {
		StopWatch pageLoad = new StopWatch();
		pageLoad.start();
		SeleniumUtils.waitForElementVisibility(driver, waitForLoadElement, waitInSeconds);
		pageLoad.stop();
		long pageLoadTime_ms = pageLoad.getTime();
		long pageLoadTime_Seconds = pageLoadTime_ms / 1000;
		return pageLoadTime_Seconds;
	}

	// To get inner text value using javaScriptExecutor
	public static String toGetTextValue(WebDriver driver, WebElement element) {
		String textValue = "";
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			textValue = (String) js.executeScript("return arguments[0].text;", element);

		} catch (Exception e) {
			throw e;
		}
		return textValue;
	}

	// Enter text using javascript Executor
	public static void enterTextUsingJavaScriptExecutor(WebDriver driver, WebElement element, String value) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].value='value';", element);

		}
		catch (Exception e) {
			throw e;
		}
	}

	// Highlight an Element
	public static void highlightAnElement(WebDriver driver, WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].style.border='3px dotted blue'", element);
	}

	// Scroll-Down Until an Element Displayed
	public static WebElement scrollToViewElement(WebDriver driver, By findByCondition) {
		WebElement element = driver.findElement(findByCondition);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		return driver.findElement(findByCondition);
	}

	// To click on any element using javaScriptExecutor
	public static WebElement clickOnElementUsingJSE(WebDriver driver, By findByCondition) {
		WebElement element = driver.findElement(findByCondition);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
		return driver.findElement(findByCondition);
	}

	public static void scrollUp(WebDriver driver) throws InterruptedException, AWTException {
		Thread.sleep(2000);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,-250)", "");
	}

	public static void scrollToBottom(WebDriver driver) throws InterruptedException, AWTException {
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(2000);
	}

	public static boolean isElementPresent(WebDriver driver, By locator) {
		turnOffImplicitWaits(driver);
		boolean result = false;
		try {
			driver.findElement(locator).isDisplayed();
			result = true;
		} catch (Exception e) {
			turnOnImplicitWaits(driver);
		} finally {
			turnOnImplicitWaits(driver);
		}
		return result;
	}
	
	
	public static void switchToFrame(WebDriver driver, WebElement element)
	{
		try {
			
			driver.switchTo().frame(element);
			
			/*
			 * List<WebElement> frames=driver.findElements(By.tagName("iframe")); int
			 * count=frames.size();
			 * 
			 * for(int i=1;i<count;i++) { driver.switchTo().frame(index); }
			 */
		} catch (Exception e) {
			throw e;
		}
	}

	public static void hoverElement(WebDriver driver, By locator) {
		Actions action = new Actions(driver);
		WebElement we = driver.findElement(locator);
		action.moveToElement(we).build().perform();
	}

	public static void clickElement(WebDriver driver, By locator) {
		Actions actions = new Actions(driver);
		WebElement we = driver.findElement(locator);
		actions.moveToElement(we);
		actions.click();
		actions.build().perform();
	}

	public static void enterData(WebDriver driver, By locator, int waitInSeconds, String data) {
		WebDriverWait wait = new WebDriverWait(driver, waitInSeconds);
		wait.until(ExpectedConditions.elementToBeClickable(locator));
		Actions actions = new Actions(driver);
		actions.moveToElement(driver.findElement(locator));
		actions.click();
		actions.sendKeys(driver.findElement(locator), data).build().perform();
		// actions.build().perform();
	}

	public static final void clickAndEnterText(WebDriver driver, By findByCondition, int waitInSeconds, String text)
			throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, waitInSeconds);
		wait.until(ExpectedConditions.elementToBeClickable(findByCondition));
		Actions actions = new Actions(driver);
		WebElement we = driver.findElement(findByCondition);
		actions.moveToElement(we);
		// actions.click();
		Thread.sleep(1000);
		we.clear();
		Thread.sleep(1000);
		actions.sendKeys(driver.findElement(findByCondition), (text)).build().perform();
		// actions.build().perform();
	}

	public static int getCountOfWebElements(WebDriver driver, By locator) {
		List<WebElement> we = driver.findElements(locator);
		int count = we.size();
		return count;
	}

	public static String getScreenshot(WebDriver driver, String screenshotName) throws Exception {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File theDir = new File(".\\Execution Reports\\FailedTestsScreenshots");
		if (!theDir.exists()) {
			theDir.mkdir();
		}
		String destination = System.getProperty("user.dir") + properties.getConfig("FAILEDTEST_SCREENSHOTS_PATH") + "/"
				+ screenshotName + ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}

	public static void actionScrollToBottom(WebDriver driver) {
		Actions actions = new Actions(driver);
		for (int i = 1; i <= 100; i++) {
			actions.sendKeys(Keys.PAGE_DOWN).perform();
		}
	}

	public static void actionScrollToTop(WebDriver driver) {
		Actions actions = new Actions(driver);
		for (int i = 1; i <= 100; i++) {
			actions.sendKeys(Keys.PAGE_UP).perform();
		}
	}

	public String getTitleText(WebDriver driver, By locator) throws Exception {
		String titleText = "";
		WebElement element;
		try {
			element = driver.findElement(locator);
			titleText = element.getText();
		} catch (Exception e) {
			throw e;
		}
		return titleText;
	}
	
	public String getTextOfElement(WebDriver driver, WebElement element) throws Exception {
		String titleText = "";		
		try {
			titleText = element.getText();
		} catch (Exception e) {
			throw e;
		}
		return titleText;
	}
	
	public String getAttributeValue(WebDriver driver, WebElement element, String attributeValue)
	{
		String textOfAttributeValue="";
		try {
			textOfAttributeValue=element.getAttribute(attributeValue);
		} catch (Exception e) {
			throw e;
		}
		return textOfAttributeValue;
	}
	

	public boolean isElementDisplayed(WebDriver driver, By locator) throws Exception {
		WebElement element;
		boolean flag = false;
		try {
			element = driver.findElement(locator);

			if (element.isDisplayed()) {
				flag = true;
			}
		} catch (Exception e) {
			throw e;
		}
		return flag;
	}

	public static void selectDropDownByValue(WebDriver driver, By locator, String value) {
		Select dropdown = new Select(driver.findElement(locator));
		dropdown.selectByValue(value);
	}

	public static void refreshPage(WebDriver driver) {
		driver.navigate().refresh();
	}

	public static void click(WebElement element, String message) throws Exception {
		try {
			element.click();
			//TestBase.logInfo(message);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public static void handleAlertPopUpWindow(WebDriver driver)
	{
		Alert alert;
		try {
			alert=driver.switchTo().alert();
			alert.dismiss();
		} catch (Exception e) {
			throw e;
		}
	}

	public static ArrayList getfileNamesFromFolder(String path) {
		ArrayList listOfFilesArray = new ArrayList();
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				listOfFilesArray.add(listOfFiles[i].getName());
			} else if (listOfFiles[i].isDirectory()) {
				System.out.println("Directory " + listOfFiles[i].getName());
			}
		}
		return listOfFilesArray;
	}

	public boolean waitTillPageURLToBeLoad(WebDriver driver, String fraction, int waitInSeconds) throws Exception {
		boolean flag;
		try {

			WebDriverWait wait = new WebDriverWait(driver, waitInSeconds);
			flag = wait.until(ExpectedConditions.urlContains(fraction)).booleanValue();
			if (flag) {
				flag = true;
			}

		} catch (Exception e) {
			throw e;
		}
		return flag;
	}

	public String getCurrentPageURL(WebDriver driver) throws Exception {
		String currentPageURL = "";
		try {
			currentPageURL = driver.getCurrentUrl();
		} catch (Exception e) {
			throw e;
		}
		return currentPageURL;
	}

	public static void Click(WebElement element, String message) throws Exception {
		try {
			element.click();
			//TestBase.logInfo(message);
		} catch (Exception e) {
			throw e;
		}
	}	

	public static void waitAndClick(WebDriver driver, WebElement iwebElement, int time) throws InterruptedException {
		for (int i = 0; i <= time; i++) {
			Thread.sleep(500);
			if (iwebElement.isDisplayed() && iwebElement.isEnabled()) {
				iwebElement.click();
				break;
			}
		}
	}

	// This will get a cell details of sepcific column from Table in
	// pm-Configuration page
	public ArrayList<String> getSepcificColumnCellDetailsFromTable(WebDriver driver) throws Exception {
		ArrayList<String> arrayList;
		List<WebElement> tableCellText;
		try {
			arrayList = new ArrayList<String>();
			tableCellText = driver
					.findElements(By.xpath("//div[@class='ui-table-wrapper ng-star-inserted']//tbody//tr//td[3]"));
			int count = tableCellText.size();
			for (int i = 1; i <= count; i++) {
				WebElement element = driver.findElement(
						By.xpath("//div[@class='ui-table-wrapper ng-star-inserted']//tbody//tr[" + i + "]//td[3]"));
				String textValue = element.getText();
				arrayList.add(textValue);
			}

		} catch (Exception e) {
			throw e;
		}
		return arrayList;
	}

	// This function will drag your slider to some Extent
	public void SliderDragToSomeExtent(WebDriver driver, int xAxis, int yAxis, WebElement element) {
		Actions actions = null;
		try {

			actions = new Actions(driver);
			actions.dragAndDropBy(element, xAxis, yAxis).build().perform();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void enterTextInTextBox(WebDriver driver, WebElement element, int waitInSeconds, String textValue)
	{
		try {
			WebDriverWait wait = new WebDriverWait(driver, waitInSeconds);
			wait.until(ExpectedConditions.visibilityOf(element));
			element.sendKeys(textValue);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public static void escMethod(WebDriver driver)
	{
		Actions action;
		try {
			action=new Actions(driver);
			action.sendKeys(Keys.ESCAPE).build().perform();
		} catch (Exception e) {
			throw e;
		}
	}

	public List<String> getAllSelectedOptionFromDropDown(WebElement element) {
		List<WebElement> list = null;
		ArrayList<String> textList = new ArrayList<String>();
		int getSize = 0;
		String textValue;
		try {
			Select select = new Select(element);
			list = select.getOptions();
			getSize = list.size();

			for (int i = 1; i <= getSize; i++) {
				textValue = list.get(i).getText();
				textList.add(textValue);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return textList;
	}
	
	/*
	public static void checkIfElementBecomeDecay(WebDriver driver, WebElement element)
	{
		WebDriverWait wait=new WebDriverWait(driver, 50);
		boolean flag=false;
		boolean f=false;
		try {
			
			f=wait.until(ExpectedConditions.not(ExpectedConditions.stalenessOf(element)));
			
			if(!f)
			{
				driver.findElement(By.tagName("img"));
				flag=true;
			}
			
		} catch (Exception e) {
			throw e;
		}		
	} 
	*/
	
	public static WebElement staleElementHandle(WebDriver driver, WebElement element, By locator)
	{
		try {
			// Check visibility. If reference is not stale, it will return the same referenced. Otherwise it will go to catch.
			element.isDisplayed();
			return element;
			
			// Relocate element in catch and return
		}catch(StaleElementReferenceException e)
		{
			return driver.findElement(locator);
			
			
		}catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
		
	}
	
	public static boolean validatePhoneNumber(String phoneNo) {

		// validate phone numbers of format "1234567890"
		if (phoneNo.matches("\\d{10}")) {
			return true;
		}
		// validating phone number with -, . or spaces
		else if (phoneNo.matches("\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}")) {
			return true;
		}

		// validating phone number with extension length from 3 to 5
		else if (phoneNo.matches("\\d{3}-\\d{3}-\\d{4}\\s(x|(ext))\\d{3,5}")) {
			return true;
		}
		// validating phone number where area code is in braces ()
		else if (phoneNo.matches("\\(\\d{3}\\)-\\d{3}-\\d{4}")) {
			return true;
		}
		// return false if nothing matches the input
		else
			return false;
	}
}
