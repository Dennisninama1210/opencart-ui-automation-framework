package com.qa.opencart.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {

    private int count = 0;
    private static int maxTry = 3;

    @Override
    public boolean retry(ITestResult iTestResult) {

        if (!iTestResult.isSuccess()) { // Check if test is not successful

            if (count < maxTry) { // Check if max retry count is not reached
                count++; // Increase retry count
                iTestResult.setStatus(ITestResult.FAILURE); // Mark test as failed
                return true; // Tell TestNG to re-run the test
            } else {
                iTestResult.setStatus(ITestResult.FAILURE); // Max retry reached
            }

        } else {
            iTestResult.setStatus(ITestResult.SUCCESS); // Test passed
        }

        return false;
    }
}