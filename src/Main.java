import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        ProcessingOfDataFiles resources = new ProcessingOfDataFiles();
        try {
            List<Report> monthlyReport = null;
            List<Report> yearlyReport = null;

            Scanner scanner = new Scanner(System.in);
            int userInput;
            do {
                printMenuAndReturnUserSolution();
                userInput = scanner.nextInt();
                switch (userInput) {
                    case 1:
                        monthlyReport = resources.readAndSaveContentFromFile(Const.MONTH);
                        break;
                    case 2:
                        yearlyReport = resources.readAndSaveContentFromFile(Const.YEAR);
                        break;
                    case 3:
                        System.out.println(3);
//                        Comparison.convertToMap(yearlyReport);
                        break;
                    case 4:
                        System.out.println(monthlyReport);
                        break;
                    case 5:
                        System.out.println(yearlyReport);
                        break;
                    case 0:
                        System.out.println("До новых встреч!");
                        scanner.close();
                        break;
                    default:
                        System.out.println("Действие отсутствует в списке!");
                }
            } while (userInput != 0);
        } catch (IllegalArgumentException e) {
            System.err.println("Отсутствуют файлы соответствующие шаблону именования отчётов!");
        } catch (IOException e) {
            System.err.println("Невозможно прочитать файл с месячным отчётом!");
        }

    }

    /*
      Программа должна завершаться только при вводе оператором специальной последовательности символов.
      Придумать такую последовательность вы можете сами.
     */
    public static void printMenuAndReturnUserSolution() {
        System.out.println("Выберите действие:");
        System.out.println("1. Считать все месячные отчёты");
        System.out.println("2. Считать годовой отчёт");
        System.out.println("3. Сверить отчёты");
        System.out.println("4. Вывести информацию о всех месячных отчётах");
        System.out.println("5. Вывести информацию о годовом отчёте");
        System.out.println("0. Выйти из программы");
    }
}


