package semaforo;

public class Semaphore {
	protected int count;

	public Semaphore()
    {
        count = 0;
    }
	
    public Semaphore(int i) {
    	count = i;
    }

    public synchronized void down() {
        while (count == 0)
            try {
                wait();
            } catch (InterruptedException _ex) {
            	_ex.printStackTrace();
            	//Thread.currentThread().interrupt();
            }
        count--;
    }

    public synchronized void up() {
    	count++;
        notify();
    }    
}
