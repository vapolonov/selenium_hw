package factory;

import exceptions.BrowserNotSupportedException;
import factory.settings.ChromeSettings;
import factory.settings.FirefoxSettings;
import listeners.MouseListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringDecorator;
import java.net.MalformedURLException;
import java.util.Locale;

public class WebDriverFactory {

  private String browserName = System.getProperty("browser.name", "chrome").toLowerCase(Locale.ROOT);

  public WebDriver getDriver() throws MalformedURLException {
    WebDriver driver;

    switch (browserName) {
      case "chrome": {
        driver = new ChromeDriver((ChromeOptions) new ChromeSettings().getSettings(new DesiredCapabilities()));
        break;
      }
      case "firefox": {
        driver = new FirefoxDriver(new FirefoxSettings().settings());
        break;
      }
      default: {
        throw new BrowserNotSupportedException();
      }
    }
    return new EventFiringDecorator<>(new MouseListener()).decorate(driver);
  }

}
