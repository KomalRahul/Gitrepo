package Hospital.HealthManagement;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;



public class MyFirstClass {
	
public static AndroidDriver<AndroidElement> driver;
	/*AppiumDriverLocalService appiumService;
    String appiumUrl;
    
    @BeforeTest
    public void setUpAndStartServer() throws MalformedURLException{
                  
appiumService = AppiumDriverLocalService.buildDefaultService();
                   appiumService.start();
                   appiumUrl = appiumService.getUrl().toString();
System.out.println("Appium Service URL Address : - "+ appiumUrl);

    }*/
	
	public static void startEmulator() throws IOException, InterruptedException
	{
		
	Runtime.getRuntime().exec(System.getProperty("user.dir")+"\\src\\main\\java\\Resources\\StartEmulator.bat");
	Thread.sleep(6000);
	}

	public static AndroidDriver<AndroidElement> capabilities(String appname) throws IOException, InterruptedException
	
	{

FileInputStream fis= new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\Hospital\\HealthManagement\\global.properties");
	   Properties prop = new Properties();
	   prop.load(fis);
	   
		
		File f = new File("src");
	    File fs = new File(f,(String) prop.get(appname));
	
	
	
	DesiredCapabilities DC = new DesiredCapabilities();
	//String device = (String) prop.get("device");
	String device = System.getProperty("deviceName");
	
	if(device.contains("Emulator"))
	{
		startEmulator();
	}
	
	DC.setCapability(MobileCapabilityType.DEVICE_NAME, device);
	DC.setCapability(MobileCapabilityType.AUTOMATION_NAME,"UiAutomator2");
	DC.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 14);// to wait app before it closes
	
	DC.setCapability(MobileCapabilityType.APP, fs.getAbsolutePath());
	
	 driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"),DC);
	
	return driver;
	
	}
	
	 /*@AfterTest
     public void End() {
                    System.out.println("Stop driver");
                    System.out.println("Stop appium service");
                    appiumService.stop();
	
	

}*/
	public static  void getscreenshot(String s) throws IOException
	{
		File srcfile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		FileUtils.copyFile(srcfile,new File(System.getProperty("user.dir")+"\\"+s+".png"));
	}
}

