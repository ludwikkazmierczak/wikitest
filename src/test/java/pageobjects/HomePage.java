package pageobjects;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class HomePage extends BasePageObject<HomePage> {

	@Inject
	@Named("home.page.url")
	private String pageURL;

	@Inject
	@Named("home.page.title")
	private String pageTitle;

	@Inject
	@Named("home.page.redirect.url")
	private String redirectURL;

	@FindBy(css = ".wikia-menu-button.contribute")
	private WebElement contributeMenuButton;

	@FindBy(css = "a[data-id='wikiavideoadd']")
	private WebElement addVideoOption;

	@FindBy(css = "a.ajaxLogin, #AccountNavigation a[href*='User:']")
	private WebElement accountNavigation;

	@FindBy(css = "input[name='username']")
	private WebElement loginInput;

	@FindBy(css = "input[name='password']")
	private WebElement passwordInput;

	@FindBy(css = ".login-button")
	private WebElement loginButton;

	@Inject
	private AddVideoPage addVideoPage;

	public HomePage logInAs(String username, String password) {
		accountNavigation.click();
		loginInput.sendKeys(username);
		passwordInput.sendKeys(password);
		loginButton.submit();

		return this;
	}

	public String getUserName() {
		return accountNavigation.getText();
	}

	public AddVideoPage goToAddVideoPage() {
		contributeMenuButton.click();
		addVideoOption.click();

		return addVideoPage.setDriver(webDriver);
	}

	@Override
	protected void load() {
		webDriver.get(pageURL);
	}

	@Override
	protected void isLoaded() throws Error {
		Assert.assertEquals(webDriver.getTitle(), pageTitle);
		Assert.assertEquals(webDriver.getCurrentUrl(), redirectURL);
	}
}
