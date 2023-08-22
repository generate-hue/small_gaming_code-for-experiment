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
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class SnakeActivity extends AppCompatActivity {
    private SnakeGame snakeGame;
    private SnakeView snakeView;
    private Handler handler;
    private final long delay = 100; // milliseconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snake);

        snakeView = findViewById(R.id.snakeView);
        snakeGame = new SnakeGame();

        handler = new Handler();
        handler.postDelayed(gameLoopRunnable, delay);
    }

    private Runnable gameLoopRunnable = new Runnable() {
        @Override
        public void run() {
            if (!snakeGame.isGameOver()) {
                snakeGame.update();
                snakeView.setSnakeGame(snakeGame);
                snakeView.invalidate();
                handler.postDelayed(this, delay);
            } else {
                // Handle game over
            }
        }
    };
}
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".SnakeActivity">

    <com.example.snakegame.SnakeView
        android:id="@+id/snakeView"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />
</RelativeLayout>
