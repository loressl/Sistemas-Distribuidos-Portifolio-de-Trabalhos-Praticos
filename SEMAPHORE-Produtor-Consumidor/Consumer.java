/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ads
 */
public class Consumer extends Thread {
    
        Programa a;

        public Consumer(Programa x) {

               a = x;
        }

        public void run() {

               try {
                   while (true) {
                       while (a.itemCount == 0)
                           sleep(100);
                       int item;
                       a.itens.down();//verifica se tem itens que podem ser consumidos
                       a.mutex.down();//se tiver entra na região crítica
                       item = (Integer) a.buffer.get(0);
                       a.buffer.remove(0);
                       a.mutex.up();
                       a.itemCount--;
                       System.out.println("consumer: consuming item "+item);
                       for (int i =0;i<10000;i++);
                   }

               }

               catch(InterruptedException e) {

                       e.printStackTrace(); 

               }

        }
    
    
}
