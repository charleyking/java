import java.net.*;
import java.io.*;
import java.util.*;

public class ChatServer {
    private ServerSocket server;
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;
 
    public ChatServer() {
        try {   
            server = new ServerSocket(1300);
            System.out.println("Server started, listening 1300");
            while (true) {
                socket = server.accept();  // get a socket from client
                System.out.println("accepted new socket" + socket);
                /* receive message from client*/
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));  // transfer socket to reader
                String received = reader.readLine();  // get message in socket
                System.out.println("received message is " + received);
                /* send message to client */
                writer = new PrintWriter(socket.getOutputStream(),true);
                writer.println("hello");
                reader.close();
                writer.close();
            }
        } catch (SocketException e) {
            System.out.println("cannot open socket");
        } catch (IOException e){
            System.out.println("Communication error");
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        new ChatServer();
    }
}
 


