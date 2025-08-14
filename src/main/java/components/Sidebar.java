package components;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import annotations.Component;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Component("xpath://main//section[1]")
public class Sidebar extends AbsComponent {

  public Sidebar(WebDriver driver) {
    super(driver);
  }

  @FindBy(xpath = "(//div[@class='ReactCollapse--content'])[1]")
  WebElement directionsBlock;

  public void checkCourseCategory(String category) {
    String label = category.split(" \\(")[0];
    if ("Специализации".equals(label) || "Подготовительные курсы".equals(label)) {
      label = "Все направления";
    }
    WebElement courseLabel = getComponentEntity()
        .findElement(By.xpath(String.format(".//*[text()='%s']/preceding-sibling::div//input", label)));
    assertThat(courseLabel.isSelected()).isTrue();
  }
}
