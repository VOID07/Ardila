package tcp;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GameServer  implements Runnable {

    private String[] palabras = null;
    private String[] respuestas = null;
    protected ObjectInputStream in;
    protected ObjectOutputStream out;
    Socket socket = null;
    private boolean running=true;

    int pos = 0;
    int port = 0;
    int intentos = 10;

    public GameServer(Socket pSocket) {
        this.palabras = new String[]{"pecera", "perro", "salchicha", "nube", "globo", "aire"};
        this.respuestas = new String[]{"------", "-----", "---------", "----", "-----", "----"};
        socket = pSocket;
        try {
            in = new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());
            
        } catch (IOException ex) {
            Logger.getLogger(GameServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void read()
    {
        try {
            String mess = in.readUTF().toLowerCase();
            if (mess.equals("stop")){
                stop();
                return;
            }
            String letra = mess.substring(0,1);
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
                send(mess);
            } else {
                send("El juego ha terminado");
            }
        } catch (IOException ex) {
            Logger.getLogger(GameServer.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }
    }
    
    public void send(final String info)
    {
        try {
            out.writeUTF(info);
            out.flush();
        } catch (IOException ex) {
            Logger.getLogger(GameServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void stop()
    {
        try {
            out.close();
            in.close();
            socket.close();
            running= false;
        } catch (IOException ex) {
            Logger.getLogger(GameServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        send("Intenta adivinar la palabra" + respuestas[pos]);
        while(running){
            read();
            if (pos == -1){
                stop();
            }
        }
    }
    
}
