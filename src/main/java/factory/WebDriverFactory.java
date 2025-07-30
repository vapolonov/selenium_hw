package factory;

import exceptions.BrowserNotSupportedException;
import factory.settings.ChromeSettings;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.util.Locale;

public class WebDriverFactory {

  private String browserName = System.getProperty("browser.name", "chrome").toLowerCase(Locale.ROOT);

  public WebDriver getDriver() {
    switch (browserName) {
      case "chrome": {
        return new ChromeDriver((ChromeOptions) new ChromeSettings().getSettings(new DesiredCapabilities()));

      }
    }
    throw new BrowserNotSupportedException();
  }
}
