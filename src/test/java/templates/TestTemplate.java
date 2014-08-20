package templates;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import guicemodules.TestModule;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Guice;

import java.util.concurrent.TimeUnit;

@Guice(modules = TestModule.class)
public class TestTemplate {

	protected WebDriver webDriver;

	@Inject
	@Named("webdriver.chrome.driver")
	private String chromedriverPath;

	@Inject
	@Named("browser.type")
	private String browserType;

	@BeforeMethod
	protected void envSetup() {

		if (StringUtils.isNotBlank(System.getProperty("browser.type"))) {
			browserType = System.getProperty("browser.type");
		}

		if (browserType.toLowerCase().equals("ff") || browserType.toLowerCase().equals("firefox")) {
			webDriver = new FirefoxDriver();
		} else if (browserType.toLowerCase().equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", chromedriverPath);

			webDriver = new ChromeDriver();
		} else {
			webDriver = new FirefoxDriver();
		}

		webDriver.manage().window().maximize();
		webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@AfterMethod
	public void tearDown() {
		if (!webDriver.equals(null)) {
			webDriver.quit();
		}
	}
}
