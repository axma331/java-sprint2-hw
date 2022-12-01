import java.util.ArrayList;

public class YearlyReport {
    public ArrayList<Expense> data = new ArrayList<>();

    /**
     * Вложенный класс с данными отчета <br><br>
     * month месяц <br>
     * amount сумма за год <br>
     * is_expense обозначает, является ли запись тратой (TRUE) или доходом (FALSE) <br>
     */
    static class Expense {
        private int month;
        private int amount;
        private boolean isExpense;

        /**
         * @param dataArray массив данных полученный из файла отчета
         */
        Expense(String[] dataArray) {
            this.month = Integer.parseInt(dataArray[0]);
            this.amount = Integer.parseInt(dataArray[1]);
            this.isExpense = Boolean.parseBoolean(dataArray[2]);
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
