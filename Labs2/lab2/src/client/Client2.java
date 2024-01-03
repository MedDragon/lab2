package client;

public class Client2 {
    public static void main(String[] args) {
        ConnectMessage connectWithServer = new ConnectMessage();
        Thread tConnectInputMessage = new Thread(connectWithServer);
        tConnectInputMessage.start();

        ReceiveMessage receiverMessage = new ReceiveMessage(connectWithServer.getInputStreamServer());
        Thread tReceiveMessage = new Thread(receiverMessage);
        tReceiveMessage.start();
    }
}
