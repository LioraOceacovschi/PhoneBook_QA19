package tests;

import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest extends TestBase{

    @BeforeMethod(alwaysRun = true)
    public void precondition(){
        if(app.getUser().isLogged()){
            app.getUser().logout();
        }
    }

    @Test(groups = {"smokeGroup","sanityGroup","regressionGroup"})
    public void loginPositiveTest(){
        User user = User.builder()
                .email("lso@abc.com")
                .password("$Abcd1234")
                .build();
        logger.info("LOGIN TEST STARTS WITH DATA: " + user.getEmail() + " & " + user.getPassword());

        app.getUser().openLoginRegistrationForm();
        app.getUser().fillLoginRegistrationForm(user);
       app.getUser().submitLogin();
        Assert.assertTrue(app.getUser().isLogged());
    }

    @Test
    public void loginNegativeTestWrongEmail() {
        User user = User.builder()
                .email("lsoabc.com")
                .password("$Abcd1234")
                .build();
        logger.info("LOGIN TEST STARTS WITH DATA: " + user.getEmail() + " & " + user.getPassword());
        app.getUser().openLoginRegistrationForm();
        app.getUser().fillLoginRegistrationForm(user);
        app.getUser().submitLogin();
        Assert.assertTrue(app.getUser().isCorrectWrongLoginAlertText());
        Assert.assertTrue(app.getUser().isAlertPresent());
        Assert.assertFalse(app.getUser().isLogged());
    }

    @Test
    public void loginNegativeTestWrongPassword() {
        User user = User.builder()
                .email("lso@abc.com")
                .password("Abcd1234")
                .build();
        logger.info("LOGIN TEST STARTS WITH DATA: " + user.getEmail() + " & " + user.getPassword());
        app.getUser().openLoginRegistrationForm();
        app.getUser().fillLoginRegistrationForm(user);
        app.getUser().submitLogin();
        Assert.assertTrue(app.getUser().isCorrectWrongLoginAlertText());
        Assert.assertTrue(app.getUser().isAlertPresent());
        Assert.assertFalse(app.getUser().isLogged());

    }



    @AfterMethod(alwaysRun = true)
    public void postCondition() {

    }

}