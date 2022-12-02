package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class StoreAppLoginPage {

    public StoreAppLoginPage(){
        WebDriver driver= Driver.getDriver();
        PageFactory.initElements(driver,this);

    }
    @FindBy(xpath = "//input[@type='text']")
    public WebElement registerEmailBox;

    @FindBy(xpath="(//input[@type='email'])[2]")
    public WebElement emailAddress;

    @FindBy(xpath = "(//button[@type='submit'])[2]")
    public WebElement signButton;

    @FindBy(xpath = "(//input[@type='email'])[1]")
    public WebElement sighInEmailBox;

    @FindBy(xpath = "(//input[@type='password'])[1]")
    public WebElement passwordSignIn;

    @FindBy(xpath = "(//button[@type='submit'])[1]")
    public WebElement login;

    public void signIn(String username, String password){
        sighInEmailBox.sendKeys(username);
        this.passwordSignIn.sendKeys(password);
        login.click();

    }
}
