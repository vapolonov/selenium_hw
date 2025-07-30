package modules;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import factory.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import pages.CatalogPage;

public class PageGuiceModule extends AbstractModule {

  private WebDriver driver;

  public PageGuiceModule(WebDriver driver) {
    this.driver = driver;
  }

  @Provides
  @Singleton
  public CatalogPage getCatalogPage() {
    return new CatalogPage(driver);
  }
}
