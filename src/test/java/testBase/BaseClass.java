package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {

	public static WebDriver driver;
	public Logger logger;// Log4j
	public Properties p;

	@BeforeClass(groups = { "Sanity", "Regression", "Master" })
	@Parameters({ "browser", "os" })
	public void setup(String browser, String os) throws IOException {

		// reading the data from properties file
		// reading the file
		FileReader file = new FileReader("./src/test/resources/config.properties");
		p = new Properties();
		p.load(file);

		if (p.getProperty("execution_env").equalsIgnoreCase("remote")) { // this setup is only for Selenium Grid 

			DesiredCapabilities capabilities = new DesiredCapabilities();

			// env
			if (os.equalsIgnoreCase("windows")) {
				capabilities.setPlatform(Platform.WIN10);
			} else if (os.equalsIgnoreCase("mac")) {
				capabilities.setPlatform(Platform.MAC);
			} else {
				System.out.println("OS is not acceptable");
				return;
			}

			// browser
			switch (browser.toLowerCase()) {
			case "chrome":
				capabilities.setBrowserName("chrome");
				break;
			case "edge":
				capabilities.setBrowserName("MicrosoftEdge");
				break;
			default:
				System.out.println("Browser is not acceptable");
				return;
			}

			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);

		}

		if (p.getProperty("execution_env").equalsIgnoreCase("local")) { 

			switch (browser.toLowerCase()) {
			case "chrome":
				driver = new ChromeDriver();
				break;
			case "edge":
				driver = new EdgeDriver();
				break;
			case "firefox":
				driver = new FirefoxDriver();
				break;
			default:
				System.out.println("Invalid browser...");
				return;
			}

		}

		logger = LogManager.getLogger(this.getClass());

		driver.manage().deleteAllCookies();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(p.getProperty("app_Url"));
		driver.manage().window().maximize();

		// log4j configuration
	}

	@AfterClass(groups = { "Sanity", "Regression", "Master" })
	public void tearDown() {
		driver.quit();
	}

	@SuppressWarnings("deprecation")
	public String randomString() {
		return RandomStringUtils.randomAlphabetic(5);
	}

	@SuppressWarnings("deprecation")
	public String randomNumber() {
		return RandomStringUtils.randomNumeric(10);
	}

	public String randomPassword() {
		return (randomString() + randomNumber() + "@");
	}

	public String captureScreenshot(String tName) {

		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

		TakesScreenshot takescreenshot = (TakesScreenshot) driver;
		File sourceFile = takescreenshot.getScreenshotAs(OutputType.FILE);

		String targetfilePath = System.getProperty("user.dir") + "//screenshots//" + tName + "_" + timeStamp + ".png";
		File targetFile = new File(targetfilePath);

		sourceFile.renameTo(targetFile); // to copy the source file to target file
											// Effectively, it moves the screenshot file from its temporary location to
											// the final destination where it will be saved.

		return targetfilePath;
	}
}
