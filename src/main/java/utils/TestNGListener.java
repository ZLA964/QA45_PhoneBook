package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.IResultMap;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.Arrays;

public class TestNGListener implements ITestListener {
    Logger logger = LoggerFactory.getLogger(TestNGListener.class);

    @Override
    public void onTestStart(ITestResult result) {
        ITestListener.super.onTestStart(result);
        String testClassName = result.getTestClass().getRealClass().getSimpleName();
        String testName = result.getName();
        logger.info("--- begin test --> " + testClassName + "-> " + testName);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ITestListener.super.onTestSuccess(result);
        String testClassName = result.getTestClass().getRealClass().getSimpleName();
        String testName = result.getName();
        logger.info("*** finish test success --> " + testClassName + "-> " + testName);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ITestListener.super.onTestFailure(result);
        logger.info("!!! fail test--> " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ITestListener.super.onTestSkipped(result);
        logger.info("--- skipped test --> " + result.getMethod());
    }

/*/   @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        ITestListener.super.onTestFailedWithTimeout(result);
    }
*/

    @Override
    public void onStart(ITestContext context) {
        ITestListener.super.onStart(context);
        String testClassName = context.getAllTestMethods()[1].getTestClass().getRealClass().getName();
        logger.info("=== " + testClassName + " start on date " + context.getStartDate() + " ===");
    }

    @Override
    public void onFinish(ITestContext context) {
        ITestListener.super.onFinish(context);
        String testClassName = context.getAllTestMethods()[1].getTestClass().getRealClass().getName();
        logger.info("=== stop testing " + testClassName + " on date " + context.getStartDate() + " ===");
        logger.info("=== passed tests ===");
        IResultMap passedTests = context.getPassedTests();
        passedTests.getAllResults().stream()
                .map(result -> result.getMethod().getMethodName())
                .forEach(s -> logger.info(s));
        logger.info("====================");
        logger.info("=== failed tests ===");
        context.getFailedTests().getAllResults().stream()
                .map(ITestResult::getMethod)
                .forEach(imethod ->logger.info(imethod.toString()));
        logger.info("====================");
    }
}
