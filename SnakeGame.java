public class SnakeGame {
    private int score;
    private boolean gameOver;
    private Snake snake;
    private Food food;

    public SnakeGame() {
        snake = new Snake();
        food = new Food();
        score = 0;
        gameOver = false;
    }

    public void update() {
        snake.move();
        if (snake.collidesWithFood(food)) {
            snake.grow();
            score += 10;
            food.randomizePosition();
        }
        if (snake.collidesWithWall() || snake.collidesWithItself()) {
            gameOver = true;
        }
    }

    // Define getters and setters for score, gameOver, etc.
}
