import java.rmi.RemoteException;

//класс-сервер, который реализует интерфейс ICalculatorOperationDiv
public class RemoteDivOperationServer implements ICalculatorOperationDiv {

    public long Div(long x, long y) throws RemoteException {
        return x / y;
    }
}