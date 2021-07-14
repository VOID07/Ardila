package udp.resources;

import java.net.DatagramPacket;

public class Resource {
    public DatagramPacket in = null;
    public boolean newServer = false;
    public String message = null;

    public Resource(boolean p_newServer, DatagramPacket p_in){
        this.newServer =  p_newServer;
        this.in = p_in;
    }
}
