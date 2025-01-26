package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC003_LoginDDT extends BaseClass {

	@Test(dataProvider = "Login", dataProviderClass = utilities.DataProviders.class, groups = {"DataDriven"})
	public void login_DDT(String email, String password, String expectedResult) {

		logger.info("***** Starting TC003_LoginDDT *******");

		try {
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			hp.clickLoginLink();

			logger.info("Logging");

			LoginPage lp = new LoginPage(driver);
			lp.giveEmail(email);
			lp.givePassword(password);
			lp.clickLoginBtn();

			MyAccountPage mp = new MyAccountPage(driver);
			boolean isMyAccountVisible = mp.isAccountPageExist();

			// Validation

			/*
			 * Valid data - Login Successful => Passed Test 
			 *            - Login Unsuccessful => Failed Test 
			 * Invalid Data - Login Successful => Failed Test
			 *              - Login Unsuccessful => Passed Test
			 */

			if (expectedResult.equalsIgnoreCase("Valid")) {
				if (isMyAccountVisible == true) {
					mp.clickLogout();
					Assert.assertTrue(true, "Login successful with VALID credentials");
				} else {
					Assert.assertTrue(false, "Login unsuccessful with VALID credentials");
				}
			}

			if (expectedResult.equalsIgnoreCase("Invalid")) {
				if (isMyAccountVisible == true) {
					mp.clickLogout();
					Assert.assertTrue(false, "Login successful with INVALID credentials");
				} else {
					Assert.assertTrue(true, "Login unsuccessful with INVALID credentials");
				}
			}
		} catch (Exception e) {
			logger.error("Test Failed with the exception " + e.getMessage());
			Assert.fail();
		}

		logger.info("***** Finished TC003_LoginDDT *****");

	}

}
