package client;
import java.io.*;
import java.net.Socket;
public class ConnectMessage implements Runnable{
    private Socket serverConnect;
    private InputStream inputStreamServer;

    public ConnectMessage(){
        try{
            serverConnect = new Socket("localhost", 8887);
            inputStreamServer = serverConnect.getInputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run(){
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStreamServer));
        String serverMessage;

        while (true){
            try {
                serverMessage = in.readLine();
                if(serverMessage != null){
                    System.out.println(serverMessage + '\n');
                    break;
                }
            } catch (IOException e) {
                System.err.println("Error reading: " + e.getMessage());
                break;
            }
        }

        PrintWriter out = null;
        BufferedReader inputUser = new BufferedReader(new InputStreamReader(System.in));
        String userMessage = null;

        while (true) {
            System.out.println("Enter massage: ");
            try {
                userMessage = inputUser.readLine();
                out = new PrintWriter(serverConnect.getOutputStream(), true);
                out.println(userMessage);
            } catch (IOException e) {
                System.err.println("Error input/output: " + e.getMessage());
            }
        }
    }

    public InputStream getInputStreamServer() {
        return inputStreamServer;
    }
}
