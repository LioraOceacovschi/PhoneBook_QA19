import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegistrationTest extends TestBase{
    @BeforeMethod
    public void precondition(){
        if(app.getUser().isLogged()){
            app.getUser().logout();
        }
    }
    @Test
    public void registrationPositiveTest(){
        int i = (int)(System.currentTimeMillis()/1000)%3600;
        User user = new User()
                .withEmail("abc_" + i + "@def.com")
                .withPassword("$Abcdef12345");
        app.getUser().openLoginRegistrationForm();
        app.getUser().fillLoginRegistrationForm(user);
        app.getUser().submitRegistration();
        Assert.assertTrue(app.getUser().isLogged());
    }

    @Test
    public void registrationNegativeTestWrongPassword(){
        int i = (int)(System.currentTimeMillis()/1000)%3600;
        User user = new User()
                .withEmail("abc_" + i + "@def.com")
                .withPassword("Abcdef12345");
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
        User user = new User()
                .withEmail("abc_" + i + "def.com")
                .withPassword("$Abcdef12345");
        app.getUser().openLoginRegistrationForm();
        app.getUser().fillLoginRegistrationForm(user);
        app.getUser().submitRegistration();
        Assert.assertTrue(app.getUser().isCorrectWrongRegAlertText());
        Assert.assertTrue(app.getUser().isAlertPresent());
        Assert.assertFalse(app.getUser().isLogged());

    }


}