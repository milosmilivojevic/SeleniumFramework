package tests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PracticeFormPageTests extends TestBase {

	@BeforeMethod
	public void setup() throws InterruptedException {
		driver.navigate().to("http://www.seleniumframework.com/Practiceform/");
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	}

	@Test
	public void newBrowserWindow() {
		practiceFormPage.clickNewBrowserWindowButton();
		ArrayList<String> handles = new ArrayList<String>(driver.getWindowHandles());
		String firstWindow = handles.get(0);
		String secondWindow = handles.get(1);
		driver.switchTo().window(secondWindow);

		String actualUrl = driver.getCurrentUrl();
		String expectedUrl = excelReader.getData("newBrowserWindow", 1, 1);
		Assert.assertEquals(actualUrl, expectedUrl);

		driver.close();
		driver.switchTo().window(firstWindow);

		actualUrl = driver.getCurrentUrl();
		expectedUrl = excelReader.getData("newBrowserWindow", 2, 1);
		Assert.assertEquals(actualUrl, expectedUrl);
	}

	@Test
	public void newMessageWindow() throws IOException {

		practiceFormPage.clickNewMessageWindowButton();

		Set<String> allWindowHandles = driver.getWindowHandles();
		Iterator<String> iterator = allWindowHandles.iterator();
		String parentWindow = iterator.next();
		String childWindow = iterator.next();
		driver.switchTo().window(childWindow);

		driver.close();
		driver.switchTo().window(parentWindow);

	}

	@Test
	public void newBrowserTab() {
		practiceFormPage.clickNewBrowserTabButton();
		ArrayList<String> handles = new ArrayList<String>(driver.getWindowHandles());
		String firstWindow = handles.get(0);
		String secondWindow = handles.get(1);
		driver.switchTo().window(secondWindow);

		String actualUrl = driver.getCurrentUrl();
		String expectedUrl = excelReader.getData("newBrowserTab", 1, 1);
		Assert.assertEquals(actualUrl, expectedUrl);

		driver.close();
		driver.switchTo().window(firstWindow);

		actualUrl = driver.getCurrentUrl();
		expectedUrl = excelReader.getData("newBrowserTab", 2, 1);
		Assert.assertEquals(actualUrl, expectedUrl);
	}

	@Test
	public void alertBox() {

		practiceFormPage.clickAlertBoxButton();

		String actualAlertBoxText = driver.switchTo().alert().getText();
		String expectedAlertBoxText = excelReader.getData("alertBox", 1, 1);
		Assert.assertEquals(actualAlertBoxText, expectedAlertBoxText);

		clickOKToCloseAlertBox();
	}

	@Test
	public void timingAlert() {

		practiceFormPage.clickTimingAlertButton();
		practiceFormPage.WaitUntilAlertShowsUp();

		String actualAlertBoxText = driver.switchTo().alert().getText();
		String expectedAlertBoxText = excelReader.getData("alertBox", 1, 1);
		Assert.assertEquals(actualAlertBoxText, expectedAlertBoxText);

		clickOKToCloseAlertBox();
	}

	@Test
	public void searchFunctionalityTest() throws InterruptedException {
		String textForSearch = excelReader.getData("search", 1, 1);

		practiceFormPage.makeSearchboxVisible();
		practiceFormPage.insertTextInSearchbox(textForSearch);
		practiceFormPage.pressEnterToSearch();

		String actualUrl = driver.getCurrentUrl();
		String expectedUrl = excelReader.getData("search", 2, 1);
		Assert.assertEquals(actualUrl, expectedUrl);
	}

	@Test
	public void verifyNavLinksText() throws IOException {

		ArrayList<String> actualNavLinks = practiceFormPage.listOfActualNavLinks();
		ArrayList<String> expectedNavLinks = practiceFormPage.listOfExpectedNavLinks();
		Assert.assertEquals(actualNavLinks, expectedNavLinks);
	}

	@Test
	public void verifyNavURLs() throws IOException {

		ArrayList<String> actualNavURLs = practiceFormPage.listOfActualURLs();
		ArrayList<String> expectedNavURLs = practiceFormPage.listOfExpectedURLs();
		Assert.assertEquals(actualNavURLs, expectedNavURLs);
	}

	@Test
	public void verifyFormPlaceholders() throws IOException {
		ArrayList<String> actualPlaceholders = practiceFormPage.listOfActualPlaceholders();
		ArrayList<String> expectedPlaceholders = practiceFormPage.listOfExpectedPlaceholders();
		Assert.assertEquals(actualPlaceholders, expectedPlaceholders);
	}

	@Test
	public void waitUntilButtonColorChanges() {
		String expectedColor = excelReader.getData("changeColorButtons", 1, 1);
		WebDriverWait wait = new WebDriverWait(driver, 10);
		Boolean colorChange = wait.until(
				ExpectedConditions.attributeToBe(practiceFormPage.getChangeColorButton(), "color", expectedColor));
		Assert.assertTrue(colorChange);
	}

	@Test
	public void changeColorOnDoubleClick() throws IOException, InterruptedException {
		practiceFormPage.doubleClickChangeColorButton();
		Thread.sleep(1000);
		String actualColor = practiceFormPage.getChangeColorOnDoubleClickButton().getCssValue("color");
		String expectedColor = excelReader.getData("changeColorButtons", 2, 1);
		Assert.assertEquals(actualColor, expectedColor);
	}

	@Test (priority = 0)
	public void fullPageScreenshot() throws IOException, InterruptedException {
		captureFullPageScreenshot();
	}

	@Test
	public void screenshotsOfSpecificElements() throws IOException {
		WebElement form = practiceFormPage.getFormWidget();
		String fileName = excelReader.getData("screenshots", 1, 1);
		captureScreenshotOfSpecificElement(form, fileName);
		
		WebElement header = practiceFormPage.getHeader();
		fileName = excelReader.getData("screenshots", 2, 1);
		captureScreenshotOfSpecificElement(header, fileName);
	}

	@Test
	public void dragAndDrop() throws IOException, InterruptedException {
		practiceFormPage.dragAndDrop();
	}

	@Test
	public void submitFormWithAllFieldsFilledIn() throws InterruptedException {
		String name = excelReader.getData("form", 1, 1);
		String email = excelReader.getData("form", 2, 1);
		int telephone = Integer.valueOf(excelReader.getData("form", 3, 1));
		String country = excelReader.getData("form", 4, 1);
		String company = excelReader.getData("form", 5, 1);
		String message = excelReader.getData("form", 6, 1);
		
		practiceFormPage.enterName(name);
		practiceFormPage.enterEmail(email);
		practiceFormPage.enterTelephone(telephone);
		practiceFormPage.enterCountry(country);
		practiceFormPage.enterCompany(company);
		practiceFormPage.enterMessage(message);

		practiceFormPage.clickSubmitButton();
		
		practiceFormPage.waitUntilConfirmationMessageIsVisible();
		
		String expectedMessage = practiceFormPage.getformSubmitConfirmationMessage().getText();
		String actualMessage = excelReader.getData("form", 1, 2);
		Assert.assertEquals(actualMessage, expectedMessage);
	}

	@Test
	public void submitFormWithRequiredFieldsFilledIn() throws InterruptedException {
		String name = excelReader.getData("form", 1, 1);
		String email = excelReader.getData("form", 2, 1);
		int telephone = Integer.valueOf(excelReader.getData("form", 3, 1));
		practiceFormPage.enterName(name);
		practiceFormPage.enterEmail(email);
		practiceFormPage.enterTelephone(telephone);

		practiceFormPage.clickSubmitButton();

		Thread.sleep(2000);
		String expectedMessage = practiceFormPage.getformSubmitConfirmationMessage().getText();
		String actualMessage = excelReader.getData("form", 1, 2);
		Assert.assertEquals(actualMessage, expectedMessage);
	}

	@Test
	public void clearForm() throws InterruptedException {
		String name = excelReader.getData("form", 1, 1);
		String email = excelReader.getData("form", 2, 1);
		int telephone = Integer.valueOf(excelReader.getData("form", 3, 1));
		String country = excelReader.getData("form", 4, 1);
		String company = excelReader.getData("form", 5, 1);
		String message = excelReader.getData("form", 6, 1);

		practiceFormPage.enterName(name);
		practiceFormPage.enterEmail(email);
		practiceFormPage.enterTelephone(telephone);
		practiceFormPage.enterCountry(country);
		practiceFormPage.enterCompany(company);
		practiceFormPage.enterMessage(message);

		practiceFormPage.clearForm();

		Assert.assertEquals(practiceFormPage.getNameInputField().getAttribute("value"), "");
		Assert.assertEquals(practiceFormPage.getTelephoneInputField().getAttribute("value"), "");
		Assert.assertEquals(practiceFormPage.getCountryInputField().getAttribute("value"), "");
		Assert.assertEquals(practiceFormPage.getCompanyInputField().getAttribute("value"), "");
		Assert.assertEquals(practiceFormPage.getMessageInputField().getAttribute("value"), "");
	}

	@Test
	public void submitEmptyForm() throws InterruptedException {

		practiceFormPage.clickSubmitButton();

		List<WebElement> errorMessages = driver.findElements(By.className("formErrorContent"));
		int actualNumberOfErrorMessages = errorMessages.size();
		int expectedNumberOfErrorMessages = Integer.valueOf(excelReader.getData("form", 1, 3));
		Assert.assertEquals(actualNumberOfErrorMessages, expectedNumberOfErrorMessages);
	}

	@Test
	public void resizeTextarea() throws InterruptedException {
		practiceFormPage.increaseTextareaSize();
		practiceFormPage.decreaseTextareaSize();
	}

	@Test
	public void screenshotWithTimestamp() throws InterruptedException, IOException {
		screenshotOfVisiblePartOfPageWithTimestamp();
	}

	@Test
	public void subscribeForNewsletter() throws InterruptedException {
		String email = excelReader.getData("subscribe", 1, 1);

		practiceFormPage.enterEmailForNewsletter(email);
		practiceFormPage.clickSubscribeButton();
		practiceFormPage.closeNewWindow();
	}

	@AfterMethod
	public void afterTest() throws InterruptedException {
		driver.manage().deleteAllCookies();
		driver.navigate().refresh();
		Thread.sleep(2000);
	}
}
