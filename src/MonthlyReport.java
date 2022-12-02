import java.util.ArrayList;
import java.util.List;

public class MonthlyReport extends Report {
    List<Expense> data = new ArrayList<>();
    private final int month;

    public MonthlyReport(int year, int month) {
        super(year);
        this.month = month;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
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
    }
}
