package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegisterPage extends BasePage{
	
	public RegisterPage(WebDriver driver){
		super(driver);
	}
	
	@FindBy(xpath = "//input[@id='input-firstname']")
	private WebElement firstNameInput;
	
	@FindBy(xpath = "//input[@id='input-lastname']")
	private WebElement lastNameInput;
	
	@FindBy(xpath = "//input[@id='input-email']")
	private WebElement emailInput;
	
	@FindBy(xpath = "//input[@id='input-telephone']")
	private WebElement telephoneInput;
	
	@FindBy(xpath = "//input[@id='input-password']")
	private WebElement passwordInput;
	
	@FindBy(xpath = "//input[@id='input-confirm']")
	private WebElement passowrdConfirmInput;
	
	@FindBy(xpath = "//input[@type='checkbox']")
	private WebElement agreementCheckbox;
	
	@FindBy(xpath = "//input[@type='submit']")
	private WebElement submitBtn;
	
	@FindBy(xpath = "//h1[normalize-space() = 'Your Account Has Been Created!']")
	private WebElement confMessage;
	
	
	public void giveFirstName(String firstName) {
		firstNameInput.sendKeys(firstName);
	}
	
	public void giveLastName(String lastName) {
		lastNameInput.sendKeys(lastName);
	}
	
	public void giveEmail(String email) {
		emailInput.sendKeys(email);
	}
	
	public void giveTelephoneNumber(String telephoneNumber) {
		telephoneInput.sendKeys(telephoneNumber);
	}
	
	public void givePassowrd(String password) {
		passwordInput.sendKeys(password);
	}
	
	public void giveConfirmPassword(String confirmationPassword) {
		passowrdConfirmInput.sendKeys(confirmationPassword);
	}
	
	public void clickOnAgreementCheckBox() {
		agreementCheckbox.click();
	}
	
	public void clickOnSubmit() {
		submitBtn.click();
	}
	
	public String getConfMsg() {
		try {
			return confMessage.getText();
		}catch(Exception e) {
			return e.getMessage();
		}
	}
}
