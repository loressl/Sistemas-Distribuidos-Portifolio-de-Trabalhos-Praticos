package processos;

import semaforo.PC;

public class Producer implements Runnable{
	
	private String text;
	private PC handler;
	
	public Producer(String msg, PC handler) {
		this.text = msg;
		this.handler = handler;
	}
	
	public void setText(String msg) {
		this.text = msg;
	}
	
	@Override
	public void run(){
		this.handler.decreaseMutex();
		
		this.handler.increaseItens();		
		this.handler.increaseCount();
		this.handler.increaseMutex();
		
		this.handler.addMessageToBuffer(this.text);
		System.out.println("Produtor produziu" + this.text.substring(this.text.lastIndexOf(":")));
	}
}