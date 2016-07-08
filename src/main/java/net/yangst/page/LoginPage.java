package net.yangst.page;

import net.yst.utils.Browser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by yangshutao on 2016/7/8.
 */
public class LoginPage  extends BasePage{

    @FindBy(id ="username")
    private WebElement  usernameInput;


    @FindBy(id = "password")
    private WebElement passwdInput;


    @FindBy(css="#loginForm button")
    private WebElement loginButton;

    public LoginPage(WebDriver driver) {
        super(driver);
    }


    public void login(String username, String passwd) {
        usernameInput.clear();

        usernameInput.sendKeys(username);

        passwdInput.clear();

        passwdInput.sendKeys(passwd);

        loginButton.click();


        Browser.waitElementInVisibleByElement(loginButton,driver);
        System.out.println("登录成功");

    }

    public static void main(String[] args) {

        String username = "admin";

        String passwd = "1";

        WebDriver driver = Browser.GetDriver(Browser.IE);
        driver.get("http://172.18.100.131:8080/");
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        loginPage.login(username, passwd);

    }

}
