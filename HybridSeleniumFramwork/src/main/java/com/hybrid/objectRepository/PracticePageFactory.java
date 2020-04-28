package com.hybrid.objectRepository;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.google.common.util.concurrent.Uninterruptibles;

public class PracticePageFactory {
	public static Logger log = LogManager.getLogger(PracticePageFactory.class.getName());

	public WebDriver driver;

	public PracticePageFactory(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(name = "radioButton")
	List<WebElement> radioButtonCount;

	@FindBy(xpath = "//label[contains(@for,'radio')]")
	List<WebElement> radioButtonText;

	@FindBy(id = "autocomplete")
	WebElement suggestionDropdown;

	@FindBy(xpath = "//select[@id='dropdown-class-example']")
	WebElement sDropDown;

	@FindBy(xpath = "//input[contains(@id,'checkBoxOption')]")
	List<WebElement> checkBox;

	@FindBy(id = "openwindow")
	WebElement windowHandles;

	@FindBy(id = "opentab")
	WebElement windowTab;

	@FindBy(id = "name")
	WebElement textAlert;

	@FindBy(id = "alertbtn")
	WebElement acceptButton;

	@FindBy(id = "confirmbtn")
	WebElement confirmAccept;

	@Test
	public List<WebElement> radioButtonCount() {
		return radioButtonCount;

	}

	@Test
	public List<WebElement> radioButtonText() {
		return radioButtonText;

	}

	@Test
	public WebElement suggestionDropdown() {
		return suggestionDropdown;

	}

	@Test
	public WebElement sDropDown() {
		return sDropDown;

	}

	@Test
	public List<WebElement> checkBox() {
		return checkBox;

	}

	@Test
	public WebElement windowHandles() {
		return windowHandles;

	}

	@Test
	public WebElement windowTab() {
		return windowTab;

	}

	@Test
	public WebElement alertText() {
		return textAlert;

	}

	@Test
	public WebElement accept() {
		return acceptButton;

	}

	@Test
	public WebElement acceptConfirm() {
		return confirmAccept;

	}

	public void radioButton() {
		log.info("radioButton test initiated");
		List<WebElement> radioButtons = radioButtonCount();
		List<WebElement> radioButtonText = radioButtonText();
		IntStream.range(0, radioButtons.size()).filter(e -> radioButtonText.get(e).getText().equalsIgnoreCase("Radio2"))
				.forEach(e -> radioButtons.get(e).click());
		log.info("radioButton test completed");

	}

	public void suggestion() {
		log.info("suggestion test initiated");
		suggestionDropdown().sendKeys("in");
		suggestionDropdown().sendKeys(Keys.DOWN);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String script = "return document.getElementById(\"autocomplete\").value;";
		String text = (String) js.executeScript(script);
		log.debug("Text captured :: " + text);
		int i = 0;
		while (!text.trim().equalsIgnoreCase("Argentina")) {
			Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);
			i++;
			suggestionDropdown().sendKeys(Keys.DOWN);
			text = (String) js.executeScript(script);
			log.debug("Text captured :: " + text);
			if (i > 20) {
				break;
			}
		}

		if (i > 20) {
			log.info("Element not found");
		} else {
			log.info("Element found");
		}
		log.info("suggestion test completed");

	}

	public void dropdown() {
		log.info("selectDropdown test initiated");
		WebElement dropDown = sDropDown();
		Select s = new Select(dropDown);
		s.selectByVisibleText("Option2");
		log.info("selectDropdown test completed");

	}

	public void checkBoxAll() {
		log.info("checkbox test initiated");
		List<WebElement> checkBox = checkBox();
		IntStream.range(0, checkBox.size()).forEach(e -> checkBox.get(e).click());
		log.info("checkbox test completed");

	}

	public void getWindowhandles() {
		log.info("WindowHandles test initiated");
		windowHandles().click();
		Set<String> ids = driver.getWindowHandles();
		Iterator<String> it = ids.iterator();
		String parentId = it.next();
		String childId = it.next();
		driver.switchTo().window(childId);
		log.debug("Child window :: " + driver.getTitle());
		driver.switchTo().window(parentId);
		log.debug("Parent window :: " + driver.getTitle());
		log.info("WindowHandles test completed");

	}

	public void getWindowTabs() {
		log.info("Windowtab test initiated");
		windowTab.click();
		Set<String> ids = driver.getWindowHandles();
		Iterator<String> it = ids.iterator();
		String parentId = it.next();
		String childId = it.next();
		driver.switchTo().window(childId);
		log.debug("Child window :: " + driver.getTitle());
		driver.switchTo().window(parentId);
		log.debug("Parent window :: " + driver.getTitle());
		log.info("Windowtab test completed");

	}

	public void alertBox() {
		log.info("alertBox test initiated");
		alertText().sendKeys("AlertTest");
		accept().click();
		Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);
		driver.switchTo().alert().accept();
		log.info("alertBox accept clicked");
		alertText().sendKeys("ConfirmationTestAccept");
		acceptConfirm().click();
		Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);
		driver.switchTo().alert().accept();
		log.info("alertBox confirm clicked");
		alertText().sendKeys("ConfirmationTestCancel");
		acceptConfirm().click();
		Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);
		driver.switchTo().alert().dismiss();
		log.info("alertBox cancel clicked");
		log.info("alertBox test completed");

	}

}
