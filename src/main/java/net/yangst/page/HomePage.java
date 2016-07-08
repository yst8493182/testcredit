package net.yangst.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by yangshutao on 2016/7/8.
 */
public class HomePage extends BasePage{


    @FindBy(id = "logOutEl")
    private WebElement logoutButton;

    public HomePage(WebDriver driver) {
        super(driver);
    }



}
