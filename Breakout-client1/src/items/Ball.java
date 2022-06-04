package items;

public class Ball extends Object {
    private boolean moving, right, up;
    public Ball(int x, int y, int diameter) {
        super(x, y, diameter, diameter);
        this.moving = false;
        this.right = true;
        this.up = true;
    }

    private void restartPosition(int barX, int barY) {
        this.x = barX;
        this.y = barY;
        this.moving = false;
        this.right = true;
        this.up = true;
    }

    public void startedMoving(int key) {
        if (key == 32) {
            this.moving = true;
        }
    }

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

    public void setRight(boolean right) {
        this.right = right;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

}
