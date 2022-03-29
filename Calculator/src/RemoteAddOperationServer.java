import java.rmi.RemoteException;

//класс-сервер, который реализует интерфейс ICalculatorOperationAdd
public class RemoteAddOperationServer implements ICalculatorOperationAdd {

    public long Add(long x, long y) throws RemoteException {
        return x + y;
    }
}