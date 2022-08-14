package pageobjects;

import framework.Helper;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;

import static framework.DriverSetup.driver;


public class HomePageListenerTraining {


    Helper elementHelper;
    By profileIcon = By.cssSelector("button[data-tooltip='My account']");
    By registerSignoutNavi = By.xpath("//div[@class='tt-dropdown-inner']/ul/li[3]/a");
    By searchInput = By.id("search-input");
    By searchIcon = By.cssSelector("button[type=submit]");
    By wishListIcon = By.cssSelector("a[data-tooltip=\"Add to Wishlist\"]");
    By imageBox = By.cssSelector(".tt-image-box");
    By favorites = By.cssSelector("div[data-tooltip=\"Favorites\"]");
    By titleOfWish = By.xpath("//*/h2[@class='tt-title']");
    By titleOfResWish = By.xpath("//*/h2[@class='tt-title']");


    public HomePageListenerTraining()
    {
        this.elementHelper=new Helper(driver);
    }

    public String addItemToWishList(){
        this.elementHelper.findElement(searchInput).click();
        this.elementHelper.typeForInput("rose",this.elementHelper.findElement(searchInput));
        this.elementHelper.findElement(searchIcon).click();
        this.elementHelper.hoverMover(this.elementHelper.findElement(imageBox));
        this.elementHelper.findElements(wishListIcon).get(0).click();
        return this.elementHelper.findElements(titleOfResWish).get(0).getText();
    }

    public void checkWishInFavorList(String expectedWish){
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("window.scrollBy(0,-250)");
        this.elementHelper.findElement(favorites).click();
        Assert.assertEquals(expectedWish,this.elementHelper.findElement(titleOfWish).getText());

    }
    public void registerNaviSignoutMethod(){
        this.elementHelper.findElement(profileIcon).click();
        this.elementHelper.findElement(registerSignoutNavi).click();
    }


}
