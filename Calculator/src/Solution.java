import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Stack;

//класс подсчета выражения
public class Solution {
    private static ICalculatorOperationAdd operationAdd = null;
    private static ICalculatorOperationSub operationSub = null;
    private static ICalculatorOperationMul operationMul = null;
    private static ICalculatorOperationDiv operationDiv = null;

    public Solution() throws NotBoundException, RemoteException {
        this.connectedServers();
    }

    //получение результата введенного выражения
    public long getResult(String expression) throws RemoteException {
        String[] converterExpression = this.getConvertedExpression(expression);
        int result = this.calculation(converterExpression);
        return (long)result;
    }

    //соединение с серверами
    private void connectedServers() throws RemoteException, NotBoundException {
        //уникальные имена объектов
        var serverOperationAdd = "server.operationAdd";
        var serverOperationSub = "server.operationSub";
        var serverOperationMul = "server.operationMul";
        var serverOperationDiv = "server.operationDiv";

        //доступ к регистру удаленных объектов
        Registry registryAdd = LocateRegistry.getRegistry(2732);
        Registry registrySub = LocateRegistry.getRegistry(1234);
        Registry registryMul = LocateRegistry.getRegistry(2123);
        Registry registryDiv = LocateRegistry.getRegistry(2345);

        //получение из регистра объектов по уникальному имени
        operationAdd = (ICalculatorOperationAdd)registryAdd.lookup(serverOperationAdd);
        operationSub = (ICalculatorOperationSub)registrySub.lookup(serverOperationSub);
        operationMul = (ICalculatorOperationMul)registryMul.lookup(serverOperationMul);
        operationDiv = (ICalculatorOperationDiv)registryDiv.lookup(serverOperationDiv);
    }

    //преобразование введенного выражения
    private String[] getConvertedExpression(String expression) {
        //заглушка
        //должна быть конвертация выражения
        String[] token = convertToReversePolish(expression);
        return token;
    }
    public String[] convertToReversePolish(String string) {
        String res="";
        if (string == null)
            return null;
        int len = string.length();
        Stack<Character> operator = new Stack<Character>();
        Stack<String> reversePolish = new Stack<String>();
        //avoid checking empty
        operator.push('#');
        for (int i = 0; i < len;) {
            //deal with space
            while (i < len && string.charAt(i) == ' ')
                i++;
            if (i == len)
                break;
            //if is number
            if (isNum(string.charAt(i))) {
                String num = "";
                while (i < len && isNum(string.charAt(i)))
                    num += string.charAt(i++);
                reversePolish.push(num);
                //is operator
            } else if (isOperator(string.charAt(i))) {
                char op = string.charAt(i);
                switch (op) {
                    case '(':
                        operator.push(op);
                        break;
                    case ')':
                        while (operator.peek() != '(')
                            reversePolish.push(Character.toString(operator.pop()));
                        operator.pop();
                        break;
                    case '+':
                    case '-':
                        if (operator.peek() == '(')
                            operator.push(op);
                        else {
                            while (operator.peek() != '#' && operator.peek() != '(')
                                reversePolish.push(Character.toString(operator.pop()));
                            operator.push(op);
                        }
                        break;
                    case '*':
                    case '/':
                        if (operator.peek() == '(')
                            operator.push(op);
                        else {
                            while (operator.peek() != '#' && operator.peek() != '+' &&
                                    operator.peek() != '-' && operator.peek() != '(')
                                reversePolish.push(Character.toString(operator.pop()));
                            operator.push(op);
                        }
                        break;
                }
                i++;
            }
        }
        while (operator.peek() != '#')
            reversePolish.push(Character.toString(operator.pop()));
        while (!reversePolish.isEmpty())
            res = res.length() == 0? reversePolish.pop() + res: reversePolish.pop() + " " + res;
        return res.split(" ");
    }
    public boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '(' || c == ')';
    }

    public boolean isNum(char c) {
        return c - '0' >= 0 && c - '0' <= 9;
    }
    //вычисление выражения
    private int calculation(String[] tokens) throws RemoteException {
        if (tokens == null || tokens.length == 0) return 0;

        Stack<Long> stack = new Stack<>();
        for (String s : tokens) {
            if (!isOperator(s)) stack.push(Long.parseLong(s));
            else {
                long numB = stack.pop(), numA = stack.pop();
                stack.push(calculationAction(numA, numB, s));
            }
        }
        return stack.pop().intValue();
    }

    private boolean isOperator(String s) {
        if (s.length() != 1) return false;
        char c = s.charAt(0);
        return c == '+' || c == '-' || c == '/' || c == '*';
    }

    private long calculationAction(long a, long b, String s) throws RemoteException {
        long rst = 0;
        char operator = s.charAt(0);
        if (operator == '+') rst = operationAdd.Add(a, b);
        if (operator == '-') rst = operationSub.Sub(a, b);
        if (operator == '*') rst = operationMul.Mul(a, b);
        if (operator == '/') rst = operationDiv.Div(a, b);
        return rst;
    }
}