package utils;

import org.openqa.selenium.WebElement;
import java.util.List;
import java.util.Random;

public class RandomUtils {

  private static final Random RANDOM = new Random();

  public static int getRandomListItem(List<WebElement> list) {
    return RANDOM.nextInt(list.size());
  }
}
