import java.rmi.Remote;
import java.rmi.RemoteException;

//интерфейс класса RemoteDivOperationServer для RMI
public interface ICalculatorOperationDiv extends Remote {
    long Div(long var1, long var3) throws RemoteException;
}