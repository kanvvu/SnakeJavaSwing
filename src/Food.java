import java.awt.Color;
import java.util.Random;
import java.awt.*;

public class Food {
    private int x;
    private int y;
    private Color color;

    public Food(){
        Random rnd = new Random();

        int offset = rnd.nextInt(35);
        int rnd2 = rnd.nextInt(75);

        this.color = new Color(200 + rnd.nextInt(55),rnd2 + offset, rnd2);

        this.x = rnd.nextInt(20);
        this.y = rnd.nextInt(18);
    }

    public void draw(Graphics g,int x,int y) {
        g.setColor(color);
        g.fillOval(this.x*30 + x,this.y*30 + y,30,30);
    }

    public int getX() {
        return x*30;
    }

    public int getY() {
        return y*30;
    }
}
