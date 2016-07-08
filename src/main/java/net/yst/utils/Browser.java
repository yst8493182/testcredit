package net.yst.utils;


import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.*;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Browser {

    public static final String IE= "IE";
    public static final String CHROME= "chrome";
    public Browser() {
        // TODO Auto-generated constructor stub
    }

    /**
     * 获取driver
     *
     * @return
     * @author
     */
    public static WebDriver GetDriver() {

        WebDriver driver;
        String path = System.getProperty("user.dir");
        System.out.println(path);
        path = path + "/src/main/resources/chromedriver.exe";
        System.out.println(path);

        System.setProperty("webdriver.chrome.driver", path);
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        return driver;
    }


    public static WebDriver GetDriver(String browser) {
        WebDriver driver;
        String path = System.getProperty("user.dir");

        if (browser.equals("IE")) {
            path = path + "/src/main/resources/IEDriverServer.exe";
            System.out.println(path);

            DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
            ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);

            System.setProperty("webdriver.ie.driver", path);

            driver = new InternetExplorerDriver(ieCapabilities);
        } else {
            path = path + "/src/main/resources/chromedriver.exe";
            System.out.println(path);
            System.setProperty("webdriver.chrome.driver", path);
            ChromeOptions options = new ChromeOptions();
            driver = new ChromeDriver(options);
        }
        driver.manage().window().maximize();
        return driver;
    }


    /**
     * 等待元素可被点击,并返回该元素
     *
     * @param by
     * @param driver
     * @return 调用示例Browser.waitForPageLoad(By.cssSelector("#lst-ib"), driver);
     */
    public static void waitForPageLoad(final By by, WebDriver driver) {

        (new WebDriverWait(driver, 30)).until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver d) {
                //return d.getTitle().toLowerCase().startsWith("selenium");
                try {
                    return d.findElement(by) != null;
                } catch (Exception e) {
                    return false;
                }
            }
        });

    }

    /**
     * 等待页面元素加载完毕
     *
     * @param driver
     * @author Taiwei.An
     */
    public static void waitForPageLoad(WebDriver driver) {
        new WebDriverWait(driver, 30).until(new ExpectedCondition<Boolean>() {

            @Override
            public Boolean apply(WebDriver driver) {
                String js = "return document.readyState";
                JavascriptExecutor jse = (JavascriptExecutor) driver;
                return jse.executeScript(js).toString().equals("complete");
            }

        });
    }


    /**
     * 等待元素可被点击,并返回该元素
     *
     * @param by
     * @param driver
     * @return
     */

    public static WebElement waitForElement(final By by, WebDriver driver) {

//		WebElement myDynamicElement = (new WebDriverWait(driver, 10))
//				  .until(ExpectedConditions.presenceOfElementLocated(By.id("myDynamicElement")));
        return (new WebDriverWait(driver, 30))
                .until(ExpectedConditions.elementToBeClickable(by));

    }

    /**
     * 等待元素可被点击,并返回该元素
     *
     * @param webElement
     * @param driver
     * @return
     * @author shutao.yang
     * @version 创建时间：2015年10月12日 上午11:25:12
     */
    public static WebElement waitForElement(final WebElement webElement, WebDriver driver) {

        return waitForElement(webElement, driver, 30, 500);

    }


    /**
     * 等待元素可被点击,并返回该元素
     *
     * @param webElement
     * @param driver
     * @param timeout
     * @param sleeptime
     * @return
     */
    public static WebElement waitForElement(final WebElement webElement, WebDriver driver, long timeout, long sleeptime) {

        return (new WebDriverWait(driver, timeout, sleeptime))
                .until(ExpectedConditions.elementToBeClickable(webElement));

    }


    /**
     * 等待指定的元素中出现指定个文本
     *
     * @param driver
     * @param element 指定的元素
     * @param text    指定包含的文本
     * @return
     * @throws InterruptedException
     */
    public static boolean textToVisibleInElement(WebDriver driver, final WebElement element, final String text) throws InterruptedException {

        for (int i = 0; i < 60; i++) {
            WebElement webElement = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOf(element));

            if (webElement.getText().contains(text)) {
                return true;
            }
            Thread.sleep(1000);
        }
        return false;
    }



    public static boolean textToVisibleInElement(WebDriver driver, final By by, final String text) throws InterruptedException {

        for (int i = 0; i < 60; i++) {

            WebElement webElement = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(by));

            if (webElement.getText().contains(text)) {
                return true;
            }
            Thread.sleep(1000);
        }
        return false;
    }


    /**
     * 等待元素可被选择,并返回该元素
     *
     * @param webElement
     * @param driver
     * @return
     * @author Taiwei.Sn
     * @version 创建时间：2015年10月12日 上午11:24:36
     */
    public static Boolean waitForElementSelect(final WebElement webElement, WebDriver driver) {
        return waitForElementSelect(webElement, driver, 30, 500);

    }

    /**
     * 等待元素可被选择,并返回该元素
     *
     * @param webElement
     * @param driver
     * @param timeouts
     * @param sleepTimes
     * @return
     */
    public static Boolean waitForElementSelect(final WebElement webElement, WebDriver driver, long timeouts, long sleepTimes) {
        WebDriverWait wait = new WebDriverWait(driver, timeouts, sleepTimes);
        return wait.until(ExpectedConditions.elementToBeSelected(webElement));

    }

    /**
     * 等待元素可见,并返回该元素
     *
     * @param webElement
     * @param driver
     * @return
     */
    public static WebElement waitForVisibleElement(final WebElement webElement, WebDriver driver) {

        return waitForVisibleElement(webElement, driver, 30, 2000);
    }


    public static WebElement waitForSelectElement(final WebElement element, WebDriver driver) {
        return new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(element));
    }


    /**
     * 等待元素可见,并返回该元素
     *
     * @param webElement
     * @param driver
     * @param timeOut
     * @param sleepTime
     * @return
     */
    public static WebElement waitForVisibleElement(final WebElement webElement, WebDriver driver, long timeOut, long sleepTime) {

        return (new WebDriverWait(driver, timeOut, sleepTime))
                .until(ExpectedConditions.visibilityOf(webElement));
    }

    /**
     * 等待元素集合都可见
     *
     * @param webElementList
     * @param driver
     * @param timeOut
     * @param sleepTimes
     * @return
     */
    public static List<WebElement> waitForVisibleElementList(final List<WebElement> webElementList, WebDriver driver, long timeOut, long sleepTimes) {

        return (new WebDriverWait(driver, timeOut, sleepTimes))
                .until(ExpectedConditions.visibilityOfAllElements(webElementList));
    }

    /**
     * 等待元素集合都可见
     *
     * @param webElementList
     * @param driver
     * @return
     */
    public static List<WebElement> waitForVisibleElementList(final List<WebElement> webElementList, WebDriver driver) {

        return waitForVisibleElementList(webElementList, driver, 30, 500);
    }

    /**
     * 等待元素集合可见,并返回该元素集合
     *
     * @param by
     * @param driver
     * @return
     */
    public static List<WebElement> waitForVisibleElementList(final By by, WebDriver driver, long timeOut) {

        return (new WebDriverWait(driver, timeOut))
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
    }

    /**
     * @param by
     * @param driver
     * @param timeOut
     * @param sleeptime
     * @return
     */
    public static List<WebElement> waitForVisibleElementList(final By by, WebDriver driver, long timeOut, long sleeptime) {

        return (new WebDriverWait(driver, timeOut, sleeptime))
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
    }


    /**
     * 等待元素可见,并返回该元素
     *
     * @param by
     * @param driver
     * @return
     */
    public static WebElement waitForVisibleElement(final By by, WebDriver driver) {

        return (new WebDriverWait(driver, 30))
                .until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public static WebElement waitForVisibleElement(final By by, WebDriver driver, long timeout, long sleeptime) {

        return (new WebDriverWait(driver, timeout, sleeptime))
                .until(ExpectedConditions.visibilityOfElementLocated(by));
    }


    /**
     * @param by
     * @param text
     * @param driver
     * @return
     */
    public static Boolean waitForPresentInElement(final By by, String text, WebDriver driver) {
        return (new WebDriverWait(driver, 2)).until(ExpectedConditions.textToBePresentInElementLocated(by, text));
    }


    public static WebElement waitElementPresentIn(final By by, WebDriver driver) {
        return (new WebDriverWait(driver, 5)).until(ExpectedConditions.presenceOfElementLocated(by));
    }


    /**
     * 等待元素不可见
     *
     * @param by
     * @param driver
     * @return 元素不可见时，返回true
     * @throws InterruptedException
     */
    public static Boolean waitForInVisibleElement(final By by, WebDriver driver) {

        return (new WebDriverWait(driver, 30))
                .until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    /**
     * 等待元素不可见
     *
     * @param by
     * @param driver
     * @return
     */
    public static Boolean waitForInVisibleElement(final By by, WebDriver driver, long timeOutSeconds) {

        return (new WebDriverWait(driver, timeOutSeconds))
                .until(ExpectedConditions.invisibilityOfElementLocated(by));
    }


    /**
     * 判断元素是否存在
     */

    public static Boolean IsElementPresent(final WebElement element, WebDriver driver) {
        try {
            if (element.isDisplayed()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }

    }

    /**
     * 根据指定的文本等待元素不可见
     *
     * @param by
     * @param driver
     * @param text
     * @return 元素不可见时，返回true
     */
    public static boolean waitForInVisibleElement(final By by, String text, WebDriver driver) {

        return (new WebDriverWait(driver, 30))
                .until(ExpectedConditions.invisibilityOfElementWithText(by, text));
    }


    /**
     * 根据By来等待元素是否显示或存在
     *
     * @param by
     * @param driver
     */
    public static void waitElementInVisibleByLocal(final By by, WebDriver driver) {
        new WebDriverWait(driver, 30, 500).until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver input) {
                //设置findElement的超时时间
                input.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
                try {
                    if (input.findElement(by).isDisplayed()) {
                        return false;
                    } else {
                        return true;
                    }
                } catch (Exception e) {
                    return true;
                }
            }
        });
    }


    /**
     * 等待元素可见
     * @param driver
     * @param by
     * @param timeout
     * @return
     */
    public static WebElement visibilityOfElementLocated(WebDriver driver, By by, long timeout) {
       return new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOfElementLocated(by));
    }


    /**
     * 等待元素不可见
     *
     * @param element
     * @param driver
     */
    public static void waitElementInVisibleByElement(final WebElement element, WebDriver driver) {
        new WebDriverWait(driver, 30, 500).until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver input) {
                //设置findElement的超时时间
                input.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
                try {
                    if (element.isDisplayed()) {
                        return false;
                    } else {
                        return true;
                    }
                } catch (Exception e) {
                    return true;
                }
            }
        });
    }


    /**
     * 等待Alert显示
     *
     * @param driver
     * @return 显示后返回Alert
     * @version 创建时间：2015年10月13日 上午11:08:10
     */
    public static Alert waitPresentForAlert(WebDriver driver) {
        return (new WebDriverWait(driver, 300))
                .until(ExpectedConditions.alertIsPresent());
    }

    /**
     * 等待Alert显示。手动设置超时时间
     *
     * @param driver
     * @param timeoutInSeconds
     * @return
     */
    public static Alert waitPresentForAlert(WebDriver driver, long timeoutInSeconds) {
        return (new WebDriverWait(driver, timeoutInSeconds))
                .until(ExpectedConditions.alertIsPresent());
    }

    /**
     * 等待元素可被选中
     *
     * @param driver
     * @param element
     * @param timeOutInSeconds
     * @return
     * @author shutao.yang
     * @version 创建时间：2015年10月16日 上午11:38:50
     */
    public static boolean waiForBeSelect(WebDriver driver, WebElement element, long timeOutInSeconds) {
        return new WebDriverWait(driver, timeOutInSeconds).until(ExpectedConditions.elementToBeSelected(element));
    }

    /**
     * 等待给定的文本是否存在于指定元素中
     *
     * @param driver
     * @param element
     * @param text
     * @param timeOutInSeconds
     * @return liuli
     */
    public static boolean waitForTextToBePresentInElement(WebDriver driver, WebElement element, String text, long timeOutInSeconds) {
        return new WebDriverWait(driver, timeOutInSeconds).until(ExpectedConditions.textToBePresentInElement(element, text));
    }



    /**
     * 已废弃，不要使用
     * 判断WebElement是否显示
     *
     * @param by
     * @param driver
     * @return
     */
    @Deprecated
    public static boolean isfound(By by, WebDriver driver) {
        try {
            driver.findElement(by);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public static boolean elementIsVisiable(final By by, WebDriver driver) {
        return (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver input) {
                return input.findElement(by).isDisplayed();
            }
        });
    }


    public static boolean waitElementAttachedDocument(WebElement element, long timeOutInSeconds, WebDriver driver) {
        return new WebDriverWait(driver, timeOutInSeconds).until(ExpectedConditions.stalenessOf(element));
    }


    /**
     * 使用jQuery查找元素
     *
     * @param driver
     * @param js
     * @return 返回执行js的对象集合
     */
    @SuppressWarnings("unchecked")
    public static List<WebElement> getElementsByjQuery(WebDriver driver, String js) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        return (List<WebElement>) jse.executeScript(js);
    }

    /**
     * 使用js执行元素的action操作
     *
     * @param driver
     * @param actionScript 对元素执行的操作
     * @param element
     * @throws Exception
     */
    public static void elementActionByJs(WebDriver driver, String actionScript, WebElement element) throws Exception {
        if (element == null) {
            throw new Exception("元素为空");
        }
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0]." + actionScript, element);
    }


    /**
     * 根据title，查询并返回指定的windowId
     *
     * @param driver
     * @param title
     * @return
     */
    public static String getWindowIdByTitle(WebDriver driver, String title) throws InterruptedException {
        String windowId = null;
        Thread.sleep(1000);
        Set<String> handles = driver.getWindowHandles();
        System.out.println(handles.size());
        System.out.println(handles);
        for (String handle :handles) {
            if (driver.switchTo().window(handle).getTitle().contains(title)) {
                windowId = handle;
                //              System.out.println("====="+windowId+"============");
                break;
            }
        }
        if (windowId != null) {
            return windowId;
        } else {
            try {
//                driver.quit();
                throw new Exception("没有找到指定的window窗口");
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return null;
            }
        }
    }


    /**
     * 根据url，查询并返回指定的windowId
     *
     * @param driver
     * @param url
     * @return
     */
    public static String getWindowIdByUrl(WebDriver driver, String url) {
        String windowId = null;
        Set<String> handles = driver.getWindowHandles();
        System.out.println(handles.size());
        System.out.println(handles);
        for (String handle : handles) {
            String url1 = driver.switchTo().window(handle).getCurrentUrl();
            System.out.println(url1);
            if (url1.contains(url)) {
                windowId = handle;
                //              System.out.println("====="+windowId+"============");
                break;
            }
        }
        if (windowId != null) {
            return windowId;
        } else {
            try {
//                driver.quit();
                throw new Exception("没有找到指定的window窗口");
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return null;
            }
        }
    }


    /**
     * 将Exception.printStackTrace()转换为String输出
     * @param e
     * @return
     *liuli
     */
    public static String getErrorInfoFromException(Exception e) {
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            return "\r\n" + sw.toString() + "\r\n";
        } catch (Exception e2) {
            return "bad getErrorInfoFromException";
        }
    }

    public static void main(String[] args) {
//        WebDriver driver = Browser.GetDriver();
//        CtaLogin.ctaLogin(driver);
//        String url = GlobalConfig.getCtaUrl("trademanage/incomeTransferMatch");
//        driver.get(url);
//
//        WebElement lendNoShow = incomeTransferMatchPage.lendNoShowT;
//        boolean res = Browser.waitForTextToBePresentInElement(driver, lendNoShow, "F45301982", 10);
//        System.out.println(res);
//        lendNoShow.click();

        WebDriver driver = Browser.GetDriver(Browser.IE);
        driver.get("http://172.18.100.87:8080/jenkins");

    }
}
