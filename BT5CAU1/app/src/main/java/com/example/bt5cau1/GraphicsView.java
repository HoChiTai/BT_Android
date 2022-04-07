package com.example.bt5cau1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

public class GraphicsView extends View {
    public GraphicsView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Rect r = new Rect(40, 40, 400, 200);
        Paint p = new Paint();
        p.setStyle(Paint.Style.FILL);
        p.setColor(Color.RED);
        canvas.drawRect(r, p);

        invalidate();
    }
}
