package com.hybrid.test;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.google.common.util.concurrent.Uninterruptibles;
import com.hybrid.base.Base;
import com.hybrid.objectRepository.PracticePageFactory;

public class PracticePageTest extends Base {

	public static Logger log = LogManager.getLogger(PracticePageTest.class.getName());

	@BeforeTest
	public void initialze() throws IOException {
		driver = setUp();
		log.info("Driver initialized...");
		driver.get(prop.getProperty("url"));
		log.info("Hit url...");

	}

	@Test
	public void formValidations() throws IOException, InterruptedException {
	PracticePageFactory obj = new PracticePageFactory(driver);
	obj.radioButton();
	obj.suggestion();
	obj.dropdown();
	obj.checkBoxAll();
        
        
		
	}

	@AfterTest
	public void tearDown() {

		driver.close();
		driver = null;
		log.info("Should teardown the task....");

	}
}