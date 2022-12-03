import java.util.ArrayList;
import java.util.List;

public class YearlyReport implements Report {
    List<Expense> expenses = new ArrayList<>();
    private final int year;

    public YearlyReport(int year) {
        this.year = year;
    }

    public int getYear() {
        return year;
    }

    @Override
    public int minExpense() {
        int min = 0;
        for (Expense expense : expenses) {
            if (min > expense.amount && expense.isExpense) {
                min = expense.amount;
            }
        }
        return min;
    }

    @Override
    public int minIncome() {
        int min = 0;
        for (Expense expense : expenses) {
            if (min > expense.amount && !expense.isExpense) {
                min = expense.amount;
            }
        }
        return min;
    }

    @Override
    public int maxExpense() {
        int max = 0;
        for (Expense expense : expenses) {
            if (max < expense.amount && expense.isExpense) {
                max = expense.amount;
            }
        }
        return max;
    }

    @Override
    public int maxIncome() {
        int max = 0;
        for (Expense expense : expenses) {
            if (max < expense.amount && !expense.isExpense) {
                max = expense.amount;
            }
        }
        return max;
    }

    @Override
    public double avgExpense() {
        int avg = 0, cnt = 0;
        for (Expense expense : expenses) {
            if (expense.isExpense) {
                avg += expense.amount;
                ++cnt;
            }
        }
        return (double) avg / cnt;
    }

    @Override
    public double avgIncome() {
        int avg = 0, cnt = 0;
        for (Expense expense : expenses) {
            if (!expense.isExpense) {
                avg += expense.amount;
                ++cnt;
            }
        }
        return (double) avg / cnt;
    }

    @Override
    public String toString() {
        return "Ежегодный отчет за " +
                year + "\n " + expenses;
    }

    static class Expense {
        private final int month;
        private final int amount;
        private final boolean isExpense;

        /**
         * Вложенный класс для хранения данных из отчета о движении денежных средств
         *
         * @param month     месяц
         * @param amount    сумма за год
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

        @Override
        public String toString() {
            return  month +
                    "| " + amount +
                    "| " + isExpense +
                    "\n";
        }
    }
}
