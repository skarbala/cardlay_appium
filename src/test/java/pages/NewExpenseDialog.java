package pages;

import org.openqa.selenium.By;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;

public class NewExpenseDialog {

  private AppiumDriver<MobileElement> driver;

  public NewExpenseDialog(AppiumDriver<MobileElement> driver) {
    this.driver = driver;
  }


  public void addNewExpenseData(String price) {
    int screenHeight = driver.manage().window().getSize().height;

    driver.findElement(By.id("com.seb.mobile.stage_intern_test:id/etComment"))
        .sendKeys("Expense_Dinner-Mestiansky pivovar");
    driver.findElement(By.id("com.seb.mobile.stage_intern_test:id/etMerchant"))
        .sendKeys("Test merchant automation");
    driver.findElement(By.id("com.seb.mobile.stage_intern_test:id/etPlace"))
        .sendKeys("Test place automation");

    scrollByOffsetY(screenHeight, 30);
    driver.findElement(By.id("com.seb.mobile.stage_intern_test:id/etItem")).click();
    driver
        .findElementByXPath("//android.widget.ListView/android.widget.FrameLayout[1]/android.widget.TextView")
        .click();

    driver.findElement(By.id("com.seb.mobile.stage_intern_test:id/etAmount"))
        .sendKeys(price);
    scrollByOffsetY(screenHeight, 40);

    driver.findElement(By.id("com.seb.mobile.stage_intern_test:id/vatCountryDropDown")).click();
    driver.findElement(By.xpath("//android.widget.TextView[@text='United States']")).click();
    driver.findElement(By.xpath("//android.widget.TextView[contains(@text,'0%, Various tax rates')]")).click();
    scrollByOffsetY(screenHeight, 40);

    driver.findElement(By.id("com.seb.mobile.stage_intern_test:id/categoryDropDown")).click();
    driver.findElement(By.xpath("//android.widget.TextView[@text='Food and beverage']")).click();
    driver.findElement(By.id("com.seb.mobile.stage_intern_test:id/etClient")).sendKeys("TestClient_automation");
  }

  private void scrollByOffsetY(int screenHeight, int offsetY) {
    new TouchAction(driver)
        .press(PointOption.point(50, screenHeight / 2))
        .moveTo(PointOption.point(50, (screenHeight / 2) - offsetY))
        .release()
        .perform();
  }
}
