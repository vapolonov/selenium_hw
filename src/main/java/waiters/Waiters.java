package waiters;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Waiters {

  private final WebDriver driver;
  WebDriverWait wait;

  public Waiters(WebDriver driver) {
    this.driver = driver;
  }

  public Waiters getWaitDriver() {
    wait = new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(System.getProperty("waiter.timeout"))));
    return this;
  }

  public void waitUntilPageIsReady() {
    wait.until((ExpectedCondition<Boolean>) wd ->
        {
          assert wd != null;
          return ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete");
        }
    );
  }

  public void waitForJQueryCompletes() {
    wait.until((ExpectedCondition<Boolean>) wd -> {
      JavascriptExecutor js = (JavascriptExecutor) wd;
      Boolean isJQueryDefined = (Boolean) js.executeScript("return typeof jQuery != 'undefined'");
      if (isJQueryDefined) {
        Long activeRequests = (Long) js.executeScript("return jQuery.active");
        return activeRequests == 0;
      } else {
        // Если jQuery не используется
        return true;
      }
    });
  }

  public void waitForElementVisible(WebElement element) {
    wait.until(ExpectedConditions.visibilityOf(element));
  }

  public void waitForElementVisible(By locator) {
    wait.until(ExpectedConditions.presenceOfElementLocated(locator));
  }

  public void waitForElementNotVisible(WebElement element) {
    wait.until(ExpectedConditions.invisibilityOf(element));
  }
}
