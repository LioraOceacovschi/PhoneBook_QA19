package tests;

import manager.ProviderData;
import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegistrationTest extends TestBase{
    @BeforeMethod(alwaysRun = true)
    public void precondition(){
        if(app.getUser().isLogged()){
            app.getUser().logout();
        }
    }
    @Test(groups = {"sanityGroup","regressionGroup"})
    public void registrationPositiveTest(){
        int i = (int)(System.currentTimeMillis()/1000)%3600;
        User user = User.builder()
                .email("abc_" + i + "@def.com")
                .password("$Abcdef12345")
                .build();
        logger.info("REGISTRATION TEST STARTS WITH DATA: " + user.getEmail() + " & " + user.getPassword());
        app.getUser().openLoginRegistrationForm();
        app.getUser().fillLoginRegistrationForm(user);
        app.getUser().submitRegistration();
        Assert.assertTrue(app.getUser().isLogged());
    }

//    @Test(dataProvider = "regData_CSV",dataProviderClass = ProviderData.class)
//    public void registrationPositiveTest(User user){
//        logger.info("REGISTRATION TEST STARTS WITH DATA: " + user.getEmail() + " & " + user.getPassword());
//        app.getUser().openLoginRegistrationForm();
//        app.getUser().fillLoginRegistrationForm(user);
//        app.getUser().submitRegistration();
//        Assert.assertTrue(app.getUser().isLogged());
//    }

    @Test
    public void registrationNegativeTestWrongPassword(){
        int i = (int)(System.currentTimeMillis()/1000)%3600;
        User user = User.builder()
                .email("abc_" + i + "@def.com")
                .password("Abcdef12345")
                .build();
        logger.info("REGISTRATION TEST STARTS WITH DATA: " + user.getEmail() + " & " + user.getPassword());
       app.getUser().openLoginRegistrationForm();
        app.getUser().fillLoginRegistrationForm(user);
        app.getUser().submitRegistration();
        Assert.assertTrue(app.getUser().isCorrectWrongRegAlertText());
        Assert.assertTrue(app.getUser().isAlertPresent());
        Assert.assertFalse(app.getUser().isLogged());

    }

    @Test
    public void registrationNegativeTestWrongEmail(){
        int i = (int)(System.currentTimeMillis()/1000)%3600;
        User user = User.builder()
                .email("abc_" + i + "def.com")
                .password("$Abcdef12345")
                .build();
        logger.info("REGISTRATION TEST STARTS WITH DATA: " + user.getEmail() + " & " + user.getPassword());
        app.getUser().openLoginRegistrationForm();
        app.getUser().fillLoginRegistrationForm(user);
        app.getUser().submitRegistration();
        Assert.assertTrue(app.getUser().isCorrectWrongRegAlertText());
        Assert.assertTrue(app.getUser().isAlertPresent());
        Assert.assertFalse(app.getUser().isLogged());

    }


}