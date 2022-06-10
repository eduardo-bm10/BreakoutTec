package game;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Vector;

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
    public boolean gameOver;
    protected static int points;
    protected static int lives;
    protected static int level;
    protected Bar bar;
    protected Vector<Ball> ballVector;
    private JFrame window;
    private ArrayList<Block> blocks;
    private boolean running;
    private JLabel title;
    private JLabel gameOverText;
    private JLabel pointsText;
    private JLabel livesText;
    private JLabel levelText;


    /**
     * Constructor:
     * Inicializa los atributos del juego.
     * @author Eduardo Bolívar
     */
    public Breakout() {
        this.initWindow();
        Breakout.points = 0;
        Breakout.lives = 3;
        Breakout.level = 1;
        this.initObjects(this.window.getSize().width / 2, this.window.getSize().height - 80);
        this.initText();
        this.window.add(this);
        this.window.addKeyListener(this);
        this.setLayout(null);
        this.setBackground(Color.BLACK);
        this.setVisible(true);
        this.gameOver = false;
        this.running = true;
    }

    /**
     * resetGame:
     * Devuelve el juego a sus condiciones iniciales.
     * Reestablece la cantidad de bolas en juego, la posición y tamaño de la barra, bloques, y vidas del jugador.
     * Si es un reset por Game Over, la velocidad de la bola vuelve a la inicial, y los puntos acumulados se eliminan.
     * Si es un reset por cambio de nivel, la velocidad de la bola aumenta y los puntos se mantienen.
     * @author Eduardo Bolívar
     */
    private void resetGame() {
        this.ballVector.clear();
        this.ballVector.add(new Ball(this.bar.getX(), this.bar.getY(), 10));
        Ball.stopMoving();
        if (this.gameOver) {
            points = 0;
            level = 1;
            this.ballVector.firstElement().resetSpeed();
            this.gameOver = false;
        }
        else {
            this.ballVector.firstElement().setSpeed(this.ballVector.firstElement().accelerate());
        }
        lives = 3;
        this.bar.restart(this.window.getSize().width/2 - 50, this.window.getSize().height - 80, 140, 10);
        for (Block b : this.blocks) {
            b.activate();
        }
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
        this.bar = new Bar(initX, initY, 140, 10);
        this.ballVector = new Vector<>();
        this.ballVector.add(new Ball(initX, initY - 10, 10));
        this.blocks = new ArrayList<>();

        int blockType;
        for (int i = 0; i < 8; i++) {
            switch (i) {
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
            for (int j = 0; j < 14; j++) {
                Block b = new Block(5 + ((this.window.getWidth()-10)/14)*j, 90 + 25*i, 83, 20, blockType);
                b.setMatrixId(i+1, j+1);
                this.blocks.add(b);
            }
        }
    }

    /**
     * initText:
     * Inicializa todos los mensajes visibles en pantalla.
     * Inicializa las fuentes, localización en pantalla, tamaño y color de letra.
     * @author Eduardo Bolívar
     */
    public void initText() {
        this.title = new JLabel("PRESS 'SPACE' TO START");
        this.title.setFont(new Font("Times",Font.BOLD,30));
        this.title.setBounds(this.window.getWidth()/2 - 200, this.window.getHeight()/2 - 150, 500, 300);
        this.title.setForeground(Color.white);

        this.gameOverText = new JLabel("");
        this.gameOverText.setFont(new Font("Times", Font.BOLD, 30));
        this.gameOverText.setBounds(this.window.getWidth()/2 - 100, this.window.getHeight()/2 - 150, 500, 300);

        this.pointsText = new JLabel();
        this.pointsText.setFont(new Font("Times", Font.BOLD, 20));
        this.pointsText.setBounds(50,30,150,20);
        pointsText.setForeground(Color.WHITE);

        this.livesText = new JLabel();
        this.livesText.setFont(new Font("Times", Font.BOLD, 20));
        this.livesText.setBounds(this.window.getWidth()/2-75,30, 150, 20);
        this.livesText.setForeground(Color.WHITE);

        this.levelText = new JLabel();
        this.levelText.setFont(new Font("Times", Font.BOLD, 20));
        this.levelText.setBounds(1100,30,150,20);
        this.levelText.setForeground(Color.WHITE);

        this.add(title);
        this.add(gameOverText);
        this.add(pointsText);
        this.add(livesText);
        this.add(levelText);
    }

    /**
     * removeLife:
     * Método estático que le resta una vida al jugador.
     * @author Eduardo Bolívar
     */
    public static void removeLife() {
        Breakout.lives--;
    }

    /**
     * updateText:
     * Actualiza el texto en pantalla para mostrar los puntos, las vidas, y el nivel actual.
     * En caso de Game Over, se muestra un mensaje de Game Over y los puntos conseguidos.
     * @author Eduardo Bolívar
     */
    public void updateText() {
        pointsText.setText("SCORE: " + Breakout.points);
        livesText.setText("BALLS: " + Breakout.lives + "/3");
        levelText.setText("LEVEL: " + Breakout.level);
        if (!this.gameOver) {
            this.gameOverText.setText("");
        }
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
    public void collideBallBlock(Ball b1, ArrayList<Block> blocks) {
        Rectangle r1 = new Rectangle();
        Rectangle r2 = new Rectangle();
        r1.setBounds((int) b1.getX(), (int) b1.getY(), (int) b1.getWidth(), (int) b1.getHeight());
        for (Block b : blocks) {
            r2.setBounds((int) b.getX(), (int) b.getY(), (int) b.getWidth(), (int) b.getHeight());
            if (r1.intersectsLine(r2.getX(), r2.getY() + r2.getHeight(), r2.getX() + r2.getWidth(), r2.getY() + r2.getHeight()) && b.getAlive()) {
                b1.setUp(false);
                b.kill();
                points++;
                break;
            }
            else if (r1.intersectsLine(r2.getX(), r2.getY(), r2.getX() + r2.getWidth(), r2.getY()) && b.getAlive()) {
                b1.setUp(true);
                b.kill();
                points++;
                break;
            }
            else if (r1.intersectsLine(r2.getX() + r2.getWidth(), r2.getY(), r2.getX() + r2.getWidth(), r2.getY() + r2.getHeight()) && b.getAlive()) {
                b1.setRight(true);
                b.kill();
                points++;
                break;
            }
            else if (r1.intersectsLine(r2.getX(), r2.getY(), r2.getX(), r2.getY() + r2.getHeight()) && b.getAlive()) {
                b1.setRight(false);
                b.kill();
                points++;
                break;
            }
        }
    }

    /**
     * checkCollisions:
     * Verifica constantemente si existe una colisión bola-barra o colisión bola-bloques.
     * Llama a los métodos collideBallBar y collideBallBlock.
     * @author Eduardo Bolívar
     */
    public void checkCollisions() {
        for (Ball b : this.ballVector) {
            collideBallBar(b, this.bar);
            collideBallBlock(b, this.blocks);
        }
    }

    /**
     * checkBalls:
     * Actualiza la posición y estado de cada una de las bolas en pantalla.
     * Si una bola cae, llama a la función para eliminar una bola del vector, y resta una vida al jugador.
     * @param x la posición x de la barra en caso de reiniciar la última bola.
     * @param y la posición y de la barra en caso de reiniciar la última bola.
     * @param w la anchura de la barra en caso de reiniciar la última bola.
     * @author Eduardo Bolívar
     */
    public void checkBalls(double x, double y, double w) {
        for (Ball b : this.ballVector) {
            b.update_ball(x, y, w, this.ballVector.size());
            if (b.getY() >= 720) {
                removeLife();
                removeBall(b.getId());
                break;
            }
        }
    }

    /**
     * checkNextLevel:
     * Verifica si los bloques han sido destruidos.
     * Si es así, se pasa al siguiente nivel.
     * @author Eduardo Bolívar
     */
    public void checkNextLevel() {
        int remaining = getRemainingBlocks();
        if (remaining == 0) {
            nextLevel();
        }
    }

    /**
     * getRemainingBlocks:
     * Realiza una cuenta de los bloques que siguen sin destruir.
     * @return la cantidad de bloques que la bola no ha destruido.
     * @author Eduardo Bolívar
     */
    public int getRemainingBlocks() {
        int count = 0;
        for (Block b : this.blocks) {
            if (b.getAlive()) {
                count++;
            }
        }
        return count;
    }

    /**
     * nextLevel:
     * Se encarga de aumentar el nivel del juego.
     * Llama a la función resetGame en el contexto de un aumento de nivel.
     * @author Eduardo Bolívar
     */
    public void nextLevel() {
        level++;
        resetGame();
    }

    /**
     * addBall:
     * Añade una bola al vector de bolas y asigna un ID a esta nueva bola.
     * @author Eduardo Bolívar
     *
     */
    public void addBall() {
        Ball.id_0++;
        this.ballVector.add(new Ball(this.bar.getX() + (this.bar.getWidth()/2), this.bar.getY(), 10));
    }

    /**
     * removeBall:
     * Elimina la bola especificada por el parámetro, y ajusta los índices del resto de bolas para que coincidan con el orden del vector.
     * Lo anterior para facilitar el acceso.
     * @param a el índice de la bola a eliminar.
     * @author Eduardo Bolívar
     */
    public void removeBall(int a) {
        Ball.id_0--;
        this.ballVector.remove(a);
        for (int i = a; i < this.ballVector.size(); i++) {
            this.ballVector.get(i).setId(this.ballVector.get(i).getId() - 1);
        }
    }

    /**
     * update:
     * Actualiza constantemente los objetos del juego.
     * Actualiza la posición de la barra, la posición de la bola, y el estado de los bloques.
     * @author Eduardo Bolívar
     */
    public void update() {
        if (lives == 0) {
            this.gameOver = true;
        }
        this.bar.update_bar();
        this.checkBalls(this.bar.getX(), this.bar.getY(), this.bar.getWidth());
        this.checkCollisions();
        this.checkNextLevel();
        this.updateText();
    }

    /**
     * render:
     * Redibuja las actualizaciones realizadas por update para ser visualizadas en pantalla.
     * @author Eduardo Bolívar
     */
    public void render() {
        this.window.repaint();
    }

    /**
     * isGameOver:
     * @return true si el juego se encuentra en estado de Game Over, y false en caso contrario.
     * @author Eduardo Bolívar
     */
    public boolean isGameOver() {
        return this.gameOver;
    }

    /**
     * showGameOverMessage:
     * Muestra un texto alternante entre el mensaje de Game Over y la cantidad de puntos logrados.
     * @throws InterruptedException
     * @author Eduardo Bolívar
     */
    public void showGameOverMessage() throws InterruptedException {
        this.gameOverText.setText("GAME OVER");
        this.gameOverText.setForeground(Color.red);
        Thread.sleep(800);
        this.gameOverText.setText("SCORE: " + Breakout.points);
        this.gameOverText.setForeground(Color.yellow);
        Thread.sleep(800);
    }

    /**
     * modBarSize:
     * Modifica el tamaño de la barra de juego.
     * @param action bit que determina si se duplica el tamaño o si se reduce a la mitad.
     * @author Eduardo Bolívar
     */
    private void modBarSize(int action) {
        if (action == 0 && this.bar.getWidth() >= 70) {
            this.bar.setWidth(this.bar.getWidth()/2);
        }
        else if (action == 1 && this.bar.getWidth() <= 280) {
            this.bar.setWidth(2*this.bar.getWidth());
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
            Ball.startMoving();
            this.title.setText("");
        }
        else if (e.getKeyCode() == 10 && gameOver) {
            resetGame();
        }
        else if (e.getKeyCode() == 27) {
            addBall();
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
        for (Ball ball : this.ballVector) {
            g2d.fillOval((int) ball.getX(), (int) ball.getY(), (int) ball.getWidth(), (int) ball.getHeight());
        }

        for (Block block : this.blocks) {
            if (block.getType() == 1) {
                g2d.setColor(Color.red);
            }
            else if (block.getType() == 2) {
                g2d.setColor(Color.ORANGE);
            }
            else if (block.getType() == 3) {
                g2d.setColor(Color.YELLOW);
            }
            else if (block.getType() == 4) {
                g2d.setColor(Color.GREEN);
            }
            if (block.getAlive()) {
                g2d.fill3DRect((int) block.getX(), (int) block.getY(), (int) block.getWidth(), (int) block.getHeight(), true);
            }
        }
    }
}
