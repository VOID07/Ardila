package udp;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

import udp.resources.Resource;

/**
 *
 * @author jeanr
 */
public class Server extends Thread{
    protected DatagramSocket socket;
    protected DatagramPacket in,out;
    protected int port;
    
    public Server(int p_port)
    {
        this.port = p_port;
        try {
            socket = new DatagramSocket(p_port);
        } catch (SocketException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Resource read()
    {
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
        boolean newServer = false;
        Resource newResource = null;
        while(true){
            newResource = this.read();
            newServer = newResource.newServer;
            this.port = newServer ? (++this.port) : port;
            if (newServer){
                Server newThread = new Server(this.port);
                newThread.start();
                send(String.valueOf(this.port), newResource.in);
                System.out.println("New gameServer listening at " + this.port);
            }
            newServer = false;
            newResource = null;
        }
    }
}
