package net.yst.test;

import net.yangst.page.LoginPage;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

/**
 * Created by yangshutao on 2016/7/8.
 */
public class TestLogin extends BaseCase{


    @Test
    public void testLogin(){
        String username = "admin";

        String passwd = "1";
        driver.get("http://172.18.100.131:8080/");

        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        loginPage.login(username, passwd);
    }

}
