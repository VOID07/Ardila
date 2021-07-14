package udp;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jeanr
 */
public class Client extends Thread {

    private DatagramSocket socket;
    private DatagramPacket in, out;

    public Client() {
        try {
            socket = new DatagramSocket();
        } catch (SocketException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void send() {
        try {
            Scanner readIn = new Scanner(System.in);
            System.out.println("Message");
            String info = readIn.nextLine();
            readIn.close();
            out = new DatagramPacket(info.getBytes(), info.getBytes().length, InetAddress.getLocalHost(), 8721);
            socket.send(out);
        } catch (UnknownHostException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void read() {
        try {
            byte[] buffer = new byte[400];

            in = new DatagramPacket(buffer, 0, buffer.length);
            socket.receive(in);
            String info = new String(in.getData());
            System.out.println(info);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void run() {
        while (true) {
            read();
        }
    }
}
