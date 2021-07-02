package rmi;

import java.rmi.registry.*;
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
			
			if (process.equalsIgnoreCase("Produtor"))
				System.out.println("\nDigite algo para produzir");
			else
				System.out.println("\nAperte ENTER para consumir");
			System.out.println("Digitar /q terminara o processo");
			registry = LocateRegistry.getRegistry(serverAddress, 
					(Integer.valueOf(serverPort)).intValue());
			rmiServer = (ReceiveMessageInterface) (registry.lookup("rmiServer"));
			
			while (inputClient.hasNextLine()) {
				input = inputClient.nextLine();
				if (input.equals("/q")) {
					System.out.println(output + " abortado.");
					break;
				}
				rmiServer.receiveMessage(process + ": " +input);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		inputClient.close();
	}
}
