import java.rmi.RemoteException;

//класс-сервер, который реализует интерфейс ICalculatorOperationSub
public class RemoteSubOperationServer implements ICalculatorOperationSub {

    public long Sub(long x, long y) throws RemoteException {
        return x - y;
    }
}