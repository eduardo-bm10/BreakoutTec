import game.Breakout;
import game.Spectator;

/**
 * Main:
 * Clase principal que inicia la ejecución del programa
 */
public class Main {
    private static Breakout instance;

    /**
     * main:
     * Instancia un objeto Breakout, y mantiene el juego en ejecución.
     * @param args
     * @throws InterruptedException para manejar el sleep.
     */
    public static void main(String[] args) throws InterruptedException {
        Breakout game = getInstance();
        while (game.isRunning()) {
            if (game.isGameOver()) {
                game.showGameOverMessage();
            }
            else {
                game.update();
                game.render();
            }
            Thread.sleep(9);
        }
    }

    public static Breakout getInstance() {
        if (instance == null) {
            instance = new Breakout();
        }
        else {
            instance = new Spectator();
        }
        return instance;
    }
}
