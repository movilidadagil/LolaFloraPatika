package pageobjects;

import framework.Helper;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class HomePage {

    WebDriver driver;
    Helper elementHelper;
    By searchInput = By.id("search-input");
    By searchIcon = By.cssSelector("button[type=submit]");
    By wishListIcon = By.cssSelector("a[data-tooltip=\"Add to Wishlist\"]");
    By imageBox = By.cssSelector(".tt-image-box");
    By favorites = By.cssSelector("div[data-tooltip=\"Favorites\"]");
    By titleOfWish = By.xpath("//*/h2[@class='tt-title']");
    By titleOfResWish = By.xpath("//*/h2[@class='tt-title']");
    public HomePage(WebDriver driver)
    {
        this.driver=driver;
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

}
