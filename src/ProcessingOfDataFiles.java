import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class ProcessingOfDataFiles implements Report {

    private File[] getArrayOfReportFiles() {
        final File folder = new File(FOLDER_PATH);
        File[] files = folder.listFiles();
        if (files == null) {
            System.out.println("В папке с отчета отсутствуют файлы!\nСчитывание данных невозможно!");
            throw new IllegalArgumentException();
        }
        return files;
    }


    public Object readAndSaveContentFromFile(String time) throws IOException {
        ArrayList<MonthlyReport> listOfMonthlyReports = null;
        ArrayList<YearlyReport> listOfYearlyReports = null;

        for (File file : getArrayOfReportFiles()) {
            if (time.equals(MONTH) && isMonthlyReportFile(file.getName())) {
                if (listOfMonthlyReports == null) {
                    listOfMonthlyReports = new ArrayList<>();
                }

                listOfMonthlyReports.add(saveListOfMonthlyExpenses(file));
            }
            if (time.equals(YEAR) && isYearlyReportFile(file.getName())) {
                if (listOfYearlyReports == null) {
                    listOfYearlyReports = new ArrayList<>();
                }
                listOfYearlyReports.add(saveListOfYearlyExpenses(file));
            }
            isMatchesNamesTemplate(file);
        }
        if (listOfMonthlyReports == null && listOfYearlyReports == null) {
            System.out.println("Не найдено ни одного допустимого файла с отчетом!");
        }
        return time.equals(MONTH) ? listOfMonthlyReports : listOfYearlyReports;
    }

    private MonthlyReport saveListOfMonthlyExpenses(File file) throws IOException {
        int year = Integer.parseInt(file.getName().substring(2, 6));
        int month = Integer.parseInt(file.getName().substring(7, 8));
        List<String> listContents = Files.readAllLines(file.toPath());
        MonthlyReport monthlyReport = new MonthlyReport(year, month);
        for (String commaSeparatedStr : listContents.subList(1, listContents.size())) {
            String[] splitStr = commaSeparatedStr.split(",");
            monthlyReport.data.add(new MonthlyReport.Expense(splitStr));
        }
        return monthlyReport;
    }

    private YearlyReport saveListOfYearlyExpenses(File file) throws IOException {
        int year = Integer.parseInt(file.getName().substring(2, 6));
        List<String> listContents = Files.readAllLines(file.toPath());
        YearlyReport yearlyReport = new YearlyReport(year);
        for (String commaSeparatedStr : listContents.subList(1, listContents.size())) {
            String[] splitStr = commaSeparatedStr.split(",");
            yearlyReport.data.add(new YearlyReport.Expense(splitStr));
        }
        return yearlyReport;
    }

    private boolean isMatchesNamesTemplate(File file) {
        if (!(isMonthlyReportFile(file.getName()) || isYearlyReportFile(file.getName()))) {
            System.out.println("Имя файла: " + file.getName()
                    + " не соответствует шаблону именования сохраненных отчётов!");
            return false;
        }
        return true;
    }

    public boolean isMonthlyReportFile(String fileName) {
        String[] checkName = fileName.split("[.]");
        if (checkName.length == 3
                && MONTH.equals(checkName[0])
                && checkName[1].chars().allMatch(Character::isDigit) && checkName[1].length() == 6
                && "csv".equals(checkName[2])) {
            return true;
        }
        return false;
    }

    private boolean isYearlyReportFile(String fileName) {
        String[] checkName = fileName.split("[.]");
        if (checkName.length == 3
                && YEAR.equals(checkName[0])
                && checkName[1].chars().allMatch(Character::isDigit) && checkName[1].length() == 4
                && "csv".equals(checkName[2])) {
            return true;
        }
        return false;
    }
}

