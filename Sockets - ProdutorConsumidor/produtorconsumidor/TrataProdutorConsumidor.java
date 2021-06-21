package produtorconsumidor;

import java.util.ArrayList;

public class TrataProdutorConsumidor {
	private int contador;
	private Semaphore mutex;
	private Semaphore items;
	private ArrayList<String> bufferDeMensagens;
	private static TrataProdutorConsumidor instance;

	private TrataProdutorConsumidor() {
		this.contador = 0;
		this.bufferDeMensagens = new ArrayList<>();
		this.mutex = new Semaphore(1);
		this.items = new Semaphore(0);
	}
	
	public static TrataProdutorConsumidor getInstance() {
		if(instance == null) instance = new TrataProdutorConsumidor();
		return instance;
	}

	public void reduzirContadorDeItem() {
		this.contador--;
	}

	public void aumentarContadorDeItem() {
		this.contador++;
	}

	public void reduzirMutex() {
		this.mutex.down();
	}

	public void aumentarMutex() {
		this.mutex.up();
	}

	public void reduzirItems() {
		this.items.down();
	}

	public void aumentarItems() {
		this.items.up();
	}
	
    public void adicionarMensagemNoBuffer(String msg) {
        this.bufferDeMensagens.add(msg);
    }
    
    public String recuperarMensagemNoBuffer(int index) {
        return this.bufferDeMensagens.get(index);
    }
    
    public void removerMensagemDoBuffer(int index) {
    	this.bufferDeMensagens.remove(index);
    }
    
	public void executarProdutor(String mensagem) {
		Produtor produtor = new Produtor(mensagem, this);
		new Thread(produtor).start();
	}

	public void executarConsumidor() {
		Consumidor consumidor = new Consumidor(this);
		new Thread(consumidor).start();
	}
    
}
