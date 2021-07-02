package rmi;

import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.*;
import java.net.*;

import semaforo.PC;

public class RmiServer extends UnicastRemoteObject implements ReceiveMessageInterface {

	private static final long serialVersionUID = 7111078431122519129L;
	int thisPort;
	String thisAddress;
	Registry registry;
	PC pc;

	public void receiveMessage(String message) throws RemoteException {
		if (message.contains("Consumidor".toLowerCase()))
			this.pc.runConsumer();
		else
			this.pc.runProducer(message);
	}

	public RmiServer() throws RemoteException {
		try {
			this.pc = PC.getInstance();
			thisAddress = (InetAddress.getLocalHost()).toString();
		} catch (Exception e) {
			throw new RemoteException("can't get inet address.");
		}
		thisPort = 3232;
		System.out.println("this address=" + thisAddress + ",port=" + thisPort);
		try {
			registry = LocateRegistry.createRegistry(thisPort);
			registry.rebind("rmiServer", this);
		} catch (RemoteException e) {
			throw e;
		}
	}

	static public void main(String args[]) {
		try {
			RmiServer s=new RmiServer();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
}