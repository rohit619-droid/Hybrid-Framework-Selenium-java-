package com.hybrid.test;

import java.io.IOException;
import org.apache.logging.log4j.*;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.hybrid.base.Base;
import com.hybrid.objectRepository.PracticePageFactory;

public class PracticePageTest extends Base {
	public static Logger log = LogManager.getLogger(PracticePageTest.class.getName());
	public PracticePageFactory obj;

	@BeforeTest
	public void initialze() throws IOException {
		driver = setUp();
		log.info("Driver initialized...");
		driver.get(prop.getProperty("url"));
		log.info("Hit url...");
		obj = new PracticePageFactory(driver);
	}

	@Test(priority = 1)
	public void radioButton() {
		obj.radioButton();
	}

	@Test(priority = 2)
	public void suggestion() {
		obj.suggestion();
	}

	@Test(priority = 3)
	public void dropdown() {
		obj.dropdown();
	}

	@Test(priority = 4)
	public void checkBoxAll() {
		obj.checkBoxAll();
	}

	@Test(priority = 5)
	public void getWindowhandles() {
		obj.getWindowhandles();
	}

	@Test(priority = 6)
	public void getWindowTabs() {
		obj.getWindowTabs();
	}

	@Test(priority = 7)
	public void alertBox() {
		obj.alertBox();
	}

	@Test(priority = 8)
	public void webTable() {
		obj.webTable();
	}

	@Test(priority = 9)
	public void elementDisplayed() {
		obj.elementDisplayed();
	}

	@Test(priority = 10)
	public void hover() {
		obj.hover();
	}

	@Test(priority = 11)
	public void iframe() {
		obj.iframe();
	}

	@AfterTest
	public void tearDown() {

		driver.quit();
		driver = null;
		log.info("Should teardown the task....");

	}
}
