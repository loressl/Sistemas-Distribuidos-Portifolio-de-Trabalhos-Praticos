package processos;

import semaforo.PC;

public class Consumer implements Runnable{
		
	private PC handler;

	public Consumer(PC handler) {
		this.handler = handler;
	}
	
	@Override
	public void run() {
		this.handler.decreaseMutex();
		this.handler.decreaseItens();
		this.handler.decreaseCount();
				
		this.handler.removeMessageFromBuffer(0);
		this.handler.increaseMutex();
		
		String msg = this.handler.getMessageFromBuffer(0);
		System.out.println("Consumidor consumiu " + msg.substring(msg.lastIndexOf(":")));
	}
}