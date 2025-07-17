package com.inkarto.testRunner;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.inkarto.utilities.AllureReportOpener;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features= {"src/test/resources/features"},  
glue={"com.inkarto.stepDefinitions","com.inkarto.hooks"},
tags="@Sanity", 
plugin= {"io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
		"pretty","html:target/cucumber-report.html"}
) 
public class TestRunner extends AbstractTestNGCucumberTests{
	@BeforeSuite
	public void beforeSuite()
	{
		AllureReportOpener.cleanAllureResults();
	}
	@AfterSuite
	public void afterSuite()
	{
		AllureReportOpener.openAllureReport();
	}
 
}