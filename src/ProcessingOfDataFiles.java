import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 * Обработка содержимого файлов
 */
public class ProcessingOfDataFiles {

    private File[] getArrayOfReportFiles() {
        File[] files = new File(Const.FOLDER_PATH).listFiles();
        if (files == null) {
            System.out.println("В папке с отчета отсутствуют файлы!\nСчитывание данных невозможно!");
            throw new IllegalArgumentException();
        }
        int cnt = 0;
        for (int i = 0; i < files.length; i++) {
            if (isMatchesNamesTemplate(files[i])) {
                ++cnt;
            }
        }
        if (files.length == cnt) {
            System.out.println("Отсутствуют файлы соответствующие шаблону именования отчётов!");
            return null;
        }
        return files;
    }


    public List<Report> readAndSaveContentFromFile(String time) throws IOException {
        List<Report> listOfReports = new ArrayList<>();

        for (File file : getArrayOfReportFiles()) {
            if (time.equals(Const.MONTH) && isMonthlyReportFile(file.getName())) {
                listOfReports.add(saveListOfMonthlyExpenses(file));
            }
            if (time.equals(Const.YEAR) && isYearlyReportFile(file.getName())) {
                listOfReports.add(saveListOfYearlyExpenses(file));
            }
        }
        if (listOfReports.isEmpty()) {
            System.err.println("Не найдено ни одного допустимого файла с отчетом!");
        }
        return listOfReports;
    }

    private MonthlyReport saveListOfMonthlyExpenses(File file) throws IOException {
        int year = Integer.parseInt(file.getName().substring(2, 6));
        int month = Integer.parseInt(file.getName().substring(7, 8));
        List<String> listContents = Files.readAllLines(file.toPath());
        MonthlyReport monthlyReport = new MonthlyReport(year, month);
        for (String commaSeparatedStr : listContents.subList(1, listContents.size())) {
            String[] splitStr = commaSeparatedStr.split(",");
            monthlyReport.data.add(new MonthlyReport.Expense(
                    splitStr[0],
                    Integer.parseInt(splitStr[2]),
                    Integer.parseInt(splitStr[3]),
                    Boolean.parseBoolean(splitStr[1]))
            );
        }
        return monthlyReport;
    }

    private YearlyReport saveListOfYearlyExpenses(File file) throws IOException {
        int year = Integer.parseInt(file.getName().substring(2, 6));
        List<String> listContents = Files.readAllLines(file.toPath());
        YearlyReport yearlyReport = new YearlyReport(year);
        for (String commaSeparatedStr : listContents.subList(1, listContents.size())) {
            String[] splitStr = commaSeparatedStr.split(",");
            yearlyReport.data.add(new YearlyReport.Expense(
                    Integer.parseInt(splitStr[0]),
                    Integer.parseInt(splitStr[1]),
                    Boolean.parseBoolean(splitStr[2])
            ));
        }
        return yearlyReport;
    }

    private boolean isMatchesNamesTemplate(File file) {
        return !(isMonthlyReportFile(file.getName()) || isYearlyReportFile(file.getName()));
    }

    public boolean isMonthlyReportFile(String fileName) {
        String[] checkName = fileName.split("[.]");
        if (checkName.length == 3
                && Const.MONTH.equals(checkName[0])
                && checkName[1].chars().allMatch(Character::isDigit) && checkName[1].length() == 6
                && "csv".equals(checkName[2])) {
            return true;
        }
        return false;
    }

    private boolean isYearlyReportFile(String fileName) {
        String[] checkName = fileName.split("[.]");
        if (checkName.length == 3
                && Const.YEAR.equals(checkName[0])
                && checkName[1].chars().allMatch(Character::isDigit) && checkName[1].length() == 4
                && "csv".equals(checkName[2])) {
            return true;
        }
        return false;
    }
}

