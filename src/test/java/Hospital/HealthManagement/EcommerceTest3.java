package Hospital.HealthManagement;

import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static java.time.Duration.ofSeconds;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;


public class EcommerceTest3 extends MyFirstClass {

	@Test(dataProvider="inputdata", dataProviderClass=TestData.class)
	
	public void TotalValidation(String input) throws InterruptedException, IOException
	{
       //appiumService.start();
		
    AndroidDriver<AndroidElement> driver=capabilities("GeneralStoreApp");
     driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
     
     driver.findElementById("com.androidsample.generalstore:id/nameField").sendKeys(input);
     driver.hideKeyboard();
     driver.findElementByXPath("//*[@text='Female']").click();
     driver.findElementById("com.androidsample.generalstore:id/spinnerCountry").click();
     driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"));");
     
    // driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView"+ "(new UiSelector().textMatches(\""+ Argentina +"\").instance(0))"));  
  
     
     driver.findElementByXPath("//*[@text='Argentina']").click();
     driver.findElementById("com.androidsample.generalstore:id/btnLetsShop").click();
     
     driver.findElementsByXPath("//*[@text='ADD TO CART']").get(0).click();
     driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"ADD TO CART\"));").click();
     //driver.findElementsByXPath("//*[@text='ADD TO CART']").get(0).click();
     
     driver.findElementById("com.androidsample.generalstore:id/appbar_btn_cart").click();
     driver.findElement(MobileBy.AndroidUIAutomator(
				"new UiScrollable(new UiSelector().resourceId(\"com.androidsample.generalstore:id/rvCartProductList\")).scrollIntoView("
				+ "new UiSelector().text(\"$120.0\"))"));
     
    Thread.sleep(4000);
    int count=driver.findElements(By.id("com.androidsample.generalstore:id/productPrice")).size();

    double sum=0;

    for(int i=0;i<count;i++)

    {

    String amount1= driver.findElements(By.id("com.androidsample.generalstore:id/productPrice")).get(i).getText();

    double amount=getAmount(amount1);

    sum=sum+amount;//280.97+116.97

    }

    System.out.println(sum+"sum of products");



    String total=driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl")).getText();



    total= total.substring(1);

    double totalValue=Double.parseDouble(total);

    System.out.println(totalValue+"Total value of products");

    Assert.assertEquals(sum, totalValue); 



    //Mobile Gestures

    WebElement checkbox=driver.findElement(By.className("android.widget.CheckBox"));

    TouchAction t=new TouchAction(driver);

    t.tap(tapOptions().withElement(element(checkbox))).perform();



    WebElement tc=driver.findElement(By.xpath("//*[@text='Please read our terms of conditions']"));

    t.longPress(longPressOptions().withElement(element(tc)).withDuration(ofSeconds(2))).release().perform();

    driver.findElement(By.id("android:id/button1")).click();

    driver.findElement(By.id("com.androidsample.generalstore:id/btnProceed")).click();
     
    //appiumService.stop();


}

public static double getAmount(String value)

{

value= value.substring(1);

double amount2value=Double.parseDouble(value);

return amount2value;

}
  
}
