import java.util.ArrayList;

public class MonthlyReport {
    public ArrayList<Expense> data = new ArrayList<>();

    /**
     * Вложенный класс с данными отчета <br><br>
     * itemName - Название товара <br>
     * quantity - Количество закупленного или проданного товара <br>
     * sumOfOne - Стоимость одной единицы товара. Целое число <br>
     * isExpense - Обозначает, является ли запись тратой (TRUE) или доходом (FALSE) <br>
     */
    static class Expense {
        private String itemName;
        private int quantity;
        private int sumOfOne;
        protected boolean isExpense;

        /**
         * @param dataArray массив данных полученный из файла отчета
         */
        Expense(String[] dataArray) {
            this.itemName = dataArray[0];
            this.quantity = Integer.parseInt(dataArray[2]);
            this.sumOfOne = Integer.parseInt(dataArray[3]);
            this.isExpense = Boolean.parseBoolean(dataArray[1]);
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
