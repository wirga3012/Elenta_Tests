import net.bytebuddy.asm.Advice;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class User {
    public String userName;
    public String email;
    public String password;
    public String repeatPassword;
    public static WebDriver driver;


    public User(String userName, String email, String password, String repeatPassword) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.repeatPassword = repeatPassword;
    }

    public static boolean registerUser(User user) {
        driver.get("https://elenta.lt/registracija");
        WebElement userName = driver.findElement(By.id("UserName"));
        WebElement email = driver.findElement(By.id("Email"));
        WebElement password = driver.findElement(By.id("Password"));
        WebElement repeatPassword = driver.findElement(By.id("Password2"));
        WebElement submit = driver.findElement(By.xpath("//*[@id=\"main-container\"]/form/fieldset/table/tbody/tr[11]/td[2]/input"));

        userName.sendKeys(user.userName);
        email.sendKeys(user.email);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        password.sendKeys(user.password);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        repeatPassword.sendKeys(user.repeatPassword);
        submit.click();

        return checkRegistrationFormStatus();
    }

    public static boolean checkRegistrationFormStatus() {
        System.out.println("checkRegistrationFormStatus");
        List<WebElement> successfullyRegistration = driver.findElements(By.xpath("//*[@id=\"main-container\"]/div[2]/h1/b"));
        if (successfullyRegistration.size() > 0 && successfullyRegistration.get(0).getText().contains("Jūs sėkmingai prisiregistravote!")){
            return true;
        }

        List<WebElement> usernameInputErrors = driver.findElements(By.xpath("//*[@id=\"main-container\"]/form/fieldset/table/tbody/tr[1]/td[2]/span"));
        List<WebElement> emailInputErrors = driver.findElements(By.xpath("//*[@id=\"main-container\"]/form/fieldset/table/tbody/tr[4]/td[2]/span"));
        List<WebElement> passwordInputErrors = driver.findElements(By.xpath("//*[@id=\"main-container\"]/form/fieldset/table/tbody/tr[7]/td[2]/span"));
        List<WebElement> passwordsNotMatchErrors = driver.findElements(By.xpath("//*[@id=\"main-container\"]/form/fieldset/table/tbody/tr[8]/td[2]/span"));

        if (!usernameInputErrors.isEmpty()) {
            System.out.println(usernameInputErrors.get(0).getText());
            return false;
        }
        if (!emailInputErrors.isEmpty()) {
            System.out.println(emailInputErrors.get(0).getText());
            return false;
        }
        if (!passwordInputErrors.isEmpty()) {
            System.out.println(passwordInputErrors.get(0).getText());
            return false;
        }
        if(!passwordsNotMatchErrors.isEmpty()){
            System.out.println(passwordsNotMatchErrors.get(0).getText());
//            for (int i = 0; i < passwordsNotMatchErrors.size(); i++) {
//                System.out.println(passwordsNotMatchErrors.get(i).getText());
//            }
            return false;
        }
        return false;
    }

    public User (String userName, String password){
        this.userName = userName;
        this.password = password;
    }
    public static boolean userLogin(User user){

        driver.findElement(By.id("UserName")).sendKeys(user.userName);
        driver.findElement(By.id("Password")).sendKeys(user.password);
        driver.findElement(By.xpath("//*[@id=\"form\"]/fieldset/table/tbody/tr[4]/td[2]/input")).click();
        return userLoginVerification();
    }
    public static boolean userLoginVerification() {

        List<WebElement>  myAd = driver.findElements(By.id("my-ads-nav-button"));

        if(myAd.size() > 0){
            logout();
            return true;
        }

        List<WebElement> noLoginInputErrors = driver.findElements(By.xpath("//*[@id=\"form\"]/fieldset/table/tbody/tr[1]/td[2]/span"));
        List<WebElement> userLoginInvalidCredentialsErrors = driver.findElements(By.xpath("//*[@id=\"form\"]/fieldset/table/tbody/tr[5]/td/div/ul/li"));
        List<WebElement> noPasswordInputErrors = driver.findElements(By.xpath("//*[@id=\"form\"]/fieldset/table/tbody/tr[3]/td[2]/span"));

        if (!noLoginInputErrors.isEmpty()) {
            System.out.println(noLoginInputErrors.get(0).getText());
            return false;
        }
        if(!userLoginInvalidCredentialsErrors.isEmpty()){
            System.out.println(userLoginInvalidCredentialsErrors.get(0).getText());
            return false;
        }
        if(!noPasswordInputErrors.isEmpty()){
            System.out.println(noPasswordInputErrors.get(0).getText());
            return false;
        }
        return false;
    }

    public static void logout(){
        driver.navigate().to(driver.getCurrentUrl() + "/accounts/logout");
        driver.findElement(By.xpath("//*[@id=\"header-container-nav\"]/a[3]")).click();

//        driver.findElement(By.id("my-ads-nav-button")).click();
//        driver.findElement(By.id("//*[@id=\"main-container\"]/table/tbody/tr[1]/td[3]/a[2]")).click();
    }

}




