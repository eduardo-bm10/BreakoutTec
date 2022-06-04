package game;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import items.Ball;
import items.Bar;
import items.Block;

public class Breakout extends JPanel implements KeyListener {
    private JFrame window;
    private Bar bar;
    private Ball ball;
    private ArrayList<Block> blocks;
    private boolean running;

    public Breakout() {
        this.initWindow();
        int initX = this.window.getSize().width / 2;
        int initY = this.window.getSize().height - 80;
        this.initObjects(initX, initY);
        this.running = true;
        this.window.add(this);
        this.window.addKeyListener(this);

        this.setBackground(Color.DARK_GRAY);
        this.setVisible(true);
    }

    private void initWindow() {
        this.window = new JFrame("Breakout");
        this.window.setSize(1250,720);
        this.window.setResizable(false);
        this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.window.setVisible(true);
    }

    private void initObjects(int initX, int initY) {
        this.bar = new Bar(initX, initY, 100, 10);
        this.ball = new Ball(initX + 50, initY - 10, 10);
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
            for (int p = 0; p < 14; p++) {
                Block b = new Block(5 + 88*p, 90 + 25*i, 83, 20, blockType);
                this.blocks.add(b);
            }
        }
    }

    public void collideBallBar(Ball b1, Bar b2) {
        Rectangle r1 = new Rectangle();
        Rectangle r2 = new Rectangle();
        r1.setBounds(b1.getX(), b1.getY(), b1.getWidth(), b1.getHeight());
        r2.setBounds(b2.getX(), b2.getY(), b2.getWidth(), b2.getHeight());
        if (r1.intersects(r2)) {
            b1.setUp(true);
        }
    }

    private void collideBallBlock(Ball b1, ArrayList<Block> blocks) {
        Rectangle r1 = new Rectangle();
        Rectangle r2 = new Rectangle();
        r1.setBounds(b1.getX(), b1.getY(), b1.getWidth(), b1.getHeight());
        for (Block b : blocks) {
            r2.setBounds(b.getX(), b.getY(), b.getWidth(), b.getHeight());
            if (r1.intersectsLine(r2.getX(), r2.getY() + r2.getHeight(), r2.getX() + r2.getWidth(), r2.getY() + r2.getHeight()) && b.getAlive()) {
                b1.setUp(false);
                b.kill();
                break;
            }
            else if (r1.intersectsLine(r2.getX(), r2.getY(), r2.getX() + r2.getWidth(), r2.getY()) && b.getAlive()) {
                b1.setUp(true);
                b.kill();
                break;
            }
            else if (r1.intersectsLine(r2.getX() + r2.getWidth(), r2.getY(), r2.getX() + r2.getWidth(), r2.getY() + r2.getHeight()) && b.getAlive()) {
                b1.setRight(true);
                b.kill();
                break;
            }
            else if (r1.intersectsLine(r2.getX(), r2.getY(), r2.getX(), r2.getY() + r2.getHeight()) && b.getAlive()) {
                b1.setRight(false);
                b.kill();
                break;
            }
        }
    }

    public boolean isRunning() {
        return this.running;
    }

    private void checkCollisions() {
        collideBallBar(this.ball, this.bar);
        collideBallBlock(this.ball, this.blocks);
    }

    public void update() {
        this.bar.update_bar();
        this.ball.update_ball(this.bar.getX(), this.bar.getY());
        this.checkCollisions();
    }

    public void render() {
        this.window.repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {;
        this.bar.move(e.getKeyCode());
        this.ball.startedMoving(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        this.bar.stop(e.getKeyCode());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.blue);
        g2d.fill3DRect(this.bar.getX(), this.bar.getY(), this.bar.getWidth(), this.bar.getHeight(),true);

        g2d.setColor(Color.white);
        g2d.fillOval(this.ball.getX(), this.ball.getY(), this.ball.getWidth(), this.bar.getHeight());

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
                g2d.fill3DRect(b.getX(), b.getY(), b.getWidth(), b.getHeight(), true);
            }
        }
    }
}
