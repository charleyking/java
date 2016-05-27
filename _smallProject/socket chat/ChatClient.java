import java.net.*;
import java.io.*;

public class ChatClient {
    private DatagramSocket socket;
    private InetAddress hostAddress;
    private byte[] buf = new byte[1000];
    private DatagramPacket packet = new DatagramPacket(buf,buf.length);
    public ChatClient(){
        try{
            socket = new DatagramSocket();
            hostAddress = InetAddress.getByName("localhost");
            while(true){
                String outMessage ="";  
                BufferedReader stdin  = new BufferedReader(new InputStreamReader(System.in));
                try {
                    outMessage = stdin.readLine();
                } catch (IOException ie) {  
                    System.err.println("IO error!");   
                }
                if (outMessage.equals("bye")) break;
                String outString = "Client say: "+ outMessage;
                byte[] buf = outString.getBytes();
                DatagramPacket out = new DatagramPacket(buf,buf.length,hostAddress,4000);
                socket.send(out);
                socket.receive(packet);
                String received = new String(packet.getData(),0,packet.getLength()) + ", received from address: "+ packet.getAddress() + ", " + packet.getPort() + 
                ": ";
                System.out.println(received);
            }
        } catch (UnknownHostException e) {
            System.out.println("Can;t open socket");
            System.exit(1);
        } catch (SocketException e) {
            System.out.println("Can;t open socket");
            e.printStackTrace();
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Communication error");
            e.printStackTrace();
            System.exit(1);
        } catch (Exception e) {
            System.err.println("Communication error");
            e.printStackTrace();
            System.exit(1);
        }
        System.out.println("ChatClient over");
    }

    public static void main(String[] args) {
        new ChatClient();
    }
}