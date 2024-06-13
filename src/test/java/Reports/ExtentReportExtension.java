package Reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.junit.jupiter.api.extension.*;

import java.io.File;

public class ExtentReportExtension implements BeforeAllCallback, AfterAllCallback, BeforeEachCallback, AfterEachCallback  {

    private static ExtentReports extentReports;
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
    private static ExtentSparkReporter sparkReporter;

    @Override
    public void beforeAll(ExtensionContext context) {
        sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir")+ File.separator + "reports"+ File.separator + "ExtentAutomationReport.html");
        sparkReporter.config().setEncoding("utf-8");
        sparkReporter.config().setDocumentTitle("Automation Report");
        sparkReporter.config().setReportName("Automation Test Results");
        sparkReporter.config().setTheme(Theme.DARK);
        extentReports = new ExtentReports();
        extentReports.attachReporter(sparkReporter);
        extentReports.setSystemInfo("Automation Tester", "AutoBot");
    }

    @Override
    public void afterAll(ExtensionContext context) throws Exception {
        extentReports.flush();
    }

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        extentTest.set(extentReports.createTest(context.getDisplayName()));
    }

    @Override
    public void afterEach(ExtensionContext context) throws Exception {
        if (context.getExecutionException().isPresent()) {
            extentTest.get().fail(context.getExecutionException().get());
        } else {
            extentTest.get().pass("Test passed");
        }
        extentReports.flush();
    }



    public static ExtentTest getExtentTest() {
        return extentTest.get();
    }

}
