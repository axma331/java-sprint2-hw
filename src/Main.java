import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<MonthlyReport> monthlyReport = null;
        List<YearlyReport> yearlyReport = null;

        Scanner scanner = new Scanner(System.in);
        int userInput;
        try {
            do {
                printMenu();
                userInput = scanner.nextInt();
                switch (userInput) {
                    case 1:
                        monthlyReport = ProcessingOfDataFilesUtils.getMonthlyReportContents();
                        break;
                    case 2:
                        yearlyReport =  ProcessingOfDataFilesUtils.getYearlyReportContents();
                        break;
                    case 3:
                        ComparisonUtils.comparison(yearlyReport, monthlyReport);
                        break;
                    case 4:
                        System.out.println(monthlyReport);
                        break;
                    case 5:
                        System.out.println(yearlyReport);
                        break;
                    case 0:
                        System.out.println("До новых встреч!");
                        break;
                    default:
                        System.out.println("Действие отсутствует в списке!");
                }
            } while (userInput != 0);
        } catch (NoReportsException e) {
            System.err.println(e.getMessage());
        } catch (IOException e) {
            System.err.println("Невозможно прочитать файл с месячным отчётом!");
        } catch (NullPointerException n) {
            System.err.println("Отсутствуют данные для вывода!");
        }
        scanner.close();
    }

    static void printMenu() {
        System.out.println("Выберите действие:");
        System.out.println("1. Считать все месячные отчёты");
        System.out.println("2. Считать годовой отчёт");
        System.out.println("3. Сверить отчёты");
        System.out.println("4. Вывести информацию о всех месячных отчётах");
        System.out.println("5. Вывести информацию о годовом отчёте");
        System.out.println("0. Выйти из программы");
    }
}


