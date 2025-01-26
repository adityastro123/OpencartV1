package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
	public WebDriver driver;

	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "//input[@id= 'input-email']")
	private WebElement emailInput;
	
	@FindBy(xpath = "//input[@id= 'input-password']")
	private WebElement passwordInput;
	
	@FindBy(xpath = "//input[@type='submit']")
	private WebElement loginBtn;
	
	
	public void giveEmail(String email) {
		emailInput.sendKeys(email);
	}
	
	public void givePassword(String password) {
		passwordInput.sendKeys(password);
	}
	
	public void clickLoginBtn() {
		loginBtn.click();
	}
}
