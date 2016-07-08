package net.yangst.page;

import org.openqa.selenium.WebDriver;

/**
 * Created by yangshutao on 2016/7/8.
 */
public class BasePage {


    public WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }
}
