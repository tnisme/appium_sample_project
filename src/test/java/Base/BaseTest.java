package Base;

import com.example.Dirvers.AndroidDriverManager;
import com.example.Dirvers.IOSDriverManager;
import com.example.Utilities.DataTest;
import com.example.Utilities.ReportUtility;
import io.appium.java_client.AppiumDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;
import com.relevantcodes.extentreports.LogStatus;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BaseTest {
    private static final ThreadLocal<String> className = new ThreadLocal<>();
    public static ThreadLocal<AppiumDriver> driver = new ThreadLocal<>();
    private final static String USER_DIR = System.getProperty("user.dir");

    public synchronized static AppiumDriver getDriver() {
        return driver.get();
    }

    @BeforeSuite
    public void setSuite() {
        String suiteName = org.testng.Reporter.getCurrentTestResult().getTestContext().getSuite().getXmlSuite().getName();
        ReportUtility.init(suiteName);
    }

    @BeforeTest
    @Parameters("platformName")
    public void setUp(@Optional("android") String platform) {
        initDriver(platform);
        DataTest.init();
    }

    @BeforeClass
    public void beforeClass() {
        preCondition();
        className.set(getClass().getSimpleName());
        ReportUtility.getInstance().startTest(className.get());
    }

    @BeforeMethod
    public void setUpBeforeMethod(Method method) {
        ReportUtility.getInstance().log(LogStatus.INFO, "<b>Start method: " + method.getName() + "</b>");
    }

    @AfterMethod
    public void afterMethod(ITestResult result, Method method) throws IOException {
        String methodName = method.getName();
        if (result.getStatus() == ITestResult.FAILURE) {
            ReportUtility.getInstance().log(LogStatus.FAIL, "Test case: " + methodName + " failed");
            ReportUtility.getInstance().log(LogStatus.FAIL, result.getThrowable().toString());
            captureScreenshot(methodName);
        } else if (result.getStatus() == ITestResult.SKIP) {
            ReportUtility.getInstance().log(LogStatus.SKIP, "Test case: " + methodName + " skipped");
        } else {
            ReportUtility.getInstance().log(LogStatus.PASS, "Test case: " + methodName + " passed");
        }
    }

    @AfterClass
    public void afterClass() throws TimeoutException {
        ReportUtility.getInstance().flush();
    }

    @AfterTest
    public void tearDown() {
        if (getDriver() != null) {
            getDriver().quit();
        }
    }

    private void initDriver(String platform) {
        if (platform.equalsIgnoreCase("android")) {
            driver.set(AndroidDriverManager.getDriver());
        } else if (platform.equalsIgnoreCase("ios")) {
            driver.set(IOSDriverManager.getDriver());
        }
    }

    protected void preCondition() {
        // Implement pre-condition
    }

    private void captureScreenshot(String methodName) throws IOException {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String fileName = methodName + "_" + timestamp + ".png";
        String screenshotPath = USER_DIR + File.separator + "test-output" + File.separator + "report" + File.separator + "screenshots" + File.separator + fileName;
        File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(srcFile, new File(screenshotPath));
        ReportUtility.getInstance().addScreenCapture(LogStatus.FAIL, screenshotPath);
    }
}
