package testcases;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageobjects.AddVideoPage;
import pageobjects.HomePage;
import pageobjects.VideosPage;
import templates.TestTemplate;

public class AddVideo extends TestTemplate {

	@Inject
	private HomePage homePage;

	@Inject
	@Named("user.name")
	private String username;

	@Inject
	@Named("user.pass")
	private String password;

	@Inject
	@Named("file.name")
	private String fileName;

	@Inject
	@Named("file.url")
	private String fileURL;

	@Inject
	@Named("file.added.msg.format")
	private String msgFormat;

	@Inject
	@Named("file.url.format")
	private String urlFormat;

	@BeforeMethod
	public void init() {
		homePage.setDriver(webDriver);

		homePage.get().logInAs(username, password);
	}

	@Test
	public void addVideoTest() {
		AddVideoPage addVideoPage = homePage.get().goToAddVideoPage();

		addVideoPage.isLoaded();

		VideosPage videosPage = addVideoPage.addVideo(fileURL);

		Assert.assertEquals(videosPage.getMessage(), String.format(msgFormat, fileName));

		videosPage.goToFilePage();

		Assert.assertEquals(webDriver.getCurrentUrl(), String.format(urlFormat, fileName.replace(" ", "_")));
	}
}
