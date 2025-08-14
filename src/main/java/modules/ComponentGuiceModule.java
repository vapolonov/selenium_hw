package modules;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import components.MainMenu;
import components.Sidebar;
import org.openqa.selenium.WebDriver;

public class ComponentGuiceModule extends AbstractModule {
  private WebDriver driver;

  public ComponentGuiceModule(WebDriver driver) {
    this.driver = driver;
  }

  @Provides
  @Singleton
  public MainMenu getMainMenu() {
    return new MainMenu(driver);
  }

  @Provides
  @Singleton
  public Sidebar getSidebar() {
    return new Sidebar(driver);
  }
}
