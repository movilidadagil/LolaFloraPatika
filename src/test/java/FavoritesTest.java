import framework.ConfigReader;
import framework.DriverSetup;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageobjects.HomePage;

public class FavoritesTest {
    static WebDriver driver;
    HomePage homePage;
    String savedWishTitle = "";
    @BeforeClass
    public void setup(){
        driver = DriverSetup.initialize_Driver(ConfigReader.initialize_Properties().get("browser").toString());
        homePage = new HomePage(driver);
    }

    @Test(priority = 1)
    public void checkAddToWishListSuccessfully(){
        savedWishTitle = homePage.addItemToWishList();
    }

    @Test(priority = 2)
    public void checkWishList(){
        homePage.checkWishInFavorList(savedWishTitle);
    }
}
