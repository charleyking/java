import java.net.*;
import java.io.*;

public class ChatClient {
    private Socket socket;
    private InetAddress hostAddress;
    private BufferedReader reader;
    private PrintWriter writer;

    public ChatClient(){
        try{
            hostAddress = InetAddress.getByName("localhost");
            socket = new Socket(hostAddress, 1300); // create socket
            while(true){
                /* write messge to server*/
                reader  = new BufferedReader(new InputStreamReader(System.in)); // get message from console
                String outMessage = reader.readLine();  
                if (outMessage.equals("bye")) break;
                writer = new PrintWriter(socket.getOutputStream(),true);  // put socket to outputstream
                writer.println(outMessage);  // write message to socket
                /* receive message from server*/
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                System.out.println(reader.readLine());
                reader.close();
                writer.close();
            }
        } catch (UnknownHostException e) {
            System.out.println("UnknownHostException");
        } catch (SocketException e) {
            System.out.println("Can't open socket, please start server first");
        } catch (IOException e) {
            System.err.println("IOException");
        } catch (Exception e) {
            System.err.println("Exception");
        }
        System.out.println("ChatClient over");
    }

    public static void main(String[] args) {
        new ChatClient();
    }
}