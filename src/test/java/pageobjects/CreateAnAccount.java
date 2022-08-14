package pageobjects;

import framework.Helper;
import model.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import static framework.DriverSetup.driver;

public class CreateAnAccount {

    public static String passwordStr;
    public static String firstname, lastname, emailuser;
    By namaAndSurname = By.id("loginInputName");
    By email = By.id("loginInputEmail");
    By password = By.id("loginInputPassword");
    By confirmPass  = By.id("loginInputPasswordConfirm");
    By createBtn = By.xpath("//button[text()='CREATE']");
    By creationError = By.xpath("//div[contains(text(),'This email')]");

    Helper elementHelper;
    WebElement nameAndSurnameEL;
    WebElement emailEl;
    WebElement passwordEl;
    WebElement confirmPassEl;
    WebElement createBtnEl;

    static HomePageListenerTraining homePageListenerTraining ;
    static User user;

    public CreateAnAccount(){

        if(user==null)
            user =  new User();

        this.elementHelper=new Helper(driver);


    }
    public void createAccountProcess(){
        this.createBtnEl = this.elementHelper.findElement(createBtn);
        typeNameAndSurname();
        typeEmail();
        typePassword();
        typeConfirmPass();
        createBtnEl.click();
        navigateOrSignoutCreateAccountScreen();

    }

    public String  unsuccessfullCreation(){
        this.createBtnEl = this.elementHelper.findElement(createBtn);
        typeNameAndSurname();
        typeEmail();
        typePassword();
        typeConfirmPass();
        createBtnEl.click();
        return this.elementHelper.findElement(creationError).getText();
    }

    public void  unsuccessfullCreationwithAssert(){
        this.createBtnEl = this.elementHelper.findElement(createBtn);
        typeNameAndSurname();
        typeEmail();
        typePassword();
        typeConfirmPass();
        createBtnEl.click();
        String error = this.elementHelper.findElement(creationError).getText();
        Assert.assertEquals(error,"This email has been used. Please log in or type in another email");
    }

    public void typeNameAndSurname(){
        this.nameAndSurnameEL = this.elementHelper.findElement(namaAndSurname);
        firstname = user.getFirstName();
        lastname = user.getLastName();
        this.elementHelper.typeForInput(firstname+" "+lastname,nameAndSurnameEL);
    }

    public void typeEmail(){
        this.emailEl = this.elementHelper.findElement(email);
        emailuser = user.getEmail();
        this.elementHelper.typeForInput(emailuser,emailEl);
    }

    public void typePassword(){
        this.passwordEl = this.elementHelper.findElement(password);
        passwordStr = user.getPassword();
        this.elementHelper.typeForInput(passwordStr,passwordEl);
    }

    public void typeConfirmPass(){
        this.confirmPassEl = this.elementHelper.findElement(confirmPass);
        this.elementHelper.typeForInput(passwordStr,confirmPassEl);
    }

    public static void navigateOrSignoutCreateAccountScreen(){
      homePageListenerTraining  = new HomePageListenerTraining();
      homePageListenerTraining.registerNaviSignoutMethod();
    }



    public static User getUser(){
        return user;
    }
}
