package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {
	public WebDriver driver;

	public HomePage (WebDriver driver){
		super(driver);
	}
	
	//Locators
	@FindBy(xpath = "//div[@id='top-links']/ul/li[2]/a")
	private WebElement myAccLink;
	
	@FindBy(xpath = "//a[contains(text(),'Register')]")
	private WebElement registerLink;
	
	@FindBy(xpath = "//a[contains(text(),'Login')]")
	private WebElement loginLink;
	
	//Action Methods
	public void clickMyAccount() {
		myAccLink.click();
	}
	
	public void clickRegisterLink() {
		registerLink.click();
	}
	
	public void clickLoginLink() {
		loginLink.click();
	}
	
}
