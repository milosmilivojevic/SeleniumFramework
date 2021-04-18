package pages;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PracticeFormPage {
	WebDriver driver;
	WebElement homeNavLink;
	WebElement seleniumNavLink;
	WebElement newBrowserWindowButton;
	WebElement newMessageWindowButton;
	WebElement newBrowserTabButton;
	WebElement alertBoxButton;
	WebElement timingAlertButton;
	WebElement changeColorButton;
	WebElement changeColorOnDoubleClickButton;
	WebElement dragMeButton;
	WebElement dragToButton;
	WebElement nameInputField;
	WebElement emailInputField;
	WebElement telephoneInputField;
	WebElement countryInputField;
	WebElement companyInputField;
	WebElement messageInputField;
	WebElement submitFormButton;
	WebElement clearFormLink;
	WebElement searchbox;
	WebElement newsletterInputField;
	WebElement subscribeButton;
	WebElement formSubmitConfirmationMessage;
	WebElement header;
	WebElement formWidget;

	public PracticeFormPage(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement getHomeNavLink() {
		return driver.findElement(By.xpath("//*[@id=\"main-nav\"]/li[1]/a"));
	}

	public WebElement getSeleniumNavLink() {
		return driver.findElement(By.xpath("//*[@id=\"main-nav\"]/li[4]/a"));
	}

	public WebElement getNewBrowserWindowButton() {
		return driver.findElement(By.id("button1"));
	}

	public WebElement getNewMessageWindowButton() {
		return driver.findElement(By.xpath("//button[text()=\"New Message Window\"]"));
	}

	public WebElement getNewBrowserTabButton() {
		return driver.findElement(By.xpath("//button[text()=\"New Browser Tab\"]"));
	}

	public WebElement getAlertBoxButton() {
		return driver.findElement(By.id("alert"));
	}

	public WebElement getTimingAlertButton() {
		return driver.findElement(By.id("timingAlert"));
	}

	public WebElement getChangeColorButton() {
		return driver.findElement(By.id("colorVar"));
	}

	public WebElement getChangeColorOnDoubleClickButton() {
		return driver.findElement(By.id("doubleClick"));
	}

	public WebElement getDragMeButton() {
		return driver.findElement(By.id("draga"));
	}

	public WebElement getDragToButton() {
		return driver.findElement(By.id("dragb"));
	}

	public WebElement getNameInputField() {
		return driver.findElement(By.name("name"));
	}

	public WebElement getEmailInputField() {
		return driver.findElement(By.xpath("//*[@class=\"form-mail\"]//child::input"));
	}

	public WebElement getTelephoneInputField() {
		return driver.findElement(By.name("telephone"));
	}

	public WebElement getCountryInputField() {
		return driver.findElement(By.name("country"));
	}

	public WebElement getCompanyInputField() {
		return driver.findElement(By.name("company"));
	}

	public WebElement getMessageInputField() {
		return driver.findElement(By.name("message"));
	}

	public WebElement getSubmitFormButton() {
		return driver.findElement(By.xpath("//a[text()=\"Submit\"]"));
	}

	public WebElement getClearFormLink() {
		return driver.findElement(By.xpath("//a[text()=\"clear\"]"));
	}

	public WebElement getSearchbox() {
		return driver.findElement(By.cssSelector("#header .mini-search .field"));
	}

	public WebElement getNewsletterInputField() {
		return driver.findElement(By.xpath("//*[@id=\"text-11\"]/div/form/p[2]/input"));
	}

	public WebElement getSubscribeButton() {
		return driver.findElement(By.xpath("//input[@value=\"Subscribe\"]"));
	}

	public WebElement getformSubmitConfirmationMessage() {
		return driver.findElement(By.className("greenPopup"));
	}

	public WebElement getHeader() {
		return driver.findElement(By.id("header"));
	}

	public WebElement getFormWidget() {
		return driver.findElement(By.id("presscore-contact-form-widget-3"));
	}

	public void clickNewBrowserWindowButton() {
		this.getNewBrowserWindowButton().click();
	}

	public void clickNewBrowserTabButton() {
		this.getNewBrowserTabButton().click();
	}

	public void clickNewMessageWindowButton() {
		this.getNewMessageWindowButton().click();
	}

	public void clickAlertBoxButton() {
		this.getAlertBoxButton().click();
	}

	public void clickTimingAlertButton() {
		this.getTimingAlertButton().click();
	}

	public void WaitUntilAlertShowsUp() {
		WebDriverWait wait = new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.alertIsPresent());
	}

	public void doubleClickChangeColorButton() {
		Actions actions = new Actions(driver);
		WebElement doubleClickButton = this.getChangeColorOnDoubleClickButton();
		actions.doubleClick(doubleClickButton).perform();
	}

	public void enterName(String name) {
		this.getNameInputField().clear();
		this.getNameInputField().sendKeys(name);
	}

	public void enterEmail(String email) {
		this.getEmailInputField().clear();
		this.getEmailInputField().sendKeys(email);
	}

	public void enterTelephone(int telephone) {
		this.getTelephoneInputField().clear();
		this.getTelephoneInputField().sendKeys(String.valueOf(telephone));
	}

	public void enterCountry(String country) {
		this.getCountryInputField().clear();
		this.getCountryInputField().sendKeys(country);
	}

	public void enterCompany(String company) {
		this.getCompanyInputField().clear();
		this.getCompanyInputField().sendKeys(company);
	}

	public void enterMessage(String message) {
		this.getMessageInputField().clear();
		this.getMessageInputField().sendKeys(message);
	}

	public void clickSubmitButton() {
		this.getSubmitFormButton().click();
	}

	public void clearForm() {
		this.getClearFormLink().click();
	}

	public void makeSearchboxVisible() {
		String js = "arguments[0].style.visibility='visible';";
		((JavascriptExecutor) driver).executeScript(js, this.getSearchbox());
	}

	public void insertTextInSearchbox(String textForSearch) {
		this.getSearchbox().click();
		this.getSearchbox().sendKeys(textForSearch);
	}

	public void pressEnterToSearch() {
		this.getSearchbox().sendKeys(Keys.ENTER);
	}

	public void enterEmailForNewsletter(String email) {
		this.getNewsletterInputField().sendKeys(email);
	}

	public void clickSubscribeButton() {
		this.getSubscribeButton().click();
	}

	public void closeNewWindow() {
		ArrayList<String> handles = new ArrayList<String>(driver.getWindowHandles());
		String firstWindow = handles.get(0);
		String secondWindow = handles.get(1);
		driver.switchTo().window(secondWindow);
		driver.close();
		driver.switchTo().window(firstWindow);
	}

	public void dragAndDrop() {
		WebElement from = this.getDragMeButton();
		WebElement to = this.getDragToButton();
		Actions act = new Actions(driver);
		act.dragAndDrop(from, to).build().perform();
	}

	public void increaseTextareaSize() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.getElementsByName('message')[0].setAttribute('rows', '30')");
	}

	public void decreaseTextareaSize() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.getElementsByName('message')[0].setAttribute('rows', '10')");
	}

	public ArrayList<String> listOfActualNavLinks() {
		List<WebElement> navLinks = driver.findElements(By.cssSelector("#main-nav > li"));
		ArrayList<String> actualNavLinks = new ArrayList<String>();
		for (int i = 0; i < navLinks.size(); i++) {
			actualNavLinks.add(navLinks.get(i).getText());
		}
		return (ArrayList<String>) actualNavLinks;
	}

	public ArrayList<String> listOfExpectedNavLinks() throws IOException {
		File file = new File("data/testData.xlsx");
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheet("navLinks");

		ArrayList<String> expectedNavLinks = new ArrayList<String>();
		for (int i = 0; i <= sheet.getLastRowNum(); i++) {
			XSSFRow row = sheet.getRow(i);
			XSSFCell text = row.getCell(0);
			expectedNavLinks.add(text.getStringCellValue());
		}
		return (ArrayList<String>) expectedNavLinks;
	}

	public ArrayList<String> listOfActualPlaceholders() {
		List<WebElement> formPlaceholders = driver.findElements(By.cssSelector(".form-fields input"));
		formPlaceholders.add(getMessageInputField());

		ArrayList<String> actualPlaceholders = new ArrayList<String>();
		for (int i = 0; i < formPlaceholders.size(); i++) {
			actualPlaceholders.add(formPlaceholders.get(i).getAttribute("placeholder"));
		}
		return (ArrayList<String>) actualPlaceholders;
	}

	public ArrayList<String> listOfExpectedPlaceholders() throws IOException {
		File file = new File("data/testData.xlsx");
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheet("formPlaceholders");

		ArrayList<String> expectedPlaceholders = new ArrayList<String>();
		for (int i = 0; i <= sheet.getLastRowNum(); i++) {
			XSSFRow row = sheet.getRow(i);
			XSSFCell text = row.getCell(0);
			expectedPlaceholders.add(text.getStringCellValue());
		}
		return (ArrayList<String>) expectedPlaceholders;
	}

	public ArrayList<String> listOfActualURLs() {
		List<WebElement> allURLs = driver.findElements(By.xpath("//*[@id=\"main-nav\"]/li/a"));
		ArrayList<String> actualNavURLs = new ArrayList<String>();
		for (int i = 0; i < allURLs.size(); i++) {
			actualNavURLs.add(allURLs.get(i).getAttribute("href"));
		}
		return (ArrayList<String>) actualNavURLs;
	}

	public ArrayList<String> listOfExpectedURLs() throws IOException {
		File file = new File("data/testData.xlsx");
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheet("navURLs");

		ArrayList<String> expectedNavURLs = new ArrayList<String>();
		for (int i = 0; i <= sheet.getLastRowNum(); i++) {
			XSSFRow row = sheet.getRow(i);
			XSSFCell text = row.getCell(0);
			expectedNavURLs.add(text.getStringCellValue());
		}
		return (ArrayList<String>) expectedNavURLs;
	}

	public void waitUntilConfirmationMessageIsVisible() {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		WebElement confirmationMessage;
		confirmationMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("greenPopup")));
	}
}
