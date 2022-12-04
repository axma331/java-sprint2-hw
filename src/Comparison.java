import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

class Comparison {
    static void comparison(List<Report> yearlyReports, List<Report> monthlyReports) {
        Map<Integer, Map<Boolean, Integer>> year = null;
        Map<Integer, Map<Boolean, Integer>> month = null;
//        System.out.println("y" + yearlyReports.size());
//        System.out.println("m" + monthlyReports.size());
        int j = 0;
        for (Report yearlyReport : yearlyReports) {
            year = convertToMap((YearlyReport) yearlyReport);
//            System.out.println("year.keySet: " + year.keySet());
//            for (Integer integer : year.keySet()) {
//                ++j;
//                System.out.println(j + "y " + integer + " " + year.get(integer));
//            }
        }
//        System.out.println(j);

        int i = 0;
        for (Report monthlyReport : monthlyReports) {
            month = convertToMap((MonthlyReport) monthlyReport);
//            System.out.println("month.keySet: " + month.keySet());
            for (Integer integer : month.keySet()) {
//                ++i;
//                System.out.println(i + "m " + integer + " " + month.get(integer));
//                System.out.println("------------------------");
                Set<Boolean> booleans = year.get(integer).keySet();
                for (Boolean isIncome : booleans) {
                    System.out.println(year.get(integer).get(isIncome));
                    System.out.println(month.get(integer).get(isIncome));
                    if (!(year.get(integer).get(isIncome)).equals(month.get(integer).get(isIncome))) {
                        System.out.println("Данные в годовом отчете по месяцу № " + integer
                                + " не равны с данными из месячного отчета: "
                                + year.get(integer).get(isIncome) + " != "
                                + month.get(integer).get(isIncome) + "!"
                        );
                    }
                }
                //                System.out.println("------------------------");
            }
        }
//        System.out.println(i);
//        System.out.println("year" + year);
//        System.out.println("month" + month);
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
