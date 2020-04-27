package com.hybrid.objectRepository;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import org.openqa.selenium.By;
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

	public List<WebElement> radioButton() {
		List<WebElement> radioButtons = radioButtonCount();
		List<WebElement> radioButtonText = radioButtonText();
		IntStream.range(0, radioButtons.size())
		         .filter(e -> radioButtonText.get(e).getText().equalsIgnoreCase("Radio2"))
				 .forEach(e -> radioButtons.get(e).click());

		return radioButtons;
	}

	public String suggestion() {
		suggestionDropdown().sendKeys("in");
		suggestionDropdown().sendKeys(Keys.DOWN);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String script = "return document.getElementById(\"autocomplete\").value;";
		String text = (String) js.executeScript(script);
		System.out.println(text);
		int i = 0;
		while (!text.trim().equalsIgnoreCase("Argentina")) {
			Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);
			i++;
			suggestionDropdown().sendKeys(Keys.DOWN);
			text = (String) js.executeScript(script);
			System.out.println(text);
			if (i > 20) {
				break;
			}
		}

		if (i > 20) {
			System.out.println("Elements not found");
		} else {
			System.out.println("Elements found");
		}

		return text;
	}

	public Select dropdown() {
		WebElement dropDown = sDropDown();
		Select s = new Select(dropDown);
		s.selectByVisibleText("Option2");
		return s;
	}

	public List<WebElement> checkBoxAll() {
		List<WebElement> checkBox = checkBox();
		IntStream.range(0, checkBox.size())
		         .forEach(e -> checkBox.get(e).click());

		return checkBox;
	}

}
