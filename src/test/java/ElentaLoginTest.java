import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.*;

import java.time.Duration;

import static org.testng.Assert.*;

public class ElentaLoginTest {

    @Test (priority = 1)
    public void LoginUser_WhenCredentialsValid_Success() {

        User user = new User("Migle", "miglute1");

        boolean result = User.userLogin(user);

        assertTrue(result);
    }

    @Test (priority = 2)
    public void LoginUser_WhenUserNameEmpty_ReturnsFalse(){

        boolean result = User.userLogin(new User("", "miglute1"));
        assertFalse(result);
    }

    @Test (priority = 3)
    public void LoginUser_WhenUserPasswordEmpty_ReturnsFalse(){

        boolean result = User.userLogin(new User("Migle", ""));
        assertFalse(result);
    }
    @Test (priority = 4)
    public void LoginUser_WrongCredentials_ReturnsFalse(){

        boolean result = User.userLogin(new User("Manu", "kose"));
        assertFalse(result);
    }

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver111.exe");
        User.driver = new ChromeDriver();
        User.driver.manage().window().maximize();
        User.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        User.driver.get("https://elenta.lt/prisijungti?returnurl=https%3A%2F%2Felenta.lt%2F");
        User.driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[1]/div[2]/div[2]/button[1]")).click();
    }

    @AfterClass
    public void afterClass() {
//        driver.quit();
    }


}









