package context;

import models.Expense;

public class DataContext {
  public static Expense getExpense() {
    return expense;
  }

  public static void setExpense(Expense expense) {
    DataContext.expense = expense;
  }

  private static Expense expense;

}
