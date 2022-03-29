import java.rmi.RemoteException;

//класс-сервер, который реализует интерфейс ICalculatorOperationMul
public class RemoteMulOperationServer implements ICalculatorOperationMul {

    public long Mul(long x, long y) throws RemoteException {
        return x * y;
    }
}