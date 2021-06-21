package produtorconsumidor;

public class Consumidor implements Runnable {

	private TrataProdutorConsumidor tratador;
	private static int id;

	public Consumidor(TrataProdutorConsumidor tratador) {
		this.tratador = tratador;
		Consumidor.id += 1;
	}

	@Override
	public void run() {
		this.tratador.reduzirItems();
		this.tratador.reduzirMutex();
		String mensagem = this.tratador.recuperarMensagemNoBuffer(0);
		this.tratador.removerMensagemDoBuffer(0);
		this.tratador.reduzirContadorDeItem();
		System.out.println("Consumidor-" + Consumidor.id + ", consumiu " + mensagem.substring(mensagem.lastIndexOf(":")));
		this.tratador.aumentarMutex();
	}

}
