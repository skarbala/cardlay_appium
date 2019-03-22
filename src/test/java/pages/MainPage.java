package pages;

import static context.AppiumDriverSingleton.getDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import models.Expense;

public class MainPage {

  public MainPage logInWithFakeUser() {
    selectFakeUser();
    new WebDriverWait(getDriver(), 120)
        .until(ExpectedConditions.visibilityOfElementLocated(By.id("btnShowHideBilledExpenses")));
    return this;
  }

  private void selectFakeUser() {
    getDriver().findElement(By.id("android:id/text1")).click();
    getDriver().findElement(By.xpath("//android.widget.ListView/android.widget.CheckedTextView[2]")).click();
  }

  public MainPage switchToExpenses() {
    MobileElement carPager = getDriver().findElement(By.id("expenses_card_pager"));

    TouchAction touchAction = new TouchAction(getDriver());
    int carPagerY = carPager.getCenter().y;
    int carPagerX = carPager.getCenter().x;

    swipeCard(touchAction, carPagerY, carPagerX);
    return this;
  }

  private void swipeCard(TouchAction touchAction, int carPagerY, int carPagerX) {
    touchAction.press(PointOption.point(carPagerX, carPagerY))
        .moveTo(PointOption.point(carPagerX - 520, carPagerY))
        .perform()
        .release();
  }

  public void clickAddPrivateExpense() {
    new WebDriverWait(getDriver(), 10)
        .until(ExpectedConditions.visibilityOfElementLocated(By.id("btnAddPrivateExpense")));
    getDriver().findElement(By.id("btnAddPrivateExpense")).click();
  }

  public void waitUntilExpenseAppears(Expense expense) {
    new WebDriverWait(getDriver(), 120)
        .until(ExpectedConditions.visibilityOfElementLocated(
            By.id("btnShowHideBilledExpenses")));
    switchToExpenses();
    new WebDriverWait(getDriver(), 10)
        .until(ExpectedConditions
            .visibilityOfElementLocated(By.xpath(
                "//*[contains(@text,'" + expense.getPrice() + "')]")));
  }
}
