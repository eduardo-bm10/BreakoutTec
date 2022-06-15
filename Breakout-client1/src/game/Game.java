package game;

import game.Breakout;
import game.Spectator;

/**
 * Main:
 * Clase principal que inicia la ejecución del programa
 */
public class Game implements Runnable {
    private static Breakout instance;

    /**
     * main:
     * Instancia un objeto Breakout, y mantiene el juego en ejecución.
     * @throws InterruptedException para manejar el sleep.
     */
    public static void checkGame() throws InterruptedException {
        while (true) {
            if (instance.isGameOver()) {
                instance.showGameOverMessage();
            } else {
                instance.update();
                instance.render();
            }
            Thread.sleep(9);
        }
    }

    public static Breakout getInstance() {
        if (instance == null) {
            instance = new Breakout();
        } else {
            instance = new Spectator();
        }
        return instance;
    }

    @Override
    public void run() {
        try {
            checkGame();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
