import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.*;

import java.time.Duration;

import static org.testng.Assert.*;

public class ElentaUserRegistrationTest {


    @Test (priority = 1)
    public void RegisterUser_WhenCredentialsValid_Success(){
        // Arrange
        User user = new User("Milanakskss","miglutCCClekmxcfdvcxigle85@gmail.com","balandis05","balandis05");

        // Act
        boolean result = User.registerUser(user);

        // Assert
        assertTrue(result);
    }
    @Parameters({"userName"})
    @Test (priority = 2)
    public void RegisterUser_WhenUserNameAlreadyExists_ReturnsFalse(){
        boolean result = User.registerUser( new User("Milanaksss","miglugggtCCCemigle85@gmail.com","balandis05","balandis05"));
        assertFalse(result);
    }

    @Test (priority = 3)
    public void RegisterUser_WhenEmailAlreadyExists_ReturnsFalse(){
        boolean result = User.registerUser( new User("Testasasd","miglutCCCemigle85@gmail.com","balandis05","balandis05"));
        assertFalse(result);
    }

    @Test (priority = 4)
    public void RegisterUser_WhenPasswordTooShort_ReturnsFalse(){
        boolean result = User.registerUser( new User("Testasasd","miglugggtCCCemigle85@gmail.com","ba","ba"));
        assertFalse(result);
    }

    @Test (priority = 5)
    public void RegisterUser_WhenPasswordsDontMatch_ReturnsFalse(){
        boolean result = User.registerUser( new User("Testasasd","miglutCCasd@gmail.com","balandis0500","balandis05"));
        assertFalse(result);
    }

    @Test (priority = 6)
    public void RegisterUser_WhenUsernameIsSymbols_ReturnsFalse(){
        boolean result = User.registerUser( new User("@*99/9/*//--.","migaa12luasdtCC@gmail.com","balandis0500","balandis0500"));
        assertFalse(result);
    }





    @BeforeClass
    public void beforeClass(){
        System.setProperty("webdriver.chrome.driver","drivers\\chromedriver111.exe");
        User.driver = new ChromeDriver();
        User.driver.manage().window().maximize();
        User.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        User.driver.get("https://elenta.lt/registracija");
        User.driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[1]/div[2]/div[2]/button[1]")).click();
    }

    @AfterClass
    public void afterClass(){
//        driver.quit();
    }

}