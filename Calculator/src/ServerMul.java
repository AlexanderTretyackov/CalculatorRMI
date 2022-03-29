import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

//сервер, который настраивает и запускает серверный класс умножения
public class ServerMul {

    public static void main(String[] args) throws RemoteException, AlreadyBoundException, InterruptedException {

        RemoteMulOperationServer serverMul = new RemoteMulOperationServer();
        Registry registry = LocateRegistry.createRegistry(2123);
        Remote stubServerMul = UnicastRemoteObject.exportObject(serverMul, 0);
        registry.bind("server.operationMul", stubServerMul);

        Thread.sleep(Integer.MAX_VALUE);
    }
}