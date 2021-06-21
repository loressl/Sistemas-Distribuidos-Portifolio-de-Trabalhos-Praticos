package rmi;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class RmiClient {
	static public void main(String args[]) {
		ReceiveMessageInterface rmiServer;
		Registry registry;
		String serverAddress = "127.0.0.1";
		String serverPort = "3232";
		String input;
		Scanner inputClient = new Scanner(System.in);
		String process = "";

		try {
			while (!process.equalsIgnoreCase("Produtor") 
					&& !process.equalsIgnoreCase("Consumidor")) {
				System.out.println("Digite o nome do processo desejado.");
				System.out.println("Opções:");
				System.out.println("Produtor");
				System.out.println("Consumidor");
				process = inputClient.nextLine();
			}

			String output = process.substring(0, 1).toUpperCase() + process.substring(1);
			System.out.println(output + " selecionado.");
			
			if (process.equalsIgnoreCase("Produtor")) //Caso seja produtor
				System.out.println("Digite para produzir");
			System.out.println("Digitar /q terminara o processo");
			registry = LocateRegistry.getRegistry(serverAddress, 
					(new Integer(serverPort)).intValue());
			rmiServer = (ReceiveMessageInterface) (registry.lookup("rmiServer"));
			
			while (inputClient.hasNextLine()) {
				input = inputClient.nextLine();
				if (input.equals("/q")) {
					System.out.println(output + " abortado.");
					break;
				}
				rmiServer.receiveMessage(process + ": " +input);
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		inputClient.close();
	}
}
