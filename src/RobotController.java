public class RobotController {
    private Rect rightPaddle;
    private Ball ball;
    public RobotController(Rect rightPaddle, Ball ball) {
        this.rightPaddle = rightPaddle;
        this.ball = ball;
    }

    public void update(double dt) {
        if (ball.velX > 0) {
            if (ball.rect.y > rightPaddle.y + rightPaddle.height / 2) rightPaddle.y += Constants.ROBOT_SPEED * dt;
            else rightPaddle.y -= Constants.ROBOT_SPEED * dt;
        } else {
            if (rightPaddle.y + rightPaddle.height/2 > Constants.SCREEN_HEIGHT/2) rightPaddle.y -= Constants.ROBOT_SPEED * dt;
            else rightPaddle.y += Constants.ROBOT_SPEED * dt;
        }

        if (rightPaddle.y + rightPaddle.height >= Constants.SCREEN_HEIGHT) {
            rightPaddle.y = Constants.SCREEN_HEIGHT - rightPaddle.height;
        } else if (rightPaddle.y <= 0 ) {
            rightPaddle.y = 0;
        }

    }

}
