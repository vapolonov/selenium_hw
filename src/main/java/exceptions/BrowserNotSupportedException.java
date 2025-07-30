package exceptions;

public class BrowserNotSupportedException extends RuntimeException {
  public BrowserNotSupportedException() {
    super("Browser not supported");
  }
}
