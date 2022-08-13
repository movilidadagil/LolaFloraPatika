package loglistener;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.relevantcodes.extentreports.LogStatus;
import lombok.extern.slf4j.Slf4j;
import org.testng.*;
import org.testng.xml.XmlSuite;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Slf4j
public class GenericListener implements ITestListener, IReporter {


    ExtentHtmlReporter htmlReporter;

    ExtentReports extent;
    //helps to generate the logs in the test report.
    ExtentTest test;

    public void onTestStart(ITestResult iTestResult) {
        log.info("onTestStart for "+ iTestResult);
   /*     test = extent.createTest(iTestResult.getMethod().getMethodName()+" "+
                iTestResult.getMethod().getDescription(), String.valueOf(iTestResult.getStatus()));
*/
    }

    public void onTestSuccess(ITestResult iTestResult) {
        log.info("onTestStartSucces for "+ iTestResult);
        test = extent.createTest(iTestResult.getTestName()+" "+
                iTestResult.getMethod().getDescription(), String.valueOf(iTestResult.getStatus()));

    }

    public void onStart(ITestContext iTestContext) {
        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") +"/test-output/testReport.html");

        //initialize ExtentReports and attach the HtmlReporter
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);

        log.info("OnStart for "+ iTestContext);
        log.info(iTestContext.getHost());
        log.info(iTestContext.getName());
        log.info(String.valueOf(iTestContext.getStartDate()));
      /*  test = extent.createTest(iTestContext.getName()+" "+
                iTestContext.getStartDate(), String.valueOf(iTestContext.getEndDate()));
*/
    }

    public void onFinish(ITestContext iTestContext) {
        log.info(String.valueOf(iTestContext.getFailedTests()));
        iTestContext.getFailedTests().getAllMethods().stream()
                .forEach(i->{
                    test = extent.createTest(i.getMethodName()+" "+
                            i.getDescription(), "FAIL");

                });



    }

    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {



        //configuration items to change the look and feel
        //add content, manage tests etc
        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setDocumentTitle("Simple Automation Report");
        htmlReporter.config().setReportName("Test Report");
        htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
        htmlReporter.config().setTheme(Theme.DARK);
        htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");



        extent.flush();
    }



    private Date getTime(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();
    }

}
