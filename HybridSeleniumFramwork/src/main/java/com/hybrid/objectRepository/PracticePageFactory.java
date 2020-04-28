package com.hybrid.objectRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import org.apache.logging.log4j.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.common.util.concurrent.Uninterruptibles;
import com.hybrid.utils.utils;

public class PracticePageFactory {
	public static Logger log = LogManager.getLogger(PracticePageFactory.class.getName());

	public WebDriver driver;
	public boolean display;
	public ArrayList<String> data;
	public PracticePageFactory(WebDriver driver) throws IOException {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		utils d = new utils();
		data = d.getData("testdata", "Testcases", "FormDatas");
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

	@FindBy(id = "product")
	WebElement table;

	@FindBy(xpath = "//td[2]")
	List<WebElement> secondColm;

	@FindBy(xpath = "//td[3]")
	List<WebElement> thirdColm;

	@FindBy(id = "displayed-text")
	WebElement displayField;

	@FindBy(id = "hide-textbox")
	WebElement hide;

	@FindBy(id = "show-textbox")
	WebElement show;

	@FindBy(id = "mousehover")
	WebElement hoverbox;

	@FindBy(linkText = "Top")
	WebElement top;

	@FindBy(linkText = "Reload")
	WebElement reload;

	@FindBy(xpath = "//iframe[@id='courses-iframe']")
	WebElement frame;

	@FindBy(xpath = "//li[contains(text(),'contact@rahulshettyacademy.com')]")
	WebElement coursetext;

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

	@Test
	public WebElement tableBox() {
		return table;

	}

	@Test
	public List<WebElement> columnSecond() {
		return secondColm;

	}

	@Test
	public List<WebElement> columnThird() {
		return thirdColm;

	}

	@Test
	public WebElement fieldDisplay() {
		return displayField;

	}

	@Test
	public WebElement hideTextBox() {
		return hide;

	}

	@Test
	public WebElement showTextBox() {
		return show;

	}

	@Test
	public WebElement hoverBox() {
		return hoverbox;

	}

	@Test
	public WebElement topClick() {
		return top;

	}

	@Test
	public WebElement reloadClick() {
		return reload;

	}

	@Test
	public WebElement iframeElement() {
		return frame;

	}

	@Test
	public WebElement courseText() {
		return coursetext;

	}

	public void radioButton() {
		log.info("radioButton test initiated");
		List<WebElement> radioButtons = radioButtonCount();
		List<WebElement> radioButtonText = radioButtonText();
		IntStream.range(0, radioButtons.size()).filter(e -> radioButtonText.get(e).getText().equalsIgnoreCase(data.get(1)))
				.forEach(e -> radioButtons.get(e).click());
		log.info("radioButton test completed");

	}

	public void suggestion() {
		log.info("suggestion test initiated");
		suggestionDropdown().sendKeys(data.get(2));
		suggestionDropdown().sendKeys(Keys.DOWN);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String script = "return document.getElementById(\"autocomplete\").value;";
		String text = (String) js.executeScript(script);
		log.debug("Text captured :: " + text);
		int i = 0;
		while (!text.trim().equalsIgnoreCase(data.get(3))) {
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
		s.selectByVisibleText(data.get(4));
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
		alertText().sendKeys(data.get(5));
		accept().click();
		Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);
		driver.switchTo().alert().accept();
		log.info("alertBox accept clicked");
		alertText().sendKeys(data.get(6));
		acceptConfirm().click();
		Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);
		driver.switchTo().alert().accept();
		log.info("alertBox confirm clicked");
		alertText().sendKeys(data.get(7));
		acceptConfirm().click();
		Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);
		driver.switchTo().alert().dismiss();
		log.info("alertBox cancel clicked");
		log.info("alertBox test completed");

	}

	public void webTable() {
		log.info("Webtable test initated");
		List<WebElement> courses = columnSecond();
		List<WebElement> priceCount = columnThird();
		IntStream.range(0, priceCount.size() - 1).forEach(e -> log
				.info("Courses :: " + courses.get(e).getText() + " :: " + "Price :: " + priceCount.get(e).getText()));
		Assert.assertEquals(courses, "book");
		log.info("Webtable test completed");

	}

	public void elementDisplayed() {
		log.info("display test initiated");
		fieldDisplay().sendKeys(data.get(8));
		display = fieldDisplay().isDisplayed();
		log.info("Before clicking hide field present or not :: " + display);
		hideTextBox().click();
		display = fieldDisplay().isDisplayed();
		log.info("After clicking hide field present or not :: " + display);
		showTextBox().click();
		display = fieldDisplay().isDisplayed();
		log.info("After clicking show field present or not :: " + display);
		log.info("display test completed");
	}

	public void hover() {
		log.info("hover test initiated");
		Actions a = new Actions(driver);
		a.moveToElement(hoverBox()).build().perform();
		topClick().click();
		a.moveToElement(hoverBox()).build().perform();
		reloadClick().click();
		log.info("hover test completed");
	}

	public void iframe() {
		log.info("iframe test initiated");
		driver.switchTo().frame(iframeElement());
		String text = courseText().getText();
		log.info("Course heading ::" + text);
		log.info("iframe test completed");

	}

}
