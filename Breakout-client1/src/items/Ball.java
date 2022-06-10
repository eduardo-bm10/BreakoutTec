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
    public static int id_0 = 0;
    private int id;
    private static boolean moving;
    private static double speed = 5;
    private boolean right, up;
    private double angle;

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
        this.right = true;
        this.up = true;
        this.id = id_0;
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
        moving = false;
        this.up = true;
    }

    /**
     * startedMoving:
     * Activa el movimiento de la bola si se presiona la barra espaciadora
     * @author Eduardo Bolívar
     */
    public static void startMoving() {
        moving = true;
    }

    /**
     * stopMoving:
     * Desactiva el movimiento cuando la ultima bola en pantalla cae.
     * @author Eduardo Bolívar
     */
    public static void stopMoving() {
        moving = false;
    }

    /**
     * updateBall:
     * Realiza el movimiento de la bola por toda la pantalla, verifica si choca con los límites de la ventana para rebotar.
     * Si cae la última bola, reinicia su posición y la coloca en el centro de la barra.
     * @param barX posición x de la barra.
     * @param barY posición y de la barra.
     * @param barWidth la anchura actual de la barra.
     * @param size tamaño actual del vector de bolas.
     * @author Eduardo Bolívar
     */
    public void update_ball(double barX, double barY, double barWidth, int size) {
        if (moving) {
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
                if (this.y >= 720 && size == 1) {
                    Breakout.removeLife();
                    this.restart(barX, barY, this.width, this.height);
                }
            }
            else if (!right && !up) {
                this.x = this.x - speed*Math.cos(this.angle);
                this.y = this.y + speed*Math.sin(this.angle);
                if (this.x <= 0) {
                    this.right = true;
                }
                if (this.y >= 720 && size == 1) {
                    Breakout.removeLife();
                    this.restart(barX, barY, this.width, this.height);
                }
            }
        }
        else if (size == 1) {
            this.x = barX + (barWidth/2) - 10;
            this.y = barY - 10;
        }
    }

    /**
     * accelerate:
     * Aumenta la velocidad de la bola en dos unidades.
     * @return el valor sumado de velocidad.
     * @author Eduardo Bolívar
     */
    public static double accelerate() {
        return speed + 2;
    }

    /**
     * getId:
     * @return el ID correspondiente a una bola.
     * @author Eduardo Bolívar
     */
    public int getId() {
        return this.id;
    }

    /**
     * setId:
     * Asigna el valor de ID a una bola.
     * @param id el ID a asignar
     * @author Eduardo Bolívar
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * setSpeed:
     * Asigna la velocidad especificada a las bolas.
     * @param sp la nueva velocidad para cada una de las bolas en juego.
     * @author Eduardo Bolívar
     */
    public static void setSpeed(double sp) {
        speed = sp;
    }

    /**
     * resetSpeed:
     * Devuelve la velocidad de las bolas a su velocidad inicial.
     * @author Eduardo Bolívar
     */
    public void resetSpeed() {
        speed = 5;
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

    /**
     * setAngle:
     * Asigna el ángulo con el que rebota la bola de la barra.
     * @param a el ángulo asignado
     * @author Eduardo Bolívar
     */
    public void setAngle(double a) {
        this.angle = a;
    }

}
