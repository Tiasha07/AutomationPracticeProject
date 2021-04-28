package pages;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class AccountPage {
	
	@FindBy(xpath = "//input[@id='email_create']")
	private WebElement email_address;
	
	@FindBy(xpath = "//button[@id='SubmitCreate']")
	private WebElement button_createAccount;
	
	@FindBy(xpath = "//input[@id='id_gender2']")
	private WebElement title;
	
	@FindBy(xpath = "//input[@id='customer_firstname']")
	private WebElement customer_firstName;
	
	@FindBy(xpath = "//input[@id='customer_lastname']")
	private WebElement customer_lastName;
	
	@FindBy(xpath = "//input[@type='password']")
	private WebElement customer_password;

	@FindBy(xpath = "//input[@id='address1']")
	private WebElement txt_address;
	
	@FindBy(xpath = "//input[@id='city']")
	private WebElement txt_city;
	
	@FindBy(id = "id_state")
	private WebElement stateDropdown;
	
	@FindBy(xpath = "//input[@id='postcode']")
	private WebElement txt_postCode;
	
	@FindBy(xpath = "//input[@id='phone_mobile']")
	private WebElement txt_mobile;
	
	@FindBy(xpath = "//input[@id='alias']")
	private WebElement addr_alias;
	
	@FindBy(xpath = "//button[@id='submitAccount']")
	private WebElement button_submitAccount;
	
	@FindBy(xpath = "//a[@class='account']")
	private WebElement link_accountName;
	
	@FindBy(xpath = "//a[@class='logout']")
	private WebElement link_logout;
	
	@FindBy(xpath = "//input[@id='email']")
	private WebElement txt_email;
	
	@FindBy(xpath = "//input[@id='passwd']")
	private WebElement txt_password;
	
	@FindBy(xpath = "//button[@id='SubmitLogin']")
	private WebElement btn_login;
	
	@FindBy(xpath = "//h3[contains(text(),'Already registered?')]")
	private WebElement heading_page;
	
	WebDriver driver;
	WebDriverWait wait;
	
	public AccountPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void createAccount()
	{
		wait = new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.elementToBeClickable(email_address));
		Random randomGenerator = new Random();
		int randomInt = randomGenerator.nextInt(1000);
		email_address.sendKeys("username"+ randomInt+"@dokti.com");
		button_createAccount.click();
	}
	
	public void RegisterUser(String state)
	{
		wait = new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.elementToBeClickable(title));
		Random random = new Random();
		int addr = random.nextInt(1000);
		String addr1 = Integer.toString(addr);
		title.click();
	    customer_firstName.sendKeys("Test");
	    customer_lastName.sendKeys("Customer");
	    customer_password.sendKeys("dokitir708");
	    txt_address.sendKeys(addr1);
		txt_city.sendKeys("Chicago");
		Select selectList = new Select(stateDropdown);
		selectList.selectByVisibleText(state);
		txt_postCode.sendKeys("60601");
		txt_mobile.sendKeys("8634685763");
		addr_alias.sendKeys(addr1);
		button_submitAccount.click();
	}
	
	public String validateMyAccountPage()
	{
		String expectedUserAccountTitle = driver.getTitle();
		return expectedUserAccountTitle;
	}
	
	public void validateAccountUserName(String FirstName, String LastName)
	{
		String UserName = FirstName + " " + LastName;
		String AccountName = link_accountName.getText();
		Assert.assertEquals(UserName, AccountName);
	}
	
	public void logoutAccount()
	{
		link_logout.click();
		String actual = driver.getTitle();
		Assert.assertEquals(actual,"Login - My Store");
	}
	
	public void loginAccount()
	{
		wait = new WebDriverWait(driver,2000);
		wait.until(ExpectedConditions.visibilityOf(heading_page));
		txt_email.sendKeys("dotikir807@lidte.com");
		txt_password.sendKeys("dotikir807");
		btn_login.click();
		String expected = "My account - My Store";
		String actual = driver.getTitle();
		Assert.assertEquals(actual, expected,"Invalid Account Title");
	}
	
}
