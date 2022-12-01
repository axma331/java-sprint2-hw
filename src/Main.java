import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        ArrayList<YearlyReport> year = new ArrayList();
        ProcessingOfDataFiles resources = new ProcessingOfDataFiles();
        year = (ArrayList<YearlyReport>) resources.readAndSaveContentFromFile(Report.YEAR);

        System.out.println(year + "The end!");


//        while (true) {
//            switch (printMenuAndReturnUserSolution()) {
//                case 1:
//                    System.out.println(1);
//                    break;
//                case 2:
//                    System.out.println(2);
//                    break;
//                case 3:
//                    System.out.println(3);
//                    break;
//                case 4:
//                    System.out.println(4);
//                    break;
//                case 5:
//                    System.out.println(5);
//                    break;
//                default:
//                    System.out.println("Действие отсутствует в списке!");
//            }
//        }

    }

    /*
      Программа должна завершаться только при вводе оператором специальной последовательности символов.
      Придумать такую последовательность вы можете сами.
     */
    public int printMenuAndReturnUserSolution() {
        System.out.println("Выберите действие:");
        System.out.println("1. Считать все месячные отчёты");
        System.out.println("2. Считать годовой отчёт");
        System.out.println("3. Сверить отчёты");
        System.out.println("4. Вывести информацию о всех месячных отчётах");
        System.out.println("5. Вывести информацию о годовом отчёте");
        return new Scanner(System.in).nextInt();
    }
}


