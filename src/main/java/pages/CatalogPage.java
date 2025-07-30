package pages;

import annotations.Path;
import org.openqa.selenium.WebDriver;

@Path("/catalog/courses")
public class CatalogPage extends AbsBasePage<CatalogPage> {

  public CatalogPage(WebDriver driver) {
    super(driver);
  }
}
