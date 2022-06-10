package items;

/**
 * Clase Bar:
 * Define la barra de juego
 * Hereda atributos y métodos de Object
 * Provee atributos para el movimiento de la barra
 * @author Eduardo Bolívar
 */
public class Bar extends Object {
    private boolean right;
    private boolean left;

    /**
     * Constructor Bar:
     * Asigna valores iniciales de posición x, posición y, anchura y altura.
     * Establece como false el movimiento de la barra ya que inicialmente no se ha presionado ninguna tecla
     * @param x posición x
     * @param y posición y
     * @param width anchura de la barra
     * @param height altura de la barra
     */
    public Bar(double x, double y, int width, int height) {
        super(x - (width/2), y, width, height);
        this.right = false;
        this.left = false;
    }

    /**
     * update_bar:
     * Si el atributo right es true, mueve la barra a la derecha hasta el límite derecho de la ventana.
     * Si el atributo left es true, mueve la barra a la derecha hasta el límite izquierdo de la ventana
     * @author Eduardo Bolívar
     */
    public void update_bar() {
        if (right) {
            this.x = this.x + 8;
            if (this.x >= (1250 - this.width)) {
                this.x = 1250 - this.width;
            }
        }
        else if (left) {
            this.x = this.x - 8;
            if (this.x <= 0) {
                this.x = 0;
            }
        }
    }

    /**
     * move:
     * Asigna true al atributo right si se presiona la flecha derecha
     * Asigna true al atributo left si se presiona la flecha izquierda
     * @param key código de tecla presionada
     * @author Eduardo Bolívar
     */
    public void move(int key) {
        if (key == 37) {
            this.left = true;
        }
        else if (key == 39) {
            this.right = true;
        }
    }

    /**
     * stop:
     * Asigna false al atributo right si se deja de presionar la flecha derecha
     * Asigna false al atributo left si se deja de presionar la flecha izquierda
     * @param key código de tecla presionada
     * @author Eduardo Bolívar
     */
    public void stop(int key) {
        if (key == 37) {
            this.left = false;
        }
        else if (key == 39) {
            this.right = false;
        }
    }


}
