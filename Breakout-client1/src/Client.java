import game.Breakout;

import java.io.*;
import java.net.*;

public class Client {

    public static void main(String[] args) throws IOException, InterruptedException {
        String hostName = "localhost";
        int portNumber = 5566;
        //Socket firstSocket = new Socket(hostName, portNumber);
        Breakout client = Game.getInstance();
        BufferedReader in, stdIn;
        while (true) {
            //in = new BufferedReader(new InputStreamReader(firstSocket.getInputStream()));
            stdIn = new BufferedReader(new InputStreamReader(System.in));
            //String message = in.readLine();
            //if (message.equals("close")) {
              //  in.close();
                //stdIn.close();
                //firstSocket.close();
            //}
            Game.checkGame(client);
            //System.out.println("Servidor: " + message);
            //PrintWriter out = new PrintWriter(firstSocket.getOutputStream(), true);
            //out.println("Working");
        }
    }
}