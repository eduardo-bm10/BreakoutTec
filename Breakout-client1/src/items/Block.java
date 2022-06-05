package items;

/**
 * Clase Block:
 * Define los bloques del juego
 * Hereda atributos y métodos de Object
 * Provee atributos para el tipo de bloque y estado actual del bloque.
 * @author Eduardo Bolívar
 */
public class Block extends Object {
    private int type;
    private boolean alive;

    /**
     * Constructor Block:
     * Asigna posición del bloque, anchura del bloque, altura del bloque, y tipo de bloque.
     * @param x posición x
     * @param y posición y
     * @param width anchura del bloque
     * @param height altura del bloque
     * @param type tipo de bloque (rojo, naranja, amarillo o verde).
     * @author Eduardo Bolívar
     */
    public Block(int x, int y, int width, int height, int type) {
        super(x,y,width,height);
        this.type = type;
        this.alive = true;
    }

    /**
     * getType:
     * @return Entero que representa el tipo de bloque: 1 para rojo, 2 para naranja, 3 para amarillo y 4 para verde.
     * @author Eduardo Bolívar
     */
    public int getType() {
        return this.type;
    }

    /**
     * getAlive:
     * @return el estado actual del bloque: true para activo y false para inactivo.
     * @author Eduardo Bolívar
     */
    public boolean getAlive() {
        return this.alive;
    }

    /**
     * kill:
     * Inactiva un bloque, por lo tanto, la bola no podrá rebotar en él ni será visible en pantalla.
     * @author Eduardo Bolívar
     */
    public void kill() {
        this.alive = false;
    }
}
