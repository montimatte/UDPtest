import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

class client{
    public static void main(String[] args) throws IOException {
        DatagramSocket sochet = new DatagramSocket();

        Scanner sc = new Scanner(System.in);

        System.out.println("Scegli 1 per set, 2 per get");

        int scelta = sc.nextInt();
        sc.nextLine();

        if(scelta == 1)
        {
            System.out.println("Inserisci la stringa: ");
            String str = sc.nextLine();

            String messaggioDaInviare = "set;"+str;
            byte[] buffer = messaggioDaInviare.getBytes();
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            packet.setAddress(InetAddress.getByName("127.0.0.1"));
            packet.setPort(12345);
            sochet.send(packet);

        }
        else if(scelta == 2)
        {
            System.out.println("Inserisci la stringa: ");
            String str = sc.nextLine();

            String messaggioDaInviare = "get;"+str;
            byte[] buffer = messaggioDaInviare.getBytes();
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            packet.setAddress(InetAddress.getByName("127.0.0.1"));
            packet.setPort(12345);
            sochet.send(packet);
            
        }

        byte[] bufferRisposta = new byte[1500];
        DatagramPacket packetRisposta = new DatagramPacket(bufferRisposta, bufferRisposta.length);
        sochet.receive(packetRisposta);
        String messaggio = new String(packetRisposta.getData(),0,packetRisposta.getLength());
        System.out.println(messaggio);


    }
}