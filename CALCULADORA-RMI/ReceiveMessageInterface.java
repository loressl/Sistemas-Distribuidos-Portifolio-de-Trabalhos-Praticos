import java.rmi.*;

public interface ReceiveMessageInterface extends Remote {
	void receiveMessage(String x) throws RemoteException;

	int add(int a, int b) throws RemoteException;

	int sub(int a, int b) throws RemoteException;

	int mul(int a, int b) throws RemoteException;

	int div(int a, int b) throws RemoteException;
}
