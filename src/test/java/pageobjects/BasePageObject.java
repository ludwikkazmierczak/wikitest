package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

public abstract class BasePageObject<T extends LoadableComponent<T>> extends LoadableComponent<T> {
	protected WebDriver webDriver;

	public <T> T setDriver(WebDriver webDriver) {
		this.webDriver = webDriver;

		PageFactory.initElements(webDriver, this);

		return (T) this;
	}
}
