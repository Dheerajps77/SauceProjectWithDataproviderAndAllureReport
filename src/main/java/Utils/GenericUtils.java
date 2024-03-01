package Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.json.Json;


public class GenericUtils {

	public static void createFileUsingFileOutputStreamClass(String filenameWithPath, String data) throws IOException {
		FileOutputStream out = new FileOutputStream(filenameWithPath);
		out.write(data.getBytes());
		out.close();
	}

	
	
	public int getTotalNumbersOfCharacters(String str)
	{
		int lenghtOfChar;
		try {
			lenghtOfChar=str.length();
			
		} catch (Exception e) {
			throw e;
		}
		return lenghtOfChar;
	}
	
	public int convertStringToInt(String str)
	{
		int value;
		try {
			
			value=Integer.parseInt(str);
		} catch (Exception e) {
			throw e;
		}
		return value;
	}
	public static String getCurrentDateTimeMS(){
		Date now = new Date();
		SimpleDateFormat formatedNow = new SimpleDateFormat("yyMMddhhmmssMs");
		String uniqueValue = formatedNow.format(now);
		return uniqueValue;
	}

	public static String getCurrentDate(){
		Date now = new Date();
		SimpleDateFormat formatedNow = new SimpleDateFormat("MM-dd-YYYY");
		String uniqueValue = formatedNow.format(now);
		return uniqueValue;
	}

	public static String getCurrentTime(){
		Date now = new Date();
		SimpleDateFormat formatedNow = new SimpleDateFormat("hh:mma");
		String uniqueValue = formatedNow.format(now);
		uniqueValue = StringUtils.stripStart(uniqueValue,"0");
		return uniqueValue.toLowerCase();
	}

	public static String getCurrentTimeMinusSecond(){
		Timestamp timestamp = new Timestamp(new Date().getTime());
		SimpleDateFormat formatedNow = new SimpleDateFormat("hh:mma");

		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(timestamp.getTime());

		cal.add(Calendar.SECOND, -60);
		timestamp = new Timestamp(cal.getTime().getTime());

		String uniqueValue = formatedNow.format(timestamp);
		uniqueValue = StringUtils.stripStart(uniqueValue,"0");
		return uniqueValue.toLowerCase();
	}

	public void cleanUpFolder(String directoryPath) throws IOException {

		File file = new File(directoryPath);
		if (!file.exists()) {
			file.mkdir();
		}
		FileUtils.cleanDirectory(file); 
	}


	public HashMap<String,String> getUserDetailsFromJSON(String path, String user) throws Exception{
		try{
			HashMap<String, String> hm=new HashMap<String, String>();
			JSONParser parser = new JSONParser();
			Reader reader = new FileReader(path);
			JSONObject jsonObject = (JSONObject) parser.parse(reader);
			JSONObject jsonObject1 = (JSONObject) parser.parse(jsonObject.get(user).toString());
			String userName=jsonObject1.get("UserName").toString();
			String password=jsonObject1.get("Password").toString();
			hm.put("username", userName);
			hm.put("password", password);
			return hm;
		}catch(Exception e){
			throw e;
		}
	}

	// Below method will return the column name in the arraylist
	public ArrayList<String> TableColumnNameValues(WebDriver driver, By locator) throws Exception {

		ArrayList<String> arrayList = null;
		try {
			arrayList = new ArrayList<String>();
			List<WebElement> element = driver.findElements(locator);
			int totalNumberOfColumn = element.size();
			for (int i = 1; i <= totalNumberOfColumn; i++) {
				WebElement elementOfColumnText = driver.findElement(
						By.xpath("//div[@class='ui-table-wrapper ng-star-inserted']//table//thead//tr//th[" + i + "]"));
				String stringValueOfElement = elementOfColumnText.getText();
				arrayList.add(stringValueOfElement);
			}
		} catch (Exception e) {
			throw e;
		}
		return arrayList;
	}

	// Below method will return the column name in the arraylist
	public ArrayList<String> getColumnHeaderNames(WebDriver driver, By locator) throws Exception {

		ArrayList<String> arrayList = null;
		try {
			arrayList = new ArrayList<String>();
			List<WebElement> element = driver.findElements(locator);
			int totalNumberOfColumn = element.size();
			for (int i = 1; i <= totalNumberOfColumn; i++) {
				WebElement elementOfColumnText = driver.findElement(
						By.xpath("//div[@class='ui-table-wrapper ng-star-inserted']//table//thead//tr//th[" + i + "]"));
				String stringValueOfElement = elementOfColumnText.getText();
				arrayList.add(stringValueOfElement);
			}
		} catch (Exception e) {
			throw e;
		}
		return arrayList;
	}

	public static String RandomString(int length)
	{
		String stringValue="ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvxyz";
		StringBuffer stringbuffer = null;
		try {

			stringbuffer=new StringBuffer(10);

			for(int i=0;i<length;i++)
			{
				int index=(int)(stringValue.length()*Math.random());
				stringbuffer.append(stringValue.charAt(index));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return stringbuffer.toString();
	}
}




