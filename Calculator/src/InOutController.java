import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

//класс контроллера ввода-вывода
public class InOutController {

    public static void main(String[] args) throws RemoteException, NotBoundException {
        String expression = inputExpression();
        outputResult(expression);
    }

    //ввод выражения
    public static String inputExpression() {
        Scanner in = new Scanner(System.in);

        System.out.print("Input expression:");

        String expression = in.nextLine();
        in.close();

        return expression;
    }

    //вывод результата
    public static void outputResult(String expression) throws RemoteException, NotBoundException {
        if (isValid(expression)) {
            Solution solution = new Solution();
            long result = solution.getResult(expression);
            System.out.printf("Result: %d", result);
        }
        else
            System.out.print("Error! Invalid expression!");
    }

    //валидация введенного выражения
    private static boolean isValid(String expression) {
        //заглушка
        //должна быть проверка валидации
        return true;
    }
}
