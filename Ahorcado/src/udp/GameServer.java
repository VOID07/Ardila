package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GameServer extends Thread{

    private String[] palabras = null;
    private String[] respuestas = null;
    protected DatagramPacket in,out;
    DatagramSocket socket = null;
    int pos = 0;
    int port = 0;
    int intentos = 10;

    public GameServer(DatagramSocket pSocket, DatagramPacket pIn) {
        this.palabras = new String[]{"pecera", "perro", "salchicha", "nube", "globo", "aire"};
        this.respuestas = new String[]{"------", "-----", "---------", "----", "-----", "----"};
        socket = pSocket;
        in = pIn;
    }

    public void read()
    {
        byte[] buffer = new byte[400];
        in = new DatagramPacket(buffer, 0, buffer.length);
        String mess = "";
        try {
            socket.receive(in);
            String letra = new String(in.getData());
            letra = letra.substring(0,1);
            if (letra != null || letra != ""){
                if(intentos > 0){
                    if (palabras[pos].contains(letra)){
                        while(palabras[pos].contains(letra)){
                            int index = palabras[pos].indexOf(letra);
                            palabras[pos] = palabras[pos].substring(0,index) + '-' + palabras[pos].substring(index+1);
                            respuestas[pos] = respuestas[pos].substring(0,index) + letra + respuestas[pos].substring(index+1);
                        }
                        mess = "Bien hecho, \n " + respuestas[pos];
                        if (!respuestas[pos].contains("-")){
                            pos++;
                            mess = mess + "\n Siguiente palabra: " + respuestas[pos];
                        }
                    } else {
                        intentos--;
                        mess ="Intentalo nuevamente\n" + respuestas[pos];
                    }
                } else {
                    pos = palabras.length;
                }
            }
            if (pos < palabras.length){
                send(mess,in);
            } else {
                send("El juego ha terminado",in);
            }
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }
    }
    
    public void send(final String info, final DatagramPacket client)
    {
        try {
            out = new DatagramPacket(info.getBytes(), info.getBytes().length,client.getAddress(), client.getPort());
            socket.send(out);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        send("Intenta adivinar la palabra" + respuestas[pos], in);
        while(true){
            read();
            if (pos == -1){
                break;
            }
        }
    }
    
}
