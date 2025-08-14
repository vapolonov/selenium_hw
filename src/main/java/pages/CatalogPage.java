package pages;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import annotations.Path;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Path("/catalog/courses")
public class CatalogPage extends AbsBasePage<CatalogPage> {

  public CatalogPage(WebDriver driver) {
    super(driver);
  }

  private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
      "d MMMM, yyyy", new Locale("ru")
  );

  @FindBy(css = "input[type='search']")
  private WebElement searchInput;

  @FindBy(xpath = "//main//section[2]")
  private WebElement catalogSection;

  @FindBy(xpath = "//section[2]//a/div[2]/div/div")
  List<WebElement> coursesDates;

  public CatalogPage searchCourse(String courseName) {
    searchInput.click();
    searchInput.sendKeys(courseName);
    return this;
  }

  public CatalogPage selectCourse(String data) {
    By courseLocator = By.xpath(String.format(".//*[contains(text(), '%s')]", data));
    assertThat(waiter.waitForCondition(ExpectedConditions.presenceOfElementLocated(courseLocator)))
        .as("Course not found: " + data)
        .isTrue();
    catalogSection.findElement(courseLocator).click();
    return this;
  }

  public List<WebElement> getEarliestCourses() {
    return getCoursesByExtremeDate(Comparator.naturalOrder());
  }

  public List<WebElement> getLatestCourses() {
    return getCoursesByExtremeDate(Comparator.reverseOrder());
  }

  private List<WebElement> getCoursesByExtremeDate(Comparator<LocalDate> comparator) {
    List<Map.Entry<WebElement, LocalDate>> coursesWithDates = coursesDates.stream()
        .map(course -> Map.entry(course, parseCourseDate(course)))
        .toList();

    return coursesWithDates.stream()
        .reduce((entry1, entry2) ->
            comparator.compare(entry1.getValue(), entry2.getValue()) <= 0 ? entry1 : entry2
        )
        .map(extremeEntry -> coursesWithDates.stream()
            .filter(entry -> entry.getValue().equals(extremeEntry.getValue()))
            .map(Map.Entry::getKey)
            .collect(Collectors.toList()))
        .orElse(List.of());
  }

  private LocalDate parseCourseDate(WebElement course) {
    String dateText = course.getText().trim().split(" . ")[0].trim();
    return LocalDate.parse(dateText, formatter);
  }

  public boolean isCourseOnPage(WebElement courseDate) {
    String dateText = courseDate.getText().trim();
    Document doc = Jsoup.parse(Objects.requireNonNull(driver.getPageSource()));
    return doc.selectFirst(String.format("div:containsOwn(%s)", dateText)) != null;
  }
}
