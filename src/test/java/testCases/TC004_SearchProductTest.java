package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.SearchPage;
import testBase.BaseClass;

public class TC004_SearchProductTest extends BaseClass {

	@Test
	public void f() {
		logger.info("******* Starting TC004_SearchProductTest *******");
		
		try {
		HomePage hm = new HomePage(driver);
		hm.productSearch(p.getProperty("searchProductName"));
		hm.clickSearchBtn();
		
		SearchPage sp = new SearchPage(driver);
		
		//checking if the search page exists
		Assert.assertTrue(sp.doesSearchPageExist(), "Search Page doesn't exist");
		
		//check if the searched product available or not
		
		
		}catch(Exception e) {
			Assert.fail();
		}
		
		logger.info("*********** Finished TC004_SearchProductTest *************");
	}
}
