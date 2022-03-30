import org.junit.Test;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import static org.junit.Assert.*;

public class SolutionTest {

    @Test
    public void convertToReversePolishWithoutParentheses() throws NotBoundException, RemoteException {
     Solution solution =new Solution();
     StringBuilder res = new StringBuilder();
      String [] massive = solution.convertToReversePolish("5+3*7-2*6/3+2*4");
      for (var e:massive) res.append(e).append(" ");
        assertEquals("5 3 7 * + 2 6 * 3 / - 2 4 * +",res.deleteCharAt(res.length()-1).toString());
    }
    @Test
    public void convertToReversePolishWithParentheses() throws NotBoundException, RemoteException {
        Solution solution =new Solution();
        StringBuilder res = new StringBuilder();
        String [] massive = solution.convertToReversePolish("(5+3)*7-2*6/(3+2)*4");
        for (var e:massive) res.append(e).append(" ");
        assertEquals("5 3 + 7 * 2 6 * 3 2 + / 4 * -",res.deleteCharAt(res.length()-1).toString());
    }
    @Test
    public void convertToReversePolishDoubleFigures() throws NotBoundException, RemoteException {
        Solution solution =new Solution();
        StringBuilder res = new StringBuilder();
        String [] massive = solution.convertToReversePolish("(50+30)*7-2*6/(3+20)*4");
        for (var e:massive) res.append(e).append(" ");
        assertEquals("50 30 + 7 * 2 6 * 3 20 + / 4 * -",res.deleteCharAt(res.length()-1).toString());
    }
}