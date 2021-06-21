package servidor;

import java.io.InputStream;
import java.util.Scanner;

import produtorconsumidor.TrataProdutorConsumidor;

public class TrataCliente implements Runnable {

	private InputStream cliente;
	private TrataProdutorConsumidor produtorConsumidor;

	public TrataCliente(InputStream cliente) {
		this.cliente = cliente;
		this.produtorConsumidor = TrataProdutorConsumidor.getInstance();
	}

	@Override
	public void run() {
		// verifica se é pra produzir ou consumir e executa a thread correspondente
		Scanner scanner = new Scanner(this.cliente);
		while (scanner.hasNextLine()) {
			String mensagemDoCliente = scanner.nextLine();
			if (mensagemDoCliente.contains("Produtor".toLowerCase())) this.produtorConsumidor.executarProdutor(mensagemDoCliente);
			else this.produtorConsumidor.executarConsumidor();
		}
		scanner.close();
	}

}
