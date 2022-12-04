import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Comparison {
    static void comparison(List<Report> yearlyReports, List<Report> monthlyReports) {
        System.out.println("y" + yearlyReports.size());
        System.out.println("m" + monthlyReports.size());

        int j = 0;
        for (Report yearlyReport : yearlyReports) {
            Map<Integer, Map<Boolean, Integer>> year = convertToMap((YearlyReport) yearlyReport);
            for (Integer integer : year.keySet()) {
                System.out.println(year.get(integer));
                ++j;
            }
        }
        System.out.println(j);

        int i = 0;
        for (Report monthlyReport : monthlyReports) {
            Map<Integer, Map<Boolean, Integer>> month = convertToMap((MonthlyReport) monthlyReport);
            for (Integer integer : month.keySet()) {
                System.out.println(month.get(integer));
                ++i;
            }
        }
        System.out.println(i);
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
