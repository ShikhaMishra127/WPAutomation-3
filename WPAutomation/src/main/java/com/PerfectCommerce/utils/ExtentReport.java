package com.PerfectCommerce.utils;

import java.io.File;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class ExtentReport {
	public ExtentReports report;
	public ExtentTest logger; 
	
	public ExtentReport(String strclass) {
		//report.loadConfig(new File("src/main/java/com/PerfectCommerce/resources/ExtentReport.xml"));
		report=new ExtentReports("/usr/local/bin/abc.html");
		report.loadConfig(new File("ExtentReport.xml"));

		logger=report.startTest(strclass);
		
	}
}
