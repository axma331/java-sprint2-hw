import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Обработка содержимого файлов
 */
public class ProcessingOfDataFiles {

    /**
     * Получение содержимого директории FOLDER_PATH
     * @return массив файлы для обработки
     */
    private File[] getArrayOfReportFiles() throws NullPointerException {
        File[] files = new File(Const.FOLDER_PATH).listFiles();
        if (files == null) {
            throw new IllegalArgumentException();
        }
        int cnt = 0;
        for (File file : Objects.requireNonNull(files)) {
            if (isMatchesNamesTemplate(file)) {
                ++cnt;
            }
        }
        if (files.length == cnt) {
            throw new IllegalArgumentException();
        }
        return files;
    }

    /**
     * Получение, обработка и сохранение содержимого файлов отчета
     *
     * @param time отчетный период
     * @return сохраненный отчет за запрашиваемый период
     * @throws IOException при невозможности считывания из файла
     */
    public List<Report> readAndSaveContentFromFile(String time) throws IOException {
        File[] filesWithReports = getArrayOfReportFiles();
        List<Report> listOfReports = new ArrayList<>();
        for (File report : filesWithReports) {
            if (time.equals(Const.MONTH) && isMonthlyReportFile(report.getName())) {
                listOfReports.add(saveListOfMonthlyExpenses(report));
            } else if (time.equals(Const.YEAR) && isYearlyReportFile(report.getName())) {
                listOfReports.add(saveListOfYearlyExpenses(report));
            }
        }
        if (listOfReports.isEmpty()) {
            System.err.println("В файлах отсутствуют данные по отчетам!");
        }

        return listOfReports;
    }

    private MonthlyReport saveListOfMonthlyExpenses(File file) throws IOException {
        int year = Integer.parseInt(file.getName().substring(2, 6));
        int month = Integer.parseInt(file.getName().substring(7, 8));
        List<String> contents = Files.readAllLines(file.toPath());
        MonthlyReport report = new MonthlyReport(year, month);
        for (String commaSeparatedStr : contents.subList(1, contents.size())) {
            String[] splitStr = commaSeparatedStr.split(",");
            report.expenses.add(new MonthlyReport.Expense(
                    splitStr[0],
                    Integer.parseInt(splitStr[2]),
                    Integer.parseInt(splitStr[3]),
                    Boolean.parseBoolean(splitStr[1]))
            );
        }
        Map<Integer, Map<Boolean, Integer>> m = Comparison.convertToMap(report);
        System.out.println(m);
        return report;
    }

    private YearlyReport saveListOfYearlyExpenses(File file) throws IOException {
        int year = Integer.parseInt(file.getName().substring(2, 6));
        List<String> contents = Files.readAllLines(file.toPath());
        YearlyReport report = new YearlyReport(year);
        for (String commaSeparatedStr : contents.subList(1, contents.size())) {
            String[] splitStr = commaSeparatedStr.split(",");
            report.expenses.add(new YearlyReport.Expense(
                    Integer.parseInt(splitStr[0]),
                    Integer.parseInt(splitStr[1]),
                    Boolean.parseBoolean(splitStr[2])
            ));
        }
        Map<Integer, Map<Boolean, Integer>> m = Comparison.convertToMap(report);
        System.out.println(m);
        return report;
    }

    private boolean isMatchesNamesTemplate(File file) {
        return !(isMonthlyReportFile(file.getName()) || isYearlyReportFile(file.getName()));
    }

    private boolean isMonthlyReportFile(String fileName) {
        String[] checkName = fileName.split("[.]");
        return (checkName.length == 3
                && Const.MONTH.equals(checkName[0])
                && checkName[1].chars().allMatch(Character::isDigit) && checkName[1].length() == 6
                && "csv".equals(checkName[2]));
    }

    private boolean isYearlyReportFile(String fileName) {
        String[] checkName = fileName.split("[.]");
        return (checkName.length == 3
                && Const.YEAR.equals(checkName[0])
                && checkName[1].chars().allMatch(Character::isDigit) && checkName[1].length() == 4
                && "csv".equals(checkName[2]));
    }
}

