package servidor;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Servidor {

	public static void main(String[] args) throws IOException{
		new Servidor(12345).executa();
	}
	
	private int porta;
	private List<PrintStream> clientesConectados;	
	public Servidor(int porta) {
		this.porta = porta;
		this.clientesConectados = new ArrayList<>();
	}
	
	@SuppressWarnings("resource")
	public void executa() throws IOException {
		ServerSocket servidor = new ServerSocket(this.porta);
		System.out.println("Servidor rodando na porta: " + this.porta);
		
		while(true) {
			// aceita um cliente
			System.out.println("Aguardando conexao...");
			Socket cliente = servidor.accept();
			System.out.println("Novo cliente conectado: " + cliente.getInetAddress().getHostAddress());
			
			// adiciona saida do cliente na lista
			PrintStream ps = new PrintStream(cliente.getOutputStream());
			this.clientesConectados.add(ps);
			
			// cria tratador de cliente numa nova thread
			TrataCliente tc = new TrataCliente(cliente.getInputStream());
			new Thread(tc).start();
		}
	}

}
