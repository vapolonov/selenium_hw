package listeners;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WrapsDriver;
import org.openqa.selenium.support.events.WebDriverListener;

public class MouseListener implements WebDriverListener {

  @Override
  public void beforeClick(WebElement element) {
    WebDriver driver;
    if (element instanceof WrapsDriver) {
      driver = ((WrapsDriver) element).getWrappedDriver();
    } else {
      throw new IllegalArgumentException("Element does not implement WrapsDriver");
    }
    JavascriptExecutor js = (JavascriptExecutor) driver;
    js.executeScript("arguments[0].style.border='1px solid red';",
        element);
  }
}
