import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MonthlyReport {
    List<Expense> expenses = new ArrayList<>();
    final int year;
    final int month;

    public MonthlyReport(int year, int month) {
        this.year = year;
        this.month = month;
    }

    
    public int minExpense() {
        int min = 0;
        for (Expense expense : expenses) {
            if (min > expense.cost() && expense.isExpense) {
                min = expense.cost();
            }
        }
        return min;
    }

    
    public int minIncome() {
        int min = 0;
        for (Expense expense : expenses) {
            if (min > expense.cost() && !expense.isExpense) {
                min = expense.cost();
            }
        }
        return min;
    }

    
    public int maxExpense() {
        int max = 0;
        for (Expense expense : expenses) {
            if (max < expense.cost() && expense.isExpense) {
                max = expense.cost();
            }
        }
        return max;
    }

    
    public int maxIncome() {
        int max = 0;
        for (Expense expense : expenses) {
            if (max < expense.cost() && !expense.isExpense) {
                max = expense.cost();
            }
        }
        return max;
    }

    
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

    Map<String, Integer> maxIncomeItem() {
        int maxIncome = 0;
        String maxItemName = "";
        for (Expense expense : expenses) {
            if (expense.isExpense) {
                if (expense.cost() > maxIncome) {
                    maxIncome = expense.cost();
                    maxItemName = expense.itemName;
                }
            }
        }
        return Map.of(maxItemName, maxIncome);
    }


    static class Expense {
        final String itemName;
        final int quantity;
        final int sumOfOne;
        final boolean isExpense;

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

        public int cost() {
            return quantity * sumOfOne;
        }

        
        public String toString() {
            return itemName + " "
                    + quantity + " "
                    + sumOfOne + " "
                    + "\n";
        }
    }

    
    public String toString() {
        return "Ежемесячный отчет за "
                + month + "." + year + "\n "
                + expenses.toString()
                .replace("[", "")
                .replace("]", "")
                .replace(",", "")
                + "Самый прибыльный товар: "
                + maxIncomeItem().toString()
                .replace("{", "")
                .replace("}", "")
                .replace("=", " = ") + "\n";
    }
}
