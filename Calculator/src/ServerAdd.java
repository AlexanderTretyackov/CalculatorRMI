import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

//сервер, который настраивает и запускает серверный класс добавления
public class ServerAdd {

    public static void main(String[] args) throws RemoteException, AlreadyBoundException, InterruptedException {

        RemoteAddOperationServer serverAdd = new RemoteAddOperationServer();
        Registry registry = LocateRegistry.createRegistry(2732);
        Remote stubServerAdd = UnicastRemoteObject.exportObject(serverAdd, 0);
        registry.bind("server.operationAdd", stubServerAdd);

        Thread.sleep(Integer.MAX_VALUE);
    }
}