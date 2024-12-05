import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.ArrayList;

public class Server {
    public static void main(String[] args) throws IOException {
        DatagramSocket socketServer=new DatagramSocket(12345);
        byte[] buffer =new byte[1500];
        DatagramPacket packetLettura=new DatagramPacket(buffer, buffer.length);

        ArrayList<String> stringhe= new ArrayList<>();
        while (true) {
            //lettura dati
            socketServer.receive(packetLettura);
            String messaggio=new String(packetLettura.getData(),0,packetLettura.getLength());
            String comando=messaggio.split(";")[0];
            String valore=messaggio.split(";")[1];
            
            //risposte
            String risposta="";
            byte[] bufferRisposta;
            DatagramPacket packetRisposta;
            if(comando.toLowerCase().equals("set")){
                stringhe.add(valore);
                risposta="ok";
            }
            else if(comando.toLowerCase().equals("get")){
                risposta="0";
                for(int i=0;i<stringhe.size();i++){
                    if(stringhe.get(i).equals(valore)){
                        risposta=stringhe.get(i);
                    }
                }
            }
            else{
                risposta="err";
            }

            bufferRisposta=risposta.getBytes();
            packetRisposta=new DatagramPacket(bufferRisposta, bufferRisposta.length);
            packetRisposta.setAddress(packetLettura.getAddress());
            packetRisposta.setPort(packetLettura.getPort());
            socketServer.send(packetRisposta);
        }
    }
}