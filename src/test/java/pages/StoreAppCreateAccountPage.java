package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class StoreAppCreateAccountPage {

    public StoreAppCreateAccountPage(){
        WebDriver driver= Driver.getDriver();
        PageFactory.initElements(driver,this);
    }
    @FindBy(id="password")
    public WebElement password;

    @FindBy(id="days")
    public WebElement birthDay;

    @FindBy(id="months")
    public WebElement birthMonth;

    @FindBy(id="years")
    public WebElement birthYear;

    @FindBy(id= "first_name")
    public WebElement  firstName;

    @FindBy(id="last_name")
    public WebElement lastName;

    @FindBy(id="address1")
    public WebElement address;

    @FindBy(id="country")
    public WebElement country;

    @FindBy(id="state")
    public WebElement state;

    @FindBy(id="city")
    public WebElement city;

    @FindBy(id="zipcode")
    public WebElement zipcode;

    @FindBy(id="mobile_number")
    public WebElement mobile_number;

    @FindBy(xpath = "(//button[@type='submit'])[1]")
    public WebElement CreateAccount;






}
