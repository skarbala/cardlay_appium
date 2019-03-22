package context;

import static context.AppiumDriverSingleton.getDriver;

import org.junit.After;
import org.junit.Before;

public class AndroidTestBase {

  @Before
  public void setUp() {
    getDriver();
  }

  @After
  public void tearDown() {
    getDriver().quit();
  }
}
