package items;

/**
 * Clase Object:
 * Tiene los atributos de posición y dimensiones de los objetos y métodos para acceder a dichos atributos.
 * @author Eduardo Bolívar
 */
public class Object
{
    protected double x;
    protected double y;
    protected int width;
    protected int height;

    /**
     * Constructor Object:
     * Asigna valores iniciales de posición en x, posición en y, ancho y altura del objeto.
     * @param x posición x inicial.
     * @param y posición y inicial.
     * @param width anchura inicial del objeto.
     * @param height altura inicial del objeto.
     * @author Eduardo Bolívar
     */
    public Object(double x, double y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     * getX:
     * @return la posición x actual del objeto.
     * @author Eduardo Bolívar
     */
    public double getX() {
        return this.x;
    }

    /**
     * getY:
     * @return la posición en y actual del objeto.
     * @author Eduardo Bolívar
     */
    public double getY() {
        return this.y;
    }

    /**
     * getWidth:
     * @return la anchura del objeto.
     * @author Eduardo Bolívar
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * setWidth:
     * Permite actualizar la anchura del objeto.
     * @param width la nueva anchura del objeto.
     * @author Eduardo Bolívar
     */
    public void setWidth(double width) {
        this.width = (int) width;
    }

    /**
     * getHeight:
     * @return la altura del objeto.
     * @author Eduardo Bolívar
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * restart:
     * Reinicia la posición y las dimensiones del objeto a las que tenía cuando inició la ejecución del programa.
     * @param x la posición x inicial.
     * @param y la posición y inicial.
     * @param width la anchura inicial.
     * @param height la altura inicial.
     * @author Eduardo Bolívar
     */
    public void restart(double x, double y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
}
