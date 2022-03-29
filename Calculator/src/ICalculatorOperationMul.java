import java.rmi.Remote;
import java.rmi.RemoteException;

//интерфейс класса RemoteMulOperationServer для RMI
public interface ICalculatorOperationMul extends Remote {
    long Mul(long var1, long var3) throws RemoteException;
}