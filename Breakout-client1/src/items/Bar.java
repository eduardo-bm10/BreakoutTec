package items;

public class Bar extends Object {
    private boolean right;
    private boolean left;
    public Bar(int x, int y, int width, int height) {
        super(x - (width/2), y, width, height);
        this.right = false;
        this.left = false;
    }

    public void update_bar() {
        if (right) {
            this.x = this.x + 10;
            if (this.x >= (1250 - this.width)) {
                this.x = 1250 - this.width;
            }
        }
        else if (left) {
            this.x = this.x - 10;
            if (this.x <= 0) {
                this.x = 0;
            }
        }
    }

    public void move(int key) {
        if (key == 37) {
            this.left = true;
        }
        else if (key == 39) {
            this.right = true;
        }
    }

    public void stop(int key) {
        if (key == 37) {
            this.left = false;
        }
        else if (key == 39) {
            this.right = false;
        }
    }


}
