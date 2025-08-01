package commons;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import waiters.Waiters;

public abstract class AbsCommon {

  protected WebDriver driver;
  protected Waiters waiter;

  public AbsCommon(WebDriver driver) {
    this.driver = driver;
    this.waiter = new Waiters(driver).getWaitDriver();
    PageFactory.initElements(driver, this);
  }
}
