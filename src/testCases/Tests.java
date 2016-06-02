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

	@BeforeClass(alwaysRun = true)
	public void setUp() throws Exception {
		webdriver = new FirefoxDriver();
		webdriver.get("http://172.25.30.86/myshop/");
		webdriver.manage().window().maximize();

		es = new ExecuteStep(webdriver);
	}

	@Test(groups = { "Smoke", "Regression" }, priority = 0)
	public void testLogin() throws Exception {

		es.ExecuteTestStep("Login", "loginLink");
		es.ExecuteTestStep("Login", "username");
		es.ExecuteTestStep("Login", "password");
		es.ExecuteTestStep("Login", "loginButton");
		es.ExecuteTestStep("Login", "accountName");
	}

	@Test(groups = { "Regression" }, priority = 1)
	public void testProductSearch() throws Exception {

		es.ExecuteTestStep("Search", "producrsearchTextbox");
		es.ExecuteTestStep("Search", "productSearchButton");
		es.ExecuteTestStep("Search", "addCartbutton");
	}

	@Test(groups = { "Regression" }, priority = 2)
	public void testCheckout() throws Exception {

		es.ExecuteTestStep("Cart", "shoppingCart");
		es.ExecuteTestStep("Cart", "checkOut");
	}

	@Test(groups = { "Smoke", "Regression" }, priority = 3)
	public void testLogout() throws Exception {

		es.ExecuteTestStep("Login", "logoutLink");
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() throws Exception {
		webdriver.close();
	}

}