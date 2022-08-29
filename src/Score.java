import org.w3c.dom.Text;

import java.awt.*;

public class Score {
    public int left;
    public int right;

    private Font font;

    public Score() {
        this.left = 0;
        this.right = 0;
        font = new Font("Times New Roman", Font.PLAIN, 50);
    }
    public void scoreLeft() {
        this.left++;
    }
    public void scoreRight() {
        this.right++;
    }
    public void reset() {
        this.left = 0;
        this.right = 0;
    }
    public void draw(Graphics2D g2) {
        g2.setColor(Color.WHITE);
        g2.setFont(font);
        g2.drawString("" + left, 100,100);
        g2.drawString("" + right, Constants.SCREEN_WIDTH - 100, 100);
    }
}