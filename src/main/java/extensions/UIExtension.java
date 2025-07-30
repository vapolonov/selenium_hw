package extensions;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import factory.WebDriverFactory;
import modules.PageGuiceModule;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.WebDriver;

public class UIExtension implements BeforeEachCallback, AfterEachCallback {

  private WebDriver driver;

  @Override
  public void beforeEach(ExtensionContext context) throws Exception {
    driver = new WebDriverFactory().getDriver();

    Injector injector = Guice.createInjector(
        new PageGuiceModule(driver)
    );
    injector.injectMembers(context.getTestInstance().get());
  }

  @Override
  public void afterEach(ExtensionContext context) throws Exception {
    if (driver != null) {
      driver.quit();
    }

  }
}
