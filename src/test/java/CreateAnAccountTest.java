import genericlistener.GenericListener;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageobjects.CreateAnAccount;

@Listeners(GenericListener.class)
public class CreateAnAccountTest {


    CreateAnAccount createAnAccount;
    @Test(priority = 0, description = "create an account")
    public void checkCreateAnAccount(){
        CreateAnAccount.navigateOrSignoutCreateAccountScreen();
        if(createAnAccount==null)
            createAnAccount = new CreateAnAccount();
        createAnAccount.createAccountProcess();
    }

    @Test(priority = 1, description = "create an account unsuccesfully")
    public void checkUnSuccessfullCreateAnAccount(){
        CreateAnAccount.navigateOrSignoutCreateAccountScreen();
        if(createAnAccount==null)
             createAnAccount = new CreateAnAccount();
        String error = createAnAccount.unsuccessfullCreation();
        Assert.assertEquals(error,"This email has been used. Please log in or type in another email");
        //createAnAccount.unsuccessfullCreationwithAssert();
    }

    @Test(priority = 1, description = "create an account unsuccesfully")
    public void checkUnSuccessfullCreateAnAccountByUsingSameFirtsNameLastName(){
        CreateAnAccount.navigateOrSignoutCreateAccountScreen();
        if(createAnAccount==null)
            createAnAccount = new CreateAnAccount();
        String error = createAnAccount.unsuccessfullCreation();
        Assert.assertEquals(error,"This email has been used. Please log in or type in another email");
        //createAnAccount.unsuccessfullCreationwithAssert();
    }
}
