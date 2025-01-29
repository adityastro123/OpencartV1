package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchPage extends BasePage {
	
	public SearchPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "//h2[contains(text(),'Products meeting the search criteria')]")
	private WebElement productSearchHeading;
	
	@FindBy(xpath = "//div[@class='product-thumb']")
	private WebElement product;
	
	public boolean doesSearchPageExist() {
		try {
			return productSearchHeading.isDisplayed();
		}catch(Exception e) {
			return false;
		}
	}
	
	//action method for product search availability --> todo
	public boolean isProductAvailable() {
		try {
			return product.isDisplayed();
		}catch(Exception e) {
			return false;
		}
	}
}
