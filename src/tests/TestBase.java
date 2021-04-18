package tests;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.imageio.ImageIO;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import pages.PracticeFormPage;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.coordinates.WebDriverCoordsProvider;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;


public class TestBase {
	WebDriver driver;
	PracticeFormPage practiceFormPage;
	ExcelReader excelReader;
	
	@BeforeClass
	public void beforeClass() throws IOException {
		
		System.setProperty("webdriver.chrome.driver", "driver-lib\\chromedriver.exe");
		
		this.driver = new ChromeDriver();
		this.practiceFormPage = new PracticeFormPage(driver);
		this.excelReader = new ExcelReader("data\\testData.xlsx");
		
		driver.manage().window().maximize();
	}
	@AfterClass
	public void afterClass() {
		driver.close();
	}
	
	public void captureFullPageScreenshot() throws IOException {
		
		Screenshot s = new AShot()
				.shootingStrategy(ShootingStrategies.viewportPasting(ShootingStrategies.scaling(1.25f), 1500))
				.takeScreenshot(driver);
		ImageIO.write(s.getImage(), "PNG", new File("data\\screenshots\\fullPage\\fullPageScreenshot.png"));
	}
	public void screenshotOfVisiblePartOfPageWithTimestamp() throws IOException {
		Date d = new Date();
		String fileName = d.toString().replace(":", "_").replace(" ", "_") + ".png";
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileHandler.copy(screenshot, new File("data\\screenshots\\visibleArea\\" + fileName));
	}
	public void captureScreenshotOfSpecificElement(WebElement element, String fileName) throws IOException {
	    Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(ShootingStrategies.scaling(1.25f), 1500))
	      .coordsProvider(new WebDriverCoordsProvider())
	      .takeScreenshot(driver, element);
	    ImageIO.write(screenshot.getImage(),"PNG",new File("data\\screenshots\\element\\" + fileName + ".png"));
	}
	public void clickOKToCloseAlertBox() {
		driver.switchTo().alert().accept();
	}
}
