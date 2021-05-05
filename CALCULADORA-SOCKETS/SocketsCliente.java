package calculadorasockets;

import java.io.IOException; 
import java.net.DatagramPacket; 
import java.net.DatagramSocket; 
import java.net.InetAddress; 
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class SocketsCliente {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
       Scanner sc = new Scanner(System.in); 
  
        
        DatagramSocket ds = new DatagramSocket(); 
  
        InetAddress ip = InetAddress.getLocalHost(); 
        byte buf[] = null; 
  
        while (true) 
        { 
            System.out.print("Digite no seguinte formato:"); 
            System.out.println("'x + y | x - y | x * y | x / y'"); 
            String inp = sc.nextLine(); 
            buf = new byte[65535]; 
  
            buf = inp.getBytes(); 
  
            DatagramPacket DpSend = 
                      new DatagramPacket(buf, buf.length, ip, 1234); 
  
            ds.send(DpSend); 

            if (inp.equals("exit")) 
                break; 
  
            buf = new byte[65535]; 
            DatagramPacket DpReceive = 
                                 new DatagramPacket(buf, buf.length); 
            ds.receive(DpReceive); 
            
            String bf = new String(buf,0,buf.length);
            String answer = "";
            
            for(int count = 0; bf.toString().charAt(count) != '|';count++){
                answer+= bf.toString().charAt(count);
            }
            
            System.out.println("Resultado = " + 
                                answer); 
        } 
    }
    
}