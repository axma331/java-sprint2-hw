import java.util.ArrayList;
import java.util.List;

public class MonthlyReport implements Report {
    List<Expense> expenses = new ArrayList<>();
    private final int year;
    private final int month;

    public MonthlyReport(int year, int month) {
        this.year = year;
        this.month = month;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    @Override
    public int minExpense() {
        int min = 0;
        for (Expense expense : expenses) {
            if (min > expense.cost() && expense.isExpense) {
                min = expense.cost();
            }
        }
        return min;
    }

    @Override
    public int minIncome() {
        int min = 0;
        for (Expense expense : expenses) {
            if (min > expense.cost() && !expense.isExpense) {
                min = expense.cost();
            }
        }
        return min;
    }

    @Override
    public int maxExpense() {
        int max = 0;
        for (Expense expense : expenses) {
            if (max < expense.cost() && expense.isExpense) {
                max = expense.cost();
            }
        }
        return max;
    }

    @Override
    public int maxIncome() {
        int max = 0;
        for (Expense expense : expenses) {
            if (max < expense.cost() && !expense.isExpense) {
                max = expense.cost();
            }
        }
        return max;
    }

    @Override
    public double avgExpense() {
        int avg = 0, cnt = 0;
        for (Expense expense : expenses) {
            if (expense.isExpense) {
                avg += expense.cost();
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
                avg += expense.cost();
                ++cnt;
            }
        }
        return (double) avg / cnt;
    }

    @Override
    public String toString() {
        return "Ежемесячный отчет за " +
               month + "." + year +
                "\n " + expenses;
    }

    static class Expense {
        private final String itemName;
        private final int quantity;
        private final int sumOfOne;
        private final boolean isExpense;

        /**
         * Вложенный класс для хранения данных из отчета о движении денежных средств
         *
         * @param itemName  Название товара
         * @param quantity  Количество закупленного или проданного товара
         * @param sumOfOne  Стоимость одной единицы товара. Целое число
         * @param isExpense Обозначает, является ли запись тратой (TRUE) или доходом (FALSE)
         */
        Expense(String itemName, int quantity, int sumOfOne, boolean isExpense) {
            this.itemName = itemName;
            this.quantity = quantity;
            this.sumOfOne = sumOfOne;
            this.isExpense = isExpense;
        }

        public String getItemName() {
            return itemName;
        }

        public int getQuantity() {
            return quantity;
        }

        public int getSumOfOne() {
            return sumOfOne;
        }

        public boolean isExpense() {
            return isExpense;
        }

        public int cost() {
            return quantity * sumOfOne;
        }

        @Override
        public String toString() {
            return itemName +
                    "| " + quantity +
                    "| " + sumOfOne +
                    "| " + isExpense +
                    "\n";
        }
    }
}
