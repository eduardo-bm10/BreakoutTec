package socket;

import game.Breakout;
import game.Game;

import java.io.*;
import java.net.*;

public class Client {
    private static BufferedReader in, stdIn;
    private static PrintWriter out;
    private static Breakout client;

    public static void main(String[] args) throws IOException, InterruptedException {
        String hostName = "localhost";
        int portNumber = 5566;
        Socket firstSocket = new Socket(hostName, portNumber);

        client = Game.getInstance();

        in = new BufferedReader(new InputStreamReader(firstSocket.getInputStream()));
        stdIn = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(firstSocket.getOutputStream(), true);

        while (firstSocket.isConnected()) {
            String message = receiveInfo();;
            if (message.equals("close")) {
               in.close();
               stdIn.close();
               firstSocket.close();
            }
            Game.checkGame(client);
            checkMessage(message);
            System.out.println("Servidor: " + message);
        }
    }

    public static void sendInfo(String msg) {
        Client.out.println(msg);
    }

    public static String receiveInfo() throws IOException {
        return Client.in.readLine();
    }

    public static void checkMessage(String msg) {
        switch (msg) {
            case "+V":
                client.changeSpeed(1);
            case "-V":
                client.changeSpeed(0);
            case "+B":
                client.modBarSize(1);
            case "-B":
                client.modBarSize(0);
            case "LL":
                Breakout.removeLife();
            case "NB":
                client.addBall();
            case "NL":
                client.giveLife();
            case "LG":
                client.gameOver = true;
            case "WG":
                client.nextLevel();
            default:
        }
    }
}