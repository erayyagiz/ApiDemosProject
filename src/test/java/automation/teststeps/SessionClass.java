package automation.teststeps;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;


import java.net.URL;
import java.util.concurrent.TimeUnit;

public  class SessionClass {

    AppiumDriver<MobileElement> driver;

    @BeforeMethod
    public void setUp() {
        try {
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability("platformName", "Android");
            caps.setCapability("platformVersion", "10");
            caps.setCapability("deviceName", "ERAY");
            caps.setCapability("udid", "SYK0119812000026");
            caps.setCapability("appPackage", "io.appium.android.apis");
            caps.setCapability("appActivity", "io.appium.android.apis.ApiDemos");

            driver = new AppiumDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"), caps);
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterMethod
    public void tearDown() {
        try {
            driver.quit();
        } catch (Exception exp) {
            exp.printStackTrace();
        }
    }

    public AppiumDriver<MobileElement> getDriver() {
		return this.driver;
	}
}
