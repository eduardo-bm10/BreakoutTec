package game;

public class Spectator extends Breakout {
    @Override
    public void update() {
        if (Breakout.lives == 0) {
            this.gameOver = true;
        }
        this.ball.update_ball(this.bar.getX(), this.bar.getY(), this.bar.getWidth());
        this.checkCollisions();
        this.updateText();
    }
}
