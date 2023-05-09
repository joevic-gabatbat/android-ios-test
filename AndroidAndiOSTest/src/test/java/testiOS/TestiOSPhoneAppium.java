package testiOS;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import utils.ReadJSONFile;

public class TestiOSPhoneAppium {

	static IOSDriver driver;
	String jsonFilePath = "src/test/java/utils/ConfigiOSPhone.json";
	ReadJSONFile rj = new ReadJSONFile();

	@BeforeTest
	public void beforeClass() throws FileNotFoundException, IOException, ParseException {
		String deviceName = rj.getDeviceName(jsonFilePath);
		String platformVersion = rj.getPlatformVersion(jsonFilePath);
		String automationName = rj.getAutomationName(jsonFilePath);
		String udID = rj.getUDID(jsonFilePath);
		File fs = new File("src/test/java/utils/UIKitCatalog.app");
		XCUITestOptions cap = new XCUITestOptions();
		cap.setDeviceName(deviceName);
		cap.setApp(fs.getAbsolutePath());
		cap.setPlatformVersion(platformVersion);
		cap.setAutomationName(automationName);
		cap.setUdid(udID);
		driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), cap);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}

	@Test(priority = 1)
	public void touchandhold() throws MalformedURLException {
		driver.findElement(AppiumBy.accessibilityId("Steppers")).click();
		WebElement ele = driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeButton[`label=='Increment'`]"));
		Map<String, Object> params = new HashMap<>();
		params.put("element", ((RemoteWebElement) ele).getId());
		params.put("duration", 5);
		driver.executeScript("mobile:touchAndHold", params);
		System.out.println("Done touch and hold.");
	}
	
	@Test(priority = 2)
	public void scroll() throws MalformedURLException {
		driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeButton[`label=='UIKitCatalog'`]")).click();
		WebElement ele = driver.findElement(AppiumBy.accessibilityId("Web View"));
		Map<String, Object> params = new HashMap<>();
		params.put("element", ((RemoteWebElement) ele).getId());
		params.put("direction", "down");
		driver.executeScript("mobile:scroll", params);
		driver.findElement(AppiumBy.accessibilityId("Web View")).click();
		System.out.println("Done scroll.");
	}
	
	@Test(priority = 3)
	public void selectDatePickerValue() throws MalformedURLException {
		driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeButton[`label=='UIKitCatalog'`]")).click();
		driver.findElement(AppiumBy.accessibilityId("Picker View")).click();
		driver.findElement(AppiumBy.accessibilityId("Red color component value")).sendKeys("255");
		driver.findElement(AppiumBy.accessibilityId("Green color component value")).sendKeys("0");
		driver.findElement(AppiumBy.accessibilityId("Blue color component value")).sendKeys("0");
		System.out.println("Done select date picker value and change color to red.");
	}
	
	@Test(priority = 4)
	public void swipe() throws MalformedURLException {
		driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeButton[`label=='UIKitCatalog'`]")).click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement ele = driver.findElement(AppiumBy.accessibilityId("Buttons"));
		Map<String, Object> params = new HashMap<>();
		params.put("direction", "down");
		params.put("element", ((RemoteWebElement) ele).getId());
		js.executeScript("mobile: swipe", params);
		System.out.println("Done swipe to Buttons.");
	}
	
	@Test(priority = 5)
	public void clickButtons() throws MalformedURLException {
		driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeStaticText[`label == 'Button'`][1]")).click();
		driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeButton[`label == 'add'`]")).click();
		driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeButton[`label == 'More Info'`]")).click();
		driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeButton[`label == 'X Button'`]")).click();
		driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeStaticText[`label == 'Button'`][2]")).click();
		driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeButton[`label == 'Person'`]")).click();
		driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeStaticText[`label == 'Button'`][3]")).click();
		System.out.println("Clicked all buttons.");
	}
	
	@Test(priority = 6)
	public void dragFromToForDuration() throws MalformedURLException {
		driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeButton[`label=='UIKitCatalog'`]")).click();
		WebElement ele = driver.findElement(AppiumBy.accessibilityId("Buttons"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Map<String, Object> params = new HashMap<>();
		params.put("duration", 1.0);
		params.put("fromX", 100);
		params.put("fromY", 100);
		params.put("toX", 200);
		params.put("toY", 200);
		params.put("element", ((RemoteWebElement) ele).getId());
		js.executeScript("mobile: dragFromToForDuration", params);
		System.out.println("Done drag from to for duration.");
	}
}
