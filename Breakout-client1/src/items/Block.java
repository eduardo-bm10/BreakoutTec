package items;

public class Block extends Object {
    private int type;
    private boolean alive;
    public Block(int x, int y, int width, int height, int type) {
        super(x,y,width,height);
        this.type = type;
        this.alive = true;
    }

    public int getType() {
        return this.type;
    }

    public boolean getAlive() {
        return this.alive;
    }

    public void kill() {
        this.alive = false;
    }
}
