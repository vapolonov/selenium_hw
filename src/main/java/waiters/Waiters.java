package waiters;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
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

  public boolean waitForCondition(ExpectedCondition condition) {
    try {
      wait.until(condition);
      return true;
    } catch (Exception ignored) {
      return false;
    }
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

  public void acceptCookies() {
    By cookiesBtn = By.xpath("//*[@id='__next']/div[1]/div[3]/div/div/div/button");
    waitForCondition(ExpectedConditions.visibilityOf(driver.findElement(cookiesBtn)));
    driver.findElement(cookiesBtn).click();
  }
}
