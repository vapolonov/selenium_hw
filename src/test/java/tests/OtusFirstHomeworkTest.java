package tests;

import com.google.inject.Inject;
import components.MainMenu;
import extensions.UIExtension;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebElement;
import pages.CatalogPage;
import pages.CoursePage;
import pages.MainPage;
import java.util.List;
import java.util.Map;

@ExtendWith(UIExtension.class)
public class OtusFirstHomeworkTest {

  SoftAssertions softly = new SoftAssertions();

  @Inject
  private CatalogPage catalogPage;

  @Inject
  private CoursePage coursePage;

  @Inject
  private MainPage mainPage;

  @Inject
  private MainMenu mainMenu;

  @Test
  public void courseShouldFindByNameInCatalog() {
    String courseName = "Java QA Engineer. Professional";
    String searchQuery = "Java";
    catalogPage.open()
        .searchCourse(searchQuery)
        .selectCourse(courseName);
    coursePage.checkCourseTitle(courseName);
  }

  @Test
  public void courseCategoryShouldOpenViaMainMenu() {
    mainPage.open();
    mainMenu.selectMenuItem();
    String category = mainMenu.selectRandomCategory();
    catalogPage.checkCourseCategory(category);
  }

  @Test
  public void latestAndEarlierCourseShouldBeDisplayed() {
    catalogPage.open();

    Map<String, List<WebElement>> coursesByDate = Map.of(
        "Earliest", catalogPage.getEarliestCourses(),
        "Latest", catalogPage.getLatestCourses()
    );

    coursesByDate.forEach((label, courses) ->
        courses.forEach(course ->
            softly.assertThat(catalogPage.isCourseOnPage(course))
                .as("%s course should be present on the page: %s", label, course.getText())
                .isTrue()
        )
    );
    softly.assertAll();
  }
}
