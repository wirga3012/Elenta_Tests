import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.time.Duration;
import static org.testng.Assert.*;

public class ElentaAdCreationTest {

    @Test
    public void Create_Success() {
        boolean result = Ad.Create(new Ad("Test","Test summarry","45","Kaunas","864568719","asda@ss.lt" ));
        assertTrue(result);
    }

    @Test
    public void Create_WhenTitleEmpty_ReturnsFalse() {
        boolean result = Ad.Create(new Ad("","Test summarry","45","Kaunas","864568719","asda@ss.lt" ));
        assertFalse(result);
    }
    @Test
    public void Create_WhenSummaryEmpty_ReturnsFalse() {
        boolean result = Ad.Create(new Ad("Test","","45","Kaunas","864568719","asda@ss.lt" ));
        assertFalse(result);
    }
    @Test
    public void Create_WhenPriceEmpty_ReturnsFalse() {
        boolean result = Ad.Create(new Ad("Test","Test summarry","","Kaunas","864568719","asda@ss.lt" ));
        assertFalse(result);
    }
    @Test
    public void Create_WhenPriceIsNegative_ReturnsFalse() {
        boolean result = Ad.Create(new Ad("Test","Test summarry","-45","Kaunas","864568719","asda@ss.lt" ));
        assertFalse(result);
    }
    @Test
    public void Create_EmailInvalid_ReturnsFalse() {
        boolean result = Ad.Create(new Ad("","Test summarry","45","Kaunas","864568719","asdalt" ));
        assertFalse(result);
    }
    @Test
    public void Create_WhenPhoneEmpty_ReturnsFalse() {
        boolean result = Ad.Create(new Ad("","Test summarry","45","Kaunas","","asda@ss.lt" ));
        assertFalse(result);
    }
    @Test
    public void Create_WhenPhoneNotCorrect_ReturnsFalse() {
        boolean result = Ad.Create(new Ad("","Test summarry","45","Kaunas","123","asda@ss.lt" ));
        assertFalse(result);
    }
    @Test
    public void Create_WhenPhoneFormatWrong_ReturnsFalse() {
        boolean result = Ad.Create(new Ad("kkk","Test summarry","45","Kaunas","123456789","asda@ss.lt" ));
        assertFalse(result);
    }



    @BeforeClass
    public void beforeClass(){
        System.setProperty("webdriver.chrome.driver","drivers\\chromedriver111.exe");
        Ad.driver = new ChromeDriver();
        Ad.driver.manage().window().maximize();
        Ad.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        Ad.driver.get("https://elenta.lt/patalpinti/ivesti-informacija?categoryId=AutoMoto_Automobiliai&actionId=Siulo&returnurl=%2Fpatalpinti%2Fivesti-informacija");
                                                     //html/body/div[5]/div[2]/div[1]/div[2]/div[2]/button[1]
        // /html/body/div[4]/div[2]/div[1]/div[2]/div[2]/button[1]/p
        Ad.driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[1]/div[2]/div[2]/button[1]")).click();
    }

    @AfterClass
    public void afterClass(){
//        driver.quit();
    }

}














