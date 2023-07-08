package manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    WebDriver wd;
    HelperUser user;

    public void init(){
        wd = new ChromeDriver();
        wd.navigate().to("https://telranedu.web.app/home");
        user = new HelperUser(wd);
        wd.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    public HelperUser getUser() {
        return user;
    }

    public void tearDown() {
        //  wd.quit();
    }
}
