package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.*;
import utilities.ConfigReader;
import utilities.TestBase;

import java.util.List;

public class SauceLabsTests extends TestBase {



    @Test(priority= 1, groups={"regression","smoke"})
    public void validateLoginTest(){
        driver.get(ConfigReader.getProperty("SauceLabsURL"));
        SaucedemoLoginPage loginPage=new SaucedemoLoginPage();
        loginPage.login();
        String actualPageHeader=driver.findElement(By.xpath("//span[@class='title']")).getText();
        String expectedPageHeader="PRODUCTS";

        Assert.assertEquals(actualPageHeader, expectedPageHeader);
    }
    @Test (priority = 2, groups= {"regression"})
    public void validateFilterByPriceTest(){
        driver.get(ConfigReader.getProperty("SauceLabsURL"));
        SaucedemoLoginPage loginPage=new SaucedemoLoginPage();
        loginPage.login();
        WebElement filter= driver.findElement(By.xpath("//select[@class='product_sort_container']"));
        Select select=new Select(filter);
        select.selectByValue("lohi");

        List<WebElement> prices=driver.findElements(By.xpath("//div[@class='inventory_item_price']"));

        //7.99$- 9,99$- 15.99$- 15.99$ -29.99$ - 49.99$
        for(int i=1; i<prices.size(); i++){
            //"$9.99" -> 9.99
            String price=prices.get(i).getText().substring(1); //"$9.99"-> "9.99"
            double priceDouble=Double.parseDouble(price); // "9,99" -> 9.99

            //"$7.99" -> 7.99
            String price2=prices.get(i-1).getText().substring(1); // "$7.99" -> "7.99"
            double priceDouble2=Double.parseDouble(price2); //"7.99" -> 7.99

            Assert.assertTrue(priceDouble>=priceDouble2);
        }


    }

    @Test (priority = 3, groups = {"regression", "smoke"})
    public void validateOrderFunctioanalityTest(){
        driver.get(ConfigReader.getProperty("SauceLabsURL"));
        SaucedemoLoginPage loginPage=new SaucedemoLoginPage();
        loginPage.login();

        driver.findElement(By.id("add-to-cart-sauce-labs-onesie")).click();
        driver.findElement(By.xpath("//a[@class='shopping_cart_link']")).click();
        String price= driver.findElement(By.xpath("//div[@class='inventory_item_price']")).getText();
        driver.findElement(By.id("checkout")).click();

        driver.findElement(By.id("first-name")).sendKeys("John");
        driver.findElement(By.id("last-name")).sendKeys("Doe");
        driver.findElement(By.id("postal-code")).sendKeys("12345");
        driver.findElement(By.id("continue")).click();

        String checkOutPrice=driver.findElement(By.xpath("//div[@class='summary_subtotal_label']")).getText();

        SoftAssert softAssert = new SoftAssert();

        Assert.assertEquals(checkOutPrice.substring(checkOutPrice.lastIndexOf(" ")+1), price);

        driver.findElement(By.id("finish")).click();

        String actualSuccessMessage=driver.findElement(By.xpath("//h2[@class='complete-header']")).getText();
        String expectedSuccessMessage="THANK YOU FOR YOUR ORDER";

        softAssert.assertEquals(actualSuccessMessage,expectedSuccessMessage);
        softAssert.assertAll();
    }
    @Test(priority=4, groups={"regression", "smoke"})
    public void validateCheckoutTotalTest(){
        driver.get(ConfigReader.getProperty("SauceLabsURL"));
        SaucedemoLoginPage loginPage=new SaucedemoLoginPage();
        loginPage.login();

        SaucedemoHomePage homePage=new SaucedemoHomePage();
        homePage.backPackProduct.click();
        homePage.bikeLightProduct.click();
        String backpackPrice=homePage.backpackPrice.getText();
        String bikeLightPrice=homePage.bikeLightPrice.getText();
        homePage.cart.click();

        SaucedemoMyCartPage myCartPage=new SaucedemoMyCartPage();
        myCartPage.checkoutButton.click();

        SaucedemoCheckoutPage checkoutPage=new SaucedemoCheckoutPage();
        checkoutPage.checkoutWithValidInfo();

        SaucedemoCheckoutOverviewPage checkoutOverviewPage=new SaucedemoCheckoutOverviewPage();
        String actualTotalPrice= checkoutOverviewPage.totalPrice.getText();

        //validate-> backpackPrice+ bikeLightPrice== actualTotalPrice

        //backpackPrice -> "$29.99"
        //bikeLightPrice-> "$9,99"

        double backpackPriceDouble=Double.parseDouble(backpackPrice.substring(1));
        double bikeLightPriceDouble=Double.parseDouble(bikeLightPrice.substring(1));

        //actualTotalPrice-> "Item total: $39.98"
        double actualTotalPriceDouble=Double.parseDouble(actualTotalPrice.substring(actualTotalPrice.indexOf("$")+1)) ;

        Assert.assertTrue(backpackPriceDouble+bikeLightPriceDouble == actualTotalPriceDouble);



    }


    }


