package com.example.StepDefinitions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;
import pages.AccountPage;
import pages.CartPage;
import pages.HomePage;
import pages.ProductPage;
import pages.ShoppingPage;

public class CustomerScenarioStepDefinition {
	
	private WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String expectedSignInTitle = "Sign in";
	String expectedMyAccountTitle = "My account - My Store";
	String capturedData;
	HomePage homepage;
	AccountPage accountPage;
	ShoppingPage shoppingPage;
	ProductPage productPage;
	CartPage cartPage;
	
	@Before
	public void setup() throws InterruptedException, IOException
	{
		FileInputStream fis = new FileInputStream(projectPath+"/src/test/resources/Properties/browser.properties");
        Properties property = new Properties();
        property.load(fis);
		System.out.println("Inside Step : Browser is open");
		System.out.println("Project path is " + projectPath);
		String browser = property.getProperty("browser");
		System.out.println(browser);
		if(browser.equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver",projectPath+"/src/test/resources/Drivers/chromedriver.exe");
			driver = new ChromeDriver();
		}
		else if(browser.equalsIgnoreCase("internetexplorer"))
		{
			System.setProperty("webdriver.ie.driver",projectPath+"/src/test/resources/Drivers/IEDriverServer.exe");
		    driver = new InternetExplorerDriver();
		}
		else
		{
			throw new IllegalArgumentException("Invalid Browser value");
		}
        driver.manage().window().maximize();
        driver.get("http://automationpractice.com/index.php");
        //driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        //Thread.sleep(5000);
	}
	
	@SuppressWarnings("deprecation")
	@Given("^the user is on Sign In Page$")
	public void the_user_is_on_sign_in_page() {
		Assert.assertTrue(driver.getTitle().equals("My Store"));
		homepage = new HomePage(driver);
		accountPage = new AccountPage(driver);
		shoppingPage = new ShoppingPage(driver);
		productPage = new ProductPage(driver);
		cartPage = new CartPage(driver);
		Assert.assertEquals(expectedSignInTitle,homepage.fetchActualSignInText());
		homepage.clickSignIn();
	}

	@When("^the user creates an account$")
	public void the_user_creates_an_account() throws InterruptedException{
		accountPage.createAccount();
	}

	@And("^fills the personal information$")
	public void fills_the_personal_information() {
		accountPage.RegisterUser("Illinois");
	}

	@SuppressWarnings("deprecation")
	@Then("^account should be created successfully$")
	public void account_should_be_created() {
		Assert.assertEquals(expectedMyAccountTitle,accountPage.validateMyAccountPage());
	}

	@And("^correct name and surname must be displayed$")
	public void correct_name_and_surname_must_be_displayed() {
		accountPage.validateAccountUserName("Test","Customer");
	}

	
	@And("^the user is able to logout successfully$")
	public void the_user_is_able_to_logout_successfully()
	{
		accountPage.logoutAccount();
	}
	
	@When("^user logs in the account$")
	public void user_logs_in_the_account() {
		accountPage.loginAccount();
	}
	
	@SuppressWarnings("deprecation")
	@When("^adds a dress in the cart$")
	public void adds_a_dress_in_the_cart() throws InterruptedException {
		shoppingPage.clickWomenCategory();
		shoppingPage.addWomenFilters();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1000)");
		shoppingPage.clickwomenBlouse();
		productPage.setProductColor();
		productPage.setSize("M");
		productPage.clickAddToCart();
		Thread.sleep(5000);
		productPage.closeCartFrame();
		cartPage.ClickonCart();
	}

	@SuppressWarnings("deprecation")
	@When("^proceeds to checkout page$")
	public void proceeds_to_checkout_page() {
		productPage.clickOnProceedToCheckout();
	}

	@When("^continues till Payment Page$")
	public void continues_till_payment_page() {
		productPage.clickOnProceedToCheckout();
		cartPage.clickonShippingAgreement();
		productPage.clickOnProceedToCheckout();
	}

	@Then("^the order details are validated$")
	public void the_order_details_are_validated() {
		productPage.verifyOrderDetails();
	}

	@After
	public void tearDown()
	{
		driver.quit();
	}
	
}

