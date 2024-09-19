package utility.api;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportWithListener implements ITestListener{

	ExtentSparkReporter createReport;
	ExtentReports Addtest;
	ExtentTest Addlog;

	void setup() {
		createReport = new ExtentSparkReporter("ApiSwaggerReport.html");
		Addtest = new ExtentReports();
		Addtest.attachReporter(createReport);

		Addtest.setSystemInfo("OperatingSystem", "Windows");
		Addtest.setSystemInfo("Browser", "Chrome");
		Addtest.setSystemInfo("Browser_Version", "127.23.45.11");
		Addtest.setSystemInfo("Environment", "Test");
		Addtest.setSystemInfo("User", "Anjali Tripathi");

		createReport.config().setDocumentTitle("MYTESTREPORT");
		createReport.config().setReportName("RESTASSUREDREPORT");
		createReport.config().setTheme(Theme.STANDARD);

	}

	public void onTestStart(ITestResult result) {
		// not implemented
	}

	public void onTestSuccess(ITestResult result) {

		Addlog = Addtest.createTest(result.getName());
		Addlog.log(Status.PASS, MarkupHelper.createLabel("Test case passed", ExtentColor.GREEN));

	}

	public void onTestFailure(ITestResult result) {

		Addlog = Addtest.createTest(result.getName());
		Addlog.log(Status.FAIL, MarkupHelper.createLabel("Test case failed", ExtentColor.RED));

	}

	public void onTestSkipped(ITestResult result) {

		Addlog = Addtest.createTest(result.getName());
		Addlog.log(Status.SKIP, MarkupHelper.createLabel("Test case skipped", ExtentColor.ORANGE));

	}

	public void onStart(ITestContext context) {

		setup();

	}

	public void onFinish(ITestContext context) {

		Addtest.flush();

	}
	
}
