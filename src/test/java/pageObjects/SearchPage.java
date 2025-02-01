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

	@FindBy(xpath = "//div[@class='button-group']//button[1]")
	private WebElement addToCartBtn;

	@FindBy(xpath = "//ul[@class='breadcrumb']//following-sibling::div")
	private WebElement addToCartSuccessAlert;

	@FindBy(xpath = "//span[text()='Shopping Cart']")
	private WebElement shoppingCartBtn;

	public boolean doesSearchPageExist() {
		try {
			return productSearchHeading.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	// action method for product search availability --> todo
	public boolean isProductAvailable() {
		try {
			return product.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public void addToCart() {
		addToCartBtn.click();
	}

	public boolean isProductAddedToCart() {
		return addToCartSuccessAlert.isDisplayed();
	}

	public void clickOnShoppingCartBtn() {
		shoppingCartBtn.click();
	}
}
