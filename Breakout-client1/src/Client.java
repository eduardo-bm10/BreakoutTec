import java.io.*;
import java.net.*;

public class Client {

    public static void main(String[] args) throws IOException {

        String hostName = "localhost";
        int portNumber = 5566;
        Socket firstSocket = new Socket(hostName, portNumber);



        BufferedReader in = new BufferedReader(new InputStreamReader(firstSocket.getInputStream()));
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Servidor: " + in.readLine());

        PrintWriter out = new PrintWriter(firstSocket.getOutputStream(), true);
        out.println("Working");

        in.close();
        stdIn.close();
        firstSocket.close();

    }
}