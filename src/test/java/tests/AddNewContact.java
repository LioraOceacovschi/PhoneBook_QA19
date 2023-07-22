package tests;

import models.Contact;
import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddNewContact extends TestBase {

    @BeforeMethod(alwaysRun = true)
    public void preCondition() {
        User user = User.builder()
                .email("lso@abc.com")
                .password("$Abcd1234")
                .build();

        if (!app.getUser().isLogged()) {
            app.getUser().login(user);
        }
    }

    @Test(groups = {"sanityGroup"})
    public void addNewContactPositive() {
        int i = (int) (System.currentTimeMillis() / 1000) % 3600;
        Contact contact = Contact.builder()
                .name("Vasya"+i)
                .lastName("Pupkin")
                .phone("123456789" + i)
                .email("vasya" + i + "@mail.ru")
                .address("Haifa")
                .description("Friend")
                .build();
        logger.info(" addNewContactPositive() test starts with data : " +contact.getPhone() );

        app.getContact().openAddContactForm();
        app.getContact().fillAddContactForm(contact);
        app.getContact().submitAddContactForm();
        Assert.assertTrue(app.getContact().isContactAddSuccessful(contact.getPhone()));

    }


    @AfterMethod(alwaysRun = true)
    public void postCondition() {

    }

}
