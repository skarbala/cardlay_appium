package enumerators;

public enum Currency {
  DKK("DKK");

  private String uiValue;

  Currency(String uiValue) {
    this.uiValue = uiValue;
  }

  public String getUiValue() {
    return uiValue;
  }
}
