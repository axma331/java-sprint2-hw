import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        ProcessingOfDataFiles resources = new ProcessingOfDataFiles();
        List<Report> month =  resources.readAndSaveContentFromFile(Const.MONTH);
        List<Report> year = resources.readAndSaveContentFromFile(Const.YEAR);

        Scanner scanner = new Scanner(System.in);
        int userInput;
        do {
            printMenuAndReturnUserSolution();
            userInput = scanner.nextInt();
            switch (userInput) {
                case 1:
                    System.out.println(1);
                    break;
                case 2:
                    System.out.println(2);
                    break;
                case 3:
                    System.out.println(3);
                    break;
                case 4:
                    System.out.println(4);
                    break;
                case 5:
                    System.out.println(5);
                    break;
                case 0:
                    System.out.println("До новых встреч!");
                    scanner.close();
                    break;
                default:
                    System.out.println("Действие отсутствует в списке!");
            }
        } while (userInput != 0);
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


