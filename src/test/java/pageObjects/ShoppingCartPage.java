package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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

	public boolean doesShoppingCartPageExist() {
		try {
			return shoppingCartHeading.isDisplayed();
		} catch (Exception e) {
			System.out.println("error occured: "+ e.getMessage());
			return false;
		}
	}

	public boolean isShoppingCartEmpty() {
		try {
			return shoppingCartEmptyMessage.isDisplayed();
		} catch (Exception e) {
			System.out.println("error occured: "+ e.getMessage());
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
			System.out.println("error occured: "+ e.getMessage());
			return false;
		}
	}

}
