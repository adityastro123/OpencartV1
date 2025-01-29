package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchPage extends BasePage {
	
	public SearchPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "//div[@id='content']//h1")
	private WebElement productSearchHeading;
	
	public boolean doesSearchPageExist() {
		try {
			return productSearchHeading.isDisplayed();
		}catch(Exception e) {
			return false;
		}
	}
	
	//action method for product search availability --> todo
}
