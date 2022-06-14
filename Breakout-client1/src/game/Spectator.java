package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Clase Spectator:
 * Es una subclase de Breakout.
 * Spectator permite las mismas funcionalidades visuales de Breakout, con la diferencia de que no puede mover la barra.
 * Hace override a la función update para invalidar el movimiento de la barra en juego.
 * @author Eduardo Bolívar
 */
public class Spectator extends Breakout implements KeyListener {
    /**
     * update:
     * Verifica y actualiza constantemente el estado de juego.
     * No permite el movimiento de la barra mediante el teclado.
     * @author Eduardo Bolívar
     */
    @Override
    public void update() {
        if (Breakout.lives == 0) {
            this.gameOver = true;
        }
        this.checkBalls(this.bar.getX(), this.bar.getY(), this.bar.getWidth());
        this.checkCollisions();
        this.updateText();
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
