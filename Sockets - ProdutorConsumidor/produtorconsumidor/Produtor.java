package produtorconsumidor;

public class Produtor implements Runnable {

	private String mensagem;
	private TrataProdutorConsumidor tratador;
	private static int id;

	public Produtor(String mensagem, TrataProdutorConsumidor tratador) {
		this.mensagem = mensagem;
		this.tratador = tratador;
		Produtor.id += 1;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	@Override
	public void run() {
		this.tratador.reduzirMutex();
		this.tratador.aumentarItems();
		this.tratador.adicionarMensagemNoBuffer(this.mensagem);
		this.tratador.aumentarContadorDeItem();
		System.out.println("Produtor-" + Produtor.id + ", produziu" + this.mensagem.substring(this.mensagem.lastIndexOf(":")));
		this.tratador.aumentarMutex();
	}

}
