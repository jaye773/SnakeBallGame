package com.ballgame.snake.snakeballgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class GameBoard extends View {

    Paint bluePaint = new Paint();
    Paint redPaint = new Paint();
    int positionX;
    int positionY;
    int radius;

    int newDotX;
    int newDotY;
    int newDotRadius;

    public GameBoard(Context context) {
        super(context);
    }

    public GameBoard(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public GameBoard(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        bluePaint.setColor(Color.BLUE);
        redPaint.setColor(Color.RED);

        canvas.drawCircle(positionX, positionY, radius, bluePaint);

        canvas.drawCircle(newDotX, newDotY, newDotRadius, redPaint);
    }

    public void updateState(int positionX, int positionY, int radius) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.radius = radius;
    }

    public void setDot(int newDotX, int newDotY, int newDotRadius) {
        this.newDotX = newDotX;
        this.newDotY = newDotY;
        this.newDotRadius = newDotRadius;
    }
}
