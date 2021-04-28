package pages;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class CartPage {
	
	WebDriver driver;
	
	@FindBy(xpath = "//b[contains(text(),'Cart')]")
	private WebElement button_cart;
	
	@FindBy(xpath = "//input[@id='email']")
	private WebElement text_email;
	
	@FindBy(xpath = "//input[@id='passwd']")
	private WebElement text_password;
	
	@FindBy(xpath = "//button[@id='SubmitLogin']")
	private WebElement button_signIn;
	
	@FindBy(xpath = "//span[contains(text(),'Add a new address')]")
	private WebElement button_newAddress;
	
	@FindBy(xpath = "//input[@name='address1']")
	private WebElement text_Address;
	
	@FindBy(xpath = "//input[@name='city']")
	private WebElement text_City;
	
	@FindBy(id = "id_state")
	private WebElement stateDropdown;
	
	@FindBy(xpath = "//input[@name='postcode']")
	private WebElement text_PostCode;
	
	@FindBy(xpath = "//input[@id='phone']")
	private WebElement text_homePhone;
	
	@FindBy(xpath = "//input[@id='phone_mobile']")
	private WebElement text_mobilePhone;
	
	@FindBy(xpath = "//input[@id='alias']")
	private WebElement text_alias;
	
	@FindBy(xpath = "//span[contains(text(),'Save')]")
	private WebElement button_save;
	
	@FindBy(xpath = "//input[@id=\"cgv\"]")
	private WebElement checkbox_shippingagreement;
	
	@FindBy(xpath = "//a[contains(text(),'Pay by bank wire')]")
	private WebElement link_PayByBankWire;
	
	@FindBy(xpath = "//span[contains(text(),'I confirm my order')]")
	private WebElement link_Confirm;
	
	@FindBy(xpath = "//strong[contains(text(),'Your order on My Store is complete.')]")
	private WebElement text_OrderComplete;
	
	@FindBy(xpath = "//a[@title='Back to orders']")
	private WebElement button_backToOrders;
	
	@FindBy(xpath = "(//span[contains(text(),'Details')])[1]")
	private WebElement productDetail;
	
	@FindBy(xpath = "//button[@name='submitNewsletter']")
	private WebElement button_newsletterSubmit;
	
	@FindBy(xpath = "//p[@class='alert alert-danger']")
	private WebElement msg_alert;

	
	public void addAddress(String state)
	{
		Random random = new Random();
		int addr = random.nextInt(1000);
		String addr1 = Integer.toString(addr);
		button_newAddress.click();
		text_Address.sendKeys(addr1);
		text_City.sendKeys("ABC");
		Select selectList = new Select(stateDropdown);
		selectList.selectByVisibleText(state);
		text_PostCode.sendKeys("60601");
		text_homePhone.sendKeys("8745678379");
		text_mobilePhone.sendKeys("8634685763");
		text_alias.sendKeys(addr1);
		button_save.click();
	}
	
	public void clickonShippingAgreement()
	{
		checkbox_shippingagreement.click();
	}
	
	public CartPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void ClickonCart()
	{	
		button_cart.click();
	}
	
	public void Login(String email, String password)
	{
		text_email.sendKeys(email);
		text_password.sendKeys(password);
		button_signIn.click();
	}

	public void clickonPaymentMode()
	{
		link_PayByBankWire.click();
	}
	
	public void clickonConfirm()
	{
		link_Confirm.click();
	}
	
	public String getConfirmOrderText()
	{
		String actual_txt_cnfrm = text_OrderComplete.getText();
		return actual_txt_cnfrm;
	}
	
	public void validateErrorMessage()
	{
		button_newsletterSubmit.click();
		String actualError = msg_alert.getText();
		Assert.assertEquals(actualError, "Newsletter : Invalid email address.", "Incorrect Error Message");
	}
	
}
