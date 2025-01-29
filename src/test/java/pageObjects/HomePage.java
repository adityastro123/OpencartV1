package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

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
	
	@FindBy(xpath = "//div[@id='search']//input")
	private WebElement searchProductInput;
	
	@FindBy(xpath = "//div[@id='search']//following-sibling::span//button")
	private WebElement productSearchBtn;
	
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
	
	public void productSearch(String productName) {
		searchProductInput.sendKeys(productName);
	}
	
	public void clickSearchBtn() {
		productSearchBtn.click();
	}
	
}
