import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ProcessingOfDataFiles {
    private final File folder;
    ArrayList<YearlyReport> listOfYearlyReports;
    ArrayList<MonthlyReport> listOfMonthlyReports;

    public ProcessingOfDataFiles(String pathname) {
        folder = new File(pathname);
        listOfYearlyReports = new ArrayList<>();
        listOfMonthlyReports = new ArrayList<>();
    }

    public void listFilesForFolder() throws IOException {
        File[] files = this.folder.listFiles();
        if (files == null) {
            return;
        }
        for (File fileWithContents : files) {
            List<String> listContents = Files.readAllLines(Path.of(fileWithContents.toString()));
            if (isMonthlyReportFile(fileWithContents.getName())) {
                listOfMonthlyReports.add(savingMonthlyReportDataFromFile(listContents));
            } else if (isYearlyReportFile(fileWithContents.getName())) {
                listOfYearlyReports.add(savingYearlyReportDataFromFile(listContents));
            } else {
                System.out.println("Имя файла: " + fileWithContents.getName()
                        + " не соответствует шаблону именования сохраненных отчётов!");
            }
        }
//		} catch (Throwable e) {
//			System.out.println("Невозможно прочитать файл с отчётом.");
//		}
    }

    private MonthlyReport savingMonthlyReportDataFromFile(List<String> listContents) {
        MonthlyReport monthlyReport = new MonthlyReport();
        for (String commaSeparatedStr : listContents.subList(1, listContents.size())) {
//            System.out.println(commaSeparatedStr);
            String[] splitStr = commaSeparatedStr.split(",");
            monthlyReport.data.add(new MonthlyReport.Data(splitStr));
        }
        return monthlyReport;
    }

    private YearlyReport savingYearlyReportDataFromFile(List<String> listContents) {
        YearlyReport yearlyReport = new YearlyReport();
        for (String commaSeparatedStr : listContents.subList(1, listContents.size())) {
//            System.out.println(commaSeparatedStr);
            String[] splitStr = commaSeparatedStr.split(",");
            yearlyReport.data.add(new YearlyReport.Data(splitStr));
        }
        return yearlyReport;
    }

    public boolean isMonthlyReportFile(String fileName) {
        String[] checkName = fileName.split("[.]");
        if (checkName.length == 3
                && "m".equals(checkName[0])
                && checkName[1].chars().allMatch(Character::isDigit) && checkName[1].length() == 6 //Проверку на соответствие года сделать бы в идеале
                && "csv".equals(checkName[2])) {
            return true;            //Видимо все же надо будет сохранять информацию о годе и месяце отчета для сверки с годовым!
        }
        return false;
    }

    private boolean isYearlyReportFile(String fileName) {
        String[] checkName = fileName.split("[.]");
        if (checkName.length == 3
                && "y".equals(checkName[0])
                && checkName[1].chars().allMatch(Character::isDigit) && checkName[1].length() == 4 //Проверку на соответствие года сделать бы в идеале
                && "csv".equals(checkName[2])) {
            return true;
        }
        return false;
    }
}

