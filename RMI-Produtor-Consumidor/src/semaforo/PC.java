package semaforo;

import java.util.ArrayList;

import processos.Consumer;
import processos.Producer;

public class PC {
	private int count;
	private Semaphore mutex;
	private Semaphore itens;
	private ArrayList<String> messageBuffer;
	private static PC instance;
	
	private PC() {
		this.count = 0;
		this.messageBuffer = new ArrayList<>();		
		this.itens = new Semaphore(0);
		this.mutex = new Semaphore(1);
	}
	
	public static PC getInstance() {
		if(instance == null)
			instance = new PC();
		return instance;
	}
	
	public void runProducer(String mensagem) {
		Producer producer = new Producer(mensagem, this);
		new Thread(producer).start();
	}

	public void runConsumer() {
		Consumer consumer = new Consumer(this);
		new Thread(consumer).start();
	} 
	
	public void increaseCount() {
		this.count++;
	}
	public void decreaseCount() {
		this.count--;
	}
	
	public void increaseMutex() {
		this.mutex.up();
	}
	public void decreaseMutex() {
		this.mutex.down();
	}
	
	public void increaseItens() {
		this.itens.up();
	}
	public void decreaseItens() {
		this.itens.down();
	}
	
	public void addMessageToBuffer(String msg) {
        this.messageBuffer.add(msg);
    }
    public void removeMessageFromBuffer(int index) {
    	this.messageBuffer.remove(index);
    }
    public String getMessageFromBuffer(int index) {
        return this.messageBuffer.get(index);
    }
}
