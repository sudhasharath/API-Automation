package com.api.framework.testrunner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = { "src/test/resources/cucumber/features/" }, glue = {
		"com.api.framework.stepdefinitions" }, tags = { "@GitApiFeature" }, plugin = {
				"com.api.framework.reporting.adapter.ExtentCucumberAdapter:", "html:target/cucumber-reports" })
public class RunTest {

}
