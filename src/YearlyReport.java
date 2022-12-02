import java.util.ArrayList;
import java.util.List;

public class YearlyReport extends Report {
    List<Expense> data = new ArrayList<>();

    public YearlyReport(int year) {
        super(year);
    }

    public int getYear() {
        return year;
    }

    static class Expense {
        private final int month;
        private final int amount;
        private final boolean isExpense;

        /**
         * Вложенный класс для хранения данных из отчета о движении денежных средств
         * @param month месяц
         * @param amount сумма за год
         * @param isExpense обозначает, является ли запись тратой (TRUE) или доходом (FALSE)
         */
        Expense(int month, int amount, boolean isExpense) {
            this.month = month;
            this.amount = amount;
            this.isExpense = isExpense;
        }

        public int getMonth() {
            return month;
        }

        public int getAmount() {
            return amount;
        }

        public boolean isExpense() {
            return isExpense;
        }
    }

}
