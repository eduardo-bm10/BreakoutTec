package items;

import game.Breakout;
import java.lang.Math;

/**
 * Clase Ball:
 * Define la bola de juego
 * Hereda atributos y métodos de Object
 * Provee atributos para el movimiento de la bola
 * @author Eduardo Bolívar
 */
public class Ball extends Object {
    private boolean moving, right, up;
    private double speed, angle;

    /**
     * Constructor Ball:
     * Asigna valores iniciales para posición, y tamaño de la bola.
     * @param x posición inicial x
     * @param y posición inicial y
     * @param diameter diamétro de la bola
     * @author Eduardo Bolívar
     */
    public Ball(double x, double y, int diameter) {
        super(x - (diameter/2), y, diameter, diameter);
        this.moving = false;
        this.right = true;
        this.up = true;
        this.speed = 5;
    }

    /**
     * restartPosition:
     * Devuelve la bola a la posición en la que se encuentra la barra y reinicia su movimiento
     * @param x posición x de la barra
     * @param y posición y de la barra
     * @param width nueva anchura de la bola
     * @param height nueva altura de la bola
     * @author Eduardo Bolívar
     */
    @Override
    public void restart(double x, double y, int width, int height) {
        super.restart(x,y,width,height);
        this.moving = false;
        this.up = true;
    }

    /**
     * startedMoving:
     * Activa el movimiento de la bola si se presiona la barra espaciadora
     * @author Eduardo Bolívar
     */
    public void startedMoving() {
        this.moving = true;
    }

    /**
     * updateBall:
     * Realiza el movimiento de la bola por toda la pantalla, verifica si choca con los límites de la ventana para rebotar.
     * Si cae, reinicia la posición de la bola y la coloca en la barra.
     * @param barX posición x de la barra.
     * @param barY posición y de la barra.
     * @author Eduardo Bolívar
     */
    public void update_ball(double barX, double barY, double barWidth) {
        if (this.moving) {
            if (right && up) {
                this.x = this.x + speed*Math.cos(this.angle);
                this.y = this.y - speed*Math.sin(this.angle);
                if (this.x >= 1230) {
                    this.right = false;
                }
                if (this.y <= 0) {
                    this.up = false;
                }
            }
            else if (!right && up) {
                this.x = this.x - speed*Math.cos(this.angle);
                this.y = this.y - speed*Math.sin(this.angle);
                if (this.x <= 0) {
                    this.right = true;
                }
                if (this.y <= 0) {
                    this.up = false;
                }
            }
            else if (right && !up) {
                this.x = this.x + speed*Math.cos(this.angle);
                this.y = this.y + speed*Math.sin(this.angle);
                if (this.x >= 1230) {
                    this.right = false;
                }
                if (this.y >= 720) {
                    this.restart(barX, barY, this.width, this.height);
                    Breakout.removeLife();
                }
            }
            else if (!right && !up) {
                this.x = this.x - speed*Math.cos(this.angle);
                this.y = this.y + speed*Math.sin(this.angle);
                if (this.x <= 0) {
                    this.right = true;
                }
                if (this.y >= 720) {
                    this.restart(barX, barY, this.width, this.height);
                    Breakout.removeLife();
                }
            }
        }
        else {
            this.x = barX + (barWidth/2) - 10;
            this.y = barY - 10;
        }
    }

    public double accelerate() {
        return this.speed + 2;
    }

    public void resetSpeed() {
        this.speed = 5;
    }

    /**
     * setRight:
     * Asigna si la bola va a la derecha o no.
     * @param right true si la bola se mueve a la derecha, false si se mueve a la izquierda.
     * @author Eduardo Bolívar
     */
    public void setRight(boolean right) {
        this.right = right;
    }

    /**
     * setUp:
     * Asigna si la bola va hacia arriba o no.
     * @param up true si la bola se mueve hacia arriba, false si se mueve hacia abajo
     * @author Eduardo Bolívar
     */
    public void setUp(boolean up) {
        this.up = up;
    }

    public void setAngle(double a) {
        this.angle = a;
    }

}
