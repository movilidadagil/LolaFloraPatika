import framework.ConfigReader;
import framework.DriverSetup;
import genericlistener.GenericListener;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageobjects.HomePage;

@Slf4j
@Listeners({GenericListener.class})
public class FavoritesTest {
    static WebDriver driver;
    HomePage homePage;
    String savedWishTitle = "";

    @BeforeClass
    public void setup(){
        driver = DriverSetup.initialize_Driver(ConfigReader.initialize_Properties().get("browser").toString());
        homePage = new HomePage(driver);
    }

    @Test(description = "add wish")
    public void checkAddToWishListSuccessfully(){
        savedWishTitle = homePage.addItemToWishList();
    }

    @Test(priority = 2)
    public void checkWishList(){
        String titleOfWishes = homePage.checkWishInFavorList();
        Assert.assertEquals(savedWishTitle,titleOfWishes);
    }
}
