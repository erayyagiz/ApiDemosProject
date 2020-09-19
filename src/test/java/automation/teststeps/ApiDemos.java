package automation.teststeps;

import com.github.kilianB.hash.Hash;

import com.github.kilianB.hashAlgorithms.HashingAlgorithm;
import com.github.kilianB.hashAlgorithms.PerceptiveHash;

import io.appium.java_client.TouchAction;

import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

import org.apache.commons.io.FileUtils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.testng.Assert;
import org.testng.annotations.Test;


import java.io.File;
import java.io.IOException;

import static java.time.Duration.ofMillis;



public class ApiDemos extends SessionClass {

    @Test
    public void runTest1() {
        getDriver().findElementByAccessibilityId("Text").click();
        getDriver().findElementByAccessibilityId("LogTextBox").click();
        getDriver().findElementByAccessibilityId("Add").click();
        String text = getDriver().findElementById("io.appium.android.apis:id/text").getText();
        if (text.equals("This is a test\n")) {
            System.out.println("Text is correct!");
        } else {
            Assert.fail("Text is not correct!");
        }
    }

    @Test
    public void runTest2() {
        getDriver().findElementByAccessibilityId("Preference").click();
        getDriver().findElementByAccessibilityId("3. Preference dependencies").click();
        getDriver().findElementById("android:id/checkbox").click();
        getDriver().findElementByXPath("//android.widget.TextView[@text='WiFi settings']").click();
        getDriver().findElementById("android:id/edit").sendKeys("Eray Wifi");
        getDriver().findElementById("android:id/button1").click();
    }

    public void screenshot(String filename) throws IOException {
        File srcFile = getDriver().getScreenshotAs(OutputType.FILE);
        File targetFile = new File(filename + ".png");
        FileUtils.copyFile(srcFile, targetFile);
    }

    @Test
    public void runTest3() throws IOException, InterruptedException {
        getDriver().findElementByAccessibilityId("Animation").click();
        getDriver().findElementByAccessibilityId("Multiple Properties").click();
        getDriver().findElementByAccessibilityId("Run").click();
        Thread.sleep(5000);
        screenshot("actual");

        File img0 = new File("actual.png");
        File img1 = new File("expected.png");

        HashingAlgorithm hasher = new PerceptiveHash(32);

        Hash hash0 = hasher.hash(img0);
        Hash hash1 = hasher.hash(img1);

        double similarityScore = hash0.normalizedHammingDistance(hash1);

        if(similarityScore <= .06) {
            System.out.println("One of the balls is below the screen'");
        }else {
            Assert.fail("One of the balls is not below the screen");
        }
    }

    @Test
    public void runTest4() throws InterruptedException {
        getDriver().findElementByAccessibilityId("App").click();
        getDriver().findElementByAccessibilityId("Notification").click();
        getDriver().findElementByAccessibilityId("NotifyWithText").click();
        getDriver().findElementById("io.appium.android.apis:id/short_notify").click();
        String text = getDriver().findElementByXPath("//*[@text='Short notification']").getText();
        if (text.equals("Short notification")) {
            System.out.println("Text is correct!");
        } else {
            Assert.fail("Text is not correct!");
        }
        Thread.sleep(2000);
        getDriver().findElementById("io.appium.android.apis:id/long_notify").click();
        String text1 = getDriver().findElementByXPath("//*[contains(@text,'This is a long notification')]").getText();
        if (text1.equals("This is a long notification. See, you might need a second more to read it.")) {
            System.out.println("Text is correct!");
        } else {
            Assert.fail("Text is not correct!");
        }
    }

    @Test
    public void runTest5() {
        getDriver().findElementByAccessibilityId("Media").click();
        getDriver().findElementByAccessibilityId("AudioFx").click();
        getDriver().findElementByXPath("(//android.widget.SeekBar)[1]").sendKeys("10000");
        Point dimension = getDriver().findElementByXPath("(//android.widget.SeekBar)[1]").getLocation();
        int startX, endX, startY, endY;
        startX = (int) (dimension.getX() * 0.5);
        endX = (int) (dimension.getX() * 0.9);
        startY = (int) (dimension.getY() * 1.041);
        endY = (int) (dimension.getY() * 1.041);

        TouchAction action = new TouchAction(getDriver())
                .press(PointOption.point(startX, startY))
                .waitAction(WaitOptions.waitOptions(ofMillis(1500)))
                .moveTo(PointOption.point(endX, endY))
                .release()
                .perform();
    }

    @Test
    public void runTest6() {
        getDriver().findElementByAccessibilityId("OS").click();
        getDriver().findElementByAccessibilityId("SMS Messaging").click();
        getDriver().findElementById("io.appium.android.apis:id/sms_recipient").sendKeys("Telekom");
        getDriver().findElementById("io.appium.android.apis:id/sms_content").sendKeys("Houston, we have a problem.");
        getDriver().findElementById("io.appium.android.apis:id/sms_send_message").click();
        String control = getDriver().findElementByAccessibilityId("SMS Messaging").getAttribute("text");
        if (control != null) {
            Assert.fail("CRASH");
        }
    }
}
