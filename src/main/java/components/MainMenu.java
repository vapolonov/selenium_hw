package components;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static utils.RandomUtils.getRandomListItem;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;

public class MainMenu extends AbsComponent {

  public MainMenu(WebDriver driver) {
    super(driver);
  }

  @FindBy(xpath = "//span[@title='Обучение']")
    private WebElement trainingMenuBtn;

  @FindBy(xpath = "//nav//a[contains(@href, '/categories')]")
    private List<WebElement> categories;

  public MainMenu selectMenuItem() {
    actions.moveToElement(trainingMenuBtn).build().perform();
    return this;
  }

  public String selectRandomCategory() {
    assertThat(waiter.waitForCondition(ExpectedConditions.visibilityOfAllElements(categories)))
        .as("Categories not visible")
        .isTrue();
    WebElement randomCategory = categories.get(getRandomListItem(categories));
    String category = randomCategory.getText();
    randomCategory.click();
    return category;
  }
}
