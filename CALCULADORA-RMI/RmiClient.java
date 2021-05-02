import java.rmi.*;
import java.rmi.registry.*;
import java.util.Scanner;
import java.net.*;

public class RmiClient {
	static public void main(String args[]) {
		ReceiveMessageInterface rmiServer;
		Registry registry;
		String serverAddress = args[0];
		String serverPort = args[1];
		String text = args[2];

		System.out.println("sending " + text + " to " + serverAddress + ":" + serverPort);
		System.out.println("\n********************CALCULADORA RMI*************************\n");
		try {
			// get the “registry”
			registry = LocateRegistry.getRegistry(serverAddress, (new Integer(serverPort)).intValue());
			// look up the remote object
			rmiServer = (ReceiveMessageInterface) (registry.lookup("rmiServer"));
			// call the remote method
			rmiServer.receiveMessage(text);
			Scanner scanner = new Scanner(System.in);
			int out, option, number1, number2, result;
			do {
				out = option = number1 = number2 = result = 0;
				System.out.println("\n");
				System.out.println("Digite o primeiro número:");
				number1 = scanner.nextInt();
				System.out.println("Digite o segundo número:");
				number2 = scanner.nextInt();
				System.out.println("Digite o número referente a operação que desejada:");
				System.out.println("1-Somar");
				System.out.println("2-Subtração");
				System.out.println("3-Multiplicação");
				System.out.println("4-Divisão");
				option = scanner.nextInt();

				switch (option) {
				case 1:
					result = rmiServer.add(number1, number2);
					System.out.println("Resultado: " + result);
					System.out.println("\n");
					break;
				case 2:
					result = rmiServer.sub(number1, number2);
					System.out.println("Resultado: " + result);
					System.out.println("\n\n");
					break;
				case 3:
					result = rmiServer.mul(number1, number2);
					System.out.println("Resultado: " + result);
					System.out.println("\n");
					break;
				case 4:
					result = rmiServer.div(number1, number2);
					System.out.println("Resultado: " + result);
					System.out.println("\n");
					break;
				default:
					System.out.println("Opção inválida.");
					break;
				}
				System.out.println("Continuar?");
				System.out.println("1-Sim");
				System.out.println("Qualquer outro número - Não");
				out = scanner.nextInt();
				System.out.println("\n");

			} while (out != 1);
			System.out.println("Até mais...");
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}
