package tests;

import org.junit.Test;

import context.AndroidTestBase;
import enumerators.Currency;
import models.Expense;
import pages.MainPage;
import pages.NewExpenseDialog;

public class OtherExpensesTest extends AndroidTestBase {

  @Test
  public void itShouldAddNewExpense() {
    Expense expense = new Expense();
    expense.setCategory("Food and beverage");
    expense.setCurrency(Currency.DKK);
    expense.setComment("Expense_Dinner-Mestiansky pivovar");
    expense.setPrice("234");
    expense.setMerchant("Test merchant automation");
    expense.setPlace("Test place automation");
    expense.setClient("Test place automation");

    new MainPage()
        .logInWithFakeUser()
        .switchToExpenses()
        .clickAddPrivateExpense();

    new NewExpenseDialog()
        .addNewExpenseData(expense)
        .clickSaveButton();

    new MainPage().waitUntilExpenseAppears(expense);
  }
}
