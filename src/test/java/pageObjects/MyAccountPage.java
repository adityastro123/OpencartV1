package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {

	public MyAccountPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//h2[text() = 'My Account']")
	private WebElement loginMsg;

	@FindBy(xpath = "(//a[text()='Logout'])[2]")
	private WebElement logutBtn;

	public boolean isAccountPageExist() {
		try {
			return loginMsg.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public void clickLogout() {
		logutBtn.click();
	}
}
