package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.SearchPage;
import pageObjects.ShoppingCartPage;
import testBase.BaseClass;


public class TC005_AddToCartTest extends BaseClass {
	@Test
	public void addToCart() {
		logger.info("************* Started TC005_AddToCartTest **************");

		try {
			
			HomePage hm = new HomePage(driver);
			hm.productSearch(p.getProperty("searchProductName"));
			hm.clickSearchBtn();

			SearchPage sp = new SearchPage(driver);

			// checking if the search page exists
			Assert.assertTrue(sp.doesSearchPageExist(), "Search Page doesn't exist");

			// check if the searched product available or not
			Assert.assertTrue(sp.isProductAvailable(), "Product not available");
			
			sp.addToCart();
			sp.clickOnShoppingCartBtn();

			ShoppingCartPage scp = new ShoppingCartPage(driver);

			// checking if the shopping page exist or not
			Assert.assertTrue(scp.doesShoppingCartPageExist(), "Shopping Cart page doesn't exist");

			// checking if the shopping is empty or not
			Assert.assertTrue(scp.isShoppingCartEmpty(), "Shopping Cart is Empty!!");

			// checking if the product is successfully added into the cart or not
			Assert.assertTrue(scp.isProductAddedInCart(p.getProperty("searchProductName")),
					"Product is not added in the cart!!");
			
			
			//adding one more of the same product in the cart
			scp.addMoreProduct(); //now there are two of the same product
			
			Assert.assertTrue(scp.isProductAdded(), "No extra Product added!");
			logger.info("Product added");
			
			Thread.sleep(3000);
			
			//remove the product from the cart //faulty update button- not adding extra product
			scp.removeProductFromCart();
			
			//checking if the product is removed from the cart
			Assert.assertTrue(scp.isShoppingCartEmpty(), "Product not removed from the cart!!");
			logger.info("Product removed");
			
		} catch (Exception e) {
			logger.error("Error occured: ", e.getMessage());
			Assert.fail();
		}

		logger.info("************ Finished TC005_AddToCartTest ***************");
	}
}
