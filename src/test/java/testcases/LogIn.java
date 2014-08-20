package testcases;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.apache.commons.lang3.text.WordUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageobjects.HomePage;
import templates.TestTemplate;

public class LogIn extends TestTemplate {

	@Inject
	private HomePage homePage;

	@Inject
	@Named("user.name")
	private String username;

	@Inject
	@Named("user.pass")
	private String password;

	@BeforeMethod
	public void init() {
		homePage.setDriver(webDriver);
	}

	@Test
	public void correctLogIn() {
		homePage.get().logInAs(username, password);

		Assert.assertEquals(homePage.getUserName(), WordUtils.capitalize(username));
	}
}
