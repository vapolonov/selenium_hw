package pages;

import static org.assertj.core.api.Assertions.assertThat;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CoursePage extends AbsBasePage<CoursePage> {

  public CoursePage(WebDriver driver) {
    super(driver);
  }

  @FindBy(css = "h1")
  WebElement courseTitle;

  public void checkCourseTitle(String title) {
    assertThat(courseTitle.getText()).isEqualTo(title);
  }
}
