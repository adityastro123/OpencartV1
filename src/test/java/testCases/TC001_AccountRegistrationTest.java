package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.RegisterPage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {

	@Test(groups= {"Regression", "Master"})
	public void verify_account_registration() {
		logger.info("**********  Starting TC001_AccountRegistrationTest  **********************");

		try {
			HomePage hp = new HomePage(driver);

			hp.clickMyAccount();
			hp.clickRegisterLink();

			RegisterPage rp = new RegisterPage(driver);

			logger.info("Giving customer details");

			rp.giveFirstName(randomString().toUpperCase());
			rp.giveLastName(randomString().toUpperCase());
			rp.giveEmail(randomString() + "@gmail.com");
			rp.giveTelephoneNumber(randomNumber());

			String pass = randomPassword();
			rp.givePassowrd(pass);
			rp.giveConfirmPassword(pass);

			rp.clickOnAgreementCheckBox();
			rp.clickOnSubmit();

			logger.info("Validating the account creation message");
			String confMessage = rp.getConfMsg();
			if (confMessage.equals("Your Account Has Been Created!")) {

				Assert.assertTrue(true);

			} else {

				logger.error("Test Failed..");
				Assert.assertTrue(false);

			}
			// Assert.assertEquals(confMessage, "Your Account Has Been Created!",
			// "Registration Failed!!");
		} catch (Exception e) {
			logger.debug("Debug logs...");
			Assert.fail();

		}

		logger.info("**********  Finished TC001_AccountRegistrationTest  **********************");
	}

}
