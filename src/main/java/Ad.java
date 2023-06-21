import net.bytebuddy.asm.Advice;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Ad {
    public String Title;
    public String Summary;
    public String Price;
    public String City;
    public String PhoneNumber;
    public String Email;
    public static WebDriver driver;

    public Ad(String adTitle, String adSummary, String price, String city, String phoneNumber, String email) {
        Title = adTitle;
        Summary = adSummary;
        Price = price;
        City = city;
        PhoneNumber = phoneNumber;
        Email = email;
    }

    public static boolean Create(Ad ad) {
        driver.navigate().refresh();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        driver.findElement(By.id("title")).sendKeys(ad.Title);
        driver.findElement(By.id("text")).sendKeys(ad.Summary);
        driver.findElement(By.id("price")).sendKeys(ad.Price);
        driver.findElement(By.id("location-search-box")).sendKeys(ad.City);
        driver.findElement(By.id("phone")).sendKeys(ad.PhoneNumber);
        driver.findElement(By.id("email")).sendKeys(ad.Email);
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        driver.findElement(By.id("submit-button")).click();

        boolean success = ValidateFirstStep();
        if (!success) {
            return false;
        }

        driver.findElement(By.id("forward-button")).click();
        driver.findElement(By.id("forward-button")).click();

        List<WebElement> adActiveElement = driver.findElements(By.xpath("//*[@id=\"main-container\"]/h4"));
        if (!adActiveElement.isEmpty()) {
            driver.get("https://elenta.lt/patalpinti/ivesti-informacija?categoryId=AutoMoto_Automobiliai&actionId=Siulo&returnurl=%2Fpatalpinti%2Fivesti-informacija");
            return true;
        }
        return false;
    }

    public static boolean isAlertPresent()
    {
        try
        {
            driver.switchTo().alert();
            return true;
        }   // try
        catch (NoAlertPresentException Ex)
        {
            return false;
        }   // catch
    }   // isAlertPresent()
    public static boolean ValidateFirstStep() {
        List<WebElement> forward = driver.findElements(By.id("forward-button"));

        if (!forward.isEmpty()) {
            return true;
        }

        List<WebElement> titleError = driver.findElements(By.tagName("div"));
        List<WebElement> summaryError = driver.findElements(By.id("txte"));
        List<WebElement> phoneNumberError1 = driver.findElements(By.id("pe"));
        List<WebElement> phoneNumberError2 = driver.findElements(By.id("ce"));
        List<WebElement> emailError = driver.findElements(By.id("ee"));

        if (!titleError.get(0).getText().isEmpty()) {
            System.out.println(titleError.get(0).getText());
            return false;
        }
        if (!summaryError.get(0).getText().isEmpty()) {
            System.out.println(summaryError.get(0).getText());
            return false;
        }
        if (!phoneNumberError1.get(0).getText().isEmpty()) {
            System.out.println(phoneNumberError1.get(0).getText());
            return false;
        }
        if (!phoneNumberError2.get(0).getText().isEmpty()) {
            phoneNumberError2.get(0).getText();
            System.out.println();
            return false;
        }

        if (!emailError.get(0).getText().isEmpty()) {
            System.out.println(emailError.get(0).getText());
            return false;
        }

        String alert = driver.switchTo().alert().getText();
        if (!alert.isEmpty()) {
            System.out.println(alert);
            return false;
        }

        return false;
    }

}
