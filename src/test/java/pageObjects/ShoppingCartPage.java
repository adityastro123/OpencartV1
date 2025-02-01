package pageObjects;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ShoppingCartPage extends BasePage {

	public ShoppingCartPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//h1[contains(text(),'Shopping Cart')]")
	private WebElement shoppingCartHeading;

	@FindBy(xpath = "(//p[text()='Your shopping cart is empty!'])[2]")
	private WebElement shoppingCartEmptyMessage;

	@FindBy(xpath = "//div[@id='content']//form//table")
	private WebElement productTable;

	@FindBy(xpath = "//button[@type='submit']")
	private WebElement addMoreProductBtn;

	@FindBy(xpath = "//button[@type='submit']//following-sibling::button")
	private WebElement removeProductBtn;

	@FindBy(xpath = "//div[contains(text(),'Success: You have modified your shopping cart!')]")
	private WebElement productUpdateAlert;

	public boolean doesShoppingCartPageExist() {
		try {
			return shoppingCartHeading.isDisplayed();
		} catch (Exception e) {
			System.out.println("error occured: " + e.getMessage());
			return false;
		}
	}

	public boolean isShoppingCartEmpty() {
		try {
			return shoppingCartEmptyMessage.isDisplayed();
		} catch (Exception e) {
			System.out.println("error occured: " + e.getMessage());
			return true;
		}
	}

	public boolean isProductAddedInCart(String pname) {

		try {
			List<WebElement> products = productTable.findElements(By.xpath(".//td[2]//a"));
			for (WebElement product : products) {
				if (product.getText().equalsIgnoreCase(pname)) {
					return true;
				}
			}

			return false;

		} catch (Exception e) {
			System.out.println("error occured: " + e.getMessage());
			return false;
		}
	}

	public void addMoreProduct() {
		addMoreProductBtn.click();
	}

	public void removeProductFromCart() {
		removeProductBtn.click();
	}

	public boolean isProductAdded() {
		try {			
			return productUpdateAlert.isDisplayed();
		} catch (Exception e) {
			System.out.println("error occured: " + e.getMessage());
			return false;
		}
	}
}
