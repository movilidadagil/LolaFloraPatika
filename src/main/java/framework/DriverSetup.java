package framework;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class DriverSetup {

    static Map<String, Object> data ;

    static WebDriver driver;

    public static WebDriver initialize_Driver(String browser){
        data = ConfigReader.initialize_Properties();

        //Chrome options for register-login pages
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-blink-features=AutomationControlled"); //removes the detection
        options.addArguments("--disable-notifications");
        options.setExperimentalOption("excludeSwitches", "disable-popup-blocking");
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"}); //removes the banner
        //properties = ConfigReader.getProperties();
        if (browser.equals("Chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver(options);
        } else if (browser.equals("Firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver(options);
        }
        String url = String.valueOf(data.get("url"));
        int impWait = Integer.parseInt(String.valueOf(data.get("implicityWait")));
        int pageWait = Integer.parseInt(String.valueOf(data.get("pageLoadTimeout")));
        driver.get(url);
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(impWait, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(pageWait, TimeUnit.SECONDS);
        return getDriver();
    }

    public static WebDriver getDriver(){
        return driver;
    }


}