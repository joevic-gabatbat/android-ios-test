package testAndroid;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import utils.ReadDataFromExcel;

public class TestAndroidTabletAppium extends BaseClassAndroidTablet {

	String excelPath = "./TestData/TestData.xlsx";
	String sheetName = "TestData - Android";

	@Test(priority = 0)
	public void wifiSettings() {
		ReadDataFromExcel excel = new ReadDataFromExcel();
		String wifiTitle = excel.getDataFromExcel(excelPath, sheetName, 1, "WifiTitle");
		String wifiName = excel.getDataFromExcel(excelPath, sheetName, 1, "WifiName");
		driver.findElement(AppiumBy.accessibilityId("Preference")).click();
		driver.findElement(AppiumBy.accessibilityId("3. Preference dependencies")).click();
		driver.findElement(By.id("android:id/checkbox")).click();
		driver.findElement(By.xpath(("(//android.widget.RelativeLayout)[2]"))).click();
		String alertTitle = driver.findElement(By.id("android:id/alertTitle")).getText();
		Assert.assertEquals(alertTitle, wifiTitle);
		driver.findElement(By.id("android:id/edit")).sendKeys(wifiName);
		List<WebElement> buttons = driver.findElements(By.xpath("//android.widget.Button"));
		System.out.println("Button count: " + buttons.size());
		driver.findElements(AppiumBy.className("android.widget.Button")).get(1).click();
	}

}
