import game.Breakout;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Breakout game = new Breakout();
        while (game.isRunning()) {
            game.update();
            game.render();
            Thread.sleep(10);
        }
    }
}
