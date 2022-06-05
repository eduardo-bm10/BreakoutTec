package items;

/**
 * Clase Ball:
 * Define la bola de juego
 * Hereda atributos y métodos de Object
 * Provee atributos para el movimiento de la bola
 * @author Eduardo Bolívar
 */
public class Ball extends Object {
    private boolean moving, right, up;

    /**
     * Constructor Ball:
     * Asigna valores iniciales para posición, y tamaño de la bola.
     * @param x posición inicial x
     * @param y posición inicial y
     * @param diameter diamétro de la bola
     * @author Eduardo Bolívar
     */
    public Ball(int x, int y, int diameter) {
        super(x, y, diameter, diameter);
        this.moving = false;
        this.right = true;
        this.up = true;
    }

    /**
     * restartPosition:
     * Devuelve la bola a la posición en la que se encuentra la barra y reinicia su movimiento
     * @param barX posición x de la barra
     * @param barY posición y de la barra
     * @author Eduardo Bolívar
     */
    private void restartPosition(int barX, int barY) {
        this.x = barX;
        this.y = barY;
        this.moving = false;
        this.right = true;
        this.up = true;
    }

    /**
     * startedMoving:
     * Activa el movimiento de la bola si se presiona la barra espaciadora
     * @param key código de tecla presionada
     * @author Eduardo Bolívar
     */
    public void startedMoving(int key) {
        if (key == 32) {
            this.moving = true;
        }
    }

    /**
     * updateBall:
     * Realiza el movimiento de la bola por toda la pantalla, verifica si choca con los límites de la ventana para rebotar.
     * Si cae, reinicia la posición de la bola y la coloca en la barra.
     * @param barX posición x de la barra.
     * @param barY posición y de la barra.
     * @author Eduardo Bolívar
     */
    public void update_ball(int barX, int barY) {
        int speed = 5;
        if (this.moving) {
            if (right && up) {
                this.x = this.x + speed;
                this.y = this.y - speed;
                if (this.x >= 1230) {
                    this.right = false;
                }
                if (this.y <= 0) {
                    this.up = false;
                }
            }
            else if (!right && up) {
                this.x = this.x - speed;
                this.y = this.y - speed;
                if (this.x <= 0) {
                    this.right = true;
                }
                if (this.y <= 0) {
                    this.up = false;
                }
            }
            else if (right && !up) {
                this.x = this.x + speed;
                this.y = this.y + speed;
                if (this.x >= 1230) {
                    this.right = false;
                }
                if (this.y >= barY + 15) {
                    this.restartPosition(barX, barY);
                }
            }
            else if (!right && !up) {
                this.x = this.x - speed;
                this.y = this.y + speed;
                if (this.x <= 0) {
                    this.right = true;
                }
                if (this.y >= barY + 15) {
                    this.restartPosition(barX,barY);
                }
            }
        }
        else {
            this.x = barX + 40;
            this.y = barY - 10;
        }
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

}
