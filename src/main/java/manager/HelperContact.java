package manager;

import models.Contact;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelperContact extends HelperBase {

    Logger logger = LoggerFactory.getLogger(HelperContact.class);
    public HelperContact(WebDriver wd) {
        super(wd);
    }

    public void openContactList() {
        click(By.xpath("//a[.='CONTACTS']"));
    }

    public void openAddContactForm() {
        new WebDriverWait(wd, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[.='ADD']")));
        click(By.xpath("//a[.='ADD']"));
    }


    public void fillAddContactForm(Contact contact) {
        pause(3000);
        type(By.xpath("//input[@placeholder='Name']"), contact.getName());
        type(By.xpath("//input[@placeholder='Last Name']"), contact.getLastName());
        type(By.xpath("//input[@placeholder='Phone']"), contact.getPhone());
        type(By.xpath("//input[@placeholder='email']"), contact.getEmail());
        type(By.xpath("//input[@placeholder='Address']"), contact.getAddress());
        type(By.xpath("//input[@placeholder='description']"), contact.getDescription());
    }

    public void submitAddContactForm() {
        click(By.xpath("//button[.='Save']"));
    }

    public boolean isContactAddSuccessful(String contactNumber) {
        return isElementPresent(By.xpath("//div[@class='contact-item_card__2SOIM'][last()]/h3[.='" + contactNumber + "']"));
    }

    public void selectContact(By locator) {
        click(locator);
    }

    public void clickRemoveContactButton() {
        click(By.xpath("//button[.='Remove']"));
    }

    public void removeFirstContact() {
        click(By.xpath("//div[@class='contact-item_card__2SOIM']"));
        clickRemoveContactButton();
    }

    public int countOfContacts() {
        return wd.findElements(By.xpath("//div[@class='contact-item_card__2SOIM']")).size();
    }

    public int removeOneContact() {
        int countBefore = countOfContacts();
        logger.info("Number of contacts before is  " + countBefore);
        String phone = wd.findElement(By.xpath("//div[@class='contact-item_card__2SOIM']//h3")).getText();
        logger.info("The deleted phone number is  " + phone );
        removeFirstContact();
        pause(5000);
        int countAfter = countOfContacts();
        logger.info("Number of contacts after is  " + countAfter);
        return countAfter - countBefore;
    }

    public void removeAllContacts(){
        while(wd.findElements(By.xpath("//div[@class='contact-item_card__2SOIM']")).size()!= 0){
            removeOneContact();
        }
    }

    public boolean isContactPresent(){
        return isElementPresent(By.xpath("//div[@class='contact-item_card__2SOIM']"));
    }

    public void addOneContact(){
        int i = (int) (System.currentTimeMillis() / 1000) % 3600;
        Contact contact = Contact.builder()
                .name("Vasya"+i)
                .lastName("Pupkin")
                .phone("123456789" + i)
                .email("vasya" + i + "@mail.ru")
                .address("Haifa")
                .description("Friend")
                .build();

        openAddContactForm();
        fillAddContactForm(contact);
        submitAddContactForm();
    }

}
