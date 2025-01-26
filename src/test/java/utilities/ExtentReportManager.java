package utilities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener {

	public ExtentSparkReporter spark; // UI of the report
	public ExtentReports extent; // populate the common info on the report
	public ExtentTest test; // creating test case entries in report and update status in the test methods

	String repName;

	public void onStart(ITestContext context) { // context will capture the current test details which got executed

		/*
		 * SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss"); //
		 * creating the format in which the date should be Date date = new Date(); //
		 * this 'date' object will have the info of the current date and time String
		 * currentDateTime = df.format(date); // calling the format() on the
		 * SimpleDateFormat Obj and passing the date so that it can format the date in
		 * which we have defined
		 */

		// or

		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

		repName = "Test-Report-" + timeStamp + ".html";

		spark = new ExtentSparkReporter("./reports/" + repName);

		spark.config().setDocumentTitle("Automation Report");
		spark.config().setReportName("Functional Testing");
		spark.config().setTheme(Theme.STANDARD);

		extent = new ExtentReports();

		extent.attachReporter(spark);
		extent.setSystemInfo("Application", "OpenCart");
		extent.setSystemInfo("Test Environment", "QA");
		extent.setSystemInfo("Tester Name", System.getProperty("user.name"));
		extent.setSystemInfo("System Name", "localhost");

		String os = context.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("OS", os);

		String browser = context.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("browser", browser);

		List<String> includedGroups = context.getCurrentXmlTest().getIncludedGroups();
		if (!includedGroups.isEmpty()) {
			extent.setSystemInfo("Groups Included", includedGroups.toString());
		}
	}

	public void onTestSuccess(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups()); // to display groups in the report
		test.log(Status.PASS, "Test case PASSED is " + result.getName());
	}

	public void onTestFailure(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());

		test.log(Status.FAIL, "Test case FAILED is " + result.getName());
		test.log(Status.INFO, "Test FAILED because of " + result.getThrowable());

		try {
			String imgPath = new BaseClass().captureScreenshot(result.getTestName());
			test.addScreenCaptureFromPath(imgPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onTestSkipped(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());

		test.log(Status.SKIP, "Test case SKIPPED is " + result.getName());
	}

	public void onFinish(ITestContext context) {
		extent.flush();
	}
}
