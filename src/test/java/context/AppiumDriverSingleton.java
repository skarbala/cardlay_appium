package context;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class AppiumDriverSingleton {
  private static AndroidDriver<MobileElement> driver;


  public static AndroidDriver<MobileElement> getDriver() {
    if (driver == null) {
      initializeDriver();
    }
    return driver;
  }

  private static void initializeDriver() {
    DesiredCapabilities caps = new DesiredCapabilities();
    caps.setCapability("deviceName", "MI");
    caps.setCapability("udid", "9bc5d6aa0704"); //Give Device ID of your mobile phone
    caps.setCapability("platformName", "Android");
    caps.setCapability("platformVersion", "9.0");
    caps.setCapability("appPackage", "com.seb.mobile.stage_intern_test");
    caps.setCapability("appActivity", "com.seb.mobile.view.login.RequestOtpActivity");
    caps.setCapability("noReset", "true");
    caps.setCapability("unicodeKeyboard", true);
    caps.setCapability("resetKeyboard", true);
    try {
      driver =
          new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"), caps);
    } catch (MalformedURLException ignored) {

    }

  }
}
