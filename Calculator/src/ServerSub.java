import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

//сервер, который настраивает и запускает серверный класс вычитания
public class ServerSub {

    public static void main(String[] args) throws RemoteException, AlreadyBoundException, InterruptedException {

        RemoteSubOperationServer serverSub = new RemoteSubOperationServer();
        Registry registry = LocateRegistry.createRegistry(1234);
        Remote stubServerSub = UnicastRemoteObject.exportObject(serverSub, 0);
        registry.bind("server.operationSub", stubServerSub);

        Thread.sleep(Integer.MAX_VALUE);
    }
}