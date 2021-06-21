package rmi;

import java.rmi.*;
public interface ReceiveMessageInterface extends Remote
{
	void receiveMessage(String x) throws RemoteException;
}