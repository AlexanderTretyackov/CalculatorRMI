import java.rmi.Remote;
import java.rmi.RemoteException;


//интерфейс класса RemoteAddOperationServer для RMI
public interface ICalculatorOperationAdd extends Remote {
    long Add(long var1, long var3) throws RemoteException;
}