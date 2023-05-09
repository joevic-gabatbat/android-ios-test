package testAndroid;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

import org.json.simple.parser.ParseException;
import org.junit.AfterClass;
import org.testng.annotations.BeforeClass;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.MobileCapabilityType;
import utils.ReadJSONFile;

public class BaseClassAndroidPhone {

	static AndroidDriver driver;
	String jsonFilePath = "src/test/java/utils/ConfigAndroidPhone.json";
	ReadJSONFile rj = new ReadJSONFile();

	@BeforeClass
	public void configureAppLaunch() throws FileNotFoundException, IOException, ParseException {
		String deviceName = rj.getDeviceName(jsonFilePath);
		String platformVersion = rj.getPlatformVersion(jsonFilePath);
		String automationName = rj.getAutomationName(jsonFilePath);
		String platformName = rj.getPlatformName(jsonFilePath);
		File fil = new File("src/test/java/utils/");
		File fs = new File(fil, "ApiDemos-debug.apk");
		UiAutomator2Options cap = new UiAutomator2Options();
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
		cap.setCapability(MobileCapabilityType.APP, fs.getAbsolutePath());
		cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, platformVersion);
		cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, automationName);
		cap.setCapability(MobileCapabilityType.PLATFORM_NAME, platformName);
		cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 3600);

		try {
			driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), cap);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@AfterClass
	public void closeTest() {
		driver.quit();
	}
}
