import genericlistener.GenericListener;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageobjects.HomePageListenerTraining;

import java.net.MalformedURLException;
import java.net.URL;

@Slf4j
@Listeners({GenericListener.class})
public class FavoritesTestListenerTraining {
    HomePageListenerTraining homePage;
    String savedWishTitle = "";



    @Test(description = "add wish")
    public void checkAddToWishListSuccessfully(){
        homePage = new HomePageListenerTraining();
        savedWishTitle = homePage.addItemToWishList();
    }

    @Test(priority = 2)
    public void checkWishList(){
        homePage = new HomePageListenerTraining();
        homePage.checkWishInFavorList(savedWishTitle);
    }
}
