package guicemodules;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class TestModule extends AbstractModule {

	@Override
	public void configure() {
		Properties properties = new Properties();
		try {
			properties.load(new FileReader("src/main/resources/properties/env.properties"));
			properties.load(new FileReader("src/main/resources/properties/page.properties"));
			properties.load(new FileReader("src/main/resources/properties/test.properties"));

			Names.bindProperties(binder(), properties);
		} catch (FileNotFoundException e) {
			System.out.println("The configuration file Test.properties can not be found");
		} catch (IOException e) {
			System.out.println("I/O Exception during loading configuration");
		}
	}
}
