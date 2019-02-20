package tests;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import pages.NewExpenseDialog;

public class NewExpenseTest extends AndroidTestBase {

  @Test
  public void addNewExpense() {
    String price = "123";
    driver.findElement(By.id("android:id/text1")).click();
    driver.findElement(By.xpath("//android.widget.ListView/android.widget.CheckedTextView[2]")).click();
    new WebDriverWait(driver, 120)
        .until(ExpectedConditions.visibilityOfElementLocated(
            By.id("com.seb.mobile.stage_intern_test:id/btnShowHideBilledExpenses")));
    switchToExpenses();

    new WebDriverWait(driver, 10)
        .until(ExpectedConditions.visibilityOfElementLocated(By.id(
            "com.seb.mobile.stage_intern_test:id/btnAddPrivateExpense")));
    driver.findElement(By.id("com.seb.mobile.stage_intern_test:id/btnAddPrivateExpense")).click();
    NewExpenseDialog newExpenseDialog = new NewExpenseDialog(driver);
    newExpenseDialog.addNewExpenseData(price);
    driver.findElement(By.id("com.seb.mobile.stage_intern_test:id/btnSave")).click();
    new WebDriverWait(driver, 120)
        .until(ExpectedConditions.visibilityOfElementLocated(
            By.id("com.seb.mobile.stage_intern_test:id/btnShowHideBilledExpenses")));
    switchToExpenses();
    new WebDriverWait(driver, 10)
        .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@text,'" + price + "')]")));
  }

  private void switchToExpenses() {
    MobileElement carPager = driver.findElement(By.xpath("//*[@resource-id='com.seb.mobile" +
        ".stage_intern_test:id/expenses_card_pager']"));

    TouchAction touchAction = new TouchAction(driver);
    int carPagerY = carPager.getCenter().y;
    int carPagerX = carPager.getCenter().x;

    touchAction.press(PointOption.point(carPagerX, carPagerY))
        .moveTo(PointOption.point(carPagerX - 500, carPagerY))
        .perform()
        .release();
  }

  private void scrollByOffsetY(int screenHeight, int offsetY) {
    new TouchAction(driver)
        .press(PointOption.point(50, screenHeight / 2))
        .moveTo(PointOption.point(50, (screenHeight / 2) - offsetY))
        .release()
        .perform();


  }
}
