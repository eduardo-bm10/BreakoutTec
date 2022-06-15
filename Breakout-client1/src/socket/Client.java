package socket;

import game.Breakout;
import game.Game;

import java.io.*;
import java.net.*;

public class Client {
    private static BufferedReader in;
    private static PrintWriter out;
    private static Breakout client;
    public static void main(String[] args) throws IOException {
        String hostName = "localhost";
        int portNumber = 5566;
        Socket firstSocket = new Socket(hostName, portNumber);
        in = new BufferedReader(new InputStreamReader(firstSocket.getInputStream()));
        out = new PrintWriter(firstSocket.getOutputStream(), true);

        client = Game.getInstance();
        Game g = new Game();
        Thread t = new Thread(g);
        t.start();

        while (true) {
            String message = receiveInfo();
            if (message.equals("close")) {
               in.close();
               out.close();
               firstSocket.close();
               break;
            }
            checkMessage(message);
        }
    }

    public static void sendInfo(String msg) {
        Client.out.println(msg);
    }

    public static String receiveInfo() throws IOException {
        return Client.in.readLine();
    }

    public static void checkMessage(String msg) {
        if (msg.startsWith("K")) {
            int i = Integer.parseInt(msg.split(":")[1].split(",")[0]);
            int j = Integer.parseInt(msg.split(":")[1].split(",")[1]);
            client.killBlock(i,j);
        }
        else if (msg.equals("1") || msg.equals("2") || msg.equals("3") || msg.equals("4")) {
            client.addPoints(Integer.parseInt(msg));
        }
        else {
            switch (msg) {
                case "+V":
                    client.changeSpeed(1);
                case "-V":
                    client.changeSpeed(0);
                case "+B":
                    client.modBarSize(1);
                case "-B":
                    client.modBarSize(0);
                case "NB":
                    client.addBall();
                case "NL":
                    client.giveLife();
            }
        }
    }
}