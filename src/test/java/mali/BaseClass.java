package mali;

import framework.ConfigReader;
import framework.DriverSetup;
import org.openqa.selenium.WebDriver;

public class BaseClass {

    public WebDriver driver;
    public BaseClass(){
        driver =  DriverSetup.initialize_Driver(ConfigReader.initialize_Properties().get("browser").toString());
    }
}
