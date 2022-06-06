package game;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import items.Ball;
import items.Bar;
import items.Block;

/**
 * Breakout:
 * Clase de juego. Crea la ventana principal y contiene los métodos necesarios para la ejecución de la jugabilidad.
 * Hereda JPanel para dibujar los objetos en pantalla.
 * Implementa KeyListener para input de teclado.
 * @author Eduardo Bolívar
 */
public class Breakout extends JPanel implements KeyListener {
    private static int points;
    private static int lives;
    private JFrame window;
    private Bar bar;
    private Ball ball;
    private ArrayList<Block> blocks;
    private boolean running;
    private JLabel pointsText;
    private JLabel livesText;

    /**
     * Constructor:
     * Inicializa los atributos del juego.
     * @author Eduardo Bolívar
     */
    public Breakout() {
        this.initWindow();
        int initX = this.window.getSize().width / 2;
        int initY = this.window.getSize().height - 80;
        Breakout.points = 0;
        Breakout.lives = 3;

        this.initObjects(initX, initY);
        this.initText();

        this.window.add(this);
        this.window.addKeyListener(this);
        this.setBackground(Color.BLACK);
        this.setVisible(true);

        this.running = true;
    }

    /**
     * initWindow:
     * Método que inicializa la ventana de juego.
     * Asigna título, tamaño. y visión de la ventana.
     * @author Eduardo Bolívar
     */
    private void initWindow() {
        this.window = new JFrame("Breakout");
        this.window.setSize(1250,720);
        this.window.setResizable(false);
        this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.window.setVisible(true);
    }

    /**
     * initObjects:
     * Inicializa los objetos en pantalla. Establece la posición inicial de la barra, de la bola, y de cada bloque en el arreglo de bloques.
     * Agrega los bloques al arreglo.
     * @param initX es la posición X inicial de la barra y de la bola, la cual corresponde a la mitad de la pantalla.
     * @param initY es la posición Y inicial de la barra y de la bola, que corresponde a un punto bajo de la pantalla.
     * @author Eduardo Bolívar
     */
    private void initObjects(int initX, int initY) {
        this.bar = new Bar(initX, initY, 100, 10);
        this.ball = new Ball(initX, initY, 10);
        this.blocks = new ArrayList<>();

        int blockType;
        for (int j = 0; j < 8; j++) {
            switch (j) {
                case 0:
                case 1:
                    blockType = 1;
                    break;
                case 2:
                case 3:
                    blockType = 2;
                    break;
                case 4:
                case 5:
                    blockType = 3;
                    break;
                case 6:
                case 7:
                default:
                    blockType = 4;
            }
            for (int i = 0; i < 14; i++) {
                Block b = new Block(5 + ((this.window.getWidth()-10)/14)*i, 90 + 25*j, 83, 20, blockType);
                b.setMatrixId(i+1, j+1);
                this.blocks.add(b);
            }
        }
    }

    public void initText() {
        this.pointsText = new JLabel();
        pointsText.setSize(100,100);
        pointsText.setLocation(50,50);
        pointsText.setForeground(Color.WHITE);

        this.livesText = new JLabel();
        this.livesText.setSize(100,100);
        this.livesText.setLocation(250,50);
        this.livesText.setForeground(Color.WHITE);


        this.add(pointsText);
        this.add(livesText);
    }

    public static void removeLife() {
        Breakout.lives--;
    }

    public void updateText() {
        pointsText.setText("Puntos: " + Breakout.points);
        livesText.setText("Vidas: " + Breakout.lives);
    }

    /**
     * collideBallBar:
     * Verifica si existe colisión entre la bola y la barra del jugador, si es así, invierte la dirección de la bola hacia arriba.
     * @param b1 bola de juego.
     * @param b2 barra de juego.
     * @author Eduardo Bolívar
     */
    public void collideBallBar(Ball b1, Bar b2) {
        Rectangle r1 = new Rectangle();
        Rectangle r2 = new Rectangle();
        r1.setBounds((int) b1.getX(), (int) b1.getY(), (int) b1.getWidth(), (int) b1.getHeight());
        r2.setBounds((int) b2.getX(), (int) b2.getY(), (int) b2.getWidth(), (int) b2.getHeight());
        double barDiv = b2.getWidth()/6;
        if (r1.intersectsLine(r2.getX(),r2.getY(),r2.getX() + barDiv,r2.getY())) {
            b1.setRight(false);
            b1.setUp(true);
            b1.setAngle(Math.PI/6);
        }
        else if (r1.intersectsLine(r2.getX() + barDiv,r2.getY(),r2.getX() + 2*barDiv,r2.getY())) {
            b1.setRight(false);
            b1.setUp(true);
            b1.setAngle(Math.PI/4);
        }
        else if (r1.intersectsLine(r2.getX() + 2*barDiv,r2.getY(),r2.getX() + 3*barDiv,r2.getY())) {
            b1.setRight(false);
            b1.setUp(true);
            b1.setAngle(Math.PI/3);
        }
        else if (r1.intersectsLine(r2.getX() + 3*barDiv,r2.getY(),r2.getX() + 4*barDiv,r2.getY())) {
            b1.setRight(true);
            b1.setUp(true);
            b1.setAngle(Math.PI/3);
        }
        else if (r1.intersectsLine(r2.getX() + 4*barDiv,r2.getY(),r2.getX() + 5*barDiv,r2.getY())) {
            b1.setRight(true);
            b1.setUp(true);
            b1.setAngle(Math.PI/4);
        }
        else if (r1.intersectsLine(r2.getX() + 5*barDiv,r2.getY(),r2.getX() + 6*barDiv,r2.getY())) {
            b1.setRight(true);
            b1.setUp(true);
            b1.setAngle(Math.PI/6);
        }
    }

    /**
     * collideBallBlock:
     * Verifica si existe colisión entre la bola y algún bloque dentro del arreglo de bloques, si es así, destruye el bloque y cambia la dirección de la bola.
     * @param b1 bola del juego.
     * @param blocks arreglo de bloques del juego.
     * @author Eduardo Bolívar
     */
    private void collideBallBlock(Ball b1, ArrayList<Block> blocks) {
        Rectangle r1 = new Rectangle();
        Rectangle r2 = new Rectangle();
        r1.setBounds((int) b1.getX(), (int) b1.getY(), (int) b1.getWidth(), (int) b1.getHeight());
        for (Block b : blocks) {
            r2.setBounds((int) b.getX(), (int) b.getY(), (int) b.getWidth(), (int) b.getHeight());
            if (r1.intersectsLine(r2.getX(), r2.getY() + r2.getHeight(), r2.getX() + r2.getWidth(), r2.getY() + r2.getHeight()) && b.getAlive()) {
                b1.setUp(false);
                b.kill();
                points++;
                System.out.printf("Block: [%d,%d]\n", b.getMatrixI(), b.getMatrixJ());
                break;
            }
            else if (r1.intersectsLine(r2.getX(), r2.getY(), r2.getX() + r2.getWidth(), r2.getY()) && b.getAlive()) {
                b1.setUp(true);
                b.kill();
                points++;
                System.out.printf("Block: [%d,%d]\n", b.getMatrixI(), b.getMatrixJ());
                break;
            }
            else if (r1.intersectsLine(r2.getX() + r2.getWidth(), r2.getY(), r2.getX() + r2.getWidth(), r2.getY() + r2.getHeight()) && b.getAlive()) {
                b1.setRight(true);
                b.kill();
                points++;
                System.out.printf("Block: [%d,%d]\n", b.getMatrixI(), b.getMatrixJ());
                break;
            }
            else if (r1.intersectsLine(r2.getX(), r2.getY(), r2.getX(), r2.getY() + r2.getHeight()) && b.getAlive()) {
                b1.setRight(false);
                b.kill();
                points++;
                System.out.printf("Block: [%d,%d]\n", b.getMatrixI(), b.getMatrixJ());
                break;
            }
        }
    }

    /**
     * isRunning:
     * Informa del estado de ejecución del juego, si está ejecutándose o no.
     * @return true si el juego está en ejecución. False si la ejecución termina.
     * @author Eduardo Bolívar
     *
     */
    public boolean isRunning() {
        return this.running;
    }

    /**
     * checkCollisions:
     * Verifica constantemente si existe una colisión bola-barra o colisión bola-bloques.
     * Llama a los métodos collideBallBar y collideBallBlock.
     * @author Eduardo Bolívar
     */
    private void checkCollisions() {
        collideBallBar(this.ball, this.bar);
        collideBallBlock(this.ball, this.blocks);
    }

    /**
     * update:
     * Actualiza constantemente los objetos del juego.
     * Actualiza la posición de la barra, la posición de la bola, y el estado de los bloques.
     * @author Eduardo Bolívar
     */
    public void update() {
        if (lives == 0) {
            this.gameOver();
        }
        this.bar.update_bar();
        this.ball.update_ball(this.bar.getX(), this.bar.getY());

        this.updateText();
        this.checkCollisions();
    }

    /**
     * render:
     * Redibuja las actualizaciones realizadas por update para ser visualizadas en pantalla.
     * @author Eduardo Bolívar
     */
    public void render() {
        this.window.repaint();
    }

    public void gameOver() {
        this.running = false;
    }

    public void close() {
        this.running = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * keyPressed:
     * Método de la interfaz KeyListener.
     * Realiza el movimiento de la barra si se presiona la flecha derecha o izquierda.
     * Realiza el lanzamiento de la bola desde la barra si se presiona barra espaciadora.
     * Finaliza la ejecución del juego si se presiona ESC.
     * @param e the event to be processed.
     */
    @Override
    public void keyPressed(KeyEvent e) {;
        this.bar.move(e.getKeyCode());
        if (e.getKeyCode() == 32) {
            this.ball.startedMoving();
        }
        else if (e.getKeyCode() == 27) {
            this.close();
        }
    }

    /**
     * keyReleased:
     * Método de la interfaz KeyListener
     * Detiene el movimiento de la barra cuando se libera la flecha derecha o izquierda.
     * @param e the event to be processed
     */
    @Override
    public void keyReleased(KeyEvent e) {
        this.bar.stop(e.getKeyCode());
    }

    /**
     * paintComponent:
     * Método de la clase JPanel
     * Dibuja un rectángulo para la barra de juego en la posición respectiva
     * Dibuja una círculo para la bola de juego en la posición respectiva
     * Dibuja los bloques si estos no han sido destruidos por la bola
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.blue);
        g2d.fill3DRect((int) this.bar.getX(), (int) this.bar.getY(), (int) this.bar.getWidth(), (int) this.bar.getHeight(),true);

        g2d.setColor(Color.white);
        g2d.fillOval((int) this.ball.getX(), (int) this.ball.getY(), (int) this.ball.getWidth(), (int) this.bar.getHeight());

        if (lives == 0) {
            g2d.drawString("Game over", this.window.getWidth() / 2, this.window.getHeight() / 2);
        }

        for (Block b : this.blocks) {
            if (b.getType() == 1) {
                g2d.setColor(Color.red);
            }
            else if (b.getType() == 2) {
                g2d.setColor(Color.ORANGE);
            }
            else if (b.getType() == 3) {
                g2d.setColor(Color.YELLOW);
            }
            else if (b.getType() == 4) {
                g2d.setColor(Color.GREEN);
            }
            if (b.getAlive()) {
                g2d.fill3DRect((int) b.getX(), (int) b.getY(), (int) b.getWidth(), (int) b.getHeight(), true);
            }
        }
    }
}
