package com.ballgame.snake.snakeballgame;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int positionX = -1;
    int positionY = -1;
    int scoreAndRadius = 30;

    int deltaX = 0;
    int deltaY = 0;

    int newDotX = 0;
    int newDotY = 0;
    int newDotRadius = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final GameBoard gameBoard = (GameBoard) findViewById(R.id.game_board);
        final TextView scoreTextView = (TextView) findViewById(R.id.score);

        new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                positionX += deltaX;
                positionY += deltaY;

                int height = gameBoard.getHeight();
                positionY = Math.max(0, positionY);
                positionY = Math.min(height, positionY);

                int width = gameBoard.getWidth();
                positionX = Math.max(0, positionX);
                positionX = Math.min(width, positionX);

                if (gameBoard.isAttachedToWindow()
                        && Math.sqrt(Math.pow((positionX-newDotX), 2) + Math.pow((positionY-newDotY), 2)) < (newDotRadius + scoreAndRadius)) {
                    scoreAndRadius += 10;
                    Random random = new Random();
                    newDotY = random.nextInt(height);
                    newDotX = random.nextInt(width);
                }

                scoreTextView.setText("Score: " + scoreAndRadius);

                gameBoard.updateState(positionX, positionY, scoreAndRadius);
                gameBoard.setDot(newDotX, newDotY, newDotRadius);
                gameBoard.invalidate();
                this.sendEmptyMessageDelayed(0, 50);
            }
        }.sendEmptyMessageDelayed(0 , 50);

        findViewById(R.id.upButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deltaY = Math.max(deltaY - 10, -10);
            }
        });

        findViewById(R.id.downButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deltaY = Math.min(deltaY + 10, 10);
            }
        });

        findViewById(R.id.leftButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deltaX = Math.max(deltaX - 10, -10);
            }
        });

        findViewById(R.id.rightButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deltaX = Math.min(deltaX + 10, 10);
            }
        });
    }
}
