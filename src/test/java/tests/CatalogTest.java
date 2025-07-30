package tests;

import com.google.inject.Inject;
import extensions.UIExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.CatalogPage;

@ExtendWith(UIExtension.class)
public class CatalogTest {

  @Inject
  private CatalogPage catalogPage;

  @Test
  public void courseShouldFindByNameInCatalog() {
    catalogPage.open();
  }
}
