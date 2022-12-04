import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProcessingOfDataFiles {

    /**
     * Получение, обработка и сохранение содержимого файлов отчета
     *
     * @param time отчетный период
     * @return сохраненный отчет за запрашиваемый период
     * @throws IOException при невозможности считывания из файла
     */
    static public List<YearlyReport> getYearlyReportContents() throws IOException {
        List<YearlyReport> savedReports = new ArrayList<>();
        File[] filesWithReports = getFiles();
        for (File file : filesWithReports) {
            if (isYearlyReportFile(file.getName())) {
                savedReports.add(saveListOfYearlyExpenses(file));
            }
        }
        if (savedReports.isEmpty()) {
            System.err.println("В файлах отсутствуют отчётные данные!");
        }
        return savedReports;
    }

    static public List<MonthlyReport> getMonthlyReportContents() throws IOException {
        List<MonthlyReport> savedReports = new ArrayList<>();
        File[] filesWithReports = getFiles();
        for (File file : filesWithReports) {
            if (isMonthlyReportFile(file.getName())) {
                savedReports.add(getMonthlyExpense(file));
            }
        }
        if (savedReports.isEmpty()) {
            System.err.println("В файлах отсутствуют отчётные данные!");
        }
        return savedReports;
    }

    static private File[] getFiles() throws NullPointerException {
        File[] files = new File("resources").listFiles();
        int cnt = 0;
        for (File file : Objects.requireNonNull(files)) {
            if (!isMatchesNamesTemplate(file)) {
                ++cnt;
            }
        }
        if (files.length == cnt) {
            throw new NoReportsException("Отсутствуют файлы соответствующие шаблону именования отчётов!");
        }
        return files;
    }

    static private MonthlyReport getMonthlyExpense(File file) throws IOException {
        MonthlyReport report = new MonthlyReport(
                Integer.parseInt(file.getName().substring(2, 6)),
                Integer.parseInt(file.getName().substring(7, 8))
        );

        List<String> contents = Files.readAllLines(file.toPath());
        for (String commaSeparatedStr : contents.subList(1, contents.size())) {
            String[] splitContent = commaSeparatedStr.split(",");
            report.expenses.add(new MonthlyReport.Expense(
                    splitContent[0],
                    Integer.parseInt(splitContent[2]),
                    Integer.parseInt(splitContent[3]),
                    Boolean.parseBoolean(splitContent[1]))
            );
        }
        return report;
    }

    static private YearlyReport saveListOfYearlyExpenses(File file) throws IOException {
        YearlyReport report = new YearlyReport(Integer.parseInt(file.getName().substring(2, 6)));

        List<String> contents = Files.readAllLines(file.toPath());
        for (String commaSeparatedStr : contents.subList(1, contents.size())) {
            String[] splitContent = commaSeparatedStr.split(",");
            report.expenses.add(new YearlyReport.Expense(
                    Integer.parseInt(splitContent[0]),
                    Integer.parseInt(splitContent[1]),
                    Boolean.parseBoolean(splitContent[2])
            ));
        }
        return report;
    }

    static private boolean isMatchesNamesTemplate(File file) {
        return isMonthlyReportFile(file.getName()) || isYearlyReportFile(file.getName());
    }

    static private boolean isMonthlyReportFile(String fileName) {
        String[] checkName = fileName.split("[.]");
        return (checkName.length == 3
                && "m".equals(checkName[0])
                && checkName[1].chars().allMatch(Character::isDigit) && checkName[1].length() == 6
                && "csv".equals(checkName[2]));
    }

    static private boolean isYearlyReportFile(String fileName) {
        String[] checkName = fileName.split("[.]");
        return (checkName.length == 3
                && "y".equals(checkName[0])
                && checkName[1].chars().allMatch(Character::isDigit) && checkName[1].length() == 4
                && "csv".equals(checkName[2]));
    }
}

