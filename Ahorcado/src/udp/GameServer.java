package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.util.logging.Level;
import java.util.logging.Logger;

import udp.resources.Resource;

public class GameServer extends Server{

    private String[] palabras = null;

    public GameServer(int p_port) {
        super(p_port);
        this.palabras = new String[]{"pecera", "perro", "salchicha", "nube", "globo", "aire"};
    }

    @Override
    public Resource read() {
        byte[] buffer = new byte[400];
        in = new DatagramPacket(buffer, 0, buffer.length);
        String message = "";
        boolean newServer = false;
        try {
            socket.receive(in);
            message = new String(in.getData());
            if (message != null || message != ""){
                newServer = true;
            }
            System.out.println(message);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            return this.read();
        }
        Resource response = new Resource(newServer, in);
        return response;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        super.run();
    }
    
}
