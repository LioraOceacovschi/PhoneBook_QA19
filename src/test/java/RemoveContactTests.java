import models.User;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RemoveContactTests extends TestBase {
    @BeforeMethod
    public void preCondition() {
        User user = User.builder()
                .email("lso@abc.com")
                .password("$Abcd1234")
                .build();

        if (!app.getUser().isLogged()) {
            app.getUser().login(user);
        }
    }

    @Test
    public void removeOneContact() {
        if(!app.getContact().isContactPresent()) app.getContact().addOneContact();
        int res = app.getContact().removeOneContact();
        Assert.assertEquals(res, -1);
    }

    @Test
    public void removeAllContactsTest() {
        app.getContact().removeAllContacts();
        Assert.assertFalse(app.getContact().isContactPresent());


    }

}
