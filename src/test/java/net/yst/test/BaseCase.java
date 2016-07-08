package net.yst.test;

import net.yst.utils.Browser;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

/**
 * Created by yangshutao on 2016/7/8.
 */
public class BaseCase {

    public WebDriver driver;


    @BeforeMethod
    public void beforeMethod() {

        if (driver == null) {
            driver = Browser.GetDriver(Browser.IE);
        }

    }


    @AfterMethod
    public void afterMethod() {
        if (driver != null) {
            driver.quit();
        }
    }

}
