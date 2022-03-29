import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

//сервер, который настраивает и запускает серверный класс деления
public class ServerDiv {

    public static void main(String[] args) throws RemoteException, AlreadyBoundException, InterruptedException {

        RemoteDivOperationServer serverDiv = new RemoteDivOperationServer();
        Registry registry = LocateRegistry.createRegistry(2345);
        Remote stubServerDiv = UnicastRemoteObject.exportObject(serverDiv, 3);
        registry.bind("server.operationDiv", stubServerDiv);

        Thread.sleep(Integer.MAX_VALUE);
    }
}