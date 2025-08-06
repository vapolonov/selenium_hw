package factory.settings;

import org.openqa.selenium.firefox.FirefoxOptions;

public class FirefoxSettings {

  public FirefoxOptions settings() {
    FirefoxOptions options = new FirefoxOptions();
    options.addArguments("--width=1920");
    options.addArguments("--height=1080");
    return options;
  }

}
