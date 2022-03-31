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
            try{
                long result = solution.getResult(expression);
                System.out.printf("Result: %d", result);
            }
            catch (ArithmeticException ex)
            {
                System.out.print("Error! Division by zero!");
            }
        }
    }

    //валидация введенного выражения
    private static boolean isValid(String expression) {
        char[] chArray = expression.toCharArray();
        int previous = 0;
        int brace = 0;
        for (int i = 0; i < expression.length(); i++){
            char symbol = chArray[i];
            switch (symbol){
                case '+':
                case '-':
                case '*':
                case '/':
                    if(previous < 3){
                        System.out.println("Знак может стоять только после числа или ')'.");
                        return false;
                    }
                    previous = 1;
                    break;
                case '(':
                    if (previous == 3){
                        System.out.println("'(' не может стоять после ')'.");
                        return false;
                    }
                    brace = brace + 1;
                    previous = 2;
                    break;
                case ')':
                    if (previous != 4){
                        System.out.println("')' может стоять только после числа.");
                        return false;
                    }
                    if (brace == 0){
                        System.out.println("Для использования закрывающей скобки, сначла должна быть напечатана парная ей открывающая скобка.");
                        return false;
                    }
                    brace = brace - 1;
                    previous = 3;
                    break;
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                case '0':
                    if(previous == 3){
                        System.out.println("Число не может стоять сразу после закрывающей скобки.");
                        return false;
                    }
                    previous = 4;
                    break;
                default:
                    System.out.println("'"+symbol +  "' нелья использовать в выражении.");
                    return false;
            }
        }
        if (brace != 0 ){
            System.out.println("Есть незакрытые скобки");
            return false;
        }
        if (previous == 1){
            System.out.println("Выражение не может заканчиваться знаком");
            return false;
        }
        return true;
    }
}
