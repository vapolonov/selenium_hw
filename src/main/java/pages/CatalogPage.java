package pages;

import annotations.Path;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Path("/catalog/courses")
public class CatalogPage extends AbsBasePage<CatalogPage> {

  public CatalogPage(WebDriver driver) {
    super(driver);
  }

  @FindBy(css = "input[type='search']")
  private WebElement searchInput;

  public CatalogPage searchCourse(String courseName) {
    searchInput.click();
    searchInput.sendKeys(courseName);
    return this;
  }

  public CatalogPage selectCourse(String data) {
    By courseLocator = By.xpath(String.format("//h6/div[text()='%s']", data));
    waiter.waitForElementVisible(courseLocator);
    driver.findElement(courseLocator).click();
    return this;
  }
}
