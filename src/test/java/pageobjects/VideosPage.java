package pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class VideosPage extends BasePageObject<VideosPage> {

	@FindBy(css = ".global-notification .msg")
	private WebElement messageBox;

	@FindBy(css = ".global-notification .msg a")
	private WebElement fileLink;

	public String getMessage() {
		return messageBox.getText();
	}

	public void goToFilePage() {
		fileLink.click();
	}

	@Override
	protected void load() {

	}

	@Override
	protected void isLoaded() throws Error {

	}
}
