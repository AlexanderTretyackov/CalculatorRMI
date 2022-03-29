import java.rmi.Remote;
import java.rmi.RemoteException;

//интерфейс класса RemoteSubOperationServer для RMI
public interface ICalculatorOperationSub extends Remote {
    long Sub(long var1, long var3) throws RemoteException;
}