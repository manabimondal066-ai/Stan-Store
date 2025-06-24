package testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
	public static WebDriver driver;
	public Logger logger;
	public Properties p;

	@BeforeClass
	@Parameters({ "os", "browser" })
	public void setUp(String os, String br) throws IOException {
		// loading config.properties file
		FileInputStream file = new FileInputStream(".//src//test//resources//config.properties");
		p = new Properties();
		p.load(file);
		logger = LogManager.getLogger(this.getClass()); // log4j2

		switch (br.toLowerCase()) {
		case "chrome":
			driver = new ChromeDriver();
			break;
		case "edge":
			driver = new EdgeDriver();
			break;
		case "firefox":
			driver = new FirefoxDriver();
			break;
		default:
			System.out.println("Invalid Browser name, try again..");
			return;
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		//driver.get(p.getProperty("appUrl"));
		driver.get("https://stage-store.getstan.app/");
		driver.manage().window().maximize();
	}

	@AfterClass(groups = { "Regression", "Sanity", "Master" })
	public void tearDown() {
		driver.quit();
	}

	public String randomString() {
		String generatedStringr = RandomStringUtils.randomAlphabetic(5);
		return generatedStringr;
	}

	public String randomNumber(int a) {
		String generatedNumber = RandomStringUtils.randomNumeric(a);
		return generatedNumber;
	}

	public String randomAlphaNumeric() {
		String generatedStringr = RandomStringUtils.randomAlphabetic(5);
		String generatedNumber = RandomStringUtils.randomNumeric(3);
		return generatedStringr + generatedNumber + "@gmail.com";
	}

	public String captureScreen(String tname) throws IOException {
		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp + ".png";
		File targetFile = new File(targetFilePath);
		sourceFile.renameTo(targetFile);
		return targetFilePath;
	}
}
