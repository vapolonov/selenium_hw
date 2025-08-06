package commons;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import waiters.Waiters;

public abstract class AbsCommon {

  protected WebDriver driver;
  protected Waiters waiter;
  protected Actions actions;

  public AbsCommon(WebDriver driver) {
    this.driver = driver;
    this.waiter = new Waiters(driver).getWaitDriver();
    this.actions = new Actions(driver);
    PageFactory.initElements(driver, this);
  }

  public void acceptCookies() {

  }
}
