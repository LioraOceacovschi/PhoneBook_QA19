package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HelperUser extends HelperBase{
    public HelperUser(WebDriver wd) {
        super(wd);
    }

    public void openLoginRegistrationForm(){
        click(By.xpath("//*[text()='LOGIN']"));
    }

    public void fillLoginRegistrationForm(String email, String password) {
        type(By.xpath("//input[1]"), email);
        type(By.xpath("//input[2]"), password);
    }

    public void submitLogin(){
        click(By.xpath("//button[1]"));
    }

    public void submitRegistration(){
        click(By.xpath("//button[2]"));
    }
    public boolean isLogged(){
        return isElementPresent(By.xpath("//*[text()='Sign Out']"));
    }

    public void logout(){
        click(By.xpath("//*[text()='Sign Out']"));
    }
}
