package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.ExcelUtils;
import util.UtilFunctions;

import java.util.concurrent.TimeUnit;

public class initiateTransactionsSteps {
    WebDriver driver = null;
    WebDriverWait wait;
    UtilFunctions util ;

    @Given("user on my shopify store page")
    public void user_on_my_shopify_store_page() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
    @When("^user is with (.*) and (.*)$")
    public void user_is_with_url_password(String url, String password ) {
        driver.get(url);
        util = new UtilFunctions(driver);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 5);

        driver.findElement(By.id("password")).click();
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys(password);
    }

    @When("click on enter button")
    public void click_on_enter_button() {
        //itpf.clickSubmit();
        driver.findElement(By.xpath("//button[@type='submit']")).click();

    }
    @When("user is navigated to store home page")
    public void user_is_navigated_to_store_home_page() {

    }
    @When("click on catalog")
    public void click_on_catalog() throws InterruptedException {

        boolean isElement1Present = true;
        WebElement catalogElement;
        try{
            Thread.sleep(2000);
            catalogElement = driver.findElement(By.xpath("//span[@class='site-nav__label'][normalize-space()='Catalog']"));
            System.out.println("catalogElement present");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='site-nav__label'][normalize-space()='Catalog']")));
            util.waitAndClickXpath("//span[@class='site-nav__label'][normalize-space()='Catalog']");

        }catch (NoSuchElementException e){
            isElement1Present = false;
            System.out.println("catalogElement not present");
        }

        if(isElement1Present == false) {
            catalogElement = driver.findElement(By.xpath("//span[normalize-space()='Catalog']"));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Catalog']")));
            util.waitAndClickXpath("//span[normalize-space()='Catalog']");

        }



    }
    @When("user add product in card")
    public void user_add_product_in_card() throws InterruptedException {

        util.waitAndClickXpath("//main[@id='MainContent']//ul[1]//li[4]");

    }
    @When("click on buy it now")
    public void click_on_buy_it_now() throws InterruptedException {

        util.waitAndClickXpath("//button[normalize-space()='Buy it now']");
    }
    @When("user fill contact information")
    public void user_fill_contact_information() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='checkout_email_or_phone']")));

        driver.findElement(By.xpath("//input[@id='checkout_email_or_phone']")).click();
        driver.findElement(By.xpath("//input[@id='checkout_email_or_phone']")).clear();
        driver.findElement(By.xpath("//input[@id='checkout_email_or_phone']")).sendKeys("ajay.kumar@gmail.com");

        driver.findElement(By.xpath("//input[@id='checkout_shipping_address_first_name']")).click();
        driver.findElement(By.xpath("//input[@id='checkout_shipping_address_first_name']")).clear();
        driver.findElement(By.xpath("//input[@id='checkout_shipping_address_first_name']")).sendKeys("Ajay");

        driver.findElement(By.xpath("//input[@id='checkout_shipping_address_last_name']")).click();
        driver.findElement(By.xpath("//input[@id='checkout_shipping_address_last_name']")).clear();
        driver.findElement(By.xpath("//input[@id='checkout_shipping_address_last_name']")).sendKeys("Kumar");


        util.waitAndClickXpath("//input[@id='checkout_shipping_address_address1']");
        driver.findElement(By.xpath("//input[@id='checkout_shipping_address_address1']")).clear();
        driver.findElement(By.xpath("//input[@id='checkout_shipping_address_address1']")).sendKeys("5400 Meadows Road, 5th Floor, Lake Oswego, OR 97035");

        js.executeScript("window.scrollBy(0,350)", "");

        driver.findElement(By.xpath("//input[@id='checkout_shipping_address_city']")).click();
        driver.findElement(By.xpath("//input[@id='checkout_shipping_address_city']")).clear();
        driver.findElement(By.xpath("//input[@id='checkout_shipping_address_city']")).sendKeys("Oswego");
        // select from dropdown
        Select drpCountry = new Select(driver.findElement(By.xpath("//select[@id='checkout_shipping_address_country']")));
        drpCountry.selectByVisibleText("United States");

        Select drpCity = new Select(driver.findElement(By.xpath("//select[@id='checkout_shipping_address_province']")));
        drpCity.selectByVisibleText("Oregon");

        driver.findElement(By.xpath("//input[@id='checkout_shipping_address_zip']")).click();
        driver.findElement(By.xpath("//input[@id='checkout_shipping_address_zip']")).clear();
        driver.findElement(By.xpath("//input[@id='checkout_shipping_address_zip']")).sendKeys("97035");

    }
    @When("click on continue to shipping")
    public void click_on_continue_to_shipping() {
        driver.findElement(By.xpath("//button[@id='continue_button']")).click();
    }
    @When("user choose a shipping method")
    public void user_choose_a_shipping_method() throws InterruptedException {


    }
    @When("click on continue to payment")
    public void click_on_continue_to_payment() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,400)", "");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='continue_button']")));
        Thread.sleep(2000);
        driver.findElement(By.xpath("//button[@id='continue_button']")).click();
    }
    @When("user fill credit card detail")
    public void user_fill_credit_card_detail() throws InterruptedException {

        JavascriptExecutor js = (JavascriptExecutor) driver;
        Integer noOfFrames = Integer.parseInt(js.executeScript("return window.length").toString());
        System.out.println("No. of iframes on the page are " + noOfFrames);
        js.executeScript("window.scrollBy(0,400)", "");

        driver.switchTo().frame(1);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("number")));
        driver.findElement(By.id("number")).sendKeys(Keys. NUMPAD4);
        driver.findElement(By.id("number")).sendKeys(Keys. NUMPAD2);
        driver.findElement(By.id("number")).sendKeys(Keys. NUMPAD4);
        driver.findElement(By.id("number")).sendKeys(Keys. NUMPAD2);

        driver.findElement(By.id("number")).sendKeys(Keys. NUMPAD4);
        driver.findElement(By.id("number")).sendKeys(Keys. NUMPAD2);
        driver.findElement(By.id("number")).sendKeys(Keys. NUMPAD4);
        driver.findElement(By.id("number")).sendKeys(Keys. NUMPAD2);

        driver.findElement(By.id("number")).sendKeys(Keys. NUMPAD4);
        driver.findElement(By.id("number")).sendKeys(Keys. NUMPAD2);
        driver.findElement(By.id("number")).sendKeys(Keys. NUMPAD4);
        driver.findElement(By.id("number")).sendKeys(Keys. NUMPAD2);

        driver.findElement(By.id("number")).sendKeys(Keys. NUMPAD4);
        driver.findElement(By.id("number")).sendKeys(Keys. NUMPAD2);
        driver.findElement(By.id("number")).sendKeys(Keys. NUMPAD4);
        driver.findElement(By.id("number")).sendKeys(Keys. NUMPAD2);


        driver.switchTo().defaultContent();

        Thread.sleep(1000);
        driver.switchTo().frame(2);
        driver.findElement(By.id("name")).sendKeys("test");

        driver.switchTo().defaultContent();
        Thread.sleep(1000);
        driver.switchTo().frame(3);
        driver.findElement(By.id("expiry")).sendKeys(Keys. NUMPAD1);
        driver.findElement(By.id("expiry")).sendKeys(Keys. NUMPAD2);
        driver.findElement(By.id("expiry")).sendKeys(Keys. NUMPAD2);
        driver.findElement(By.id("expiry")).sendKeys(Keys. NUMPAD4);

        driver.switchTo().defaultContent();
        Thread.sleep(1000);
        driver.switchTo().frame(4);
        driver.findElement(By.id("verification_value")).sendKeys("123");

        driver.switchTo().defaultContent();


    }
    @When("click on pay now button")
    public void click_on_pay_now_button() throws InterruptedException {
        driver.findElement(By.xpath("//div[@class='shown-if-js']//button[@id='continue_button']")).click();
        Thread.sleep(4000);
    }
    @Then("user is navigated to order detail page")
    public void user_is_navigated_to_order_detail_page() {
        // driver.close();
        //driver.quit();

    }

    @When("user is login in to store with {string} and {string}")
    public void user_is_login_with_url_password(String url, String password ) {
        driver.get(url);
        util = new UtilFunctions(driver);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);

        driver.findElement(By.id("password")).click();
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys(password);
    }

    @When("^user fill contact information from (.*) and (.*)$")
    public void user_fill_contact_information_from_and(String excelFileName, String rowNum) throws InterruptedException {
        System.out.println("fileName::"+excelFileName);

        String projectPath = System.getProperty("user.dir");
        String fileName = projectPath + "/datafiles/"+ excelFileName;
        int sheetIndex = 0;
        int rowNumber = Integer.parseInt(rowNum);  ;
        System.out.println("rowNumber: "+rowNumber);
        ExcelUtils excelUtils = new ExcelUtils(fileName,sheetIndex);
        JavascriptExecutor js = (JavascriptExecutor) driver;

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='checkout_email_or_phone']")));

        driver.findElement(By.xpath("//input[@id='checkout_email_or_phone']")).click();
        driver.findElement(By.xpath("//input[@id='checkout_email_or_phone']")).clear();
        driver.findElement(By.xpath("//input[@id='checkout_email_or_phone']")).sendKeys(excelUtils.getCellDataString(rowNumber,1).toString());

        driver.findElement(By.xpath("//input[@id='checkout_shipping_address_first_name']")).click();
        driver.findElement(By.xpath("//input[@id='checkout_shipping_address_first_name']")).clear();
        driver.findElement(By.xpath("//input[@id='checkout_shipping_address_first_name']")).sendKeys(excelUtils.getCellDataString(rowNumber,2).toString());

        driver.findElement(By.xpath("//input[@id='checkout_shipping_address_last_name']")).click();
        driver.findElement(By.xpath("//input[@id='checkout_shipping_address_last_name']")).clear();
        driver.findElement(By.xpath("//input[@id='checkout_shipping_address_last_name']")).sendKeys(excelUtils.getCellDataString(rowNumber,3).toString());


        util.waitAndClickXpath("//input[@id='checkout_shipping_address_address1']");
        driver.findElement(By.xpath("//input[@id='checkout_shipping_address_address1']")).clear();
        driver.findElement(By.xpath("//input[@id='checkout_shipping_address_address1']")).sendKeys(excelUtils.getCellDataString(rowNumber,4).toString());

        js.executeScript("window.scrollBy(0,350)", "");

        driver.findElement(By.xpath("//input[@id='checkout_shipping_address_city']")).click();
        driver.findElement(By.xpath("//input[@id='checkout_shipping_address_city']")).clear();
        driver.findElement(By.xpath("//input[@id='checkout_shipping_address_city']")).sendKeys(excelUtils.getCellDataString(rowNumber,6).toString());
        // select from dropdown
        Select drpCountry = new Select(driver.findElement(By.xpath("//select[@id='checkout_shipping_address_country']")));
        drpCountry.selectByVisibleText(excelUtils.getCellDataString(rowNumber,7).toString());


        Select drpCity = new Select(driver.findElement(By.xpath("//select[@id='checkout_shipping_address_province']")));
        drpCity.selectByVisibleText(excelUtils.getCellDataString(rowNumber,8).toString());

        System.out.println("checkout_shipping_address_zip: "+excelUtils.getCellDataString(rowNumber,9));
        driver.findElement(By.xpath("//input[@id='checkout_shipping_address_zip']")).click();
        driver.findElement(By.xpath("//input[@id='checkout_shipping_address_zip']")).clear();
        driver.findElement(By.xpath("//input[@id='checkout_shipping_address_zip']")).sendKeys(""+excelUtils.getCellDataInt(rowNumber,9));


    }

    @When("click add to card and check out")
    public void  click_add_to_card_and_check_out() throws InterruptedException {

        util.waitAndClickXpath("//button[@aria-label='Add to cart']");
        driver.switchTo().activeElement();
        util.waitAndClickXpath("//div[@role='dialog']//div//a[@href='/cart']");
        driver.switchTo().defaultContent();
        util.waitAndClickXpath("//input[@value='Check out']");
    }

}
