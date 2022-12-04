import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Класс для сравнения годовых и месячных отчетов
 */
class Comparison {
    static void comparison(List<YearlyReport> yearlyReports, List<MonthlyReport> monthlyReports) {
        if (yearlyReports == null || monthlyReports == null) {
            System.out.println("Отсутствуют данные для сверки по одному или двум отчетам!");
            return;
        }
        Map<Integer, Map<Boolean, Integer>> year = null;
        Map<Integer, Map<Boolean, Integer>> month = null;
        for (YearlyReport yR : yearlyReports) {
            year = convertToMap(yR);
        }

        for (MonthlyReport mR : monthlyReports) {
            month = convertToMap(mR);
            for (Integer integer : month.keySet()) {
                Set<Boolean> booleans = year.get(integer).keySet();
                for (Boolean isIncome : booleans) {
                    if (!(year.get(integer).get(isIncome)).equals(month.get(integer).get(isIncome))) {
                        System.out.println("Данные в годовом отчете по месяцу № " + integer
                                + " не равны с данными из месячного отчета: "
                                + year.get(integer).get(isIncome) + " != "
                                + month.get(integer).get(isIncome) + "!"
                        );
                    }
                }
            }
        }
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
