package tests;

import com.google.inject.Inject;
import extensions.UIExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.CatalogPage;
import pages.CoursePage;

@ExtendWith(UIExtension.class)
public class CatalogTest {

  @Inject
  private CatalogPage catalogPage;

  @Inject
  private CoursePage coursePage;

  @Test
  public void courseShouldFindByNameInCatalog() {
    catalogPage.open()
        .searchCourse("Java QA")
        .selectCourse("Java QA Engineer. Professional");

  }
}
