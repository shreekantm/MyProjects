package testCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import operations.ExecuteStep;

public class Tests {

	private WebDriver webdriver;
	private ExecuteStep es;

	private enum Operation {
		CLICKLINK, SETTEXT, CLICKBUTTON, CHECKLINKTEXT, SELECTDROPDOWNVALUE, SELECTRADIOBUTTONVALUE, SELECTCHECKBOXVALUE, CHECKTEXT

	}

	@BeforeClass(alwaysRun = true)
	public void setUp() throws Exception {
		webdriver = new FirefoxDriver();
		webdriver.get("http://172.25.30.86/myshop/");
		webdriver.manage().window().maximize();

		es = new ExecuteStep(webdriver);
	}

	@Test(groups = { "Smoke", "Regression" }, priority = 0)
	public void testLogin() throws Exception {

		es.ExecuteTestStep("Login", "loginLink", Operation.CLICKLINK.toString());
		es.ExecuteTestStep("Login", "username", Operation.SETTEXT.toString());
		es.ExecuteTestStep("Login", "password", Operation.SETTEXT.toString());
		es.ExecuteTestStep("Login", "loginButton", Operation.CLICKBUTTON.toString());
		es.ExecuteTestStep("Login", "accountName", Operation.CHECKLINKTEXT.toString());
	}

	@Test(groups = { "Regression" }, priority = 1)
	public void testProductSearch() throws Exception {

		es.ExecuteTestStep("Search", "producrsearchTextbox", Operation.SETTEXT.toString());
		es.ExecuteTestStep("Search", "productSearchButton", Operation.CLICKBUTTON.toString());
		es.ExecuteTestStep("Search", "addCartbutton", Operation.CLICKBUTTON.toString());
	}

	@Test(groups = { "Regression" }, priority = 4)
	public void testCheckout() throws Exception {

		// es.ExecuteTestStep("Cart", "shoppingCart");
		webdriver.navigate().to("http://172.25.30.86/myshop/cart");
		es.ExecuteTestStep("Cart", "checkOut", Operation.CLICKBUTTON.toString());
	}

	@Test(groups = { "Smoke", "Regression", "CurrentTest" }, priority = 3)
	public void testBYOC() throws Exception {

		es.ExecuteTestStep("BYOC", "buildYourOwnComputer", Operation.CLICKLINK.toString());
		es.ExecuteTestStep("BYOC", "processor", Operation.SELECTDROPDOWNVALUE.toString());
		es.ExecuteTestStep("BYOC", "ram", Operation.SELECTDROPDOWNVALUE.toString());
		es.ExecuteTestStep("BYOC", "hdd", Operation.SELECTRADIOBUTTONVALUE.toString());
		es.ExecuteTestStep("BYOC", "os", Operation.SELECTRADIOBUTTONVALUE.toString());
		es.ExecuteTestStep("BYOC", "software1", Operation.SELECTCHECKBOXVALUE.toString());
		es.ExecuteTestStep("BYOC", "software2", Operation.SELECTCHECKBOXVALUE.toString());
		es.ExecuteTestStep("BYOC", "price", Operation.CHECKTEXT.toString());
		es.ExecuteTestStep("BYOC", "addToCart", Operation.CLICKBUTTON.toString());

		Thread.sleep(2000);
		testCheckout();

	}

	@Test(groups = { "Smoke", "Regression" }, priority = 4)
	public void testLogout() throws Exception {

		es.ExecuteTestStep("Login", "logoutLink", Operation.CLICKLINK.toString());
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() throws Exception {
		webdriver.close();
	}

}