package operations;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import static org.testng.Assert.*;

public class UIOperation {
	WebDriver driver;

	public UIOperation(WebDriver driver) {
		this.driver = driver;
	}

	public void perform(String operation, String objectType, String value, String data) throws Exception {

		switch (operation.toUpperCase()) {
		case "CLICKLINK":
			// Perform click
			driver.findElement(this.getObject(objectType, value)).click();
			break;
		case "SETTEXT":
			// Set text on control
			driver.findElement(this.getObject(objectType, value)).sendKeys(data);
			break;
		case "CLICKBUTTON":
			// Perform click
			driver.findElement(this.getObject(objectType, value)).click();
			break;
		case "CHECKLINKTEXT":
			assertEquals(driver.findElement(this.getObject(objectType, value)).getText(), data);
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