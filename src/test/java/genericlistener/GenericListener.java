package genericlistener;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import framework.ConfigReader;
import framework.DriverSetup;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.*;
import org.testng.xml.XmlSuite;
import pageobjects.CreateAnAccount;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Slf4j
public class GenericListener implements ITestListener, IReporter {


    ExtentHtmlReporter htmlReporter;

    ExtentReports extent;
    //helps to generate the logs in the test report.
    ExtentTest test;

    public void onTestStart(ITestResult iTestResult) {
        log.info("onTestStart for " + iTestResult);
        if (iTestResult.getMethod().isBeforeClassConfiguration()) {
            log.info(iTestResult.getMethod().getMethodName());
        }
   /*     test = extent.createTest(iTestResult.getMethod().getMethodName()+" "+
                iTestResult.getMethod().getDescription(), String.valueOf(iTestResult.getStatus()));
*/
    }

    public void onTestSuccess(ITestResult iTestResult) {
        log.info("onTestStartSucces for " + iTestResult);
        test = extent.createTest(iTestResult.getTestName() + " " +
                iTestResult.getMethod().getDescription(), String.valueOf(iTestResult.getStatus()));
        test.log(Status.PASS,
                "User with "+CreateAnAccount.getUser().getFirstName() +
                        " " + CreateAnAccount.getUser().getLastName() +
                        " " + CreateAnAccount.getUser().getEmail() +
                        " " + CreateAnAccount.getUser().getPassword()+ " is created" );

    }

    public void onTestFailure(ITestResult iTestResult) {
        log.info("onTestFailure for " + iTestResult);
        test = extent.createTest(iTestResult.getTestName() + " " +
                iTestResult.getMethod().getDescription(), String.valueOf(iTestResult.getStatus()));
        test.log(Status.FAIL, iTestResult.getThrowable());
    }

    public void onStart(ITestContext iTestContext) {
        try {
            DriverSetup.driver = new RemoteWebDriver(new URL("http:localhost:4444/wd/hub"), DesiredCapabilities.chrome());
            DriverSetup.driver.manage().window().maximize();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        //DriverSetup.driver.manage().window().maximize();
        //DriverSetup.initialize_Driver(ConfigReader.initialize_Properties().get("browser").toString());
        //DriverSetup.driver = DriverSetup.initialize_Driver(ConfigReader.initialize_Properties().get("browser").toString());
        /*
        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-output/testReport2.html");

        //initialize ExtentReports and attach the HtmlReporter
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);

        log.info("OnStart for " + iTestContext);
        log.info(iTestContext.getHost());
        log.info(iTestContext.getName());
        log.info(String.valueOf(iTestContext.getStartDate()));
        */
      /*  test = extent.createTest(iTestContext.getName()+" "+
                iTestContext.getStartDate(), String.valueOf(iTestContext.getEndDate()));
*/
    }

    public void onFinish(ITestContext iTestContext) {
        log.info(String.valueOf(iTestContext.getFailedTests()));
        iTestContext.getFailedTests().getAllMethods().stream()
                .forEach(i -> {
                    test = extent.createTest(i.getMethodName() + " " +
                            i.getDescription(), "FAIL");

                });


    }

    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {


        //configuration items to change the look and feel
        //add content, manage tests etc
        /*htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setDocumentTitle("Simple Automation Report");
        htmlReporter.config().setReportName("Test Report");
        htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
        htmlReporter.config().setTheme(Theme.DARK);
        htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");


        extent.flush();*/
    }


    private Date getTime(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();
    }

}
