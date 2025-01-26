package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass {

	@Test(groups= {"Sanity", "Master"})
	public void loginTest() {
		logger.info("*********** Starting TC002_LoginTest **************");

		try {

			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			hp.clickLoginLink();

			logger.info("Logging");

			LoginPage lp = new LoginPage(driver);
			lp.giveEmail(p.getProperty("email"));
			lp.givePassword(p.getProperty("password"));
			lp.clickLoginBtn();

			MyAccountPage mp = new MyAccountPage(driver);

//			if(mp.isAccountPageExist()) {
//				logger.info("Login Successful");
//			}else {
//				logger.error("Login Unsuccessful...");
//				
//			}

			Assert.assertTrue(mp.isAccountPageExist());

		} catch (Exception e) {
			logger.error("Login Unsuccessful" + e.getMessage());
			Assert.fail();
		}

		logger.info("********************* Finished TC002_LoginTest **********************");
	}
}
