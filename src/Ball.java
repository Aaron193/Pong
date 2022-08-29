import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.*;

public class Ball {

    public Rect rect;
    public Rect leftPaddle, rightPaddle;
    public Score score;

    private int speed = 400;
    public double velX = -1;
    private double velY = 0;

    public Ball (Rect rect, Rect leftPaddle, Rect rightPaddle, Score score) {
        this.rect = rect;
        this.leftPaddle = leftPaddle;
        this.rightPaddle = rightPaddle;
        this.score = score;
    }
        public void update(double dt) {

        if (wallCollision()) {
            if (velX > 0) score.scoreLeft();
            else score.scoreRight();
            newRound();
        }
        else if (verticalCollision()) velY *= -1;
        else if (leftPaddleCollision()) {
            double angle = Math.atan2(rect.y + (rect.height/2) - (leftPaddle.y + leftPaddle.height/2), rect.x + (rect.width/2) - (leftPaddle.x + leftPaddle.width/2));
            velX = Math.cos(angle/1.5);
            velY = Math.sin(angle/1.5);
        } else if (rightPaddleCollision()) {
            double angle = Math.atan2((rightPaddle.y + rightPaddle.height/2) - rect.y + (rect.height/2),(rightPaddle.x + rightPaddle.width/2) - rect.x + (rect.width/2));
            System.out.println(angle);
            velX = -Math.cos(angle/1.5);
            velY = -Math.sin(angle/1.5);
        }

        this.rect.x += velX * speed * dt;
        this.rect.y += velY * speed * dt;

    }
    private void newRound() {
        velX = 0;
        velY = 0;
        rect.x = Constants.SCREEN_WIDTH/2;
        rect.y = Constants.SCREEN_HEIGHT/2;
        setTimeout(() -> {
            double randomAngle = Math.random() * Math.PI * 2;
            velX = Math.cos(randomAngle);
            velY = Math.sin(randomAngle);
        }, 1000);

    }
    private boolean wallCollision() {
        return rect.x + (rect.width*2) >= Constants.SCREEN_WIDTH || rect.x - rect.width <= 0;
    }
    private boolean verticalCollision() {
        return rect.y + (rect.height*2) >= Constants.SCREEN_HEIGHT || rect.y <= Constants.TOOLBAR_HEIGHT;
    }
    private boolean leftPaddleCollision() {
        return rect.x <= leftPaddle.x + leftPaddle.width && rect.y + rect.height >= leftPaddle.y && rect.y <= leftPaddle.y + leftPaddle.height;
    }
    private boolean rightPaddleCollision() {
        return rect.x + rect.width >= rightPaddle.x && rect.y + rect.height >= rightPaddle.y && rect.y <= rightPaddle.y + rightPaddle.height;
    }
    private static void setTimeout(Runnable runnable, int delay){
        new Thread(() -> {
            try {
                Thread.sleep(delay);
                runnable.run();
            }
            catch (Exception e){
                System.err.println(e);
            }
        }).start();
    }
}
