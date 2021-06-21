package produtorconsumidor;

public class Semaphore {

    public Semaphore() {
        contador = 0;
    }

    public Semaphore(int i) {
        contador = i;
    }

    public synchronized void down() {
        while (contador == 0)
            try {
                wait();
            } catch (InterruptedException _ex) {
            }
        contador--;
    }

    public synchronized void up() {
        contador++;
        notify();
    }

    protected int contador;
}
