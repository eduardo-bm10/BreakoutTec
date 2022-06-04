import game.Breakout;

/**
 * Main:
 * Clase principal que inicia la ejecución del programa
 */
public class Main {
    /**
     * main:
     * Instancia un objeto Breakout, y mantiene el juego en ejecución.
     * @param args
     * @throws InterruptedException para manejar el sleep.
     */
    public static void main(String[] args) throws InterruptedException {
        Breakout game = new Breakout();
        while (game.isRunning()) {
            game.update();
            game.render();
            Thread.sleep(10);
        }
    }
}
