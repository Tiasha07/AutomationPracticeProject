package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ShoppingPage {
	
	@FindBy(xpath = "//a[@title='Women']")
	private WebElement women_Category;

	@FindBy(xpath = "//div[@class='rte']/p/strong")
	private WebElement women_Page_Text;
	
	@FindBy(xpath = "//input[@name='layered_category_4']")
	private WebElement women_FilterByTop;
	
	@FindBy(xpath = "//input[@name='layered_id_attribute_group_1']")
	private WebElement women_FilterBySize;
	
	@FindBy(xpath = "//input[@name='layered_id_attribute_group_11']")
	private WebElement women_FilterByColor;
	
	@FindBy(xpath = "(//a[@title='Blouse'] | //a[contains(text(),'Blouse')])[4]")
	private WebElement women_Blouse;
	
	
	WebDriver driver;
	
	public ShoppingPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public String fetchWomenCategoryLandingPageText()
	{
		String pageText = women_Page_Text.getText();
		return pageText;
	}
	
	public void clickWomenCategory()
	{
		women_Category.click();
	}
	
	public void addWomenFilters()
	{
		women_FilterByTop.click();
		women_FilterBySize.click();
		women_FilterByColor.click();
	}
	
	public void clickwomenBlouse()
	{
		women_Blouse.click();
	}
}
