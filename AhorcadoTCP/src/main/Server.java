package main;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

import tcp.GameServer;

public class Server {
    private ServerSocket serverSocket;
    private Socket socket;
    private List<GameServer> clientList;
    private ExecutorService service;

    public void start() {
        try {
            service = Executors.newCachedThreadPool();
            clientList = new ArrayList<>();
            serverSocket = new ServerSocket(4545);
            accept();
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            return ;
        }
    }

    private void accept() throws IOException {
        while (true) {
            socket = serverSocket.accept();
            var client = new GameServer(socket);
            clientList.add(client);
            service.submit(client);
        }
    }

    public static void main(String[] args) throws Exception {
        Server server = new Server();
        server.start();
    }
}
