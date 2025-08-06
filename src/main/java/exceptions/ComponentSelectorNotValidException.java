package exceptions;

public class ComponentSelectorNotValidException extends RuntimeException {
  public ComponentSelectorNotValidException() {
    super("Component selector not valid");
  }
}
