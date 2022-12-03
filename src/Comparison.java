import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Comparison {
    void comparison(List<YearlyReport> yearlyReports, List<YearlyReport> monthlyReports) {

    }


    static Map<Integer, Map<Boolean, Integer>> convertToMap(YearlyReport report) {
        Map<Integer, Map<Boolean, Integer>> bigMap = new HashMap<>();
        for (YearlyReport.Expense expense : report.expenses) {
            if (!bigMap.containsKey(expense.month)) {
                bigMap.put(expense.month, new HashMap<>());
            }
            Map<Boolean, Integer> values = bigMap.get(expense.month);
            values.put(expense.isExpense, expense.amount);
        }
        return bigMap;
    }

    static Map<Integer, Map<Boolean, Integer>> convertToMap(MonthlyReport report) {
        Map<Integer, Map<Boolean, Integer>> bigMap = new HashMap<>();
        for (MonthlyReport.Expense expense : report.expenses) {
            if (!bigMap.containsKey(report.month)) {
                bigMap.put(report.month, new HashMap<>());
            }
            Map<Boolean, Integer> values = bigMap.get(report.month);
            values.put(expense.isExpense, values.getOrDefault(expense.isExpense, 0) + expense.cost());
        }
        return bigMap;
    }
}
