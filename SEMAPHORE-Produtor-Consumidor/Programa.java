/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ads
 */
import java.util.ArrayList;

public class Programa {

    int itemCount;
    ArrayList buffer;

    Semaphore itens = new Semaphore(0);
    Semaphore mutex = new Semaphore(1);

    Programa() {
        itemCount = 0;
        buffer = new ArrayList();
    }

    int compartilhada;

    public static void main(String[] args) {
        Programa t = new Programa();
        t.run();
    }

    public void run() {
        Consumer c = new Consumer(this);
        Consumer c1 = new Consumer(this);
        Consumer c2 = new Consumer(this);
        Consumer c3 = new Consumer(this);
        Consumer c4 = new Consumer(this);
        Consumer c5 = new Consumer(this);

        Producer p = new Producer(this);
        Producer p1 = new Producer(this);

        p.start();
        p1.start();

        c.start();
        c1.start();
        c2.start();
        c3.start();
        c4.start();
        c5.start();

    }

}
