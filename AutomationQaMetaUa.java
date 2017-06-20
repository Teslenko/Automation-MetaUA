/**
 * Created by vt on 16.06.17.
 */


import com.codeborne.selenide.Configuration;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.openqa.selenium.By;
import sun.awt.image.ImageWatched;

import java.awt.*;
import java.awt.event.KeyEvent;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.by;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byTitle;
import static com.codeborne.selenide.Selenide.*;

public class AutomationQaMetaUa {

    final String browserName = "chrome";
    final int timeOut = 5000;
    final String browserPropertyName = "webdriver.chrome.driver";
    final String browserPropertyPath = "./src/main/resources/chromedriver";

    @Test
    public void setup() throws InterruptedException, AWTException {

        System.setProperty("java.util.logging.SimpleFormatter.format", "%1$tT %4$s %5$s%6$s%n");// We have normal logo

        open("http://meta.ua");                             // Open page

       LoginFormVerification();

       ForgotPasswordRegister();

       SelectLanguageOnPage();

       SearchFieldVerification();

       SiteSettingVerification();

       SwitchNewsTab();

        TranslationField();

       SiteBarVerification();

       SwitchInternetTab();

    }

    @Test
    public void LoginFormVerification() throws InterruptedException {
        $(By.name("login")).val("TeslQA");                      Thread.sleep(1000); //Input in Login field
        $(By.name("lifetime")).shouldBe(checked);               Thread.sleep(1000); //Verification for checkbox
        $(By.name("password")).val("qwe123456").pressEnter();   Thread.sleep(2000); //Input Password and press Enter
        $(By.id("id_logout")).click();                          Thread.sleep(1500); //Click Logout button
    }

    @Test
    public void ForgotPasswordRegister() throws InterruptedException {
        $(by("tabindex", "103")).click();        Thread.sleep(1500); // Press to Forgot button
        $(By.id("meta_logo_img")).click();                           // Click to LogoImg, go to Homepage
        $(by("tabindex", "104")).click();        Thread.sleep(1500); // Click to Register button
        $$(".logo").get(0).click();                                  // Click to LogoImg, go to Homepage
    }

    @Test
    public void SelectLanguageOnPage() throws InterruptedException {
        $(byText("українська")).click();        Thread.sleep(1000); //Select Ukraine Language
        $(byText("русский")).click();           Thread.sleep(1000); //Select Russia Language
        $(by("tabindex", "5")).click();         Thread.sleep(1000); //Select English Language
    }

    @Test
    public void SearchFieldVerification() throws InterruptedException {
        $(By.name("q")).val("Autotesting").pressEnter();    Thread.sleep(1000); //Enter text im Search Field
        $$(".gs-title").shouldHave(size(46));               Thread.sleep(1000); //Verification quantity finder class
        $$(".gsc-cursor-page").get(1).scrollTo();           Thread.sleep(1000); //Observe page(Scrool to futer)
        $(by("id", "meta_logo_img")).click();               Thread.sleep(1000); //Click to LogoImg, go to Homepage
    }

    @Test
    public void SiteSettingVerification() throws InterruptedException {
        $(by("tabindex", "3")).shouldBe(text("Site settings")).click(); Thread.sleep(2000); //Verification Text in "SiteSetting" button
        $(By.id("l2")).click();                                         Thread.sleep(1000); //Click Russia Language
        $$(".y_button").get(0).shouldBe(value("Save")).click();         Thread.sleep(1000); //Click Save button
    }

    @Test
    public void SwitchNewsTab() throws InterruptedException {
        $(By.id("news_1text")).click();                     Thread.sleep(1000); //Click on Tab(General)
        $(By.id("news_1text")).click();                     Thread.sleep(1000); //Click on Tab(General)
        $(By.id("news_2text")).click();                     Thread.sleep(1000); //Click on Tab(Odessa)
        $(By.id("news_3text")).click();                     Thread.sleep(1000); //Click on Tab(Sport)
        $(by("href", "/?news=ALL")).click();                Thread.sleep(1000); //Click on Button(All sections)
        $(by("title", "Close")).click();                    Thread.sleep(1000); //Close popup
    }

    @Test
    public void SwitchInternetTab() throws InterruptedException, AWTException {
        $(by("id", "s-news")).click();                      Thread.sleep(1000); // Click News Tab
        $(by("id", "meta_logo_img")).click();               Thread.sleep(1000); // Click to LogoImg, go to Homepage
        $(by("id", "s-map")).click();                       Thread.sleep(1000); // Click Maps Tab
        $(by("id", "meta_logo_img")).click();               Thread.sleep(1000); // Click to LogoImg, go to Homepage
        $(by("id", "s-edu")).click();                       Thread.sleep(1000); // Click Referats Tab
        $(by("id", "meta_logo_img")).click();               Thread.sleep(1000); // Click to LogoImg, go to Homepage
        $(by("id", "s-market")).click();                    Thread.sleep(1000); // Click Shops Tab
        $(byText("Meta")).click();                          Thread.sleep(1000); // Click to LogoImg, go to Homepage

        Robot robot = new Robot();                                              //Announce Robot framework
        $(By.id("s-dir")).contextClick();                   Thread.sleep(1000);  //Press for Directory tab "right button mouse"
        robot.keyPress(KeyEvent.VK_ENTER);			        Thread.sleep(1700); //Click for "open a new tab"
        robot.keyPress(KeyEvent.VK_ESCAPE);                 Thread.sleep(1000); //Press Escape, To stabilize the test
        switchTo().window(1);                               Thread.sleep(1000); //Switch to new Tab
        switchTo().window(1).close();                       Thread.sleep(1000); //Close New Tab
    }

    @Test
    public void TranslationField() throws InterruptedException {
        $$("a#meta-translate-link").get(0).
                shouldHave(attribute("href", "http://translate.meta.ua/ru/"));          Thread.sleep(1000); //Checken Link Translation
        $(By.name("text_source")).val("Hello my friend, I hope that my tests will pass");   //Enter english text
        $(by("src", "http://translate.meta.ua/images/btn-translate-ru.png")).click();   Thread.sleep(1000); //Click - 'Translate'
        switchTo().window(1);                               Thread.sleep(1000);             //Switch to new Tab
        $(by("name", "text_source")).shouldHave(id(("form-source")));   Thread.sleep(1000); //Verification "Translation text" field
        switchTo().window(1).close();                       Thread.sleep(1000);             //Close New Tab
        open("http://meta.ua");                             // Open page
    }

    @Test
    public void SiteBarVerification() throws InterruptedException {
        $$(".a").shouldHave(size(13));                      Thread.sleep(500);  //Verification quantity SiteBar class
        $$(".a").get(1).click();                            Thread.sleep(500);  //Verification Site Bar
        $$(".a").get(2).click();                            Thread.sleep(500);
        $$(".a").get(3).click();                            Thread.sleep(500);
        $$(".a").get(4).click();                            Thread.sleep(500);
        $$(".a").get(4).shouldHave(attribute("title", "restore"));     Thread.sleep(500);   //Verification the title after click on Site Bar
        $$(".a").get(5).click();                            Thread.sleep(500);
        $$(".a").get(6).click();                            Thread.sleep(500);
        $$(".a").get(7).click();                            Thread.sleep(500);
        $$(".a").get(8).click();                            Thread.sleep(500);
        $$(".a").get(9).click();                            Thread.sleep(500);
        $$(".a").get(10).click();                           Thread.sleep(500);
        $$(".a").get(11).click();                           Thread.sleep(500);
        $$(".a").get(12).click();                           Thread.sleep(500);
    }

        @Rule
        public TestRule report = new com.codeborne.selenide.junit.TextReport(). //Test Logo in the form of a table
                onFailedTest(true);

        @Before
        public void before () {
            Configuration.browser = browserName;
            Configuration.timeout = timeOut;
            System.setProperty(browserPropertyName, browserPropertyPath);
        }
}
