package factory.settings;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.AbstractDriverOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class ChromeSettings implements ISettings {

  @Override
  public AbstractDriverOptions getSettings(DesiredCapabilities caps) {
    ChromeOptions chromeOptions = new ChromeOptions();
    chromeOptions.merge(caps);
    chromeOptions.addArguments("--start-maximized");
    return chromeOptions;
  }
}
