import udp.Server;

public class RunServer {
    public static void main(String[] args) throws Exception {
        Server server = new Server(5000);
        server.start();
        server.join();
    }
}
