import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import static org.junit.Assert.*;

public class InOutControllerTest {
    private ByteArrayOutputStream output;
    private PrintStream old;
    @Before
    public void setUpStreams() {
        old = System.out;
        output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(old);
    }
    @Test
    public void outputResultWithoutParenthesesTest() throws NotBoundException, RemoteException {
       InOutController.outputResult("5+3*7-2*6/3+2*4");
        Assert.assertEquals("Result: 30",output.toString());
    }
    @Test
    public void outputResultWithParenthesesTest() throws NotBoundException, RemoteException {
        InOutController.outputResult("(5+3)*7-2*6/(3+2)*4");
        Assert.assertEquals("Result: 48",output.toString());
    }
    @Test
    public void outputResultDoubleFiguresTest() throws NotBoundException, RemoteException {
        InOutController.outputResult("(50+30)*7-2*6/(3+20)*4");
        Assert.assertEquals("Result: 560",output.toString());
    }
}