package pageobjects;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class AddVideoPage extends BasePageObject<AddVideoPage> {

	@Inject
	private VideosPage videosPage;

	@Inject
	@Named("add.video.page.url")
	private String pageURL;

	@FindBy(id = "wpWikiaVideoAddUrl")
	private WebElement videoURLInput;

	@FindBy(css = "input[value='Add']")
	private WebElement addVideoButton;

	public VideosPage addVideo(String videoURL) {
		videoURLInput.sendKeys(videoURL);
		addVideoButton.click();

		return videosPage.setDriver(webDriver);
	}

	@Override
	protected void load() {

	}

	@Override
	public void isLoaded() throws Error {
		Assert.assertEquals(webDriver.getCurrentUrl(), pageURL);
	}
}
