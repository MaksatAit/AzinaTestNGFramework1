package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.StoreAppCreateAccountPage;
import pages.StoreAppHomePage;
import pages.StoreAppLoginPage;
import utilities.BrowserUtils;
import utilities.ConfigReader;
import utilities.TestBase;

import java.util.Random;

public class StoreAppTests extends TestBase {

    String email;
    String passwordSignIn;

    @DataProvider(name="registerData")
    public static Object[][] registerData(){
        Object[][] data=new Object[][]{
                {"1234million","1","8","2000","Azina","Barpieva","2321 Cling str","United States","California","Glendale","123456", "1234567812" },
                {"456milliard","2","4","2005","Kim","Yan","232 Winnetka str","United States","Illinois","Tampa","54321", "1234567812" },
                {"789done","5","11","1950","John","Doe","123 Sarah str","United States","Texas","Tampa","54371", "1234567812"}
        };
        return data;
    }

    @Test(dataProvider="registerData", groups={"regression","smoke"})
    public void validateRegisterFunctionalityTest(String password, String birthDay, String birthMonth, String birthYear, String firstName,
                                                  String lastName, String address, String country, String state, String city, String zipcode,
                                                   String mobile_number){
        driver.get(ConfigReader.getProperty("StoreAppURL"));
        StoreAppHomePage homePage=new StoreAppHomePage();
        homePage.loginButton.click();
        StoreAppLoginPage loginPage=new StoreAppLoginPage();
        Random random=new Random();
        int randomNum=random.nextInt();
        email=randomNum+"1amina@gmail.com";

        loginPage.registerEmailBox.sendKeys(randomNum+"Azina");
        loginPage.emailAddress.sendKeys(randomNum+"inala@gmail.com");
        loginPage.signButton.click();


        StoreAppCreateAccountPage createAccountPage=new StoreAppCreateAccountPage();
        passwordSignIn = password;
        createAccountPage.password.sendKeys(password);
        BrowserUtils.selectDropdownByValue(createAccountPage.birthDay,birthDay);
        BrowserUtils.selectDropdownByValue(createAccountPage.birthMonth, birthMonth);
        BrowserUtils.selectDropdownByValue(createAccountPage.birthYear,birthYear);
        createAccountPage.firstName.sendKeys(firstName);
        createAccountPage.lastName.sendKeys(lastName);
        createAccountPage.address.sendKeys(address);
        BrowserUtils.selectDropdownByValue(createAccountPage.country, country);
        createAccountPage.state.sendKeys(state);
        createAccountPage.city.sendKeys(city);
        createAccountPage.zipcode.sendKeys(zipcode);
        createAccountPage.mobile_number.sendKeys(mobile_number);
        createAccountPage.CreateAccount.click();

        String actualTitle=driver.getTitle();
        String expectedTitle="Automation Exercise - Account Created";

        Assert.assertEquals(actualTitle,expectedTitle);


    }
    @Test(dependsOnMethods = "validateRegisterFunctionalityTest", groups={"regression","smoke"})
    public void validateSignInFunctionalityTest() {
        driver.get(ConfigReader.getProperty("StoreAppURL"));
        StoreAppLoginPage loginPage=new StoreAppLoginPage();
        loginPage.signIn(email,passwordSignIn);

        String actualTitle=driver.getTitle();
        String expectedTitle="Automation Exercise - Signup / Login";

        Assert.assertEquals(actualTitle, expectedTitle);
    }

}
