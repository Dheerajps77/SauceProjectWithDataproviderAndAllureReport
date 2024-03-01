package Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesManager {
	
	public static PropertiesManager envProperties=null;
	private Properties configProperties;
	private Properties constants;
	private Properties logMessages;
	private Properties pagesURL;
	
	public PropertiesManager() {
		configProperties = ConfigFile();	
		constants = ConstantsFile();
		logMessages = LogMessagesFile();
		pagesURL=PagesURL();
	}
	public Properties ConfigFile() {
		File file = new File(System.getProperty("user.dir") + "/Configuration Files/Config.properties");
		FileInputStream fileInput = null;
		Properties props = new Properties();
		try {
			fileInput = new FileInputStream(file);
			props.load(fileInput);
		} 
		catch (FileNotFoundException e) {			
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();			
		}
		return props;
	}
	
	public Properties ConstantsFile() {
		File file = new File(System.getProperty("user.dir") + "/Configuration Files/Constants.properties");
		FileInputStream fileInput = null;
		Properties props = new Properties();
		try {
			fileInput = new FileInputStream(file);
			props.load(fileInput);
		} 
		catch (FileNotFoundException e) {			
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();			
		}
		return props;
	}
	
	public Properties LogMessagesFile() {
		File file = new File(System.getProperty("user.dir") + "/Configuration Files/LogMessages.properties"); 
		FileInputStream fileInput = null;
		Properties props = new Properties();
		try {
			fileInput = new FileInputStream(file);
			props.load(fileInput);
		} 
		catch (FileNotFoundException e) {			
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();			
		}
		return props;
	}
	
	public Properties PagesURL() {
		File file = new File(System.getProperty("user.dir") + "/Configuration Files/URLs.properties"); 
		FileInputStream fileInput = null;
		Properties props = new Properties();
		try {
			fileInput = new FileInputStream(file);
			props.load(fileInput);
		} 
		catch (FileNotFoundException e) {			
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();			
		}
		return props;
	}
		
	public static PropertiesManager getInstance() {
		if (envProperties == null) {
			envProperties = new PropertiesManager();
		}
		return envProperties;
	}

	public String getConfig(String key) {
		return configProperties.getProperty(key); 
	}
	
	public String getConstant(String key) {
		return constants.getProperty(key);
	}
	
	public String getLogMessage(String key) {
		return logMessages.getProperty(key);
	}
	
	public String getPagesURL(String key) {
		return pagesURL.getProperty(key);
	}

}