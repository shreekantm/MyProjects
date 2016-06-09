package operations;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.testng.Assert.*;

public class UIOperation {
	WebDriver driver;
	Select drpDown;
	int i;
	WebDriverWait wait;

	public UIOperation(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, 10);
	}

	public void perform(String operation, String objectType, String value, String data) throws Exception {

		switch (operation) {
		case "CLICKLINK":
			// Perform click
			wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(this.getObject(objectType, value))));
			driver.findElement(this.getObject(objectType, value)).click();
			break;

		case "SETTEXT":
			// Set text on control
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(this.getObject(objectType, value))));
			driver.findElement(this.getObject(objectType, value)).sendKeys(data);
			break;

		case "CLICKBUTTON":
			// Perform click
			wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(this.getObject(objectType, value))));
			driver.findElement(this.getObject(objectType, value)).click();
			break;

		case "CHECKLINKTEXT":
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(this.getObject(objectType, value))));
			assertEquals(driver.findElement(this.getObject(objectType, value)).getText(), data);

		case "SELECTDROPDOWNVALUE":
			drpDown = new Select(driver.findElement(this.getObject(objectType, value)));
			drpDown.selectByVisibleText(data);
			break;

		case "SELECTRADIOBUTTONVALUE":
			if (!driver.findElement(this.getObject(objectType, value)).isSelected())
				driver.findElement(this.getObject(objectType, value)).click();
			break;

		case "SELECTCHECKBOXVALUE":
			if (!driver.findElement(this.getObject(objectType, value)).isSelected())
				driver.findElement(this.getObject(objectType, value)).click();
			break;

		case "CHECKTEXT":
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(this.getObject(objectType, value))));
			assertEquals(driver.findElement(this.getObject(objectType, value)).getText().trim(), data);

		default:
			break;
		}
	}

	private By getObject(String objectType, String value) throws Exception {

		switch (objectType.toUpperCase()) {

		case "XPATH":
			return By.xpath(value);

		case "CLASS":
			return By.className(value);

		case "NAME":
			return By.name(value);

		case "ID":
			return By.id(value);

		case "CSS":
			return By.cssSelector(value);

		case "LINKTEXT":
			return By.linkText(value);

		case "PARTIALLINK":
			return By.partialLinkText(value);

		default:
			throw new Exception("Wrong object type");

		}

	}

}