package manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    Logger logger = LoggerFactory.getLogger(ApplicationManager.class);

    EventFiringWebDriver wd;
    HelperUser user;

    public void init(){
        wd = new EventFiringWebDriver(new ChromeDriver());
        wd.register(new MyListener());
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
