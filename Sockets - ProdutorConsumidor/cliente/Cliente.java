package cliente;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente {
	public static void main(String[] args) throws UnknownHostException, IOException {
		new Cliente("127.0.0.1", 12345).executa();
	}

	private String host;
	private int porta;

	public Cliente(String host, int porta) {
		this.host = host;
		this.porta = porta;
	}

	public void executa() throws UnknownHostException, IOException {
		Socket cliente = new Socket(host, porta);
		System.out.println("O cliente se conectou ao servidor!");

		// thread para receber mensagens do servidor
		Recebedor r = new Recebedor(cliente.getInputStream());
		new Thread(r).start();

		// lê mensagens do teclado e envia a mensagem para o servidor
		Scanner teclado = new Scanner(System.in);
		String tipo = "";
		String mensagemParaServidor;

		PrintStream saida = new PrintStream(cliente.getOutputStream());
		while (!tipo.equalsIgnoreCase("Produtor") && !tipo.equalsIgnoreCase("Consumidor")) {
			System.out.println("Produtor ou Consumidor?");
			tipo = teclado.nextLine();
		}
		
		boolean ehProdutor = tipo.equalsIgnoreCase("Produtor");

		System.out.println("Para sair digite /quit");
		if(ehProdutor) System.out.println("Digite algo para produzir");

		while (teclado.hasNextLine()) {
			mensagemParaServidor = teclado.nextLine();
			if (mensagemParaServidor.startsWith("/quit"))
				break;
			saida.println("<" + tipo + ">: " + mensagemParaServidor);
		}

		System.out.println("Conexao encerrada...");
		teclado.close();
		saida.close();
		cliente.close();
	}
}
