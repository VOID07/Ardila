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
    
    public void read()
    {
        byte[] buffer = new byte[400];
        in = new DatagramPacket(buffer, 0, buffer.length); 
        String message = "";
        try {
            this.socket.receive(in);
            message = new String(in.getData());
            message = message.substring(0,5);
            System.out.println(message.toLowerCase());
            if (message.toLowerCase().equals("start")){
                GameServer game = new GameServer(socket, in);
                game.start();
                game.join();
            } else {
                String mess = "\nIntentaluelo nuevamente, envia 'start' para iniciar";
                send(mess, in);
            }
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException e) {
            e.printStackTrace();
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
        while(true){
            read();
        }
    }
}
