package cliente;

import java.io.InputStream;
import java.util.Scanner;

public class Recebedor implements Runnable {

	private InputStream servidor;

	public Recebedor(InputStream servidor) {
		this.servidor = servidor;
	}

	@Override
	public void run() {
		// recebe msgs do servidor e imprime na tela
		Scanner scanner = new Scanner(this.servidor);
		while (scanner.hasNextLine()) 
			System.out.println(scanner.nextLine());
		
		scanner.close();
	}

}
