import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

class YearlyReport {
    List<Expense> expenses = new ArrayList<>();
    public final int year;

    YearlyReport(int year) {
        this.year = year;
    }

    
    public int minExpense() {
        int min = 0;
        for (Expense expense : expenses) {
            if (min > expense.amount && expense.isExpense) {
                min = expense.amount;
            }
        }
        return min;
    }

    
    public int minIncome() {
        int min = 0;
        for (Expense expense : expenses) {
            if (min > expense.amount && !expense.isExpense) {
                min = expense.amount;
            }
        }
        return min;
    }

    
    public int maxExpense() {
        int max = 0;
        for (Expense expense : expenses) {
            if (max < expense.amount && expense.isExpense) {
                max = expense.amount;
            }
        }
        return max;
    }

    
    public int maxIncome() {
        int max = 0;
        for (Expense expense : expenses) {
            if (max < expense.amount && !expense.isExpense) {
                max = expense.amount;
            }
        }
        return max;
    }

    
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


    Map<Integer, Integer> maxIncomeItem() throws NullPointerException {
        int maxIncome = 0;
        Integer maxItemName = null;
        for (YearlyReport.Expense expense : expenses) {
            if (expense.isExpense) {
                if (expense.amount > maxIncome) {
                    maxIncome = expense.amount;
                    maxItemName = expense.month;
                }
            }
        }
        return Map.of(Objects.requireNonNull(maxItemName), maxIncome);
    }

    static public class Expense {
        final int month;
        final int amount;
        final boolean isExpense;

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

        
        public String toString() {
            return month + " "
                    + amount + " "
                    + isExpense + " \n";
        }
    }

    
    public String toString() {
        return "Ежегодный отчет за " +
                year + "\n " + expenses +
                "\nСамый прибыльный товар :" + maxIncomeItem() + "\n";
    }
}
