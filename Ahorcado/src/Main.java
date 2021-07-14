import udp.Server;

public class Main {
    public static void main(String[] args) throws Exception {
        Server listenerServer = new Server(5000);
        listenerServer.start();
    }
}
