package com.example.bt5cau3;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.view.MotionEvent;
import android.view.View;

public class GraphicsView extends View {
    Bitmap[] frames = new Bitmap[6];
    int i = 0;

    public GraphicsView(Context context) {
        super(context);
        frames[0] = BitmapFactory.decodeResource(getResources(), R.drawable.win_1);
        frames[1] = BitmapFactory.decodeResource(getResources(), R.drawable.win_2);
        frames[2] = BitmapFactory.decodeResource(getResources(), R.drawable.win_3);
        frames[3] = BitmapFactory.decodeResource(getResources(), R.drawable.win_4);
        frames[4] = BitmapFactory.decodeResource(getResources(), R.drawable.win_5);
        frames[5] = BitmapFactory.decodeResource(getResources(), R.drawable.win_6);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (i < 6) {
            canvas.drawBitmap(frames[i], 0, 0, new Paint());
        }
        else {
            i = 0;
            canvas.drawBitmap(frames[i], 0, 0, new Paint());
        }
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP ) {
            i++;
        }
        return true;
    }
}
