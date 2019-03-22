package pages;

import static context.AppiumDriverSingleton.getDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import enumerators.Currency;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.offset.PointOption;

public class NewExpenseDialog {

  @AndroidFindBy(id = "vatCountryDropDown")
  private MobileElement vatCountryDropDown;

  public NewExpenseDialog() {
    PageFactory.initElements(new AppiumFieldDecorator(getDriver()), this);
  }

  public void addNewExpenseData(
      String comment,
      String merchant,
      String place,
      Currency currency,
      String price,
      String category,
      String client
  ) {
    setComment(comment);
    setMerchant(merchant);
    setPlace(place);
    selectCurrency(currency);
    setPrice(price);
    selectVat();
    selectCategory(category);
    setClient(client);
  }

  private void setComment(String comment) {
    scrollUntilVisible(By.id("etComment")).setValue(comment);
  }

  private void setMerchant(String merchant) {
    scrollUntilVisible(By.id("etMerchant")).setValue(merchant);
  }

  private void setPlace(String place) {
    scrollUntilVisible(By.id("etPlace")).setValue(place);
  }

  private void setPrice(String price) {
    scrollUntilVisible(By.id("etAmount")).setValue(price);
  }

  private void setClient(String client) {
    scrollUntilVisible(By.id("etClient")).setValue(client);
  }

  private void selectCurrency(Currency currency) {
    scrollUntilVisible(By.id("etItem")).click();
    getDriver()
        .findElementByXPath("//android.widget.TextView[@text='" + currency.getUiValue() + "']")
        .click();
  }

  private void selectCategory(String category) {
    scrollUntilVisible(By.id("categoryDropDown")).click();
    getDriver().findElement(By.xpath("//android.widget.TextView[@text='" + category + "']")).click();
  }

  private void selectVat() {
    scrollUntilVisible(By.id("vatCountryDropDown")).click();
    getDriver().findElement(By.xpath("//android.widget.TextView[@text='United States']")).click();
  }

  private MobileElement scrollUntilVisible(By locator) {
    int screenHeight = getDriver().manage().window().getSize().height;
    int numberOfElements = getDriver().findElements(locator).size();
    while (numberOfElements == 0) {
      scrollByOffsetY(screenHeight);
      numberOfElements = getDriver().findElements(locator).size();
    }
    int buttonSaveWrapperHeight = getButtonSaveWrapperHeight();

    while (getDriver().findElement(locator).getLocation().y > screenHeight - buttonSaveWrapperHeight) {
      scrollByOffsetY(screenHeight);
    }

    return getDriver().findElement(locator);
  }

  private void scrollByOffsetY(int screenHeight) {
    new TouchAction(getDriver())
        .press(PointOption.point(1, screenHeight / 2))
        .moveTo(PointOption.point(1, (screenHeight / 2) - 20))
        .release()
        .perform();
  }

  public void clickSaveButton() {
    getDriver().findElement(By.id("btnSave")).click();
  }

  private int getButtonSaveWrapperHeight() {
    return getDriver().findElement(By.id("btnSave")).getRect().getHeight() + 200;
  }
}
